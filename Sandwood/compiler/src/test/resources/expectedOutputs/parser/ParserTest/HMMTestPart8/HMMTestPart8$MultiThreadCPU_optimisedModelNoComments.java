package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart8$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart8$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var69;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private double[][] distribution$sample71;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var69;
	private double[] logProbability$var85;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart8$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[][] get$distribution$sample71() {
		return distribution$sample71;
	}

	@Override
	public final void set$distribution$sample71(double[][] cv$value) {
		distribution$sample71 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
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
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final int get$states() {
		return 5;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample71() {
		if(!fixedProbFlag$sample71) {
			if(fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var64];
					if((1 == i$var64)) {
						int var27 = st[0];
						if(((0 <= var27) && (var27 < 5))) {
							cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[st[0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					}
					if((2 <= i$var64)) {
						int var27 = st[(i$var64 - 1)];
						if(((0 <= var27) && (var27 < 5))) {
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[st[(i$var64 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var69[(i$var64 - 1)] = cv$distributionAccumulator;
					logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = logProbability$sample71[(i$var64 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[(i$var64 - 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample71)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if((0 == j)) {
					int var43 = st[0];
					if(((0 <= var43) && (var43 < 5))) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[0]]);
						cv$probabilityReached = 1.0;
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample71) {
						int var43 = st[j];
						if(((0 <= var43) && (var43 < 5))) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[j]]);
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
						for(int index$sample71$6 = 0; index$sample71$6 < 5; index$sample71$6 += 1) {
							double cv$probabilitySample71Value7 = distribution$sample71[(j - 1)][index$sample71$6];
							int var43 = st[j];
							if(((0 <= var43) && (var43 < 5))) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[j]]));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value7);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var85[j] = cv$distributionAccumulator;
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample87[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < 5; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var27], v, 5));
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var16 = logProbability$var28;
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < 5; var43 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var43], 1.0, 1.0));
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$var32 = logProbability$var44;
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var51 = cv$distributionAccumulator;
			logProbability$var52 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
		} else {
			logProbability$var51 = logProbability$var52;
			logProbability$st = (logProbability$st + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				int cv$sampleValue = st[i$var64];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[st[(i$var64 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[(i$var64 - 1)] = cv$distributionAccumulator;
				logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = logProbability$sample71[(i$var64 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[(i$var64 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var85[j] = cv$distributionAccumulator;
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample87[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if((var27 == 0))
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		if(fixedFlag$sample71) {
			if(((var27 == st[0]) && (1 < samples)))
				cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
			for(int i$var64 = 2; i$var64 < samples; i$var64 += 1) {
				if((var27 == st[(i$var64 - 1)]))
					cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + 1.0);
			}
		} else {
			if(((var27 == st[0]) && (1 < samples))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample71[0][cv$loopIndex]);
			}
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				if((var27 == st[(i$var64 - 1)])) {
					int index$i$30 = (i$var64 - 1);
					if((1 <= index$i$30)) {
						for(int index$sample71$31 = 0; index$sample71$31 < 5; index$sample71$31 += 1) {
							double cv$distributionProbability = distribution$sample71[(index$i$30 - 1)][index$sample71$31];
							for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[(i$var64 - 1)][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 5);
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		if(((var43 == st[0]) && (0 < samples))) {
			cv$count = 1.0;
			if(flips[0])
				cv$sum = 1.0;
		}
		for(int j = 1; j < samples; j += 1) {
			if((var43 == st[j])) {
				if(fixedFlag$sample71) {
					cv$count = (cv$count + 1.0);
					if(flips[j])
						cv$sum = (cv$sum + 1.0);
				} else {
					for(int index$sample71$7 = 0; index$sample71$7 < 5; index$sample71$7 += 1) {
						double cv$probabilitySample71Value8 = distribution$sample71[(j - 1)][index$sample71$7];
						cv$count = (cv$count + cv$probabilitySample71Value8);
						if(flips[j])
							cv$sum = (cv$sum + cv$probabilitySample71Value8);
					}
				}
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample53() {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			st[0] = cv$valuePos;
			double cv$accumulatedProbabilities = Math.log(m[0][cv$valuePos]);
			if((fixedFlag$sample71 && (1 < samples))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var27 = st[0];
				if(((0 <= var27) && (var27 < 5))) {
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[1]) && (st[1] < 5))?Math.log(m[cv$valuePos][st[1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
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
			if((0 < samples)) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var43 = st[0];
				if(((0 <= var43) && (var43 < 5))) {
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
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
			if((!fixedFlag$sample71 && (1 < samples))) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var69[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var27 = st[0];
				if(((0 <= var27) && (var27 < 5))) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var69, 1.0, m[cv$valuePos], 5);
				}
				double[] cv$sampleDistribution = distribution$sample71[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var69[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var52$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var52$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, 5);
	}

	private final void sample71(int i$var64) {
		int cv$numNumStates = 0;
		if((1 == i$var64)) {
			int var27 = st[0];
			if(((0 <= var27) && (var27 < 5)))
				cv$numNumStates = 5;
		}
		if(fixedFlag$sample71) {
			if((2 <= i$var64)) {
				int var27 = st[(i$var64 - 1)];
				if(((0 <= var27) && (var27 < 5)))
					cv$numNumStates = 5;
			}
		} else {
			int index$i$6 = (i$var64 - 1);
			if(((1 <= index$i$6) && !(index$i$6 == i$var64))) {
				int var27 = st[(i$var64 - 1)];
				if(((0 <= var27) && (var27 < 5)))
					cv$numNumStates = 5;
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var64)) {
				int var27 = st[0];
				if(((0 <= var27) && (var27 < 5))) {
					cv$reachedDistributionSourceRV = 1.0;
					double cv$accumulatedProbabilities = Math.log(m[st[0]][cv$valuePos]);
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var43 = st[1];
					if(((0 <= var43) && (var43 < 5))) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(flips[1], bias[cv$valuePos]);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
			}
			int index$i$18 = (i$var64 - 1);
			if(((1 <= index$i$18) && !(index$i$18 == i$var64))) {
				for(int index$sample71$19 = 0; index$sample71$19 < 5; index$sample71$19 += 1) {
					double cv$probabilitySample71Value20 = distribution$sample71[(index$i$18 - 1)][index$sample71$19];
					int var27 = st[(i$var64 - 1)];
					if(((0 <= var27) && (var27 < 5))) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value20);
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value20) + Math.log(m[cv$valuePos][cv$valuePos]));
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var43 = st[i$var64];
						if(((0 <= var43) && (var43 < 5))) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(flips[i$var64], bias[index$sample71$19]);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
				}
			}
			int index$i$40_2 = (i$var64 + 1);
			if((index$i$40_2 < samples)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var69[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var27 = st[(index$i$40_2 - 1)];
				if(((0 <= var27) && (var27 < 5))) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var64)) {
						int index$var27$45_1 = st[0];
						if(((0 <= index$var27$45_1) && (index$var27$45_1 < 5)))
							scopeVariable$reachedSourceProbability = 1.0;
					}
					int index$i$47 = (i$var64 - 1);
					if((((1 <= index$i$47) && !(index$i$47 == i$var64)) && !(index$i$47 == index$i$40_2))) {
						for(int index$sample71$48 = 0; index$sample71$48 < 5; index$sample71$48 += 1) {
							int index$var27$53_1 = st[(i$var64 - 1)];
							if(((0 <= index$var27$53_1) && (index$var27$53_1 < 5)))
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample71[(index$i$47 - 1)][index$sample71$48]);
						}
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var69, scopeVariable$reachedSourceProbability, m[cv$valuePos], 5);
				}
				double[] cv$sampleDistribution = distribution$sample71[(index$i$40_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var69[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var70$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample71[(i$var64 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[5];
		cv$distributionAccumulator$var69 = new double[5];
		cv$var52$stateProbabilityGlobal = new double[5];
		cv$var70$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!fixedFlag$sample28) {
			m = new double[5][];
			for(int var27 = 0; var27 < 5; var27 += 1)
				m[var27] = new double[5];
		}
		if(!fixedFlag$sample45)
			bias = new double[5];
		if((!fixedFlag$sample53 || !fixedFlag$sample71))
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		distribution$sample71 = new double[(length$flipsMeasured - 1)][];
		for(int i$var64 = 1; i$var64 < length$flipsMeasured; i$var64 += 1)
			distribution$sample71[(i$var64 - 1)] = new double[5];
		logProbability$var69 = new double[(length$flipsMeasured - 1)];
		logProbability$sample71 = new double[(length$flipsMeasured - 1)];
		logProbability$var85 = new double[length$flipsMeasured];
		logProbability$sample87 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double[] cv$distribution$sample71 = distribution$sample71[(i$var64 - 1)];
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = 0.0;
				if((1 == i$var64)) {
					int var27 = st[0];
					if(((0 <= var27) && (var27 < 5))) {
						double[] var68 = m[st[0]];
						for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
							cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + var68[index$var69]);
					}
				}
				int index$i$4 = (i$var64 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample71$5 = 0; index$sample71$5 < 5; index$sample71$5 += 1) {
						double cv$probabilitySample71Value6 = distribution$sample71[(index$i$4 - 1)][index$sample71$5];
						int var27 = st[(i$var64 - 1)];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = m[st[(i$var64 - 1)]];
							for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
								cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value6 * var68[index$var69]));
						}
					}
				}
				double cv$var69$sum = 0.0;
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample71) {
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
					sample71(i$var64);
			}
		} else {
			if(!fixedFlag$sample71) {
				for(int i$var64 = (samples - 1); i$var64 >= 1; i$var64 -= 1)
					sample71(i$var64);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = 0.0;
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
			logProbability$var69[(i$var64 - 1)] = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				logProbability$sample71[(i$var64 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var85[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample87[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
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
		     + "model HMMTestPart8(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}