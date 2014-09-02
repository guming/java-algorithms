package org.jinn.java.jdk.classload.hotswap;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by gumingcn on 14-8-28.
 */
public class TestHotswap {

    public static void main(String[] args) {
        while(true) {
            try {

                InputStream in = new FileInputStream("/Users/gumingcn/dev/work/java-algorithms/target/classes/org/jinn/java/jdk/classload/hotswap/Bee.class");
                byte[] classbyte = new byte[in.available()];
                in.read(classbyte);
                in.close();
                HotswapCL hcl = new HotswapCL(HotswapCL.class.getClassLoader());
                Class clazz = hcl.loadByte(classbyte);
                Object bee = clazz.newInstance();
                Method m = bee.getClass().getMethod("getValue", new Class[]{});
                m.invoke(bee, new Object[]{});

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
