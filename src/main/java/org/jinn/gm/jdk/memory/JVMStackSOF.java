package org.jinn.gm.jdk.memory;

/**
 * Created by gumingcn on 14-8-15.
 */
public class JVMStackSOF {

    private int stacklength=1;

    public void stackLength(){
        stacklength++;
        stackLength();
    }

    public static void main(String[] args) throws Throwable{
        JVMStackSOF jss=new JVMStackSOF();
        try{
            jss.stackLength();
        }catch (Throwable e){
            System.out.println("stack length:"+jss.stacklength);
            throw e;
        }
    }
}
