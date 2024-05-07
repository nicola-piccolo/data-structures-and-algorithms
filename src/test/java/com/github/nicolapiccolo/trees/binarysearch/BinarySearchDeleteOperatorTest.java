package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySearchDeleteOperatorTest {

	@Test
	public void delete() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>(counter);
		deleteOperator.delete(rootNode);
		assertTrue(rootNode.isDeleted());
	}
}
