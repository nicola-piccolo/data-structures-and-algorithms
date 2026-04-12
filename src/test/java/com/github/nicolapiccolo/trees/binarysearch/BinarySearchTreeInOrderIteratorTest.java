package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeInOrderIteratorTest {
	@Test
	public void initializeWith_onlyRoot() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String nodePayloadToCheck = iterator.next();
		assertTrue(value == nodePayloadToCheck);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void initializeWith_leftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String nodePayloadToCheck = iterator.next();
		assertTrue(leftValue == nodePayloadToCheck);
		assertTrue(iterator.hasNext());
		nodePayloadToCheck = iterator.next();
		assertTrue(value == nodePayloadToCheck);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void initializeWith_rightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String nodePayloadToCheck = iterator.next();
		assertTrue(value == nodePayloadToCheck);
		assertTrue(iterator.hasNext());
		nodePayloadToCheck = iterator.next();
		assertTrue(rightValue == nodePayloadToCheck);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void initializeWith_leftAndRightChildren() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);		
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String nodePayloadToCheck = iterator.next();
		assertTrue(leftValue == nodePayloadToCheck);		
		assertTrue(iterator.hasNext());
		nodePayloadToCheck = iterator.next();
		assertTrue(value == nodePayloadToCheck);
		assertTrue(iterator.hasNext());
		nodePayloadToCheck = iterator.next();
		assertTrue(rightValue == nodePayloadToCheck);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void initializeWith_deletedChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);		
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		rightChild.setIsDeleted();
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		iterator.initializeWith(rootNode);
		assertTrue(iterator.hasNext());
		String nodePayloadToCheck = iterator.next();
		assertTrue(leftValue == nodePayloadToCheck);		
		assertTrue(iterator.hasNext());
		nodePayloadToCheck = iterator.next();
		assertTrue(value == nodePayloadToCheck);
		assertFalse(iterator.hasNext());
	}
}
