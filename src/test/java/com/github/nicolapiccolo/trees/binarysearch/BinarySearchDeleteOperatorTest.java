package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchDeleteOperatorTest {

	@Test
	public void delete() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>(counter);
		deleteOperator.delete(rootNode);
		assertTrue(rootNode.isDeleted());
	}
}
