package org.jinn.java.jdk.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by gumingcn on 14-8-12.
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {

        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.gc();
    }
}
