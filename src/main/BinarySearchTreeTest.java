package main;

import junit.framework.TestCase;

import java.util.Objects;

/**
 * Created by ted on 2/28/16.
 */
public class BinarySearchTreeTest extends TestCase {

    public void testAdd() throws Exception {

    }

    public void testToString() throws Exception {
        BinarySearchTree<String> b = new BinarySearchTree<>();

        b.add("B");
        b.add("A");
        b.add("D");
        b.add("C");
        b.add("E");

        assertEquals( b.toString(), "[ A, B, C, D, E]");
    }

    public void testIterator() throws Exception {
        BinarySearchTree<String> b = new BinarySearchTree<>();

        b.add("C");
        b.add("A");
        b.add("B");
        b.add("E");
        b.add("D");

        BinarySearchTree.BinarySearchTreeIterator bsti = b.iterator();

        while(bsti.hasNext()) {
            assertNotNull(bsti.next());
        }
    }

    public void testToArray() throws Exception {
        BinarySearchTree<String> b = new BinarySearchTree<>();

        b.add("B");
        b.add("D");
        b.add("C");
        b.add("E");
        b.add("A");

        Object[] t = b.toArray();
        Object[] ck = new Object[]{"A", "B", "C", "D", "E"};

        for(int i = 0; i < ck.length; i++)
            assertEquals(ck[i], t[i]);
    }

    public void testToArray1() throws Exception {
        BinarySearchTree<String> b = new BinarySearchTree<>();

        b.add("D");
        b.add("A");
        b.add("B");
        b.add("E");
        b.add("C");

        Object[] o = new Object[7];
        Object[] ck = {"A", "B", "C", "D", "E", null, null};
        b.toArray(o);

        for(int i = 0; i < o.length; i++)
            assertEquals(o[i], ck[i]);
    }
}