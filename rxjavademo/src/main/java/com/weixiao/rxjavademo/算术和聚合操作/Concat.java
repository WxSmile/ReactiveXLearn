package com.weixiao.rxjavademo.算术和聚合操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class Concat {

    public static void main(String[] args) throws InterruptedException {


        /**
         * Concat操作符连接多个Observable的输出，
         * 就好像它们是一个Observable，
         * 第一个Observable发射的所有数据在第二个Observable发射的任何数据前面
         *
         aLong = [0]
         aLong = [1]
         aLong = [2]
         aLong = [3]
         aLong = [4]
         aLong = [5]
         aLong = [6]
         aLong = [7]
         aLong = [8]
         aLong = [9]
         aLong = [0]
         aLong = [1]
         aLong = [2]
         aLong = [3]
         aLong = [4]
         */

        Observable<Long> interval = Observable.interval(100, TimeUnit.MILLISECONDS).take(10);
        Observable<Long> interval1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(5);
        Observable.concat(interval,interval1).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });

        Thread.sleep(10000);

    }
}
