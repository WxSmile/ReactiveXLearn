package com.weixiao.rxjavademo.辅助操作;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by weixiao-sm on 2016/11/22.
 */
public class To {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 将Observable转换为另一个对象或数据结构
         *
         integers = [[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]]
         stringStringMap = [{ckey=c, akey=a, bkey=b}]
         stringCollectionMap = [{1=[1], 2=[2], 3=[3], 4=[4], 5=[5], 6=[6], 7=[7], 8=[8], 9=[9], 10=[10]}]
         integers = [[5, 3, 3, 2, 2, 2, 1]]
         */
        Iterator<Integer> iterator = Observable.range(1, 10)
                .toBlocking().getIterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
        }

//        Future<Integer> integerFuture = Observable.range(1, 10).toBlocking().toFuture();
//        Integer integer = integerFuture.get();

        Observable.range(1, 10).toList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = [" + integers + "]");
            }
        });

        Observable.just("a","b","c").toMap(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"key";
            }
        }).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> stringStringMap) {
                System.out.println("stringStringMap = [" + stringStringMap + "]");
            }
        });

        Observable.range(1,10).toMultimap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return integer.toString();
            }
        }).subscribe(new Action1<Map<String, Collection<Integer>>>() {
            @Override
            public void call(Map<String, Collection<Integer>> stringCollectionMap) {
                System.out.println("stringCollectionMap = [" + stringCollectionMap + "]");
            }
        });

        Observable.just(2,3,1,2,3,5,2).toSortedList(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                //判断返回值大于等于小于0
                return integer2 - integer;
            }
        }).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("integers = [" + integers + "]");
            }
        });
    }
}
