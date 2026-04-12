package com.github.nicolapiccolo.maps.hashtables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CompressorTest {

	@Test
	public void hashCodeOf_null_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Compressor compressor = new Compressor();
			compressor.compress(0, null);
		});
	}

	@Test
	public void compress_zero_returnsOffset() {
		Compressor compressor = new Compressor();
		CompressorParametersDto dto = this.buildDto();
		assertTrue(compressor.compress(0, dto) == dto.offset());
	}
	
	private CompressorParametersDto buildDto() {
		return new CompressorParametersDto(3, 7, 13, 10);
	}
	
	@Test
	public void compress_one_returnsValue() {
		Compressor compressor = new Compressor();
		CompressorParametersDto dto = this.buildDto();
		assertTrue(compressor.compress(1, dto) == ((dto.multiplier() + dto.offset()) % dto.modulo()) % dto.bucketArraySize());
	}
}
