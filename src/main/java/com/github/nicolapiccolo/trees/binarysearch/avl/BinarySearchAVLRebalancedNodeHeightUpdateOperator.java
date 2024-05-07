package com.github.nicolapiccolo.trees.binarysearch.avl;

import java.util.Map;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchAVLRebalancedNodeHeightUpdateOperator<V> {

	public void updateHeightsFrom(BinarySearchTreeNode<V> rebalancedNode, Integer originalHeight, Map<Integer, Integer> nodeHeights) {
		this.setRebalancedNodeHeightEqualsToOriginal(rebalancedNode, originalHeight, nodeHeights);
		this.doUpdateHeightsFrom(rebalancedNode, nodeHeights);
	}
	
	private void setRebalancedNodeHeightEqualsToOriginal(BinarySearchTreeNode<V> rebalancedNode, Integer originalHeight, Map<Integer, Integer> nodeHeights) {
		nodeHeights.put(rebalancedNode.getKey(), originalHeight);
	}

	private void doUpdateHeightsFrom(BinarySearchTreeNode<V> rebalancedNode, Map<Integer, Integer> nodeHeights) {
		this.updateChildrenHeightsWith(rebalancedNode, nodeHeights);
		if(rebalancedNode.hasLeftChild()) {
			this.doUpdateHeightsFrom(rebalancedNode.getLeftChild(), nodeHeights);
		}
		if(rebalancedNode.hasRightChild()) {
			this.doUpdateHeightsFrom(rebalancedNode.getRightChild(), nodeHeights);
		}
	}
	
	private void updateChildrenHeightsWith(BinarySearchTreeNode<V> rebalancedNode, Map<Integer, Integer> nodeHeights) {
		Integer rebalancedNodeHeight = nodeHeights.get(rebalancedNode.getKey());
		if(rebalancedNode.hasLeftChild()) {
			BinarySearchTreeNode<V> leftChild = rebalancedNode.getLeftChild();
			nodeHeights.put(leftChild.getKey(), rebalancedNodeHeight-1);
		}
		if(rebalancedNode.hasRightChild()) {
			BinarySearchTreeNode<V> rightChild = rebalancedNode.getRightChild();
			nodeHeights.put(rightChild.getKey(), rebalancedNodeHeight-1);
		}
	}
}
