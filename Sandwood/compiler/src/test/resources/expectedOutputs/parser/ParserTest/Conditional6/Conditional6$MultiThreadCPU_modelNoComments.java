package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional6$CoreInterface {
	private double a;
	private double b;
	private double[] cv$var5$stateProbabilityGlobal;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample10;
	private double logProbability$sample9;
	private double logProbability$u;
	private double logProbability$value;
	private boolean observedGuard;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
		fixedProbFlag$sample9 = false;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample10 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample5 = false;
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
	public final double get$logProbability$a() {
		return logProbability$a;
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
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	@Override
	public final double get$logProbability$u() {
		return logProbability$u;
	}

	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	@Override
	public final boolean get$observedGuard() {
		return observedGuard;
	}

	@Override
	public final void set$observedGuard(boolean cv$value) {
		observedGuard = cv$value;
	}

	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	@Override
	public final void set$observedValue(double cv$value) {
		observedValue = cv$value;
	}

	@Override
	public final double get$value() {
		return value;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = b;
				{
					{
						double var6 = 0.0;
						double var7 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + (((var6 <= cv$sampleValue) && (cv$sampleValue < var7))?(-Math.log((var7 - var6))):Double.NEGATIVE_INFINITY));
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
			logProbability$u = (logProbability$u + cv$sampleAccumulator);
			logProbability$sample10 = cv$sampleProbability;
			boolean cv$guard$value = false;
			logProbability$b = (logProbability$b + cv$accumulator);
			{
				if(!guard) {
					if(!cv$guard$value) {
						cv$guard$value = true;
						logProbability$value = (logProbability$value + cv$sampleProbability);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sample10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$u = (logProbability$u + cv$rvAccumulator);
			boolean cv$guard$value = false;
			logProbability$b = (logProbability$b + cv$accumulator);
			{
				if(!guard) {
					if(!cv$guard$value) {
						cv$guard$value = true;
						logProbability$value = (logProbability$value + cv$sampleValue);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				boolean cv$sampleValue = guard;
				{
					{
						double var3 = 0.5;
						double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var3:(1.0 - var3))));
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
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$guard = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$guard;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
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
				double cv$sampleValue = a;
				{
					{
						double var6 = 0.0;
						double var7 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + (((var6 <= cv$sampleValue) && (cv$sampleValue < var7))?(-Math.log((var7 - var6))):Double.NEGATIVE_INFINITY));
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
			logProbability$u = (logProbability$u + cv$sampleAccumulator);
			logProbability$sample9 = cv$sampleProbability;
			boolean cv$guard$value = false;
			logProbability$a = (logProbability$a + cv$accumulator);
			{
				if(guard) {
					if(!cv$guard$value) {
						cv$guard$value = true;
						logProbability$value = (logProbability$value + cv$sampleProbability);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sample9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$u = (logProbability$u + cv$rvAccumulator);
			boolean cv$guard$value = false;
			logProbability$a = (logProbability$a + cv$accumulator);
			{
				if(guard) {
					if(!cv$guard$value) {
						cv$guard$value = true;
						logProbability$value = (logProbability$value + cv$sampleValue);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample5() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var5$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				guard = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var3;
					{
						cv$temp$0$var3 = 0.5;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + Math.log((cv$currentValue?cv$temp$0$var3:(1.0 - cv$temp$0$var3))));
					{
						{
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double cv$logSum = 0.0;
			{
				double cv$lseMax = cv$stateProbabilityLocal[0];
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				else {
					double cv$lseSum = 0.0;
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			guard = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates) == 1);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var5$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample9)
			a = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		if(!fixedFlag$sample10)
			b = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		if(guard) {
			if(!(fixedFlag$sample5 && fixedFlag$sample9))
				value = a;
		} else {
			if(!(fixedFlag$sample5 && fixedFlag$sample10))
				value = b;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample9)
			a = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		if(!fixedFlag$sample10)
			b = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		if(guard) {
			if(!(fixedFlag$sample5 && fixedFlag$sample9))
				value = a;
		} else {
			if(!(fixedFlag$sample5 && fixedFlag$sample10))
				value = b;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				sample5();
		} else {
			if(!fixedFlag$sample5)
				sample5();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$guard = Double.NaN;
		logProbability$u = 0.0;
		logProbability$a = 0.0;
		logProbability$value = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$sample9 = Double.NaN;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$sample10 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void propogateObservedValues() {
		value = observedValue;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Conditional6(double observedValue, boolean observedGuard)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    Uniform u = uniform(0.0, 1.0);\n"
		     + "    double a = u.sample();\n"
		     + "    double b = u.sample();\n"
		     + "        \n"
		     + "    double value = guard?a:b;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}