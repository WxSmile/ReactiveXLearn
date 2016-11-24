package com.weixiao.rxjavademo.错误处理;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Catch {
    public static void main(String[] args) {


        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i == 4) {
                        try {
                            throw new Exception("this is a error");
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });

        /**
         * 在Observable发生错误或者异常的时候（即将回调onError方法），拦截错误并执行指定逻辑
         * 返回一个和源Observable类型相同的结果
         *
         integer = [0]
         integer = [1]
         integer = [2]
         integer = [3]
         throwable = [java.lang.Exception: this is a error] onErrorReturn do my self
         integer = [1004]
         cmp
         */
        observable.onErrorReturn(new Func1<Throwable, Integer>() {
            @Override
            public Integer call(Throwable throwable) {
                System.out.println("throwable = [" + throwable + "] onErrorReturn do my self");
                return 1004;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("cmp");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        /**
         * 返回一个和源Observable类型相同的Observable
         *
         * 不同的是onErrorResumeNext拦截的是错误或者异常
         * 而onExceptionResumeNext拦截的只是异常
         *
         integer = [0]
         integer = [1]
         integer = [2]
         integer = [3]
         throwable = [java.lang.Exception: this is a error] onErrorResumeNext do my self
         integer = [100]
         integer = [101]
         integer = [102]
         cmp
         */
//        observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
//            @Override
//            public Observable<? extends Integer> call(Throwable throwable) {
//                System.out.println("throwable = [" + throwable + "] onErrorResumeNext do my self");
//                return Observable.just(100,101,102);
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

//        observable.onExceptionResumeNext(Observable.just(100,101,102)).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                System.out.printf("cmp");
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
    }
}
