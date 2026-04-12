package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchSwapNodesOperatorTest {
	@Test
	public void swap() {
		Integer firstKey = 3;
		String firstValue = "firstValue";
		BinarySearchTreeNode<String> firstNode = new BinarySearchTreeNode<String>(firstKey, firstValue);
		Integer secondKey = 5;
		String secondValue = "secondValue";
		BinarySearchTreeNode<String> secondNode = new BinarySearchTreeNode<String>(secondKey, secondValue);
		BinarySearchSwapNodesOperator<String> operator = new BinarySearchSwapNodesOperator<String>();
		operator.swap(firstNode, secondNode);
		assertTrue(firstKey == secondNode.getKey());
		assertTrue(firstValue == secondNode.getPayload());
		assertTrue(secondKey == firstNode.getKey());
		assertTrue(secondValue == firstNode.getPayload());
	}
}
