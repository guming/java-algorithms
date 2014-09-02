package org.jinn.java.jdk.classload;

/**
 * Created by gumingcn on 14-8-21.
 */
public class NotificationTest {
    public static void main(String[] args) {
//        System.out.println(SubTestStatic.value);
        //base static init
        //123
        //subtest 没有执行init 因为引用的是父类的static 而不需要加载子类
//        System.out.println(SubTestStatic.value2);
        //456
        //常量直接输出值 因为这个常量已经在NotificationTest常量池里
//        SubTestStatic[] b=new SubTestStatic[5];
        //nothing to print
        //没有加载
//        b[0]=new SubTestStatic();
//        System.out.println("test:"+b[0].getValue());
        //base init & sub init
        //123
//        SubTestStatic st=new SubTestStatic();
//        System.out.println(st.getValue2());
    }
}
