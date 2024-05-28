package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$var31$countGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample58;
	private double[] logProbability$sample78;
	private double logProbability$var19;
	private double logProbability$var31;
	private double[] logProbability$var55;
	private double[] logProbability$var75;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$a() {
		return a;
	}

	@Override
	public final void set$a(int[] cv$value) {
		a = cv$value;
		setFlag$a = true;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample78 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample78 = false;
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
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample58 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final void set$n(int cv$value) {
		n = cv$value;
	}

	@Override
	public final int get$states() {
		return 5;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$sampleAccumulator = 0.0;
			for(int var30 = 0; var30 < 5; var30 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var30], v));
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$var31 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			logProbability$var19 = logProbability$var31;
			logProbability$m = (logProbability$m + logProbability$var31);
			logProbability$$model = (logProbability$$model + logProbability$var31);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				int cv$sampleValue = a[i$var48];
				double[] var54 = m[b[i$var48]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var55[(i$var48 - 1)] = cv$distributionAccumulator;
				logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
				if((i$var48 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample32);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var55[(i$var48 - 1)] = cv$sampleValue;
				if((i$var48 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var75[j] = cv$distributionAccumulator;
				logProbability$sample78[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample78[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var75[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int var30) {
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var31$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
			if((var30 == b[i$var48]))
				cv$var31$countGlobal[a[i$var48]] = (cv$var31$countGlobal[a[i$var48]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var31$countGlobal, m[var30]);
	}

	private final void sample58(int i$var48) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			a[i$var48] = cv$valuePos;
			int index$i$1_1 = (i$var48 + 1);
			if((index$i$1_1 < n))
				b[index$i$1_1] = a[(index$i$1_1 - 1)];
			double[] cv$temp$0$var54 = m[b[i$var48]];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var54.length)?Math.log(cv$temp$0$var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var48 + 1);
			if((index$i$2_2 < n)) {
				double[] var54 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= a[index$i$2_2]) && (a[index$i$2_2] < var54.length))?Math.log(var54[a[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var48 - 1)], (1 / cv$valuePos)) + cv$accumulatedProbabilities);
			cv$var56$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var56$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var56$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var56$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var56$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		a[i$var48] = DistributionSampling.sampleCategorical(RNG$, cv$var56$stateProbabilityGlobal);
		int index$i$8_1 = (i$var48 + 1);
		if((index$i$8_1 < n))
			b[index$i$8_1] = a[(index$i$8_1 - 1)];
	}

	@Override
	public final void allocateScratch() {
		cv$var31$countGlobal = new double[5];
		cv$var56$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!setFlag$m) {
			m = new double[5][];
			for(int var30 = 0; var30 < 5; var30 += 1)
				m[var30] = new double[5];
		}
		if(!setFlag$a)
			a = new int[n];
		b = new int[n];
		if(!setFlag$flips)
			flips = new boolean[n];
		logProbability$var55 = new double[(n - 1)];
		logProbability$sample58 = new double[(n - 1)];
		logProbability$var75 = new double[n];
		logProbability$sample78 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
		if(!fixedFlag$sample78) {
			for(int j = 0; j < n; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample32) {
				for(int var30 = 0; var30 < 5; var30 += 1)
					sample32(var30);
			}
			if(!fixedFlag$sample58) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
					sample58(i$var48);
			}
		} else {
			if(!fixedFlag$sample58) {
				for(int i$var48 = (n - 1); i$var48 >= 1; i$var48 -= 1)
					sample58(i$var48);
			}
			if(!fixedFlag$sample32) {
				for(int var30 = 4; var30 >= 0; var30 -= 1)
					sample32(var30);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var16 = 0; i$var16 < 5; i$var16 += 1)
			v[i$var16] = 0.1;
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var19 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var31 = 0.0;
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
			logProbability$var55[(i$var48 - 1)] = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				logProbability$sample58[(i$var48 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var75[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample78) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample78[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
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
		if(setFlag$a) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				b[i$var48] = a[(i$var48 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sample();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}