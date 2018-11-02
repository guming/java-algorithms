package org.jinn.java.transaction;

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
        SingalVersionTransaction svt = new SingalVersionTransaction(element);
        svt.start();
        countDownLatch.countDown();
        int resultCode=elementHandler.update(element,svt);
        if(resultCode>0) {
            System.out.println("update success:" + element);
        }
        svt.commit();
    }
}
