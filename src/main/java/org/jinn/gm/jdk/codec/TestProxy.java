package org.jinn.gm.jdk.codec;

import java.lang.reflect.Proxy;

/**
 * Created by cyrus.gu on 2015/2/9.
 */
public class TestProxy {

    public static void main(String[] args) {
        User u=new User();
        u.setId(10);
        IUser uproxy = (IUser) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{IUser.class}, new DynamicProxyHandler(u));
        System.out.println(uproxy.getId());
        uproxy.setAge(20);
        System.out.println(u.getAge());
    }

}
