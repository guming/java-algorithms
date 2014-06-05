package org.jinn.java.algorithms.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by guming on 14-3-17.
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root = null;

    private int size = 0;

    protected enum Position {
        LEFT, RIGHT
    };

    protected static class Node<E extends Comparable<E>> {
        Node<E> leftNode=null;
        Node<E> rightNode=null;
        Node<E> parentNode=null;
        E elem;

        Node(Node<E> parentNode, E elem) {
            this.parentNode = parentNode;
            this.elem = elem;
        }
        public boolean isLeaf() {
            return rightNode == null && leftNode == null;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    ", parentNode=" + parentNode +
                    ", elem=" + elem +
                    '}';
        }
    }
    public Node<E> addNode(E elem) {
        Node<E> newNode = new Node<E>(null, elem);
        if (root == null) {
            root = newNode;
            size++;
            return newNode;
        }

        Node<E> node = root;
        while(node!=null){
            if ( node.elem.compareTo(elem)>0) {//left child tree
                if(node.leftNode==null) {
                    node.leftNode = newNode;
                    size++;
                    newNode.parentNode=node;
                    return newNode;
                }else {
                    node = node.leftNode;
                }
            } else {//right child tree
                if(node.rightNode==null) {
                    node.rightNode = newNode;
                    size++;
                    newNode.parentNode=node;
                    return newNode;
                }else{
                    node=node.rightNode;
                }
            }
        }
        return newNode;
    }
    public Node<E> getNode(E elem) {
        Node<E> node = root;
        while (node!=null) {
            if(node.elem.compareTo(elem)==0){
                return node;
            }else if (node.elem.compareTo(elem)>0) {
                node = node.leftNode;
            }else {
                node = node.rightNode;
            }
        }
        return null;
    }
    public Node<E> remove(E elem) {
        Node<E> nodeTemp = this.getNode(elem);
        return removeNode(nodeTemp);
    }
    public Node<E> removeNode(Node<E> rNode){
        replaceNode(rNode);
        size--;
        return null;
    }
    public void replaceNode(Node<E> rNode) {
        Node<E> t = rNode.parentNode;
        final Node<E> right=rNode.rightNode;
        Node<E> left=rNode.rightNode;
        if (t==null) {
            root=null;
            return;
        }
        if (t.elem.compareTo(rNode.elem)>0){//left
            if(rNode.isLeaf()) {
                t.leftNode = null;
            }else {
                t.leftNode = right;
            }
        }else{//right
            if(rNode.isLeaf()) {
                t.rightNode = null;
            }else{
                t.rightNode=right;
            }
        }
        Node<E> upNode=rNode.rightNode;
        while (left!=null&&upNode!=null){
            if(upNode.leftNode==null){
                upNode.leftNode=left;
                break;
            }else{
                upNode= upNode.leftNode;
            }
        }
    }

    protected void rotateRight(Node<E> rNode) {
        Position parentPosition = null;
        Node<E> parent = rNode.parentNode;
        Node<E> left = rNode.leftNode;
        Node<E>  rightchild=left.rightNode;
        if (parent != null) {
            if (rNode.equals(parent.leftNode)) {
                parentPosition=Position.LEFT;
            }else{
                parentPosition = Position.RIGHT;
            }
        }
        if (parentPosition != null) {
            if (parentPosition==Position.LEFT){
                parent.leftNode=left;//modify parent node
            }else{
                parent.rightNode=left;//modify parent node
            }
            left.parentNode=parent;//exchange parent
        }else {
            root=left;
            left.parentNode = null;
        }

        left.rightNode = null;

        if (rightchild == null) {
            rNode.leftNode = null;//none left node
        } else {
            rightchild.parentNode = rNode;
            rNode.leftNode = rightchild;
        }
        left.rightNode = rNode;//exchange old left
        rNode.parentNode = left;//exchange old left

    }

    protected void rotateLeft(Node<E> rNode) {
        Position parentPosition = null;
        Node<E> parent = rNode.parentNode;
        Node<E> pivot = rNode.rightNode;
        Node<E> leftchild = pivot.leftNode;
        if (parent != null) {
            if (rNode.equals(parent.leftNode)) {
                parentPosition=Position.LEFT;
            }else{
                parentPosition = Position.RIGHT;
            }
        }
        if (parentPosition != null) {
            if (parentPosition==Position.LEFT){
                parent.leftNode=pivot;//modify parent node
            }else{
                parent.rightNode=pivot;//modify parent node
            }
            pivot.parentNode=parent;//exchange parent
        }else {
            root=pivot;
            pivot.parentNode = null;
        }

        pivot.leftNode = null;

        if (leftchild == null) {
            rNode.rightNode = null;//none left node
        } else {
            leftchild.parentNode = rNode;
            rNode.rightNode = leftchild;
        }
        pivot.leftNode = rNode;//exchange old left
        rNode.parentNode = pivot;//exchange old left
    }

    public E[] getBFS() {
        Queue<Node<E>> queue = new ArrayDeque<Node<E>>();
        System.out.println(size);
        E[] values = (E[]) new Comparable[size];
        int count = 0;
        Node<E> node = root;
        while (node != null) {
            if(count>=size)
                break;
            values[count] = node.elem;
            if (node.leftNode != null)
                queue.add(node.leftNode);
            if (node.rightNode != null)
                queue.add(node.rightNode);
            if (!queue.isEmpty())
                node = queue.remove();
            else
                node = null;
            count++;
        }
        return values;
    }

    public static void main(String[] args) {
        BinarySearchTree<User> binaryTree=new BinarySearchTree<User>();
        User u15=new User("c", 15);
        User u45=new User("b", 45);
        User u60=new User("h", 60);
        binaryTree.addNode(new User("a", 82));
        binaryTree.addNode(u45);
        binaryTree.addNode(u15);
        binaryTree.addNode(u60);
        binaryTree.addNode(new User("d", 92));
        binaryTree.addNode(new User("e", 112));
        binaryTree.addNode(new User("f", 102));
        binaryTree.addNode(new User("g", 101));

//        binaryTree.addNode(92);
//        binaryTree.addNode(45);
//        binaryTree.addNode(15);
//        binaryTree.addNode(105);
        binaryTree.remove(u45);
        binaryTree.addNode(new User("i", 200));
//        User[] a=binaryTree.getBFS();
//        for (User integer : a) {
//            System.out.println(integer.toString());
//        }

    }

}
