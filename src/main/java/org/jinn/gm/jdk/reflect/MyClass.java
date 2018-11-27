package org.jinn.gm.jdk.reflect;

/**
 * Created by guming on 2017/9/15.
 */
public class MyClass {
    public int count;
    public MyClass(int start) {
        count = start;
    }
    public void increase(int step) {
        count = count + step;
    }

    public MyClass() {
    }


}
