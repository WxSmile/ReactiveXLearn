package com.weixiao.rxjavademo.辅助操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class TimeStamp {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 将一个发射T类型数据的Observable转换为一个发射类型为Timestamped<T>的数据的Observable，
         * 每一项都包含数据的原始发射时间。
         *
         longTimestamped = [Timestamped(timestampMillis = 1479923527526, value = 0)]
         longTimestamped = [Timestamped(timestampMillis = 1479923528510, value = 1)]
         longTimestamped = [Timestamped(timestampMillis = 1479923529510, value = 2)]
         longTimestamped = [Timestamped(timestampMillis = 1479923530510, value = 3)]
         longTimestamped = [Timestamped(timestampMillis = 1479923531511, value = 4)]
         */
        Observable.interval(1, TimeUnit.SECONDS).take(5)
                .timestamp().subscribe(new Action1<Timestamped<Long>>() {
            @Override
            public void call(Timestamped<Long> longTimestamped) {
                System.out.println("longTimestamped = [" + longTimestamped + "]");
            }
        });

        Thread.sleep(10000);
    }
}
