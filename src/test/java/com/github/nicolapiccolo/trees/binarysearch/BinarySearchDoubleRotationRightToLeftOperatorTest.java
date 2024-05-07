package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchDoubleRotationRightToLeftOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		assertFalse(rootNode.hasChildren());		
	}

	@Test
	public void rotate_rootAndRightChild() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		assertEquals(key, rootNode.getKey());
		assertEquals(rightKey, newRightChild.getKey());		
	}
	
	
	@Test
	public void rotate_rootAndRightChildWithGrandChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		
		Integer leftGrandChildKey = 55;
		String leftGrandChildValue = "leftGrandChildValue";
		BinarySearchTreeNode<String> leftGrandChild = new BinarySearchTreeNode<String>(leftGrandChildKey, leftGrandChildValue);
		rightChild.setLeftChild(leftGrandChild);
		
		Integer rightGrandChildKey = 99;
		String rightGrandChildValue = "rightGrandChildValue";
		BinarySearchTreeNode<String> rightGrandChild = new BinarySearchTreeNode<String>(rightGrandChildKey, rightGrandChildValue);
		rightChild.setRightChild(rightGrandChild);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getRightChild();
		assertEquals(leftGrandChildKey, rootNode.getKey());
		assertEquals(key, newLeftChild.getKey());
		assertEquals(rightKey, newRightChild.getKey());
		assertEquals(rightGrandChildKey, newRightGrandChild.getKey());
	}	
}
