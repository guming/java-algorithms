package org.jinn.java.jdk.classload.tools;

/**
 * Created by gumingcn on 14-8-28.
 */
public class HackFoo {
    public static final int value=1;
    public int add(int i){
        System.out.println(">>>>>" + i);
        return value+i;
    }
}
