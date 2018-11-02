package org.jinn.java.jdk.serialize;

import java.io.*;

/**
 * Created by guming on 2017/9/11.
 */
public class SerializationUtil {

    public static Object deserialize(String fileName) throws IOException,ClassNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    public static void serialize(String fileName,Object obj) throws IOException,ClassNotFoundException{
        FileOutputStream fis = new FileOutputStream(fileName);
        ObjectOutputStream ois = new ObjectOutputStream(fis);
        ois.writeObject(obj);
        ois.close();
    }

}
