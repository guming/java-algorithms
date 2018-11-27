package org.jinn.gm.jdk.thread;


import java.text.DateFormat;
import java.util.Date;

/**
 * Created by gumingcn on 14-8-14.
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task());
        t1.start();
        t2.start();

    }

    public static String threadSafeFormat(Date date){
        DateFormat df=PreThreadFormater.getFormat();
        return df.format(date);
    }

}
