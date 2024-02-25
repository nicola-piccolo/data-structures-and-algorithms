package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchRotationLeftToRightOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		assertFalse(rootNode.hasChildren());		
	}
	
	@Test
	public void rotate_rootAndLeftChild() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		assertEquals(leftKey, newRootPayload.getKey());
		assertEquals(key, newRightChildPayload.getKey());
	}
	
	@Test
	public void rotate_rootAndLeftChildWithGrandChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		
		Integer leftGrandChildKey = 7;
		String leftGrandChildValue = "leftGrandChildValue";
		BinarySearchTreeNodePayload<String> leftGrandChildPayload = new BinarySearchTreeNodePayload<String>(leftGrandChildKey, leftGrandChildValue);
		BinarySearchTreeNode<String> leftGrandChild = new BinarySearchTreeNode<String>(leftGrandChildPayload);
		leftChild.setLeftChild(leftGrandChild);
		
		Integer rightGrandChildKey = 27;
		String rightGrandChildValue = "rightGrandChildValue";
		BinarySearchTreeNodePayload<String> rightGrandChildPayload = new BinarySearchTreeNodePayload<String>(rightGrandChildKey, rightGrandChildValue);
		BinarySearchTreeNode<String> rightGrandChild = new BinarySearchTreeNode<String>(rightGrandChildPayload);
		leftChild.setRightChild(rightGrandChild);
		
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftChildPayload = newLeftChild.getPayload();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getLeftChild();
		BinarySearchTreeNodePayload<String> newRightGrandChildPayload = newRightGrandChild.getPayload();
		assertEquals(leftKey, newRootPayload.getKey());
		assertEquals(key, newRightChildPayload.getKey());
		assertEquals(leftGrandChildKey, newLeftChildPayload.getKey());
		assertEquals(rightGrandChildKey, newRightGrandChildPayload.getKey());
	}
	
	@Test
	public void rotate_rootAndBothChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);		
		BinarySearchRotationLeftToRightOperator<String> rotateOperator = new BinarySearchRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getRightChild();
		BinarySearchTreeNodePayload<String> newRightGrandChildPayload = newRightGrandChild.getPayload();		
		assertEquals(leftKey, newRootPayload.getKey());
		assertEquals(key, newRightChildPayload.getKey());
		assertEquals(rightKey, newRightGrandChildPayload.getKey());
	}	
}
