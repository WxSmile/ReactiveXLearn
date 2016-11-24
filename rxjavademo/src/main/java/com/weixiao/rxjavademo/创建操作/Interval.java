package com.weixiao.rxjavademo.创建操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Interval {

    public static void main(String[] args) {

        Observable.interval(2,2, TimeUnit.SECONDS)
                  .subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.printf("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Throwable = " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("aLong = " + aLong);
            }
        });

        //-----
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }

    /**
     * 创建一个按固定时间间隔发射一个无限递增的整数序列的可观察对象。
     *
     aLong = 0
     aLong = 1
     aLong = 2
     aLong = 3
     aLong = 4
     */
}
