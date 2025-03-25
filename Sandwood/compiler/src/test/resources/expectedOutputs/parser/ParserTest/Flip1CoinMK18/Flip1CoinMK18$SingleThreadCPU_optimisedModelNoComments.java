package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK18$CoreInterface {
	private int a;
	private int b;
	private double[][][] bias;
	private int c;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var10;
	private double logProbability$var16;
	private double logProbability$var97;
	private double q;
	private int samples;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample103 = false;
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
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample103 = false;
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < samples; var96 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var96], bias[a][b][c]));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var97 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedFlag$sample17);
		} else {
			logProbability$bernoulli = logProbability$var97;
			logProbability$flips = (logProbability$flips + logProbability$var97);
			logProbability$$model = (logProbability$$model + logProbability$var97);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$q = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			logProbability$var10 = logProbability$q;
			logProbability$bias = (logProbability$bias + logProbability$q);
			logProbability$$model = (logProbability$$model + logProbability$q);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + logProbability$q);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
			logProbability$var16 = cv$distributionAccumulator;
			logProbability$t = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var16 = logProbability$t;
			logProbability$bias = (logProbability$bias + logProbability$t);
			logProbability$$model = (logProbability$$model + logProbability$t);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$t);
		}
	}

	private final void sample11() {
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
					double traceTempVariable$var84$5_2 = (1 - q);
					for(int var96 = 0; var96 < samples; var96 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$5_2) + cv$accumulatedProbabilities);
				}
				if((1 == a)) {
					if(((0 == b) && (1 == c))) {
						double traceTempVariable$var84$6_2 = (1 - q);
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$6_2) + cv$accumulatedProbabilities);
					}
					if((1 == b)) {
						if((0 == c)) {
							double traceTempVariable$var84$7_2 = (1 - q);
							for(int var96 = 0; var96 < samples; var96 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$7_2) + cv$accumulatedProbabilities);
						}
						if((1 == c)) {
							for(int var96 = 0; var96 < samples; var96 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], q) + cv$accumulatedProbabilities);
						}
					}
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			q = cv$proposedValue;
			bias[0][1][0] = (1 - cv$proposedValue);
			double[][] var52 = bias[1];
			var52[0][1] = (1 - cv$proposedValue);
			double[] var67 = var52[1];
			var67[0] = (1 - cv$proposedValue);
			var67[1] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((((0 == a) && (1 == b)) && (0 == c))) {
				double traceTempVariable$var84$5_2 = (1 - cv$proposedValue);
				for(int var96 = 0; var96 < samples; var96 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$5_2) + cv$accumulatedProbabilities);
			}
			if((1 == a)) {
				if(((0 == b) && (1 == c))) {
					double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
					for(int var96 = 0; var96 < samples; var96 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$6_2) + cv$accumulatedProbabilities);
				}
				if((1 == b)) {
					if((0 == c)) {
						double traceTempVariable$var84$7_2 = (1 - cv$proposedValue);
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$7_2) + cv$accumulatedProbabilities);
					}
					if((1 == c)) {
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], cv$proposedValue) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			q = cv$originalValue;
			bias[0][1][0] = (1 - cv$originalValue);
			double[][] var52 = bias[1];
			var52[0][1] = (1 - cv$originalValue);
			double[] var67 = var52[1];
			var67[0] = (1 - cv$originalValue);
			var67[1] = cv$originalValue;
		}
	}

	private final void sample17() {
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
							for(int var96 = 0; var96 < samples; var96 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], t) + cv$accumulatedProbabilities);
						}
						if((1 == c)) {
							double traceTempVariable$var84$6_2 = (1 - t);
							for(int var96 = 0; var96 < samples; var96 += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$6_2) + cv$accumulatedProbabilities);
						}
					}
					if(((1 == b) && (1 == c))) {
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], t) + cv$accumulatedProbabilities);
					}
				}
				if((((1 == a) && (0 == b)) && (0 == c))) {
					for(int var96 = 0; var96 < samples; var96 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], t) + cv$accumulatedProbabilities);
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			t = cv$proposedValue;
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = cv$proposedValue;
			var23[1] = (1 - cv$proposedValue);
			var21[1][1] = cv$proposedValue;
			bias[1][0][0] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((0 == a)) {
				if((0 == b)) {
					if((0 == c)) {
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], cv$proposedValue) + cv$accumulatedProbabilities);
					}
					if((1 == c)) {
						double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
						for(int var96 = 0; var96 < samples; var96 += 1)
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], traceTempVariable$var84$6_2) + cv$accumulatedProbabilities);
					}
				}
				if(((1 == b) && (1 == c))) {
					for(int var96 = 0; var96 < samples; var96 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], cv$proposedValue) + cv$accumulatedProbabilities);
				}
			}
			if((((1 == a) && (0 == b)) && (0 == c))) {
				for(int var96 = 0; var96 < samples; var96 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var96], cv$proposedValue) + cv$accumulatedProbabilities);
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			t = cv$originalValue;
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = cv$originalValue;
			var23[1] = (1 - cv$originalValue);
			var21[1][1] = cv$originalValue;
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
		flips = new boolean[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
		for(int var96 = 0; var96 < samples; var96 += 1)
			flips[var96] = DistributionSampling.sampleBernoulli(RNG$, bias[a][b][c]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample17)
				sample17();
		} else {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample11)
				sample11();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$q = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103)
			logProbability$var97 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17) {
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(!fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample17) {
			double[][] var21 = bias[0];
			double[] var23 = var21[0];
			var23[0] = t;
			var23[1] = (1 - t);
			double[] var36 = var21[1];
			var36[0] = (1 - q);
			var36[1] = t;
		}
		if(fixedFlag$sample11) {
			double[][] var52 = bias[1];
			double[] var54 = var52[0];
			var54[0] = t;
			var54[1] = (1 - q);
			double[] var67 = var52[1];
			var67[0] = (1 - q);
			var67[1] = q;
		}
	}

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
		     + "public model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}