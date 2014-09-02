package org.jinn.java.jdk.classload.tools;

/**
 * Created by gumingcn on 14-8-28.
 */
public class ClassModifier {

    public static final int CONSTANT_POOL_COUNT_INDEX=8;

    public static final int CONSTANT_UTF8_INFO=1;

    public static final int[] CONSTANT_ITEM_LENGTH={-1,-1,5,-1,5,9,9,3,3,5,5,5,5};

    public static final int u1=1;

    public static final int u2=2;

    private byte[] classbyte;

    public ClassModifier(byte[] classbyte) {
        this.classbyte = classbyte;
    }

    public byte[] modifyUTF8Constant(String oldString,String newString){
        int cpc=getConstantPoolCount();
        int offset=CONSTANT_POOL_COUNT_INDEX+u2;
        for (int i = 0; i <cpc ; i++) {
            int tag=ByteUtils.bytes2Int(classbyte,offset,u1);
            if(tag==CONSTANT_UTF8_INFO){
                int len=ByteUtils.bytes2Int(classbyte,offset+u1,u2);
                offset +=(u1+u2);
                String str=ByteUtils.bytes2String(classbyte,offset,len);
                if(str.equalsIgnoreCase(oldString)){
                    byte[] bytes=ByteUtils.string2Bytes(newString);
                    byte[] strlen=ByteUtils.int2Bytes(newString.length(),u2);
                    classbyte=ByteUtils.bytesReplace(classbyte,offset-u2,u2,strlen);
                    classbyte=ByteUtils.bytesReplace(classbyte,offset,len,bytes);
                    return classbyte;
                }else{
                    offset+=len;
                }
            }else{
                offset +=CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classbyte;
    }

    public int getConstantPoolCount(){
        return ByteUtils.bytes2Int(classbyte,CONSTANT_POOL_COUNT_INDEX,u2);
    }

}
