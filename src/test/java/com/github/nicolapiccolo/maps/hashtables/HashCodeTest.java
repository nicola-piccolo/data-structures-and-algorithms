package com.github.nicolapiccolo.maps.hashtables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class HashCodeTest {

	@Test
	public void hashCodeOf_null_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			HashCode hashCode = new HashCode();
			hashCode.hashCodeOf(null);
		});
	}

	@Test
	public void hashCodeOf_emptyString_returnsZero() {
		HashCode hashCode = new HashCode();
		assertTrue(hashCode.hashCodeOf("") == 0);
	}
	
	@Test
	public void hashCodeOf_singleCharacter_returnsIntValue() {
		HashCode hashCode = new HashCode();
		assertTrue(hashCode.hashCodeOf("a") == (int)'a');
	}
	
	@Test
	public void hashCodeOf_twoCharacters_returnsIntValue() {
		HashCode hashCode = new HashCode();
		assertTrue(hashCode.hashCodeOf("ab") == 3202);
	}
}
