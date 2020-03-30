package pers.xy.main.delegate;

public class TestEvent extends Event {
    private String test = "consume event";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
