package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySearchAppendOperatorTest {

	@Test
	public void append_existingValue_overwrite() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		String anotherValue = "anotherValue";
		BinarySearchAppendOperator<String> operator = new BinarySearchAppendOperator<String>();
		operator.append(rootNode, key, anotherValue);
		BinarySearchTreeNodePayload<String> newRootPayload = rootNode.getPayload();
		assertEquals(key, newRootPayload.getKey());
		assertEquals(anotherValue, newRootPayload.getValue());
	}

	@Test
	public void append_newValue_addChild() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchAppendOperator<String> operator = new BinarySearchAppendOperator<String>();
		operator.append(rootNode, leftKey, leftValue);
		assertTrue(rootNode.hasLeftChild());
		assertFalse(rootNode.hasRightChild());
		BinarySearchTreeNode<String> leftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> leftChildPayload = leftChild.getPayload();
		assertEquals(leftKey, leftChildPayload.getKey());
		assertEquals(leftValue, leftChildPayload.getValue());
	}
}
