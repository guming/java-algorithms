package org.jinn.gm.jdk.reference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class ExplicitGarbageRetrieve {
    public static void main(String[] args) throws InterruptedException {
        MyDate date = new MyDate();
        date = null;
        System.gc();
        Thread.sleep(1000*6);
    }
}
