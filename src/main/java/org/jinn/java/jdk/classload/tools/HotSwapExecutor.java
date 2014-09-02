package org.jinn.java.jdk.classload.tools;

import java.lang.reflect.Method;

/**
 * Created by gumingcn on 14-8-28.
 */
public class HotSwapExecutor {
    public static void execute(byte[] bytes){
        ClassModifier cm=new ClassModifier(bytes);
        byte[] modifibyte=cm.modifyUTF8Constant("org/jinn/java/jdk/classload/tools/Foo",
                "org/jinn/java/jdk/classload/tools/HackFoo");
        HotSwapClassLoader hsc=new HotSwapClassLoader();
        Class clazz=hsc.loadByte(modifibyte);
        try {
            Method m=clazz.getMethod("main",new Class[]{String[].class});
            System.out.println(clazz.getClassLoader());
            m.invoke(null,new String[]{null});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
