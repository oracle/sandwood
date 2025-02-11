package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics$CoreInterface {
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var39;
	private double[][] cv$var22$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var30$stateProbabilityGlobal;
	private double[] cv$var40$stateProbabilityGlobal;
	private double[] distribution$sample35;
	private double[][] distribution$sample45;
	private boolean fixedFlag$sample100 = false;
	private boolean fixedFlag$sample110 = false;
	private boolean fixedFlag$sample119 = false;
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample129 = false;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample110 = false;
	private boolean fixedProbFlag$sample119 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample129 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] guard$sample35gaussian118$global;
	private boolean[] guard$sample35gaussian123$global;
	private boolean[] guard$sample35gaussian128$global;
	private boolean[] guard$sample45gaussian118$global;
	private boolean[] guard$sample45gaussian123$global;
	private boolean[] guard$sample45gaussian128$global;
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
	private double[] logProbability$sample119;
	private double[] logProbability$sample124;
	private double[] logProbability$sample129;
	private double[] logProbability$sample45;
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

	public HMMMetrics$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample119 = false;
	}

	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	@Override
	public final void set$cpuMean(double[] cv$value) {
		cpuMean = cv$value;
		setFlag$cpuMean = true;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample119 = false;
	}

	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	@Override
	public final void set$cpuVar(double[] cv$value) {
		cpuVar = cv$value;
		setFlag$cpuVar = true;
		fixedProbFlag$sample90 = false;
		fixedProbFlag$sample119 = false;
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
	public final boolean get$fixedFlag$sample100() {
		return fixedFlag$sample100;
	}

	@Override
	public final void set$fixedFlag$sample100(boolean cv$value) {
		fixedFlag$sample100 = cv$value;
		fixedProbFlag$sample100 = (fixedFlag$sample100 && fixedProbFlag$sample100);
		fixedProbFlag$sample124 = (fixedFlag$sample100 && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample110() {
		return fixedFlag$sample110;
	}

	@Override
	public final void set$fixedFlag$sample110(boolean cv$value) {
		fixedFlag$sample110 = cv$value;
		fixedProbFlag$sample110 = (fixedFlag$sample110 && fixedProbFlag$sample110);
		fixedProbFlag$sample129 = (fixedFlag$sample110 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample119() {
		return fixedFlag$sample119;
	}

	@Override
	public final void set$fixedFlag$sample119(boolean cv$value) {
		fixedFlag$sample119 = cv$value;
		fixedProbFlag$sample119 = (fixedFlag$sample119 && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		fixedFlag$sample124 = cv$value;
		fixedProbFlag$sample124 = (fixedFlag$sample124 && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample129() {
		return fixedFlag$sample129;
	}

	@Override
	public final void set$fixedFlag$sample129(boolean cv$value) {
		fixedFlag$sample129 = cv$value;
		fixedProbFlag$sample129 = (fixedFlag$sample129 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		fixedFlag$sample25 = cv$value;
		fixedProbFlag$sample25 = (fixedFlag$sample25 && fixedProbFlag$sample25);
		fixedProbFlag$sample45 = (fixedFlag$sample25 && fixedProbFlag$sample45);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample35 = (fixedFlag$sample32 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample45 = (fixedFlag$sample35 && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (fixedFlag$sample35 && fixedProbFlag$sample119);
		fixedProbFlag$sample124 = (fixedFlag$sample35 && fixedProbFlag$sample124);
		fixedProbFlag$sample129 = (fixedFlag$sample35 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (fixedFlag$sample45 && fixedProbFlag$sample119);
		fixedProbFlag$sample124 = (fixedFlag$sample45 && fixedProbFlag$sample124);
		fixedProbFlag$sample129 = (fixedFlag$sample45 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
		fixedProbFlag$sample119 = (fixedFlag$sample58 && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedProbFlag$sample69);
		fixedProbFlag$sample124 = (fixedFlag$sample69 && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedProbFlag$sample80);
		fixedProbFlag$sample129 = (fixedFlag$sample80 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedProbFlag$sample90);
		fixedProbFlag$sample119 = (fixedFlag$sample90 && fixedProbFlag$sample119);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample35 = false;
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
		fixedProbFlag$sample25 = false;
		fixedProbFlag$sample45 = false;
	}

	@Override
	public final double[] get$mem() {
		return mem;
	}

	@Override
	public final void set$mem(double[] cv$value) {
		mem = cv$value;
		setFlag$mem = true;
		fixedProbFlag$sample124 = false;
	}

	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	@Override
	public final void set$memMean(double[] cv$value) {
		memMean = cv$value;
		setFlag$memMean = true;
		fixedProbFlag$sample69 = false;
		fixedProbFlag$sample124 = false;
	}

	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	@Override
	public final void set$memVar(double[] cv$value) {
		memVar = cv$value;
		setFlag$memVar = true;
		fixedProbFlag$sample100 = false;
		fixedProbFlag$sample124 = false;
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
		fixedProbFlag$sample129 = false;
	}

	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
		fixedProbFlag$sample80 = false;
		fixedProbFlag$sample129 = false;
	}

	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		pageFaultsVar = cv$value;
		setFlag$pageFaultsVar = true;
		fixedProbFlag$sample110 = false;
		fixedProbFlag$sample129 = false;
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
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample119 = false;
		fixedProbFlag$sample124 = false;
		fixedProbFlag$sample129 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample119() {
		if(!fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var109];
					if(fixedFlag$sample35) {
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
							for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample35$3;
								double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
															double cv$weightedProbability = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample35$11 = 0; index$sample35$11 < noStates; index$sample35$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample35$11;
													double cv$probabilitySample35Value12 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$14_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$14_1];
																	double var112 = cpuVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample35) {
						if((0 == i$var109)) {
							for(int var52 = 0; var52 < noStates; var52 += 1) {
								if((var52 == st[i$var109])) {
									if(fixedFlag$sample45) {
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
												for(int index$sample45$27 = 0; index$sample45$27 < noStates; index$sample45$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample45$27;
													double cv$probabilitySample45Value28 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$30_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$30_1];
																	double var112 = cpuVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value28);
																}
															}
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
							for(int index$sample35$19 = 0; index$sample35$19 < noStates; index$sample35$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample35$19;
								double cv$probabilitySample35Value20 = (1.0 * distribution$sample35[index$sample35$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var52 = 0; var52 < noStates; var52 += 1) {
										if((var52 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample45) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$22_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$22_1];
																	double var112 = cpuVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample45$33 = 0; index$sample45$33 < noStates; index$sample45$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample45$33;
															double cv$probabilitySample45Value34 = (cv$probabilitySample35Value20 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$36_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$36_1];
																			double var112 = cpuVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
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
								for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample45$43;
									double cv$probabilitySample45Value44 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$43]);
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
																double cv$weightedProbability = (Math.log(cv$probabilitySample45Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample45$52 = 0; index$sample45$52 < noStates; index$sample45$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample45$52;
															double cv$probabilitySample45Value53 = (cv$probabilitySample45Value44 * distribution$sample45[((index$i$51 - 1) / 1)][index$sample45$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$55_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$55_1];
																			double var112 = cpuVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var52 = 0; var52 < noStates; var52 += 1) {
									if((var52 == st[i$var109])) {
										if(fixedFlag$sample35) {
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
												for(int index$sample35$68 = 0; index$sample35$68 < noStates; index$sample35$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample35$68;
													double cv$probabilitySample35Value69 = (1.0 * distribution$sample35[index$sample35$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$71_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$71_1];
																	double var112 = cpuVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value69);
																}
															}
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
								for(int index$sample45$61 = 0; index$sample45$61 < noStates; index$sample45$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample45$61;
									double cv$probabilitySample45Value62 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var52 = 0; var52 < noStates; var52 += 1) {
											if((var52 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample35) {
													if((0 == i$var109)) {
														for(int var84 = 0; var84 < noStates; var84 += 1) {
															if((var84 == traceTempVariable$s$64_1)) {
																{
																	double var111 = cpuMean[traceTempVariable$s$64_1];
																	double var112 = cpuVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample35$73 = 0; index$sample35$73 < noStates; index$sample35$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample35$73;
															double cv$probabilitySample35Value74 = (cv$probabilitySample45Value62 * distribution$sample35[index$sample35$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$76_1)) {
																		{
																			double var111 = cpuMean[traceTempVariable$s$76_1];
																			double var112 = cpuVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample35Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var111) / Math.sqrt(var112))) - (0.5 * Math.log(var112))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$sample119[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample119[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample124() {
		if(!fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var109];
					if(fixedFlag$sample35) {
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
							for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample35$3;
								double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
															double cv$weightedProbability = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample35$11 = 0; index$sample35$11 < noStates; index$sample35$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample35$11;
													double cv$probabilitySample35Value12 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$14_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$14_1];
																	double var117 = memVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample35) {
						if((0 == i$var109)) {
							for(int var63 = 0; var63 < noStates; var63 += 1) {
								if((var63 == st[i$var109])) {
									if(fixedFlag$sample45) {
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
												for(int index$sample45$27 = 0; index$sample45$27 < noStates; index$sample45$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample45$27;
													double cv$probabilitySample45Value28 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$30_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$30_1];
																	double var117 = memVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value28);
																}
															}
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
							for(int index$sample35$19 = 0; index$sample35$19 < noStates; index$sample35$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample35$19;
								double cv$probabilitySample35Value20 = (1.0 * distribution$sample35[index$sample35$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var63 = 0; var63 < noStates; var63 += 1) {
										if((var63 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample45) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$22_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$22_1];
																	double var117 = memVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample45$33 = 0; index$sample45$33 < noStates; index$sample45$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample45$33;
															double cv$probabilitySample45Value34 = (cv$probabilitySample35Value20 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$36_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$36_1];
																			double var117 = memVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
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
								for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample45$43;
									double cv$probabilitySample45Value44 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$43]);
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
																double cv$weightedProbability = (Math.log(cv$probabilitySample45Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample45$52 = 0; index$sample45$52 < noStates; index$sample45$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample45$52;
															double cv$probabilitySample45Value53 = (cv$probabilitySample45Value44 * distribution$sample45[((index$i$51 - 1) / 1)][index$sample45$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$55_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$55_1];
																			double var117 = memVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var63 = 0; var63 < noStates; var63 += 1) {
									if((var63 == st[i$var109])) {
										if(fixedFlag$sample35) {
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
												for(int index$sample35$68 = 0; index$sample35$68 < noStates; index$sample35$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample35$68;
													double cv$probabilitySample35Value69 = (1.0 * distribution$sample35[index$sample35$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$71_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$71_1];
																	double var117 = memVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value69);
																}
															}
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
								for(int index$sample45$61 = 0; index$sample45$61 < noStates; index$sample45$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample45$61;
									double cv$probabilitySample45Value62 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var63 = 0; var63 < noStates; var63 += 1) {
											if((var63 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample35) {
													if((0 == i$var109)) {
														for(int var94 = 0; var94 < noStates; var94 += 1) {
															if((var94 == traceTempVariable$s$64_1)) {
																{
																	double var116 = memMean[traceTempVariable$s$64_1];
																	double var117 = memVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample35$73 = 0; index$sample35$73 < noStates; index$sample35$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample35$73;
															double cv$probabilitySample35Value74 = (cv$probabilitySample45Value62 * distribution$sample35[index$sample35$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$76_1)) {
																		{
																			double var116 = memMean[traceTempVariable$s$76_1];
																			double var117 = memVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample35Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var116) / Math.sqrt(var117))) - (0.5 * Math.log(var117))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$sample124[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample124[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample129() {
		if(!fixedProbFlag$sample129) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var109];
					if(fixedFlag$sample35) {
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
							for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
								int distributionTempVariable$var30$5 = index$sample35$3;
								double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
															double cv$weightedProbability = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample35$11 = 0; index$sample35$11 < noStates; index$sample35$11 += 1) {
													int distributionTempVariable$var30$13 = index$sample35$11;
													double cv$probabilitySample35Value12 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var30$13;
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$14_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$14_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample35) {
						if((0 == i$var109)) {
							for(int var74 = 0; var74 < noStates; var74 += 1) {
								if((var74 == st[i$var109])) {
									if(fixedFlag$sample45) {
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
												for(int index$sample45$27 = 0; index$sample45$27 < noStates; index$sample45$27 += 1) {
													int distributionTempVariable$var40$29 = index$sample45$27;
													double cv$probabilitySample45Value28 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var40$29;
													if((i$var34 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$30_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$30_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value28);
																}
															}
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
							for(int index$sample35$19 = 0; index$sample35$19 < noStates; index$sample35$19 += 1) {
								int distributionTempVariable$var30$21 = index$sample35$19;
								double cv$probabilitySample35Value20 = (1.0 * distribution$sample35[index$sample35$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var30$21;
								if((0 == i$var109)) {
									for(int var74 = 0; var74 < noStates; var74 += 1) {
										if((var74 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample45) {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if((i$var34 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$22_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$22_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
													if(true) {
														for(int index$sample45$33 = 0; index$sample45$33 < noStates; index$sample45$33 += 1) {
															int distributionTempVariable$var40$35 = index$sample45$33;
															double cv$probabilitySample45Value34 = (cv$probabilitySample35Value20 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var40$35;
															if((i$var34 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$36_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$36_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
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
								for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
									int distributionTempVariable$var40$45 = index$sample45$43;
									double cv$probabilitySample45Value44 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$43]);
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
																double cv$weightedProbability = (Math.log(cv$probabilitySample45Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var34)) {
														for(int index$sample45$52 = 0; index$sample45$52 < noStates; index$sample45$52 += 1) {
															int distributionTempVariable$var40$54 = index$sample45$52;
															double cv$probabilitySample45Value53 = (cv$probabilitySample45Value44 * distribution$sample45[((index$i$51 - 1) / 1)][index$sample45$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var40$54;
															if((index$i$51 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$55_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$55_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample45Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
						for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
							if((i$var34 == i$var109)) {
								for(int var74 = 0; var74 < noStates; var74 += 1) {
									if((var74 == st[i$var109])) {
										if(fixedFlag$sample35) {
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
												for(int index$sample35$68 = 0; index$sample35$68 < noStates; index$sample35$68 += 1) {
													int distributionTempVariable$var30$70 = index$sample35$68;
													double cv$probabilitySample35Value69 = (1.0 * distribution$sample35[index$sample35$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var30$70;
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$71_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$71_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample35Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value69);
																}
															}
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
								for(int index$sample45$61 = 0; index$sample45$61 < noStates; index$sample45$61 += 1) {
									int distributionTempVariable$var40$63 = index$sample45$61;
									double cv$probabilitySample45Value62 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var40$63;
									if((i$var34 == i$var109)) {
										for(int var74 = 0; var74 < noStates; var74 += 1) {
											if((var74 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample35) {
													if((0 == i$var109)) {
														for(int var104 = 0; var104 < noStates; var104 += 1) {
															if((var104 == traceTempVariable$s$64_1)) {
																{
																	double var121 = pageFaultsMean[traceTempVariable$s$64_1];
																	double var122 = pageFaultsVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample45Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample35$73 = 0; index$sample35$73 < noStates; index$sample35$73 += 1) {
															int distributionTempVariable$var30$75 = index$sample35$73;
															double cv$probabilitySample35Value74 = (cv$probabilitySample45Value62 * distribution$sample35[index$sample35$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var30$75;
															if((0 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$76_1)) {
																		{
																			double var121 = pageFaultsMean[traceTempVariable$s$76_1];
																			double var122 = pageFaultsVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample35Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var121) / Math.sqrt(var122))) - (0.5 * Math.log(var122))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$sample129[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample129[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var123[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample35() {
		if(!fixedProbFlag$sample35) {
			if(fixedFlag$sample35) {
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
				if(fixedFlag$sample35)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample35)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample32);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var29 = cv$rvAccumulator;
			if(fixedFlag$sample35)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!fixedProbFlag$sample45) {
			if(fixedFlag$sample45) {
				double cv$accumulator = 0.0;
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var34;
					{
						int cv$sampleValue = st[i$var34];
						if(fixedFlag$sample35) {
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
								for(int index$sample35$4 = 0; index$sample35$4 < noStates; index$sample35$4 += 1) {
									int distributionTempVariable$var30$6 = index$sample35$4;
									double cv$probabilitySample35Value5 = (1.0 * distribution$sample35[index$sample35$4]);
									int traceTempVariable$var37$7_1 = distributionTempVariable$var30$6;
									if((0 == (i$var34 - 1))) {
										for(int var21 = 0; var21 < noStates; var21 += 1) {
											if((var21 == traceTempVariable$var37$7_1)) {
												{
													double[] var38 = m[traceTempVariable$var37$7_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample35Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value5);
												}
											}
										}
									}
								}
							}
						}
						if((index$i$1 == (i$var34 - 1))) {
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
						if(fixedFlag$sample45) {
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
									for(int index$sample45$13 = 0; index$sample45$13 < noStates; index$sample45$13 += 1) {
										int distributionTempVariable$var40$15 = index$sample45$13;
										double cv$probabilitySample45Value14 = (1.0 * distribution$sample45[((index$i$12 - 1) / 1)][index$sample45$13]);
										int traceTempVariable$var37$16_1 = distributionTempVariable$var40$15;
										if((index$i$12 == (i$var34 - 1))) {
											for(int var21 = 0; var21 < noStates; var21 += 1) {
												if((var21 == traceTempVariable$var37$16_1)) {
													{
														double[] var38 = m[traceTempVariable$var37$16_1];
														double cv$weightedProbability = (Math.log(cv$probabilitySample45Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value14);
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
					logProbability$sample45[((i$var34 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample45)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample45)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample25) && fixedFlag$sample35);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45[((i$var34 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[((i$var34 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample45)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample100() {
		if(!fixedProbFlag$sample100) {
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
			if(fixedFlag$sample100)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample100 = fixedFlag$sample100;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var95;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var90 = cv$rvAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample100)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample110() {
		if(!fixedProbFlag$sample110) {
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
			if(fixedFlag$sample110)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample110 = fixedFlag$sample110;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var105;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var100 = cv$rvAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample110)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample119() {
		if(!fixedProbFlag$sample119) {
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
				logProbability$sample119[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample119[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample124() {
		if(!fixedProbFlag$sample124) {
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
				logProbability$sample124[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample124[((i$var109 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[((i$var109 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample129() {
		if(!fixedProbFlag$sample129) {
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
				logProbability$sample129[((i$var109 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample129[((i$var109 - 0) / 1)];
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

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
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
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample32);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var29 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
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
				logProbability$sample45[((i$var34 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample25) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45[((i$var34 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[((i$var34 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
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
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var53;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
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
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample69 = fixedFlag$sample69;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var64;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var59 = cv$rvAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
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
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample80 = fixedFlag$sample80;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var70 = cv$rvAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
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
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample90 = fixedFlag$sample90;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var85;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var80 = cv$rvAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample100(int var94, int threadID$cv$var94, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = memVar[var94];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var117$16_1 = cv$currentValue;
										if((var94 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var117$17_1 = cv$currentValue;
												if((var94 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	private final void sample110(int var104, int threadID$cv$var104, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = pageFaultsVar[var104];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var122$16_1 = cv$currentValue;
										if((var104 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var122$17_1 = cv$currentValue;
												if((var104 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	private final void sample25(int var21, int threadID$cv$var21, Rng RNG$) {
		double[] cv$targetLocal = m[var21];
		double[] cv$countLocal = cv$var22$countGlobal[threadID$cv$var21];
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
						if(fixedFlag$sample35) {
							if((0 == (i$var34 - 1))) {
								if((var21 == st[(i$var34 - 1)])) {
									if(fixedFlag$sample45) {
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
								for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
									int distributionTempVariable$var30$5 = index$sample35$3;
									double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
									int traceTempVariable$var37$6_1 = distributionTempVariable$var30$5;
									if((0 == (i$var34 - 1))) {
										if((var21 == traceTempVariable$var37$6_1)) {
											if(fixedFlag$sample45) {
												{
													int index$i$21 = i$var34;
													{
														{
															{
																{
																	cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + cv$probabilitySample35Value4);
																}
															}
														}
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
						if(fixedFlag$sample45) {
							for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
								if((index$i$10_1 == (i$var34 - 1))) {
									if((var21 == st[(i$var34 - 1)])) {
										if(fixedFlag$sample45) {
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
									for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
										int distributionTempVariable$var40$14 = index$sample45$12;
										double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((index$i$11 - 1) / 1)][index$sample45$12]);
										int traceTempVariable$var37$15_1 = distributionTempVariable$var40$14;
										if((index$i$11 == (i$var34 - 1))) {
											if((var21 == traceTempVariable$var37$15_1)) {
												if(fixedFlag$sample45) {
													{
														int index$i$25 = i$var34;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + cv$probabilitySample45Value13);
																	}
																}
															}
														}
													}
												}
											}
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
					if(fixedFlag$sample35) {
						if((0 == (i$var34 - 1))) {
							if((var21 == st[(i$var34 - 1)])) {
								if(!fixedFlag$sample45) {
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
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample35$32 = 0; index$sample35$32 < noStates; index$sample35$32 += 1) {
								int distributionTempVariable$var30$34 = index$sample35$32;
								double cv$probabilitySample35Value33 = (1.0 * distribution$sample35[index$sample35$32]);
								int traceTempVariable$var37$35_1 = distributionTempVariable$var30$34;
								if((0 == (i$var34 - 1))) {
									if((var21 == traceTempVariable$var37$35_1)) {
										if(!fixedFlag$sample45) {
											{
												int index$i$50 = i$var34;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample35Value33);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
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
					if(fixedFlag$sample45) {
						for(int index$i$39_1 = 1; index$i$39_1 < samples; index$i$39_1 += 1) {
							if((index$i$39_1 == (i$var34 - 1))) {
								if((var21 == st[(i$var34 - 1)])) {
									if(!fixedFlag$sample45) {
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
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
									int distributionTempVariable$var40$43 = index$sample45$41;
									double cv$probabilitySample45Value42 = (1.0 * distribution$sample45[((index$i$40 - 1) / 1)][index$sample45$41]);
									int traceTempVariable$var37$44_1 = distributionTempVariable$var40$43;
									if((index$i$40 == (i$var34 - 1))) {
										if((var21 == traceTempVariable$var37$44_1)) {
											if(!fixedFlag$sample45) {
												{
													int index$i$54 = i$var34;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample45Value42);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var34 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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

	private final void sample32() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var27$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if(fixedFlag$sample35) {
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
				if(!fixedFlag$sample35) {
					{
						{
							{
								double scopeVariable$reachedSourceProbability = 0.0;
								{
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
								}
								double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
								for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample35[cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample35() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var30$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
								if(fixedFlag$sample45) {
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
						boolean[] guard$sample35gaussian118 = guard$sample35gaussian118$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian118[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian118[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$8_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample35gaussian118[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian118[((i$var109 - 0) / 1)] = true;
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
														for(int index$sample35$14 = 0; index$sample35$14 < noStates; index$sample35$14 += 1) {
															int distributionTempVariable$var30$16 = index$sample35$14;
															double cv$probabilitySample35Value15 = (1.0 * distribution$sample35[index$sample35$14]);
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
																				if(((Math.log(cv$probabilitySample35Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$4$var111) / Math.sqrt(cv$temp$5$var112))) - (0.5 * Math.log(cv$temp$5$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value15);
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
													if(fixedFlag$sample45) {
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
																for(int index$sample45$23 = 0; index$sample45$23 < noStates; index$sample45$23 += 1) {
																	int distributionTempVariable$var40$25 = index$sample45$23;
																	double cv$probabilitySample45Value24 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$23]);
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
																						if(((Math.log(cv$probabilitySample45Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value24);
																					}
																				}
																			}
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
								if(!guard$sample35gaussian118[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian118[((i$var109 - 0) / 1)] = true;
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
												for(int index$sample35$30 = 0; index$sample35$30 < noStates; index$sample35$30 += 1) {
													int distributionTempVariable$var30$32 = index$sample35$30;
													double cv$probabilitySample35Value31 = (1.0 * distribution$sample35[index$sample35$30]);
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
																				if(((Math.log(cv$probabilitySample35Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value31);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample45) {
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
														for(int index$sample45$40 = 0; index$sample45$40 < noStates; index$sample45$40 += 1) {
															int distributionTempVariable$var40$42 = index$sample45$40;
															double cv$probabilitySample45Value41 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$40]);
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
																						if(((Math.log(cv$probabilitySample45Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value41);
																					}
																				}
																			}
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
						boolean[] guard$sample35gaussian123 = guard$sample35gaussian123$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian123[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian123[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$58_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample35gaussian123[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian123[((i$var109 - 0) / 1)] = true;
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
														for(int index$sample35$64 = 0; index$sample35$64 < noStates; index$sample35$64 += 1) {
															int distributionTempVariable$var30$66 = index$sample35$64;
															double cv$probabilitySample35Value65 = (1.0 * distribution$sample35[index$sample35$64]);
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
																				if(((Math.log(cv$probabilitySample35Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$20$var116) / Math.sqrt(cv$temp$21$var117))) - (0.5 * Math.log(cv$temp$21$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value65);
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
													if(fixedFlag$sample45) {
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
																for(int index$sample45$73 = 0; index$sample45$73 < noStates; index$sample45$73 += 1) {
																	int distributionTempVariable$var40$75 = index$sample45$73;
																	double cv$probabilitySample45Value74 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$73]);
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
																						if(((Math.log(cv$probabilitySample45Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value74);
																					}
																				}
																			}
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
								if(!guard$sample35gaussian123[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian123[((i$var109 - 0) / 1)] = true;
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
												for(int index$sample35$80 = 0; index$sample35$80 < noStates; index$sample35$80 += 1) {
													int distributionTempVariable$var30$82 = index$sample35$80;
													double cv$probabilitySample35Value81 = (1.0 * distribution$sample35[index$sample35$80]);
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
																				if(((Math.log(cv$probabilitySample35Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value81);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample45) {
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
														for(int index$sample45$90 = 0; index$sample45$90 < noStates; index$sample45$90 += 1) {
															int distributionTempVariable$var40$92 = index$sample45$90;
															double cv$probabilitySample45Value91 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$90]);
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
																						if(((Math.log(cv$probabilitySample45Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$32$var116) / Math.sqrt(cv$temp$33$var117))) - (0.5 * Math.log(cv$temp$33$var117)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value91);
																					}
																				}
																			}
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
						boolean[] guard$sample35gaussian128 = guard$sample35gaussian128$global;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian128[((i$var109 - 0) / 1)] = false;
						}
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109))
								guard$sample35gaussian128[((i$var109 - 0) / 1)] = false;
						}
						int traceTempVariable$s$108_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((0 == i$var109)) {
								if(!guard$sample35gaussian128[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian128[((i$var109 - 0) / 1)] = true;
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
														for(int index$sample35$114 = 0; index$sample35$114 < noStates; index$sample35$114 += 1) {
															int distributionTempVariable$var30$116 = index$sample35$114;
															double cv$probabilitySample35Value115 = (1.0 * distribution$sample35[index$sample35$114]);
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
																				if(((Math.log(cv$probabilitySample35Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$36$var121) / Math.sqrt(cv$temp$37$var122))) - (0.5 * Math.log(cv$temp$37$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value115);
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
													if(fixedFlag$sample45) {
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
																for(int index$sample45$123 = 0; index$sample45$123 < noStates; index$sample45$123 += 1) {
																	int distributionTempVariable$var40$125 = index$sample45$123;
																	double cv$probabilitySample45Value124 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$123]);
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
																						if(((Math.log(cv$probabilitySample45Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$40$var121) / Math.sqrt(cv$temp$41$var122))) - (0.5 * Math.log(cv$temp$41$var122)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value124);
																					}
																				}
																			}
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
								if(!guard$sample35gaussian128[((i$var109 - 0) / 1)]) {
									guard$sample35gaussian128[((i$var109 - 0) / 1)] = true;
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
												for(int index$sample35$130 = 0; index$sample35$130 < noStates; index$sample35$130 += 1) {
													int distributionTempVariable$var30$132 = index$sample35$130;
													double cv$probabilitySample35Value131 = (1.0 * distribution$sample35[index$sample35$130]);
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
																				if(((Math.log(cv$probabilitySample35Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$44$var121) / Math.sqrt(cv$temp$45$var122))) - (0.5 * Math.log(cv$temp$45$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value131);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample45) {
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
														for(int index$sample45$140 = 0; index$sample45$140 < noStates; index$sample45$140 += 1) {
															int distributionTempVariable$var40$142 = index$sample45$140;
															double cv$probabilitySample45Value141 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$140]);
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
																						if(((Math.log(cv$probabilitySample45Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$48$var121) / Math.sqrt(cv$temp$49$var122))) - (0.5 * Math.log(cv$temp$49$var122)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value141);
																					}
																				}
																			}
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
							if(!fixedFlag$sample45) {
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
									double[] cv$sampleDistribution = distribution$sample45[((i$var34 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample35;
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample45(int i$var34) {
		int cv$noStates = 0;
		int index$i$1 = i$var34;
		if(fixedFlag$sample35) {
			if((0 == (i$var34 - 1))) {
				for(int var21 = 0; var21 < noStates; var21 += 1) {
					if((var21 == st[(i$var34 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		} else {
			if(true) {
				for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
					int distributionTempVariable$var30$5 = index$sample35$3;
					double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
					int traceTempVariable$var37$6_1 = distributionTempVariable$var30$5;
					if((0 == (i$var34 - 1))) {
						for(int var21 = 0; var21 < noStates; var21 += 1) {
							if((var21 == traceTempVariable$var37$6_1))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		}
		if((index$i$1 == (i$var34 - 1))) {
			for(int var21 = 0; var21 < noStates; var21 += 1) {
				if((var21 == st[(i$var34 - 1)]))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		if(fixedFlag$sample45) {
			for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
				if((index$i$10_1 == (i$var34 - 1))) {
					for(int var21 = 0; var21 < noStates; var21 += 1) {
						if((var21 == st[(i$var34 - 1)]))
							cv$noStates = Math.max(cv$noStates, noStates);
					}
				}
			}
		} else {
			for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
				if(!(index$i$11 == index$i$1)) {
					for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
						int distributionTempVariable$var40$14 = index$sample45$12;
						double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((index$i$11 - 1) / 1)][index$sample45$12]);
						int traceTempVariable$var37$15_1 = distributionTempVariable$var40$14;
						if((index$i$11 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$15_1))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var40$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$19 = i$var34;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample35) {
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
									int traceTempVariable$var37$35_1 = cv$currentValue;
								}
							}
							{
								{
									boolean[] guard$sample45gaussian118 = guard$sample45gaussian118$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$47_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$47_1)) {
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$47_1)) {
																			{
																				{
																					double cv$temp$4$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$47_1];
																						cv$temp$4$var111 = var111;
																					}
																					double cv$temp$5$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$47_1];
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
															if((var52 == traceTempVariable$s$47_1)) {
																int traceTempVariable$s$67_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$67_1)) {
																			{
																				{
																					double cv$temp$6$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$67_1];
																						cv$temp$6$var111 = var111;
																					}
																					double cv$temp$7$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$67_1];
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
																for(int index$i$68 = 1; index$i$68 < samples; index$i$68 += 1) {
																	if(!(index$i$68 == index$i$19)) {
																		for(int index$sample45$69 = 0; index$sample45$69 < noStates; index$sample45$69 += 1) {
																			int distributionTempVariable$var40$71 = index$sample45$69;
																			double cv$probabilitySample45Value70 = (1.0 * distribution$sample45[((index$i$68 - 1) / 1)][index$sample45$69]);
																			int traceTempVariable$s$72_1 = distributionTempVariable$var40$71;
																			if((index$i$68 == i$var109)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$72_1)) {
																						{
																							{
																								double cv$temp$8$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$72_1];
																									cv$temp$8$var111 = var111;
																								}
																								double cv$temp$9$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$72_1];
																									cv$temp$9$var112 = var112;
																								}
																								if(((Math.log(cv$probabilitySample45Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value70);
																							}
																						}
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
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$51_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$36$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$51_1];
																						cv$temp$36$var111 = var111;
																					}
																					double cv$temp$37$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$51_1];
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
														int traceTempVariable$s$131_1 = cv$currentValue;
														if((index$i$19 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$131_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$131_1)) {
																			{
																				{
																					double cv$temp$38$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$131_1];
																						cv$temp$38$var111 = var111;
																					}
																					double cv$temp$39$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$131_1];
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
														for(int index$i$132 = 1; index$i$132 < samples; index$i$132 += 1) {
															if(!(index$i$132 == index$i$19)) {
																for(int index$sample45$133 = 0; index$sample45$133 < noStates; index$sample45$133 += 1) {
																	int distributionTempVariable$var40$135 = index$sample45$133;
																	double cv$probabilitySample45Value134 = (1.0 * distribution$sample45[((index$i$132 - 1) / 1)][index$sample45$133]);
																	int traceTempVariable$s$136_1 = distributionTempVariable$var40$135;
																	if((index$i$132 == i$var109)) {
																		for(int var52 = 0; var52 < noStates; var52 += 1) {
																			if((var52 == traceTempVariable$s$136_1)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$136_1)) {
																						{
																							{
																								double cv$temp$40$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$136_1];
																									cv$temp$40$var111 = var111;
																								}
																								double cv$temp$41$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$136_1];
																									cv$temp$41$var112 = var112;
																								}
																								if(((Math.log(cv$probabilitySample45Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$40$var111) / Math.sqrt(cv$temp$41$var112))) - (0.5 * Math.log(cv$temp$41$var112)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value134);
																							}
																						}
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
									boolean[] guard$sample45gaussian123 = guard$sample45gaussian123$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$241_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$241_1)) {
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$241_1)) {
																			{
																				{
																					double cv$temp$68$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$241_1];
																						cv$temp$68$var116 = var116;
																					}
																					double cv$temp$69$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$241_1];
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
															if((var63 == traceTempVariable$s$241_1)) {
																int traceTempVariable$s$261_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$261_1)) {
																			{
																				{
																					double cv$temp$70$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$261_1];
																						cv$temp$70$var116 = var116;
																					}
																					double cv$temp$71$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$261_1];
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
																for(int index$i$262 = 1; index$i$262 < samples; index$i$262 += 1) {
																	if(!(index$i$262 == index$i$19)) {
																		for(int index$sample45$263 = 0; index$sample45$263 < noStates; index$sample45$263 += 1) {
																			int distributionTempVariable$var40$265 = index$sample45$263;
																			double cv$probabilitySample45Value264 = (1.0 * distribution$sample45[((index$i$262 - 1) / 1)][index$sample45$263]);
																			int traceTempVariable$s$266_1 = distributionTempVariable$var40$265;
																			if((index$i$262 == i$var109)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$266_1)) {
																						{
																							{
																								double cv$temp$72$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$266_1];
																									cv$temp$72$var116 = var116;
																								}
																								double cv$temp$73$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$266_1];
																									cv$temp$73$var117 = var117;
																								}
																								if(((Math.log(cv$probabilitySample45Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$72$var116) / Math.sqrt(cv$temp$73$var117))) - (0.5 * Math.log(cv$temp$73$var117)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value264);
																							}
																						}
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
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$245_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$245_1)) {
																			{
																				{
																					double cv$temp$100$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$245_1];
																						cv$temp$100$var116 = var116;
																					}
																					double cv$temp$101$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$245_1];
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
														int traceTempVariable$s$325_1 = cv$currentValue;
														if((index$i$19 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$325_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$325_1)) {
																			{
																				{
																					double cv$temp$102$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$325_1];
																						cv$temp$102$var116 = var116;
																					}
																					double cv$temp$103$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$325_1];
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
														for(int index$i$326 = 1; index$i$326 < samples; index$i$326 += 1) {
															if(!(index$i$326 == index$i$19)) {
																for(int index$sample45$327 = 0; index$sample45$327 < noStates; index$sample45$327 += 1) {
																	int distributionTempVariable$var40$329 = index$sample45$327;
																	double cv$probabilitySample45Value328 = (1.0 * distribution$sample45[((index$i$326 - 1) / 1)][index$sample45$327]);
																	int traceTempVariable$s$330_1 = distributionTempVariable$var40$329;
																	if((index$i$326 == i$var109)) {
																		for(int var63 = 0; var63 < noStates; var63 += 1) {
																			if((var63 == traceTempVariable$s$330_1)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$330_1)) {
																						{
																							{
																								double cv$temp$104$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$330_1];
																									cv$temp$104$var116 = var116;
																								}
																								double cv$temp$105$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$330_1];
																									cv$temp$105$var117 = var117;
																								}
																								if(((Math.log(cv$probabilitySample45Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$104$var116) / Math.sqrt(cv$temp$105$var117))) - (0.5 * Math.log(cv$temp$105$var117)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value328);
																							}
																						}
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
									boolean[] guard$sample45gaussian128 = guard$sample45gaussian128$global;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
									}
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109))
											guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
									}
									int traceTempVariable$s$435_1 = cv$currentValue;
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$435_1)) {
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$435_1)) {
																			{
																				{
																					double cv$temp$132$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$435_1];
																						cv$temp$132$var121 = var121;
																					}
																					double cv$temp$133$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$435_1];
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
															if((var74 == traceTempVariable$s$435_1)) {
																int traceTempVariable$s$455_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$455_1)) {
																			{
																				{
																					double cv$temp$134$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$455_1];
																						cv$temp$134$var121 = var121;
																					}
																					double cv$temp$135$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$455_1];
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
																for(int index$i$456 = 1; index$i$456 < samples; index$i$456 += 1) {
																	if(!(index$i$456 == index$i$19)) {
																		for(int index$sample45$457 = 0; index$sample45$457 < noStates; index$sample45$457 += 1) {
																			int distributionTempVariable$var40$459 = index$sample45$457;
																			double cv$probabilitySample45Value458 = (1.0 * distribution$sample45[((index$i$456 - 1) / 1)][index$sample45$457]);
																			int traceTempVariable$s$460_1 = distributionTempVariable$var40$459;
																			if((index$i$456 == i$var109)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$460_1)) {
																						{
																							{
																								double cv$temp$136$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$460_1];
																									cv$temp$136$var121 = var121;
																								}
																								double cv$temp$137$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$460_1];
																									cv$temp$137$var122 = var122;
																								}
																								if(((Math.log(cv$probabilitySample45Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$136$var121) / Math.sqrt(cv$temp$137$var122))) - (0.5 * Math.log(cv$temp$137$var122)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value458);
																							}
																						}
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
									for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
										if((i$var34 == i$var109)) {
											if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
												guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$439_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$439_1)) {
																			{
																				{
																					double cv$temp$164$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$439_1];
																						cv$temp$164$var121 = var121;
																					}
																					double cv$temp$165$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$439_1];
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
														int traceTempVariable$s$519_1 = cv$currentValue;
														if((index$i$19 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$519_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$519_1)) {
																			{
																				{
																					double cv$temp$166$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$519_1];
																						cv$temp$166$var121 = var121;
																					}
																					double cv$temp$167$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$519_1];
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
														for(int index$i$520 = 1; index$i$520 < samples; index$i$520 += 1) {
															if(!(index$i$520 == index$i$19)) {
																for(int index$sample45$521 = 0; index$sample45$521 < noStates; index$sample45$521 += 1) {
																	int distributionTempVariable$var40$523 = index$sample45$521;
																	double cv$probabilitySample45Value522 = (1.0 * distribution$sample45[((index$i$520 - 1) / 1)][index$sample45$521]);
																	int traceTempVariable$s$524_1 = distributionTempVariable$var40$523;
																	if((index$i$520 == i$var109)) {
																		for(int var74 = 0; var74 < noStates; var74 += 1) {
																			if((var74 == traceTempVariable$s$524_1)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$524_1)) {
																						{
																							{
																								double cv$temp$168$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$524_1];
																									cv$temp$168$var121 = var121;
																								}
																								double cv$temp$169$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$524_1];
																									cv$temp$169$var122 = var122;
																								}
																								if(((Math.log(cv$probabilitySample45Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$168$var121) / Math.sqrt(cv$temp$169$var122))) - (0.5 * Math.log(cv$temp$169$var122)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value522);
																							}
																						}
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
					for(int index$sample35$21 = 0; index$sample35$21 < noStates; index$sample35$21 += 1) {
						int distributionTempVariable$var30$23 = index$sample35$21;
						double cv$probabilitySample35Value22 = (1.0 * distribution$sample35[index$sample35$21]);
						int traceTempVariable$var37$24_1 = distributionTempVariable$var30$23;
						if((0 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$24_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value22);
									double[] cv$temp$1$var38;
									{
										double[] var38 = m[traceTempVariable$var37$24_1];
										cv$temp$1$var38 = var38;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample35Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var37$36_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample45gaussian118 = guard$sample45gaussian118$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$48_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$48_1)) {
																		int traceTempVariable$s$76_1 = distributionTempVariable$var30$23;
																		if((0 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$76_1)) {
																					{
																						{
																							double cv$temp$10$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$76_1];
																								cv$temp$10$var111 = var111;
																							}
																							double cv$temp$11$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$76_1];
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
																			for(int index$sample35$77 = 0; index$sample35$77 < noStates; index$sample35$77 += 1) {
																				int distributionTempVariable$var30$79 = index$sample35$77;
																				double cv$probabilitySample35Value78 = (1.0 * distribution$sample35[index$sample35$77]);
																				int traceTempVariable$s$80_1 = distributionTempVariable$var30$79;
																				if((0 == i$var109)) {
																					for(int var84 = 0; var84 < noStates; var84 += 1) {
																						if((var84 == traceTempVariable$s$80_1)) {
																							{
																								{
																									double cv$temp$12$var111;
																									{
																										double var111 = cpuMean[traceTempVariable$s$80_1];
																										cv$temp$12$var111 = var111;
																									}
																									double cv$temp$13$var112;
																									{
																										double var112 = cpuVar[traceTempVariable$s$80_1];
																										cv$temp$13$var112 = var112;
																									}
																									if(((Math.log(cv$probabilitySample35Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value78);
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
																	if((var52 == traceTempVariable$s$48_1)) {
																		int traceTempVariable$s$84_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$84_1)) {
																					{
																						{
																							double cv$temp$14$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$84_1];
																								cv$temp$14$var111 = var111;
																							}
																							double cv$temp$15$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$84_1];
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
																		for(int index$i$85 = 1; index$i$85 < samples; index$i$85 += 1) {
																			if(!(index$i$85 == index$i$19)) {
																				for(int index$sample45$86 = 0; index$sample45$86 < noStates; index$sample45$86 += 1) {
																					int distributionTempVariable$var40$88 = index$sample45$86;
																					double cv$probabilitySample45Value87 = (1.0 * distribution$sample45[((index$i$85 - 1) / 1)][index$sample45$86]);
																					int traceTempVariable$s$89_1 = distributionTempVariable$var40$88;
																					if((index$i$85 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$89_1)) {
																								{
																									{
																										double cv$temp$16$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$89_1];
																											cv$temp$16$var111 = var111;
																										}
																										double cv$temp$17$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$89_1];
																											cv$temp$17$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample45Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$16$var111) / Math.sqrt(cv$temp$17$var112))) - (0.5 * Math.log(cv$temp$17$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value87);
																									}
																								}
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
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$141_1 = distributionTempVariable$var30$23;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$141_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$141_1)) {
																					{
																						{
																							double cv$temp$42$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$141_1];
																								cv$temp$42$var111 = var111;
																							}
																							double cv$temp$43$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$141_1];
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
																	for(int index$sample35$142 = 0; index$sample35$142 < noStates; index$sample35$142 += 1) {
																		int distributionTempVariable$var30$144 = index$sample35$142;
																		double cv$probabilitySample35Value143 = (1.0 * distribution$sample35[index$sample35$142]);
																		int traceTempVariable$s$145_1 = distributionTempVariable$var30$144;
																		if((0 == i$var109)) {
																			for(int var52 = 0; var52 < noStates; var52 += 1) {
																				if((var52 == traceTempVariable$s$145_1)) {
																					for(int var84 = 0; var84 < noStates; var84 += 1) {
																						if((var84 == traceTempVariable$s$145_1)) {
																							{
																								{
																									double cv$temp$44$var111;
																									{
																										double var111 = cpuMean[traceTempVariable$s$145_1];
																										cv$temp$44$var111 = var111;
																									}
																									double cv$temp$45$var112;
																									{
																										double var112 = cpuVar[traceTempVariable$s$145_1];
																										cv$temp$45$var112 = var112;
																									}
																									if(((Math.log(cv$probabilitySample35Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$44$var111) / Math.sqrt(cv$temp$45$var112))) - (0.5 * Math.log(cv$temp$45$var112)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value143);
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
																if((index$i$19 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$150_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$150_1)) {
																					{
																						{
																							double cv$temp$46$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$150_1];
																								cv$temp$46$var111 = var111;
																							}
																							double cv$temp$47$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$150_1];
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
																for(int index$i$151 = 1; index$i$151 < samples; index$i$151 += 1) {
																	if(!(index$i$151 == index$i$19)) {
																		for(int index$sample45$152 = 0; index$sample45$152 < noStates; index$sample45$152 += 1) {
																			int distributionTempVariable$var40$154 = index$sample45$152;
																			double cv$probabilitySample45Value153 = (1.0 * distribution$sample45[((index$i$151 - 1) / 1)][index$sample45$152]);
																			int traceTempVariable$s$155_1 = distributionTempVariable$var40$154;
																			if((index$i$151 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$155_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$155_1)) {
																								{
																									{
																										double cv$temp$48$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$155_1];
																											cv$temp$48$var111 = var111;
																										}
																										double cv$temp$49$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$155_1];
																											cv$temp$49$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample45Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$48$var111) / Math.sqrt(cv$temp$49$var112))) - (0.5 * Math.log(cv$temp$49$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value153);
																									}
																								}
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
											boolean[] guard$sample45gaussian123 = guard$sample45gaussian123$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$242_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$242_1)) {
																		int traceTempVariable$s$270_1 = distributionTempVariable$var30$23;
																		if((0 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$270_1)) {
																					{
																						{
																							double cv$temp$74$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$270_1];
																								cv$temp$74$var116 = var116;
																							}
																							double cv$temp$75$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$270_1];
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
																			for(int index$sample35$271 = 0; index$sample35$271 < noStates; index$sample35$271 += 1) {
																				int distributionTempVariable$var30$273 = index$sample35$271;
																				double cv$probabilitySample35Value272 = (1.0 * distribution$sample35[index$sample35$271]);
																				int traceTempVariable$s$274_1 = distributionTempVariable$var30$273;
																				if((0 == i$var109)) {
																					for(int var94 = 0; var94 < noStates; var94 += 1) {
																						if((var94 == traceTempVariable$s$274_1)) {
																							{
																								{
																									double cv$temp$76$var116;
																									{
																										double var116 = memMean[traceTempVariable$s$274_1];
																										cv$temp$76$var116 = var116;
																									}
																									double cv$temp$77$var117;
																									{
																										double var117 = memVar[traceTempVariable$s$274_1];
																										cv$temp$77$var117 = var117;
																									}
																									if(((Math.log(cv$probabilitySample35Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$76$var116) / Math.sqrt(cv$temp$77$var117))) - (0.5 * Math.log(cv$temp$77$var117)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value272);
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
																	if((var63 == traceTempVariable$s$242_1)) {
																		int traceTempVariable$s$278_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$278_1)) {
																					{
																						{
																							double cv$temp$78$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$278_1];
																								cv$temp$78$var116 = var116;
																							}
																							double cv$temp$79$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$278_1];
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
																		for(int index$i$279 = 1; index$i$279 < samples; index$i$279 += 1) {
																			if(!(index$i$279 == index$i$19)) {
																				for(int index$sample45$280 = 0; index$sample45$280 < noStates; index$sample45$280 += 1) {
																					int distributionTempVariable$var40$282 = index$sample45$280;
																					double cv$probabilitySample45Value281 = (1.0 * distribution$sample45[((index$i$279 - 1) / 1)][index$sample45$280]);
																					int traceTempVariable$s$283_1 = distributionTempVariable$var40$282;
																					if((index$i$279 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$283_1)) {
																								{
																									{
																										double cv$temp$80$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$283_1];
																											cv$temp$80$var116 = var116;
																										}
																										double cv$temp$81$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$283_1];
																											cv$temp$81$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample45Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$80$var116) / Math.sqrt(cv$temp$81$var117))) - (0.5 * Math.log(cv$temp$81$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value281);
																									}
																								}
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
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$335_1 = distributionTempVariable$var30$23;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$335_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$335_1)) {
																					{
																						{
																							double cv$temp$106$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$335_1];
																								cv$temp$106$var116 = var116;
																							}
																							double cv$temp$107$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$335_1];
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
																	for(int index$sample35$336 = 0; index$sample35$336 < noStates; index$sample35$336 += 1) {
																		int distributionTempVariable$var30$338 = index$sample35$336;
																		double cv$probabilitySample35Value337 = (1.0 * distribution$sample35[index$sample35$336]);
																		int traceTempVariable$s$339_1 = distributionTempVariable$var30$338;
																		if((0 == i$var109)) {
																			for(int var63 = 0; var63 < noStates; var63 += 1) {
																				if((var63 == traceTempVariable$s$339_1)) {
																					for(int var94 = 0; var94 < noStates; var94 += 1) {
																						if((var94 == traceTempVariable$s$339_1)) {
																							{
																								{
																									double cv$temp$108$var116;
																									{
																										double var116 = memMean[traceTempVariable$s$339_1];
																										cv$temp$108$var116 = var116;
																									}
																									double cv$temp$109$var117;
																									{
																										double var117 = memVar[traceTempVariable$s$339_1];
																										cv$temp$109$var117 = var117;
																									}
																									if(((Math.log(cv$probabilitySample35Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$108$var116) / Math.sqrt(cv$temp$109$var117))) - (0.5 * Math.log(cv$temp$109$var117)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value337);
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
																if((index$i$19 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$344_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$344_1)) {
																					{
																						{
																							double cv$temp$110$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$344_1];
																								cv$temp$110$var116 = var116;
																							}
																							double cv$temp$111$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$344_1];
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
																for(int index$i$345 = 1; index$i$345 < samples; index$i$345 += 1) {
																	if(!(index$i$345 == index$i$19)) {
																		for(int index$sample45$346 = 0; index$sample45$346 < noStates; index$sample45$346 += 1) {
																			int distributionTempVariable$var40$348 = index$sample45$346;
																			double cv$probabilitySample45Value347 = (1.0 * distribution$sample45[((index$i$345 - 1) / 1)][index$sample45$346]);
																			int traceTempVariable$s$349_1 = distributionTempVariable$var40$348;
																			if((index$i$345 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$349_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$349_1)) {
																								{
																									{
																										double cv$temp$112$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$349_1];
																											cv$temp$112$var116 = var116;
																										}
																										double cv$temp$113$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$349_1];
																											cv$temp$113$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample45Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$112$var116) / Math.sqrt(cv$temp$113$var117))) - (0.5 * Math.log(cv$temp$113$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value347);
																									}
																								}
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
											boolean[] guard$sample45gaussian128 = guard$sample45gaussian128$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$436_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$436_1)) {
																		int traceTempVariable$s$464_1 = distributionTempVariable$var30$23;
																		if((0 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$464_1)) {
																					{
																						{
																							double cv$temp$138$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$464_1];
																								cv$temp$138$var121 = var121;
																							}
																							double cv$temp$139$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$464_1];
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
																			for(int index$sample35$465 = 0; index$sample35$465 < noStates; index$sample35$465 += 1) {
																				int distributionTempVariable$var30$467 = index$sample35$465;
																				double cv$probabilitySample35Value466 = (1.0 * distribution$sample35[index$sample35$465]);
																				int traceTempVariable$s$468_1 = distributionTempVariable$var30$467;
																				if((0 == i$var109)) {
																					for(int var104 = 0; var104 < noStates; var104 += 1) {
																						if((var104 == traceTempVariable$s$468_1)) {
																							{
																								{
																									double cv$temp$140$var121;
																									{
																										double var121 = pageFaultsMean[traceTempVariable$s$468_1];
																										cv$temp$140$var121 = var121;
																									}
																									double cv$temp$141$var122;
																									{
																										double var122 = pageFaultsVar[traceTempVariable$s$468_1];
																										cv$temp$141$var122 = var122;
																									}
																									if(((Math.log(cv$probabilitySample35Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$140$var121) / Math.sqrt(cv$temp$141$var122))) - (0.5 * Math.log(cv$temp$141$var122)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value466);
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
																	if((var74 == traceTempVariable$s$436_1)) {
																		int traceTempVariable$s$472_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$472_1)) {
																					{
																						{
																							double cv$temp$142$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$472_1];
																								cv$temp$142$var121 = var121;
																							}
																							double cv$temp$143$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$472_1];
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
																		for(int index$i$473 = 1; index$i$473 < samples; index$i$473 += 1) {
																			if(!(index$i$473 == index$i$19)) {
																				for(int index$sample45$474 = 0; index$sample45$474 < noStates; index$sample45$474 += 1) {
																					int distributionTempVariable$var40$476 = index$sample45$474;
																					double cv$probabilitySample45Value475 = (1.0 * distribution$sample45[((index$i$473 - 1) / 1)][index$sample45$474]);
																					int traceTempVariable$s$477_1 = distributionTempVariable$var40$476;
																					if((index$i$473 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$477_1)) {
																								{
																									{
																										double cv$temp$144$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$477_1];
																											cv$temp$144$var121 = var121;
																										}
																										double cv$temp$145$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$477_1];
																											cv$temp$145$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample45Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$144$var121) / Math.sqrt(cv$temp$145$var122))) - (0.5 * Math.log(cv$temp$145$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value475);
																									}
																								}
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
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$529_1 = distributionTempVariable$var30$23;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$529_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$529_1)) {
																					{
																						{
																							double cv$temp$170$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$529_1];
																								cv$temp$170$var121 = var121;
																							}
																							double cv$temp$171$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$529_1];
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
																	for(int index$sample35$530 = 0; index$sample35$530 < noStates; index$sample35$530 += 1) {
																		int distributionTempVariable$var30$532 = index$sample35$530;
																		double cv$probabilitySample35Value531 = (1.0 * distribution$sample35[index$sample35$530]);
																		int traceTempVariable$s$533_1 = distributionTempVariable$var30$532;
																		if((0 == i$var109)) {
																			for(int var74 = 0; var74 < noStates; var74 += 1) {
																				if((var74 == traceTempVariable$s$533_1)) {
																					for(int var104 = 0; var104 < noStates; var104 += 1) {
																						if((var104 == traceTempVariable$s$533_1)) {
																							{
																								{
																									double cv$temp$172$var121;
																									{
																										double var121 = pageFaultsMean[traceTempVariable$s$533_1];
																										cv$temp$172$var121 = var121;
																									}
																									double cv$temp$173$var122;
																									{
																										double var122 = pageFaultsVar[traceTempVariable$s$533_1];
																										cv$temp$173$var122 = var122;
																									}
																									if(((Math.log(cv$probabilitySample35Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$172$var121) / Math.sqrt(cv$temp$173$var122))) - (0.5 * Math.log(cv$temp$173$var122)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value531);
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
																if((index$i$19 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$538_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$538_1)) {
																					{
																						{
																							double cv$temp$174$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$538_1];
																								cv$temp$174$var121 = var121;
																							}
																							double cv$temp$175$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$538_1];
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
																for(int index$i$539 = 1; index$i$539 < samples; index$i$539 += 1) {
																	if(!(index$i$539 == index$i$19)) {
																		for(int index$sample45$540 = 0; index$sample45$540 < noStates; index$sample45$540 += 1) {
																			int distributionTempVariable$var40$542 = index$sample45$540;
																			double cv$probabilitySample45Value541 = (1.0 * distribution$sample45[((index$i$539 - 1) / 1)][index$sample45$540]);
																			int traceTempVariable$s$543_1 = distributionTempVariable$var40$542;
																			if((index$i$539 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$543_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$543_1)) {
																								{
																									{
																										double cv$temp$176$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$543_1];
																											cv$temp$176$var121 = var121;
																										}
																										double cv$temp$177$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$543_1];
																											cv$temp$177$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample45Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$176$var121) / Math.sqrt(cv$temp$177$var122))) - (0.5 * Math.log(cv$temp$177$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value541);
																									}
																								}
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
			int traceTempVariable$var37$27_1 = cv$currentValue;
			if((index$i$19 == (i$var34 - 1))) {
				for(int var21 = 0; var21 < noStates; var21 += 1) {
					if((var21 == traceTempVariable$var37$27_1)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var38;
						{
							double[] var38 = m[traceTempVariable$var37$27_1];
							cv$temp$2$var38 = var38;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var38.length))?Math.log(cv$temp$2$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var37$37_1 = cv$currentValue;
							}
						}
						{
							{
								boolean[] guard$sample45gaussian118 = guard$sample45gaussian118$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$49_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var52 = 0; var52 < noStates; var52 += 1) {
														if((var52 == traceTempVariable$s$49_1)) {
															if(fixedFlag$sample35) {
																if((0 == i$var109)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$49_1)) {
																			{
																				{
																					double cv$temp$18$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$49_1];
																						cv$temp$18$var111 = var111;
																					}
																					double cv$temp$19$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$49_1];
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
																	for(int index$sample35$94 = 0; index$sample35$94 < noStates; index$sample35$94 += 1) {
																		int distributionTempVariable$var30$96 = index$sample35$94;
																		double cv$probabilitySample35Value95 = (1.0 * distribution$sample35[index$sample35$94]);
																		int traceTempVariable$s$97_1 = distributionTempVariable$var30$96;
																		if((0 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$97_1)) {
																					{
																						{
																							double cv$temp$20$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$97_1];
																								cv$temp$20$var111 = var111;
																							}
																							double cv$temp$21$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$97_1];
																								cv$temp$21$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample35Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$20$var111) / Math.sqrt(cv$temp$21$var112))) - (0.5 * Math.log(cv$temp$21$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value95);
																						}
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
														if((var52 == traceTempVariable$s$49_1)) {
															int traceTempVariable$s$101_1 = cv$currentValue;
															if((index$i$19 == i$var109)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$101_1)) {
																		{
																			{
																				double cv$temp$22$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$101_1];
																					cv$temp$22$var111 = var111;
																				}
																				double cv$temp$23$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$101_1];
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
															for(int index$i$102 = 1; index$i$102 < samples; index$i$102 += 1) {
																if(!(index$i$102 == index$i$19)) {
																	for(int index$sample45$103 = 0; index$sample45$103 < noStates; index$sample45$103 += 1) {
																		int distributionTempVariable$var40$105 = index$sample45$103;
																		double cv$probabilitySample45Value104 = (1.0 * distribution$sample45[((index$i$102 - 1) / 1)][index$sample45$103]);
																		int traceTempVariable$s$106_1 = distributionTempVariable$var40$105;
																		if((index$i$102 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$106_1)) {
																					{
																						{
																							double cv$temp$24$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$106_1];
																								cv$temp$24$var111 = var111;
																							}
																							double cv$temp$25$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$106_1];
																								cv$temp$25$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample45Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value104);
																						}
																					}
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
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
														if((0 == i$var109)) {
															for(int var52 = 0; var52 < noStates; var52 += 1) {
																if((var52 == traceTempVariable$s$53_1)) {
																	for(int var84 = 0; var84 < noStates; var84 += 1) {
																		if((var84 == traceTempVariable$s$53_1)) {
																			{
																				{
																					double cv$temp$50$var111;
																					{
																						double var111 = cpuMean[traceTempVariable$s$53_1];
																						cv$temp$50$var111 = var111;
																					}
																					double cv$temp$51$var112;
																					{
																						double var112 = cpuVar[traceTempVariable$s$53_1];
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
															for(int index$sample35$161 = 0; index$sample35$161 < noStates; index$sample35$161 += 1) {
																int distributionTempVariable$var30$163 = index$sample35$161;
																double cv$probabilitySample35Value162 = (1.0 * distribution$sample35[index$sample35$161]);
																int traceTempVariable$s$164_1 = distributionTempVariable$var30$163;
																if((0 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$164_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$164_1)) {
																					{
																						{
																							double cv$temp$52$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$164_1];
																								cv$temp$52$var111 = var111;
																							}
																							double cv$temp$53$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$164_1];
																								cv$temp$53$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample35Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$52$var111) / Math.sqrt(cv$temp$53$var112))) - (0.5 * Math.log(cv$temp$53$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value162);
																						}
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
													if((index$i$19 == i$var109)) {
														for(int var52 = 0; var52 < noStates; var52 += 1) {
															if((var52 == traceTempVariable$s$169_1)) {
																for(int var84 = 0; var84 < noStates; var84 += 1) {
																	if((var84 == traceTempVariable$s$169_1)) {
																		{
																			{
																				double cv$temp$54$var111;
																				{
																					double var111 = cpuMean[traceTempVariable$s$169_1];
																					cv$temp$54$var111 = var111;
																				}
																				double cv$temp$55$var112;
																				{
																					double var112 = cpuVar[traceTempVariable$s$169_1];
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
													for(int index$i$170 = 1; index$i$170 < samples; index$i$170 += 1) {
														if(!(index$i$170 == index$i$19)) {
															for(int index$sample45$171 = 0; index$sample45$171 < noStates; index$sample45$171 += 1) {
																int distributionTempVariable$var40$173 = index$sample45$171;
																double cv$probabilitySample45Value172 = (1.0 * distribution$sample45[((index$i$170 - 1) / 1)][index$sample45$171]);
																int traceTempVariable$s$174_1 = distributionTempVariable$var40$173;
																if((index$i$170 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$174_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$174_1)) {
																					{
																						{
																							double cv$temp$56$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$174_1];
																								cv$temp$56$var111 = var111;
																							}
																							double cv$temp$57$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$174_1];
																								cv$temp$57$var112 = var112;
																							}
																							if(((Math.log(cv$probabilitySample45Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$56$var111) / Math.sqrt(cv$temp$57$var112))) - (0.5 * Math.log(cv$temp$57$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value172);
																						}
																					}
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
								boolean[] guard$sample45gaussian123 = guard$sample45gaussian123$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$243_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var63 = 0; var63 < noStates; var63 += 1) {
														if((var63 == traceTempVariable$s$243_1)) {
															if(fixedFlag$sample35) {
																if((0 == i$var109)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$243_1)) {
																			{
																				{
																					double cv$temp$82$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$243_1];
																						cv$temp$82$var116 = var116;
																					}
																					double cv$temp$83$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$243_1];
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
																	for(int index$sample35$288 = 0; index$sample35$288 < noStates; index$sample35$288 += 1) {
																		int distributionTempVariable$var30$290 = index$sample35$288;
																		double cv$probabilitySample35Value289 = (1.0 * distribution$sample35[index$sample35$288]);
																		int traceTempVariable$s$291_1 = distributionTempVariable$var30$290;
																		if((0 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$291_1)) {
																					{
																						{
																							double cv$temp$84$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$291_1];
																								cv$temp$84$var116 = var116;
																							}
																							double cv$temp$85$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$291_1];
																								cv$temp$85$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample35Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$84$var116) / Math.sqrt(cv$temp$85$var117))) - (0.5 * Math.log(cv$temp$85$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value289);
																						}
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
														if((var63 == traceTempVariable$s$243_1)) {
															int traceTempVariable$s$295_1 = cv$currentValue;
															if((index$i$19 == i$var109)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$295_1)) {
																		{
																			{
																				double cv$temp$86$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$295_1];
																					cv$temp$86$var116 = var116;
																				}
																				double cv$temp$87$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$295_1];
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
															for(int index$i$296 = 1; index$i$296 < samples; index$i$296 += 1) {
																if(!(index$i$296 == index$i$19)) {
																	for(int index$sample45$297 = 0; index$sample45$297 < noStates; index$sample45$297 += 1) {
																		int distributionTempVariable$var40$299 = index$sample45$297;
																		double cv$probabilitySample45Value298 = (1.0 * distribution$sample45[((index$i$296 - 1) / 1)][index$sample45$297]);
																		int traceTempVariable$s$300_1 = distributionTempVariable$var40$299;
																		if((index$i$296 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$300_1)) {
																					{
																						{
																							double cv$temp$88$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$300_1];
																								cv$temp$88$var116 = var116;
																							}
																							double cv$temp$89$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$300_1];
																								cv$temp$89$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample45Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$88$var116) / Math.sqrt(cv$temp$89$var117))) - (0.5 * Math.log(cv$temp$89$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value298);
																						}
																					}
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
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
														if((0 == i$var109)) {
															for(int var63 = 0; var63 < noStates; var63 += 1) {
																if((var63 == traceTempVariable$s$247_1)) {
																	for(int var94 = 0; var94 < noStates; var94 += 1) {
																		if((var94 == traceTempVariable$s$247_1)) {
																			{
																				{
																					double cv$temp$114$var116;
																					{
																						double var116 = memMean[traceTempVariable$s$247_1];
																						cv$temp$114$var116 = var116;
																					}
																					double cv$temp$115$var117;
																					{
																						double var117 = memVar[traceTempVariable$s$247_1];
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
															for(int index$sample35$355 = 0; index$sample35$355 < noStates; index$sample35$355 += 1) {
																int distributionTempVariable$var30$357 = index$sample35$355;
																double cv$probabilitySample35Value356 = (1.0 * distribution$sample35[index$sample35$355]);
																int traceTempVariable$s$358_1 = distributionTempVariable$var30$357;
																if((0 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$358_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$358_1)) {
																					{
																						{
																							double cv$temp$116$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$358_1];
																								cv$temp$116$var116 = var116;
																							}
																							double cv$temp$117$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$358_1];
																								cv$temp$117$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample35Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$116$var116) / Math.sqrt(cv$temp$117$var117))) - (0.5 * Math.log(cv$temp$117$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value356);
																						}
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
													if((index$i$19 == i$var109)) {
														for(int var63 = 0; var63 < noStates; var63 += 1) {
															if((var63 == traceTempVariable$s$363_1)) {
																for(int var94 = 0; var94 < noStates; var94 += 1) {
																	if((var94 == traceTempVariable$s$363_1)) {
																		{
																			{
																				double cv$temp$118$var116;
																				{
																					double var116 = memMean[traceTempVariable$s$363_1];
																					cv$temp$118$var116 = var116;
																				}
																				double cv$temp$119$var117;
																				{
																					double var117 = memVar[traceTempVariable$s$363_1];
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
													for(int index$i$364 = 1; index$i$364 < samples; index$i$364 += 1) {
														if(!(index$i$364 == index$i$19)) {
															for(int index$sample45$365 = 0; index$sample45$365 < noStates; index$sample45$365 += 1) {
																int distributionTempVariable$var40$367 = index$sample45$365;
																double cv$probabilitySample45Value366 = (1.0 * distribution$sample45[((index$i$364 - 1) / 1)][index$sample45$365]);
																int traceTempVariable$s$368_1 = distributionTempVariable$var40$367;
																if((index$i$364 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$368_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$368_1)) {
																					{
																						{
																							double cv$temp$120$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$368_1];
																								cv$temp$120$var116 = var116;
																							}
																							double cv$temp$121$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$368_1];
																								cv$temp$121$var117 = var117;
																							}
																							if(((Math.log(cv$probabilitySample45Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$120$var116) / Math.sqrt(cv$temp$121$var117))) - (0.5 * Math.log(cv$temp$121$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value366);
																						}
																					}
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
								boolean[] guard$sample45gaussian128 = guard$sample45gaussian128$global;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
								}
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109))
										guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
								}
								int traceTempVariable$s$437_1 = cv$currentValue;
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var74 = 0; var74 < noStates; var74 += 1) {
														if((var74 == traceTempVariable$s$437_1)) {
															if(fixedFlag$sample35) {
																if((0 == i$var109)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$437_1)) {
																			{
																				{
																					double cv$temp$146$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$437_1];
																						cv$temp$146$var121 = var121;
																					}
																					double cv$temp$147$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$437_1];
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
																	for(int index$sample35$482 = 0; index$sample35$482 < noStates; index$sample35$482 += 1) {
																		int distributionTempVariable$var30$484 = index$sample35$482;
																		double cv$probabilitySample35Value483 = (1.0 * distribution$sample35[index$sample35$482]);
																		int traceTempVariable$s$485_1 = distributionTempVariable$var30$484;
																		if((0 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$485_1)) {
																					{
																						{
																							double cv$temp$148$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$485_1];
																								cv$temp$148$var121 = var121;
																							}
																							double cv$temp$149$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$485_1];
																								cv$temp$149$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample35Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$148$var121) / Math.sqrt(cv$temp$149$var122))) - (0.5 * Math.log(cv$temp$149$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value483);
																						}
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
														if((var74 == traceTempVariable$s$437_1)) {
															int traceTempVariable$s$489_1 = cv$currentValue;
															if((index$i$19 == i$var109)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$489_1)) {
																		{
																			{
																				double cv$temp$150$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$489_1];
																					cv$temp$150$var121 = var121;
																				}
																				double cv$temp$151$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$489_1];
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
															for(int index$i$490 = 1; index$i$490 < samples; index$i$490 += 1) {
																if(!(index$i$490 == index$i$19)) {
																	for(int index$sample45$491 = 0; index$sample45$491 < noStates; index$sample45$491 += 1) {
																		int distributionTempVariable$var40$493 = index$sample45$491;
																		double cv$probabilitySample45Value492 = (1.0 * distribution$sample45[((index$i$490 - 1) / 1)][index$sample45$491]);
																		int traceTempVariable$s$494_1 = distributionTempVariable$var40$493;
																		if((index$i$490 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$494_1)) {
																					{
																						{
																							double cv$temp$152$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$494_1];
																								cv$temp$152$var121 = var121;
																							}
																							double cv$temp$153$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$494_1];
																								cv$temp$153$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample45Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$152$var121) / Math.sqrt(cv$temp$153$var122))) - (0.5 * Math.log(cv$temp$153$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value492);
																						}
																					}
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
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									if((i$var34 == i$var109)) {
										if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
											guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
														if((0 == i$var109)) {
															for(int var74 = 0; var74 < noStates; var74 += 1) {
																if((var74 == traceTempVariable$s$441_1)) {
																	for(int var104 = 0; var104 < noStates; var104 += 1) {
																		if((var104 == traceTempVariable$s$441_1)) {
																			{
																				{
																					double cv$temp$178$var121;
																					{
																						double var121 = pageFaultsMean[traceTempVariable$s$441_1];
																						cv$temp$178$var121 = var121;
																					}
																					double cv$temp$179$var122;
																					{
																						double var122 = pageFaultsVar[traceTempVariable$s$441_1];
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
															for(int index$sample35$549 = 0; index$sample35$549 < noStates; index$sample35$549 += 1) {
																int distributionTempVariable$var30$551 = index$sample35$549;
																double cv$probabilitySample35Value550 = (1.0 * distribution$sample35[index$sample35$549]);
																int traceTempVariable$s$552_1 = distributionTempVariable$var30$551;
																if((0 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$552_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$552_1)) {
																					{
																						{
																							double cv$temp$180$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$552_1];
																								cv$temp$180$var121 = var121;
																							}
																							double cv$temp$181$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$552_1];
																								cv$temp$181$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample35Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$180$var121) / Math.sqrt(cv$temp$181$var122))) - (0.5 * Math.log(cv$temp$181$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value550);
																						}
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
													if((index$i$19 == i$var109)) {
														for(int var74 = 0; var74 < noStates; var74 += 1) {
															if((var74 == traceTempVariable$s$557_1)) {
																for(int var104 = 0; var104 < noStates; var104 += 1) {
																	if((var104 == traceTempVariable$s$557_1)) {
																		{
																			{
																				double cv$temp$182$var121;
																				{
																					double var121 = pageFaultsMean[traceTempVariable$s$557_1];
																					cv$temp$182$var121 = var121;
																				}
																				double cv$temp$183$var122;
																				{
																					double var122 = pageFaultsVar[traceTempVariable$s$557_1];
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
													for(int index$i$558 = 1; index$i$558 < samples; index$i$558 += 1) {
														if(!(index$i$558 == index$i$19)) {
															for(int index$sample45$559 = 0; index$sample45$559 < noStates; index$sample45$559 += 1) {
																int distributionTempVariable$var40$561 = index$sample45$559;
																double cv$probabilitySample45Value560 = (1.0 * distribution$sample45[((index$i$558 - 1) / 1)][index$sample45$559]);
																int traceTempVariable$s$562_1 = distributionTempVariable$var40$561;
																if((index$i$558 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$562_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$562_1)) {
																					{
																						{
																							double cv$temp$184$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$562_1];
																								cv$temp$184$var121 = var121;
																							}
																							double cv$temp$185$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$562_1];
																								cv$temp$185$var122 = var122;
																							}
																							if(((Math.log(cv$probabilitySample45Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$184$var121) / Math.sqrt(cv$temp$185$var122))) - (0.5 * Math.log(cv$temp$185$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value560);
																						}
																					}
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
					for(int index$sample45$29 = 0; index$sample45$29 < noStates; index$sample45$29 += 1) {
						int distributionTempVariable$var40$31 = index$sample45$29;
						double cv$probabilitySample45Value30 = (1.0 * distribution$sample45[((index$i$28 - 1) / 1)][index$sample45$29]);
						int traceTempVariable$var37$32_1 = distributionTempVariable$var40$31;
						if((index$i$28 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$32_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample45Value30);
									double[] cv$temp$3$var38;
									{
										double[] var38 = m[traceTempVariable$var37$32_1];
										cv$temp$3$var38 = var38;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample45Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var38.length))?Math.log(cv$temp$3$var38[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var37$38_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample45gaussian118 = guard$sample45gaussian118$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian118[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$50_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var52 = 0; var52 < noStates; var52 += 1) {
																	if((var52 == traceTempVariable$s$50_1)) {
																		if(fixedFlag$sample35) {
																			if((0 == i$var109)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$50_1)) {
																						{
																							{
																								double cv$temp$26$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$50_1];
																									cv$temp$26$var111 = var111;
																								}
																								double cv$temp$27$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$50_1];
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
																				for(int index$sample35$111 = 0; index$sample35$111 < noStates; index$sample35$111 += 1) {
																					int distributionTempVariable$var30$113 = index$sample35$111;
																					double cv$probabilitySample35Value112 = (1.0 * distribution$sample35[index$sample35$111]);
																					int traceTempVariable$s$114_1 = distributionTempVariable$var30$113;
																					if((0 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$114_1)) {
																								{
																									{
																										double cv$temp$28$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$114_1];
																											cv$temp$28$var111 = var111;
																										}
																										double cv$temp$29$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$114_1];
																											cv$temp$29$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample35Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value112);
																									}
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
																	if((var52 == traceTempVariable$s$50_1)) {
																		int traceTempVariable$s$118_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$118_1)) {
																					{
																						{
																							double cv$temp$30$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$118_1];
																								cv$temp$30$var111 = var111;
																							}
																							double cv$temp$31$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$118_1];
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
																		int traceTempVariable$s$119_1 = distributionTempVariable$var40$31;
																		if((index$i$28 == i$var109)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$119_1)) {
																					{
																						{
																							double cv$temp$32$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$119_1];
																								cv$temp$32$var111 = var111;
																							}
																							double cv$temp$33$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$119_1];
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
																		for(int index$i$120 = 1; index$i$120 < samples; index$i$120 += 1) {
																			if((!(index$i$120 == index$i$19) && !(index$i$120 == index$i$28))) {
																				for(int index$sample45$121 = 0; index$sample45$121 < noStates; index$sample45$121 += 1) {
																					int distributionTempVariable$var40$123 = index$sample45$121;
																					double cv$probabilitySample45Value122 = (1.0 * distribution$sample45[((index$i$120 - 1) / 1)][index$sample45$121]);
																					int traceTempVariable$s$124_1 = distributionTempVariable$var40$123;
																					if((index$i$120 == i$var109)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$124_1)) {
																								{
																									{
																										double cv$temp$34$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$124_1];
																											cv$temp$34$var111 = var111;
																										}
																										double cv$temp$35$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$124_1];
																											cv$temp$35$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample45Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$34$var111) / Math.sqrt(cv$temp$35$var112))) - (0.5 * Math.log(cv$temp$35$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value122);
																									}
																								}
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
											int traceTempVariable$s$54_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian118[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian118[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample35) {
																	if((0 == i$var109)) {
																		for(int var52 = 0; var52 < noStates; var52 += 1) {
																			if((var52 == traceTempVariable$s$54_1)) {
																				for(int var84 = 0; var84 < noStates; var84 += 1) {
																					if((var84 == traceTempVariable$s$54_1)) {
																						{
																							{
																								double cv$temp$58$var111;
																								{
																									double var111 = cpuMean[traceTempVariable$s$54_1];
																									cv$temp$58$var111 = var111;
																								}
																								double cv$temp$59$var112;
																								{
																									double var112 = cpuVar[traceTempVariable$s$54_1];
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
																		for(int index$sample35$180 = 0; index$sample35$180 < noStates; index$sample35$180 += 1) {
																			int distributionTempVariable$var30$182 = index$sample35$180;
																			double cv$probabilitySample35Value181 = (1.0 * distribution$sample35[index$sample35$180]);
																			int traceTempVariable$s$183_1 = distributionTempVariable$var30$182;
																			if((0 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$183_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$183_1)) {
																								{
																									{
																										double cv$temp$60$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$183_1];
																											cv$temp$60$var111 = var111;
																										}
																										double cv$temp$61$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$183_1];
																											cv$temp$61$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample35Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$60$var111) / Math.sqrt(cv$temp$61$var112))) - (0.5 * Math.log(cv$temp$61$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value181);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$188_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$188_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$188_1)) {
																					{
																						{
																							double cv$temp$62$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$188_1];
																								cv$temp$62$var111 = var111;
																							}
																							double cv$temp$63$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$188_1];
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
																int traceTempVariable$s$189_1 = distributionTempVariable$var40$31;
																if((index$i$28 == i$var109)) {
																	for(int var52 = 0; var52 < noStates; var52 += 1) {
																		if((var52 == traceTempVariable$s$189_1)) {
																			for(int var84 = 0; var84 < noStates; var84 += 1) {
																				if((var84 == traceTempVariable$s$189_1)) {
																					{
																						{
																							double cv$temp$64$var111;
																							{
																								double var111 = cpuMean[traceTempVariable$s$189_1];
																								cv$temp$64$var111 = var111;
																							}
																							double cv$temp$65$var112;
																							{
																								double var112 = cpuVar[traceTempVariable$s$189_1];
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
																for(int index$i$190 = 1; index$i$190 < samples; index$i$190 += 1) {
																	if((!(index$i$190 == index$i$19) && !(index$i$190 == index$i$28))) {
																		for(int index$sample45$191 = 0; index$sample45$191 < noStates; index$sample45$191 += 1) {
																			int distributionTempVariable$var40$193 = index$sample45$191;
																			double cv$probabilitySample45Value192 = (1.0 * distribution$sample45[((index$i$190 - 1) / 1)][index$sample45$191]);
																			int traceTempVariable$s$194_1 = distributionTempVariable$var40$193;
																			if((index$i$190 == i$var109)) {
																				for(int var52 = 0; var52 < noStates; var52 += 1) {
																					if((var52 == traceTempVariable$s$194_1)) {
																						for(int var84 = 0; var84 < noStates; var84 += 1) {
																							if((var84 == traceTempVariable$s$194_1)) {
																								{
																									{
																										double cv$temp$66$var111;
																										{
																											double var111 = cpuMean[traceTempVariable$s$194_1];
																											cv$temp$66$var111 = var111;
																										}
																										double cv$temp$67$var112;
																										{
																											double var112 = cpuVar[traceTempVariable$s$194_1];
																											cv$temp$67$var112 = var112;
																										}
																										if(((Math.log(cv$probabilitySample45Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$66$var111) / Math.sqrt(cv$temp$67$var112))) - (0.5 * Math.log(cv$temp$67$var112)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value192);
																									}
																								}
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
											boolean[] guard$sample45gaussian123 = guard$sample45gaussian123$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian123[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$244_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var63 = 0; var63 < noStates; var63 += 1) {
																	if((var63 == traceTempVariable$s$244_1)) {
																		if(fixedFlag$sample35) {
																			if((0 == i$var109)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$244_1)) {
																						{
																							{
																								double cv$temp$90$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$244_1];
																									cv$temp$90$var116 = var116;
																								}
																								double cv$temp$91$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$244_1];
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
																				for(int index$sample35$305 = 0; index$sample35$305 < noStates; index$sample35$305 += 1) {
																					int distributionTempVariable$var30$307 = index$sample35$305;
																					double cv$probabilitySample35Value306 = (1.0 * distribution$sample35[index$sample35$305]);
																					int traceTempVariable$s$308_1 = distributionTempVariable$var30$307;
																					if((0 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$308_1)) {
																								{
																									{
																										double cv$temp$92$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$308_1];
																											cv$temp$92$var116 = var116;
																										}
																										double cv$temp$93$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$308_1];
																											cv$temp$93$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample35Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$92$var116) / Math.sqrt(cv$temp$93$var117))) - (0.5 * Math.log(cv$temp$93$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value306);
																									}
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
																	if((var63 == traceTempVariable$s$244_1)) {
																		int traceTempVariable$s$312_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$312_1)) {
																					{
																						{
																							double cv$temp$94$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$312_1];
																								cv$temp$94$var116 = var116;
																							}
																							double cv$temp$95$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$312_1];
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
																		int traceTempVariable$s$313_1 = distributionTempVariable$var40$31;
																		if((index$i$28 == i$var109)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$313_1)) {
																					{
																						{
																							double cv$temp$96$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$313_1];
																								cv$temp$96$var116 = var116;
																							}
																							double cv$temp$97$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$313_1];
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
																		for(int index$i$314 = 1; index$i$314 < samples; index$i$314 += 1) {
																			if((!(index$i$314 == index$i$19) && !(index$i$314 == index$i$28))) {
																				for(int index$sample45$315 = 0; index$sample45$315 < noStates; index$sample45$315 += 1) {
																					int distributionTempVariable$var40$317 = index$sample45$315;
																					double cv$probabilitySample45Value316 = (1.0 * distribution$sample45[((index$i$314 - 1) / 1)][index$sample45$315]);
																					int traceTempVariable$s$318_1 = distributionTempVariable$var40$317;
																					if((index$i$314 == i$var109)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$318_1)) {
																								{
																									{
																										double cv$temp$98$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$318_1];
																											cv$temp$98$var116 = var116;
																										}
																										double cv$temp$99$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$318_1];
																											cv$temp$99$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample45Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$98$var116) / Math.sqrt(cv$temp$99$var117))) - (0.5 * Math.log(cv$temp$99$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value316);
																									}
																								}
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
											int traceTempVariable$s$248_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian123[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian123[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample35) {
																	if((0 == i$var109)) {
																		for(int var63 = 0; var63 < noStates; var63 += 1) {
																			if((var63 == traceTempVariable$s$248_1)) {
																				for(int var94 = 0; var94 < noStates; var94 += 1) {
																					if((var94 == traceTempVariable$s$248_1)) {
																						{
																							{
																								double cv$temp$122$var116;
																								{
																									double var116 = memMean[traceTempVariable$s$248_1];
																									cv$temp$122$var116 = var116;
																								}
																								double cv$temp$123$var117;
																								{
																									double var117 = memVar[traceTempVariable$s$248_1];
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
																		for(int index$sample35$374 = 0; index$sample35$374 < noStates; index$sample35$374 += 1) {
																			int distributionTempVariable$var30$376 = index$sample35$374;
																			double cv$probabilitySample35Value375 = (1.0 * distribution$sample35[index$sample35$374]);
																			int traceTempVariable$s$377_1 = distributionTempVariable$var30$376;
																			if((0 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$377_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$377_1)) {
																								{
																									{
																										double cv$temp$124$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$377_1];
																											cv$temp$124$var116 = var116;
																										}
																										double cv$temp$125$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$377_1];
																											cv$temp$125$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample35Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$124$var116) / Math.sqrt(cv$temp$125$var117))) - (0.5 * Math.log(cv$temp$125$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value375);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$382_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$382_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$382_1)) {
																					{
																						{
																							double cv$temp$126$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$382_1];
																								cv$temp$126$var116 = var116;
																							}
																							double cv$temp$127$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$382_1];
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
																int traceTempVariable$s$383_1 = distributionTempVariable$var40$31;
																if((index$i$28 == i$var109)) {
																	for(int var63 = 0; var63 < noStates; var63 += 1) {
																		if((var63 == traceTempVariable$s$383_1)) {
																			for(int var94 = 0; var94 < noStates; var94 += 1) {
																				if((var94 == traceTempVariable$s$383_1)) {
																					{
																						{
																							double cv$temp$128$var116;
																							{
																								double var116 = memMean[traceTempVariable$s$383_1];
																								cv$temp$128$var116 = var116;
																							}
																							double cv$temp$129$var117;
																							{
																								double var117 = memVar[traceTempVariable$s$383_1];
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
																for(int index$i$384 = 1; index$i$384 < samples; index$i$384 += 1) {
																	if((!(index$i$384 == index$i$19) && !(index$i$384 == index$i$28))) {
																		for(int index$sample45$385 = 0; index$sample45$385 < noStates; index$sample45$385 += 1) {
																			int distributionTempVariable$var40$387 = index$sample45$385;
																			double cv$probabilitySample45Value386 = (1.0 * distribution$sample45[((index$i$384 - 1) / 1)][index$sample45$385]);
																			int traceTempVariable$s$388_1 = distributionTempVariable$var40$387;
																			if((index$i$384 == i$var109)) {
																				for(int var63 = 0; var63 < noStates; var63 += 1) {
																					if((var63 == traceTempVariable$s$388_1)) {
																						for(int var94 = 0; var94 < noStates; var94 += 1) {
																							if((var94 == traceTempVariable$s$388_1)) {
																								{
																									{
																										double cv$temp$130$var116;
																										{
																											double var116 = memMean[traceTempVariable$s$388_1];
																											cv$temp$130$var116 = var116;
																										}
																										double cv$temp$131$var117;
																										{
																											double var117 = memVar[traceTempVariable$s$388_1];
																											cv$temp$131$var117 = var117;
																										}
																										if(((Math.log(cv$probabilitySample45Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$130$var116) / Math.sqrt(cv$temp$131$var117))) - (0.5 * Math.log(cv$temp$131$var117)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value386);
																									}
																								}
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
											boolean[] guard$sample45gaussian128 = guard$sample45gaussian128$global;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
											}
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109))
													guard$sample45gaussian128[((i$var109 - 0) / 1)] = false;
											}
											int traceTempVariable$s$438_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var74 = 0; var74 < noStates; var74 += 1) {
																	if((var74 == traceTempVariable$s$438_1)) {
																		if(fixedFlag$sample35) {
																			if((0 == i$var109)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$438_1)) {
																						{
																							{
																								double cv$temp$154$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$438_1];
																									cv$temp$154$var121 = var121;
																								}
																								double cv$temp$155$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$438_1];
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
																				for(int index$sample35$499 = 0; index$sample35$499 < noStates; index$sample35$499 += 1) {
																					int distributionTempVariable$var30$501 = index$sample35$499;
																					double cv$probabilitySample35Value500 = (1.0 * distribution$sample35[index$sample35$499]);
																					int traceTempVariable$s$502_1 = distributionTempVariable$var30$501;
																					if((0 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$502_1)) {
																								{
																									{
																										double cv$temp$156$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$502_1];
																											cv$temp$156$var121 = var121;
																										}
																										double cv$temp$157$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$502_1];
																											cv$temp$157$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample35Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$156$var121) / Math.sqrt(cv$temp$157$var122))) - (0.5 * Math.log(cv$temp$157$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value500);
																									}
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
																	if((var74 == traceTempVariable$s$438_1)) {
																		int traceTempVariable$s$506_1 = cv$currentValue;
																		if((index$i$19 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$506_1)) {
																					{
																						{
																							double cv$temp$158$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$506_1];
																								cv$temp$158$var121 = var121;
																							}
																							double cv$temp$159$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$506_1];
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
																		int traceTempVariable$s$507_1 = distributionTempVariable$var40$31;
																		if((index$i$28 == i$var109)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$507_1)) {
																					{
																						{
																							double cv$temp$160$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$507_1];
																								cv$temp$160$var121 = var121;
																							}
																							double cv$temp$161$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$507_1];
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
																		for(int index$i$508 = 1; index$i$508 < samples; index$i$508 += 1) {
																			if((!(index$i$508 == index$i$19) && !(index$i$508 == index$i$28))) {
																				for(int index$sample45$509 = 0; index$sample45$509 < noStates; index$sample45$509 += 1) {
																					int distributionTempVariable$var40$511 = index$sample45$509;
																					double cv$probabilitySample45Value510 = (1.0 * distribution$sample45[((index$i$508 - 1) / 1)][index$sample45$509]);
																					int traceTempVariable$s$512_1 = distributionTempVariable$var40$511;
																					if((index$i$508 == i$var109)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$512_1)) {
																								{
																									{
																										double cv$temp$162$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$512_1];
																											cv$temp$162$var121 = var121;
																										}
																										double cv$temp$163$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$512_1];
																											cv$temp$163$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample45Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$162$var121) / Math.sqrt(cv$temp$163$var122))) - (0.5 * Math.log(cv$temp$163$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value510);
																									}
																								}
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
											int traceTempVariable$s$442_1 = cv$currentValue;
											for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
												if((i$var34 == i$var109)) {
													if(!guard$sample45gaussian128[((i$var109 - 0) / 1)]) {
														guard$sample45gaussian128[((i$var109 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample35) {
																	if((0 == i$var109)) {
																		for(int var74 = 0; var74 < noStates; var74 += 1) {
																			if((var74 == traceTempVariable$s$442_1)) {
																				for(int var104 = 0; var104 < noStates; var104 += 1) {
																					if((var104 == traceTempVariable$s$442_1)) {
																						{
																							{
																								double cv$temp$186$var121;
																								{
																									double var121 = pageFaultsMean[traceTempVariable$s$442_1];
																									cv$temp$186$var121 = var121;
																								}
																								double cv$temp$187$var122;
																								{
																									double var122 = pageFaultsVar[traceTempVariable$s$442_1];
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
																		for(int index$sample35$568 = 0; index$sample35$568 < noStates; index$sample35$568 += 1) {
																			int distributionTempVariable$var30$570 = index$sample35$568;
																			double cv$probabilitySample35Value569 = (1.0 * distribution$sample35[index$sample35$568]);
																			int traceTempVariable$s$571_1 = distributionTempVariable$var30$570;
																			if((0 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$571_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$571_1)) {
																								{
																									{
																										double cv$temp$188$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$571_1];
																											cv$temp$188$var121 = var121;
																										}
																										double cv$temp$189$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$571_1];
																											cv$temp$189$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample35Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$188$var121) / Math.sqrt(cv$temp$189$var122))) - (0.5 * Math.log(cv$temp$189$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value569);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$576_1 = cv$currentValue;
																if((index$i$19 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$576_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$576_1)) {
																					{
																						{
																							double cv$temp$190$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$576_1];
																								cv$temp$190$var121 = var121;
																							}
																							double cv$temp$191$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$576_1];
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
																int traceTempVariable$s$577_1 = distributionTempVariable$var40$31;
																if((index$i$28 == i$var109)) {
																	for(int var74 = 0; var74 < noStates; var74 += 1) {
																		if((var74 == traceTempVariable$s$577_1)) {
																			for(int var104 = 0; var104 < noStates; var104 += 1) {
																				if((var104 == traceTempVariable$s$577_1)) {
																					{
																						{
																							double cv$temp$192$var121;
																							{
																								double var121 = pageFaultsMean[traceTempVariable$s$577_1];
																								cv$temp$192$var121 = var121;
																							}
																							double cv$temp$193$var122;
																							{
																								double var122 = pageFaultsVar[traceTempVariable$s$577_1];
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
																for(int index$i$578 = 1; index$i$578 < samples; index$i$578 += 1) {
																	if((!(index$i$578 == index$i$19) && !(index$i$578 == index$i$28))) {
																		for(int index$sample45$579 = 0; index$sample45$579 < noStates; index$sample45$579 += 1) {
																			int distributionTempVariable$var40$581 = index$sample45$579;
																			double cv$probabilitySample45Value580 = (1.0 * distribution$sample45[((index$i$578 - 1) / 1)][index$sample45$579]);
																			int traceTempVariable$s$582_1 = distributionTempVariable$var40$581;
																			if((index$i$578 == i$var109)) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if((var74 == traceTempVariable$s$582_1)) {
																						for(int var104 = 0; var104 < noStates; var104 += 1) {
																							if((var104 == traceTempVariable$s$582_1)) {
																								{
																									{
																										double cv$temp$194$var121;
																										{
																											double var121 = pageFaultsMean[traceTempVariable$s$582_1];
																											cv$temp$194$var121 = var121;
																										}
																										double cv$temp$195$var122;
																										{
																											double var122 = pageFaultsVar[traceTempVariable$s$582_1];
																											cv$temp$195$var122 = var122;
																										}
																										if(((Math.log(cv$probabilitySample45Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$194$var121) / Math.sqrt(cv$temp$195$var122))) - (0.5 * Math.log(cv$temp$195$var122)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value580);
																									}
																								}
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
					int traceTempVariable$var37$621_1 = cv$currentValue;
					for(int index$i$621_2 = 1; index$i$621_2 < samples; index$i$621_2 += 1) {
						if((i$var34 == (index$i$621_2 - 1))) {
							{
								int index$i$623 = index$i$621_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var39;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var21 = 0; var21 < noStates; var21 += 1) {
									if((var21 == traceTempVariable$var37$621_1)) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample35) {
												if((0 == (i$var34 - 1))) {
													for(int index$var21$630_1 = 0; index$var21$630_1 < noStates; index$var21$630_1 += 1) {
														if((index$var21$630_1 == st[(i$var34 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample35$626 = 0; index$sample35$626 < noStates; index$sample35$626 += 1) {
														int distributionTempVariable$var30$628 = index$sample35$626;
														double cv$probabilitySample35Value627 = (1.0 * distribution$sample35[index$sample35$626]);
														int traceTempVariable$var37$629_1 = distributionTempVariable$var30$628;
														if((0 == (i$var34 - 1))) {
															for(int index$var21$631_1 = 0; index$var21$631_1 < noStates; index$var21$631_1 += 1) {
																if((index$var21$631_1 == traceTempVariable$var37$629_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample35Value627);
															}
														}
													}
												}
											}
											int traceTempVariable$var37$632_1 = cv$currentValue;
											if((index$i$19 == (i$var34 - 1))) {
												for(int index$var21$638_1 = 0; index$var21$638_1 < noStates; index$var21$638_1 += 1) {
													if((index$var21$638_1 == traceTempVariable$var37$632_1))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$633 = 1; index$i$633 < samples; index$i$633 += 1) {
												if((!(index$i$633 == index$i$19) && !(index$i$633 == index$i$623))) {
													for(int index$sample45$634 = 0; index$sample45$634 < noStates; index$sample45$634 += 1) {
														int distributionTempVariable$var40$636 = index$sample45$634;
														double cv$probabilitySample45Value635 = (1.0 * distribution$sample45[((index$i$633 - 1) / 1)][index$sample45$634]);
														int traceTempVariable$var37$637_1 = distributionTempVariable$var40$636;
														if((index$i$633 == (i$var34 - 1))) {
															for(int index$var21$639_1 = 0; index$var21$639_1 < noStates; index$var21$639_1 += 1) {
																if((index$var21$639_1 == traceTempVariable$var37$637_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample45Value635);
															}
														}
													}
												}
											}
											double[] cv$temp$196$var38;
											{
												double[] var38 = m[traceTempVariable$var37$621_1];
												cv$temp$196$var38 = var38;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$196$var38);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample45[((index$i$621_2 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample45[((i$var34 - 1) / 1)];
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample58(int var52, int threadID$cv$var52, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = cpuMean[var52];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var111$16_1 = cv$currentValue;
										if((var52 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var111$17_1 = cv$currentValue;
												if((var52 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	private final void sample69(int var63, int threadID$cv$var63, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = memMean[var63];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$6$var116) / Math.sqrt(cv$temp$7$var117))) - (0.5 * Math.log(cv$temp$7$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$8$var116) / Math.sqrt(cv$temp$9$var117))) - (0.5 * Math.log(cv$temp$9$var117)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$10$var116) / Math.sqrt(cv$temp$11$var117))) - (0.5 * Math.log(cv$temp$11$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$12$var116) / Math.sqrt(cv$temp$13$var117))) - (0.5 * Math.log(cv$temp$13$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$14$var116) / Math.sqrt(cv$temp$15$var117))) - (0.5 * Math.log(cv$temp$15$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var116$16_1 = cv$currentValue;
										if((var63 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$18$var116) / Math.sqrt(cv$temp$19$var117))) - (0.5 * Math.log(cv$temp$19$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var116$17_1 = cv$currentValue;
												if((var63 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$22$var116) / Math.sqrt(cv$temp$23$var117))) - (0.5 * Math.log(cv$temp$23$var117)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$24$var116) / Math.sqrt(cv$temp$25$var117))) - (0.5 * Math.log(cv$temp$25$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$26$var116) / Math.sqrt(cv$temp$27$var117))) - (0.5 * Math.log(cv$temp$27$var117)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$temp$28$var116) / Math.sqrt(cv$temp$29$var117))) - (0.5 * Math.log(cv$temp$29$var117)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	private final void sample80(int var74, int threadID$cv$var74, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = pageFaultsMean[var74];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$6$var121) / Math.sqrt(cv$temp$7$var122))) - (0.5 * Math.log(cv$temp$7$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$8$var121) / Math.sqrt(cv$temp$9$var122))) - (0.5 * Math.log(cv$temp$9$var122)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$10$var121) / Math.sqrt(cv$temp$11$var122))) - (0.5 * Math.log(cv$temp$11$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$12$var121) / Math.sqrt(cv$temp$13$var122))) - (0.5 * Math.log(cv$temp$13$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$14$var121) / Math.sqrt(cv$temp$15$var122))) - (0.5 * Math.log(cv$temp$15$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var121$16_1 = cv$currentValue;
										if((var74 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$18$var121) / Math.sqrt(cv$temp$19$var122))) - (0.5 * Math.log(cv$temp$19$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var121$17_1 = cv$currentValue;
												if((var74 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$22$var121) / Math.sqrt(cv$temp$23$var122))) - (0.5 * Math.log(cv$temp$23$var122)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$24$var121) / Math.sqrt(cv$temp$25$var122))) - (0.5 * Math.log(cv$temp$25$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$26$var121) / Math.sqrt(cv$temp$27$var122))) - (0.5 * Math.log(cv$temp$27$var122)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$temp$28$var121) / Math.sqrt(cv$temp$29$var122))) - (0.5 * Math.log(cv$temp$29$var122)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	private final void sample90(int var84, int threadID$cv$var84, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = cpuVar[var84];
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
							if(fixedFlag$sample35) {
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
												if(fixedFlag$sample45) {
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
															for(int index$sample45$26 = 0; index$sample45$26 < noStates; index$sample45$26 += 1) {
																int distributionTempVariable$var40$28 = index$sample45$26;
																double cv$probabilitySample45Value27 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$26]);
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
																					if(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$6$var111) / Math.sqrt(cv$temp$7$var112))) - (0.5 * Math.log(cv$temp$7$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value27);
																				}
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
									for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
										int distributionTempVariable$var30$5 = index$sample35$3;
										double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
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
																			if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$8$var111) / Math.sqrt(cv$temp$9$var112))) - (0.5 * Math.log(cv$temp$9$var112)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample35$33 = 0; index$sample35$33 < noStates; index$sample35$33 += 1) {
																int distributionTempVariable$var30$35 = index$sample35$33;
																double cv$probabilitySample35Value34 = (cv$probabilitySample35Value4 * distribution$sample35[index$sample35$33]);
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
																					if(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$10$var111) / Math.sqrt(cv$temp$11$var112))) - (0.5 * Math.log(cv$temp$11$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample45) {
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
																					if(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$12$var111) / Math.sqrt(cv$temp$13$var112))) - (0.5 * Math.log(cv$temp$13$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
																if(true) {
																	for(int index$sample45$41 = 0; index$sample45$41 < noStates; index$sample45$41 += 1) {
																		int distributionTempVariable$var40$43 = index$sample45$41;
																		double cv$probabilitySample45Value42 = (cv$probabilitySample35Value4 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$41]);
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
																							if(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$14$var111) / Math.sqrt(cv$temp$15$var112))) - (0.5 * Math.log(cv$temp$15$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value42);
																						}
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
							if(fixedFlag$sample45) {
								for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
									if((i$var34 == i$var109)) {
										double traceTempVariable$var112$16_1 = cv$currentValue;
										if((var84 == st[i$var109])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample35) {
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
															for(int index$sample35$48 = 0; index$sample35$48 < noStates; index$sample35$48 += 1) {
																int distributionTempVariable$var30$50 = index$sample35$48;
																double cv$probabilitySample35Value49 = (1.0 * distribution$sample35[index$sample35$48]);
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
																					if(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$18$var111) / Math.sqrt(cv$temp$19$var112))) - (0.5 * Math.log(cv$temp$19$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value49);
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
										for(int index$sample45$12 = 0; index$sample45$12 < noStates; index$sample45$12 += 1) {
											int distributionTempVariable$var40$14 = index$sample45$12;
											double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var34 - 1) / 1)][index$sample45$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var40$14;
											if((i$var34 == i$var109)) {
												double traceTempVariable$var112$17_1 = cv$currentValue;
												if((var84 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample35) {
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
																					if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$22$var111) / Math.sqrt(cv$temp$23$var112))) - (0.5 * Math.log(cv$temp$23$var112)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample35$57 = 0; index$sample35$57 < noStates; index$sample35$57 += 1) {
																		int distributionTempVariable$var30$59 = index$sample35$57;
																		double cv$probabilitySample35Value58 = (cv$probabilitySample45Value13 * distribution$sample35[index$sample35$57]);
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
																							if(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))))) + 1)) + (Math.log(cv$probabilitySample35Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$24$var111) / Math.sqrt(cv$temp$25$var112))) - (0.5 * Math.log(cv$temp$25$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample35Value58);
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
																				if(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$26$var111) / Math.sqrt(cv$temp$27$var112))) - (0.5 * Math.log(cv$temp$27$var112)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var34)) {
																	for(int index$sample45$65 = 0; index$sample45$65 < noStates; index$sample45$65 += 1) {
																		int distributionTempVariable$var40$67 = index$sample45$65;
																		double cv$probabilitySample45Value66 = (cv$probabilitySample45Value13 * distribution$sample45[((index$i$64 - 1) / 1)][index$sample45$65]);
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
																							if(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))))) + 1)) + (Math.log(cv$probabilitySample45Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$temp$28$var111) / Math.sqrt(cv$temp$29$var112))) - (0.5 * Math.log(cv$temp$29$var112)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample45Value66);
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

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var21 = 0; var21 < noStates; var21 += 1)
				cv$max = Math.max(cv$max, noStates);
			{
				int cv$threadCount = threadCount();
				cv$var22$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var22$countGlobal[cv$index] = new double[cv$max];
			}
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
			guard$sample35gaussian118$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample35gaussian123$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample35gaussian128$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$var23$max = noStates;
			cv$var40$stateProbabilityGlobal = new double[cv$var23$max];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample45gaussian118$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample45gaussian123$global = new boolean[cv$max_i$var109];
		}
		{
			int cv$max_i$var109 = 0;
			cv$max_i$var109 = Math.max(cv$max_i$var109, ((length$cpu_measured - 0) / 1));
			guard$sample45gaussian128$global = new boolean[cv$max_i$var109];
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
			distribution$sample35 = new double[noStates];
		}
		{
			distribution$sample45 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)][];
			for(int i$var34 = 1; i$var34 < length$cpu_measured; i$var34 += 1)
				distribution$sample45[((i$var34 - 1) / 1)] = new double[noStates];
		}
		{
			logProbability$var39 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample45 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var113 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample119 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var118 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample124 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var123 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample129 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						double[] var22 = m[var21];
						if(!fixedFlag$sample25)
							DistributionSampling.sampleDirichlet(RNG$1, v, var22);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample45)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
						if(!fixedFlag$sample58)
							cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
						if(!fixedFlag$sample69)
							memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
						if(!fixedFlag$sample80)
							pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
						if(!fixedFlag$sample90)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
						if(!fixedFlag$sample100)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
						if(!fixedFlag$sample110)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						if(!fixedFlag$sample119)
							cpu[i$var109] = ((Math.sqrt(cpuVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var109]]);
						if(!fixedFlag$sample124)
							mem[i$var109] = ((Math.sqrt(memVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var109]]);
						if(!fixedFlag$sample129)
							pageFaults[i$var109] = ((Math.sqrt(pageFaultsVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var109]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						double[] var22 = m[var21];
						if(!fixedFlag$sample25)
							DistributionSampling.sampleDirichlet(RNG$1, v, var22);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		double[] cv$distribution$sample35 = distribution$sample35;
		for(int index$var29 = 0; index$var29 < noStates; index$var29 += 1) {
			double cv$value = (((0.0 <= index$var29) && (index$var29 < initialStateDistribution.length))?initialStateDistribution[index$var29]:0.0);
			if(!fixedFlag$sample35)
				cv$distribution$sample35[index$var29] = cv$value;
		}
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			double[] cv$distribution$sample45 = distribution$sample45[((i$var34 - 1) / 1)];
			for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1) {
				if(!fixedFlag$sample45)
					cv$distribution$sample45[index$var39] = 0.0;
			}
			if(fixedFlag$sample35) {
				if((0 == (i$var34 - 1))) {
					for(int var21 = 0; var21 < noStates; var21 += 1) {
						if((var21 == st[(i$var34 - 1)])) {
							{
								if(!fixedFlag$sample45) {
									double[] var38 = m[st[(i$var34 - 1)]];
									for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
										cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (1.0 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample35$2 = 0; index$sample35$2 < noStates; index$sample35$2 += 1) {
						int distributionTempVariable$var30$4 = index$sample35$2;
						double cv$probabilitySample35Value3 = (1.0 * distribution$sample35[index$sample35$2]);
						int traceTempVariable$var37$5_1 = distributionTempVariable$var30$4;
						if((0 == (i$var34 - 1))) {
							for(int var21 = 0; var21 < noStates; var21 += 1) {
								if((var21 == traceTempVariable$var37$5_1)) {
									{
										if(!fixedFlag$sample45) {
											double[] var38 = m[traceTempVariable$var37$5_1];
											for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
												cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample35Value3 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample45) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var34 - 1))) {
						for(int var21 = 0; var21 < noStates; var21 += 1) {
							if((var21 == st[(i$var34 - 1)])) {
								{
									if(!fixedFlag$sample45) {
										double[] var38 = m[st[(i$var34 - 1)]];
										for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
											cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (1.0 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample45$10 = 0; index$sample45$10 < noStates; index$sample45$10 += 1) {
							int distributionTempVariable$var40$12 = index$sample45$10;
							double cv$probabilitySample45Value11 = (1.0 * distribution$sample45[((index$i$9 - 1) / 1)][index$sample45$10]);
							int traceTempVariable$var37$13_1 = distributionTempVariable$var40$12;
							if((index$i$9 == (i$var34 - 1))) {
								for(int var21 = 0; var21 < noStates; var21 += 1) {
									if((var21 == traceTempVariable$var37$13_1)) {
										{
											if(!fixedFlag$sample45) {
												double[] var38 = m[traceTempVariable$var37$13_1];
												for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
													cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample45Value11 * (((0.0 <= index$var39) && (index$var39 < var38.length))?var38[index$var39]:0.0)));
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
				if(!fixedFlag$sample45)
					cv$var39$sum = (cv$var39$sum + cv$distribution$sample45[index$var39]);
			}
			for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1) {
				if(!fixedFlag$sample45)
					cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] / cv$var39$sum);
			}
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
						if(!fixedFlag$sample58)
							cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
						if(!fixedFlag$sample69)
							memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
						if(!fixedFlag$sample80)
							pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
						if(!fixedFlag$sample90)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
						if(!fixedFlag$sample100)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
						if(!fixedFlag$sample110)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						double[] var22 = m[var21];
						if(!fixedFlag$sample25)
							DistributionSampling.sampleDirichlet(RNG$1, v, var22);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample45)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
						if(!fixedFlag$sample58)
							cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
						if(!fixedFlag$sample69)
							memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
						if(!fixedFlag$sample80)
							pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
						if(!fixedFlag$sample90)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
						if(!fixedFlag$sample100)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
						if(!fixedFlag$sample110)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
							if(!fixedFlag$sample25)
								sample25(var21, threadID$var21, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample32)
				sample32();
			if(!fixedFlag$sample35)
				sample35();
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				if(!fixedFlag$sample45)
					sample45(i$var34);
			}
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
							if(!fixedFlag$sample58)
								sample58(var52, threadID$var52, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
							if(!fixedFlag$sample69)
								sample69(var63, threadID$var63, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
							if(!fixedFlag$sample80)
								sample80(var74, threadID$var74, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
							if(!fixedFlag$sample90)
								sample90(var84, threadID$var84, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
							if(!fixedFlag$sample100)
								sample100(var94, threadID$var94, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
							if(!fixedFlag$sample110)
								sample110(var104, threadID$var104, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
							if(!fixedFlag$sample110)
								sample110(var104, threadID$var104, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
							if(!fixedFlag$sample100)
								sample100(var94, threadID$var94, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
							if(!fixedFlag$sample90)
								sample90(var84, threadID$var84, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
							if(!fixedFlag$sample80)
								sample80(var74, threadID$var74, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
							if(!fixedFlag$sample69)
								sample69(var63, threadID$var63, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
							if(!fixedFlag$sample58)
								sample58(var52, threadID$var52, RNG$1);
						}
				}
			);
			for(int i$var34 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var34 >= ((1 - 1) + 1); i$var34 -= 1) {
				if(!fixedFlag$sample45)
					sample45(i$var34);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample32)
				sample32();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
							if(!fixedFlag$sample25)
								sample25(var21, threadID$var21, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var14, int forEnd$var14, int threadID$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var14 = forStart$var14; var14 < forEnd$var14; var14 += 1)
						v[var14] = 0.1;
			}
		);
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
		if(!fixedProbFlag$sample32)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var30 = 0.0;
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
			logProbability$var39[((i$var34 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				logProbability$sample45[((i$var34 - 1) / 1)] = 0.0;
		}
		logProbability$var48 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var53 = 0.0;
		logProbability$var59 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample69)
			logProbability$var64 = 0.0;
		logProbability$var70 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$var75 = 0.0;
		logProbability$var80 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample90)
			logProbability$var85 = 0.0;
		logProbability$var90 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample100)
			logProbability$var95 = 0.0;
		logProbability$var100 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample110)
			logProbability$var105 = 0.0;
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var113[((i$var109 - 0) / 1)] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample119) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample119[((i$var109 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var118[((i$var109 - 0) / 1)] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample124) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample124[((i$var109 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var123[((i$var109 - 0) / 1)] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample129) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample129[((i$var109 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		if(fixedFlag$sample100)
			logProbabilityValue$sample100();
		if(fixedFlag$sample110)
			logProbabilityValue$sample110();
		logProbabilityValue$sample119();
		logProbabilityValue$sample124();
		logProbabilityValue$sample129();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample32();
		logProbabilityDistribution$sample35();
		logProbabilityDistribution$sample45();
		logProbabilityValue$sample58();
		logProbabilityValue$sample69();
		logProbabilityValue$sample80();
		logProbabilityValue$sample90();
		logProbabilityValue$sample100();
		logProbabilityValue$sample110();
		logProbabilityDistribution$sample119();
		logProbabilityDistribution$sample124();
		logProbabilityDistribution$sample129();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample32();
		logProbabilityValue$sample35();
		logProbabilityValue$sample45();
		logProbabilityValue$sample58();
		logProbabilityValue$sample69();
		logProbabilityValue$sample80();
		logProbabilityValue$sample90();
		logProbabilityValue$sample100();
		logProbabilityValue$sample110();
		logProbabilityValue$sample119();
		logProbabilityValue$sample124();
		logProbabilityValue$sample129();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1) {
						double[] var22 = m[var21];
						if(!fixedFlag$sample25)
							DistributionSampling.sampleDirichlet(RNG$1, v, var22);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
			if(!fixedFlag$sample45)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1) {
						if(!fixedFlag$sample58)
							cpuMean[var52] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1) {
						if(!fixedFlag$sample69)
							memMean[var63] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
						if(!fixedFlag$sample80)
							pageFaultsMean[var74] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1) {
						if(!fixedFlag$sample90)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1) {
						if(!fixedFlag$sample100)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1) {
						if(!fixedFlag$sample110)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
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