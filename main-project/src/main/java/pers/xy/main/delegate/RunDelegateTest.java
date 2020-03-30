package pers.xy.main.delegate;

import java.lang.reflect.InvocationTargetException;

public class RunDelegateTest {
    public static void exec() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        EventHandler<TestEvent> handler = new EventHandler<TestEvent>();
        handler.addListen(new Consumer(), "test", TestEvent.class);
        handler.invoke(new TestEvent());
    }

    static class Consumer{
        public void test(TestEvent event){
            System.out.println(event.getTest());
        }
    }
}
