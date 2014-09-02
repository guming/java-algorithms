package org.jinn.java.jdk.classload;

/**
 * Created by gumingcn on 14-8-21.
 */
public class SubTestStatic extends BaseTestStatic{
    static{
        System.out.println("sub static init");
    }

    public static void main(String[] args) {
        System.out.println(SubTestStatic.value);

    }
}
