package pers.xy.main.classloader;

import pers.xy.common.BaseManager;

public class LoadInfo {
    /** 自定义的类加载器 */
    private MyClassLoader myClasslLoader;

    /** 记录要加载的类的时间戳-->加载的时间 */
    private long loadTime;

    /** 需要被热加载的类 */
    private BaseManager manager;

    public LoadInfo(MyClassLoader myClasslLoader, long loadTime) {
        this.myClasslLoader = myClasslLoader;
        this.loadTime = loadTime;
    }

    public MyClassLoader getMyClasslLoader() {
        return myClasslLoader;
    }

    public void setMyClasslLoader(MyClassLoader myClasslLoader) {
        this.myClasslLoader = myClasslLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
