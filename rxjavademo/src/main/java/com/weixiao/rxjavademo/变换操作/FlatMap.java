package com.weixiao.rxjavademo.变换操作;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weixiao-sm on 2016/11/8.
 */
public class FlatMap {

    public static void main(String[] args) throws InterruptedException {

        /**
         o = [11]
         o = [12]
         o = [13]
         o = [14]
         o = [15]
         o = [16]
         o = [17]
         o = [18]
         o = [19]
         o = [20]
         */
//        Observable.range(1,10).flatMap(new Func1<Integer, Observable<?>>() {
//            @Override
//            public Observable<?> call(Integer integer) {
//                int flatMap = integer+10;
//                return Observable.just(flatMap);
//            }
//        }).subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object o) {
//                System.out.println("o = [" + o + "]");
//            }
//        });

        /**
         s = [14.215.177.38]
         emit = [http://www.baidu.com/:14.215.177.38]
         s = [216.58.199.228]
         emit = [http://www.google.com/:216.58.199.228]
         s = [202.89.233.103]
         emit = [https://www.bing.com/:202.89.233.103]

         当加了Schedulers之后：
         ip = [202.89.233.103] Thread: RxCachedThreadScheduler-4
         emit = [https://www.bing.com/:202.89.233.103] Thread: RxCachedThreadScheduler-4
         ip = [14.215.177.37] Thread: RxCachedThreadScheduler-2
         emit = [http://www.baidu.com/:14.215.177.37] Thread: RxCachedThreadScheduler-2
         ip = [93.46.8.89] Thread: RxCachedThreadScheduler-3
         emit = [http://www.google.com/:93.46.8.89] Thread: RxCachedThreadScheduler-3
         */
//        Observable.just("http://www.baidu.com/",
//                "http://www.google.com/",
//                "https://www.bing.com/")
//                .flatMap(new Func1<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(String s) {
//                        return creteIpObservable(s);
//                    }
//                })
////                .observeOn(Schedulers.trampoline())
////                .subscribeOn(Schedulers.io())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println("ip = [" + s + "]" + " Thread: " + Thread.currentThread().getName());
//                    }
//                });

        Thread.sleep(100000);
    }

    private static Observable<String> creteIpObservable(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String ipByUrl = getIPByUrl(s);
                    subscriber.onNext(ipByUrl);
                    System.out.println("emit = [" + s + ":" + ipByUrl + "]" + " Thread: " + Thread.currentThread().getName());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        })/*.subscribeOn(Schedulers.io())*/;
    }

    private static String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);
    }

    /**
     *
     *

     */
}
