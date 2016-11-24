package com.weixiao.rxjavademo.辅助操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class Using {
    public static void main(String[] args) throws InterruptedException {

        /**
         using操作符接受三个参数：using(Func0,Func1,Action1))
         一个用户创建一次性资源的工厂函数
         一个用于创建Observable的工厂函数
         一个用于释放资源的函数
         *
         crt resource integer 233
         integer = [233]
         dispose integer 233
         */
        Observable.using(new Func0<Integer>() {
            @Override
            public Integer call() {
                System.out.println("crt resource integer 233");
                return 233;
            }
        }, new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.just(integer).delay(2000,TimeUnit.MILLISECONDS);
            }
        }, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.printf("dispose integer " + integer );
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        Thread.sleep(10000);
    }
}
