package com.github.nicolapiccolo.trees.binarysearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTreeNodeToRootIterator<V> implements BinarySearchTreeIterator<V> {
	private Iterator<V> iterator;
	private Integer key;
	
	public BinarySearchTreeNodeToRootIterator(Integer key) {
		this.key = key;
		List<V> emptyNodeList = new ArrayList<V>();
		this.buildIteratorWith(emptyNodeList);
	}
	
	private void buildIteratorWith(List<V> nodeList){
		this.iterator = nodeList.iterator();
	}

	@Override
	public void initializeWith(BinarySearchTreeNode<V> root) {
		BinarySearchTreeNode<V> matchingNode = this.findLeafNode(root);
		List<V> payloadList = new ArrayList<V>();
		payloadList.add(matchingNode.getPayload());
		BinarySearchTreeNode<V> currentNode = matchingNode;
		while(!currentNode.isRoot()) {
			currentNode = currentNode.getParent();
			payloadList.add(currentNode.getPayload());
		}
		this.buildIteratorWith(payloadList);
	}
	
	private BinarySearchTreeNode<V> findLeafNode(BinarySearchTreeNode<V> root) {
		BinarySearchFindOperator<V> findOperator = new BinarySearchFindOperator<V>(root);
		return findOperator.findNodeWith(this.key);
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
