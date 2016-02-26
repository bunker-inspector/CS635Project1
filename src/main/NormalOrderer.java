package main;

/**
 * Created by ted on 2/16/16.
 */
class NormalOrderer<T> extends Orderer {
    @Override
    public BinarySearchTree.Node addNode(Object newValue, BinarySearchTree.Node currentNode) {
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) < 0) {
            currentNode.leftChild = currentNode.leftChild.addNode(newValue, this);
        }
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) >= 0) {
            currentNode.rightChild = currentNode.rightChild.addNode(newValue, this);
        }
        return currentNode;
    }


}