package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchDeleteOperator<V> {
	private BinarySeachNodesCounter counter;
	
	public BinarySearchDeleteOperator(BinarySeachNodesCounter counter) {
		this.counter = counter;
	}
	
	public void delete(BinarySearchTreeNode<V> nodeToDelete){
		nodeToDelete.setIsDeleted();
		this.counter.decreaseSize();
	}		
}
