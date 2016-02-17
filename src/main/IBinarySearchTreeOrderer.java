package main;

/**
 * Created by ted on 2/16/16.
 */
interface IBinarySearchTreeOrderer {
    BinarySearchTree.Leaf addLeaf(Object newValue, BinarySearchTree.Leaf currentLeaf);
}
