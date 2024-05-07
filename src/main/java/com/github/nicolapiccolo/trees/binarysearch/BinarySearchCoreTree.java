package com.github.nicolapiccolo.trees.binarysearch;

import java.util.Optional;

public class BinarySearchCoreTree<V> implements BinarySearchTree<V> {
	private BinarySeachNodesCounter counter;
	private Optional<BinarySearchTreeNode<V>> root = Optional.empty();
	private Optional<BinarySearchTreePutPostProcessor<V>> putPostProcessor = Optional.empty();
	private Optional<BinarySearchTreeDeletePostProcessor<V>> deletePostProcessor = Optional.empty();

	public BinarySearchCoreTree() {
		this.counter = new BinarySeachNodesCounter();
	}
	
	public BinarySearchCoreTree(int deletedNodesPercentage, int deletedNodesCountThreshold) {
		this.counter = new BinarySeachNodesCounter(deletedNodesPercentage, deletedNodesCountThreshold);
	}
	
	@Override
	public int size() {
		return this.counter.getSize();
	}

	@Override
	public Optional<V> get(Integer key) {
		if(this.counter.isEmpty()) {
			return Optional.empty(); 
		}
		return this.doGet(key);
	}
	
	private Optional<V> doGet(Integer key){
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		if(!matchingNode.getKey().equals(key)) {
			return Optional.empty();
		}
		return Optional.of(matchingNode.getPayload());
	}

	private BinarySearchTreeNode<V> getMatchingNodeFor(Integer key){
		BinarySearchFindOperator<V> finder = new BinarySearchFindOperator<V>(this.root.get());
		BinarySearchTreeNode<V> matchingNode = finder.findNodeWith(key);
		return matchingNode;
	}
	
	public void setPutPostProcessor(BinarySearchTreePutPostProcessor<V> processor) {
		this.putPostProcessor = Optional.of(processor);
	}
	
	public void setDeletePostProcessor(BinarySearchTreeDeletePostProcessor<V> processor) {
		this.deletePostProcessor = Optional.of(processor);
	}

	@Override
	public void put(Integer key, V value) {
		if(this.counter.isEmpty()) {
			this.addRootWith(key, value);
		} else {
			this.doPut(key, value);
		}
		this.doPutPostProcess(key);
	}
	
	private void addRootWith(Integer key, V value) {
		BinarySearchTreeNode<V> rootNode = new BinarySearchTreeNode<V>(key, value);
		this.root = Optional.of(rootNode);
		this.counter.initializeSizeToOne();
	}
	
	private void doPut(Integer key, V value) {
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		BinarySearchAppendOperator<V> operator = new BinarySearchAppendOperator<V>(this.counter);
		operator.append(matchingNode, key, value);
	}
	
	private void doPutPostProcess(Integer key) {
		if(!this.putPostProcessor.isEmpty()) {
			BinarySearchTreePutPostProcessor<V> processor = this.putPostProcessor.get();
			processor.processWith(key, this.root.get());
		}
	}

	@Override
	public void delete(Integer key) {
		if(this.counter.isEmpty()) {
			return;
		}
		BinarySearchTreeNode<V> matchingNode = this.getMatchingNodeFor(key);
		if(!this.isKeyExistingAndNodeActive(matchingNode, key)) {
			return;
		}
		if(this.hasOnlyRootNode()) {
			this.doDeleteRoot();
		} else {
			this.doDelete(matchingNode);
		}
	}
	
	private boolean isKeyExistingAndNodeActive(BinarySearchTreeNode<V> matchingNode, int key) {
		return !matchingNode.isDeleted() && matchingNode.getKey().equals(key);
	}
	
	private boolean hasOnlyRootNode() {
		return this.counter.getSize() == 1;
	}
	
	private void doDeleteRoot() {
		this.root = Optional.empty();
		this.counter.resetSize();
	}
	
	private void doDelete(BinarySearchTreeNode<V> nodeToDelete){
		BinarySearchDeleteOperator<V> deleteOperator = new BinarySearchDeleteOperator<V>(this.counter);
		deleteOperator.delete(nodeToDelete);
		if(this.shouldRebuildTree()) {
			this.rebuildTree();
			this.doDeletePostProcess();
		}
	}
	
	private boolean shouldRebuildTree() {
		return this.counter.hasDeletedNodesCountPassedThreshold() && this.counter.hasDeletedNodesPercentagePassedThreshold();
	}
	
	private void rebuildTree() {
		BinarySearchTreeDeleteRebuildPostProcessor<V> deletePostProcessor = new BinarySearchTreeDeleteRebuildPostProcessor<V>();
		deletePostProcessor.processWith(this.root.get());
		BinarySearchTreeNode<V> newRoot = deletePostProcessor.getNewRoot();
		this.root = Optional.of(newRoot);
	}
	
	private void doDeletePostProcess() {
		this.deletePostProcessor.ifPresent(deletePostProcessor -> deletePostProcessor.processWith(this.root.get()));
	}
	
	@Override
	public void iterateWith(BinarySearchTreeIterator<V> binarySearchTreeIterator){
		if(this.counter.isEmpty()) {
			return; 
		}
		binarySearchTreeIterator.initializeWith(this.root.get());
	}
}
