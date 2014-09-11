package org.jinn.java.jdk.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gumingcn on 14-9-11.
 */
public class SeverSelector {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9999));
            Selector selector= Selector.open();
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            while (true){
                int readycounts=selector.select();
                if(readycounts==0){
                    continue;
                }
                Set set=selector.selectedKeys();
                Iterator it= (Iterator) set.iterator();
                while(it.hasNext()){
                    SelectionKey key2 = (SelectionKey) it.next();
                    it.remove();
//                    System.out.println(key2.readyOps());
                    if(key2.isAcceptable()){
                        System.out.println("accept");
                        ServerSocketChannel  server = (ServerSocketChannel) key2.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    if(key2.isReadable()){
                        System.out.println("connect");
                        SocketChannel client = (SocketChannel) key2.channel();
                        //当客户端已经关闭时，服务端显示关闭client channel
                        if (client != null&&!client.socket().isClosed()) {
                            System.out.println("close");
                            client.close();
                            continue;
                        }
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
