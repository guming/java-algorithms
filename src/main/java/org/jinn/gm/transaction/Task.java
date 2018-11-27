package org.jinn.gm.transaction;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable{
    private String name;
    private CountDownLatch countDownLatch;
    private ElementHandler elementHandler;
    public Task(String name, CountDownLatch cdl, ElementHandler elementHandler) {
        this.name = name;
        this.countDownLatch = cdl;
        this.elementHandler = elementHandler;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId()+","+name);
        Element element = new Element(1,name);
        long start = System.currentTimeMillis();
        SingalVersionTransaction svt = new SingalVersionTransaction(element);
        svt.start();
        int resultCode=elementHandler.update(element,svt);
        if(resultCode>0) {
            System.out.println("update success:" + element);
        }
        svt.commit();
        long exec = System.currentTimeMillis() - start;
        System.out.println("exec time:"+exec+","+Thread.currentThread().getId()+","+name);
    }
}
