package main;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by ted on 1/22/16.
 */
public class BinarySearchTree<T> implements Iterable, Collection {

    private Leaf root = null;

    class Leaf {

        private Object value = null;
        private Leaf leftChild = null;
        private Leaf rightChild = null;

        Leaf (Object o, Leaf l, Leaf r) {
            value = o;
            leftChild = l;
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

        private Leaf addLeaf(Object newValue) {
            if (((Comparable<Object>)newValue).compareTo(value) < 0)
                if (leftChild == null)
                    leftChild = new Leaf(newValue, null, null);
                else
                    leftChild = leftChild.addLeaf(newValue);
            if (((Comparable<Object>)newValue).compareTo(value) >= 0)
                if (rightChild == null)
                    rightChild = new Leaf(newValue, null, null);
                else
                    rightChild = rightChild.addLeaf(newValue);
            return this;
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

    public boolean isEmpty() {
        return (root == null);
    }

    public void addLeaf(Object newValue) {
        if (isEmpty()) {
            root = new Leaf(newValue, null, null);
        }
        else {
            root.addLeaf(newValue);
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean add(Object o) {
        addLeaf(o);
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
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public T[] toArray(Object[] a) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    public void printInOrder() {
        if (root != null)
            root.printInOrder();
        else
            System.out.println("Root is null: Nothing to do.");
    }

    public void printVowelNodesReverseAlphabetic () {
        if (!isEmpty())
            root.printVowelNodesReverseAlphabetic();
        else
            System.out.println("Root is null: Nothing to do.");
    }

    public static void main(String[] args) {
        BinarySearchTree b = new BinarySearchTree();
        b.addLeaf("A");
        b.addLeaf("B");
        b.addLeaf("C");
        b.addLeaf("d");
        b.addLeaf("e");
        b.addLeaf("f");

        b.printInOrder();
    }
}
