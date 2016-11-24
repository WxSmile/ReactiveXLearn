package com.weixiao.rxjavademo.辅助操作;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class MaterializeAndDematerialize {

    public static void main(String[] args) {

        /**
         * 将来自原始Observable的通知转换为Notification对象，然后它返回的Observable会发射这些数据。
         *
         * Dematerialize操作符是Materialize的逆向过程，它将Materialize转换的结果还原成它原本的形式。
         */
        Observable.just(1).materialize().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                System.out.println("integerNotification = [" + integerNotification.toString() + "]");
            }
        });

        Observable.just(1).materialize().dematerialize().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("o = [" + o + "]");
            }
        });
    }
}
