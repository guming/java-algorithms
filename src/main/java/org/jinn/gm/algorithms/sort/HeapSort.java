package org.jinn.gm.algorithms.sort;

/**
 * Created by guming on 2017/5/8.
 */
public class HeapSort<E extends Comparable<E>> {

    public  int[] sort(int[] unsorted){
        createHeap(unsorted);
        sortHeap(unsorted);
        return unsorted;
    }

    public  void createHeap(int[] unsorted){
        int length = unsorted.length;
        int size = 0;
        for(int i=0;i<length;i++){
            int elem = unsorted[i];
            size=add(size, elem, unsorted);
        }
    }

    public  int add(int size,int element,int[] unsorted){
        int len=size;
        int i = len;
        unsorted[len] = element;
        int parentIndex = ((i - 1) / 2);
        int parent = unsorted[parentIndex];
        while(element>parent){
            swap(parentIndex,i,unsorted);
            i = parentIndex;
            element = unsorted[i];
            parentIndex = (i - 1) / 2;
            parent = unsorted[parentIndex];
        }
        len++;
        return len;
    }

    public  void sortHeap(int[] unsorted){
        int length = unsorted.length;
        for (int index = length - 1; index > 0; index--) {
            swap(0,index,unsorted);
            int i=0;
            while(true){
                int left = (2 * i) + 1;
                if(left>=index){
                    break;
                }
                int right = left + 1;
                if(right>=index){
                    if(unsorted[left]>unsorted[i]){
                        swap(left,i,unsorted);
                    }
                    break;
                }
                int iElem = unsorted[i];
                int leftElem = unsorted[left];
                int rightElem = unsorted[right];
                //left>i
                if(leftElem>iElem){
                    //left >right
                    if(leftElem>rightElem) {
                        swap(left, i, unsorted);
                        i = left;
                        continue;
                    }else {//left<right
                        swap(right, i, unsorted);
                        i = right;
                        continue;
                    }
                }
                //left<i && right>i
                if(rightElem>iElem){
                    swap(right,i,unsorted);
                    i = right;
                    continue;
                }
                //i>left && i>right
                break;
            }
        }

    }


    private  void swap(int parentIndex, int childIndex, int[] unsorted) {
        int parent = unsorted[parentIndex];
        unsorted[parentIndex] = unsorted[childIndex];
        unsorted[childIndex] = parent;
    }


    public static void main(String[] args) {
        int[] a = {1, 40, 6, 8, 2, 9, 5, 3, 0,7};
        HeapSort heapSort = new HeapSort();
        int[] b=heapSort.sort(a.clone());
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }
}
