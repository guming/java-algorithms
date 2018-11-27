package org.jinn.gm.jdk.reference;

import java.lang.ref.SoftReference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        test();
    }
    public static void test(){
        System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024));
        SoftReference sdate=new SoftReference(new MyDate());
//        sdate = null;
//        System.gc();
        ReferenceTest.drainMemory();
        Byte[] b=new Byte[1024*1024*30];
        System.out.println(sdate.get());
        System.out.println(Runtime.getRuntime().freeMemory()/(1024*1024));
    }
}
