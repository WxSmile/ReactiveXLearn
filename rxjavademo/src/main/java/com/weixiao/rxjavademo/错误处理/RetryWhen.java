package com.weixiao.rxjavademo.错误处理;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class RetryWhen {
    public static void main(String[] args) throws InterruptedException {


        /**
         * 当源Observable出现错误或者异常时，回调retryWhen返回的Observable
         * 如果retryWhen返回的Observable没有错误或者异常时，就会重新执行源Observable的逻辑
         * 否则执行onError方法
         *
         subscribing
         delay retry by 1 second(s)
         subscribing
         delay retry by 2 second(s)
         subscribing
         delay retry by 3 second(s)
         subscribing
         cmp
         */
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("subscribing");
                subscriber.onError(new RuntimeException("always fails"));
            }
        });

        observable.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.zipWith(Observable.range(1, 3), new Func2<Throwable, Integer, Integer>() {
                    @Override
                    public Integer call(Throwable throwable, Integer integer) {
                        return integer;
                    }
                }).flatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        System.out.println("delay retry by " + integer + " second(s)");
                        //每一秒中执行一次
                        return Observable.timer(integer, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.printf("cmp");
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

        Thread.sleep(10000);

    }
}
