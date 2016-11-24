package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class Distinct {

    public static void main(String[] args) {

        /**
         * 过滤重复的数据项
         *
         integer = [1]
         integer = [2]
         integer = [3]
         integer = [4]
         integer = [6]
         */
//        Observable.just(1,2,3,1,4,6)
//                .distinct()
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        System.out.println("integer = [" + integer + "]");
//                    }
//                });

        Observable.just(1,2,3,1,4,6)
                .distinct(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer;
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
