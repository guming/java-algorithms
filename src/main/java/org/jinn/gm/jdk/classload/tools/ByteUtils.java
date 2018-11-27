package org.jinn.gm.jdk.classload.tools;

/**
 * Created by gumingcn on 14-8-28.
 */
public class ByteUtils {

    public static int bytes2Int(byte[] bytes,int start,int len){
        int sum=0;
        int end=start+len;
        for (int i = start; i < end; i++) {
            int n=((int)bytes[i])&0xff;
            n <<=(--len)*8;
            sum=n+sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value,int len){
        byte[] bytes=new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[len-i-1]=(byte)((value>>8*i)&0xff);
        }
        return bytes;
    }

    public static String bytes2String(byte[] bytes,int start,int len){
        return new String(bytes,start,len);
    }

    public static byte[] string2Bytes(String value){
        return value.getBytes();
    }

    public static byte[] bytesReplace(byte[] src,int offset,int len,byte[] replace){
        byte[] newBytes=new byte[src.length+(replace.length-len)];
        System.arraycopy(src,0,newBytes,0,offset);
        System.arraycopy(replace,0,newBytes,offset,replace.length);
        System.arraycopy(src,offset+len,newBytes,offset+replace.length,src.length-offset-len);
        return newBytes;
    }

}


