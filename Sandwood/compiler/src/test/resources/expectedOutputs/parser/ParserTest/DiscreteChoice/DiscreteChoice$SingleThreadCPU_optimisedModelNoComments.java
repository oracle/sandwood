package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoice$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] guard$sample24put65$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample24;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var23;
	private double logProbability$var65;
	private double logProbability$var77;
	private int noObs;
	private int noProducts;
	private double[] prob;
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
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
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
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample78 = false;
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[i$var18] / 3.1622776601683795)) - 1.151292546497023);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var23[(i$var18 - 1)] = cv$distributionAccumulator;
				logProbability$sample24[(i$var18 - 1)] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$sampleValue = logProbability$sample24[(i$var18 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var23[(i$var18 - 1)] = cv$sampleValue;
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$sampleAccumulator = 0.0;
			for(int var76 = 0; var76 < noObs; var76 += 1) {
				int cv$sampleValue = choices[var76];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var65 = cv$sampleAccumulator;
			logProbability$var77 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample78 = fixedFlag$sample24;
		} else {
			logProbability$var65 = logProbability$var77;
			logProbability$choices = (logProbability$choices + logProbability$var77);
			logProbability$$model = (logProbability$$model + logProbability$var77);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var77);
		}
	}

	private final void sample24(int i$var18) {
		double cv$originalValue = ut[i$var18];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int var76 = 0; var76 < noObs; var76 += 1)
				cv$accumulatedProbabilities = ((((0.0 <= choices[var76]) && (choices[var76] < noProducts))?Math.log(prob[choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[i$var18] = cv$proposedValue;
		exped[i$var18] = Math.exp(ut[i$var18]);
		double reduceVar$sum$0 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction44Index]);
		sum = reduceVar$sum$0;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			guard$sample24put65$global[i$var61] = false;
		guard$sample24put65$global[i$var18] = false;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(!guard$sample24put65$global[i$var61]) {
				guard$sample24put65$global[i$var61] = true;
				prob[i$var61] = (exped[i$var61] / reduceVar$sum$0);
			}
		}
		if(!guard$sample24put65$global[i$var18]) {
			guard$sample24put65$global[i$var18] = true;
			prob[i$var18] = (exped[i$var18] / reduceVar$sum$0);
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int var76 = 0; var76 < noObs; var76 += 1)
			cv$accumulatedProbabilities = ((((0.0 <= choices[var76]) && (choices[var76] < noProducts))?Math.log(prob[choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[i$var18] = cv$originalValue;
			exped[i$var18] = Math.exp(ut[i$var18]);
			double reduceVar$sum$2 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				reduceVar$sum$2 = (reduceVar$sum$2 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$2;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
				guard$sample24put65$global[i$var61] = false;
			guard$sample24put65$global[i$var18] = false;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
				if(!guard$sample24put65$global[i$var61]) {
					guard$sample24put65$global[i$var61] = true;
					prob[i$var61] = (exped[i$var61] / reduceVar$sum$2);
				}
			}
			if(!guard$sample24put65$global[i$var18]) {
				guard$sample24put65$global[i$var18] = true;
				prob[i$var18] = (exped[i$var18] / reduceVar$sum$2);
			}
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample24put65$global = new boolean[Math.max(0, noProducts)];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample24)
			ut = new double[noProducts];
		exped = new double[noProducts];
		prob = new double[noProducts];
		choices = new int[noObs];
		logProbability$var23 = new double[(noProducts - 1)];
		logProbability$sample24 = new double[(noProducts - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
				exped[i$var36] = Math.exp(ut[i$var36]);
			double reduceVar$sum$3 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				reduceVar$sum$3 = (reduceVar$sum$3 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$3;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
				prob[i$var61] = (exped[i$var61] / reduceVar$sum$3);
		}
		for(int var76 = 0; var76 < noObs; var76 += 1)
			choices[var76] = DistributionSampling.sampleCategorical(RNG$, prob, noProducts);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
			exped[i$var36] = Math.exp(ut[i$var36]);
		double reduceVar$sum$7 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction44Index]);
		sum = reduceVar$sum$7;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			prob[i$var61] = (exped[i$var61] / reduceVar$sum$7);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
			exped[i$var36] = Math.exp(ut[i$var36]);
		double reduceVar$sum$4 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			reduceVar$sum$4 = (reduceVar$sum$4 + exped[cv$reduction44Index]);
		sum = reduceVar$sum$4;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			prob[i$var61] = (exped[i$var61] / reduceVar$sum$4);
		for(int var76 = 0; var76 < noObs; var76 += 1)
			choices[var76] = DistributionSampling.sampleCategorical(RNG$, prob, noProducts);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
				exped[i$var36] = Math.exp(ut[i$var36]);
			double reduceVar$sum$5 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
				reduceVar$sum$5 = (reduceVar$sum$5 + exped[cv$reduction44Index]);
			sum = reduceVar$sum$5;
			for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
				prob[i$var61] = (exped[i$var61] / reduceVar$sum$5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
			exped[i$var36] = Math.exp(ut[i$var36]);
		double reduceVar$sum$6 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction44Index]);
		sum = reduceVar$sum$6;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			prob[i$var61] = (exped[i$var61] / reduceVar$sum$6);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample24) {
			if(system$gibbsForward) {
				for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
					sample24(i$var18);
			} else {
				for(int i$var18 = (noProducts - 1); i$var18 >= 1; i$var18 -= 1)
					sample24(i$var18);
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
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
			logProbability$var23[(i$var18 - 1)] = Double.NaN;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				logProbability$sample24[(i$var18 - 1)] = Double.NaN;
		}
		logProbability$var65 = Double.NaN;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$var77 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1)
			exped[i$var36] = Math.exp(ut[i$var36]);
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1)
			reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction44Index]);
		sum = reduceVar$sum$8;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
			prob[i$var61] = (exped[i$var61] / reduceVar$sum$8);
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
		     + "public model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) {\n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}