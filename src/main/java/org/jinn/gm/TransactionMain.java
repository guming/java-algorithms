package org.jinn.gm;

import org.jinn.gm.transaction.Element;
import org.jinn.gm.transaction.ElementHandler;
import org.jinn.gm.transaction.Scheduler;
import org.jinn.gm.transaction.Task;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionMain {
    private static int count = 8;
    private static ExecutorService executor = Executors.newFixedThreadPool(count);
    private static AtomicInteger autoIdGenerater = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
//        Thread.sleep(3000);
//        for(int j=0;j<100;j++){
            CountDownLatch countDownLatch = new CountDownLatch(count);
            ElementHandler elementHandler = new ElementHandler();
            Element element = new Element(autoIdGenerater.addAndGet(1), "test1");
            elementHandler.insert(element);
            System.out.println(element);
            for (int i = 0; i < count; i++) {
                Task task1 = new Task("r" + i, countDownLatch, elementHandler);
                executor.submit(task1);
            }
    //        Task task2 = new Task("r2", countDownLatch, elementHandler);
    //        executor.submit(task2);
//            countDownLatch.await();

            executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println(elementHandler.get(1));
            while (Scheduler.getRequestQueue().size() != 0) {
                System.out.println(Scheduler.getRequestQueue().poll());
            }
            elementHandler = null;
//        }
        executor.shutdownNow();
    }
}
