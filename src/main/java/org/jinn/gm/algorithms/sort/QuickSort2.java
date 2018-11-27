package org.jinn.gm.algorithms.sort;

import java.util.Random;

/**
 * Created by gumingcn on 2014/12/15.
 * if less than 11 then used the insertionSort
 */
public class QuickSort2 <E extends Comparable<E>> {

    private static final Random RANDOM = new Random();

    public static final int CUTOFF = 11;

    private PIVOT_TYPE type=PIVOT_TYPE.RANDOM;

    public static enum PIVOT_TYPE {
        FIRST, MIDDLE, RANDOM
    };

    public QuickSort2(PIVOT_TYPE type) {
        this.type = type;
    }

    public E[] sorted(E[] unsorted) {
        int pivot = getRandomIdx(unsorted.length);
        sorted(unsorted,pivot,0,unsorted.length-1);
        return unsorted;
    }

    public void sorted(E[] unsorted,int pivot,int start,int end){
        if(CUTOFF+start<end) {
            E value = unsorted[pivot + start];
            int s = start;
            int f = end;
            while (s <= f) {
                while (unsorted[s].compareTo(value) < 0) {
                    s++;
                }
                while (unsorted[f].compareTo(value) > 0) {
                    f--;
                }
                if (s <= f) {
                    swap(s, f, unsorted);
                    s++;
                    f--;
                }
            }
            if (start < f) {
                pivot = getRandomIdx((f - start) + 1);
                sorted(unsorted, pivot, start, f);
            }
            if (s < end) {
                pivot = getRandomIdx((end - s) + 1);
                sorted(unsorted, pivot, s, end);
            }
        }else{
            insertionSort(unsorted);
        }
    }

    private int getRandomIdx(int length){
        if(type==PIVOT_TYPE.FIRST){
            return 0;
        }else if(type==PIVOT_TYPE.MIDDLE){
            return length/2;
        }else{
            return RANDOM.nextInt(length);
        }
    }
    private void swap(int idx1,int idx2,E[] arr) {
        E temp = arr[idx1];
        arr[idx1]=arr[idx2];
        arr[idx2]=temp;
    }

    public E[] insertionSort(E[] unsorted) {
        System.out.println("insertionSort");
        int j;
        for (int i = 1; i <unsorted.length; i++) {
            E temp = unsorted[i];
            for ( j = i; i>0&&temp.compareTo(unsorted[j-1])<0 ; j--) {
                unsorted[j]=unsorted[j-1];
            }
            unsorted[j]=temp;
        }
        return unsorted;
    }

    public static void main(String[] args) {

        QuickSort2<Integer> quickSort2=new QuickSort2<Integer>(PIVOT_TYPE.MIDDLE);
        Integer[] arr = {1, 4, 7, 5, 9, 10, 12, 3, 20};
        Integer[] sortedarr=quickSort2.sorted(arr);
        for (int i = 0; i < sortedarr.length; i++) {
            Integer integer = sortedarr[i];
            System.out.println(integer);
        }

    }
}
