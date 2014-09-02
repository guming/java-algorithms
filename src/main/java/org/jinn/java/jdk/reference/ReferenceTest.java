package org.jinn.java.jdk.reference;

/**
 * Created by gumingcn on 14-8-12.
 */
public class ReferenceTest {
    /** Creates a new instance of ReferenceTest */
    public ReferenceTest() {
    }

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1024 * 20];
        for(int i = 0; i < 1024 * 10; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }
        }
    }

}
