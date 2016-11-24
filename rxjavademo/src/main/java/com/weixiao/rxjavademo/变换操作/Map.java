package com.weixiao.rxjavademo.变换操作;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by weixiao-sm on 2016/11/8.
 */
public class Map {

    public static void main(String[] args) throws InterruptedException {


        /**
         o = [1opt]
         o = [2opt]
         o = [3opt]
         compleate
         */
//        Observable.just("1","2","3").map(new Func1<String, Object>() {
//            @Override
//            public Object call(String s) {
//                return s+"opt";
//            }
//        }).subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object o) {
//                System.out.println("o = [" + o + "]");
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                System.out.println("throwable = [" + throwable + "]");
//            }
//        }, new Action0() {
//            @Override
//            public void call() {
//                System.out.println("compleate");
//
//            }
//        });


        /**
         s = [this,]
         s = [is]
         s = [rxjava]
         strings = [[THIS,, IS, RXJAVA]]
         reserver strings = [[RXJAVA, IS, THIS,]]
         */
//        Observable.from(new String[]{"this,","is","rxjava"})
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        System.out.println("s = [" + s + "]");
//                        return s.toUpperCase();
//                    }
//                })
//        .toList()
//        .map(new Func1<List<String>, List<String>>() {
//            @Override
//            public List<String> call(List<String> strings) {
//                System.out.println("strings = [" + strings + "]");
//                Collections.reverse(strings);
//                return strings;
//            }
//        })
//        .subscribe(new Action1<List<String>>() {
//            @Override
//            public void call(List<String> strings) {
//                System.out.println(" reserver strings = [" + strings + "]");
//            }
//        });


        /**
         s = [http://www.baidu.com/ :14.215.177.38]
         s = [http://www.google.com/ :93.46.8.89]
         s = [https://www.bing.com/ :202.89.233.103]
         */
        Observable.just("http://www.baidu.com/",
                "http://www.google.com/",
                "https://www.bing.com/")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            return s + " :" + getIPByUrl(s);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        return null;
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

    private static String getIPByUrl(String str) throws MalformedURLException, UnknownHostException {
        URL urls = new URL(str);
        String host = urls.getHost();
        String address = InetAddress.getByName(host).toString();
        int b = address.indexOf("/");
        return address.substring(b + 1);
    }

    /**
     * 通过使用map中的方法对Observable中发射出来的所有数据进行变换

     */
}
