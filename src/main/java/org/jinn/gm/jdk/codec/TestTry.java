package org.jinn.gm.jdk.codec;

/**
 * Created by gumingcn on 14-8-22.
 */
public class TestTry {

    public static int inc(){
        int x=0;
        try {
            x=1;
            throw new Exception();
//            return x;
        }catch (Exception e){
            x=2;
            System.out.println("set value 2,x:"+x);
            return x;
        }finally {
            x=3;
            System.out.println("do finally,x:"+x);
        }
    }

    public static void main(String[] args) {
        System.out.println(TestTry.inc());
    }
}
