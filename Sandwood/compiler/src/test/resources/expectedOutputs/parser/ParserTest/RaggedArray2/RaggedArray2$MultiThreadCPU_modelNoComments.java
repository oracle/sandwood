package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray2$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample81 = false;
	private boolean fixedProbFlag$sample84 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var76;
	private double logProbability$var79;
	private double logProbability$var83;
	private double logProbability$var96;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[][] get$b() {
		return b;
	}

	@Override
	public final double[] get$c() {
		return c;
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
		fixedProbFlag$sample84 = (fixedFlag$sample81 && fixedProbFlag$sample84);
		fixedProbFlag$sample100 = (fixedFlag$sample81 && fixedProbFlag$sample100);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
		fixedProbFlag$sample100 = (fixedFlag$sample84 && fixedProbFlag$sample100);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample100 = false;
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final double get$p() {
		return p;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
		fixedProbFlag$sample81 = false;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample100 = false;
	}

	private final void logProbabilityValue$sample100() {
		if(!fixedProbFlag$sample100) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = obs[var95];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?p:(1.0 - p))));
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
			logProbability$var83 = cv$sampleAccumulator;
			logProbability$var96 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample100 = (fixedFlag$sample81 && fixedFlag$sample84);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var96;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var83 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = y;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var76 = cv$sampleAccumulator;
			logProbability$y = cv$sampleProbability;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = fixedFlag$sample81;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var76 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = i;
				{
					{
						double[] var78 = a[y];
						int lengthCV$a$82_11 = -1;
						{
							if((1 == y))
								lengthCV$a$82_11 = 3;
						}
						{
							if((0 == y))
								lengthCV$a$82_11 = 2;
						}
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$82_11))?Math.log(var78[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$i = cv$sampleProbability;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample81() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				y = cv$currentValue;
				{
					{
						p = b[cv$currentValue][i];
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$c;
					{
						cv$temp$0$c = c;
					}
					int cv$temp$1$$var294;
					{
						cv$temp$1$$var294 = 2;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var294))?Math.log(cv$temp$0$c[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$y$2_1 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double[] cv$temp$2$var78;
												{
													double[] var78 = a[traceTempVariable$y$2_1];
													cv$temp$2$var78 = var78;
												}
												int cv$temp$3$$var296;
												{
													int lengthCV$a$82_8 = -1;
													{
														if((1 == traceTempVariable$y$2_1))
															lengthCV$a$82_8 = 3;
													}
													{
														if((0 == traceTempVariable$y$2_1))
															lengthCV$a$82_8 = 2;
													}
													int $var296 = lengthCV$a$82_8;
													cv$temp$3$$var296 = $var296;
												}
												if(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$3$$var296))?Math.log(cv$temp$2$var78[i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$3$$var296))?Math.log(cv$temp$2$var78[i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$3$$var296))?Math.log(cv$temp$2$var78[i]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$3$$var296))?Math.log(cv$temp$2$var78[i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$3$$var296))?Math.log(cv$temp$2$var78[i]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							int traceTempVariable$y$7_1 = cv$currentValue;
							{
								for(int var95 = 0; var95 < length$obs_measured; var95 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$4$p;
													{
														cv$temp$4$p = p;
													}
													if(((Math.log(1.0) + Math.log((obs[var95]?cv$temp$4$p:(1.0 - cv$temp$4$p)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((obs[var95]?cv$temp$4$p:(1.0 - cv$temp$4$p)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((obs[var95]?cv$temp$4$p:(1.0 - cv$temp$4$p))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((obs[var95]?cv$temp$4$p:(1.0 - cv$temp$4$p)))))) + 1)) + (Math.log(1.0) + Math.log((obs[var95]?cv$temp$4$p:(1.0 - cv$temp$4$p)))));
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
			y = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
			{
				{
					p = b[y][i];
				}
			}
		}
	}

	private final void sample84() {
		if(true) {
			int cv$numNumStates = 0;
			{
				int lengthCV$a$82_9 = -1;
				{
					if((1 == y))
						lengthCV$a$82_9 = 3;
				}
				{
					if((0 == y))
						lengthCV$a$82_9 = 2;
				}
				cv$numNumStates = Math.max(cv$numNumStates, lengthCV$a$82_9);
			}
			double[] cv$stateProbabilityLocal = cv$var80$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				i = cv$currentValue;
				{
					{
						p = b[y][cv$currentValue];
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var78;
					{
						double[] var78 = a[y];
						cv$temp$0$var78 = var78;
					}
					int cv$temp$1$$var318;
					{
						int lengthCV$a$82_10 = -1;
						{
							if((1 == y))
								lengthCV$a$82_10 = 3;
						}
						{
							if((0 == y))
								lengthCV$a$82_10 = 2;
						}
						int $var318 = lengthCV$a$82_10;
						cv$temp$1$$var318 = $var318;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var318))?Math.log(cv$temp$0$var78[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$i$6_1 = cv$currentValue;
							{
								for(int var95 = 0; var95 < length$obs_measured; var95 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$p;
													{
														cv$temp$2$p = p;
													}
													if(((Math.log(1.0) + Math.log((obs[var95]?cv$temp$2$p:(1.0 - cv$temp$2$p)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((obs[var95]?cv$temp$2$p:(1.0 - cv$temp$2$p)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((obs[var95]?cv$temp$2$p:(1.0 - cv$temp$2$p))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((obs[var95]?cv$temp$2$p:(1.0 - cv$temp$2$p)))))) + 1)) + (Math.log(1.0) + Math.log((obs[var95]?cv$temp$2$p:(1.0 - cv$temp$2$p)))));
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
			i = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
			{
				{
					p = b[y][i];
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var77$stateProbabilityGlobal = new double[2];
		}
		{
			int cv$var33$max = 2;
			cv$var33$max = Math.max(cv$var33$max, 3);
			cv$var80$stateProbabilityGlobal = new double[cv$var33$max];
		}
	}

	@Override
	public final void allocator() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		{
			b = new double[2][];
			b[0] = new double[2];
			b[1] = new double[3];
		}
		{
			c = new double[2];
		}
		{
			obs = new boolean[length$obs_measured];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		int lengthCV$a$82_12 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_12 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_12 = 2;
			}
		}
		if(!fixedFlag$sample84)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_12);
		if(!(fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, p);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		int lengthCV$a$82_14 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_14 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_14 = 2;
			}
		}
		if(!fixedFlag$sample84)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_14);
		if(!(fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		int lengthCV$a$82_13 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_13 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_13 = 2;
			}
		}
		if(!fixedFlag$sample84)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_13);
		if(!(fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample81)
				sample81();
			if(!fixedFlag$sample84)
				sample84();
		} else {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample81)
				sample81();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var5 = a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		double[] var37 = b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var76 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$y = 0.0;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$i = 0.0;
		logProbability$var83 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample100)
			logProbability$var96 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		int lengthCV$a$82_15 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_15 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample84)
					lengthCV$a$82_15 = 2;
			}
		}
		if(!fixedFlag$sample84)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_15);
		if(!(fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = obs_measured;
		boolean[] cv$target1 = obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if((fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
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
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}