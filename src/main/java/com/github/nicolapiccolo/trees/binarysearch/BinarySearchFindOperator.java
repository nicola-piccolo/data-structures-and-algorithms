package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchFindOperator<V> {
	private BinarySearchTreeNode<V> root;
	
	public BinarySearchFindOperator(BinarySearchTreeNode<V> root) {
		this.root = root;
	} 
	
	public BinarySearchTreeNode<V> findNodeWith(Integer key){
		if(this.root.isKeyEqualsTo(key)) {
			return this.root;
		}
		return this.doFind(key);
	}
	
	private BinarySearchTreeNode<V> doFind(Integer key){
		BinarySearchTreeNode<V> currentNode = this.root;
		while(this.shouldContinueSearch(currentNode, key)) {
			currentNode = this.getNextNode(currentNode, key);
			if(currentNode.isKeyEqualsTo(key)) {
				return currentNode;
			}
		}
		return currentNode;
	}
	
	private boolean shouldContinueSearch(BinarySearchTreeNode<V> currentNode, Integer key) {
		return (currentNode.isKeyLessThan(key) && currentNode.hasRightChild()) ||
				(currentNode.isKeyGreaterThan(key) && currentNode.hasLeftChild());
	}
	
	private BinarySearchTreeNode<V> getNextNode(BinarySearchTreeNode<V> currentNode, Integer key){
		return currentNode.isKeyLessThan(key) ? currentNode.getRightChild() : currentNode.getLeftChild();
	}
}
