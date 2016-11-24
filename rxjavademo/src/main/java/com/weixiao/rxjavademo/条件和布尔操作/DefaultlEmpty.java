package com.weixiao.rxjavademo.条件和布尔操作;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/24.
 */
public class DefaultlEmpty {
    public static void main(String[] args) {

        /**
         * 如果原始Observable没有发射任何值，就发射一个默认值。
         *
         s = [empty]
         */
        Observable.empty().defaultIfEmpty("empty")
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object s) {
                        System.out.println("s = [" + s + "]");
                    }
                });
    }
}
