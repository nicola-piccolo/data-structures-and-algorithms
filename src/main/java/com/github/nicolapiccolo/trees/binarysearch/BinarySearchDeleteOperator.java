package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchDeleteOperator<V> {
	private BinarySearchNodesCounter counter;
	
	public BinarySearchDeleteOperator(BinarySearchNodesCounter counter) {
		this.counter = counter;
	}
	
	public void delete(BinarySearchTreeNode<V> nodeToDelete){
		nodeToDelete.setIsDeleted();
		this.counter.decreaseSize();
	}		
}
