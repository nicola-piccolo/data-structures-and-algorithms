package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchTreeNode<V> {
	private Integer key;
	private V payload;
	private BinarySearchTreeNode<V> parent;
	private BinarySearchTreeNode<V> leftChild;
	private BinarySearchTreeNode<V> rightChild;
	private boolean isDeleted;
	
	public BinarySearchTreeNode(Integer key, V payload) {
		this.setKey(key);
		this.setPayload(payload);
		this.resetIsDeleted();
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public void setPayload(V payload) {
		this.payload = payload;
	}
	
	public void resetIsDeleted() {
		this.isDeleted = false;
	}
	
	public void setIsDeleted() {
		this.isDeleted = true;
	}
	
	public boolean isDeleted() {
		return this.isDeleted;
	}
	
	public void setParent(BinarySearchTreeNode<V> parent) {
		this.parent = parent;
	}
	
	public void resetParent() {
		this.parent = null;
	}
	
	public Integer getKey() {
		return this.key;
	}
	
	public V getPayload() {
		return this.payload;
	}
	
	public BinarySearchTreeNode<V> getParent() {
		return this.parent;
	}
	
	public boolean isRoot() {
		return this.parent == null;
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
