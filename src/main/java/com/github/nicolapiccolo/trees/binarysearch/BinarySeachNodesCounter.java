package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySeachNodesCounter {
	private int activeNodesCount = 0;
	private int deletedNodesCount = 0;
	private int deletedNodesPercentageThreshold = 20;
	private int deletedNodesCountThreshold = 100;

	public BinarySeachNodesCounter() {}

	public BinarySeachNodesCounter(int deletedNodesPercentageThreshold, int deletedNodesCountThreshold) {
		this.deletedNodesPercentageThreshold = deletedNodesPercentageThreshold;
		this.deletedNodesCountThreshold = deletedNodesCountThreshold;
	}
	
	public boolean isEmpty() {
		return this.activeNodesCount == 0;
	}
	
	public void initializeSizeToOne() {
		this.activeNodesCount = 1;
		this.deletedNodesCount = 0;
	}
	
	public int getSize() {
		return this.activeNodesCount;
	}
	
	public void decreaseSize() {
		if(this.activeNodesCount > 0) {
			this.activeNodesCount--;
			this.deletedNodesCount++;			
		}
	}
	
	public void resetSize() {
		this.activeNodesCount = 0;
		this.deletedNodesCount = 0;
	}
	
	public void updateSizeOnPut(boolean isNewKey, boolean isMatchingNodeDeleted) {
		if(isNewKey) {
			this.activeNodesCount++;
		}
		if(!isNewKey && isMatchingNodeDeleted) {
			this.activeNodesCount++;
			this.deletedNodesCount--;
		}
	}
	
	public boolean hasDeletedNodesCountPassedThreshold() {
		return this.deletedNodesCount >= this.deletedNodesCountThreshold;
	}
	
	public boolean hasDeletedNodesPercentagePassedThreshold() {
		if((this.deletedNodesCount + this.activeNodesCount) == 0) {
			return false;
		}
		double currentDeletedNodesPercentage = 100 * this.deletedNodesCount / (this.deletedNodesCount + this.activeNodesCount);
		return currentDeletedNodesPercentage >= this.deletedNodesPercentageThreshold;
	}
}
