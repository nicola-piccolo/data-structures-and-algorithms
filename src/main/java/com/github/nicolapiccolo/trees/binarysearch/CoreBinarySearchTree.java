package com.github.nicolapiccolo.trees.binarysearch;

import java.util.Iterator;
import java.util.Optional;

public class CoreBinarySearchTree<V> implements BinarySearchTree<V> {
	private int size = 0;
	private Optional<BinarySearchTreeNode<V>> root = Optional.empty();

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Optional<V> get(Integer key) {
		if(this.size == 0) {
			return Optional.empty(); 
		}
		BinarySearchTreeNode<V> rootNode = this.root.get();
		if(this.areKeysMatching(rootNode, key)) {
			return this.buildOptionalFrom(rootNode);
		}
		return this.doGet(key);
	}
	
	private boolean areKeysMatching(BinarySearchTreeNode<V> node, Integer key) {
		BinarySearchTreeNodePayload<V> payload = node.getPayload();
		return payload.getKey() == key;
	}
	
	private Optional<V> buildOptionalFrom(BinarySearchTreeNode<V> node){
		BinarySearchTreeNodePayload<V> payload = node.getPayload();
		return Optional.of(payload.getValue());
	}
	
	private Optional<V> doGet(Integer key){
		BinarySearchTreeNode<V> foundNode = this.searchTreeNodeWith(key);
		if(this.areKeysMatching(foundNode, key)) {
			return this.buildOptionalFrom(foundNode);
		}
		return Optional.empty();
	}

	private BinarySearchTreeNode<V> searchTreeNodeWith(Integer key){
		BinarySearchTreeNode<V> currentNode = this.root.get();
		while(this.shouldContinueSearch(currentNode, key)) {
			currentNode = this.getNextNode(currentNode, key);
			if(this.areKeysMatching(currentNode, key)) {
				return currentNode;
			}
		}
		return currentNode;
	}
	
	private boolean shouldContinueSearch(BinarySearchTreeNode<V> currentNode, Integer key) {
		return (this.isKeyGreaterThan(currentNode, key) && currentNode.hasRightChild()) ||
				(this.isKeyLessThan(currentNode, key) && currentNode.hasLeftChild());
	}
	
	private boolean isKeyGreaterThan(BinarySearchTreeNode<V> currentNode, Integer key) {
		BinarySearchTreeNodePayload<V> payload = currentNode.getPayload();
		return key > payload.getKey();
	}
	
	private boolean isKeyLessThan(BinarySearchTreeNode<V> currentNode, Integer key) {
		BinarySearchTreeNodePayload<V> payload = currentNode.getPayload();
		return key < payload.getKey();
	}
	
	private BinarySearchTreeNode<V> getNextNode(BinarySearchTreeNode<V> currentNode, Integer key){
		return this.isKeyGreaterThan(currentNode, key) ? currentNode.getRightChild() : currentNode.getLeftChild();
	}

	@Override
	public void put(Integer key, V value) {
		if(this.size == 0) {
			this.addRootWith(key, value);
			return;
		}
		BinarySearchTreeNode<V> foundNode = this.searchTreeNodeWith(key);
		if(this.areKeysMatching(foundNode, key)) {
			this.replaceValueIn(foundNode, value);
		} else {
			this.addChildNodeTo(foundNode, key, value);
		}
	}
	
	private void addRootWith(Integer key, V value) {
		BinarySearchTreeNodePayload<V> payload = new BinarySearchTreeNodePayload<V>(key, value);
		BinarySearchTreeNode<V> rootNode = new BinarySearchTreeNode<V>(payload);
		this.root = Optional.of(rootNode);
		this.size = 1;
	}
	
	private void replaceValueIn(BinarySearchTreeNode<V> node, V value) {
		BinarySearchTreeNodePayload<V> oldPayload = node.getPayload();
		BinarySearchTreeNodePayload<V> newPayload = new BinarySearchTreeNodePayload<V>(oldPayload.getKey(), value);
		node.setPayload(newPayload);
	}
	
