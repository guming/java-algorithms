package org.jinn.gm.jdk.nio.transfer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * Created by Yao on 2014/10/16.
 */
public class GenneralFile {

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        GenneralFile.generateFile(1024 * 1024 * 1024);
        long end = System.currentTimeMillis();
        System.out.println("the times:"+(end - start));
    }
    public static void generateFile(int size) throws Exception{
        RandomAccessFile raf = new RandomAccessFile("d:/random.txt","rw");
        String temp2="{\"action\":\"edit\",\"redis_key_hash\":\"1\",\"DB_key_hash\":\"\"," +
                "\"time\":\"1406168332.35081900\",\"source\":\"web\",\"mars_cid\":\"\"," +
                "\"session_id\":\"\",\"info\":{\"cart_id\":\"6185\",\"user_id\":\""+100+"\",\"brand_id\":\"7511\"," +
                "\"num\":2,\"warehouse\":\"as大劫案快解放但就是放得开束ash侃大山" +
                "ash看动画东方航空上帝会富士康解释都很费劲第三方还是开货到付款导师考核发解释都开发还是看到横峰街道很费劲黑道教父黑道教" +
                "ash看动画东方航空上帝会富士康解释都很费劲第三方还是开货到付款导师考核发解释都开发还是看到横峰街道很费劲黑道教父黑道教" +
                "都很费劲第三方还是开货到付款导师考核发解释都开发还是看到横峰街道很费劲黑道教父黑道教" +
                "ash看动画东方航空上帝会富士康解释都很费劲第三方还师考核发解释都开发还是看到横峰街道很费劲黑道教父黑道教" +
                "父花雕鸡开户行静安寺咁大噶就是个法华经爱就是大是大非带结发华东师范\",\"merchandise_id\":\"1001950\",\"channel\":\"te\"," +
                "\"cart_record_id\":\"8765\",\"size_id\":\"2756943\"}}";
        FileChannel channel = raf.getChannel();
        int count = size/temp2.getBytes().length;
        long start = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(temp2.getBytes().length);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for(int i=0;i<count;i++){

                buffer.put(temp2.getBytes());
                buffer.flip();
            channel.write(buffer);
            long xx = System.currentTimeMillis();
            buffer.clear();//别忘了clear
        }
        channel.close();
        raf.close();
    }
    private static Random random = new Random();
    //需要申请的空间，大小不要查过-xmn
    private static final int allocateSize = 1024;

}
