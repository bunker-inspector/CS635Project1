package main;

import java.util.*;

/**
 * Created by ted on 1/22/16.
 */
public class BinarySearchTree<T> extends AbstractCollection implements Iterable {

    private Node<T> root;
    private int size;
    private Orderer orderer;

    static class Node<T> {

        protected Object value       = null;
        protected Node<T> leftChild  = null;
        protected Node<T> rightChild = null;

        Node() {
            value      = null;
            leftChild  = null;
            rightChild = null;
        }

        Node(Object o, Node<T> l, Node<T> r) {
            value      = o;
            leftChild  = l;
            rightChild = r;
        }

        private void printInOrder() {
            if (leftChild != null) {
                leftChild.printInOrder();
            }

            System.out.println(value);

            if (rightChild != null) {
                rightChild.printInOrder();
            }
        }

        private void printVowelNodesReverseAlphabetic () {
            if (rightChild != null) {
                rightChild.printVowelNodesReverseAlphabetic();
            }

            String leafValue = (String) value;

            if (leafValue.matches("[AEIOUaeiou]+.*")) {
                System.out.println(value);
            }

            if (leftChild != null) {
                leftChild.printVowelNodesReverseAlphabetic();
            }
        }
    }

    static class RootNode<T> extends Node {
        RootNode (Object o) {
            value = o;
            leftChild = null;
            rightChild = null;
        }
    }

    static class LeafNode<T> extends Node {
        LeafNode (Object o) {
            value = o;
            leftChild = null;
            rightChild = null;
        }
    }

    BinarySearchTree () {
        root = null;
        size = 0;
        orderer = new NormalOrderer();
    }

    BinarySearchTree (Orderer o) {
        root = null;
        size = 0;
        orderer = o;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addNode(Object newValue) {
        if (isEmpty()) {
            root = new RootNode<T>(newValue);
        }
        else {
            orderer.addNode(newValue, root);
        }
        size++;
    }

    @Override
    public BinarySearchTreeIterator<T> iterator() {
        return new BinarySearchTreeIterator<T>();
    }

    private class BinarySearchTreeIterator<T> implements Iterator{
        Stack<Node<T>> encounterOrder;

        private BinarySearchTreeIterator() {
            encounterOrder = new Stack<>();

            while (root != null) {
                encounterOrder.push((Node<T>) root);
                root = root.leftChild;
            }
        }

        public Node<T> currentValue() {
            return encounterOrder.peek();
        }

        public boolean hasNext() {
            return !encounterOrder.isEmpty();
        }

        public Node<T> next() {
            Node<T> currentNode = encounterOrder.pop();
            Node<T> result = currentNode;
            if (currentNode.rightChild != null) {
                currentNode = currentNode.rightChild;
                while (currentNode != null) {
                    encounterOrder.push(currentNode);
                    currentNode = currentNode.leftChild;
                }
            }
            return result;
        }

        @Override
        public void remove() {

        }
    }

    @Override
    public boolean add(Object o) {
        addNode(o);
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        BinarySearchTreeIterator iterator = iterator();

        String result = "[" + iterator.next().value.toString();

        while(iterator.hasNext()) {
            result += ", " + iterator.next().value.toString();
        }

        return  result + "]";
    }

    public void printTypes() {
        BinarySearchTreeIterator iterator = iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];

        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator<>();

        result[0] = iterator.currentValue();

        for (int i = 0; iterator.hasNext(); i++) {
            result[i] = iterator.next();
        }

        return  result;
    }

    @Override
    public T[] toArray(Object[] a) {
        if (a.length < size()) {
            a = toArray();
            return (T[])a;
        }

        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator<>();

        a[0] = iterator.currentValue();

        for (int i = 0; iterator.hasNext(); i++) {
            a[i] = iterator.next();
        }

        return (T[])a;
    }

    public void printInOrder() {
        BinarySearchTreeIterator<T> bsti = iterator();

       if(isEmpty()) {
           System.out.println("Tree is empty. Nothing to do...");
       }

        System.out.println(bsti.currentValue());

        while(bsti.hasNext()) {
            System.out.println(bsti.next().value);
        }
    }

    public void printVowelNodesReverseAlphabetic () {
        if (!isEmpty())
            root.printVowelNodesReverseAlphabetic();
        else
            System.out.println("Root is null: Nothing to do.");
    }

    public static void main(String[] args) {
        BinarySearchTree<String> b = new BinarySearchTree();
        b.add("C");
        b.add("B");
        b.add("A");
        b.add("e");
        b.add("d");
        b.add("f");

        System.out.println(b.toString());
    }
}
