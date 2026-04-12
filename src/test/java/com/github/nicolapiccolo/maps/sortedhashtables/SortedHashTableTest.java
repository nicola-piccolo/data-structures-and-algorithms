package com.github.nicolapiccolo.maps.sortedhashtables;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SortedHashTableTest {

	@Test
	public void isEmpty_newTable_returnsTrue() {
		SortedHashTable<String> table = new SortedHashTable<>();
		assertTrue(table.isEmpty());
	}

	@Test
	public void size_newTable_returnsZero() {
		SortedHashTable<String> table = new SortedHashTable<>();
		assertTrue(table.size() == 0);
	}

	@Test
	public void put_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SortedHashTable<String> table = new SortedHashTable<>();
			table.put(null, "value");
		});
	}

	@Test
	public void put_singleEntry() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		assertTrue(table.size() == 1);
		assertFalse(table.isEmpty());
	}

	@Test
	public void put_duplicateKey_updatesValue() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		table.put(1, "ONE");
		assertTrue(table.size() == 1);
		assertTrue(table.get(1).get().equals("ONE"));
	}

	@Test
	public void get_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SortedHashTable<String> table = new SortedHashTable<>();
			table.get(null);
		});
	}

	@Test
	public void get_existingKey_returnsValue() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		Optional<String> result = table.get(1);
		assertTrue(result.isPresent());
		assertTrue(result.get().equals("one"));
	}

	@Test
	public void get_nonExistingKey_returnsEmpty() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		assertTrue(table.get(2).isEmpty());
	}

	@Test
	public void delete_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SortedHashTable<String> table = new SortedHashTable<>();
			table.delete(null);
		});
	}

	@Test
	public void delete_existingKey_removesEntry() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		table.delete(1);
		assertTrue(table.isEmpty());
		assertTrue(table.get(1).isEmpty());
	}

	@Test
	public void delete_nonExistingKey_noChange() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(1, "one");
		table.delete(2);
		assertTrue(table.size() == 1);
	}

	@Test
	public void firstKey_emptyTable_returnsEmpty() {
		SortedHashTable<String> table = new SortedHashTable<>();
		assertTrue(table.firstKey().isEmpty());
	}

	@Test
	public void firstKey_multipleEntries_returnsSmallest() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(30, "thirty");
		table.put(10, "ten");
		table.put(20, "twenty");
		assertTrue(table.firstKey().get() == 10);
	}

	@Test
	public void lastKey_emptyTable_returnsEmpty() {
		SortedHashTable<String> table = new SortedHashTable<>();
		assertTrue(table.lastKey().isEmpty());
	}

	@Test
	public void lastKey_multipleEntries_returnsLargest() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(30, "thirty");
		table.put(20, "twenty");
		assertTrue(table.lastKey().get() == 30);
	}

	@Test
	public void floorKey_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SortedHashTable<String> table = new SortedHashTable<>();
			table.floorKey(null);
		});
	}

	@Test
	public void floorKey_exactMatch_returnsKey() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		table.put(30, "thirty");
		assertTrue(table.floorKey(20).get() == 20);
	}

	@Test
	public void floorKey_betweenKeys_returnsLowerKey() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(30, "thirty");
		assertTrue(table.floorKey(25).get() == 10);
	}

	@Test
	public void floorKey_belowAllKeys_returnsEmpty() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		assertTrue(table.floorKey(5).isEmpty());
	}

	@Test
	public void ceilingKey_nullKey_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			SortedHashTable<String> table = new SortedHashTable<>();
			table.ceilingKey(null);
		});
	}

	@Test
	public void ceilingKey_exactMatch_returnsKey() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		table.put(30, "thirty");
		assertTrue(table.ceilingKey(20).get() == 20);
	}

	@Test
	public void ceilingKey_betweenKeys_returnsHigherKey() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(30, "thirty");
		assertTrue(table.ceilingKey(25).get() == 30);
	}

	@Test
	public void ceilingKey_aboveAllKeys_returnsEmpty() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		assertTrue(table.ceilingKey(25).isEmpty());
	}

	@Test
	public void iterator_multipleEntries_iteratesInSortedOrder() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(30, "thirty");
		table.put(10, "ten");
		table.put(20, "twenty");
		Iterator<Integer> iterator = table.iterator();
		assertTrue(iterator.next() == 10);
		assertTrue(iterator.next() == 20);
		assertTrue(iterator.next() == 30);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void iterator_afterDelete_excludesDeletedKey() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		table.put(30, "thirty");
		table.delete(20);
		Iterator<Integer> iterator = table.iterator();
		assertTrue(iterator.next() == 10);
		assertTrue(iterator.next() == 30);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void firstKey_afterDelete_updatesCorrectly() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		table.delete(10);
		assertTrue(table.firstKey().get() == 20);
	}

	@Test
	public void lastKey_afterDelete_updatesCorrectly() {
		SortedHashTable<String> table = new SortedHashTable<>();
		table.put(10, "ten");
		table.put(20, "twenty");
		table.delete(20);
		assertTrue(table.lastKey().get() == 10);
	}
}
