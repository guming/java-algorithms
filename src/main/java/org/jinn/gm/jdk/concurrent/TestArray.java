package org.jinn.gm.jdk.concurrent;

/**
 * Created by gumingcn on 14-8-28.
 */
public class TestArray {
    public static void main(String[] args) {
        Integer[] arr=new Integer[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i]=i;
        }
        long start=System.currentTimeMillis();
        int sum=0;
        int j=0;
        for (int i = 0; i < arr.length; i++) {
//            sum=arr[i]+arr[i+1];
//            if(i<=(arr.length-1))
//                sum+=arr[i+1];

//            i++;
            sum+=arr[i];
            j++;
        }

        System.out.println(System.currentTimeMillis()-start);
        System.out.println("sum:"+sum+",j:"+j);
    }
}
