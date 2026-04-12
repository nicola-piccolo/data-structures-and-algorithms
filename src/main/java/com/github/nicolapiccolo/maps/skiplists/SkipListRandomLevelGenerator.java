package com.github.nicolapiccolo.maps.skiplists;

public class SkipListRandomLevelGenerator {
	private final int maxLevel;

	public SkipListRandomLevelGenerator(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int generate() {
		int level = 1;
		while (level < this.maxLevel && Math.random() < 0.5) {
			level++;
		}
		return level;
	}
}
