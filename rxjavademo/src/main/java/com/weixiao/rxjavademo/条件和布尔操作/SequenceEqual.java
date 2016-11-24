package com.weixiao.rxjavademo.条件和布尔操作;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/24.
 */
public class SequenceEqual {

    public static void main(String[] args) {

        /**
         * 判定两个Observables是否发射相同的数据序列。
         *
         aBoolean = [false]
         */
        Observable<Integer> just = Observable.just(1, 2, 3, 4);
        Observable<Integer> just1 = Observable.just(2, 3, 4, 5);
        Observable.sequenceEqual(just,just1)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println("aBoolean = [" + aBoolean + "]");
                    }
                });
    }
}
