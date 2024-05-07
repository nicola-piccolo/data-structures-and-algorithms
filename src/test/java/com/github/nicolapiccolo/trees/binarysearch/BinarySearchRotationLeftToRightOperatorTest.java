package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchRotationLeftToRightOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		assertFalse(rootNode.hasChildren());		
	}
	
	@Test
	public void rotate_rootAndLeftChild() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		assertEquals(leftKey, rootNode.getKey());
		assertEquals(key, newRightChild.getKey());
	}
	
	@Test
	public void rotate_rootAndLeftChildWithGrandChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		
		Integer leftGrandChildKey = 7;
		String leftGrandChildValue = "leftGrandChildValue";
		BinarySearchTreeNode<String> leftGrandChild = new BinarySearchTreeNode<String>(leftGrandChildKey, leftGrandChildValue);
		leftChild.setLeftChild(leftGrandChild);
		
		Integer rightGrandChildKey = 27;
		String rightGrandChildValue = "rightGrandChildValue";
		BinarySearchTreeNode<String> rightGrandChild = new BinarySearchTreeNode<String>(rightGrandChildKey, rightGrandChildValue);
		leftChild.setRightChild(rightGrandChild);
		
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getLeftChild();
		assertEquals(leftKey, rootNode.getKey());
		assertEquals(key, newRightChild.getKey());
		assertEquals(leftGrandChildKey, newLeftChild.getKey());
		assertEquals(rightGrandChildKey, newRightGrandChild.getKey());
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
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getRightChild();
		assertEquals(leftKey, rootNode.getKey());
		assertEquals(key, newRightChild.getKey());
		assertEquals(rightKey, newRightGrandChild.getKey());
	}	
}
