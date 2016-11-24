package com.weixiao.rxjavademo.变换操作;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by weixiao-sm on 2016/11/8.
 */
public class GroupBy {
    public static void main(String[] args) {

        Observable.just(1,1,2,3,1,2,3,2,1).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {

                return integer;
            }
        }).subscribe(new Subscriber<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("inner completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("inner e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("group:"+integerIntegerGroupedObservable.getKey()+" value = [" + integer + "]");

                    }
                });
            }
        });

        /**
         * groupBy操作符，通过应用一个函数，这个函数的结果被作为一个key，将原始的Observable数据分组为不同的GroupedObservables并缓存
         *
         * key相同的数据会被同一个GroupedObservable发射
         *
         * 如果你忽略这些GroupedObservable中的任何一个，这个缓存可能形成一个潜在的内存泄露
         */
    }
}
