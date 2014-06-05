package org.jinn.java.algorithms.stack;

/**
 * Created by guming on 14-3-24.
 */
public class LinkedStack<E> implements IStack<E> {
    private Node<E> top=null;
    private int size=0;

    public LinkedStack() {
    }
    public LinkedStack(Node<E> top) {
        this.top = top;
    }

    private static class Node<E>{
        E value;
        Node<E> above=null;
        Node<E> below=null;
        private Node(E value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", above=" + above +
                    ", below=" + below +
                    '}';
        }
    }

    @Override
    public boolean remove(E value) {
        Node<E> nowTop = top;
        while (nowTop != null) {
            if (nowTop.value.equals(value)){
                Node<E> below=nowTop.below;
                Node<E> above=nowTop.above;
                if (above!=null) {
                    above.below = below;
                    if (below!= null) {
                        below.above = above;
                    }
                    top = above;
                }else{
                    below.above=null;
                    top=below;
                }
                size--;
                return true;
            }else{
                nowTop=nowTop.below;
            }
        }
        return false;
    }

    @Override
    public E peek() {
        if(top==null)
            return null;
        return top.value;
    }

    @Override
    public boolean push(E value) {
        Node<E> newNode=new Node<E>(value);
        if (top == null) {
            top = newNode;
        }else {
            Node<E> topNow = top;
            topNow.above = newNode;
            newNode.below = topNow;
            top = newNode;
        }
        size++;
        return true;
    }

    @Override
    public E pop() {
        final Node<E> nowTop=top;
        if(top!=null){
            return null;
        }else{
            Node<E> below=top.below;
            below.above=null;
            top=below;
            size--;
            if (below == null) {
                return null;
            }else {
                return below.value;
            }
        }
    }

    @Override
    public void clear() {
        top=null;
        size=0;
    }

    @Override
    public boolean contains(E value) {
        Node<E> nowTop = top;
        while (nowTop != null) {
            if (nowTop.value.equals(value)){
                return true;
            }else{
                nowTop=nowTop.below;
            }
        }
        return false;
    }
}
