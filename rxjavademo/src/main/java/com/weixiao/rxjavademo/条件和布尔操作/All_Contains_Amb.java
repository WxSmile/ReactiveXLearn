package com.weixiao.rxjavademo.条件和布尔操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/24.
 */
public class All_Contains_Amb {

    public static void main(String[] args) throws InterruptedException {


        /**
         * All操作符判断Observable所发射的所有数据是否都满足某个条件
         * 发射一个单个布尔值的Observable
         * 如果每一项数据都曼珠条件，这发射的数据是true，否则false。
         *
         * aBoolean = [true]
         */
        Observable.just(1,2,3,4)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer>0;
                    }
                })
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println("aBoolean = [" + aBoolean + "]");
                    }
                });

        /**
         * 判定一个Observable是否发射指一个特定的值。
         * 如果发射了那个值，则返回的Observable将发射true，否则false。
         *
         aBoolean = [true]
         */
        Observable.just(1,2,3,4,4,5)
                .contains(1)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println("aBoolean = [" + aBoolean + "]");
                    }
                });


        /**
         * 只发射其中一个Observable的数据和通知，忽略其他的Observable
         * 这个Observable需要首先发射数据或者通知给amb
         */
        Observable<String> interval = Observable.just("2","3","4").delay(100,TimeUnit.MILLISECONDS);
        Observable<String> interval2 = Observable.just("5","6","7").delay(0,TimeUnit.MILLISECONDS);
        Observable.amb(interval,interval2)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("s = [" + s + "]");
                    }
                });

        Thread.sleep(10000);
    }
}
