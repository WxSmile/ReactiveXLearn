package com.weixiao.rxjavademo.创建操作;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/9/27.
 */
public class Create {

    public static void main(String[] args) {

        Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> obsever) {

                try {
                    if(!obsever.isUnsubscribed()){//如果没有被取消订阅
                        for (int i = 0; i < 5; i++) {
                            obsever.onNext(i);
                        }
                    }
                    obsever.onCompleted();
                } catch (Exception e) {
                    obsever.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = [" + e.getMessage() + "]");
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Next item = [" + item + "]");
            }
        });
    }

    /**
     * 使用一个函数从头创建一个可观察对象
     * 运行结果
     Next item = [0]
     Next item = [1]
     Next item = [2]
     Next item = [3]
     Next item = [4]
     Sequence complete.
     */
}
