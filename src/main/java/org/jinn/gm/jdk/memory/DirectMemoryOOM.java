package org.jinn.gm.jdk.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by gumingcn on 14-8-15.
 */
public class DirectMemoryOOM {

    public static final int _1mb=1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField= Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe=(Unsafe)unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1mb);
        }
    }

}