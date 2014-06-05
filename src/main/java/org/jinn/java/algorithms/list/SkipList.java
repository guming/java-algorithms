package org.jinn.java.algorithms.list;

import java.util.Random;

/**
 * Created by guming on 14-3-24.
 */
public class SkipList<E extends  Comparable<E>>  {

    private transient volatile HeadIndex<E> head;
    private int MAX_LEVEL=31;//randomLevel max value is 31
    private static final Random seedGenerator = new Random();
    private transient int randomSeed;

    public SkipList() {
        this.randomSeed = seedGenerator.nextInt() | 0x0100; // ensure nonzero;
        this.head = new HeadIndex<E>(new Node<E>(null,null),null,null,1);
    }
    static class Index<E extends  Comparable<E>> {
        Node<E> node;
        Index<E> down;
        Index<E> right;

        Index(Node<E> node, Index<E> down, Index<E> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
    }
    static final class HeadIndex<E extends  Comparable<E>> extends Index<E> {
        final int level;
        HeadIndex(Node<E> node, Index<E> down, Index<E> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }
    /**
     * Returns a random level for inserting a new node.
     * Hardwired to k=1, p=0.5, max 31 (see above and
     * Pugh's "Skip List Cookbook", sec 3.4).
     *
     * This uses the simplest of the generators described in George
     * Marsaglia's "Xorshift RNGs" paper.  This is not a high-quality
     * generator but is acceptable here.
     */
    private int randomLevel() {
        int x = randomSeed;
        x ^= x << 13;
        x ^= x >>> 17;
        randomSeed = x ^= x << 5;
            if ((x & 0x80000001) != 0) // test highest and lowest bits
            return 0;
        int level = 1;
        while (((x >>>= 1) & 1) != 0) ++level;
        return level;
    }

    public void add(E value) {
        HeadIndex<E> h=head;
        int level = randomLevel();
        if(level==0){
            level++;
        }
        Node<E> newNode = new Node<E>(value);
        int max=head.level;
        if(level<=max) {
            Index idx = null;
            for (int i = 1; i <= level; i++) {
                idx=new Index<E>(newNode,idx,null);
            }
            addIndex(idx, h, level);
        }else{
            level=max+1;
            Index<E>[] idxs = (Index<E>[])new Index[level+1];
            Index<E> idx = null;
            for (int i = 1; i <= level; ++i)
                idxs[i] = idx = new Index<E>(newNode, idx, null);
            HeadIndex<E> oldh;
            oldh = head;
            int oldLevel = oldh.level;
            HeadIndex<E> newh = oldh;
            Node<E> oldbase = oldh.node;
            newh = new HeadIndex<E>(oldbase, newh, idxs[level], level);
            head = newh;
            addIndex(idxs[oldLevel], newh, oldLevel);
        }
    }
    private void addIndex(Index<E> idx,HeadIndex<E> h,int level){
          int hlevel=h.level;
        Index<E> htemp=h;
        Index<E> down=h.down;
        Index<E> right = null;
          if(hlevel==level){
              right=htemp.right;
              if (right != null) {
                 if (right.node.value.compareTo(idx.node.value)>0){
                     htemp.right=idx;
                     idx.right=right;
                 }else {
                     Index<E> pre=right;
                     while (right!=null&&right.node.value.compareTo(idx.node.value) < 0) {
                         pre=right;
                         right=right.right;
                     }
                     pre.right=idx;
                     idx.right=right;
                 }
              }else{
                  htemp.right=idx;
              }
          }else {
              while (hlevel >=  level&&down!=null) {
                  right = down.right;
                  if (right != null) {
                      if (right.node.value.compareTo(idx.node.value)>0){
                          down.right=idx;
                          idx.right=right;
                      }else {
                          Index<E> pre=right;
                          while (right!=null&&right.node.value.compareTo(idx.node.value) < 0) {
                              pre=right;
                              right=right.right;
                          }
                          pre.right=idx;
                          idx.right=right;
                      }
                  }else{
                      down.right=idx;
                  }
                  down=down.down;
                  hlevel--;
              }
          }
    }

//    public E findValue(E value){
//
//    }

    protected static class Node<E extends Comparable<E>>{
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value,Node<E> next) {
            this.value = value;
        }

        public Node(Node<E> next, Node<E> down, int level,E value) {
            this.next = next;
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
