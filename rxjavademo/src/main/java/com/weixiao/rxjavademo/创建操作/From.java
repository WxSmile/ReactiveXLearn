package com.weixiao.rxjavademo.创建操作;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class From {

    public static void main(String[] args) {

        final Integer[] items = {0,1,2,3,4,5};
        Observable.from(items).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer item) {
                System.out.println("item = " + item);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable = " + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("Sequence complete");
            }
        });


        Observable.from(new Future<Integer[]>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return true;
            }

            @Override
            public boolean isCancelled() {
                return true;
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Integer[] get() throws InterruptedException, ExecutionException {
                return items;
            }

            @Override
            public Integer[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        }).subscribe(new Action1<Integer[]>() {
            @Override
            public void call(Integer[] item) {
                System.out.println("item = " + item);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable = " + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("Sequence complete");
            }
        });

        Observable.from(new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(1);
                integers.add(2);
                integers.add(3);
                Iterator<Integer> iterator = integers.iterator();
                return iterator;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer item) {
                System.out.println("item = " + item);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable = " + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("Sequence complete");
            }
        });


        /**
         *  From创建一个可以接收Future、Iterable和数组为参数的可观察者
         *  对于Iterator和数组，发射的数据是他们的每一项数据
         *  对于Future，会发射Future.get()产生的单个数据
         *
         item = 0
         item = 1
         item = 2
         item = 3
         item = 4
         item = 5
         Sequence complete
         item = [Ljava.lang.Integer;@19469ea2
         Sequence complete
         item = 1
         item = 2
         item = 3
         Sequence complete
         */
    }
}
