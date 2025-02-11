package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray5$CoreInterface {
	private double[][] a;
	private double[] d;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample44 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var40;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray5$SingleThreadCPU(ExecutionTarget target) {
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
		setFlag$d = true;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample44 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample44 = (fixedFlag$sample35 && fixedProbFlag$sample44);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedProbFlag$sample44);
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
	public final void set$obs(boolean[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample44 = false;
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

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = d;
				{
					{
						double[] var30 = a[y];
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var30));
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
			logProbability$var31 = cv$sampleAccumulator;
			logProbability$d = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$d;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var31 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < length$obs_measured; var39 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = obs[var39];
					{
						{
							double var33 = d[y];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var33));
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var40;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample35() {
		double[] cv$targetLocal = d;
		double cv$originalProbability = 0.0;
		double cv$proposedProbability = 0.0;
		int cv$arrayLength = cv$targetLocal.length;
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
				double[] cv$temp$0$var30;
				{
					cv$temp$0$var30 = a[y];
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$targetLocal, cv$temp$0$var30));
				{
					{
						{
							for(int var39 = 0; var39 < length$obs_measured; var39 += 1) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$1$var33;
												{
													double var33 = d[y];
													cv$temp$1$var33 = var33;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var39], cv$temp$1$var33)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var39], cv$temp$1$var33)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var39], cv$temp$1$var33));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var39], cv$temp$1$var33)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var39], cv$temp$1$var33)));
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

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		if(!setFlag$d) {
			{
				d = new double[d.length];
			}
		}
		if(!setFlag$obs) {
			{
				obs = new boolean[length$obs_measured];
			}
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		for(int var39 = 0; var39 < length$obs_measured; var39 += 1) {
			if(!fixedFlag$sample44)
				obs[var39] = DistributionSampling.sampleBernoulli(RNG$, d[y]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample35)
				sample35();
		} else {
			if(!fixedFlag$sample35)
				sample35();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var8 = a[0];
		var8[0] = 0.4;
		var8[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var31 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$d = 0.0;
		logProbability$var34 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$var40 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray5(int y, boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    \n    double[] d = dirichlet(a[y]).sample();\n    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}