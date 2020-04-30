package pers.xy.main.classloader;

public class RunClassLoaderTest {
    public static void execute(){
        Thread thread = new Thread(new ClassMonitor());
        thread.start();
    }

    public static void currentClassLoaderTest(){
        ClassLoader classLoader = new CurrentClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        CurrentLoadObj obj = new CurrentLoadObj();
    }
}
