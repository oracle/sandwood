package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	private double[] beta;
	private double[] cv$var19$countGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample64 = false;
	private boolean fixedProbFlag$sample76 = false;
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
	private double logProbability$var18;
	private double logProbability$var21;
	private double logProbability$var50;
	private double logProbability$var62;
	private double logProbability$var74;
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
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
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
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
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
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample64 = false;
		fixedProbFlag$sample76 = false;
	}

	@Override
	public final double[] get$p() {
		return p;
	}

	@Override
	public final void set$p(double[] cv$value) {
		p = cv$value;
		setFlag$p = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample23 = false;
	}

	@Override
	public final int[] get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(int[] cv$value) {
		prior = cv$value;
		setFlag$prior = true;
		fixedProbFlag$sample23 = false;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample64 = false;
		fixedProbFlag$sample76 = false;
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(p, beta);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$p = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var18 = logProbability$p;
			logProbability$$model = (logProbability$$model + logProbability$p);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$p);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
			logProbability$var21 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample20);
		} else {
			logProbability$var21 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var49 = 0; i$var49 < length; i$var49 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[0] / 10)));
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedFlag$sample23);
		} else {
			logProbability$b1 = logProbability$var50;
			logProbability$output = (logProbability$output + logProbability$var50);
			logProbability$$model = (logProbability$$model + logProbability$var50);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var50);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var61 = 1; i$var61 < length; i$var61 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var61], (prior[1] / 10)));
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedFlag$sample23);
		} else {
			logProbability$b2 = logProbability$var62;
			logProbability$output = (logProbability$output + logProbability$var62);
			logProbability$$model = (logProbability$$model + logProbability$var62);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var73 = 2; i$var73 < length; i$var73 += 3)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var73], (prior[2] / 10)));
			logProbability$b3 = cv$sampleAccumulator;
			logProbability$var74 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample76 = (fixedFlag$sample76 && fixedFlag$sample23);
		} else {
			logProbability$b3 = logProbability$var74;
			logProbability$output = (logProbability$output + logProbability$var74);
			logProbability$$model = (logProbability$$model + logProbability$var74);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var74);
		}
	}

	private final void sample20() {
		cv$var19$countGlobal[0] = 0.0;
		cv$var19$countGlobal[1] = 0.0;
		cv$var19$countGlobal[2] = 0.0;
		cv$var19$countGlobal[0] = (cv$var19$countGlobal[0] + prior[0]);
		cv$var19$countGlobal[1] = (cv$var19$countGlobal[1] + prior[1]);
		cv$var19$countGlobal[2] = (cv$var19$countGlobal[2] + prior[2]);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var19$countGlobal, p);
	}

	private final void sample23() {
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
			for(int i$var49 = 0; i$var49 < length; i$var49 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[0] / 10)) + cv$accumulatedProbabilities);
			for(int i$var61 = 1; i$var61 < length; i$var61 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var61], (prior[1] / 10)) + cv$accumulatedProbabilities);
			for(int i$var73 = 2; i$var73 < length; i$var73 += 3)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var73], (prior[2] / 10)) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		prior[cv$sourceIndex] = (prior[cv$sourceIndex] - cv$changeValue);
		prior[cv$destinationIndex] = (prior[cv$destinationIndex] + cv$changeValue);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
		for(int i$var49 = 0; i$var49 < length; i$var49 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[0] / 10)) + cv$accumulatedProbabilities);
		for(int i$var61 = 1; i$var61 < length; i$var61 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var61], (prior[1] / 10)) + cv$accumulatedProbabilities);
		for(int i$var73 = 2; i$var73 < length; i$var73 += 3)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var73], (prior[2] / 10)) + cv$accumulatedProbabilities);
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			prior[cv$sourceIndex] = (prior[cv$sourceIndex] + cv$changeValue);
			prior[cv$destinationIndex] = (prior[cv$destinationIndex] - cv$changeValue);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var19$countGlobal = new double[3];
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
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample23)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
		if(!fixedFlag$sample52) {
			for(int i$var49 = 0; i$var49 < length; i$var49 += 3)
				output[i$var49] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / 10));
		}
		if(!fixedFlag$sample64) {
			for(int i$var61 = 1; i$var61 < length; i$var61 += 3)
				output[i$var61] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / 10));
		}
		if(!fixedFlag$sample76) {
			for(int i$var73 = 2; i$var73 < length; i$var73 += 3)
				output[i$var73] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / 10));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample23)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample23)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample23)
				sample23();
		} else {
			if(!fixedFlag$sample23)
				sample23();
			if(!fixedFlag$sample20)
				sample20();
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
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$p = 0.0;
		logProbability$var21 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var50 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
		logProbability$b3 = 0.0;
		if(!fixedProbFlag$sample76)
			logProbability$var74 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample52();
		logProbabilityValue$sample64();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample23();
		logProbabilityValue$sample52();
		logProbabilityValue$sample64();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample23();
		logProbabilityValue$sample52();
		logProbabilityValue$sample64();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample23)
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
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}