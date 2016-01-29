package main;

/**
 * Created by ted on 1/22/16.
 */
public class BST {

    private Leaf root = null;

    class Leaf {
        private String value;
        private Leaf leftChild;
        private Leaf rightChild;

        Leaf () {
            value      = null;
            leftChild  = null;
            rightChild = null;
        }

        Leaf (String s, Leaf l, Leaf r) {
            value = s;
            leftChild = l;
            rightChild = r;
        }

        public String getValue () {
            return value;
        }

        public Leaf getLeftChild () {
            return leftChild;
        }

        public Leaf getRightChild() {
            return rightChild;
        }
    }

    public Leaf getRoot() {
        return root;
    }

    public void add(String newString) {
            root = add(newString, root);
    }

    private Leaf add(String newValue, Leaf compareLeaf) {
        if (compareLeaf == null)
            return new Leaf(newValue, null, null);
        if (newValue.compareTo(compareLeaf.getValue()) < 0)
            compareLeaf.leftChild = add(newValue ,compareLeaf.getLeftChild());
        if (newValue.compareTo(compareLeaf.getValue()) >= 0)
            compareLeaf.rightChild = add(newValue, compareLeaf.getRightChild());
        return compareLeaf;
    }

    public void printInOrder() {
        if (root != null)
            printInOrder(root);
        else
            System.out.println("Root is null: Nothing to do.");
    }

    private void printInOrder(Leaf l) {
        if (l.getLeftChild() != null)
            printInOrder(l.getLeftChild());

        System.out.println(l.getValue());

        if (l.getRightChild() != null)
            printInOrder(l.getRightChild());
    }

    public void printVowelNodesReverseAlphabetic () {
        if (root != null)
            printVowelNodesReverseAlphabetic(root);
        else
            System.out.println("Root is null: Nothing to do.");
    }

    private void printVowelNodesReverseAlphabetic (Leaf l) {
        if (l.getRightChild() != null)
            printVowelNodesReverseAlphabetic(l.getRightChild());

        String leafValue = l.getValue();

        if (leafValue.matches(".*[AEIOUaeiou].*"))
            System.out.println(l.getValue());

        if (l.getLeftChild() != null)
            printVowelNodesReverseAlphabetic(l.getLeftChild());
    }

    public static void main(String[] args) {
        BST b = new BST();
        b.add("A");
        b.add("B");
        b.add("I");
        b.add("C");

        b.printInOrder();
        b.printVowelNodesReverseAlphabetic();
    }
}
