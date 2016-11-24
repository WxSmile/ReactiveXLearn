package com.weixiao.rxjavademo.创建操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Repeat {

    public static void main(String[] args) {

        Observable.just(1,2,3,4).repeat(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = " + e);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        Observable.just(1,2).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return Observable.interval(1, TimeUnit.SECONDS);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = " + e);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        //---
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }

        /**

         integer = [1]
         integer = [2]
         integer = [3]
         integer = [4]
         integer = [1]
         integer = [2]
         integer = [3]
         integer = [4]
         completed
         integer = [1]
         integer = [2]
         integer = [1]
         integer = [2]
         integer = [1]
         integer = [2]
         integer = [1]
         integer = [2]

         */
    }
}
