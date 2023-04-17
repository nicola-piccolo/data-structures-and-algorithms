package com.github.nicolapiccolo.trees;

import java.util.Stack;

public class BinaryTreePreorderIterator implements BinaryTreeIterator {
	private Stack<BinaryTreeNode> nodesToVisit = new Stack<>();

	public void initializeWith(BinaryTreeNode root) {
		if(root==null) {
			throw new RuntimeException("Root node is null!");
		}
		this.nodesToVisit.push(root);
	}

	@Override
	public boolean hasNext() {
		return !this.nodesToVisit.isEmpty();
	}

	@Override
	public Integer next() {
		BinaryTreeNode node = this.getNextNodeToVisit();
		this.loadChildrenOf(node);
		return node.getValue();
	}

	private BinaryTreeNode getNextNodeToVisit() {
		BinaryTreeNode node = this.nodesToVisit.pop();
		return node;
	}
	
	private void loadChildrenOf(BinaryTreeNode node) {
		if(node.getRightChild()!=null) {
			this.nodesToVisit.push(node.getRightChild());
		}
		if(node.getLeftChild()!=null) {
			this.nodesToVisit.push(node.getLeftChild());
		}
	}
}
