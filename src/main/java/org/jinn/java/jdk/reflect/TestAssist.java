package org.jinn.java.jdk.reflect;

import javassist.*;

import java.io.IOException;

/**
 * Created by guming on 2017/9/19.
 */
public class TestAssist {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("org.jinn.java.jdk.reflect.MyClass");
            CtMethod m = cc.getDeclaredMethod("increase");
            m.insertBefore(" long s=System.currentTimeMillis();");
            m.insertAfter(" long e=System.currentTimeMillis();System.out.println(e);");
//            cc.writeFile();
            Class c = cc.toClass();
            MyClass h = (MyClass)c.newInstance();
            h.increase(5);
            System.out.println(h.count);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
