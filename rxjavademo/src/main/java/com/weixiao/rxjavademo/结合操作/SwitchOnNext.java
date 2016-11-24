package com.weixiao.rxjavademo.结合操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class SwitchOnNext {
    public static void main(String[] args) throws InterruptedException {


        /**
         * 将一组Observables转换为一个Observable，规则是：
         * 对于这些Observable发射的数据，如果同一时间内存在两个或多个发射的数据，只取最后一个Observable发射的数据
         *
         aLong = [0]
         aLong = [10]
         aLong = [20]
         aLong = [0] //和第一个Observable 的 30 存在同一时间内
         aLong = [10]
         aLong = [20]
         aLong = [30]
         aLong = [40]
         */
        Observable<Observable<Long>> observable = Observable.interval(0, 500, TimeUnit.MILLISECONDS).map(new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(final Long num) {

                return Observable.interval(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);
            }
        }).take(2);

        Observable.switchOnNext(observable).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });

        Thread.sleep(10000);
    }
}
