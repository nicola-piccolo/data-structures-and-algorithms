package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchDoubleRotationLeftToRightOperator<V> {

	public void rotate(BinarySearchTreeNode<V> parentNode) {
		if(!this.shouldRotate(parentNode)) {
			return;
		}		
		this.doRotate(parentNode);
	}
	
	private boolean shouldRotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasChildren() || !parentNode.hasLeftChild()) {
			return false;
		}
		BinarySearchTreeNode<V> leftChild = parentNode.getLeftChild();
		return leftChild.hasRightChild();
	}

	private void doRotate(BinarySearchTreeNode<V> parentNode) {
		BinarySearchRotationRightToLeftOperator<V> rightRotationOperator = new BinarySearchRotationRightToLeftOperator<V>();
		rightRotationOperator.rotate(parentNode.getLeftChild());
		BinarySearchRotationLeftToRightOperator<V> leftRotationOperator = new BinarySearchRotationLeftToRightOperator<V>();
		leftRotationOperator.rotate(parentNode);
	}
}
