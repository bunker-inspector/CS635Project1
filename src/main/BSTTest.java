package main;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

import main.BST.Leaf;

public class BSTTest {

	public void checkBstness(BST test) {
        if (test.getRoot() != null) {
            checkBstness(test.getRoot());
        }
        else
            System.out.println("Root is null: Nothing to do.");
    }

    private void checkBstness(Leaf l) {
    	
        if (l.getLeftChild() != null) {
        	assertTrue(l.getValue().compareTo(l.getLeftChild().getValue()) > 0);
            checkBstness(l.getLeftChild());
    	}
            
        if (l.getRightChild() != null) {
        	assertTrue(l.getValue().compareTo(l.getRightChild().getValue()) <= 0);
            checkBstness(l.getRightChild());
        }
    }
    
    private ArrayList<String> bstToArrayList(BST toConvert) {
    	if (toConvert.getRoot() != null)
            return bstToArrayList(toConvert.getRoot(), new ArrayList<String>());
        else
            return new ArrayList<String>();
    		
    }
  
    private ArrayList<String> bstToArrayList(Leaf l, ArrayList<String> converted) {
    	if (l.getLeftChild() != null) 
            converted = bstToArrayList(l.getLeftChild(), converted);
    	
    	converted.add(l.getValue());
            
        if (l.getRightChild() != null) 
            converted = bstToArrayList(l.getRightChild(), converted);
        
        return converted;
        
    }
	
	@Test
	public void addTest() {
		BST test = new BST();
		test.add("0");
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		test.add("5");
		
		checkBstness(test);
		
		ArrayList<String> testAsArrayList = bstToArrayList(test);
	
		assertTrue(testAsArrayList.get(0).equals("0"));
		assertTrue(testAsArrayList.get(1).equals("1"));
		assertTrue(testAsArrayList.get(2).equals("2"));
		assertTrue(testAsArrayList.get(3).equals("3"));
		assertTrue(testAsArrayList.get(4).equals("4"));
		assertTrue(testAsArrayList.get(5).equals("5"));
		
		test = new BST();
		
		test.add("4");
		test.add("1");
		test.add("2");
		test.add("5");
		test.add("3");
		test.add("0");
		
		checkBstness(test);
		
		testAsArrayList = bstToArrayList(test);
	
		assertTrue(testAsArrayList.get(0).equals("0"));
		assertTrue(testAsArrayList.get(1).equals("1"));
		assertTrue(testAsArrayList.get(2).equals("2"));
		assertTrue(testAsArrayList.get(3).equals("3"));
		assertTrue(testAsArrayList.get(4).equals("4"));
		assertTrue(testAsArrayList.get(5).equals("5"));
	}

}
