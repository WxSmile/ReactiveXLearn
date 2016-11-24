package com.weixiao.rxjavademo.创建操作;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Defer {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> obsever) {
                        try {
                            if (!obsever.isUnsubscribed()) {//如果没有被取消订阅
                                for (int i = 0; i < 5; i++) {
                                    obsever.onNext(i);
                                }
                            }
                            obsever.onCompleted();
                        } catch (Exception e) {
                            obsever.onError(e);
                        }
                    }
                });
            }
        });

        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e.getMessage() + "]");
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Next item = [" + item + "]");
            }
        });
    }

    /**
     * 直到有观察者订阅之后才会创建可观察者对象，并且每一个观察者收到的可观察者对象都是新的
     *
     * 输出结果
     integer = 0
     integer = 1
     integer = 2
     integer = 3
     integer = 4
     Next item = [0]
     Next item = [1]
     Next item = [2]
     Next item = [3]
     Next item = [4]
     Sequence complete.
     */
}
