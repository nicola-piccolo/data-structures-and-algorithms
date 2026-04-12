package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class BinarySearchDoubleRotationLeftToRightOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
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
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		
		assertFalse(rootNode.hasRightChild());
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		assertEquals(key, rootNode.getKey());
		assertEquals(leftKey, newLeftChild.getKey());
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
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNode<String> newLeftGrandChild = newLeftChild.getLeftChild();
		assertEquals(rightGrandChildKey, rootNode.getKey());
		assertEquals(leftKey, newLeftChild.getKey());
		assertEquals(key, newRightChild.getKey());
		assertEquals(leftGrandChildKey, newLeftGrandChild.getKey());
	}
}
