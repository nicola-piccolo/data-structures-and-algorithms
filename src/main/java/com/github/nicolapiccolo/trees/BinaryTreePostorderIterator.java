package com.github.nicolapiccolo.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderIterator implements BinaryTreeIterator {
	private List<BinaryTreeNode> nodesToVisit = new ArrayList<>();
	private int currentIndex = 0;

	public void initializeWith(BinaryTreeNode root) {
		if(root==null) {
			throw new IllegalArgumentException("Root node is null!");
		}
		this.loadAllDescendantOf(root);
	}
	
	private void loadAllDescendantOf(BinaryTreeNode node) {
		if(node==null) {
			return;
		}
		this.loadAllDescendantOf(node.getLeftChild());
		this.loadAllDescendantOf(node.getRightChild());
		this.nodesToVisit.add(node);
	}

	@Override
	public boolean hasNext() {
		return this.currentIndex < this.nodesToVisit.size();
	}

	@Override
	public Integer next() {
		BinaryTreeNode node = this.nodesToVisit.get(this.currentIndex++);
		return node.getValue();
	}
}
