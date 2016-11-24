package com.weixiao.rxjavademo.算术和聚合操作;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/25.
 */
public class Reduce {
    public static void main(String[] args) {

        /**
         *Reduce操作符对原始Observable发射数据的第一项应用一个函数，
         * 然后再将这个函数的返回值与第二项数据一起传递给函数，
         * 以此类推，持续这个过程直到原始Observable发射它的最后一项数据并终止，
         * 此时Reduce返回的Observable发射这个函数返回的最终值
         *
         * 如果原始Observable没有发射任何数据，则reduce抛出异常IllegalArgumentException
         s = [h], s2 = [e]
         s = [he], s2 = [l]
         s = [hel], s2 = [l]
         s = [hell], s2 = [o]
         cus = [hello]
         */
        Observable.just("h","e","l","l","o").reduce(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                System.out.println("s = [" + s + "], s2 = [" + s2 + "]");
                return s+s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("cus = [" + s + "]");
            }
        });
    }
}
