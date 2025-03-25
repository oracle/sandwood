package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTest$CoreInterface {
	private double[] bias;
	private double[] cv$var28$countGlobal;
	private double[] cv$var68$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample69;
	private double[] logProbability$sample84;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double[] logProbability$var67;
	private double[] logProbability$var82;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample84 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
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
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample69 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		fixedProbFlag$sample69 = false;
		fixedProbFlag$sample84 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var16 = logProbability$var28;
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$var32 = logProbability$var44;
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
			double cv$accumulator = 0.0;
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1) {
				int cv$sampleValue = st[i$var62];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[st[(i$var62 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var67[(i$var62 - 1)] = cv$distributionAccumulator;
				logProbability$sample69[(i$var62 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1) {
				double cv$rvAccumulator = logProbability$sample69[(i$var62 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var67[(i$var62 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var82[j] = cv$distributionAccumulator;
				logProbability$sample84[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample84 = (fixedFlag$sample45 && fixedFlag$sample69);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample84[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var82[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27) {
		cv$var28$countGlobal[0] = 0.0;
		cv$var28$countGlobal[1] = 0.0;
		for(int i$var62 = 1; i$var62 < samples; i$var62 += 1) {
			if((var27 == st[(i$var62 - 1)]))
				cv$var28$countGlobal[st[i$var62]] = (cv$var28$countGlobal[st[i$var62]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], 2);
	}

	private final void sample45(int var43) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var43 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample69(int i$var62) {
		{
			st[i$var62] = 0;
			double cv$accumulatedProbabilities = Math.log(m[st[(i$var62 - 1)]][0]);
			int index$i$2_2 = (i$var62 + 1);
			if((index$i$2_2 < samples))
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < 2))?Math.log(m[0][st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i$var62], bias[0]) + cv$accumulatedProbabilities);
			cv$var68$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[i$var62] = 1;
		double cv$accumulatedProbabilities = Math.log(m[st[(i$var62 - 1)]][1]);
		int index$i$2_2 = (i$var62 + 1);
		if((index$i$2_2 < samples))
			cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < 2))?Math.log(m[1][st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i$var62], bias[1]) + cv$accumulatedProbabilities);
		cv$var68$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var68$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var68$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var68$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var68$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var68$stateProbabilityGlobal[0] = 0.5;
			cv$var68$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var68$stateProbabilityGlobal[0] = Math.exp((cv$var68$stateProbabilityGlobal[0] - cv$logSum));
			cv$var68$stateProbabilityGlobal[1] = Math.exp((cv$var68$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var68$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var68$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i$var62] = DistributionSampling.sampleCategorical(RNG$, cv$var68$stateProbabilityGlobal, 2);
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[2];
		cv$var68$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		if(!fixedFlag$sample69)
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		logProbability$var67 = new double[(length$flipsMeasured - 1)];
		logProbability$sample69 = new double[(length$flipsMeasured - 1)];
		logProbability$var82 = new double[length$flipsMeasured];
		logProbability$sample84 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample69) {
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
				st[i$var62] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var62 - 1)]], 2);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample69) {
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
				st[i$var62] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var62 - 1)]], 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample69) {
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
				st[i$var62] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var62 - 1)]], 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				sample28(0);
				sample28(1);
			}
			if(!fixedFlag$sample45) {
				sample45(0);
				sample45(1);
			}
			if(!fixedFlag$sample69) {
				for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
					sample69(i$var62);
			}
		} else {
			if(!fixedFlag$sample69) {
				for(int i$var62 = (samples - 1); i$var62 >= 1; i$var62 -= 1)
					sample69(i$var62);
			}
			if(!fixedFlag$sample45) {
				sample45(1);
				sample45(0);
			}
			if(!fixedFlag$sample28) {
				sample28(1);
				sample28(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
		samples = length$flipsMeasured;
		st[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
			logProbability$var67[(i$var62 - 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample69) {
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
				logProbability$sample69[(i$var62 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var82[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample84) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample84[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		logProbabilityValue$sample84();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample69();
		logProbabilityValue$sample84();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample69();
		logProbabilityValue$sample84();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample69) {
			for(int i$var62 = 1; i$var62 < samples; i$var62 += 1)
				st[i$var62] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var62 - 1)]], 2);
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
		     + "model HMMTest(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "        st[0] = 0;\n"
		     + "                \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "\n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}