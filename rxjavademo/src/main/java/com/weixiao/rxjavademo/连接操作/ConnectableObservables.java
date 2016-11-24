package com.weixiao.rxjavademo.连接操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class ConnectableObservables {

    public static void main(String[] args) throws InterruptedException {

        /**
         * publish 将普通的Observable转换为可连接的Observable         *
         * 可连接的Observable在被订阅时并不开始发射数据，只有在它的connect()被调用时才开始。
         *
         */

        ConnectableObservable<Integer> connectableObservable = Observable.range(1, 5).publish();

        /**
         * connect
         * 让一个可连接的Observable开始发射数据给订阅者
         *
         sub#2 = [1]
         sub#1 = [1]
         sub#2 = [2]
         sub#1 = [2]
         sub#2 = [3]
         sub#1 = [3]
         sub#2 = [4]
         sub#1 = [4]
         sub#2 = [5]
         sub#1 = [5]
         */

//        connectableObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("sub#2 = [" + integer + "]");
//            }
//        });
//        connectableObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("sub#1 = [" + integer + "]");
//            }
//        });
//
//        connectableObservable.connect();

        /**
         * refCount  将一个可连接的Observable转化为普通的Observable
         * 这个转化的过程是自动的
         * 即：统计订阅【可连接Observable】的观察者，直到这些观察者都完成时，断开与【可连接Observable】的连接
         *      之后订阅了 【普通Observable】的订阅者可以接收到数据。
         *
         *
         sub#2 = [1]
         sub#1 = [1]
         sub#2 = [2]
         sub#1 = [2]
         sub#2 = [3]
         sub#1 = [3]
         sub#2 = [4]
         sub#1 = [4]
         sub#2 = [5]
         sub#1 = [5]
         sub# ref#1 = [1]
         sub# ref#1 = [2]
         sub# ref#1 = [3]
         sub# ref#1 = [4]
         sub# ref#1 = [5]
         sub# ref#2 = [1]
         sub# ref#2 = [2]
         sub# ref#2 = [3]
         sub# ref#2 = [4]
         sub# ref#2 = [5]
         */

//        Observable<Integer> ordinaryObservable = connectableObservable.refCount();
//        ordinaryObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("sub# ref#1 = [" + integer + "]");
//            }
//        });
//
//        ordinaryObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("sub# ref#2 = [" + integer + "]");
//            }
//        });


        /**
         * Replay操作符返回一个Connectable Observable 对象并且可以缓存其发射过的数据，
         * 这样即使有订阅者在其发射数据之后进行订阅也能收到其之前发射过的数据。
         *
         sub replay#1 = [1]
         sub replay#1 = [2]
         sub replay#1 = [3]
         sub replay#2 = [1]
         sub replay#2 = [2]
         sub replay#2 = [3]
         sub replay#1 = [4]
         sub replay#2 = [4]
         sub replay#1 = [5]
         sub replay#2 = [5]
         sub replay#1 = [6]
         sub replay#2 = [6]
         */
        final ConnectableObservable<Integer> replay = Observable.range(1, 6).replay();
        replay.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("sub replay#1 = [" + integer + "]");
                if(integer==3)
                    replay.subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            System.out.println("sub replay#2 = [" + integer + "]");
                        }
                    });

            }
        });
        replay.connect();

        Thread.sleep(10000);

    }
}
