package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest7$CoreInterface {
	private double[] bias;
	private int cat;
	private double[] cv$var31$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double data;
	private double[] distribution$sample31;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample51 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cat;
	private double logProbability$data;
	private double logProbability$result;
	private double logProbability$sample45;
	private double logProbability$var43;
	private double observedData;
	private double[] prob;
	private int result;
	private boolean system$gibbsForward = true;
	private int var43;

	public DistributionTest7$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final int get$cat() {
		return cat;
	}

	@Override
	public final void set$cat(int cv$value) {
		cat = cv$value;
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample51 = false;
	}

	@Override
	public final double get$data() {
		return data;
	}

	@Override
	public final double[] get$distribution$sample31() {
		return distribution$sample31;
	}

	@Override
	public final void set$distribution$sample31(double[] cv$value) {
		distribution$sample31 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
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
	public final double get$logProbability$cat() {
		return logProbability$cat;
	}

	@Override
	public final double get$logProbability$data() {
		return logProbability$data;
	}

	@Override
	public final double get$logProbability$result() {
		return logProbability$result;
	}

	@Override
	public final double get$observedData() {
		return observedData;
	}

	@Override
	public final void set$observedData(double cv$value) {
		observedData = cv$value;
	}

	@Override
	public final double[] get$prob() {
		return prob;
	}

	@Override
	public final int get$result() {
		return result;
	}

	@Override
	public final int get$var43() {
		return var43;
	}

	@Override
	public final void set$var43(int cv$value) {
		var43 = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample51 = false;
	}

	private final void logProbabilityDistribution$sample31() {
		if(!fixedProbFlag$sample31) {
			if(fixedFlag$sample31) {
				double cv$distributionAccumulator = (((0.0 <= cat) && (cat < 3))?Math.log(prob[cat]):Double.NEGATIVE_INFINITY);
				logProbability$cat = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample31 = true;
			}
		} else {
			logProbability$$model = (logProbability$$model + logProbability$cat);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$cat);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				if(fixedFlag$sample31) {
					if((0 == cat)) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(var43, 0.2, 10);
						cv$probabilityReached = 1.0;
					}
					if((2 == cat)) {
						double cv$weightedProbability = DistributionSampling.logProbabilityBinomial(var43, 0.5, 10);
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
				} else {
					double cv$probabilitySample31Value4 = distribution$sample31[0];
					cv$distributionAccumulator = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(var43, 0.2, 10));
					{
						double cv$probabilitySample31Value11 = distribution$sample31[1];
						double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(var43, 0.3, 10));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilitySample31Value4 + cv$probabilitySample31Value11);
					}
					double cv$probabilitySample31Value18 = distribution$sample31[2];
					double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(var43, 0.5, 10));
					if((cv$weightedProbability < cv$distributionAccumulator))
						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
					else {
						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
							cv$distributionAccumulator = cv$weightedProbability;
						else
							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
					}
					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = cv$distributionAccumulator;
				logProbability$sample45 = cv$distributionAccumulator;
				if((fixedFlag$sample31 && fixedFlag$sample45))
					logProbability$result = (logProbability$result + cv$distributionAccumulator);
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
				cv$accumulator = logProbability$sample45;
				if((fixedFlag$sample31 && fixedFlag$sample45))
					logProbability$result = (logProbability$result + logProbability$sample45);
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(fixedFlag$sample31) {
				if(!(cat == 1)) {
					cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((data - var43) / Math.sqrt(cat))) - (Math.log(cat) * 0.5));
					cv$probabilityReached = 1.0;
				}
			} else {
				cv$distributionAccumulator = Double.POSITIVE_INFINITY;
				cv$probabilityReached = (distribution$sample31[0] + distribution$sample31[2]);
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			logProbability$data = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$data);
			logProbability$$evidence = (logProbability$$evidence + logProbability$data);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$distributionAccumulator = (((0.0 <= cat) && (cat < 3))?Math.log(prob[cat]):Double.NEGATIVE_INFINITY);
			logProbability$cat = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$cat);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$cat);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(var43, bias[cat], 10);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$sample45 = cv$distributionAccumulator;
				logProbability$result = (logProbability$result + cv$distributionAccumulator);
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
				cv$accumulator = logProbability$sample45;
				logProbability$result = (logProbability$result + logProbability$sample45);
			}
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((data - result) / Math.sqrt(cat))) - (Math.log(cat) * 0.5));
			logProbability$data = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$data);
			logProbability$$evidence = (logProbability$$evidence + logProbability$data);
		}
	}

	private final void sample31() {
		cv$var31$stateProbabilityGlobal[0] = Double.POSITIVE_INFINITY;
		cv$var31$stateProbabilityGlobal[1] = Math.log(prob[1]);
		cv$var31$stateProbabilityGlobal[2] = (((((DistributionSampling.logProbabilityBinomial(var43, bias[2], 10) + DistributionSampling.logProbabilityGaussian(((data - var43) / 1.4142135623730951))) + DistributionSampling.logProbabilityGaussian(((data - var43) / 1.4142135623730951))) + DistributionSampling.logProbabilityBinomial(var43, 0.5, 10)) + Math.log(prob[2])) - 0.6931471805599453);
		double cv$logSum;
		double cv$lseMax = cv$var31$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var31$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var31$stateProbabilityGlobal[2];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log(((Math.exp((cv$var31$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var31$stateProbabilityGlobal[1] - cv$lseMax))) + Math.exp((cv$var31$stateProbabilityGlobal[2] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			distribution$sample31[0] = 0.3333333333333333;
			distribution$sample31[1] = 0.3333333333333333;
			distribution$sample31[2] = 0.3333333333333333;
		} else {
			distribution$sample31[0] = Math.exp((cv$var31$stateProbabilityGlobal[0] - cv$logSum));
			distribution$sample31[1] = Math.exp((cv$var31$stateProbabilityGlobal[1] - cv$logSum));
			distribution$sample31[2] = Math.exp((cv$var31$stateProbabilityGlobal[2] - cv$logSum));
		}
		for(int cv$indexName = 3; cv$indexName < cv$var31$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample31[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample45() {
		int cv$numStates = 0;
		if(fixedFlag$sample31) {
			if((0 == cat))
				cv$numStates = 11;
			if((2 == cat))
				cv$numStates = 11;
		} else
			cv$numStates = 11;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			var43 = cv$valuePos;
			result = cv$valuePos;
			if(fixedFlag$sample31) {
				if((0 == cat)) {
					cv$reachedDistributionSourceRV = 1.0;
					cv$stateProbabilityValue = Double.POSITIVE_INFINITY;
				}
				if((2 == cat)) {
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((data - cv$valuePos) / 1.4142135623730951)) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.5, 10)) - 0.34657359027997264);
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				cv$reachedDistributionSourceRV = ((distribution$sample31[0] + distribution$sample31[1]) + distribution$sample31[2]);
				cv$stateProbabilityValue = Double.POSITIVE_INFINITY;
			}
			cv$var43$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		double cv$logSum;
		double cv$lseMax = cv$var43$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var43$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var43$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var43$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numStates; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var43$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		var43 = DistributionSampling.sampleCategorical(RNG$, cv$var43$stateProbabilityGlobal, cv$numStates);
		result = var43;
	}

	@Override
	public final void allocateScratch() {
		cv$var31$stateProbabilityGlobal = new double[3];
		cv$var43$stateProbabilityGlobal = new double[11];
	}

	@Override
	public final void allocator() {
		bias = new double[3];
		prob = new double[3];
		distribution$sample31 = new double[3];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1)) {
			if(!fixedFlag$sample31)
				result = 5;
		} else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + result);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample31) {
			distribution$sample31[0] = prob[0];
			distribution$sample31[1] = prob[1];
			distribution$sample31[2] = prob[2];
		}
		if((!(cat == 1) && !fixedFlag$sample45))
			var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1))
			result = 5;
		else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + result);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1)) {
			if(!fixedFlag$sample31)
				result = 5;
		} else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1))
			result = 5;
		else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				sample31();
			if((!(cat == 1) && !fixedFlag$sample45))
				sample45();
		} else {
			if((!(cat == 1) && !fixedFlag$sample45))
				sample45();
			if(!fixedFlag$sample31)
				sample31();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		bias[0] = 0.2;
		bias[1] = 0.3;
		bias[2] = 0.5;
		prob[0] = 0.2;
		prob[1] = 0.4;
		prob[2] = 0.4;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$cat = Double.NaN;
		logProbability$var43 = 0.0;
		logProbability$result = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$sample45 = Double.NaN;
		if(!fixedProbFlag$sample51)
			logProbability$data = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void propagateObservedValues() {
		data = observedData;
	}

	@Override
	public final void setIntermediates() {
		if(!(cat == 1)) {
			if((fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

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
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}