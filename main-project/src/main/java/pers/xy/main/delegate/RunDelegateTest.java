package pers.xy.main.delegate;

import java.lang.reflect.InvocationTargetException;

public class RunDelegateTest {
    public static void exec() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        EventHandle<TestEvent> handler = new EventHandle<TestEvent>();
        handler.addListen(new Consumer(), "test", TestEvent.class);
        handler.invoke(new TestEvent());
    }

    static class Consumer{
        public void test(TestEvent event){
            System.out.println(event.getTest());
        }
    }
}
