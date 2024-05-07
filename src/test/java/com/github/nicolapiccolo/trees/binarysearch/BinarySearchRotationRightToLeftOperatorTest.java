package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchRotationRightToLeftOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchRotationRightToLeftOperator<String> rotateOperator = new BinarySearchRotationRightToLeftOperator<String>();
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
		BinarySearchRotationRightToLeftOperator<String> rotateOperator = new BinarySearchRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		assertEquals(rightKey, rootNode.getKey());
		assertEquals(key, newLeftChild.getKey());		
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
		
		BinarySearchRotationRightToLeftOperator<String> rotateOperator = new BinarySearchRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newLeftGrandChild = newLeftChild.getRightChild();
		assertEquals(rightKey, rootNode.getKey());
		assertEquals(key, newLeftChild.getKey());
		assertEquals(rightGrandChildKey, newRightChild.getKey());
		assertEquals(leftGrandChildKey, newLeftGrandChild.getKey());
	}	
	
	@Test
	public void rotate_rootAndBothChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);		
		BinarySearchRotationRightToLeftOperator<String> rotateOperator = new BinarySearchRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNode<String> newLeftGrandChild = newLeftChild.getLeftChild();
		assertEquals(rightKey, rootNode.getKey());
		assertEquals(key, newLeftChild.getKey());
		assertEquals(leftKey, newLeftGrandChild.getKey());
	}	
}
