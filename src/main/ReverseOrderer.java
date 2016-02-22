package main;

/**
 * Created by ted on 2/16/16.
 */
class ReverseOrderer extends Orderer {
    public BinarySearchTree.Node addNode(Object newValue, BinarySearchTree.Node currentNode) {
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) >= 0) {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = new BinarySearchTree.LeafNode(newValue);
            }
            else {
                //turns existing LeafNodes to regular nodes since they are about to have children
                currentNode.leftChild = addNode(newValue,
                        new BinarySearchTree.Node(currentNode.leftChild.value,
                                currentNode.leftChild.leftChild,
                                currentNode.leftChild.rightChild));
            }
        }
        if (((Comparable<Object>)newValue).compareTo(currentNode.value) < 0) {
            if (currentNode.rightChild == null) {
                currentNode.rightChild = new BinarySearchTree.LeafNode(newValue);
            }
            else {
                //turns existing LeafNodes to regular nodes since they are about to have children
                currentNode.rightChild = addNode(newValue,
                        new BinarySearchTree.Node(currentNode.rightChild.value,
                                currentNode.rightChild.leftChild,
                                currentNode.rightChild.rightChild));
            }
        }
        return currentNode;
    }
}
