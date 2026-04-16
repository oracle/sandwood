package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray3$MultiThreadCPU extends CoreModelMultiThreadCPU implements RaggedArray3$CoreInterface {
double[][] a;
	boolean constrainedFlag$sample39 = true;
	double[] d;
	boolean fixedFlag$sample39 = false;
	boolean fixedProbFlag$sample39 = false;
	boolean fixedProbFlag$sample53 = false;
	int length$obs_measured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$d;
	double logProbability$obs;
	double logProbability$var51;
	int[] obs;
	int[] obs_measured;
	boolean system$gibbsForward = true;
	int y;
	double[] cv$var37$countGlobal;

	public RaggedArray3$MultiThreadCPU(ExecutionTarget target) {
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
	public final void set$d(double[] cv$value, boolean allocated$) {
		d = cv$value;
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value, boolean allocated$) {
		fixedFlag$sample39 = cv$value;
		constrainedFlag$sample39 = (fixedFlag$sample39 || constrainedFlag$sample39);
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample53 = (fixedFlag$sample39 && fixedProbFlag$sample53);
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value, boolean allocated$) {
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
	public final int[] get$obs() {
		return obs;
	}

	@Override
	public final int[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(int[] cv$value, boolean allocated$) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value, boolean allocated$) {
		y = cv$value;
	}

	private final void drawValueSample39() {
		int lengthCV$a$37_16 = -1;
		{
			{
				if((0 == y))
					lengthCV$a$37_16 = 2;
			}
		}
		{
			{
				if((1 == y))
					lengthCV$a$37_16 = 3;
			}
		}
		DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_16, d);
	}

	private final void inferSample39() {
		if(true) {
			constrainedFlag$sample39 = false;
			double[] cv$targetLocal = d;
			double[] cv$countLocal = cv$var37$countGlobal;
			int lengthCV$a$37_14 = -1;
			{
				{
					if((0 == y))
						lengthCV$a$37_14 = 2;
				}
			}
			{
				{
					if((1 == y))
						lengthCV$a$37_14 = 3;
				}
			}
			int cv$arrayLength = lengthCV$a$37_14;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									for(int var50 = 0; var50 < length$obs_measured; var50 += 1) {
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											constrainedFlag$sample39 = true;
											{
												{
													{
														{
															{
																cv$countLocal[obs[var50]] = (cv$countLocal[obs[var50]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample39) {
				int lengthCV$a$37_15 = -1;
				{
					{
						if((0 == y))
							lengthCV$a$37_15 = 2;
					}
				}
				{
					{
						if((1 == y))
							lengthCV$a$37_15 = 3;
					}
				}
				Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$countLocal, cv$targetLocal, lengthCV$a$37_15);
			}
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = d;
					{
						{
							double[] var35 = a[y];
							int lengthCV$a$37_17 = -1;
							{
								{
									if((0 == y))
										lengthCV$a$37_17 = 2;
								}
							}
							{
								{
									if((1 == y))
										lengthCV$a$37_17 = 3;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var35, lengthCV$a$37_17));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
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
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < length$obs_measured; var50 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = obs[var50];
						{
							{
								int lengthCV$a$37_18 = -1;
								{
									{
										if((0 == y))
											lengthCV$a$37_18 = 2;
									}
								}
								{
									{
										if((1 == y))
											lengthCV$a$37_18 = 3;
									}
								}
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_18)) && (0 < lengthCV$a$37_18)) && (0.0 <= d[cv$sampleValue])) && (d[cv$sampleValue] <= 1.0))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < length$obs_measured; var50 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var51;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		if(!fixedFlag$sample39) {
			{
				int lengthCV$a$37_13 = -1;
				{
					{
						if((0 == y))
							lengthCV$a$37_13 = 2;
					}
				}
				{
					{
						if((1 == y))
							lengthCV$a$37_13 = 3;
					}
				}
				d = new double[lengthCV$a$37_13];
			}
		}
		{
			obs = new int[length$obs_measured];
		}
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		int cv$var34$max = 2;
		cv$var34$max = Math.max(cv$var34$max, 3);
		cv$var37$countGlobal = new double[cv$var34$max];
	}

	@Override
	public final void forwardGeneration() {
		int lengthCV$a$37_19 = -1;
		{
			{
				if((0 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_19 = 2;
				}
			}
		}
		{
			{
				if((1 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_19 = 3;
				}
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_19, d);
		int lengthCV$a$37_20 = -1;
		{
			{
				if((0 == y))
					lengthCV$a$37_20 = 2;
			}
		}
		{
			{
				if((1 == y))
					lengthCV$a$37_20 = 3;
			}
		}
		int lengthCV$a$37_20$1 = lengthCV$a$37_20;
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
						obs[var50] = DistributionSampling.sampleCategorical(RNG$1, d, lengthCV$a$37_20$1);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		int lengthCV$a$37_25 = -1;
		{
			{
				if((0 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_25 = 2;
				}
			}
		}
		{
			{
				if((1 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_25 = 3;
				}
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_25, d);
	}

	@Override
	public final void forwardGenerationPrime() {
		int lengthCV$a$37_21 = -1;
		{
			{
				if((0 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_21 = 2;
				}
			}
		}
		{
			{
				if((1 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_21 = 3;
				}
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_21, d);
		int lengthCV$a$37_22 = -1;
		{
			{
				if((0 == y))
					lengthCV$a$37_22 = 2;
			}
		}
		{
			{
				if((1 == y))
					lengthCV$a$37_22 = 3;
			}
		}
		int lengthCV$a$37_22$1 = lengthCV$a$37_22;
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
						obs[var50] = DistributionSampling.sampleCategorical(RNG$1, d, lengthCV$a$37_22$1);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		int lengthCV$a$37_23 = -1;
		{
			{
				if((0 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_23 = 2;
				}
			}
		}
		{
			{
				if((1 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_23 = 3;
				}
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_23, d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		int lengthCV$a$37_24 = -1;
		{
			{
				if((0 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_24 = 2;
				}
			}
		}
		{
			{
				if((1 == y)) {
					if(!fixedFlag$sample39)
						lengthCV$a$37_24 = 3;
				}
			}
		}
		if(!fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_24, d);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample39)
				inferSample39();
		} else {
			if(!fixedFlag$sample39)
				inferSample39();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample39)
			drawValueSample39();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$d = Double.NaN;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var51 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = obs_measured;
		int[] cv$target1 = obs;
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
		     + "public model RaggedArray3(int y, int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}