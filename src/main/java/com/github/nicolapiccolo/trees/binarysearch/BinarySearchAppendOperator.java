package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchAppendOperator<V> {
	public void append(BinarySearchTreeNode<V> node, Integer key, V value) {
		if(node.isKeyEqualsTo(key)) {
			this.replaceValueIn(node, value);
		} else {
			this.addChildNodeTo(node, key, value);
		}
	}
	
	private void replaceValueIn(BinarySearchTreeNode<V> node, V newValue) {
		BinarySearchTreeNodePayload<V> oldPayload = node.getPayload();
		BinarySearchTreeNodePayload<V> newPayload = new BinarySearchTreeNodePayload<V>(oldPayload.getKey(), newValue);
		node.setPayload(newPayload);
	}
	
	private void addChildNodeTo(BinarySearchTreeNode<V> parentNode, Integer key, V value) {
		BinarySearchTreeNodePayload<V> payload = new BinarySearchTreeNodePayload<V>(key, value); 
		BinarySearchTreeNode<V> leafNode = new BinarySearchTreeNode<V>(payload);
		if(parentNode.isKeyLessThan(key)) {
			parentNode.setRightChild(leafNode);
		} else {
			parentNode.setLeftChild(leafNode);
		}
	}
	
}
