package com.weixiao.publish_subscribe;

/**
 * Created by wangssmf on 2016/5/25.
 * email： wangssmf@hehenian.com
 * 功能：
 *
 * 观察者模式是对象的行为模式，又叫发布-订阅(Publish/Subscribe)模式
 *
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
 */
public class ObserverPatternDemo {

    public static void main(String[] args){

        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexaObserver(subject);
        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }

}
