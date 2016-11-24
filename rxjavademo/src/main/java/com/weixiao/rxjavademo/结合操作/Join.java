package com.weixiao.rxjavademo.结合操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 在每个Observable发射的数据 的生命周期内，如果有另外一个Observable发射数据
         * 则这两个数据将被应用到指定函数，产生的结果当作数据发射
         *
         aLong = [0], aLong2 = [0]
         Next: 0
         aLong = [5], aLong2 = [0]
         Next: 5
         aLong = [5], aLong2 = [10]
         Next: 15
         aLong = [10], aLong2 = [10]
         Next: 20
         aLong = [10], aLong2 = [20]
         Next: 30
         aLong = [15], aLong2 = [20]
         Next: 35
         aLong = [15], aLong2 = [30]
         Next: 45
         aLong = [20], aLong2 = [30]
         Next: 50
         aLong = [20], aLong2 = [40]
         Next: 60
         Sequence complete.
         */


        //产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);

        observable1.join(observable2, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {

                System.out.println("aLong = [" + aLong + "], aLong2 = [" + aLong2 + "]");
                return aLong + aLong2;
            }
        }).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next: " + aLong);
            }
        });

        Thread.sleep(10000);
    }
}
