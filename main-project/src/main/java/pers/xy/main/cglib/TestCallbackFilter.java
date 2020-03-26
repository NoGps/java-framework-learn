package pers.xy.main.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class TestCallbackFilter implements CallbackFilter {
    public int accept(Method method) {
        if(method.getName().equals("test")){
            System.out.println("filter test method");
            return 0;
        }
        return 0;
    }
}
