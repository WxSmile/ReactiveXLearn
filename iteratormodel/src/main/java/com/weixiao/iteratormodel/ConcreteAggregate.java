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

        return new ConcreteIterator(this);
    }

    public Object get(int index){
        return items.get(index);
    }

    public int getCount(){
        return items.size();
    }


}
