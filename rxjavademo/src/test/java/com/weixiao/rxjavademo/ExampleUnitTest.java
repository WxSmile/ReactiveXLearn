package com.weixiao.rxjavademo;

import android.util.Log;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void interval(){
            Observable.interval(1, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
                @Override public void onCompleted() {
                    Log.e("------ onNext ", "onCompleted");
                }

                @Override public void onError(Throwable e) {

                }

                @Override public void onNext(Long aLong) {
                    Log.e("------ onNext ", "aLong : " + aLong);
                }
            });

    }

    @Test
    public void bufferTime(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                while (true) {
                    subscriber.onNext("消息" +System.currentTimeMillis());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }). buffer(3, TimeUnit.SECONDS).//每隔3秒 取出消息
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