package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic$CoreInterface {
	private int[] a;
	private int[] b;
	private double[][] cv$var17$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample36;
	private double[] logProbability$sample49;
	private double logProbability$var12;
	private double logProbability$var17;
	private double[] logProbability$var33;
	private double[] logProbability$var46;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$b = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic$MultiThreadCPU(ExecutionTarget target) {
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
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final void set$b(int[] cv$value) {
		b = cv$value;
		setFlag$b = true;
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
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

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < 5; var16 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var16], v));
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var12 = logProbability$var17;
			logProbability$m = (logProbability$m + logProbability$var17);
			logProbability$$model = (logProbability$$model + logProbability$var17);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var17);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				int cv$sampleValue = a[i$var26];
				double[] var32 = m[b[i$var26]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var33[(i$var26 - 1)] = cv$distributionAccumulator;
				logProbability$sample36[(i$var26 - 1)] = cv$distributionAccumulator;
				if((i$var26 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample36[(i$var26 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var33[(i$var26 - 1)] = cv$sampleValue;
				if((i$var26 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var46[j] = cv$distributionAccumulator;
				logProbability$sample49[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample49[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int var16, int threadID$cv$var16, Rng RNG$) {
		double[] cv$countLocal = cv$var17$countGlobal[threadID$cv$var16];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if((var16 == b[i$var26]))
				cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var16]);
	}

	private final void sample36(int i$var26) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			a[i$var26] = cv$valuePos;
			int index$i$1_1 = (i$var26 + 1);
			if((index$i$1_1 < n))
				b[index$i$1_1] = a[(index$i$1_1 - 1)];
			double[] cv$temp$0$var32 = m[b[i$var26]];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var32.length)?Math.log(cv$temp$0$var32[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var26 + 1);
			if((index$i$2_2 < n)) {
				double[] var32 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= a[index$i$2_2]) && (a[index$i$2_2] < var32.length))?Math.log(var32[a[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var26 - 1)], (1 / cv$valuePos)) + cv$accumulatedProbabilities);
			cv$var34$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var34$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var34$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var34$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var34$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var34$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var34$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var34$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		a[i$var26] = DistributionSampling.sampleCategorical(RNG$, cv$var34$stateProbabilityGlobal);
		int index$i$8_1 = (i$var26 + 1);
		if((index$i$8_1 < n))
			b[index$i$8_1] = a[(index$i$8_1 - 1)];
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var17$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var17$countGlobal[cv$index] = new double[5];
		cv$var34$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!setFlag$m) {
			m = new double[5][];
			for(int var16 = 0; var16 < 5; var16 += 1)
				m[var16] = new double[5];
		}
		if(!setFlag$a)
			a = new int[n];
		if(!setFlag$b)
			b = new int[n];
		if(!setFlag$flips)
			flips = new boolean[n];
		logProbability$var33 = new double[(n - 1)];
		logProbability$sample36 = new double[(n - 1)];
		logProbability$var46 = new double[n];
		logProbability$sample49 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
		if(!fixedFlag$sample49)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample18)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample18(var16, threadID$var16, RNG$1);
					}
				);

			if(!fixedFlag$sample36) {
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
					sample36(i$var26);
			}
		} else {
			if(!fixedFlag$sample36) {
				for(int i$var26 = (n - 1); i$var26 >= 1; i$var26 -= 1)
					sample36(i$var26);
			}
			if(!fixedFlag$sample18)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample18(var16, threadID$var16, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var9, int forEnd$i$var9, int threadID$i$var9, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var9 = forStart$i$var9; i$var9 < forEnd$i$var9; i$var9 += 1)
						v[i$var9] = 0.1;
			}
		);
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var17 = 0.0;
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			logProbability$var33[(i$var26 - 1)] = 0.0;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				logProbability$sample36[(i$var26 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var46[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample49[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
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
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				b[i$var26] = a[(i$var26 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sample();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}