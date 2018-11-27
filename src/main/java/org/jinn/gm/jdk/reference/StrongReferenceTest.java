package org.jinn.gm.jdk.reference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        MyDate date = new MyDate();
        System.gc();
    }
}
