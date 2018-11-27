package org.jinn.gm.algorithms.queue;

/**
 * Created by guming on 14-3-24.
 */
public class LinkedQueue2<E> implements Queue<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    public LinkedQueue2() {
        head=tail=new Node<E>(null);
    }

    private static class Node<E>{
        E value;
        Node<E> next=null;
        private Node(E value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
    @Override
    public boolean offer(E value) {
        Node<E> newNode = new Node<E>(value);
        tail = tail.next =newNode;
        size++;
        return true;
    }

    @Override
    public E peek() {
        Node<E> trail=head;
        if (trail == null) {
            return null;
        }
        return trail.value;
    }

    @Override
    public E poll() {
        if (head == null) {
            return null;
        }
        Node<E> trail=head;
        Node<E> first=head.next;
        E result = first.value;
        head=first;
        first.value=null;
        size--;
        return result;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E value) {
        if (value == null) return false;
        for (Node<E> p = head.next; p != null; p = p.next)
            if (value.equals(p.value))
                return true;
        return false;
    }

    @Override
    public boolean remove(E value) {
        Node<E> prev=head;
        Node<E> next=head.next;
        while(next!=null&&!value.equals(next.value)) {
            prev=next;
            next=next.next;
        }
        if(next.value.equals(value)) {
            prev.next = next.next;
            next.value = null;
            if (tail == next) {
                tail = prev;
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        head = tail = new Node<E>(null);
        size = 0;
    }
}
