package org.jinn.gm.jdk.nio;

import java.nio.channels.Selector;

/**
 * Created by gumingcn on 2014/10/22.
 */
public class TestSelector {
    private static final int MAXSIZE=5;
    public static final void main( String argc[] ) {
        Selector [] sels = new Selector[ MAXSIZE];
        try{
            for( int i = 0 ;i< MAXSIZE ;++i ) {
                sels[i] = Selector.open();
                //sels[i].close();
            }
            Thread.sleep(30000);
        }catch( Exception ex ){
            throw new RuntimeException( ex );
        }
        int OP_ACCEPT = 1 << 4;
        System.out.println(OP_ACCEPT);
        int OP_CONNECT = 1 << 3;
        System.out.println(OP_CONNECT);
        System.out.println(~OP_CONNECT);
    }
}
