package com.weixiao.rxjavademo.过滤操作;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class SkipLast {
    public static void main(String[] args) {

        /**
         * 忽略发射的数据中的后N项数据
         */
        Observable.just(1,2,3,4,5,6,7)
                .skipLast(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });


    }
}
