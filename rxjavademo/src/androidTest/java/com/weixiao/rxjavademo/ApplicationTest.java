package com.weixiao.rxjavademo;

import android.app.Application;
import android.os.SystemClock;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        bufferTime();
    }

    public void bufferTime(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                while (true) {
                    subscriber.onNext("消息" + SystemClock.elapsedRealtime());
                    SystemClock.sleep(2000);//每隔2s发送消息
                }

            }
        }).subscribeOn(Schedulers.io()).
                buffer(3, TimeUnit.SECONDS).//每隔3秒 取出消息
                subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Log.d("buffer","-----------------onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("buffer","----------------->onError:");
            }

            @Override
            public void onNext(List<String> strings) {
                Log.d("buffer","----------------->onNext:" + strings);
            }
        });
    }
}