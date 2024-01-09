package com.github.nicolapiccolo.trees.binarysearch;

public class BinarySearchTreeNodePayload<V> {
	private Integer key;
	private V value;
	
	public BinarySearchTreeNodePayload(
		Integer key,
		V value
	) {
		this.setKey(key);
		this.setValue(value);
	}
	
	private void setKey(Integer key) {
		this.key = key;
	}
	
	private void setValue(V value) {
		this.value = value;
	}
	
	public Integer getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
}
