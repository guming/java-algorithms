package org.jinn.gm.jdk.reflect;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by guming on 2017/9/19.
 */
public class TestMyClass {

    public List getList(final List list){
        return (List) Proxy.newProxyInstance(TestMyClass.class.getClassLoader(), new Class[]{List.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if("add".equals(method.getName())){
                    throw new UnsupportedOperationException();
                }else{
                    return method.invoke(list, args);
                }
            }
        });
    }
    public static void main(String[] args) {
        MyClass myClass = new MyClass(0);
        myClass.increase(2);
        System.out.println(myClass.count);
        try {
            Constructor constructor = MyClass.class.getConstructor(int.class);
            Object myClassr = constructor.newInstance(0);
            Method method = MyClass.class.getMethod("increase", int.class);
            method.invoke(myClassr, 5);
            Field field = MyClass.class.getField("count");
            System.out.println("reflect:"+field.get(myClassr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        TestMyClass testMyClass = new TestMyClass();
        List rlist=testMyClass.getList(arrayList);
        System.out.println(rlist.get(0));
        rlist.add(3);

    }

}
