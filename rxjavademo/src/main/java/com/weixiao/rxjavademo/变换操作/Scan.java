package com.weixiao.rxjavademo.变换操作;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/8.
 */
public class Scan {

    public static void main(String[] args) {

        Observable.just(1,2,3,4).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer scan, Integer originNext) {
                System.out.println("scan = [" + scan + "], originNext = [" + originNext + "]");
                return scan + originNext;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext integer = [" + integer + "]");
            }
        });
    }

    /**
     * scan操作符  就是将原始Observable的第一项数据应用一个函数，这个函数的结果作为自己的第一个数据发射项
     *
     * 然后将这个数据发射项和原始数据发射的下一项数据作用于这个函数，这个函数的记过作为数据项发射项
     *
     * 然后将这个数据和原始数据发射的下一项数据作用于这个函数，这个函数的记过作为数据项发射项
     *
     * 持续进行上面的过程，产生剩余的数据序列。
     *
     onNext integer = [1]
     scan = [1], originNext = [2]
     onNext integer = [3]
     scan = [3], originNext = [3]
     onNext integer = [6]
     scan = [6], originNext = [4]
     onNext integer = [10]
     onCompleted

     */
}
