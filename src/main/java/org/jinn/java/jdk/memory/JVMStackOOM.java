package org.jinn.java.jdk.memory;

/**
 * Created by gumingcn on 14-8-15.
 */
public class JVMStackOOM {

    public static void main(String[] args) {

        JVMStackOOM jso = new JVMStackOOM();
        jso.stackleakByThread();

    }
    private void dontstop(){
        while(true){

        }
    }

    public void stackleakByThread(){

        while(true){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    dontstop();
                }
            });
            thread.start();
        }
    }

}
