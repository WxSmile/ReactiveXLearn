package com.weixiao.rxjavademo.过滤操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Debounce {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 在收到原始Observable的一项数据之后，如果在debounce规定的时间内（小于等于）没有原始数据发射时
         * 则把这个数据提交给订阅者，否则忽略这个数据
         *
         emit: 1 sleep 101
         emit: 2 sleep 201
         emit: 3 sleep 301
         emit: 4 sleep 401
         emit: 5 sleep 501
         sub integer = [5]
         emit: 6 sleep 601
         sub integer = [6]
         emit: 7 sleep 701
         sub integer = [7]
         emit: 8 sleep 801
         sub integer = [8]
         emit: 9 sleep 901
         sub integer = [9]
         */
        Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        for (int i = 1; i <10; i++) {
                            subscriber.onNext(i);
                            System.out.println("emit: " + i + " sleep " + (i*100+1));
                            try {
                                Thread.sleep(i * 100+1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .debounce(400,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("sub integer = [" + integer + "]");
                    }
                });

        Thread.sleep(100000);
    }
}
