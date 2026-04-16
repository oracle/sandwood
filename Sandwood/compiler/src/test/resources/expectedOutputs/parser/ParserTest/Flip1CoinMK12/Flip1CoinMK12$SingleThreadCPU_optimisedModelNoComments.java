package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK12$SingleThreadCPU extends CoreModelSingleThreadCPU implements Flip1CoinMK12$CoreInterface {
double bias;
	boolean constrainedFlag$sample16 = true;
	boolean constrainedFlag$sample28 = true;
	boolean constrainedFlag$sample35 = true;
	boolean fixedFlag$sample16 = false;
	boolean fixedFlag$sample28 = false;
	boolean fixedFlag$sample35 = false;
	boolean fixedProbFlag$sample16 = false;
	boolean fixedProbFlag$sample28 = false;
	boolean fixedProbFlag$sample35 = false;
	boolean fixedProbFlag$sample52 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	boolean guard1;
	int guard2;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$sample16;
	double logProbability$sample28;
	double logProbability$sample35;
	double logProbability$var14;
	double logProbability$var26;
	double logProbability$var33;
	double logProbability$var48;
	int samples;
	boolean system$gibbsForward = true;
	double var14;
	double var26;
	double var33;

	public Flip1CoinMK12$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value, boolean allocated$) {
		fixedFlag$sample16 = cv$value;
		constrainedFlag$sample16 = (cv$value || constrainedFlag$sample16);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
		fixedFlag$sample28 = cv$value;
		constrainedFlag$sample28 = (cv$value || constrainedFlag$sample28);
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value, boolean allocated$) {
		fixedFlag$sample35 = cv$value;
		constrainedFlag$sample35 = (cv$value || constrainedFlag$sample35);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
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
	public final int get$guard2() {
		return guard2;
	}

	@Override
	public final void set$guard2(int cv$value, boolean allocated$) {
		guard2 = cv$value;
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

	@Override
	public final double get$var14() {
		return var14;
	}

	@Override
	public final void set$var14(double cv$value, boolean allocated$) {
		var14 = cv$value;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample52 = false;
	}

	@Override
	public final double get$var26() {
		return var26;
	}

	@Override
	public final void set$var26(double cv$value, boolean allocated$) {
		var26 = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample52 = false;
	}

	@Override
	public final double get$var33() {
		return var33;
	}

	@Override
	public final void set$var33(double cv$value, boolean allocated$) {
		var33 = cv$value;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample52 = false;
	}

	private final void drawValueSample16() {
		var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		bias = var14;
	}

	private final void drawValueSample28() {
		var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
		bias = var26;
	}

	private final void drawValueSample35() {
		var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
		bias = var33;
	}

	private final void inferSample16() {
		constrainedFlag$sample16 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var47 = 0; var47 < samples; var47 += 1) {
			constrainedFlag$sample16 = true;
			cv$count = (cv$count + 1);
			if(flips[var47])
				cv$sum = (cv$sum + 1);
		}
		if(constrainedFlag$sample16) {
			var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			bias = var14;
		}
	}

	private final void inferSample28() {
		constrainedFlag$sample28 = false;
		double cv$originalValue = (var26 * 2);
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1) {
				constrainedFlag$sample28 = true;
				cv$accumulatedProbabilities = ((((0.0 <= var26) && (var26 <= 1.0))?Math.log((flips[var47]?var26:(1.0 - var26))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample28) {
			var26 = (cv$proposedValue / 2);
			bias = var26;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1) {
				constrainedFlag$sample28 = true;
				cv$accumulatedProbabilities = ((((0.0 <= var26) && (var26 <= 1.0))?Math.log((flips[var47]?var26:(1.0 - var26))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				var26 = (cv$originalValue / 2);
				bias = var26;
			}
		}
	}

	private final void inferSample35() {
		constrainedFlag$sample35 = false;
		double cv$originalValue = (var33 * 3);
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1) {
				constrainedFlag$sample35 = true;
				cv$accumulatedProbabilities = ((((0.0 <= var33) && (var33 <= 1.0))?Math.log((flips[var47]?var33:(1.0 - var33))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample35) {
			var33 = (cv$proposedValue / 3);
			bias = var33;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1) {
				constrainedFlag$sample35 = true;
				cv$accumulatedProbabilities = ((((0.0 <= var33) && (var33 <= 1.0))?Math.log((flips[var47]?var33:(1.0 - var33))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				var33 = (cv$originalValue / 3);
				bias = var33;
			}
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(var14, 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$sample16 = cv$distributionAccumulator;
			}
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			if(guard1)
				cv$accumulator = logProbability$sample16;
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var26 * 2), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$sample28 = cv$distributionAccumulator;
			}
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			if(((guard2 <= 2) && !guard1))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2)))
				cv$accumulator = logProbability$sample28;
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			if(((guard2 <= 2) && !guard1))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var33 * 3), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$sample35 = cv$distributionAccumulator;
			}
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			if((!(guard2 <= 2) && !guard1))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2)))
				cv$accumulator = logProbability$sample35;
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			if((!(guard2 <= 2) && !guard1))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < samples; var47 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= bias) && (bias <= 1.0))?Math.log((flips[var47]?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = ((fixedFlag$sample16 && fixedFlag$sample28) && fixedFlag$sample35);
		} else {
			logProbability$bernoulli = logProbability$var48;
			logProbability$flips = (logProbability$flips + logProbability$var48);
			logProbability$$model = (logProbability$$model + logProbability$var48);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	@Override
	public final void allocate() {
		flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
		for(int var47 = 0; var47 < samples; var47 += 1)
			flips[var47] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
		for(int var47 = 0; var47 < samples; var47 += 1)
			flips[var47] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(guard1) {
			if(!fixedFlag$sample16)
				inferSample16();
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					inferSample28();
			} else {
				if(!fixedFlag$sample35)
					inferSample35();
			}
		}
		system$gibbsForward = !system$gibbsForward;
		if(guard1) {
			if(!constrainedFlag$sample16)
				drawValueSample16();
		} else {
			if((guard2 <= 2)) {
				if(!constrainedFlag$sample28)
					drawValueSample28();
			} else {
				if(!constrainedFlag$sample35)
					drawValueSample35();
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var14 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$sample16 = Double.NaN;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$sample28 = Double.NaN;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sample35 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var48 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		samples = length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(fixedFlag$sample28)
					bias = var26;
			} else {
				if(fixedFlag$sample35)
					bias = var33;
			}
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
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