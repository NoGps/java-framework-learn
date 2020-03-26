package pers.xy.main.cglib;

import net.sf.cglib.proxy.*;

public class RunCglibTest {
    public static void run(){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new TestMethodInterceptor());
        Test test=(Test)enhancer.create();
        test.test();
    }

    public static void runFixed(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        FixedValue fixedValue = new TestFixed();
        enhancer.setCallbacks(new Callback[]{new TestFixed(), new TestMethodInterceptor()});
        enhancer.setCallbackFilter(new TestCallbackFilter());
        Test test = (Test)enhancer.create();
        System.out.println(test.test1());
    }

    public static void runCallBack(){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(Test.class);
        CallbackFilter callbackFilter = new TestCallbackFilter();

        /**
         * (1)callback1：方法拦截器
         (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb= NoOp.INSTANCE;
        Callback callback1=new TestMethodInterceptor();
        Callback fixedValue=new TestFixed();
        Callback[] cbarray=new Callback[]{callback1,noopCb,fixedValue};
        //enhancer.setCallback(new TargetInterceptor());
        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(callbackFilter);
        Test test=(Test)enhancer.create();
        //test.test();
        System.out.println( test.test1());
    }
}
