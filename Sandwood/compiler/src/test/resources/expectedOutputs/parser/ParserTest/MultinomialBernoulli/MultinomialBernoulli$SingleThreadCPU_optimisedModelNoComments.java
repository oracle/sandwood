package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	private double[] beta;
	private double[] cv$var17$countGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample72 = false;
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
	private double logProbability$var16;
	private double logProbability$var19;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var72;
	private boolean[] observed;
	private boolean[] output;
	private double[] p;
	private int[] prior;
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
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
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
	public final double[] get$p() {
		return p;
	}

	@Override
	public final void set$p(double[] cv$value) {
		p = cv$value;
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
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample60 = false;
		fixedProbFlag$sample72 = false;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(p, beta, 3);
			logProbability$var16 = cv$distributionAccumulator;
			logProbability$p = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var16 = logProbability$p;
			logProbability$$model = (logProbability$$model + logProbability$p);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$p);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(prior, p, 3, 10);
			logProbability$var19 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedFlag$sample17);
		} else {
			logProbability$var19 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
				double var24 = (double)(prior[0] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((output[i$var47]?var24:(1.0 - var24))));
			}
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample48 = fixedFlag$sample20;
		} else {
			logProbability$b1 = logProbability$var48;
			logProbability$output = (logProbability$output + logProbability$var48);
			logProbability$$model = (logProbability$$model + logProbability$var48);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!fixedProbFlag$sample60) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
				double var29 = (double)(prior[1] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((output[i$var59]?var29:(1.0 - var29))));
			}
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var60 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample60 = fixedFlag$sample20;
		} else {
			logProbability$b2 = logProbability$var60;
			logProbability$output = (logProbability$output + logProbability$var60);
			logProbability$$model = (logProbability$$model + logProbability$var60);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var60);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
				double var34 = (double)(prior[2] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((output[i$var71]?var34:(1.0 - var34))));
			}
			logProbability$b3 = cv$sampleAccumulator;
			logProbability$var72 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample72 = fixedFlag$sample20;
		} else {
			logProbability$b3 = logProbability$var72;
			logProbability$output = (logProbability$output + logProbability$var72);
			logProbability$$model = (logProbability$$model + logProbability$var72);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var72);
		}
	}

	private final void sample17() {
		cv$var17$countGlobal[0] = 0.0;
		cv$var17$countGlobal[1] = 0.0;
		cv$var17$countGlobal[2] = 0.0;
		cv$var17$countGlobal[0] = (cv$var17$countGlobal[0] + prior[0]);
		cv$var17$countGlobal[1] = (cv$var17$countGlobal[1] + prior[1]);
		cv$var17$countGlobal[2] = (cv$var17$countGlobal[2] + prior[2]);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var17$countGlobal, p, 3);
	}

	private final void sample20() {
		double cv$originalProbability;
		int cv$nonZeroCount = 0;
		if(!(prior[0] == 0))
			cv$nonZeroCount = 1;
		if(!(prior[1] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		if(!(prior[2] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		int cv$sourceIndex = (int)((double)cv$nonZeroCount * DistributionSampling.sampleUniform(RNG$));
		for(int cv$loopIndex = 0; cv$loopIndex <= cv$sourceIndex; cv$loopIndex += 1) {
			if((prior[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		int cv$changeValue = (int)(((double)prior[cv$sourceIndex] * DistributionSampling.sampleUniform(RNG$)) + 1.0);
		int cv$destinationIndex = (int)(DistributionSampling.sampleUniform(RNG$) * 2.0);
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 3, 10);
			for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
				double cv$temp$3$var24 = (double)(prior[0] / 10);
				cv$accumulatedProbabilities = (Math.log((output[i$var47]?cv$temp$3$var24:(1.0 - cv$temp$3$var24))) + cv$accumulatedProbabilities);
			}
			for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
				double cv$temp$4$var29 = (double)(prior[1] / 10);
				cv$accumulatedProbabilities = (Math.log((output[i$var59]?cv$temp$4$var29:(1.0 - cv$temp$4$var29))) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
				double cv$temp$5$var34 = (double)(prior[2] / 10);
				cv$accumulatedProbabilities = (Math.log((output[i$var71]?cv$temp$5$var34:(1.0 - cv$temp$5$var34))) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		prior[cv$sourceIndex] = (prior[cv$sourceIndex] - cv$changeValue);
		prior[cv$destinationIndex] = (prior[cv$destinationIndex] + cv$changeValue);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 3, 10);
		for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
			double cv$temp$3$var24 = (double)(prior[0] / 10);
			cv$accumulatedProbabilities = (Math.log((output[i$var47]?cv$temp$3$var24:(1.0 - cv$temp$3$var24))) + cv$accumulatedProbabilities);
		}
		for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
			double cv$temp$4$var29 = (double)(prior[1] / 10);
			cv$accumulatedProbabilities = (Math.log((output[i$var59]?cv$temp$4$var29:(1.0 - cv$temp$4$var29))) + cv$accumulatedProbabilities);
		}
		for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
			double cv$temp$5$var34 = (double)(prior[2] / 10);
			cv$accumulatedProbabilities = (Math.log((output[i$var71]?cv$temp$5$var34:(1.0 - cv$temp$5$var34))) + cv$accumulatedProbabilities);
		}
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			prior[cv$sourceIndex] = (prior[cv$sourceIndex] + cv$changeValue);
			prior[cv$destinationIndex] = (prior[cv$destinationIndex] - cv$changeValue);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var17$countGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		beta = new double[3];
		if(!fixedFlag$sample17)
			p = new double[3];
		if(!fixedFlag$sample20)
			prior = new int[3];
		output = new boolean[length$observed];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, 10, prior);
		for(int i$var47 = 0; i$var47 < length; i$var47 += 3)
			output[i$var47] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / 10));
		for(int i$var59 = 1; i$var59 < length; i$var59 += 3)
			output[i$var59] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / 10));
		for(int i$var71 = 2; i$var71 < length; i$var71 += 3)
			output[i$var71] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / 10));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, 10, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, 10, prior);
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
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$p = Double.NaN;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = Double.NaN;
		logProbability$b1 = Double.NaN;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var48 = Double.NaN;
		logProbability$b2 = Double.NaN;
		if(!fixedProbFlag$sample60)
			logProbability$var60 = Double.NaN;
		logProbability$b3 = Double.NaN;
		if(!fixedProbFlag$sample72)
			logProbability$var72 = Double.NaN;
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
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, 10, prior);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
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