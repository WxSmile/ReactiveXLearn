package com.weixiao.rxjavademo.结合操作;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Zip {

    public static void main(String[] args) {

        /**
         * zip返回一个Observable，它使用指定函数 按顺序结合两个或多个Observables发射的数据项
         * 然后发射这个函数的结果
         * zip只发射和Observables中 数据项最少的Observable 一样多个的 数据
         *
         integer = [14]
         integer = [28]
         integer = [42]
         */
        Observable<Integer> observable1 = Observable.just(10, 20, 30);
        Observable<Integer> observable2 = Observable.just(4, 8,12,16);

        Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

    }
}
