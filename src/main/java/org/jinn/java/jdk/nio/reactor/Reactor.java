package org.jinn.java.jdk.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gumingcn on 2014/10/23.
 */
public class Reactor implements Runnable{
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey=serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
            try {
                while(!Thread.interrupted()){
                    selector.select();
                    Set set=selector.selectedKeys();
//                    System.out.println("size:"+set.size());
                    Iterator iterator = set.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = (SelectionKey)iterator.next();
                        dispatch(key);
                    }
                    set.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void dispatch(SelectionKey key){
        Runnable run=(Runnable)key.attachment();
        if(null!=run)
            run.run();
    }
    private class Acceptor implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel=serverSocketChannel.accept();
                System.out.println("client accept");
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class Handler implements Runnable{
        private   ByteBuffer readbuffer = ByteBuffer.allocate(4096);
        private   ByteBuffer writebuffer = ByteBuffer.allocate(4096);
        final SocketChannel sc;
        final SelectionKey sk;
        private Handler(Selector sl,SocketChannel sc) throws IOException {
            this.sc=sc;
            sc.configureBlocking(false);//nio none-blocking
            sk=sc.register(sl,0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sl.wakeup();
        }
        @Override
        public void run() {
            try {
                handle();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("finished");
        }
        public void handle() throws IOException {
            System.out.println("handle run");
            if(sk.isReadable()){
                System.out.println("read");
                readbuffer.clear();
                int count = sc.read(readbuffer);
                if (count > 0) {
                    String receiveText = new String( readbuffer.array(),0,count);
                    System.out.println("server read:"+receiveText);
                }
                sk.interestOps(SelectionKey.OP_WRITE);
//                sc.register(selector, SelectionKey.OP_WRITE);//error
            }
            if(sk.isWritable()){
                System.out.println("write");
//                SocketChannel client = (SocketChannel) sk.channel();
                writebuffer.clear();
                String sendText = "send to client:" +" test";
                writebuffer.put(sendText.getBytes());
                writebuffer.flip();
                sc.write(writebuffer);
                sk.interestOps(SelectionKey.OP_READ);
//                sc.register(selector, SelectionKey.OP_READ);
            }
        }
    }
    public static void main(String[] args) {
        try {
            Reactor reactor=new Reactor(8888);
            reactor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
