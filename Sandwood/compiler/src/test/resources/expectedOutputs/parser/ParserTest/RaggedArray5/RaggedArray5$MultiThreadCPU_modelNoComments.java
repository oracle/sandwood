package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray5$CoreInterface {
	private double[][] a;
	private double[] d;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample54 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var36;
	private double logProbability$var39;
	private double logProbability$var52;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[] get$d() {
		return d;
	}

	@Override
	public final void set$d(double[] cv$value) {
		d = cv$value;
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample54 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample54 = (fixedFlag$sample39 && fixedProbFlag$sample54);
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = d;
				{
					{
						double[] var35 = a[y];
						int lengthCV$a$37_11 = -1;
						{
							if((0 == y))
								lengthCV$a$37_11 = 2;
						}
						{
							if((1 == y))
								lengthCV$a$37_11 = 3;
						}
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var35, lengthCV$a$37_11));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$d = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$d;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var36 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < length$obs_measured; var51 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = obs[var51];
					{
						{
							double var38 = d[y];
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var38:(1.0 - var38))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var39 = cv$sampleAccumulator;
			logProbability$var52 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var39 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample39() {
		if(true) {
			double[] cv$targetLocal = d;
			double cv$originalProbability = 0.0;
			double cv$proposedProbability = 0.0;
			int lengthCV$a$37_9 = -1;
			{
				if((0 == y))
					lengthCV$a$37_9 = 2;
			}
			{
				if((1 == y))
					lengthCV$a$37_9 = 3;
			}
			int cv$arrayLength = lengthCV$a$37_9;
			int cv$indexToChange = (int)(0.0 + ((cv$arrayLength - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
			double cv$proposedDifference;
			if((cv$movementRatio < 0))
				cv$proposedDifference = cv$targetLocal[cv$indexToChange];
			else {
				cv$proposedDifference = (1.0 - cv$targetLocal[cv$indexToChange]);
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					if((cv$temp < cv$proposedDifference))
						cv$proposedDifference = cv$temp;
				}
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					if((cv$temp < cv$proposedDifference))
						cv$proposedDifference = cv$temp;
				}
			}
			cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
			double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
			for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				if((cv$valuePos == 1)) {
					{
						for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
							cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
						cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] + cv$proposedDifference);
						for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
							cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var35;
					{
						cv$temp$0$var35 = a[y];
					}
					int cv$temp$1$$var175;
					{
						int lengthCV$a$37_10 = -1;
						{
							if((0 == y))
								lengthCV$a$37_10 = 2;
						}
						{
							if((1 == y))
								lengthCV$a$37_10 = 3;
						}
						cv$temp$1$$var175 = lengthCV$a$37_10;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$targetLocal, cv$temp$0$var35, cv$temp$1$$var175));
					{
						{
							{
								for(int var51 = 0; var51 < length$obs_measured; var51 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var38;
													{
														double var38 = d[y];
														cv$temp$2$var38 = var38;
													}
													if(((Math.log(1.0) + Math.log((obs[var51]?cv$temp$2$var38:(1.0 - cv$temp$2$var38)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((obs[var51]?cv$temp$2$var38:(1.0 - cv$temp$2$var38)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((obs[var51]?cv$temp$2$var38:(1.0 - cv$temp$2$var38))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((obs[var51]?cv$temp$2$var38:(1.0 - cv$temp$2$var38)))))) + 1)) + (Math.log(1.0) + Math.log((obs[var51]?cv$temp$2$var38:(1.0 - cv$temp$2$var38)))));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
						}
					}
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				if((cv$valuePos == 0))
					cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
				else
					cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			if(((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)))))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
				cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
					cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		if(!fixedFlag$sample39) {
			{
				int lengthCV$a$37_8 = -1;
				{
					if((0 == y))
						lengthCV$a$37_8 = 2;
				}
				{
					if((1 == y))
						lengthCV$a$37_8 = 3;
				}
				d = new double[lengthCV$a$37_8];
			}
		}
		{
			obs = new boolean[length$obs_measured];
		}
	}

	@Override
	public final void forwardGeneration() {
		int lengthCV$a$37_12 = -1;
		{
			if((0 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_12 = 2;
			}
		}
		{
			if((1 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_12 = 3;
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_12, d);
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, d[y]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		int lengthCV$a$37_14 = -1;
		{
			if((0 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_14 = 2;
			}
		}
		{
			if((1 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_14 = 3;
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_14, d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		int lengthCV$a$37_13 = -1;
		{
			if((0 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_13 = 2;
			}
		}
		{
			if((1 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_13 = 3;
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_13, d);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample39)
				sample39();
		} else {
			if(!fixedFlag$sample39)
				sample39();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var36 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$d = 0.0;
		logProbability$var39 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var52 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logProbabilityGeneration() {
		int lengthCV$a$37_15 = -1;
		{
			if((0 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_15 = 2;
			}
		}
		{
			if((1 == y)) {
				if(!fixedFlag$sample39)
					lengthCV$a$37_15 = 3;
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_15, d);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = obs_measured;
		boolean[] cv$target1 = obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray5(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}