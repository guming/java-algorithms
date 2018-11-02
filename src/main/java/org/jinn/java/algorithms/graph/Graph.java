package org.jinn.java.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph <E> {

    public static class Node <E> {
        private E value = null;
        private int weight = 0;
        private List<Edge<E>> edges = new ArrayList<Edge<E>>();

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<Edge<E>> getEdges() {
            return edges;
        }

        public void setEdges(List<Edge<E>> edges) {
            this.edges = edges;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }

    public static class Edge <E> {

    }
}
