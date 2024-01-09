package com.github.nicolapiccolo.trees.binarysearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BinarySearchTreeInOrderIteratorBuilder<V> {
	public Iterator<BinarySearchTreeNodePayload<V>> buildFrom(Optional<BinarySearchTreeNode<V>> root){
		List<BinarySearchTreeNodePayload<V>> payloads = new ArrayList<BinarySearchTreeNodePayload<V>>();
		if(!root.isEmpty()) {
			BinarySearchTreeNode<V> rootNode = root.get();
			this.visitInOrderFrom(rootNode, payloads);
		}
		return payloads.iterator();
	}
	
	private void visitInOrderFrom(BinarySearchTreeNode<V> currentNode, List<BinarySearchTreeNodePayload<V>> payloads) {
		if(currentNode.hasLeftChild()) {
			this.visitInOrderFrom(currentNode.getLeftChild(), payloads);
		}
		payloads.add(currentNode.getPayload());
		if(currentNode.hasRightChild()) {
			this.visitInOrderFrom(currentNode.getRightChild(), payloads);
		}
	}
}
