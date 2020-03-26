package pers.xy.main.cglib;

public class Test {

    public void test(String str){
        System.out.println("hello, private test " + str);
    }

    public void test(){
        System.out.println("hello, public test");
    }

    public String test1(){
        return "hello, test1";
    }
}
