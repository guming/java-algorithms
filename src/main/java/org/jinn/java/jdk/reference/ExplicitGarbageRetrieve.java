package org.jinn.java.jdk.reference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class ExplicitGarbageRetrieve {
    public static void main(String[] args) {
        MyDate date = new MyDate();
        date = null;
        System.gc();
    }
}
