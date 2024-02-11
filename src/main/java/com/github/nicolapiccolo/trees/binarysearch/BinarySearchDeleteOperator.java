package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchDeleteOperator<V> {
	public BinarySearchTreeNode<V> deleteRoot(BinarySearchTreeNode<V> root) {
		BinarySearchTreeNode<V> newRoot;
		if(root.hasLeftChild() && root.hasRightChild()) {
			newRoot = this.updateRootWithBothChildren(root);
		} else {
			newRoot = this.updateRootWithSingleChild(root);
		}
		return newRoot;
	}
	
	private BinarySearchTreeNode<V> updateRootWithBothChildren(BinarySearchTreeNode<V> rootNode) {
		BinarySearchTreeNode<V> predecessorNode = this.findPredecessorNodeFor(rootNode);
		predecessorNode.setRightChild(rootNode.getRightChild());
		return predecessorNode;
	}
	
	private BinarySearchTreeNode<V> findPredecessorNodeFor(BinarySearchTreeNode<V> nodeToDelete) {
		BinarySearchTreeNode<V> predecessorNode = nodeToDelete.getLeftChild();
		while(predecessorNode.hasRightChild()) {
			predecessorNode = predecessorNode.getRightChild();
		}
		return predecessorNode;
	}
	
	private BinarySearchTreeNode<V> updateRootWithSingleChild(BinarySearchTreeNode<V> rootNode) {
		BinarySearchTreeNode<V> newRootNode = rootNode.hasLeftChild() ? rootNode.getLeftChild() : rootNode.getRightChild();
		return newRootNode;
	}
	
	public void delete(BinarySearchTreeNode<V> nodeToDelete){
		if(nodeToDelete.hasChildren()) {
			this.attachChildrenTo(nodeToDelete.getParent(), nodeToDelete);
		} else {
			this.resetChildFor(nodeToDelete.getParent(), nodeToDelete);
		}		
	}
	
	private void attachChildrenTo(BinarySearchTreeNode<V> parentNode, BinarySearchTreeNode<V> nodeToDelete){
		if(nodeToDelete.hasLeftChild() && nodeToDelete.hasRightChild()) {
			this.findPredecessorAndReplaceNodes(nodeToDelete);
		} else {
			this.moveChildUpAndAttachTo(parentNode, nodeToDelete);
		}
	}
	
	private void findPredecessorAndReplaceNodes(BinarySearchTreeNode<V> nodeToDelete) {
		BinarySearchTreeNode<V> predecessorNode = this.findPredecessorNodeFor(nodeToDelete);
		this.replaceNodes(nodeToDelete, predecessorNode);
	}
	
	private void replaceNodes(BinarySearchTreeNode<V> nodeToSwap, BinarySearchTreeNode<V> predecessorNode) {
		nodeToSwap.setPayload(predecessorNode.getPayload());
		BinarySearchTreeNode<V> predecessorNodeParent = predecessorNode.getParent();
		if(predecessorNode.hasLeftChild()) {
			BinarySearchTreeNode<V> predecessorNodeLeftChild = predecessorNode.getLeftChild();
			predecessorNodeParent.setRightChild(predecessorNodeLeftChild);
		} else {
			predecessorNodeParent.resetRightChild();
		}
	}
	
	private void moveChildUpAndAttachTo(BinarySearchTreeNode<V> parentNode, BinarySearchTreeNode<V> nodeToDelete){
		BinarySearchTreeNode<V> grandChildNode = nodeToDelete.hasLeftChild() ? nodeToDelete.getLeftChild() : nodeToDelete.getRightChild();
		if(parentNode.getLeftChild() == nodeToDelete) {
			parentNode.setLeftChild(grandChildNode);
		} else {
			parentNode.setRightChild(grandChildNode);
		}
	}
	
	private void resetChildFor(BinarySearchTreeNode<V> parentNode, BinarySearchTreeNode<V> nodeToDelete){
		if(parentNode.getLeftChild() == nodeToDelete) {
			parentNode.resetLeftChild();
		} else {
			parentNode.resetRightChild();
		}
	}		
}
