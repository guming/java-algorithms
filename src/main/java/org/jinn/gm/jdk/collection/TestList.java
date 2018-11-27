package org.jinn.gm.jdk.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by gumingcn on 2014/9/25.
 */
public class TestList {
    public static void main(String[] args) {
        ArrayList list1=new ArrayList();
        LinkedList list2=new LinkedList();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list1.add(i);
        }

        long end=System.currentTimeMillis();
        System.out.println(end-start);


        for (int i = 0; i <1000000 ; i++) {
            list2.add(i);
        }

        System.out.println(System.currentTimeMillis()-end);
    }
}
