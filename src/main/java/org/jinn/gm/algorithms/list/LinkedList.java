package org.jinn.gm.algorithms.list;

/**
 * Created by guming on 14-3-17.
 */
public class LinkedList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size=0;
    public LinkedList() {
    }

    public void linkFirst(E elem){
        final Node<E> first=firstNode;
        final Node<E> newNode=new Node<E>(null,first,elem);
        firstNode=newNode;
        if(first==null){
            lastNode=newNode;
        }else{
            first.preNode=newNode;
        }
        size++;
    }
    public void linkLast(E elem){
        final Node<E> last=lastNode;
        final Node<E> newNode=new Node<E>(last,null,elem);
        lastNode=newNode;
        if(last==null){
            firstNode=newNode;
        }else{
            last.nextNode=newNode;
        }
        size++;
    }
    void linkBefore(E elem,Node<E> before){
        if(before==null){
            linkFirst(elem);
            return;
        }
        final Node<E> pre=before.preNode;
        final Node<E> newNode=new Node<E>(pre,before,elem);
        before.preNode=newNode;
        if(pre==null) {
            firstNode = newNode;
        }else{
            pre.nextNode = newNode;
        }
        size++;
    }
    public E unlinkFirst(Node<E> f){
        if(firstNode==null)
            return null;
        else{
            final Node<E> next = f.nextNode;
            final E elem=f.elem;
            f.elem=null;
            f.nextNode=null;
            firstNode=next;
            if(next==null){
                lastNode=null;
            }else{
                next.preNode=null;
            }
            size--;
            return elem;
        }
    }
    public E unlinkLast(Node<E> l){
        if(lastNode==null)
            return null;
        else{
            final Node<E> pre = l.preNode;
            final E elem=l.elem;
            l.elem=null;
            l.preNode=null;
            lastNode=pre;
            if(pre==null){
                firstNode=null;
            }else{
                pre.nextNode=null;
            }
            size--;
            return elem;
        }
    }
    public E removeFirst() {
       return unlinkFirst(firstNode);
    }
    public E removeLast() {
        return unlinkLast(lastNode);
    }
    private static class Node<E>{
        Node<E> preNode;
        Node<E> nextNode;
        E elem;
        Node(Node<E> preNode, Node<E> nextNode, E elem) {
            this.preNode = preNode;
            this.nextNode = nextNode;
            this.elem = elem;
        }
    }

    public void push(E elem) {
        linkFirst(elem);
    }
    public E poll() {
        return removeLast();
    }
}
