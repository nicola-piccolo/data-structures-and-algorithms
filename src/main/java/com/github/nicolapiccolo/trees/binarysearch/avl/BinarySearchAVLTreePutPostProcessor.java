package com.github.nicolapiccolo.trees.binarysearch.avl;

import java.util.Map;
import java.util.Optional;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchDoubleRotationLeftToRightOperator;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchDoubleRotationOperator;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchDoubleRotationRightToLeftOperator;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchFindOperator;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreePutPostProcessor;

public class BinarySearchAVLTreePutPostProcessor<V> implements BinarySearchTreePutPostProcessor<V> {
	private Map<Integer, Integer> nodeHeights;
	
	public BinarySearchAVLTreePutPostProcessor(Map<Integer, Integer> nodeHeights) {
		this.nodeHeights = nodeHeights;
	}

	@Override
	public void processWith(Integer keyToProcess, BinarySearchTreeNode<V> root) {
		BinarySearchTreeNode<V> matchingNode = this.findMatchingNodeFor(keyToProcess, root);
		this.updateNodeHeightsFor(matchingNode);
		Optional<BinarySearchTreeNode<V>> unbalancedNode = this.searchForUnbalancedNodeFrom(matchingNode);
		if(unbalancedNode.isEmpty()) {
			return;
		}
		this.rebalance(unbalancedNode.get());
	}
	
	private BinarySearchTreeNode<V> findMatchingNodeFor(Integer keyToProcess, BinarySearchTreeNode<V> root) {
		BinarySearchFindOperator<V> operator = new BinarySearchFindOperator<V>(root);
		return operator.findNodeWith(keyToProcess);
	}
	
	private void updateNodeHeightsFor(BinarySearchTreeNode<V> matchingNode) {
		BinarySearchAVLBottomUpHeightUpdateOperator<V> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<V>();
		operator.updateHeightsFrom(matchingNode, this.nodeHeights);
	}
	
	private Optional<BinarySearchTreeNode<V>> searchForUnbalancedNodeFrom(BinarySearchTreeNode<V> matchingNode){
		BinarySearchTreeNode<V> currentNode = matchingNode;
		while(!currentNode.isRoot()) {
			currentNode = currentNode.getParent();
			if(this.isNodeUnbalanced(currentNode)) {
				return Optional.of(currentNode);
			}
		}
		return Optional.empty();
	}
	
	private boolean isNodeUnbalanced(BinarySearchTreeNode<V> currentNode) {
		Integer leftChildHeight = currentNode.hasLeftChild() ? this.getHeightOf(currentNode.getLeftChild()) : 0;
		Integer rightChildHeight = currentNode.hasRightChild() ? this.getHeightOf(currentNode.getRightChild()) : 0;
		return Math.abs(leftChildHeight - rightChildHeight) > 1;
	}
	
	private Integer getHeightOf(BinarySearchTreeNode<V> node) {
		return this.nodeHeights.get(node.getKey());
	}
	
	private void rebalance(BinarySearchTreeNode<V> unbalancedNode) {
		BinarySearchDoubleRotationOperator<V> doubleRotationOperator = this.getDoubleRotationOperator(unbalancedNode);
		Integer originalHeight = this.getHeightOf(unbalancedNode);
		doubleRotationOperator.rotate(unbalancedNode);
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<V> nodeHeightUpdateOperator = 
			new BinarySearchAVLRebalancedNodeHeightUpdateOperator<V>();
		nodeHeightUpdateOperator.updateHeightsFrom(unbalancedNode, originalHeight, this.nodeHeights);
	}
	
	private BinarySearchDoubleRotationOperator<V> getDoubleRotationOperator(BinarySearchTreeNode<V> unbalancedNode){
		Integer leftChildHeight = unbalancedNode.hasLeftChild() ? this.getHeightOf(unbalancedNode.getLeftChild()) : 0;
		Integer rightChildHeight = unbalancedNode.hasRightChild() ? this.getHeightOf(unbalancedNode.getRightChild()) : 0;		
		return rightChildHeight > leftChildHeight ? new BinarySearchDoubleRotationRightToLeftOperator<V>() : 
			new BinarySearchDoubleRotationLeftToRightOperator<V>();
	}
}
