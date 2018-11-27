package org.jinn.gm.jdk.exception;

/**
 * Created by guming on 2017/9/14.
 */
public class BizException extends RuntimeException{
    public BizException(String sub) {
        super();
        System.out.println(sub);
    }
}
