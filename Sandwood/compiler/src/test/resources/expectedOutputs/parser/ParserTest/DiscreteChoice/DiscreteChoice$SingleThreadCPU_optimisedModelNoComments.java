package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoice$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] guard$sample19put43$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample19;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var17;
	private double logProbability$var42;
	private double logProbability$var47;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$exped = false;
	private boolean setFlag$prob = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoice$SingleThreadCPU(ExecutionTarget target) {
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
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final void set$exped(double[] cv$value) {
		exped = cv$value;
		setFlag$exped = true;
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
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
	public final void set$prob(double[] cv$value) {
		prob = cv$value;
		setFlag$prob = true;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final void set$sum(double cv$value) {
		sum = cv$value;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(ut[i$var12], 0.0, 10.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var17[(i$var12 - 1)] = cv$distributionAccumulator;
				logProbability$sample19[(i$var12 - 1)] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$sampleValue = logProbability$sample19[(i$var12 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var17[(i$var12 - 1)] = cv$sampleValue;
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityCategorical(choices[var46], prob));
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample19);
		} else {
			logProbability$var42 = logProbability$var47;
			logProbability$choices = (logProbability$choices + logProbability$var47);
			logProbability$$model = (logProbability$$model + logProbability$var47);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	private final void sample19(int i$var12) {
		double cv$originalValue = ut[i$var12];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue, 0.0, 10.0);
			for(int var46 = 0; var46 < noObs; var46 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(choices[var46], prob) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[i$var12] = cv$proposedValue;
		exped[i$var12] = Math.exp(ut[i$var12]);
		double reduceVar$sum$0 = 0.0;
		for(int cv$reduction88Index = 0; cv$reduction88Index < noProducts; cv$reduction88Index += 1)
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction88Index]);
		sum = reduceVar$sum$0;
		for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
			guard$sample19put43$global[i$var38] = false;
		guard$sample19put43$global[i$var12] = false;
		for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
			if(!guard$sample19put43$global[i$var38]) {
				guard$sample19put43$global[i$var38] = true;
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$0);
			}
		}
		if(!guard$sample19put43$global[i$var12]) {
			guard$sample19put43$global[i$var12] = true;
			prob[i$var12] = (exped[i$var12] / reduceVar$sum$0);
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue, 0.0, 10.0);
		for(int var46 = 0; var46 < noObs; var46 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(choices[var46], prob) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[i$var12] = cv$originalValue;
			exped[i$var12] = Math.exp(ut[i$var12]);
			double reduceVar$sum$2 = 0.0;
			for(int cv$reduction196Index = 0; cv$reduction196Index < noProducts; cv$reduction196Index += 1)
				reduceVar$sum$2 = (reduceVar$sum$2 + exped[cv$reduction196Index]);
			sum = reduceVar$sum$2;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				guard$sample19put43$global[i$var38] = false;
			guard$sample19put43$global[i$var12] = false;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
				if(!guard$sample19put43$global[i$var38]) {
					guard$sample19put43$global[i$var38] = true;
					prob[i$var38] = (exped[i$var38] / reduceVar$sum$2);
				}
			}
			if(!guard$sample19put43$global[i$var12]) {
				guard$sample19put43$global[i$var12] = true;
				prob[i$var12] = (exped[i$var12] / reduceVar$sum$2);
			}
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample19put43$global = new boolean[Math.max(0, noProducts)];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut)
			ut = new double[noProducts];
		if(!setFlag$exped)
			exped = new double[noProducts];
		if(!setFlag$prob)
			prob = new double[noProducts];
		if(!setFlag$choices)
			choices = new int[noObs];
		logProbability$var17 = new double[(noProducts - 1)];
		logProbability$sample19 = new double[(noProducts - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			double reduceVar$sum$3 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$3;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$3);
		}
		if(!fixedFlag$sample49) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				choices[var46] = DistributionSampling.sampleCategorical(RNG$, prob);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			double reduceVar$sum$5 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$5;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			double reduceVar$sum$4 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$4;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$4);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample19) {
			if(system$gibbsForward) {
				for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
					sample19(i$var12);
			} else {
				for(int i$var12 = (noProducts - 1); i$var12 >= 1; i$var12 -= 1)
					sample19(i$var12);
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
		for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
			logProbability$var17[(i$var12 - 1)] = 0.0;
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				logProbability$sample19[(i$var12 - 1)] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var47 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				ut[i$var12] = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			double reduceVar$sum$6 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$6;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$6);
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
			for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1)
				exped[i$var23] = Math.exp(ut[i$var23]);
			double reduceVar$sum$7 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1)
				reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction32Index]);
			sum = reduceVar$sum$7;
			for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
				prob[i$var38] = (exped[i$var38] / reduceVar$sum$7);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) {\n        ut[i]= gaussian(0, 10).sample();\n    }\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}