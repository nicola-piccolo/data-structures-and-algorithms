package com.github.nicolapiccolo.trees.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeDeleteRebuildPostProcessor<V> implements BinarySearchTreeDeletePostProcessor<V> {
	private BinarySearchTreeNode<V> newRoot;

	@Override
	public void processWith(BinarySearchTreeNode<V> root){
		List<BinarySearchTreeNode<V>> nodes = this.buildNodeListFrom(root);
		this.newRoot = this.createBalancedTreeFrom(nodes);
	}
	
	private List<BinarySearchTreeNode<V>> buildNodeListFrom(BinarySearchTreeNode<V> root){
		List<BinarySearchTreeNode<V>> nodes = new ArrayList<BinarySearchTreeNode<V>>();
		this.addActiveNodeTo(nodes, root);
		this.resetParentAndChildrenFor(nodes);
		return nodes;
	}
	
	private void addActiveNodeTo(List<BinarySearchTreeNode<V>> nodes, BinarySearchTreeNode<V> node) {
		if(node.hasLeftChild()) {
			this.addActiveNodeTo(nodes, node.getLeftChild());
		}
		if(!node.isDeleted()) {
			nodes.add(node);
		}
		if(node.hasRightChild()) {
			this.addActiveNodeTo(nodes, node.getRightChild());
		}
	}
	
	private void resetParentAndChildrenFor(List<BinarySearchTreeNode<V>> nodes) {
		for(BinarySearchTreeNode<V> node : nodes) {
			node.resetParent();
			if(node.hasLeftChild()) {
				node.resetLeftChild();
			}
			if(node.hasRightChild()) {
				node.resetRightChild();
			}
		}
	}
	
	private BinarySearchTreeNode<V> createBalancedTreeFrom(List<BinarySearchTreeNode<V>> nodes){
		int fromIndex = 0;
		int toIndex = nodes.size()-1;
		int rootIndex = this.getRootIndexFor(fromIndex, toIndex);
		this.doCreateBalancedTreeFrom(nodes, fromIndex, toIndex);
		return nodes.get(rootIndex);
	}
	
	private int getRootIndexFor(int fromIndex, int toIndex) {
		return Math.floorDiv(fromIndex + toIndex, 2);
	}
	
	private void doCreateBalancedTreeFrom(List<BinarySearchTreeNode<V>> nodes, int fromIndex, int toIndex){
		if(!this.shouldContinue(fromIndex, toIndex)) {
			return;
		}
		if(this.shouldProcessTwoNodes(fromIndex, toIndex)) {
			this.processTwoNodes(nodes, fromIndex, toIndex);
			return;
		}
		this.processMoreThanTwoNodes(nodes, fromIndex, toIndex);
	}
	
	private boolean shouldContinue(int fromIndex, int toIndex) {
		return fromIndex != toIndex;
	}
	
	private boolean shouldProcessTwoNodes(int fromIndex, int toIndex) {
		return fromIndex + 1 == toIndex;
	}
	
	private void processTwoNodes(List<BinarySearchTreeNode<V>> nodes, int fromIndex, int toIndex){
		BinarySearchTreeNode<V> parentNode = nodes.get(fromIndex);
		BinarySearchTreeNode<V> rightChildNode = nodes.get(toIndex);
		parentNode.setRightChild(rightChildNode);
	}
	
	private void processMoreThanTwoNodes(List<BinarySearchTreeNode<V>> nodes, int fromIndex, int toIndex){
		int rootIndex = this.getRootIndexFor(fromIndex, toIndex);
		int leftChildIndex = this.getRootIndexFor(fromIndex, rootIndex-1);
		int rightChildIndex = this.getRootIndexFor(rootIndex+1, toIndex);
		this.linkParentWithChildren(nodes, rootIndex, leftChildIndex, rightChildIndex);
		this.doCreateBalancedTreeFrom(nodes, fromIndex, rootIndex-1);
		this.doCreateBalancedTreeFrom(nodes, rootIndex+1, toIndex);		
	}
	
	private void linkParentWithChildren(List<BinarySearchTreeNode<V>> nodes, int rootIndex, int leftChildIndex, int rightChildIndex){
		BinarySearchTreeNode<V> parentNode = nodes.get(rootIndex);
		BinarySearchTreeNode<V> leftChildNode = nodes.get(leftChildIndex);
		BinarySearchTreeNode<V> rightChildNode = nodes.get(rightChildIndex);
		parentNode.setLeftChild(leftChildNode);
		parentNode.setRightChild(rightChildNode);
	}
	
	public BinarySearchTreeNode<V> getNewRoot(){
		return this.newRoot;
	}
}
