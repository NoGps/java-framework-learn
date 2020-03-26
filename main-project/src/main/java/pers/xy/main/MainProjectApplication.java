package pers.xy.main;

import pers.xy.main.classloader.RunClassLoaderTest;
import pers.xy.main.generictype.RunGenericTypeTest;

public class MainProjectApplication {
    public static void main(String[] args) {
        //RunJdkProxyTest.run();
        //OverrideTestRun.run();
        //RunCglibTest.run();
        //RunCglibTest.runCallBack();
        //RunCglibTest.runFixed();
//        RunGenericTypeTest.run();
//        RunGenericTypeTest.runMutil();
        RunClassLoaderTest.execute();
    }
}
