package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$var29$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample55;
	private double[] logProbability$sample75;
	private double logProbability$var17;
	private double logProbability$var29;
	private double[] logProbability$var53;
	private double[] logProbability$var73;
	private double[][] m;
	private int n;
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
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample75 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample55 = false;
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

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var28], v, 5));
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var17 = logProbability$var29;
			logProbability$m = (logProbability$m + logProbability$var29);
			logProbability$$model = (logProbability$$model + logProbability$var29);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var29);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				int cv$sampleValue = a[i$var46];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[b[i$var46]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var53[(i$var46 - 1)] = cv$distributionAccumulator;
				logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				if((i$var46 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var53[(i$var46 - 1)] = cv$sampleValue;
				if((i$var46 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double var72 = (double)(1 / a[(j + 1)]);
				double cv$distributionAccumulator = Math.log((flips[j]?var72:(1.0 - var72)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var73[j] = cv$distributionAccumulator;
				logProbability$sample75[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample75[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample29(int var28) {
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var29$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if((var28 == b[i$var46]))
				cv$var29$countGlobal[a[i$var46]] = (cv$var29$countGlobal[a[i$var46]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var29$countGlobal, m[var28], 5);
	}

	private final void sample55(int i$var46) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			a[i$var46] = cv$valuePos;
			int index$i$2_1 = (i$var46 + 1);
			if((index$i$2_1 < n))
				b[index$i$2_1] = a[(index$i$2_1 - 1)];
			double cv$accumulatedProbabilities = Math.log(m[b[i$var46]][cv$valuePos]);
			int index$i$3_2 = (i$var46 + 1);
			if((index$i$3_2 < n))
				cv$accumulatedProbabilities = ((((0.0 <= a[index$i$3_2]) && (a[index$i$3_2] < 5))?Math.log(m[cv$valuePos][a[index$i$3_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			double cv$temp$4$var72 = (double)(1 / cv$valuePos);
			cv$accumulatedProbabilities = (Math.log((flips[(i$var46 - 1)]?cv$temp$4$var72:(1.0 - cv$temp$4$var72))) + cv$accumulatedProbabilities);
			cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var54$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var54$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var54$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		a[i$var46] = DistributionSampling.sampleCategorical(RNG$, cv$var54$stateProbabilityGlobal, 5);
		int index$i$10_1 = (i$var46 + 1);
		if((index$i$10_1 < n))
			b[index$i$10_1] = a[(index$i$10_1 - 1)];
	}

	@Override
	public final void allocateScratch() {
		cv$var29$countGlobal = new double[5];
		cv$var54$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!fixedFlag$sample29) {
			m = new double[5][];
			for(int var28 = 0; var28 < 5; var28 += 1)
				m[var28] = new double[5];
		}
		if(!fixedFlag$sample55)
			a = new int[n];
		b = new int[n];
		flips = new boolean[n];
		logProbability$var53 = new double[(n - 1)];
		logProbability$sample55 = new double[(n - 1)];
		logProbability$var73 = new double[n];
		logProbability$sample75 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample29) {
				for(int var28 = 0; var28 < 5; var28 += 1)
					sample29(var28);
			}
			if(!fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
					sample55(i$var46);
			}
		} else {
			if(!fixedFlag$sample55) {
				for(int i$var46 = (n - 1); i$var46 >= 1; i$var46 -= 1)
					sample55(i$var46);
			}
			if(!fixedFlag$sample29) {
				for(int var28 = 4; var28 >= 0; var28 -= 1)
					sample29(var28);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			v[i$var14] = 0.1;
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var17 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = Double.NaN;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			logProbability$var53[(i$var46 - 1)] = Double.NaN;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var73[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample75[j] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			b[i$var46] = a[(i$var46 - 1)];
	}

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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sample();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}