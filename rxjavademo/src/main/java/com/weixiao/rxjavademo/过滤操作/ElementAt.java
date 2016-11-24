package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class ElementAt {

    public static void main(String[] args) {

        /**
         * ElementAt操作符获取原始Observable发射的数据序列指定索引位置的数据项，然后当做自己的唯一数据发射。
         integer = [11]
         integer = [20]
         */
        Observable.range(1,20)
                .elementAt(10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        //如果index大于数据项数，会发射指定参数的值
        //如果指定负的index，则会抛出异常
        Observable.range(1,20)
                .elementAtOrDefault(21,20)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
    }
}
