package org.jinn.java.algorithms.queue;

/**
 * Created by guming on 14-3-24.
 */
public class LinkedQueue<E> implements Queue<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    public LinkedQueue() {
    }

    private static class Node<E>{
        E value;
        Node<E> prev=null;
        Node<E> next=null;
        private Node(E value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }
    @Override
    public boolean offer(E value) {
        Node<E> addNode = new Node<E>(value);
        if (head == null) {
            head = addNode;
            tail = addNode;
        }else{
            Node<E> nhead=head;
            addNode.next = nhead;
            addNode.prev = null;
            nhead.prev=addNode;
            head = addNode;
        }
        size++;
        return true;
    }

    @Override
    public E peek() {
        if(tail==null){
            return null;
        }
        return tail.value;
    }

    @Override
    public E poll() {
        if(head== null && tail==null)
            return null;
        Node<E> ntail = tail;
        E result = ntail.value;
        if(size<=1){
            head=null;
            tail=null;
        }else {
            Node<E> prev = ntail.prev;
            prev.next = null;
            tail = prev;
        }
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E value) {
        if (head == null)
            return false;
        Node<E> node = head;
        while (node != null) {
            if (node.value.equals(value))
                return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean remove(E value) {
        Node<E> link=head;
        while(link!=null&&!value.equals(link.value)) {
            link=link.next;
        }
        if (link == null) return false;
        Node<E> prev = link.prev;
        Node<E> next = link.next;
        if(prev!=null&&next!=null){
            prev.next=next;
            next.prev=prev;
        }else if(prev!=null&&next==null){
            prev.next=null;
            tail=prev;
        }else if(next!=null&&prev==null){
            next.prev=null;
            head=next;
        }else{
            head=null;
            tail=null;
        }
        size--;
        return true;
    }

    @Override
    public void clear() {
        head=null;
        tail=null;
        size=0;
    }
}
