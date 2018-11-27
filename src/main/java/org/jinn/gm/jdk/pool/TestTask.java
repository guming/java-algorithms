package org.jinn.gm.jdk.pool;

/**
 * Created by gumingcn on 2014/10/14.
 */
public class TestTask implements Runnable {
    public static long time;
    public TestTask(long time) {
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
