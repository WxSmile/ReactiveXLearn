package com.weixiao.publish_subscribe;

/**
 * Created by wangssmf on 2016/5/25.
 * email： wangssmf@hehenian.com
 * 功能：观察者实体类
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
