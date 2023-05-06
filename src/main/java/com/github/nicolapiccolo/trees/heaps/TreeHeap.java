package com.github.nicolapiccolo.trees.heaps;

import java.util.ArrayList;
import java.util.List;

import com.github.nicolapiccolo.trees.BinaryTreeIterator;
import com.github.nicolapiccolo.trees.BinaryTreeNode;

public class TreeHeap {
	private BinaryTreeNode root;
	private BinaryTreeNode nextAvailableParent;
	private BinaryTreeNode latestLeaf;
	
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
		this.restoreHeapBottomUpWith(newLeaf);
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
		this.latestLeaf = leaf;
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
	
	private void restoreHeapBottomUpWith(BinaryTreeNode newLeaf) {
		if(newLeaf.isRoot()) {
			return;
		}
		BinaryTreeNode parent = newLeaf.getParent();
		if(newLeaf.getValue() < parent.getValue()) {
			this.swap(newLeaf, parent);
			this.restoreHeapBottomUpWith(parent);
		}
	}
	
	private void swap(BinaryTreeNode newLeaf, BinaryTreeNode parent) {
		Integer leafValue = newLeaf.getValue();
		newLeaf.setValue(parent.getValue());
		parent.setValue(leafValue);
	}
	
	public Integer removeRoot() {
		if(this.root == null) {
			throw new RuntimeException("Empty tree, cannot remote root");
		}
		Integer valueToReturn = this.root.getValue();
		this.moveLatestLeafToRoot();
		this.restoreHeapTopDownFrom(this.root);
		return valueToReturn;
	}
	
	public void moveLatestLeafToRoot() {
		if(!this.root.hasLeftChild() && !this.root.hasRightChild()) {
			this.resetHeap();
			return;
		}
		this.swap(this.latestLeaf, this.root);
		this.removeLatestLeaf();
	}
	
	private void resetHeap() {
		this.root = null;
		this.latestLeaf = null;
		this.nextAvailableParent = null;
	}
	
	private void removeLatestLeaf() {
		BinaryTreeNode latestLeafParent = this.latestLeaf.getParent();
		if(latestLeafParent.hasRightChild()) {
			this.removeLatestLeafAsRightChild();
		} else {
			this.removeLatestLeafAsLeftChild();
		}
	}
	
	private void removeLatestLeafAsRightChild() {
		this.nextAvailableParent = this.latestLeaf.getParent();
		this.nextAvailableParent.resetRightChild();		
		this.latestLeaf = this.nextAvailableParent.getLeftChild();
	}
	
	private void removeLatestLeafAsLeftChild() {
		this.nextAvailableParent.resetLeftChild();
		this.latestLeaf = this.findRightmostLeaf();
	}
	
	private BinaryTreeNode findRightmostLeaf() {
		BinaryTreeNode currentNode = this.latestLeaf.getParent();
		while(currentNode != this.root && currentNode == currentNode.getParent().getLeftChild()) {
			currentNode = currentNode.getParent();
		}
		if(currentNode != this.root) {
			currentNode = currentNode.getParent().getLeftChild();
		}
		while(currentNode.hasRightChild()) {
			currentNode = currentNode.getRightChild();
		}
		return currentNode;
	}
	
	private void restoreHeapTopDownFrom(BinaryTreeNode currentNode) {
		if(currentNode==null) {
			return;
		}
		if(this.isLeftChildGreaterThan(currentNode) && this.isRightChildGreaterThan(currentNode)) {
			return;
		}
		BinaryTreeNode child;
		if(this.shouldSwapWithRightChild(currentNode)) {
			child = currentNode.getRightChild();
		} else {
			child = currentNode.getLeftChild();
		}
		this.swap(child, currentNode);
		this.restoreHeapBottomUpWith(child);
	}
	
	private boolean isLeftChildGreaterThan(BinaryTreeNode currentNode) {
		if(!currentNode.hasLeftChild()) {
			return true;
		}
		return currentNode.getLeftChild().getValue() >= currentNode.getValue(); 
	}
	
	private boolean isRightChildGreaterThan(BinaryTreeNode currentNode) {
		if(!currentNode.hasRightChild()) {
			return true;
		}
		return currentNode.getRightChild().getValue() >= currentNode.getValue(); 
	}
	
	private boolean shouldSwapWithRightChild(BinaryTreeNode currentNode) {
		if(!currentNode.hasRightChild()) {
			return false;
		}
		return currentNode.getRightChild().getValue() < currentNode.getLeftChild().getValue();
	}
}
