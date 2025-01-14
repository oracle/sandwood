package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK12$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK12$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private boolean[][] intermediateFlips;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample37;
	private double[] logProbability$sample54;
	private double logProbability$var14;
	private double logProbability$var19;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK12$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
		fixedProbFlag$sample37 = (fixedFlag$sample16 && fixedProbFlag$sample37);
		fixedProbFlag$sample54 = (fixedFlag$sample16 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		fixedProbFlag$sample37 = (fixedFlag$sample21 && fixedProbFlag$sample37);
		fixedProbFlag$sample54 = (fixedFlag$sample21 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		fixedFlag$sample37 = cv$value;
		fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedProbFlag$sample37);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
	}

	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final boolean[][] get$intermediateFlips() {
		return intermediateFlips;
	}

	@Override
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
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
	public final double[] get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	@Override
	public final double[] get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
	}

	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias[0];
				{
					{
						double var10 = 1.0;
						double var11 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var11));
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
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var14 = cv$sampleProbability;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var14;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[i$var18];
					{
						{
							double var10 = 1.0;
							double var11 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var11));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var19;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[j][var33];
						{
							{
								double var28 = bias[j];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var28));
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
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli1[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample37[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = ((fixedFlag$sample37 && fixedFlag$sample16) && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample37[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[k][var48];
						{
							{
								double var43 = bias[k];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var43));
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
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli2[((k - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample54[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = ((fixedFlag$sample54 && fixedFlag$sample16) && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample54[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < 1; j += 1) {
						if((0 == j)) {
							{
								for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
									cv$count = (cv$count + 1);
									if(flips[j][var33])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			{
				{
					for(int k = 1; k < coins; k += 1) {
						if((0 == k)) {
							{
								for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
									cv$count = (cv$count + 1);
									if(flips[k][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		double var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0] = var14;
	}

	private final void sample21(int i$var18) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < 1; j += 1) {
						if((i$var18 == j)) {
							{
								for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
									cv$count = (cv$count + 1);
									if(flips[j][var33])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
			{
				{
					for(int k = 1; k < coins; k += 1) {
						if((i$var18 == k)) {
							{
								for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
									cv$count = (cv$count + 1);
									if(flips[k][var48])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		double var19 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[i$var18] = var19;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured.length][];
				for(int j = 0; j < 1; j += 1)
					flips[j] = new boolean[length$flipsMeasured[j]];
				for(int k = 1; k < length$flipsMeasured.length; k += 1)
					flips[k] = new boolean[length$flipsMeasured[k]];
			}
		}
		{
			intermediateFlips = new boolean[length$flipsMeasured.length][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1)
				intermediateFlips[l] = new boolean[length$flipsMeasured[l]];
		}
		if(!setFlag$bias) {
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
		{
			logProbability$bernoulli1 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample37 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$bernoulli2 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample54 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
			if(!fixedFlag$sample21)
				bias[i$var18] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var30 = flips[j];
			for(int var33 = 0; var33 < length$flipsMeasured[j]; var33 += 1) {
				if(!fixedFlag$sample37)
					var30[var33] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
		for(int k = 1; k < coins; k += 1) {
			boolean[] var45 = flips[k];
			for(int var48 = 0; var48 < length$flipsMeasured[k]; var48 += 1) {
				if(!fixedFlag$sample54)
					var45[var48] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
			if(!fixedFlag$sample21)
				bias[i$var18] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
			if(!fixedFlag$sample21)
				bias[i$var18] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample16)
				sample16();
			for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
				if(!fixedFlag$sample21)
					sample21(i$var18);
			}
		} else {
			for(int i$var18 = (coins - ((((coins - 1) - 1) % 1) + 1)); i$var18 >= ((1 - 1) + 1); i$var18 -= 1) {
				if(!fixedFlag$sample21)
					sample21(i$var18);
			}
			if(!fixedFlag$sample16)
				sample16();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$beta = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$var19 = 0.0;
		for(int j = 0; j < 1; j += 1)
			logProbability$bernoulli1[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37) {
			for(int j = 0; j < 1; j += 1)
				logProbability$sample37[((j - 0) / 1)] = 0.0;
		}
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[((k - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample54) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample54[((k - 1) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample37();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var18 = 1; i$var18 < coins; i$var18 += 1) {
			if(!fixedFlag$sample21)
				bias[i$var18] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				boolean[] target = intermediateFlips[l];
				for(int m$var62 = 0; m$var62 < length$flipsMeasured[l]; m$var62 += 1)
					target[m$var62] = flipsMeasured[l][m$var62];
			}
		}
		{
			for(int i$var68 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var68 >= ((0 - 1) + 1); i$var68 -= 1) {
				boolean[] cv$source1 = intermediateFlips[(coins - (i$var68 + 1))];
				boolean[] cv$target1 = flips[i$var68];
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
					cv$target1[cv$index1] = cv$source1[cv$index1];
			}
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    boolean[][] intermediateFlips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n    \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int l:[0..coins)) {\n        boolean[] source = flipsMeasured[l];\n        int noFlips = source.length;\n        boolean[] target = new boolean[noFlips];\n        intermediateFlips[l] = target;\n        \n        for(int m:[0..noFlips))\n            target[m] = source[m];\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = intermediateFlips[coins - (i+1)];\n        f.observe(m);\n    }\n}";
	}
}