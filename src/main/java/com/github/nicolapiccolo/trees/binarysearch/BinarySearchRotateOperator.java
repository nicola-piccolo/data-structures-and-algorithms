package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchRotateOperator<V> {

	public void rotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasChildren()) {
			return;
		}		
		this.doRotate(parentNode);
	}

	private void doRotate(BinarySearchTreeNode<V> parentNode) {
		if(!parentNode.hasRightChild()) {
			this.rotateParentAndLeftChild(parentNode);
			return;
		}
		if(!parentNode.hasLeftChild()) {
			this.rotateParentAndRightChild(parentNode);
			return;
		}
		this.rotateParentWithBothChildren(parentNode);
	}
	
	private void rotateParentAndLeftChild(BinarySearchTreeNode<V> parentNode) {
		this.moveLeftChildToRight(parentNode);
		this.swapNodesPayloads(parentNode, parentNode.getRightChild());
		this.reassignRightChildChildren(parentNode);
	}
	
	private void moveLeftChildToRight(BinarySearchTreeNode<V> parentNode) {
		BinarySearchTreeNode<V> newRightChild = parentNode.getLeftChild();
		parentNode.setRightChild(newRightChild);
		parentNode.resetLeftChild();		
	}
	
	private void swapNodesPayloads(BinarySearchTreeNode<V> firstNode, BinarySearchTreeNode<V> secondNode) {
		BinarySearchTreeNodePayload<V> firstNodePayload = firstNode.getPayload();
		BinarySearchTreeNodePayload<V> secondNodePayload = secondNode.getPayload();
		firstNode.setPayload(secondNodePayload);
		secondNode.setPayload(firstNodePayload);
	}
	
	private void reassignRightChildChildren(BinarySearchTreeNode<V> parentNode) {
		BinarySearchTreeNode<V> rightChild = parentNode.getRightChild();
		if(rightChild.hasLeftChild()) {
			parentNode.setLeftChild(rightChild.getLeftChild());
			rightChild.resetLeftChild();
		}
		if(rightChild.hasRightChild()) {
			rightChild.setLeftChild(rightChild.getRightChild());
			rightChild.resetRightChild();
		}
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
		BinarySearchTreeNode<V> originalRightChild = parentNode.getRightChild();
		this.rotateParentAndLeftChild(parentNode);
		BinarySearchTreeNode<V> currentRightChild = parentNode.getRightChild();
		currentRightChild.setRightChild(originalRightChild);
	}
}
