package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
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
	private double logProbability$var100;
	private double logProbability$var112;
	private double logProbability$var117;
	private double logProbability$var129;
	private double logProbability$var134;
	private double logProbability$var146;
	private double logProbability$var151;
	private double logProbability$var163;
	private double[] logProbability$var178;
	private double logProbability$var18;
	private double[] logProbability$var183;
	private double[] logProbability$var188;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var37;
	private double logProbability$var38;
	private double[] logProbability$var55;
	private double logProbability$var64;
	private double logProbability$var76;
	private double logProbability$var82;
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
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var174];
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var75 = 0; var75 < noStates; var75 += 1) {
								if((var75 == st[i$var174])) {
									if((0 == i$var174)) {
										for(int var128 = 0; var128 < noStates; var128 += 1) {
											if((var128 == st[i$var174])) {
												{
													double var176 = cpuMean[st[i$var174]];
													double var177 = cpuVar[st[i$var174]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
						if(true) {
							for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
								int distributionTempVariable$var38$5 = index$sample39$3;
								double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
								if((0 == i$var174)) {
									for(int var75 = 0; var75 < noStates; var75 += 1) {
										if((var75 == st[i$var174])) {
											if((0 == i$var174)) {
												for(int var128 = 0; var128 < noStates; var128 += 1) {
													if((var128 == st[i$var174])) {
														{
															double var176 = cpuMean[st[i$var174]];
															double var177 = cpuVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
											if(!true) {
												for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
													int distributionTempVariable$var38$13 = index$sample39$11;
													double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
													if((0 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	double var176 = cpuMean[st[i$var174]];
																	double var177 = cpuVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var75 = 0; var75 < noStates; var75 += 1) {
								if((var75 == st[i$var174])) {
									if(fixedFlag$sample57) {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if((i$var50 == i$var174)) {
												for(int var128 = 0; var128 < noStates; var128 += 1) {
													if((var128 == st[i$var174])) {
														{
															double var176 = cpuMean[st[i$var174]];
															double var177 = cpuVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
													int distributionTempVariable$var56$29 = index$sample57$27;
													double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
													if((i$var50 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	double var176 = cpuMean[st[i$var174]];
																	double var177 = cpuVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					} else {
						if(true) {
							for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
								int distributionTempVariable$var38$21 = index$sample39$19;
								double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
								if((0 == i$var174)) {
									for(int var75 = 0; var75 < noStates; var75 += 1) {
										if((var75 == st[i$var174])) {
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	double var176 = cpuMean[st[i$var174]];
																	double var177 = cpuVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
											} else {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
															int distributionTempVariable$var56$35 = index$sample57$33;
															double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
															if((i$var50 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			double var176 = cpuMean[st[i$var174]];
																			double var177 = cpuVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var75 = 0; var75 < noStates; var75 += 1) {
									if((var75 == st[i$var174])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var174)) {
												for(int var128 = 0; var128 < noStates; var128 += 1) {
													if((var128 == st[i$var174])) {
														{
															double var176 = cpuMean[st[i$var174]];
															double var177 = cpuVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
									int distributionTempVariable$var56$45 = index$sample57$43;
									double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
									if((i$var50 == i$var174)) {
										for(int var75 = 0; var75 < noStates; var75 += 1) {
											if((var75 == st[i$var174])) {
												if((i$var50 == i$var174)) {
													for(int var128 = 0; var128 < noStates; var128 += 1) {
														if((var128 == st[i$var174])) {
															{
																double var176 = cpuMean[st[i$var174]];
																double var177 = cpuVar[st[i$var174]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var50)) {
														for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
															int distributionTempVariable$var56$54 = index$sample57$52;
															double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
															if((index$i$51 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			double var176 = cpuMean[st[i$var174]];
																			double var177 = cpuVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var75 = 0; var75 < noStates; var75 += 1) {
									if((var75 == st[i$var174])) {
										if(fixedFlag$sample39) {
											if((0 == i$var174)) {
												for(int var128 = 0; var128 < noStates; var128 += 1) {
													if((var128 == st[i$var174])) {
														{
															double var176 = cpuMean[st[i$var174]];
															double var177 = cpuVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
										} else {
											if(true) {
												for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
													int distributionTempVariable$var38$70 = index$sample39$68;
													double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
													if((0 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	double var176 = cpuMean[st[i$var174]];
																	double var177 = cpuVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
									int distributionTempVariable$var56$63 = index$sample57$61;
									double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
									if((i$var50 == i$var174)) {
										for(int var75 = 0; var75 < noStates; var75 += 1) {
											if((var75 == st[i$var174])) {
												if(fixedFlag$sample39) {
													if((0 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	double var176 = cpuMean[st[i$var174]];
																	double var177 = cpuVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
												} else {
													if(true) {
														for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
															int distributionTempVariable$var38$75 = index$sample39$73;
															double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
															if((0 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			double var176 = cpuMean[st[i$var174]];
																			double var177 = cpuVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var178[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample180[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample180[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var178[((i$var174 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample185() {
		if(!fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var174];
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var93 = 0; var93 < noStates; var93 += 1) {
								if((var93 == st[i$var174])) {
									if((0 == i$var174)) {
										for(int var145 = 0; var145 < noStates; var145 += 1) {
											if((var145 == st[i$var174])) {
												{
													double var181 = memMean[st[i$var174]];
													double var182 = memVar[st[i$var174]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
						if(true) {
							for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
								int distributionTempVariable$var38$5 = index$sample39$3;
								double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
								if((0 == i$var174)) {
									for(int var93 = 0; var93 < noStates; var93 += 1) {
										if((var93 == st[i$var174])) {
											if((0 == i$var174)) {
												for(int var145 = 0; var145 < noStates; var145 += 1) {
													if((var145 == st[i$var174])) {
														{
															double var181 = memMean[st[i$var174]];
															double var182 = memVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
											if(!true) {
												for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
													int distributionTempVariable$var38$13 = index$sample39$11;
													double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
													if((0 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	double var181 = memMean[st[i$var174]];
																	double var182 = memVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var93 = 0; var93 < noStates; var93 += 1) {
								if((var93 == st[i$var174])) {
									if(fixedFlag$sample57) {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if((i$var50 == i$var174)) {
												for(int var145 = 0; var145 < noStates; var145 += 1) {
													if((var145 == st[i$var174])) {
														{
															double var181 = memMean[st[i$var174]];
															double var182 = memVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
													int distributionTempVariable$var56$29 = index$sample57$27;
													double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
													if((i$var50 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	double var181 = memMean[st[i$var174]];
																	double var182 = memVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					} else {
						if(true) {
							for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
								int distributionTempVariable$var38$21 = index$sample39$19;
								double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
								if((0 == i$var174)) {
									for(int var93 = 0; var93 < noStates; var93 += 1) {
										if((var93 == st[i$var174])) {
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	double var181 = memMean[st[i$var174]];
																	double var182 = memVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
											} else {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
															int distributionTempVariable$var56$35 = index$sample57$33;
															double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
															if((i$var50 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			double var181 = memMean[st[i$var174]];
																			double var182 = memVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var93 = 0; var93 < noStates; var93 += 1) {
									if((var93 == st[i$var174])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var174)) {
												for(int var145 = 0; var145 < noStates; var145 += 1) {
													if((var145 == st[i$var174])) {
														{
															double var181 = memMean[st[i$var174]];
															double var182 = memVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
									int distributionTempVariable$var56$45 = index$sample57$43;
									double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
									if((i$var50 == i$var174)) {
										for(int var93 = 0; var93 < noStates; var93 += 1) {
											if((var93 == st[i$var174])) {
												if((i$var50 == i$var174)) {
													for(int var145 = 0; var145 < noStates; var145 += 1) {
														if((var145 == st[i$var174])) {
															{
																double var181 = memMean[st[i$var174]];
																double var182 = memVar[st[i$var174]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var50)) {
														for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
															int distributionTempVariable$var56$54 = index$sample57$52;
															double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
															if((index$i$51 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			double var181 = memMean[st[i$var174]];
																			double var182 = memVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var93 = 0; var93 < noStates; var93 += 1) {
									if((var93 == st[i$var174])) {
										if(fixedFlag$sample39) {
											if((0 == i$var174)) {
												for(int var145 = 0; var145 < noStates; var145 += 1) {
													if((var145 == st[i$var174])) {
														{
															double var181 = memMean[st[i$var174]];
															double var182 = memVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
										} else {
											if(true) {
												for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
													int distributionTempVariable$var38$70 = index$sample39$68;
													double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
													if((0 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	double var181 = memMean[st[i$var174]];
																	double var182 = memVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
									int distributionTempVariable$var56$63 = index$sample57$61;
									double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
									if((i$var50 == i$var174)) {
										for(int var93 = 0; var93 < noStates; var93 += 1) {
											if((var93 == st[i$var174])) {
												if(fixedFlag$sample39) {
													if((0 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	double var181 = memMean[st[i$var174]];
																	double var182 = memVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
												} else {
													if(true) {
														for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
															int distributionTempVariable$var38$75 = index$sample39$73;
															double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
															if((0 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			double var181 = memMean[st[i$var174]];
																			double var182 = memVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var183[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample185[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample185[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var183[((i$var174 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var174];
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var111 = 0; var111 < noStates; var111 += 1) {
								if((var111 == st[i$var174])) {
									if((0 == i$var174)) {
										for(int var162 = 0; var162 < noStates; var162 += 1) {
											if((var162 == st[i$var174])) {
												{
													double var186 = pageFaultsMean[st[i$var174]];
													double var187 = pageFaultsVar[st[i$var174]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
						if(true) {
							for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
								int distributionTempVariable$var38$5 = index$sample39$3;
								double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
								if((0 == i$var174)) {
									for(int var111 = 0; var111 < noStates; var111 += 1) {
										if((var111 == st[i$var174])) {
											if((0 == i$var174)) {
												for(int var162 = 0; var162 < noStates; var162 += 1) {
													if((var162 == st[i$var174])) {
														{
															double var186 = pageFaultsMean[st[i$var174]];
															double var187 = pageFaultsVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
											if(!true) {
												for(int index$sample39$11 = 0; index$sample39$11 < noStates; index$sample39$11 += 1) {
													int distributionTempVariable$var38$13 = index$sample39$11;
													double cv$probabilitySample39Value12 = (cv$probabilitySample39Value4 * distribution$sample39[index$sample39$11]);
													if((0 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	double var186 = pageFaultsMean[st[i$var174]];
																	double var187 = pageFaultsVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					if(fixedFlag$sample39) {
						if((0 == i$var174)) {
							for(int var111 = 0; var111 < noStates; var111 += 1) {
								if((var111 == st[i$var174])) {
									if(fixedFlag$sample57) {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if((i$var50 == i$var174)) {
												for(int var162 = 0; var162 < noStates; var162 += 1) {
													if((var162 == st[i$var174])) {
														{
															double var186 = pageFaultsMean[st[i$var174]];
															double var187 = pageFaultsVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
									} else {
										for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
											if(true) {
												for(int index$sample57$27 = 0; index$sample57$27 < noStates; index$sample57$27 += 1) {
													int distributionTempVariable$var56$29 = index$sample57$27;
													double cv$probabilitySample57Value28 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$27]);
													if((i$var50 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	double var186 = pageFaultsMean[st[i$var174]];
																	double var187 = pageFaultsVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					} else {
						if(true) {
							for(int index$sample39$19 = 0; index$sample39$19 < noStates; index$sample39$19 += 1) {
								int distributionTempVariable$var38$21 = index$sample39$19;
								double cv$probabilitySample39Value20 = (1.0 * distribution$sample39[index$sample39$19]);
								if((0 == i$var174)) {
									for(int var111 = 0; var111 < noStates; var111 += 1) {
										if((var111 == st[i$var174])) {
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	double var186 = pageFaultsMean[st[i$var174]];
																	double var187 = pageFaultsVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
											} else {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$33 = 0; index$sample57$33 < noStates; index$sample57$33 += 1) {
															int distributionTempVariable$var56$35 = index$sample57$33;
															double cv$probabilitySample57Value34 = (cv$probabilitySample39Value20 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$33]);
															if((i$var50 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			double var186 = pageFaultsMean[st[i$var174]];
																			double var187 = pageFaultsVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var111 = 0; var111 < noStates; var111 += 1) {
									if((var111 == st[i$var174])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var174)) {
												for(int var162 = 0; var162 < noStates; var162 += 1) {
													if((var162 == st[i$var174])) {
														{
															double var186 = pageFaultsMean[st[i$var174]];
															double var187 = pageFaultsVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
									int distributionTempVariable$var56$45 = index$sample57$43;
									double cv$probabilitySample57Value44 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$43]);
									if((i$var50 == i$var174)) {
										for(int var111 = 0; var111 < noStates; var111 += 1) {
											if((var111 == st[i$var174])) {
												if((i$var50 == i$var174)) {
													for(int var162 = 0; var162 < noStates; var162 += 1) {
														if((var162 == st[i$var174])) {
															{
																double var186 = pageFaultsMean[st[i$var174]];
																double var187 = pageFaultsVar[st[i$var174]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var50)) {
														for(int index$sample57$52 = 0; index$sample57$52 < noStates; index$sample57$52 += 1) {
															int distributionTempVariable$var56$54 = index$sample57$52;
															double cv$probabilitySample57Value53 = (cv$probabilitySample57Value44 * distribution$sample57[((index$i$51 - 1) / 1)][index$sample57$52]);
															if((index$i$51 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			double var186 = pageFaultsMean[st[i$var174]];
																			double var187 = pageFaultsVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					if(fixedFlag$sample57) {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((i$var50 == i$var174)) {
								for(int var111 = 0; var111 < noStates; var111 += 1) {
									if((var111 == st[i$var174])) {
										if(fixedFlag$sample39) {
											if((0 == i$var174)) {
												for(int var162 = 0; var162 < noStates; var162 += 1) {
													if((var162 == st[i$var174])) {
														{
															double var186 = pageFaultsMean[st[i$var174]];
															double var187 = pageFaultsVar[st[i$var174]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
										} else {
											if(true) {
												for(int index$sample39$68 = 0; index$sample39$68 < noStates; index$sample39$68 += 1) {
													int distributionTempVariable$var38$70 = index$sample39$68;
													double cv$probabilitySample39Value69 = (1.0 * distribution$sample39[index$sample39$68]);
													if((0 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	double var186 = pageFaultsMean[st[i$var174]];
																	double var187 = pageFaultsVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample39Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
					} else {
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if(true) {
								for(int index$sample57$61 = 0; index$sample57$61 < noStates; index$sample57$61 += 1) {
									int distributionTempVariable$var56$63 = index$sample57$61;
									double cv$probabilitySample57Value62 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$61]);
									if((i$var50 == i$var174)) {
										for(int var111 = 0; var111 < noStates; var111 += 1) {
											if((var111 == st[i$var174])) {
												if(fixedFlag$sample39) {
													if((0 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	double var186 = pageFaultsMean[st[i$var174]];
																	double var187 = pageFaultsVar[st[i$var174]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
												} else {
													if(true) {
														for(int index$sample39$73 = 0; index$sample39$73 < noStates; index$sample39$73 += 1) {
															int distributionTempVariable$var38$75 = index$sample39$73;
															double cv$probabilitySample39Value74 = (cv$probabilitySample57Value62 * distribution$sample39[index$sample39$73]);
															if((0 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			double var186 = pageFaultsMean[st[i$var174]];
																			double var187 = pageFaultsVar[st[i$var174]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample39Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var188[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample190[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var188[((i$var174 - 0) / 1)] = cv$rvAccumulator;
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
					int cv$sampleValue = st[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var37 = cv$sampleAccumulator;
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
			logProbability$var37 = cv$rvAccumulator;
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
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var50;
					{
						int cv$sampleValue = st[i$var50];
						if(fixedFlag$sample39) {
							if((0 == (i$var50 - 1))) {
								for(int var29 = 0; var29 < noStates; var29 += 1) {
									if((var29 == st[(i$var50 - 1)])) {
										{
											double[] var54 = m[st[(i$var50 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							if(true) {
								for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
									int distributionTempVariable$var38$6 = index$sample39$4;
									double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
									if((0 == (i$var50 - 1))) {
										for(int var29 = 0; var29 < noStates; var29 += 1) {
											if((var29 == st[(i$var50 - 1)])) {
												{
													double[] var54 = m[st[(i$var50 - 1)]];
													double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if((index$i$1 == (i$var50 - 1))) {
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)])) {
									{
										double[] var54 = m[st[(i$var50 - 1)]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample57) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var50 - 1))) {
									for(int var29 = 0; var29 < noStates; var29 += 1) {
										if((var29 == st[(i$var50 - 1)])) {
											{
												double[] var54 = m[st[(i$var50 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int index$i$12 = 1; index$i$12 < samples; index$i$12 += 1) {
								if(!(index$i$12 == index$i$1)) {
									for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
										int distributionTempVariable$var56$15 = index$sample57$13;
										double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((index$i$12 - 1) / 1)][index$sample57$13]);
										if((index$i$12 == (i$var50 - 1))) {
											for(int var29 = 0; var29 < noStates; var29 += 1) {
												if((var29 == st[(i$var50 - 1)])) {
													{
														double[] var54 = m[st[(i$var50 - 1)]];
														double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var55[((i$var50 - 1) / 1)] = cv$sampleAccumulator;
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
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((i$var50 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[((i$var50 - 1) / 1)] = cv$rvAccumulator;
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
			for(int var111 = 0; var111 < noStates; var111 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaultsMean[var111];
					{
						{
							double var98 = 814.0;
							double var99 = 335550.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var98) / Math.sqrt(var99))) - (0.5 * Math.log(var99))));
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
			logProbability$var100 = cv$sampleAccumulator;
			logProbability$var112 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample113 = fixedFlag$sample113;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var112;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var100 = cv$rvAccumulator;
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
			for(int var128 = 0; var128 < noStates; var128 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var117 = cv$sampleAccumulator;
			logProbability$var129 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample130 = fixedFlag$sample130;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var129;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var117 = cv$rvAccumulator;
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
			for(int var145 = 0; var145 < noStates; var145 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var134 = cv$sampleAccumulator;
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample147 = fixedFlag$sample147;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var146;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var134 = cv$rvAccumulator;
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
			for(int var162 = 0; var162 < noStates; var162 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var151 = cv$sampleAccumulator;
			logProbability$var163 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample164 = fixedFlag$sample164;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var163;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var151 = cv$rvAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample180() {
		if(!fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var174];
					{
						{
							double var176 = cpuMean[st[i$var174]];
							double var177 = cpuVar[st[i$var174]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var176) / Math.sqrt(var177))) - (0.5 * Math.log(var177))));
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
				logProbability$var178[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample180[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample180[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var178[((i$var174 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample185() {
		if(!fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var174];
					{
						{
							double var181 = memMean[st[i$var174]];
							double var182 = memVar[st[i$var174]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var181) / Math.sqrt(var182))) - (0.5 * Math.log(var182))));
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
				logProbability$var183[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample185[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample185[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var183[((i$var174 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var174];
					{
						{
							double var186 = pageFaultsMean[st[i$var174]];
							double var187 = pageFaultsVar[st[i$var174]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var186) / Math.sqrt(var187))) - (0.5 * Math.log(var187))));
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
				logProbability$var188[((i$var174 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample190[((i$var174 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var174 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var188[((i$var174 - 0) / 1)] = cv$rvAccumulator;
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
			for(int var29 = 0; var29 < noStates; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var34 = cv$sampleAccumulator;
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
			logProbability$var34 = cv$rvAccumulator;
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
				int cv$sampleValue = st[0];
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var37 = cv$sampleAccumulator;
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
			logProbability$var37 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var50;
				{
					int cv$sampleValue = st[i$var50];
					{
						{
							double[] var54 = m[st[(i$var50 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var55[((i$var50 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample57[((i$var50 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((i$var50 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[((i$var50 - 1) / 1)] = cv$rvAccumulator;
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
			for(int var75 = 0; var75 < noStates; var75 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpuMean[var75];
					{
						{
							double var63 = 16.0;
							double var62 = 8.6;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var63) / Math.sqrt(var62))) - (0.5 * Math.log(var62))));
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
			logProbability$var64 = cv$sampleAccumulator;
			logProbability$var76 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var76;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var64 = cv$rvAccumulator;
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
			for(int var93 = 0; var93 < noStates; var93 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = memMean[var93];
					{
						{
							double var80 = 94.0;
							double var81 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var80) / Math.sqrt(var81))) - (0.5 * Math.log(var81))));
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
			logProbability$var82 = cv$sampleAccumulator;
			logProbability$var94 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var94;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var82 = cv$rvAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample113(int var111) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = pageFaultsMean[var111];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var112 = cv$proposedValue;
					{
						{
							pageFaultsMean[var111] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var98;
				{
					cv$temp$0$var98 = 814.0;
				}
				double cv$temp$1$var99;
				{
					cv$temp$1$var99 = 335550.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var98) / Math.sqrt(cv$temp$1$var99))) - (0.5 * Math.log(cv$temp$1$var99))));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var186$8_1 = cv$currentValue;
									if((var111 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var162 = 0; var162 < noStates; var162 += 1) {
														if((var162 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var186;
																	{
																		double var186 = traceTempVariable$var186$8_1;
																		cv$temp$2$var186 = var186;
																	}
																	double cv$temp$3$var187;
																	{
																		double var187 = pageFaultsVar[st[i$var174]];
																		cv$temp$3$var187 = var187;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var162 = 0; var162 < noStates; var162 += 1) {
																if((var162 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var186;
																			{
																				double var186 = traceTempVariable$var186$8_1;
																				cv$temp$4$var186 = var186;
																			}
																			double cv$temp$5$var187;
																			{
																				double var187 = pageFaultsVar[st[i$var174]];
																				cv$temp$5$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var186;
																					{
																						double var186 = traceTempVariable$var186$8_1;
																						cv$temp$6$var186 = var186;
																					}
																					double cv$temp$7$var187;
																					{
																						double var187 = pageFaultsVar[st[i$var174]];
																						cv$temp$7$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var186$9_1 = cv$currentValue;
											if((var111 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var162 = 0; var162 < noStates; var162 += 1) {
																if((var162 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var186;
																			{
																				double var186 = traceTempVariable$var186$9_1;
																				cv$temp$8$var186 = var186;
																			}
																			double cv$temp$9$var187;
																			{
																				double var187 = pageFaultsVar[st[i$var174]];
																				cv$temp$9$var187 = var187;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var186;
																					{
																						double var186 = traceTempVariable$var186$9_1;
																						cv$temp$10$var186 = var186;
																					}
																					double cv$temp$11$var187;
																					{
																						double var187 = pageFaultsVar[st[i$var174]];
																						cv$temp$11$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var186;
																					{
																						double var186 = traceTempVariable$var186$9_1;
																						cv$temp$12$var186 = var186;
																					}
																					double cv$temp$13$var187;
																					{
																						double var187 = pageFaultsVar[st[i$var174]];
																						cv$temp$13$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var186;
																							{
																								double var186 = traceTempVariable$var186$9_1;
																								cv$temp$14$var186 = var186;
																							}
																							double cv$temp$15$var187;
																							{
																								double var187 = pageFaultsVar[st[i$var174]];
																								cv$temp$15$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var186$17_1 = cv$currentValue;
										if((var111 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var162 = 0; var162 < noStates; var162 += 1) {
																if((var162 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var186;
																			{
																				double var186 = traceTempVariable$var186$17_1;
																				cv$temp$16$var186 = var186;
																			}
																			double cv$temp$17$var187;
																			{
																				double var187 = pageFaultsVar[st[i$var174]];
																				cv$temp$17$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var186;
																					{
																						double var186 = traceTempVariable$var186$17_1;
																						cv$temp$18$var186 = var186;
																					}
																					double cv$temp$19$var187;
																					{
																						double var187 = pageFaultsVar[st[i$var174]];
																						cv$temp$19$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var162 = 0; var162 < noStates; var162 += 1) {
																if((var162 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var186;
																			{
																				double var186 = traceTempVariable$var186$17_1;
																				cv$temp$20$var186 = var186;
																			}
																			double cv$temp$21$var187;
																			{
																				double var187 = pageFaultsVar[st[i$var174]];
																				cv$temp$21$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var186$18_1 = cv$currentValue;
												if((var111 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var186;
																					{
																						double var186 = traceTempVariable$var186$18_1;
																						cv$temp$22$var186 = var186;
																					}
																					double cv$temp$23$var187;
																					{
																						double var187 = pageFaultsVar[st[i$var174]];
																						cv$temp$23$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var186;
																							{
																								double var186 = traceTempVariable$var186$18_1;
																								cv$temp$24$var186 = var186;
																							}
																							double cv$temp$25$var187;
																							{
																								double var187 = pageFaultsVar[st[i$var174]];
																								cv$temp$25$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))));
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
															if((i$var50 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var186;
																				{
																					double var186 = traceTempVariable$var186$18_1;
																					cv$temp$26$var186 = var186;
																				}
																				double cv$temp$27$var187;
																				{
																					double var187 = pageFaultsVar[st[i$var174]];
																					cv$temp$27$var187 = var187;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var186;
																							{
																								double var186 = traceTempVariable$var186$18_1;
																								cv$temp$28$var186 = var186;
																							}
																							double cv$temp$29$var187;
																							{
																								double var187 = pageFaultsVar[st[i$var174]];
																								cv$temp$29$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))));
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
			double var112 = cv$originalValue;
			{
				{
					pageFaultsMean[var111] = var112;
				}
			}
		}
	}

	private final void sample130(int var128) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = cpuVar[var128];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var129 = cv$proposedValue;
					{
						{
							cpuVar[var128] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var116;
				{
					cv$temp$0$var116 = 5.0;
				}
				double cv$temp$1$var115;
				{
					cv$temp$1$var115 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var116, cv$temp$1$var115));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var177$8_1 = cv$currentValue;
									if((var128 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var75 = 0; var75 < noStates; var75 += 1) {
														if((var75 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var176;
																	{
																		double var176 = cpuMean[st[i$var174]];
																		cv$temp$2$var176 = var176;
																	}
																	double cv$temp$3$var177;
																	{
																		double var177 = traceTempVariable$var177$8_1;
																		cv$temp$3$var177 = var177;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var176;
																			{
																				double var176 = cpuMean[st[i$var174]];
																				cv$temp$4$var176 = var176;
																			}
																			double cv$temp$5$var177;
																			{
																				double var177 = traceTempVariable$var177$8_1;
																				cv$temp$5$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var176;
																					{
																						double var176 = cpuMean[st[i$var174]];
																						cv$temp$6$var176 = var176;
																					}
																					double cv$temp$7$var177;
																					{
																						double var177 = traceTempVariable$var177$8_1;
																						cv$temp$7$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var177$9_1 = cv$currentValue;
											if((var128 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var176;
																			{
																				double var176 = cpuMean[st[i$var174]];
																				cv$temp$8$var176 = var176;
																			}
																			double cv$temp$9$var177;
																			{
																				double var177 = traceTempVariable$var177$9_1;
																				cv$temp$9$var177 = var177;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var176;
																					{
																						double var176 = cpuMean[st[i$var174]];
																						cv$temp$10$var176 = var176;
																					}
																					double cv$temp$11$var177;
																					{
																						double var177 = traceTempVariable$var177$9_1;
																						cv$temp$11$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var176;
																					{
																						double var176 = cpuMean[st[i$var174]];
																						cv$temp$12$var176 = var176;
																					}
																					double cv$temp$13$var177;
																					{
																						double var177 = traceTempVariable$var177$9_1;
																						cv$temp$13$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var75 = 0; var75 < noStates; var75 += 1) {
																				if((var75 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var176;
																							{
																								double var176 = cpuMean[st[i$var174]];
																								cv$temp$14$var176 = var176;
																							}
																							double cv$temp$15$var177;
																							{
																								double var177 = traceTempVariable$var177$9_1;
																								cv$temp$15$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var177$17_1 = cv$currentValue;
										if((var128 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var176;
																			{
																				double var176 = cpuMean[st[i$var174]];
																				cv$temp$16$var176 = var176;
																			}
																			double cv$temp$17$var177;
																			{
																				double var177 = traceTempVariable$var177$17_1;
																				cv$temp$17$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var176;
																					{
																						double var176 = cpuMean[st[i$var174]];
																						cv$temp$18$var176 = var176;
																					}
																					double cv$temp$19$var177;
																					{
																						double var177 = traceTempVariable$var177$17_1;
																						cv$temp$19$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var176;
																			{
																				double var176 = cpuMean[st[i$var174]];
																				cv$temp$20$var176 = var176;
																			}
																			double cv$temp$21$var177;
																			{
																				double var177 = traceTempVariable$var177$17_1;
																				cv$temp$21$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var177$18_1 = cv$currentValue;
												if((var128 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var176;
																					{
																						double var176 = cpuMean[st[i$var174]];
																						cv$temp$22$var176 = var176;
																					}
																					double cv$temp$23$var177;
																					{
																						double var177 = traceTempVariable$var177$18_1;
																						cv$temp$23$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var75 = 0; var75 < noStates; var75 += 1) {
																				if((var75 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var176;
																							{
																								double var176 = cpuMean[st[i$var174]];
																								cv$temp$24$var176 = var176;
																							}
																							double cv$temp$25$var177;
																							{
																								double var177 = traceTempVariable$var177$18_1;
																								cv$temp$25$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))));
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
															if((i$var50 == i$var174)) {
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var176;
																				{
																					double var176 = cpuMean[st[i$var174]];
																					cv$temp$26$var176 = var176;
																				}
																				double cv$temp$27$var177;
																				{
																					double var177 = traceTempVariable$var177$18_1;
																					cv$temp$27$var177 = var177;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var75 = 0; var75 < noStates; var75 += 1) {
																				if((var75 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var176;
																							{
																								double var176 = cpuMean[st[i$var174]];
																								cv$temp$28$var176 = var176;
																							}
																							double cv$temp$29$var177;
																							{
																								double var177 = traceTempVariable$var177$18_1;
																								cv$temp$29$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))));
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
			double var129 = cv$originalValue;
			{
				{
					cpuVar[var128] = var129;
				}
			}
		}
	}

	private final void sample147(int var145) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = memVar[var145];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var146 = cv$proposedValue;
					{
						{
							memVar[var145] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var133;
				{
					cv$temp$0$var133 = 5.0;
				}
				double cv$temp$1$var132;
				{
					cv$temp$1$var132 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var133, cv$temp$1$var132));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var182$8_1 = cv$currentValue;
									if((var145 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var93 = 0; var93 < noStates; var93 += 1) {
														if((var93 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var181;
																	{
																		double var181 = memMean[st[i$var174]];
																		cv$temp$2$var181 = var181;
																	}
																	double cv$temp$3$var182;
																	{
																		double var182 = traceTempVariable$var182$8_1;
																		cv$temp$3$var182 = var182;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var181;
																			{
																				double var181 = memMean[st[i$var174]];
																				cv$temp$4$var181 = var181;
																			}
																			double cv$temp$5$var182;
																			{
																				double var182 = traceTempVariable$var182$8_1;
																				cv$temp$5$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var181;
																					{
																						double var181 = memMean[st[i$var174]];
																						cv$temp$6$var181 = var181;
																					}
																					double cv$temp$7$var182;
																					{
																						double var182 = traceTempVariable$var182$8_1;
																						cv$temp$7$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var182$9_1 = cv$currentValue;
											if((var145 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var181;
																			{
																				double var181 = memMean[st[i$var174]];
																				cv$temp$8$var181 = var181;
																			}
																			double cv$temp$9$var182;
																			{
																				double var182 = traceTempVariable$var182$9_1;
																				cv$temp$9$var182 = var182;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var181;
																					{
																						double var181 = memMean[st[i$var174]];
																						cv$temp$10$var181 = var181;
																					}
																					double cv$temp$11$var182;
																					{
																						double var182 = traceTempVariable$var182$9_1;
																						cv$temp$11$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var181;
																					{
																						double var181 = memMean[st[i$var174]];
																						cv$temp$12$var181 = var181;
																					}
																					double cv$temp$13$var182;
																					{
																						double var182 = traceTempVariable$var182$9_1;
																						cv$temp$13$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var93 = 0; var93 < noStates; var93 += 1) {
																				if((var93 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var181;
																							{
																								double var181 = memMean[st[i$var174]];
																								cv$temp$14$var181 = var181;
																							}
																							double cv$temp$15$var182;
																							{
																								double var182 = traceTempVariable$var182$9_1;
																								cv$temp$15$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var182$17_1 = cv$currentValue;
										if((var145 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var181;
																			{
																				double var181 = memMean[st[i$var174]];
																				cv$temp$16$var181 = var181;
																			}
																			double cv$temp$17$var182;
																			{
																				double var182 = traceTempVariable$var182$17_1;
																				cv$temp$17$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var181;
																					{
																						double var181 = memMean[st[i$var174]];
																						cv$temp$18$var181 = var181;
																					}
																					double cv$temp$19$var182;
																					{
																						double var182 = traceTempVariable$var182$17_1;
																						cv$temp$19$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var181;
																			{
																				double var181 = memMean[st[i$var174]];
																				cv$temp$20$var181 = var181;
																			}
																			double cv$temp$21$var182;
																			{
																				double var182 = traceTempVariable$var182$17_1;
																				cv$temp$21$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var182$18_1 = cv$currentValue;
												if((var145 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var181;
																					{
																						double var181 = memMean[st[i$var174]];
																						cv$temp$22$var181 = var181;
																					}
																					double cv$temp$23$var182;
																					{
																						double var182 = traceTempVariable$var182$18_1;
																						cv$temp$23$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var93 = 0; var93 < noStates; var93 += 1) {
																				if((var93 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var181;
																							{
																								double var181 = memMean[st[i$var174]];
																								cv$temp$24$var181 = var181;
																							}
																							double cv$temp$25$var182;
																							{
																								double var182 = traceTempVariable$var182$18_1;
																								cv$temp$25$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))));
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
															if((i$var50 == i$var174)) {
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var181;
																				{
																					double var181 = memMean[st[i$var174]];
																					cv$temp$26$var181 = var181;
																				}
																				double cv$temp$27$var182;
																				{
																					double var182 = traceTempVariable$var182$18_1;
																					cv$temp$27$var182 = var182;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var93 = 0; var93 < noStates; var93 += 1) {
																				if((var93 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var181;
																							{
																								double var181 = memMean[st[i$var174]];
																								cv$temp$28$var181 = var181;
																							}
																							double cv$temp$29$var182;
																							{
																								double var182 = traceTempVariable$var182$18_1;
																								cv$temp$29$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))));
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
			double var146 = cv$originalValue;
			{
				{
					memVar[var145] = var146;
				}
			}
		}
	}

	private final void sample164(int var162) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = pageFaultsVar[var162];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var163 = cv$proposedValue;
					{
						{
							pageFaultsVar[var162] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var150;
				{
					cv$temp$0$var150 = 5.0;
				}
				double cv$temp$1$var149;
				{
					cv$temp$1$var149 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var150, cv$temp$1$var149));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var187$8_1 = cv$currentValue;
									if((var162 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var111 = 0; var111 < noStates; var111 += 1) {
														if((var111 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var186;
																	{
																		double var186 = pageFaultsMean[st[i$var174]];
																		cv$temp$2$var186 = var186;
																	}
																	double cv$temp$3$var187;
																	{
																		double var187 = traceTempVariable$var187$8_1;
																		cv$temp$3$var187 = var187;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$2$var186) / Math.sqrt(cv$temp$3$var187))) - (0.5 * Math.log(cv$temp$3$var187)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var186;
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				cv$temp$4$var186 = var186;
																			}
																			double cv$temp$5$var187;
																			{
																				double var187 = traceTempVariable$var187$8_1;
																				cv$temp$5$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$4$var186) / Math.sqrt(cv$temp$5$var187))) - (0.5 * Math.log(cv$temp$5$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var186;
																					{
																						double var186 = pageFaultsMean[st[i$var174]];
																						cv$temp$6$var186 = var186;
																					}
																					double cv$temp$7$var187;
																					{
																						double var187 = traceTempVariable$var187$8_1;
																						cv$temp$7$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$6$var186) / Math.sqrt(cv$temp$7$var187))) - (0.5 * Math.log(cv$temp$7$var187)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var187$9_1 = cv$currentValue;
											if((var162 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var186;
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				cv$temp$8$var186 = var186;
																			}
																			double cv$temp$9$var187;
																			{
																				double var187 = traceTempVariable$var187$9_1;
																				cv$temp$9$var187 = var187;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$8$var186) / Math.sqrt(cv$temp$9$var187))) - (0.5 * Math.log(cv$temp$9$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var186;
																					{
																						double var186 = pageFaultsMean[st[i$var174]];
																						cv$temp$10$var186 = var186;
																					}
																					double cv$temp$11$var187;
																					{
																						double var187 = traceTempVariable$var187$9_1;
																						cv$temp$11$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$10$var186) / Math.sqrt(cv$temp$11$var187))) - (0.5 * Math.log(cv$temp$11$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var186;
																					{
																						double var186 = pageFaultsMean[st[i$var174]];
																						cv$temp$12$var186 = var186;
																					}
																					double cv$temp$13$var187;
																					{
																						double var187 = traceTempVariable$var187$9_1;
																						cv$temp$13$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$12$var186) / Math.sqrt(cv$temp$13$var187))) - (0.5 * Math.log(cv$temp$13$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var111 = 0; var111 < noStates; var111 += 1) {
																				if((var111 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var186;
																							{
																								double var186 = pageFaultsMean[st[i$var174]];
																								cv$temp$14$var186 = var186;
																							}
																							double cv$temp$15$var187;
																							{
																								double var187 = traceTempVariable$var187$9_1;
																								cv$temp$15$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$14$var186) / Math.sqrt(cv$temp$15$var187))) - (0.5 * Math.log(cv$temp$15$var187)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var187$17_1 = cv$currentValue;
										if((var162 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var186;
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				cv$temp$16$var186 = var186;
																			}
																			double cv$temp$17$var187;
																			{
																				double var187 = traceTempVariable$var187$17_1;
																				cv$temp$17$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$16$var186) / Math.sqrt(cv$temp$17$var187))) - (0.5 * Math.log(cv$temp$17$var187)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var186;
																					{
																						double var186 = pageFaultsMean[st[i$var174]];
																						cv$temp$18$var186 = var186;
																					}
																					double cv$temp$19$var187;
																					{
																						double var187 = traceTempVariable$var187$17_1;
																						cv$temp$19$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$18$var186) / Math.sqrt(cv$temp$19$var187))) - (0.5 * Math.log(cv$temp$19$var187)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var186;
																			{
																				double var186 = pageFaultsMean[st[i$var174]];
																				cv$temp$20$var186 = var186;
																			}
																			double cv$temp$21$var187;
																			{
																				double var187 = traceTempVariable$var187$17_1;
																				cv$temp$21$var187 = var187;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$20$var186) / Math.sqrt(cv$temp$21$var187))) - (0.5 * Math.log(cv$temp$21$var187)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var187$18_1 = cv$currentValue;
												if((var162 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var186;
																					{
																						double var186 = pageFaultsMean[st[i$var174]];
																						cv$temp$22$var186 = var186;
																					}
																					double cv$temp$23$var187;
																					{
																						double var187 = traceTempVariable$var187$18_1;
																						cv$temp$23$var187 = var187;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$22$var186) / Math.sqrt(cv$temp$23$var187))) - (0.5 * Math.log(cv$temp$23$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var111 = 0; var111 < noStates; var111 += 1) {
																				if((var111 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var186;
																							{
																								double var186 = pageFaultsMean[st[i$var174]];
																								cv$temp$24$var186 = var186;
																							}
																							double cv$temp$25$var187;
																							{
																								double var187 = traceTempVariable$var187$18_1;
																								cv$temp$25$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$24$var186) / Math.sqrt(cv$temp$25$var187))) - (0.5 * Math.log(cv$temp$25$var187)))));
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
															if((i$var50 == i$var174)) {
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var186;
																				{
																					double var186 = pageFaultsMean[st[i$var174]];
																					cv$temp$26$var186 = var186;
																				}
																				double cv$temp$27$var187;
																				{
																					double var187 = traceTempVariable$var187$18_1;
																					cv$temp$27$var187 = var187;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$26$var186) / Math.sqrt(cv$temp$27$var187))) - (0.5 * Math.log(cv$temp$27$var187)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var111 = 0; var111 < noStates; var111 += 1) {
																				if((var111 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var186;
																							{
																								double var186 = pageFaultsMean[st[i$var174]];
																								cv$temp$28$var186 = var186;
																							}
																							double cv$temp$29$var187;
																							{
																								double var187 = traceTempVariable$var187$18_1;
																								cv$temp$29$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$28$var186) / Math.sqrt(cv$temp$29$var187))) - (0.5 * Math.log(cv$temp$29$var187)))));
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
			double var163 = cv$originalValue;
			{
				{
					pageFaultsVar[var162] = var163;
				}
			}
		}
	}

	private final void sample30(int var29) {
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
							if((0 == (i$var50 - 1))) {
								if((var29 == st[(i$var50 - 1)])) {
									if(fixedFlag$sample57) {
										{
											int index$i$19 = i$var50;
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
						} else {
							if(true) {
								for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
									int distributionTempVariable$var38$5 = index$sample39$3;
									double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
									if((0 == (i$var50 - 1))) {
										if((var29 == st[(i$var50 - 1)])) {
											if(fixedFlag$sample57) {
												{
													int index$i$21 = i$var50;
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
					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
						if(fixedFlag$sample57) {
							for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
								if((index$i$10_1 == (i$var50 - 1))) {
									if((var29 == st[(i$var50 - 1)])) {
										if(fixedFlag$sample57) {
											{
												int index$i$23 = i$var50;
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
						} else {
							for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
								if(true) {
									for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
										int distributionTempVariable$var56$14 = index$sample57$12;
										double cv$probabilitySample57Value13 = (1.0 * distribution$sample57[((index$i$11 - 1) / 1)][index$sample57$12]);
										if((index$i$11 == (i$var50 - 1))) {
											if((var29 == st[(i$var50 - 1)])) {
												if(fixedFlag$sample57) {
													{
														int index$i$25 = i$var50;
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
		{
			{
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					if(fixedFlag$sample39) {
						if((0 == (i$var50 - 1))) {
							if((var29 == st[(i$var50 - 1)])) {
								if(!fixedFlag$sample57) {
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
					} else {
						if(true) {
							for(int index$sample39$32 = 0; index$sample39$32 < noStates; index$sample39$32 += 1) {
								int distributionTempVariable$var38$34 = index$sample39$32;
								double cv$probabilitySample39Value33 = (1.0 * distribution$sample39[index$sample39$32]);
								if((0 == (i$var50 - 1))) {
									if((var29 == st[(i$var50 - 1)])) {
										if(!fixedFlag$sample57) {
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
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					if(fixedFlag$sample57) {
						for(int index$i$39_1 = 1; index$i$39_1 < samples; index$i$39_1 += 1) {
							if((index$i$39_1 == (i$var50 - 1))) {
								if((var29 == st[(i$var50 - 1)])) {
									if(!fixedFlag$sample57) {
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
					} else {
						for(int index$i$40 = 1; index$i$40 < samples; index$i$40 += 1) {
							if(true) {
								for(int index$sample57$41 = 0; index$sample57$41 < noStates; index$sample57$41 += 1) {
									int distributionTempVariable$var56$43 = index$sample57$41;
									double cv$probabilitySample57Value42 = (1.0 * distribution$sample57[((index$i$40 - 1) / 1)][index$sample57$41]);
									if((index$i$40 == (i$var50 - 1))) {
										if((var29 == st[(i$var50 - 1)])) {
											if(!fixedFlag$sample57) {
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
	}

	private final void sample36() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var35$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if(fixedFlag$sample39) {
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
		{
			{
				if(!fixedFlag$sample39) {
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
	}

	private final void sample39() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var38$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$initialStateDistribution;
				{
					cv$temp$0$initialStateDistribution = initialStateDistribution;
				}
				int cv$temp$1$$var455;
				{
					int $var455 = noStates;
					cv$temp$1$$var455 = $var455;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var455))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var53$1_1 = cv$currentValue;
						for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
							if((0 == (i$var50 - 1))) {
								if(fixedFlag$sample57) {
									{
										int index$i$3 = i$var50;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var29 = 0; var29 < noStates; var29 += 1) {
												if((var29 == st[(i$var50 - 1)])) {
													{
														{
															double[] cv$temp$2$var54;
															{
																double[] var54 = m[traceTempVariable$var53$1_1];
																cv$temp$2$var54 = var54;
															}
															int cv$temp$3$$var466;
															{
																int $var466 = noStates;
																cv$temp$3$$var466 = $var466;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var50]) && (st[i$var50] < cv$temp$3$$var466))?Math.log(cv$temp$2$var54[st[i$var50]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var50]) && (st[i$var50] < cv$temp$3$$var466))?Math.log(cv$temp$2$var54[st[i$var50]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var50]) && (st[i$var50] < cv$temp$3$$var466))?Math.log(cv$temp$2$var54[st[i$var50]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var50]) && (st[i$var50] < cv$temp$3$$var466))?Math.log(cv$temp$2$var54[st[i$var50]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var50]) && (st[i$var50] < cv$temp$3$$var466))?Math.log(cv$temp$2$var54[st[i$var50]]):Double.NEGATIVE_INFINITY)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
				{
					{
						boolean[] guard$sample39gaussian179 = guard$sample39gaussian179$global;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian179[((i$var174 - 0) / 1)] = false;
						}
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian179[((i$var174 - 0) / 1)] = false;
						}
						int traceTempVariable$s$8_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian179[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian179[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var75 = 0; var75 < noStates; var75 += 1) {
												if((var75 == st[i$var174])) {
													int traceTempVariable$s$13_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	{
																		double cv$temp$4$var176;
																		{
																			double var176 = cpuMean[traceTempVariable$s$13_1];
																			cv$temp$4$var176 = var176;
																		}
																		double cv$temp$5$var177;
																		{
																			double var177 = cpuVar[traceTempVariable$s$13_1];
																			cv$temp$5$var177 = var177;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample39$14 = 0; index$sample39$14 < noStates; index$sample39$14 += 1) {
															int distributionTempVariable$var38$16 = index$sample39$14;
															double cv$probabilitySample39Value15 = (1.0 * distribution$sample39[index$sample39$14]);
															int traceTempVariable$s$17_1 = cv$currentValue;
															if((0 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$6$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$17_1];
																					cv$temp$6$var176 = var176;
																				}
																				double cv$temp$7$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$17_1];
																					cv$temp$7$var177 = var177;
																				}
																				if(((Math.log(cv$probabilitySample39Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))));
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
											for(int var75 = 0; var75 < noStates; var75 += 1) {
												if((var75 == st[i$var174])) {
													if(fixedFlag$sample57) {
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$8$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$8_1];
																					cv$temp$8$var176 = var176;
																				}
																				double cv$temp$9$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$8_1];
																					cv$temp$9$var177 = var177;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	if((i$var50 == i$var174)) {
																		for(int var128 = 0; var128 < noStates; var128 += 1) {
																			if((var128 == st[i$var174])) {
																				{
																					{
																						double cv$temp$10$var176;
																						{
																							double var176 = cpuMean[traceTempVariable$s$8_1];
																							cv$temp$10$var176 = var176;
																						}
																						double cv$temp$11$var177;
																						{
																							double var177 = cpuVar[traceTempVariable$s$8_1];
																							cv$temp$11$var177 = var177;
																						}
																						if(((Math.log(cv$probabilitySample57Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))));
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
						int traceTempVariable$s$9_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian179[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian179[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$29_1 = cv$currentValue;
											if((0 == i$var174)) {
												for(int var75 = 0; var75 < noStates; var75 += 1) {
													if((var75 == st[i$var174])) {
														for(int var128 = 0; var128 < noStates; var128 += 1) {
															if((var128 == st[i$var174])) {
																{
																	{
																		double cv$temp$12$var176;
																		{
																			double var176 = cpuMean[traceTempVariable$s$29_1];
																			cv$temp$12$var176 = var176;
																		}
																		double cv$temp$13$var177;
																		{
																			double var177 = cpuVar[traceTempVariable$s$29_1];
																			cv$temp$13$var177 = var177;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
													int traceTempVariable$s$33_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var75 = 0; var75 < noStates; var75 += 1) {
															if((var75 == st[i$var174])) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$14$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$33_1];
																					cv$temp$14$var176 = var176;
																				}
																				double cv$temp$15$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$33_1];
																					cv$temp$15$var177 = var177;
																				}
																				if(((Math.log(cv$probabilitySample39Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))));
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
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var75 = 0; var75 < noStates; var75 += 1) {
															if((var75 == st[i$var174])) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$16$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$9_1];
																					cv$temp$16$var176 = var176;
																				}
																				double cv$temp$17$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$9_1];
																					cv$temp$17$var177 = var177;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))));
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
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$40 = 0; index$sample57$40 < noStates; index$sample57$40 += 1) {
															int distributionTempVariable$var56$42 = index$sample57$40;
															double cv$probabilitySample57Value41 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$40]);
															if((i$var50 == i$var174)) {
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		for(int var128 = 0; var128 < noStates; var128 += 1) {
																			if((var128 == st[i$var174])) {
																				{
																					{
																						double cv$temp$18$var176;
																						{
																							double var176 = cpuMean[traceTempVariable$s$9_1];
																							cv$temp$18$var176 = var176;
																						}
																						double cv$temp$19$var177;
																						{
																							double var177 = cpuVar[traceTempVariable$s$9_1];
																							cv$temp$19$var177 = var177;
																						}
																						if(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))));
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
				{
					{
						boolean[] guard$sample39gaussian184 = guard$sample39gaussian184$global;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian184[((i$var174 - 0) / 1)] = false;
						}
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian184[((i$var174 - 0) / 1)] = false;
						}
						int traceTempVariable$s$58_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian184[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian184[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var93 = 0; var93 < noStates; var93 += 1) {
												if((var93 == st[i$var174])) {
													int traceTempVariable$s$63_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	{
																		double cv$temp$20$var181;
																		{
																			double var181 = memMean[traceTempVariable$s$63_1];
																			cv$temp$20$var181 = var181;
																		}
																		double cv$temp$21$var182;
																		{
																			double var182 = memVar[traceTempVariable$s$63_1];
																			cv$temp$21$var182 = var182;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample39$64 = 0; index$sample39$64 < noStates; index$sample39$64 += 1) {
															int distributionTempVariable$var38$66 = index$sample39$64;
															double cv$probabilitySample39Value65 = (1.0 * distribution$sample39[index$sample39$64]);
															int traceTempVariable$s$67_1 = cv$currentValue;
															if((0 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$22$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$67_1];
																					cv$temp$22$var181 = var181;
																				}
																				double cv$temp$23$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$67_1];
																					cv$temp$23$var182 = var182;
																				}
																				if(((Math.log(cv$probabilitySample39Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))));
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
											for(int var93 = 0; var93 < noStates; var93 += 1) {
												if((var93 == st[i$var174])) {
													if(fixedFlag$sample57) {
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$24$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$58_1];
																					cv$temp$24$var181 = var181;
																				}
																				double cv$temp$25$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$58_1];
																					cv$temp$25$var182 = var182;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	if((i$var50 == i$var174)) {
																		for(int var145 = 0; var145 < noStates; var145 += 1) {
																			if((var145 == st[i$var174])) {
																				{
																					{
																						double cv$temp$26$var181;
																						{
																							double var181 = memMean[traceTempVariable$s$58_1];
																							cv$temp$26$var181 = var181;
																						}
																						double cv$temp$27$var182;
																						{
																							double var182 = memVar[traceTempVariable$s$58_1];
																							cv$temp$27$var182 = var182;
																						}
																						if(((Math.log(cv$probabilitySample57Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))));
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
						int traceTempVariable$s$59_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian184[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian184[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$79_1 = cv$currentValue;
											if((0 == i$var174)) {
												for(int var93 = 0; var93 < noStates; var93 += 1) {
													if((var93 == st[i$var174])) {
														for(int var145 = 0; var145 < noStates; var145 += 1) {
															if((var145 == st[i$var174])) {
																{
																	{
																		double cv$temp$28$var181;
																		{
																			double var181 = memMean[traceTempVariable$s$79_1];
																			cv$temp$28$var181 = var181;
																		}
																		double cv$temp$29$var182;
																		{
																			double var182 = memVar[traceTempVariable$s$79_1];
																			cv$temp$29$var182 = var182;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
													int traceTempVariable$s$83_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var93 = 0; var93 < noStates; var93 += 1) {
															if((var93 == st[i$var174])) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$30$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$83_1];
																					cv$temp$30$var181 = var181;
																				}
																				double cv$temp$31$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$83_1];
																					cv$temp$31$var182 = var182;
																				}
																				if(((Math.log(cv$probabilitySample39Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$30$var181) / Math.sqrt(cv$temp$31$var182))) - (0.5 * Math.log(cv$temp$31$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$30$var181) / Math.sqrt(cv$temp$31$var182))) - (0.5 * Math.log(cv$temp$31$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$30$var181) / Math.sqrt(cv$temp$31$var182))) - (0.5 * Math.log(cv$temp$31$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$30$var181) / Math.sqrt(cv$temp$31$var182))) - (0.5 * Math.log(cv$temp$31$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$30$var181) / Math.sqrt(cv$temp$31$var182))) - (0.5 * Math.log(cv$temp$31$var182)))));
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
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var93 = 0; var93 < noStates; var93 += 1) {
															if((var93 == st[i$var174])) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$32$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$59_1];
																					cv$temp$32$var181 = var181;
																				}
																				double cv$temp$33$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$59_1];
																					cv$temp$33$var182 = var182;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$32$var181) / Math.sqrt(cv$temp$33$var182))) - (0.5 * Math.log(cv$temp$33$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$32$var181) / Math.sqrt(cv$temp$33$var182))) - (0.5 * Math.log(cv$temp$33$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$32$var181) / Math.sqrt(cv$temp$33$var182))) - (0.5 * Math.log(cv$temp$33$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$32$var181) / Math.sqrt(cv$temp$33$var182))) - (0.5 * Math.log(cv$temp$33$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$32$var181) / Math.sqrt(cv$temp$33$var182))) - (0.5 * Math.log(cv$temp$33$var182)))));
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
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$90 = 0; index$sample57$90 < noStates; index$sample57$90 += 1) {
															int distributionTempVariable$var56$92 = index$sample57$90;
															double cv$probabilitySample57Value91 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$90]);
															if((i$var50 == i$var174)) {
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		for(int var145 = 0; var145 < noStates; var145 += 1) {
																			if((var145 == st[i$var174])) {
																				{
																					{
																						double cv$temp$34$var181;
																						{
																							double var181 = memMean[traceTempVariable$s$59_1];
																							cv$temp$34$var181 = var181;
																						}
																						double cv$temp$35$var182;
																						{
																							double var182 = memVar[traceTempVariable$s$59_1];
																							cv$temp$35$var182 = var182;
																						}
																						if(((Math.log(cv$probabilitySample57Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$34$var181) / Math.sqrt(cv$temp$35$var182))) - (0.5 * Math.log(cv$temp$35$var182)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$34$var181) / Math.sqrt(cv$temp$35$var182))) - (0.5 * Math.log(cv$temp$35$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$34$var181) / Math.sqrt(cv$temp$35$var182))) - (0.5 * Math.log(cv$temp$35$var182))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$34$var181) / Math.sqrt(cv$temp$35$var182))) - (0.5 * Math.log(cv$temp$35$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$34$var181) / Math.sqrt(cv$temp$35$var182))) - (0.5 * Math.log(cv$temp$35$var182)))));
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
				{
					{
						boolean[] guard$sample39gaussian189 = guard$sample39gaussian189$global;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian189[((i$var174 - 0) / 1)] = false;
						}
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174))
								guard$sample39gaussian189[((i$var174 - 0) / 1)] = false;
						}
						int traceTempVariable$s$108_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian189[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian189[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var111 = 0; var111 < noStates; var111 += 1) {
												if((var111 == st[i$var174])) {
													int traceTempVariable$s$113_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	{
																		double cv$temp$36$var186;
																		{
																			double var186 = pageFaultsMean[traceTempVariable$s$113_1];
																			cv$temp$36$var186 = var186;
																		}
																		double cv$temp$37$var187;
																		{
																			double var187 = pageFaultsVar[traceTempVariable$s$113_1];
																			cv$temp$37$var187 = var187;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$36$var186) / Math.sqrt(cv$temp$37$var187))) - (0.5 * Math.log(cv$temp$37$var187)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$36$var186) / Math.sqrt(cv$temp$37$var187))) - (0.5 * Math.log(cv$temp$37$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$36$var186) / Math.sqrt(cv$temp$37$var187))) - (0.5 * Math.log(cv$temp$37$var187))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$36$var186) / Math.sqrt(cv$temp$37$var187))) - (0.5 * Math.log(cv$temp$37$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$36$var186) / Math.sqrt(cv$temp$37$var187))) - (0.5 * Math.log(cv$temp$37$var187)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample39$114 = 0; index$sample39$114 < noStates; index$sample39$114 += 1) {
															int distributionTempVariable$var38$116 = index$sample39$114;
															double cv$probabilitySample39Value115 = (1.0 * distribution$sample39[index$sample39$114]);
															int traceTempVariable$s$117_1 = cv$currentValue;
															if((0 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$38$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$117_1];
																					cv$temp$38$var186 = var186;
																				}
																				double cv$temp$39$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$117_1];
																					cv$temp$39$var187 = var187;
																				}
																				if(((Math.log(cv$probabilitySample39Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$38$var186) / Math.sqrt(cv$temp$39$var187))) - (0.5 * Math.log(cv$temp$39$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$38$var186) / Math.sqrt(cv$temp$39$var187))) - (0.5 * Math.log(cv$temp$39$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$38$var186) / Math.sqrt(cv$temp$39$var187))) - (0.5 * Math.log(cv$temp$39$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$38$var186) / Math.sqrt(cv$temp$39$var187))) - (0.5 * Math.log(cv$temp$39$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$38$var186) / Math.sqrt(cv$temp$39$var187))) - (0.5 * Math.log(cv$temp$39$var187)))));
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
											for(int var111 = 0; var111 < noStates; var111 += 1) {
												if((var111 == st[i$var174])) {
													if(fixedFlag$sample57) {
														for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$40$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$108_1];
																					cv$temp$40$var186 = var186;
																				}
																				double cv$temp$41$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$108_1];
																					cv$temp$41$var187 = var187;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$40$var186) / Math.sqrt(cv$temp$41$var187))) - (0.5 * Math.log(cv$temp$41$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$40$var186) / Math.sqrt(cv$temp$41$var187))) - (0.5 * Math.log(cv$temp$41$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$40$var186) / Math.sqrt(cv$temp$41$var187))) - (0.5 * Math.log(cv$temp$41$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$40$var186) / Math.sqrt(cv$temp$41$var187))) - (0.5 * Math.log(cv$temp$41$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$40$var186) / Math.sqrt(cv$temp$41$var187))) - (0.5 * Math.log(cv$temp$41$var187)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	if((i$var50 == i$var174)) {
																		for(int var162 = 0; var162 < noStates; var162 += 1) {
																			if((var162 == st[i$var174])) {
																				{
																					{
																						double cv$temp$42$var186;
																						{
																							double var186 = pageFaultsMean[traceTempVariable$s$108_1];
																							cv$temp$42$var186 = var186;
																						}
																						double cv$temp$43$var187;
																						{
																							double var187 = pageFaultsVar[traceTempVariable$s$108_1];
																							cv$temp$43$var187 = var187;
																						}
																						if(((Math.log(cv$probabilitySample57Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$42$var186) / Math.sqrt(cv$temp$43$var187))) - (0.5 * Math.log(cv$temp$43$var187)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$42$var186) / Math.sqrt(cv$temp$43$var187))) - (0.5 * Math.log(cv$temp$43$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$42$var186) / Math.sqrt(cv$temp$43$var187))) - (0.5 * Math.log(cv$temp$43$var187))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$42$var186) / Math.sqrt(cv$temp$43$var187))) - (0.5 * Math.log(cv$temp$43$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$42$var186) / Math.sqrt(cv$temp$43$var187))) - (0.5 * Math.log(cv$temp$43$var187)))));
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
						int traceTempVariable$s$109_1 = cv$currentValue;
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if((0 == i$var174)) {
								if(!guard$sample39gaussian189[((i$var174 - 0) / 1)]) {
									guard$sample39gaussian189[((i$var174 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$129_1 = cv$currentValue;
											if((0 == i$var174)) {
												for(int var111 = 0; var111 < noStates; var111 += 1) {
													if((var111 == st[i$var174])) {
														for(int var162 = 0; var162 < noStates; var162 += 1) {
															if((var162 == st[i$var174])) {
																{
																	{
																		double cv$temp$44$var186;
																		{
																			double var186 = pageFaultsMean[traceTempVariable$s$129_1];
																			cv$temp$44$var186 = var186;
																		}
																		double cv$temp$45$var187;
																		{
																			double var187 = pageFaultsVar[traceTempVariable$s$129_1];
																			cv$temp$45$var187 = var187;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$44$var186) / Math.sqrt(cv$temp$45$var187))) - (0.5 * Math.log(cv$temp$45$var187)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$44$var186) / Math.sqrt(cv$temp$45$var187))) - (0.5 * Math.log(cv$temp$45$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$44$var186) / Math.sqrt(cv$temp$45$var187))) - (0.5 * Math.log(cv$temp$45$var187))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$44$var186) / Math.sqrt(cv$temp$45$var187))) - (0.5 * Math.log(cv$temp$45$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$44$var186) / Math.sqrt(cv$temp$45$var187))) - (0.5 * Math.log(cv$temp$45$var187)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
													int traceTempVariable$s$133_1 = cv$currentValue;
													if((0 == i$var174)) {
														for(int var111 = 0; var111 < noStates; var111 += 1) {
															if((var111 == st[i$var174])) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$46$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$133_1];
																					cv$temp$46$var186 = var186;
																				}
																				double cv$temp$47$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$133_1];
																					cv$temp$47$var187 = var187;
																				}
																				if(((Math.log(cv$probabilitySample39Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$46$var186) / Math.sqrt(cv$temp$47$var187))) - (0.5 * Math.log(cv$temp$47$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$46$var186) / Math.sqrt(cv$temp$47$var187))) - (0.5 * Math.log(cv$temp$47$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$46$var186) / Math.sqrt(cv$temp$47$var187))) - (0.5 * Math.log(cv$temp$47$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$46$var186) / Math.sqrt(cv$temp$47$var187))) - (0.5 * Math.log(cv$temp$47$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$46$var186) / Math.sqrt(cv$temp$47$var187))) - (0.5 * Math.log(cv$temp$47$var187)))));
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
											if(fixedFlag$sample57) {
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if((i$var50 == i$var174)) {
														for(int var111 = 0; var111 < noStates; var111 += 1) {
															if((var111 == st[i$var174])) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$48$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$109_1];
																					cv$temp$48$var186 = var186;
																				}
																				double cv$temp$49$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$109_1];
																					cv$temp$49$var187 = var187;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$48$var186) / Math.sqrt(cv$temp$49$var187))) - (0.5 * Math.log(cv$temp$49$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$48$var186) / Math.sqrt(cv$temp$49$var187))) - (0.5 * Math.log(cv$temp$49$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$48$var186) / Math.sqrt(cv$temp$49$var187))) - (0.5 * Math.log(cv$temp$49$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$48$var186) / Math.sqrt(cv$temp$49$var187))) - (0.5 * Math.log(cv$temp$49$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$48$var186) / Math.sqrt(cv$temp$49$var187))) - (0.5 * Math.log(cv$temp$49$var187)))));
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
												for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
													if(true) {
														for(int index$sample57$140 = 0; index$sample57$140 < noStates; index$sample57$140 += 1) {
															int distributionTempVariable$var56$142 = index$sample57$140;
															double cv$probabilitySample57Value141 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$140]);
															if((i$var50 == i$var174)) {
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		for(int var162 = 0; var162 < noStates; var162 += 1) {
																			if((var162 == st[i$var174])) {
																				{
																					{
																						double cv$temp$50$var186;
																						{
																							double var186 = pageFaultsMean[traceTempVariable$s$109_1];
																							cv$temp$50$var186 = var186;
																						}
																						double cv$temp$51$var187;
																						{
																							double var187 = pageFaultsVar[traceTempVariable$s$109_1];
																							cv$temp$51$var187 = var187;
																						}
																						if(((Math.log(cv$probabilitySample57Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$50$var186) / Math.sqrt(cv$temp$51$var187))) - (0.5 * Math.log(cv$temp$51$var187)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$50$var186) / Math.sqrt(cv$temp$51$var187))) - (0.5 * Math.log(cv$temp$51$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$50$var186) / Math.sqrt(cv$temp$51$var187))) - (0.5 * Math.log(cv$temp$51$var187))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$50$var186) / Math.sqrt(cv$temp$51$var187))) - (0.5 * Math.log(cv$temp$51$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$50$var186) / Math.sqrt(cv$temp$51$var187))) - (0.5 * Math.log(cv$temp$51$var187)))));
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
					int traceTempVariable$var53$156_1 = cv$currentValue;
					for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
						if((0 == (i$var50 - 1))) {
							if(!fixedFlag$sample57) {
								{
									int index$i$158 = i$var50;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var55;
									for(int cv$i = 0; cv$i < noStates; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var29 = 0; var29 < noStates; var29 += 1) {
										if((var29 == st[(i$var50 - 1)])) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$52$var54;
												{
													double[] var54 = m[traceTempVariable$var53$156_1];
													cv$temp$52$var54 = var54;
												}
												int cv$temp$53$$var729;
												{
													int $var729 = noStates;
													cv$temp$53$$var729 = $var729;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$52$var54, cv$temp$53$$var729);
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
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample39;
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
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample57(int i$var50) {
		int cv$numNumStates = 0;
		int index$i$1 = i$var50;
		if(fixedFlag$sample39) {
			if((0 == (i$var50 - 1))) {
				for(int var29 = 0; var29 < noStates; var29 += 1) {
					if((var29 == st[(i$var50 - 1)]))
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		} else {
			if(true) {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					int distributionTempVariable$var38$5 = index$sample39$3;
					double cv$probabilitySample39Value4 = (1.0 * distribution$sample39[index$sample39$3]);
					if((0 == (i$var50 - 1))) {
						for(int var29 = 0; var29 < noStates; var29 += 1) {
							if((var29 == st[(i$var50 - 1)]))
								cv$numNumStates = Math.max(cv$numNumStates, noStates);
						}
					}
				}
			}
		}
		if((index$i$1 == (i$var50 - 1))) {
			for(int var29 = 0; var29 < noStates; var29 += 1) {
				if((var29 == st[(i$var50 - 1)]))
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		}
		if(fixedFlag$sample57) {
			for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
				if((index$i$10_1 == (i$var50 - 1))) {
					for(int var29 = 0; var29 < noStates; var29 += 1) {
						if((var29 == st[(i$var50 - 1)]))
							cv$numNumStates = Math.max(cv$numNumStates, noStates);
					}
				}
			}
		} else {
			for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
				if(!(index$i$11 == index$i$1)) {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						int distributionTempVariable$var56$14 = index$sample57$12;
						double cv$probabilitySample57Value13 = (1.0 * distribution$sample57[((index$i$11 - 1) / 1)][index$sample57$12]);
						if((index$i$11 == (i$var50 - 1))) {
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)]))
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var56$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			int index$i$19 = i$var50;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample39) {
				if((0 == (i$var50 - 1))) {
					for(int var29 = 0; var29 < noStates; var29 += 1) {
						if((var29 == st[(i$var50 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var54;
							{
								double[] var54 = m[st[(i$var50 - 1)]];
								cv$temp$0$var54 = var54;
							}
							int cv$temp$1$$var788;
							{
								int $var788 = noStates;
								cv$temp$1$$var788 = $var788;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var788))?Math.log(cv$temp$0$var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var53$35_1 = cv$currentValue;
								}
							}
							{
								{
									boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
									}
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
									}
									int traceTempVariable$s$47_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var75 = 0; var75 < noStates; var75 += 1) {
															if((var75 == st[i$var174])) {
																if((0 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$8$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$47_1];
																						cv$temp$8$var176 = var176;
																					}
																					double cv$temp$9$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$47_1];
																						cv$temp$9$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var75 = 0; var75 < noStates; var75 += 1) {
															if((var75 == st[i$var174])) {
																int traceTempVariable$s$67_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$67_1];
																						cv$temp$10$var176 = var176;
																					}
																					double cv$temp$11$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$67_1];
																						cv$temp$11$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$68 = 1; index$i$68 < samples; index$i$68 += 1) {
																	if(!(index$i$68 == index$i$19)) {
																		for(int index$sample57$69 = 0; index$sample57$69 < noStates; index$sample57$69 += 1) {
																			int distributionTempVariable$var56$71 = index$sample57$69;
																			double cv$probabilitySample57Value70 = (1.0 * distribution$sample57[((index$i$68 - 1) / 1)][index$sample57$69]);
																			int traceTempVariable$s$72_1 = cv$currentValue;
																			if((index$i$68 == i$var174)) {
																				for(int var128 = 0; var128 < noStates; var128 += 1) {
																					if((var128 == st[i$var174])) {
																						{
																							{
																								double cv$temp$12$var176;
																								{
																									double var176 = cpuMean[traceTempVariable$s$72_1];
																									cv$temp$12$var176 = var176;
																								}
																								double cv$temp$13$var177;
																								{
																									double var177 = cpuVar[traceTempVariable$s$72_1];
																									cv$temp$13$var177 = var177;
																								}
																								if(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value70);
																							}
																						}
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
									int traceTempVariable$s$51_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$40$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$51_1];
																						cv$temp$40$var176 = var176;
																					}
																					double cv$temp$41$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$51_1];
																						cv$temp$41$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$40$var176) / Math.sqrt(cv$temp$41$var177))) - (0.5 * Math.log(cv$temp$41$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$40$var176) / Math.sqrt(cv$temp$41$var177))) - (0.5 * Math.log(cv$temp$41$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$40$var176) / Math.sqrt(cv$temp$41$var177))) - (0.5 * Math.log(cv$temp$41$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$40$var176) / Math.sqrt(cv$temp$41$var177))) - (0.5 * Math.log(cv$temp$41$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$40$var176) / Math.sqrt(cv$temp$41$var177))) - (0.5 * Math.log(cv$temp$41$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$131_1 = cv$currentValue;
														if((index$i$19 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$42$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$131_1];
																						cv$temp$42$var176 = var176;
																					}
																					double cv$temp$43$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$131_1];
																						cv$temp$43$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$42$var176) / Math.sqrt(cv$temp$43$var177))) - (0.5 * Math.log(cv$temp$43$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$42$var176) / Math.sqrt(cv$temp$43$var177))) - (0.5 * Math.log(cv$temp$43$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$42$var176) / Math.sqrt(cv$temp$43$var177))) - (0.5 * Math.log(cv$temp$43$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$42$var176) / Math.sqrt(cv$temp$43$var177))) - (0.5 * Math.log(cv$temp$43$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$42$var176) / Math.sqrt(cv$temp$43$var177))) - (0.5 * Math.log(cv$temp$43$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$132 = 1; index$i$132 < samples; index$i$132 += 1) {
															if(!(index$i$132 == index$i$19)) {
																for(int index$sample57$133 = 0; index$sample57$133 < noStates; index$sample57$133 += 1) {
																	int distributionTempVariable$var56$135 = index$sample57$133;
																	double cv$probabilitySample57Value134 = (1.0 * distribution$sample57[((index$i$132 - 1) / 1)][index$sample57$133]);
																	int traceTempVariable$s$136_1 = cv$currentValue;
																	if((index$i$132 == i$var174)) {
																		for(int var75 = 0; var75 < noStates; var75 += 1) {
																			if((var75 == st[i$var174])) {
																				for(int var128 = 0; var128 < noStates; var128 += 1) {
																					if((var128 == st[i$var174])) {
																						{
																							{
																								double cv$temp$44$var176;
																								{
																									double var176 = cpuMean[traceTempVariable$s$136_1];
																									cv$temp$44$var176 = var176;
																								}
																								double cv$temp$45$var177;
																								{
																									double var177 = cpuVar[traceTempVariable$s$136_1];
																									cv$temp$45$var177 = var177;
																								}
																								if(((Math.log(cv$probabilitySample57Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$44$var176) / Math.sqrt(cv$temp$45$var177))) - (0.5 * Math.log(cv$temp$45$var177)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$44$var176) / Math.sqrt(cv$temp$45$var177))) - (0.5 * Math.log(cv$temp$45$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$44$var176) / Math.sqrt(cv$temp$45$var177))) - (0.5 * Math.log(cv$temp$45$var177))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$44$var176) / Math.sqrt(cv$temp$45$var177))) - (0.5 * Math.log(cv$temp$45$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$44$var176) / Math.sqrt(cv$temp$45$var177))) - (0.5 * Math.log(cv$temp$45$var177)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value134);
																							}
																						}
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
							{
								{
									boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
									}
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
									}
									int traceTempVariable$s$241_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var93 = 0; var93 < noStates; var93 += 1) {
															if((var93 == st[i$var174])) {
																if((0 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$72$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$241_1];
																						cv$temp$72$var181 = var181;
																					}
																					double cv$temp$73$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$241_1];
																						cv$temp$73$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$72$var181) / Math.sqrt(cv$temp$73$var182))) - (0.5 * Math.log(cv$temp$73$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$72$var181) / Math.sqrt(cv$temp$73$var182))) - (0.5 * Math.log(cv$temp$73$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$72$var181) / Math.sqrt(cv$temp$73$var182))) - (0.5 * Math.log(cv$temp$73$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$72$var181) / Math.sqrt(cv$temp$73$var182))) - (0.5 * Math.log(cv$temp$73$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$72$var181) / Math.sqrt(cv$temp$73$var182))) - (0.5 * Math.log(cv$temp$73$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var93 = 0; var93 < noStates; var93 += 1) {
															if((var93 == st[i$var174])) {
																int traceTempVariable$s$261_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$74$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$261_1];
																						cv$temp$74$var181 = var181;
																					}
																					double cv$temp$75$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$261_1];
																						cv$temp$75$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$74$var181) / Math.sqrt(cv$temp$75$var182))) - (0.5 * Math.log(cv$temp$75$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$74$var181) / Math.sqrt(cv$temp$75$var182))) - (0.5 * Math.log(cv$temp$75$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$74$var181) / Math.sqrt(cv$temp$75$var182))) - (0.5 * Math.log(cv$temp$75$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$74$var181) / Math.sqrt(cv$temp$75$var182))) - (0.5 * Math.log(cv$temp$75$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$74$var181) / Math.sqrt(cv$temp$75$var182))) - (0.5 * Math.log(cv$temp$75$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$262 = 1; index$i$262 < samples; index$i$262 += 1) {
																	if(!(index$i$262 == index$i$19)) {
																		for(int index$sample57$263 = 0; index$sample57$263 < noStates; index$sample57$263 += 1) {
																			int distributionTempVariable$var56$265 = index$sample57$263;
																			double cv$probabilitySample57Value264 = (1.0 * distribution$sample57[((index$i$262 - 1) / 1)][index$sample57$263]);
																			int traceTempVariable$s$266_1 = cv$currentValue;
																			if((index$i$262 == i$var174)) {
																				for(int var145 = 0; var145 < noStates; var145 += 1) {
																					if((var145 == st[i$var174])) {
																						{
																							{
																								double cv$temp$76$var181;
																								{
																									double var181 = memMean[traceTempVariable$s$266_1];
																									cv$temp$76$var181 = var181;
																								}
																								double cv$temp$77$var182;
																								{
																									double var182 = memVar[traceTempVariable$s$266_1];
																									cv$temp$77$var182 = var182;
																								}
																								if(((Math.log(cv$probabilitySample57Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$76$var181) / Math.sqrt(cv$temp$77$var182))) - (0.5 * Math.log(cv$temp$77$var182)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$76$var181) / Math.sqrt(cv$temp$77$var182))) - (0.5 * Math.log(cv$temp$77$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$76$var181) / Math.sqrt(cv$temp$77$var182))) - (0.5 * Math.log(cv$temp$77$var182))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$76$var181) / Math.sqrt(cv$temp$77$var182))) - (0.5 * Math.log(cv$temp$77$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$76$var181) / Math.sqrt(cv$temp$77$var182))) - (0.5 * Math.log(cv$temp$77$var182)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value264);
																							}
																						}
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
									int traceTempVariable$s$245_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$104$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$245_1];
																						cv$temp$104$var181 = var181;
																					}
																					double cv$temp$105$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$245_1];
																						cv$temp$105$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$104$var181) / Math.sqrt(cv$temp$105$var182))) - (0.5 * Math.log(cv$temp$105$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$104$var181) / Math.sqrt(cv$temp$105$var182))) - (0.5 * Math.log(cv$temp$105$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$104$var181) / Math.sqrt(cv$temp$105$var182))) - (0.5 * Math.log(cv$temp$105$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$104$var181) / Math.sqrt(cv$temp$105$var182))) - (0.5 * Math.log(cv$temp$105$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$104$var181) / Math.sqrt(cv$temp$105$var182))) - (0.5 * Math.log(cv$temp$105$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$325_1 = cv$currentValue;
														if((index$i$19 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$106$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$325_1];
																						cv$temp$106$var181 = var181;
																					}
																					double cv$temp$107$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$325_1];
																						cv$temp$107$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$106$var181) / Math.sqrt(cv$temp$107$var182))) - (0.5 * Math.log(cv$temp$107$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$106$var181) / Math.sqrt(cv$temp$107$var182))) - (0.5 * Math.log(cv$temp$107$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$106$var181) / Math.sqrt(cv$temp$107$var182))) - (0.5 * Math.log(cv$temp$107$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$106$var181) / Math.sqrt(cv$temp$107$var182))) - (0.5 * Math.log(cv$temp$107$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$106$var181) / Math.sqrt(cv$temp$107$var182))) - (0.5 * Math.log(cv$temp$107$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$326 = 1; index$i$326 < samples; index$i$326 += 1) {
															if(!(index$i$326 == index$i$19)) {
																for(int index$sample57$327 = 0; index$sample57$327 < noStates; index$sample57$327 += 1) {
																	int distributionTempVariable$var56$329 = index$sample57$327;
																	double cv$probabilitySample57Value328 = (1.0 * distribution$sample57[((index$i$326 - 1) / 1)][index$sample57$327]);
																	int traceTempVariable$s$330_1 = cv$currentValue;
																	if((index$i$326 == i$var174)) {
																		for(int var93 = 0; var93 < noStates; var93 += 1) {
																			if((var93 == st[i$var174])) {
																				for(int var145 = 0; var145 < noStates; var145 += 1) {
																					if((var145 == st[i$var174])) {
																						{
																							{
																								double cv$temp$108$var181;
																								{
																									double var181 = memMean[traceTempVariable$s$330_1];
																									cv$temp$108$var181 = var181;
																								}
																								double cv$temp$109$var182;
																								{
																									double var182 = memVar[traceTempVariable$s$330_1];
																									cv$temp$109$var182 = var182;
																								}
																								if(((Math.log(cv$probabilitySample57Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$108$var181) / Math.sqrt(cv$temp$109$var182))) - (0.5 * Math.log(cv$temp$109$var182)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$108$var181) / Math.sqrt(cv$temp$109$var182))) - (0.5 * Math.log(cv$temp$109$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$108$var181) / Math.sqrt(cv$temp$109$var182))) - (0.5 * Math.log(cv$temp$109$var182))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$108$var181) / Math.sqrt(cv$temp$109$var182))) - (0.5 * Math.log(cv$temp$109$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$108$var181) / Math.sqrt(cv$temp$109$var182))) - (0.5 * Math.log(cv$temp$109$var182)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value328);
																							}
																						}
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
							{
								{
									boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
									}
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174))
											guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
									}
									int traceTempVariable$s$435_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var111 = 0; var111 < noStates; var111 += 1) {
															if((var111 == st[i$var174])) {
																if((0 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$136$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$435_1];
																						cv$temp$136$var186 = var186;
																					}
																					double cv$temp$137$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$435_1];
																						cv$temp$137$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$136$var186) / Math.sqrt(cv$temp$137$var187))) - (0.5 * Math.log(cv$temp$137$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$136$var186) / Math.sqrt(cv$temp$137$var187))) - (0.5 * Math.log(cv$temp$137$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$136$var186) / Math.sqrt(cv$temp$137$var187))) - (0.5 * Math.log(cv$temp$137$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$136$var186) / Math.sqrt(cv$temp$137$var187))) - (0.5 * Math.log(cv$temp$137$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$136$var186) / Math.sqrt(cv$temp$137$var187))) - (0.5 * Math.log(cv$temp$137$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var111 = 0; var111 < noStates; var111 += 1) {
															if((var111 == st[i$var174])) {
																int traceTempVariable$s$455_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$138$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$455_1];
																						cv$temp$138$var186 = var186;
																					}
																					double cv$temp$139$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$455_1];
																						cv$temp$139$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$138$var186) / Math.sqrt(cv$temp$139$var187))) - (0.5 * Math.log(cv$temp$139$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$138$var186) / Math.sqrt(cv$temp$139$var187))) - (0.5 * Math.log(cv$temp$139$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$138$var186) / Math.sqrt(cv$temp$139$var187))) - (0.5 * Math.log(cv$temp$139$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$138$var186) / Math.sqrt(cv$temp$139$var187))) - (0.5 * Math.log(cv$temp$139$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$138$var186) / Math.sqrt(cv$temp$139$var187))) - (0.5 * Math.log(cv$temp$139$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$456 = 1; index$i$456 < samples; index$i$456 += 1) {
																	if(!(index$i$456 == index$i$19)) {
																		for(int index$sample57$457 = 0; index$sample57$457 < noStates; index$sample57$457 += 1) {
																			int distributionTempVariable$var56$459 = index$sample57$457;
																			double cv$probabilitySample57Value458 = (1.0 * distribution$sample57[((index$i$456 - 1) / 1)][index$sample57$457]);
																			int traceTempVariable$s$460_1 = cv$currentValue;
																			if((index$i$456 == i$var174)) {
																				for(int var162 = 0; var162 < noStates; var162 += 1) {
																					if((var162 == st[i$var174])) {
																						{
																							{
																								double cv$temp$140$var186;
																								{
																									double var186 = pageFaultsMean[traceTempVariable$s$460_1];
																									cv$temp$140$var186 = var186;
																								}
																								double cv$temp$141$var187;
																								{
																									double var187 = pageFaultsVar[traceTempVariable$s$460_1];
																									cv$temp$141$var187 = var187;
																								}
																								if(((Math.log(cv$probabilitySample57Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$140$var186) / Math.sqrt(cv$temp$141$var187))) - (0.5 * Math.log(cv$temp$141$var187)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$140$var186) / Math.sqrt(cv$temp$141$var187))) - (0.5 * Math.log(cv$temp$141$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$140$var186) / Math.sqrt(cv$temp$141$var187))) - (0.5 * Math.log(cv$temp$141$var187))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$140$var186) / Math.sqrt(cv$temp$141$var187))) - (0.5 * Math.log(cv$temp$141$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$140$var186) / Math.sqrt(cv$temp$141$var187))) - (0.5 * Math.log(cv$temp$141$var187)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value458);
																							}
																						}
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
									int traceTempVariable$s$439_1 = cv$currentValue;
									for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
										if((i$var50 == i$var174)) {
											if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
												guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$168$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$439_1];
																						cv$temp$168$var186 = var186;
																					}
																					double cv$temp$169$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$439_1];
																						cv$temp$169$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$168$var186) / Math.sqrt(cv$temp$169$var187))) - (0.5 * Math.log(cv$temp$169$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$168$var186) / Math.sqrt(cv$temp$169$var187))) - (0.5 * Math.log(cv$temp$169$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$168$var186) / Math.sqrt(cv$temp$169$var187))) - (0.5 * Math.log(cv$temp$169$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$168$var186) / Math.sqrt(cv$temp$169$var187))) - (0.5 * Math.log(cv$temp$169$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$168$var186) / Math.sqrt(cv$temp$169$var187))) - (0.5 * Math.log(cv$temp$169$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$519_1 = cv$currentValue;
														if((index$i$19 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$170$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$519_1];
																						cv$temp$170$var186 = var186;
																					}
																					double cv$temp$171$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$519_1];
																						cv$temp$171$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$170$var186) / Math.sqrt(cv$temp$171$var187))) - (0.5 * Math.log(cv$temp$171$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$170$var186) / Math.sqrt(cv$temp$171$var187))) - (0.5 * Math.log(cv$temp$171$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$170$var186) / Math.sqrt(cv$temp$171$var187))) - (0.5 * Math.log(cv$temp$171$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$170$var186) / Math.sqrt(cv$temp$171$var187))) - (0.5 * Math.log(cv$temp$171$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$170$var186) / Math.sqrt(cv$temp$171$var187))) - (0.5 * Math.log(cv$temp$171$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$520 = 1; index$i$520 < samples; index$i$520 += 1) {
															if(!(index$i$520 == index$i$19)) {
																for(int index$sample57$521 = 0; index$sample57$521 < noStates; index$sample57$521 += 1) {
																	int distributionTempVariable$var56$523 = index$sample57$521;
																	double cv$probabilitySample57Value522 = (1.0 * distribution$sample57[((index$i$520 - 1) / 1)][index$sample57$521]);
																	int traceTempVariable$s$524_1 = cv$currentValue;
																	if((index$i$520 == i$var174)) {
																		for(int var111 = 0; var111 < noStates; var111 += 1) {
																			if((var111 == st[i$var174])) {
																				for(int var162 = 0; var162 < noStates; var162 += 1) {
																					if((var162 == st[i$var174])) {
																						{
																							{
																								double cv$temp$172$var186;
																								{
																									double var186 = pageFaultsMean[traceTempVariable$s$524_1];
																									cv$temp$172$var186 = var186;
																								}
																								double cv$temp$173$var187;
																								{
																									double var187 = pageFaultsVar[traceTempVariable$s$524_1];
																									cv$temp$173$var187 = var187;
																								}
																								if(((Math.log(cv$probabilitySample57Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$172$var186) / Math.sqrt(cv$temp$173$var187))) - (0.5 * Math.log(cv$temp$173$var187)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$172$var186) / Math.sqrt(cv$temp$173$var187))) - (0.5 * Math.log(cv$temp$173$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$172$var186) / Math.sqrt(cv$temp$173$var187))) - (0.5 * Math.log(cv$temp$173$var187))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$172$var186) / Math.sqrt(cv$temp$173$var187))) - (0.5 * Math.log(cv$temp$173$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$172$var186) / Math.sqrt(cv$temp$173$var187))) - (0.5 * Math.log(cv$temp$173$var187)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value522);
																							}
																						}
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
			} else {
				if(true) {
					for(int index$sample39$21 = 0; index$sample39$21 < noStates; index$sample39$21 += 1) {
						int distributionTempVariable$var38$23 = index$sample39$21;
						double cv$probabilitySample39Value22 = (1.0 * distribution$sample39[index$sample39$21]);
						if((0 == (i$var50 - 1))) {
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value22);
									double[] cv$temp$2$var54;
									{
										double[] var54 = m[st[(i$var50 - 1)]];
										cv$temp$2$var54 = var54;
									}
									int cv$temp$3$$var789;
									{
										int $var789 = noStates;
										cv$temp$3$$var789 = $var789;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var789))?Math.log(cv$temp$2$var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var53$36_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$48_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		if((0 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$48_1];
																								cv$temp$14$var176 = var176;
																							}
																							double cv$temp$15$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$48_1];
																								cv$temp$15$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$77 = 0; index$sample39$77 < noStates; index$sample39$77 += 1) {
																				int distributionTempVariable$var38$79 = index$sample39$77;
																				double cv$probabilitySample39Value78 = (1.0 * distribution$sample39[index$sample39$77]);
																				if((0 == i$var174)) {
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == st[i$var174])) {
																							{
																								{
																									double cv$temp$16$var176;
																									{
																										double var176 = cpuMean[traceTempVariable$s$48_1];
																										cv$temp$16$var176 = var176;
																									}
																									double cv$temp$17$var177;
																									{
																										double var177 = cpuVar[traceTempVariable$s$48_1];
																										cv$temp$17$var177 = var177;
																									}
																									if(((Math.log(cv$probabilitySample39Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value78);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		int traceTempVariable$s$84_1 = cv$currentValue;
																		if((index$i$19 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$18$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$84_1];
																								cv$temp$18$var176 = var176;
																							}
																							double cv$temp$19$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$84_1];
																								cv$temp$19$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$85 = 1; index$i$85 < samples; index$i$85 += 1) {
																			if(!(index$i$85 == index$i$19)) {
																				for(int index$sample57$86 = 0; index$sample57$86 < noStates; index$sample57$86 += 1) {
																					int distributionTempVariable$var56$88 = index$sample57$86;
																					double cv$probabilitySample57Value87 = (1.0 * distribution$sample57[((index$i$85 - 1) / 1)][index$sample57$86]);
																					int traceTempVariable$s$89_1 = cv$currentValue;
																					if((index$i$85 == i$var174)) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$20$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$89_1];
																											cv$temp$20$var176 = var176;
																										}
																										double cv$temp$21$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$89_1];
																											cv$temp$21$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample57Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value87);
																									}
																								}
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
											int traceTempVariable$s$52_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if((0 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$46$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$52_1];
																								cv$temp$46$var176 = var176;
																							}
																							double cv$temp$47$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$52_1];
																								cv$temp$47$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$46$var176) / Math.sqrt(cv$temp$47$var177))) - (0.5 * Math.log(cv$temp$47$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$46$var176) / Math.sqrt(cv$temp$47$var177))) - (0.5 * Math.log(cv$temp$47$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$46$var176) / Math.sqrt(cv$temp$47$var177))) - (0.5 * Math.log(cv$temp$47$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$46$var176) / Math.sqrt(cv$temp$47$var177))) - (0.5 * Math.log(cv$temp$47$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$46$var176) / Math.sqrt(cv$temp$47$var177))) - (0.5 * Math.log(cv$temp$47$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample39$142 = 0; index$sample39$142 < noStates; index$sample39$142 += 1) {
																		int distributionTempVariable$var38$144 = index$sample39$142;
																		double cv$probabilitySample39Value143 = (1.0 * distribution$sample39[index$sample39$142]);
																		if((0 == i$var174)) {
																			for(int var75 = 0; var75 < noStates; var75 += 1) {
																				if((var75 == st[i$var174])) {
																					for(int var128 = 0; var128 < noStates; var128 += 1) {
																						if((var128 == st[i$var174])) {
																							{
																								{
																									double cv$temp$48$var176;
																									{
																										double var176 = cpuMean[traceTempVariable$s$52_1];
																										cv$temp$48$var176 = var176;
																									}
																									double cv$temp$49$var177;
																									{
																										double var177 = cpuVar[traceTempVariable$s$52_1];
																										cv$temp$49$var177 = var177;
																									}
																									if(((Math.log(cv$probabilitySample39Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$48$var176) / Math.sqrt(cv$temp$49$var177))) - (0.5 * Math.log(cv$temp$49$var177)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$48$var176) / Math.sqrt(cv$temp$49$var177))) - (0.5 * Math.log(cv$temp$49$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$48$var176) / Math.sqrt(cv$temp$49$var177))) - (0.5 * Math.log(cv$temp$49$var177))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$48$var176) / Math.sqrt(cv$temp$49$var177))) - (0.5 * Math.log(cv$temp$49$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$48$var176) / Math.sqrt(cv$temp$49$var177))) - (0.5 * Math.log(cv$temp$49$var177)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value143);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$150_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$50$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$150_1];
																								cv$temp$50$var176 = var176;
																							}
																							double cv$temp$51$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$150_1];
																								cv$temp$51$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$50$var176) / Math.sqrt(cv$temp$51$var177))) - (0.5 * Math.log(cv$temp$51$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$50$var176) / Math.sqrt(cv$temp$51$var177))) - (0.5 * Math.log(cv$temp$51$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$50$var176) / Math.sqrt(cv$temp$51$var177))) - (0.5 * Math.log(cv$temp$51$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$50$var176) / Math.sqrt(cv$temp$51$var177))) - (0.5 * Math.log(cv$temp$51$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$50$var176) / Math.sqrt(cv$temp$51$var177))) - (0.5 * Math.log(cv$temp$51$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$151 = 1; index$i$151 < samples; index$i$151 += 1) {
																	if(!(index$i$151 == index$i$19)) {
																		for(int index$sample57$152 = 0; index$sample57$152 < noStates; index$sample57$152 += 1) {
																			int distributionTempVariable$var56$154 = index$sample57$152;
																			double cv$probabilitySample57Value153 = (1.0 * distribution$sample57[((index$i$151 - 1) / 1)][index$sample57$152]);
																			int traceTempVariable$s$155_1 = cv$currentValue;
																			if((index$i$151 == i$var174)) {
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == st[i$var174])) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$52$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$155_1];
																											cv$temp$52$var176 = var176;
																										}
																										double cv$temp$53$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$155_1];
																											cv$temp$53$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample57Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$52$var176) / Math.sqrt(cv$temp$53$var177))) - (0.5 * Math.log(cv$temp$53$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$52$var176) / Math.sqrt(cv$temp$53$var177))) - (0.5 * Math.log(cv$temp$53$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$52$var176) / Math.sqrt(cv$temp$53$var177))) - (0.5 * Math.log(cv$temp$53$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$52$var176) / Math.sqrt(cv$temp$53$var177))) - (0.5 * Math.log(cv$temp$53$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$52$var176) / Math.sqrt(cv$temp$53$var177))) - (0.5 * Math.log(cv$temp$53$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value153);
																									}
																								}
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
									{
										{
											boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$242_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		if((0 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$78$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$242_1];
																								cv$temp$78$var181 = var181;
																							}
																							double cv$temp$79$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$242_1];
																								cv$temp$79$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$78$var181) / Math.sqrt(cv$temp$79$var182))) - (0.5 * Math.log(cv$temp$79$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$78$var181) / Math.sqrt(cv$temp$79$var182))) - (0.5 * Math.log(cv$temp$79$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$78$var181) / Math.sqrt(cv$temp$79$var182))) - (0.5 * Math.log(cv$temp$79$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$78$var181) / Math.sqrt(cv$temp$79$var182))) - (0.5 * Math.log(cv$temp$79$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$78$var181) / Math.sqrt(cv$temp$79$var182))) - (0.5 * Math.log(cv$temp$79$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$271 = 0; index$sample39$271 < noStates; index$sample39$271 += 1) {
																				int distributionTempVariable$var38$273 = index$sample39$271;
																				double cv$probabilitySample39Value272 = (1.0 * distribution$sample39[index$sample39$271]);
																				if((0 == i$var174)) {
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == st[i$var174])) {
																							{
																								{
																									double cv$temp$80$var181;
																									{
																										double var181 = memMean[traceTempVariable$s$242_1];
																										cv$temp$80$var181 = var181;
																									}
																									double cv$temp$81$var182;
																									{
																										double var182 = memVar[traceTempVariable$s$242_1];
																										cv$temp$81$var182 = var182;
																									}
																									if(((Math.log(cv$probabilitySample39Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$80$var181) / Math.sqrt(cv$temp$81$var182))) - (0.5 * Math.log(cv$temp$81$var182)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$80$var181) / Math.sqrt(cv$temp$81$var182))) - (0.5 * Math.log(cv$temp$81$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$80$var181) / Math.sqrt(cv$temp$81$var182))) - (0.5 * Math.log(cv$temp$81$var182))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$80$var181) / Math.sqrt(cv$temp$81$var182))) - (0.5 * Math.log(cv$temp$81$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$80$var181) / Math.sqrt(cv$temp$81$var182))) - (0.5 * Math.log(cv$temp$81$var182)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value272);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		int traceTempVariable$s$278_1 = cv$currentValue;
																		if((index$i$19 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$82$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$278_1];
																								cv$temp$82$var181 = var181;
																							}
																							double cv$temp$83$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$278_1];
																								cv$temp$83$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$82$var181) / Math.sqrt(cv$temp$83$var182))) - (0.5 * Math.log(cv$temp$83$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$82$var181) / Math.sqrt(cv$temp$83$var182))) - (0.5 * Math.log(cv$temp$83$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$82$var181) / Math.sqrt(cv$temp$83$var182))) - (0.5 * Math.log(cv$temp$83$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$82$var181) / Math.sqrt(cv$temp$83$var182))) - (0.5 * Math.log(cv$temp$83$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$82$var181) / Math.sqrt(cv$temp$83$var182))) - (0.5 * Math.log(cv$temp$83$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$279 = 1; index$i$279 < samples; index$i$279 += 1) {
																			if(!(index$i$279 == index$i$19)) {
																				for(int index$sample57$280 = 0; index$sample57$280 < noStates; index$sample57$280 += 1) {
																					int distributionTempVariable$var56$282 = index$sample57$280;
																					double cv$probabilitySample57Value281 = (1.0 * distribution$sample57[((index$i$279 - 1) / 1)][index$sample57$280]);
																					int traceTempVariable$s$283_1 = cv$currentValue;
																					if((index$i$279 == i$var174)) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$84$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$283_1];
																											cv$temp$84$var181 = var181;
																										}
																										double cv$temp$85$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$283_1];
																											cv$temp$85$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample57Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$84$var181) / Math.sqrt(cv$temp$85$var182))) - (0.5 * Math.log(cv$temp$85$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$84$var181) / Math.sqrt(cv$temp$85$var182))) - (0.5 * Math.log(cv$temp$85$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$84$var181) / Math.sqrt(cv$temp$85$var182))) - (0.5 * Math.log(cv$temp$85$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$84$var181) / Math.sqrt(cv$temp$85$var182))) - (0.5 * Math.log(cv$temp$85$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$84$var181) / Math.sqrt(cv$temp$85$var182))) - (0.5 * Math.log(cv$temp$85$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value281);
																									}
																								}
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
											int traceTempVariable$s$246_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if((0 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$110$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$246_1];
																								cv$temp$110$var181 = var181;
																							}
																							double cv$temp$111$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$246_1];
																								cv$temp$111$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$110$var181) / Math.sqrt(cv$temp$111$var182))) - (0.5 * Math.log(cv$temp$111$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$110$var181) / Math.sqrt(cv$temp$111$var182))) - (0.5 * Math.log(cv$temp$111$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$110$var181) / Math.sqrt(cv$temp$111$var182))) - (0.5 * Math.log(cv$temp$111$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$110$var181) / Math.sqrt(cv$temp$111$var182))) - (0.5 * Math.log(cv$temp$111$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$110$var181) / Math.sqrt(cv$temp$111$var182))) - (0.5 * Math.log(cv$temp$111$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample39$336 = 0; index$sample39$336 < noStates; index$sample39$336 += 1) {
																		int distributionTempVariable$var38$338 = index$sample39$336;
																		double cv$probabilitySample39Value337 = (1.0 * distribution$sample39[index$sample39$336]);
																		if((0 == i$var174)) {
																			for(int var93 = 0; var93 < noStates; var93 += 1) {
																				if((var93 == st[i$var174])) {
																					for(int var145 = 0; var145 < noStates; var145 += 1) {
																						if((var145 == st[i$var174])) {
																							{
																								{
																									double cv$temp$112$var181;
																									{
																										double var181 = memMean[traceTempVariable$s$246_1];
																										cv$temp$112$var181 = var181;
																									}
																									double cv$temp$113$var182;
																									{
																										double var182 = memVar[traceTempVariable$s$246_1];
																										cv$temp$113$var182 = var182;
																									}
																									if(((Math.log(cv$probabilitySample39Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$112$var181) / Math.sqrt(cv$temp$113$var182))) - (0.5 * Math.log(cv$temp$113$var182)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$112$var181) / Math.sqrt(cv$temp$113$var182))) - (0.5 * Math.log(cv$temp$113$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$112$var181) / Math.sqrt(cv$temp$113$var182))) - (0.5 * Math.log(cv$temp$113$var182))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$112$var181) / Math.sqrt(cv$temp$113$var182))) - (0.5 * Math.log(cv$temp$113$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$112$var181) / Math.sqrt(cv$temp$113$var182))) - (0.5 * Math.log(cv$temp$113$var182)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value337);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$344_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$114$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$344_1];
																								cv$temp$114$var181 = var181;
																							}
																							double cv$temp$115$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$344_1];
																								cv$temp$115$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$114$var181) / Math.sqrt(cv$temp$115$var182))) - (0.5 * Math.log(cv$temp$115$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$114$var181) / Math.sqrt(cv$temp$115$var182))) - (0.5 * Math.log(cv$temp$115$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$114$var181) / Math.sqrt(cv$temp$115$var182))) - (0.5 * Math.log(cv$temp$115$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$114$var181) / Math.sqrt(cv$temp$115$var182))) - (0.5 * Math.log(cv$temp$115$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$114$var181) / Math.sqrt(cv$temp$115$var182))) - (0.5 * Math.log(cv$temp$115$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$345 = 1; index$i$345 < samples; index$i$345 += 1) {
																	if(!(index$i$345 == index$i$19)) {
																		for(int index$sample57$346 = 0; index$sample57$346 < noStates; index$sample57$346 += 1) {
																			int distributionTempVariable$var56$348 = index$sample57$346;
																			double cv$probabilitySample57Value347 = (1.0 * distribution$sample57[((index$i$345 - 1) / 1)][index$sample57$346]);
																			int traceTempVariable$s$349_1 = cv$currentValue;
																			if((index$i$345 == i$var174)) {
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == st[i$var174])) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$116$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$349_1];
																											cv$temp$116$var181 = var181;
																										}
																										double cv$temp$117$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$349_1];
																											cv$temp$117$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample57Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$116$var181) / Math.sqrt(cv$temp$117$var182))) - (0.5 * Math.log(cv$temp$117$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$116$var181) / Math.sqrt(cv$temp$117$var182))) - (0.5 * Math.log(cv$temp$117$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$116$var181) / Math.sqrt(cv$temp$117$var182))) - (0.5 * Math.log(cv$temp$117$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$116$var181) / Math.sqrt(cv$temp$117$var182))) - (0.5 * Math.log(cv$temp$117$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$116$var181) / Math.sqrt(cv$temp$117$var182))) - (0.5 * Math.log(cv$temp$117$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value347);
																									}
																								}
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
									{
										{
											boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$436_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		if((0 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$142$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$436_1];
																								cv$temp$142$var186 = var186;
																							}
																							double cv$temp$143$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$436_1];
																								cv$temp$143$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$142$var186) / Math.sqrt(cv$temp$143$var187))) - (0.5 * Math.log(cv$temp$143$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$142$var186) / Math.sqrt(cv$temp$143$var187))) - (0.5 * Math.log(cv$temp$143$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$142$var186) / Math.sqrt(cv$temp$143$var187))) - (0.5 * Math.log(cv$temp$143$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$142$var186) / Math.sqrt(cv$temp$143$var187))) - (0.5 * Math.log(cv$temp$143$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$142$var186) / Math.sqrt(cv$temp$143$var187))) - (0.5 * Math.log(cv$temp$143$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample39$465 = 0; index$sample39$465 < noStates; index$sample39$465 += 1) {
																				int distributionTempVariable$var38$467 = index$sample39$465;
																				double cv$probabilitySample39Value466 = (1.0 * distribution$sample39[index$sample39$465]);
																				if((0 == i$var174)) {
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == st[i$var174])) {
																							{
																								{
																									double cv$temp$144$var186;
																									{
																										double var186 = pageFaultsMean[traceTempVariable$s$436_1];
																										cv$temp$144$var186 = var186;
																									}
																									double cv$temp$145$var187;
																									{
																										double var187 = pageFaultsVar[traceTempVariable$s$436_1];
																										cv$temp$145$var187 = var187;
																									}
																									if(((Math.log(cv$probabilitySample39Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$144$var186) / Math.sqrt(cv$temp$145$var187))) - (0.5 * Math.log(cv$temp$145$var187)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$144$var186) / Math.sqrt(cv$temp$145$var187))) - (0.5 * Math.log(cv$temp$145$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$144$var186) / Math.sqrt(cv$temp$145$var187))) - (0.5 * Math.log(cv$temp$145$var187))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$144$var186) / Math.sqrt(cv$temp$145$var187))) - (0.5 * Math.log(cv$temp$145$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$144$var186) / Math.sqrt(cv$temp$145$var187))) - (0.5 * Math.log(cv$temp$145$var187)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value466);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		int traceTempVariable$s$472_1 = cv$currentValue;
																		if((index$i$19 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$146$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$472_1];
																								cv$temp$146$var186 = var186;
																							}
																							double cv$temp$147$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$472_1];
																								cv$temp$147$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$146$var186) / Math.sqrt(cv$temp$147$var187))) - (0.5 * Math.log(cv$temp$147$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$146$var186) / Math.sqrt(cv$temp$147$var187))) - (0.5 * Math.log(cv$temp$147$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$146$var186) / Math.sqrt(cv$temp$147$var187))) - (0.5 * Math.log(cv$temp$147$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$146$var186) / Math.sqrt(cv$temp$147$var187))) - (0.5 * Math.log(cv$temp$147$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$146$var186) / Math.sqrt(cv$temp$147$var187))) - (0.5 * Math.log(cv$temp$147$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$473 = 1; index$i$473 < samples; index$i$473 += 1) {
																			if(!(index$i$473 == index$i$19)) {
																				for(int index$sample57$474 = 0; index$sample57$474 < noStates; index$sample57$474 += 1) {
																					int distributionTempVariable$var56$476 = index$sample57$474;
																					double cv$probabilitySample57Value475 = (1.0 * distribution$sample57[((index$i$473 - 1) / 1)][index$sample57$474]);
																					int traceTempVariable$s$477_1 = cv$currentValue;
																					if((index$i$473 == i$var174)) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$148$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$477_1];
																											cv$temp$148$var186 = var186;
																										}
																										double cv$temp$149$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$477_1];
																											cv$temp$149$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample57Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$148$var186) / Math.sqrt(cv$temp$149$var187))) - (0.5 * Math.log(cv$temp$149$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$148$var186) / Math.sqrt(cv$temp$149$var187))) - (0.5 * Math.log(cv$temp$149$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$148$var186) / Math.sqrt(cv$temp$149$var187))) - (0.5 * Math.log(cv$temp$149$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$148$var186) / Math.sqrt(cv$temp$149$var187))) - (0.5 * Math.log(cv$temp$149$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$148$var186) / Math.sqrt(cv$temp$149$var187))) - (0.5 * Math.log(cv$temp$149$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value475);
																									}
																								}
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
											int traceTempVariable$s$440_1 = cv$currentValue;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if((0 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$174$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$440_1];
																								cv$temp$174$var186 = var186;
																							}
																							double cv$temp$175$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$440_1];
																								cv$temp$175$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$174$var186) / Math.sqrt(cv$temp$175$var187))) - (0.5 * Math.log(cv$temp$175$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$174$var186) / Math.sqrt(cv$temp$175$var187))) - (0.5 * Math.log(cv$temp$175$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$174$var186) / Math.sqrt(cv$temp$175$var187))) - (0.5 * Math.log(cv$temp$175$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$174$var186) / Math.sqrt(cv$temp$175$var187))) - (0.5 * Math.log(cv$temp$175$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$174$var186) / Math.sqrt(cv$temp$175$var187))) - (0.5 * Math.log(cv$temp$175$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample39$530 = 0; index$sample39$530 < noStates; index$sample39$530 += 1) {
																		int distributionTempVariable$var38$532 = index$sample39$530;
																		double cv$probabilitySample39Value531 = (1.0 * distribution$sample39[index$sample39$530]);
																		if((0 == i$var174)) {
																			for(int var111 = 0; var111 < noStates; var111 += 1) {
																				if((var111 == st[i$var174])) {
																					for(int var162 = 0; var162 < noStates; var162 += 1) {
																						if((var162 == st[i$var174])) {
																							{
																								{
																									double cv$temp$176$var186;
																									{
																										double var186 = pageFaultsMean[traceTempVariable$s$440_1];
																										cv$temp$176$var186 = var186;
																									}
																									double cv$temp$177$var187;
																									{
																										double var187 = pageFaultsVar[traceTempVariable$s$440_1];
																										cv$temp$177$var187 = var187;
																									}
																									if(((Math.log(cv$probabilitySample39Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$176$var186) / Math.sqrt(cv$temp$177$var187))) - (0.5 * Math.log(cv$temp$177$var187)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$176$var186) / Math.sqrt(cv$temp$177$var187))) - (0.5 * Math.log(cv$temp$177$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$176$var186) / Math.sqrt(cv$temp$177$var187))) - (0.5 * Math.log(cv$temp$177$var187))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$176$var186) / Math.sqrt(cv$temp$177$var187))) - (0.5 * Math.log(cv$temp$177$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$176$var186) / Math.sqrt(cv$temp$177$var187))) - (0.5 * Math.log(cv$temp$177$var187)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value531);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$538_1 = cv$currentValue;
																if((index$i$19 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$178$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$538_1];
																								cv$temp$178$var186 = var186;
																							}
																							double cv$temp$179$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$538_1];
																								cv$temp$179$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$178$var186) / Math.sqrt(cv$temp$179$var187))) - (0.5 * Math.log(cv$temp$179$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$178$var186) / Math.sqrt(cv$temp$179$var187))) - (0.5 * Math.log(cv$temp$179$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$178$var186) / Math.sqrt(cv$temp$179$var187))) - (0.5 * Math.log(cv$temp$179$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$178$var186) / Math.sqrt(cv$temp$179$var187))) - (0.5 * Math.log(cv$temp$179$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$178$var186) / Math.sqrt(cv$temp$179$var187))) - (0.5 * Math.log(cv$temp$179$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$539 = 1; index$i$539 < samples; index$i$539 += 1) {
																	if(!(index$i$539 == index$i$19)) {
																		for(int index$sample57$540 = 0; index$sample57$540 < noStates; index$sample57$540 += 1) {
																			int distributionTempVariable$var56$542 = index$sample57$540;
																			double cv$probabilitySample57Value541 = (1.0 * distribution$sample57[((index$i$539 - 1) / 1)][index$sample57$540]);
																			int traceTempVariable$s$543_1 = cv$currentValue;
																			if((index$i$539 == i$var174)) {
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == st[i$var174])) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$180$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$543_1];
																											cv$temp$180$var186 = var186;
																										}
																										double cv$temp$181$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$543_1];
																											cv$temp$181$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample57Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$180$var186) / Math.sqrt(cv$temp$181$var187))) - (0.5 * Math.log(cv$temp$181$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$180$var186) / Math.sqrt(cv$temp$181$var187))) - (0.5 * Math.log(cv$temp$181$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$180$var186) / Math.sqrt(cv$temp$181$var187))) - (0.5 * Math.log(cv$temp$181$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$180$var186) / Math.sqrt(cv$temp$181$var187))) - (0.5 * Math.log(cv$temp$181$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$180$var186) / Math.sqrt(cv$temp$181$var187))) - (0.5 * Math.log(cv$temp$181$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value541);
																									}
																								}
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
			int traceTempVariable$var53$27_1 = cv$currentValue;
			if((index$i$19 == (i$var50 - 1))) {
				for(int var29 = 0; var29 < noStates; var29 += 1) {
					if((var29 == st[(i$var50 - 1)])) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$4$var54;
						{
							double[] var54 = m[traceTempVariable$var53$27_1];
							cv$temp$4$var54 = var54;
						}
						int cv$temp$5$$var790;
						{
							int $var790 = noStates;
							cv$temp$5$$var790 = $var790;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var790))?Math.log(cv$temp$4$var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var53$37_1 = cv$currentValue;
							}
						}
						{
							{
								boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
								}
								int traceTempVariable$s$49_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var75 = 0; var75 < noStates; var75 += 1) {
														if((var75 == st[i$var174])) {
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$49_1];
																						cv$temp$22$var176 = var176;
																					}
																					double cv$temp$23$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$49_1];
																						cv$temp$23$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$94 = 0; index$sample39$94 < noStates; index$sample39$94 += 1) {
																		int distributionTempVariable$var38$96 = index$sample39$94;
																		double cv$probabilitySample39Value95 = (1.0 * distribution$sample39[index$sample39$94]);
																		if((0 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$49_1];
																								cv$temp$24$var176 = var176;
																							}
																							double cv$temp$25$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$49_1];
																								cv$temp$25$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample39Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value95);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var75 = 0; var75 < noStates; var75 += 1) {
														if((var75 == st[i$var174])) {
															int traceTempVariable$s$101_1 = cv$currentValue;
															if((index$i$19 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$101_1];
																					cv$temp$26$var176 = var176;
																				}
																				double cv$temp$27$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$101_1];
																					cv$temp$27$var177 = var177;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$102 = 1; index$i$102 < samples; index$i$102 += 1) {
																if(!(index$i$102 == index$i$19)) {
																	for(int index$sample57$103 = 0; index$sample57$103 < noStates; index$sample57$103 += 1) {
																		int distributionTempVariable$var56$105 = index$sample57$103;
																		double cv$probabilitySample57Value104 = (1.0 * distribution$sample57[((index$i$102 - 1) / 1)][index$sample57$103]);
																		int traceTempVariable$s$106_1 = cv$currentValue;
																		if((index$i$102 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$106_1];
																								cv$temp$28$var176 = var176;
																							}
																							double cv$temp$29$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$106_1];
																								cv$temp$29$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value104);
																						}
																					}
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
								int traceTempVariable$s$53_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var75 = 0; var75 < noStates; var75 += 1) {
																if((var75 == st[i$var174])) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$54$var176;
																					{
																						double var176 = cpuMean[traceTempVariable$s$53_1];
																						cv$temp$54$var176 = var176;
																					}
																					double cv$temp$55$var177;
																					{
																						double var177 = cpuVar[traceTempVariable$s$53_1];
																						cv$temp$55$var177 = var177;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$54$var176) / Math.sqrt(cv$temp$55$var177))) - (0.5 * Math.log(cv$temp$55$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$54$var176) / Math.sqrt(cv$temp$55$var177))) - (0.5 * Math.log(cv$temp$55$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$54$var176) / Math.sqrt(cv$temp$55$var177))) - (0.5 * Math.log(cv$temp$55$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$54$var176) / Math.sqrt(cv$temp$55$var177))) - (0.5 * Math.log(cv$temp$55$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$54$var176) / Math.sqrt(cv$temp$55$var177))) - (0.5 * Math.log(cv$temp$55$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$161 = 0; index$sample39$161 < noStates; index$sample39$161 += 1) {
																int distributionTempVariable$var38$163 = index$sample39$161;
																double cv$probabilitySample39Value162 = (1.0 * distribution$sample39[index$sample39$161]);
																if((0 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$56$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$53_1];
																								cv$temp$56$var176 = var176;
																							}
																							double cv$temp$57$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$53_1];
																								cv$temp$57$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample39Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$56$var176) / Math.sqrt(cv$temp$57$var177))) - (0.5 * Math.log(cv$temp$57$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$56$var176) / Math.sqrt(cv$temp$57$var177))) - (0.5 * Math.log(cv$temp$57$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$56$var176) / Math.sqrt(cv$temp$57$var177))) - (0.5 * Math.log(cv$temp$57$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$56$var176) / Math.sqrt(cv$temp$57$var177))) - (0.5 * Math.log(cv$temp$57$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$56$var176) / Math.sqrt(cv$temp$57$var177))) - (0.5 * Math.log(cv$temp$57$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value162);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$169_1 = cv$currentValue;
													if((index$i$19 == i$var174)) {
														for(int var75 = 0; var75 < noStates; var75 += 1) {
															if((var75 == st[i$var174])) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$58$var176;
																				{
																					double var176 = cpuMean[traceTempVariable$s$169_1];
																					cv$temp$58$var176 = var176;
																				}
																				double cv$temp$59$var177;
																				{
																					double var177 = cpuVar[traceTempVariable$s$169_1];
																					cv$temp$59$var177 = var177;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$58$var176) / Math.sqrt(cv$temp$59$var177))) - (0.5 * Math.log(cv$temp$59$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$58$var176) / Math.sqrt(cv$temp$59$var177))) - (0.5 * Math.log(cv$temp$59$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$58$var176) / Math.sqrt(cv$temp$59$var177))) - (0.5 * Math.log(cv$temp$59$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$58$var176) / Math.sqrt(cv$temp$59$var177))) - (0.5 * Math.log(cv$temp$59$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$58$var176) / Math.sqrt(cv$temp$59$var177))) - (0.5 * Math.log(cv$temp$59$var177)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$170 = 1; index$i$170 < samples; index$i$170 += 1) {
														if(!(index$i$170 == index$i$19)) {
															for(int index$sample57$171 = 0; index$sample57$171 < noStates; index$sample57$171 += 1) {
																int distributionTempVariable$var56$173 = index$sample57$171;
																double cv$probabilitySample57Value172 = (1.0 * distribution$sample57[((index$i$170 - 1) / 1)][index$sample57$171]);
																int traceTempVariable$s$174_1 = cv$currentValue;
																if((index$i$170 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$60$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$174_1];
																								cv$temp$60$var176 = var176;
																							}
																							double cv$temp$61$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$174_1];
																								cv$temp$61$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$60$var176) / Math.sqrt(cv$temp$61$var177))) - (0.5 * Math.log(cv$temp$61$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$60$var176) / Math.sqrt(cv$temp$61$var177))) - (0.5 * Math.log(cv$temp$61$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$60$var176) / Math.sqrt(cv$temp$61$var177))) - (0.5 * Math.log(cv$temp$61$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$60$var176) / Math.sqrt(cv$temp$61$var177))) - (0.5 * Math.log(cv$temp$61$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$60$var176) / Math.sqrt(cv$temp$61$var177))) - (0.5 * Math.log(cv$temp$61$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value172);
																						}
																					}
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
						{
							{
								boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
								}
								int traceTempVariable$s$243_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var93 = 0; var93 < noStates; var93 += 1) {
														if((var93 == st[i$var174])) {
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$86$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$243_1];
																						cv$temp$86$var181 = var181;
																					}
																					double cv$temp$87$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$243_1];
																						cv$temp$87$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$86$var181) / Math.sqrt(cv$temp$87$var182))) - (0.5 * Math.log(cv$temp$87$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$86$var181) / Math.sqrt(cv$temp$87$var182))) - (0.5 * Math.log(cv$temp$87$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$86$var181) / Math.sqrt(cv$temp$87$var182))) - (0.5 * Math.log(cv$temp$87$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$86$var181) / Math.sqrt(cv$temp$87$var182))) - (0.5 * Math.log(cv$temp$87$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$86$var181) / Math.sqrt(cv$temp$87$var182))) - (0.5 * Math.log(cv$temp$87$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$288 = 0; index$sample39$288 < noStates; index$sample39$288 += 1) {
																		int distributionTempVariable$var38$290 = index$sample39$288;
																		double cv$probabilitySample39Value289 = (1.0 * distribution$sample39[index$sample39$288]);
																		if((0 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$88$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$243_1];
																								cv$temp$88$var181 = var181;
																							}
																							double cv$temp$89$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$243_1];
																								cv$temp$89$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample39Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$88$var181) / Math.sqrt(cv$temp$89$var182))) - (0.5 * Math.log(cv$temp$89$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$88$var181) / Math.sqrt(cv$temp$89$var182))) - (0.5 * Math.log(cv$temp$89$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$88$var181) / Math.sqrt(cv$temp$89$var182))) - (0.5 * Math.log(cv$temp$89$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$88$var181) / Math.sqrt(cv$temp$89$var182))) - (0.5 * Math.log(cv$temp$89$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$88$var181) / Math.sqrt(cv$temp$89$var182))) - (0.5 * Math.log(cv$temp$89$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value289);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var93 = 0; var93 < noStates; var93 += 1) {
														if((var93 == st[i$var174])) {
															int traceTempVariable$s$295_1 = cv$currentValue;
															if((index$i$19 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$90$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$295_1];
																					cv$temp$90$var181 = var181;
																				}
																				double cv$temp$91$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$295_1];
																					cv$temp$91$var182 = var182;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$90$var181) / Math.sqrt(cv$temp$91$var182))) - (0.5 * Math.log(cv$temp$91$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$90$var181) / Math.sqrt(cv$temp$91$var182))) - (0.5 * Math.log(cv$temp$91$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$90$var181) / Math.sqrt(cv$temp$91$var182))) - (0.5 * Math.log(cv$temp$91$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$90$var181) / Math.sqrt(cv$temp$91$var182))) - (0.5 * Math.log(cv$temp$91$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$90$var181) / Math.sqrt(cv$temp$91$var182))) - (0.5 * Math.log(cv$temp$91$var182)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$296 = 1; index$i$296 < samples; index$i$296 += 1) {
																if(!(index$i$296 == index$i$19)) {
																	for(int index$sample57$297 = 0; index$sample57$297 < noStates; index$sample57$297 += 1) {
																		int distributionTempVariable$var56$299 = index$sample57$297;
																		double cv$probabilitySample57Value298 = (1.0 * distribution$sample57[((index$i$296 - 1) / 1)][index$sample57$297]);
																		int traceTempVariable$s$300_1 = cv$currentValue;
																		if((index$i$296 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$92$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$300_1];
																								cv$temp$92$var181 = var181;
																							}
																							double cv$temp$93$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$300_1];
																								cv$temp$93$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$92$var181) / Math.sqrt(cv$temp$93$var182))) - (0.5 * Math.log(cv$temp$93$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$92$var181) / Math.sqrt(cv$temp$93$var182))) - (0.5 * Math.log(cv$temp$93$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$92$var181) / Math.sqrt(cv$temp$93$var182))) - (0.5 * Math.log(cv$temp$93$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$92$var181) / Math.sqrt(cv$temp$93$var182))) - (0.5 * Math.log(cv$temp$93$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$92$var181) / Math.sqrt(cv$temp$93$var182))) - (0.5 * Math.log(cv$temp$93$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value298);
																						}
																					}
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
								int traceTempVariable$s$247_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var93 = 0; var93 < noStates; var93 += 1) {
																if((var93 == st[i$var174])) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$118$var181;
																					{
																						double var181 = memMean[traceTempVariable$s$247_1];
																						cv$temp$118$var181 = var181;
																					}
																					double cv$temp$119$var182;
																					{
																						double var182 = memVar[traceTempVariable$s$247_1];
																						cv$temp$119$var182 = var182;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$118$var181) / Math.sqrt(cv$temp$119$var182))) - (0.5 * Math.log(cv$temp$119$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$118$var181) / Math.sqrt(cv$temp$119$var182))) - (0.5 * Math.log(cv$temp$119$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$118$var181) / Math.sqrt(cv$temp$119$var182))) - (0.5 * Math.log(cv$temp$119$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$118$var181) / Math.sqrt(cv$temp$119$var182))) - (0.5 * Math.log(cv$temp$119$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$118$var181) / Math.sqrt(cv$temp$119$var182))) - (0.5 * Math.log(cv$temp$119$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$355 = 0; index$sample39$355 < noStates; index$sample39$355 += 1) {
																int distributionTempVariable$var38$357 = index$sample39$355;
																double cv$probabilitySample39Value356 = (1.0 * distribution$sample39[index$sample39$355]);
																if((0 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$120$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$247_1];
																								cv$temp$120$var181 = var181;
																							}
																							double cv$temp$121$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$247_1];
																								cv$temp$121$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample39Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$120$var181) / Math.sqrt(cv$temp$121$var182))) - (0.5 * Math.log(cv$temp$121$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$120$var181) / Math.sqrt(cv$temp$121$var182))) - (0.5 * Math.log(cv$temp$121$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$120$var181) / Math.sqrt(cv$temp$121$var182))) - (0.5 * Math.log(cv$temp$121$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$120$var181) / Math.sqrt(cv$temp$121$var182))) - (0.5 * Math.log(cv$temp$121$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$120$var181) / Math.sqrt(cv$temp$121$var182))) - (0.5 * Math.log(cv$temp$121$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value356);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$363_1 = cv$currentValue;
													if((index$i$19 == i$var174)) {
														for(int var93 = 0; var93 < noStates; var93 += 1) {
															if((var93 == st[i$var174])) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$122$var181;
																				{
																					double var181 = memMean[traceTempVariable$s$363_1];
																					cv$temp$122$var181 = var181;
																				}
																				double cv$temp$123$var182;
																				{
																					double var182 = memVar[traceTempVariable$s$363_1];
																					cv$temp$123$var182 = var182;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$122$var181) / Math.sqrt(cv$temp$123$var182))) - (0.5 * Math.log(cv$temp$123$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$122$var181) / Math.sqrt(cv$temp$123$var182))) - (0.5 * Math.log(cv$temp$123$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$122$var181) / Math.sqrt(cv$temp$123$var182))) - (0.5 * Math.log(cv$temp$123$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$122$var181) / Math.sqrt(cv$temp$123$var182))) - (0.5 * Math.log(cv$temp$123$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$122$var181) / Math.sqrt(cv$temp$123$var182))) - (0.5 * Math.log(cv$temp$123$var182)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$364 = 1; index$i$364 < samples; index$i$364 += 1) {
														if(!(index$i$364 == index$i$19)) {
															for(int index$sample57$365 = 0; index$sample57$365 < noStates; index$sample57$365 += 1) {
																int distributionTempVariable$var56$367 = index$sample57$365;
																double cv$probabilitySample57Value366 = (1.0 * distribution$sample57[((index$i$364 - 1) / 1)][index$sample57$365]);
																int traceTempVariable$s$368_1 = cv$currentValue;
																if((index$i$364 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$124$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$368_1];
																								cv$temp$124$var181 = var181;
																							}
																							double cv$temp$125$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$368_1];
																								cv$temp$125$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$124$var181) / Math.sqrt(cv$temp$125$var182))) - (0.5 * Math.log(cv$temp$125$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$124$var181) / Math.sqrt(cv$temp$125$var182))) - (0.5 * Math.log(cv$temp$125$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$124$var181) / Math.sqrt(cv$temp$125$var182))) - (0.5 * Math.log(cv$temp$125$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$124$var181) / Math.sqrt(cv$temp$125$var182))) - (0.5 * Math.log(cv$temp$125$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$124$var181) / Math.sqrt(cv$temp$125$var182))) - (0.5 * Math.log(cv$temp$125$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value366);
																						}
																					}
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
						{
							{
								boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
								}
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174))
										guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
								}
								int traceTempVariable$s$437_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var111 = 0; var111 < noStates; var111 += 1) {
														if((var111 == st[i$var174])) {
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$150$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$437_1];
																						cv$temp$150$var186 = var186;
																					}
																					double cv$temp$151$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$437_1];
																						cv$temp$151$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$150$var186) / Math.sqrt(cv$temp$151$var187))) - (0.5 * Math.log(cv$temp$151$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$150$var186) / Math.sqrt(cv$temp$151$var187))) - (0.5 * Math.log(cv$temp$151$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$150$var186) / Math.sqrt(cv$temp$151$var187))) - (0.5 * Math.log(cv$temp$151$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$150$var186) / Math.sqrt(cv$temp$151$var187))) - (0.5 * Math.log(cv$temp$151$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$150$var186) / Math.sqrt(cv$temp$151$var187))) - (0.5 * Math.log(cv$temp$151$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample39$482 = 0; index$sample39$482 < noStates; index$sample39$482 += 1) {
																		int distributionTempVariable$var38$484 = index$sample39$482;
																		double cv$probabilitySample39Value483 = (1.0 * distribution$sample39[index$sample39$482]);
																		if((0 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$152$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$437_1];
																								cv$temp$152$var186 = var186;
																							}
																							double cv$temp$153$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$437_1];
																								cv$temp$153$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample39Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$152$var186) / Math.sqrt(cv$temp$153$var187))) - (0.5 * Math.log(cv$temp$153$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$152$var186) / Math.sqrt(cv$temp$153$var187))) - (0.5 * Math.log(cv$temp$153$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$152$var186) / Math.sqrt(cv$temp$153$var187))) - (0.5 * Math.log(cv$temp$153$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$152$var186) / Math.sqrt(cv$temp$153$var187))) - (0.5 * Math.log(cv$temp$153$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$152$var186) / Math.sqrt(cv$temp$153$var187))) - (0.5 * Math.log(cv$temp$153$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value483);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var111 = 0; var111 < noStates; var111 += 1) {
														if((var111 == st[i$var174])) {
															int traceTempVariable$s$489_1 = cv$currentValue;
															if((index$i$19 == i$var174)) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$154$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$489_1];
																					cv$temp$154$var186 = var186;
																				}
																				double cv$temp$155$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$489_1];
																					cv$temp$155$var187 = var187;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$154$var186) / Math.sqrt(cv$temp$155$var187))) - (0.5 * Math.log(cv$temp$155$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$154$var186) / Math.sqrt(cv$temp$155$var187))) - (0.5 * Math.log(cv$temp$155$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$154$var186) / Math.sqrt(cv$temp$155$var187))) - (0.5 * Math.log(cv$temp$155$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$154$var186) / Math.sqrt(cv$temp$155$var187))) - (0.5 * Math.log(cv$temp$155$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$154$var186) / Math.sqrt(cv$temp$155$var187))) - (0.5 * Math.log(cv$temp$155$var187)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$490 = 1; index$i$490 < samples; index$i$490 += 1) {
																if(!(index$i$490 == index$i$19)) {
																	for(int index$sample57$491 = 0; index$sample57$491 < noStates; index$sample57$491 += 1) {
																		int distributionTempVariable$var56$493 = index$sample57$491;
																		double cv$probabilitySample57Value492 = (1.0 * distribution$sample57[((index$i$490 - 1) / 1)][index$sample57$491]);
																		int traceTempVariable$s$494_1 = cv$currentValue;
																		if((index$i$490 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$156$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$494_1];
																								cv$temp$156$var186 = var186;
																							}
																							double cv$temp$157$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$494_1];
																								cv$temp$157$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$156$var186) / Math.sqrt(cv$temp$157$var187))) - (0.5 * Math.log(cv$temp$157$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$156$var186) / Math.sqrt(cv$temp$157$var187))) - (0.5 * Math.log(cv$temp$157$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$156$var186) / Math.sqrt(cv$temp$157$var187))) - (0.5 * Math.log(cv$temp$157$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$156$var186) / Math.sqrt(cv$temp$157$var187))) - (0.5 * Math.log(cv$temp$157$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$156$var186) / Math.sqrt(cv$temp$157$var187))) - (0.5 * Math.log(cv$temp$157$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value492);
																						}
																					}
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
								int traceTempVariable$s$441_1 = cv$currentValue;
								for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
									if((i$var50 == i$var174)) {
										if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
											guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var111 = 0; var111 < noStates; var111 += 1) {
																if((var111 == st[i$var174])) {
																	for(int var162 = 0; var162 < noStates; var162 += 1) {
																		if((var162 == st[i$var174])) {
																			{
																				{
																					double cv$temp$182$var186;
																					{
																						double var186 = pageFaultsMean[traceTempVariable$s$441_1];
																						cv$temp$182$var186 = var186;
																					}
																					double cv$temp$183$var187;
																					{
																						double var187 = pageFaultsVar[traceTempVariable$s$441_1];
																						cv$temp$183$var187 = var187;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$182$var186) / Math.sqrt(cv$temp$183$var187))) - (0.5 * Math.log(cv$temp$183$var187)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$182$var186) / Math.sqrt(cv$temp$183$var187))) - (0.5 * Math.log(cv$temp$183$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$182$var186) / Math.sqrt(cv$temp$183$var187))) - (0.5 * Math.log(cv$temp$183$var187))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$182$var186) / Math.sqrt(cv$temp$183$var187))) - (0.5 * Math.log(cv$temp$183$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$182$var186) / Math.sqrt(cv$temp$183$var187))) - (0.5 * Math.log(cv$temp$183$var187)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample39$549 = 0; index$sample39$549 < noStates; index$sample39$549 += 1) {
																int distributionTempVariable$var38$551 = index$sample39$549;
																double cv$probabilitySample39Value550 = (1.0 * distribution$sample39[index$sample39$549]);
																if((0 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$184$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$441_1];
																								cv$temp$184$var186 = var186;
																							}
																							double cv$temp$185$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$441_1];
																								cv$temp$185$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample39Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$184$var186) / Math.sqrt(cv$temp$185$var187))) - (0.5 * Math.log(cv$temp$185$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$184$var186) / Math.sqrt(cv$temp$185$var187))) - (0.5 * Math.log(cv$temp$185$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$184$var186) / Math.sqrt(cv$temp$185$var187))) - (0.5 * Math.log(cv$temp$185$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$184$var186) / Math.sqrt(cv$temp$185$var187))) - (0.5 * Math.log(cv$temp$185$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$184$var186) / Math.sqrt(cv$temp$185$var187))) - (0.5 * Math.log(cv$temp$185$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value550);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$557_1 = cv$currentValue;
													if((index$i$19 == i$var174)) {
														for(int var111 = 0; var111 < noStates; var111 += 1) {
															if((var111 == st[i$var174])) {
																for(int var162 = 0; var162 < noStates; var162 += 1) {
																	if((var162 == st[i$var174])) {
																		{
																			{
																				double cv$temp$186$var186;
																				{
																					double var186 = pageFaultsMean[traceTempVariable$s$557_1];
																					cv$temp$186$var186 = var186;
																				}
																				double cv$temp$187$var187;
																				{
																					double var187 = pageFaultsVar[traceTempVariable$s$557_1];
																					cv$temp$187$var187 = var187;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$186$var186) / Math.sqrt(cv$temp$187$var187))) - (0.5 * Math.log(cv$temp$187$var187)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$186$var186) / Math.sqrt(cv$temp$187$var187))) - (0.5 * Math.log(cv$temp$187$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$186$var186) / Math.sqrt(cv$temp$187$var187))) - (0.5 * Math.log(cv$temp$187$var187))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$186$var186) / Math.sqrt(cv$temp$187$var187))) - (0.5 * Math.log(cv$temp$187$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$186$var186) / Math.sqrt(cv$temp$187$var187))) - (0.5 * Math.log(cv$temp$187$var187)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$558 = 1; index$i$558 < samples; index$i$558 += 1) {
														if(!(index$i$558 == index$i$19)) {
															for(int index$sample57$559 = 0; index$sample57$559 < noStates; index$sample57$559 += 1) {
																int distributionTempVariable$var56$561 = index$sample57$559;
																double cv$probabilitySample57Value560 = (1.0 * distribution$sample57[((index$i$558 - 1) / 1)][index$sample57$559]);
																int traceTempVariable$s$562_1 = cv$currentValue;
																if((index$i$558 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$188$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$562_1];
																								cv$temp$188$var186 = var186;
																							}
																							double cv$temp$189$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$562_1];
																								cv$temp$189$var187 = var187;
																							}
																							if(((Math.log(cv$probabilitySample57Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$188$var186) / Math.sqrt(cv$temp$189$var187))) - (0.5 * Math.log(cv$temp$189$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$188$var186) / Math.sqrt(cv$temp$189$var187))) - (0.5 * Math.log(cv$temp$189$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$188$var186) / Math.sqrt(cv$temp$189$var187))) - (0.5 * Math.log(cv$temp$189$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$188$var186) / Math.sqrt(cv$temp$189$var187))) - (0.5 * Math.log(cv$temp$189$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$188$var186) / Math.sqrt(cv$temp$189$var187))) - (0.5 * Math.log(cv$temp$189$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value560);
																						}
																					}
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
			for(int index$i$28 = 1; index$i$28 < samples; index$i$28 += 1) {
				if(!(index$i$28 == index$i$19)) {
					for(int index$sample57$29 = 0; index$sample57$29 < noStates; index$sample57$29 += 1) {
						int distributionTempVariable$var56$31 = index$sample57$29;
						double cv$probabilitySample57Value30 = (1.0 * distribution$sample57[((index$i$28 - 1) / 1)][index$sample57$29]);
						int traceTempVariable$var53$32_1 = cv$currentValue;
						if((index$i$28 == (i$var50 - 1))) {
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value30);
									double[] cv$temp$6$var54;
									{
										double[] var54 = m[traceTempVariable$var53$32_1];
										cv$temp$6$var54 = var54;
									}
									int cv$temp$7$$var791;
									{
										int $var791 = noStates;
										cv$temp$7$$var791 = $var791;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var791))?Math.log(cv$temp$6$var54[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var53$38_1 = distributionTempVariable$var56$31;
										}
									}
									{
										{
											boolean[] guard$sample57gaussian179 = guard$sample57gaussian179$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian179[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$50_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		if(fixedFlag$sample39) {
																			if((0 == i$var174)) {
																				for(int var128 = 0; var128 < noStates; var128 += 1) {
																					if((var128 == st[i$var174])) {
																						{
																							{
																								double cv$temp$30$var176;
																								{
																									double var176 = cpuMean[traceTempVariable$s$50_1];
																									cv$temp$30$var176 = var176;
																								}
																								double cv$temp$31$var177;
																								{
																									double var177 = cpuVar[traceTempVariable$s$50_1];
																									cv$temp$31$var177 = var177;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$30$var176) / Math.sqrt(cv$temp$31$var177))) - (0.5 * Math.log(cv$temp$31$var177)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$30$var176) / Math.sqrt(cv$temp$31$var177))) - (0.5 * Math.log(cv$temp$31$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$30$var176) / Math.sqrt(cv$temp$31$var177))) - (0.5 * Math.log(cv$temp$31$var177))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$30$var176) / Math.sqrt(cv$temp$31$var177))) - (0.5 * Math.log(cv$temp$31$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$30$var176) / Math.sqrt(cv$temp$31$var177))) - (0.5 * Math.log(cv$temp$31$var177)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample39$111 = 0; index$sample39$111 < noStates; index$sample39$111 += 1) {
																					int distributionTempVariable$var38$113 = index$sample39$111;
																					double cv$probabilitySample39Value112 = (1.0 * distribution$sample39[index$sample39$111]);
																					if((0 == i$var174)) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$32$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$50_1];
																											cv$temp$32$var176 = var176;
																										}
																										double cv$temp$33$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$50_1];
																											cv$temp$33$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample39Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$32$var176) / Math.sqrt(cv$temp$33$var177))) - (0.5 * Math.log(cv$temp$33$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$32$var176) / Math.sqrt(cv$temp$33$var177))) - (0.5 * Math.log(cv$temp$33$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$32$var176) / Math.sqrt(cv$temp$33$var177))) - (0.5 * Math.log(cv$temp$33$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$32$var176) / Math.sqrt(cv$temp$33$var177))) - (0.5 * Math.log(cv$temp$33$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$32$var176) / Math.sqrt(cv$temp$33$var177))) - (0.5 * Math.log(cv$temp$33$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value112);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var75 = 0; var75 < noStates; var75 += 1) {
																	if((var75 == st[i$var174])) {
																		int traceTempVariable$s$118_1 = distributionTempVariable$var56$31;
																		if((index$i$19 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$34$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$118_1];
																								cv$temp$34$var176 = var176;
																							}
																							double cv$temp$35$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$118_1];
																								cv$temp$35$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$34$var176) / Math.sqrt(cv$temp$35$var177))) - (0.5 * Math.log(cv$temp$35$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$34$var176) / Math.sqrt(cv$temp$35$var177))) - (0.5 * Math.log(cv$temp$35$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$34$var176) / Math.sqrt(cv$temp$35$var177))) - (0.5 * Math.log(cv$temp$35$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$34$var176) / Math.sqrt(cv$temp$35$var177))) - (0.5 * Math.log(cv$temp$35$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$34$var176) / Math.sqrt(cv$temp$35$var177))) - (0.5 * Math.log(cv$temp$35$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$119_1 = distributionTempVariable$var56$31;
																		if((index$i$28 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$36$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$119_1];
																								cv$temp$36$var176 = var176;
																							}
																							double cv$temp$37$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$119_1];
																								cv$temp$37$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$36$var176) / Math.sqrt(cv$temp$37$var177))) - (0.5 * Math.log(cv$temp$37$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$36$var176) / Math.sqrt(cv$temp$37$var177))) - (0.5 * Math.log(cv$temp$37$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$36$var176) / Math.sqrt(cv$temp$37$var177))) - (0.5 * Math.log(cv$temp$37$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$36$var176) / Math.sqrt(cv$temp$37$var177))) - (0.5 * Math.log(cv$temp$37$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$36$var176) / Math.sqrt(cv$temp$37$var177))) - (0.5 * Math.log(cv$temp$37$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$120 = 1; index$i$120 < samples; index$i$120 += 1) {
																			if((!(index$i$120 == index$i$19) && !(index$i$120 == index$i$28))) {
																				for(int index$sample57$121 = 0; index$sample57$121 < noStates; index$sample57$121 += 1) {
																					int distributionTempVariable$var56$123 = index$sample57$121;
																					double cv$probabilitySample57Value122 = (1.0 * distribution$sample57[((index$i$120 - 1) / 1)][index$sample57$121]);
																					int traceTempVariable$s$124_1 = distributionTempVariable$var56$31;
																					if((index$i$120 == i$var174)) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$38$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$124_1];
																											cv$temp$38$var176 = var176;
																										}
																										double cv$temp$39$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$124_1];
																											cv$temp$39$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample57Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$38$var176) / Math.sqrt(cv$temp$39$var177))) - (0.5 * Math.log(cv$temp$39$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$38$var176) / Math.sqrt(cv$temp$39$var177))) - (0.5 * Math.log(cv$temp$39$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$38$var176) / Math.sqrt(cv$temp$39$var177))) - (0.5 * Math.log(cv$temp$39$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$38$var176) / Math.sqrt(cv$temp$39$var177))) - (0.5 * Math.log(cv$temp$39$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$38$var176) / Math.sqrt(cv$temp$39$var177))) - (0.5 * Math.log(cv$temp$39$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value122);
																									}
																								}
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
											int traceTempVariable$s$54_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian179[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian179[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample39) {
																	if((0 == i$var174)) {
																		for(int var75 = 0; var75 < noStates; var75 += 1) {
																			if((var75 == st[i$var174])) {
																				for(int var128 = 0; var128 < noStates; var128 += 1) {
																					if((var128 == st[i$var174])) {
																						{
																							{
																								double cv$temp$62$var176;
																								{
																									double var176 = cpuMean[traceTempVariable$s$54_1];
																									cv$temp$62$var176 = var176;
																								}
																								double cv$temp$63$var177;
																								{
																									double var177 = cpuVar[traceTempVariable$s$54_1];
																									cv$temp$63$var177 = var177;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$62$var176) / Math.sqrt(cv$temp$63$var177))) - (0.5 * Math.log(cv$temp$63$var177)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$62$var176) / Math.sqrt(cv$temp$63$var177))) - (0.5 * Math.log(cv$temp$63$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$62$var176) / Math.sqrt(cv$temp$63$var177))) - (0.5 * Math.log(cv$temp$63$var177))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$62$var176) / Math.sqrt(cv$temp$63$var177))) - (0.5 * Math.log(cv$temp$63$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$62$var176) / Math.sqrt(cv$temp$63$var177))) - (0.5 * Math.log(cv$temp$63$var177)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample39$180 = 0; index$sample39$180 < noStates; index$sample39$180 += 1) {
																			int distributionTempVariable$var38$182 = index$sample39$180;
																			double cv$probabilitySample39Value181 = (1.0 * distribution$sample39[index$sample39$180]);
																			if((0 == i$var174)) {
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == st[i$var174])) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$64$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$54_1];
																											cv$temp$64$var176 = var176;
																										}
																										double cv$temp$65$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$54_1];
																											cv$temp$65$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample39Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$64$var176) / Math.sqrt(cv$temp$65$var177))) - (0.5 * Math.log(cv$temp$65$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$64$var176) / Math.sqrt(cv$temp$65$var177))) - (0.5 * Math.log(cv$temp$65$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$64$var176) / Math.sqrt(cv$temp$65$var177))) - (0.5 * Math.log(cv$temp$65$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$64$var176) / Math.sqrt(cv$temp$65$var177))) - (0.5 * Math.log(cv$temp$65$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$64$var176) / Math.sqrt(cv$temp$65$var177))) - (0.5 * Math.log(cv$temp$65$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value181);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$188_1 = distributionTempVariable$var56$31;
																if((index$i$19 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$66$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$188_1];
																								cv$temp$66$var176 = var176;
																							}
																							double cv$temp$67$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$188_1];
																								cv$temp$67$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$66$var176) / Math.sqrt(cv$temp$67$var177))) - (0.5 * Math.log(cv$temp$67$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$66$var176) / Math.sqrt(cv$temp$67$var177))) - (0.5 * Math.log(cv$temp$67$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$66$var176) / Math.sqrt(cv$temp$67$var177))) - (0.5 * Math.log(cv$temp$67$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$66$var176) / Math.sqrt(cv$temp$67$var177))) - (0.5 * Math.log(cv$temp$67$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$66$var176) / Math.sqrt(cv$temp$67$var177))) - (0.5 * Math.log(cv$temp$67$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$189_1 = distributionTempVariable$var56$31;
																if((index$i$28 == i$var174)) {
																	for(int var75 = 0; var75 < noStates; var75 += 1) {
																		if((var75 == st[i$var174])) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$68$var176;
																							{
																								double var176 = cpuMean[traceTempVariable$s$189_1];
																								cv$temp$68$var176 = var176;
																							}
																							double cv$temp$69$var177;
																							{
																								double var177 = cpuVar[traceTempVariable$s$189_1];
																								cv$temp$69$var177 = var177;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$68$var176) / Math.sqrt(cv$temp$69$var177))) - (0.5 * Math.log(cv$temp$69$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$68$var176) / Math.sqrt(cv$temp$69$var177))) - (0.5 * Math.log(cv$temp$69$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$68$var176) / Math.sqrt(cv$temp$69$var177))) - (0.5 * Math.log(cv$temp$69$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$68$var176) / Math.sqrt(cv$temp$69$var177))) - (0.5 * Math.log(cv$temp$69$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$68$var176) / Math.sqrt(cv$temp$69$var177))) - (0.5 * Math.log(cv$temp$69$var177)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$190 = 1; index$i$190 < samples; index$i$190 += 1) {
																	if((!(index$i$190 == index$i$19) && !(index$i$190 == index$i$28))) {
																		for(int index$sample57$191 = 0; index$sample57$191 < noStates; index$sample57$191 += 1) {
																			int distributionTempVariable$var56$193 = index$sample57$191;
																			double cv$probabilitySample57Value192 = (1.0 * distribution$sample57[((index$i$190 - 1) / 1)][index$sample57$191]);
																			int traceTempVariable$s$194_1 = distributionTempVariable$var56$31;
																			if((index$i$190 == i$var174)) {
																				for(int var75 = 0; var75 < noStates; var75 += 1) {
																					if((var75 == st[i$var174])) {
																						for(int var128 = 0; var128 < noStates; var128 += 1) {
																							if((var128 == st[i$var174])) {
																								{
																									{
																										double cv$temp$70$var176;
																										{
																											double var176 = cpuMean[traceTempVariable$s$194_1];
																											cv$temp$70$var176 = var176;
																										}
																										double cv$temp$71$var177;
																										{
																											double var177 = cpuVar[traceTempVariable$s$194_1];
																											cv$temp$71$var177 = var177;
																										}
																										if(((Math.log(cv$probabilitySample57Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$70$var176) / Math.sqrt(cv$temp$71$var177))) - (0.5 * Math.log(cv$temp$71$var177)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$70$var176) / Math.sqrt(cv$temp$71$var177))) - (0.5 * Math.log(cv$temp$71$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$70$var176) / Math.sqrt(cv$temp$71$var177))) - (0.5 * Math.log(cv$temp$71$var177))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$70$var176) / Math.sqrt(cv$temp$71$var177))) - (0.5 * Math.log(cv$temp$71$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$70$var176) / Math.sqrt(cv$temp$71$var177))) - (0.5 * Math.log(cv$temp$71$var177)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value192);
																									}
																								}
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
									{
										{
											boolean[] guard$sample57gaussian184 = guard$sample57gaussian184$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian184[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$244_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		if(fixedFlag$sample39) {
																			if((0 == i$var174)) {
																				for(int var145 = 0; var145 < noStates; var145 += 1) {
																					if((var145 == st[i$var174])) {
																						{
																							{
																								double cv$temp$94$var181;
																								{
																									double var181 = memMean[traceTempVariable$s$244_1];
																									cv$temp$94$var181 = var181;
																								}
																								double cv$temp$95$var182;
																								{
																									double var182 = memVar[traceTempVariable$s$244_1];
																									cv$temp$95$var182 = var182;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$94$var181) / Math.sqrt(cv$temp$95$var182))) - (0.5 * Math.log(cv$temp$95$var182)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$94$var181) / Math.sqrt(cv$temp$95$var182))) - (0.5 * Math.log(cv$temp$95$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$94$var181) / Math.sqrt(cv$temp$95$var182))) - (0.5 * Math.log(cv$temp$95$var182))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$94$var181) / Math.sqrt(cv$temp$95$var182))) - (0.5 * Math.log(cv$temp$95$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$94$var181) / Math.sqrt(cv$temp$95$var182))) - (0.5 * Math.log(cv$temp$95$var182)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample39$305 = 0; index$sample39$305 < noStates; index$sample39$305 += 1) {
																					int distributionTempVariable$var38$307 = index$sample39$305;
																					double cv$probabilitySample39Value306 = (1.0 * distribution$sample39[index$sample39$305]);
																					if((0 == i$var174)) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$96$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$244_1];
																											cv$temp$96$var181 = var181;
																										}
																										double cv$temp$97$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$244_1];
																											cv$temp$97$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample39Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$96$var181) / Math.sqrt(cv$temp$97$var182))) - (0.5 * Math.log(cv$temp$97$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$96$var181) / Math.sqrt(cv$temp$97$var182))) - (0.5 * Math.log(cv$temp$97$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$96$var181) / Math.sqrt(cv$temp$97$var182))) - (0.5 * Math.log(cv$temp$97$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$96$var181) / Math.sqrt(cv$temp$97$var182))) - (0.5 * Math.log(cv$temp$97$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$96$var181) / Math.sqrt(cv$temp$97$var182))) - (0.5 * Math.log(cv$temp$97$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value306);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var93 = 0; var93 < noStates; var93 += 1) {
																	if((var93 == st[i$var174])) {
																		int traceTempVariable$s$312_1 = distributionTempVariable$var56$31;
																		if((index$i$19 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$98$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$312_1];
																								cv$temp$98$var181 = var181;
																							}
																							double cv$temp$99$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$312_1];
																								cv$temp$99$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$98$var181) / Math.sqrt(cv$temp$99$var182))) - (0.5 * Math.log(cv$temp$99$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$98$var181) / Math.sqrt(cv$temp$99$var182))) - (0.5 * Math.log(cv$temp$99$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$98$var181) / Math.sqrt(cv$temp$99$var182))) - (0.5 * Math.log(cv$temp$99$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$98$var181) / Math.sqrt(cv$temp$99$var182))) - (0.5 * Math.log(cv$temp$99$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$98$var181) / Math.sqrt(cv$temp$99$var182))) - (0.5 * Math.log(cv$temp$99$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$313_1 = distributionTempVariable$var56$31;
																		if((index$i$28 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$100$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$313_1];
																								cv$temp$100$var181 = var181;
																							}
																							double cv$temp$101$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$313_1];
																								cv$temp$101$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$100$var181) / Math.sqrt(cv$temp$101$var182))) - (0.5 * Math.log(cv$temp$101$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$100$var181) / Math.sqrt(cv$temp$101$var182))) - (0.5 * Math.log(cv$temp$101$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$100$var181) / Math.sqrt(cv$temp$101$var182))) - (0.5 * Math.log(cv$temp$101$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$100$var181) / Math.sqrt(cv$temp$101$var182))) - (0.5 * Math.log(cv$temp$101$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$100$var181) / Math.sqrt(cv$temp$101$var182))) - (0.5 * Math.log(cv$temp$101$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$314 = 1; index$i$314 < samples; index$i$314 += 1) {
																			if((!(index$i$314 == index$i$19) && !(index$i$314 == index$i$28))) {
																				for(int index$sample57$315 = 0; index$sample57$315 < noStates; index$sample57$315 += 1) {
																					int distributionTempVariable$var56$317 = index$sample57$315;
																					double cv$probabilitySample57Value316 = (1.0 * distribution$sample57[((index$i$314 - 1) / 1)][index$sample57$315]);
																					int traceTempVariable$s$318_1 = distributionTempVariable$var56$31;
																					if((index$i$314 == i$var174)) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$102$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$318_1];
																											cv$temp$102$var181 = var181;
																										}
																										double cv$temp$103$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$318_1];
																											cv$temp$103$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample57Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$102$var181) / Math.sqrt(cv$temp$103$var182))) - (0.5 * Math.log(cv$temp$103$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$102$var181) / Math.sqrt(cv$temp$103$var182))) - (0.5 * Math.log(cv$temp$103$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$102$var181) / Math.sqrt(cv$temp$103$var182))) - (0.5 * Math.log(cv$temp$103$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$102$var181) / Math.sqrt(cv$temp$103$var182))) - (0.5 * Math.log(cv$temp$103$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$102$var181) / Math.sqrt(cv$temp$103$var182))) - (0.5 * Math.log(cv$temp$103$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value316);
																									}
																								}
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
											int traceTempVariable$s$248_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian184[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian184[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample39) {
																	if((0 == i$var174)) {
																		for(int var93 = 0; var93 < noStates; var93 += 1) {
																			if((var93 == st[i$var174])) {
																				for(int var145 = 0; var145 < noStates; var145 += 1) {
																					if((var145 == st[i$var174])) {
																						{
																							{
																								double cv$temp$126$var181;
																								{
																									double var181 = memMean[traceTempVariable$s$248_1];
																									cv$temp$126$var181 = var181;
																								}
																								double cv$temp$127$var182;
																								{
																									double var182 = memVar[traceTempVariable$s$248_1];
																									cv$temp$127$var182 = var182;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$126$var181) / Math.sqrt(cv$temp$127$var182))) - (0.5 * Math.log(cv$temp$127$var182)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$126$var181) / Math.sqrt(cv$temp$127$var182))) - (0.5 * Math.log(cv$temp$127$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$126$var181) / Math.sqrt(cv$temp$127$var182))) - (0.5 * Math.log(cv$temp$127$var182))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$126$var181) / Math.sqrt(cv$temp$127$var182))) - (0.5 * Math.log(cv$temp$127$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$126$var181) / Math.sqrt(cv$temp$127$var182))) - (0.5 * Math.log(cv$temp$127$var182)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample39$374 = 0; index$sample39$374 < noStates; index$sample39$374 += 1) {
																			int distributionTempVariable$var38$376 = index$sample39$374;
																			double cv$probabilitySample39Value375 = (1.0 * distribution$sample39[index$sample39$374]);
																			if((0 == i$var174)) {
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == st[i$var174])) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$128$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$248_1];
																											cv$temp$128$var181 = var181;
																										}
																										double cv$temp$129$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$248_1];
																											cv$temp$129$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample39Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$128$var181) / Math.sqrt(cv$temp$129$var182))) - (0.5 * Math.log(cv$temp$129$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$128$var181) / Math.sqrt(cv$temp$129$var182))) - (0.5 * Math.log(cv$temp$129$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$128$var181) / Math.sqrt(cv$temp$129$var182))) - (0.5 * Math.log(cv$temp$129$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$128$var181) / Math.sqrt(cv$temp$129$var182))) - (0.5 * Math.log(cv$temp$129$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$128$var181) / Math.sqrt(cv$temp$129$var182))) - (0.5 * Math.log(cv$temp$129$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value375);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$382_1 = distributionTempVariable$var56$31;
																if((index$i$19 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$130$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$382_1];
																								cv$temp$130$var181 = var181;
																							}
																							double cv$temp$131$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$382_1];
																								cv$temp$131$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$130$var181) / Math.sqrt(cv$temp$131$var182))) - (0.5 * Math.log(cv$temp$131$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$130$var181) / Math.sqrt(cv$temp$131$var182))) - (0.5 * Math.log(cv$temp$131$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$130$var181) / Math.sqrt(cv$temp$131$var182))) - (0.5 * Math.log(cv$temp$131$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$130$var181) / Math.sqrt(cv$temp$131$var182))) - (0.5 * Math.log(cv$temp$131$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$130$var181) / Math.sqrt(cv$temp$131$var182))) - (0.5 * Math.log(cv$temp$131$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$383_1 = distributionTempVariable$var56$31;
																if((index$i$28 == i$var174)) {
																	for(int var93 = 0; var93 < noStates; var93 += 1) {
																		if((var93 == st[i$var174])) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$132$var181;
																							{
																								double var181 = memMean[traceTempVariable$s$383_1];
																								cv$temp$132$var181 = var181;
																							}
																							double cv$temp$133$var182;
																							{
																								double var182 = memVar[traceTempVariable$s$383_1];
																								cv$temp$133$var182 = var182;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$132$var181) / Math.sqrt(cv$temp$133$var182))) - (0.5 * Math.log(cv$temp$133$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$132$var181) / Math.sqrt(cv$temp$133$var182))) - (0.5 * Math.log(cv$temp$133$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$132$var181) / Math.sqrt(cv$temp$133$var182))) - (0.5 * Math.log(cv$temp$133$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$132$var181) / Math.sqrt(cv$temp$133$var182))) - (0.5 * Math.log(cv$temp$133$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$132$var181) / Math.sqrt(cv$temp$133$var182))) - (0.5 * Math.log(cv$temp$133$var182)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$384 = 1; index$i$384 < samples; index$i$384 += 1) {
																	if((!(index$i$384 == index$i$19) && !(index$i$384 == index$i$28))) {
																		for(int index$sample57$385 = 0; index$sample57$385 < noStates; index$sample57$385 += 1) {
																			int distributionTempVariable$var56$387 = index$sample57$385;
																			double cv$probabilitySample57Value386 = (1.0 * distribution$sample57[((index$i$384 - 1) / 1)][index$sample57$385]);
																			int traceTempVariable$s$388_1 = distributionTempVariable$var56$31;
																			if((index$i$384 == i$var174)) {
																				for(int var93 = 0; var93 < noStates; var93 += 1) {
																					if((var93 == st[i$var174])) {
																						for(int var145 = 0; var145 < noStates; var145 += 1) {
																							if((var145 == st[i$var174])) {
																								{
																									{
																										double cv$temp$134$var181;
																										{
																											double var181 = memMean[traceTempVariable$s$388_1];
																											cv$temp$134$var181 = var181;
																										}
																										double cv$temp$135$var182;
																										{
																											double var182 = memVar[traceTempVariable$s$388_1];
																											cv$temp$135$var182 = var182;
																										}
																										if(((Math.log(cv$probabilitySample57Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$134$var181) / Math.sqrt(cv$temp$135$var182))) - (0.5 * Math.log(cv$temp$135$var182)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$134$var181) / Math.sqrt(cv$temp$135$var182))) - (0.5 * Math.log(cv$temp$135$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$134$var181) / Math.sqrt(cv$temp$135$var182))) - (0.5 * Math.log(cv$temp$135$var182))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$134$var181) / Math.sqrt(cv$temp$135$var182))) - (0.5 * Math.log(cv$temp$135$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$134$var181) / Math.sqrt(cv$temp$135$var182))) - (0.5 * Math.log(cv$temp$135$var182)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value386);
																									}
																								}
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
									{
										{
											boolean[] guard$sample57gaussian189 = guard$sample57gaussian189$global;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
											}
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174))
													guard$sample57gaussian189[((i$var174 - 0) / 1)] = false;
											}
											int traceTempVariable$s$438_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		if(fixedFlag$sample39) {
																			if((0 == i$var174)) {
																				for(int var162 = 0; var162 < noStates; var162 += 1) {
																					if((var162 == st[i$var174])) {
																						{
																							{
																								double cv$temp$158$var186;
																								{
																									double var186 = pageFaultsMean[traceTempVariable$s$438_1];
																									cv$temp$158$var186 = var186;
																								}
																								double cv$temp$159$var187;
																								{
																									double var187 = pageFaultsVar[traceTempVariable$s$438_1];
																									cv$temp$159$var187 = var187;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$158$var186) / Math.sqrt(cv$temp$159$var187))) - (0.5 * Math.log(cv$temp$159$var187)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$158$var186) / Math.sqrt(cv$temp$159$var187))) - (0.5 * Math.log(cv$temp$159$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$158$var186) / Math.sqrt(cv$temp$159$var187))) - (0.5 * Math.log(cv$temp$159$var187))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$158$var186) / Math.sqrt(cv$temp$159$var187))) - (0.5 * Math.log(cv$temp$159$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$158$var186) / Math.sqrt(cv$temp$159$var187))) - (0.5 * Math.log(cv$temp$159$var187)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample39$499 = 0; index$sample39$499 < noStates; index$sample39$499 += 1) {
																					int distributionTempVariable$var38$501 = index$sample39$499;
																					double cv$probabilitySample39Value500 = (1.0 * distribution$sample39[index$sample39$499]);
																					if((0 == i$var174)) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$160$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$438_1];
																											cv$temp$160$var186 = var186;
																										}
																										double cv$temp$161$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$438_1];
																											cv$temp$161$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample39Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$160$var186) / Math.sqrt(cv$temp$161$var187))) - (0.5 * Math.log(cv$temp$161$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$160$var186) / Math.sqrt(cv$temp$161$var187))) - (0.5 * Math.log(cv$temp$161$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$160$var186) / Math.sqrt(cv$temp$161$var187))) - (0.5 * Math.log(cv$temp$161$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$160$var186) / Math.sqrt(cv$temp$161$var187))) - (0.5 * Math.log(cv$temp$161$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$160$var186) / Math.sqrt(cv$temp$161$var187))) - (0.5 * Math.log(cv$temp$161$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value500);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var111 = 0; var111 < noStates; var111 += 1) {
																	if((var111 == st[i$var174])) {
																		int traceTempVariable$s$506_1 = distributionTempVariable$var56$31;
																		if((index$i$19 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$162$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$506_1];
																								cv$temp$162$var186 = var186;
																							}
																							double cv$temp$163$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$506_1];
																								cv$temp$163$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$162$var186) / Math.sqrt(cv$temp$163$var187))) - (0.5 * Math.log(cv$temp$163$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$162$var186) / Math.sqrt(cv$temp$163$var187))) - (0.5 * Math.log(cv$temp$163$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$162$var186) / Math.sqrt(cv$temp$163$var187))) - (0.5 * Math.log(cv$temp$163$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$162$var186) / Math.sqrt(cv$temp$163$var187))) - (0.5 * Math.log(cv$temp$163$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$162$var186) / Math.sqrt(cv$temp$163$var187))) - (0.5 * Math.log(cv$temp$163$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$507_1 = distributionTempVariable$var56$31;
																		if((index$i$28 == i$var174)) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$164$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$507_1];
																								cv$temp$164$var186 = var186;
																							}
																							double cv$temp$165$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$507_1];
																								cv$temp$165$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$164$var186) / Math.sqrt(cv$temp$165$var187))) - (0.5 * Math.log(cv$temp$165$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$164$var186) / Math.sqrt(cv$temp$165$var187))) - (0.5 * Math.log(cv$temp$165$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$164$var186) / Math.sqrt(cv$temp$165$var187))) - (0.5 * Math.log(cv$temp$165$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$164$var186) / Math.sqrt(cv$temp$165$var187))) - (0.5 * Math.log(cv$temp$165$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$164$var186) / Math.sqrt(cv$temp$165$var187))) - (0.5 * Math.log(cv$temp$165$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$508 = 1; index$i$508 < samples; index$i$508 += 1) {
																			if((!(index$i$508 == index$i$19) && !(index$i$508 == index$i$28))) {
																				for(int index$sample57$509 = 0; index$sample57$509 < noStates; index$sample57$509 += 1) {
																					int distributionTempVariable$var56$511 = index$sample57$509;
																					double cv$probabilitySample57Value510 = (1.0 * distribution$sample57[((index$i$508 - 1) / 1)][index$sample57$509]);
																					int traceTempVariable$s$512_1 = distributionTempVariable$var56$31;
																					if((index$i$508 == i$var174)) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$166$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$512_1];
																											cv$temp$166$var186 = var186;
																										}
																										double cv$temp$167$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$512_1];
																											cv$temp$167$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample57Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$166$var186) / Math.sqrt(cv$temp$167$var187))) - (0.5 * Math.log(cv$temp$167$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$166$var186) / Math.sqrt(cv$temp$167$var187))) - (0.5 * Math.log(cv$temp$167$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$166$var186) / Math.sqrt(cv$temp$167$var187))) - (0.5 * Math.log(cv$temp$167$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$166$var186) / Math.sqrt(cv$temp$167$var187))) - (0.5 * Math.log(cv$temp$167$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$166$var186) / Math.sqrt(cv$temp$167$var187))) - (0.5 * Math.log(cv$temp$167$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value510);
																									}
																								}
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
											int traceTempVariable$s$442_1 = distributionTempVariable$var56$31;
											for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
												if((i$var50 == i$var174)) {
													if(!guard$sample57gaussian189[((i$var174 - 0) / 1)]) {
														guard$sample57gaussian189[((i$var174 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample39) {
																	if((0 == i$var174)) {
																		for(int var111 = 0; var111 < noStates; var111 += 1) {
																			if((var111 == st[i$var174])) {
																				for(int var162 = 0; var162 < noStates; var162 += 1) {
																					if((var162 == st[i$var174])) {
																						{
																							{
																								double cv$temp$190$var186;
																								{
																									double var186 = pageFaultsMean[traceTempVariable$s$442_1];
																									cv$temp$190$var186 = var186;
																								}
																								double cv$temp$191$var187;
																								{
																									double var187 = pageFaultsVar[traceTempVariable$s$442_1];
																									cv$temp$191$var187 = var187;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$190$var186) / Math.sqrt(cv$temp$191$var187))) - (0.5 * Math.log(cv$temp$191$var187)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$190$var186) / Math.sqrt(cv$temp$191$var187))) - (0.5 * Math.log(cv$temp$191$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$190$var186) / Math.sqrt(cv$temp$191$var187))) - (0.5 * Math.log(cv$temp$191$var187))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$190$var186) / Math.sqrt(cv$temp$191$var187))) - (0.5 * Math.log(cv$temp$191$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$190$var186) / Math.sqrt(cv$temp$191$var187))) - (0.5 * Math.log(cv$temp$191$var187)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample39$568 = 0; index$sample39$568 < noStates; index$sample39$568 += 1) {
																			int distributionTempVariable$var38$570 = index$sample39$568;
																			double cv$probabilitySample39Value569 = (1.0 * distribution$sample39[index$sample39$568]);
																			if((0 == i$var174)) {
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == st[i$var174])) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$192$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$442_1];
																											cv$temp$192$var186 = var186;
																										}
																										double cv$temp$193$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$442_1];
																											cv$temp$193$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample39Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$192$var186) / Math.sqrt(cv$temp$193$var187))) - (0.5 * Math.log(cv$temp$193$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$192$var186) / Math.sqrt(cv$temp$193$var187))) - (0.5 * Math.log(cv$temp$193$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$192$var186) / Math.sqrt(cv$temp$193$var187))) - (0.5 * Math.log(cv$temp$193$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$192$var186) / Math.sqrt(cv$temp$193$var187))) - (0.5 * Math.log(cv$temp$193$var187)))))) + 1)) + (Math.log(cv$probabilitySample39Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$192$var186) / Math.sqrt(cv$temp$193$var187))) - (0.5 * Math.log(cv$temp$193$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value569);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$576_1 = distributionTempVariable$var56$31;
																if((index$i$19 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$194$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$576_1];
																								cv$temp$194$var186 = var186;
																							}
																							double cv$temp$195$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$576_1];
																								cv$temp$195$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$194$var186) / Math.sqrt(cv$temp$195$var187))) - (0.5 * Math.log(cv$temp$195$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$194$var186) / Math.sqrt(cv$temp$195$var187))) - (0.5 * Math.log(cv$temp$195$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$194$var186) / Math.sqrt(cv$temp$195$var187))) - (0.5 * Math.log(cv$temp$195$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$194$var186) / Math.sqrt(cv$temp$195$var187))) - (0.5 * Math.log(cv$temp$195$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$194$var186) / Math.sqrt(cv$temp$195$var187))) - (0.5 * Math.log(cv$temp$195$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$577_1 = distributionTempVariable$var56$31;
																if((index$i$28 == i$var174)) {
																	for(int var111 = 0; var111 < noStates; var111 += 1) {
																		if((var111 == st[i$var174])) {
																			for(int var162 = 0; var162 < noStates; var162 += 1) {
																				if((var162 == st[i$var174])) {
																					{
																						{
																							double cv$temp$196$var186;
																							{
																								double var186 = pageFaultsMean[traceTempVariable$s$577_1];
																								cv$temp$196$var186 = var186;
																							}
																							double cv$temp$197$var187;
																							{
																								double var187 = pageFaultsVar[traceTempVariable$s$577_1];
																								cv$temp$197$var187 = var187;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$196$var186) / Math.sqrt(cv$temp$197$var187))) - (0.5 * Math.log(cv$temp$197$var187)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$196$var186) / Math.sqrt(cv$temp$197$var187))) - (0.5 * Math.log(cv$temp$197$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$196$var186) / Math.sqrt(cv$temp$197$var187))) - (0.5 * Math.log(cv$temp$197$var187))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$196$var186) / Math.sqrt(cv$temp$197$var187))) - (0.5 * Math.log(cv$temp$197$var187)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$196$var186) / Math.sqrt(cv$temp$197$var187))) - (0.5 * Math.log(cv$temp$197$var187)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$578 = 1; index$i$578 < samples; index$i$578 += 1) {
																	if((!(index$i$578 == index$i$19) && !(index$i$578 == index$i$28))) {
																		for(int index$sample57$579 = 0; index$sample57$579 < noStates; index$sample57$579 += 1) {
																			int distributionTempVariable$var56$581 = index$sample57$579;
																			double cv$probabilitySample57Value580 = (1.0 * distribution$sample57[((index$i$578 - 1) / 1)][index$sample57$579]);
																			int traceTempVariable$s$582_1 = distributionTempVariable$var56$31;
																			if((index$i$578 == i$var174)) {
																				for(int var111 = 0; var111 < noStates; var111 += 1) {
																					if((var111 == st[i$var174])) {
																						for(int var162 = 0; var162 < noStates; var162 += 1) {
																							if((var162 == st[i$var174])) {
																								{
																									{
																										double cv$temp$198$var186;
																										{
																											double var186 = pageFaultsMean[traceTempVariable$s$582_1];
																											cv$temp$198$var186 = var186;
																										}
																										double cv$temp$199$var187;
																										{
																											double var187 = pageFaultsVar[traceTempVariable$s$582_1];
																											cv$temp$199$var187 = var187;
																										}
																										if(((Math.log(cv$probabilitySample57Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$198$var186) / Math.sqrt(cv$temp$199$var187))) - (0.5 * Math.log(cv$temp$199$var187)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$198$var186) / Math.sqrt(cv$temp$199$var187))) - (0.5 * Math.log(cv$temp$199$var187)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$198$var186) / Math.sqrt(cv$temp$199$var187))) - (0.5 * Math.log(cv$temp$199$var187))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$198$var186) / Math.sqrt(cv$temp$199$var187))) - (0.5 * Math.log(cv$temp$199$var187)))))) + 1)) + (Math.log(cv$probabilitySample57Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$temp$198$var186) / Math.sqrt(cv$temp$199$var187))) - (0.5 * Math.log(cv$temp$199$var187)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value580);
																									}
																								}
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
			{
				{
					int traceTempVariable$var53$621_1 = cv$currentValue;
					for(int index$i$621_2 = 1; index$i$621_2 < samples; index$i$621_2 += 1) {
						if((i$var50 == (index$i$621_2 - 1))) {
							{
								int index$i$623 = index$i$621_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var55;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var29 = 0; var29 < noStates; var29 += 1) {
									if((var29 == st[(index$i$621_2 - 1)])) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample39) {
												if((0 == (i$var50 - 1))) {
													for(int index$var29$630_1 = 0; index$var29$630_1 < noStates; index$var29$630_1 += 1) {
														if((index$var29$630_1 == st[(i$var50 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample39$626 = 0; index$sample39$626 < noStates; index$sample39$626 += 1) {
														int distributionTempVariable$var38$628 = index$sample39$626;
														double cv$probabilitySample39Value627 = (1.0 * distribution$sample39[index$sample39$626]);
														if((0 == (i$var50 - 1))) {
															for(int index$var29$631_1 = 0; index$var29$631_1 < noStates; index$var29$631_1 += 1) {
																if((index$var29$631_1 == st[(i$var50 - 1)]))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample39Value627);
															}
														}
													}
												}
											}
											int traceTempVariable$var53$632_1 = cv$currentValue;
											if((index$i$19 == (i$var50 - 1))) {
												for(int index$var29$638_1 = 0; index$var29$638_1 < noStates; index$var29$638_1 += 1) {
													if((index$var29$638_1 == st[(i$var50 - 1)]))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$633 = 1; index$i$633 < samples; index$i$633 += 1) {
												if((!(index$i$633 == index$i$19) && !(index$i$633 == index$i$623))) {
													for(int index$sample57$634 = 0; index$sample57$634 < noStates; index$sample57$634 += 1) {
														int distributionTempVariable$var56$636 = index$sample57$634;
														double cv$probabilitySample57Value635 = (1.0 * distribution$sample57[((index$i$633 - 1) / 1)][index$sample57$634]);
														int traceTempVariable$var53$637_1 = cv$currentValue;
														if((index$i$633 == (i$var50 - 1))) {
															for(int index$var29$639_1 = 0; index$var29$639_1 < noStates; index$var29$639_1 += 1) {
																if((index$var29$639_1 == st[(i$var50 - 1)]))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value635);
															}
														}
													}
												}
											}
											double[] cv$temp$200$var54;
											{
												double[] var54 = m[traceTempVariable$var53$621_1];
												cv$temp$200$var54 = var54;
											}
											int cv$temp$201$$var1482;
											{
												int $var1482 = noStates;
												cv$temp$201$$var1482 = $var1482;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$200$var54, cv$temp$201$$var1482);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample57[((index$i$621_2 - 1) / 1)];
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
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample57[((i$var50 - 1) / 1)];
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
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample77(int var75) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = cpuMean[var75];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var76 = cv$proposedValue;
					{
						{
							cpuMean[var75] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var63;
				{
					cv$temp$0$var63 = 16.0;
				}
				double cv$temp$1$var62;
				{
					cv$temp$1$var62 = 8.6;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var63) / Math.sqrt(cv$temp$1$var62))) - (0.5 * Math.log(cv$temp$1$var62))));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var176$8_1 = cv$currentValue;
									if((var75 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var128 = 0; var128 < noStates; var128 += 1) {
														if((var128 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var176;
																	{
																		double var176 = traceTempVariable$var176$8_1;
																		cv$temp$2$var176 = var176;
																	}
																	double cv$temp$3$var177;
																	{
																		double var177 = cpuVar[st[i$var174]];
																		cv$temp$3$var177 = var177;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$2$var176) / Math.sqrt(cv$temp$3$var177))) - (0.5 * Math.log(cv$temp$3$var177)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var128 = 0; var128 < noStates; var128 += 1) {
																if((var128 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var176;
																			{
																				double var176 = traceTempVariable$var176$8_1;
																				cv$temp$4$var176 = var176;
																			}
																			double cv$temp$5$var177;
																			{
																				double var177 = cpuVar[st[i$var174]];
																				cv$temp$5$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$4$var176) / Math.sqrt(cv$temp$5$var177))) - (0.5 * Math.log(cv$temp$5$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var176;
																					{
																						double var176 = traceTempVariable$var176$8_1;
																						cv$temp$6$var176 = var176;
																					}
																					double cv$temp$7$var177;
																					{
																						double var177 = cpuVar[st[i$var174]];
																						cv$temp$7$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$6$var176) / Math.sqrt(cv$temp$7$var177))) - (0.5 * Math.log(cv$temp$7$var177)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var176$9_1 = cv$currentValue;
											if((var75 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var128 = 0; var128 < noStates; var128 += 1) {
																if((var128 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var176;
																			{
																				double var176 = traceTempVariable$var176$9_1;
																				cv$temp$8$var176 = var176;
																			}
																			double cv$temp$9$var177;
																			{
																				double var177 = cpuVar[st[i$var174]];
																				cv$temp$9$var177 = var177;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$8$var176) / Math.sqrt(cv$temp$9$var177))) - (0.5 * Math.log(cv$temp$9$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var176;
																					{
																						double var176 = traceTempVariable$var176$9_1;
																						cv$temp$10$var176 = var176;
																					}
																					double cv$temp$11$var177;
																					{
																						double var177 = cpuVar[st[i$var174]];
																						cv$temp$11$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$10$var176) / Math.sqrt(cv$temp$11$var177))) - (0.5 * Math.log(cv$temp$11$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var176;
																					{
																						double var176 = traceTempVariable$var176$9_1;
																						cv$temp$12$var176 = var176;
																					}
																					double cv$temp$13$var177;
																					{
																						double var177 = cpuVar[st[i$var174]];
																						cv$temp$13$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$12$var176) / Math.sqrt(cv$temp$13$var177))) - (0.5 * Math.log(cv$temp$13$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var176;
																							{
																								double var176 = traceTempVariable$var176$9_1;
																								cv$temp$14$var176 = var176;
																							}
																							double cv$temp$15$var177;
																							{
																								double var177 = cpuVar[st[i$var174]];
																								cv$temp$15$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$14$var176) / Math.sqrt(cv$temp$15$var177))) - (0.5 * Math.log(cv$temp$15$var177)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var176$17_1 = cv$currentValue;
										if((var75 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var128 = 0; var128 < noStates; var128 += 1) {
																if((var128 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var176;
																			{
																				double var176 = traceTempVariable$var176$17_1;
																				cv$temp$16$var176 = var176;
																			}
																			double cv$temp$17$var177;
																			{
																				double var177 = cpuVar[st[i$var174]];
																				cv$temp$17$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$16$var176) / Math.sqrt(cv$temp$17$var177))) - (0.5 * Math.log(cv$temp$17$var177)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var176;
																					{
																						double var176 = traceTempVariable$var176$17_1;
																						cv$temp$18$var176 = var176;
																					}
																					double cv$temp$19$var177;
																					{
																						double var177 = cpuVar[st[i$var174]];
																						cv$temp$19$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$18$var176) / Math.sqrt(cv$temp$19$var177))) - (0.5 * Math.log(cv$temp$19$var177)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var128 = 0; var128 < noStates; var128 += 1) {
																if((var128 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var176;
																			{
																				double var176 = traceTempVariable$var176$17_1;
																				cv$temp$20$var176 = var176;
																			}
																			double cv$temp$21$var177;
																			{
																				double var177 = cpuVar[st[i$var174]];
																				cv$temp$21$var177 = var177;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$20$var176) / Math.sqrt(cv$temp$21$var177))) - (0.5 * Math.log(cv$temp$21$var177)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var176$18_1 = cv$currentValue;
												if((var75 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var128 = 0; var128 < noStates; var128 += 1) {
																		if((var128 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var176;
																					{
																						double var176 = traceTempVariable$var176$18_1;
																						cv$temp$22$var176 = var176;
																					}
																					double cv$temp$23$var177;
																					{
																						double var177 = cpuVar[st[i$var174]];
																						cv$temp$23$var177 = var177;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$22$var176) / Math.sqrt(cv$temp$23$var177))) - (0.5 * Math.log(cv$temp$23$var177)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var176;
																							{
																								double var176 = traceTempVariable$var176$18_1;
																								cv$temp$24$var176 = var176;
																							}
																							double cv$temp$25$var177;
																							{
																								double var177 = cpuVar[st[i$var174]];
																								cv$temp$25$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$24$var176) / Math.sqrt(cv$temp$25$var177))) - (0.5 * Math.log(cv$temp$25$var177)))));
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
															if((i$var50 == i$var174)) {
																for(int var128 = 0; var128 < noStates; var128 += 1) {
																	if((var128 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var176;
																				{
																					double var176 = traceTempVariable$var176$18_1;
																					cv$temp$26$var176 = var176;
																				}
																				double cv$temp$27$var177;
																				{
																					double var177 = cpuVar[st[i$var174]];
																					cv$temp$27$var177 = var177;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$26$var176) / Math.sqrt(cv$temp$27$var177))) - (0.5 * Math.log(cv$temp$27$var177)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var128 = 0; var128 < noStates; var128 += 1) {
																				if((var128 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var176;
																							{
																								double var176 = traceTempVariable$var176$18_1;
																								cv$temp$28$var176 = var176;
																							}
																							double cv$temp$29$var177;
																							{
																								double var177 = cpuVar[st[i$var174]];
																								cv$temp$29$var177 = var177;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$temp$28$var176) / Math.sqrt(cv$temp$29$var177))) - (0.5 * Math.log(cv$temp$29$var177)))));
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
			double var76 = cv$originalValue;
			{
				{
					cpuMean[var75] = var76;
				}
			}
		}
	}

	private final void sample95(int var93) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = memMean[var93];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var94 = cv$proposedValue;
					{
						{
							memMean[var93] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var80;
				{
					cv$temp$0$var80 = 94.0;
				}
				double cv$temp$1$var81;
				{
					cv$temp$1$var81 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var80) / Math.sqrt(cv$temp$1$var81))) - (0.5 * Math.log(cv$temp$1$var81))));
				{
					{
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample39) {
								if((0 == i$var174)) {
									double traceTempVariable$var181$8_1 = cv$currentValue;
									if((var93 == st[i$var174])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var174)) {
													for(int var145 = 0; var145 < noStates; var145 += 1) {
														if((var145 == st[i$var174])) {
															{
																{
																	double cv$temp$2$var181;
																	{
																		double var181 = traceTempVariable$var181$8_1;
																		cv$temp$2$var181 = var181;
																	}
																	double cv$temp$3$var182;
																	{
																		double var182 = memVar[st[i$var174]];
																		cv$temp$3$var182 = var182;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$2$var181) / Math.sqrt(cv$temp$3$var182))) - (0.5 * Math.log(cv$temp$3$var182)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample57) {
													for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
														if((i$var50 == i$var174)) {
															for(int var145 = 0; var145 < noStates; var145 += 1) {
																if((var145 == st[i$var174])) {
																	{
																		{
																			double cv$temp$4$var181;
																			{
																				double var181 = traceTempVariable$var181$8_1;
																				cv$temp$4$var181 = var181;
																			}
																			double cv$temp$5$var182;
																			{
																				double var182 = memVar[st[i$var174]];
																				cv$temp$5$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$4$var181) / Math.sqrt(cv$temp$5$var182))) - (0.5 * Math.log(cv$temp$5$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((i$var50 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$6$var181;
																					{
																						double var181 = traceTempVariable$var181$8_1;
																						cv$temp$6$var181 = var181;
																					}
																					double cv$temp$7$var182;
																					{
																						double var182 = memVar[st[i$var174]];
																						cv$temp$7$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value28) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$6$var181) / Math.sqrt(cv$temp$7$var182))) - (0.5 * Math.log(cv$temp$7$var182)))));
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
							} else {
								if(true) {
									for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
										int distributionTempVariable$var38$6 = index$sample39$4;
										double cv$probabilitySample39Value5 = (1.0 * distribution$sample39[index$sample39$4]);
										if((0 == i$var174)) {
											double traceTempVariable$var181$9_1 = cv$currentValue;
											if((var93 == st[i$var174])) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var174)) {
															for(int var145 = 0; var145 < noStates; var145 += 1) {
																if((var145 == st[i$var174])) {
																	{
																		{
																			double cv$temp$8$var181;
																			{
																				double var181 = traceTempVariable$var181$9_1;
																				cv$temp$8$var181 = var181;
																			}
																			double cv$temp$9$var182;
																			{
																				double var182 = memVar[st[i$var174]];
																				cv$temp$9$var182 = var182;
																			}
																			if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$8$var181) / Math.sqrt(cv$temp$9$var182))) - (0.5 * Math.log(cv$temp$9$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample39$34 = 0; index$sample39$34 < noStates; index$sample39$34 += 1) {
																int distributionTempVariable$var38$36 = index$sample39$34;
																double cv$probabilitySample39Value35 = (cv$probabilitySample39Value5 * distribution$sample39[index$sample39$34]);
																if((0 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$10$var181;
																					{
																						double var181 = traceTempVariable$var181$9_1;
																						cv$temp$10$var181 = var181;
																					}
																					double cv$temp$11$var182;
																					{
																						double var182 = memVar[st[i$var174]];
																						cv$temp$11$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value35) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$10$var181) / Math.sqrt(cv$temp$11$var182))) - (0.5 * Math.log(cv$temp$11$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value35);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample57) {
															for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
																if((i$var50 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$12$var181;
																					{
																						double var181 = traceTempVariable$var181$9_1;
																						cv$temp$12$var181 = var181;
																					}
																					double cv$temp$13$var182;
																					{
																						double var182 = memVar[st[i$var174]];
																						cv$temp$13$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value5) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$12$var181) / Math.sqrt(cv$temp$13$var182))) - (0.5 * Math.log(cv$temp$13$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample39Value5);
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
																		if((i$var50 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$14$var181;
																							{
																								double var181 = traceTempVariable$var181$9_1;
																								cv$temp$14$var181 = var181;
																							}
																							double cv$temp$15$var182;
																							{
																								double var182 = memVar[st[i$var174]];
																								cv$temp$15$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value43) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$14$var181) / Math.sqrt(cv$temp$15$var182))) - (0.5 * Math.log(cv$temp$15$var182)))));
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
						for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
							if(fixedFlag$sample57) {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var174)) {
										double traceTempVariable$var181$17_1 = cv$currentValue;
										if((var93 == st[i$var174])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample39) {
														if((0 == i$var174)) {
															for(int var145 = 0; var145 < noStates; var145 += 1) {
																if((var145 == st[i$var174])) {
																	{
																		{
																			double cv$temp$16$var181;
																			{
																				double var181 = traceTempVariable$var181$17_1;
																				cv$temp$16$var181 = var181;
																			}
																			double cv$temp$17$var182;
																			{
																				double var182 = memVar[st[i$var174]];
																				cv$temp$17$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$16$var181) / Math.sqrt(cv$temp$17$var182))) - (0.5 * Math.log(cv$temp$17$var182)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$18$var181;
																					{
																						double var181 = traceTempVariable$var181$17_1;
																						cv$temp$18$var181 = var181;
																					}
																					double cv$temp$19$var182;
																					{
																						double var182 = memVar[st[i$var174]];
																						cv$temp$19$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value50) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$18$var181) / Math.sqrt(cv$temp$19$var182))) - (0.5 * Math.log(cv$temp$19$var182)))));
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
													for(int index$i$55_1 = 1; index$i$55_1 < samples; index$i$55_1 += 1) {
														if((index$i$55_1 == i$var174)) {
															for(int var145 = 0; var145 < noStates; var145 += 1) {
																if((var145 == st[i$var174])) {
																	{
																		{
																			double cv$temp$20$var181;
																			{
																				double var181 = traceTempVariable$var181$17_1;
																				cv$temp$20$var181 = var181;
																			}
																			double cv$temp$21$var182;
																			{
																				double var182 = memVar[st[i$var174]];
																				cv$temp$21$var182 = var182;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$20$var181) / Math.sqrt(cv$temp$21$var182))) - (0.5 * Math.log(cv$temp$21$var182)))));
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
							} else {
								for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
											int distributionTempVariable$var56$15 = index$sample57$13;
											double cv$probabilitySample57Value14 = (1.0 * distribution$sample57[((i$var50 - 1) / 1)][index$sample57$13]);
											if((i$var50 == i$var174)) {
												double traceTempVariable$var181$18_1 = cv$currentValue;
												if((var93 == st[i$var174])) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample39) {
																if((0 == i$var174)) {
																	for(int var145 = 0; var145 < noStates; var145 += 1) {
																		if((var145 == st[i$var174])) {
																			{
																				{
																					double cv$temp$22$var181;
																					{
																						double var181 = traceTempVariable$var181$18_1;
																						cv$temp$22$var181 = var181;
																					}
																					double cv$temp$23$var182;
																					{
																						double var182 = memVar[st[i$var174]];
																						cv$temp$23$var182 = var182;
																					}
																					if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$22$var181) / Math.sqrt(cv$temp$23$var182))) - (0.5 * Math.log(cv$temp$23$var182)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((0 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$24$var181;
																							{
																								double var181 = traceTempVariable$var181$18_1;
																								cv$temp$24$var181 = var181;
																							}
																							double cv$temp$25$var182;
																							{
																								double var182 = memVar[st[i$var174]];
																								cv$temp$25$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))))) + 1)) + (Math.log(cv$probabilitySample39Value59) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$24$var181) / Math.sqrt(cv$temp$25$var182))) - (0.5 * Math.log(cv$temp$25$var182)))));
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
															if((i$var50 == i$var174)) {
																for(int var145 = 0; var145 < noStates; var145 += 1) {
																	if((var145 == st[i$var174])) {
																		{
																			{
																				double cv$temp$26$var181;
																				{
																					double var181 = traceTempVariable$var181$18_1;
																					cv$temp$26$var181 = var181;
																				}
																				double cv$temp$27$var182;
																				{
																					double var182 = memVar[st[i$var174]];
																					cv$temp$27$var182 = var182;
																				}
																				if(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$26$var181) / Math.sqrt(cv$temp$27$var182))) - (0.5 * Math.log(cv$temp$27$var182)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value14);
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
																		if((index$i$65 == i$var174)) {
																			for(int var145 = 0; var145 < noStates; var145 += 1) {
																				if((var145 == st[i$var174])) {
																					{
																						{
																							double cv$temp$28$var181;
																							{
																								double var181 = traceTempVariable$var181$18_1;
																								cv$temp$28$var181 = var181;
																							}
																							double cv$temp$29$var182;
																							{
																								double var182 = memVar[st[i$var174]];
																								cv$temp$29$var182 = var182;
																							}
																							if(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))))) + 1)) + (Math.log(cv$probabilitySample57Value67) + (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$temp$28$var181) / Math.sqrt(cv$temp$29$var182))) - (0.5 * Math.log(cv$temp$29$var182)))));
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
			double var94 = cv$originalValue;
			{
				{
					memMean[var93] = var94;
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
			logProbability$var55 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample57 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var178 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample180 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var183 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample185 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var188 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
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
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		double[] cv$distribution$sample39 = distribution$sample39;
		for(int index$var37 = 0; index$var37 < noStates; index$var37 += 1) {
			double cv$value = (((0.0 <= index$var37) && (index$var37 < noStates))?initialStateDistribution[index$var37]:0.0);
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
				if((0 == (i$var50 - 1))) {
					for(int var29 = 0; var29 < noStates; var29 += 1) {
						if((var29 == st[(i$var50 - 1)])) {
							{
								if(!fixedFlag$sample57) {
									double[] var54 = m[st[(i$var50 - 1)]];
									for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
										cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (1.0 * (((0.0 <= index$var55) && (index$var55 < noStates))?var54[index$var55]:0.0)));
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
						if((0 == (i$var50 - 1))) {
							for(int var29 = 0; var29 < noStates; var29 += 1) {
								if((var29 == st[(i$var50 - 1)])) {
									{
										if(!fixedFlag$sample57) {
											double[] var54 = m[st[(i$var50 - 1)]];
											for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
												cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * (((0.0 <= index$var55) && (index$var55 < noStates))?var54[index$var55]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample57) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var50 - 1))) {
						for(int var29 = 0; var29 < noStates; var29 += 1) {
							if((var29 == st[(i$var50 - 1)])) {
								{
									if(!fixedFlag$sample57) {
										double[] var54 = m[st[(i$var50 - 1)]];
										for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
											cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (1.0 * (((0.0 <= index$var55) && (index$var55 < noStates))?var54[index$var55]:0.0)));
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
							if((index$i$9 == (i$var50 - 1))) {
								for(int var29 = 0; var29 < noStates; var29 += 1) {
									if((var29 == st[(i$var50 - 1)])) {
										{
											if(!fixedFlag$sample57) {
												double[] var54 = m[st[(i$var50 - 1)]];
												for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
													cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * (((0.0 <= index$var55) && (index$var55 < noStates))?var54[index$var55]:0.0)));
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

	@Override
	public final void initializeConstants() {
		for(int var15 = 0; var15 < noStates; var15 += 1)
			v[var15] = 0.1;
		samples = length$cpu_measured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var37 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var38 = 0.0;
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
			logProbability$var55[((i$var50 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				logProbability$sample57[((i$var50 - 1) / 1)] = 0.0;
		}
		logProbability$var64 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var76 = 0.0;
		logProbability$var82 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var94 = 0.0;
		logProbability$var100 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample113)
			logProbability$var112 = 0.0;
		logProbability$var117 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample130)
			logProbability$var129 = 0.0;
		logProbability$var134 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample147)
			logProbability$var146 = 0.0;
		logProbability$var151 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample164)
			logProbability$var163 = 0.0;
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
			logProbability$var178[((i$var174 - 0) / 1)] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample180) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample180[((i$var174 - 0) / 1)] = 0.0;
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
			logProbability$var183[((i$var174 - 0) / 1)] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample185[((i$var174 - 0) / 1)] = 0.0;
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
			logProbability$var188[((i$var174 - 0) / 1)] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample190[((i$var174 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
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
	public final void logProbabilityGeneration() {
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
		logModelProbabilitiesVal();
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