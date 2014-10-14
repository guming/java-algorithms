package org.jinn.java.jdk.pool;

import java.util.concurrent.*;

/**
 * Created by gumingcn on 2014/10/14.
 */
public class TestJdkThreadExecutor {
    public static void main(String[] args) {
        int minThread = 2;
        int maxThead = 10;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(maxThead);
        ThreadPoolExecutor executor =  new ThreadPoolExecutor(minThread,maxThead, 60, TimeUnit.SECONDS,queue, Executors.defaultThreadFactory());
        for(int i=0;i<minThread;i++){
            executor.submit(new TestTask(10000));
        }
        System.out.println(minThread==executor.getPoolSize());
        for(int i=minThread;i<maxThead;i++){
            executor.submit(new TestTask(10000));
        }
        System.out.println(2 == executor.getPoolSize());
        System.out.println(8 == queue.size());
        for(int i=0;i<maxThead;i++){
            executor.submit(new TestTask(10000));
        }
        System.out.println(maxThead == executor.getPoolSize());
        try{
            executor.submit(new TestTask(10000));
        }catch(RejectedExecutionException e){
        }
    }

}
