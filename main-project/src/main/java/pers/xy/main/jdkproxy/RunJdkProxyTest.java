package pers.xy.main.jdkproxy;

public class RunJdkProxyTest {
    public static void run(){
        TestProxy proxy = new TestProxy(new TestImpl());
        Test test = proxy.getProxy();
        test.test();
    }
}
