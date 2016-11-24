package com.weixiao.publish_subscribe;

/**
 * Created by wangssmf on 2016/5/25.
 * email： wangssmf@hehenian.com
 * 功能：观察者对象
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
