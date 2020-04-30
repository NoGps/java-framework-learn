package pers.xy.plugin.mymanager;

import pers.xy.common.BaseManager;

public class MyManager implements BaseManager {
    Counter counter = new Counter();
    public void logic() {
        System.out.println(counter.getClass().getClassLoader().getClass().getName());
        counter.count();
    }
}
