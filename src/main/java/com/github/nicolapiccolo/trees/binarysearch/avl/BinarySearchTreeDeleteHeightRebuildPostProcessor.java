package com.github.nicolapiccolo.trees.binarysearch.avl;

import java.util.Map;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeDeletePostProcessor;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchTreeDeleteHeightRebuildPostProcessor<V>  implements BinarySearchTreeDeletePostProcessor<V> {
	private Map<Integer, Integer> nodeHeights;
	
	public BinarySearchTreeDeleteHeightRebuildPostProcessor(Map<Integer, Integer> nodeHeights) {
		this.nodeHeights = nodeHeights;
		this.nodeHeights.clear();
	}

	@Override
	public void processWith(BinarySearchTreeNode<V> node){
		if(!node.hasChildren()) {
			this.initializeHeightFor(node.getKey());
			return;
		}
		if(node.hasLeftChild()) {
			this.processWith(node.getLeftChild());
		}
		if(node.hasRightChild()) {
			this.processWith(node.getRightChild());
		}
		this.setHeightFor(node);
	}
	
	private void initializeHeightFor(Integer key) {
		this.nodeHeights.put(key, BinarySearchAVLConstants.INITIAL_HEIGHT);
	}
	
	private void setHeightFor(BinarySearchTreeNode<V> node) {
		Integer leftChildHeight = node.hasLeftChild() ? this.nodeHeights.get(node.getLeftChild().getKey()) : 0;
		Integer rightChildHeight = node.hasRightChild() ? this.nodeHeights.get(node.getRightChild().getKey()) : 0;
		Integer nodeHeight = Math.max(leftChildHeight, rightChildHeight) + 1;
		this.nodeHeights.put(node.getKey(), nodeHeight);
	}
}
