package com.github.nicolapiccolo.maps.skiplists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SkipListTest {

	@Test
	public void isEmpty_newList_returnsTrue() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.isEmpty());
	}

	@Test
	public void size_newList_returnsZero() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.size() == 0);
	}

	@Test
	public void constructor_zeroMaxLevel_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			new SkipList<String>(0);
		});
	}

	@Test
	public void put_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SkipList<String> list = new SkipList<>();
			list.put(null, "value");
		});
	}

	@Test
	public void put_singleEntry() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		assertTrue(list.size() == 1);
		assertFalse(list.isEmpty());
	}

	@Test
	public void put_multipleEntries() {
		SkipList<String> list = new SkipList<>();
		list.put(30, "thirty");
		list.put(10, "ten");
		list.put(20, "twenty");
		assertTrue(list.size() == 3);
	}

	@Test
	public void put_duplicateKey_updatesValue() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		list.put(1, "ONE");
		assertTrue(list.size() == 1);
		assertTrue(list.get(1).get().equals("ONE"));
	}

	@Test
	public void get_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SkipList<String> list = new SkipList<>();
			list.get(null);
		});
	}

	@Test
	public void get_existingKey_returnsValue() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		Optional<String> result = list.get(1);
		assertTrue(result.isPresent());
		assertTrue(result.get().equals("one"));
	}

	@Test
	public void get_nonExistingKey_returnsEmpty() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		assertTrue(list.get(2).isEmpty());
	}

	@Test
	public void get_multipleEntries_returnsCorrectValues() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		list.put(30, "thirty");
		assertTrue(list.get(10).get().equals("ten"));
		assertTrue(list.get(20).get().equals("twenty"));
		assertTrue(list.get(30).get().equals("thirty"));
	}

	@Test
	public void delete_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SkipList<String> list = new SkipList<>();
			list.delete(null);
		});
	}

	@Test
	public void delete_existingKey_removesEntry() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		list.delete(1);
		assertTrue(list.isEmpty());
		assertTrue(list.get(1).isEmpty());
	}

	@Test
	public void delete_nonExistingKey_noChange() {
		SkipList<String> list = new SkipList<>();
		list.put(1, "one");
		list.delete(2);
		assertTrue(list.size() == 1);
	}

	@Test
	public void delete_middleEntry_preservesOthers() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		list.put(30, "thirty");
		list.delete(20);
		assertTrue(list.size() == 2);
		assertTrue(list.get(10).isPresent());
		assertTrue(list.get(20).isEmpty());
		assertTrue(list.get(30).isPresent());
	}

	@Test
	public void firstKey_emptyList_returnsEmpty() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.firstKey().isEmpty());
	}

	@Test
	public void firstKey_multipleEntries_returnsSmallest() {
		SkipList<String> list = new SkipList<>();
		list.put(30, "thirty");
		list.put(10, "ten");
		list.put(20, "twenty");
		assertTrue(list.firstKey().get() == 10);
	}

	@Test
	public void lastKey_emptyList_returnsEmpty() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.lastKey().isEmpty());
	}

	@Test
	public void lastKey_multipleEntries_returnsLargest() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(30, "thirty");
		list.put(20, "twenty");
		assertTrue(list.lastKey().get() == 30);
	}

	@Test
	public void floorKey_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SkipList<String> list = new SkipList<>();
			list.floorKey(null);
		});
	}

	@Test
	public void floorKey_exactMatch_returnsKey() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		list.put(30, "thirty");
		assertTrue(list.floorKey(20).get() == 20);
	}

	@Test
	public void floorKey_betweenKeys_returnsLowerKey() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(30, "thirty");
		assertTrue(list.floorKey(25).get() == 10);
	}

	@Test
	public void floorKey_belowAllKeys_returnsEmpty() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		assertTrue(list.floorKey(5).isEmpty());
	}

	@Test
	public void ceilingKey_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SkipList<String> list = new SkipList<>();
			list.ceilingKey(null);
		});
	}

	@Test
	public void ceilingKey_exactMatch_returnsKey() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		list.put(30, "thirty");
		assertTrue(list.ceilingKey(20).get() == 20);
	}

	@Test
	public void ceilingKey_betweenKeys_returnsHigherKey() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(30, "thirty");
		assertTrue(list.ceilingKey(25).get() == 30);
	}

	@Test
	public void ceilingKey_aboveAllKeys_returnsEmpty() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		assertTrue(list.ceilingKey(25).isEmpty());
	}

	@Test
	public void iterator_multipleEntries_iteratesInSortedOrder() {
		SkipList<String> list = new SkipList<>();
		list.put(30, "thirty");
		list.put(10, "ten");
		list.put(20, "twenty");
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.next() == 10);
		assertTrue(iterator.next() == 20);
		assertTrue(iterator.next() == 30);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void iterator_afterDelete_excludesDeletedKey() {
		SkipList<String> list = new SkipList<>();
		list.put(10, "ten");
		list.put(20, "twenty");
		list.put(30, "thirty");
		list.delete(20);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.next() == 10);
		assertTrue(iterator.next() == 30);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void putAndDelete_manyEntries() {
		SkipList<Integer> list = new SkipList<>();
		for (int i = 0; i < 100; i++) {
			list.put(i, i);
		}
		assertTrue(list.size() == 100);
		for (int i = 0; i < 50; i++) {
			list.delete(i);
		}
		assertTrue(list.size() == 50);
		assertTrue(list.firstKey().get() == 50);
		assertTrue(list.lastKey().get() == 99);
	}

	@Test
	public void maxLevelOne_stillWorks() {
		SkipList<String> list = new SkipList<>(1);
		list.put(30, "thirty");
		list.put(10, "ten");
		list.put(20, "twenty");
		assertTrue(list.size() == 3);
		assertTrue(list.get(10).get().equals("ten"));
		assertTrue(list.firstKey().get() == 10);
		assertTrue(list.lastKey().get() == 30);
	}
}
