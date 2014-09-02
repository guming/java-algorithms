package org.jinn.java.jdk.classload.hotswap;

/**
 * Created by gumingcn on 14-8-28.
 */
public class Bee {
    private int value=1;

    public int getValue() {
        System.out.println("version one.");
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
