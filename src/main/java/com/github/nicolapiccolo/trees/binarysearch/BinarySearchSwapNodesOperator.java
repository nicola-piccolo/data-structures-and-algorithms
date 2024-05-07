package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchSwapNodesOperator<V> {

	public void swap(BinarySearchTreeNode<V> firstNode, BinarySearchTreeNode<V> secondNode) {
		Integer firstNodekey = firstNode.getKey();
		V firstNodePayload = firstNode.getPayload();
		Integer secondNodekey = secondNode.getKey();
		V secondNodePayload = secondNode.getPayload();
		firstNode.setKey(secondNodekey);
		firstNode.setPayload(secondNodePayload);
		secondNode.setKey(firstNodekey);
		secondNode.setPayload(firstNodePayload);
	}
}
