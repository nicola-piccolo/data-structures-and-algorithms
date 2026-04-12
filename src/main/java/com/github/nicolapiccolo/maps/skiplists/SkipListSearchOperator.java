package com.github.nicolapiccolo.maps.skiplists;

public class SkipListSearchOperator<V> {
	private final SkipListNode<V> head;
	private final int maxLevel;

	public SkipListSearchOperator(SkipListNode<V> head, int maxLevel) {
		this.head = head;
		this.maxLevel = maxLevel;
	}

	public SkipListSearchResult<V> search(Integer key) {
		@SuppressWarnings("unchecked")
		SkipListNode<V>[] update = new SkipListNode[this.maxLevel];
		SkipListNode<V> current = this.head;
		for (int level = this.maxLevel - 1; level >= 0; level--) {
			current = this.advanceWhileLessThan(current, level, key);
			update[level] = current;
		}
		SkipListNode<V> candidate = current.getForward(0);
		return new SkipListSearchResult<>(candidate, update);
	}

	private SkipListNode<V> advanceWhileLessThan(SkipListNode<V> current, int level, Integer key) {
		while (this.hasNextAtLevel(current, level) && current.getForward(level).getKey() < key) {
			current = current.getForward(level);
		}
		return current;
	}

	private boolean hasNextAtLevel(SkipListNode<V> node, int level) {
		return node.getForward(level) != null;
	}
}
