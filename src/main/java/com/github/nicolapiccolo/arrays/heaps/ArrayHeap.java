package com.github.nicolapiccolo.arrays.heaps;

public class ArrayHeap {
	private Integer[] nodes;
	private int nextLatestLeafIndex;
	
	public ArrayHeap(int capacity) {
		this.nodes = new Integer[capacity];
		this.nextLatestLeafIndex = 0;
	}
	
	public void insert(Integer newValue) {
		if(this.isMaxCapacityReached()) {
			throw new RuntimeException("Max capacity reached");
		}
		this.insertAsLastLeaf(newValue);
		this.restoreHeapBottomUp();
	}
	
	private boolean isMaxCapacityReached() {
		return this.nextLatestLeafIndex == this.nodes.length;
	}
	
	private void insertAsLastLeaf(Integer newValue) {
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
		return this.nodes[parentNodeIndex] > this.nodes[currentNodeIndex];
	}
	
	private void swapNodes(int parentNodeIndex, int currentNodeIndex) {
		Integer nodeToSwap = this.nodes[parentNodeIndex];
		this.nodes[parentNodeIndex] = this.nodes[currentNodeIndex];
		this.nodes[currentNodeIndex] = nodeToSwap;
	}
	
	public Integer[] getNodes() {
		Integer[] nodesToReturn = new Integer[this.nextLatestLeafIndex];
		for(int index=0; index<this.nextLatestLeafIndex; index++) {
			nodesToReturn[index] = this.nodes[index];
		}
		return nodesToReturn;
	}
	
	public Integer removeRoot() {
		if(!this.hasNodes()) {
			throw new RuntimeException("No nodes available");
		}
		if(this.hasOnlyRoot()) {
			return this.doRemoveRoot();
		}
		Integer rootNodeValue = this.doRemoveRoot();
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
	
	private Integer doRemoveRoot() {
		this.nextLatestLeafIndex--;
		return this.nodes[0];
	}
	
	private void moveLatestLeafToRoot() {
		this.nodes[0] = this.nodes[this.nextLatestLeafIndex];
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
		int currentNodeValue = this.nodes[currentNodeIndex];
		return this.isValueGreaterThan(leftChildIndex, currentNodeValue) || this.isValueGreaterThan(rightChildIndex, currentNodeValue);
	}
	
	private boolean isValueGreaterThan(int childIndex, int value) {
		if(childIndex >= this.nextLatestLeafIndex) {
			return false;
		}
		return this.nodes[childIndex] <= value;
	}
	
	private int getChildToSwapIndex(int currentNodeIndex) {
		int leftChildIndex = 2 * currentNodeIndex + 1;
		int leftChildValue = this.nodes[leftChildIndex];
		int rightChildIndex = 2 * currentNodeIndex + 2;
		int rightChildValue = this.nodes[rightChildIndex];
		return leftChildValue < rightChildValue ? leftChildIndex : rightChildIndex;
	}
}
