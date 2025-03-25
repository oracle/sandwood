package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK19$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK19$CoreInterface {
	private int a;
	private int b;
	private double[][] bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var15;
	private double logProbability$var47;
	private double logProbability$var9;
	private double q;
	private int samples;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK19$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$a() {
		return a;
	}

	@Override
	public final void set$a(int cv$value) {
		a = cv$value;
	}

	@Override
	public final int get$b() {
		return b;
	}

	@Override
	public final void set$b(int cv$value) {
		b = cv$value;
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
		fixedProbFlag$sample48 = (fixedFlag$sample10 && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
		fixedProbFlag$sample48 = (fixedFlag$sample16 && fixedProbFlag$sample48);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
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
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$q() {
		return logProbability$q;
	}

	@Override
	public final double get$logProbability$t() {
		return logProbability$t;
	}

	@Override
	public final double get$q() {
		return q;
	}

	@Override
	public final void set$q(double cv$value) {
		q = cv$value;
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample48 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	@Override
	public final double get$t() {
		return t;
	}

	@Override
	public final void set$t(double cv$value) {
		t = cv$value;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample48 = false;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = q;
				{
					{
						double var7 = 1.0;
						double var8 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var7, var8));
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
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$q = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$q;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = t;
				{
					{
						double var13 = 1.0;
						double var14 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var13, var14));
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
			logProbability$var15 = cv$sampleAccumulator;
			logProbability$t = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$t;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var15 = cv$rvAccumulator;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < samples; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var46];
					{
						{
							double[] inner = bias[0];
							inner[0] = q;
							double var34 = inner[b];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var34));
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
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample10 && fixedFlag$sample16);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample10() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if((0 == b)) {
						{
							for(int var46 = 0; var46 < samples; var46 += 1) {
								cv$count = (cv$count + 1);
								if(flips[var46])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
		}
		q = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			{
				double[] inner = bias[0];
				inner[0] = q;
			}
		}
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if((0 == 0)) {
						if((1 == b)) {
							{
								for(int var46 = 0; var46 < samples; var46 += 1) {
									cv$count = (cv$count + 1);
									if(flips[var46])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		t = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			{
				double[] var28 = bias[0];
				var28[1] = t;
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			bias = new double[1][];
			bias[0] = new double[2];
		}
		{
			flips = new boolean[samples];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample16)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[] inner = bias[0];
		if(!fixedFlag$sample10)
			inner[0] = q;
		double[] var28 = bias[0];
		if(!fixedFlag$sample16)
			var28[1] = t;
		inner[0] = q;
		for(int var46 = 0; var46 < samples; var46 += 1)
			flips[var46] = DistributionSampling.sampleBernoulli(RNG$, inner[b]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				sample10();
			if(!fixedFlag$sample16)
				sample16();
		} else {
			if(!fixedFlag$sample16)
				sample16();
			if(!fixedFlag$sample10)
				sample10();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$q = 0.0;
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var47 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    \n"
		     + "    double[][] bias = new double[1][];\n"
		     + "    private double[] inner = new double[2];\n"
		     + "    inner[0] = q;\n"
		     + "    bias[0] = inner;\n"
		     + "    bias[0][1] = t;\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(inner[b]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}