package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceAlt$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoiceAlt$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample81 = false;
	private boolean[] guard$sample27put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample27;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var25;
	private double logProbability$var67;
	private double logProbability$var79;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceAlt$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	@Override
	public final void set$ObsChoices(int[] cv$value) {
		ObsChoices = cv$value;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final void set$choices(int[] cv$value) {
		choices = cv$value;
		setFlag$choices = true;
		fixedProbFlag$sample81 = false;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
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
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	@Override
	public final int get$noObs() {
		return noObs;
	}

	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	@Override
	public final double[] get$prob() {
		return prob;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample27 = false;
		fixedProbFlag$sample81 = false;
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[i$var20] / 3.1622776601683795)) - 1.151292546497023);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var25[(i$var20 - 1)] = cv$distributionAccumulator;
				logProbability$sample27[(i$var20 - 1)] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1) {
				double cv$sampleValue = logProbability$sample27[(i$var20 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var25[(i$var20 - 1)] = cv$sampleValue;
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$sampleAccumulator = 0.0;
			for(int var78 = 0; var78 < noObs; var78 += 1) {
				int cv$sampleValue = choices[var78];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob.length))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var67 = cv$sampleAccumulator;
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedFlag$sample27);
		} else {
			logProbability$var67 = logProbability$var79;
			logProbability$choices = (logProbability$choices + logProbability$var79);
			logProbability$$model = (logProbability$$model + logProbability$var79);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var79);
		}
	}

	private final void sample27(int i$var20) {
		double cv$originalValue = ut[i$var20];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int var78 = 0; var78 < noObs; var78 += 1)
				cv$accumulatedProbabilities = ((((0.0 <= choices[var78]) && (choices[var78] < prob.length))?Math.log(prob[choices[var78]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[i$var20] = cv$proposedValue;
		exped[i$var20] = Math.exp(ut[i$var20]);
		double reduceVar$sum$0 = 0.0;
		for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction47Index]);
		sum = reduceVar$sum$0;
		for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
			guard$sample27put68$global[i$var63] = false;
		guard$sample27put68$global[i$var20] = false;
		for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
			if(!guard$sample27put68$global[i$var63]) {
				guard$sample27put68$global[i$var63] = true;
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$0);
			}
		}
		if(!guard$sample27put68$global[i$var20]) {
			guard$sample27put68$global[i$var20] = true;
			prob[i$var20] = (exped[i$var20] / reduceVar$sum$0);
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int var78 = 0; var78 < noObs; var78 += 1)
			cv$accumulatedProbabilities = ((((0.0 <= choices[var78]) && (choices[var78] < prob.length))?Math.log(prob[choices[var78]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[i$var20] = cv$originalValue;
			exped[i$var20] = Math.exp(ut[i$var20]);
			double reduceVar$sum$2 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$2 = (reduceVar$sum$2 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$2;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				guard$sample27put68$global[i$var63] = false;
			guard$sample27put68$global[i$var20] = false;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
				if(!guard$sample27put68$global[i$var63]) {
					guard$sample27put68$global[i$var63] = true;
					prob[i$var63] = (exped[i$var63] / reduceVar$sum$2);
				}
			}
			if(!guard$sample27put68$global[i$var20]) {
				guard$sample27put68$global[i$var20] = true;
				prob[i$var20] = (exped[i$var20] / reduceVar$sum$2);
			}
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample27put68$global = new boolean[Math.max(0, noProducts)];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut)
			ut = new double[noProducts];
		exped = new double[noProducts];
		prob = new double[noProducts];
		if(!setFlag$choices)
			choices = new int[noObs];
		logProbability$var25 = new double[(noProducts - 1)];
		logProbability$sample27 = new double[(noProducts - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				ut[i$var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				exped[i$var38] = Math.exp(ut[i$var38]);
			double reduceVar$sum$3 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$3;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$3);
		}
		if(!fixedFlag$sample81) {
			for(int var78 = 0; var78 < noObs; var78 += 1)
				choices[var78] = DistributionSampling.sampleCategorical(RNG$, prob);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				ut[i$var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				exped[i$var38] = Math.exp(ut[i$var38]);
			double reduceVar$sum$5 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$5;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				ut[i$var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				exped[i$var38] = Math.exp(ut[i$var38]);
			double reduceVar$sum$4 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$4;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$4);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample27) {
			if(system$gibbsForward) {
				for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
					sample27(i$var20);
			} else {
				for(int i$var20 = (noProducts - 1); i$var20 >= 1; i$var20 -= 1)
					sample27(i$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
			logProbability$var25[(i$var20 - 1)] = 0.0;
		logProbability$prob = 0.0;
		logProbability$sum = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				logProbability$sample27[(i$var20 - 1)] = 0.0;
		}
		logProbability$var67 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$var79 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				ut[i$var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				exped[i$var38] = Math.exp(ut[i$var38]);
			double reduceVar$sum$6 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$6;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$6);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$ut) {
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				exped[i$var38] = Math.exp(ut[i$var38]);
			double reduceVar$sum$7 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1)
				reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction47Index]);
			sum = reduceVar$sum$7;
			for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
				prob[i$var63] = (exped[i$var63] / reduceVar$sum$7);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) \n        ut[i]= gaussian(0, 10).sample();\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}