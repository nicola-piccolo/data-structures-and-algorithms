package com.github.nicolapiccolo.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBreadthFirstIterator implements BinaryTreeIterator {
	private List<BinaryTreeNode> nodesToVisit = new ArrayList<>();

	public void initializeWith(BinaryTreeNode root) {
		if(root==null) {
			throw new RuntimeException("Root node is null!");
		}
		this.nodesToVisit.add(root);
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
		BinaryTreeNode node = this.nodesToVisit.remove(this.nodesToVisit.size()-1);
		return node;
	}
	
	private void loadChildrenOf(BinaryTreeNode node) {
		if(node.getLeftChild()!=null) {
			this.nodesToVisit.add(0, node.getLeftChild());
		}		
		if(node.getRightChild()!=null) {
			this.nodesToVisit.add(0, node.getRightChild());
		}
	}
}
