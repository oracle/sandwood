package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK11$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK11$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample57;
	private double[] logProbability$sample87;
	private double logProbability$var13;
	private double logProbability$var26;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK11$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample15 = false;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		fixedProbFlag$sample57 = (fixedFlag$sample15 && fixedProbFlag$sample57);
		fixedProbFlag$sample87 = (fixedFlag$sample15 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample57 = (fixedFlag$sample28 && fixedProbFlag$sample57);
		fixedProbFlag$sample87 = (fixedFlag$sample28 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample87 = false;
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

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias[0];
				{
					{
						double var9 = 1.0;
						double var10 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var10));
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
			logProbability$var13 = cv$sampleProbability;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var13;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[i$var25];
					{
						{
							double var9 = 1.0;
							double var10 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var10));
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
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var26;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var53 = 0; var53 < length$flipsMeasured[j]; var53 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[j][var53];
						{
							{
								double var42 = bias[j];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var42));
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
				logProbability$sample57[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample15) && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var81 = 0; var81 < length$flipsMeasured[k]; var81 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[k][var81];
						{
							{
								double var70 = bias[k];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var70));
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
				logProbability$sample87[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample87 && fixedFlag$sample15) && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample15() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < 1; j += 1) {
						if((0 == j)) {
							{
								for(int var53 = 0; var53 < length$flipsMeasured[j]; var53 += 1) {
									cv$count = (cv$count + 1);
									if(flips[j][var53])
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
								for(int var81 = 0; var81 < length$flipsMeasured[k]; var81 += 1) {
									cv$count = (cv$count + 1);
									if(flips[k][var81])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		double var13 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0] = var13;
	}

	private final void sample28(int i$var25) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < 1; j += 1) {
						if((i$var25 == j)) {
							{
								for(int var53 = 0; var53 < length$flipsMeasured[j]; var53 += 1) {
									cv$count = (cv$count + 1);
									if(flips[j][var53])
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
						if((i$var25 == k)) {
							{
								for(int var81 = 0; var81 < length$flipsMeasured[k]; var81 += 1) {
									cv$count = (cv$count + 1);
									if(flips[k][var81])
										cv$sum = (cv$sum + 1);
								}
							}
						}
					}
				}
			}
		}
		double var26 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[i$var25] = var26;
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
		if(!setFlag$bias) {
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
		{
			logProbability$bernoulli1 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample57 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$bernoulli2 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample87 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
			if(!fixedFlag$sample28)
				bias[i$var25] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var44 = flips[j];
			for(int var53 = 0; var53 < length$flipsMeasured[j]; var53 += 1) {
				if(!fixedFlag$sample57)
					var44[var53] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
		for(int k = 1; k < coins; k += 1) {
			boolean[] var72 = flips[k];
			for(int var81 = 0; var81 < length$flipsMeasured[k]; var81 += 1) {
				if(!fixedFlag$sample87)
					var72[var81] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
			if(!fixedFlag$sample28)
				bias[i$var25] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
			if(!fixedFlag$sample28)
				bias[i$var25] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample15)
				sample15();
			for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
				if(!fixedFlag$sample28)
					sample28(i$var25);
			}
		} else {
			for(int i$var25 = (coins - ((((coins - 1) - 1) % 1) + 1)); i$var25 >= ((1 - 1) + 1); i$var25 -= 1) {
				if(!fixedFlag$sample28)
					sample28(i$var25);
			}
			if(!fixedFlag$sample15)
				sample15();
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
		if(!fixedProbFlag$sample15)
			logProbability$var13 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var26 = 0.0;
		for(int j = 0; j < 1; j += 1)
			logProbability$bernoulli1[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int j = 0; j < 1; j += 1)
				logProbability$sample57[((j - 0) / 1)] = 0.0;
		}
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[((k - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample87[((k - 1) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		logProbabilityValue$sample57();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample28();
		logProbabilityValue$sample57();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample28();
		logProbabilityValue$sample57();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample15)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var25 = 1; i$var25 < coins; i$var25 += 1) {
			if(!fixedFlag$sample28)
				bias[i$var25] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i$var94 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var94 >= ((0 - 1) + 1); i$var94 -= 1) {
			boolean[] cv$source1 = flipsMeasured[(coins - (i$var94 + 1))];
			boolean[] cv$target1 = flips[i$var94];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
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
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}