package main;

/**
 * Created by ted on 2/16/16.
 */
class NormalOrderer extends Orderer {
    @Override
    public BinarySearchTree.Leaf addLeaf(Object newValue, BinarySearchTree.Leaf currentLeaf) {
        if (((Comparable<Object>)newValue).compareTo(currentLeaf.value) < 0) {
            if (currentLeaf.leftChild == null) {
                currentLeaf.leftChild = new BinarySearchTree.Leaf(newValue, null, null);
            }
            else {
                currentLeaf.leftChild = addLeaf(newValue, currentLeaf.leftChild);
            }
        }
        if (((Comparable<Object>)newValue).compareTo(currentLeaf.value) >= 0) {
            if (currentLeaf.rightChild == null) {
                currentLeaf.rightChild = new BinarySearchTree.Leaf(newValue, null, null);
            }
            else {
                currentLeaf.rightChild = addLeaf(newValue, currentLeaf.rightChild);
            }
        }
        return currentLeaf;
    }
}