package org.jinn.java.jdk.reference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class ImplicitGarbageRetrieve {
    public static void main(String[] args) {
        MyDate date = new MyDate();
        date = null;
        ReferenceTest.drainMemory();
    }
}
