package com.weixiao.rxjavademo.阻塞操作;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class BlockingObservables {

    public static void main(String[] args) throws InterruptedException {

        /**
         * BlockingObservable的方法不是将一个Observable变换为另一个，也不是过滤Observables，
         * 它们会打断Observable的调用链，
         * 会阻塞等待直到Observable发射了想要的数据，然后返回这个数据（而不是一个Observable）
         */


        /**
         * 下面是相似的阻塞和非阻塞操作符single例子
         block single = 5 thread: main
         integer = [5] thread: RxNewThreadScheduler-1
         */

        Observable<Integer> just = Observable.just(10, 2, 4, 5, 6);
        Integer single = just.count().toBlocking().single();
        System.out.println("block single = " + single +" thread: " + Thread.currentThread().getName());

        Observable<Integer> single1 = just.count().single();
        single1.observeOn(Schedulers.newThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = [" + integer + "]"+" thread: " + Thread.currentThread().getName());
            }
        });

        Thread.sleep(10000);
    }
}
