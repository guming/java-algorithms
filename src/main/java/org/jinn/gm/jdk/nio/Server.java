package org.jinn.gm.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by gumingcn on 14-9-11.
 */
public class Server {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel=null;
        try {

            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            ByteBuffer bb=ByteBuffer.allocate(1024);
            while (true){
                SocketChannel sc=serverSocketChannel.accept();
                if (sc != null) {
                    sc.read(bb);
                    bb.flip();
                    System.out.println(new String(bb.array()));
                    bb.clear();
                    System.out.println("-------------");
                }
                if(sc!=null&&!sc.socket().isClosed()) {
                    System.out.println(sc.isConnected());
                    System.out.println(sc.isOpen());
                    System.out.println(sc.socket().isClosed());
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
