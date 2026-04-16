package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic$SingleThreadCPU extends CoreModelSingleThreadCPU implements Deterministic$CoreInterface {
int[] a;
	int[] b;
	boolean[] constrainedFlag$sample29;
	boolean[] constrainedFlag$sample55;
	boolean fixedFlag$sample29 = false;
	boolean fixedFlag$sample55 = false;
	boolean fixedProbFlag$sample29 = false;
	boolean fixedProbFlag$sample55 = false;
	boolean fixedProbFlag$sample75 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$a;
	double logProbability$b;
	double logProbability$flips;
	double logProbability$m;
	double[] logProbability$sample55;
	double[] logProbability$sample75;
	double logProbability$var29;
	double[][] m;
	int n;
	int states;
	boolean system$gibbsForward = true;
	double[] v;
	double[] cv$var29$countGlobal;
	double[] cv$var54$stateProbabilityGlobal;

	public Deterministic$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$a() {
		return a;
	}

	@Override
	public final void set$a(int[] cv$value, boolean allocated$) {
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
	public final void set$fixedFlag$sample29(boolean cv$value, boolean allocated$) {
		fixedFlag$sample29 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
				constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		}
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value, boolean allocated$) {
		fixedFlag$sample55 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
				constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
		}
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
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final void set$n(int cv$value, boolean allocated$) {
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

	private final void drawValueSample29(int var28) {
		DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
	}

	private final void drawValueSample55(int i$var46) {
		a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
		int index$i$1_1 = (i$var46 + 1);
		if((index$i$1_1 < n))
			b[index$i$1_1] = a[(index$i$1_1 - 1)];
	}

	private final void inferSample29(int var28) {
		constrainedFlag$sample29[var28] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var29$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(((var28 == b[i$var46]) && (fixedFlag$sample55 || constrainedFlag$sample55[(i$var46 - 1)]))) {
				constrainedFlag$sample29[var28] = true;
				cv$var29$countGlobal[a[i$var46]] = (cv$var29$countGlobal[a[i$var46]] + 1.0);
			}
		}
		if(constrainedFlag$sample29[var28])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var29$countGlobal, m[var28], 5);
	}

	private final void inferSample55(int i$var46) {
		constrainedFlag$sample55[(i$var46 - 1)] = false;
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			a[i$var46] = cv$valuePos;
			int index$i$2_1 = (i$var46 + 1);
			if((index$i$2_1 < n))
				b[index$i$2_1] = a[(index$i$2_1 - 1)];
			double[] var52 = m[b[i$var46]];
			double cv$accumulatedProbabilities = (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$3_2 = (i$var46 + 1);
			if(((index$i$3_2 < n) && (fixedFlag$sample55 || constrainedFlag$sample55[(index$i$3_2 - 1)]))) {
				double[] sc$var52$1 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((((0.0 <= a[index$i$3_2]) && (a[index$i$3_2] < 5)) && (0.0 <= sc$var52$1[a[index$i$3_2]])) && (sc$var52$1[a[index$i$3_2]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			constrainedFlag$sample55[(i$var46 - 1)] = true;
			double var72 = (double)(1 / cv$valuePos);
			cv$accumulatedProbabilities = ((((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[(i$var46 - 1)]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample55[(i$var46 - 1)]) {
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
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var28], v, 5));
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
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
				double[] var52 = m[b[i$var46]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
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
				double cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample75[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample75[j]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
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
		constrainedFlag$sample29 = new boolean[5];
		constrainedFlag$sample55 = new boolean[(n - 1)];
		logProbability$sample55 = new double[(n - 1)];
		logProbability$sample75 = new double[n];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		cv$var29$countGlobal = new double[5];
		cv$var54$stateProbabilityGlobal = new double[5];
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
					inferSample29(var28);
			}
			if(!fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
					inferSample55(i$var46);
			}
		} else {
			if(!fixedFlag$sample55) {
				for(int i$var46 = (n - 1); i$var46 >= 1; i$var46 -= 1)
					inferSample55(i$var46);
			}
			if(!fixedFlag$sample29) {
				for(int var28 = 4; var28 >= 0; var28 -= 1)
					inferSample29(var28);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var28 = 0; var28 < 5; var28 += 1) {
			if(!constrainedFlag$sample29[var28])
				drawValueSample29(var28);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!constrainedFlag$sample55[(i$var46 - 1)])
				drawValueSample55(i$var46);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = Double.NaN;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample75[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			v[i$var14] = 0.1;
		a[0] = 0;
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
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