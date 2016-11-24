package com.weixiao.rxjavademo.辅助操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class Timeout {

    public static void main(String[] args) throws InterruptedException {
        /**
         每当  "原始Observable"  发射了一项数据，timeout就启动一个计时器，
         如果计时器超过了指定指定的时长而原始Observable没有发射另一项数据，
         timeout就抛出TimeoutException，以一个错误通知终止Observable。

         e = [java.util.concurrent.TimeoutException]

         */

        Observable.interval(2,TimeUnit.SECONDS)
                .timeout(1,TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.printf("cmp");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });

        Thread.sleep(10000);
    }
}
