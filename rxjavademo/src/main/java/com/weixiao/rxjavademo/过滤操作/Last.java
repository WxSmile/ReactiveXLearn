package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Last {
    public static void main(String[] args) {

        /**
         * 只发射最后一项数据，或者你感兴趣的数据（通过指定函数的数据）中的最后一项数据
         */
        Observable.just(1,2,3,4,5)
                .last()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        Observable.just(1,2,3,4,5,6,1,2,3)
                .last(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        System.out.println("emit: " + integer);
                        return integer == 1;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        Observable.just(1,2,3,4,5,6)
                .lastOrDefault(7,new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        System.out.println("emit: " + integer);
                        return integer == 100;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
    }
}
