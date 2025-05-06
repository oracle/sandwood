package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK11$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK11$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample9 = false;
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
	private double[] logProbability$sample49;
	private double[] logProbability$sample77;
	private double logProbability$var22;
	private double logProbability$var9;
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
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample49 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		fixedProbFlag$sample49 = (fixedFlag$sample22 && fixedProbFlag$sample49);
		fixedProbFlag$sample77 = (fixedFlag$sample22 && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		fixedProbFlag$sample49 = (fixedFlag$sample9 && fixedProbFlag$sample49);
		fixedProbFlag$sample77 = (fixedFlag$sample9 && fixedProbFlag$sample77);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
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

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[i$var21];
					{
						{
							double var5 = 1.0;
							double var6 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var6));
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
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[j][var48];
						{
							{
								double var37 = bias[j];
								double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var37:(1.0 - var37))));
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
				logProbability$bernoulli1[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample9 && fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[k][var75];
						{
							{
								double var64 = bias[k];
								double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var64:(1.0 - var64))));
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
				logProbability$bernoulli2[((k - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample77[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = (fixedFlag$sample9 && fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = logProbability$sample77[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
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
				double cv$sampleValue = bias[0];
				{
					{
						double var5 = 1.0;
						double var6 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var6));
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
			logProbability$var9 = cv$sampleProbability;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int i$var21) {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						for(int j = 0; j < 1; j += 1) {
							if((i$var21 == j)) {
								{
									for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
										cv$count = (cv$count + 1);
										if(flips[j][var48])
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
							if((i$var21 == k)) {
								{
									for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
										cv$count = (cv$count + 1);
										if(flips[k][var75])
											cv$sum = (cv$sum + 1);
									}
								}
							}
						}
					}
				}
			}
			double var22 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					bias[i$var21] = var22;
				}
			}
		}
	}

	private final void sample9() {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						for(int j = 0; j < 1; j += 1) {
							if((0 == j)) {
								{
									for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1) {
										cv$count = (cv$count + 1);
										if(flips[j][var48])
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
									for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1) {
										cv$count = (cv$count + 1);
										if(flips[k][var75])
											cv$sum = (cv$sum + 1);
									}
								}
							}
						}
					}
				}
			}
			double var9 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					bias[0] = var9;
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			flips = new boolean[length$flipsMeasured.length][];
			for(int j = 0; j < 1; j += 1)
				flips[j] = new boolean[length$flipsMeasured[j]];
			for(int k = 1; k < length$flipsMeasured.length; k += 1)
				flips[k] = new boolean[length$flipsMeasured[k]];
		}
		if((!fixedFlag$sample9 || !fixedFlag$sample22)) {
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
		{
			logProbability$bernoulli1 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample49 = new double[((((1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$bernoulli2 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample77 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
			if(!fixedFlag$sample22)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var39 = flips[j];
			for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1)
				var39[var48] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
		}
		for(int k = 1; k < coins; k += 1) {
			boolean[] var66 = flips[k];
			for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
			if(!fixedFlag$sample22)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
			if(!fixedFlag$sample22)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var39 = flips[j];
			for(int var48 = 0; var48 < length$flipsMeasured[j]; var48 += 1)
				var39[var48] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
		}
		for(int k = 1; k < coins; k += 1) {
			boolean[] var66 = flips[k];
			for(int var75 = 0; var75 < length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(RNG$, bias[k]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
			if(!fixedFlag$sample22)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
			if(!fixedFlag$sample22)
				bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			for(int i$var21 = 1; i$var21 < coins; i$var21 += 1) {
				if(!fixedFlag$sample22)
					sample22(i$var21);
			}
		} else {
			for(int i$var21 = (coins - ((((coins - 1) - 1) % 1) + 1)); i$var21 >= ((1 - 1) + 1); i$var21 -= 1) {
				if(!fixedFlag$sample22)
					sample22(i$var21);
			}
			if(!fixedFlag$sample9)
				sample9();
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
		if(!fixedProbFlag$sample9)
			logProbability$var9 = Double.NaN;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = Double.NaN;
		for(int j = 0; j < 1; j += 1)
			logProbability$bernoulli1[((j - 0) / 1)] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < 1; j += 1)
				logProbability$sample49[((j - 0) / 1)] = Double.NaN;
		}
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[((k - 1) / 1)] = Double.NaN;
		if(!fixedProbFlag$sample77) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample77[((k - 1) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var88 = (coins - ((((coins - 1) - 0) % 1) + 1)); i$var88 >= ((0 - 1) + 1); i$var88 -= 1) {
			boolean[] cv$source1 = flipsMeasured[(coins - (i$var88 + 1))];
			boolean[] cv$target1 = flips[i$var88];
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