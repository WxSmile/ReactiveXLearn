package com.weixiao.rxjavademo.过滤操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Sample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 发射 间隔时间点 最近已发射的数据
         */
        Observable.interval(1, TimeUnit.SECONDS)
                .sample(5,TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });

        Thread.sleep(20000);
    }
}
