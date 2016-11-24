package com.weixiao.rxjavademo.字符串操作;

import java.io.IOException;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class StringObservables {
    public static void main(String[] args) throws IOException {


        /**
         *将发射字符串Observable的数据拼接起来按一行来发送
         *
         s = [he
         llo]
         */
        Observable<String> just = Observable.just("he\n" + "ll" + "o");
        StringObservable.byLine(just).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = [" + s + "]");
            }
        });


        /**
         * decode 操作符将发射字节数组字符流的Observable转换为发射字符串的Observable
         *
         bytes = [[B@681a9515]
         decode s = [hello word]
         */
        byte[] bytes = {104, 101, 108, 108, 111, 32, 119, 111, 114, 100};
        Observable<byte[]> just1 = Observable.just(bytes);
        just1.subscribe(new Action1<byte[]>() {
            @Override
            public void call(byte[] bytes) {
                System.out.println("bytes = [" + bytes + "]");
            }
        });
        StringObservable.decode(just1,"utf-8").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("decode s = [" + s + "]");
            }
        });

        /**
         * encode将 一个发射字符串的Observable转换为发射字节数组的Observable
         *
         bytes[0] = [104]
         bytes[1] = [101]
         bytes[2] = [108]
         bytes[3] = [108]
         bytes[4] = [111]
         bytes[5] = [32]
         bytes[6] = [119]
         bytes[7] = [111]
         bytes[8] = [114]
         bytes[9] = [100]
         */
        Observable<String> just2 = Observable.just("hello word");
        StringObservable.encode(just2,"utf-8").subscribe(new Action1<byte[]>() {
            @Override
            public void call(byte[] bytes) {
                for (int i = 0; i < bytes.length; i++) {
                    System.out.println("bytes[" + i + "] = [" + bytes[i] + "]");
                }
            }
        });

        /**
         * join将发射字符串序列的Observable转换为发射用 指定字符连接起来的 单个字符串的Observable
         *
         * join s = [1-2-3]
         */
        Observable<String> just3 = Observable.just("1","2","3");
        StringObservable.join(just3,"-").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("join s = [" + s + "]");
            }
        });


        /**
         * 将发射字符串的Observable转换为另一个发射字符串的Observable
         * 后者使用指定正则表达式分割前者发射的所有字符串
         *
         split s = [hello]
         split s = [word]
         */
        Observable<String> just4 = Observable.just("hello word");
        StringObservable.split(just4," ").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("split s = [" + s + "]");
            }
        });


        /**
         * 将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者连接前者发射的所有字符串
         *
         * stringConcat s = [hello wordhello rxjava]
         */
        Observable<String> just5 = Observable.just("hello word", "hello rxjava");
        StringObservable.stringConcat(just5).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("stringConcat s = [" + s + "]");
            }
        });
    }
}
