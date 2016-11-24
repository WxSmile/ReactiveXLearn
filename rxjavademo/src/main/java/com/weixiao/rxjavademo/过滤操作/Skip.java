package com.weixiao.rxjavademo.过滤操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Skip {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 忽略发射的前N项数据或者指定时长内的数据
         *
         integer = [4]
         integer = [5]
         integer = [6]
         aLong = [3]
         aLong = [4]
         aLong = [5]
         aLong = [6]
         aLong = [7]
         aLong = [8]
         aLong = [9]
         */

        Observable.just(1,2,3,4,5,6)
                .skip(3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        Observable.interval(1, TimeUnit.SECONDS)
                .skip(4,TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });

        Thread.sleep(10000);
    }
}
