package com.weixiao.iteratormodel;

/**
 * Created by wangssmf on 2016/5/26.
 * email： wangssmf@hehenian.com
 * 功能：迭代器抽象类
 */
public interface Iterator {

      Object first();

      Object next();

      Object currentItem();

      boolean isDone();
}
