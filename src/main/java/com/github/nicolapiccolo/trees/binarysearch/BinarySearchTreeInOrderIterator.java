package com.github.nicolapiccolo.trees.binarysearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTreeInOrderIterator<V> implements BinarySearchTreeIterator<V> {
	private Iterator<V> iterator;
	
	public BinarySearchTreeInOrderIterator() {
		List<V> emptyNodePayloadList = new ArrayList<V>();
		this.buildIteratorWith(emptyNodePayloadList);
	}
	
	private void buildIteratorWith(List<V> nodePayloadList){
		this.iterator = nodePayloadList.iterator();
	}

	@Override
	public void initializeWith(BinarySearchTreeNode<V> root) {
		List<V> nodeList = new ArrayList<V>();
		this.visitInOrderFrom(root, nodeList);
		this.buildIteratorWith(nodeList);
	}
	
	private void visitInOrderFrom(BinarySearchTreeNode<V> currentNode, List<V> nodeList) {
		if(currentNode.hasLeftChild()) {
			this.visitInOrderFrom(currentNode.getLeftChild(), nodeList);
		}
		if(!currentNode.isDeleted()) {
			nodeList.add(currentNode.getPayload());
		}
		if(currentNode.hasRightChild()) {
			this.visitInOrderFrom(currentNode.getRightChild(), nodeList);
		}
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public V next() {
		return this.iterator.next();
	}
}
