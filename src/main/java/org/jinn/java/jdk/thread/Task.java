package org.jinn.java.jdk.thread;

/**
 * Created by gumingcn on 14-8-14.
 */

import java.util.Date;

public class Task implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<2; i++){
            System.out.println("Thread: " + Thread.currentThread().getName() + " Formatted Date: " + ThreadLocalTest.threadSafeFormat(new Date()) );
        }

    }
}
