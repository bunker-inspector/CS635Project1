package main;

/**
 * Created by ted on 2/16/16.
 */
class NormalOrderer extends Orderer {
    public BinarySearchTree.Node addNode(Object newValue, BinarySearchTree.LeafNode currentNode) {
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) < 0) {
            return new BinarySearchTree.Node(currentNode.value, new BinarySearchTree.LeafNode(newValue), null);
        }
        else {
            return new BinarySearchTree.Node(currentNode.value, null, new BinarySearchTree.LeafNode(newValue));
        }
    }

    @Override
    public BinarySearchTree.Node addNode(Object newValue, BinarySearchTree.Node currentNode) {
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) < 0) {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = new BinarySearchTree.LeafNode(newValue);
            }
            else {
                currentNode.leftChild = addNode(newValue, currentNode.leftChild);
            }
        }
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) >= 0) {
            if (currentNode.rightChild == null) {
                currentNode.rightChild = new BinarySearchTree.LeafNode(newValue);
            }
            else {
                currentNode.rightChild = addNode(newValue, currentNode.rightChild);
            }
        }
        return currentNode;
    }


}