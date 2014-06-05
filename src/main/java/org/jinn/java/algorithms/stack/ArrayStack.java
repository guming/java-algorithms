package org.jinn.java.algorithms.stack;

import java.util.Arrays;

/**
 * Created by guming on 14-3-24.
 */
public class ArrayStack<E> implements IStack<E> {
    private static final int DEFAULT_SIZE = 0;
    private int size=0;
    private E[] values=(E[])new Object[DEFAULT_SIZE];

    public ArrayStack() {
    }

    @Override
    public boolean remove(E value) {
        for (int i = 0; i <size ; i++) {
            if (values[i].equals(value)){
                if(i!=--size){
                    values[i]=null;
                    System.arraycopy(values,i+1,values,i,size-i);
                }
                int shrinkSize=values.length;
                if(size>DEFAULT_SIZE&&size<shrinkSize+(shrinkSize<<1)){
                    System.arraycopy(values,0,values,0,size);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public E peek() {
        if (size<=0) {
            return null;
        }
        return values[size-1];
    }

    @Override
    public boolean push(E value) {
        int growSize = this.size;
        if (growSize > values.length) {
            values = Arrays.copyOf(values, (growSize + (growSize >> 1)));
        }
        values[size++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (size<=0) {
            return null;
        }
        E result = values[--size];
        values[size]=null;
        int shrinkSize=values.length;
        if(size>DEFAULT_SIZE&&size<shrinkSize+(shrinkSize<<1)){
            System.arraycopy(values,0,values,0,size);
        }
        return result;
    }

    @Override
    public void clear() {
        values = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i <size ; i++) {
            if (value.equals(values[i])){
                return true;
            }
        }
        return false;
    }
}
