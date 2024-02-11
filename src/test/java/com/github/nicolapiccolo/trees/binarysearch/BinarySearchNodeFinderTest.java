package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchNodeFinderTest {
	@Test
	public void findNodeWith_rootKey_returnsRoot() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		BinarySearchNodeFinder<String> finder = new BinarySearchNodeFinder<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(key);
		BinarySearchTreeNodePayload<String> matchingNodePayload = matchingNode.getPayload();
		assertEquals(key, matchingNodePayload.getKey());
		assertEquals(value, matchingNodePayload.getValue());		
	}
	
	@Test
	public void findNodeWith_existingKey_returnsMatchingNode() {
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
		BinarySearchNodeFinder<String> finder = new BinarySearchNodeFinder<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(leftKey);
		BinarySearchTreeNodePayload<String> matchingNodePayload = matchingNode.getPayload();
		assertEquals(leftKey, matchingNodePayload.getKey());
		assertEquals(leftValue, matchingNodePayload.getValue());		
	}
	
	@Test
	public void findNodeWith_nonExistingKey_returnsClosestNode() {
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
		BinarySearchNodeFinder<String> finder = new BinarySearchNodeFinder<String>(rootNode);
		BinarySearchTreeNode<String> matchingNode = finder.findNodeWith(15);
		BinarySearchTreeNodePayload<String> matchingNodePayload = matchingNode.getPayload();
		assertEquals(leftKey, matchingNodePayload.getKey());
		assertEquals(leftValue, matchingNodePayload.getValue());	
	}
}
