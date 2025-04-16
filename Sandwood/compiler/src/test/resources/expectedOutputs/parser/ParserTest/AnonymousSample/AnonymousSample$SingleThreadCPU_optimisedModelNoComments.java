package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class AnonymousSample$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements AnonymousSample$CoreInterface {
	private double[] amounts1;
	private double[] amounts2;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$obsAmounts1;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$amounts1;
	private double logProbability$amounts2;
	private double logProbability$mean1;
	private double logProbability$mean2;
	private double logProbability$priorSigma2;
	private double[] logProbability$sample35;
	private double[] logProbability$sample39;
	private double logProbability$var14;
	private double logProbability$var20;
	private double[] logProbability$var34;
	private double[] logProbability$var38;
	private double logProbability$var39;
	private double logProbability$var8;
	private double mean1;
	private double mean2;
	private int n;
	private double[] obsAmounts1;
	private double[] obsAmounts2;
	private double priorSigma2;
	private boolean system$gibbsForward = true;
	private double[] var39;

	public AnonymousSample$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$amounts1() {
		return amounts1;
	}

	@Override
	public final double[] get$amounts2() {
		return amounts2;
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
	}

	@Override
	public final int get$length$obsAmounts1() {
		return length$obsAmounts1;
	}

	@Override
	public final void set$length$obsAmounts1(int cv$value) {
		length$obsAmounts1 = cv$value;
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
	public final double get$logProbability$amounts1() {
		return logProbability$amounts1;
	}

	@Override
	public final double get$logProbability$amounts2() {
		return logProbability$amounts2;
	}

	@Override
	public final double get$logProbability$mean1() {
		return logProbability$mean1;
	}

	@Override
	public final double get$logProbability$mean2() {
		return logProbability$mean2;
	}

	@Override
	public final double get$logProbability$priorSigma2() {
		return logProbability$priorSigma2;
	}

	@Override
	public final double get$mean1() {
		return mean1;
	}

	@Override
	public final void set$mean1(double cv$value) {
		mean1 = cv$value;
		fixedProbFlag$sample15 = false;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final double get$mean2() {
		return mean2;
	}

	@Override
	public final void set$mean2(double cv$value) {
		mean2 = cv$value;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample39 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final double[] get$obsAmounts1() {
		return obsAmounts1;
	}

	@Override
	public final void set$obsAmounts1(double[] cv$value) {
		obsAmounts1 = cv$value;
	}

	@Override
	public final double[] get$obsAmounts2() {
		return obsAmounts2;
	}

	@Override
	public final void set$obsAmounts2(double[] cv$value) {
		obsAmounts2 = cv$value;
	}

	@Override
	public final double get$priorSigma2() {
		return priorSigma2;
	}

	@Override
	public final void set$priorSigma2(double cv$value) {
		priorSigma2 = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample39 = false;
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mean1 - 2000.0) / 100.0)) - 4.605170185988092);
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$mean1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			logProbability$var14 = logProbability$mean1;
			logProbability$$model = (logProbability$$model + logProbability$mean1);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + logProbability$mean1);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mean2 - 2000.0) / 100.0)) - 4.605170185988092);
			logProbability$var20 = cv$distributionAccumulator;
			logProbability$mean2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			logProbability$var20 = logProbability$mean2;
			logProbability$$model = (logProbability$$model + logProbability$mean2);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + logProbability$mean2);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(priorSigma2))) - (Math.log(priorSigma2) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var34[i] = cv$distributionAccumulator;
				logProbability$sample35[i] = cv$distributionAccumulator;
			}
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$rvAccumulator = logProbability$sample35[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[i] = cv$rvAccumulator;
			}
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(priorSigma2))) - (Math.log(priorSigma2) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var38[i] = cv$distributionAccumulator;
				logProbability$sample39[i] = cv$distributionAccumulator;
			}
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = (fixedFlag$sample9 && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$rvAccumulator = logProbability$sample39[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var38[i] = cv$rvAccumulator;
			}
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			logProbability$var8 = cv$distributionAccumulator;
			logProbability$priorSigma2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$var8 = logProbability$priorSigma2;
			logProbability$$model = (logProbability$$model + logProbability$priorSigma2);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$priorSigma2);
		}
	}

	private final void sample15() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < n; i += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + amounts1[i]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		mean1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample21() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < n; i += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + var39[i]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		mean2 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample9() {
		double cv$originalValue = priorSigma2;
		double cv$originalProbability;
		double cv$var = ((priorSigma2 * priorSigma2) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + priorSigma2);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			for(int i = 0; i < n; i += 1)
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(priorSigma2))) + cv$accumulatedProbabilities) - (Math.log(priorSigma2) * 0.5));
			for(int i = 0; i < n; i += 1)
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(priorSigma2))) + cv$accumulatedProbabilities) - (Math.log(priorSigma2) * 0.5));
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		priorSigma2 = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 10000.0) / 30.0)) - 3.4011973816621555);
		for(int i = 0; i < n; i += 1)
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((amounts1[i] - mean1) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		for(int i = 0; i < n; i += 1)
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((var39[i] - mean2) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			priorSigma2 = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		amounts1 = new double[length$obsAmounts1];
		amounts2 = new double[length$obsAmounts1];
		var39 = new double[length$obsAmounts1];
		logProbability$var34 = new double[length$obsAmounts1];
		logProbability$sample35 = new double[length$obsAmounts1];
		logProbability$var38 = new double[length$obsAmounts1];
		logProbability$sample39 = new double[length$obsAmounts1];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		for(int i = 0; i < n; i += 1) {
			amounts1[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean1);
			var39[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean2);
			amounts2[i] = (amounts1[i] + var39[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample21)
				sample21();
		} else {
			if(!fixedFlag$sample21)
				sample21();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample9)
				sample9();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		n = length$obsAmounts1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$priorSigma2 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$mean1 = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$mean2 = 0.0;
		for(int i = 0; i < n; i += 1)
			logProbability$var34[i] = 0.0;
		logProbability$amounts1 = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample35[i] = 0.0;
		}
		for(int i = 0; i < n; i += 1)
			logProbability$var38[i] = 0.0;
		logProbability$var39 = 0.0;
		logProbability$amounts2 = 0.0;
		if(!fixedProbFlag$sample39) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample39[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((DistributionSampling.sampleGaussian(RNG$) * 30.0) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((DistributionSampling.sampleGaussian(RNG$) * 100.0) + 2000.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		{
			int cv$length1 = amounts1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				amounts1[cv$index1] = obsAmounts1[cv$index1];
		}
		int cv$length1 = amounts2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			amounts2[cv$index1] = obsAmounts2[cv$index1];
		for(int i = (n - 1); i >= 0; i -= 1)
			var39[i] = (amounts2[i] - amounts1[i]);
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
		     + "    int n = obsAmounts1.length;\n"
		     + "\n"
		     + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
		     + "\n"
		     + "    double mean1 = gaussian(2000, 10000).sample();\n"
		     + "    double mean2 = gaussian(2000, 10000).sample();\n"
		     + "\n"
		     + "\n"
		     + "    double[] amounts1 = new double[n];\n"
		     + "    double[] amounts2 = new double[n];\n"
		     + "    for(int i : [0..n)) {\n"
		     + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
		     + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    amounts1.observe(obsAmounts1);\n"
		     + "    amounts2.observe(obsAmounts2);\n"
		     + "}";
	}
}