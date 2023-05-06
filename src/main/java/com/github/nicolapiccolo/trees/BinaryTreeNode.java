package com.github.nicolapiccolo.trees;

public class BinaryTreeNode {
	private Integer value;
	private BinaryTreeNode parent;
	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;
	
	public BinaryTreeNode(
		Integer value,
		BinaryTreeNode parent
	) {
		this.setValue(value);
		this.setParent(parent);
	}
	
	public void setValue(Integer newValue) {
		this.value = newValue;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public BinaryTreeNode getParent() {
		return this.parent;
	}
	
	public boolean isRoot() {
		return this.parent == null;
	}
	
	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}
	
	public void setLeftChild(BinaryTreeNode leftChild){
		this.leftChild = leftChild;
	}
	
	public void resetLeftChild(){
		this.leftChild = null;
	}
	
	public BinaryTreeNode getLeftChild(){
		return this.leftChild;
	}
	
	public boolean hasLeftChild() {
		return this.leftChild != null;
	}
	
	public void setRightChild(BinaryTreeNode rightChild){
		this.rightChild = rightChild;
	}
	
	public void resetRightChild(){
		this.rightChild = null;
	}

	public BinaryTreeNode getRightChild(){
		return this.rightChild;
	}
	
	public boolean hasRightChild() {
		return this.rightChild != null;
	}
}
