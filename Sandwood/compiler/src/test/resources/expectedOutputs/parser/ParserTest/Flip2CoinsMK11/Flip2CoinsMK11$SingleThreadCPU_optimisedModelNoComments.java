package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK11$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK11$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample49;
	private double[] logProbability$sample77;
	private double logProbability$var22;
	private double logProbability$var9;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK11$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample49 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double[] get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	@Override
	public final double[] get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
	}

	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[i$var21], 1.0, 1.0));
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var22);
			logProbability$bias = (logProbability$bias + logProbability$var22);
			logProbability$$model = (logProbability$$model + logProbability$var22);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < length$flipsMeasured[0]; var48 += 1) {
				double var37 = bias[0];
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[0][var48]?var37:(1.0 - var37))));
			}
			logProbability$bernoulli1[0] = cv$sampleAccumulator;
			logProbability$sample49[0] = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample9 && fixedFlag$sample22);
		} else {
			double cv$rvAccumulator = logProbability$sample49[0];
			logProbability$bernoulli1[0] = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
					double var64 = bias[k];
					cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[k][var75]?var64:(1.0 - var64))));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				logProbability$sample77[(k - 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = (fixedFlag$sample9 && fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = logProbability$sample77[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0);
			logProbability$beta = (logProbability$beta + cv$distributionAccumulator);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var9);
			logProbability$bias = (logProbability$bias + logProbability$var9);
			logProbability$$model = (logProbability$$model + logProbability$var9);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var9);
		}
	}

	private final void sample22(int i$var21) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var75 = 0; var75 < length$flipsMeasured[i$var21]; var75 += 1) {
			cv$count = (cv$count + 1);
			if(flips[i$var21][var75])
				cv$sum = (cv$sum + 1);
		}
		bias[i$var21] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample9() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var48 = 0; var48 < length$flipsMeasured[0]; var48 += 1) {
			cv$count = (cv$count + 1);
			if(flips[0][var48])
				cv$sum = (cv$sum + 1);
		}
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips = new boolean[length$flipsMeasured.length][];
		flips[0] = new boolean[length$flipsMeasured[0]];
		for(int k = 1; k < length$flipsMeasured.length; k += 1)
			flips[k] = new boolean[length$flipsMeasured[k]];
		if((!fixedFlag$sample9 || !fixedFlag$sample22))
			bias = new double[length$flipsMeasured.length];
		logProbability$bernoulli1 = new double[1];
		logProbability$sample49 = new double[1];
		logProbability$bernoulli2 = new double[(length$flipsMeasured.length - 1)];
		logProbability$sample77 = new double[(length$flipsMeasured.length - 1)];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		boolean[] var39 = flips[0];
		for(int var48 = 0; var48 < length$flipsMeasured[0]; var48 += 1)
			var39[var48] = DistributionSampling.sampleBernoulli(RNG$, bias[0]);
		for(int k = 1; k < coins; k += 1) {
			boolean[] var66 = flips[k];
			for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample22) {
				for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
					sample22(i$var21);
			}
		} else {
			if(!fixedFlag$sample22) {
				for(int i$var21 = (coins - 1); i$var21 >= 1; i$var21 -= 1)
					sample22(i$var21);
			}
			if(!fixedFlag$sample9)
				sample9();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$beta = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$var9 = 0.0;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = 0.0;
		logProbability$bernoulli1[0] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$sample49[0] = 0.0;
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[(k - 1)] = 0.0;
		if(!fixedProbFlag$sample77) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample77[(k - 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample22) {
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var88 = (coins - 1); i$var88 >= 0; i$var88 -= 1) {
			boolean[] cv$source1 = flipsMeasured[(coins - (i$var88 + 1))];
			boolean[] cv$target1 = flips[i$var88];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}