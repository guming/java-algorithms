package org.jinn.java.jdk.reflect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by guming on 2017/9/20.
 */
public class MyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get(className);
            CtMethod m = cc.getDeclaredMethod("increase");
            m.insertBefore(" long s=System.currentTimeMillis();");
            m.insertAfter(" long e=System.currentTimeMillis();System.out.println(e);");
//            cc.writeFile();
//            Class c = cc.toClass();
//            MyClass h = (MyClass)c.newInstance();
//            h.increase(5);
//            System.out.println(h.count);
            return cc.toBytecode();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
