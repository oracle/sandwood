package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

<<<<<<< Upstream, based on POW
final class Flip1CoinMK14$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK14$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean constrainedFlag$sample8 = true;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample8 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var35;
	private int samples;
	private boolean system$gibbsForward = true;
=======
final class Flip1CoinMK14$SingleThreadCPU extends CoreModelSingleThreadCPU implements Flip1CoinMK14$CoreInterface {
double b;
	double bias;
	double[] c;
	boolean constrainedFlag$sample8 = true;
	boolean fixedFlag$sample8 = false;
	boolean fixedProbFlag$sample37 = false;
	boolean fixedProbFlag$sample8 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	boolean guard1;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$b;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$sample8;
	double logProbability$var35;
	int samples;
	boolean system$gibbsForward = true;
>>>>>>> daee89e Adding in a class to hold just the state. This will be worked on further as the code generation progresses. Commit before adding inner classes to the outer classes. Updating output class structure checkpoint Checkpoint in the restructuring of the output classes to increase the shared code. Finished restructuring the classes, time to start using inner classes. Updates to tree structure Changing the structure of get field so that it can be used to get other types of field, read for getting data out of the scratch and model data classes. Removing unused imports Adding nodes to allow fields in an object ot be set. Moving rng package so that we can add other internal only variable types. Updates to the handling of transformations. Moving from sets to lists of generics Updating the structure of inner class. Changing the passing of fields to sub classes. Updating class structure

	public Flip1CoinMK14$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value, boolean allocated$) {
		b = cv$value;
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample37 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value, boolean allocated$) {
		fixedFlag$sample8 = cv$value;
		constrainedFlag$sample8 = (cv$value || constrainedFlag$sample8);
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
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
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value, boolean allocated$) {
		guard1 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
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
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
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
	public final int get$samples() {
		return samples;
	}

	private final void drawValueSample8() {
		b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1)
			bias = b;
		else {
			c[0] = (b / 2);
			bias = c[0];
		}
	}

	private final void inferSample8() {
		constrainedFlag$sample8 = false;
		double cv$originalValue = b;
		double cv$originalProbability;
		double cv$var = (((b < 0)?(-b):b) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			if(guard1) {
				for(int var34 = 0; var34 < samples; var34 += 1) {
					constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= b) && (b <= 1.0))?Math.log((flips[var34]?b:(1.0 - b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double traceTempVariable$var21$5_2 = (b / 2);
				for(int var34 = 0; var34 < samples; var34 += 1) {
					constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample8) {
			b = cv$proposedValue;
			if(guard1)
				bias = cv$proposedValue;
			else {
				c[0] = (cv$proposedValue / 2);
				bias = c[0];
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if(guard1) {
				for(int var34 = 0; var34 < samples; var34 += 1) {
					constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((flips[var34]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double traceTempVariable$var21$5_2 = (cv$proposedValue / 2);
				for(int var34 = 0; var34 < samples; var34 += 1) {
					constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				b = cv$originalValue;
				if(guard1)
					bias = cv$originalValue;
				else {
					c[0] = (cv$originalValue / 2);
					bias = c[0];
				}
			}
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= bias) && (bias <= 1.0))?Math.log((flips[var34]?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample37 = fixedFlag$sample8;
		} else {
			logProbability$bernoulli = logProbability$var35;
			logProbability$flips = (logProbability$flips + logProbability$var35);
			logProbability$$model = (logProbability$$model + logProbability$var35);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			logProbability$b = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			if(guard1) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			}
			if((!guard1 && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			boolean cv$guard$bias = false;
			if(guard1) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + logProbability$b);
			}
			if((!guard1 && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + logProbability$b);
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	@Override
	public final void allocate() {
		if(!guard1)
			c = new double[1];
		flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
		for(int var34 = 0; var34 < samples; var34 += 1)
			flips[var34] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
		for(int var34 = 0; var34 < samples; var34 += 1)
			flips[var34] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample8)
			inferSample8();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample8)
			drawValueSample8();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$b = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var35 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		samples = length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}