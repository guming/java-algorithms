package org.jinn.gm.jdk.nio.reactor;

/**
 * Created by gumingcn on 14-9-11.
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {

    private static int flag = 0;
    private static int BLOCK = 4096;
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(
            "localhost", 8888);
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(SERVER_ADDRESS);

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count=0;

        while (true) {
            selector.select();
            selectionKeys = selector.selectedKeys();
//            System.out.println(selectionKeys.size());
            iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    System.out.println("client connect");
                    client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                        System.out.println("connected!");
                    }
                    sendbuffer.put("Hello,Server".getBytes());
                    sendbuffer.flip();
                    client.write(sendbuffer);
                    selectionKey.interestOps(SelectionKey.OP_READ);
//                    client.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    client = (SocketChannel) selectionKey.channel();
                    receivebuffer.clear();
                    count=client.read(receivebuffer);
                    if(count>0){
                        receiveText = new String( receivebuffer.array(),0,count);
                        System.out.println("receive:" + receiveText);
                    }
//                    client.register(selector, SelectionKey.OP_WRITE);
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                } else if (selectionKey.isWritable()) {
                    sendbuffer.clear();
                    client = (SocketChannel) selectionKey.channel();
                    sendText = "message from client--" + (flag++);
                    sendbuffer.put(sendText.getBytes());
                    sendbuffer.flip();
                    client.write(sendbuffer);
//                  client.register(selector, SelectionKey.OP_READ);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }
    }
}

