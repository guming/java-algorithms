package org.jinn.java.jdk.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by gumingcn on 14-8-12.
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {
        MyDate myDate = new MyDate();
        ReferenceQueue queue = new ReferenceQueue();
        System.gc();
        System.out.println("--------");
        PhantomReference ref = new PhantomReference(myDate, queue);
        myDate = null;
        System.gc();
        try {
            Thread.sleep(1000*6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
