package com.weixiao.rxjavademo.结合操作;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class StartWith {
    public static void main(String[] args) {
        /**
         * 在Observable发射数据之前插入指定的某些数据
         integer = [10]
         integer = [20]
         integer = [30]
         integer = [1]
         integer = [2]
         integer = [3]
         integer = [4]
         */

        Observable.just(1,2,3,4).startWith(10,20,30)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
    }
}
