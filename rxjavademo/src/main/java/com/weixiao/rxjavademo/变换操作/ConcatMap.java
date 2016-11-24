package com.weixiao.rxjavademo.变换操作;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/19.
 */
public class ConcatMap {

    public static void main(String[] args) throws InterruptedException {

        Observable.just("http://www.baidu.com/",
                "http://www.google.com/",
                "https://www.bing.com/")
                .concatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return createIpObservavleMultiThread(s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("s = [" + s + "]");
                    }
                });

        Thread.sleep(10000);
    }

    private static Observable<String> createIpObservavleMultiThread(final String s) {
        return  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String ip = getIPByUrl(s);
                    subscriber.onNext(ip);
                    System.out.println("emit = [" + s + ":" + ip + "]" + " Thread: " + Thread.currentThread().getName());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
//        .subscribeOn(Schedulers.trampoline());
    }

    private static String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);
    }
}
