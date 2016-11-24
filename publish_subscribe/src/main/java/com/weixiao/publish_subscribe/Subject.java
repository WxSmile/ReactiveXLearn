package com.weixiao.publish_subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangssmf on 2016/5/25.
 * email： wangssmf@hehenian.com
 * 功能：可观察对象
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
