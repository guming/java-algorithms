package org.jinn.java.algorithms.sort;

import java.util.Random;

/**
 * Created by guming on 14-3-20.
 */
public class QuickSort<E extends Comparable<E>> {

    private static final Random RANDOM = new Random();

    private PIVOT_TYPE type=PIVOT_TYPE.RANDOM;

    public static enum PIVOT_TYPE {
        FIRST, MIDDLE, RANDOM
    };
    public E[] sorted(E[] unsorted,PIVOT_TYPE conType) {
        int pivot = 0;
        if (type == PIVOT_TYPE.MIDDLE) {
            pivot = unsorted.length/2;
        } else if (type == PIVOT_TYPE.RANDOM) {
            pivot = getRandomIdx(unsorted.length);
        }
        sorted(pivot,0,unsorted.length-1,unsorted);
        return unsorted;
    }
    public void sorted(int pivot,int start,int end,E[] unsorted) {
        int pivotIdex=start+pivot;
        E privot = unsorted[pivotIdex];
        int s = start;
        int f = end;
        while(s<=f){
            while(unsorted[s].compareTo(privot)<0) {
                s++;
            }
            while(unsorted[f].compareTo(privot)>0) {
                f--;
            }
            if(s<=f){
                swap(s,f,unsorted);
                s++;
                f--;
            }
        }
        if(start<f){
            pivot = getRandomIdx((f-start)+1);
            sorted(pivot,start,f,unsorted);
        }
        if(s<end){
            pivot = getRandomIdx((end-s)+1);
            sorted(pivot,s,end,unsorted);
        }
    }
    public int getRandomIdx(int length){
        if (type==PIVOT_TYPE.FIRST) {
            return 0;
        } else if(type==PIVOT_TYPE.RANDOM) {
            return RANDOM.nextInt(length);
        }else {
            return length/2;
        }
    }
    private void swap(int idx1,int idx2,E[] arr) {
        E temp = arr[idx1];
        arr[idx1]=arr[idx2];
        arr[idx2]=temp;
    }
    public static void main(String[] args) {
        QuickSort<Integer> qs = new QuickSort<Integer>();
        Integer[] arr = {1, 4, 7, 5, 9, 10, 12, 3, 20};
        Integer[] sortedarr=qs.sorted(arr,PIVOT_TYPE.MIDDLE);
        for (int i = 0; i < sortedarr.length; i++) {
            Integer integer = sortedarr[i];
            System.out.println(integer);
        }
    }
}
