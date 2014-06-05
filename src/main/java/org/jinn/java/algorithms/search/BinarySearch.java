package org.jinn.java.algorithms.search;

import com.jinn.algorithms.sort.QuickSort;

/**
 * Created by guming on 14-3-21.
 */
public class BinarySearch<E extends Comparable<E>> {
    private static final int MIN_LENGTH=30;//less item no need binary search
    public E find(E[] sorted,final int start,final int end,E someone){
        int s = start;
        int f = end;
        E result=null;
        if(start==end){
            if(sorted[start].compareTo(someone)==0)
                return someone;
            else
                return result;
        }
        int middle = ((f-s)+1) / 2+start;
        if(sorted[middle].compareTo(someone)==0){
            return sorted[middle];
        }
        if(sorted[middle].compareTo(someone)>0){
            if(MIN_LENGTH<(f-s+1)) {
                s = middle - 1;
                result = find(sorted, start, s, someone);
            }else{
                result =  linearSearch(someone, start, s, sorted);
            }
        }
        if(sorted[middle].compareTo(someone)<0) {
            if(MIN_LENGTH<(f-s+1)) {
                f = middle + 1;
                result = find(sorted, f, end, someone);
            }else
                result =  linearSearch(someone, f, end, sorted);
        }

        return result;
    }
    private E linearSearch(E value, int start, int end,E[] sorted) {
        for (int i = start; i <= end; i++) {
            E iValue = sorted[i];
            if (value.compareTo(iValue)==0)
                return iValue;
        }
        return null;
    }
    public static void main(String[] args) {
        QuickSort<Integer> qs = new QuickSort<Integer>();
        BinarySearch<Integer> bs =new BinarySearch<Integer>();
        Integer[] arr = {1, 4, 7, 5, 9, 10, 12, 3, 20};
        Integer[] sortedarr=qs.sorted(arr, QuickSort.PIVOT_TYPE.MIDDLE);
        System.out.println(bs.find(sortedarr,0,sortedarr.length-1,12));
    }
}
