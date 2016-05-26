package com.weixiao.iteratormodel;

/**
 * Created by wangssmf on 2016/5/26.
 * email： wangssmf@hehenian.com
 * 功能：
 */
public class ConcreteIterator implements Iterator {

    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public Object first() {
        current = 0;
        return aggregate.get(current);
    }

    @Override
    public Object next() {
        current++;
        return aggregate.get(current);
    }

    @Override
    public Object currentItem() {
        return aggregate.get(current);
    }

    @Override
    public boolean isDone() {
        if(current>=aggregate.getCount()-1){
            return true;
        }
        return false;
    }
}
