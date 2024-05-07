package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchFindOperator<V> {
	private BinarySearchTreeNode<V> root;
	
	public BinarySearchFindOperator(BinarySearchTreeNode<V> root) {
		this.root = root;
	} 
	
	public BinarySearchTreeNode<V> findNodeWith(Integer key){
		if(this.root.getKey().equals(key)) {
			return this.root;
		}
		return this.doFind(key);
	}
	
	private BinarySearchTreeNode<V> doFind(Integer key){
		BinarySearchTreeNode<V> currentNode = this.root;
		while(this.shouldContinueSearch(currentNode, key)) {
			currentNode = this.getNextNode(currentNode, key);
			if(currentNode.getKey().equals(key)) {
				return currentNode;
			}
		}
		return currentNode;
	}
	
	private boolean shouldContinueSearch(BinarySearchTreeNode<V> currentNode, Integer key) {
		return (key > currentNode.getKey() && currentNode.hasRightChild()) ||
				(key < currentNode.getKey() && currentNode.hasLeftChild());
	}
	
	private BinarySearchTreeNode<V> getNextNode(BinarySearchTreeNode<V> currentNode, Integer key){
		return key > currentNode.getKey() ? currentNode.getRightChild() : currentNode.getLeftChild();
	}
}
