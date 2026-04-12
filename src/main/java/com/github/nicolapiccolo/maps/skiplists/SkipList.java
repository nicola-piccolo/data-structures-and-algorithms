package com.github.nicolapiccolo.maps.skiplists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SkipList<V> {
	private static final int DEFAULT_MAX_LEVEL = 16;
	private final int maxLevel;
	private final SkipListNode<V> head;
	private final SkipListRandomLevelGenerator levelGenerator;
	private final SkipListSearchOperator<V> searchOperator;
	private int size;

	public SkipList() {
		this(DEFAULT_MAX_LEVEL);
	}

	public SkipList(int maxLevel) {
		if (maxLevel < 1) {
			throw new IllegalArgumentException("Max level must be at least 1!");
		}
		this.maxLevel = maxLevel;
		this.head = new SkipListNode<>(null, null, maxLevel);
		this.levelGenerator = new SkipListRandomLevelGenerator(maxLevel);
		this.searchOperator = new SkipListSearchOperator<>(this.head, maxLevel);
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void put(Integer key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		SkipListSearchResult<V> result = this.searchOperator.search(key);
		if (result.isExactMatch(key)) {
			result.getNode().setValue(value);
			return;
		}
		this.insertNewNode(key, value, result.getUpdate());
	}

	private void insertNewNode(Integer key, V value, SkipListNode<V>[] update) {
		int newLevel = this.levelGenerator.generate();
		SkipListNode<V> newNode = new SkipListNode<>(key, value, newLevel);
		for (int level = 0; level < newLevel; level++) {
			newNode.setForward(level, update[level].getForward(level));
			update[level].setForward(level, newNode);
		}
		this.size++;
	}

	public Optional<V> get(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		SkipListSearchResult<V> result = this.searchOperator.search(key);
		if (result.isExactMatch(key)) {
			return Optional.of(result.getNode().getValue());
		}
		return Optional.empty();
	}

	public void delete(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		SkipListSearchResult<V> result = this.searchOperator.search(key);
		if (!result.isExactMatch(key)) {
			return;
		}
		this.removeNode(result.getNode(), result.getUpdate());
	}

	private void removeNode(SkipListNode<V> nodeToRemove, SkipListNode<V>[] update) {
		for (int level = 0; level < nodeToRemove.getLevel(); level++) {
			update[level].setForward(level, nodeToRemove.getForward(level));
		}
		this.size--;
	}

	public Optional<Integer> firstKey() {
		SkipListNode<V> first = this.head.getForward(0);
		if (first == null) {
			return Optional.empty();
		}
		return Optional.of(first.getKey());
	}

	public Optional<Integer> lastKey() {
		SkipListNode<V> current = this.head;
		for (int level = this.maxLevel - 1; level >= 0; level--) {
			while (current.getForward(level) != null) {
				current = current.getForward(level);
			}
		}
		if (current == this.head) {
			return Optional.empty();
		}
		return Optional.of(current.getKey());
	}

	public Optional<Integer> floorKey(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		SkipListSearchResult<V> result = this.searchOperator.search(key);
		if (result.isExactMatch(key)) {
			return Optional.of(key);
		}
		SkipListNode<V> predecessor = result.getUpdate()[0];
		if (predecessor == this.head) {
			return Optional.empty();
		}
		return Optional.of(predecessor.getKey());
	}

	public Optional<Integer> ceilingKey(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		SkipListSearchResult<V> result = this.searchOperator.search(key);
		if (result.getNode() == null) {
			return Optional.empty();
		}
		return Optional.of(result.getNode().getKey());
	}

	public Iterator<Integer> iterator() {
		List<Integer> keys = new ArrayList<>();
		SkipListNode<V> current = this.head.getForward(0);
		while (current != null) {
			keys.add(current.getKey());
			current = current.getForward(0);
		}
		return keys.iterator();
	}
}
