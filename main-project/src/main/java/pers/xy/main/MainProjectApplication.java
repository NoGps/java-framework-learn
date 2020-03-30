package pers.xy.main;

import org.quartz.SchedulerException;
import pers.xy.main.delegate.RunDelegateTest;
import pers.xy.main.quartz.MyScheduler;

public class MainProjectApplication {
    public static void main(String[] args) throws Exception {
        //RunJdkProxyTest.run();
        //OverrideTestRun.run();
        //RunCglibTest.run();
        //RunCglibTest.runCallBack();
        //RunCglibTest.runFixed();
//        RunGenericTypeTest.run();
//        RunGenericTypeTest.runMutil();
//        RunClassLoaderTest.execute();
        //MyScheduler.exec();
        RunDelegateTest.exec();
    }
}
