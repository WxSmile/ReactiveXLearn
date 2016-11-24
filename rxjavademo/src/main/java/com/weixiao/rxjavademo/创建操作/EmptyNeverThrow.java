package com.weixiao.rxjavademo.创建操作;

import rx.Observable;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class EmptyNeverThrow {

    public static void main(String[] args) {

        Observable.empty();

        Observable.never();

        Observable.error(new Throwable());

        /**
         * Empty - 创建一个不发射任何数据但是正常终止的可观察对象
         * Never - 创建一个不发射数据也不终止的可观察对象
         * Throw - 创建一个不发射数据但是以一个异常终止的可观察对象。
         *
         * 这3个操作符一般用于测试，或者其他需要可观察对象作为参数的操作符
         */
    }
}
