package org.jinn.java.algorithms.stack;

/**
 * Created by guming on 14-3-24.
 */
public interface IStack<E> {
    public boolean remove(E value);
    public E peek();
    public boolean push(E value);
    public E pop();
    public void clear();
    public boolean contains(E value);
}
