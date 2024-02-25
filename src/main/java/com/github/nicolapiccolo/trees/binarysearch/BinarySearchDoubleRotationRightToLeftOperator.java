package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchDoubleRotationRightToLeftOperator<V> {

	public void rotate(BinarySearchTreeNode<V> parentNode) {
		if(!this.shouldRotate(parentNode)) {
			return;
		}		
		this.doRotate(parentNode);
	}
	
	private boolean shouldRotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasChildren() || !parentNode.hasRightChild()) {
			return false;
		}
		BinarySearchTreeNode<V> rightChild = parentNode.getRightChild();
		return rightChild.hasLeftChild();
	}

	private void doRotate(BinarySearchTreeNode<V> parentNode) {
		BinarySearchRotationLeftToRightOperator<V> leftRotationOperator = new BinarySearchRotationLeftToRightOperator<V>();
		leftRotationOperator.rotate(parentNode.getRightChild());
		BinarySearchRotationRightToLeftOperator<V> rightRotationOperator = new BinarySearchRotationRightToLeftOperator<V>();
		rightRotationOperator.rotate(parentNode);
	}
}
