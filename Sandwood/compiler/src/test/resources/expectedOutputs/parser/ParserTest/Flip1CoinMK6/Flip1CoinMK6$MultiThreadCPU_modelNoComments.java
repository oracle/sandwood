package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK6$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[] flips1;
	private boolean[] flips2;
	private boolean[] flipsMeasured1;
	private boolean[] flipsMeasured2;
	private int length$flipsMeasured1;
	private int length$flipsMeasured2;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips1;
	private double logProbability$flips2;
	private double logProbability$var22;
	private double logProbability$var35;
	private double logProbability$var8;
	private int samples1;
	private int samples2;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		fixedProbFlag$sample22 = (fixedFlag$sample9 && fixedProbFlag$sample22);
		fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean[] get$flips1() {
		return flips1;
	}

	@Override
	public final boolean[] get$flips2() {
		return flips2;
	}

	@Override
	public final boolean[] get$flipsMeasured1() {
		return flipsMeasured1;
	}

	@Override
	public final void set$flipsMeasured1(boolean[] cv$value) {
		flipsMeasured1 = cv$value;
	}

	@Override
	public final boolean[] get$flipsMeasured2() {
		return flipsMeasured2;
	}

	@Override
	public final void set$flipsMeasured2(boolean[] cv$value) {
		flipsMeasured2 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured1() {
		return length$flipsMeasured1;
	}

	@Override
	public final void set$length$flipsMeasured1(int cv$value) {
		length$flipsMeasured1 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured2() {
		return length$flipsMeasured2;
	}

	@Override
	public final void set$length$flipsMeasured2(int cv$value) {
		length$flipsMeasured2 = cv$value;
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
	public final double get$logProbability$flips1() {
		return logProbability$flips1;
	}

	@Override
	public final double get$logProbability$flips2() {
		return logProbability$flips2;
	}

	@Override
	public final int get$samples1() {
		return samples1;
	}

	@Override
	public final int get$samples2() {
		return samples2;
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var21 = 0; var21 < samples1; var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips1[var21];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?bias:(1.0 - bias))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(Double.isNaN(logProbability$bernoulli))
				logProbability$bernoulli = cv$sampleAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var21 = 0; var21 < samples1; var21 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(Double.isNaN(logProbability$bernoulli))
				logProbability$bernoulli = cv$rvAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$rvAccumulator);
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples2; var34 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips2[var34];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?bias:(1.0 - bias))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			if(Double.isNaN(logProbability$bernoulli))
				logProbability$bernoulli = cv$sampleAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$sampleAccumulator);
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples2; var34 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(Double.isNaN(logProbability$bernoulli))
				logProbability$bernoulli = cv$rvAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$rvAccumulator);
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias;
				{
					{
						double var5 = 1.0;
						double var7 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var7));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var8 = cv$sampleAccumulator;
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample9() {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int var21 = 0; var21 < samples1; var21 += 1) {
								cv$count = (cv$count + 1);
								if(flips1[var21])
									cv$sum = (cv$sum + 1);
							}
						}
						{
							for(int var34 = 0; var34 < samples2; var34 += 1) {
								cv$count = (cv$count + 1);
								if(flips2[var34])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
			bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			flips1 = new boolean[length$flipsMeasured1];
		}
		{
			flips2 = new boolean[length$flipsMeasured2];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, samples1, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						flips1[var21] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
		parallelFor(RNG$, 0, samples2, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						flips2[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, samples1, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						flips1[var21] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
		parallelFor(RNG$, 0, samples2, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						flips2[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
		} else {
			if(!fixedFlag$sample9)
				sample9();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples1 = length$flipsMeasured1;
		samples2 = length$flipsMeasured2;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$bias = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips1 = 0.0;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = Double.NaN;
		logProbability$flips2 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var35 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var46 = (samples1 - ((((samples1 - 1) - 0) % 1) + 1)); i$var46 >= ((0 - 1) + 1); i$var46 -= 1)
			flips1[i$var46] = flipsMeasured1[i$var46];
		for(int i$var58 = (samples2 - ((((samples2 - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1)
			flips2[i$var58] = flipsMeasured2[i$var58];
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
		     + "public model Flip1CoinMK6(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n"
		     + "    int samples1 = flipsMeasured1.length;\n"
		     + "    int samples2 = flipsMeasured2.length;\n"
		     + "        \n"
		     + "    double bias = beta(1.0, 1).sample();\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips1 = bernoulli.sample(samples1);\n"
		     + "    boolean[] flips2 = bernoulli.sample(samples2);\n"
		     + "\n"
		     + "    for(int i:[0..samples1))\n"
		     + "        flips1[i].observe(flipsMeasured1[i]);\n"
		     + "\n"
		     + "    for(int i:[0..samples2))\n"
		     + "        flips2[i].observe(flipsMeasured2[i]);\n"
		     + "}\n"
		     + "";
	}
}