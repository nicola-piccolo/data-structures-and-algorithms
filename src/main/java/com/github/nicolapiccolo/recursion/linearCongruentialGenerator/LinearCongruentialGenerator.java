package com.github.nicolapiccolo.recursion.linearCongruentialGenerator;

public class LinearCongruentialGenerator {
	public double generatePseudoRandomNumberFrom(LinearCongruentialGeneratorParameters parameters,
			int numberOfIterations) {
		if (numberOfIterations == 0) {
			return parameters.seed;
		}
		int previousNumberOfIterations = numberOfIterations - 1;
		double previousPseudoRandomNumber = this.generatePseudoRandomNumberFrom(parameters, previousNumberOfIterations);
		double pseudoRandomNumber = (parameters.multiplier * previousPseudoRandomNumber + parameters.increment)
				% parameters.modulus;
		return pseudoRandomNumber;
	}
}
