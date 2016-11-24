package com.weixiao.rxjavademo.创建操作;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Just {

    public static void main(String[] args) {

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        Observable.just(1,2,3,4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });

        Observable.just(integers).subscribe(new Subscriber<ArrayList<Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(ArrayList<Integer> integers) {
                System.out.println("integers = " + integers.size());
            }
        });

        String nVal = null;

        Observable.just(nVal).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = " + e);
            }

            @Override
            public void onNext(String str) {
                if(null == str){
                    System.out.println("str = null");
                }
            }
        });
    }

    /**
     *  创建一个可观察对象，发射指定值
     *
     *  发射一个数组，观察者接收的就是一个数组
     *  发射一个整数，观察者接收的就是一个整数
     *  发射1-9个整数，观察者接收的就是1-9个整数
     *  发射一个Null值，观察者接收的就是一个Null值
     *
     Next: 1
     Next: 2
     Next: 3
     Next: 4
     Sequence complete.
     integers = 4
     Sequence complete.
     */
}
