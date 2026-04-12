package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeNodeToRootIteratorTest {
	@Test
	public void buildFrom_onlyRoot() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchTreeNodeToRootIterator<String> iterator = new BinarySearchTreeNodeToRootIterator<String>(key);
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String payloadToCheck = iterator.next();
		assertTrue(value == payloadToCheck);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void buildFrom_leftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		BinarySearchTreeNodeToRootIterator<String> iterator = new BinarySearchTreeNodeToRootIterator<String>(leftKey);
		iterator.initializeWith(leftChild);
		assertTrue(iterator.hasNext());
		String payloadToCheck = iterator.next();
		assertTrue(leftValue == payloadToCheck);
		assertTrue(iterator.hasNext());
		payloadToCheck = iterator.next();
		assertTrue(value == payloadToCheck);
		assertFalse(iterator.hasNext());
	}
}
