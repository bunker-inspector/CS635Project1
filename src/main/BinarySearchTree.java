package main;

import java.util.*;

/**
 * Created by ted on 1/22/16.
 */
public class BinarySearchTree<T> extends AbstractCollection implements Iterable {

    private RootNode<T> root;
    private int size;
    private Orderer orderer;
    private static LeafNode nullNode = new LeafNode<>();

    /**
     * Node Classes
     * @param <T>
     */

    static class Node<T> {

        protected T value;
        protected Node<T> leftChild;
        protected Node<T> rightChild;

        Node() {
            value      = null;
            leftChild  = nullNode;
            rightChild = nullNode;
        }

        Node(T t) {
            value      = t;
            leftChild  = nullNode;
            rightChild = nullNode;
        }

        Node(T t, Node<T> l, Node<T> r) {
            value      = t;
            leftChild  = l;
            rightChild = r;
        }

        protected void printInOrder() {
            leftChild.printInOrder();
            System.out.print(value + " ");
            rightChild.printInOrder();
        }

        Node<T> addNode(T newValue, Orderer<T> o) {
            return o.addNode(newValue, this);
        }

        @Override
        public String toString() {
            return ", " + value.toString();
        }
    }

    static class RootNode<T> extends Node {
        RootNode(T t) {
            super(t);
        }
    }

    static class LeafNode<T> extends Node {
        private LeafNode() {
            value      = null;
            leftChild  = null;
            rightChild = null;
        }

        @Override
        Node<T> addNode(Object newValue, Orderer o) {
            return new Node(newValue);
        }

        @Override
        public String toString() {
            return "";
        }

        public void printInOrder() {
            return;
        }
    }

    /**
     * BinarySearchTree Classes and Collection function implementation
     */

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

    public void addNode(T newValue) {
        if (isEmpty()) {
            root = new RootNode<T>(newValue);
        }
        else {
            root.addNode(newValue, orderer);
        }
        size++;
    }

    @Override
    public boolean add(Object o) {
        addNode((T)o);
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
        BinarySearchTreeIterator iterator = (BinarySearchTreeIterator<String>) iterator();

        String result = "[";

        while(iterator.hasNext()) {
            Node tmp = iterator.next();
            result += tmp.toString();
        }

        result = result.replaceFirst(",", "");

        return  result + "]";
    }

    /**
     * Iterator and Decorator Classes
     */

    @Override
    public BinarySearchTreeIterator<T> iterator() {
        return new BinarySearchTreeIterator<T>();
    }

    class BinarySearchTreeIterator<T> implements Iterator{
        Stack<Node<T>> encounteredNodes;

        private BinarySearchTreeIterator() {
            encounteredNodes = new Stack<>();

            Node<T> firstFinder = root;

            while (firstFinder != null) {
                encounteredNodes.push(firstFinder);
                firstFinder = firstFinder.leftChild;
            }
        }

        public Node<T> currentValue() {
            return encounteredNodes.peek();
        }

        public boolean hasNext() {
            return !encounteredNodes.isEmpty();
        }

        public Node<T> next() {
            Node<T> currentNode = encounteredNodes.pop();
            Node<T> result = currentNode;
            if (currentNode.rightChild != null) {
                currentNode = currentNode.rightChild;
                while (currentNode != null) {
                    encounteredNodes.push(currentNode);
                    currentNode = currentNode.leftChild;
                }
            }
            return result;
        }

        @Override
        public void remove() {

        }
    }

    abstract class IteratorDecorator extends BinarySearchTreeIterator {
        IteratorDecorator() {}

        IteratorDecorator(BinarySearchTreeIterator bsti) {}
    }

    class CapitalizeDecorator extends IteratorDecorator {
        private BinarySearchTreeIterator<String> iterator;

        CapitalizeDecorator(){};

        CapitalizeDecorator(BinarySearchTreeIterator<String> i) {
            iterator = i;
        }

        @Override
        public Node next() {
            Node result = (Node)iterator.next();
            result.value = ((String)result.value).toUpperCase();
            return result;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Node currentValue() {
            return iterator.currentValue();
        }
    }

    class FindVowelDecorator extends IteratorDecorator {
        BinarySearchTreeIterator<String> iterator;

        FindVowelDecorator(){}

        FindVowelDecorator(BinarySearchTreeIterator<String> bsti){
            iterator = bsti;
        }

        @Override
        public Node next() {
            Node result;

            while(hasNext()) {
                result = iterator.next();
                if ((result.toString()).matches("[AEIOUaeiou]+.*"))
                    return result;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Node currentValue() {
            return iterator.currentValue();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];

        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator<>();

        Node<T> n = null;
        int i = 0;
        while(iterator.hasNext()) {
            n = iterator.next();
            if(n.value != null) {
                result[i] = n.value;
                i++;
            }
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

        Node<T> n = null;
        int i = 0;
        while(iterator.hasNext()) {
            n = iterator.next();
            if(n.value != null) {
                a[i] = n.value;
                i++;
            }
        }

        return (T[])a;
    }

    public void printInOrder() { root.printInOrder(); }

    public static void main(String[] args) {
        BinarySearchTree<String> b = new BinarySearchTree();
        b.add("C");
        b.add("B");
        b.add("A");
        b.add("e");
        b.add("d");
        b.add("f");
        b.add("a");
        b.add("E");
        b.add("f");

        b.printInOrder();
    }
}
