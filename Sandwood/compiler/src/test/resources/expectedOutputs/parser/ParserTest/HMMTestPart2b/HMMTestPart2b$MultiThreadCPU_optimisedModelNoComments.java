package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart2b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart2b$CoreInterface {
	private double[] bias;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample86;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double[] logProbability$var69;
	private double[] logProbability$var84;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart2b$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample86 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
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
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
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
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample86 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
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

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1) {
				int cv$sampleValue = st[(i$var64 + 1)];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[st[i$var64]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[i$var64] = cv$distributionAccumulator;
				logProbability$sample71[i$var64] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1) {
				double cv$rvAccumulator = logProbability$sample71[i$var64];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[i$var64] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double cv$accumulator = 0.0;
			for(int j = 1; j < samples; j += 1) {
				double var83 = bias[st[j]];
				double cv$distributionAccumulator = Math.log((flips[j]?var83:(1.0 - var83)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var84[(j - 1)] = cv$distributionAccumulator;
				logProbability$sample86[(j - 1)] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample86 = (fixedFlag$sample45 && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 1; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample86[(j - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var84[(j - 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1) {
			if((var27 == st[i$var64]))
				cv$countLocal[st[(i$var64 + 1)]] = (cv$countLocal[st[(i$var64 + 1)]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 1; j < samples; j += 1) {
			if((var43 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample71(int i$var64) {
		{
			st[(i$var64 + 1)] = 0;
			double cv$accumulatedProbabilities = Math.log(m[st[i$var64]][0]);
			int index$i$2_2 = (i$var64 + 1);
			if((index$i$2_2 < (samples - 1)))
				cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$2_2 + 1)]) && (st[(index$i$2_2 + 1)] < 2))?Math.log(m[0][st[(index$i$2_2 + 1)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			double cv$temp$4$var83 = bias[0];
			cv$accumulatedProbabilities = (Math.log((flips[(i$var64 + 1)]?cv$temp$4$var83:(1.0 - cv$temp$4$var83))) + cv$accumulatedProbabilities);
			cv$var70$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[(i$var64 + 1)] = 1;
		double cv$accumulatedProbabilities = Math.log(m[st[i$var64]][1]);
		int index$i$2_2 = (i$var64 + 1);
		if((index$i$2_2 < (samples - 1)))
			cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$2_2 + 1)]) && (st[(index$i$2_2 + 1)] < 2))?Math.log(m[1][st[(index$i$2_2 + 1)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		double cv$temp$4$var83 = bias[1];
		cv$accumulatedProbabilities = (Math.log((flips[(i$var64 + 1)]?cv$temp$4$var83:(1.0 - cv$temp$4$var83))) + cv$accumulatedProbabilities);
		cv$var70$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var70$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var70$stateProbabilityGlobal[0] = 0.5;
			cv$var70$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var70$stateProbabilityGlobal[0] = Math.exp((cv$var70$stateProbabilityGlobal[0] - cv$logSum));
			cv$var70$stateProbabilityGlobal[1] = Math.exp((cv$var70$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, 2);
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[2];
		cv$var70$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		if(!fixedFlag$sample71)
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		logProbability$var69 = new double[(length$flipsMeasured - 1)];
		logProbability$sample71 = new double[(length$flipsMeasured - 1)];
		logProbability$var84 = new double[(length$flipsMeasured - 1)];
		logProbability$sample86 = new double[(length$flipsMeasured - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var64]], 2);
		}
		parallelFor(RNG$, 1, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var64]], 2);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var64]], 2);
		}
		parallelFor(RNG$, 1, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var64]], 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				st[(i$var64 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var64]], 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample71) {
				for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
					sample71(i$var64);
			}
		} else {
			if(!fixedFlag$sample71) {
				for(int i$var64 = (samples - 2); i$var64 >= 0; i$var64 -= 1)
					sample71(i$var64);
			}
			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
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
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		st[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$var32 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
			logProbability$var69[i$var64] = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int i$var64 = 0; i$var64 < (samples - 1); i$var64 += 1)
				logProbability$sample71[i$var64] = Double.NaN;
		}
		for(int j = 1; j < samples; j += 1)
			logProbability$var84[(j - 1)] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample86) {
			for(int j = 1; j < samples; j += 1)
				logProbability$sample86[(j - 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample86();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample71();
		logProbabilityValue$sample86();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample71();
		logProbabilityValue$sample86();
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
		     + "model HMMTestPart2b(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
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
		     + "        st[0] = 0;\n"
		     + "        \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "\n"
		     + "        for(int i:[0..samples-1))\n"
		     + "            st[i+1] = categorical(m[st[i]]).sample();\n"
		     + "            \n"
		     + "        for(int j:[1..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}