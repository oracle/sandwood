package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK5$CoreInterface {
	private boolean fixedFlag$sample112 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedProbFlag$sample112 = false;
	private boolean fixedProbFlag$sample68 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[] logProbability$sample112;
	private double[][] logProbability$sample68;
	private double[] logProbability$var102;
	private double[][] logProbability$var60;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK5$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample112() {
		return fixedFlag$sample112;
	}

	@Override
	public final void set$fixedFlag$sample112(boolean cv$value) {
		fixedFlag$sample112 = cv$value;
		fixedProbFlag$sample112 = (fixedFlag$sample112 && fixedProbFlag$sample112);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		fixedProbFlag$sample112 = (fixedFlag$sample68 && fixedProbFlag$sample112);
	}

	@Override
	public final int[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(int[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
		fixedProbFlag$sample112 = false;
	}

	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	@Override
	public final void set$indirection1(double[][] cv$value) {
		indirection1 = cv$value;
		setFlag$indirection1 = true;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample112 = false;
	}

	@Override
	public final double[][] get$indirection2() {
		return indirection2;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	@Override
	public final double get$logProbability$indirection1() {
		return logProbability$indirection1;
	}

	@Override
	public final double get$logProbability$indirection2() {
		return logProbability$indirection2;
	}

	@Override
	public final int[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(int[] cv$value) {
		observed = cv$value;
	}

	private final void logProbabilityValue$sample112() {
		if(!fixedProbFlag$sample112) {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = generated[m];
					{
						{
							double[] var101 = indirection2[m];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var101.length))?Math.log(var101[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var102[((m - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample112[((m - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample112 = (fixedFlag$sample112 && fixedFlag$sample68);
		} else {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample112[((m - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var102[((m - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = indirection1[i][j];
						{
							{
								double var58 = 0.0;
								double var59 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + (((var58 <= cv$sampleValue) && (cv$sampleValue <= var59))?(-Math.log((var59 - var58))):Double.NEGATIVE_INFINITY));
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
					logProbability$var60[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample68[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$indirection2 = false;
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										if(!cv$guard$indirection2) {
											cv$guard$indirection2 = true;
											logProbability$indirection2 = (logProbability$indirection2 + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample68[((i - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var60[((i - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$indirection2 = false;
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										if(!cv$guard$indirection2) {
											cv$guard$indirection2 = true;
											logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample68(int i, int j) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = indirection1[i][j];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var61 = cv$proposedValue;
					double[] var57 = indirection1[i];
					var57[j] = cv$currentValue;
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										{
											double[] var85 = indirection2[k];
											var85[l] = indirection1[l][k];
										}
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var58;
				{
					cv$temp$0$var58 = 0.0;
				}
				double cv$temp$1$var59;
				{
					cv$temp$1$var59 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var58 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var59))?(-Math.log((cv$temp$1$var59 - cv$temp$0$var58))):Double.NEGATIVE_INFINITY));
				{
					{
						double traceTempVariable$var87$2_1 = cv$currentValue;
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										for(int m = 0; m < length$observed; m += 1) {
											if((k == m)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$2$var101;
																	{
																		double[] var101 = indirection2[m];
																		cv$temp$2$var101 = var101;
																	}
																	if(((Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[m]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[m]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[m]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[m]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[m]]):Double.NEGATIVE_INFINITY)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
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
											}
										}
									}
								}
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
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var61 = cv$originalValue;
			double[] var57 = indirection1[i];
			var57[j] = var61;
			{
				for(int l = 0; l < 10; l += 1) {
					if((i == l)) {
						for(int k = 0; k < length$observed; k += 1) {
							if((j == k)) {
								{
									double[] var85 = indirection2[k];
									var85[l] = indirection1[l][k];
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$generated) {
			{
				generated = new int[length$observed];
			}
		}
		if(!setFlag$indirection1) {
			{
				indirection1 = new double[10][];
				for(int var18 = 0; var18 < 10; var18 += 1)
					indirection1[var18] = new double[length$observed];
			}
		}
		{
			indirection2 = new double[length$observed][];
			for(int var33 = 0; var33 < length$observed; var33 += 1)
				indirection2[var33] = new double[10];
		}
		{
			logProbability$var60 = new double[((((10 - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < 10; i += 1)
				logProbability$var60[((i - 0) / 1)] = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample68 = new double[((((10 - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < 10; i += 1)
				logProbability$sample68[((i - 0) / 1)] = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var102 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample112 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < 10; i += 1) {
			double[] var57 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample68)
					var57[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var85 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample68)
					var85[l] = indirection1[l][k];
			}
		}
		for(int m = 0; m < length$observed; m += 1) {
			if(!fixedFlag$sample112)
				generated[m] = DistributionSampling.sampleCategorical(RNG$, indirection2[m]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int i = 0; i < 10; i += 1) {
			double[] var57 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample68)
					var57[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var85 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample68)
					var85[l] = indirection1[l][k];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < 10; i += 1) {
			double[] var57 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample68)
					var57[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var85 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample68)
					var85[l] = indirection1[l][k];
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					if(!fixedFlag$sample68)
						sample68(i, j);
				}
			}
		} else {
			for(int i = (10 - ((((10 - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				for(int j = (length$observed - ((((length$observed - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!fixedFlag$sample68)
						sample68(i, j);
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < 10; i += 1) {
			for(int j = 0; j < length$observed; j += 1)
				logProbability$var60[((i - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$indirection2 = 0.0;
		logProbability$indirection1 = 0.0;
		if(!fixedProbFlag$sample68) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1)
					logProbability$sample68[((i - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var102[((m - 0) / 1)] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample112) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample112[((m - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int i = 0; i < 10; i += 1) {
			double[] var57 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample68)
					var57[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var85 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample68)
					var85[l] = indirection1[l][k];
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[] cv$source1 = observed;
		int[] cv$target1 = generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int k = 0; k < length$observed; k += 1) {
			double[] var85 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(setFlag$indirection1)
					var85[l] = indirection1[l][k];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK5(int[] observed) {\n    int[] generated = new int[observed.length];\n    double[][] indirection1 = new double[10][observed.length];\n    double[][] indirection2 = new double[observed.length][10];\n\n    for(int i=0; i<10; i++) {\n        for(int j=0; j<observed.length; j++) {\n            indirection1[i][j] = uniform(0.0, 1.0).sample();\n        }\n    }\n    \n    for(int k=0; k<observed.length; k++) {\n        for(int l=0; l<10; l++) {\n            indirection2[k][l] = indirection1[l][k];\n        }\n    }\n    \n    for(int m=0; m<observed.length; m++) {\n        generated[m] = categorical(indirection2[m]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}