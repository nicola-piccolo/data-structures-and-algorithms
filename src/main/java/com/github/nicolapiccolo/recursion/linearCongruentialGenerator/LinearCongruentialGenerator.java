package com.github.nicolapiccolo.recursion.linearCongruentialGenerator;

public class LinearCongruentialGenerator {
	public double generatePseudoRandomNumberFrom(LinearCongruentialGeneratorParameters parameters,
			int numberOfIterations) {
		if (numberOfIterations < 0) {
			throw new IllegalArgumentException("Number of iterations must be non-negative!");
		}
		if (parameters.modulus() == 0) {
			throw new IllegalArgumentException("Modulus must be non-zero!");
		}
		return this.doGenerate(parameters, numberOfIterations);
	}

	private double doGenerate(LinearCongruentialGeneratorParameters parameters, int numberOfIterations) {
		if (numberOfIterations == 0) {
			return parameters.seed();
		}
		double previousPseudoRandomNumber = this.doGenerate(parameters, numberOfIterations - 1);
		return (parameters.multiplier() * previousPseudoRandomNumber + parameters.increment())
				% parameters.modulus();
	}
}
