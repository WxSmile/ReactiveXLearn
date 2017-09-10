package com.weixiao.publish_subscribe.publish_subscribe2;

import java.util.ArrayList;

/**
 * Created by weixiao on 2017/9/10.
 */

public class Demo2 {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(15);
        integers.add(20);
        Observerble observerble = Observerble.create();
        Subscription subscribe = observerble.setNumber(integers).subscribe(new Observer() {

            private Subscription subscription;

            @Override
            public void onNext(int number) {
                System.out.println("onNext Binary String: " + number + "= "
                        + Integer.toBinaryString(number));
                if (number == 20) {
                    System.out.println("onNext -- cancel ");
                    subscription.cancel();
                }
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                this.subscription = subscription;
            }
        });
        subscribe.cancel();
    }
}
