package org.jinn.gm.algorithms.Tree;

/**
 * Created by guming on 14-3-19.
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    protected static class AVLNode<E extends Comparable<E>> extends Node<E>{
        private int height = 1;
        public void updateHeight() {
            int leftheight = 0;
            int rightheight = 0;
            if (leftNode!=null) {
                AVLNode<E> lefttemp = (AVLNode<E>)leftNode;
                leftheight = lefttemp.height;
            }
            if (rightNode!=null) {
                AVLNode<E> righttemp = (AVLNode<E>)rightNode;
                rightheight = righttemp.height;
            }
            if (leftheight>rightheight){
                height=leftheight+1;
            }else{
                height=rightheight+1;
            }
        }
        public int getBalanceFactor(){
            int leftheight = 0;
            int rightheight = 0;
            if (leftNode!=null) {
                AVLNode<E> lefttemp = (AVLNode<E>)leftNode;
                leftheight = lefttemp.height;
            }
            if (rightNode!=null) {
                AVLNode<E> righttemp = (AVLNode<E>)rightNode;
                rightheight = righttemp.height;
            }
            return  rightheight-leftheight;
        }
        public AVLNode(Node<E> parentNode, E elem) {
            super(parentNode, elem);
        }

        @Override
        public String toString() {
            return "AVLNode{" +
                    "height=" + height +
                    "} " + super.toString();
        }
    }
    private enum Balance {
        LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
    };

    @Override
    public Node<E> addNode(E elem){
        Node<E> nodeToReturn =(Node<E>)super.addNode(elem);
        AVLNode<E> nodeAdded = (AVLNode<E>) nodeToReturn;
        while(nodeAdded!=null){
            nodeAdded.updateHeight();
            nodeAdded =(AVLNode<E>) nodeAdded.parentNode;
        }
        return nodeToReturn;
    }
    public void balanceAfterInsert(AVLNode<E> node){
        
    }
}
