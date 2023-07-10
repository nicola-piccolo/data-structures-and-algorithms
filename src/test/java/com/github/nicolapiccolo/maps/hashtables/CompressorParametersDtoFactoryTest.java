package com.github.nicolapiccolo.maps.hashtables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.nicolapiccolo.maps.hashtables.CompressorParametersDto;
import com.github.nicolapiccolo.maps.hashtables.CompressorParametersDtoFactory;

public class CompressorParametersDtoFactoryTest {

	@Test(expected = RuntimeException.class)
	public void getDtoFrom_one_throwsException() {
		CompressorParametersDtoFactory factory = new CompressorParametersDtoFactory();
		factory.getDtoFrom(1);
	}

	@Test
	public void getDtoFrom_two() {
		CompressorParametersDtoFactory factory = new CompressorParametersDtoFactory();
		CompressorParametersDto dto = factory.getDtoFrom(2);
		assertTrue(dto.bucketArraySize == 2);
		assertTrue(dto.modulo == 3);
		assertTrue(dto.multiplier < dto.modulo && dto.multiplier > 0);
		assertTrue(dto.offset < dto.modulo && dto.offset > 0);
	}

	@Test
	public void getDtoFrom_three() {
		CompressorParametersDtoFactory factory = new CompressorParametersDtoFactory();
		CompressorParametersDto dto = factory.getDtoFrom(3);
		assertTrue(dto.bucketArraySize == 3);
		assertTrue(dto.modulo == 5);
		assertTrue(dto.multiplier < dto.modulo && dto.multiplier > 0);
		assertTrue(dto.offset < dto.modulo && dto.offset > 0);
	}
	
	@Test
	public void getDtoFrom_four() {
		CompressorParametersDtoFactory factory = new CompressorParametersDtoFactory();
		CompressorParametersDto dto = factory.getDtoFrom(4);
		assertTrue(dto.bucketArraySize == 4);
		assertTrue(dto.modulo == 5);
		assertTrue(dto.multiplier < dto.modulo && dto.multiplier > 0);
		assertTrue(dto.offset < dto.modulo && dto.offset > 0);
	}
}
