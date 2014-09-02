package org.jinn.java.jdk.classload;

/**
 * Created by gumingcn on 14-8-20.
 */
public class TestCode {
    public static final int m=0;
    public int getM(){
        return m+1;
    }
    public static void main(String[] args) {
        TestCode tc=new TestCode();
        tc.getM();
        System.out.println(tc.getM());
    }
}
