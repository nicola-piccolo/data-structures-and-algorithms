package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySearchTreeNodePayloadTest {
	@Test
	public void getKey() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> node = new BinarySearchTreeNodePayload<String>(key, value);
		assertTrue(key == node.getKey());
	}
	@Test
	public void getValue() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> node = new BinarySearchTreeNodePayload<String>(key, value);
		assertTrue(value == node.getValue());
	}
}
