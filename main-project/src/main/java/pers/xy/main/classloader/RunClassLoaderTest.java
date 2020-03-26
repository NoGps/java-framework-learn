package pers.xy.main.classloader;

public class RunClassLoaderTest {
    public static void execute(){
        Thread thread = new Thread(new ClassMonitor());
        thread.start();
    }
}
