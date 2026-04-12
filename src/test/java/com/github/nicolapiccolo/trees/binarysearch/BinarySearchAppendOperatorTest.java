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
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		String anotherValue = "anotherValue";
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter(); 
		BinarySearchAppendOperator<String> operator = new BinarySearchAppendOperator<String>(counter);
		operator.append(rootNode, key, anotherValue);
		assertEquals(key, rootNode.getKey());
		assertEquals(anotherValue, rootNode.getPayload());
	}
	
	@Test
	public void append_deletedValue_overwriteAndRestore() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		rootNode.setIsDeleted();
		String anotherValue = "anotherValue";
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter(); 
		BinarySearchAppendOperator<String> operator = new BinarySearchAppendOperator<String>(counter);
		operator.append(rootNode, key, anotherValue);
		assertEquals(key, rootNode.getKey());
		assertEquals(anotherValue, rootNode.getPayload());
		assertFalse(rootNode.isDeleted());
	}

	@Test
	public void append_newValue_addChild() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter(); 
		BinarySearchAppendOperator<String> operator = new BinarySearchAppendOperator<String>(counter);
		operator.append(rootNode, leftKey, leftValue);
		assertTrue(rootNode.hasLeftChild());
		assertFalse(rootNode.hasRightChild());
		BinarySearchTreeNode<String> leftChild = rootNode.getLeftChild();
		assertEquals(leftKey, leftChild.getKey());
		assertEquals(leftValue, leftChild.getPayload());
	}
}
