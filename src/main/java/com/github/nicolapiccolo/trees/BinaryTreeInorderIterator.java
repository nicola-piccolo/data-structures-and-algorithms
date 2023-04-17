package com.github.nicolapiccolo.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderIterator implements BinaryTreeIterator {
	private List<BinaryTreeNode> nodesToVisit = new ArrayList<>();

	public void initializeWith(BinaryTreeNode root) {
		if(root==null) {
			throw new RuntimeException("Root node is null!");
		}
		this.loadAllDescendantOf(root);
	}
	
	private void loadAllDescendantOf(BinaryTreeNode node) {
		if(node==null) {
			return;
		}
		this.loadAllDescendantOf(node.getLeftChild());
		this.nodesToVisit.add(node);
		this.loadAllDescendantOf(node.getRightChild());
	}

	@Override
	public boolean hasNext() {
		return !this.nodesToVisit.isEmpty();
	}

	@Override
	public Integer next() {
		BinaryTreeNode node = this.getNextNodeToVisit();
		return node.getValue();
	}

	private BinaryTreeNode getNextNodeToVisit() {
		BinaryTreeNode node = this.nodesToVisit.remove(0);
		return node;
	}
}
