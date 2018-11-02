package org.jinn.java.jdk.exception;

/**
 * Created by guming on 2017/9/14.
 */
public class SubBizException extends BizException {
    public SubBizException(String sub) {
        super("sub");
        System.out.println(sub);
    }
}
