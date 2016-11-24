package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class First {
    public static void main(String[] args) {

        /**
         * 只发射第一项 或者你感兴趣的（满足指定函数的数据）第一项数据

         integer = [1]
         integer = [3]
         */
        Observable.just(1,2,3)
                .first()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        Observable.just(1,2,3,3,4,5)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer==3;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });
    }
}
