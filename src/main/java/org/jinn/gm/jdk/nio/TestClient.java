package org.jinn.gm.jdk.nio;

/**
 * Created by gumingcn on 14-9-11.
 */
public class TestClient {

    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
            Client a=new Client(System.currentTimeMillis()+"-");
            a.run();
//        }
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
