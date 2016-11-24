package com.weixiao.rxjavademo.辅助操作;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class ObserverOn {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 指定Observable在一个特定的调度器上来调用观察者的onNext onCompleted onError方法
         *
         * ObserverOn当遇到异常时，会立刻调用onError，不会等待延时或者慢速的的数据发射
         *
         * SubscribeOn操作符的作用类似，但它是用于指定Observable本身在特定的调度器上执行，它同样会在那个调度器上给观察者发通知。
         */
        Observable.just(1,2,3)
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.printf("cmp"+" thread= "+ Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.printf("e"+" thread= "+ Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]"+" thread= "+ Thread.currentThread().getName());
                    }
                });

        Thread.sleep(10000);
    }
}
