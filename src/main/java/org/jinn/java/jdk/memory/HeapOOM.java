package org.jinn.java.jdk.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gumingcn on 14-8-15.
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<TestObject> testObjectList = new ArrayList<TestObject>();
        while(true) {
            testObjectList.add(new TestObject());
        }
    }

    static class TestObject{

    }
}
