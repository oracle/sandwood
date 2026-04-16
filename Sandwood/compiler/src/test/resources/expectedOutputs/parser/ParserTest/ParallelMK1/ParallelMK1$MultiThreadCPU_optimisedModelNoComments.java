package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK1$MultiThreadCPU extends CoreModelMultiThreadCPU implements ParallelMK1$CoreInterface {
boolean[] constrainedFlag$sample20;
	boolean fixedFlag$sample20 = false;
	boolean fixedProbFlag$sample20 = false;
	boolean fixedProbFlag$sample24 = false;
	double[] generated;
	double[] indirection;
	int length$observed;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$generated;
	double logProbability$indirection;
	double logProbability$sample;
	double[] logProbability$sample20;
	double[] logProbability$sample24;
	double[] observed;
	double[] sample;
	boolean system$gibbsForward = true;
	boolean[][] guard$sample20gaussian23$global;

	public ParallelMK1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		fixedFlag$sample20 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
				constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		}
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	@Override
	public final void set$indirection(double[] cv$value, boolean allocated$) {
		indirection = cv$value;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value, boolean allocated$) {
		length$observed = cv$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	@Override
	public final double get$logProbability$indirection() {
		return logProbability$indirection;
	}

	@Override
	public final double[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(double[] cv$value, boolean allocated$) {
		observed = cv$value;
	}

	@Override
	public final double[] get$sample() {
		return sample;
	}

	@Override
	public final void set$sample(double[] cv$value, boolean allocated$) {
		sample = cv$value;
	}

	private final void drawValueSample20(int i, int threadID$cv$i, Rng RNG$) {
		sample[i] = DistributionSampling.sampleUniform(RNG$);
		indirection[i] = sample[i];
	}

	private final void inferSample20(int i, int threadID$cv$i, Rng RNG$) {
		constrainedFlag$sample20[i] = false;
		double cv$originalValue = sample[i];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			boolean[] guard$sample20gaussian23 = guard$sample20gaussian23$global[threadID$cv$i];
			guard$sample20gaussian23[i] = false;
			if(!guard$sample20gaussian23[i]) {
				guard$sample20gaussian23[i] = true;
				constrainedFlag$sample20[i] = true;
				double var22 = indirection[i];
				cv$accumulatedProbabilities = (((0.0 < var22)?(DistributionSampling.logProbabilityGaussian(((generated[i] - cv$originalValue) / Math.sqrt(var22))) - (Math.log(var22) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(!guard$sample20gaussian23[i]) {
				guard$sample20gaussian23[i] = true;
				constrainedFlag$sample20[i] = true;
				cv$accumulatedProbabilities = (((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((generated[i] - cv$originalValue) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample20[i]) {
			sample[i] = cv$proposedValue;
			indirection[i] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			boolean[] guard$sample20gaussian23 = guard$sample20gaussian23$global[threadID$cv$i];
			guard$sample20gaussian23[i] = false;
			if(!guard$sample20gaussian23[i]) {
				guard$sample20gaussian23[i] = true;
				constrainedFlag$sample20[i] = true;
				double var22 = indirection[i];
				cv$accumulatedProbabilities = (((0.0 < var22)?(DistributionSampling.logProbabilityGaussian(((generated[i] - cv$proposedValue) / Math.sqrt(var22))) - (Math.log(var22) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(!guard$sample20gaussian23[i]) {
				guard$sample20gaussian23[i] = true;
				constrainedFlag$sample20[i] = true;
				cv$accumulatedProbabilities = (((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((generated[i] - cv$proposedValue) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				sample[i] = cv$originalValue;
				indirection[i] = sample[i];
			}
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleValue = sample[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample20[i] = cv$distributionAccumulator;
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$indirection = (logProbability$indirection + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample20[i]);
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$indirection = (logProbability$indirection + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double var22 = indirection[i];
				double cv$distributionAccumulator = ((0.0 < var22)?(DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(var22))) - (Math.log(var22) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample24[i] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample24[i]);
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		generated = new double[length$observed];
		indirection = new double[length$observed];
		if(!fixedFlag$sample20)
			sample = new double[length$observed];
		constrainedFlag$sample20 = new boolean[length$observed];
		logProbability$sample20 = new double[length$observed];
		logProbability$sample24 = new double[length$observed];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		guard$sample20gaussian23$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample20gaussian23$global[cv$index] = new boolean[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample20) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[i] = sample[i];
						}
						generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample20)
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
						indirection[i] = sample[i];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample20)
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
						indirection[i] = sample[i];
						generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[i] = sample[i];
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample20)
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
						indirection[i] = sample[i];
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample20) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample20(i, threadID$i, RNG$1);
					}
				);
			else
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample20(i, threadID$i, RNG$1);
					}
				);
		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!constrainedFlag$sample20[i])
							drawValueSample20(i, threadID$i, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample20[i] = Double.NaN;
		}
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample24[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						indirection[i] = sample[i];
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK1(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length];\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        double sample = uniform(0.0, 1.0).sample();\n"
		     + "        indirection[i] = sample;\n"
		     + "        generated[i] = gaussian(sample, indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}