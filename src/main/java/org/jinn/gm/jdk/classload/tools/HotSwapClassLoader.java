package org.jinn.gm.jdk.classload.tools;

/**
 * Created by gumingcn on 14-8-28.
 */
public class HotSwapClassLoader extends ClassLoader{

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
