package pers.xy.main.generictype;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class RunGenericTypeTest {
    public static void run(){
        List<Test1> list = new ArrayList();
        list.add(new Test1("test1"));
        list.add(new Test1("test2"));
        list.add(new Test1("test3"));
        String str = JSONObject.toJSON(list).toString();

        List parseList = JSONObject.parseObject(str, new TypeReference<List<Test1>>(){});

        System.out.println(JSONObject.toJSON(parseList).toString());
    }

    public static void runMutil(){
        TestGeneric<Test1, Test2> test = new TestGeneric<Test1, Test2>();
        test.setD(new Test2("test2"));
        test.setT(new Test1("test1"));
        String str = JSONObject.toJSON(test).toString();
        TestGeneric generic = JSONObject.parseObject(str, new TypeReference<TestGeneric<Test1, Test2>>(){});
        System.out.println(JSONObject.toJSON(generic).toString());
    }
}
