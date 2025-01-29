package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	private double[] beta;
	private double[] cv$var16$countGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample52 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$b3;
	private double logProbability$output;
	private double logProbability$p;
	private double logProbability$prior;
	private double logProbability$var15;
	private double logProbability$var18;
	private double logProbability$var40;
	private double logProbability$var45;
	private double logProbability$var50;
	private boolean[] observed;
	private boolean[] output;
	private double[] p;
	private int[] prior;
	private boolean setFlag$output = false;
	private boolean setFlag$p = false;
	private boolean setFlag$prior = false;
	private boolean system$gibbsForward = true;

	public MultinomialBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final int get$length() {
		return length;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	@Override
	public final double get$logProbability$b2() {
		return logProbability$b2;
	}

	@Override
	public final double get$logProbability$b3() {
		return logProbability$b3;
	}

	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	@Override
	public final int get$n() {
		return 10;
	}

	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(boolean[] cv$value) {
		observed = cv$value;
	}

	@Override
	public final boolean[] get$output() {
		return output;
	}

	@Override
	public final void set$output(boolean[] cv$value) {
		output = cv$value;
		setFlag$output = true;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample52 = false;
	}

	@Override
	public final double[] get$p() {
		return p;
	}

	@Override
	public final void set$p(double[] cv$value) {
		p = cv$value;
		setFlag$p = true;
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample20 = false;
	}

	@Override
	public final int[] get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(int[] cv$value) {
		prior = cv$value;
		setFlag$prior = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample52 = false;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(p, beta);
			logProbability$var15 = cv$distributionAccumulator;
			logProbability$p = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var15 = logProbability$p;
			logProbability$$model = (logProbability$$model + logProbability$p);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$p);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedFlag$sample17);
		} else {
			logProbability$var18 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)));
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedFlag$sample20);
		} else {
			logProbability$b1 = logProbability$var40;
			logProbability$output = (logProbability$output + logProbability$var40);
			logProbability$$model = (logProbability$$model + logProbability$var40);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)));
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var45 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedFlag$sample20);
		} else {
			logProbability$b2 = logProbability$var45;
			logProbability$output = (logProbability$output + logProbability$var45);
			logProbability$$model = (logProbability$$model + logProbability$var45);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var45);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)));
			logProbability$b3 = cv$sampleAccumulator;
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedFlag$sample20);
		} else {
			logProbability$b3 = logProbability$var50;
			logProbability$output = (logProbability$output + logProbability$var50);
			logProbability$$model = (logProbability$$model + logProbability$var50);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var50);
		}
	}

	private final void sample17() {
		cv$var16$countGlobal[0] = 0.0;
		cv$var16$countGlobal[1] = 0.0;
		cv$var16$countGlobal[2] = 0.0;
		cv$var16$countGlobal[0] = (cv$var16$countGlobal[0] + prior[0]);
		cv$var16$countGlobal[1] = (cv$var16$countGlobal[1] + prior[1]);
		cv$var16$countGlobal[2] = (cv$var16$countGlobal[2] + prior[2]);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var16$countGlobal, p);
	}

	private final void sample20() {
		double cv$originalProbability;
		int cv$nonZeroCount = 0;
		for(int cv$loopIndex = 0; cv$loopIndex < prior.length; cv$loopIndex += 1) {
			if(!(prior[cv$loopIndex] == 0))
				cv$nonZeroCount = (cv$nonZeroCount + 1);
		}
		int cv$sourceIndex = (int)((double)cv$nonZeroCount * DistributionSampling.sampleUniform(RNG$));
		for(int cv$loopIndex = 0; cv$loopIndex <= cv$sourceIndex; cv$loopIndex += 1) {
			if((prior[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		int cv$changeValue = (int)(((double)prior[cv$sourceIndex] * DistributionSampling.sampleUniform(RNG$)) + 1.0);
		int cv$destinationIndex = (int)((double)(prior.length - 1) * DistributionSampling.sampleUniform(RNG$));
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)) + cv$accumulatedProbabilities);
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)) + cv$accumulatedProbabilities);
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		prior[cv$sourceIndex] = (prior[cv$sourceIndex] - cv$changeValue);
		prior[cv$destinationIndex] = (prior[cv$destinationIndex] + cv$changeValue);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
		for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)) + cv$accumulatedProbabilities);
		for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)) + cv$accumulatedProbabilities);
		for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)) + cv$accumulatedProbabilities);
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			prior[cv$sourceIndex] = (prior[cv$sourceIndex] + cv$changeValue);
			prior[cv$destinationIndex] = (prior[cv$destinationIndex] - cv$changeValue);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var16$countGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		beta = new double[3];
		if(!setFlag$p)
			p = new double[3];
		if(!setFlag$prior)
			prior = new int[3];
		if(!setFlag$output)
			output = new boolean[length$observed];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
		if(!fixedFlag$sample42) {
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
				output[i$var39] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / 10));
		}
		if(!fixedFlag$sample47) {
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
				output[i$var44] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / 10));
		}
		if(!fixedFlag$sample52) {
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
				output[i$var49] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / 10));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample20)
				sample20();
		} else {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample17)
				sample17();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		beta[0] = 0.1;
		beta[1] = 0.1;
		beta[2] = 0.1;
		length = length$observed;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$p = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var40 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var45 = 0.0;
		logProbability$b3 = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var50 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model MultinomialBernoulli(boolean[] observed) {\n    double[] beta = {0.1, 0.1, 0.1};\n    double[] p = dirichlet(beta).sample();\n    int n = 10;\n    int[] prior = multinomial(p, n).sample();\n    Bernoulli b1 = new Bernoulli(prior[0]/n);\n    Bernoulli b2 = new Bernoulli(prior[1]/n);\n    Bernoulli b3 = new Bernoulli(prior[2]/n);\n    int length = observed.length;\n    boolean[] output = new boolean[length];\n    for(int i=0; i<length; i+=3)\n        output[i] = b1.sample();\n    for(int i=1; i<length; i+=3)\n        output[i] = b2.sample();\n    for(int i=2; i<length; i+=3)\n        output[i] = b3.sample();\n    output.observe(observed);\n}\n";
	}
}