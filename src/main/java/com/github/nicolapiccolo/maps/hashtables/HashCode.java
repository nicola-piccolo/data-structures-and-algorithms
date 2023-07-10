package com.github.nicolapiccolo.maps.hashtables;

public class HashCode {
	private static final int NUMBER_OF_BITS_OF_INT = 32;
	private static final int NUMBER_OF_BITS_TO_SHIFT = 5;
	
	public int hashCodeOf(String stringToHash) {
		if(stringToHash == null) {
			throw new RuntimeException("Cannot hash a null string");
		}
		int hashCode = 0;
		for(int index=0; index<stringToHash.length(); index++) {
			hashCode = this.shiftBitsOf(hashCode);
			hashCode += stringToHash.charAt(index);
		}
		return hashCode;
	}
	
	private int shiftBitsOf(int hashCode) {
		int numberOfBitToShiftLeft = NUMBER_OF_BITS_TO_SHIFT;
		int numberOfBitToShiftRight = NUMBER_OF_BITS_OF_INT - NUMBER_OF_BITS_TO_SHIFT;
		int updatedHashCode = (hashCode << numberOfBitToShiftLeft) | (hashCode >> numberOfBitToShiftRight);
		return updatedHashCode;
	}
}
