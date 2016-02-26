package main;

/**
 * Created by ted on 2/16/16.
 */
interface IBinarySearchTreeOrderer<T> {
    BinarySearchTree.Node addNode(T newValue, BinarySearchTree.Node currentNode);
}
