package com.weixiao.rxjavademo.条件和布尔操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/24.
 */
public class TakeUntil_TakeWhile {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 当第二个Observable发射了一个数据或者终止时，丢弃原始Observable接下来发射的任何数据
         *
         aLong = [0]
         aLong = [1]
         aLong = [2]
         */
        Observable<Long> interval = Observable.interval(500, TimeUnit.MILLISECONDS).take(10);
//        Observable<Long> interval1 = Observable.interval(2000, TimeUnit.MILLISECONDS);
//        interval.takeUntil(interval1)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        System.out.println("aLong = [" + aLong + "]");
//                    }
//                });

        /**
         * 发射Observable的数据，直到一个指定的条件不成立，停止发射原始Observable，并终止自己的Observable
         aLong = [0]
         aLong = [1]
         aLong = [2]
         aLong = [3]
         aLong = [4]
         aLong = [5]
         aLong = [6]
         */
        interval.takeWhile(new Func1<Long, Boolean>() {
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
