package org.jinn.gm.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by gumingcn on 14-9-11.
 */
public class Client implements Runnable{
    public static String name;
    public static void main(String[] args) {
    }

    public Client(String v) {
        name=v;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc=SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1",9999));
            if (sc.isConnected()){
                ByteBuffer bb=ByteBuffer.allocate(1024);
                bb.put(("test 123"+name).getBytes());
                bb.flip();
                sc.write(bb);
            }
            sc.close();
//            sc.socket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
