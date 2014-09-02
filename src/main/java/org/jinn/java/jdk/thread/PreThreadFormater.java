package org.jinn.java.jdk.thread;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by gumingcn on 14-8-14.
 */
public class PreThreadFormater {

        public static final ThreadLocal<SimpleDateFormat> dataFormatHolder=
                new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected  SimpleDateFormat initialValue(){
                System.out.println("Creating SimpleDateFormat for Thread : " + Thread.currentThread().getName());
                return new SimpleDateFormat("dd/MM/yyyy");

            }
        };
    public static DateFormat getFormat() {
        return dataFormatHolder.get();
    }
}
