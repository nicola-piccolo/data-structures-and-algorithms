package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchDoubleRotationRightToLeftOperatorTest {
	@Test
	public void rotate_onlyRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		assertFalse(rootNode.hasChildren());		
	}

	@Test
	public void rotate_rootAndRightChild() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		assertEquals(key, newRootPayload.getKey());
		assertEquals(rightKey, newRightChildPayload.getKey());		
	}
	
	
	@Test
	public void rotate_rootAndRightChildWithGrandChildren() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		
		Integer leftGrandChildKey = 55;
		String leftGrandChildValue = "leftGrandChildValue";
		BinarySearchTreeNodePayload<String> leftGrandChildPayload = new BinarySearchTreeNodePayload<String>(leftGrandChildKey, leftGrandChildValue);
		BinarySearchTreeNode<String> leftGrandChild = new BinarySearchTreeNode<String>(leftGrandChildPayload);
		rightChild.setLeftChild(leftGrandChild);
		
		Integer rightGrandChildKey = 99;
		String rightGrandChildValue = "rightGrandChildValue";
		BinarySearchTreeNodePayload<String> rightGrandChildPayload = new BinarySearchTreeNodePayload<String>(rightGrandChildKey, rightGrandChildValue);
		BinarySearchTreeNode<String> rightGrandChild = new BinarySearchTreeNode<String>(rightGrandChildPayload);
		rightChild.setRightChild(rightGrandChild);
		
		BinarySearchDoubleRotationRightToLeftOperator<String> rotateOperator = new BinarySearchDoubleRotationRightToLeftOperator<String>();
		rotateOperator.rotate(rootNode);
		
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftChildPayload = newLeftChild.getPayload();
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightChildPayload = newRightChild.getPayload();
		BinarySearchTreeNode<String> newRightGrandChild = newRightChild.getRightChild();
		BinarySearchTreeNodePayload<String> newRightGrandChildPayload = newRightGrandChild.getPayload();
		assertEquals(leftGrandChildKey, newRootPayload.getKey());
		assertEquals(key, newLeftChildPayload.getKey());
		assertEquals(rightKey, newRightChildPayload.getKey());
		assertEquals(rightGrandChildKey, newRightGrandChildPayload.getKey());
	}	
}
