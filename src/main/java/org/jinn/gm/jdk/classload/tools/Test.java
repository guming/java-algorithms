package org.jinn.gm.jdk.classload.tools;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by gumingcn on 14-8-28.
 */
public class Test {
    public static void main(String[] args) {
        try {
            InputStream in=new FileInputStream("/Users/gumingcn/dev/work/gm-algorithms/target/classes/org/jinn/gm/jdk/classload/tools/Caculate.class");
            byte[] classbyte=new byte[in.available()];
            in.read(classbyte);
            in.close();
            HotSwapExecutor.execute(classbyte);
            Foo f=new Foo();
            HackFoo hf=new HackFoo();
            System.out.println(Foo.class.getClassLoader());
            System.out.println(HackFoo.class.getClassLoader());
            f.add(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
