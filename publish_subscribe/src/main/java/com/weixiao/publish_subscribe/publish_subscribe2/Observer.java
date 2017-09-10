package com.weixiao.publish_subscribe.publish_subscribe2;

/**
 * Created by weixiao on 2017/9/10.
 */

public abstract class Observer implements Subscription {

    private Observerble observerble;

    void observerOnObserverble(Observerble observerble){

        this.observerble = observerble;
    }

    public abstract void onNext(int number);

    public abstract void onComplete();

    public abstract void onSubscribe(Subscription subscription);

    @Override
    public void cancel() {
        observerble = null;
    }
}
