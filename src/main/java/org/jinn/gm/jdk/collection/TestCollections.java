package org.jinn.gm.jdk.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by gumingcn on 2014/9/26.
 */
public class TestCollections {
    public static List map = new CopyOnWriteArrayList<String>(new ArrayList<String>());

    public static void main(String[] args) {
        ExecutorService pool= Executors.newFixedThreadPool(2);
        List n=new ArrayList();
        for (int i = 0; i <5 ; i++) {
            final int s=i;
            Callable r=new Callable() {
                public Future call() {
                    map.add(0,s);
                    System.out.println("value:" + map.get(0));
                    return null;
                }
            };
            n.add(r);
        }
        try {
            pool.invokeAll(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

    }
}
