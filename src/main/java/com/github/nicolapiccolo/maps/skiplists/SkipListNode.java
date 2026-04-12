package com.github.nicolapiccolo.maps.skiplists;

public class SkipListNode<V> {
	private final Integer key;
	private V value;
	private final SkipListNode<V>[] forward;

	@SuppressWarnings("unchecked")
	public SkipListNode(Integer key, V value, int level) {
		this.key = key;
		this.value = value;
		this.forward = new SkipListNode[level];
	}

	public Integer getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getLevel() {
		return this.forward.length;
	}

	public SkipListNode<V> getForward(int level) {
		return this.forward[level];
	}

	public void setForward(int level, SkipListNode<V> node) {
		this.forward[level] = node;
	}
}
