package org.jinn.java.algorithms.list;

import org.jinn.java.algorithms.list.LinkedList;

/**
 * Created by guming on 14-3-17.
 */
public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<String> llist = new LinkedList<String>();
        llist.push("a");
        llist.push("b");
        llist.push("c");
        llist.push("d");
        for (int i=0;i<4;i++) {
            System.out.println(llist.poll());
        }
    }
}
