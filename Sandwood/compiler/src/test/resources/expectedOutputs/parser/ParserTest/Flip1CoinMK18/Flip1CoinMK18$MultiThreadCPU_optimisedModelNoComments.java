package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK18$CoreInterface {
	private int a;
	private int b;
	private double[][][] bias;
	private int c;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample85 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample85 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var12;
	private double logProbability$var18;
	private double logProbability$var78;
	private double q;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$MultiThreadCPU(ExecutionTarget target) {
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
	public final double[][][] get$bias() {
		return bias;
	}

	@Override
	public final int get$c() {
		return c;
	}

	@Override
	public final void set$c(int cv$value) {
		c = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample85() {
		return fixedFlag$sample85;
	}

	@Override
	public final void set$fixedFlag$sample85(boolean cv$value) {
		fixedFlag$sample85 = cv$value;
		fixedProbFlag$sample85 = (cv$value && fixedProbFlag$sample85);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
			logProbability$var12 = cv$distributionAccumulator;
			logProbability$q = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			logProbability$var12 = logProbability$q;
			logProbability$bias = (logProbability$bias + logProbability$q);
			logProbability$$model = (logProbability$$model + logProbability$q);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + logProbability$q);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$t = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var18 = logProbability$t;
			logProbability$bias = (logProbability$bias + logProbability$t);
			logProbability$$model = (logProbability$$model + logProbability$t);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$t);
		}
	}

	private final void logProbabilityValue$sample85() {
		if(!fixedProbFlag$sample85) {
			double cv$sampleAccumulator = 0.0;
			for(int var77 = 0; var77 < samples; var77 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var77], bias[a][b][c]));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var78 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample85 = ((fixedFlag$sample85 && fixedFlag$sample14) && fixedFlag$sample20);
		} else {
			logProbability$bernoulli = logProbability$var78;
			logProbability$flips = (logProbability$flips + logProbability$var78);
			logProbability$$model = (logProbability$$model + logProbability$var78);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var78);
		}
	}

	private final void sample14() {
		double cv$originalValue = q;
		double cv$originalProbability;
		double cv$var = ((q * q) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + q);
		double cv$proposedProbability;
		{
			{
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
				if((((0 == a) && (1 == b)) && (0 == c))) {
					double traceTempVariable$var72$5_2 = (1 - q);
					for(int var77 = 0; var77 < samples; var77 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$5_2) + cv$accumulatedProbabilities);
				}
				if((1 == a)) {
					if(((0 == b) && (1 == c))) {
						double traceTempVariable$var72$6_2 = (1 - q);
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$6_2) + cv$accumulatedProbabilities);
					}
					if((1 == b)) {
						if((0 == c)) {
							double traceTempVariable$var72$7_2 = (1 - q);
							for(int var77 = 0; var77 < samples; var77 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$7_2) + cv$accumulatedProbabilities);
						}
						if((1 == c)) {
							for(int var77 = 0; var77 < samples; var77 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], q) + cv$accumulatedProbabilities);
						}
					}
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			q = cv$proposedValue;
			bias[0][1][0] = (1 - cv$proposedValue);
			double[][] var47 = bias[1];
			var47[0][1] = (1 - cv$proposedValue);
			double[] var59 = var47[1];
			var59[0] = (1 - cv$proposedValue);
			var59[1] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((((0 == a) && (1 == b)) && (0 == c))) {
				double traceTempVariable$var72$5_2 = (1 - cv$proposedValue);
				for(int var77 = 0; var77 < samples; var77 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$5_2) + cv$accumulatedProbabilities);
			}
			if((1 == a)) {
				if(((0 == b) && (1 == c))) {
					double traceTempVariable$var72$6_2 = (1 - cv$proposedValue);
					for(int var77 = 0; var77 < samples; var77 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$6_2) + cv$accumulatedProbabilities);
				}
				if((1 == b)) {
					if((0 == c)) {
						double traceTempVariable$var72$7_2 = (1 - cv$proposedValue);
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$7_2) + cv$accumulatedProbabilities);
					}
					if((1 == c)) {
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], cv$proposedValue) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			q = cv$originalValue;
			bias[0][1][0] = (1 - cv$originalValue);
			double[][] var47 = bias[1];
			var47[0][1] = (1 - cv$originalValue);
			double[] var59 = var47[1];
			var59[0] = (1 - cv$originalValue);
			var59[1] = cv$originalValue;
		}
	}

	private final void sample20() {
		double cv$originalValue = t;
		double cv$originalProbability;
		double cv$var = ((t * t) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + t);
		double cv$proposedProbability;
		{
			{
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
				if((0 == a)) {
					if((0 == b)) {
						if((0 == c)) {
							for(int var77 = 0; var77 < samples; var77 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], t) + cv$accumulatedProbabilities);
						}
						if((1 == c)) {
							double traceTempVariable$var72$6_2 = (1 - t);
							for(int var77 = 0; var77 < samples; var77 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$6_2) + cv$accumulatedProbabilities);
						}
					}
					if(((1 == b) && (1 == c))) {
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], t) + cv$accumulatedProbabilities);
					}
				}
				if((((1 == a) && (0 == b)) && (0 == c))) {
					for(int var77 = 0; var77 < samples; var77 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], t) + cv$accumulatedProbabilities);
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			t = cv$proposedValue;
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = cv$proposedValue;
			var25[1] = (1 - cv$proposedValue);
			var23[1][1] = cv$proposedValue;
			bias[1][0][0] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((0 == a)) {
				if((0 == b)) {
					if((0 == c)) {
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], cv$proposedValue) + cv$accumulatedProbabilities);
					}
					if((1 == c)) {
						double traceTempVariable$var72$6_2 = (1 - cv$proposedValue);
						for(int var77 = 0; var77 < samples; var77 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], traceTempVariable$var72$6_2) + cv$accumulatedProbabilities);
					}
				}
				if(((1 == b) && (1 == c))) {
					for(int var77 = 0; var77 < samples; var77 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], cv$proposedValue) + cv$accumulatedProbabilities);
				}
			}
			if((((1 == a) && (0 == b)) && (0 == c))) {
				for(int var77 = 0; var77 < samples; var77 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var77], cv$proposedValue) + cv$accumulatedProbabilities);
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			t = cv$originalValue;
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = cv$originalValue;
			var25[1] = (1 - cv$originalValue);
			var23[1][1] = cv$originalValue;
			bias[1][0][0] = cv$originalValue;
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		bias = new double[2][][];
		double[][] subarray$0 = new double[2][];
		bias[0] = subarray$0;
		subarray$0[0] = new double[2];
		subarray$0[1] = new double[2];
		double[][] subarray$1 = new double[2][];
		bias[1] = subarray$1;
		subarray$1[0] = new double[2];
		subarray$1[1] = new double[2];
		if(!setFlag$flips)
			flips = new boolean[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if((!fixedFlag$sample14 || !fixedFlag$sample20)) {
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = t;
			var25[1] = (1 - t);
			double[] var35 = var23[1];
			var35[0] = (1 - q);
			var35[1] = t;
			double[][] var47 = bias[1];
			double[] var49 = var47[0];
			var49[0] = t;
			var49[1] = (1 - q);
			double[] var59 = var47[1];
			var59[0] = (1 - q);
			var59[1] = q;
		}
		if(!fixedFlag$sample85)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var77, int forEnd$var77, int threadID$var77, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var77 = forStart$var77; var77 < forEnd$var77; var77 += 1)
							flips[var77] = DistributionSampling.sampleBernoulli(RNG$1, bias[a][b][c]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if((!fixedFlag$sample14 || !fixedFlag$sample20)) {
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = t;
			var25[1] = (1 - t);
			double[] var35 = var23[1];
			var35[0] = (1 - q);
			var35[1] = t;
			double[][] var47 = bias[1];
			double[] var49 = var47[0];
			var49[0] = t;
			var49[1] = (1 - q);
			double[] var59 = var47[1];
			var59[0] = (1 - q);
			var59[1] = q;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if((!fixedFlag$sample14 || !fixedFlag$sample20)) {
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = t;
			var25[1] = (1 - t);
			double[] var35 = var23[1];
			var35[0] = (1 - q);
			var35[1] = t;
			double[][] var47 = bias[1];
			double[] var49 = var47[0];
			var49[0] = t;
			var49[1] = (1 - q);
			double[] var59 = var47[1];
			var59[0] = (1 - q);
			var59[1] = q;
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample14)
				sample14();
			if(!fixedFlag$sample20)
				sample20();
		} else {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample14)
				sample14();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$q = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample85)
			logProbability$var78 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if((!fixedFlag$sample14 || !fixedFlag$sample20)) {
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = t;
			var25[1] = (1 - t);
			double[] var35 = var23[1];
			var35[0] = (1 - q);
			var35[1] = t;
			double[][] var47 = bias[1];
			double[] var49 = var47[0];
			var49[0] = t;
			var49[1] = (1 - q);
			double[] var59 = var47[1];
			var59[0] = (1 - q);
			var59[1] = q;
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double[][] var23 = bias[0];
		double[] var25 = var23[0];
		var25[0] = t;
		var25[1] = (1 - t);
		double[] var35 = var23[1];
		var35[0] = (1 - q);
		var35[1] = t;
		double[][] var47 = bias[1];
		double[] var49 = var47[0];
		var49[0] = t;
		var49[1] = (1 - q);
		double[] var59 = var47[1];
		var59[0] = (1 - q);
		var59[1] = q;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n    \n    double q = beta(1,1).sample();\n    double t = beta(1,1).sample();\n    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n    \n    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n    boolean[] flips = bernoulli.sample(samples);\n    \n    flips.observe(flipsMeasured);\n}\n";
	}
}