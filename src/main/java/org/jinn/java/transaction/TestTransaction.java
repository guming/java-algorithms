package org.jinn.java.transaction;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestTransaction {
    private static int count = 8;
    private static ExecutorService executor = Executors.newFixedThreadPool(count);
    private static AtomicInteger autoIdGenerater = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ElementHandler elementHandler = new ElementHandler();
        Element element = new Element(autoIdGenerater.addAndGet(1),"test1");
        elementHandler.insert(element);
        System.out.println(element);
        for (int i = 0; i < count; i++) {
            Task task1 = new Task("r"+i, countDownLatch, elementHandler);
            executor.submit(task1);
        }
//        Task task2 = new Task("r2", countDownLatch, elementHandler);
//        executor.submit(task2);
        countDownLatch.await();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println(elementHandler.get(1));
        while(!Scheduler.getRequestQueue().isEmpty()){
            System.out.println(Scheduler.getRequestQueue().poll());
        }
        executor.shutdownNow();
    }
}
