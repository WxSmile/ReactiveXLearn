package com.weixiao.rxjavademo.变换操作;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/7.
 */
public class Buffer {

    public static void main(String[] args) throws InterruptedException {

//        Observable.interval(1, TimeUnit.SECONDS)
//                .buffer(5).subscribe(new Action1<List<Long>>() {
//            @Override
//            public void call(List<Long> longs) {
//                System.out.println("longs = [" + longs + "]");
//            }
//        });

        //从原始Observeable的第一项数据开始创建缓存，每当收到第skip项数据，用count项数据填充缓存，并以List的形式发射缓存
        //此后从原skip项数据开始，重新计算新skip项数据的位置，循环前面的操作直到数据发射完毕
//        Observable.interval(1,TimeUnit.SECONDS).take(10).buffer(3,1).subscribe(new Action1<List<Long>>() {
//            @Override
//            public void call(List<Long> longs) {
//                System.out.println("longs = [" + longs + "]");
//            }
//        });


        //buffer开始将数据以List的形式开始缓存，直到func0创建的Observable发射一次TClosing时，buffer发射当前的缓存
        //然后调用func0创建一个新的Observable并监视这个Observable，重复之前的过程直到原来的Observable执行完毕。
//        Observable.interval(1,TimeUnit.SECONDS).take(15).buffer(new Func0<Observable<?>>() {
//            @Override
//            public Observable<?> call() {
//                System.out.println("func0");
//                return Observable.interval(3, TimeUnit.SECONDS);
//            }
//        }).subscribe(new Action1<List<Long>>() {
//            @Override
//            public void call(List<Long> longs) {
//                System.out.println("longs = [" + longs + "]");
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        }, new Action0() {
//            @Override
//            public void call() {
//                System.out.println("cmp");
//            }
//        });

        //buffer监视一个Observable，每当这个Observable发射了一个值，
        //就创建一个新的List收集来自原始Observable的数据，并发射原来的List缓存的数据
//        Observable.interval(1, TimeUnit.SECONDS)
//                .buffer(Observable.interval(1,TimeUnit.SECONDS)).subscribe(new Action1<List<Long>>() {
//            @Override
//            public void call(List<Long> longs) {
//                System.out.println("longs = [" + longs + "]");
//            }
//        });

        //定期以List的形式发射新的数据，每个时间段，收集来自原始的Observable的数据。
        Observable.interval(1, TimeUnit.SECONDS).buffer(4,TimeUnit.SECONDS)
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        System.out.println("longs = [" + longs + "]");
                    }
                });

        //每当收到来自原始Observable的count项数据，或者每过了一段定期的时间后，
        // buffer就以List的形式发射这期间的数据，即使数据少于count项。
        Observable.interval(1,TimeUnit.SECONDS).buffer(4,TimeUnit.SECONDS,3).subscribe(new Action1<List<Long>>() {
            @Override
            public void call(List<Long> longs) {
                System.out.println("longs = [" + longs + "]");
            }
        });

        Thread.sleep(100000);
    }

}
