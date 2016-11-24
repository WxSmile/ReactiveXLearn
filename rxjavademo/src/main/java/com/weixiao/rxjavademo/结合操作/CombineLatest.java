package com.weixiao.rxjavademo.结合操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/20.
 */
public class CombineLatest {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 当一个Observable发射了一个数据，合并另一个Observable发射的最近数据
         * 然后基于这个结果发射数据
         *
         emit: observable1 recnet = [1], observable2 recnet = [0]
         aLong = [1]
         emit: observable1 recnet = [2], observable2 recnet = [0]
         aLong = [2]
         emit: observable1 recnet = [3], observable2 recnet = [0]
         emit: observable1 recnet = [3], observable2 recnet = [10]
         aLong = [3]
         aLong = [13]
         emit: observable1 recnet = [4], observable2 recnet = [10]
         aLong = [14]
         emit: observable1 recnet = [4], observable2 recnet = [20]
         aLong = [24]
         emit: observable1 recnet = [4], observable2 recnet = [30]
         aLong = [34]
         emit: observable1 recnet = [4], observable2 recnet = [40]
         aLong = [44]
         */
        Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 1;
                    }
                }).take(5);
        Observable<Long> observable2 = Observable.interval(2, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);

        Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                System.out.println("emit: observable1 recnet = [" + aLong + "], observable2 recnet = [" + aLong2 + "]");
                return aLong+aLong2;
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = [" + aLong + "]");
            }
        });


        Thread.sleep(10000);
    }
}
