package com.weixiao.rxjavademo.辅助操作;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class TimeInterval {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 这个操作符将原始Observable转换为另一个Obserervable，后者发射一个标志替换前者的数据项，
         * 这个标志表示前者的两个连续发射物之间流逝的时间长度。
         * 新的Observable的第一个发射物表示的是在观察者订阅原始Observable到原始Observable发射它的第一项数据之间流逝的时间长度。
         * 不存在与原始Observable发射最后一项数据和发射onCompleted通知之间时长对应的发射物。
         *
         longTimeInterval = [0]
         longTimeInterval = [1013]
         longTimeInterval = [1]
         longTimeInterval = [990]
         longTimeInterval = [2]
         longTimeInterval = [1001]
         longTimeInterval = [3]
         longTimeInterval = [1000]
         longTimeInterval = [4]
         longTimeInterval = [999]
         */
        Observable.interval(1, TimeUnit.SECONDS).take(5)
                .timeInterval().subscribe(new Action1<rx.schedulers.TimeInterval<Long>>() {
            @Override
            public void call(rx.schedulers.TimeInterval<Long> longTimeInterval) {
                System.out.println("longTimeInterval = [" + longTimeInterval.getValue() + "]");
                System.out.println("longTimeInterval = [" + longTimeInterval.getIntervalInMilliseconds() + "]");
            }
        });

        Thread.sleep(10000);
    }
}
