package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchDoubleRotationLeftToRightOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
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
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		assertFalse(rootNode.hasRightChild());
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftChildPayload = newLeftChild.getPayload();
		assertEquals(key, newRootPayload.getKey());
		assertEquals(leftKey, newLeftChildPayload.getKey());
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
		
		BinarySearchDoubleRotationLeftToRightOperator<String> rotateOperator = new BinarySearchDoubleRotationLeftToRightOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftChildPayload = newLeftChild.getPayload();
		BinarySearchTreeNode<String> newLeftGrandChild = newLeftChild.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftGrandChildPayload = newLeftGrandChild.getPayload();
		assertEquals(rightGrandChildKey, newRootPayload.getKey());
		assertEquals(leftKey, newLeftChildPayload.getKey());
		assertEquals(key, newRightChildPayload.getKey());
		assertEquals(leftGrandChildKey, newLeftGrandChildPayload.getKey());
	}
}
