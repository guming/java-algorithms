package org.jinn.gm.jdk.classload.hotswap;

/**
 * Created by gumingcn on 14-8-28.
 */
public class HotswapCL extends ClassLoader {
    public HotswapCL(ClassLoader parent) {
        super(parent);
    }
    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
