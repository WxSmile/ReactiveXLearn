package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Filter {

    public static void main(String[] args) {

        /**
         *  filter 操作符指定一个boolean函数，通过这个函数返回true的数据才会被发射
         integer = [1]
         integer = [2]
         integer = [3]
         cmp
         */
        Observable.just(1,2,3,4,5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer<4;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("throwable = [" + throwable + "]");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        System.out.println("cmp");
                    }
                });
    }
}
