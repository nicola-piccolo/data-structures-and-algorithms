package com.github.nicolapiccolo.maps.hashtables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CompressorTest {

	@Test(expected = RuntimeException.class)
	public void hashCodeOf_null_throwsException() {
		Compressor compressor = new Compressor();
		compressor.compress(0, null);
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
