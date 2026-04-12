package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchAppendOperator<V> {
	private BinarySearchNodesCounter counter;
	
	public BinarySearchAppendOperator(BinarySearchNodesCounter counter) {
		this.counter = counter;
	}
	
	public void append(BinarySearchTreeNode<V> node, Integer key, V value) {
		this.updateNodesCountersOnPut(node, key);
		if(node.getKey().equals(key)) {
			this.replaceValueIn(node, value);
		} else {
			this.addChildNodeTo(node, key, value);
		}
	}
	
	private void updateNodesCountersOnPut(BinarySearchTreeNode<V> node, Integer key) {
		boolean isNewKey = !node.getKey().equals(key);
		boolean isMatchingNodeDeleted = node.isDeleted();
		this.counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
	}
	
	private void replaceValueIn(BinarySearchTreeNode<V> node, V newValue) {
		node.resetIsDeleted();
		node.setPayload(newValue);
	}
	
	private void addChildNodeTo(BinarySearchTreeNode<V> parentNode, Integer key, V value) {
		BinarySearchTreeNode<V> leafNode = new BinarySearchTreeNode<V>(key, value);
		if(key > parentNode.getKey()) {
			parentNode.setRightChild(leafNode);
		} else {
			parentNode.setLeftChild(leafNode);
		}
	}	
}
