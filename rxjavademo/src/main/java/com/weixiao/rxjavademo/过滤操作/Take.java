package com.weixiao.rxjavademo.过滤操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Take {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 只发射前N项数据或者指定时长内(小于)的数据，忽略后面的数据，然后发射完成通知
         *
         integer = [1]
         integer = [2]
         integer = [3]
         integer = [4]
         integer = [5]
         integer = [6]
         integer = [7]
         integer = [8]
         integer = [9]
         integer = [10]
         */
        Observable.range(1,20).take(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        Observable.interval(1,TimeUnit.SECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });

        Thread.sleep(10000);
    }
}
