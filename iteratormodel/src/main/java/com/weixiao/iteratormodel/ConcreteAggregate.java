package com.weixiao.iteratormodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangssmf on 2016/5/26.
 * email： wangssmf@hehenian.com
 * 功能：
 */
public class ConcreteAggregate implements Aggregat {


    private List<Object> items = new ArrayList<>();

    @Override
    public Iterator creatIterator() {
        items.add("item 1");
        items.add("item 2");
        items.add("item 3");
        return new ConcreteIterator(this);
    }

    public Object get(int index){
        return items.get(index);
    }

    public int getCount(){
        return items.size();
    }


}
