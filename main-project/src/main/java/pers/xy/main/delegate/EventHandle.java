package pers.xy.main.delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventHandle<T extends Event> {

    private List<Object> objects = new ArrayList<Object>();
    private List<String> methodNames = new ArrayList<String>();
    private List<Class> paramTypesList = new ArrayList<Class>();

    public EventHandle(){

    }

    public void addListen(Object object,String methodName,Class<?> arg){
        this.objects.add(object);
        this.methodNames.add(methodName);
        this.paramTypesList.add(arg);
    }

    public void invoke(Event event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for(int i = 0; i < objects.size(); i++){
            Method method=objects.get(i).getClass().getMethod(methodNames.get(i), paramTypesList.get(i));
            if(null==method){
                return;
            }
            method.invoke(objects.get(i), event);
        }

    }
}
