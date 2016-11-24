package com.weixiao.rxjavademo.条件和布尔操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/24.
 */
public class SkipUntil_SkipWhile {

    public static void main(String[] args) throws InterruptedException {


        /**
         * skipUtil订阅原始Observable，忽略他的发射物，直到第二个Observable发射了一项数据那一刻。

         aLong = [3]
         aLong = [4]
         aLong = [5]
         aLong = [6]
         aLong = [7]
         aLong = [8]
         aLong = [9]
         aLong = [10]
         */
        Observable<Long> interval = Observable.interval(500, TimeUnit.MILLISECONDS).take(10);
//        Observable<Long> interval1 = Observable.interval(2000, TimeUnit.MILLISECONDS);
//        interval.skipUntil(interval1)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        System.out.println("aLong = [" + aLong + "]");
//                    }
//                });


        /**
         * 忽略原始Observable发射的数据，直到你制定的某个条件变为false的那一刻
         * 开始发射原始Observable的数据
         *
         aLong = [7]
         aLong = [8]
         aLong = [9]
         */
        interval.skipWhile(new Func1<Long, Boolean>() {
            @Override
            public Boolean call(Long aLong) {
                if(aLong==7)
                    return false;
                return true;
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });


        Thread.sleep(10000);
    }
}
