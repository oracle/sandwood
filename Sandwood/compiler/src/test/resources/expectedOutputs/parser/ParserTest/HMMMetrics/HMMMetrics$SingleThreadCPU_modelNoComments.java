package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
	private boolean[] constrainedFlag$sample113;
	private boolean[] constrainedFlag$sample130;
	private boolean[] constrainedFlag$sample147;
	private boolean[] constrainedFlag$sample164;
	private boolean[] constrainedFlag$sample30;
	private boolean constrainedFlag$sample36 = true;
	private boolean constrainedFlag$sample39 = true;
	private boolean[] constrainedFlag$sample57;
	private boolean[] constrainedFlag$sample77;
	private boolean[] constrainedFlag$sample95;
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var55;
	private double[] cv$var30$countGlobal;
	private double[] cv$var35$countGlobal;
	private double[] cv$var38$stateProbabilityGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private double[] distribution$sample39;
	private double[][] distribution$sample57;
	private boolean fixedFlag$sample113 = false;
	private boolean fixedFlag$sample130 = false;
	private boolean fixedFlag$sample147 = false;
	private boolean fixedFlag$sample164 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample113 = false;
	private boolean fixedProbFlag$sample130 = false;
	private boolean fixedProbFlag$sample147 = false;
	private boolean fixedProbFlag$sample164 = false;
	private boolean fixedProbFlag$sample180 = false;
	private boolean fixedProbFlag$sample185 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample95 = false;
	private boolean[] guard$sample39gaussian179$global;
	private boolean[] guard$sample39gaussian184$global;
	private boolean[] guard$sample39gaussian189$global;
	private boolean[] guard$sample57gaussian179$global;
	private boolean[] guard$sample57gaussian184$global;
	private boolean[] guard$sample57gaussian189$global;
	private double[] initialStateDistribution;
	private int length$cpu_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cpu;
	private double logProbability$cpuMean;
	private double logProbability$cpuVar;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$mem;
	private double logProbability$memMean;
	private double logProbability$memVar;
	private double logProbability$pageFaults;
	private double logProbability$pageFaultsMean;
	private double logProbability$pageFaultsVar;
	private double[] logProbability$sample180;
	private double[] logProbability$sample185;
	private double[] logProbability$sample190;
	private double[] logProbability$sample57;
	private double logProbability$st;
	private double logProbability$var112;
	private double logProbability$var129;
	private double logProbability$var146;
	private double logProbability$var163;
	private double logProbability$var30;
	private double logProbability$var38;
	private double logProbability$var76;
	private double logProbability$var94;
	private double[][] m;
	private double[] mem;
	private double[] memMean;
	private double[] memVar;
	private double[] mem_measured;
	private int noStates;
	private double[] pageFaults;
	private double[] pageFaultsMean;
	private double[] pageFaultsVar;
	private double[] pageFaults_measured;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$cpu() {
		return cpu;
	}

	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	@Override
	public final void set$cpuMean(double[] cv$value) {
		cpuMean = cv$value;
		fixedProbFlag$sample77 = false;
		fixedProbFlag$sample180 = false;
	}

	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	@Override
	public final void set$cpuVar(double[] cv$value) {
		cpuVar = cv$value;
		fixedProbFlag$sample130 = false;
		fixedProbFlag$sample180 = false;
	}

	@Override
	public final double[] get$cpu_measured() {
		return cpu_measured;
	}

	@Override
	public final void set$cpu_measured(double[] cv$value) {
		cpu_measured = cv$value;
	}

	@Override
	public final double[] get$distribution$sample39() {
		return distribution$sample39;
	}

	@Override
	public final void set$distribution$sample39(double[] cv$value) {
		distribution$sample39 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	@Override
	public final void set$distribution$sample57(double[][] cv$value) {
		distribution$sample57 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	@Override
	public final void set$fixedFlag$sample113(boolean cv$value) {
		fixedFlag$sample113 = cv$value;
		fixedProbFlag$sample113 = (fixedFlag$sample113 && fixedProbFlag$sample113);
		fixedProbFlag$sample190 = (fixedFlag$sample113 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample130() {
		return fixedFlag$sample130;
	}

	@Override
	public final void set$fixedFlag$sample130(boolean cv$value) {
		fixedFlag$sample130 = cv$value;
		fixedProbFlag$sample130 = (fixedFlag$sample130 && fixedProbFlag$sample130);
		fixedProbFlag$sample180 = (fixedFlag$sample130 && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample147() {
		return fixedFlag$sample147;
	}

	@Override
	public final void set$fixedFlag$sample147(boolean cv$value) {
		fixedFlag$sample147 = cv$value;
		fixedProbFlag$sample147 = (fixedFlag$sample147 && fixedProbFlag$sample147);
		fixedProbFlag$sample185 = (fixedFlag$sample147 && fixedProbFlag$sample185);
	}

	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	@Override
	public final void set$fixedFlag$sample164(boolean cv$value) {
		fixedFlag$sample164 = cv$value;
		fixedProbFlag$sample164 = (fixedFlag$sample164 && fixedProbFlag$sample164);
		fixedProbFlag$sample190 = (fixedFlag$sample164 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample57 = (fixedFlag$sample30 && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
		fixedProbFlag$sample39 = (fixedFlag$sample36 && fixedProbFlag$sample39);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample57 = (fixedFlag$sample39 && fixedProbFlag$sample57);
		fixedProbFlag$sample180 = (fixedFlag$sample39 && fixedProbFlag$sample180);
		fixedProbFlag$sample185 = (fixedFlag$sample39 && fixedProbFlag$sample185);
		fixedProbFlag$sample190 = (fixedFlag$sample39 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample180 = (fixedFlag$sample57 && fixedProbFlag$sample180);
		fixedProbFlag$sample185 = (fixedFlag$sample57 && fixedProbFlag$sample185);
		fixedProbFlag$sample190 = (fixedFlag$sample57 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		fixedFlag$sample77 = cv$value;
		fixedProbFlag$sample77 = (fixedFlag$sample77 && fixedProbFlag$sample77);
		fixedProbFlag$sample180 = (fixedFlag$sample77 && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		fixedProbFlag$sample185 = (fixedFlag$sample95 && fixedProbFlag$sample185);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample39 = false;
	}

	@Override
	public final int get$length$cpu_measured() {
		return length$cpu_measured;
	}

	@Override
	public final void set$length$cpu_measured(int cv$value) {
		length$cpu_measured = cv$value;
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
	public final double get$logProbability$cpu() {
		return logProbability$cpu;
	}

	@Override
	public final double get$logProbability$cpuMean() {
		return logProbability$cpuMean;
	}

	@Override
	public final double get$logProbability$cpuVar() {
		return logProbability$cpuVar;
	}

	@Override
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$mem() {
		return logProbability$mem;
	}

	@Override
	public final double get$logProbability$memMean() {
		return logProbability$memMean;
	}

	@Override
	public final double get$logProbability$memVar() {
		return logProbability$memVar;
	}

	@Override
	public final double get$logProbability$pageFaults() {
		return logProbability$pageFaults;
	}

	@Override
	public final double get$logProbability$pageFaultsMean() {
		return logProbability$pageFaultsMean;
	}

	@Override
	public final double get$logProbability$pageFaultsVar() {
		return logProbability$pageFaultsVar;
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
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample57 = false;
	}

	@Override
	public final double[] get$mem() {
		return mem;
	}

	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	@Override
	public final void set$memMean(double[] cv$value) {
		memMean = cv$value;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample185 = false;
	}

	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	@Override
	public final void set$memVar(double[] cv$value) {
		memVar = cv$value;
		fixedProbFlag$sample147 = false;
		fixedProbFlag$sample185 = false;
	}

	@Override
	public final double[] get$mem_measured() {
		return mem_measured;
	}

	@Override
	public final void set$mem_measured(double[] cv$value) {
		mem_measured = cv$value;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	@Override
	public final double[] get$pageFaults() {
		return pageFaults;
	}

	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		pageFaultsMean = cv$value;
		fixedProbFlag$sample113 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		pageFaultsVar = cv$value;
		fixedProbFlag$sample164 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$pageFaults_measured() {
		return pageFaults_measured;
	}

	@Override
	public final void set$pageFaults_measured(double[] cv$value) {
		pageFaults_measured = cv$value;
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
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample180 = false;
		fixedProbFlag$sample185 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample180() {
		if(!fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = cpu[i$var174];
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var75 = 0; var75 < noStates; var75 += 1) {
											if((var75 == st[i$var174])) {
												{
													if((0 == i$var174)) {
														{
															for(int var128 = 0; var128 < noStates; var128 += 1) {
																if((var128 == st[i$var174])) {
																	{
																		double var176 = cpuMean[st[i$var174]];
																		double var177 = cpuVar[st[i$var174]];
																		double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
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
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
									int distributionTempVariable$var38$5 = index$sample39$3;
									double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
									{
										int traceTempVariable$s$6_1 = distributionTempVariable$var38$5;
										if((0 == i$var174)) {
											{
												for(int var75 = 0; var75 < noStates; var75 += 1) {
													if((var75 == traceTempVariable$s$6_1)) {
														{
															int traceTempVariable$s$10_1 = distributionTempVariable$var38$5;
															if((0 == i$var174)) {
																{
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == traceTempVariable$s$10_1)) {
																			{
																				double var176 = cpuMean[traceTempVariable$s$10_1];
																				double var177 = cpuVar[traceTempVariable$s$10_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
																int distributionTempVariable$var38$13 = index$sample39$11;
																double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
																{
																	int traceTempVariable$s$14_1 = distributionTempVariable$var38$13;
																	if((0 == i$var174)) {
																		{
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == traceTempVariable$s$14_1)) {
																					{
																						double var176 = cpuMean[traceTempVariable$s$14_1];
																						double var177 = cpuVar[traceTempVariable$s$14_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value12);
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
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var75 = 0; var75 < noStates; var75 += 1) {
											if((var75 == st[i$var174])) {
												if(fixedFlag$sample57) {
													{
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																{
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				double var176 = cpuMean[st[i$var174]];
																				double var177 = cpuVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													}
												} else {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if(true) {
															for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																int distributionTempVariable$var56$29 = index$sample57$27;
																double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																{
																	int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																	if((i$var50 == i$var174)) {
																		{
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == traceTempVariable$s$30_1)) {
																					{
																						double var176 = cpuMean[traceTempVariable$s$30_1];
																						double var177 = cpuVar[traceTempVariable$s$30_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value28);
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
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
									int distributionTempVariable$var38$21 = index$sample39$19;
									double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
									{
										int traceTempVariable$s$22_1 = distributionTempVariable$var38$21;
										if((0 == i$var174)) {
											{
												for(int var75 = 0; var75 < noStates; var75 += 1) {
													if((var75 == traceTempVariable$s$22_1)) {
														if(fixedFlag$sample57) {
															{
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if((i$var50 == i$var174)) {
																		{
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == traceTempVariable$s$22_1)) {
																					{
																						double var176 = cpuMean[traceTempVariable$s$22_1];
																						double var177 = cpuVar[traceTempVariable$s$22_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value20);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if(true) {
																	for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
																		int distributionTempVariable$var56$35 = index$sample57$33;
																		double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
																		{
																			int traceTempVariable$s$36_1 = distributionTempVariable$var56$35;
																			if((i$var50 == i$var174)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == traceTempVariable$s$36_1)) {
																							{
																								double var176 = cpuMean[traceTempVariable$s$36_1];
																								double var177 = cpuVar[traceTempVariable$s$36_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value34);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var75 = 0; var75 < noStates; var75 += 1) {
												if((var75 == st[i$var174])) {
													{
														for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
															if((index$i$49_1 == i$var174)) {
																{
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				double var176 = cpuMean[st[i$var174]];
																				double var177 = cpuVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
										int distributionTempVariable$var56$45 = index$sample57$43;
										double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
										{
											int traceTempVariable$s$46_1 = distributionTempVariable$var56$45;
											if((i$var50 == i$var174)) {
												{
													for(int var75 = 0; var75 < noStates; var75 += 1) {
														if((var75 == traceTempVariable$s$46_1)) {
															{
																int traceTempVariable$s$50_1 = distributionTempVariable$var56$45;
																if((i$var50 == i$var174)) {
																	{
																		for(int var128 = 0; var128 < noStates; var128 += 1) {
																			if((var128 == traceTempVariable$s$50_1)) {
																				{
																					double var176 = cpuMean[traceTempVariable$s$50_1];
																					double var177 = cpuVar[traceTempVariable$s$50_1];
																					double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
																if(!(index$i$51 == i$var50)) {
																	for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
																		int distributionTempVariable$var56$54 = index$sample57$52;
																		double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
																		{
																			int traceTempVariable$s$55_1 = distributionTempVariable$var56$54;
																			if((index$i$51 == i$var174)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == traceTempVariable$s$55_1)) {
																							{
																								double var176 = cpuMean[traceTempVariable$s$55_1];
																								double var177 = cpuVar[traceTempVariable$s$55_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value53);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var75 = 0; var75 < noStates; var75 += 1) {
												if((var75 == st[i$var174])) {
													if(fixedFlag$sample39) {
														{
															if((0 == i$var174)) {
																{
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				double var176 = cpuMean[st[i$var174]];
																				double var177 = cpuVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
																int distributionTempVariable$var38$70 = index$sample39$68;
																double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
																{
																	int traceTempVariable$s$71_1 = distributionTempVariable$var38$70;
																	if((0 == i$var174)) {
																		{
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == traceTempVariable$s$71_1)) {
																					{
																						double var176 = cpuMean[traceTempVariable$s$71_1];
																						double var177 = cpuVar[traceTempVariable$s$71_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value69);
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
											}
										}
									}
								}
							}
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
										int distributionTempVariable$var56$63 = index$sample57$61;
										double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
										{
											int traceTempVariable$s$64_1 = distributionTempVariable$var56$63;
											if((i$var50 == i$var174)) {
												{
													for(int var75 = 0; var75 < noStates; var75 += 1) {
														if((var75 == traceTempVariable$s$64_1)) {
															if(fixedFlag$sample39) {
																{
																	if((0 == i$var174)) {
																		{
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == traceTempVariable$s$64_1)) {
																					{
																						double var176 = cpuMean[traceTempVariable$s$64_1];
																						double var177 = cpuVar[traceTempVariable$s$64_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value62);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
																		int distributionTempVariable$var38$75 = index$sample39$73;
																		double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
																		{
																			int traceTempVariable$s$76_1 = distributionTempVariable$var38$75;
																			if((0 == i$var174)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == traceTempVariable$s$76_1)) {
																							{
																								double var176 = cpuMean[traceTempVariable$s$76_1];
																								double var177 = cpuVar[traceTempVariable$s$76_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value74);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample180[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample180[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample185() {
		if(!fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = mem[i$var174];
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var93 = 0; var93 < noStates; var93 += 1) {
											if((var93 == st[i$var174])) {
												{
													if((0 == i$var174)) {
														{
															for(int var145 = 0; var145 < noStates; var145 += 1) {
																if((var145 == st[i$var174])) {
																	{
																		double var181 = memMean[st[i$var174]];
																		double var182 = memVar[st[i$var174]];
																		double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
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
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
									int distributionTempVariable$var38$5 = index$sample39$3;
									double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
									{
										int traceTempVariable$s$6_1 = distributionTempVariable$var38$5;
										if((0 == i$var174)) {
											{
												for(int var93 = 0; var93 < noStates; var93 += 1) {
													if((var93 == traceTempVariable$s$6_1)) {
														{
															int traceTempVariable$s$10_1 = distributionTempVariable$var38$5;
															if((0 == i$var174)) {
																{
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == traceTempVariable$s$10_1)) {
																			{
																				double var181 = memMean[traceTempVariable$s$10_1];
																				double var182 = memVar[traceTempVariable$s$10_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
																int distributionTempVariable$var38$13 = index$sample39$11;
																double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
																{
																	int traceTempVariable$s$14_1 = distributionTempVariable$var38$13;
																	if((0 == i$var174)) {
																		{
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == traceTempVariable$s$14_1)) {
																					{
																						double var181 = memMean[traceTempVariable$s$14_1];
																						double var182 = memVar[traceTempVariable$s$14_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value12);
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
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var93 = 0; var93 < noStates; var93 += 1) {
											if((var93 == st[i$var174])) {
												if(fixedFlag$sample57) {
													{
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																{
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				double var181 = memMean[st[i$var174]];
																				double var182 = memVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													}
												} else {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if(true) {
															for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																int distributionTempVariable$var56$29 = index$sample57$27;
																double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																{
																	int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																	if((i$var50 == i$var174)) {
																		{
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == traceTempVariable$s$30_1)) {
																					{
																						double var181 = memMean[traceTempVariable$s$30_1];
																						double var182 = memVar[traceTempVariable$s$30_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value28);
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
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
									int distributionTempVariable$var38$21 = index$sample39$19;
									double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
									{
										int traceTempVariable$s$22_1 = distributionTempVariable$var38$21;
										if((0 == i$var174)) {
											{
												for(int var93 = 0; var93 < noStates; var93 += 1) {
													if((var93 == traceTempVariable$s$22_1)) {
														if(fixedFlag$sample57) {
															{
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if((i$var50 == i$var174)) {
																		{
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == traceTempVariable$s$22_1)) {
																					{
																						double var181 = memMean[traceTempVariable$s$22_1];
																						double var182 = memVar[traceTempVariable$s$22_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value20);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if(true) {
																	for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
																		int distributionTempVariable$var56$35 = index$sample57$33;
																		double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
																		{
																			int traceTempVariable$s$36_1 = distributionTempVariable$var56$35;
																			if((i$var50 == i$var174)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == traceTempVariable$s$36_1)) {
																							{
																								double var181 = memMean[traceTempVariable$s$36_1];
																								double var182 = memVar[traceTempVariable$s$36_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value34);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var93 = 0; var93 < noStates; var93 += 1) {
												if((var93 == st[i$var174])) {
													{
														for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
															if((index$i$49_1 == i$var174)) {
																{
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				double var181 = memMean[st[i$var174]];
																				double var182 = memVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
										int distributionTempVariable$var56$45 = index$sample57$43;
										double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
										{
											int traceTempVariable$s$46_1 = distributionTempVariable$var56$45;
											if((i$var50 == i$var174)) {
												{
													for(int var93 = 0; var93 < noStates; var93 += 1) {
														if((var93 == traceTempVariable$s$46_1)) {
															{
																int traceTempVariable$s$50_1 = distributionTempVariable$var56$45;
																if((i$var50 == i$var174)) {
																	{
																		for(int var145 = 0; var145 < noStates; var145 += 1) {
																			if((var145 == traceTempVariable$s$50_1)) {
																				{
																					double var181 = memMean[traceTempVariable$s$50_1];
																					double var182 = memVar[traceTempVariable$s$50_1];
																					double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
																if(!(index$i$51 == i$var50)) {
																	for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
																		int distributionTempVariable$var56$54 = index$sample57$52;
																		double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
																		{
																			int traceTempVariable$s$55_1 = distributionTempVariable$var56$54;
																			if((index$i$51 == i$var174)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == traceTempVariable$s$55_1)) {
																							{
																								double var181 = memMean[traceTempVariable$s$55_1];
																								double var182 = memVar[traceTempVariable$s$55_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value53);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var93 = 0; var93 < noStates; var93 += 1) {
												if((var93 == st[i$var174])) {
													if(fixedFlag$sample39) {
														{
															if((0 == i$var174)) {
																{
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				double var181 = memMean[st[i$var174]];
																				double var182 = memVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
																int distributionTempVariable$var38$70 = index$sample39$68;
																double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
																{
																	int traceTempVariable$s$71_1 = distributionTempVariable$var38$70;
																	if((0 == i$var174)) {
																		{
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == traceTempVariable$s$71_1)) {
																					{
																						double var181 = memMean[traceTempVariable$s$71_1];
																						double var182 = memVar[traceTempVariable$s$71_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value69);
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
											}
										}
									}
								}
							}
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
										int distributionTempVariable$var56$63 = index$sample57$61;
										double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
										{
											int traceTempVariable$s$64_1 = distributionTempVariable$var56$63;
											if((i$var50 == i$var174)) {
												{
													for(int var93 = 0; var93 < noStates; var93 += 1) {
														if((var93 == traceTempVariable$s$64_1)) {
															if(fixedFlag$sample39) {
																{
																	if((0 == i$var174)) {
																		{
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == traceTempVariable$s$64_1)) {
																					{
																						double var181 = memMean[traceTempVariable$s$64_1];
																						double var182 = memVar[traceTempVariable$s$64_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value62);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
																		int distributionTempVariable$var38$75 = index$sample39$73;
																		double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
																		{
																			int traceTempVariable$s$76_1 = distributionTempVariable$var38$75;
																			if((0 == i$var174)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == traceTempVariable$s$76_1)) {
																							{
																								double var181 = memMean[traceTempVariable$s$76_1];
																								double var182 = memVar[traceTempVariable$s$76_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value74);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample185[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample185[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = pageFaults[i$var174];
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var111 = 0; var111 < noStates; var111 += 1) {
											if((var111 == st[i$var174])) {
												{
													if((0 == i$var174)) {
														{
															for(int var162 = 0; var162 < noStates; var162 += 1) {
																if((var162 == st[i$var174])) {
																	{
																		double var186 = pageFaultsMean[st[i$var174]];
																		double var187 = pageFaultsVar[st[i$var174]];
																		double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
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
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
									int distributionTempVariable$var38$5 = index$sample39$3;
									double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
									{
										int traceTempVariable$s$6_1 = distributionTempVariable$var38$5;
										if((0 == i$var174)) {
											{
												for(int var111 = 0; var111 < noStates; var111 += 1) {
													if((var111 == traceTempVariable$s$6_1)) {
														{
															int traceTempVariable$s$10_1 = distributionTempVariable$var38$5;
															if((0 == i$var174)) {
																{
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == traceTempVariable$s$10_1)) {
																			{
																				double var186 = pageFaultsMean[traceTempVariable$s$10_1];
																				double var187 = pageFaultsVar[traceTempVariable$s$10_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
																int distributionTempVariable$var38$13 = index$sample39$11;
																double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
																{
																	int traceTempVariable$s$14_1 = distributionTempVariable$var38$13;
																	if((0 == i$var174)) {
																		{
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == traceTempVariable$s$14_1)) {
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$14_1];
																						double var187 = pageFaultsVar[traceTempVariable$s$14_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value12);
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
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample39) {
							{
								if((0 == i$var174)) {
									{
										for(int var111 = 0; var111 < noStates; var111 += 1) {
											if((var111 == st[i$var174])) {
												if(fixedFlag$sample57) {
													{
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																{
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				double var187 = pageFaultsVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													}
												} else {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if(true) {
															for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																int distributionTempVariable$var56$29 = index$sample57$27;
																double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																{
																	int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																	if((i$var50 == i$var174)) {
																		{
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == traceTempVariable$s$30_1)) {
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$30_1];
																						double var187 = pageFaultsVar[traceTempVariable$s$30_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value28);
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
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
									int distributionTempVariable$var38$21 = index$sample39$19;
									double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
									{
										int traceTempVariable$s$22_1 = distributionTempVariable$var38$21;
										if((0 == i$var174)) {
											{
												for(int var111 = 0; var111 < noStates; var111 += 1) {
													if((var111 == traceTempVariable$s$22_1)) {
														if(fixedFlag$sample57) {
															{
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if((i$var50 == i$var174)) {
																		{
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == traceTempVariable$s$22_1)) {
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$22_1];
																						double var187 = pageFaultsVar[traceTempVariable$s$22_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value20);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if(true) {
																	for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
																		int distributionTempVariable$var56$35 = index$sample57$33;
																		double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
																		{
																			int traceTempVariable$s$36_1 = distributionTempVariable$var56$35;
																			if((i$var50 == i$var174)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == traceTempVariable$s$36_1)) {
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$36_1];
																								double var187 = pageFaultsVar[traceTempVariable$s$36_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value34);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var111 = 0; var111 < noStates; var111 += 1) {
												if((var111 == st[i$var174])) {
													{
														for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
															if((index$i$49_1 == i$var174)) {
																{
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				double var187 = pageFaultsVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
										int distributionTempVariable$var56$45 = index$sample57$43;
										double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
										{
											int traceTempVariable$s$46_1 = distributionTempVariable$var56$45;
											if((i$var50 == i$var174)) {
												{
													for(int var111 = 0; var111 < noStates; var111 += 1) {
														if((var111 == traceTempVariable$s$46_1)) {
															{
																int traceTempVariable$s$50_1 = distributionTempVariable$var56$45;
																if((i$var50 == i$var174)) {
																	{
																		for(int var162 = 0; var162 < noStates; var162 += 1) {
																			if((var162 == traceTempVariable$s$50_1)) {
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$50_1];
																					double var187 = pageFaultsVar[traceTempVariable$s$50_1];
																					double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
																if(!(index$i$51 == i$var50)) {
																	for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
																		int distributionTempVariable$var56$54 = index$sample57$52;
																		double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
																		{
																			int traceTempVariable$s$55_1 = distributionTempVariable$var56$54;
																			if((index$i$51 == i$var174)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == traceTempVariable$s$55_1)) {
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$55_1];
																								double var187 = pageFaultsVar[traceTempVariable$s$55_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value53);
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
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample57) {
							{
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										{
											for(int var111 = 0; var111 < noStates; var111 += 1) {
												if((var111 == st[i$var174])) {
													if(fixedFlag$sample39) {
														{
															if((0 == i$var174)) {
																{
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				double var187 = pageFaultsVar[st[i$var174]];
																				double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
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
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
																int distributionTempVariable$var38$70 = index$sample39$68;
																double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
																{
																	int traceTempVariable$s$71_1 = distributionTempVariable$var38$70;
																	if((0 == i$var174)) {
																		{
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == traceTempVariable$s$71_1)) {
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$71_1];
																						double var187 = pageFaultsVar[traceTempVariable$s$71_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value69);
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
											}
										}
									}
								}
							}
						} else {
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
										int distributionTempVariable$var56$63 = index$sample57$61;
										double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
										{
											int traceTempVariable$s$64_1 = distributionTempVariable$var56$63;
											if((i$var50 == i$var174)) {
												{
													for(int var111 = 0; var111 < noStates; var111 += 1) {
														if((var111 == traceTempVariable$s$64_1)) {
															if(fixedFlag$sample39) {
																{
																	if((0 == i$var174)) {
																		{
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == traceTempVariable$s$64_1)) {
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$64_1];
																						double var187 = pageFaultsVar[traceTempVariable$s$64_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value62);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
																		int distributionTempVariable$var38$75 = index$sample39$73;
																		double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
																		{
																			int traceTempVariable$s$76_1 = distributionTempVariable$var38$75;
																			if((0 == i$var174)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == traceTempVariable$s$76_1)) {
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$76_1];
																								double var187 = pageFaultsVar[traceTempVariable$s$76_1];
																								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value74);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample190[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample39() {
		if(!fixedProbFlag$sample39) {
			if(fixedFlag$sample39) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = st[0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var38 = cv$sampleProbability;
				if(fixedFlag$sample39)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample39)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedFlag$sample36);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var38;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(fixedFlag$sample39)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var50;
					{
						{
							int cv$sampleValue = st[i$var50];
							if(fixedFlag$sample39) {
								{
									if((0 == (i$var50 - 1))) {
										{
											for(int var29 = 0; var29 < noStates; var29 += 1) {
												if((var29 == st[(i$var50 - 1)])) {
													{
														double[] var54 = m[st[(i$var50 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										}
									}
								}
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										{
											int traceTempVariable$var53$7_1 = distributionTempVariable$var38$6;
											if((0 == (i$var50 - 1))) {
												{
													for(int var29 = 0; var29 < noStates; var29 += 1) {
														if((var29 == traceTempVariable$var53$7_1)) {
															{
																double[] var54 = m[traceTempVariable$var53$7_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value5);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							{
								if((index$i$1 == (i$var50 - 1))) {
									{
										for(int var29 = 0; var29 < noStates; var29 += 1) {
											if((var29 == st[(i$var50 - 1)])) {
												{
													double[] var54 = m[st[(i$var50 - 1)]];
													double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									}
								}
							}
							if(fixedFlag$sample57) {
								{
									for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
										if((index$i$11_1 == (i$var50 - 1))) {
											{
												for(int var29 = 0; var29 < noStates; var29 += 1) {
													if((var29 == st[(i$var50 - 1)])) {
														{
															double[] var54 = m[st[(i$var50 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											}
										}
									}
								}
							} else {
								for(int index$i$12 = 1; index$i$12 < samples; index$i$12 += 1) {
									if(!(index$i$12 == index$i$1)) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((index$i$12 - 1) / 1)][index$sample57$13]);
											{
												int traceTempVariable$var53$16_1 = distributionTempVariable$var56$15;
												if((index$i$12 == (i$var50 - 1))) {
													{
														for(int var29 = 0; var29 < noStates; var29 += 1) {
															if((var29 == traceTempVariable$var53$16_1)) {
																{
																	double[] var54 = m[traceTempVariable$var53$16_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value14);
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
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample57[((i$var50 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample57)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample57)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((i$var50 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(fixedFlag$sample57)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample113() {
		if(!fixedProbFlag$sample113) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var111 = 0; var111 < noStates; var111 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = pageFaultsMean[var111];
						{
							{
								double var98 = 814.0;
								double var99 = 335550.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var99)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var98) / Math.sqrt(var99))) - (0.5 * Math.log(var99))):Double.NEGATIVE_INFINITY));
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
			logProbability$var112 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample113 = fixedFlag$sample113;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var111 = 0; var111 < noStates; var111 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var112;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample130() {
		if(!fixedProbFlag$sample130) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var128 = 0; var128 < noStates; var128 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = cpuVar[var128];
						{
							{
								double var116 = 5.0;
								double var115 = 0.5;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var116, var115));
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
			logProbability$var129 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample130 = fixedFlag$sample130;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var129;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample147() {
		if(!fixedProbFlag$sample147) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var145 = 0; var145 < noStates; var145 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = memVar[var145];
						{
							{
								double var133 = 5.0;
								double var132 = 0.5;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var133, var132));
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
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample147 = fixedFlag$sample147;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var145 = 0; var145 < noStates; var145 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var146;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var162 = 0; var162 < noStates; var162 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = pageFaultsVar[var162];
						{
							{
								double var150 = 5.0;
								double var149 = 0.5;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var150, var149));
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
			logProbability$var163 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample164 = fixedFlag$sample164;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var162 = 0; var162 < noStates; var162 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var163;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample180() {
		if(!fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = cpu[i$var174];
						{
							{
								double var176 = cpuMean[st[i$var174]];
								double var177 = cpuVar[st[i$var174]];
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample180[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample180[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample185() {
		if(!fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = mem[i$var174];
						{
							{
								double var181 = memMean[st[i$var174]];
								double var182 = memVar[st[i$var174]];
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample185[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample185[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = pageFaults[i$var174];
						{
							{
								double var186 = pageFaultsMean[st[i$var174]];
								double var187 = pageFaultsVar[st[i$var174]];
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample190[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < noStates; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var29];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < noStates; var29 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = initialStateDistribution;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = st[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var38 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var38;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var50;
				{
					{
						int cv$sampleValue = st[i$var50];
						{
							{
								double[] var54 = m[st[(i$var50 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample57[((i$var50 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((i$var50 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var75 = 0; var75 < noStates; var75 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = cpuMean[var75];
						{
							{
								double var63 = 16.0;
								double var62 = 8.6;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var62)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var63) / Math.sqrt(var62))) - (0.5 * Math.log(var62))):Double.NEGATIVE_INFINITY));
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
			logProbability$var76 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var76;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var93 = 0; var93 < noStates; var93 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = memMean[var93];
						{
							{
								double var80 = 94.0;
								double var81 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var81)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var80) / Math.sqrt(var81))) - (0.5 * Math.log(var81))):Double.NEGATIVE_INFINITY));
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
			logProbability$var94 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var93 = 0; var93 < noStates; var93 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var94;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample113(int var111) {
		if(true) {
			constrainedFlag$sample113[((var111 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = pageFaultsMean[var111];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample113[((var111 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var112 = cv$proposedValue;
						{
							{
								{
									pageFaultsMean[var111] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 335550.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 814.0) / Math.sqrt(335550.0))) - (0.5 * Math.log(335550.0))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var186$8_1 = cv$currentValue;
													if((var111 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample113[((var111 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == st[i$var174])) {
																							{
																								{
																									{
																										double var187 = pageFaultsVar[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == st[i$var174])) {
																									{
																										{
																											{
																												double var187 = pageFaultsVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var187 = pageFaultsVar[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$8_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var186$9_1 = cv$currentValue;
															if((var111 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample113[((var111 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var187 = pageFaultsVar[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var187 = pageFaultsVar[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var187 = pageFaultsVar[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var162 = 0; var162 < noStates; var162 += 1) {
																												if((var162 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var187 = pageFaultsVar[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$9_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var186$17_1 = cv$currentValue;
														if((var111 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample113[((var111 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == st[i$var174])) {
																									{
																										{
																											{
																												double var187 = pageFaultsVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var187 = pageFaultsVar[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == st[i$var174])) {
																									{
																										{
																											{
																												double var187 = pageFaultsVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$17_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var186$18_1 = cv$currentValue;
																if((var111 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample113[((var111 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var187 = pageFaultsVar[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var162 = 0; var162 < noStates; var162 += 1) {
																												if((var162 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var187 = pageFaultsVar[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var162 = 0; var162 < noStates; var162 += 1) {
																									if((var162 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var187 = pageFaultsVar[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var162 = 0; var162 < noStates; var162 += 1) {
																												if((var162 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var187 = pageFaultsVar[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - traceTempVariable$var186$18_1) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var112 = cv$originalValue;
							{
								{
									{
										pageFaultsMean[var111] = var112;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample130(int var128) {
		if(true) {
			constrainedFlag$sample130[((var128 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = cpuVar[var128];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample130[((var128 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var129 = cv$proposedValue;
						{
							{
								{
									cpuVar[var128] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 5.0, 0.5));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var177$8_1 = cv$currentValue;
													if((var128 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample130[((var128 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var75 = 0; var75 < noStates; var75 += 1) {
																						if((var75 == st[i$var174])) {
																							{
																								{
																									{
																										double var176 = cpuMean[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == st[i$var174])) {
																									{
																										{
																											{
																												double var176 = cpuMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var177$8_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$8_1))) - (0.5 * Math.log(traceTempVariable$var177$8_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var177$9_1 = cv$currentValue;
															if((var128 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample130[((var128 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var176 = cpuMean[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var75 = 0; var75 < noStates; var75 += 1) {
																												if((var75 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var176 = cpuMean[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var177$9_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$9_1))) - (0.5 * Math.log(traceTempVariable$var177$9_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var177$17_1 = cv$currentValue;
														if((var128 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample130[((var128 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == st[i$var174])) {
																									{
																										{
																											{
																												double var176 = cpuMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == st[i$var174])) {
																									{
																										{
																											{
																												double var176 = cpuMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var177$17_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$17_1))) - (0.5 * Math.log(traceTempVariable$var177$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var177$18_1 = cv$currentValue;
																if((var128 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample130[((var128 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var75 = 0; var75 < noStates; var75 += 1) {
																												if((var75 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var176 = cpuMean[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var75 = 0; var75 < noStates; var75 += 1) {
																									if((var75 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var176 = cpuMean[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var75 = 0; var75 < noStates; var75 += 1) {
																												if((var75 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var176 = cpuMean[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var177$18_1)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(traceTempVariable$var177$18_1))) - (0.5 * Math.log(traceTempVariable$var177$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var129 = cv$originalValue;
							{
								{
									{
										cpuVar[var128] = var129;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample147(int var145) {
		if(true) {
			constrainedFlag$sample147[((var145 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = memVar[var145];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample147[((var145 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var146 = cv$proposedValue;
						{
							{
								{
									memVar[var145] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 5.0, 0.5));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var182$8_1 = cv$currentValue;
													if((var145 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample147[((var145 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var93 = 0; var93 < noStates; var93 += 1) {
																						if((var93 == st[i$var174])) {
																							{
																								{
																									{
																										double var181 = memMean[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == st[i$var174])) {
																									{
																										{
																											{
																												double var181 = memMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var182$8_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$8_1))) - (0.5 * Math.log(traceTempVariable$var182$8_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var182$9_1 = cv$currentValue;
															if((var145 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample147[((var145 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var181 = memMean[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var93 = 0; var93 < noStates; var93 += 1) {
																												if((var93 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var181 = memMean[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var182$9_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$9_1))) - (0.5 * Math.log(traceTempVariable$var182$9_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var182$17_1 = cv$currentValue;
														if((var145 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample147[((var145 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == st[i$var174])) {
																									{
																										{
																											{
																												double var181 = memMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == st[i$var174])) {
																									{
																										{
																											{
																												double var181 = memMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var182$17_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$17_1))) - (0.5 * Math.log(traceTempVariable$var182$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var182$18_1 = cv$currentValue;
																if((var145 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample147[((var145 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var93 = 0; var93 < noStates; var93 += 1) {
																												if((var93 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var181 = memMean[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var93 = 0; var93 < noStates; var93 += 1) {
																									if((var93 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var181 = memMean[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var93 = 0; var93 < noStates; var93 += 1) {
																												if((var93 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var181 = memMean[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var182$18_1)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(traceTempVariable$var182$18_1))) - (0.5 * Math.log(traceTempVariable$var182$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var146 = cv$originalValue;
							{
								{
									{
										memVar[var145] = var146;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample164(int var162) {
		if(true) {
			constrainedFlag$sample164[((var162 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = pageFaultsVar[var162];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample164[((var162 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var163 = cv$proposedValue;
						{
							{
								{
									pageFaultsVar[var162] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 5.0, 0.5));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var187$8_1 = cv$currentValue;
													if((var162 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample164[((var162 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var111 = 0; var111 < noStates; var111 += 1) {
																						if((var111 == st[i$var174])) {
																							{
																								{
																									{
																										double var186 = pageFaultsMean[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == st[i$var174])) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < traceTempVariable$var187$8_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$8_1))) - (0.5 * Math.log(traceTempVariable$var187$8_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var187$9_1 = cv$currentValue;
															if((var162 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample164[((var162 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var111 = 0; var111 < noStates; var111 += 1) {
																												if((var111 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var186 = pageFaultsMean[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var187$9_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$9_1))) - (0.5 * Math.log(traceTempVariable$var187$9_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var187$17_1 = cv$currentValue;
														if((var162 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample164[((var162 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == st[i$var174])) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == st[i$var174])) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var187$17_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$17_1))) - (0.5 * Math.log(traceTempVariable$var187$17_1))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var187$18_1 = cv$currentValue;
																if((var162 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample164[((var162 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var111 = 0; var111 < noStates; var111 += 1) {
																												if((var111 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var186 = pageFaultsMean[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var111 = 0; var111 < noStates; var111 += 1) {
																									if((var111 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var186 = pageFaultsMean[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var111 = 0; var111 < noStates; var111 += 1) {
																												if((var111 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var186 = pageFaultsMean[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < traceTempVariable$var187$18_1)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(traceTempVariable$var187$18_1))) - (0.5 * Math.log(traceTempVariable$var187$18_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var163 = cv$originalValue;
							{
								{
									{
										pageFaultsVar[var162] = var163;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample30(int var29) {
		if(true) {
			constrainedFlag$sample30[((var29 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var29];
			double[] cv$countLocal = cv$var30$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(fixedFlag$sample39) {
								{
									if((0 == (i$var50 - 1))) {
										{
											if((var29 == st[(i$var50 - 1)])) {
												if(fixedFlag$sample57) {
													{
														{
															int index$i$19 = i$var50;
															boolean cv$sampleConstrained = (fixedFlag$sample57 || constrainedFlag$sample57[((i$var50 - 1) / 1)]);
															if(cv$sampleConstrained) {
																constrainedFlag$sample30[((var29 - 0) / 1)] = true;
																{
																	{
																		{
																			{
																				{
																					cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + 1.0);
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
										}
									}
								}
							} else {
								if(true) {
									for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
										int distributionTempVariable$var38$5 = index$sample39$3;
										double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
										{
											int traceTempVariable$var53$6_1 = distributionTempVariable$var38$5;
											if((0 == (i$var50 - 1))) {
												{
													if((var29 == traceTempVariable$var53$6_1)) {
														if(fixedFlag$sample57) {
															{
																{
																	int index$i$21 = i$var50;
																	boolean cv$sampleConstrained = (fixedFlag$sample57 || constrainedFlag$sample57[((i$var50 - 1) / 1)]);
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample30[((var29 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + cv$probabilitySample39Value4);
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
												}
											}
										}
									}
								}
							}
						}
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(fixedFlag$sample57) {
								{
									for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
										if((index$i$10_1 == (i$var50 - 1))) {
											{
												if((var29 == st[(i$var50 - 1)])) {
													if(fixedFlag$sample57) {
														{
															{
																int index$i$23 = i$var50;
																boolean cv$sampleConstrained = (fixedFlag$sample57 || constrainedFlag$sample57[((i$var50 - 1) / 1)]);
																if(cv$sampleConstrained) {
																	constrainedFlag$sample30[((var29 - 0) / 1)] = true;
																	{
																		{
																			{
																				{
																					{
																						cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + 1.0);
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
											}
										}
									}
								}
							} else {
								for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
									if(true) {
										for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
											int distributionTempVariable$var56$14 = index$sample57$12;
											double cv$probabilitySample57Value13 = (1.0 * distribution$sample57[((index$i$11 - 1) / 1)][index$sample57$12]);
											{
												int traceTempVariable$var53$15_1 = distributionTempVariable$var56$14;
												if((index$i$11 == (i$var50 - 1))) {
													{
														if((var29 == traceTempVariable$var53$15_1)) {
															if(fixedFlag$sample57) {
																{
																	{
																		int index$i$25 = i$var50;
																		boolean cv$sampleConstrained = (fixedFlag$sample57 || constrainedFlag$sample57[((i$var50 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample30[((var29 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + cv$probabilitySample57Value13);
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
			}
			{
				{
					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
						if(fixedFlag$sample39) {
							{
								if((0 == (i$var50 - 1))) {
									{
										if((var29 == st[(i$var50 - 1)])) {
											if(!fixedFlag$sample57) {
												{
													{
														int index$i$48 = i$var50;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((i$var50 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample39$32 = 0; index$sample39$32 < noStates; index$sample39$32 += 1) {
									int distributionTempVariable$var38$34 = index$sample39$32;
									double cv$probabilitySample39Value33 = (1.0 * distribution$sample39[index$sample39$32]);
									{
										int traceTempVariable$var53$35_1 = distributionTempVariable$var38$34;
										if((0 == (i$var50 - 1))) {
											{
												if((var29 == traceTempVariable$var53$35_1)) {
													if(!fixedFlag$sample57) {
														{
															{
																int index$i$50 = i$var50;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample39Value33);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((i$var50 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							}
						}
					}
					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
						if(fixedFlag$sample57) {
							{
								for(int index$i$39_1 = 1; index$i$39_1 < samples; index$i$39_1 += 1) {
									if((index$i$39_1 == (i$var50 - 1))) {
										{
											if((var29 == st[(i$var50 - 1)])) {
												if(!fixedFlag$sample57) {
													{
														{
															int index$i$52 = i$var50;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((i$var50 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
						} else {
							for(int index$i$40 = 1; index$i$40 < samples; index$i$40 += 1) {
								if(true) {
									for(int index$sample57$41 = 0; index$sample57$41 < noStates; index$sample57$41 += 1) {
										int distributionTempVariable$var56$43 = index$sample57$41;
										double cv$probabilitySample57Value42 = (1.0 * distribution$sample57[((index$i$40 - 1) / 1)][index$sample57$41]);
										{
											int traceTempVariable$var53$44_1 = distributionTempVariable$var56$43;
											if((index$i$40 == (i$var50 - 1))) {
												{
													if((var29 == traceTempVariable$var53$44_1)) {
														if(!fixedFlag$sample57) {
															{
																{
																	int index$i$54 = i$var50;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample57Value42);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((i$var50 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample30[((var29 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample36() {
		if(true) {
			constrainedFlag$sample36 = false;
			double[] cv$targetLocal = initialStateDistribution;
			double[] cv$countLocal = cv$var35$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if(fixedFlag$sample39) {
								{
									{
										boolean cv$sampleConstrained = (fixedFlag$sample39 || constrainedFlag$sample39);
										if(cv$sampleConstrained) {
											constrainedFlag$sample36 = true;
											{
												{
													{
														{
															{
																cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
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
					}
				}
			}
			{
				{
					{
						if(!fixedFlag$sample39) {
							{
								{
									{
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											{
												scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
												cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample39[cv$loopIndex] * cv$distributionProbability));
										}
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample36)
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample39() {
		if(true) {
			constrainedFlag$sample39 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var38$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$currentValue])) && (initialStateDistribution[cv$currentValue] <= 1.0))?Math.log(initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var53$1_1 = cv$currentValue;
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((0 == (i$var50 - 1))) {
										if(fixedFlag$sample57) {
											{
												{
													int index$i$3 = i$var50;
													boolean cv$sampleConstrained = (fixedFlag$sample57 || constrainedFlag$sample57[((i$var50 - 1) / 1)]);
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var29 = 0; var29 < noStates; var29 += 1) {
																	if((var29 == traceTempVariable$var53$1_1)) {
																		{
																			{
																				{
																					double[] var54 = m[traceTempVariable$var53$1_1];
																					if(((Math.log(1.0) + ((((((0.0 <= st[i$var50]) && (st[i$var50] < noStates)) && (0 < noStates)) && (0.0 <= var54[st[i$var50]])) && (var54[st[i$var50]] <= 1.0))?Math.log(var54[st[i$var50]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i$var50]) && (st[i$var50] < noStates)) && (0 < noStates)) && (0.0 <= var54[st[i$var50]])) && (var54[st[i$var50]] <= 1.0))?Math.log(var54[st[i$var50]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i$var50]) && (st[i$var50] < noStates)) && (0 < noStates)) && (0.0 <= var54[st[i$var50]])) && (var54[st[i$var50]] <= 1.0))?Math.log(var54[st[i$var50]]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i$var50]) && (st[i$var50] < noStates)) && (0 < noStates)) && (0.0 <= var54[st[i$var50]])) && (var54[st[i$var50]] <= 1.0))?Math.log(var54[st[i$var50]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i$var50]) && (st[i$var50] < noStates)) && (0 < noStates)) && (0.0 <= var54[st[i$var50]])) && (var54[st[i$var50]] <= 1.0))?Math.log(var54[st[i$var50]]):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
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
					{
						{
							boolean[] guard$sample39gaussian179 = guard$sample39gaussian179$global;
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian179[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian179[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								int traceTempVariable$s$8_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian179[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian179[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == traceTempVariable$s$8_1)) {
																		{
																			int traceTempVariable$s$13_1 = cv$currentValue;
																			if((0 == i$var174)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == traceTempVariable$s$13_1)) {
																							{
																								{
																									{
																										double var176 = cpuMean[traceTempVariable$s$13_1];
																										double var177 = cpuVar[traceTempVariable$s$13_1];
																										if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$14 = 0; index$sample39$14 < noStates; index$sample39$14 += 1) {
																				int distributionTempVariable$var38$16 = index$sample39$14;
																				double cv$probabilitySample39Value15 = (1.0 * distribution$sample39[index$sample39$14]);
																				{
																					int traceTempVariable$s$17_1 = distributionTempVariable$var38$16;
																					if((0 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == traceTempVariable$s$17_1)) {
																									{
																										{
																											{
																												double var176 = cpuMean[traceTempVariable$s$17_1];
																												double var177 = cpuVar[traceTempVariable$s$17_1];
																												if(((Math.log(cv$probabilitySample39Value15) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value15) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value15) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value15) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value15) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value15);
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
																	}
																}
															}
															{
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == traceTempVariable$s$8_1)) {
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == traceTempVariable$s$8_1)) {
																									{
																										{
																											{
																												double var176 = cpuMean[traceTempVariable$s$8_1];
																												double var177 = cpuVar[traceTempVariable$s$8_1];
																												if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$23 = 0; index$sample57$23 < noStates; index$sample57$23 += 1) {
																						int distributionTempVariable$var56$25 = index$sample57$23;
																						double cv$probabilitySample57Value24 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$23]);
																						{
																							int traceTempVariable$s$26_1 = distributionTempVariable$var56$25;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$26_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$26_1];
																														double var177 = cpuVar[traceTempVariable$s$26_1];
																														if(((Math.log(cv$probabilitySample57Value24) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value24) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value24) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value24) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value24) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value24);
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
																			}
																		}
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
							{
								int traceTempVariable$s$9_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian179[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian179[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																int traceTempVariable$s$29_1 = cv$currentValue;
																if((0 == i$var174)) {
																	{
																		for(int var75 = 0; var75 < noStates; var75 += 1) {
																			if((var75 == traceTempVariable$s$29_1)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == traceTempVariable$s$29_1)) {
																							{
																								{
																									{
																										double var176 = cpuMean[traceTempVariable$s$29_1];
																										double var177 = cpuVar[traceTempVariable$s$29_1];
																										if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
															}
															if(!true) {
																for(int index$sample39$30 = 0; index$sample39$30 < noStates; index$sample39$30 += 1) {
																	int distributionTempVariable$var38$32 = index$sample39$30;
																	double cv$probabilitySample39Value31 = (1.0 * distribution$sample39[index$sample39$30]);
																	{
																		int traceTempVariable$s$33_1 = distributionTempVariable$var38$32;
																		if((0 == i$var174)) {
																			{
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == traceTempVariable$s$33_1)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var176 = cpuMean[traceTempVariable$s$33_1];
																												double var177 = cpuVar[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value31) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value31) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value31) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value31) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value31) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value31);
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
																	}
																}
															}
															if(fixedFlag$sample57) {
																{
																	for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																		if((i$var50 == i$var174)) {
																			{
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == traceTempVariable$s$9_1)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == traceTempVariable$s$9_1)) {
																									{
																										{
																											{
																												double var176 = cpuMean[traceTempVariable$s$9_1];
																												double var177 = cpuVar[traceTempVariable$s$9_1];
																												if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	}
																}
															} else {
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if(true) {
																		for(int index$sample57$40 = 0; index$sample57$40 < noStates; index$sample57$40 += 1) {
																			int distributionTempVariable$var56$42 = index$sample57$40;
																			double cv$probabilitySample57Value41 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$40]);
																			{
																				int traceTempVariable$s$43_1 = distributionTempVariable$var56$42;
																				if((i$var50 == i$var174)) {
																					{
																						for(int var75 = 0; var75 < noStates; var75 += 1) {
																							if((var75 == traceTempVariable$s$43_1)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$43_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$43_1];
																														double var177 = cpuVar[traceTempVariable$s$43_1];
																														if(((Math.log(cv$probabilitySample57Value41) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value41) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value41) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value41) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value41) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value41);
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
																			}
																		}
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
					{
						{
							boolean[] guard$sample39gaussian184 = guard$sample39gaussian184$global;
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian184[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian184[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								int traceTempVariable$s$58_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian184[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian184[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == traceTempVariable$s$58_1)) {
																		{
																			int traceTempVariable$s$63_1 = cv$currentValue;
																			if((0 == i$var174)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == traceTempVariable$s$63_1)) {
																							{
																								{
																									{
																										double var181 = memMean[traceTempVariable$s$63_1];
																										double var182 = memVar[traceTempVariable$s$63_1];
																										if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$64 = 0; index$sample39$64 < noStates; index$sample39$64 += 1) {
																				int distributionTempVariable$var38$66 = index$sample39$64;
																				double cv$probabilitySample39Value65 = (1.0 * distribution$sample39[index$sample39$64]);
																				{
																					int traceTempVariable$s$67_1 = distributionTempVariable$var38$66;
																					if((0 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == traceTempVariable$s$67_1)) {
																									{
																										{
																											{
																												double var181 = memMean[traceTempVariable$s$67_1];
																												double var182 = memVar[traceTempVariable$s$67_1];
																												if(((Math.log(cv$probabilitySample39Value65) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value65) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value65) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value65) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value65) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value65);
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
																	}
																}
															}
															{
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == traceTempVariable$s$58_1)) {
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == traceTempVariable$s$58_1)) {
																									{
																										{
																											{
																												double var181 = memMean[traceTempVariable$s$58_1];
																												double var182 = memVar[traceTempVariable$s$58_1];
																												if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$73 = 0; index$sample57$73 < noStates; index$sample57$73 += 1) {
																						int distributionTempVariable$var56$75 = index$sample57$73;
																						double cv$probabilitySample57Value74 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$73]);
																						{
																							int traceTempVariable$s$76_1 = distributionTempVariable$var56$75;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$76_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$76_1];
																														double var182 = memVar[traceTempVariable$s$76_1];
																														if(((Math.log(cv$probabilitySample57Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value74) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value74);
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
																			}
																		}
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
							{
								int traceTempVariable$s$59_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian184[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian184[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																int traceTempVariable$s$79_1 = cv$currentValue;
																if((0 == i$var174)) {
																	{
																		for(int var93 = 0; var93 < noStates; var93 += 1) {
																			if((var93 == traceTempVariable$s$79_1)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == traceTempVariable$s$79_1)) {
																							{
																								{
																									{
																										double var181 = memMean[traceTempVariable$s$79_1];
																										double var182 = memVar[traceTempVariable$s$79_1];
																										if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
															}
															if(!true) {
																for(int index$sample39$80 = 0; index$sample39$80 < noStates; index$sample39$80 += 1) {
																	int distributionTempVariable$var38$82 = index$sample39$80;
																	double cv$probabilitySample39Value81 = (1.0 * distribution$sample39[index$sample39$80]);
																	{
																		int traceTempVariable$s$83_1 = distributionTempVariable$var38$82;
																		if((0 == i$var174)) {
																			{
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == traceTempVariable$s$83_1)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == traceTempVariable$s$83_1)) {
																									{
																										{
																											{
																												double var181 = memMean[traceTempVariable$s$83_1];
																												double var182 = memVar[traceTempVariable$s$83_1];
																												if(((Math.log(cv$probabilitySample39Value81) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value81) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value81) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value81) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value81) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value81);
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
																	}
																}
															}
															if(fixedFlag$sample57) {
																{
																	for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																		if((i$var50 == i$var174)) {
																			{
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == traceTempVariable$s$59_1)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == traceTempVariable$s$59_1)) {
																									{
																										{
																											{
																												double var181 = memMean[traceTempVariable$s$59_1];
																												double var182 = memVar[traceTempVariable$s$59_1];
																												if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	}
																}
															} else {
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if(true) {
																		for(int index$sample57$90 = 0; index$sample57$90 < noStates; index$sample57$90 += 1) {
																			int distributionTempVariable$var56$92 = index$sample57$90;
																			double cv$probabilitySample57Value91 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$90]);
																			{
																				int traceTempVariable$s$93_1 = distributionTempVariable$var56$92;
																				if((i$var50 == i$var174)) {
																					{
																						for(int var93 = 0; var93 < noStates; var93 += 1) {
																							if((var93 == traceTempVariable$s$93_1)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$93_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$93_1];
																														double var182 = memVar[traceTempVariable$s$93_1];
																														if(((Math.log(cv$probabilitySample57Value91) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value91) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value91) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value91) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value91) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value91);
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
																			}
																		}
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
					{
						{
							boolean[] guard$sample39gaussian189 = guard$sample39gaussian189$global;
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian189[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174))
										guard$sample39gaussian189[((i$var174 - 0) / 1)] = false;
								}
							}
							{
								int traceTempVariable$s$108_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian189[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian189[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == traceTempVariable$s$108_1)) {
																		{
																			int traceTempVariable$s$113_1 = cv$currentValue;
																			if((0 == i$var174)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == traceTempVariable$s$113_1)) {
																							{
																								{
																									{
																										double var186 = pageFaultsMean[traceTempVariable$s$113_1];
																										double var187 = pageFaultsVar[traceTempVariable$s$113_1];
																										if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$114 = 0; index$sample39$114 < noStates; index$sample39$114 += 1) {
																				int distributionTempVariable$var38$116 = index$sample39$114;
																				double cv$probabilitySample39Value115 = (1.0 * distribution$sample39[index$sample39$114]);
																				{
																					int traceTempVariable$s$117_1 = distributionTempVariable$var38$116;
																					if((0 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == traceTempVariable$s$117_1)) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[traceTempVariable$s$117_1];
																												double var187 = pageFaultsVar[traceTempVariable$s$117_1];
																												if(((Math.log(cv$probabilitySample39Value115) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value115) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value115) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value115) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value115) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value115);
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
																	}
																}
															}
															{
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == traceTempVariable$s$108_1)) {
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == traceTempVariable$s$108_1)) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[traceTempVariable$s$108_1];
																												double var187 = pageFaultsVar[traceTempVariable$s$108_1];
																												if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$123 = 0; index$sample57$123 < noStates; index$sample57$123 += 1) {
																						int distributionTempVariable$var56$125 = index$sample57$123;
																						double cv$probabilitySample57Value124 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$123]);
																						{
																							int traceTempVariable$s$126_1 = distributionTempVariable$var56$125;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$126_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$126_1];
																														double var187 = pageFaultsVar[traceTempVariable$s$126_1];
																														if(((Math.log(cv$probabilitySample57Value124) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value124) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value124) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value124) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value124) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value124);
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
																			}
																		}
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
							{
								int traceTempVariable$s$109_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((0 == i$var174)) {
										if(!guard$sample39gaussian189[((i$var174 - 0) / 1)]) {
											guard$sample39gaussian189[((i$var174 - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample39 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																int traceTempVariable$s$129_1 = cv$currentValue;
																if((0 == i$var174)) {
																	{
																		for(int var111 = 0; var111 < noStates; var111 += 1) {
																			if((var111 == traceTempVariable$s$129_1)) {
																				{
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == traceTempVariable$s$129_1)) {
																							{
																								{
																									{
																										double var186 = pageFaultsMean[traceTempVariable$s$129_1];
																										double var187 = pageFaultsVar[traceTempVariable$s$129_1];
																										if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
															}
															if(!true) {
																for(int index$sample39$130 = 0; index$sample39$130 < noStates; index$sample39$130 += 1) {
																	int distributionTempVariable$var38$132 = index$sample39$130;
																	double cv$probabilitySample39Value131 = (1.0 * distribution$sample39[index$sample39$130]);
																	{
																		int traceTempVariable$s$133_1 = distributionTempVariable$var38$132;
																		if((0 == i$var174)) {
																			{
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == traceTempVariable$s$133_1)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == traceTempVariable$s$133_1)) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[traceTempVariable$s$133_1];
																												double var187 = pageFaultsVar[traceTempVariable$s$133_1];
																												if(((Math.log(cv$probabilitySample39Value131) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value131) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value131) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value131) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value131) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value131);
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
																	}
																}
															}
															if(fixedFlag$sample57) {
																{
																	for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																		if((i$var50 == i$var174)) {
																			{
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == traceTempVariable$s$109_1)) {
																						{
																							for(int var162 = 0; var162 < noStates; var162 += 1) {
																								if((var162 == traceTempVariable$s$109_1)) {
																									{
																										{
																											{
																												double var186 = pageFaultsMean[traceTempVariable$s$109_1];
																												double var187 = pageFaultsVar[traceTempVariable$s$109_1];
																												if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	}
																}
															} else {
																for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																	if(true) {
																		for(int index$sample57$140 = 0; index$sample57$140 < noStates; index$sample57$140 += 1) {
																			int distributionTempVariable$var56$142 = index$sample57$140;
																			double cv$probabilitySample57Value141 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$140]);
																			{
																				int traceTempVariable$s$143_1 = distributionTempVariable$var56$142;
																				if((i$var50 == i$var174)) {
																					{
																						for(int var111 = 0; var111 < noStates; var111 += 1) {
																							if((var111 == traceTempVariable$s$143_1)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$143_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$143_1];
																														double var187 = pageFaultsVar[traceTempVariable$s$143_1];
																														if(((Math.log(cv$probabilitySample57Value141) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value141) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value141) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value141) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value141) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value141);
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
																			}
																		}
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
				{
					{
						{
							int traceTempVariable$var53$156_1 = cv$currentValue;
							for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
								if((0 == (i$var50 - 1))) {
									if(!fixedFlag$sample57) {
										{
											{
												int index$i$158 = i$var50;
												double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var55;
												for(int cv$i = 0; cv$i < noStates; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												double cv$reachedDistributionProbability = 0.0;
												{
													for(int var29 = 0; var29 < noStates; var29 += 1) {
														if((var29 == traceTempVariable$var53$156_1)) {
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double[] var54 = m[traceTempVariable$var53$156_1];
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var54, noStates);
															}
														}
													}
												}
												double[] cv$sampleDistribution = distribution$sample57[((i$var50 - 1) / 1)];
												double cv$overlap = 0.0;
												for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
													double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
													double cv$sampleDistValue = cv$sampleDistribution[cv$i];
													if((cv$sampleDistValue < cv$normalisedDistValue))
														cv$overlap = (cv$overlap + cv$sampleDistValue);
													else
														cv$overlap = (cv$overlap + cv$normalisedDistValue);
												}
												cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
											}
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample39) {
				double[] cv$localProbability = distribution$sample39;
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void sample57(int i$var50) {
		int index$i$1 = i$var50;
		if(true) {
			constrainedFlag$sample57[((i$var50 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(fixedFlag$sample39) {
				{
					if((0 == (i$var50 - 1))) {
						{
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)]))
									cv$numStates = Math.max(cv$numStates, noStates);
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						int distributionTempVariable$var38$5 = index$sample39$3;
						double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
						{
							int traceTempVariable$var53$6_1 = distributionTempVariable$var38$5;
							if((0 == (i$var50 - 1))) {
								{
									for(int var29 = 0; var29 < noStates; var29 += 1) {
										if((var29 == traceTempVariable$var53$6_1))
											cv$numStates = Math.max(cv$numStates, noStates);
									}
								}
							}
						}
					}
				}
			}
			{
				if((index$i$1 == (i$var50 - 1))) {
					{
						for(int var29 = 0; var29 < noStates; var29 += 1) {
							if((var29 == st[(i$var50 - 1)]))
								cv$numStates = Math.max(cv$numStates, noStates);
						}
					}
				}
			}
			for(int index$i$10 = 1; index$i$10 < samples; index$i$10 += 1) {
				if(!(index$i$10 == index$i$1)) {
					for(int index$sample57$11 = 0; index$sample57$11 < noStates; index$sample57$11 += 1) {
						int distributionTempVariable$var56$13 = index$sample57$11;
						double cv$probabilitySample57Value12 = (1.0 * distribution$sample57[((index$i$10 - 1) / 1)][index$sample57$11]);
						{
							int traceTempVariable$var53$14_1 = distributionTempVariable$var56$13;
							if((index$i$10 == (i$var50 - 1))) {
								{
									for(int var29 = 0; var29 < noStates; var29 += 1) {
										if((var29 == traceTempVariable$var53$14_1))
											cv$numStates = Math.max(cv$numStates, noStates);
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var56$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample39) {
					{
						if((0 == (i$var50 - 1))) {
							{
								for(int var29 = 0; var29 < noStates; var29 += 1) {
									if((var29 == st[(i$var50 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var54 = m[st[(i$var50 - 1)]];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$currentValue])) && (var54[cv$currentValue] <= 1.0))?Math.log(var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												{
													int traceTempVariable$var53$32_1 = cv$currentValue;
												}
											}
										}
										{
											{
												boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													int traceTempVariable$s$44_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					for(int var75 = 0; var75 < noStates; var75 += 1) {
																						if((var75 == traceTempVariable$s$44_1)) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$44_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$44_1];
																															double var177 = cpuVar[traceTempVariable$s$44_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					for(int var75 = 0; var75 < noStates; var75 += 1) {
																						if((var75 == traceTempVariable$s$44_1)) {
																							{
																								int traceTempVariable$s$64_1 = cv$currentValue;
																								if((index$i$1 == i$var174)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$64_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$64_1];
																															double var177 = cpuVar[traceTempVariable$s$64_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																								if(!(index$i$65 == index$i$1)) {
																									for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																										int distributionTempVariable$var56$68 = index$sample57$66;
																										double cv$probabilitySample57Value67 = (1.0 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																										{
																											int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																											if((index$i$65 == i$var174)) {
																												{
																													for(int var128 = 0; var128 < noStates; var128 += 1) {
																														if((var128 == traceTempVariable$s$69_1)) {
																															{
																																{
																																	{
																																		double var176 = cpuMean[traceTempVariable$s$69_1];
																																		double var177 = cpuVar[traceTempVariable$s$69_1];
																																		if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
																							}
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
												{
													int traceTempVariable$s$48_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$48_1)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$48_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$48_1];
																															double var177 = cpuVar[traceTempVariable$s$48_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					int traceTempVariable$s$128_1 = cv$currentValue;
																					if((index$i$1 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$128_1)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$128_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$128_1];
																															double var177 = cpuVar[traceTempVariable$s$128_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				for(int index$i$129 = 1; index$i$129 < samples; index$i$129 += 1) {
																					if(!(index$i$129 == index$i$1)) {
																						for(int index$sample57$130 = 0; index$sample57$130 < noStates; index$sample57$130 += 1) {
																							int distributionTempVariable$var56$132 = index$sample57$130;
																							double cv$probabilitySample57Value131 = (1.0 * distribution$sample57[((index$i$129 - 1) / 1)][index$sample57$130]);
																							{
																								int traceTempVariable$s$133_1 = distributionTempVariable$var56$132;
																								if((index$i$129 == i$var174)) {
																									{
																										for(int var75 = 0; var75 < noStates; var75 += 1) {
																											if((var75 == traceTempVariable$s$133_1)) {
																												{
																													for(int var128 = 0; var128 < noStates; var128 += 1) {
																														if((var128 == traceTempVariable$s$133_1)) {
																															{
																																{
																																	{
																																		double var176 = cpuMean[traceTempVariable$s$133_1];
																																		double var177 = cpuVar[traceTempVariable$s$133_1];
																																		if(((Math.log(cv$probabilitySample57Value131) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value131) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value131) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value131) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value131) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value131);
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
																							}
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
										{
											{
												boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													int traceTempVariable$s$238_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					for(int var93 = 0; var93 < noStates; var93 += 1) {
																						if((var93 == traceTempVariable$s$238_1)) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$238_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$238_1];
																															double var182 = memVar[traceTempVariable$s$238_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					for(int var93 = 0; var93 < noStates; var93 += 1) {
																						if((var93 == traceTempVariable$s$238_1)) {
																							{
																								int traceTempVariable$s$258_1 = cv$currentValue;
																								if((index$i$1 == i$var174)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$258_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$258_1];
																															double var182 = memVar[traceTempVariable$s$258_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$259 = 1; index$i$259 < samples; index$i$259 += 1) {
																								if(!(index$i$259 == index$i$1)) {
																									for(int index$sample57$260 = 0; index$sample57$260 < noStates; index$sample57$260 += 1) {
																										int distributionTempVariable$var56$262 = index$sample57$260;
																										double cv$probabilitySample57Value261 = (1.0 * distribution$sample57[((index$i$259 - 1) / 1)][index$sample57$260]);
																										{
																											int traceTempVariable$s$263_1 = distributionTempVariable$var56$262;
																											if((index$i$259 == i$var174)) {
																												{
																													for(int var145 = 0; var145 < noStates; var145 += 1) {
																														if((var145 == traceTempVariable$s$263_1)) {
																															{
																																{
																																	{
																																		double var181 = memMean[traceTempVariable$s$263_1];
																																		double var182 = memVar[traceTempVariable$s$263_1];
																																		if(((Math.log(cv$probabilitySample57Value261) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value261) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value261) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value261) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value261) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value261);
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
																							}
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
												{
													int traceTempVariable$s$242_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$242_1)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$242_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$242_1];
																															double var182 = memVar[traceTempVariable$s$242_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					int traceTempVariable$s$322_1 = cv$currentValue;
																					if((index$i$1 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$322_1)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$322_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$322_1];
																															double var182 = memVar[traceTempVariable$s$322_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				for(int index$i$323 = 1; index$i$323 < samples; index$i$323 += 1) {
																					if(!(index$i$323 == index$i$1)) {
																						for(int index$sample57$324 = 0; index$sample57$324 < noStates; index$sample57$324 += 1) {
																							int distributionTempVariable$var56$326 = index$sample57$324;
																							double cv$probabilitySample57Value325 = (1.0 * distribution$sample57[((index$i$323 - 1) / 1)][index$sample57$324]);
																							{
																								int traceTempVariable$s$327_1 = distributionTempVariable$var56$326;
																								if((index$i$323 == i$var174)) {
																									{
																										for(int var93 = 0; var93 < noStates; var93 += 1) {
																											if((var93 == traceTempVariable$s$327_1)) {
																												{
																													for(int var145 = 0; var145 < noStates; var145 += 1) {
																														if((var145 == traceTempVariable$s$327_1)) {
																															{
																																{
																																	{
																																		double var181 = memMean[traceTempVariable$s$327_1];
																																		double var182 = memVar[traceTempVariable$s$327_1];
																																		if(((Math.log(cv$probabilitySample57Value325) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value325) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value325) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value325) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value325) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value325);
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
																							}
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
										{
											{
												boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174))
															guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
													}
												}
												{
													int traceTempVariable$s$432_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					for(int var111 = 0; var111 < noStates; var111 += 1) {
																						if((var111 == traceTempVariable$s$432_1)) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$432_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$432_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$432_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					for(int var111 = 0; var111 < noStates; var111 += 1) {
																						if((var111 == traceTempVariable$s$432_1)) {
																							{
																								int traceTempVariable$s$452_1 = cv$currentValue;
																								if((index$i$1 == i$var174)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$452_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$452_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$452_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$453 = 1; index$i$453 < samples; index$i$453 += 1) {
																								if(!(index$i$453 == index$i$1)) {
																									for(int index$sample57$454 = 0; index$sample57$454 < noStates; index$sample57$454 += 1) {
																										int distributionTempVariable$var56$456 = index$sample57$454;
																										double cv$probabilitySample57Value455 = (1.0 * distribution$sample57[((index$i$453 - 1) / 1)][index$sample57$454]);
																										{
																											int traceTempVariable$s$457_1 = distributionTempVariable$var56$456;
																											if((index$i$453 == i$var174)) {
																												{
																													for(int var162 = 0; var162 < noStates; var162 += 1) {
																														if((var162 == traceTempVariable$s$457_1)) {
																															{
																																{
																																	{
																																		double var186 = pageFaultsMean[traceTempVariable$s$457_1];
																																		double var187 = pageFaultsVar[traceTempVariable$s$457_1];
																																		if(((Math.log(cv$probabilitySample57Value455) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value455) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value455) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value455) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value455) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value455);
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
																							}
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
												{
													int traceTempVariable$s$436_1 = cv$currentValue;
													for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
														if((i$var50 == i$var174)) {
															if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$436_1)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$436_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$436_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$436_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				{
																					int traceTempVariable$s$516_1 = cv$currentValue;
																					if((index$i$1 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$516_1)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$516_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$516_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$516_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																				for(int index$i$517 = 1; index$i$517 < samples; index$i$517 += 1) {
																					if(!(index$i$517 == index$i$1)) {
																						for(int index$sample57$518 = 0; index$sample57$518 < noStates; index$sample57$518 += 1) {
																							int distributionTempVariable$var56$520 = index$sample57$518;
																							double cv$probabilitySample57Value519 = (1.0 * distribution$sample57[((index$i$517 - 1) / 1)][index$sample57$518]);
																							{
																								int traceTempVariable$s$521_1 = distributionTempVariable$var56$520;
																								if((index$i$517 == i$var174)) {
																									{
																										for(int var111 = 0; var111 < noStates; var111 += 1) {
																											if((var111 == traceTempVariable$s$521_1)) {
																												{
																													for(int var162 = 0; var162 < noStates; var162 += 1) {
																														if((var162 == traceTempVariable$s$521_1)) {
																															{
																																{
																																	{
																																		double var186 = pageFaultsMean[traceTempVariable$s$521_1];
																																		double var187 = pageFaultsVar[traceTempVariable$s$521_1];
																																		if(((Math.log(cv$probabilitySample57Value519) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value519) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value519) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value519) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value519) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value519);
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
																							}
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
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample39$18 = 0; index$sample39$18 < noStates; index$sample39$18 += 1) {
							int distributionTempVariable$var38$20 = index$sample39$18;
							double cv$probabilitySample39Value19 = (1.0 * distribution$sample39[index$sample39$18]);
							{
								int traceTempVariable$var53$21_1 = distributionTempVariable$var38$20;
								if((0 == (i$var50 - 1))) {
									{
										for(int var29 = 0; var29 < noStates; var29 += 1) {
											if((var29 == traceTempVariable$var53$21_1)) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value19);
												double[] var54 = m[traceTempVariable$var53$21_1];
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value19) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$currentValue])) && (var54[cv$currentValue] <= 1.0))?Math.log(var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var53$33_1 = cv$currentValue;
														}
													}
												}
												{
													{
														boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$45_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$45_1)) {
																									{
																										int traceTempVariable$s$73_1 = distributionTempVariable$var38$20;
																										if((0 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$73_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$73_1];
																																	double var177 = cpuVar[traceTempVariable$s$73_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									if(!true) {
																										for(int index$sample39$74 = 0; index$sample39$74 < noStates; index$sample39$74 += 1) {
																											int distributionTempVariable$var38$76 = index$sample39$74;
																											double cv$probabilitySample39Value75 = (1.0 * distribution$sample39[index$sample39$74]);
																											{
																												int traceTempVariable$s$77_1 = distributionTempVariable$var38$76;
																												if((0 == i$var174)) {
																													{
																														for(int var128 = 0; var128 < noStates; var128 += 1) {
																															if((var128 == traceTempVariable$s$77_1)) {
																																{
																																	{
																																		{
																																			double var176 = cpuMean[traceTempVariable$s$77_1];
																																			double var177 = cpuVar[traceTempVariable$s$77_1];
																																			if(((Math.log(cv$probabilitySample39Value75) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value75) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value75) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value75) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value75) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value75);
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
																								}
																							}
																						}
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$45_1)) {
																									{
																										int traceTempVariable$s$81_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$81_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$81_1];
																																	double var177 = cpuVar[traceTempVariable$s$81_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$82 = 1; index$i$82 < samples; index$i$82 += 1) {
																										if(!(index$i$82 == index$i$1)) {
																											for(int index$sample57$83 = 0; index$sample57$83 < noStates; index$sample57$83 += 1) {
																												int distributionTempVariable$var56$85 = index$sample57$83;
																												double cv$probabilitySample57Value84 = (1.0 * distribution$sample57[((index$i$82 - 1) / 1)][index$sample57$83]);
																												{
																													int traceTempVariable$s$86_1 = distributionTempVariable$var56$85;
																													if((index$i$82 == i$var174)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$86_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$86_1];
																																				double var177 = cpuVar[traceTempVariable$s$86_1];
																																				if(((Math.log(cv$probabilitySample57Value84) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value84) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value84) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value84) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value84) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value84);
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
																									}
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
														{
															int traceTempVariable$s$49_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							int traceTempVariable$s$138_1 = distributionTempVariable$var38$20;
																							if((0 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$138_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$138_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$138_1];
																																	double var177 = cpuVar[traceTempVariable$s$138_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						if(!true) {
																							for(int index$sample39$139 = 0; index$sample39$139 < noStates; index$sample39$139 += 1) {
																								int distributionTempVariable$var38$141 = index$sample39$139;
																								double cv$probabilitySample39Value140 = (1.0 * distribution$sample39[index$sample39$139]);
																								{
																									int traceTempVariable$s$142_1 = distributionTempVariable$var38$141;
																									if((0 == i$var174)) {
																										{
																											for(int var75 = 0; var75 < noStates; var75 += 1) {
																												if((var75 == traceTempVariable$s$142_1)) {
																													{
																														for(int var128 = 0; var128 < noStates; var128 += 1) {
																															if((var128 == traceTempVariable$s$142_1)) {
																																{
																																	{
																																		{
																																			double var176 = cpuMean[traceTempVariable$s$142_1];
																																			double var177 = cpuVar[traceTempVariable$s$142_1];
																																			if(((Math.log(cv$probabilitySample39Value140) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value140) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value140) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value140) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value140) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value140);
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
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$147_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$147_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$147_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$147_1];
																																	double var177 = cpuVar[traceTempVariable$s$147_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$148 = 1; index$i$148 < samples; index$i$148 += 1) {
																							if(!(index$i$148 == index$i$1)) {
																								for(int index$sample57$149 = 0; index$sample57$149 < noStates; index$sample57$149 += 1) {
																									int distributionTempVariable$var56$151 = index$sample57$149;
																									double cv$probabilitySample57Value150 = (1.0 * distribution$sample57[((index$i$148 - 1) / 1)][index$sample57$149]);
																									{
																										int traceTempVariable$s$152_1 = distributionTempVariable$var56$151;
																										if((index$i$148 == i$var174)) {
																											{
																												for(int var75 = 0; var75 < noStates; var75 += 1) {
																													if((var75 == traceTempVariable$s$152_1)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$152_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$152_1];
																																				double var177 = cpuVar[traceTempVariable$s$152_1];
																																				if(((Math.log(cv$probabilitySample57Value150) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value150) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value150) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value150) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value150) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value150);
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
																									}
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
												{
													{
														boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$239_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$239_1)) {
																									{
																										int traceTempVariable$s$267_1 = distributionTempVariable$var38$20;
																										if((0 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$267_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$267_1];
																																	double var182 = memVar[traceTempVariable$s$267_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									if(!true) {
																										for(int index$sample39$268 = 0; index$sample39$268 < noStates; index$sample39$268 += 1) {
																											int distributionTempVariable$var38$270 = index$sample39$268;
																											double cv$probabilitySample39Value269 = (1.0 * distribution$sample39[index$sample39$268]);
																											{
																												int traceTempVariable$s$271_1 = distributionTempVariable$var38$270;
																												if((0 == i$var174)) {
																													{
																														for(int var145 = 0; var145 < noStates; var145 += 1) {
																															if((var145 == traceTempVariable$s$271_1)) {
																																{
																																	{
																																		{
																																			double var181 = memMean[traceTempVariable$s$271_1];
																																			double var182 = memVar[traceTempVariable$s$271_1];
																																			if(((Math.log(cv$probabilitySample39Value269) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value269) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value269) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value269) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value269) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value269);
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
																								}
																							}
																						}
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$239_1)) {
																									{
																										int traceTempVariable$s$275_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$275_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$275_1];
																																	double var182 = memVar[traceTempVariable$s$275_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$276 = 1; index$i$276 < samples; index$i$276 += 1) {
																										if(!(index$i$276 == index$i$1)) {
																											for(int index$sample57$277 = 0; index$sample57$277 < noStates; index$sample57$277 += 1) {
																												int distributionTempVariable$var56$279 = index$sample57$277;
																												double cv$probabilitySample57Value278 = (1.0 * distribution$sample57[((index$i$276 - 1) / 1)][index$sample57$277]);
																												{
																													int traceTempVariable$s$280_1 = distributionTempVariable$var56$279;
																													if((index$i$276 == i$var174)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$280_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$280_1];
																																				double var182 = memVar[traceTempVariable$s$280_1];
																																				if(((Math.log(cv$probabilitySample57Value278) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value278) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value278) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value278) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value278) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value278);
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
																									}
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
														{
															int traceTempVariable$s$243_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							int traceTempVariable$s$332_1 = distributionTempVariable$var38$20;
																							if((0 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$332_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$332_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$332_1];
																																	double var182 = memVar[traceTempVariable$s$332_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						if(!true) {
																							for(int index$sample39$333 = 0; index$sample39$333 < noStates; index$sample39$333 += 1) {
																								int distributionTempVariable$var38$335 = index$sample39$333;
																								double cv$probabilitySample39Value334 = (1.0 * distribution$sample39[index$sample39$333]);
																								{
																									int traceTempVariable$s$336_1 = distributionTempVariable$var38$335;
																									if((0 == i$var174)) {
																										{
																											for(int var93 = 0; var93 < noStates; var93 += 1) {
																												if((var93 == traceTempVariable$s$336_1)) {
																													{
																														for(int var145 = 0; var145 < noStates; var145 += 1) {
																															if((var145 == traceTempVariable$s$336_1)) {
																																{
																																	{
																																		{
																																			double var181 = memMean[traceTempVariable$s$336_1];
																																			double var182 = memVar[traceTempVariable$s$336_1];
																																			if(((Math.log(cv$probabilitySample39Value334) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value334) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value334) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value334) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value334) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value334);
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
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$341_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$341_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$341_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$341_1];
																																	double var182 = memVar[traceTempVariable$s$341_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$342 = 1; index$i$342 < samples; index$i$342 += 1) {
																							if(!(index$i$342 == index$i$1)) {
																								for(int index$sample57$343 = 0; index$sample57$343 < noStates; index$sample57$343 += 1) {
																									int distributionTempVariable$var56$345 = index$sample57$343;
																									double cv$probabilitySample57Value344 = (1.0 * distribution$sample57[((index$i$342 - 1) / 1)][index$sample57$343]);
																									{
																										int traceTempVariable$s$346_1 = distributionTempVariable$var56$345;
																										if((index$i$342 == i$var174)) {
																											{
																												for(int var93 = 0; var93 < noStates; var93 += 1) {
																													if((var93 == traceTempVariable$s$346_1)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$346_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$346_1];
																																				double var182 = memVar[traceTempVariable$s$346_1];
																																				if(((Math.log(cv$probabilitySample57Value344) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value344) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value344) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value344) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value344) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value344);
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
																									}
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
												{
													{
														boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$433_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$433_1)) {
																									{
																										int traceTempVariable$s$461_1 = distributionTempVariable$var38$20;
																										if((0 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$461_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$461_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$461_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									if(!true) {
																										for(int index$sample39$462 = 0; index$sample39$462 < noStates; index$sample39$462 += 1) {
																											int distributionTempVariable$var38$464 = index$sample39$462;
																											double cv$probabilitySample39Value463 = (1.0 * distribution$sample39[index$sample39$462]);
																											{
																												int traceTempVariable$s$465_1 = distributionTempVariable$var38$464;
																												if((0 == i$var174)) {
																													{
																														for(int var162 = 0; var162 < noStates; var162 += 1) {
																															if((var162 == traceTempVariable$s$465_1)) {
																																{
																																	{
																																		{
																																			double var186 = pageFaultsMean[traceTempVariable$s$465_1];
																																			double var187 = pageFaultsVar[traceTempVariable$s$465_1];
																																			if(((Math.log(cv$probabilitySample39Value463) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value463) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value463) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value463) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value463) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value463);
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
																								}
																							}
																						}
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$433_1)) {
																									{
																										int traceTempVariable$s$469_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$469_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$469_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$469_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$470 = 1; index$i$470 < samples; index$i$470 += 1) {
																										if(!(index$i$470 == index$i$1)) {
																											for(int index$sample57$471 = 0; index$sample57$471 < noStates; index$sample57$471 += 1) {
																												int distributionTempVariable$var56$473 = index$sample57$471;
																												double cv$probabilitySample57Value472 = (1.0 * distribution$sample57[((index$i$470 - 1) / 1)][index$sample57$471]);
																												{
																													int traceTempVariable$s$474_1 = distributionTempVariable$var56$473;
																													if((index$i$470 == i$var174)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$474_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$474_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$474_1];
																																				if(((Math.log(cv$probabilitySample57Value472) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value472) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value472) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value472) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value472) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value472);
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
																									}
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
														{
															int traceTempVariable$s$437_1 = cv$currentValue;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							int traceTempVariable$s$526_1 = distributionTempVariable$var38$20;
																							if((0 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$526_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$526_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$526_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$526_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						if(!true) {
																							for(int index$sample39$527 = 0; index$sample39$527 < noStates; index$sample39$527 += 1) {
																								int distributionTempVariable$var38$529 = index$sample39$527;
																								double cv$probabilitySample39Value528 = (1.0 * distribution$sample39[index$sample39$527]);
																								{
																									int traceTempVariable$s$530_1 = distributionTempVariable$var38$529;
																									if((0 == i$var174)) {
																										{
																											for(int var111 = 0; var111 < noStates; var111 += 1) {
																												if((var111 == traceTempVariable$s$530_1)) {
																													{
																														for(int var162 = 0; var162 < noStates; var162 += 1) {
																															if((var162 == traceTempVariable$s$530_1)) {
																																{
																																	{
																																		{
																																			double var186 = pageFaultsMean[traceTempVariable$s$530_1];
																																			double var187 = pageFaultsVar[traceTempVariable$s$530_1];
																																			if(((Math.log(cv$probabilitySample39Value528) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value528) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value528) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value528) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value528) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value528);
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
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$535_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$535_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$535_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$535_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$535_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$536 = 1; index$i$536 < samples; index$i$536 += 1) {
																							if(!(index$i$536 == index$i$1)) {
																								for(int index$sample57$537 = 0; index$sample57$537 < noStates; index$sample57$537 += 1) {
																									int distributionTempVariable$var56$539 = index$sample57$537;
																									double cv$probabilitySample57Value538 = (1.0 * distribution$sample57[((index$i$536 - 1) / 1)][index$sample57$537]);
																									{
																										int traceTempVariable$s$540_1 = distributionTempVariable$var56$539;
																										if((index$i$536 == i$var174)) {
																											{
																												for(int var111 = 0; var111 < noStates; var111 += 1) {
																													if((var111 == traceTempVariable$s$540_1)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$540_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$540_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$540_1];
																																				if(((Math.log(cv$probabilitySample57Value538) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value538) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value538) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value538) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value538) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value538);
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
																									}
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
										}
									}
								}
							}
						}
					}
				}
				{
					int traceTempVariable$var53$24_1 = cv$currentValue;
					if((index$i$1 == (i$var50 - 1))) {
						{
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == traceTempVariable$var53$24_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] var54 = m[traceTempVariable$var53$24_1];
									double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$currentValue])) && (var54[cv$currentValue] <= 1.0))?Math.log(var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											{
												int traceTempVariable$var53$34_1 = cv$currentValue;
											}
										}
									}
									{
										{
											boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												int traceTempVariable$s$46_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == traceTempVariable$s$46_1)) {
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$46_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$46_1];
																															double var177 = cpuVar[traceTempVariable$s$46_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$91 = 0; index$sample39$91 < noStates; index$sample39$91 += 1) {
																									int distributionTempVariable$var38$93 = index$sample39$91;
																									double cv$probabilitySample39Value92 = (1.0 * distribution$sample39[index$sample39$91]);
																									{
																										int traceTempVariable$s$94_1 = distributionTempVariable$var38$93;
																										if((0 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$94_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$94_1];
																																	double var177 = cpuVar[traceTempVariable$s$94_1];
																																	if(((Math.log(cv$probabilitySample39Value92) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value92) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value92) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value92) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value92) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value92);
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
																						}
																					}
																				}
																			}
																			{
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == traceTempVariable$s$46_1)) {
																						{
																							int traceTempVariable$s$98_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$98_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$98_1];
																														double var177 = cpuVar[traceTempVariable$s$98_1];
																														if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$99 = 1; index$i$99 < samples; index$i$99 += 1) {
																							if(!(index$i$99 == index$i$1)) {
																								for(int index$sample57$100 = 0; index$sample57$100 < noStates; index$sample57$100 += 1) {
																									int distributionTempVariable$var56$102 = index$sample57$100;
																									double cv$probabilitySample57Value101 = (1.0 * distribution$sample57[((index$i$99 - 1) / 1)][index$sample57$100]);
																									{
																										int traceTempVariable$s$103_1 = distributionTempVariable$var56$102;
																										if((index$i$99 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$103_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$103_1];
																																	double var177 = cpuVar[traceTempVariable$s$103_1];
																																	if(((Math.log(cv$probabilitySample57Value101) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value101) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value101) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value101) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value101) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value101);
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
																						}
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
											{
												int traceTempVariable$s$50_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$50_1)) {
																									{
																										for(int var128 = 0; var128 < noStates; var128 += 1) {
																											if((var128 == traceTempVariable$s$50_1)) {
																												{
																													{
																														{
																															double var176 = cpuMean[traceTempVariable$s$50_1];
																															double var177 = cpuVar[traceTempVariable$s$50_1];
																															if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$158 = 0; index$sample39$158 < noStates; index$sample39$158 += 1) {
																						int distributionTempVariable$var38$160 = index$sample39$158;
																						double cv$probabilitySample39Value159 = (1.0 * distribution$sample39[index$sample39$158]);
																						{
																							int traceTempVariable$s$161_1 = distributionTempVariable$var38$160;
																							if((0 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$161_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$161_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$161_1];
																																	double var177 = cpuVar[traceTempVariable$s$161_1];
																																	if(((Math.log(cv$probabilitySample39Value159) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value159) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value159) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value159) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value159) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value159);
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
																						}
																					}
																				}
																			}
																			{
																				int traceTempVariable$s$166_1 = cv$currentValue;
																				if((index$i$1 == i$var174)) {
																					{
																						for(int var75 = 0; var75 < noStates; var75 += 1) {
																							if((var75 == traceTempVariable$s$166_1)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$166_1)) {
																											{
																												{
																													{
																														double var176 = cpuMean[traceTempVariable$s$166_1];
																														double var177 = cpuVar[traceTempVariable$s$166_1];
																														if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																			}
																			for(int index$i$167 = 1; index$i$167 < samples; index$i$167 += 1) {
																				if(!(index$i$167 == index$i$1)) {
																					for(int index$sample57$168 = 0; index$sample57$168 < noStates; index$sample57$168 += 1) {
																						int distributionTempVariable$var56$170 = index$sample57$168;
																						double cv$probabilitySample57Value169 = (1.0 * distribution$sample57[((index$i$167 - 1) / 1)][index$sample57$168]);
																						{
																							int traceTempVariable$s$171_1 = distributionTempVariable$var56$170;
																							if((index$i$167 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$171_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$171_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$171_1];
																																	double var177 = cpuVar[traceTempVariable$s$171_1];
																																	if(((Math.log(cv$probabilitySample57Value169) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value169) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value169) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value169) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value169) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value169);
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
																						}
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
									{
										{
											boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												int traceTempVariable$s$240_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == traceTempVariable$s$240_1)) {
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$240_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$240_1];
																															double var182 = memVar[traceTempVariable$s$240_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$285 = 0; index$sample39$285 < noStates; index$sample39$285 += 1) {
																									int distributionTempVariable$var38$287 = index$sample39$285;
																									double cv$probabilitySample39Value286 = (1.0 * distribution$sample39[index$sample39$285]);
																									{
																										int traceTempVariable$s$288_1 = distributionTempVariable$var38$287;
																										if((0 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$288_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$288_1];
																																	double var182 = memVar[traceTempVariable$s$288_1];
																																	if(((Math.log(cv$probabilitySample39Value286) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value286) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value286) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value286) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value286) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value286);
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
																						}
																					}
																				}
																			}
																			{
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == traceTempVariable$s$240_1)) {
																						{
																							int traceTempVariable$s$292_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$292_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$292_1];
																														double var182 = memVar[traceTempVariable$s$292_1];
																														if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$293 = 1; index$i$293 < samples; index$i$293 += 1) {
																							if(!(index$i$293 == index$i$1)) {
																								for(int index$sample57$294 = 0; index$sample57$294 < noStates; index$sample57$294 += 1) {
																									int distributionTempVariable$var56$296 = index$sample57$294;
																									double cv$probabilitySample57Value295 = (1.0 * distribution$sample57[((index$i$293 - 1) / 1)][index$sample57$294]);
																									{
																										int traceTempVariable$s$297_1 = distributionTempVariable$var56$296;
																										if((index$i$293 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$297_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$297_1];
																																	double var182 = memVar[traceTempVariable$s$297_1];
																																	if(((Math.log(cv$probabilitySample57Value295) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value295) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value295) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value295) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value295) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value295);
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
																						}
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
											{
												int traceTempVariable$s$244_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$244_1)) {
																									{
																										for(int var145 = 0; var145 < noStates; var145 += 1) {
																											if((var145 == traceTempVariable$s$244_1)) {
																												{
																													{
																														{
																															double var181 = memMean[traceTempVariable$s$244_1];
																															double var182 = memVar[traceTempVariable$s$244_1];
																															if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$352 = 0; index$sample39$352 < noStates; index$sample39$352 += 1) {
																						int distributionTempVariable$var38$354 = index$sample39$352;
																						double cv$probabilitySample39Value353 = (1.0 * distribution$sample39[index$sample39$352]);
																						{
																							int traceTempVariable$s$355_1 = distributionTempVariable$var38$354;
																							if((0 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$355_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$355_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$355_1];
																																	double var182 = memVar[traceTempVariable$s$355_1];
																																	if(((Math.log(cv$probabilitySample39Value353) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value353) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value353) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value353) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value353) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value353);
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
																						}
																					}
																				}
																			}
																			{
																				int traceTempVariable$s$360_1 = cv$currentValue;
																				if((index$i$1 == i$var174)) {
																					{
																						for(int var93 = 0; var93 < noStates; var93 += 1) {
																							if((var93 == traceTempVariable$s$360_1)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$360_1)) {
																											{
																												{
																													{
																														double var181 = memMean[traceTempVariable$s$360_1];
																														double var182 = memVar[traceTempVariable$s$360_1];
																														if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																			}
																			for(int index$i$361 = 1; index$i$361 < samples; index$i$361 += 1) {
																				if(!(index$i$361 == index$i$1)) {
																					for(int index$sample57$362 = 0; index$sample57$362 < noStates; index$sample57$362 += 1) {
																						int distributionTempVariable$var56$364 = index$sample57$362;
																						double cv$probabilitySample57Value363 = (1.0 * distribution$sample57[((index$i$361 - 1) / 1)][index$sample57$362]);
																						{
																							int traceTempVariable$s$365_1 = distributionTempVariable$var56$364;
																							if((index$i$361 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$365_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$365_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$365_1];
																																	double var182 = memVar[traceTempVariable$s$365_1];
																																	if(((Math.log(cv$probabilitySample57Value363) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value363) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value363) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value363) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value363) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value363);
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
																						}
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
									{
										{
											boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174))
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
												}
											}
											{
												int traceTempVariable$s$434_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == traceTempVariable$s$434_1)) {
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$434_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$434_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$434_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$479 = 0; index$sample39$479 < noStates; index$sample39$479 += 1) {
																									int distributionTempVariable$var38$481 = index$sample39$479;
																									double cv$probabilitySample39Value480 = (1.0 * distribution$sample39[index$sample39$479]);
																									{
																										int traceTempVariable$s$482_1 = distributionTempVariable$var38$481;
																										if((0 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$482_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$482_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$482_1];
																																	if(((Math.log(cv$probabilitySample39Value480) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value480) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value480) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value480) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value480) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value480);
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
																						}
																					}
																				}
																			}
																			{
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == traceTempVariable$s$434_1)) {
																						{
																							int traceTempVariable$s$486_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$486_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$486_1];
																														double var187 = pageFaultsVar[traceTempVariable$s$486_1];
																														if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$487 = 1; index$i$487 < samples; index$i$487 += 1) {
																							if(!(index$i$487 == index$i$1)) {
																								for(int index$sample57$488 = 0; index$sample57$488 < noStates; index$sample57$488 += 1) {
																									int distributionTempVariable$var56$490 = index$sample57$488;
																									double cv$probabilitySample57Value489 = (1.0 * distribution$sample57[((index$i$487 - 1) / 1)][index$sample57$488]);
																									{
																										int traceTempVariable$s$491_1 = distributionTempVariable$var56$490;
																										if((index$i$487 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$491_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$491_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$491_1];
																																	if(((Math.log(cv$probabilitySample57Value489) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value489) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value489) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value489) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value489) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value489);
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
																						}
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
											{
												int traceTempVariable$s$438_1 = cv$currentValue;
												for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
													if((i$var50 == i$var174)) {
														if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
															guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$438_1)) {
																									{
																										for(int var162 = 0; var162 < noStates; var162 += 1) {
																											if((var162 == traceTempVariable$s$438_1)) {
																												{
																													{
																														{
																															double var186 = pageFaultsMean[traceTempVariable$s$438_1];
																															double var187 = pageFaultsVar[traceTempVariable$s$438_1];
																															if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$546 = 0; index$sample39$546 < noStates; index$sample39$546 += 1) {
																						int distributionTempVariable$var38$548 = index$sample39$546;
																						double cv$probabilitySample39Value547 = (1.0 * distribution$sample39[index$sample39$546]);
																						{
																							int traceTempVariable$s$549_1 = distributionTempVariable$var38$548;
																							if((0 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$549_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$549_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$549_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$549_1];
																																	if(((Math.log(cv$probabilitySample39Value547) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value547) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value547) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value547) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value547) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value547);
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
																						}
																					}
																				}
																			}
																			{
																				int traceTempVariable$s$554_1 = cv$currentValue;
																				if((index$i$1 == i$var174)) {
																					{
																						for(int var111 = 0; var111 < noStates; var111 += 1) {
																							if((var111 == traceTempVariable$s$554_1)) {
																								{
																									for(int var162 = 0; var162 < noStates; var162 += 1) {
																										if((var162 == traceTempVariable$s$554_1)) {
																											{
																												{
																													{
																														double var186 = pageFaultsMean[traceTempVariable$s$554_1];
																														double var187 = pageFaultsVar[traceTempVariable$s$554_1];
																														if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																			}
																			for(int index$i$555 = 1; index$i$555 < samples; index$i$555 += 1) {
																				if(!(index$i$555 == index$i$1)) {
																					for(int index$sample57$556 = 0; index$sample57$556 < noStates; index$sample57$556 += 1) {
																						int distributionTempVariable$var56$558 = index$sample57$556;
																						double cv$probabilitySample57Value557 = (1.0 * distribution$sample57[((index$i$555 - 1) / 1)][index$sample57$556]);
																						{
																							int traceTempVariable$s$559_1 = distributionTempVariable$var56$558;
																							if((index$i$555 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$559_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$559_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$559_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$559_1];
																																	if(((Math.log(cv$probabilitySample57Value557) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value557) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value557) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value557) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value557) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value557);
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
																						}
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
							}
						}
					}
				}
				for(int index$i$25 = 1; index$i$25 < samples; index$i$25 += 1) {
					if(!(index$i$25 == index$i$1)) {
						for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
							int distributionTempVariable$var56$28 = index$sample57$26;
							double cv$probabilitySample57Value27 = (1.0 * distribution$sample57[((index$i$25 - 1) / 1)][index$sample57$26]);
							{
								int traceTempVariable$var53$29_1 = distributionTempVariable$var56$28;
								if((index$i$25 == (i$var50 - 1))) {
									{
										for(int var29 = 0; var29 < noStates; var29 += 1) {
											if((var29 == traceTempVariable$var53$29_1)) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
												double[] var54 = m[traceTempVariable$var53$29_1];
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$currentValue])) && (var54[cv$currentValue] <= 1.0))?Math.log(var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var53$35_1 = distributionTempVariable$var56$28;
														}
													}
												}
												{
													{
														boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$47_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$47_1)) {
																									if(fixedFlag$sample39) {
																										{
																											if((0 == i$var174)) {
																												{
																													for(int var128 = 0; var128 < noStates; var128 += 1) {
																														if((var128 == traceTempVariable$s$47_1)) {
																															{
																																{
																																	{
																																		double var176 = cpuMean[traceTempVariable$s$47_1];
																																		double var177 = cpuVar[traceTempVariable$s$47_1];
																																		if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									} else {
																										if(true) {
																											for(int index$sample39$108 = 0; index$sample39$108 < noStates; index$sample39$108 += 1) {
																												int distributionTempVariable$var38$110 = index$sample39$108;
																												double cv$probabilitySample39Value109 = (1.0 * distribution$sample39[index$sample39$108]);
																												{
																													int traceTempVariable$s$111_1 = distributionTempVariable$var38$110;
																													if((0 == i$var174)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$111_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$111_1];
																																				double var177 = cpuVar[traceTempVariable$s$111_1];
																																				if(((Math.log(cv$probabilitySample39Value109) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value109) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value109) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value109) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value109) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value109);
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
																									}
																								}
																							}
																						}
																						{
																							for(int var75 = 0; var75 < noStates; var75 += 1) {
																								if((var75 == traceTempVariable$s$47_1)) {
																									{
																										int traceTempVariable$s$115_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$115_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$115_1];
																																	double var177 = cpuVar[traceTempVariable$s$115_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									{
																										int traceTempVariable$s$116_1 = distributionTempVariable$var56$28;
																										if((index$i$25 == i$var174)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$116_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$116_1];
																																	double var177 = cpuVar[traceTempVariable$s$116_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$117 = 1; index$i$117 < samples; index$i$117 += 1) {
																										if((!(index$i$117 == index$i$1) && !(index$i$117 == index$i$25))) {
																											for(int index$sample57$118 = 0; index$sample57$118 < noStates; index$sample57$118 += 1) {
																												int distributionTempVariable$var56$120 = index$sample57$118;
																												double cv$probabilitySample57Value119 = (1.0 * distribution$sample57[((index$i$117 - 1) / 1)][index$sample57$118]);
																												{
																													int traceTempVariable$s$121_1 = distributionTempVariable$var56$120;
																													if((index$i$117 == i$var174)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$121_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$121_1];
																																				double var177 = cpuVar[traceTempVariable$s$121_1];
																																				if(((Math.log(cv$probabilitySample57Value119) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value119) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value119) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value119) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value119) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value119);
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
																									}
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
														{
															int traceTempVariable$s$51_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var75 = 0; var75 < noStates; var75 += 1) {
																											if((var75 == traceTempVariable$s$51_1)) {
																												{
																													for(int var128 = 0; var128 < noStates; var128 += 1) {
																														if((var128 == traceTempVariable$s$51_1)) {
																															{
																																{
																																	{
																																		double var176 = cpuMean[traceTempVariable$s$51_1];
																																		double var177 = cpuVar[traceTempVariable$s$51_1];
																																		if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$177 = 0; index$sample39$177 < noStates; index$sample39$177 += 1) {
																									int distributionTempVariable$var38$179 = index$sample39$177;
																									double cv$probabilitySample39Value178 = (1.0 * distribution$sample39[index$sample39$177]);
																									{
																										int traceTempVariable$s$180_1 = distributionTempVariable$var38$179;
																										if((0 == i$var174)) {
																											{
																												for(int var75 = 0; var75 < noStates; var75 += 1) {
																													if((var75 == traceTempVariable$s$180_1)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$180_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$180_1];
																																				double var177 = cpuVar[traceTempVariable$s$180_1];
																																				if(((Math.log(cv$probabilitySample39Value178) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value178) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value178) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value178) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value178) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value178);
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
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$185_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$185_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$185_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$185_1];
																																	double var177 = cpuVar[traceTempVariable$s$185_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						{
																							int traceTempVariable$s$186_1 = distributionTempVariable$var56$28;
																							if((index$i$25 == i$var174)) {
																								{
																									for(int var75 = 0; var75 < noStates; var75 += 1) {
																										if((var75 == traceTempVariable$s$186_1)) {
																											{
																												for(int var128 = 0; var128 < noStates; var128 += 1) {
																													if((var128 == traceTempVariable$s$186_1)) {
																														{
																															{
																																{
																																	double var176 = cpuMean[traceTempVariable$s$186_1];
																																	double var177 = cpuVar[traceTempVariable$s$186_1];
																																	if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$187 = 1; index$i$187 < samples; index$i$187 += 1) {
																							if((!(index$i$187 == index$i$1) && !(index$i$187 == index$i$25))) {
																								for(int index$sample57$188 = 0; index$sample57$188 < noStates; index$sample57$188 += 1) {
																									int distributionTempVariable$var56$190 = index$sample57$188;
																									double cv$probabilitySample57Value189 = (1.0 * distribution$sample57[((index$i$187 - 1) / 1)][index$sample57$188]);
																									{
																										int traceTempVariable$s$191_1 = distributionTempVariable$var56$190;
																										if((index$i$187 == i$var174)) {
																											{
																												for(int var75 = 0; var75 < noStates; var75 += 1) {
																													if((var75 == traceTempVariable$s$191_1)) {
																														{
																															for(int var128 = 0; var128 < noStates; var128 += 1) {
																																if((var128 == traceTempVariable$s$191_1)) {
																																	{
																																		{
																																			{
																																				double var176 = cpuMean[traceTempVariable$s$191_1];
																																				double var177 = cpuVar[traceTempVariable$s$191_1];
																																				if(((Math.log(cv$probabilitySample57Value189) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value189) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value189) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value189) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value189) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value189);
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
																									}
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
												{
													{
														boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$241_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$241_1)) {
																									if(fixedFlag$sample39) {
																										{
																											if((0 == i$var174)) {
																												{
																													for(int var145 = 0; var145 < noStates; var145 += 1) {
																														if((var145 == traceTempVariable$s$241_1)) {
																															{
																																{
																																	{
																																		double var181 = memMean[traceTempVariable$s$241_1];
																																		double var182 = memVar[traceTempVariable$s$241_1];
																																		if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									} else {
																										if(true) {
																											for(int index$sample39$302 = 0; index$sample39$302 < noStates; index$sample39$302 += 1) {
																												int distributionTempVariable$var38$304 = index$sample39$302;
																												double cv$probabilitySample39Value303 = (1.0 * distribution$sample39[index$sample39$302]);
																												{
																													int traceTempVariable$s$305_1 = distributionTempVariable$var38$304;
																													if((0 == i$var174)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$305_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$305_1];
																																				double var182 = memVar[traceTempVariable$s$305_1];
																																				if(((Math.log(cv$probabilitySample39Value303) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value303) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value303) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value303) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value303) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value303);
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
																									}
																								}
																							}
																						}
																						{
																							for(int var93 = 0; var93 < noStates; var93 += 1) {
																								if((var93 == traceTempVariable$s$241_1)) {
																									{
																										int traceTempVariable$s$309_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$309_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$309_1];
																																	double var182 = memVar[traceTempVariable$s$309_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									{
																										int traceTempVariable$s$310_1 = distributionTempVariable$var56$28;
																										if((index$i$25 == i$var174)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$310_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$310_1];
																																	double var182 = memVar[traceTempVariable$s$310_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$311 = 1; index$i$311 < samples; index$i$311 += 1) {
																										if((!(index$i$311 == index$i$1) && !(index$i$311 == index$i$25))) {
																											for(int index$sample57$312 = 0; index$sample57$312 < noStates; index$sample57$312 += 1) {
																												int distributionTempVariable$var56$314 = index$sample57$312;
																												double cv$probabilitySample57Value313 = (1.0 * distribution$sample57[((index$i$311 - 1) / 1)][index$sample57$312]);
																												{
																													int traceTempVariable$s$315_1 = distributionTempVariable$var56$314;
																													if((index$i$311 == i$var174)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$315_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$315_1];
																																				double var182 = memVar[traceTempVariable$s$315_1];
																																				if(((Math.log(cv$probabilitySample57Value313) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value313) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value313) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value313) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value313) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value313);
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
																									}
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
														{
															int traceTempVariable$s$245_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var93 = 0; var93 < noStates; var93 += 1) {
																											if((var93 == traceTempVariable$s$245_1)) {
																												{
																													for(int var145 = 0; var145 < noStates; var145 += 1) {
																														if((var145 == traceTempVariable$s$245_1)) {
																															{
																																{
																																	{
																																		double var181 = memMean[traceTempVariable$s$245_1];
																																		double var182 = memVar[traceTempVariable$s$245_1];
																																		if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$371 = 0; index$sample39$371 < noStates; index$sample39$371 += 1) {
																									int distributionTempVariable$var38$373 = index$sample39$371;
																									double cv$probabilitySample39Value372 = (1.0 * distribution$sample39[index$sample39$371]);
																									{
																										int traceTempVariable$s$374_1 = distributionTempVariable$var38$373;
																										if((0 == i$var174)) {
																											{
																												for(int var93 = 0; var93 < noStates; var93 += 1) {
																													if((var93 == traceTempVariable$s$374_1)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$374_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$374_1];
																																				double var182 = memVar[traceTempVariable$s$374_1];
																																				if(((Math.log(cv$probabilitySample39Value372) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value372) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value372) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value372) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value372) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value372);
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
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$379_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$379_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$379_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$379_1];
																																	double var182 = memVar[traceTempVariable$s$379_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						{
																							int traceTempVariable$s$380_1 = distributionTempVariable$var56$28;
																							if((index$i$25 == i$var174)) {
																								{
																									for(int var93 = 0; var93 < noStates; var93 += 1) {
																										if((var93 == traceTempVariable$s$380_1)) {
																											{
																												for(int var145 = 0; var145 < noStates; var145 += 1) {
																													if((var145 == traceTempVariable$s$380_1)) {
																														{
																															{
																																{
																																	double var181 = memMean[traceTempVariable$s$380_1];
																																	double var182 = memVar[traceTempVariable$s$380_1];
																																	if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$381 = 1; index$i$381 < samples; index$i$381 += 1) {
																							if((!(index$i$381 == index$i$1) && !(index$i$381 == index$i$25))) {
																								for(int index$sample57$382 = 0; index$sample57$382 < noStates; index$sample57$382 += 1) {
																									int distributionTempVariable$var56$384 = index$sample57$382;
																									double cv$probabilitySample57Value383 = (1.0 * distribution$sample57[((index$i$381 - 1) / 1)][index$sample57$382]);
																									{
																										int traceTempVariable$s$385_1 = distributionTempVariable$var56$384;
																										if((index$i$381 == i$var174)) {
																											{
																												for(int var93 = 0; var93 < noStates; var93 += 1) {
																													if((var93 == traceTempVariable$s$385_1)) {
																														{
																															for(int var145 = 0; var145 < noStates; var145 += 1) {
																																if((var145 == traceTempVariable$s$385_1)) {
																																	{
																																		{
																																			{
																																				double var181 = memMean[traceTempVariable$s$385_1];
																																				double var182 = memVar[traceTempVariable$s$385_1];
																																				if(((Math.log(cv$probabilitySample57Value383) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value383) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value383) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value383) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value383) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value383);
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
																									}
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
												{
													{
														boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174))
																	guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
															}
														}
														{
															int traceTempVariable$s$435_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$435_1)) {
																									if(fixedFlag$sample39) {
																										{
																											if((0 == i$var174)) {
																												{
																													for(int var162 = 0; var162 < noStates; var162 += 1) {
																														if((var162 == traceTempVariable$s$435_1)) {
																															{
																																{
																																	{
																																		double var186 = pageFaultsMean[traceTempVariable$s$435_1];
																																		double var187 = pageFaultsVar[traceTempVariable$s$435_1];
																																		if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									} else {
																										if(true) {
																											for(int index$sample39$496 = 0; index$sample39$496 < noStates; index$sample39$496 += 1) {
																												int distributionTempVariable$var38$498 = index$sample39$496;
																												double cv$probabilitySample39Value497 = (1.0 * distribution$sample39[index$sample39$496]);
																												{
																													int traceTempVariable$s$499_1 = distributionTempVariable$var38$498;
																													if((0 == i$var174)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$499_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$499_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$499_1];
																																				if(((Math.log(cv$probabilitySample39Value497) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value497) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value497) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value497) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value497) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value497);
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
																									}
																								}
																							}
																						}
																						{
																							for(int var111 = 0; var111 < noStates; var111 += 1) {
																								if((var111 == traceTempVariable$s$435_1)) {
																									{
																										int traceTempVariable$s$503_1 = cv$currentValue;
																										if((index$i$1 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$503_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$503_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$503_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									{
																										int traceTempVariable$s$504_1 = distributionTempVariable$var56$28;
																										if((index$i$25 == i$var174)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$504_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$504_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$504_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$i$505 = 1; index$i$505 < samples; index$i$505 += 1) {
																										if((!(index$i$505 == index$i$1) && !(index$i$505 == index$i$25))) {
																											for(int index$sample57$506 = 0; index$sample57$506 < noStates; index$sample57$506 += 1) {
																												int distributionTempVariable$var56$508 = index$sample57$506;
																												double cv$probabilitySample57Value507 = (1.0 * distribution$sample57[((index$i$505 - 1) / 1)][index$sample57$506]);
																												{
																													int traceTempVariable$s$509_1 = distributionTempVariable$var56$508;
																													if((index$i$505 == i$var174)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$509_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$509_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$509_1];
																																				if(((Math.log(cv$probabilitySample57Value507) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value507) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value507) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value507) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value507) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value507);
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
																									}
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
														{
															int traceTempVariable$s$439_1 = distributionTempVariable$var56$28;
															for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
																if((i$var50 == i$var174)) {
																	if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
																		guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((i$var50 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample39) {
																							{
																								if((0 == i$var174)) {
																									{
																										for(int var111 = 0; var111 < noStates; var111 += 1) {
																											if((var111 == traceTempVariable$s$439_1)) {
																												{
																													for(int var162 = 0; var162 < noStates; var162 += 1) {
																														if((var162 == traceTempVariable$s$439_1)) {
																															{
																																{
																																	{
																																		double var186 = pageFaultsMean[traceTempVariable$s$439_1];
																																		double var187 = pageFaultsVar[traceTempVariable$s$439_1];
																																		if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							}
																						} else {
																							if(true) {
																								for(int index$sample39$565 = 0; index$sample39$565 < noStates; index$sample39$565 += 1) {
																									int distributionTempVariable$var38$567 = index$sample39$565;
																									double cv$probabilitySample39Value566 = (1.0 * distribution$sample39[index$sample39$565]);
																									{
																										int traceTempVariable$s$568_1 = distributionTempVariable$var38$567;
																										if((0 == i$var174)) {
																											{
																												for(int var111 = 0; var111 < noStates; var111 += 1) {
																													if((var111 == traceTempVariable$s$568_1)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$568_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$568_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$568_1];
																																				if(((Math.log(cv$probabilitySample39Value566) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value566) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value566) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value566) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value566) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value566);
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
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$s$573_1 = cv$currentValue;
																							if((index$i$1 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$573_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$573_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$573_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$573_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						{
																							int traceTempVariable$s$574_1 = distributionTempVariable$var56$28;
																							if((index$i$25 == i$var174)) {
																								{
																									for(int var111 = 0; var111 < noStates; var111 += 1) {
																										if((var111 == traceTempVariable$s$574_1)) {
																											{
																												for(int var162 = 0; var162 < noStates; var162 += 1) {
																													if((var162 == traceTempVariable$s$574_1)) {
																														{
																															{
																																{
																																	double var186 = pageFaultsMean[traceTempVariable$s$574_1];
																																	double var187 = pageFaultsVar[traceTempVariable$s$574_1];
																																	if(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						}
																						for(int index$i$575 = 1; index$i$575 < samples; index$i$575 += 1) {
																							if((!(index$i$575 == index$i$1) && !(index$i$575 == index$i$25))) {
																								for(int index$sample57$576 = 0; index$sample57$576 < noStates; index$sample57$576 += 1) {
																									int distributionTempVariable$var56$578 = index$sample57$576;
																									double cv$probabilitySample57Value577 = (1.0 * distribution$sample57[((index$i$575 - 1) / 1)][index$sample57$576]);
																									{
																										int traceTempVariable$s$579_1 = distributionTempVariable$var56$578;
																										if((index$i$575 == i$var174)) {
																											{
																												for(int var111 = 0; var111 < noStates; var111 += 1) {
																													if((var111 == traceTempVariable$s$579_1)) {
																														{
																															for(int var162 = 0; var162 < noStates; var162 += 1) {
																																if((var162 == traceTempVariable$s$579_1)) {
																																	{
																																		{
																																			{
																																				double var186 = pageFaultsMean[traceTempVariable$s$579_1];
																																				double var187 = pageFaultsVar[traceTempVariable$s$579_1];
																																				if(((Math.log(cv$probabilitySample57Value577) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value577) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value577) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value577) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value577) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value577);
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
																									}
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
										}
									}
								}
							}
						}
					}
				}
				{
					{
						{
							int traceTempVariable$var53$618_1 = cv$currentValue;
							for(int index$i$618_2 = 1; index$i$618_2 < samples; index$i$618_2 += 1) {
								if((i$var50 == (index$i$618_2 - 1))) {
									{
										{
											int index$i$620 = index$i$618_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var55;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											{
												for(int var29 = 0; var29 < noStates; var29 += 1) {
													if((var29 == traceTempVariable$var53$618_1)) {
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															if(fixedFlag$sample39) {
																{
																	if((0 == (i$var50 - 1))) {
																		{
																			for(int index$var29$627_1 = 0; index$var29$627_1 < noStates; index$var29$627_1 += 1) {
																				if((index$var29$627_1 == st[(i$var50 - 1)]))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$623 = 0; index$sample39$623 < noStates; index$sample39$623 += 1) {
																		int distributionTempVariable$var38$625 = index$sample39$623;
																		double cv$probabilitySample39Value624 = (1.0 * distribution$sample39[index$sample39$623]);
																		{
																			int traceTempVariable$var53$626_1 = distributionTempVariable$var38$625;
																			if((0 == (i$var50 - 1))) {
																				{
																					for(int index$var29$628_1 = 0; index$var29$628_1 < noStates; index$var29$628_1 += 1) {
																						if((index$var29$628_1 == traceTempVariable$var53$626_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample39Value624);
																					}
																				}
																			}
																		}
																	}
																}
															}
															{
																int traceTempVariable$var53$629_1 = cv$currentValue;
																if((index$i$1 == (i$var50 - 1))) {
																	{
																		for(int index$var29$635_1 = 0; index$var29$635_1 < noStates; index$var29$635_1 += 1) {
																			if((index$var29$635_1 == traceTempVariable$var53$629_1))
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
															for(int index$i$630 = 1; index$i$630 < samples; index$i$630 += 1) {
																if((!(index$i$630 == index$i$1) && !(index$i$630 == index$i$620))) {
																	for(int index$sample57$631 = 0; index$sample57$631 < noStates; index$sample57$631 += 1) {
																		int distributionTempVariable$var56$633 = index$sample57$631;
																		double cv$probabilitySample57Value632 = (1.0 * distribution$sample57[((index$i$630 - 1) / 1)][index$sample57$631]);
																		{
																			int traceTempVariable$var53$634_1 = distributionTempVariable$var56$633;
																			if((index$i$630 == (i$var50 - 1))) {
																				{
																					for(int index$var29$636_1 = 0; index$var29$636_1 < noStates; index$var29$636_1 += 1) {
																						if((index$var29$636_1 == traceTempVariable$var53$634_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value632);
																					}
																				}
																			}
																		}
																	}
																}
															}
															double[] var54 = m[traceTempVariable$var53$618_1];
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var54, noStates);
														}
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample57[((index$i$618_2 - 1) / 1)];
											double cv$overlap = 0.0;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
												double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
												double cv$sampleDistValue = cv$sampleDistribution[cv$i];
												if((cv$sampleDistValue < cv$normalisedDistValue))
													cv$overlap = (cv$overlap + cv$sampleDistValue);
												else
													cv$overlap = (cv$overlap + cv$normalisedDistValue);
											}
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample57[((i$var50 - 1) / 1)]) {
				double[] cv$localProbability = distribution$sample57[((i$var50 - 1) / 1)];
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void sample77(int var75) {
		if(true) {
			constrainedFlag$sample77[((var75 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = cpuMean[var75];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample77[((var75 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var76 = cv$proposedValue;
						{
							{
								{
									cpuMean[var75] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 8.6)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 16.0) / Math.sqrt(8.6))) - (0.5 * Math.log(8.6))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var176$8_1 = cv$currentValue;
													if((var75 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample77[((var75 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == st[i$var174])) {
																							{
																								{
																									{
																										double var177 = cpuVar[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == st[i$var174])) {
																									{
																										{
																											{
																												double var177 = cpuVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var177 = cpuVar[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$8_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var176$9_1 = cv$currentValue;
															if((var75 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample77[((var75 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var177 = cpuVar[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var177 = cpuVar[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var177 = cpuVar[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var128 = 0; var128 < noStates; var128 += 1) {
																												if((var128 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var177 = cpuVar[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$9_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var176$17_1 = cv$currentValue;
														if((var75 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample77[((var75 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == st[i$var174])) {
																									{
																										{
																											{
																												double var177 = cpuVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var177 = cpuVar[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var128 = 0; var128 < noStates; var128 += 1) {
																								if((var128 == st[i$var174])) {
																									{
																										{
																											{
																												double var177 = cpuVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$17_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var176$18_1 = cv$currentValue;
																if((var75 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample77[((var75 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var128 = 0; var128 < noStates; var128 += 1) {
																										if((var128 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var177 = cpuVar[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var128 = 0; var128 < noStates; var128 += 1) {
																												if((var128 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var177 = cpuVar[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var128 = 0; var128 < noStates; var128 += 1) {
																									if((var128 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var177 = cpuVar[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var128 = 0; var128 < noStates; var128 += 1) {
																												if((var128 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var177 = cpuVar[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - traceTempVariable$var176$18_1) / Math.sqrt(var177))) - (0.5 * Math.log(var177))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var76 = cv$originalValue;
							{
								{
									{
										cpuMean[var75] = var76;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample95(int var93) {
		if(true) {
			constrainedFlag$sample95[((var93 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = memMean[var93];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample95[((var93 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var94 = cv$proposedValue;
						{
							{
								{
									memMean[var93] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 1.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 94.0) / Math.sqrt(1.0))) - (0.5 * Math.log(1.0))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample39) {
										{
											if((0 == i$var174)) {
												{
													double traceTempVariable$var181$8_1 = cv$currentValue;
													if((var93 == st[i$var174])) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample95[((var93 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			if((0 == i$var174)) {
																				{
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == st[i$var174])) {
																							{
																								{
																									{
																										double var182 = memVar[st[i$var174]];
																										if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample57) {
																			{
																				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																					if((i$var50 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == st[i$var174])) {
																									{
																										{
																											{
																												double var182 = memVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																				if(true) {
																					for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
																						int distributionTempVariable$var56$29 = index$sample57$27;
																						double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
																						{
																							int traceTempVariable$s$30_1 = distributionTempVariable$var56$29;
																							if((i$var50 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$30_1)) {
																											{
																												{
																													{
																														double var182 = memVar[traceTempVariable$s$30_1];
																														if(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value28) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$8_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value28);
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
									} else {
										if(true) {
											for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
												int distributionTempVariable$var38$6 = index$sample39$4;
												double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
												{
													int traceTempVariable$s$7_1 = distributionTempVariable$var38$6;
													if((0 == i$var174)) {
														{
															double traceTempVariable$var181$9_1 = cv$currentValue;
															if((var93 == traceTempVariable$s$7_1)) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample95[((var93 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					int traceTempVariable$s$33_1 = distributionTempVariable$var38$6;
																					if((0 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == traceTempVariable$s$33_1)) {
																									{
																										{
																											{
																												double var182 = memVar[traceTempVariable$s$33_1];
																												if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																						int distributionTempVariable$var38$36 = index$sample39$34;
																						double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																						{
																							int traceTempVariable$s$37_1 = distributionTempVariable$var38$36;
																							if((0 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$37_1)) {
																											{
																												{
																													{
																														double var182 = memVar[traceTempVariable$s$37_1];
																														if(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value35) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
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
																				if(fixedFlag$sample57) {
																					{
																						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																							if((i$var50 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$7_1)) {
																											{
																												{
																													{
																														double var182 = memVar[traceTempVariable$s$7_1];
																														if(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																						if(true) {
																							for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
																								int distributionTempVariable$var56$44 = index$sample57$42;
																								double cv$probabilitySample57Value43 = (cv$probabilitySample39Value5 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$42]);
																								{
																									int traceTempVariable$s$45_1 = distributionTempVariable$var56$44;
																									if((i$var50 == i$var174)) {
																										{
																											for(int var145 = 0; var145 < noStates; var145 += 1) {
																												if((var145 == traceTempVariable$s$45_1)) {
																													{
																														{
																															{
																																double var182 = memVar[traceTempVariable$s$45_1];
																																if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$9_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
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
									}
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if(fixedFlag$sample57) {
										{
											for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
												if((i$var50 == i$var174)) {
													{
														double traceTempVariable$var181$17_1 = cv$currentValue;
														if((var93 == st[i$var174])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample95[((var93 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample39) {
																				{
																					if((0 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == st[i$var174])) {
																									{
																										{
																											{
																												double var182 = memVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample39$49 = 0; index$sample39$49 < noStates; index$sample39$49 += 1) {
																						int distributionTempVariable$var38$51 = index$sample39$49;
																						double cv$probabilitySample39Value50 = (1.0 * distribution$sample39[index$sample39$49]);
																						{
																							int traceTempVariable$s$52_1 = distributionTempVariable$var38$51;
																							if((0 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$52_1)) {
																											{
																												{
																													{
																														double var182 = memVar[traceTempVariable$s$52_1];
																														if(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value50) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value50);
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
																			}
																			{
																				for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
																					if((index$i$55_1 == i$var174)) {
																						{
																							for(int var145 = 0; var145 < noStates; var145 += 1) {
																								if((var145 == st[i$var174])) {
																									{
																										{
																											{
																												double var182 = memVar[st[i$var174]];
																												if(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$17_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
													int distributionTempVariable$var56$15 = index$sample57$13;
													double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
													{
														int traceTempVariable$s$16_1 = distributionTempVariable$var56$15;
														if((i$var50 == i$var174)) {
															{
																double traceTempVariable$var181$18_1 = cv$currentValue;
																if((var93 == traceTempVariable$s$16_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample95[((var93 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample39) {
																						{
																							if((0 == i$var174)) {
																								{
																									for(int var145 = 0; var145 < noStates; var145 += 1) {
																										if((var145 == traceTempVariable$s$16_1)) {
																											{
																												{
																													{
																														double var182 = memVar[traceTempVariable$s$16_1];
																														if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample39$58 = 0; index$sample39$58 < noStates; index$sample39$58 += 1) {
																								int distributionTempVariable$var38$60 = index$sample39$58;
																								double cv$probabilitySample39Value59 = (cv$probabilitySample57Value14 * distribution$sample39[index$sample39$58]);
																								{
																									int traceTempVariable$s$61_1 = distributionTempVariable$var38$60;
																									if((0 == i$var174)) {
																										{
																											for(int var145 = 0; var145 < noStates; var145 += 1) {
																												if((var145 == traceTempVariable$s$61_1)) {
																													{
																														{
																															{
																																double var182 = memVar[traceTempVariable$s$61_1];
																																if(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample39Value59) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value59);
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
																					}
																					{
																						int traceTempVariable$s$64_1 = distributionTempVariable$var56$15;
																						if((i$var50 == i$var174)) {
																							{
																								for(int var145 = 0; var145 < noStates; var145 += 1) {
																									if((var145 == traceTempVariable$s$64_1)) {
																										{
																											{
																												{
																													double var182 = memVar[traceTempVariable$s$64_1];
																													if(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$65 = 1; index$i$65 < samples; index$i$65 += 1) {
																						if(!(index$i$65 == i$var50)) {
																							for(int index$sample57$66 = 0; index$sample57$66 < noStates; index$sample57$66 += 1) {
																								int distributionTempVariable$var56$68 = index$sample57$66;
																								double cv$probabilitySample57Value67 = (cv$probabilitySample57Value14 * distribution$sample57[((index$i$65 - 1) / 1)][index$sample57$66]);
																								{
																									int traceTempVariable$s$69_1 = distributionTempVariable$var56$68;
																									if((index$i$65 == i$var174)) {
																										{
																											for(int var145 = 0; var145 < noStates; var145 += 1) {
																												if((var145 == traceTempVariable$s$69_1)) {
																													{
																														{
																															{
																																double var182 = memVar[traceTempVariable$s$69_1];
																																if(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value67) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - traceTempVariable$var181$18_1) / Math.sqrt(var182))) - (0.5 * Math.log(var182))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value67);
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var94 = cv$originalValue;
							{
								{
									{
										memMean[var93] = var94;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var30$countGlobal = new double[noStates];
		}
		{
			cv$var35$countGlobal = new double[noStates];
		}
		{
			int cv$var31$max = noStates;
			cv$distributionAccumulator$var55 = new double[cv$var31$max];
		}
		{
			cv$var38$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample39gaussian179$global = new boolean[cv$max_i$var174];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample39gaussian184$global = new boolean[cv$max_i$var174];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample39gaussian189$global = new boolean[cv$max_i$var174];
		}
		{
			int cv$var31$max = noStates;
			cv$var56$stateProbabilityGlobal = new double[cv$var31$max];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample57gaussian179$global = new boolean[cv$max_i$var174];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample57gaussian184$global = new boolean[cv$max_i$var174];
		}
		{
			int cv$max_i$var174 = 0;
			cv$max_i$var174 = Math.max(cv$max_i$var174, ((length$cpu_measured - 0) / 1));
			guard$sample57gaussian189$global = new boolean[cv$max_i$var174];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		if(!fixedFlag$sample30) {
			{
				m = new double[noStates][];
				for(int var29 = 0; var29 < noStates; var29 += 1)
					m[var29] = new double[noStates];
			}
		}
		if((!fixedFlag$sample39 || !fixedFlag$sample57)) {
			{
				st = new int[length$cpu_measured];
			}
		}
		if(!fixedFlag$sample36) {
			{
				initialStateDistribution = new double[noStates];
			}
		}
		{
			cpu = new double[length$cpu_measured];
		}
		{
			mem = new double[length$cpu_measured];
		}
		{
			pageFaults = new double[length$cpu_measured];
		}
		if(!fixedFlag$sample77) {
			{
				cpuMean = new double[noStates];
			}
		}
		if(!fixedFlag$sample95) {
			{
				memMean = new double[noStates];
			}
		}
		if(!fixedFlag$sample113) {
			{
				pageFaultsMean = new double[noStates];
			}
		}
		if(!fixedFlag$sample130) {
			{
				cpuVar = new double[noStates];
			}
		}
		if(!fixedFlag$sample147) {
			{
				memVar = new double[noStates];
			}
		}
		if(!fixedFlag$sample164) {
			{
				pageFaultsVar = new double[noStates];
			}
		}
		{
			distribution$sample39 = new double[noStates];
		}
		{
			distribution$sample57 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)][];
			for(int i$var50 = 1; i$var50 < length$cpu_measured; i$var50 += 1)
				distribution$sample57[((i$var50 - 1) / 1)] = new double[noStates];
		}
		{
			constrainedFlag$sample95 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample30 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample77 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample57 = new boolean[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			constrainedFlag$sample164 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample147 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample130 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample113 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample57 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample180 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample185 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample190 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!fixedFlag$sample57)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!fixedFlag$sample77)
				cpuMean[var75] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!fixedFlag$sample95)
				memMean[var93] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!fixedFlag$sample113)
				pageFaultsMean[var111] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!fixedFlag$sample130)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!fixedFlag$sample147)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!fixedFlag$sample164)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
			cpu[i$var174] = ((Math.sqrt(cpuVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var174]]);
			mem[i$var174] = ((Math.sqrt(memVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var174]]);
			pageFaults[i$var174] = ((Math.sqrt(pageFaultsVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var174]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		double[] cv$distribution$sample39 = distribution$sample39;
		for(int index$var37 = 0; index$var37 < noStates; index$var37 += 1) {
			double cv$value = ((((((0.0 <= index$var37) && (index$var37 < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[index$var37])) && (initialStateDistribution[index$var37] <= 1.0))?initialStateDistribution[index$var37]:0.0);
			if(!fixedFlag$sample39)
				cv$distribution$sample39[index$var37] = cv$value;
		}
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			double[] cv$distribution$sample57 = distribution$sample57[((i$var50 - 1) / 1)];
			for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
				if(!fixedFlag$sample57)
					cv$distribution$sample57[index$var55] = 0.0;
			}
			if(fixedFlag$sample39) {
				{
					if((0 == (i$var50 - 1))) {
						{
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)])) {
									{
										double[] var54 = m[st[(i$var50 - 1)]];
										for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
											if(!fixedFlag$sample57)
												cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (1.0 * ((((((0.0 <= index$var55) && (index$var55 < noStates)) && (0 < noStates)) && (0.0 <= var54[index$var55])) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample39$2 = 0; index$sample39$2 < noStates; index$sample39$2 += 1) {
						int distributionTempVariable$var38$4 = index$sample39$2;
						double cv$probabilitySample39Value3 = (1.0 * distribution$sample39[index$sample39$2]);
						{
							int traceTempVariable$var53$5_1 = distributionTempVariable$var38$4;
							if((0 == (i$var50 - 1))) {
								{
									for(int var29 = 0; var29 < noStates; var29 += 1) {
										if((var29 == traceTempVariable$var53$5_1)) {
											{
												double[] var54 = m[traceTempVariable$var53$5_1];
												for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
													if(!fixedFlag$sample57)
														cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * ((((((0.0 <= index$var55) && (index$var55 < noStates)) && (0 < noStates)) && (0.0 <= var54[index$var55])) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
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
			if(fixedFlag$sample57) {
				{
					for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
						if((index$i$8_1 == (i$var50 - 1))) {
							{
								for(int var29 = 0; var29 < noStates; var29 += 1) {
									if((var29 == st[(i$var50 - 1)])) {
										{
											double[] var54 = m[st[(i$var50 - 1)]];
											for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
												if(!fixedFlag$sample57)
													cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (1.0 * ((((((0.0 <= index$var55) && (index$var55 < noStates)) && (0 < noStates)) && (0.0 <= var54[index$var55])) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample57$10 = 0; index$sample57$10 < noStates; index$sample57$10 += 1) {
							int distributionTempVariable$var56$12 = index$sample57$10;
							double cv$probabilitySample57Value11 = (1.0 * distribution$sample57[((index$i$9 - 1) / 1)][index$sample57$10]);
							{
								int traceTempVariable$var53$13_1 = distributionTempVariable$var56$12;
								if((index$i$9 == (i$var50 - 1))) {
									{
										for(int var29 = 0; var29 < noStates; var29 += 1) {
											if((var29 == traceTempVariable$var53$13_1)) {
												{
													double[] var54 = m[traceTempVariable$var53$13_1];
													for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
														if(!fixedFlag$sample57)
															cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * ((((((0.0 <= index$var55) && (index$var55 < noStates)) && (0 < noStates)) && (0.0 <= var54[index$var55])) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
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
			}
			double cv$var55$sum = 0.0;
			for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
				if(!fixedFlag$sample57)
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
			}
			for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1) {
				if(!fixedFlag$sample57)
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!fixedFlag$sample77)
				cpuMean[var75] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!fixedFlag$sample95)
				memMean[var93] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!fixedFlag$sample113)
				pageFaultsMean[var111] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!fixedFlag$sample130)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!fixedFlag$sample147)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!fixedFlag$sample164)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!fixedFlag$sample57)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!fixedFlag$sample77)
				cpuMean[var75] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!fixedFlag$sample95)
				memMean[var93] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!fixedFlag$sample113)
				pageFaultsMean[var111] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!fixedFlag$sample130)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!fixedFlag$sample147)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!fixedFlag$sample164)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
			cpu[i$var174] = ((Math.sqrt(cpuVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var174]]);
			mem[i$var174] = ((Math.sqrt(memVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var174]]);
			pageFaults[i$var174] = ((Math.sqrt(pageFaultsVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var174]]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!fixedFlag$sample57)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!fixedFlag$sample77)
				cpuMean[var75] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!fixedFlag$sample95)
				memMean[var93] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!fixedFlag$sample113)
				pageFaultsMean[var111] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!fixedFlag$sample130)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!fixedFlag$sample147)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!fixedFlag$sample164)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!fixedFlag$sample57)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!fixedFlag$sample77)
				cpuMean[var75] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!fixedFlag$sample95)
				memMean[var93] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!fixedFlag$sample113)
				pageFaultsMean[var111] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!fixedFlag$sample130)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!fixedFlag$sample147)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!fixedFlag$sample164)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var29 = 0; var29 < noStates; var29 += 1) {
				if(!fixedFlag$sample30)
					sample30(var29);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample39)
				sample39();
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				if(!fixedFlag$sample57)
					sample57(i$var50);
			}
			for(int var75 = 0; var75 < noStates; var75 += 1) {
				if(!fixedFlag$sample77)
					sample77(var75);
			}
			for(int var93 = 0; var93 < noStates; var93 += 1) {
				if(!fixedFlag$sample95)
					sample95(var93);
			}
			for(int var111 = 0; var111 < noStates; var111 += 1) {
				if(!fixedFlag$sample113)
					sample113(var111);
			}
			for(int var128 = 0; var128 < noStates; var128 += 1) {
				if(!fixedFlag$sample130)
					sample130(var128);
			}
			for(int var145 = 0; var145 < noStates; var145 += 1) {
				if(!fixedFlag$sample147)
					sample147(var145);
			}
			for(int var162 = 0; var162 < noStates; var162 += 1) {
				if(!fixedFlag$sample164)
					sample164(var162);
			}
		} else {
			for(int var162 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var162 >= ((0 - 1) + 1); var162 -= 1) {
				if(!fixedFlag$sample164)
					sample164(var162);
			}
			for(int var145 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var145 >= ((0 - 1) + 1); var145 -= 1) {
				if(!fixedFlag$sample147)
					sample147(var145);
			}
			for(int var128 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var128 >= ((0 - 1) + 1); var128 -= 1) {
				if(!fixedFlag$sample130)
					sample130(var128);
			}
			for(int var111 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var111 >= ((0 - 1) + 1); var111 -= 1) {
				if(!fixedFlag$sample113)
					sample113(var111);
			}
			for(int var93 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var93 >= ((0 - 1) + 1); var93 -= 1) {
				if(!fixedFlag$sample95)
					sample95(var93);
			}
			for(int var75 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var75 >= ((0 - 1) + 1); var75 -= 1) {
				if(!fixedFlag$sample77)
					sample77(var75);
			}
			for(int i$var50 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var50 >= ((1 - 1) + 1); i$var50 -= 1) {
				if(!fixedFlag$sample57)
					sample57(i$var50);
			}
			if(!fixedFlag$sample39)
				sample39();
			if(!fixedFlag$sample36)
				sample36();
			for(int var29 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
				if(!fixedFlag$sample30)
					sample30(var29);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var30 = Double.NaN;
		if(!fixedProbFlag$sample36)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var38 = Double.NaN;
		if(!fixedProbFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				logProbability$sample57[((i$var50 - 1) / 1)] = Double.NaN;
		}
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var76 = Double.NaN;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var94 = Double.NaN;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample113)
			logProbability$var112 = Double.NaN;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample130)
			logProbability$var129 = Double.NaN;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample147)
			logProbability$var146 = Double.NaN;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample164)
			logProbability$var163 = Double.NaN;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample180) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample180[((i$var174 - 0) / 1)] = Double.NaN;
		}
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample185[((i$var174 - 0) / 1)] = Double.NaN;
		}
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample190[((i$var174 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var15 = 0; var15 < noStates; var15 += 1)
			v[var15] = 0.1;
		samples = length$cpu_measured;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
			constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
			constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
		for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
			constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
		for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
			constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
		for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
			constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(fixedFlag$sample113)
			logProbabilityValue$sample113();
		if(fixedFlag$sample130)
			logProbabilityValue$sample130();
		if(fixedFlag$sample147)
			logProbabilityValue$sample147();
		if(fixedFlag$sample164)
			logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample39();
		logProbabilityDistribution$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityDistribution$sample180();
		logProbabilityDistribution$sample185();
		logProbabilityDistribution$sample190();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityValue$sample39();
		logProbabilityValue$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	@Override
	public final void propagateObservedValues() {
		{
			double[] cv$source1 = cpu_measured;
			double[] cv$target1 = cpu;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			double[] cv$source1 = mem_measured;
			double[] cv$target1 = mem;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			double[] cv$source1 = pageFaults_measured;
			double[] cv$target1 = pageFaults;
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n"
		     + "    \n"
		     + "    // Construct vectors describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    // Determine how many samples the model will need to produce.\n"
		     + "    int samples = cpu_measured.length;\n"
		     + "    \n"
		     + "    // Allocate space for the state.\n"
		     + "    int[] st = new int[samples];\n"
		     + "\n"
		     + "    // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    st[0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "\n"
		     + "    //Determine the remaining states based on the previous state.\n"
		     + "    for(int i:[1 .. samples))\n"
		     + "        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "    //Generate each metric.\n"
		     + "    double[] cpu = new double[samples];\n"
		     + "    double[] mem = new double[samples];\n"
		     + "    double[] pageFaults = new double[samples];\n"
		     + "    \n"
		     + "    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n"
		     + "    double[] memMean = gaussian(94, 1).sample(noStates);\n"
		     + "    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n"
		     + "    \n"
		     + "    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    \n"
		     + "    for(int i:[0 .. samples)) {\n"
		     + "        int s = st[i];\n"
		     + "        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n"
		     + "        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n"
		     + "        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the values we have measured.\n"
		     + "    cpu.observe(cpu_measured);\n"
		     + "    mem.observe(mem_measured);\n"
		     + "    pageFaults.observe(pageFaults_measured);\n"
		     + "}\n"
		     + "";
	}
}