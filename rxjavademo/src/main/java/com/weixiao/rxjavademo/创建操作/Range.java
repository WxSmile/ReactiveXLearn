package com.weixiao.rxjavademo.创建操作;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Range {

    public static void main(String[] args) {
        Observable.range(0,9).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.printf("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });

        Observable.range(1,0).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.printf("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });

        Observable.range(0,-1).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.printf("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    /**
     *  Range操作符发射一个范围内的有序整数序列，你可以指定范围的起始和长度。
     *  它接受两个参数，一个是范围的起始值，一个是范围的数据的数
     *  如果你将第二个参数设为0，将导致Observable不发射任何数据（如果设置为负数，会抛异常）
     *
     integer = 0
     integer = 1
     integer = 2
     integer = 3
     integer = 4
     integer = 5
     integer = 6
     integer = 7
     integer = 8
     completedcompletedException in thread "main" java.lang.IllegalArgumentException: Count can not be negative
     at rx.Observable.range(Observable.java:2491)
     at com.weixiao.rxjavademo.createOpt.Range.main(Range.java:46)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
     */

}
