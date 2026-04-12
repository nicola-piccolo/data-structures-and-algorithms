package com.github.nicolapiccolo.maps.skiplists;

public class SkipListSearchResult<V> {
	private final SkipListNode<V> node;
	private final SkipListNode<V>[] update;

	public SkipListSearchResult(SkipListNode<V> node, SkipListNode<V>[] update) {
		this.node = node;
		this.update = update;
	}

	public SkipListNode<V> getNode() {
		return this.node;
	}

	public SkipListNode<V>[] getUpdate() {
		return this.update;
	}

	public boolean isExactMatch(Integer key) {
		return this.node != null && this.node.getKey() != null && this.node.getKey().equals(key);
	}
}
