package org.jinn.gm.jdk.nio.transfer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class TransferToServer  {
  ServerSocketChannel listener = null;
  protected void mySetup()
  {
    InetSocketAddress listenAddr =  new InetSocketAddress(9026);

    try {
      listener = ServerSocketChannel.open();
      ServerSocket ss = listener.socket();
      ss.setReuseAddress(true);
      ss.bind(listenAddr);
      System.out.println("Listening on port : "+ listenAddr.toString());
    } catch (IOException e) {
      System.out.println("Failed to bind, is port : "+ listenAddr.toString()
          + " already in use ? Error Msg : "+e.getMessage());
      e.printStackTrace();
    }

  }

  public static void main(String[] args)
  {
    TransferToServer dns = new TransferToServer();
    dns.mySetup();
    dns.readData();
  }

  private void readData()  {

      ByteBuffer dst = ByteBuffer.allocate(4 * 1024 * 1024);
      try {
          RandomAccessFile raf = new RandomAccessFile("d:/random2.txt","rw");
          FileChannel fc=raf.getChannel();
          MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, 100*1024*1024);
          SocketChannel conn = listener.accept();
          long start = System.currentTimeMillis();
          System.out.println("Accepted : "+conn);
          conn.configureBlocking(true);
          int nread = 0;
          while (nread != -1)  {
                  try {
					  nread = conn.read(mappedByteBuffer);
//                      dst.flip();
//                      mappedByteBuffer.put(dst);
//                      dst=null;
                  } catch (IOException e) {
                      e.printStackTrace();
                      nread = -1;
                  }
                 dst.rewind();
              }

          mappedByteBuffer.force();
          System.out.println("recv end:"+(System.currentTimeMillis()-start));
      } catch (IOException e) {
		  e.printStackTrace();
	  }
  }
}
