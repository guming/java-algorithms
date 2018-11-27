package org.jinn.gm.jdk.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gumingcn on 2014/9/25.
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<String, String>(2048*1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i <1000000 ; i++) {
            map.put(i+"key",i+"value");
        }
        System.out.println(System.currentTimeMillis()-start);
        for (String s : map.keySet()) {
            map.get(s);
        }
        System.out.println(System.currentTimeMillis()-start);
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
