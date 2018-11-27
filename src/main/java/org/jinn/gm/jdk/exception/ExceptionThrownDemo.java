package org.jinn.gm.jdk.exception;

/**
 * Created by guming on 2017/9/14.
 */
public class ExceptionThrownDemo {
    public static void main(String[] args) {
        System.out.println("----------------");
        try{
            throw new RuntimeException("try");
        }catch (Exception e){
            System.out.println("come try");
            throw new RuntimeException("sd");
        }finally {
            System.out.println("finally");
        }
    }
}
