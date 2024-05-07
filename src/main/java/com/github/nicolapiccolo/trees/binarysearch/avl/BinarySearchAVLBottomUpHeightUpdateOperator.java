package com.github.nicolapiccolo.trees.binarysearch.avl;

import java.util.Map;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchAVLBottomUpHeightUpdateOperator<V> {

	public void updateHeightsFrom(BinarySearchTreeNode<V> startNode, Map<Integer, Integer> nodeHeights) {
		if(this.hasHeightFor(startNode, nodeHeights)) {
			return;
		}
		this.initializeHeightFor(startNode, nodeHeights);
		this.doUpdateHeightsFrom(startNode, nodeHeights);
	}
	
	private boolean hasHeightFor(BinarySearchTreeNode<V> node, Map<Integer, Integer> nodeHeights) {
		return nodeHeights.containsKey(node.getKey());
	}	
	
	private void initializeHeightFor(BinarySearchTreeNode<V> startNode, Map<Integer, Integer> nodeHeights) {
		nodeHeights.put(startNode.getKey(), BinarySearchAVLConstants.INITIAL_HEIGHT);
	}
	
	private void doUpdateHeightsFrom(BinarySearchTreeNode<V> startNode, Map<Integer, Integer> nodeHeights) {
		BinarySearchTreeNode<V> currentNode = startNode;
		Integer currentHeight = BinarySearchAVLConstants.INITIAL_HEIGHT;
		while(this.shouldContinue(currentNode, nodeHeights)) {
			currentNode = currentNode.getParent();
			currentHeight++;
			this.doUpdateNodeHeightOf(currentNode, currentHeight, nodeHeights);
		}		
	}
	
	private boolean shouldContinue(BinarySearchTreeNode<V> currentNode, Map<Integer, Integer> nodeHeights) {
		if(currentNode.isRoot()) {
			return false;
		}
		BinarySearchTreeNode<V> parentNode = currentNode.getParent();
		Integer leftChildHeight = parentNode.hasLeftChild() ? this.getHeightOf(parentNode.getLeftChild(), nodeHeights) : 0;
		Integer rightChildHeight = parentNode.hasRightChild() ? this.getHeightOf(parentNode.getRightChild(), nodeHeights) : 0;
		return Math.abs(leftChildHeight - rightChildHeight) <= 1;
	}
	
	private Integer getHeightOf(BinarySearchTreeNode<V> node, Map<Integer, Integer> nodeHeights) {
		return nodeHeights.get(node.getKey());
	}
	
	private void doUpdateNodeHeightOf(BinarySearchTreeNode<V> currentNode, Integer currentHeight, Map<Integer, Integer> nodeHeights) {
		if(nodeHeights.get(currentNode.getKey()) < currentHeight) {
			nodeHeights.put(currentNode.getKey(), currentHeight);
		}
	}
}
