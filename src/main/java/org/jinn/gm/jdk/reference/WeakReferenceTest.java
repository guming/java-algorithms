package org.jinn.gm.jdk.reference;

import java.lang.ref.WeakReference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference wdate = new WeakReference(new MyDate());
        ReferenceTest.drainMemory();
        System.gc();
        System.out.println(wdate.get());
    }
}
