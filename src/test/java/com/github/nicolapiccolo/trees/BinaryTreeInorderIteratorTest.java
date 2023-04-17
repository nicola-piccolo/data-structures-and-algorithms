package com.github.nicolapiccolo.trees;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryTreeInorderIteratorTest {
	@Test(expected = RuntimeException.class)
	public void initializeWith() {
		BinaryTreeNode root = null;
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
	}	
	@Test
	public void hasNext_onlyRoot_returnsTrue() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		assertTrue(iterator.hasNext());
	}
	@Test
	public void next_onlyRoot() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		Integer result = iterator.next();
		assertTrue(value == result);
	}
	@Test
	public void hasNext_onlyRootAfterVisit_returnsFalse() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		iterator.next();
		assertFalse(iterator.hasNext());
	}
	@Test
	public void next_oneLeftChild() {
		Integer parentValue = 1;		
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(parentValue, parent);
		Integer leftChildValue = 2;
		BinaryTreeNode leftChild = new BinaryTreeNode(leftChildValue, root);
		root.setLeftChild(leftChild);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		Integer expectedChildValue = iterator.next();
		assertTrue(leftChildValue == expectedChildValue);		
		Integer expectedParentValue = iterator.next();
		assertTrue(parentValue == expectedParentValue);
	}	
	@Test
	public void next_oneRightChild() {
		Integer parentValue = 1;		
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(parentValue, parent);
		Integer rightChildValue = 2;
		BinaryTreeNode rightChild = new BinaryTreeNode(rightChildValue, root);
		root.setRightChild(rightChild);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		Integer expectedParentValue = iterator.next();
		assertTrue(parentValue == expectedParentValue);		
		Integer expectedChildValue = iterator.next();
		assertTrue(rightChildValue == expectedChildValue);		
	}
	@Test
	public void next_oneGrandChild() {
		BinaryTreeNode parent = null;
		Integer parentValue = 1;		
		BinaryTreeNode root = new BinaryTreeNode(parentValue, parent);
		Integer leftChildValue = 2;
		BinaryTreeNode leftChild = new BinaryTreeNode(leftChildValue, root);
		root.setLeftChild(leftChild);
		Integer rightChildValue = 3;
		BinaryTreeNode rightChild = new BinaryTreeNode(rightChildValue, root);
		root.setRightChild(rightChild);
		Integer leftGrandChildValue = 4;
		BinaryTreeNode leftGrandchild = new BinaryTreeNode(leftGrandChildValue, leftChild);
		leftChild.setLeftChild(leftGrandchild);
		BinaryTreeIterator iterator = new BinaryTreeInorderIterator();
		iterator.initializeWith(root);
		Integer expectedGrandchildValue = iterator.next();
		assertTrue(leftGrandChildValue == expectedGrandchildValue);
		Integer expectedLeftChildValue = iterator.next();
		assertTrue(leftChildValue == expectedLeftChildValue);
		Integer expectedParentValue = iterator.next();
		assertTrue(parentValue == expectedParentValue);		
		Integer expectedRightChildValue = iterator.next();
		assertTrue(rightChildValue == expectedRightChildValue);				
	}
}
