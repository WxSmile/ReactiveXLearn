package com.weixiao.rxjavademo.错误处理;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class Retry {
    public static void main(String[] args) {
        /**
         * 当Observable发生错误或异常的时候，拦截并重新订阅源Observable
         * 没有count参数的retry操作符会一直重新订阅
         * 有count参数的retry操作符会在订阅count次后，如果仍然出现错误或异常，则会回调onError方法
         *
         Next:0
         Next:1
         Next:2
         Next:3
         Next:0
         Next:1
         Next:2
         Next:3
         Next:0
         Next:1
         Next:2
         Next:3
         Error: this is number 4 error！
         */
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.retry(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });
    }
}
