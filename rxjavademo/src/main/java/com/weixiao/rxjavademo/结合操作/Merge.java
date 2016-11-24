package com.weixiao.rxjavademo.结合操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Merge {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 按照2个Observable提交结果的时间顺序，对发射的数据进行合并
         *
         aLong = [0]
         aLong = [0]
         aLong = [1]
         aLong = [1]
         aLong = [2]
         aLong = [2]
         aLong = [3]
         aLong = [3]
         aLong = [4]
         aLong = [4]
         */
        Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS).take(5);
        Observable<Long> observable2 = Observable.interval(1, TimeUnit.SECONDS).take(5).delay(500,TimeUnit.MILLISECONDS);
        Observable.mergeDelayError(observable1,observable2).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("cmp");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });

        Thread.sleep(20000);
    }
}
