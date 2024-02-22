package com.github.nicolapiccolo.trees.binarysearch;

import java.util.Iterator;
import java.util.Optional;

public class BinarySearchCoreTree<V> implements BinarySearchTree<V> {
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
		return this.doGet(key);
	}
	
	private Optional<V> doGet(Integer key){
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		if(!matchingNode.isKeyEqualsTo(key)) {
			return Optional.empty();
		}
		BinarySearchTreeNodePayload<V> payload = matchingNode.getPayload();
		return Optional.of(payload.getValue());
	}

	private BinarySearchTreeNode<V> getMatchingNodeFor(Integer key){
		BinarySearchFindOperator<V> finder = new BinarySearchFindOperator<V>(this.root.get());
		BinarySearchTreeNode<V> matchingNode = finder.findNodeWith(key);
		return matchingNode;
	}

	@Override
	public void put(Integer key, V value) {
		if(this.size == 0) {
			this.addRootWith(key, value);
			return;
		}
		this.doPut(key, value);
	}
	
	private void addRootWith(Integer key, V value) {
		BinarySearchTreeNodePayload<V> payload = new BinarySearchTreeNodePayload<V>(key, value);
		BinarySearchTreeNode<V> rootNode = new BinarySearchTreeNode<V>(payload);
		this.root = Optional.of(rootNode);
		this.size = 1;
	}
	
	private void doPut(Integer key, V value) {
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		if(!matchingNode.isKeyEqualsTo(key)) {
			this.size++;
		}
		BinarySearchAppendOperator<V> operator = new BinarySearchAppendOperator<V>();
		operator.append(matchingNode, key, value);
	}

	@Override
	public void delete(Integer key) {
		if(this.size == 0) {
			return;
		}
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		if(!matchingNode.isKeyEqualsTo(key)) {
			return;
		}
		this.doDelete(matchingNode);
	}
	
	private void doDelete(BinarySearchTreeNode<V> nodeToDelete){
		if(nodeToDelete.isRoot()) {
			this.doDeleteRoot(nodeToDelete);
		} else {
			this.doDeleteNonRootNode(nodeToDelete);
		}
		this.size--;
	}
	
	private void doDeleteRoot(BinarySearchTreeNode<V> root) {
		if(this.size == 1) {
			this.root = Optional.empty();
		} else {
			BinarySearchDeleteOperator<V> deleteOperator = new BinarySearchDeleteOperator<V>();
			BinarySearchTreeNode<V> newRoot = deleteOperator.deleteRoot(root);
			this.root = Optional.of(newRoot);
		}
	}
	
	private void doDeleteNonRootNode(BinarySearchTreeNode<V> nodeToDelete) {
		BinarySearchDeleteOperator<V> deleteOperator = new BinarySearchDeleteOperator<V>();
		deleteOperator.delete(nodeToDelete);
	}
	
	public Iterator<BinarySearchTreeNodePayload<V>> iterator(){
		BinarySearchTreeInOrderIteratorBuilder<V> builder = new BinarySearchTreeInOrderIteratorBuilder<V>();
		return builder.buildFrom(root);
	}
}
