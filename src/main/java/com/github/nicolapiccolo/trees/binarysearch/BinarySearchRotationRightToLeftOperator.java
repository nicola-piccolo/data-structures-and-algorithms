package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchRotationRightToLeftOperator<V> {

	public void rotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasChildren() || !parentNode.hasRightChild()) {
			return;
		}		
		this.doRotate(parentNode);
	}

	private void doRotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasLeftChild()) {
			this.rotateParentAndRightChild(parentNode);
			return;
		}
		this.rotateParentWithBothChildren(parentNode);
	}
	
	private void rotateParentAndRightChild(BinarySearchTreeNode<V> parentNode) {
		this.moveRightChildToLeft(parentNode);
		this.swapNodesPayloads(parentNode, parentNode.getLeftChild());
		this.reassignLeftChildChildren(parentNode);
	}
	
	private void moveRightChildToLeft(BinarySearchTreeNode<V> parentNode) {
		BinarySearchTreeNode<V> newLeftChild = parentNode.getRightChild();
		parentNode.setLeftChild(newLeftChild);
		parentNode.resetRightChild();		
	}

	private void swapNodesPayloads(BinarySearchTreeNode<V> firstNode, BinarySearchTreeNode<V> secondNode) {
		BinarySearchTreeNodePayload<V> firstNodePayload = firstNode.getPayload();
		BinarySearchTreeNodePayload<V> secondNodePayload = secondNode.getPayload();
		firstNode.setPayload(secondNodePayload);
		secondNode.setPayload(firstNodePayload);
	}

	private void reassignLeftChildChildren(BinarySearchTreeNode<V> parentNode) {
		BinarySearchTreeNode<V> leftChild = parentNode.getLeftChild();
		if(leftChild.hasRightChild()) {
			parentNode.setRightChild(leftChild.getRightChild());
			leftChild.resetRightChild();
		}		
		if(leftChild.hasLeftChild()) {
			leftChild.setRightChild(leftChild.getLeftChild());
			leftChild.resetLeftChild();
		}
	}
	
	private void rotateParentWithBothChildren(BinarySearchTreeNode<V> parentNode) {
		BinarySearchTreeNode<V> originalLeftChild = parentNode.getLeftChild();
		this.rotateParentAndRightChild(parentNode);
		BinarySearchTreeNode<V> currentLeftChild = parentNode.getLeftChild();
		currentLeftChild.setLeftChild(originalLeftChild);
	}
}
