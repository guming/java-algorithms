package org.jinn.gm.jdk.serialize;

import java.io.IOException;

/**
 * Created by Yao on 2014/10/24.
 */
public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setId(1);
        user.setName("hollis");
        user.setAddress("aksdhkjahkld");
        SerializationUtil.serialize("user.ser",user);
        User user1=(User)SerializationUtil.deserialize("user.ser");
        System.out.println(user1.toString());
    }
}
