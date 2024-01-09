package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;

public class BinarySearchTreeInOrderIteratorBuilderTest {
	@Test
	public void buildFrom_noRoot() {
		BinarySearchTreeInOrderIteratorBuilder<String> builder = new BinarySearchTreeInOrderIteratorBuilder<String>();
		Optional<BinarySearchTreeNode<String>> root = Optional.empty();
		Iterator<BinarySearchTreeNodePayload<String>> iterator = builder.buildFrom(root);
		assertFalse(iterator.hasNext());
	}
	@Test
	public void buildFrom_onlyRoot() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(payload);
		BinarySearchTreeInOrderIteratorBuilder<String> builder = new BinarySearchTreeInOrderIteratorBuilder<String>();
		Iterator<BinarySearchTreeNodePayload<String>> iterator = builder.buildFrom(Optional.of(rootNode));
		assertTrue(iterator.hasNext());
		assertTrue(payload == iterator.next());
		assertFalse(iterator.hasNext());
	}
	@Test
	public void buildFrom_leftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(payload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftPayload);
		rootNode.setLeftChild(leftChild);
		BinarySearchTreeInOrderIteratorBuilder<String> builder = new BinarySearchTreeInOrderIteratorBuilder<String>();
		Iterator<BinarySearchTreeNodePayload<String>> iterator = builder.buildFrom(Optional.of(rootNode));
		assertTrue(iterator.hasNext());
		assertTrue(leftPayload == iterator.next());
		assertTrue(iterator.hasNext());
		assertTrue(payload == iterator.next());
		assertFalse(iterator.hasNext());
	}
	@Test
	public void buildFrom_rightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(payload);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightPayload);
		rootNode.setRightChild(rightChild);
		BinarySearchTreeInOrderIteratorBuilder<String> builder = new BinarySearchTreeInOrderIteratorBuilder<String>();
		Iterator<BinarySearchTreeNodePayload<String>> iterator = builder.buildFrom(Optional.of(rootNode));
		assertTrue(iterator.hasNext());
		assertTrue(payload == iterator.next());
		assertTrue(iterator.hasNext());
		assertTrue(rightPayload == iterator.next());
		assertFalse(iterator.hasNext());
	}
	@Test
	public void buildFrom_leftAndRightChildren() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(payload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftPayload);		
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightPayload);
		rootNode.setRightChild(rightChild);
		BinarySearchTreeInOrderIteratorBuilder<String> builder = new BinarySearchTreeInOrderIteratorBuilder<String>();
		Iterator<BinarySearchTreeNodePayload<String>> iterator = builder.buildFrom(Optional.of(rootNode));
		assertTrue(iterator.hasNext());
		assertTrue(leftPayload == iterator.next());		
		assertTrue(iterator.hasNext());
		assertTrue(payload == iterator.next());
		assertTrue(iterator.hasNext());
		assertTrue(rightPayload == iterator.next());
		assertFalse(iterator.hasNext());
	}
}
