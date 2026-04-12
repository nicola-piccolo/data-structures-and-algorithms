package com.github.nicolapiccolo.trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTreeBreadthFirstIterator implements BinaryTreeIterator {
	private Deque<BinaryTreeNode> nodesToVisit = new ArrayDeque<>();

	public void initializeWith(BinaryTreeNode root) {
		if(root==null) {
			throw new IllegalArgumentException("Root node is null!");
		}
		this.nodesToVisit.add(root);
	}

	@Override
	public boolean hasNext() {
		return !this.nodesToVisit.isEmpty();
	}

	@Override
	public Integer next() {
		BinaryTreeNode node = this.nodesToVisit.poll();
		this.loadChildrenOf(node);
		return node.getValue();
	}

	private void loadChildrenOf(BinaryTreeNode node) {
		if(node.getLeftChild()!=null) {
			this.nodesToVisit.add(node.getLeftChild());
		}		
		if(node.getRightChild()!=null) {
			this.nodesToVisit.add(node.getRightChild());
		}
	}
}
