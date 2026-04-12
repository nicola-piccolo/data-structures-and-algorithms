package com.github.nicolapiccolo.trees.binarysearch.avl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTree;

public class BinarySearchAVLTreeFactoryTest {
	@Test
	public void create() {
		BinarySearchAVLTreeFactory<String> factory = new BinarySearchAVLTreeFactory<String>();
		int deletedNodesPercentage = 10;
		int deletedNodesCountThreshold = 1;
		BinarySearchTree<String> tree = factory.create(deletedNodesPercentage, deletedNodesCountThreshold);
		assertTrue(tree instanceof BinarySearchTree<?>);
	}
}
