package org.jinn.gm.jdk.classload;

/**
 * Created by gumingcn on 14-8-21.
 */
public class BaseTestStatic {
    static{
        System.out.println("base static init");
    }
    public static int value=123;
    public static final int value2=456;
    public int value3=789;

    public int getValue() {
        return value;
    }
    public int getValue2() {
        return value3;
    }
}
