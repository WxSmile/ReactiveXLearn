package com.weixiao.rxjavademo.变换操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class FlatMap2 {
    public static void main(String[] args) throws InterruptedException {

        /**
         * flatMap是将原始Observable发射的数据转换为多个Observable
         * 然后“合并”这些Observable，并一次发射这些Observable
         *
         * conatMap和flatMap类似
         * 但是conatMap是“连接”转换后的Observable，
         * 所以conatMap的结果是有顺序的，不会出现交叉的现象
         *
         * switchMap和flatMap类似
         * 但是switchMap在当原始Observable发射一个新的数据转换为新的Observable时，
         * 它将取消订阅并停止监视之前的那个数据的Observable，只监视当前的Observable
         *
         *
         * flatMap
         flatMap integer = [20]
         flatMap integer = [10]
         flatMap integer = [30]
         flatMap integer = [15]
         flatMap integer = [10]
         flatMap integer = [5]
         *
         * conatMap
         conatMap integer = [10]
         conatMap integer = [5]
         conatMap integer = [20]
         conatMap integer = [10]
         conatMap integer = [30]
         conatMap integer = [15]
         *
         * switchMap
         switchMap integer = [30]
         switchMap integer = [15]
         */

        Observable.just(10,20,30).flatMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                int delay = 2;
                if(integer>10) delay = 1;
                return Observable.from(new Integer[]{integer,integer/2}).delay(delay, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(" flatMap integer = [" + integer + "]");
            }
        });

//        Observable.just(10,20,30).concatMap(new Func1<Integer, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call(Integer integer) {
//                int delay = 2;
//                if(integer>10) delay = 1;
//                return Observable.from(new Integer[]{integer,integer/2}).delay(delay, TimeUnit.SECONDS);
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println(" conatMap integer = [" + integer + "]");
//            }
//        });

//        Observable.just(10,20,30).switchMap(new Func1<Integer, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call(Integer integer) {
//                int delay = 2;
//                if(integer>10) delay = 1;
//                return Observable.from(new Integer[]{integer,integer/2}).delay(delay, TimeUnit.SECONDS);
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println(" switchMap integer = [" + integer + "]");
//            }
//        });




        Thread.sleep(10000);
    }
}
