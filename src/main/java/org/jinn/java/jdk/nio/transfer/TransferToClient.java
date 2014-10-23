package org.jinn.java.jdk.nio.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToClient {
	
	public static void main(String[] args) throws IOException{
		TransferToClient sfc = new TransferToClient();
		sfc.testSendfile();
	}
	public void testSendfile() throws IOException {
	    String host = "localhost";
	    int port = 9026;
	    SocketAddress sad = new InetSocketAddress(host, port);
	    SocketChannel sc = SocketChannel.open();
	    sc.connect(sad);
	    sc.configureBlocking(true);
        RandomAccessFile raf = new RandomAccessFile("d:/random.txt","r");
	    FileChannel fc = raf.getChannel();
            long start = System.currentTimeMillis();
	    long nsent = 0, curnset = 0;
        long size=raf.length();
        long reminding=size;
        while(curnset<size){
             fc.transferTo(curnset, 10*1024*1024, sc);
            curnset=curnset+10*1024*1024;
            System.out.println("once");
        }
//        fc.transferTo(0, size, sc);
	    System.out.println("total bytes transferred--"+curnset+" and time taken in MS--"+(System.currentTimeMillis() - start));
	    fc.close();
        sc.close();

    }


}
