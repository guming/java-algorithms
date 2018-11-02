package org.jinn.java.jdk.exception;

/**
 * Created by guming on 2017/9/14.
 */
public class ExceptionTestDemo {

    public void test() throws SubBizException{
        throw new BizException("father");
    }

    public static void main(String[] args) {
        ExceptionTestDemo demo = new ExceptionTestDemo();
        demo.test();
    }
}
