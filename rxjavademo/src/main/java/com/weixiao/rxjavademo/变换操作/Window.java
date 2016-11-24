package com.weixiao.rxjavademo.变换操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/8.
 */
public class Window {
    public static void main(String[] args) throws InterruptedException {

        /**
         * window(count)  window操作符立即打开一个Observable窗口
         * 每当当前窗口发射了count个数据，就会关闭当前窗口，打开一个新的窗口
         begin.....
         aLong = [0]
         aLong = [1]
         aLong = [2]
         begin.....
         aLong = [3]
         aLong = [4]
         aLong = [5]
         begin.....
         aLong = [6]
         aLong = [7]
         aLong = [8]
         begin.....
         aLong = [9]
         */
//        Observable.interval(1,TimeUnit.SECONDS).take(10)
//                .window(3).subscribe(new Action1<Observable<Long>>() {
//            @Override
//            public void call(Observable<Long> longObservable) {
//                System.out.println("begin.....");
//                longObservable.subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        System.out.println("aLong = [" + aLong + "]");
//                    }
//                });
//            }
//        });


        /**
         *  window(count, skip)
         *
         *  原始Observable数据每发射skip项数据就会打开一个新的窗口
         *
         *  每当当前窗口发射了count项数据就会关闭当前窗口
         *
         *  如果skip==count，它的行为和window(count一致)
         *  如果skip < count,会有count-skip个重叠的数据
             begin.....
             Long = [0]
             Long = [1]
             begin.....
             Long = [2]
             inner completed
             Long = [2]
             Long = [3]
             begin.....
             Long = [4]
             inner completed
             Long = [4]
             Long = [5]
             begin.....
             Long = [6]
             inner completed
             Long = [6]
             Long = [7]
             begin.....
             Long = [8]
             inner completed
             Long = [8]
             Long = [9]
             inner completed
             completed
         *
         *  如果skip > count,会有skip-count个数据被丢弃
             begin.....
             Long = [0]
             Long = [1]
             Long = [2]
             inner completed
             begin.....
             Long = [5]
             Long = [6]
             Long = [7]
             inner completed
             completed
         */
//        Observable.interval(1, TimeUnit.SECONDS).take(10)
//                .window(3,5).subscribe(new Subscriber<Observable<Long>>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("e = [" + e + "]");
//            }
//
//            @Override
//            public void onNext(Observable<Long> integerObservable) {
//                System.out.println("begin.....");
//                integerObservable.subscribe(new Subscriber<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("inner completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("e = [" + e + "]");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        System.out.println("Long = [" + aLong + "]");
//                    }
//                });
//            }
//        });

        /**
         * window(timespan, unit[, scheduler])
         *
         * 每当过了timespan长时间就会关闭当前窗口并打开一个新的窗口
         *
         begin....
         aLong = [0]
         aLong = [1]
         begin....
         aLong = [2]
         aLong = [3]
         aLong = [4]
         begin....
         aLong = [5]
         aLong = [6]
         aLong = [7]
         begin....
         aLong = [8]
         aLong = [9]
         */
        Observable.interval(1, TimeUnit.SECONDS).take(10)
                .window(3,TimeUnit.SECONDS).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> longObservable) {
                System.out.println("begin....");
                longObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = [" + aLong + "]");
                    }
                });
            }
        });

        Thread.sleep(100000);

        /**
         * window 操作符，定期将原始Observable数据分解为一个Observable窗口，然后发射这些Observable
         *
         * 定期可能是 按照数量或者时间
         */
    }
}
