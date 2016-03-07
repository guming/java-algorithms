package org.jinn.java.algorithms.sort;

import java.util.Comparator;

/**
 * Created by gumingcn on 2014/12/15.
 */
public class MergeSort<E extends Comparator<E>> {

    public int[] sort(int[] unsorted){
        if (unsorted.length==1)
            return unsorted;
        int half = unsorted.length/2;
        int[] arr1=new int[half];
        int[] arr2=new int[unsorted.length-half];

        System.arraycopy(unsorted,0,arr1,0,arr1.length);
        System.arraycopy(unsorted,half,arr2,0,arr2.length);
        arr1=sort(arr1);
        arr2=sort(arr2);
        return mergesorted(arr1,arr2);
    }

    public int[] mergesorted(int[] arr1, int[] arr2) {
        int i=0,j=0,k=0;
        int[] result=new int[arr1.length+ arr2.length];

        while (true) {
            if (arr1[i]<arr2[j]){
                result[k]=arr1[i];
                if (++i>arr1.length-1){
                    break;
                }
            }else{
                result[k]=arr2[j];
                if (++j>arr2.length-1){
                    break;
                }
            }
            k++;
        }
        for(;i<arr1.length;i++){
            result[++k] = arr1[i];
        }
        for(;j<arr2.length;j++){
            result[++k] = arr2[j];
        }
        return result;
    }

    public static void main(String[] args) {
        MergeSort ms=new MergeSort();
        int[] unsorted={3,4,1,5,2,7,14,9,8,23};
        int[] result=ms.sort(unsorted);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}