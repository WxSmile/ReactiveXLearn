package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class IgnoreElements {
    public static void main(String[] args) {


        /**
         * 忽略所有发射的数据，只允许终止通知（onError或onCompleted）通过
         *
         cmp
         */
        Observable.range(1,10)
                .ignoreElements()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("cmp");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
    }
}
