package com.github.nicolapiccolo.arrays.heaps;

public class ArrayHeap<T extends Comparable<T>> {
	private T[] nodes;
	private int nextLatestLeafIndex;
	
	@SuppressWarnings("unchecked")
	public ArrayHeap(int capacity) {
		this.nodes = (T[]) new Comparable[capacity];
		this.nextLatestLeafIndex = 0;
	}
	
	public void insert(T newValue) {
		if(this.isMaxCapacityReached()) {
			throw new IllegalStateException("Max capacity reached");
		}
		this.insertAsLastLeaf(newValue);
		this.restoreHeapBottomUp();
	}
	
	private boolean isMaxCapacityReached() {
		return this.nextLatestLeafIndex == this.nodes.length;
	}
	
	private void insertAsLastLeaf(T newValue) {
		this.nodes[this.nextLatestLeafIndex] = newValue;
		this.nextLatestLeafIndex++;
	}
	
	private void restoreHeapBottomUp() {
		int currentNodeIndex = this.nextLatestLeafIndex - 1;
		while(currentNodeIndex > 0) {
			int parentNodeIndex = this.getParentIndexFor(currentNodeIndex);
			if(!this.shouldSwapNodes(parentNodeIndex, currentNodeIndex)) {
				break;
			}
			this.swapNodes(parentNodeIndex, currentNodeIndex);
			currentNodeIndex = parentNodeIndex;
		}
	}
	
	private int getParentIndexFor(int currentNodeIndex) {
		return Math.floorDiv(currentNodeIndex-1, 2);
	}
	
	private boolean shouldSwapNodes(int parentNodeIndex, int currentNodeIndex) {
		return this.nodes[parentNodeIndex].compareTo(this.nodes[currentNodeIndex]) > 0;
	}
	
	private void swapNodes(int parentNodeIndex, int currentNodeIndex) {
		T nodeToSwap = this.nodes[parentNodeIndex];
		this.nodes[parentNodeIndex] = this.nodes[currentNodeIndex];
		this.nodes[currentNodeIndex] = nodeToSwap;
	}
	
	@SuppressWarnings("unchecked")
	public T[] getNodes() {
		T[] nodesToReturn = (T[]) new Comparable[this.nextLatestLeafIndex];
		for(int index=0; index<this.nextLatestLeafIndex; index++) {
			nodesToReturn[index] = this.nodes[index];
		}
		return nodesToReturn;
	}
	
	public T removeRoot() {
		if(!this.hasNodes()) {
			throw new IllegalStateException("No nodes available");
		}
		if(this.hasOnlyRoot()) {
			return this.doRemoveRoot();
		}
		T rootNodeValue = this.doRemoveRoot();
		this.moveLatestLeafToRoot();
		this.restoreHeapTopDown();
		return rootNodeValue;
	}
	
	private boolean hasNodes() {
		return this.nextLatestLeafIndex > 0;
	}
	
	private boolean hasOnlyRoot() {
		return this.nextLatestLeafIndex == 1;
	}
	
	private T doRemoveRoot() {
		T rootValue = this.nodes[0];
		this.nextLatestLeafIndex--;
		this.nodes[0] = null;
		return rootValue;
	}
	
	private void moveLatestLeafToRoot() {
		this.nodes[0] = this.nodes[this.nextLatestLeafIndex];
		this.nodes[this.nextLatestLeafIndex] = null;
	}
	
	private void restoreHeapTopDown() {
		int currentNodeIndex = 0;
		while(this.shouldCheckCurrentNode(currentNodeIndex)) {
			int childToSwapIndex = this.getChildToSwapIndex(currentNodeIndex);
			this.swapNodes(currentNodeIndex, childToSwapIndex);
			currentNodeIndex = childToSwapIndex;
		}
	}
	
	private boolean shouldCheckCurrentNode(int currentNodeIndex) {
		return currentNodeIndex < this.nextLatestLeafIndex && this.isCurrentNodeGreaterThanChildren(currentNodeIndex);
	}
	
	private boolean isCurrentNodeGreaterThanChildren(int currentNodeIndex) {
		int leftChildIndex = 2 * currentNodeIndex + 1;
		int rightChildIndex = 2 * currentNodeIndex + 2;
		T currentNodeValue = this.nodes[currentNodeIndex];
		return this.isChildSmallerOrEqualTo(leftChildIndex, currentNodeValue) || this.isChildSmallerOrEqualTo(rightChildIndex, currentNodeValue);
	}
	
	private boolean isChildSmallerOrEqualTo(int childIndex, T value) {
		if(childIndex >= this.nextLatestLeafIndex) {
			return false;
		}
		return this.nodes[childIndex].compareTo(value) <= 0;
	}
	
	private int getChildToSwapIndex(int currentNodeIndex) {
		int leftChildIndex = 2 * currentNodeIndex + 1;
		int rightChildIndex = 2 * currentNodeIndex + 2;
		if(rightChildIndex >= this.nextLatestLeafIndex) {
			return leftChildIndex;
		}
		return this.nodes[leftChildIndex].compareTo(this.nodes[rightChildIndex]) <= 0 ? leftChildIndex : rightChildIndex;
	}
	
	public int size() {
		return this.nextLatestLeafIndex;
	}
}
