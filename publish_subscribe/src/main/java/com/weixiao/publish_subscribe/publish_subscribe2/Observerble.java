package com.weixiao.publish_subscribe.publish_subscribe2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixiao on 2017/9/10.
 */

public class Observerble {

    private List<Observer> observers = new ArrayList<>();

    private List<Integer> number;

    public List<Integer> getNumber() {
        return number;
    }

    public Observerble setNumber(List<Integer> number) {
        this.number = number;
        return this;
    }

    public static Observerble create(){
        return new Observerble();
    }

    public Subscription subscribe(Observer observer){
        observers.add(observer);
        observer.observerOnObserverble(this);
        observer.onSubscribe((Subscription)observer);
        for (Integer integer : number) {
            observer.onNext(integer);
        }
        observer.onComplete();
        return observer;
    }
}
