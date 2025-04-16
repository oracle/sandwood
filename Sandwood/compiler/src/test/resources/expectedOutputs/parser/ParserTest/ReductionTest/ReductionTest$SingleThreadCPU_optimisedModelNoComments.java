package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private double[] cv$var30$countGlobal;
	private double[] cv$var61$stateProbabilityGlobal;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample62;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double[] logProbability$var60;
	private double[] logProbability$var84;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		fixedFlag$sample62 = cv$value;
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
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
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample62 = false;
	}

	@Override
	public final int get$noCats() {
		return noCats;
	}

	@Override
	public final void set$noCats(int cv$value) {
		noCats = cv$value;
	}

	@Override
	public final int get$noFlips() {
		return noFlips;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noCats; var29 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v, noStates));
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			logProbability$var18 = logProbability$var30;
			logProbability$m = (logProbability$m + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var45], 1.0, 1.0));
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			logProbability$var34 = logProbability$var46;
			logProbability$bias = (logProbability$bias + logProbability$var46);
			logProbability$$model = (logProbability$$model + logProbability$var46);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
				int cv$sampleValue = st[i$var58];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[i$var58][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var60[i$var58] = cv$distributionAccumulator;
				logProbability$sample62[i$var58] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedFlag$sample30);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
				double cv$rvAccumulator = logProbability$sample62[i$var58];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var60[i$var58] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				int reduceVar$var82$2 = 0;
				for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1)
					reduceVar$var82$2 = (reduceVar$var82$2 + st[cv$reduction78Index]);
				double var83 = bias[reduceVar$var82$2];
				double cv$distributionAccumulator = Math.log((flips[j$var73]?var83:(1.0 - var83)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var84[j$var73] = cv$distributionAccumulator;
				logProbability$sample87[j$var73] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = (fixedFlag$sample47 && fixedFlag$sample62);
		} else {
			double cv$accumulator = 0.0;
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				double cv$rvAccumulator = logProbability$sample87[j$var73];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var84[j$var73] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample30(int var29) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var30$countGlobal[cv$loopIndex] = 0.0;
		cv$var30$countGlobal[st[var29]] = (cv$var30$countGlobal[st[var29]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var30$countGlobal, m[var29], noStates);
	}

	private final void sample47(int var45) {
		int cv$sum = 0;
		int cv$count = 0;
		int reduceVar$var82$0 = 0;
		for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1)
			reduceVar$var82$0 = (reduceVar$var82$0 + st[cv$reduction78Index]);
		if((var45 == reduceVar$var82$0)) {
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				cv$count = (cv$count + 1);
				if(flips[j$var73])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample62(int i$var58) {
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			st[i$var58] = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[i$var58][cv$valuePos]):Double.NEGATIVE_INFINITY);
			int reduceVar$var82$1 = 0;
			for(int cv$reduction222Index = 0; cv$reduction222Index < i$var58; cv$reduction222Index += 1)
				reduceVar$var82$1 = (reduceVar$var82$1 + st[cv$reduction222Index]);
			for(int cv$reduction222Index = (i$var58 + 1); cv$reduction222Index < noCats; cv$reduction222Index += 1)
				reduceVar$var82$1 = (reduceVar$var82$1 + st[cv$reduction222Index]);
			reduceVar$var82$1 = (cv$valuePos + reduceVar$var82$1);
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				double cv$temp$2$var83 = bias[reduceVar$var82$1];
				cv$accumulatedProbabilities = (Math.log((flips[j$var73]?cv$temp$2$var83:(1.0 - cv$temp$2$var83))) + cv$accumulatedProbabilities);
			}
			cv$var61$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var61$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var61$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var61$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var61$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var61$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var61$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var61$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var61$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i$var58] = DistributionSampling.sampleCategorical(RNG$, cv$var61$stateProbabilityGlobal, cv$numNumStates);
	}

	@Override
	public final void allocateScratch() {
		cv$var30$countGlobal = new double[((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):flipsMeasured.length))];
		cv$var61$stateProbabilityGlobal = new double[((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):flipsMeasured.length))];
	}

	@Override
	public final void allocator() {
		v = new double[(length$flipsMeasured / noCats)];
		if(!fixedFlag$sample30) {
			m = new double[noCats][];
			for(int var29 = 0; var29 < noCats; var29 += 1)
				m[var29] = new double[(length$flipsMeasured / noCats)];
		}
		if(!fixedFlag$sample47)
			bias = new double[length$flipsMeasured];
		if(!fixedFlag$sample62)
			st = new int[noCats];
		flips = new boolean[length$flipsMeasured];
		logProbability$var60 = new double[noCats];
		logProbability$sample62 = new double[noCats];
		logProbability$var84 = new double[length$flipsMeasured];
		logProbability$sample87 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noCats; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				st[i$var58] = DistributionSampling.sampleCategorical(RNG$, m[i$var58], noStates);
		}
		for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
			int reduceVar$var82$3 = 0;
			for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1)
				reduceVar$var82$3 = (reduceVar$var82$3 + st[cv$reduction78Index]);
			flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$, bias[reduceVar$var82$3]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noCats; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				st[i$var58] = DistributionSampling.sampleCategorical(RNG$, m[i$var58], noStates);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noCats; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				st[i$var58] = DistributionSampling.sampleCategorical(RNG$, m[i$var58], noStates);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample30) {
				for(int var29 = 0; var29 < noCats; var29 += 1)
					sample30(var29);
			}
			if(!fixedFlag$sample47) {
				for(int var45 = 0; var45 < noFlips; var45 += 1)
					sample47(var45);
			}
			if(!fixedFlag$sample62) {
				for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
					sample62(i$var58);
			}
		} else {
			if(!fixedFlag$sample62) {
				for(int i$var58 = (noCats - 1); i$var58 >= 0; i$var58 -= 1)
					sample62(i$var58);
			}
			if(!fixedFlag$sample47) {
				for(int var45 = (noFlips - 1); var45 >= 0; var45 -= 1)
					sample47(var45);
			}
			if(!fixedFlag$sample30) {
				for(int var29 = (noCats - 1); var29 >= 0; var29 -= 1)
					sample30(var29);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		for(int i$var15 = 0; i$var15 < (length$flipsMeasured / noCats); i$var15 += 1)
			v[i$var15] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
			logProbability$var60[i$var58] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				logProbability$sample62[i$var58] = 0.0;
		}
		for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1)
			logProbability$var84[j$var73] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1)
				logProbability$sample87[j$var73] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noCats; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				st[i$var58] = DistributionSampling.sampleCategorical(RNG$, m[i$var58], noStates);
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
		     + "model ReductionTest(boolean[] flipsMeasured, int noCats) {\n"
		     + "    int noFlips = flipsMeasured.length;\n"
		     + "    int noStates = noFlips/noCats;\n"
		     + "    \n"
		     + "    double[] v = new double[noStates];\n"
		     + "    for(int i:[0..noStates))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(noCats);\n"
		     + "    \n"
		     + "    double[] bias = beta(1.0, 1.0).sample(noFlips);\n"
		     + "    \n"
		     + "    int[] st = new int[noCats];\n"
		     + "\n"
		     + "\n"
		     + "    for(int i:[0..noCats))\n"
		     + "        st[i] = categorical(m[i]).sample();\n"
		     + "            \n"
		     + "    boolean[] flips = new boolean[noFlips];\n"
		     + "            \n"
		     + "    for(int j:[0..noFlips))\n"
		     + "        flips[j] = bernoulli(bias[sum(st)]).sample();\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "    \n"
		     + "    private int sum(int[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}