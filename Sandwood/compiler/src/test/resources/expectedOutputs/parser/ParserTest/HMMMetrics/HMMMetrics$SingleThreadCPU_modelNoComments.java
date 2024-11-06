package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var39;
	private double[] cv$var22$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var30$stateProbabilityGlobal;
	private double[] cv$var40$stateProbabilityGlobal;
	private double[] distribution$sample34;
	private double[][] distribution$sample44;
	private boolean fixedFlag$sample109 = false;
	private boolean fixedFlag$sample118 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample128 = false;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedFlag$sample99 = false;
	private boolean fixedProbFlag$sample109 = false;
	private boolean fixedProbFlag$sample118 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample128 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean fixedProbFlag$sample89 = false;
	private boolean fixedProbFlag$sample99 = false;
	private boolean[] guard$sample34gaussian117$global;
	private boolean[] guard$sample34gaussian122$global;
	private boolean[] guard$sample34gaussian127$global;
	private boolean[] guard$sample44gaussian117$global;
	private boolean[] guard$sample44gaussian122$global;
	private boolean[] guard$sample44gaussian127$global;
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
	private double[] logProbability$sample118;
	private double[] logProbability$sample123;
	private double[] logProbability$sample128;
	private double[] logProbability$sample44;
	private double logProbability$st;
	private double logProbability$var100;
	private double logProbability$var105;
	private double[] logProbability$var113;
	private double[] logProbability$var118;
	private double[] logProbability$var123;
	private double logProbability$var17;
	private double logProbability$var22;
	private double logProbability$var26;
	private double logProbability$var29;
	private double logProbability$var30;
	private double[] logProbability$var39;
	private double logProbability$var48;
	private double logProbability$var53;
	private double logProbability$var59;
	private double logProbability$var64;
	private double logProbability$var70;
	private double logProbability$var75;
	private double logProbability$var80;
	private double logProbability$var85;
	private double logProbability$var90;
	private double logProbability$var95;
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
	private boolean setFlag$cpu = false;
	private boolean setFlag$cpuMean = false;
	private boolean setFlag$cpuVar = false;
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$mem = false;
	private boolean setFlag$memMean = false;
	private boolean setFlag$memVar = false;
	private boolean setFlag$pageFaults = false;
	private boolean setFlag$pageFaultsMean = false;
	private boolean setFlag$pageFaultsVar = false;
	private boolean setFlag$st = false;
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
	public final void set$cpu(double[] cv$value) {
		cpu = cv$value;
		setFlag$cpu = true;
	}

	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	@Override
	public final void set$cpuMean(double[] cv$value) {
		cpuMean = cv$value;
		setFlag$cpuMean = true;
	}

	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	@Override
	public final void set$cpuVar(double[] cv$value) {
		cpuVar = cv$value;
		setFlag$cpuVar = true;
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
	public final boolean get$fixedFlag$sample109() {
		return fixedFlag$sample109;
	}

	@Override
	public final void set$fixedFlag$sample109(boolean cv$value) {
		fixedFlag$sample109 = cv$value;
		fixedProbFlag$sample109 = (fixedFlag$sample109 && fixedProbFlag$sample109);
		fixedProbFlag$sample128 = (fixedFlag$sample109 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample118() {
		return fixedFlag$sample118;
	}

	@Override
	public final void set$fixedFlag$sample118(boolean cv$value) {
		fixedFlag$sample118 = cv$value;
		fixedProbFlag$sample118 = (fixedFlag$sample118 && fixedProbFlag$sample118);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (fixedFlag$sample123 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample128() {
		return fixedFlag$sample128;
	}

	@Override
	public final void set$fixedFlag$sample128(boolean cv$value) {
		fixedFlag$sample128 = cv$value;
		fixedProbFlag$sample128 = (fixedFlag$sample128 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		fixedFlag$sample25 = cv$value;
		fixedProbFlag$sample25 = (fixedFlag$sample25 && fixedProbFlag$sample25);
		fixedProbFlag$sample44 = (fixedFlag$sample25 && fixedProbFlag$sample44);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample34 = (fixedFlag$sample31 && fixedProbFlag$sample34);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample44 = (fixedFlag$sample34 && fixedProbFlag$sample44);
		fixedProbFlag$sample118 = (fixedFlag$sample34 && fixedProbFlag$sample118);
		fixedProbFlag$sample123 = (fixedFlag$sample34 && fixedProbFlag$sample123);
		fixedProbFlag$sample128 = (fixedFlag$sample34 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedProbFlag$sample44);
		fixedProbFlag$sample118 = (fixedFlag$sample44 && fixedProbFlag$sample118);
		fixedProbFlag$sample123 = (fixedFlag$sample44 && fixedProbFlag$sample123);
		fixedProbFlag$sample128 = (fixedFlag$sample44 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample118 = (fixedFlag$sample57 && fixedProbFlag$sample118);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		fixedProbFlag$sample123 = (fixedFlag$sample68 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		fixedProbFlag$sample128 = (fixedFlag$sample79 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	@Override
	public final void set$fixedFlag$sample89(boolean cv$value) {
		fixedFlag$sample89 = cv$value;
		fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedProbFlag$sample89);
		fixedProbFlag$sample118 = (fixedFlag$sample89 && fixedProbFlag$sample118);
	}

	@Override
	public final boolean get$fixedFlag$sample99() {
		return fixedFlag$sample99;
	}

	@Override
	public final void set$fixedFlag$sample99(boolean cv$value) {
		fixedFlag$sample99 = cv$value;
		fixedProbFlag$sample99 = (fixedFlag$sample99 && fixedProbFlag$sample99);
		fixedProbFlag$sample123 = (fixedFlag$sample99 && fixedProbFlag$sample123);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
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
		setFlag$m = true;
	}

	@Override
	public final double[] get$mem() {
		return mem;
	}

	@Override
	public final void set$mem(double[] cv$value) {
		mem = cv$value;
		setFlag$mem = true;
	}

	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	@Override
	public final void set$memMean(double[] cv$value) {
		memMean = cv$value;
		setFlag$memMean = true;
	}

	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	@Override
	public final void set$memVar(double[] cv$value) {
		memVar = cv$value;
		setFlag$memVar = true;
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
	public final void set$pageFaults(double[] cv$value) {
		pageFaults = cv$value;
		setFlag$pageFaults = true;
	}

	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
	}

	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		pageFaultsVar = cv$value;
		setFlag$pageFaultsVar = true;
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
		setFlag$st = true;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample118() {
		if(!fixedProbFlag$sample118) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var109];
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var52 = 0; var52 < noStates; var52 += 1) {
								if((var52 == st[i$var109])) {
									if((0 == i$var109)) {
										for(int var84 = 0; var84 < noStates; var84 += 1) {
											if((var84 == st[i$var109])) {
												{
													double var111 = cpuMean[st[i$var109]];
													double var112 = cpuVar[st[i$var109]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
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
							for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample34$3;
								double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
								if((0 == i$var109)) {
									for(int var52 = 0; var52 < noStates; var52 += 1) {
										if((var52 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var30$5;
											if((0 == i$var109)) {
												for(int var84 = 0; var84 < noStates; var84 += 1) {
													if((var84 == traceTempVariable$s$10_1)) {
														{
															double var111 = cpuMean[traceTempVariable$s$10_1];
															double var112 = cpuVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample34$11 = 0; index$sample34$11 < noStates; index$sample34$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample34$11;
													double cv$probabilitySample34Value12 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$14_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$14_1];
																	double var112 = cpuVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var52 = 0; var52 < noStates; var52 += 1) {
								if((var52 == st[i$var109])) {
									if(fixedFlag$sample44) {
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if((i$var34 == i$var109)) {
												for(int var84 = 0; var84 < noStates; var84 += 1) {
													if((var84 == st[i$var109])) {
														{
															double var111 = cpuMean[st[i$var109]];
															double var112 = cpuVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
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
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if(true) {
												for(int index$sample44$27 = 0; index$sample44$27 < noStates; index$sample44$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample44$27;
													double cv$probabilitySample44Value28 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$30_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$30_1];
																	double var112 = cpuVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value28);
																}
															}
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
							for(int index$sample34$19 = 0; index$sample34$19 < noStates; index$sample34$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample34$19;
								double cv$probabilitySample34Value20 = (1.0 * distribution$sample34[index$sample34$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var52 = 0; var52 < noStates; var52 += 1) {
										if((var52 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$22_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$22_1];
																	double var112 = cpuVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$33 = 0; index$sample44$33 < noStates; index$sample44$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample44$33;
															double cv$probabilitySample44Value34 = (cv$probabilitySample34Value20 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$36_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$36_1];
																			double var112 = cpuVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var52 = 0; var52 < noStates; var52 += 1) {
									if((var52 == st[i$var109])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var109)) {
												for(int var84 = 0; var84 < noStates; var84 += 1) {
													if((var84 == st[i$var109])) {
														{
															double var111 = cpuMean[st[i$var109]];
															double var112 = cpuVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample44$43;
									double cv$probabilitySample44Value44 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var40$45;
									if((i$var34 == i$var109)) {
										for(int var52 = 0; var52 < noStates; var52 += 1) {
											if((var52 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var40$45;
												if((i$var34 == i$var109)) {
													for(int var84 = 0; var84 < noStates; var84 += 1) {
														if((var84 == traceTempVariable$s$50_1)) {
															{
																double var111 = cpuMean[traceTempVariable$s$50_1];
																double var112 = cpuVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample44$52 = 0; index$sample44$52 < noStates; index$sample44$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample44$52;
															double cv$probabilitySample44Value53 = (cv$probabilitySample44Value44 * distribution$sample44[((index$i$51 - 1) / 1)][index$sample44$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$55_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$55_1];
																			double var112 = cpuVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var52 = 0; var52 < noStates; var52 += 1) {
									if((var52 == st[i$var109])) {
										if(fixedFlag$sample34) {
											if((0 == i$var109)) {
												for(int var84 = 0; var84 < noStates; var84 += 1) {
													if((var84 == st[i$var109])) {
														{
															double var111 = cpuMean[st[i$var109]];
															double var112 = cpuVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
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
												for(int index$sample34$68 = 0; index$sample34$68 < noStates; index$sample34$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample34$68;
													double cv$probabilitySample34Value69 = (1.0 * distribution$sample34[index$sample34$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$71_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$71_1];
																	double var112 = cpuVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value69);
																}
															}
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$61 = 0; index$sample44$61 < noStates; index$sample44$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample44$61;
									double cv$probabilitySample44Value62 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var52 = 0; var52 < noStates; var52 += 1) {
											if((var52 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample34) {
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$64_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$64_1];
																	double var112 = cpuVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample34$73 = 0; index$sample34$73 < noStates; index$sample34$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample34$73;
															double cv$probabilitySample34Value74 = (cv$probabilitySample44Value62 * distribution$sample34[index$sample34$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$76_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$76_1];
																			double var112 = cpuVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample34Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var113[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample118[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample118 = ((((fixedFlag$sample118 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample57) && fixedFlag$sample89);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample118[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var109];
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var63 = 0; var63 < noStates; var63 += 1) {
								if((var63 == st[i$var109])) {
									if((0 == i$var109)) {
										for(int var94 = 0; var94 < noStates; var94 += 1) {
											if((var94 == st[i$var109])) {
												{
													double var116 = memMean[st[i$var109]];
													double var117 = memVar[st[i$var109]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
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
							for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample34$3;
								double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
								if((0 == i$var109)) {
									for(int var63 = 0; var63 < noStates; var63 += 1) {
										if((var63 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var30$5;
											if((0 == i$var109)) {
												for(int var94 = 0; var94 < noStates; var94 += 1) {
													if((var94 == traceTempVariable$s$10_1)) {
														{
															double var116 = memMean[traceTempVariable$s$10_1];
															double var117 = memVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample34$11 = 0; index$sample34$11 < noStates; index$sample34$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample34$11;
													double cv$probabilitySample34Value12 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$14_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$14_1];
																	double var117 = memVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var63 = 0; var63 < noStates; var63 += 1) {
								if((var63 == st[i$var109])) {
									if(fixedFlag$sample44) {
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if((i$var34 == i$var109)) {
												for(int var94 = 0; var94 < noStates; var94 += 1) {
													if((var94 == st[i$var109])) {
														{
															double var116 = memMean[st[i$var109]];
															double var117 = memVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
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
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if(true) {
												for(int index$sample44$27 = 0; index$sample44$27 < noStates; index$sample44$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample44$27;
													double cv$probabilitySample44Value28 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$30_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$30_1];
																	double var117 = memVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value28);
																}
															}
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
							for(int index$sample34$19 = 0; index$sample34$19 < noStates; index$sample34$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample34$19;
								double cv$probabilitySample34Value20 = (1.0 * distribution$sample34[index$sample34$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var63 = 0; var63 < noStates; var63 += 1) {
										if((var63 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$22_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$22_1];
																	double var117 = memVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$33 = 0; index$sample44$33 < noStates; index$sample44$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample44$33;
															double cv$probabilitySample44Value34 = (cv$probabilitySample34Value20 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$36_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$36_1];
																			double var117 = memVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var63 = 0; var63 < noStates; var63 += 1) {
									if((var63 == st[i$var109])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var109)) {
												for(int var94 = 0; var94 < noStates; var94 += 1) {
													if((var94 == st[i$var109])) {
														{
															double var116 = memMean[st[i$var109]];
															double var117 = memVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample44$43;
									double cv$probabilitySample44Value44 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var40$45;
									if((i$var34 == i$var109)) {
										for(int var63 = 0; var63 < noStates; var63 += 1) {
											if((var63 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var40$45;
												if((i$var34 == i$var109)) {
													for(int var94 = 0; var94 < noStates; var94 += 1) {
														if((var94 == traceTempVariable$s$50_1)) {
															{
																double var116 = memMean[traceTempVariable$s$50_1];
																double var117 = memVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample44$52 = 0; index$sample44$52 < noStates; index$sample44$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample44$52;
															double cv$probabilitySample44Value53 = (cv$probabilitySample44Value44 * distribution$sample44[((index$i$51 - 1) / 1)][index$sample44$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$55_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$55_1];
																			double var117 = memVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var63 = 0; var63 < noStates; var63 += 1) {
									if((var63 == st[i$var109])) {
										if(fixedFlag$sample34) {
											if((0 == i$var109)) {
												for(int var94 = 0; var94 < noStates; var94 += 1) {
													if((var94 == st[i$var109])) {
														{
															double var116 = memMean[st[i$var109]];
															double var117 = memVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
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
												for(int index$sample34$68 = 0; index$sample34$68 < noStates; index$sample34$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample34$68;
													double cv$probabilitySample34Value69 = (1.0 * distribution$sample34[index$sample34$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$71_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$71_1];
																	double var117 = memVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value69);
																}
															}
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$61 = 0; index$sample44$61 < noStates; index$sample44$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample44$61;
									double cv$probabilitySample44Value62 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var63 = 0; var63 < noStates; var63 += 1) {
											if((var63 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample34) {
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$64_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$64_1];
																	double var117 = memVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample34$73 = 0; index$sample34$73 < noStates; index$sample34$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample34$73;
															double cv$probabilitySample34Value74 = (cv$probabilitySample44Value62 * distribution$sample34[index$sample34$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$76_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$76_1];
																			double var117 = memVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample34Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var118[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample123[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = ((((fixedFlag$sample123 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample68) && fixedFlag$sample99);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample123[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample128() {
		if(!fixedProbFlag$sample128) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var109];
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var74 = 0; var74 < noStates; var74 += 1) {
								if((var74 == st[i$var109])) {
									if((0 == i$var109)) {
										for(int var104 = 0; var104 < noStates; var104 += 1) {
											if((var104 == st[i$var109])) {
												{
													double var121 = pageFaultsMean[st[i$var109]];
													double var122 = pageFaultsVar[st[i$var109]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
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
							for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample34$3;
								double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
								if((0 == i$var109)) {
									for(int var74 = 0; var74 < noStates; var74 += 1) {
										if((var74 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var30$5;
											if((0 == i$var109)) {
												for(int var104 = 0; var104 < noStates; var104 += 1) {
													if((var104 == traceTempVariable$s$10_1)) {
														{
															double var121 = pageFaultsMean[traceTempVariable$s$10_1];
															double var122 = pageFaultsVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample34$11 = 0; index$sample34$11 < noStates; index$sample34$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample34$11;
													double cv$probabilitySample34Value12 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$14_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$14_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample34) {
						if((0 == i$var109)) {
							for(int var74 = 0; var74 < noStates; var74 += 1) {
								if((var74 == st[i$var109])) {
									if(fixedFlag$sample44) {
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if((i$var34 == i$var109)) {
												for(int var104 = 0; var104 < noStates; var104 += 1) {
													if((var104 == st[i$var109])) {
														{
															double var121 = pageFaultsMean[st[i$var109]];
															double var122 = pageFaultsVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
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
										for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
											if(true) {
												for(int index$sample44$27 = 0; index$sample44$27 < noStates; index$sample44$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample44$27;
													double cv$probabilitySample44Value28 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$30_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$30_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value28);
																}
															}
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
							for(int index$sample34$19 = 0; index$sample34$19 < noStates; index$sample34$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample34$19;
								double cv$probabilitySample34Value20 = (1.0 * distribution$sample34[index$sample34$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var74 = 0; var74 < noStates; var74 += 1) {
										if((var74 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$22_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$22_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$33 = 0; index$sample44$33 < noStates; index$sample44$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample44$33;
															double cv$probabilitySample44Value34 = (cv$probabilitySample34Value20 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$36_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$36_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var74 = 0; var74 < noStates; var74 += 1) {
									if((var74 == st[i$var109])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var109)) {
												for(int var104 = 0; var104 < noStates; var104 += 1) {
													if((var104 == st[i$var109])) {
														{
															double var121 = pageFaultsMean[st[i$var109]];
															double var122 = pageFaultsVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample44$43;
									double cv$probabilitySample44Value44 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var40$45;
									if((i$var34 == i$var109)) {
										for(int var74 = 0; var74 < noStates; var74 += 1) {
											if((var74 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var40$45;
												if((i$var34 == i$var109)) {
													for(int var104 = 0; var104 < noStates; var104 += 1) {
														if((var104 == traceTempVariable$s$50_1)) {
															{
																double var121 = pageFaultsMean[traceTempVariable$s$50_1];
																double var122 = pageFaultsVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample44$52 = 0; index$sample44$52 < noStates; index$sample44$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample44$52;
															double cv$probabilitySample44Value53 = (cv$probabilitySample44Value44 * distribution$sample44[((index$i$51 - 1) / 1)][index$sample44$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$55_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$55_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample44Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample44) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var74 = 0; var74 < noStates; var74 += 1) {
									if((var74 == st[i$var109])) {
										if(fixedFlag$sample34) {
											if((0 == i$var109)) {
												for(int var104 = 0; var104 < noStates; var104 += 1) {
													if((var104 == st[i$var109])) {
														{
															double var121 = pageFaultsMean[st[i$var109]];
															double var122 = pageFaultsVar[st[i$var109]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
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
												for(int index$sample34$68 = 0; index$sample34$68 < noStates; index$sample34$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample34$68;
													double cv$probabilitySample34Value69 = (1.0 * distribution$sample34[index$sample34$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$71_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$71_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample34Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value69);
																}
															}
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
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if(true) {
								for(int index$sample44$61 = 0; index$sample44$61 < noStates; index$sample44$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample44$61;
									double cv$probabilitySample44Value62 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var74 = 0; var74 < noStates; var74 += 1) {
											if((var74 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample34) {
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$64_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$64_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample44Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample34$73 = 0; index$sample34$73 < noStates; index$sample34$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample34$73;
															double cv$probabilitySample34Value74 = (cv$probabilitySample44Value62 * distribution$sample34[index$sample34$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$76_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$76_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample34Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var123[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample128[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample128 = ((((fixedFlag$sample128 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample79) && fixedFlag$sample109);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample128[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var123[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample34() {
		if(!fixedProbFlag$sample34) {
			if(fixedFlag$sample34) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var29 = cv$sampleAccumulator;
				logProbability$var30 = cv$sampleProbability;
				if(fixedFlag$sample34)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample34)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedFlag$sample31);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var29 = cv$rvAccumulator;
			if(fixedFlag$sample34)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample44() {
		if(!fixedProbFlag$sample44) {
			if(fixedFlag$sample44) {
				double cv$accumulator = 0.0;
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var34;
					{
						int cv$sampleValue = st[i$var34];
						if(fixedFlag$sample34) {
							if((0 == (i$var34 - 1))) {
								for(int var21 = 0; var21 < noStates; var21 += 1) {
									if((var21 == st[(i$var34 - 1)])) {
										{
											double[] var38 = m[st[(i$var34 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample34$4 = 0; index$sample34$4 < noStates; index$sample34$4 += 1) {
									int distributionTempVariable$var30$6 = index$sample34$4;
									double cv$probabilitySample34Value5 = (1.0 * distribution$sample34[index$sample34$4]);
									int traceTempVariable$var37$7_1 = distributionTempVariable$var30$6;
									if((0 == (i$var34 - 1))) {
										for(int var21 = 0; var21 < noStates; var21 += 1) {
											if((var21 == traceTempVariable$var37$7_1)) {
												{
													double[] var38 = m[traceTempVariable$var37$7_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample34Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value5);
												}
											}
										}
									}
								}
							}
						}
						int traceTempVariable$var37$10_1 = DistributionSampling.sampleCategorical(RNG$, m[st[(index$i$1 - 1)]]);
						if((index$i$1 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$10_1)) {
									{
										double[] var38 = m[traceTempVariable$var37$10_1];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample44) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var34 - 1))) {
									for(int var21 = 0; var21 < noStates; var21 += 1) {
										if((var21 == st[(i$var34 - 1)])) {
											{
												double[] var38 = m[st[(i$var34 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample44$13 = 0; index$sample44$13 < noStates; index$sample44$13 += 1) {
										int distributionTempVariable$var40$15 = index$sample44$13;
										double cv$probabilitySample44Value14 = (1.0 * distribution$sample44[((index$i$12 - 1) / 1)][index$sample44$13]);
										int traceTempVariable$var37$16_1 = distributionTempVariable$var40$15;
										if((index$i$12 == (i$var34 - 1))) {
											for(int var21 = 0; var21 < noStates; var21 += 1) {
												if((var21 == traceTempVariable$var37$16_1)) {
													{
														double[] var38 = m[traceTempVariable$var37$16_1];
														double cv$weightedProbability = (Math.log(cv$probabilitySample44Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value14);
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
					logProbability$var39[((i$var34 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample44[((i$var34 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample44)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample44)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample44 = ((fixedFlag$sample44 && fixedFlag$sample25) && fixedFlag$sample34);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample44[((i$var34 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[((i$var34 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample44)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample109() {
		if(!fixedProbFlag$sample109) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var104 = 0; var104 < noStates; var104 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaultsVar[var104];
					{
						{
							double var99 = 5.0;
							double var98 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var99, var98));
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
			logProbability$var105 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample109)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample109 = fixedFlag$sample109;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var105;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var100 = cv$rvAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample109)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample118() {
		if(!fixedProbFlag$sample118) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var109];
					{
						{
							double var111 = cpuMean[st[i$var109]];
							double var112 = cpuVar[st[i$var109]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
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
				logProbability$var113[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample118[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample118 = ((((fixedFlag$sample118 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample57) && fixedFlag$sample89);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample118[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var109];
					{
						{
							double var116 = memMean[st[i$var109]];
							double var117 = memVar[st[i$var109]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
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
				logProbability$var118[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample123[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = ((((fixedFlag$sample123 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample68) && fixedFlag$sample99);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample123[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample128() {
		if(!fixedProbFlag$sample128) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var109];
					{
						{
							double var121 = pageFaultsMean[st[i$var109]];
							double var122 = pageFaultsVar[st[i$var109]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
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
				logProbability$var123[((i$var109 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample128[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample128 = ((((fixedFlag$sample128 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample79) && fixedFlag$sample109);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample128[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var123[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample25() {
		if(!fixedProbFlag$sample25) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noStates; var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var21];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
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
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample25 = fixedFlag$sample25;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var17 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = initialStateDistribution;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
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
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var29 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var34;
				{
					int cv$sampleValue = st[i$var34];
					{
						{
							double[] var38 = m[st[(i$var34 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var39[((i$var34 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample44[((i$var34 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = ((fixedFlag$sample44 && fixedFlag$sample25) && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample44[((i$var34 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[((i$var34 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var52 = 0; var52 < noStates; var52 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpuMean[var52];
					{
						{
							double var47 = 16.0;
							double var46 = 8.6;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
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
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var53 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var53;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var63 = 0; var63 < noStates; var63 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = memMean[var63];
					{
						{
							double var57 = 94.0;
							double var58 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var57) / Math.sqrt(var58))) - (0.5 * Math.log(var58))));
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
			logProbability$var59 = cv$sampleAccumulator;
			logProbability$var64 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var64;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var59 = cv$rvAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaultsMean[var74];
					{
						{
							double var68 = 814.0;
							double var69 = 335550.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var68) / Math.sqrt(var69))) - (0.5 * Math.log(var69))));
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
			logProbability$var70 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = fixedFlag$sample79;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var70 = cv$rvAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < noStates; var84 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpuVar[var84];
					{
						{
							double var79 = 5.0;
							double var78 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var79, var78));
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
			logProbability$var80 = cv$sampleAccumulator;
			logProbability$var85 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample89 = fixedFlag$sample89;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var85;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var80 = cv$rvAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample99() {
		if(!fixedProbFlag$sample99) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var94 = 0; var94 < noStates; var94 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = memVar[var94];
					{
						{
							double var89 = 5.0;
							double var88 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var89, var88));
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
			logProbability$var90 = cv$sampleAccumulator;
			logProbability$var95 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample99)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample99 = fixedFlag$sample99;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var95;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var90 = cv$rvAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample99)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample109(int var104) {
		double cv$originalValue = pageFaultsVar[var104];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var105 = cv$proposedValue;
					pageFaultsVar[var104] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var99;
				{
					cv$temp$0$var99 = 5.0;
				}
				double cv$temp$1$var98;
				{
					cv$temp$1$var98 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var99, cv$temp$1$var98));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var122$7_1 = cv$currentValue;
									if((var104 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var74 = 0; var74 < noStates; var74 += 1) {
														if((var74 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var121;
																	{
																		double var121 = pageFaultsMean[st[i$var109]];
																		cv$temp$2$var121 = var121;
																	}
																	double cv$temp$3$var122;
																	{
																		double var122 = traceTempVariable$var122$7_1;
																		cv$temp$3$var122 = var122;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var121;
																			{
																				double var121 = pageFaultsMean[st[i$var109]];
																				cv$temp$4$var121 = var121;
																			}
																			double cv$temp$5$var122;
																			{
																				double var122 = traceTempVariable$var122$7_1;
																				cv$temp$5$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$29_1];
																						cv$temp$6$var121 = var121;
																					}
																					double cv$temp$7$var122;
																					{
																						double var122 = traceTempVariable$var122$7_1;
																						cv$temp$7$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var122$8_1 = cv$currentValue;
											if((var104 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var121;
																			{
																				double var121 = pageFaultsMean[traceTempVariable$s$32_1];
																				cv$temp$8$var121 = var121;
																			}
																			double cv$temp$9$var122;
																			{
																				double var122 = traceTempVariable$var122$8_1;
																				cv$temp$9$var122 = var122;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$36_1];
																						cv$temp$10$var121 = var121;
																					}
																					double cv$temp$11$var122;
																					{
																						double var122 = traceTempVariable$var122$8_1;
																						cv$temp$11$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$6_1];
																						cv$temp$12$var121 = var121;
																					}
																					double cv$temp$13$var122;
																					{
																						double var122 = traceTempVariable$var122$8_1;
																						cv$temp$13$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var74 = 0; var74 < noStates; var74 += 1) {
																				if((var74 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$44_1];
																								cv$temp$14$var121 = var121;
																							}
																							double cv$temp$15$var122;
																							{
																								double var122 = traceTempVariable$var122$8_1;
																								cv$temp$15$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var122$16_1 = cv$currentValue;
										if((var104 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var121;
																			{
																				double var121 = pageFaultsMean[st[i$var109]];
																				cv$temp$16$var121 = var121;
																			}
																			double cv$temp$17$var122;
																			{
																				double var122 = traceTempVariable$var122$16_1;
																				cv$temp$17$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$51_1];
																						cv$temp$18$var121 = var121;
																					}
																					double cv$temp$19$var122;
																					{
																						double var122 = traceTempVariable$var122$16_1;
																						cv$temp$19$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var121;
																			{
																				double var121 = pageFaultsMean[st[i$var109]];
																				cv$temp$20$var121 = var121;
																			}
																			double cv$temp$21$var122;
																			{
																				double var122 = traceTempVariable$var122$16_1;
																				cv$temp$21$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var122$17_1 = cv$currentValue;
												if((var104 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$15_1];
																						cv$temp$22$var121 = var121;
																					}
																					double cv$temp$23$var122;
																					{
																						double var122 = traceTempVariable$var122$17_1;
																						cv$temp$23$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var74 = 0; var74 < noStates; var74 += 1) {
																				if((var74 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$60_1];
																								cv$temp$24$var121 = var121;
																							}
																							double cv$temp$25$var122;
																							{
																								double var122 = traceTempVariable$var122$17_1;
																								cv$temp$25$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$63_1];
																					cv$temp$26$var121 = var121;
																				}
																				double cv$temp$27$var122;
																				{
																					double var122 = traceTempVariable$var122$17_1;
																					cv$temp$27$var122 = var122;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var74 = 0; var74 < noStates; var74 += 1) {
																				if((var74 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$68_1];
																								cv$temp$28$var121 = var121;
																							}
																							double cv$temp$29$var122;
																							{
																								double var122 = traceTempVariable$var122$17_1;
																								cv$temp$29$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var105 = cv$originalValue;
			pageFaultsVar[var104] = var105;
		}
	}

	private final void sample25(int var21) {
		double[] cv$targetLocal = m[var21];
		double[] cv$countLocal = cv$var22$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
						if(fixedFlag$sample34) {
							if((0 == (i$var34 - 1))) {
								if((var21 == st[(i$var34 - 1)])) {
									if(fixedFlag$sample44) {
										{
											int index$i$19 = i$var34;
											{
												{
													{
														{
															cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + 1.0);
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
								for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
									int distributionTempVariable$var30$5 = index$sample34$3;
									double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
									int traceTempVariable$var37$6_1 = distributionTempVariable$var30$5;
									if((0 == (i$var34 - 1))) {
										if((var21 == traceTempVariable$var37$6_1)) {
											if(fixedFlag$sample44) {
												{
													int index$i$21 = i$var34;
													{
														{
															{
																{
																	cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + cv$probabilitySample34Value4);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
						if(fixedFlag$sample44) {
							for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
								if((index$i$10_1 == (i$var34 - 1))) {
									if((var21 == st[(i$var34 - 1)])) {
										if(fixedFlag$sample44) {
											{
												int index$i$23 = i$var34;
												{
													{
														{
															{
																cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + 1.0);
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
									for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
										int distributionTempVariable$var40$14 = index$sample44$12;
										double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((index$i$11 - 1) / 1)][index$sample44$12]);
										int traceTempVariable$var37$15_1 = distributionTempVariable$var40$14;
										if((index$i$11 == (i$var34 - 1))) {
											if((var21 == traceTempVariable$var37$15_1)) {
												if(fixedFlag$sample44) {
													{
														int index$i$25 = i$var34;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + cv$probabilitySample44Value13);
																	}
																}
															}
														}
													}
												}
											}
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
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					if(fixedFlag$sample34) {
						if((0 == (i$var34 - 1))) {
							if((var21 == st[(i$var34 - 1)])) {
								if(!fixedFlag$sample44) {
									{
										int index$i$48 = i$var34;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample44[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample34$32 = 0; index$sample34$32 < noStates; index$sample34$32 += 1) {
								int distributionTempVariable$var30$34 = index$sample34$32;
								double cv$probabilitySample34Value33 = (1.0 * distribution$sample34[index$sample34$32]);
								int traceTempVariable$var37$35_1 = distributionTempVariable$var30$34;
								if((0 == (i$var34 - 1))) {
									if((var21 == traceTempVariable$var37$35_1)) {
										if(!fixedFlag$sample44) {
											{
												int index$i$50 = i$var34;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample34Value33);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample44[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					if(fixedFlag$sample44) {
						for(int index$i$39_1 = 1; index$i$39_1 < samples; index$i$39_1 += 1) {
							if((index$i$39_1 == (i$var34 - 1))) {
								if((var21 == st[(i$var34 - 1)])) {
									if(!fixedFlag$sample44) {
										{
											int index$i$52 = i$var34;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample44[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
									int distributionTempVariable$var40$43 = index$sample44$41;
									double cv$probabilitySample44Value42 = (1.0 * distribution$sample44[((index$i$40 - 1) / 1)][index$sample44$41]);
									int traceTempVariable$var37$44_1 = distributionTempVariable$var40$43;
									if((index$i$40 == (i$var34 - 1))) {
										if((var21 == traceTempVariable$var37$44_1)) {
											if(!fixedFlag$sample44) {
												{
													int index$i$54 = i$var34;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample44Value42);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample44[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample31() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var27$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if(fixedFlag$sample34) {
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
				if(!fixedFlag$sample34) {
					{
						{
							{
								double scopeVariable$reachedSourceProbability = 0.0;
								{
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
								}
								double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
								for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample34[cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample34() {
		double[] cv$stateProbabilityLocal = cv$var30$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$initialStateDistribution.length))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var37$1_1 = cv$currentValue;
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((0 == (i$var34 - 1))) {
								if(fixedFlag$sample44) {
									{
										int index$i$3 = i$var34;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var21 = 0; var21 < noStates; var21 += 1) {
												if((var21 == traceTempVariable$var37$1_1)) {
													{
														{
															double[] cv$temp$1$var38;
															{
																double[] var38 = m[traceTempVariable$var37$1_1];
																cv$temp$1$var38 = var38;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var34]) && (st[i$var34] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[i$var34]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var34]) && (st[i$var34] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[i$var34]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var34]) && (st[i$var34] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[i$var34]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var34]) && (st[i$var34] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[i$var34]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var34]) && (st[i$var34] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[i$var34]]):Double.NEGATIVE_INFINITY)));
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
						boolean[] guard$sample34gaussian117 = guard$sample34gaussian117$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian117[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian117[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$8_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian117[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian117[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var52 = 0; var52 < noStates; var52 += 1) {
												if((var52 == traceTempVariable$s$8_1)) {
													int traceTempVariable$s$13_1 = cv$currentValue;
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$13_1)) {
																{
																	{
																		double cv$temp$2$var111;
																		{
																			double var111 = cpuMean[traceTempVariable$s$13_1];
																			cv$temp$2$var111 = var111;
																		}
																		double cv$temp$3$var112;
																		{
																			double var112 = cpuVar[traceTempVariable$s$13_1];
																			cv$temp$3$var112 = var112;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample34$14 = 0; index$sample34$14 < noStates; index$sample34$14 += 1) {
															int distributionTempVariable$var30$16 = index$sample34$14;
															double cv$probabilitySample34Value15 = (1.0 * distribution$sample34[index$sample34$14]);
															int traceTempVariable$s$17_1 = distributionTempVariable$var30$16;
															if((0 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$17_1)) {
																		{
																			{
																				double cv$temp$4$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$17_1];
																					cv$temp$4$var111 = var111;
																				}
																				double cv$temp$5$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$17_1];
																					cv$temp$5$var112 = var112;
																				}
																				if(((Math.log(cv$probabilitySample34Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value15);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var52 = 0; var52 < noStates; var52 += 1) {
												if((var52 == traceTempVariable$s$8_1)) {
													if(fixedFlag$sample44) {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if((i$var34 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$8_1)) {
																		{
																			{
																				double cv$temp$6$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$8_1];
																					cv$temp$6$var111 = var111;
																				}
																				double cv$temp$7$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$8_1];
																					cv$temp$7$var112 = var112;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if(true) {
																for(int index$sample44$23 = 0; index$sample44$23 < noStates; index$sample44$23 += 1) {
																	int distributionTempVariable$var40$25 = index$sample44$23;
																	double cv$probabilitySample44Value24 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$23]);
																	int traceTempVariable$s$26_1 = distributionTempVariable$var40$25;
																	if((i$var34 == i$var109)) {
																		for(int var84 = 0; var84 < noStates; var84 += 1) {
																			if((var84 == traceTempVariable$s$26_1)) {
																				{
																					{
																						double cv$temp$8$var111;
																						{
																							double var111 = cpuMean[traceTempVariable$s$26_1];
																							cv$temp$8$var111 = var111;
																						}
																						double cv$temp$9$var112;
																						{
																							double var112 = cpuVar[traceTempVariable$s$26_1];
																							cv$temp$9$var112 = var112;
																						}
																						if(((Math.log(cv$probabilitySample44Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value24);
																					}
																				}
																			}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian117[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian117[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$29_1 = cv$currentValue;
											if((0 == i$var109)) {
												for(int var52 = 0; var52 < noStates; var52 += 1) {
													if((var52 == traceTempVariable$s$29_1)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$29_1)) {
																{
																	{
																		double cv$temp$10$var111;
																		{
																			double var111 = cpuMean[traceTempVariable$s$29_1];
																			cv$temp$10$var111 = var111;
																		}
																		double cv$temp$11$var112;
																		{
																			double var112 = cpuVar[traceTempVariable$s$29_1];
																			cv$temp$11$var112 = var112;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
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
												for(int index$sample34$30 = 0; index$sample34$30 < noStates; index$sample34$30 += 1) {
													int distributionTempVariable$var30$32 = index$sample34$30;
													double cv$probabilitySample34Value31 = (1.0 * distribution$sample34[index$sample34$30]);
													int traceTempVariable$s$33_1 = distributionTempVariable$var30$32;
													if((0 == i$var109)) {
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$33_1)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$33_1)) {
																		{
																			{
																				double cv$temp$12$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$33_1];
																					cv$temp$12$var111 = var111;
																				}
																				double cv$temp$13$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$33_1];
																					cv$temp$13$var112 = var112;
																				}
																				if(((Math.log(cv$probabilitySample34Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value31);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$9_1)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$9_1)) {
																		{
																			{
																				double cv$temp$14$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$9_1];
																					cv$temp$14$var111 = var111;
																				}
																				double cv$temp$15$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$9_1];
																					cv$temp$15$var112 = var112;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
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
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$40 = 0; index$sample44$40 < noStates; index$sample44$40 += 1) {
															int distributionTempVariable$var40$42 = index$sample44$40;
															double cv$probabilitySample44Value41 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$40]);
															int traceTempVariable$s$43_1 = distributionTempVariable$var40$42;
															if((i$var34 == i$var109)) {
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$43_1)) {
																		for(int var84 = 0; var84 < noStates; var84 += 1) {
																			if((var84 == traceTempVariable$s$43_1)) {
																				{
																					{
																						double cv$temp$16$var111;
																						{
																							double var111 = cpuMean[traceTempVariable$s$43_1];
																							cv$temp$16$var111 = var111;
																						}
																						double cv$temp$17$var112;
																						{
																							double var112 = cpuVar[traceTempVariable$s$43_1];
																							cv$temp$17$var112 = var112;
																						}
																						if(((Math.log(cv$probabilitySample44Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value41);
																					}
																				}
																			}
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
						boolean[] guard$sample34gaussian122 = guard$sample34gaussian122$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian122[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian122[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$58_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian122[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian122[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var63 = 0; var63 < noStates; var63 += 1) {
												if((var63 == traceTempVariable$s$58_1)) {
													int traceTempVariable$s$63_1 = cv$currentValue;
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$63_1)) {
																{
																	{
																		double cv$temp$18$var116;
																		{
																			double var116 = memMean[traceTempVariable$s$63_1];
																			cv$temp$18$var116 = var116;
																		}
																		double cv$temp$19$var117;
																		{
																			double var117 = memVar[traceTempVariable$s$63_1];
																			cv$temp$19$var117 = var117;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample34$64 = 0; index$sample34$64 < noStates; index$sample34$64 += 1) {
															int distributionTempVariable$var30$66 = index$sample34$64;
															double cv$probabilitySample34Value65 = (1.0 * distribution$sample34[index$sample34$64]);
															int traceTempVariable$s$67_1 = distributionTempVariable$var30$66;
															if((0 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$67_1)) {
																		{
																			{
																				double cv$temp$20$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$67_1];
																					cv$temp$20$var116 = var116;
																				}
																				double cv$temp$21$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$67_1];
																					cv$temp$21$var117 = var117;
																				}
																				if(((Math.log(cv$probabilitySample34Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value65);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var63 = 0; var63 < noStates; var63 += 1) {
												if((var63 == traceTempVariable$s$58_1)) {
													if(fixedFlag$sample44) {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if((i$var34 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$58_1)) {
																		{
																			{
																				double cv$temp$22$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$58_1];
																					cv$temp$22$var116 = var116;
																				}
																				double cv$temp$23$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$58_1];
																					cv$temp$23$var117 = var117;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if(true) {
																for(int index$sample44$73 = 0; index$sample44$73 < noStates; index$sample44$73 += 1) {
																	int distributionTempVariable$var40$75 = index$sample44$73;
																	double cv$probabilitySample44Value74 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$73]);
																	int traceTempVariable$s$76_1 = distributionTempVariable$var40$75;
																	if((i$var34 == i$var109)) {
																		for(int var94 = 0; var94 < noStates; var94 += 1) {
																			if((var94 == traceTempVariable$s$76_1)) {
																				{
																					{
																						double cv$temp$24$var116;
																						{
																							double var116 = memMean[traceTempVariable$s$76_1];
																							cv$temp$24$var116 = var116;
																						}
																						double cv$temp$25$var117;
																						{
																							double var117 = memVar[traceTempVariable$s$76_1];
																							cv$temp$25$var117 = var117;
																						}
																						if(((Math.log(cv$probabilitySample44Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value74);
																					}
																				}
																			}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian122[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian122[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$79_1 = cv$currentValue;
											if((0 == i$var109)) {
												for(int var63 = 0; var63 < noStates; var63 += 1) {
													if((var63 == traceTempVariable$s$79_1)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$79_1)) {
																{
																	{
																		double cv$temp$26$var116;
																		{
																			double var116 = memMean[traceTempVariable$s$79_1];
																			cv$temp$26$var116 = var116;
																		}
																		double cv$temp$27$var117;
																		{
																			double var117 = memVar[traceTempVariable$s$79_1];
																			cv$temp$27$var117 = var117;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))));
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
												for(int index$sample34$80 = 0; index$sample34$80 < noStates; index$sample34$80 += 1) {
													int distributionTempVariable$var30$82 = index$sample34$80;
													double cv$probabilitySample34Value81 = (1.0 * distribution$sample34[index$sample34$80]);
													int traceTempVariable$s$83_1 = distributionTempVariable$var30$82;
													if((0 == i$var109)) {
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$83_1)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$83_1)) {
																		{
																			{
																				double cv$temp$28$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$83_1];
																					cv$temp$28$var116 = var116;
																				}
																				double cv$temp$29$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$83_1];
																					cv$temp$29$var117 = var117;
																				}
																				if(((Math.log(cv$probabilitySample34Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value81);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$59_1)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$59_1)) {
																		{
																			{
																				double cv$temp$30$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$59_1];
																					cv$temp$30$var116 = var116;
																				}
																				double cv$temp$31$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$59_1];
																					cv$temp$31$var117 = var117;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$30$var116) / Math.sqrt(cv$temp$31$var117))) - (0.5 * Math.log(cv$temp$31$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$30$var116) / Math.sqrt(cv$temp$31$var117))) - (0.5 * Math.log(cv$temp$31$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$30$var116) / Math.sqrt(cv$temp$31$var117))) - (0.5 * Math.log(cv$temp$31$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$30$var116) / Math.sqrt(cv$temp$31$var117))) - (0.5 * Math.log(cv$temp$31$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$30$var116) / Math.sqrt(cv$temp$31$var117))) - (0.5 * Math.log(cv$temp$31$var117)))));
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
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$90 = 0; index$sample44$90 < noStates; index$sample44$90 += 1) {
															int distributionTempVariable$var40$92 = index$sample44$90;
															double cv$probabilitySample44Value91 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$90]);
															int traceTempVariable$s$93_1 = distributionTempVariable$var40$92;
															if((i$var34 == i$var109)) {
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$93_1)) {
																		for(int var94 = 0; var94 < noStates; var94 += 1) {
																			if((var94 == traceTempVariable$s$93_1)) {
																				{
																					{
																						double cv$temp$32$var116;
																						{
																							double var116 = memMean[traceTempVariable$s$93_1];
																							cv$temp$32$var116 = var116;
																						}
																						double cv$temp$33$var117;
																						{
																							double var117 = memVar[traceTempVariable$s$93_1];
																							cv$temp$33$var117 = var117;
																						}
																						if(((Math.log(cv$probabilitySample44Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value91);
																					}
																				}
																			}
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
						boolean[] guard$sample34gaussian127 = guard$sample34gaussian127$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian127[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample34gaussian127[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$108_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian127[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian127[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var74 = 0; var74 < noStates; var74 += 1) {
												if((var74 == traceTempVariable$s$108_1)) {
													int traceTempVariable$s$113_1 = cv$currentValue;
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$113_1)) {
																{
																	{
																		double cv$temp$34$var121;
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$113_1];
																			cv$temp$34$var121 = var121;
																		}
																		double cv$temp$35$var122;
																		{
																			double var122 = pageFaultsVar[traceTempVariable$s$113_1];
																			cv$temp$35$var122 = var122;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$34$var121) / Math.sqrt(cv$temp$35$var122))) - (0.5 * Math.log(cv$temp$35$var122)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$34$var121) / Math.sqrt(cv$temp$35$var122))) - (0.5 * Math.log(cv$temp$35$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$34$var121) / Math.sqrt(cv$temp$35$var122))) - (0.5 * Math.log(cv$temp$35$var122))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$34$var121) / Math.sqrt(cv$temp$35$var122))) - (0.5 * Math.log(cv$temp$35$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$34$var121) / Math.sqrt(cv$temp$35$var122))) - (0.5 * Math.log(cv$temp$35$var122)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample34$114 = 0; index$sample34$114 < noStates; index$sample34$114 += 1) {
															int distributionTempVariable$var30$116 = index$sample34$114;
															double cv$probabilitySample34Value115 = (1.0 * distribution$sample34[index$sample34$114]);
															int traceTempVariable$s$117_1 = distributionTempVariable$var30$116;
															if((0 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$117_1)) {
																		{
																			{
																				double cv$temp$36$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$117_1];
																					cv$temp$36$var121 = var121;
																				}
																				double cv$temp$37$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$117_1];
																					cv$temp$37$var122 = var122;
																				}
																				if(((Math.log(cv$probabilitySample34Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value115);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var74 = 0; var74 < noStates; var74 += 1) {
												if((var74 == traceTempVariable$s$108_1)) {
													if(fixedFlag$sample44) {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if((i$var34 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$108_1)) {
																		{
																			{
																				double cv$temp$38$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$108_1];
																					cv$temp$38$var121 = var121;
																				}
																				double cv$temp$39$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$108_1];
																					cv$temp$39$var122 = var122;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$38$var121) / Math.sqrt(cv$temp$39$var122))) - (0.5 * Math.log(cv$temp$39$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$38$var121) / Math.sqrt(cv$temp$39$var122))) - (0.5 * Math.log(cv$temp$39$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$38$var121) / Math.sqrt(cv$temp$39$var122))) - (0.5 * Math.log(cv$temp$39$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$38$var121) / Math.sqrt(cv$temp$39$var122))) - (0.5 * Math.log(cv$temp$39$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$38$var121) / Math.sqrt(cv$temp$39$var122))) - (0.5 * Math.log(cv$temp$39$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
															if(true) {
																for(int index$sample44$123 = 0; index$sample44$123 < noStates; index$sample44$123 += 1) {
																	int distributionTempVariable$var40$125 = index$sample44$123;
																	double cv$probabilitySample44Value124 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$123]);
																	int traceTempVariable$s$126_1 = distributionTempVariable$var40$125;
																	if((i$var34 == i$var109)) {
																		for(int var104 = 0; var104 < noStates; var104 += 1) {
																			if((var104 == traceTempVariable$s$126_1)) {
																				{
																					{
																						double cv$temp$40$var121;
																						{
																							double var121 = pageFaultsMean[traceTempVariable$s$126_1];
																							cv$temp$40$var121 = var121;
																						}
																						double cv$temp$41$var122;
																						{
																							double var122 = pageFaultsVar[traceTempVariable$s$126_1];
																							cv$temp$41$var122 = var122;
																						}
																						if(((Math.log(cv$probabilitySample44Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value124);
																					}
																				}
																			}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample34gaussian127[((i$var109 - 0) / 1)]) {
									guard$sample34gaussian127[((i$var109 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$129_1 = cv$currentValue;
											if((0 == i$var109)) {
												for(int var74 = 0; var74 < noStates; var74 += 1) {
													if((var74 == traceTempVariable$s$129_1)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$129_1)) {
																{
																	{
																		double cv$temp$42$var121;
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$129_1];
																			cv$temp$42$var121 = var121;
																		}
																		double cv$temp$43$var122;
																		{
																			double var122 = pageFaultsVar[traceTempVariable$s$129_1];
																			cv$temp$43$var122 = var122;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$42$var121) / Math.sqrt(cv$temp$43$var122))) - (0.5 * Math.log(cv$temp$43$var122)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$42$var121) / Math.sqrt(cv$temp$43$var122))) - (0.5 * Math.log(cv$temp$43$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$42$var121) / Math.sqrt(cv$temp$43$var122))) - (0.5 * Math.log(cv$temp$43$var122))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$42$var121) / Math.sqrt(cv$temp$43$var122))) - (0.5 * Math.log(cv$temp$43$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$42$var121) / Math.sqrt(cv$temp$43$var122))) - (0.5 * Math.log(cv$temp$43$var122)))));
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
												for(int index$sample34$130 = 0; index$sample34$130 < noStates; index$sample34$130 += 1) {
													int distributionTempVariable$var30$132 = index$sample34$130;
													double cv$probabilitySample34Value131 = (1.0 * distribution$sample34[index$sample34$130]);
													int traceTempVariable$s$133_1 = distributionTempVariable$var30$132;
													if((0 == i$var109)) {
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$133_1)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$133_1)) {
																		{
																			{
																				double cv$temp$44$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$133_1];
																					cv$temp$44$var121 = var121;
																				}
																				double cv$temp$45$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$133_1];
																					cv$temp$45$var122 = var122;
																				}
																				if(((Math.log(cv$probabilitySample34Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value131);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample44) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$109_1)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$109_1)) {
																		{
																			{
																				double cv$temp$46$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$109_1];
																					cv$temp$46$var121 = var121;
																				}
																				double cv$temp$47$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$109_1];
																					cv$temp$47$var122 = var122;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$46$var121) / Math.sqrt(cv$temp$47$var122))) - (0.5 * Math.log(cv$temp$47$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$46$var121) / Math.sqrt(cv$temp$47$var122))) - (0.5 * Math.log(cv$temp$47$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$46$var121) / Math.sqrt(cv$temp$47$var122))) - (0.5 * Math.log(cv$temp$47$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$46$var121) / Math.sqrt(cv$temp$47$var122))) - (0.5 * Math.log(cv$temp$47$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$46$var121) / Math.sqrt(cv$temp$47$var122))) - (0.5 * Math.log(cv$temp$47$var122)))));
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
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample44$140 = 0; index$sample44$140 < noStates; index$sample44$140 += 1) {
															int distributionTempVariable$var40$142 = index$sample44$140;
															double cv$probabilitySample44Value141 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$140]);
															int traceTempVariable$s$143_1 = distributionTempVariable$var40$142;
															if((i$var34 == i$var109)) {
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$143_1)) {
																		for(int var104 = 0; var104 < noStates; var104 += 1) {
																			if((var104 == traceTempVariable$s$143_1)) {
																				{
																					{
																						double cv$temp$48$var121;
																						{
																							double var121 = pageFaultsMean[traceTempVariable$s$143_1];
																							cv$temp$48$var121 = var121;
																						}
																						double cv$temp$49$var122;
																						{
																							double var122 = pageFaultsVar[traceTempVariable$s$143_1];
																							cv$temp$49$var122 = var122;
																						}
																						if(((Math.log(cv$probabilitySample44Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value141);
																					}
																				}
																			}
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
					int traceTempVariable$var37$156_1 = cv$currentValue;
					for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
						if((0 == (i$var34 - 1))) {
							if(!fixedFlag$sample44) {
								{
									int index$i$158 = i$var34;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var39;
									for(int cv$i = 0; cv$i < noStates; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var21 = 0; var21 < noStates; var21 += 1) {
										if((var21 == traceTempVariable$var37$156_1)) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$50$var38;
												{
													double[] var38 = m[traceTempVariable$var37$156_1];
													cv$temp$50$var38 = var38;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$50$var38);
											}
										}
									}
									double[] cv$sampleDistribution = distribution$sample44[((i$var34 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample34;
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample44(int i$var34) {
		double[] cv$stateProbabilityLocal = cv$var40$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$i$1 = i$var34;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample34) {
				if((0 == (i$var34 - 1))) {
					for(int var21 = 0; var21 < noStates; var21 += 1) {
						if((var21 == st[(i$var34 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var38;
							{
								double[] var38 = m[st[(i$var34 - 1)]];
								cv$temp$0$var38 = var38;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var38.length))?Math.log(cv$temp$0$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var37$17_1 = cv$currentValue;
								}
							}
							{
								{
									boolean[] guard$sample44gaussian117 = guard$sample44gaussian117$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$29_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$29_1)) {
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$4$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$29_1];
																						cv$temp$4$var111 = var111;
																					}
																					double cv$temp$5$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$29_1];
																						cv$temp$5$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$29_1)) {
																int traceTempVariable$s$49_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$49_1)) {
																			{
																				{
																					double cv$temp$6$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$49_1];
																						cv$temp$6$var111 = var111;
																					}
																					double cv$temp$7$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$49_1];
																						cv$temp$7$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$50 = 1; index$i$50 < samples; index$i$50 += 1) {
																	if(!(index$i$50 == index$i$1)) {
																		for(int index$sample44$51 = 0; index$sample44$51 < noStates; index$sample44$51 += 1) {
																			int distributionTempVariable$var40$53 = index$sample44$51;
																			double cv$probabilitySample44Value52 = (1.0 * distribution$sample44[((index$i$50 - 1) / 1)][index$sample44$51]);
																			int traceTempVariable$s$54_1 = distributionTempVariable$var40$53;
																			if((index$i$50 == i$var109)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$54_1)) {
																						{
																							{
																								double cv$temp$8$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$54_1];
																									cv$temp$8$var111 = var111;
																								}
																								double cv$temp$9$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$54_1];
																									cv$temp$9$var112 = var112;
																								}
																								if(((Math.log(cv$probabilitySample44Value52) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value52) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value52) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value52) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value52) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value52);
																							}
																						}
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
									int traceTempVariable$s$33_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$33_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$33_1)) {
																			{
																				{
																					double cv$temp$36$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$33_1];
																						cv$temp$36$var111 = var111;
																					}
																					double cv$temp$37$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$33_1];
																						cv$temp$37$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$36$var111) / Math.sqrt(cv$temp$37$var112))) - (0.5 * Math.log(cv$temp$37$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$36$var111) / Math.sqrt(cv$temp$37$var112))) - (0.5 * Math.log(cv$temp$37$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$36$var111) / Math.sqrt(cv$temp$37$var112))) - (0.5 * Math.log(cv$temp$37$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$36$var111) / Math.sqrt(cv$temp$37$var112))) - (0.5 * Math.log(cv$temp$37$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$36$var111) / Math.sqrt(cv$temp$37$var112))) - (0.5 * Math.log(cv$temp$37$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$113_1 = cv$currentValue;
														if((index$i$1 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$113_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$113_1)) {
																			{
																				{
																					double cv$temp$38$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$113_1];
																						cv$temp$38$var111 = var111;
																					}
																					double cv$temp$39$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$113_1];
																						cv$temp$39$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$38$var111) / Math.sqrt(cv$temp$39$var112))) - (0.5 * Math.log(cv$temp$39$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$38$var111) / Math.sqrt(cv$temp$39$var112))) - (0.5 * Math.log(cv$temp$39$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$38$var111) / Math.sqrt(cv$temp$39$var112))) - (0.5 * Math.log(cv$temp$39$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$38$var111) / Math.sqrt(cv$temp$39$var112))) - (0.5 * Math.log(cv$temp$39$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$38$var111) / Math.sqrt(cv$temp$39$var112))) - (0.5 * Math.log(cv$temp$39$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$114 = 1; index$i$114 < samples; index$i$114 += 1) {
															if(!(index$i$114 == index$i$1)) {
																for(int index$sample44$115 = 0; index$sample44$115 < noStates; index$sample44$115 += 1) {
																	int distributionTempVariable$var40$117 = index$sample44$115;
																	double cv$probabilitySample44Value116 = (1.0 * distribution$sample44[((index$i$114 - 1) / 1)][index$sample44$115]);
																	int traceTempVariable$s$118_1 = distributionTempVariable$var40$117;
																	if((index$i$114 == i$var109)) {
																		for(int var52 = 0; var52 < noStates; var52 += 1) {
																			if((var52 == traceTempVariable$s$118_1)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$118_1)) {
																						{
																							{
																								double cv$temp$40$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$118_1];
																									cv$temp$40$var111 = var111;
																								}
																								double cv$temp$41$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$118_1];
																									cv$temp$41$var112 = var112;
																								}
																								if(((Math.log(cv$probabilitySample44Value116) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value116) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value116) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value116) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value116) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value116);
																							}
																						}
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
									boolean[] guard$sample44gaussian122 = guard$sample44gaussian122$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$223_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$223_1)) {
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$223_1)) {
																			{
																				{
																					double cv$temp$68$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$223_1];
																						cv$temp$68$var116 = var116;
																					}
																					double cv$temp$69$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$223_1];
																						cv$temp$69$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$68$var116) / Math.sqrt(cv$temp$69$var117))) - (0.5 * Math.log(cv$temp$69$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$68$var116) / Math.sqrt(cv$temp$69$var117))) - (0.5 * Math.log(cv$temp$69$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$68$var116) / Math.sqrt(cv$temp$69$var117))) - (0.5 * Math.log(cv$temp$69$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$68$var116) / Math.sqrt(cv$temp$69$var117))) - (0.5 * Math.log(cv$temp$69$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$68$var116) / Math.sqrt(cv$temp$69$var117))) - (0.5 * Math.log(cv$temp$69$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$223_1)) {
																int traceTempVariable$s$243_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$243_1)) {
																			{
																				{
																					double cv$temp$70$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$243_1];
																						cv$temp$70$var116 = var116;
																					}
																					double cv$temp$71$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$243_1];
																						cv$temp$71$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$70$var116) / Math.sqrt(cv$temp$71$var117))) - (0.5 * Math.log(cv$temp$71$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$70$var116) / Math.sqrt(cv$temp$71$var117))) - (0.5 * Math.log(cv$temp$71$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$70$var116) / Math.sqrt(cv$temp$71$var117))) - (0.5 * Math.log(cv$temp$71$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$70$var116) / Math.sqrt(cv$temp$71$var117))) - (0.5 * Math.log(cv$temp$71$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$70$var116) / Math.sqrt(cv$temp$71$var117))) - (0.5 * Math.log(cv$temp$71$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$244 = 1; index$i$244 < samples; index$i$244 += 1) {
																	if(!(index$i$244 == index$i$1)) {
																		for(int index$sample44$245 = 0; index$sample44$245 < noStates; index$sample44$245 += 1) {
																			int distributionTempVariable$var40$247 = index$sample44$245;
																			double cv$probabilitySample44Value246 = (1.0 * distribution$sample44[((index$i$244 - 1) / 1)][index$sample44$245]);
																			int traceTempVariable$s$248_1 = distributionTempVariable$var40$247;
																			if((index$i$244 == i$var109)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$248_1)) {
																						{
																							{
																								double cv$temp$72$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$248_1];
																									cv$temp$72$var116 = var116;
																								}
																								double cv$temp$73$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$248_1];
																									cv$temp$73$var117 = var117;
																								}
																								if(((Math.log(cv$probabilitySample44Value246) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value246) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value246) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value246) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value246) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value246);
																							}
																						}
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
									int traceTempVariable$s$227_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$227_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$227_1)) {
																			{
																				{
																					double cv$temp$100$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$227_1];
																						cv$temp$100$var116 = var116;
																					}
																					double cv$temp$101$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$227_1];
																						cv$temp$101$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$100$var116) / Math.sqrt(cv$temp$101$var117))) - (0.5 * Math.log(cv$temp$101$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$100$var116) / Math.sqrt(cv$temp$101$var117))) - (0.5 * Math.log(cv$temp$101$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$100$var116) / Math.sqrt(cv$temp$101$var117))) - (0.5 * Math.log(cv$temp$101$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$100$var116) / Math.sqrt(cv$temp$101$var117))) - (0.5 * Math.log(cv$temp$101$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$100$var116) / Math.sqrt(cv$temp$101$var117))) - (0.5 * Math.log(cv$temp$101$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$307_1 = cv$currentValue;
														if((index$i$1 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$307_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$307_1)) {
																			{
																				{
																					double cv$temp$102$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$307_1];
																						cv$temp$102$var116 = var116;
																					}
																					double cv$temp$103$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$307_1];
																						cv$temp$103$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$102$var116) / Math.sqrt(cv$temp$103$var117))) - (0.5 * Math.log(cv$temp$103$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$102$var116) / Math.sqrt(cv$temp$103$var117))) - (0.5 * Math.log(cv$temp$103$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$102$var116) / Math.sqrt(cv$temp$103$var117))) - (0.5 * Math.log(cv$temp$103$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$102$var116) / Math.sqrt(cv$temp$103$var117))) - (0.5 * Math.log(cv$temp$103$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$102$var116) / Math.sqrt(cv$temp$103$var117))) - (0.5 * Math.log(cv$temp$103$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$308 = 1; index$i$308 < samples; index$i$308 += 1) {
															if(!(index$i$308 == index$i$1)) {
																for(int index$sample44$309 = 0; index$sample44$309 < noStates; index$sample44$309 += 1) {
																	int distributionTempVariable$var40$311 = index$sample44$309;
																	double cv$probabilitySample44Value310 = (1.0 * distribution$sample44[((index$i$308 - 1) / 1)][index$sample44$309]);
																	int traceTempVariable$s$312_1 = distributionTempVariable$var40$311;
																	if((index$i$308 == i$var109)) {
																		for(int var63 = 0; var63 < noStates; var63 += 1) {
																			if((var63 == traceTempVariable$s$312_1)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$312_1)) {
																						{
																							{
																								double cv$temp$104$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$312_1];
																									cv$temp$104$var116 = var116;
																								}
																								double cv$temp$105$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$312_1];
																									cv$temp$105$var117 = var117;
																								}
																								if(((Math.log(cv$probabilitySample44Value310) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value310) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value310) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value310) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value310) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value310);
																							}
																						}
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
									boolean[] guard$sample44gaussian127 = guard$sample44gaussian127$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$417_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$417_1)) {
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$417_1)) {
																			{
																				{
																					double cv$temp$132$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$417_1];
																						cv$temp$132$var121 = var121;
																					}
																					double cv$temp$133$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$417_1];
																						cv$temp$133$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$132$var121) / Math.sqrt(cv$temp$133$var122))) - (0.5 * Math.log(cv$temp$133$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$132$var121) / Math.sqrt(cv$temp$133$var122))) - (0.5 * Math.log(cv$temp$133$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$132$var121) / Math.sqrt(cv$temp$133$var122))) - (0.5 * Math.log(cv$temp$133$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$132$var121) / Math.sqrt(cv$temp$133$var122))) - (0.5 * Math.log(cv$temp$133$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$132$var121) / Math.sqrt(cv$temp$133$var122))) - (0.5 * Math.log(cv$temp$133$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$417_1)) {
																int traceTempVariable$s$437_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$437_1)) {
																			{
																				{
																					double cv$temp$134$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$437_1];
																						cv$temp$134$var121 = var121;
																					}
																					double cv$temp$135$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$437_1];
																						cv$temp$135$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$134$var121) / Math.sqrt(cv$temp$135$var122))) - (0.5 * Math.log(cv$temp$135$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$134$var121) / Math.sqrt(cv$temp$135$var122))) - (0.5 * Math.log(cv$temp$135$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$134$var121) / Math.sqrt(cv$temp$135$var122))) - (0.5 * Math.log(cv$temp$135$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$134$var121) / Math.sqrt(cv$temp$135$var122))) - (0.5 * Math.log(cv$temp$135$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$134$var121) / Math.sqrt(cv$temp$135$var122))) - (0.5 * Math.log(cv$temp$135$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$438 = 1; index$i$438 < samples; index$i$438 += 1) {
																	if(!(index$i$438 == index$i$1)) {
																		for(int index$sample44$439 = 0; index$sample44$439 < noStates; index$sample44$439 += 1) {
																			int distributionTempVariable$var40$441 = index$sample44$439;
																			double cv$probabilitySample44Value440 = (1.0 * distribution$sample44[((index$i$438 - 1) / 1)][index$sample44$439]);
																			int traceTempVariable$s$442_1 = distributionTempVariable$var40$441;
																			if((index$i$438 == i$var109)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$442_1)) {
																						{
																							{
																								double cv$temp$136$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$442_1];
																									cv$temp$136$var121 = var121;
																								}
																								double cv$temp$137$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$442_1];
																									cv$temp$137$var122 = var122;
																								}
																								if(((Math.log(cv$probabilitySample44Value440) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value440) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value440) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value440) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value440) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value440);
																							}
																						}
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
									int traceTempVariable$s$421_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
												guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$421_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$421_1)) {
																			{
																				{
																					double cv$temp$164$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$421_1];
																						cv$temp$164$var121 = var121;
																					}
																					double cv$temp$165$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$421_1];
																						cv$temp$165$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$164$var121) / Math.sqrt(cv$temp$165$var122))) - (0.5 * Math.log(cv$temp$165$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$164$var121) / Math.sqrt(cv$temp$165$var122))) - (0.5 * Math.log(cv$temp$165$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$164$var121) / Math.sqrt(cv$temp$165$var122))) - (0.5 * Math.log(cv$temp$165$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$164$var121) / Math.sqrt(cv$temp$165$var122))) - (0.5 * Math.log(cv$temp$165$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$164$var121) / Math.sqrt(cv$temp$165$var122))) - (0.5 * Math.log(cv$temp$165$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$s$501_1 = cv$currentValue;
														if((index$i$1 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$501_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$501_1)) {
																			{
																				{
																					double cv$temp$166$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$501_1];
																						cv$temp$166$var121 = var121;
																					}
																					double cv$temp$167$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$501_1];
																						cv$temp$167$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$166$var121) / Math.sqrt(cv$temp$167$var122))) - (0.5 * Math.log(cv$temp$167$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$166$var121) / Math.sqrt(cv$temp$167$var122))) - (0.5 * Math.log(cv$temp$167$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$166$var121) / Math.sqrt(cv$temp$167$var122))) - (0.5 * Math.log(cv$temp$167$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$166$var121) / Math.sqrt(cv$temp$167$var122))) - (0.5 * Math.log(cv$temp$167$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$166$var121) / Math.sqrt(cv$temp$167$var122))) - (0.5 * Math.log(cv$temp$167$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$502 = 1; index$i$502 < samples; index$i$502 += 1) {
															if(!(index$i$502 == index$i$1)) {
																for(int index$sample44$503 = 0; index$sample44$503 < noStates; index$sample44$503 += 1) {
																	int distributionTempVariable$var40$505 = index$sample44$503;
																	double cv$probabilitySample44Value504 = (1.0 * distribution$sample44[((index$i$502 - 1) / 1)][index$sample44$503]);
																	int traceTempVariable$s$506_1 = distributionTempVariable$var40$505;
																	if((index$i$502 == i$var109)) {
																		for(int var74 = 0; var74 < noStates; var74 += 1) {
																			if((var74 == traceTempVariable$s$506_1)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$506_1)) {
																						{
																							{
																								double cv$temp$168$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$506_1];
																									cv$temp$168$var121 = var121;
																								}
																								double cv$temp$169$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$506_1];
																									cv$temp$169$var122 = var122;
																								}
																								if(((Math.log(cv$probabilitySample44Value504) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value504) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value504) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value504) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value504) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value504);
																							}
																						}
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
					for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
						int distributionTempVariable$var30$5 = index$sample34$3;
						double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
						int traceTempVariable$var37$6_1 = distributionTempVariable$var30$5;
						if((0 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$6_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample34Value4);
									double[] cv$temp$1$var38;
									{
										double[] var38 = m[traceTempVariable$var37$6_1];
										cv$temp$1$var38 = var38;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample34Value4) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var37$18_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample44gaussian117 = guard$sample44gaussian117$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$30_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$30_1)) {
																		int traceTempVariable$s$58_1 = distributionTempVariable$var30$5;
																		if((0 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$58_1)) {
																					{
																						{
																							double cv$temp$10$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$58_1];
																								cv$temp$10$var111 = var111;
																							}
																							double cv$temp$11$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$58_1];
																								cv$temp$11$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample34$59 = 0; index$sample34$59 < noStates; index$sample34$59 += 1) {
																				int distributionTempVariable$var30$61 = index$sample34$59;
																				double cv$probabilitySample34Value60 = (1.0 * distribution$sample34[index$sample34$59]);
																				int traceTempVariable$s$62_1 = distributionTempVariable$var30$61;
																				if((0 == i$var109)) {
																					for(int var84 = 0; var84 < noStates; var84 += 1) {
																						if((var84 == traceTempVariable$s$62_1)) {
																							{
																								{
																									double cv$temp$12$var111;
																									{
																										double var111 = cpuMean[traceTempVariable$s$62_1];
																										cv$temp$12$var111 = var111;
																									}
																									double cv$temp$13$var112;
																									{
																										double var112 = cpuVar[traceTempVariable$s$62_1];
																										cv$temp$13$var112 = var112;
																									}
																									if(((Math.log(cv$probabilitySample34Value60) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value60) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value60) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value60) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value60) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value60);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$30_1)) {
																		int traceTempVariable$s$66_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$66_1)) {
																					{
																						{
																							double cv$temp$14$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$66_1];
																								cv$temp$14$var111 = var111;
																							}
																							double cv$temp$15$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$66_1];
																								cv$temp$15$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$67 = 1; index$i$67 < samples; index$i$67 += 1) {
																			if(!(index$i$67 == index$i$1)) {
																				for(int index$sample44$68 = 0; index$sample44$68 < noStates; index$sample44$68 += 1) {
																					int distributionTempVariable$var40$70 = index$sample44$68;
																					double cv$probabilitySample44Value69 = (1.0 * distribution$sample44[((index$i$67 - 1) / 1)][index$sample44$68]);
																					int traceTempVariable$s$71_1 = distributionTempVariable$var40$70;
																					if((index$i$67 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$71_1)) {
																								{
																									{
																										double cv$temp$16$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$71_1];
																											cv$temp$16$var111 = var111;
																										}
																										double cv$temp$17$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$71_1];
																											cv$temp$17$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample44Value69) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value69) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value69) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value69) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value69) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value69);
																									}
																								}
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
											int traceTempVariable$s$34_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$123_1 = distributionTempVariable$var30$5;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$123_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$123_1)) {
																					{
																						{
																							double cv$temp$42$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$123_1];
																								cv$temp$42$var111 = var111;
																							}
																							double cv$temp$43$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$123_1];
																								cv$temp$43$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$42$var111) / Math.sqrt(cv$temp$43$var112))) - (0.5 * Math.log(cv$temp$43$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$42$var111) / Math.sqrt(cv$temp$43$var112))) - (0.5 * Math.log(cv$temp$43$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$42$var111) / Math.sqrt(cv$temp$43$var112))) - (0.5 * Math.log(cv$temp$43$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$42$var111) / Math.sqrt(cv$temp$43$var112))) - (0.5 * Math.log(cv$temp$43$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$42$var111) / Math.sqrt(cv$temp$43$var112))) - (0.5 * Math.log(cv$temp$43$var112)))));
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
																	for(int index$sample34$124 = 0; index$sample34$124 < noStates; index$sample34$124 += 1) {
																		int distributionTempVariable$var30$126 = index$sample34$124;
																		double cv$probabilitySample34Value125 = (1.0 * distribution$sample34[index$sample34$124]);
																		int traceTempVariable$s$127_1 = distributionTempVariable$var30$126;
																		if((0 == i$var109)) {
																			for(int var52 = 0; var52 < noStates; var52 += 1) {
																				if((var52 == traceTempVariable$s$127_1)) {
																					for(int var84 = 0; var84 < noStates; var84 += 1) {
																						if((var84 == traceTempVariable$s$127_1)) {
																							{
																								{
																									double cv$temp$44$var111;
																									{
																										double var111 = cpuMean[traceTempVariable$s$127_1];
																										cv$temp$44$var111 = var111;
																									}
																									double cv$temp$45$var112;
																									{
																										double var112 = cpuVar[traceTempVariable$s$127_1];
																										cv$temp$45$var112 = var112;
																									}
																									if(((Math.log(cv$probabilitySample34Value125) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value125) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value125) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value125) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value125) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value125);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$132_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$132_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$132_1)) {
																					{
																						{
																							double cv$temp$46$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$132_1];
																								cv$temp$46$var111 = var111;
																							}
																							double cv$temp$47$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$132_1];
																								cv$temp$47$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$46$var111) / Math.sqrt(cv$temp$47$var112))) - (0.5 * Math.log(cv$temp$47$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$46$var111) / Math.sqrt(cv$temp$47$var112))) - (0.5 * Math.log(cv$temp$47$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$46$var111) / Math.sqrt(cv$temp$47$var112))) - (0.5 * Math.log(cv$temp$47$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$46$var111) / Math.sqrt(cv$temp$47$var112))) - (0.5 * Math.log(cv$temp$47$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$46$var111) / Math.sqrt(cv$temp$47$var112))) - (0.5 * Math.log(cv$temp$47$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$133 = 1; index$i$133 < samples; index$i$133 += 1) {
																	if(!(index$i$133 == index$i$1)) {
																		for(int index$sample44$134 = 0; index$sample44$134 < noStates; index$sample44$134 += 1) {
																			int distributionTempVariable$var40$136 = index$sample44$134;
																			double cv$probabilitySample44Value135 = (1.0 * distribution$sample44[((index$i$133 - 1) / 1)][index$sample44$134]);
																			int traceTempVariable$s$137_1 = distributionTempVariable$var40$136;
																			if((index$i$133 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$137_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$137_1)) {
																								{
																									{
																										double cv$temp$48$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$137_1];
																											cv$temp$48$var111 = var111;
																										}
																										double cv$temp$49$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$137_1];
																											cv$temp$49$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample44Value135) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value135) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value135) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value135) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value135) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value135);
																									}
																								}
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
											boolean[] guard$sample44gaussian122 = guard$sample44gaussian122$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$224_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$224_1)) {
																		int traceTempVariable$s$252_1 = distributionTempVariable$var30$5;
																		if((0 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$252_1)) {
																					{
																						{
																							double cv$temp$74$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$252_1];
																								cv$temp$74$var116 = var116;
																							}
																							double cv$temp$75$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$252_1];
																								cv$temp$75$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$74$var116) / Math.sqrt(cv$temp$75$var117))) - (0.5 * Math.log(cv$temp$75$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$74$var116) / Math.sqrt(cv$temp$75$var117))) - (0.5 * Math.log(cv$temp$75$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$74$var116) / Math.sqrt(cv$temp$75$var117))) - (0.5 * Math.log(cv$temp$75$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$74$var116) / Math.sqrt(cv$temp$75$var117))) - (0.5 * Math.log(cv$temp$75$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$74$var116) / Math.sqrt(cv$temp$75$var117))) - (0.5 * Math.log(cv$temp$75$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample34$253 = 0; index$sample34$253 < noStates; index$sample34$253 += 1) {
																				int distributionTempVariable$var30$255 = index$sample34$253;
																				double cv$probabilitySample34Value254 = (1.0 * distribution$sample34[index$sample34$253]);
																				int traceTempVariable$s$256_1 = distributionTempVariable$var30$255;
																				if((0 == i$var109)) {
																					for(int var94 = 0; var94 < noStates; var94 += 1) {
																						if((var94 == traceTempVariable$s$256_1)) {
																							{
																								{
																									double cv$temp$76$var116;
																									{
																										double var116 = memMean[traceTempVariable$s$256_1];
																										cv$temp$76$var116 = var116;
																									}
																									double cv$temp$77$var117;
																									{
																										double var117 = memVar[traceTempVariable$s$256_1];
																										cv$temp$77$var117 = var117;
																									}
																									if(((Math.log(cv$probabilitySample34Value254) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value254) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value254) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value254) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value254) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value254);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$224_1)) {
																		int traceTempVariable$s$260_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$260_1)) {
																					{
																						{
																							double cv$temp$78$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$260_1];
																								cv$temp$78$var116 = var116;
																							}
																							double cv$temp$79$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$260_1];
																								cv$temp$79$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$78$var116) / Math.sqrt(cv$temp$79$var117))) - (0.5 * Math.log(cv$temp$79$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$78$var116) / Math.sqrt(cv$temp$79$var117))) - (0.5 * Math.log(cv$temp$79$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$78$var116) / Math.sqrt(cv$temp$79$var117))) - (0.5 * Math.log(cv$temp$79$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$78$var116) / Math.sqrt(cv$temp$79$var117))) - (0.5 * Math.log(cv$temp$79$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$78$var116) / Math.sqrt(cv$temp$79$var117))) - (0.5 * Math.log(cv$temp$79$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$261 = 1; index$i$261 < samples; index$i$261 += 1) {
																			if(!(index$i$261 == index$i$1)) {
																				for(int index$sample44$262 = 0; index$sample44$262 < noStates; index$sample44$262 += 1) {
																					int distributionTempVariable$var40$264 = index$sample44$262;
																					double cv$probabilitySample44Value263 = (1.0 * distribution$sample44[((index$i$261 - 1) / 1)][index$sample44$262]);
																					int traceTempVariable$s$265_1 = distributionTempVariable$var40$264;
																					if((index$i$261 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$265_1)) {
																								{
																									{
																										double cv$temp$80$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$265_1];
																											cv$temp$80$var116 = var116;
																										}
																										double cv$temp$81$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$265_1];
																											cv$temp$81$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample44Value263) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value263) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value263) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value263) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value263) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value263);
																									}
																								}
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
											int traceTempVariable$s$228_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$317_1 = distributionTempVariable$var30$5;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$317_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$317_1)) {
																					{
																						{
																							double cv$temp$106$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$317_1];
																								cv$temp$106$var116 = var116;
																							}
																							double cv$temp$107$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$317_1];
																								cv$temp$107$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$106$var116) / Math.sqrt(cv$temp$107$var117))) - (0.5 * Math.log(cv$temp$107$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$106$var116) / Math.sqrt(cv$temp$107$var117))) - (0.5 * Math.log(cv$temp$107$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$106$var116) / Math.sqrt(cv$temp$107$var117))) - (0.5 * Math.log(cv$temp$107$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$106$var116) / Math.sqrt(cv$temp$107$var117))) - (0.5 * Math.log(cv$temp$107$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$106$var116) / Math.sqrt(cv$temp$107$var117))) - (0.5 * Math.log(cv$temp$107$var117)))));
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
																	for(int index$sample34$318 = 0; index$sample34$318 < noStates; index$sample34$318 += 1) {
																		int distributionTempVariable$var30$320 = index$sample34$318;
																		double cv$probabilitySample34Value319 = (1.0 * distribution$sample34[index$sample34$318]);
																		int traceTempVariable$s$321_1 = distributionTempVariable$var30$320;
																		if((0 == i$var109)) {
																			for(int var63 = 0; var63 < noStates; var63 += 1) {
																				if((var63 == traceTempVariable$s$321_1)) {
																					for(int var94 = 0; var94 < noStates; var94 += 1) {
																						if((var94 == traceTempVariable$s$321_1)) {
																							{
																								{
																									double cv$temp$108$var116;
																									{
																										double var116 = memMean[traceTempVariable$s$321_1];
																										cv$temp$108$var116 = var116;
																									}
																									double cv$temp$109$var117;
																									{
																										double var117 = memVar[traceTempVariable$s$321_1];
																										cv$temp$109$var117 = var117;
																									}
																									if(((Math.log(cv$probabilitySample34Value319) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value319) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value319) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value319) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value319) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value319);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$326_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$326_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$326_1)) {
																					{
																						{
																							double cv$temp$110$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$326_1];
																								cv$temp$110$var116 = var116;
																							}
																							double cv$temp$111$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$326_1];
																								cv$temp$111$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$110$var116) / Math.sqrt(cv$temp$111$var117))) - (0.5 * Math.log(cv$temp$111$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$110$var116) / Math.sqrt(cv$temp$111$var117))) - (0.5 * Math.log(cv$temp$111$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$110$var116) / Math.sqrt(cv$temp$111$var117))) - (0.5 * Math.log(cv$temp$111$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$110$var116) / Math.sqrt(cv$temp$111$var117))) - (0.5 * Math.log(cv$temp$111$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$110$var116) / Math.sqrt(cv$temp$111$var117))) - (0.5 * Math.log(cv$temp$111$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$327 = 1; index$i$327 < samples; index$i$327 += 1) {
																	if(!(index$i$327 == index$i$1)) {
																		for(int index$sample44$328 = 0; index$sample44$328 < noStates; index$sample44$328 += 1) {
																			int distributionTempVariable$var40$330 = index$sample44$328;
																			double cv$probabilitySample44Value329 = (1.0 * distribution$sample44[((index$i$327 - 1) / 1)][index$sample44$328]);
																			int traceTempVariable$s$331_1 = distributionTempVariable$var40$330;
																			if((index$i$327 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$331_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$331_1)) {
																								{
																									{
																										double cv$temp$112$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$331_1];
																											cv$temp$112$var116 = var116;
																										}
																										double cv$temp$113$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$331_1];
																											cv$temp$113$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample44Value329) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value329) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value329) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value329) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value329) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value329);
																									}
																								}
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
											boolean[] guard$sample44gaussian127 = guard$sample44gaussian127$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$418_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$418_1)) {
																		int traceTempVariable$s$446_1 = distributionTempVariable$var30$5;
																		if((0 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$446_1)) {
																					{
																						{
																							double cv$temp$138$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$446_1];
																								cv$temp$138$var121 = var121;
																							}
																							double cv$temp$139$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$446_1];
																								cv$temp$139$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$138$var121) / Math.sqrt(cv$temp$139$var122))) - (0.5 * Math.log(cv$temp$139$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$138$var121) / Math.sqrt(cv$temp$139$var122))) - (0.5 * Math.log(cv$temp$139$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$138$var121) / Math.sqrt(cv$temp$139$var122))) - (0.5 * Math.log(cv$temp$139$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$138$var121) / Math.sqrt(cv$temp$139$var122))) - (0.5 * Math.log(cv$temp$139$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$138$var121) / Math.sqrt(cv$temp$139$var122))) - (0.5 * Math.log(cv$temp$139$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample34$447 = 0; index$sample34$447 < noStates; index$sample34$447 += 1) {
																				int distributionTempVariable$var30$449 = index$sample34$447;
																				double cv$probabilitySample34Value448 = (1.0 * distribution$sample34[index$sample34$447]);
																				int traceTempVariable$s$450_1 = distributionTempVariable$var30$449;
																				if((0 == i$var109)) {
																					for(int var104 = 0; var104 < noStates; var104 += 1) {
																						if((var104 == traceTempVariable$s$450_1)) {
																							{
																								{
																									double cv$temp$140$var121;
																									{
																										double var121 = pageFaultsMean[traceTempVariable$s$450_1];
																										cv$temp$140$var121 = var121;
																									}
																									double cv$temp$141$var122;
																									{
																										double var122 = pageFaultsVar[traceTempVariable$s$450_1];
																										cv$temp$141$var122 = var122;
																									}
																									if(((Math.log(cv$probabilitySample34Value448) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value448) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value448) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value448) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value448) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value448);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$418_1)) {
																		int traceTempVariable$s$454_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$454_1)) {
																					{
																						{
																							double cv$temp$142$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$454_1];
																								cv$temp$142$var121 = var121;
																							}
																							double cv$temp$143$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$454_1];
																								cv$temp$143$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$142$var121) / Math.sqrt(cv$temp$143$var122))) - (0.5 * Math.log(cv$temp$143$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$142$var121) / Math.sqrt(cv$temp$143$var122))) - (0.5 * Math.log(cv$temp$143$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$142$var121) / Math.sqrt(cv$temp$143$var122))) - (0.5 * Math.log(cv$temp$143$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$142$var121) / Math.sqrt(cv$temp$143$var122))) - (0.5 * Math.log(cv$temp$143$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$142$var121) / Math.sqrt(cv$temp$143$var122))) - (0.5 * Math.log(cv$temp$143$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$455 = 1; index$i$455 < samples; index$i$455 += 1) {
																			if(!(index$i$455 == index$i$1)) {
																				for(int index$sample44$456 = 0; index$sample44$456 < noStates; index$sample44$456 += 1) {
																					int distributionTempVariable$var40$458 = index$sample44$456;
																					double cv$probabilitySample44Value457 = (1.0 * distribution$sample44[((index$i$455 - 1) / 1)][index$sample44$456]);
																					int traceTempVariable$s$459_1 = distributionTempVariable$var40$458;
																					if((index$i$455 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$459_1)) {
																								{
																									{
																										double cv$temp$144$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$459_1];
																											cv$temp$144$var121 = var121;
																										}
																										double cv$temp$145$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$459_1];
																											cv$temp$145$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample44Value457) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value457) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value457) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value457) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value457) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value457);
																									}
																								}
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
											int traceTempVariable$s$422_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$511_1 = distributionTempVariable$var30$5;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$511_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$511_1)) {
																					{
																						{
																							double cv$temp$170$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$511_1];
																								cv$temp$170$var121 = var121;
																							}
																							double cv$temp$171$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$511_1];
																								cv$temp$171$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$170$var121) / Math.sqrt(cv$temp$171$var122))) - (0.5 * Math.log(cv$temp$171$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$170$var121) / Math.sqrt(cv$temp$171$var122))) - (0.5 * Math.log(cv$temp$171$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$170$var121) / Math.sqrt(cv$temp$171$var122))) - (0.5 * Math.log(cv$temp$171$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$170$var121) / Math.sqrt(cv$temp$171$var122))) - (0.5 * Math.log(cv$temp$171$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$170$var121) / Math.sqrt(cv$temp$171$var122))) - (0.5 * Math.log(cv$temp$171$var122)))));
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
																	for(int index$sample34$512 = 0; index$sample34$512 < noStates; index$sample34$512 += 1) {
																		int distributionTempVariable$var30$514 = index$sample34$512;
																		double cv$probabilitySample34Value513 = (1.0 * distribution$sample34[index$sample34$512]);
																		int traceTempVariable$s$515_1 = distributionTempVariable$var30$514;
																		if((0 == i$var109)) {
																			for(int var74 = 0; var74 < noStates; var74 += 1) {
																				if((var74 == traceTempVariable$s$515_1)) {
																					for(int var104 = 0; var104 < noStates; var104 += 1) {
																						if((var104 == traceTempVariable$s$515_1)) {
																							{
																								{
																									double cv$temp$172$var121;
																									{
																										double var121 = pageFaultsMean[traceTempVariable$s$515_1];
																										cv$temp$172$var121 = var121;
																									}
																									double cv$temp$173$var122;
																									{
																										double var122 = pageFaultsVar[traceTempVariable$s$515_1];
																										cv$temp$173$var122 = var122;
																									}
																									if(((Math.log(cv$probabilitySample34Value513) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value513) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value513) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value513) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value513) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value513);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$520_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$520_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$520_1)) {
																					{
																						{
																							double cv$temp$174$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$520_1];
																								cv$temp$174$var121 = var121;
																							}
																							double cv$temp$175$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$520_1];
																								cv$temp$175$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$174$var121) / Math.sqrt(cv$temp$175$var122))) - (0.5 * Math.log(cv$temp$175$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$174$var121) / Math.sqrt(cv$temp$175$var122))) - (0.5 * Math.log(cv$temp$175$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$174$var121) / Math.sqrt(cv$temp$175$var122))) - (0.5 * Math.log(cv$temp$175$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$174$var121) / Math.sqrt(cv$temp$175$var122))) - (0.5 * Math.log(cv$temp$175$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$174$var121) / Math.sqrt(cv$temp$175$var122))) - (0.5 * Math.log(cv$temp$175$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$521 = 1; index$i$521 < samples; index$i$521 += 1) {
																	if(!(index$i$521 == index$i$1)) {
																		for(int index$sample44$522 = 0; index$sample44$522 < noStates; index$sample44$522 += 1) {
																			int distributionTempVariable$var40$524 = index$sample44$522;
																			double cv$probabilitySample44Value523 = (1.0 * distribution$sample44[((index$i$521 - 1) / 1)][index$sample44$522]);
																			int traceTempVariable$s$525_1 = distributionTempVariable$var40$524;
																			if((index$i$521 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$525_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$525_1)) {
																								{
																									{
																										double cv$temp$176$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$525_1];
																											cv$temp$176$var121 = var121;
																										}
																										double cv$temp$177$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$525_1];
																											cv$temp$177$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample44Value523) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value523) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value523) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value523) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value523) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value523);
																									}
																								}
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
			int traceTempVariable$var37$9_1 = cv$currentValue;
			if((index$i$1 == (i$var34 - 1))) {
				for(int var21 = 0; var21 < noStates; var21 += 1) {
					if((var21 == traceTempVariable$var37$9_1)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var38;
						{
							double[] var38 = m[traceTempVariable$var37$9_1];
							cv$temp$2$var38 = var38;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var38.length))?Math.log(cv$temp$2$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var37$19_1 = cv$currentValue;
							}
						}
						{
							{
								boolean[] guard$sample44gaussian117 = guard$sample44gaussian117$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$31_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var52 = 0; var52 < noStates; var52 += 1) {
														if((var52 == traceTempVariable$s$31_1)) {
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$31_1)) {
																			{
																				{
																					double cv$temp$18$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$31_1];
																						cv$temp$18$var111 = var111;
																					}
																					double cv$temp$19$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$31_1];
																						cv$temp$19$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$76 = 0; index$sample34$76 < noStates; index$sample34$76 += 1) {
																		int distributionTempVariable$var30$78 = index$sample34$76;
																		double cv$probabilitySample34Value77 = (1.0 * distribution$sample34[index$sample34$76]);
																		int traceTempVariable$s$79_1 = distributionTempVariable$var30$78;
																		if((0 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$79_1)) {
																					{
																						{
																							double cv$temp$20$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$79_1];
																								cv$temp$20$var111 = var111;
																							}
																							double cv$temp$21$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$79_1];
																								cv$temp$21$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample34Value77) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value77) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value77) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value77) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value77) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value77);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var52 = 0; var52 < noStates; var52 += 1) {
														if((var52 == traceTempVariable$s$31_1)) {
															int traceTempVariable$s$83_1 = cv$currentValue;
															if((index$i$1 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$83_1)) {
																		{
																			{
																				double cv$temp$22$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$83_1];
																					cv$temp$22$var111 = var111;
																				}
																				double cv$temp$23$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$83_1];
																					cv$temp$23$var112 = var112;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$84 = 1; index$i$84 < samples; index$i$84 += 1) {
																if(!(index$i$84 == index$i$1)) {
																	for(int index$sample44$85 = 0; index$sample44$85 < noStates; index$sample44$85 += 1) {
																		int distributionTempVariable$var40$87 = index$sample44$85;
																		double cv$probabilitySample44Value86 = (1.0 * distribution$sample44[((index$i$84 - 1) / 1)][index$sample44$85]);
																		int traceTempVariable$s$88_1 = distributionTempVariable$var40$87;
																		if((index$i$84 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$88_1)) {
																					{
																						{
																							double cv$temp$24$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$88_1];
																								cv$temp$24$var111 = var111;
																							}
																							double cv$temp$25$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$88_1];
																								cv$temp$25$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value86) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value86) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value86) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value86) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value86) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value86);
																						}
																					}
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
								int traceTempVariable$s$35_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$35_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$35_1)) {
																			{
																				{
																					double cv$temp$50$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$35_1];
																						cv$temp$50$var111 = var111;
																					}
																					double cv$temp$51$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$35_1];
																						cv$temp$51$var112 = var112;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$50$var111) / Math.sqrt(cv$temp$51$var112))) - (0.5 * Math.log(cv$temp$51$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$50$var111) / Math.sqrt(cv$temp$51$var112))) - (0.5 * Math.log(cv$temp$51$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$50$var111) / Math.sqrt(cv$temp$51$var112))) - (0.5 * Math.log(cv$temp$51$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$50$var111) / Math.sqrt(cv$temp$51$var112))) - (0.5 * Math.log(cv$temp$51$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$50$var111) / Math.sqrt(cv$temp$51$var112))) - (0.5 * Math.log(cv$temp$51$var112)))));
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
															for(int index$sample34$143 = 0; index$sample34$143 < noStates; index$sample34$143 += 1) {
																int distributionTempVariable$var30$145 = index$sample34$143;
																double cv$probabilitySample34Value144 = (1.0 * distribution$sample34[index$sample34$143]);
																int traceTempVariable$s$146_1 = distributionTempVariable$var30$145;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$146_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$146_1)) {
																					{
																						{
																							double cv$temp$52$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$146_1];
																								cv$temp$52$var111 = var111;
																							}
																							double cv$temp$53$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$146_1];
																								cv$temp$53$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample34Value144) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value144) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value144) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value144) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value144) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value144);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$151_1 = cv$currentValue;
													if((index$i$1 == i$var109)) {
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$151_1)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$151_1)) {
																		{
																			{
																				double cv$temp$54$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$151_1];
																					cv$temp$54$var111 = var111;
																				}
																				double cv$temp$55$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$151_1];
																					cv$temp$55$var112 = var112;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$54$var111) / Math.sqrt(cv$temp$55$var112))) - (0.5 * Math.log(cv$temp$55$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$54$var111) / Math.sqrt(cv$temp$55$var112))) - (0.5 * Math.log(cv$temp$55$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$54$var111) / Math.sqrt(cv$temp$55$var112))) - (0.5 * Math.log(cv$temp$55$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$54$var111) / Math.sqrt(cv$temp$55$var112))) - (0.5 * Math.log(cv$temp$55$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$54$var111) / Math.sqrt(cv$temp$55$var112))) - (0.5 * Math.log(cv$temp$55$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$152 = 1; index$i$152 < samples; index$i$152 += 1) {
														if(!(index$i$152 == index$i$1)) {
															for(int index$sample44$153 = 0; index$sample44$153 < noStates; index$sample44$153 += 1) {
																int distributionTempVariable$var40$155 = index$sample44$153;
																double cv$probabilitySample44Value154 = (1.0 * distribution$sample44[((index$i$152 - 1) / 1)][index$sample44$153]);
																int traceTempVariable$s$156_1 = distributionTempVariable$var40$155;
																if((index$i$152 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$156_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$156_1)) {
																					{
																						{
																							double cv$temp$56$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$156_1];
																								cv$temp$56$var111 = var111;
																							}
																							double cv$temp$57$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$156_1];
																								cv$temp$57$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value154) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value154) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value154) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value154) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value154) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value154);
																						}
																					}
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
								boolean[] guard$sample44gaussian122 = guard$sample44gaussian122$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$225_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var63 = 0; var63 < noStates; var63 += 1) {
														if((var63 == traceTempVariable$s$225_1)) {
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$225_1)) {
																			{
																				{
																					double cv$temp$82$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$225_1];
																						cv$temp$82$var116 = var116;
																					}
																					double cv$temp$83$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$225_1];
																						cv$temp$83$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$82$var116) / Math.sqrt(cv$temp$83$var117))) - (0.5 * Math.log(cv$temp$83$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$82$var116) / Math.sqrt(cv$temp$83$var117))) - (0.5 * Math.log(cv$temp$83$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$82$var116) / Math.sqrt(cv$temp$83$var117))) - (0.5 * Math.log(cv$temp$83$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$82$var116) / Math.sqrt(cv$temp$83$var117))) - (0.5 * Math.log(cv$temp$83$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$82$var116) / Math.sqrt(cv$temp$83$var117))) - (0.5 * Math.log(cv$temp$83$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$270 = 0; index$sample34$270 < noStates; index$sample34$270 += 1) {
																		int distributionTempVariable$var30$272 = index$sample34$270;
																		double cv$probabilitySample34Value271 = (1.0 * distribution$sample34[index$sample34$270]);
																		int traceTempVariable$s$273_1 = distributionTempVariable$var30$272;
																		if((0 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$273_1)) {
																					{
																						{
																							double cv$temp$84$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$273_1];
																								cv$temp$84$var116 = var116;
																							}
																							double cv$temp$85$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$273_1];
																								cv$temp$85$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample34Value271) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value271) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value271) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value271) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value271) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value271);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var63 = 0; var63 < noStates; var63 += 1) {
														if((var63 == traceTempVariable$s$225_1)) {
															int traceTempVariable$s$277_1 = cv$currentValue;
															if((index$i$1 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$277_1)) {
																		{
																			{
																				double cv$temp$86$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$277_1];
																					cv$temp$86$var116 = var116;
																				}
																				double cv$temp$87$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$277_1];
																					cv$temp$87$var117 = var117;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$86$var116) / Math.sqrt(cv$temp$87$var117))) - (0.5 * Math.log(cv$temp$87$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$86$var116) / Math.sqrt(cv$temp$87$var117))) - (0.5 * Math.log(cv$temp$87$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$86$var116) / Math.sqrt(cv$temp$87$var117))) - (0.5 * Math.log(cv$temp$87$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$86$var116) / Math.sqrt(cv$temp$87$var117))) - (0.5 * Math.log(cv$temp$87$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$86$var116) / Math.sqrt(cv$temp$87$var117))) - (0.5 * Math.log(cv$temp$87$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$278 = 1; index$i$278 < samples; index$i$278 += 1) {
																if(!(index$i$278 == index$i$1)) {
																	for(int index$sample44$279 = 0; index$sample44$279 < noStates; index$sample44$279 += 1) {
																		int distributionTempVariable$var40$281 = index$sample44$279;
																		double cv$probabilitySample44Value280 = (1.0 * distribution$sample44[((index$i$278 - 1) / 1)][index$sample44$279]);
																		int traceTempVariable$s$282_1 = distributionTempVariable$var40$281;
																		if((index$i$278 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$282_1)) {
																					{
																						{
																							double cv$temp$88$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$282_1];
																								cv$temp$88$var116 = var116;
																							}
																							double cv$temp$89$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$282_1];
																								cv$temp$89$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value280) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value280) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value280) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value280) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value280) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value280);
																						}
																					}
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
								int traceTempVariable$s$229_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$229_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$229_1)) {
																			{
																				{
																					double cv$temp$114$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$229_1];
																						cv$temp$114$var116 = var116;
																					}
																					double cv$temp$115$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$229_1];
																						cv$temp$115$var117 = var117;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$114$var116) / Math.sqrt(cv$temp$115$var117))) - (0.5 * Math.log(cv$temp$115$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$114$var116) / Math.sqrt(cv$temp$115$var117))) - (0.5 * Math.log(cv$temp$115$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$114$var116) / Math.sqrt(cv$temp$115$var117))) - (0.5 * Math.log(cv$temp$115$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$114$var116) / Math.sqrt(cv$temp$115$var117))) - (0.5 * Math.log(cv$temp$115$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$114$var116) / Math.sqrt(cv$temp$115$var117))) - (0.5 * Math.log(cv$temp$115$var117)))));
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
															for(int index$sample34$337 = 0; index$sample34$337 < noStates; index$sample34$337 += 1) {
																int distributionTempVariable$var30$339 = index$sample34$337;
																double cv$probabilitySample34Value338 = (1.0 * distribution$sample34[index$sample34$337]);
																int traceTempVariable$s$340_1 = distributionTempVariable$var30$339;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$340_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$340_1)) {
																					{
																						{
																							double cv$temp$116$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$340_1];
																								cv$temp$116$var116 = var116;
																							}
																							double cv$temp$117$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$340_1];
																								cv$temp$117$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample34Value338) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value338) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value338) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value338) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value338) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value338);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$345_1 = cv$currentValue;
													if((index$i$1 == i$var109)) {
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$345_1)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$345_1)) {
																		{
																			{
																				double cv$temp$118$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$345_1];
																					cv$temp$118$var116 = var116;
																				}
																				double cv$temp$119$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$345_1];
																					cv$temp$119$var117 = var117;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$118$var116) / Math.sqrt(cv$temp$119$var117))) - (0.5 * Math.log(cv$temp$119$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$118$var116) / Math.sqrt(cv$temp$119$var117))) - (0.5 * Math.log(cv$temp$119$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$118$var116) / Math.sqrt(cv$temp$119$var117))) - (0.5 * Math.log(cv$temp$119$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$118$var116) / Math.sqrt(cv$temp$119$var117))) - (0.5 * Math.log(cv$temp$119$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$118$var116) / Math.sqrt(cv$temp$119$var117))) - (0.5 * Math.log(cv$temp$119$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$346 = 1; index$i$346 < samples; index$i$346 += 1) {
														if(!(index$i$346 == index$i$1)) {
															for(int index$sample44$347 = 0; index$sample44$347 < noStates; index$sample44$347 += 1) {
																int distributionTempVariable$var40$349 = index$sample44$347;
																double cv$probabilitySample44Value348 = (1.0 * distribution$sample44[((index$i$346 - 1) / 1)][index$sample44$347]);
																int traceTempVariable$s$350_1 = distributionTempVariable$var40$349;
																if((index$i$346 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$350_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$350_1)) {
																					{
																						{
																							double cv$temp$120$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$350_1];
																								cv$temp$120$var116 = var116;
																							}
																							double cv$temp$121$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$350_1];
																								cv$temp$121$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value348) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value348) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value348) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value348) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value348) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value348);
																						}
																					}
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
								boolean[] guard$sample44gaussian127 = guard$sample44gaussian127$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$419_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var74 = 0; var74 < noStates; var74 += 1) {
														if((var74 == traceTempVariable$s$419_1)) {
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$419_1)) {
																			{
																				{
																					double cv$temp$146$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$419_1];
																						cv$temp$146$var121 = var121;
																					}
																					double cv$temp$147$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$419_1];
																						cv$temp$147$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$146$var121) / Math.sqrt(cv$temp$147$var122))) - (0.5 * Math.log(cv$temp$147$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$146$var121) / Math.sqrt(cv$temp$147$var122))) - (0.5 * Math.log(cv$temp$147$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$146$var121) / Math.sqrt(cv$temp$147$var122))) - (0.5 * Math.log(cv$temp$147$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$146$var121) / Math.sqrt(cv$temp$147$var122))) - (0.5 * Math.log(cv$temp$147$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$146$var121) / Math.sqrt(cv$temp$147$var122))) - (0.5 * Math.log(cv$temp$147$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$464 = 0; index$sample34$464 < noStates; index$sample34$464 += 1) {
																		int distributionTempVariable$var30$466 = index$sample34$464;
																		double cv$probabilitySample34Value465 = (1.0 * distribution$sample34[index$sample34$464]);
																		int traceTempVariable$s$467_1 = distributionTempVariable$var30$466;
																		if((0 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$467_1)) {
																					{
																						{
																							double cv$temp$148$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$467_1];
																								cv$temp$148$var121 = var121;
																							}
																							double cv$temp$149$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$467_1];
																								cv$temp$149$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample34Value465) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value465) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value465) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value465) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value465) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value465);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var74 = 0; var74 < noStates; var74 += 1) {
														if((var74 == traceTempVariable$s$419_1)) {
															int traceTempVariable$s$471_1 = cv$currentValue;
															if((index$i$1 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$471_1)) {
																		{
																			{
																				double cv$temp$150$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$471_1];
																					cv$temp$150$var121 = var121;
																				}
																				double cv$temp$151$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$471_1];
																					cv$temp$151$var122 = var122;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$150$var121) / Math.sqrt(cv$temp$151$var122))) - (0.5 * Math.log(cv$temp$151$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$150$var121) / Math.sqrt(cv$temp$151$var122))) - (0.5 * Math.log(cv$temp$151$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$150$var121) / Math.sqrt(cv$temp$151$var122))) - (0.5 * Math.log(cv$temp$151$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$150$var121) / Math.sqrt(cv$temp$151$var122))) - (0.5 * Math.log(cv$temp$151$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$150$var121) / Math.sqrt(cv$temp$151$var122))) - (0.5 * Math.log(cv$temp$151$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$472 = 1; index$i$472 < samples; index$i$472 += 1) {
																if(!(index$i$472 == index$i$1)) {
																	for(int index$sample44$473 = 0; index$sample44$473 < noStates; index$sample44$473 += 1) {
																		int distributionTempVariable$var40$475 = index$sample44$473;
																		double cv$probabilitySample44Value474 = (1.0 * distribution$sample44[((index$i$472 - 1) / 1)][index$sample44$473]);
																		int traceTempVariable$s$476_1 = distributionTempVariable$var40$475;
																		if((index$i$472 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$476_1)) {
																					{
																						{
																							double cv$temp$152$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$476_1];
																								cv$temp$152$var121 = var121;
																							}
																							double cv$temp$153$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$476_1];
																								cv$temp$153$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value474) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value474) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value474) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value474) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value474) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value474);
																						}
																					}
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
								int traceTempVariable$s$423_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
											guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$423_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$423_1)) {
																			{
																				{
																					double cv$temp$178$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$423_1];
																						cv$temp$178$var121 = var121;
																					}
																					double cv$temp$179$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$423_1];
																						cv$temp$179$var122 = var122;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$178$var121) / Math.sqrt(cv$temp$179$var122))) - (0.5 * Math.log(cv$temp$179$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$178$var121) / Math.sqrt(cv$temp$179$var122))) - (0.5 * Math.log(cv$temp$179$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$178$var121) / Math.sqrt(cv$temp$179$var122))) - (0.5 * Math.log(cv$temp$179$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$178$var121) / Math.sqrt(cv$temp$179$var122))) - (0.5 * Math.log(cv$temp$179$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$178$var121) / Math.sqrt(cv$temp$179$var122))) - (0.5 * Math.log(cv$temp$179$var122)))));
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
															for(int index$sample34$531 = 0; index$sample34$531 < noStates; index$sample34$531 += 1) {
																int distributionTempVariable$var30$533 = index$sample34$531;
																double cv$probabilitySample34Value532 = (1.0 * distribution$sample34[index$sample34$531]);
																int traceTempVariable$s$534_1 = distributionTempVariable$var30$533;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$534_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$534_1)) {
																					{
																						{
																							double cv$temp$180$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$534_1];
																								cv$temp$180$var121 = var121;
																							}
																							double cv$temp$181$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$534_1];
																								cv$temp$181$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample34Value532) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value532) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value532) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value532) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value532) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value532);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$s$539_1 = cv$currentValue;
													if((index$i$1 == i$var109)) {
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$539_1)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$539_1)) {
																		{
																			{
																				double cv$temp$182$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$539_1];
																					cv$temp$182$var121 = var121;
																				}
																				double cv$temp$183$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$539_1];
																					cv$temp$183$var122 = var122;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$182$var121) / Math.sqrt(cv$temp$183$var122))) - (0.5 * Math.log(cv$temp$183$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$182$var121) / Math.sqrt(cv$temp$183$var122))) - (0.5 * Math.log(cv$temp$183$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$182$var121) / Math.sqrt(cv$temp$183$var122))) - (0.5 * Math.log(cv$temp$183$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$182$var121) / Math.sqrt(cv$temp$183$var122))) - (0.5 * Math.log(cv$temp$183$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$182$var121) / Math.sqrt(cv$temp$183$var122))) - (0.5 * Math.log(cv$temp$183$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$540 = 1; index$i$540 < samples; index$i$540 += 1) {
														if(!(index$i$540 == index$i$1)) {
															for(int index$sample44$541 = 0; index$sample44$541 < noStates; index$sample44$541 += 1) {
																int distributionTempVariable$var40$543 = index$sample44$541;
																double cv$probabilitySample44Value542 = (1.0 * distribution$sample44[((index$i$540 - 1) / 1)][index$sample44$541]);
																int traceTempVariable$s$544_1 = distributionTempVariable$var40$543;
																if((index$i$540 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$544_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$544_1)) {
																					{
																						{
																							double cv$temp$184$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$544_1];
																								cv$temp$184$var121 = var121;
																							}
																							double cv$temp$185$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$544_1];
																								cv$temp$185$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value542) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value542) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value542) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value542) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value542) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value542);
																						}
																					}
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
			for(int index$i$10 = 1; index$i$10 < samples; index$i$10 += 1) {
				if(!(index$i$10 == index$i$1)) {
					for(int index$sample44$11 = 0; index$sample44$11 < noStates; index$sample44$11 += 1) {
						int distributionTempVariable$var40$13 = index$sample44$11;
						double cv$probabilitySample44Value12 = (1.0 * distribution$sample44[((index$i$10 - 1) / 1)][index$sample44$11]);
						int traceTempVariable$var37$14_1 = distributionTempVariable$var40$13;
						if((index$i$10 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$14_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample44Value12);
									double[] cv$temp$3$var38;
									{
										double[] var38 = m[traceTempVariable$var37$14_1];
										cv$temp$3$var38 = var38;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample44Value12) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var38.length))?Math.log(cv$temp$3$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var37$20_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample44gaussian117 = guard$sample44gaussian117$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian117[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$32_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$32_1)) {
																		if(fixedFlag$sample34) {
																			if((0 == i$var109)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$32_1)) {
																						{
																							{
																								double cv$temp$26$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$32_1];
																									cv$temp$26$var111 = var111;
																								}
																								double cv$temp$27$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$32_1];
																									cv$temp$27$var112 = var112;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample34$93 = 0; index$sample34$93 < noStates; index$sample34$93 += 1) {
																					int distributionTempVariable$var30$95 = index$sample34$93;
																					double cv$probabilitySample34Value94 = (1.0 * distribution$sample34[index$sample34$93]);
																					int traceTempVariable$s$96_1 = distributionTempVariable$var30$95;
																					if((0 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$96_1)) {
																								{
																									{
																										double cv$temp$28$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$96_1];
																											cv$temp$28$var111 = var111;
																										}
																										double cv$temp$29$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$96_1];
																											cv$temp$29$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample34Value94) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value94) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value94) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value94) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value94) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value94);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$32_1)) {
																		int traceTempVariable$s$100_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$100_1)) {
																					{
																						{
																							double cv$temp$30$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$100_1];
																								cv$temp$30$var111 = var111;
																							}
																							double cv$temp$31$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$100_1];
																								cv$temp$31$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$30$var111) / Math.sqrt(cv$temp$31$var112))) - (0.5 * Math.log(cv$temp$31$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$30$var111) / Math.sqrt(cv$temp$31$var112))) - (0.5 * Math.log(cv$temp$31$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$30$var111) / Math.sqrt(cv$temp$31$var112))) - (0.5 * Math.log(cv$temp$31$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$30$var111) / Math.sqrt(cv$temp$31$var112))) - (0.5 * Math.log(cv$temp$31$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$30$var111) / Math.sqrt(cv$temp$31$var112))) - (0.5 * Math.log(cv$temp$31$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$101_1 = distributionTempVariable$var40$13;
																		if((index$i$10 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$101_1)) {
																					{
																						{
																							double cv$temp$32$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$101_1];
																								cv$temp$32$var111 = var111;
																							}
																							double cv$temp$33$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$101_1];
																								cv$temp$33$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$32$var111) / Math.sqrt(cv$temp$33$var112))) - (0.5 * Math.log(cv$temp$33$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$32$var111) / Math.sqrt(cv$temp$33$var112))) - (0.5 * Math.log(cv$temp$33$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$32$var111) / Math.sqrt(cv$temp$33$var112))) - (0.5 * Math.log(cv$temp$33$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$32$var111) / Math.sqrt(cv$temp$33$var112))) - (0.5 * Math.log(cv$temp$33$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$32$var111) / Math.sqrt(cv$temp$33$var112))) - (0.5 * Math.log(cv$temp$33$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$102 = 1; index$i$102 < samples; index$i$102 += 1) {
																			if((!(index$i$102 == index$i$1) && !(index$i$102 == index$i$10))) {
																				for(int index$sample44$103 = 0; index$sample44$103 < noStates; index$sample44$103 += 1) {
																					int distributionTempVariable$var40$105 = index$sample44$103;
																					double cv$probabilitySample44Value104 = (1.0 * distribution$sample44[((index$i$102 - 1) / 1)][index$sample44$103]);
																					int traceTempVariable$s$106_1 = distributionTempVariable$var40$105;
																					if((index$i$102 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$106_1)) {
																								{
																									{
																										double cv$temp$34$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$106_1];
																											cv$temp$34$var111 = var111;
																										}
																										double cv$temp$35$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$106_1];
																											cv$temp$35$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample44Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value104);
																									}
																								}
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
											int traceTempVariable$s$36_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian117[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian117[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample34) {
																	if((0 == i$var109)) {
																		for(int var52 = 0; var52 < noStates; var52 += 1) {
																			if((var52 == traceTempVariable$s$36_1)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$36_1)) {
																						{
																							{
																								double cv$temp$58$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$36_1];
																									cv$temp$58$var111 = var111;
																								}
																								double cv$temp$59$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$36_1];
																									cv$temp$59$var112 = var112;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$58$var111) / Math.sqrt(cv$temp$59$var112))) - (0.5 * Math.log(cv$temp$59$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$58$var111) / Math.sqrt(cv$temp$59$var112))) - (0.5 * Math.log(cv$temp$59$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$58$var111) / Math.sqrt(cv$temp$59$var112))) - (0.5 * Math.log(cv$temp$59$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$58$var111) / Math.sqrt(cv$temp$59$var112))) - (0.5 * Math.log(cv$temp$59$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$58$var111) / Math.sqrt(cv$temp$59$var112))) - (0.5 * Math.log(cv$temp$59$var112)))));
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
																		for(int index$sample34$162 = 0; index$sample34$162 < noStates; index$sample34$162 += 1) {
																			int distributionTempVariable$var30$164 = index$sample34$162;
																			double cv$probabilitySample34Value163 = (1.0 * distribution$sample34[index$sample34$162]);
																			int traceTempVariable$s$165_1 = distributionTempVariable$var30$164;
																			if((0 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$165_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$165_1)) {
																								{
																									{
																										double cv$temp$60$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$165_1];
																											cv$temp$60$var111 = var111;
																										}
																										double cv$temp$61$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$165_1];
																											cv$temp$61$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample34Value163) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value163) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value163) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value163) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value163) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value163);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$170_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$170_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$170_1)) {
																					{
																						{
																							double cv$temp$62$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$170_1];
																								cv$temp$62$var111 = var111;
																							}
																							double cv$temp$63$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$170_1];
																								cv$temp$63$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$62$var111) / Math.sqrt(cv$temp$63$var112))) - (0.5 * Math.log(cv$temp$63$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$62$var111) / Math.sqrt(cv$temp$63$var112))) - (0.5 * Math.log(cv$temp$63$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$62$var111) / Math.sqrt(cv$temp$63$var112))) - (0.5 * Math.log(cv$temp$63$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$62$var111) / Math.sqrt(cv$temp$63$var112))) - (0.5 * Math.log(cv$temp$63$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$62$var111) / Math.sqrt(cv$temp$63$var112))) - (0.5 * Math.log(cv$temp$63$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$171_1 = distributionTempVariable$var40$13;
																if((index$i$10 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$171_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$171_1)) {
																					{
																						{
																							double cv$temp$64$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$171_1];
																								cv$temp$64$var111 = var111;
																							}
																							double cv$temp$65$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$171_1];
																								cv$temp$65$var112 = var112;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$64$var111) / Math.sqrt(cv$temp$65$var112))) - (0.5 * Math.log(cv$temp$65$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$64$var111) / Math.sqrt(cv$temp$65$var112))) - (0.5 * Math.log(cv$temp$65$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$64$var111) / Math.sqrt(cv$temp$65$var112))) - (0.5 * Math.log(cv$temp$65$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$64$var111) / Math.sqrt(cv$temp$65$var112))) - (0.5 * Math.log(cv$temp$65$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$64$var111) / Math.sqrt(cv$temp$65$var112))) - (0.5 * Math.log(cv$temp$65$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$172 = 1; index$i$172 < samples; index$i$172 += 1) {
																	if((!(index$i$172 == index$i$1) && !(index$i$172 == index$i$10))) {
																		for(int index$sample44$173 = 0; index$sample44$173 < noStates; index$sample44$173 += 1) {
																			int distributionTempVariable$var40$175 = index$sample44$173;
																			double cv$probabilitySample44Value174 = (1.0 * distribution$sample44[((index$i$172 - 1) / 1)][index$sample44$173]);
																			int traceTempVariable$s$176_1 = distributionTempVariable$var40$175;
																			if((index$i$172 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$176_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$176_1)) {
																								{
																									{
																										double cv$temp$66$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$176_1];
																											cv$temp$66$var111 = var111;
																										}
																										double cv$temp$67$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$176_1];
																											cv$temp$67$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample44Value174) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value174) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value174) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value174) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value174) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value174);
																									}
																								}
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
											boolean[] guard$sample44gaussian122 = guard$sample44gaussian122$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian122[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$226_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$226_1)) {
																		if(fixedFlag$sample34) {
																			if((0 == i$var109)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$226_1)) {
																						{
																							{
																								double cv$temp$90$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$226_1];
																									cv$temp$90$var116 = var116;
																								}
																								double cv$temp$91$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$226_1];
																									cv$temp$91$var117 = var117;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$90$var116) / Math.sqrt(cv$temp$91$var117))) - (0.5 * Math.log(cv$temp$91$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$90$var116) / Math.sqrt(cv$temp$91$var117))) - (0.5 * Math.log(cv$temp$91$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$90$var116) / Math.sqrt(cv$temp$91$var117))) - (0.5 * Math.log(cv$temp$91$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$90$var116) / Math.sqrt(cv$temp$91$var117))) - (0.5 * Math.log(cv$temp$91$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$90$var116) / Math.sqrt(cv$temp$91$var117))) - (0.5 * Math.log(cv$temp$91$var117)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample34$287 = 0; index$sample34$287 < noStates; index$sample34$287 += 1) {
																					int distributionTempVariable$var30$289 = index$sample34$287;
																					double cv$probabilitySample34Value288 = (1.0 * distribution$sample34[index$sample34$287]);
																					int traceTempVariable$s$290_1 = distributionTempVariable$var30$289;
																					if((0 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$290_1)) {
																								{
																									{
																										double cv$temp$92$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$290_1];
																											cv$temp$92$var116 = var116;
																										}
																										double cv$temp$93$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$290_1];
																											cv$temp$93$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample34Value288) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value288) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value288) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value288) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value288) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value288);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$226_1)) {
																		int traceTempVariable$s$294_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$294_1)) {
																					{
																						{
																							double cv$temp$94$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$294_1];
																								cv$temp$94$var116 = var116;
																							}
																							double cv$temp$95$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$294_1];
																								cv$temp$95$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$94$var116) / Math.sqrt(cv$temp$95$var117))) - (0.5 * Math.log(cv$temp$95$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$94$var116) / Math.sqrt(cv$temp$95$var117))) - (0.5 * Math.log(cv$temp$95$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$94$var116) / Math.sqrt(cv$temp$95$var117))) - (0.5 * Math.log(cv$temp$95$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$94$var116) / Math.sqrt(cv$temp$95$var117))) - (0.5 * Math.log(cv$temp$95$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$94$var116) / Math.sqrt(cv$temp$95$var117))) - (0.5 * Math.log(cv$temp$95$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$295_1 = distributionTempVariable$var40$13;
																		if((index$i$10 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$295_1)) {
																					{
																						{
																							double cv$temp$96$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$295_1];
																								cv$temp$96$var116 = var116;
																							}
																							double cv$temp$97$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$295_1];
																								cv$temp$97$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$96$var116) / Math.sqrt(cv$temp$97$var117))) - (0.5 * Math.log(cv$temp$97$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$96$var116) / Math.sqrt(cv$temp$97$var117))) - (0.5 * Math.log(cv$temp$97$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$96$var116) / Math.sqrt(cv$temp$97$var117))) - (0.5 * Math.log(cv$temp$97$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$96$var116) / Math.sqrt(cv$temp$97$var117))) - (0.5 * Math.log(cv$temp$97$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$96$var116) / Math.sqrt(cv$temp$97$var117))) - (0.5 * Math.log(cv$temp$97$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$296 = 1; index$i$296 < samples; index$i$296 += 1) {
																			if((!(index$i$296 == index$i$1) && !(index$i$296 == index$i$10))) {
																				for(int index$sample44$297 = 0; index$sample44$297 < noStates; index$sample44$297 += 1) {
																					int distributionTempVariable$var40$299 = index$sample44$297;
																					double cv$probabilitySample44Value298 = (1.0 * distribution$sample44[((index$i$296 - 1) / 1)][index$sample44$297]);
																					int traceTempVariable$s$300_1 = distributionTempVariable$var40$299;
																					if((index$i$296 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$300_1)) {
																								{
																									{
																										double cv$temp$98$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$300_1];
																											cv$temp$98$var116 = var116;
																										}
																										double cv$temp$99$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$300_1];
																											cv$temp$99$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample44Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value298);
																									}
																								}
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
											int traceTempVariable$s$230_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian122[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian122[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample34) {
																	if((0 == i$var109)) {
																		for(int var63 = 0; var63 < noStates; var63 += 1) {
																			if((var63 == traceTempVariable$s$230_1)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$230_1)) {
																						{
																							{
																								double cv$temp$122$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$230_1];
																									cv$temp$122$var116 = var116;
																								}
																								double cv$temp$123$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$230_1];
																									cv$temp$123$var117 = var117;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$122$var116) / Math.sqrt(cv$temp$123$var117))) - (0.5 * Math.log(cv$temp$123$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$122$var116) / Math.sqrt(cv$temp$123$var117))) - (0.5 * Math.log(cv$temp$123$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$122$var116) / Math.sqrt(cv$temp$123$var117))) - (0.5 * Math.log(cv$temp$123$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$122$var116) / Math.sqrt(cv$temp$123$var117))) - (0.5 * Math.log(cv$temp$123$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$122$var116) / Math.sqrt(cv$temp$123$var117))) - (0.5 * Math.log(cv$temp$123$var117)))));
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
																		for(int index$sample34$356 = 0; index$sample34$356 < noStates; index$sample34$356 += 1) {
																			int distributionTempVariable$var30$358 = index$sample34$356;
																			double cv$probabilitySample34Value357 = (1.0 * distribution$sample34[index$sample34$356]);
																			int traceTempVariable$s$359_1 = distributionTempVariable$var30$358;
																			if((0 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$359_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$359_1)) {
																								{
																									{
																										double cv$temp$124$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$359_1];
																											cv$temp$124$var116 = var116;
																										}
																										double cv$temp$125$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$359_1];
																											cv$temp$125$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample34Value357) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value357) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value357) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value357) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value357) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value357);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$364_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$364_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$364_1)) {
																					{
																						{
																							double cv$temp$126$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$364_1];
																								cv$temp$126$var116 = var116;
																							}
																							double cv$temp$127$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$364_1];
																								cv$temp$127$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$126$var116) / Math.sqrt(cv$temp$127$var117))) - (0.5 * Math.log(cv$temp$127$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$126$var116) / Math.sqrt(cv$temp$127$var117))) - (0.5 * Math.log(cv$temp$127$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$126$var116) / Math.sqrt(cv$temp$127$var117))) - (0.5 * Math.log(cv$temp$127$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$126$var116) / Math.sqrt(cv$temp$127$var117))) - (0.5 * Math.log(cv$temp$127$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$126$var116) / Math.sqrt(cv$temp$127$var117))) - (0.5 * Math.log(cv$temp$127$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$365_1 = distributionTempVariable$var40$13;
																if((index$i$10 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$365_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$365_1)) {
																					{
																						{
																							double cv$temp$128$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$365_1];
																								cv$temp$128$var116 = var116;
																							}
																							double cv$temp$129$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$365_1];
																								cv$temp$129$var117 = var117;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$128$var116) / Math.sqrt(cv$temp$129$var117))) - (0.5 * Math.log(cv$temp$129$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$128$var116) / Math.sqrt(cv$temp$129$var117))) - (0.5 * Math.log(cv$temp$129$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$128$var116) / Math.sqrt(cv$temp$129$var117))) - (0.5 * Math.log(cv$temp$129$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$128$var116) / Math.sqrt(cv$temp$129$var117))) - (0.5 * Math.log(cv$temp$129$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$128$var116) / Math.sqrt(cv$temp$129$var117))) - (0.5 * Math.log(cv$temp$129$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$366 = 1; index$i$366 < samples; index$i$366 += 1) {
																	if((!(index$i$366 == index$i$1) && !(index$i$366 == index$i$10))) {
																		for(int index$sample44$367 = 0; index$sample44$367 < noStates; index$sample44$367 += 1) {
																			int distributionTempVariable$var40$369 = index$sample44$367;
																			double cv$probabilitySample44Value368 = (1.0 * distribution$sample44[((index$i$366 - 1) / 1)][index$sample44$367]);
																			int traceTempVariable$s$370_1 = distributionTempVariable$var40$369;
																			if((index$i$366 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$370_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$370_1)) {
																								{
																									{
																										double cv$temp$130$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$370_1];
																											cv$temp$130$var116 = var116;
																										}
																										double cv$temp$131$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$370_1];
																											cv$temp$131$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample44Value368) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value368) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value368) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value368) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value368) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value368);
																									}
																								}
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
											boolean[] guard$sample44gaussian127 = guard$sample44gaussian127$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample44gaussian127[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$420_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$420_1)) {
																		if(fixedFlag$sample34) {
																			if((0 == i$var109)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$420_1)) {
																						{
																							{
																								double cv$temp$154$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$420_1];
																									cv$temp$154$var121 = var121;
																								}
																								double cv$temp$155$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$420_1];
																									cv$temp$155$var122 = var122;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$154$var121) / Math.sqrt(cv$temp$155$var122))) - (0.5 * Math.log(cv$temp$155$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$154$var121) / Math.sqrt(cv$temp$155$var122))) - (0.5 * Math.log(cv$temp$155$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$154$var121) / Math.sqrt(cv$temp$155$var122))) - (0.5 * Math.log(cv$temp$155$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$154$var121) / Math.sqrt(cv$temp$155$var122))) - (0.5 * Math.log(cv$temp$155$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$154$var121) / Math.sqrt(cv$temp$155$var122))) - (0.5 * Math.log(cv$temp$155$var122)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample34$481 = 0; index$sample34$481 < noStates; index$sample34$481 += 1) {
																					int distributionTempVariable$var30$483 = index$sample34$481;
																					double cv$probabilitySample34Value482 = (1.0 * distribution$sample34[index$sample34$481]);
																					int traceTempVariable$s$484_1 = distributionTempVariable$var30$483;
																					if((0 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$484_1)) {
																								{
																									{
																										double cv$temp$156$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$484_1];
																											cv$temp$156$var121 = var121;
																										}
																										double cv$temp$157$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$484_1];
																											cv$temp$157$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample34Value482) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value482) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value482) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value482) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value482) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value482);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$420_1)) {
																		int traceTempVariable$s$488_1 = cv$currentValue;
																		if((index$i$1 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$488_1)) {
																					{
																						{
																							double cv$temp$158$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$488_1];
																								cv$temp$158$var121 = var121;
																							}
																							double cv$temp$159$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$488_1];
																								cv$temp$159$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$158$var121) / Math.sqrt(cv$temp$159$var122))) - (0.5 * Math.log(cv$temp$159$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$158$var121) / Math.sqrt(cv$temp$159$var122))) - (0.5 * Math.log(cv$temp$159$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$158$var121) / Math.sqrt(cv$temp$159$var122))) - (0.5 * Math.log(cv$temp$159$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$158$var121) / Math.sqrt(cv$temp$159$var122))) - (0.5 * Math.log(cv$temp$159$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$158$var121) / Math.sqrt(cv$temp$159$var122))) - (0.5 * Math.log(cv$temp$159$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$489_1 = distributionTempVariable$var40$13;
																		if((index$i$10 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$489_1)) {
																					{
																						{
																							double cv$temp$160$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$489_1];
																								cv$temp$160$var121 = var121;
																							}
																							double cv$temp$161$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$489_1];
																								cv$temp$161$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$160$var121) / Math.sqrt(cv$temp$161$var122))) - (0.5 * Math.log(cv$temp$161$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$160$var121) / Math.sqrt(cv$temp$161$var122))) - (0.5 * Math.log(cv$temp$161$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$160$var121) / Math.sqrt(cv$temp$161$var122))) - (0.5 * Math.log(cv$temp$161$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$160$var121) / Math.sqrt(cv$temp$161$var122))) - (0.5 * Math.log(cv$temp$161$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$160$var121) / Math.sqrt(cv$temp$161$var122))) - (0.5 * Math.log(cv$temp$161$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$490 = 1; index$i$490 < samples; index$i$490 += 1) {
																			if((!(index$i$490 == index$i$1) && !(index$i$490 == index$i$10))) {
																				for(int index$sample44$491 = 0; index$sample44$491 < noStates; index$sample44$491 += 1) {
																					int distributionTempVariable$var40$493 = index$sample44$491;
																					double cv$probabilitySample44Value492 = (1.0 * distribution$sample44[((index$i$490 - 1) / 1)][index$sample44$491]);
																					int traceTempVariable$s$494_1 = distributionTempVariable$var40$493;
																					if((index$i$490 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$494_1)) {
																								{
																									{
																										double cv$temp$162$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$494_1];
																											cv$temp$162$var121 = var121;
																										}
																										double cv$temp$163$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$494_1];
																											cv$temp$163$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample44Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value492);
																									}
																								}
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
											int traceTempVariable$s$424_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample44gaussian127[((i$var109 - 0) / 1)]) {
														guard$sample44gaussian127[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample34) {
																	if((0 == i$var109)) {
																		for(int var74 = 0; var74 < noStates; var74 += 1) {
																			if((var74 == traceTempVariable$s$424_1)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$424_1)) {
																						{
																							{
																								double cv$temp$186$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$424_1];
																									cv$temp$186$var121 = var121;
																								}
																								double cv$temp$187$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$424_1];
																									cv$temp$187$var122 = var122;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$186$var121) / Math.sqrt(cv$temp$187$var122))) - (0.5 * Math.log(cv$temp$187$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$186$var121) / Math.sqrt(cv$temp$187$var122))) - (0.5 * Math.log(cv$temp$187$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$186$var121) / Math.sqrt(cv$temp$187$var122))) - (0.5 * Math.log(cv$temp$187$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$186$var121) / Math.sqrt(cv$temp$187$var122))) - (0.5 * Math.log(cv$temp$187$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$186$var121) / Math.sqrt(cv$temp$187$var122))) - (0.5 * Math.log(cv$temp$187$var122)))));
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
																		for(int index$sample34$550 = 0; index$sample34$550 < noStates; index$sample34$550 += 1) {
																			int distributionTempVariable$var30$552 = index$sample34$550;
																			double cv$probabilitySample34Value551 = (1.0 * distribution$sample34[index$sample34$550]);
																			int traceTempVariable$s$553_1 = distributionTempVariable$var30$552;
																			if((0 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$553_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$553_1)) {
																								{
																									{
																										double cv$temp$188$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$553_1];
																											cv$temp$188$var121 = var121;
																										}
																										double cv$temp$189$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$553_1];
																											cv$temp$189$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample34Value551) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value551) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value551) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value551) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value551) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value551);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$558_1 = cv$currentValue;
																if((index$i$1 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$558_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$558_1)) {
																					{
																						{
																							double cv$temp$190$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$558_1];
																								cv$temp$190$var121 = var121;
																							}
																							double cv$temp$191$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$558_1];
																								cv$temp$191$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$190$var121) / Math.sqrt(cv$temp$191$var122))) - (0.5 * Math.log(cv$temp$191$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$190$var121) / Math.sqrt(cv$temp$191$var122))) - (0.5 * Math.log(cv$temp$191$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$190$var121) / Math.sqrt(cv$temp$191$var122))) - (0.5 * Math.log(cv$temp$191$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$190$var121) / Math.sqrt(cv$temp$191$var122))) - (0.5 * Math.log(cv$temp$191$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$190$var121) / Math.sqrt(cv$temp$191$var122))) - (0.5 * Math.log(cv$temp$191$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$559_1 = distributionTempVariable$var40$13;
																if((index$i$10 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$559_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$559_1)) {
																					{
																						{
																							double cv$temp$192$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$559_1];
																								cv$temp$192$var121 = var121;
																							}
																							double cv$temp$193$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$559_1];
																								cv$temp$193$var122 = var122;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$192$var121) / Math.sqrt(cv$temp$193$var122))) - (0.5 * Math.log(cv$temp$193$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$192$var121) / Math.sqrt(cv$temp$193$var122))) - (0.5 * Math.log(cv$temp$193$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$192$var121) / Math.sqrt(cv$temp$193$var122))) - (0.5 * Math.log(cv$temp$193$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$192$var121) / Math.sqrt(cv$temp$193$var122))) - (0.5 * Math.log(cv$temp$193$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$192$var121) / Math.sqrt(cv$temp$193$var122))) - (0.5 * Math.log(cv$temp$193$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$560 = 1; index$i$560 < samples; index$i$560 += 1) {
																	if((!(index$i$560 == index$i$1) && !(index$i$560 == index$i$10))) {
																		for(int index$sample44$561 = 0; index$sample44$561 < noStates; index$sample44$561 += 1) {
																			int distributionTempVariable$var40$563 = index$sample44$561;
																			double cv$probabilitySample44Value562 = (1.0 * distribution$sample44[((index$i$560 - 1) / 1)][index$sample44$561]);
																			int traceTempVariable$s$564_1 = distributionTempVariable$var40$563;
																			if((index$i$560 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$564_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$564_1)) {
																								{
																									{
																										double cv$temp$194$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$564_1];
																											cv$temp$194$var121 = var121;
																										}
																										double cv$temp$195$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$564_1];
																											cv$temp$195$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample44Value562) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value562) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value562) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value562) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value562) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value562);
																									}
																								}
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
					int traceTempVariable$var37$603_1 = cv$currentValue;
					for(int index$i$603_2 = 1; index$i$603_2 < samples; index$i$603_2 += 1) {
						if((i$var34 == (index$i$603_2 - 1))) {
							{
								int index$i$605 = index$i$603_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var39;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var21 = 0; var21 < noStates; var21 += 1) {
									if((var21 == traceTempVariable$var37$603_1)) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample34) {
												if((0 == (i$var34 - 1))) {
													for(int index$var21$612_1 = 0; index$var21$612_1 < noStates; index$var21$612_1 += 1) {
														if((index$var21$612_1 == st[(i$var34 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample34$608 = 0; index$sample34$608 < noStates; index$sample34$608 += 1) {
														int distributionTempVariable$var30$610 = index$sample34$608;
														double cv$probabilitySample34Value609 = (1.0 * distribution$sample34[index$sample34$608]);
														int traceTempVariable$var37$611_1 = distributionTempVariable$var30$610;
														if((0 == (i$var34 - 1))) {
															for(int index$var21$613_1 = 0; index$var21$613_1 < noStates; index$var21$613_1 += 1) {
																if((index$var21$613_1 == traceTempVariable$var37$611_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample34Value609);
															}
														}
													}
												}
											}
											int traceTempVariable$var37$614_1 = cv$currentValue;
											if((index$i$1 == (i$var34 - 1))) {
												for(int index$var21$620_1 = 0; index$var21$620_1 < noStates; index$var21$620_1 += 1) {
													if((index$var21$620_1 == traceTempVariable$var37$614_1))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$615 = 1; index$i$615 < samples; index$i$615 += 1) {
												if((!(index$i$615 == index$i$1) && !(index$i$615 == index$i$605))) {
													for(int index$sample44$616 = 0; index$sample44$616 < noStates; index$sample44$616 += 1) {
														int distributionTempVariable$var40$618 = index$sample44$616;
														double cv$probabilitySample44Value617 = (1.0 * distribution$sample44[((index$i$615 - 1) / 1)][index$sample44$616]);
														int traceTempVariable$var37$619_1 = distributionTempVariable$var40$618;
														if((index$i$615 == (i$var34 - 1))) {
															for(int index$var21$621_1 = 0; index$var21$621_1 < noStates; index$var21$621_1 += 1) {
																if((index$var21$621_1 == traceTempVariable$var37$619_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample44Value617);
															}
														}
													}
												}
											}
											double[] cv$temp$196$var38;
											{
												double[] var38 = m[traceTempVariable$var37$603_1];
												cv$temp$196$var38 = var38;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$196$var38);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample44[((index$i$603_2 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample44[((i$var34 - 1) / 1)];
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample57(int var52) {
		double cv$originalValue = cpuMean[var52];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var53 = cv$proposedValue;
					cpuMean[var52] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var47;
				{
					cv$temp$0$var47 = 16.0;
				}
				double cv$temp$1$var46;
				{
					cv$temp$1$var46 = 8.6;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var47) / Math.sqrt(cv$temp$1$var46))) - (0.5 * Math.log(cv$temp$1$var46))));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var111$7_1 = cv$currentValue;
									if((var52 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var84 = 0; var84 < noStates; var84 += 1) {
														if((var84 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var111;
																	{
																		double var111 = traceTempVariable$var111$7_1;
																		cv$temp$2$var111 = var111;
																	}
																	double cv$temp$3$var112;
																	{
																		double var112 = cpuVar[st[i$var109]];
																		cv$temp$3$var112 = var112;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var84 = 0; var84 < noStates; var84 += 1) {
																if((var84 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var111;
																			{
																				double var111 = traceTempVariable$var111$7_1;
																				cv$temp$4$var111 = var111;
																			}
																			double cv$temp$5$var112;
																			{
																				double var112 = cpuVar[st[i$var109]];
																				cv$temp$5$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var111;
																					{
																						double var111 = traceTempVariable$var111$7_1;
																						cv$temp$6$var111 = var111;
																					}
																					double cv$temp$7$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$29_1];
																						cv$temp$7$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var111$8_1 = cv$currentValue;
											if((var52 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var84 = 0; var84 < noStates; var84 += 1) {
																if((var84 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var111;
																			{
																				double var111 = traceTempVariable$var111$8_1;
																				cv$temp$8$var111 = var111;
																			}
																			double cv$temp$9$var112;
																			{
																				double var112 = cpuVar[traceTempVariable$s$32_1];
																				cv$temp$9$var112 = var112;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var111;
																					{
																						double var111 = traceTempVariable$var111$8_1;
																						cv$temp$10$var111 = var111;
																					}
																					double cv$temp$11$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$36_1];
																						cv$temp$11$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var111;
																					{
																						double var111 = traceTempVariable$var111$8_1;
																						cv$temp$12$var111 = var111;
																					}
																					double cv$temp$13$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$6_1];
																						cv$temp$13$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var111;
																							{
																								double var111 = traceTempVariable$var111$8_1;
																								cv$temp$14$var111 = var111;
																							}
																							double cv$temp$15$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$44_1];
																								cv$temp$15$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var111$16_1 = cv$currentValue;
										if((var52 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var84 = 0; var84 < noStates; var84 += 1) {
																if((var84 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var111;
																			{
																				double var111 = traceTempVariable$var111$16_1;
																				cv$temp$16$var111 = var111;
																			}
																			double cv$temp$17$var112;
																			{
																				double var112 = cpuVar[st[i$var109]];
																				cv$temp$17$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var111;
																					{
																						double var111 = traceTempVariable$var111$16_1;
																						cv$temp$18$var111 = var111;
																					}
																					double cv$temp$19$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$51_1];
																						cv$temp$19$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var84 = 0; var84 < noStates; var84 += 1) {
																if((var84 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var111;
																			{
																				double var111 = traceTempVariable$var111$16_1;
																				cv$temp$20$var111 = var111;
																			}
																			double cv$temp$21$var112;
																			{
																				double var112 = cpuVar[st[i$var109]];
																				cv$temp$21$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var111$17_1 = cv$currentValue;
												if((var52 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var111;
																					{
																						double var111 = traceTempVariable$var111$17_1;
																						cv$temp$22$var111 = var111;
																					}
																					double cv$temp$23$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$15_1];
																						cv$temp$23$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var111;
																							{
																								double var111 = traceTempVariable$var111$17_1;
																								cv$temp$24$var111 = var111;
																							}
																							double cv$temp$25$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$60_1];
																								cv$temp$25$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var111;
																				{
																					double var111 = traceTempVariable$var111$17_1;
																					cv$temp$26$var111 = var111;
																				}
																				double cv$temp$27$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$63_1];
																					cv$temp$27$var112 = var112;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var111;
																							{
																								double var111 = traceTempVariable$var111$17_1;
																								cv$temp$28$var111 = var111;
																							}
																							double cv$temp$29$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$68_1];
																								cv$temp$29$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var53 = cv$originalValue;
			cpuMean[var52] = var53;
		}
	}

	private final void sample68(int var63) {
		double cv$originalValue = memMean[var63];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var64 = cv$proposedValue;
					memMean[var63] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var57;
				{
					cv$temp$0$var57 = 94.0;
				}
				double cv$temp$1$var58;
				{
					cv$temp$1$var58 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var57) / Math.sqrt(cv$temp$1$var58))) - (0.5 * Math.log(cv$temp$1$var58))));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var116$7_1 = cv$currentValue;
									if((var63 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var94 = 0; var94 < noStates; var94 += 1) {
														if((var94 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var116;
																	{
																		double var116 = traceTempVariable$var116$7_1;
																		cv$temp$2$var116 = var116;
																	}
																	double cv$temp$3$var117;
																	{
																		double var117 = memVar[st[i$var109]];
																		cv$temp$3$var117 = var117;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var94 = 0; var94 < noStates; var94 += 1) {
																if((var94 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var116;
																			{
																				double var116 = traceTempVariable$var116$7_1;
																				cv$temp$4$var116 = var116;
																			}
																			double cv$temp$5$var117;
																			{
																				double var117 = memVar[st[i$var109]];
																				cv$temp$5$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var116;
																					{
																						double var116 = traceTempVariable$var116$7_1;
																						cv$temp$6$var116 = var116;
																					}
																					double cv$temp$7$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$29_1];
																						cv$temp$7$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var116$8_1 = cv$currentValue;
											if((var63 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var94 = 0; var94 < noStates; var94 += 1) {
																if((var94 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var116;
																			{
																				double var116 = traceTempVariable$var116$8_1;
																				cv$temp$8$var116 = var116;
																			}
																			double cv$temp$9$var117;
																			{
																				double var117 = memVar[traceTempVariable$s$32_1];
																				cv$temp$9$var117 = var117;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var116;
																					{
																						double var116 = traceTempVariable$var116$8_1;
																						cv$temp$10$var116 = var116;
																					}
																					double cv$temp$11$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$36_1];
																						cv$temp$11$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var116;
																					{
																						double var116 = traceTempVariable$var116$8_1;
																						cv$temp$12$var116 = var116;
																					}
																					double cv$temp$13$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$6_1];
																						cv$temp$13$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var116;
																							{
																								double var116 = traceTempVariable$var116$8_1;
																								cv$temp$14$var116 = var116;
																							}
																							double cv$temp$15$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$44_1];
																								cv$temp$15$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var116$16_1 = cv$currentValue;
										if((var63 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var94 = 0; var94 < noStates; var94 += 1) {
																if((var94 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var116;
																			{
																				double var116 = traceTempVariable$var116$16_1;
																				cv$temp$16$var116 = var116;
																			}
																			double cv$temp$17$var117;
																			{
																				double var117 = memVar[st[i$var109]];
																				cv$temp$17$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var116;
																					{
																						double var116 = traceTempVariable$var116$16_1;
																						cv$temp$18$var116 = var116;
																					}
																					double cv$temp$19$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$51_1];
																						cv$temp$19$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var94 = 0; var94 < noStates; var94 += 1) {
																if((var94 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var116;
																			{
																				double var116 = traceTempVariable$var116$16_1;
																				cv$temp$20$var116 = var116;
																			}
																			double cv$temp$21$var117;
																			{
																				double var117 = memVar[st[i$var109]];
																				cv$temp$21$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var116$17_1 = cv$currentValue;
												if((var63 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var116;
																					{
																						double var116 = traceTempVariable$var116$17_1;
																						cv$temp$22$var116 = var116;
																					}
																					double cv$temp$23$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$15_1];
																						cv$temp$23$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var116;
																							{
																								double var116 = traceTempVariable$var116$17_1;
																								cv$temp$24$var116 = var116;
																							}
																							double cv$temp$25$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$60_1];
																								cv$temp$25$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var116;
																				{
																					double var116 = traceTempVariable$var116$17_1;
																					cv$temp$26$var116 = var116;
																				}
																				double cv$temp$27$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$63_1];
																					cv$temp$27$var117 = var117;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var116;
																							{
																								double var116 = traceTempVariable$var116$17_1;
																								cv$temp$28$var116 = var116;
																							}
																							double cv$temp$29$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$68_1];
																								cv$temp$29$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var64 = cv$originalValue;
			memMean[var63] = var64;
		}
	}

	private final void sample79(int var74) {
		double cv$originalValue = pageFaultsMean[var74];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var75 = cv$proposedValue;
					pageFaultsMean[var74] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var68;
				{
					cv$temp$0$var68 = 814.0;
				}
				double cv$temp$1$var69;
				{
					cv$temp$1$var69 = 335550.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var68) / Math.sqrt(cv$temp$1$var69))) - (0.5 * Math.log(cv$temp$1$var69))));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var121$7_1 = cv$currentValue;
									if((var74 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var104 = 0; var104 < noStates; var104 += 1) {
														if((var104 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var121;
																	{
																		double var121 = traceTempVariable$var121$7_1;
																		cv$temp$2$var121 = var121;
																	}
																	double cv$temp$3$var122;
																	{
																		double var122 = pageFaultsVar[st[i$var109]];
																		cv$temp$3$var122 = var122;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$2$var121) / Math.sqrt(cv$temp$3$var122))) - (0.5 * Math.log(cv$temp$3$var122)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var104 = 0; var104 < noStates; var104 += 1) {
																if((var104 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var121;
																			{
																				double var121 = traceTempVariable$var121$7_1;
																				cv$temp$4$var121 = var121;
																			}
																			double cv$temp$5$var122;
																			{
																				double var122 = pageFaultsVar[st[i$var109]];
																				cv$temp$5$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$4$var121) / Math.sqrt(cv$temp$5$var122))) - (0.5 * Math.log(cv$temp$5$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var121;
																					{
																						double var121 = traceTempVariable$var121$7_1;
																						cv$temp$6$var121 = var121;
																					}
																					double cv$temp$7$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$29_1];
																						cv$temp$7$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var121$8_1 = cv$currentValue;
											if((var74 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var104 = 0; var104 < noStates; var104 += 1) {
																if((var104 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var121;
																			{
																				double var121 = traceTempVariable$var121$8_1;
																				cv$temp$8$var121 = var121;
																			}
																			double cv$temp$9$var122;
																			{
																				double var122 = pageFaultsVar[traceTempVariable$s$32_1];
																				cv$temp$9$var122 = var122;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var121;
																					{
																						double var121 = traceTempVariable$var121$8_1;
																						cv$temp$10$var121 = var121;
																					}
																					double cv$temp$11$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$36_1];
																						cv$temp$11$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var121;
																					{
																						double var121 = traceTempVariable$var121$8_1;
																						cv$temp$12$var121 = var121;
																					}
																					double cv$temp$13$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$6_1];
																						cv$temp$13$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var121;
																							{
																								double var121 = traceTempVariable$var121$8_1;
																								cv$temp$14$var121 = var121;
																							}
																							double cv$temp$15$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$44_1];
																								cv$temp$15$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var121$16_1 = cv$currentValue;
										if((var74 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var104 = 0; var104 < noStates; var104 += 1) {
																if((var104 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var121;
																			{
																				double var121 = traceTempVariable$var121$16_1;
																				cv$temp$16$var121 = var121;
																			}
																			double cv$temp$17$var122;
																			{
																				double var122 = pageFaultsVar[st[i$var109]];
																				cv$temp$17$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$16$var121) / Math.sqrt(cv$temp$17$var122))) - (0.5 * Math.log(cv$temp$17$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var121;
																					{
																						double var121 = traceTempVariable$var121$16_1;
																						cv$temp$18$var121 = var121;
																					}
																					double cv$temp$19$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$51_1];
																						cv$temp$19$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var104 = 0; var104 < noStates; var104 += 1) {
																if((var104 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var121;
																			{
																				double var121 = traceTempVariable$var121$16_1;
																				cv$temp$20$var121 = var121;
																			}
																			double cv$temp$21$var122;
																			{
																				double var122 = pageFaultsVar[st[i$var109]];
																				cv$temp$21$var122 = var122;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$20$var121) / Math.sqrt(cv$temp$21$var122))) - (0.5 * Math.log(cv$temp$21$var122)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var121$17_1 = cv$currentValue;
												if((var74 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var121;
																					{
																						double var121 = traceTempVariable$var121$17_1;
																						cv$temp$22$var121 = var121;
																					}
																					double cv$temp$23$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$15_1];
																						cv$temp$23$var122 = var122;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var121;
																							{
																								double var121 = traceTempVariable$var121$17_1;
																								cv$temp$24$var121 = var121;
																							}
																							double cv$temp$25$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$60_1];
																								cv$temp$25$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var121;
																				{
																					double var121 = traceTempVariable$var121$17_1;
																					cv$temp$26$var121 = var121;
																				}
																				double cv$temp$27$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$63_1];
																					cv$temp$27$var122 = var122;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var121;
																							{
																								double var121 = traceTempVariable$var121$17_1;
																								cv$temp$28$var121 = var121;
																							}
																							double cv$temp$29$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$68_1];
																								cv$temp$29$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var75 = cv$originalValue;
			pageFaultsMean[var74] = var75;
		}
	}

	private final void sample89(int var84) {
		double cv$originalValue = cpuVar[var84];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var85 = cv$proposedValue;
					cpuVar[var84] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var79;
				{
					cv$temp$0$var79 = 5.0;
				}
				double cv$temp$1$var78;
				{
					cv$temp$1$var78 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var79, cv$temp$1$var78));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var112$7_1 = cv$currentValue;
									if((var84 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var52 = 0; var52 < noStates; var52 += 1) {
														if((var52 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var111;
																	{
																		double var111 = cpuMean[st[i$var109]];
																		cv$temp$2$var111 = var111;
																	}
																	double cv$temp$3$var112;
																	{
																		double var112 = traceTempVariable$var112$7_1;
																		cv$temp$3$var112 = var112;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$2$var111) / Math.sqrt(cv$temp$3$var112))) - (0.5 * Math.log(cv$temp$3$var112)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var111;
																			{
																				double var111 = cpuMean[st[i$var109]];
																				cv$temp$4$var111 = var111;
																			}
																			double cv$temp$5$var112;
																			{
																				double var112 = traceTempVariable$var112$7_1;
																				cv$temp$5$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$29_1];
																						cv$temp$6$var111 = var111;
																					}
																					double cv$temp$7$var112;
																					{
																						double var112 = traceTempVariable$var112$7_1;
																						cv$temp$7$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var112$8_1 = cv$currentValue;
											if((var84 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var111;
																			{
																				double var111 = cpuMean[traceTempVariable$s$32_1];
																				cv$temp$8$var111 = var111;
																			}
																			double cv$temp$9$var112;
																			{
																				double var112 = traceTempVariable$var112$8_1;
																				cv$temp$9$var112 = var112;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$36_1];
																						cv$temp$10$var111 = var111;
																					}
																					double cv$temp$11$var112;
																					{
																						double var112 = traceTempVariable$var112$8_1;
																						cv$temp$11$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$6_1];
																						cv$temp$12$var111 = var111;
																					}
																					double cv$temp$13$var112;
																					{
																						double var112 = traceTempVariable$var112$8_1;
																						cv$temp$13$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var52 = 0; var52 < noStates; var52 += 1) {
																				if((var52 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$44_1];
																								cv$temp$14$var111 = var111;
																							}
																							double cv$temp$15$var112;
																							{
																								double var112 = traceTempVariable$var112$8_1;
																								cv$temp$15$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var112$16_1 = cv$currentValue;
										if((var84 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var111;
																			{
																				double var111 = cpuMean[st[i$var109]];
																				cv$temp$16$var111 = var111;
																			}
																			double cv$temp$17$var112;
																			{
																				double var112 = traceTempVariable$var112$16_1;
																				cv$temp$17$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$51_1];
																						cv$temp$18$var111 = var111;
																					}
																					double cv$temp$19$var112;
																					{
																						double var112 = traceTempVariable$var112$16_1;
																						cv$temp$19$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var111;
																			{
																				double var111 = cpuMean[st[i$var109]];
																				cv$temp$20$var111 = var111;
																			}
																			double cv$temp$21$var112;
																			{
																				double var112 = traceTempVariable$var112$16_1;
																				cv$temp$21$var112 = var112;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var112$17_1 = cv$currentValue;
												if((var84 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$15_1];
																						cv$temp$22$var111 = var111;
																					}
																					double cv$temp$23$var112;
																					{
																						double var112 = traceTempVariable$var112$17_1;
																						cv$temp$23$var112 = var112;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var52 = 0; var52 < noStates; var52 += 1) {
																				if((var52 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$60_1];
																								cv$temp$24$var111 = var111;
																							}
																							double cv$temp$25$var112;
																							{
																								double var112 = traceTempVariable$var112$17_1;
																								cv$temp$25$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$63_1];
																					cv$temp$26$var111 = var111;
																				}
																				double cv$temp$27$var112;
																				{
																					double var112 = traceTempVariable$var112$17_1;
																					cv$temp$27$var112 = var112;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var52 = 0; var52 < noStates; var52 += 1) {
																				if((var52 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$68_1];
																								cv$temp$28$var111 = var111;
																							}
																							double cv$temp$29$var112;
																							{
																								double var112 = traceTempVariable$var112$17_1;
																								cv$temp$29$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var85 = cv$originalValue;
			cpuVar[var84] = var85;
		}
	}

	private final void sample99(int var94) {
		double cv$originalValue = memVar[var94];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var95 = cv$proposedValue;
					memVar[var94] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var89;
				{
					cv$temp$0$var89 = 5.0;
				}
				double cv$temp$1$var88;
				{
					cv$temp$1$var88 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var89, cv$temp$1$var88));
				{
					{
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample34) {
								if((0 == i$var109)) {
									double traceTempVariable$var117$7_1 = cv$currentValue;
									if((var94 == st[i$var109])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var109)) {
													for(int var63 = 0; var63 < noStates; var63 += 1) {
														if((var63 == st[i$var109])) {
															{
																{
																	double cv$temp$2$var116;
																	{
																		double var116 = memMean[st[i$var109]];
																		cv$temp$2$var116 = var116;
																	}
																	double cv$temp$3$var117;
																	{
																		double var117 = traceTempVariable$var117$7_1;
																		cv$temp$3$var117 = var117;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$2$var116) / Math.sqrt(cv$temp$3$var117))) - (0.5 * Math.log(cv$temp$3$var117)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample44) {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if((i$var34 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == st[i$var109])) {
																	{
																		{
																			double cv$temp$4$var116;
																			{
																				double var116 = memMean[st[i$var109]];
																				cv$temp$4$var116 = var116;
																			}
																			double cv$temp$5$var117;
																			{
																				double var117 = traceTempVariable$var117$7_1;
																				cv$temp$5$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$4$var116) / Math.sqrt(cv$temp$5$var117))) - (0.5 * Math.log(cv$temp$5$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
														if(true) {
															for(int index$sample44$26 = 0; index$sample44$26 < noStates; index$sample44$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample44$26;
																double cv$probabilitySample44Value27 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var40$28;
																if((i$var34 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$29_1];
																						cv$temp$6$var116 = var116;
																					}
																					double cv$temp$7$var117;
																					{
																						double var117 = traceTempVariable$var117$7_1;
																						cv$temp$7$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value27);
																				}
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
									for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample34$3;
										double cv$probabilitySample34Value4 = (1.0 * distribution$sample34[index$sample34$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var30$5;
										if((0 == i$var109)) {
											double traceTempVariable$var117$8_1 = cv$currentValue;
											if((var94 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var30$5;
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var116;
																			{
																				double var116 = memMean[traceTempVariable$s$32_1];
																				cv$temp$8$var116 = var116;
																			}
																			double cv$temp$9$var117;
																			{
																				double var117 = traceTempVariable$var117$8_1;
																				cv$temp$9$var117 = var117;
																			}
																			if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample34$33 = 0; index$sample34$33 < noStates; index$sample34$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample34$33;
																double cv$probabilitySample34Value34 = (cv$probabilitySample34Value4 * distribution$sample34[index$sample34$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var30$35;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$36_1];
																						cv$temp$10$var116 = var116;
																					}
																					double cv$temp$11$var117;
																					{
																						double var117 = traceTempVariable$var117$8_1;
																						cv$temp$11$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample44) {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if((i$var34 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$6_1];
																						cv$temp$12$var116 = var116;
																					}
																					double cv$temp$13$var117;
																					{
																						double var117 = traceTempVariable$var117$8_1;
																						cv$temp$13$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample44$41 = 0; index$sample44$41 < noStates; index$sample44$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample44$41;
																		double cv$probabilitySample44Value42 = (cv$probabilitySample34Value4 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var40$43;
																		if((i$var34 == i$var109)) {
																			for(int var63 = 0; var63 < noStates; var63 += 1) {
																				if((var63 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$44_1];
																								cv$temp$14$var116 = var116;
																							}
																							double cv$temp$15$var117;
																							{
																								double var117 = traceTempVariable$var117$8_1;
																								cv$temp$15$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value42);
																						}
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
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if(fixedFlag$sample44) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var117$16_1 = cv$currentValue;
										if((var94 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample34) {
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == st[i$var109])) {
																	{
																		{
																			double cv$temp$16$var116;
																			{
																				double var116 = memMean[st[i$var109]];
																				cv$temp$16$var116 = var116;
																			}
																			double cv$temp$17$var117;
																			{
																				double var117 = traceTempVariable$var117$16_1;
																				cv$temp$17$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$16$var116) / Math.sqrt(cv$temp$17$var117))) - (0.5 * Math.log(cv$temp$17$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample34$48 = 0; index$sample34$48 < noStates; index$sample34$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample34$48;
																double cv$probabilitySample34Value49 = (1.0 * distribution$sample34[index$sample34$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var30$50;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$51_1];
																						cv$temp$18$var116 = var116;
																					}
																					double cv$temp$19$var117;
																					{
																						double var117 = traceTempVariable$var117$16_1;
																						cv$temp$19$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == st[i$var109])) {
																	{
																		{
																			double cv$temp$20$var116;
																			{
																				double var116 = memMean[st[i$var109]];
																				cv$temp$20$var116 = var116;
																			}
																			double cv$temp$21$var117;
																			{
																				double var117 = traceTempVariable$var117$16_1;
																				cv$temp$21$var117 = var117;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))));
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
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if(true) {
										for(int index$sample44$12 = 0; index$sample44$12 < noStates; index$sample44$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample44$12;
											double cv$probabilitySample44Value13 = (1.0 * distribution$sample44[((i$var34 - 1) / 1)][index$sample44$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var117$17_1 = cv$currentValue;
												if((var94 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample34) {
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$15_1];
																						cv$temp$22$var116 = var116;
																					}
																					double cv$temp$23$var117;
																					{
																						double var117 = traceTempVariable$var117$17_1;
																						cv$temp$23$var117 = var117;
																					}
																					if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample34$57 = 0; index$sample34$57 < noStates; index$sample34$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample34$57;
																		double cv$probabilitySample34Value58 = (cv$probabilitySample44Value13 * distribution$sample34[index$sample34$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var30$59;
																		if((0 == i$var109)) {
																			for(int var63 = 0; var63 < noStates; var63 += 1) {
																				if((var63 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$60_1];
																								cv$temp$24$var116 = var116;
																							}
																							double cv$temp$25$var117;
																							{
																								double var117 = traceTempVariable$var117$17_1;
																								cv$temp$25$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample34Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var40$14;
															if((i$var34 == i$var109)) {
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$63_1];
																					cv$temp$26$var116 = var116;
																				}
																				double cv$temp$27$var117;
																				{
																					double var117 = traceTempVariable$var117$17_1;
																					cv$temp$27$var117 = var117;
																				}
																				if(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample44$65 = 0; index$sample44$65 < noStates; index$sample44$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample44$65;
																		double cv$probabilitySample44Value66 = (cv$probabilitySample44Value13 * distribution$sample44[((index$i$64 - 1) / 1)][index$sample44$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var40$67;
																		if((index$i$64 == i$var109)) {
																			for(int var63 = 0; var63 < noStates; var63 += 1) {
																				if((var63 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$68_1];
																								cv$temp$28$var116 = var116;
																							}
																							double cv$temp$29$var117;
																							{
																								double var117 = traceTempVariable$var117$17_1;
																								cv$temp$29$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample44Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample44Value66);
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
			double var95 = cv$originalValue;
			memVar[var94] = var95;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var21 = 0; var21 < noStates; var21 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var22$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var27$countGlobal = new double[cv$max];
		}
		{
			int cv$var23$max = noStates;
			cv$distributionAccumulator$var39 = new double[cv$var23$max];
		}
		{
			cv$var30$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample34gaussian117$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample34gaussian122$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample34gaussian127$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$var23$max = noStates;
			cv$var40$stateProbabilityGlobal = new double[cv$var23$max];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample44gaussian117$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample44gaussian122$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample44gaussian127$global = new boolean[cv$max_i$var109];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var21 = 0; var21 < noStates; var21 += 1)
					m[var21] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$cpu_measured];
			}
		}
		if(!setFlag$initialStateDistribution) {
			{
				initialStateDistribution = new double[noStates];
			}
		}
		if(!setFlag$cpu) {
			{
				cpu = new double[length$cpu_measured];
			}
		}
		if(!setFlag$mem) {
			{
				mem = new double[length$cpu_measured];
			}
		}
		if(!setFlag$pageFaults) {
			{
				pageFaults = new double[length$cpu_measured];
			}
		}
		if(!setFlag$cpuMean) {
			{
				cpuMean = new double[noStates];
			}
		}
		if(!setFlag$memMean) {
			{
				memMean = new double[noStates];
			}
		}
		if(!setFlag$pageFaultsMean) {
			{
				pageFaultsMean = new double[noStates];
			}
		}
		if(!setFlag$cpuVar) {
			{
				cpuVar = new double[noStates];
			}
		}
		if(!setFlag$memVar) {
			{
				memVar = new double[noStates];
			}
		}
		if(!setFlag$pageFaultsVar) {
			{
				pageFaultsVar = new double[noStates];
			}
		}
		{
			distribution$sample34 = new double[noStates];
		}
		{
			distribution$sample44 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)][];
			for(int i$var34 = 1; i$var34 < length$cpu_measured; i$var34 += 1)
				distribution$sample44[((i$var34 - 1) / 1)] = new double[noStates];
		}
		{
			logProbability$var39 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample44 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var113 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample118 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var118 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample123 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var123 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample128 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var21 = 0; var21 < noStates; var21 += 1) {
			double[] var22 = m[var21];
			if(!fixedFlag$sample25)
				DistributionSampling.sampleDirichlet(RNG$, v, var22);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample44)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		for(int var52 = 0; var52 < noStates; var52 += 1) {
			if(!fixedFlag$sample57)
				cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var63 = 0; var63 < noStates; var63 += 1) {
			if(!fixedFlag$sample68)
				memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var84 = 0; var84 < noStates; var84 += 1) {
			if(!fixedFlag$sample89)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var94 = 0; var94 < noStates; var94 += 1) {
			if(!fixedFlag$sample99)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var104 = 0; var104 < noStates; var104 += 1) {
			if(!fixedFlag$sample109)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			if(!fixedFlag$sample118)
				cpu[i$var109] = ((Math.sqrt(cpuVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var109]]);
			if(!fixedFlag$sample123)
				mem[i$var109] = ((Math.sqrt(memVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var109]]);
			if(!fixedFlag$sample128)
				pageFaults[i$var109] = ((Math.sqrt(pageFaultsVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var109]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var21 = 0; var21 < noStates; var21 += 1) {
			double[] var22 = m[var21];
			if(!fixedFlag$sample25)
				DistributionSampling.sampleDirichlet(RNG$, v, var22);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		double[] cv$distribution$sample34 = distribution$sample34;
		for(int index$var29 = 0; index$var29 < noStates; index$var29 += 1) {
			double cv$value = (((0.0 <= index$var29) && (index$var29 < initialStateDistribution.length))?initialStateDistribution[index$var29]:0.0);
			if(!fixedFlag$sample34)
				cv$distribution$sample34[index$var29] = cv$value;
		}
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			double[] cv$distribution$sample44 = distribution$sample44[((i$var34 - 1) / 1)];
			for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1) {
				if(!fixedFlag$sample44)
					cv$distribution$sample44[index$var39] = 0.0;
			}
			if(fixedFlag$sample34) {
				if((0 == (i$var34 - 1))) {
					for(int var21 = 0; var21 < noStates; var21 += 1) {
						if((var21 == st[(i$var34 - 1)])) {
							{
								if(!fixedFlag$sample44) {
									double[] var38 = m[st[(i$var34 - 1)]];
									for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
										cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (1.0 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample34$2 = 0; index$sample34$2 < noStates; index$sample34$2 += 1) {
						int distributionTempVariable$var30$4 = index$sample34$2;
						double cv$probabilitySample34Value3 = (1.0 * distribution$sample34[index$sample34$2]);
						int traceTempVariable$var37$5_1 = distributionTempVariable$var30$4;
						if((0 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$5_1)) {
									{
										if(!fixedFlag$sample44) {
											double[] var38 = m[traceTempVariable$var37$5_1];
											for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
												cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (cv$probabilitySample34Value3 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample44) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var34 - 1))) {
						for(int var21 = 0; var21 < noStates; var21 += 1) {
							if((var21 == st[(i$var34 - 1)])) {
								{
									if(!fixedFlag$sample44) {
										double[] var38 = m[st[(i$var34 - 1)]];
										for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
											cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (1.0 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample44$10 = 0; index$sample44$10 < noStates; index$sample44$10 += 1) {
							int distributionTempVariable$var40$12 = index$sample44$10;
							double cv$probabilitySample44Value11 = (1.0 * distribution$sample44[((index$i$9 - 1) / 1)][index$sample44$10]);
							int traceTempVariable$var37$13_1 = distributionTempVariable$var40$12;
							if((index$i$9 == (i$var34 - 1))) {
								for(int var21 = 0; var21 < noStates; var21 += 1) {
									if((var21 == traceTempVariable$var37$13_1)) {
										{
											if(!fixedFlag$sample44) {
												double[] var38 = m[traceTempVariable$var37$13_1];
												for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
													cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (cv$probabilitySample44Value11 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var39$sum = 0.0;
			for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1) {
				if(!fixedFlag$sample44)
					cv$var39$sum = (cv$var39$sum + cv$distribution$sample44[index$var39]);
			}
			for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1) {
				if(!fixedFlag$sample44)
					cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] / cv$var39$sum);
			}
		}
		for(int var52 = 0; var52 < noStates; var52 += 1) {
			if(!fixedFlag$sample57)
				cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var63 = 0; var63 < noStates; var63 += 1) {
			if(!fixedFlag$sample68)
				memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var84 = 0; var84 < noStates; var84 += 1) {
			if(!fixedFlag$sample89)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var94 = 0; var94 < noStates; var94 += 1) {
			if(!fixedFlag$sample99)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var104 = 0; var104 < noStates; var104 += 1) {
			if(!fixedFlag$sample109)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var21 = 0; var21 < noStates; var21 += 1) {
			double[] var22 = m[var21];
			if(!fixedFlag$sample25)
				DistributionSampling.sampleDirichlet(RNG$, v, var22);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample44)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		for(int var52 = 0; var52 < noStates; var52 += 1) {
			if(!fixedFlag$sample57)
				cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var63 = 0; var63 < noStates; var63 += 1) {
			if(!fixedFlag$sample68)
				memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var84 = 0; var84 < noStates; var84 += 1) {
			if(!fixedFlag$sample89)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var94 = 0; var94 < noStates; var94 += 1) {
			if(!fixedFlag$sample99)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var104 = 0; var104 < noStates; var104 += 1) {
			if(!fixedFlag$sample109)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var21 = 0; var21 < noStates; var21 += 1) {
				if(!fixedFlag$sample25)
					sample25(var21);
			}
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample34)
				sample34();
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				if(!fixedFlag$sample44)
					sample44(i$var34);
			}
			for(int var52 = 0; var52 < noStates; var52 += 1) {
				if(!fixedFlag$sample57)
					sample57(var52);
			}
			for(int var63 = 0; var63 < noStates; var63 += 1) {
				if(!fixedFlag$sample68)
					sample68(var63);
			}
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var84 = 0; var84 < noStates; var84 += 1) {
				if(!fixedFlag$sample89)
					sample89(var84);
			}
			for(int var94 = 0; var94 < noStates; var94 += 1) {
				if(!fixedFlag$sample99)
					sample99(var94);
			}
			for(int var104 = 0; var104 < noStates; var104 += 1) {
				if(!fixedFlag$sample109)
					sample109(var104);
			}
		} else {
			for(int var104 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var104 >= ((0 - 1) + 1); var104 -= 1) {
				if(!fixedFlag$sample109)
					sample109(var104);
			}
			for(int var94 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var94 >= ((0 - 1) + 1); var94 -= 1) {
				if(!fixedFlag$sample99)
					sample99(var94);
			}
			for(int var84 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var84 >= ((0 - 1) + 1); var84 -= 1) {
				if(!fixedFlag$sample89)
					sample89(var84);
			}
			for(int var74 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var74 >= ((0 - 1) + 1); var74 -= 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var63 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var63 >= ((0 - 1) + 1); var63 -= 1) {
				if(!fixedFlag$sample68)
					sample68(var63);
			}
			for(int var52 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var52 >= ((0 - 1) + 1); var52 -= 1) {
				if(!fixedFlag$sample57)
					sample57(var52);
			}
			for(int i$var34 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var34 >= ((1 - 1) + 1); i$var34 -= 1) {
				if(!fixedFlag$sample44)
					sample44(i$var34);
			}
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample31)
				sample31();
			for(int var21 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var21 >= ((0 - 1) + 1); var21 -= 1) {
				if(!fixedFlag$sample25)
					sample25(var21);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
		samples = length$cpu_measured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var17 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample25)
			logProbability$var22 = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var30 = 0.0;
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
			logProbability$var39[((i$var34 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample44) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				logProbability$sample44[((i$var34 - 1) / 1)] = 0.0;
		}
		logProbability$var48 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var53 = 0.0;
		logProbability$var59 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var64 = 0.0;
		logProbability$var70 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample79)
			logProbability$var75 = 0.0;
		logProbability$var80 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var85 = 0.0;
		logProbability$var90 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample99)
			logProbability$var95 = 0.0;
		logProbability$var100 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample109)
			logProbability$var105 = 0.0;
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var113[((i$var109 - 0) / 1)] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample118) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample118[((i$var109 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var118[((i$var109 - 0) / 1)] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample123) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample123[((i$var109 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var123[((i$var109 - 0) / 1)] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample128) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample128[((i$var109 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample25)
			logProbabilityValue$sample25();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		if(fixedFlag$sample89)
			logProbabilityValue$sample89();
		if(fixedFlag$sample99)
			logProbabilityValue$sample99();
		if(fixedFlag$sample109)
			logProbabilityValue$sample109();
		logProbabilityValue$sample118();
		logProbabilityValue$sample123();
		logProbabilityValue$sample128();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample31();
		logProbabilityDistribution$sample34();
		logProbabilityDistribution$sample44();
		logProbabilityValue$sample57();
		logProbabilityValue$sample68();
		logProbabilityValue$sample79();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
		logProbabilityValue$sample109();
		logProbabilityDistribution$sample118();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample128();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample31();
		logProbabilityValue$sample34();
		logProbabilityValue$sample44();
		logProbabilityValue$sample57();
		logProbabilityValue$sample68();
		logProbabilityValue$sample79();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
		logProbabilityValue$sample109();
		logProbabilityValue$sample118();
		logProbabilityValue$sample123();
		logProbabilityValue$sample128();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var21 = 0; var21 < noStates; var21 += 1) {
			double[] var22 = m[var21];
			if(!fixedFlag$sample25)
				DistributionSampling.sampleDirichlet(RNG$, v, var22);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample44)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		for(int var52 = 0; var52 < noStates; var52 += 1) {
			if(!fixedFlag$sample57)
				cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$)) + 16.0);
		}
		for(int var63 = 0; var63 < noStates; var63 += 1) {
			if(!fixedFlag$sample68)
				memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 94.0);
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$)) + 814.0);
		}
		for(int var84 = 0; var84 < noStates; var84 += 1) {
			if(!fixedFlag$sample89)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var94 = 0; var94 < noStates; var94 += 1) {
			if(!fixedFlag$sample99)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int var104 = 0; var104 < noStates; var104 += 1) {
			if(!fixedFlag$sample109)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			double[] cv$source1 = pageFaults_measured;
			double[] cv$target1 = pageFaults;
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
			double[] cv$source1 = cpu_measured;
			double[] cv$target1 = cpu;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n    \n    // Construct vectors describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    // Determine how many samples the model will need to produce.\n    int samples = cpu_measured.length;\n    \n    // Allocate space for the state.\n    int[] st = new int[samples];\n\n    // Set the initial state by sampling from a categorical with learnt weightings.\n    double[] initialStateDistribution = dirichlet(v).sample();\n    st[0] = categorical(initialStateDistribution).sampleDistribution();\n\n    //Determine the remaining states based on the previous state.\n    for(int i:[1 .. samples))\n        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n        \n    //Generate each metric.\n    double[] cpu = new double[samples];\n    double[] mem = new double[samples];\n    double[] pageFaults = new double[samples];\n    \n    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n    double[] memMean = gaussian(94, 1).sample(noStates);\n    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n    \n    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n    \n    for(int i:[0 .. samples)) {\n        int s = st[i];\n        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n    }\n\n    //Tie the values to the values we have measured.\n    cpu.observe(cpu_measured);\n    mem.observe(mem_measured);\n    pageFaults.observe(pageFaults_measured);\n}\n";
	}
}