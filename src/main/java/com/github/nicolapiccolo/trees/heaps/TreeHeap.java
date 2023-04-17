package com.github.nicolapiccolo.trees.heaps;

import java.util.ArrayList;
import java.util.List;

import com.github.nicolapiccolo.trees.BinaryTreeIterator;
import com.github.nicolapiccolo.trees.BinaryTreeNode;

public class TreeHeap {
	private BinaryTreeNode root;
	private BinaryTreeNode nextAvailableParent;
	
	public List<Integer> visitWith(BinaryTreeIterator iterator){
		List<Integer> list = new ArrayList<>();
		iterator.initializeWith(this.root);
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
	
	public void insert(Integer newValue) {
		if(this.isHeapEmpty()) {
			this.insertAsRoot(newValue);
			return;
		}
		BinaryTreeNode newLeaf = this.buildLeafNode(newValue, this.nextAvailableParent);
		this.insertAsLastLeaf(newLeaf);
		this.restoreHeapWith(newLeaf);
	}
	
	private boolean isHeapEmpty() {
		return this.root==null;
	}
	
	private void insertAsRoot(Integer newValue) {
		BinaryTreeNode parent = null;
		this.root = this.buildLeafNode(newValue, parent);
		this.nextAvailableParent = this.root;
	}
	
	private BinaryTreeNode buildLeafNode(Integer newValue, BinaryTreeNode parent) {
		return new BinaryTreeNode(newValue, parent);
	}
	
	private void insertAsLastLeaf(BinaryTreeNode leaf) {
		if(this.nextAvailableParent.hasLeftChild()) {
			this.appendLeafAsRightChild(leaf);
			this.updateNextAvailableParent();
		} else {
			this.appendLeafAsLeftChild(leaf);			
		}
	}
	
	private void appendLeafAsRightChild(BinaryTreeNode leaf) {
		this.nextAvailableParent.setRightChild(leaf);
	}
	
	private void updateNextAvailableParent() {
		BinaryTreeNode ancestor = this.findNextAvailableParentAncestor();
		this.nextAvailableParent = this.findLeftmostLeafFor(ancestor);
	}
	
	private BinaryTreeNode findNextAvailableParentAncestor() {
		BinaryTreeNode currentNode = this.nextAvailableParent;
		while(!currentNode.isRoot() && currentNode.getParent().getRightChild()==currentNode) {
			currentNode = currentNode.getParent();
		}
		BinaryTreeNode nextAvailableParentAncestor = currentNode.isRoot() ? currentNode : currentNode.getParent().getRightChild();  
		return nextAvailableParentAncestor;
	}
	
	private BinaryTreeNode findLeftmostLeafFor(BinaryTreeNode ancestor) {
		BinaryTreeNode currentNode = ancestor;
		while(currentNode.hasLeftChild()) {
			currentNode = currentNode.getLeftChild();
		}
		return currentNode;
	}

	private void appendLeafAsLeftChild(BinaryTreeNode leaf) {
		this.nextAvailableParent.setLeftChild(leaf);
	}
	
	private void restoreHeapWith(BinaryTreeNode newLeaf) {
		if(newLeaf.isRoot()) {
			return;
		}
		BinaryTreeNode parent = newLeaf.getParent();
		if(newLeaf.getValue() < parent.getValue()) {
			this.swap(newLeaf, parent);
			this.restoreHeapWith(parent);
		}
	}
	
	private void swap(BinaryTreeNode newLeaf, BinaryTreeNode parent) {
		Integer leafValue = newLeaf.getValue();
		newLeaf.setValue(parent.getValue());
		parent.setValue(leafValue);
	}
}
