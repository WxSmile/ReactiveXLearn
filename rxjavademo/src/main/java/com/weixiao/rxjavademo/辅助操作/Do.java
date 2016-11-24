package com.weixiao.rxjavademo.辅助操作;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class Do {
    public static void main(String[] args) {


        /**
         * 每当Observable发射一个数据，就调用一次以onNext的变体Notification为参数的回调
         */
//        Observable.range(1,5).doOnEach(new Action1<Notification<? super Integer>>() {
//            @Override
//            public void call(Notification<? super Integer> notification) {
//                System.out.println("notification = [" + notification.getValue() + "]");
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        });


        /**
         * 每当Observable发射一个数据，就调用一次以发射的数据为参数的回掉函数
         */
//        Observable.range(1,5).doOnNext(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("Do.call");
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        });

        /**
         * 每当观察者订阅这个Observable时就会调用一次这个无参数的回调函数
         *
         */
//        Observable.range(1,5).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//                System.out.println("Do.call on Subscirbe");
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        });


        /**
         * 每当Observable正常终止的调用onCompleted的时候调用这个无参数的回调函数
         *
         integer = [1]
         Do.call on CMP
         cmp
         *
         * doOnError 原理和doOnCompleted一样,只是在异常终止时调用
         *
         * doOnTerminate 无论正常或者异常终止之前调用
         */
//        Observable.just(1).doOnCompleted(new Action0() {
//            @Override
//            public void call() {
//                System.out.println("Do.call on CMP");
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                System.out.println("throwable = [" + throwable + "]");
//            }
//        }, new Action0() {
//            @Override
//            public void call() {
//                System.out.println("cmp");
//            }
//        });

//        Observable.just(1).doOnNext(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                throw new RuntimeException("error");
//            }
//        }).doOnTerminate(new Action0() {
//            @Override
//            public void call() {
//                System.out.println("Do.call termimnate");
//            }
//        }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("cmp");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("e = [" + e + "]");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        });

        /**
         * 当Observable终止后调用，无论异常还是正常终止
         */
        Observable.just(1).finallyDo(new Action0() {
            @Override
            public void call() {
                System.out.println("Do.call finalDo");
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("cmp");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
    }
}
