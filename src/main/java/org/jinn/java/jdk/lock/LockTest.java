package org.jinn.java.jdk.lock;

import java.util.concurrent.locks.Lock;

/**
 * Created by gumingcn on 14-9-2.
 */
public class LockTest {
    public static void main(String[] args) {
        final Lock lock = new DoubleLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();

                    try {
                        Thread.sleep(1000L);
                        System.out.println(Thread.currentThread());
                        Thread.sleep(1000L);
                    } catch (Exception ex) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i<10; i++) {
            Worker w = new Worker();
            w.start();
        }

        new Thread() {
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(200L);
                        System.out.println();
                    } catch (Exception ex) {

                    }
                }
            }
        }.start();

        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
