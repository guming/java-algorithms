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
                System.out.println(TestHotswap.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                System.out.println(TestHotswap.class.getResource("/").getPath());
                InputStream in = new FileInputStream(TestHotswap.class.getResource("/").getPath()+"org/jinn/java/jdk/classload/hotswap/"+"Bee.class");
                byte[] classbyte = new byte[in.available()];
                in.read(classbyte);
                in.close();
                HotswapCL hcl = new HotswapCL(HotswapCL.class.getClassLoader());
                Class clazz = hcl.loadByte(classbyte);
                Object bee = clazz.newInstance();
                Bee bee1 = new Bee();
                if(bee instanceof Bee){
                }
                if(bee1 instanceof Bee){
                    System.out.println(Bee.class.getClassLoader());
                    System.out.println(bee.getClass().getClassLoader());
                }
                Method m = bee.getClass().getMethod("getValue", new Class[]{});
                m.invoke(bee, new Object[]{});

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
