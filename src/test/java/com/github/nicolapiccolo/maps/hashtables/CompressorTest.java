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
		assertTrue(compressor.compress(0, dto) == dto.offset);
	}
	
	private CompressorParametersDto buildDto() {
		CompressorParametersDto dto = new CompressorParametersDto();
		dto.multiplier = 3;
		dto.offset = 7;
		dto.modulo = 13;
		dto.bucketArraySize = 10;
		return dto;
	}
	
	@Test
	public void compress_one_returnsValue() {
		Compressor compressor = new Compressor();
		CompressorParametersDto dto = this.buildDto();
		assertTrue(compressor.compress(1, dto) == ((dto.multiplier + dto.offset) % dto.modulo) % dto.bucketArraySize);
	}
}
