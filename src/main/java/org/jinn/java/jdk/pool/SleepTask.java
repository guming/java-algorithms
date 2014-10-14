package org.jinn.java.jdk.pool;

/**
 * Created by Yao on 2014/10/14.
 */
public class SleepTask implements Runnable {
    public static long time;
    public SleepTask(long time) {
        this.time=time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
