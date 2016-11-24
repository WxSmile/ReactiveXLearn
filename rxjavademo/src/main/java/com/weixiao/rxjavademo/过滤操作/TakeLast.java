package com.weixiao.rxjavademo.过滤操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class TakeLast {
    public static void main(String[] args) {

        /**
         * 只发射最后的N项数据或者原始Observable生命周期最后一段指定时间内发射的数据
         *
         integer = [11]
         integer = [12]
         integer = [13]
         integer = [14]
         integer = [15]
         integer = [16]
         integer = [17]
         integer = [18]
         integer = [19]
         integer = [20]
         integer = [8]
         integer = [9]
         */
        Observable.range(1,20)
                .takeLast(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).takeLast(3,TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
    }
}
