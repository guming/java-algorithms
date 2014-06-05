package org.jinn.java.algorithms.sort;


/**
 * Created by guming on 14-3-20.
 */
public class BubbleSort<E extends Comparable<E>> {

    public E[] sorted(E[] unsorted) {
        int length = unsorted.length;
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int i = 0; i < length-1; i++) {
               if (unsorted[i].compareTo(unsorted[i+1])>=0){
                swap(i,i+1,unsorted);
                swap=true;
               }
            }
            length--;
        }
        return unsorted;
    }
    private void swap(int idx1,int idx2,E[] arr) {
        E temp = arr[idx1];
        arr[idx1]=arr[idx2];
        arr[idx2]=temp;
    }
    public static void main(String[] args) {
        BubbleSort<Integer> bs = new BubbleSort<Integer>();
        Integer[] arr = {1,4,7,5,9,10,12,3};
        Integer[] sortedarr=bs.sorted(arr);
        for (int i = 0; i < sortedarr.length; i++) {
            Integer integer = sortedarr[i];
            System.out.println(integer);
        }
    }
}
