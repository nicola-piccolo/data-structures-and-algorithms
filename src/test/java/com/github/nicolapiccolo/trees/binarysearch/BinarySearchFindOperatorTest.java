package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchFindOperatorTest {
	@Test
	public void findNodeWith_rootKey_returnsRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchFindOperator<String> finder = new BinarySearchFindOperator<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(key);
		assertEquals(key, matchingNode.getKey());
		assertEquals(value, matchingNode.getPayload());		
	}
	
	@Test
	public void findNodeWith_existingKey_returnsMatchingNode() {
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
		BinarySearchFindOperator<String> finder = new BinarySearchFindOperator<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(leftKey);
		assertEquals(leftKey, matchingNode.getKey());
		assertEquals(leftValue, matchingNode.getPayload());		
	}
	
	@Test
	public void findNodeWith_nonExistingKey_returnsClosestNode() {
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
		BinarySearchFindOperator<String> finder = new BinarySearchFindOperator<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(15);
		assertEquals(leftKey, matchingNode.getKey());
		assertEquals(leftValue, matchingNode.getPayload());	
	}
}
