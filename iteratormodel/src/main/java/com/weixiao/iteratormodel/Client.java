package com.weixiao.iteratormodel;

/**
 * Created by wangssmf on 2016/5/26.
 * email： wangssmf@hehenian.com
 * 功能：
 */
public class Client {

    public static void main(String[] args){
        Aggregat aggregat = new ConcreteAggregate();
        Iterator iterator = aggregat.creatIterator();
        System.out.println(iterator.first());
        while (!iterator.isDone()){
            System.out.println(iterator.next());
        }
    }



}
