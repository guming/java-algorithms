package org.jinn.gm.jdk.codec;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cyrus.gu on 2015/2/9.
 */
public class DynamicProxyHandler implements InvocationHandler{
    private Object obj;
    public DynamicProxyHandler(Object obj) {
        this.obj=obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args != null) {
            for (Object arg:args){
                System.out.println("k "+arg);
            }
        }
        return method.invoke(obj,args);
    }

}