	private void addChildNodeTo(BinarySearchTreeNode<V> parentNode, Integer key, V value) {
		BinarySearchTreeNodePayload<V> payload = new BinarySearchTreeNodePayload<V>(key, value); 
		BinarySearchTreeNode<V> leafNode = new BinarySearchTreeNode<V>(payload);
		if(this.isKeyGreaterThan(parentNode, key)) {
			parentNode.setRightChild(leafNode);
		} else {
			parentNode.setLeftChild(leafNode);
		}
		this.size++;
	}

	@Override
	public void delete(Integer key) {
		if(this.size == 0) {
			return;
		}
		BinarySearchTreeNode<V> foundNode = this.searchTreeNodeWith(key);
		if(!this.areKeysMatching(foundNode, key)) {
			return;
		}
		this.doDelete(foundNode);
	}
	
	private void doDelete(BinarySearchTreeNode<V> nodeToDelete){
		if(nodeToDelete.isRoot()) {
			this.removeRoot();
			return;
		}
		if(nodeToDelete.hasChildren()) {
			this.attachChildrenTo(nodeToDelete.getParent(), nodeToDelete);
		} else {
			this.resetChildFor(nodeToDelete.getParent(), nodeToDelete);
		}
		this.size--;
	}

	private void removeRoot() {
		BinarySearchTreeNode<V> rootNode = this.root.get();
		if(rootNode.hasChildren()) {
			this.removeRootWithChildren(rootNode);
		} else {
			this.removeRootWithoutChildren();
		}
	}
	
	private void removeRootWithChildren(BinarySearchTreeNode<V> rootNode) {
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			this.updateRootWithBothChildren(rootNode);
		} else {
			this.updateRootWithSingleChild(rootNode);
		}
		this.size--;
	}
	
	private void updateRootWithBothChildren(BinarySearchTreeNode<V> rootNode) {
		BinarySearchTreeNode<V> predecessorNode = this.findPredecessorNodeFor(rootNode);
		predecessorNode.setRightChild(rootNode.getRightChild());
		this.root = Optional.of(predecessorNode);
	}
	
	private BinarySearchTreeNode<V> findPredecessorNodeFor(BinarySearchTreeNode<V> nodeToDelete) {
		BinarySearchTreeNode<V> predecessorNode = nodeToDelete.getLeftChild();
		while(predecessorNode.hasRightChild()) {
			predecessorNode = predecessorNode.getRightChild();
		}
		return predecessorNode;
	}
	
	private void updateRootWithSingleChild(BinarySearchTreeNode<V> rootNode) {
		BinarySearchTreeNode<V> newRootNode = rootNode.hasLeftChild() ? rootNode.getLeftChild() : rootNode.getRightChild();
		this.root = Optional.of(newRootNode);
	}
	
	private void removeRootWithoutChildren() {
		this.root = Optional.empty();
		this.size = 0;
	}
	
	private void attachChildrenTo(BinarySearchTreeNode<V> parentNode, BinarySearchTreeNode<V> nodeToDelete){
		if(nodeToDelete.hasLeftChild() && nodeToDelete.hasRightChild()) {
			this.findPredecessorAndSwapNodes(nodeToDelete);
		} else {
			this.moveChildUpAndAttachTo(parentNode, nodeToDelete);
		}
	}
	
	private void findPredecessorAndSwapNodes(BinarySearchTreeNode<V> nodeToDelete) {
		BinarySearchTreeNode<V> predecessorNode = this.findPredecessorNodeFor(nodeToDelete);
		this.swapNodes(nodeToDelete, predecessorNode);
	}
	
	private void swapNodes(BinarySearchTreeNode<V> nodeToSwap, BinarySearchTreeNode<V> predecessorNode) {
		nodeToSwap.setPayload(predecessorNode.getPayload());
		BinarySearchTreeNode<V> predecessorNodeParent = predecessorNode.getParent();
		BinarySearchTreeNode<V> predecessorNodeLeftChild = predecessorNode.getLeftChild();
		predecessorNodeParent.setRightChild(predecessorNodeLeftChild);
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
	
	public Iterator<BinarySearchTreeNodePayload<V>> iterator(){
		BinarySearchTreeInOrderIteratorBuilder<V> builder = new BinarySearchTreeInOrderIteratorBuilder<V>();
		return builder.buildFrom(root);
	}
}
