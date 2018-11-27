package org.jinn.gm.jdk.collection;

import java.util.LinkedHashMap;

/**
 * Created by Yao on 2014/11/4.
 */
public class TestLinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(5,0.75f,true);
        linkedHashMap.put("one","1");
        linkedHashMap.put("two", "2");
        linkedHashMap.put("three", "3");
        linkedHashMap.get("one");

        System.out.println(linkedHashMap.containsValue("3"));
    }
}
