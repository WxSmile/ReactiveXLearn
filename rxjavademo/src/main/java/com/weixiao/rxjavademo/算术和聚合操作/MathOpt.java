package com.weixiao.rxjavademo.算术和聚合操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class MathOpt {

    public static void main(String[] args) {


        Observable<Integer> just = Observable.just(10, 2, 4, 5, 6);

        /**
         * 计算原始Observable发射的数字的平均值并发射它
         * 如果这个Observable不发射任何数据，则会抛出异常IllegalArgumentException
         *
         * integer = [5]
         */
        rx.observables.MathObservable.averageInteger(just)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        /**
         * Min 发射原始Observable的最小值
         */

        MathObservable.min(just)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = [" + integer + "]");
                    }
                });

        /**
         * Max 发射原始Observable的最大值
         */
        MathObservable.max(just).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });

        /**
         * count 计算原始Observable发射物的数量，然后只发射这个值
         *
         */
        Integer single = just.count().toBlocking().single();
        System.out.println("single = " + single);


        /**
         * sum 计算Observable发射的数值的和，并发射这个值
         *
         * integer = [27]
         */
        MathObservable.sumInteger(just).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]");
            }
        });
    }
}
