package pers.xy.main.classloader;

import pers.xy.common.BaseManager;

public class ClassMonitor implements Runnable {
    public void run() {
        while (true) {
            BaseManager manager = ManagerFactory.getManager();
            manager.logic();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
