package org.jinn.java.jdk.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gumingcn on 14-8-15.
 */
public class HeapOOM {

    public static void main(String[] args) {
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<TestObject> testObjectList = new ArrayList<TestObject>();
        long start=Runtime.getRuntime().freeMemory();
        int i=4;
        while(i>0) {
            testObjectList.add(new TestObject());
            System.out.println("put it.");
            i--;
        }
        long end=Runtime.getRuntime().freeMemory();
        System.out.println((start-end)/1024);
    }

    static class TestObject{
        byte[] b=new byte[1024*1024];
    }
}
