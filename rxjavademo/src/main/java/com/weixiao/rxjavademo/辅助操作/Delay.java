package com.weixiao.rxjavademo.辅助操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Delay {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 在Observable发射每一项数据之前都暂停一段指定的时间段
         */

//        Observable.just(1,2,3,4)
//                .delay(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("integer = [" + integer + "]");
//            }
//        });

        Observable.just(1,2,3,4)
                .delay(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.just(integer).delay(1, TimeUnit.SECONDS);
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
        Thread.sleep(10000);
    }
}
