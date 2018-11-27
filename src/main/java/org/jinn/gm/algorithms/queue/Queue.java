package org.jinn.gm.algorithms.queue;

/**
 * Created by guming on 14-3-24.
 */
public interface Queue<E> {

    public boolean offer(E value);

    public E peek();

    public E poll();

    public int size();

    public boolean contains(E value);

    public boolean remove(E value);

    public void clear();

}
