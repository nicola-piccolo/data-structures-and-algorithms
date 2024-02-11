package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchTreeNode<V> {
	private BinarySearchTreeNodePayload<V> payload;
	private BinarySearchTreeNode<V> parent;
	private BinarySearchTreeNode<V> leftChild;
	private BinarySearchTreeNode<V> rightChild;
	
	public BinarySearchTreeNode(BinarySearchTreeNodePayload<V> payload) {
		this.setPayload(payload);
	}
	
	public void setPayload(BinarySearchTreeNodePayload<V> payload) {
		this.payload = payload;
	}
	
	public void setParent(BinarySearchTreeNode<V> parent) {
		this.parent = parent;
	}
	
	public void resetParent() {
		this.parent = null;
	}
	
	public BinarySearchTreeNodePayload<V> getPayload() {
		return this.payload;
	}
	
	public BinarySearchTreeNode<V> getParent() {
		return this.parent;
	}
	
	public boolean isRoot() {
		return this.parent == null;
	}
	
	public boolean isKeyEqualsTo(Integer key) {
		return this.payload.getKey() == key;
	}
	
	public boolean isKeyGreaterThan(Integer key) {
		return this.payload.getKey() > key;
	}
	
	public boolean isKeyLessThan(Integer key) {
		return this.payload.getKey() < key;
	}
	
	public void setLeftChild(BinarySearchTreeNode<V> leftChild){
		this.leftChild = leftChild;
		this.leftChild.setParent(this);
	}
	
	public void resetLeftChild(){
		this.leftChild.resetParent();
		this.leftChild = null;
	}
	
	public BinarySearchTreeNode<V> getLeftChild(){
		return this.leftChild;
	}
	
	public boolean hasLeftChild() {
		return this.leftChild != null;
	}
	
	public void setRightChild(BinarySearchTreeNode<V> rightChild){
		this.rightChild = rightChild;
		this.rightChild.setParent(this);
	}
	
	public void resetRightChild(){
		this.rightChild.resetParent();
		this.rightChild = null;
	}

	public BinarySearchTreeNode<V> getRightChild(){
		return this.rightChild;
	}
	
	public boolean hasRightChild() {
		return this.rightChild != null;
	}
	
	public boolean hasChildren() {
		return this.hasLeftChild() || this.hasRightChild();
	}
}
