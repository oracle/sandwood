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
	private double[] cv$distributionAccumulator$var61;
	private double[][] cv$var36$countGlobal;
	private double[] cv$var41$countGlobal;
	private double[] cv$var44$stateProbabilityGlobal;
	private double[] cv$var62$stateProbabilityGlobal;
	private double[] distribution$sample49;
	private double[][] distribution$sample67;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample140 = false;
	private boolean fixedFlag$sample157 = false;
	private boolean fixedFlag$sample174 = false;
	private boolean fixedFlag$sample190 = false;
	private boolean fixedFlag$sample195 = false;
	private boolean fixedFlag$sample200 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample140 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample174 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample195 = false;
	private boolean fixedProbFlag$sample200 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] guard$sample49gaussian189$global;
	private boolean[] guard$sample49gaussian194$global;
	private boolean[] guard$sample49gaussian199$global;
	private boolean[] guard$sample67gaussian189$global;
	private boolean[] guard$sample67gaussian194$global;
	private boolean[] guard$sample67gaussian199$global;
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
	private double[] logProbability$sample190;
	private double[] logProbability$sample195;
	private double[] logProbability$sample200;
	private double[] logProbability$sample67;
	private double logProbability$st;
	private double logProbability$var100;
	private double logProbability$var106;
	private double logProbability$var118;
	private double logProbability$var123;
	private double logProbability$var135;
	private double logProbability$var140;
	private double logProbability$var152;
	private double logProbability$var157;
	private double logProbability$var169;
	private double[] logProbability$var184;
	private double[] logProbability$var189;
	private double[] logProbability$var194;
	private double logProbability$var24;
	private double logProbability$var36;
	private double logProbability$var40;
	private double logProbability$var43;
	private double logProbability$var44;
	private double[] logProbability$var61;
	private double logProbability$var70;
	private double logProbability$var82;
	private double logProbability$var88;
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
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	@Override
	public final void set$cpuMean(double[] cv$value) {
		cpuMean = cv$value;
		setFlag$cpuMean = true;
		fixedProbFlag$sample87 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	@Override
	public final void set$cpuVar(double[] cv$value) {
		cpuVar = cv$value;
		setFlag$cpuVar = true;
		fixedProbFlag$sample140 = false;
		fixedProbFlag$sample190 = false;
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
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		fixedFlag$sample105 = cv$value;
		fixedProbFlag$sample105 = (fixedFlag$sample105 && fixedProbFlag$sample105);
		fixedProbFlag$sample195 = (fixedFlag$sample105 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (fixedFlag$sample123 && fixedProbFlag$sample123);
		fixedProbFlag$sample200 = (fixedFlag$sample123 && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample140() {
		return fixedFlag$sample140;
	}

	@Override
	public final void set$fixedFlag$sample140(boolean cv$value) {
		fixedFlag$sample140 = cv$value;
		fixedProbFlag$sample140 = (fixedFlag$sample140 && fixedProbFlag$sample140);
		fixedProbFlag$sample190 = (fixedFlag$sample140 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	@Override
	public final void set$fixedFlag$sample157(boolean cv$value) {
		fixedFlag$sample157 = cv$value;
		fixedProbFlag$sample157 = (fixedFlag$sample157 && fixedProbFlag$sample157);
		fixedProbFlag$sample195 = (fixedFlag$sample157 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample174() {
		return fixedFlag$sample174;
	}

	@Override
	public final void set$fixedFlag$sample174(boolean cv$value) {
		fixedFlag$sample174 = cv$value;
		fixedProbFlag$sample174 = (fixedFlag$sample174 && fixedProbFlag$sample174);
		fixedProbFlag$sample200 = (fixedFlag$sample174 && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		fixedFlag$sample190 = cv$value;
		fixedProbFlag$sample190 = (fixedFlag$sample190 && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample195() {
		return fixedFlag$sample195;
	}

	@Override
	public final void set$fixedFlag$sample195(boolean cv$value) {
		fixedFlag$sample195 = cv$value;
		fixedProbFlag$sample195 = (fixedFlag$sample195 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample200() {
		return fixedFlag$sample200;
	}

	@Override
	public final void set$fixedFlag$sample200(boolean cv$value) {
		fixedFlag$sample200 = cv$value;
		fixedProbFlag$sample200 = (fixedFlag$sample200 && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample67 = (fixedFlag$sample39 && fixedProbFlag$sample67);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
		fixedProbFlag$sample49 = (fixedFlag$sample46 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
		fixedProbFlag$sample67 = (fixedFlag$sample49 && fixedProbFlag$sample67);
		fixedProbFlag$sample190 = (fixedFlag$sample49 && fixedProbFlag$sample190);
		fixedProbFlag$sample195 = (fixedFlag$sample49 && fixedProbFlag$sample195);
		fixedProbFlag$sample200 = (fixedFlag$sample49 && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		fixedFlag$sample67 = cv$value;
		fixedProbFlag$sample67 = (fixedFlag$sample67 && fixedProbFlag$sample67);
		fixedProbFlag$sample190 = (fixedFlag$sample67 && fixedProbFlag$sample190);
		fixedProbFlag$sample195 = (fixedFlag$sample67 && fixedProbFlag$sample195);
		fixedProbFlag$sample200 = (fixedFlag$sample67 && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedProbFlag$sample87);
		fixedProbFlag$sample190 = (fixedFlag$sample87 && fixedProbFlag$sample190);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample49 = false;
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
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample67 = false;
	}

	@Override
	public final double[] get$mem() {
		return mem;
	}

	@Override
	public final void set$mem(double[] cv$value) {
		mem = cv$value;
		setFlag$mem = true;
		fixedProbFlag$sample195 = false;
	}

	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	@Override
	public final void set$memMean(double[] cv$value) {
		memMean = cv$value;
		setFlag$memMean = true;
		fixedProbFlag$sample105 = false;
		fixedProbFlag$sample195 = false;
	}

	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	@Override
	public final void set$memVar(double[] cv$value) {
		memVar = cv$value;
		setFlag$memVar = true;
		fixedProbFlag$sample157 = false;
		fixedProbFlag$sample195 = false;
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
		fixedProbFlag$sample200 = false;
	}

	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
		fixedProbFlag$sample123 = false;
		fixedProbFlag$sample200 = false;
	}

	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		pageFaultsVar = cv$value;
		setFlag$pageFaultsVar = true;
		fixedProbFlag$sample174 = false;
		fixedProbFlag$sample200 = false;
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
		fixedProbFlag$sample49 = false;
		fixedProbFlag$sample67 = false;
		fixedProbFlag$sample190 = false;
		fixedProbFlag$sample195 = false;
		fixedProbFlag$sample200 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var180];
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var81 = 0; var81 < noStates; var81 += 1) {
								if((var81 == st[i$var180])) {
									if((0 == i$var180)) {
										for(int var134 = 0; var134 < noStates; var134 += 1) {
											if((var134 == st[i$var180])) {
												{
													double var182 = cpuMean[st[i$var180]];
													double var183 = cpuVar[st[i$var180]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
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
							for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample49$3;
								double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
								if((0 == i$var180)) {
									for(int var81 = 0; var81 < noStates; var81 += 1) {
										if((var81 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var44$5;
											if((0 == i$var180)) {
												for(int var134 = 0; var134 < noStates; var134 += 1) {
													if((var134 == traceTempVariable$s$10_1)) {
														{
															double var182 = cpuMean[traceTempVariable$s$10_1];
															double var183 = cpuVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample49$11 = 0; index$sample49$11 < noStates; index$sample49$11 += 1) {
													int distributionTempVariable$var44$13 = index$sample49$11;
													double cv$probabilitySample49Value12 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var44$13;
													if((0 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$14_1)) {
																{
																	double var182 = cpuMean[traceTempVariable$s$14_1];
																	double var183 = cpuVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var81 = 0; var81 < noStates; var81 += 1) {
								if((var81 == st[i$var180])) {
									if(fixedFlag$sample67) {
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if((i$var56 == i$var180)) {
												for(int var134 = 0; var134 < noStates; var134 += 1) {
													if((var134 == st[i$var180])) {
														{
															double var182 = cpuMean[st[i$var180]];
															double var183 = cpuVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
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
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if(true) {
												for(int index$sample67$27 = 0; index$sample67$27 < noStates; index$sample67$27 += 1) {
													int distributionTempVariable$var62$29 = index$sample67$27;
													double cv$probabilitySample67Value28 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var62$29;
													if((i$var56 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$30_1)) {
																{
																	double var182 = cpuMean[traceTempVariable$s$30_1];
																	double var183 = cpuVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value28);
																}
															}
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
							for(int index$sample49$19 = 0; index$sample49$19 < noStates; index$sample49$19 += 1) {
								int distributionTempVariable$var44$21 = index$sample49$19;
								double cv$probabilitySample49Value20 = (1.0 * distribution$sample49[index$sample49$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var44$21;
								if((0 == i$var180)) {
									for(int var81 = 0; var81 < noStates; var81 += 1) {
										if((var81 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$22_1)) {
																{
																	double var182 = cpuMean[traceTempVariable$s$22_1];
																	double var183 = cpuVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$33 = 0; index$sample67$33 < noStates; index$sample67$33 += 1) {
															int distributionTempVariable$var62$35 = index$sample67$33;
															double cv$probabilitySample67Value34 = (cv$probabilitySample49Value20 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var62$35;
															if((i$var56 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$36_1)) {
																		{
																			double var182 = cpuMean[traceTempVariable$s$36_1];
																			double var183 = cpuVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var81 = 0; var81 < noStates; var81 += 1) {
									if((var81 == st[i$var180])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var180)) {
												for(int var134 = 0; var134 < noStates; var134 += 1) {
													if((var134 == st[i$var180])) {
														{
															double var182 = cpuMean[st[i$var180]];
															double var183 = cpuVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
									int distributionTempVariable$var62$45 = index$sample67$43;
									double cv$probabilitySample67Value44 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var62$45;
									if((i$var56 == i$var180)) {
										for(int var81 = 0; var81 < noStates; var81 += 1) {
											if((var81 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var62$45;
												if((i$var56 == i$var180)) {
													for(int var134 = 0; var134 < noStates; var134 += 1) {
														if((var134 == traceTempVariable$s$50_1)) {
															{
																double var182 = cpuMean[traceTempVariable$s$50_1];
																double var183 = cpuVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample67Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var56)) {
														for(int index$sample67$52 = 0; index$sample67$52 < noStates; index$sample67$52 += 1) {
															int distributionTempVariable$var62$54 = index$sample67$52;
															double cv$probabilitySample67Value53 = (cv$probabilitySample67Value44 * distribution$sample67[((index$i$51 - 1) / 1)][index$sample67$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var62$54;
															if((index$i$51 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$55_1)) {
																		{
																			double var182 = cpuMean[traceTempVariable$s$55_1];
																			double var183 = cpuVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var81 = 0; var81 < noStates; var81 += 1) {
									if((var81 == st[i$var180])) {
										if(fixedFlag$sample49) {
											if((0 == i$var180)) {
												for(int var134 = 0; var134 < noStates; var134 += 1) {
													if((var134 == st[i$var180])) {
														{
															double var182 = cpuMean[st[i$var180]];
															double var183 = cpuVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
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
												for(int index$sample49$68 = 0; index$sample49$68 < noStates; index$sample49$68 += 1) {
													int distributionTempVariable$var44$70 = index$sample49$68;
													double cv$probabilitySample49Value69 = (1.0 * distribution$sample49[index$sample49$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var44$70;
													if((0 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$71_1)) {
																{
																	double var182 = cpuMean[traceTempVariable$s$71_1];
																	double var183 = cpuVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value69);
																}
															}
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$61 = 0; index$sample67$61 < noStates; index$sample67$61 += 1) {
									int distributionTempVariable$var62$63 = index$sample67$61;
									double cv$probabilitySample67Value62 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var62$63;
									if((i$var56 == i$var180)) {
										for(int var81 = 0; var81 < noStates; var81 += 1) {
											if((var81 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample49) {
													if((0 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$64_1)) {
																{
																	double var182 = cpuMean[traceTempVariable$s$64_1];
																	double var183 = cpuVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample49$73 = 0; index$sample49$73 < noStates; index$sample49$73 += 1) {
															int distributionTempVariable$var44$75 = index$sample49$73;
															double cv$probabilitySample49Value74 = (cv$probabilitySample67Value62 * distribution$sample49[index$sample49$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var44$75;
															if((0 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$76_1)) {
																		{
																			double var182 = cpuMean[traceTempVariable$s$76_1];
																			double var183 = cpuVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample49Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var184[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample190[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var184[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample195() {
		if(!fixedProbFlag$sample195) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var180];
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var99 = 0; var99 < noStates; var99 += 1) {
								if((var99 == st[i$var180])) {
									if((0 == i$var180)) {
										for(int var151 = 0; var151 < noStates; var151 += 1) {
											if((var151 == st[i$var180])) {
												{
													double var187 = memMean[st[i$var180]];
													double var188 = memVar[st[i$var180]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
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
							for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample49$3;
								double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
								if((0 == i$var180)) {
									for(int var99 = 0; var99 < noStates; var99 += 1) {
										if((var99 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var44$5;
											if((0 == i$var180)) {
												for(int var151 = 0; var151 < noStates; var151 += 1) {
													if((var151 == traceTempVariable$s$10_1)) {
														{
															double var187 = memMean[traceTempVariable$s$10_1];
															double var188 = memVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample49$11 = 0; index$sample49$11 < noStates; index$sample49$11 += 1) {
													int distributionTempVariable$var44$13 = index$sample49$11;
													double cv$probabilitySample49Value12 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var44$13;
													if((0 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$14_1)) {
																{
																	double var187 = memMean[traceTempVariable$s$14_1];
																	double var188 = memVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var99 = 0; var99 < noStates; var99 += 1) {
								if((var99 == st[i$var180])) {
									if(fixedFlag$sample67) {
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if((i$var56 == i$var180)) {
												for(int var151 = 0; var151 < noStates; var151 += 1) {
													if((var151 == st[i$var180])) {
														{
															double var187 = memMean[st[i$var180]];
															double var188 = memVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
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
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if(true) {
												for(int index$sample67$27 = 0; index$sample67$27 < noStates; index$sample67$27 += 1) {
													int distributionTempVariable$var62$29 = index$sample67$27;
													double cv$probabilitySample67Value28 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var62$29;
													if((i$var56 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$30_1)) {
																{
																	double var187 = memMean[traceTempVariable$s$30_1];
																	double var188 = memVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value28);
																}
															}
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
							for(int index$sample49$19 = 0; index$sample49$19 < noStates; index$sample49$19 += 1) {
								int distributionTempVariable$var44$21 = index$sample49$19;
								double cv$probabilitySample49Value20 = (1.0 * distribution$sample49[index$sample49$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var44$21;
								if((0 == i$var180)) {
									for(int var99 = 0; var99 < noStates; var99 += 1) {
										if((var99 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$22_1)) {
																{
																	double var187 = memMean[traceTempVariable$s$22_1];
																	double var188 = memVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$33 = 0; index$sample67$33 < noStates; index$sample67$33 += 1) {
															int distributionTempVariable$var62$35 = index$sample67$33;
															double cv$probabilitySample67Value34 = (cv$probabilitySample49Value20 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var62$35;
															if((i$var56 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$36_1)) {
																		{
																			double var187 = memMean[traceTempVariable$s$36_1];
																			double var188 = memVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var99 = 0; var99 < noStates; var99 += 1) {
									if((var99 == st[i$var180])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var180)) {
												for(int var151 = 0; var151 < noStates; var151 += 1) {
													if((var151 == st[i$var180])) {
														{
															double var187 = memMean[st[i$var180]];
															double var188 = memVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
									int distributionTempVariable$var62$45 = index$sample67$43;
									double cv$probabilitySample67Value44 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var62$45;
									if((i$var56 == i$var180)) {
										for(int var99 = 0; var99 < noStates; var99 += 1) {
											if((var99 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var62$45;
												if((i$var56 == i$var180)) {
													for(int var151 = 0; var151 < noStates; var151 += 1) {
														if((var151 == traceTempVariable$s$50_1)) {
															{
																double var187 = memMean[traceTempVariable$s$50_1];
																double var188 = memVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample67Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var56)) {
														for(int index$sample67$52 = 0; index$sample67$52 < noStates; index$sample67$52 += 1) {
															int distributionTempVariable$var62$54 = index$sample67$52;
															double cv$probabilitySample67Value53 = (cv$probabilitySample67Value44 * distribution$sample67[((index$i$51 - 1) / 1)][index$sample67$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var62$54;
															if((index$i$51 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$55_1)) {
																		{
																			double var187 = memMean[traceTempVariable$s$55_1];
																			double var188 = memVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var99 = 0; var99 < noStates; var99 += 1) {
									if((var99 == st[i$var180])) {
										if(fixedFlag$sample49) {
											if((0 == i$var180)) {
												for(int var151 = 0; var151 < noStates; var151 += 1) {
													if((var151 == st[i$var180])) {
														{
															double var187 = memMean[st[i$var180]];
															double var188 = memVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
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
												for(int index$sample49$68 = 0; index$sample49$68 < noStates; index$sample49$68 += 1) {
													int distributionTempVariable$var44$70 = index$sample49$68;
													double cv$probabilitySample49Value69 = (1.0 * distribution$sample49[index$sample49$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var44$70;
													if((0 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$71_1)) {
																{
																	double var187 = memMean[traceTempVariable$s$71_1];
																	double var188 = memVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value69);
																}
															}
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$61 = 0; index$sample67$61 < noStates; index$sample67$61 += 1) {
									int distributionTempVariable$var62$63 = index$sample67$61;
									double cv$probabilitySample67Value62 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var62$63;
									if((i$var56 == i$var180)) {
										for(int var99 = 0; var99 < noStates; var99 += 1) {
											if((var99 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample49) {
													if((0 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$64_1)) {
																{
																	double var187 = memMean[traceTempVariable$s$64_1];
																	double var188 = memVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample49$73 = 0; index$sample49$73 < noStates; index$sample49$73 += 1) {
															int distributionTempVariable$var44$75 = index$sample49$73;
															double cv$probabilitySample49Value74 = (cv$probabilitySample67Value62 * distribution$sample49[index$sample49$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var44$75;
															if((0 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$76_1)) {
																		{
																			double var187 = memMean[traceTempVariable$s$76_1];
																			double var188 = memVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample49Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var189[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample195[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample195[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var189[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample200() {
		if(!fixedProbFlag$sample200) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var180];
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var117 = 0; var117 < noStates; var117 += 1) {
								if((var117 == st[i$var180])) {
									if((0 == i$var180)) {
										for(int var168 = 0; var168 < noStates; var168 += 1) {
											if((var168 == st[i$var180])) {
												{
													double var192 = pageFaultsMean[st[i$var180]];
													double var193 = pageFaultsVar[st[i$var180]];
													double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
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
							for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample49$3;
								double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
								int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
								if((0 == i$var180)) {
									for(int var117 = 0; var117 < noStates; var117 += 1) {
										if((var117 == traceTempVariable$s$6_1)) {
											int traceTempVariable$s$10_1 = distributionTempVariable$var44$5;
											if((0 == i$var180)) {
												for(int var168 = 0; var168 < noStates; var168 += 1) {
													if((var168 == traceTempVariable$s$10_1)) {
														{
															double var192 = pageFaultsMean[traceTempVariable$s$10_1];
															double var193 = pageFaultsVar[traceTempVariable$s$10_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
														}
													}
												}
											}
											if(!true) {
												for(int index$sample49$11 = 0; index$sample49$11 < noStates; index$sample49$11 += 1) {
													int distributionTempVariable$var44$13 = index$sample49$11;
													double cv$probabilitySample49Value12 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$11]);
													int traceTempVariable$s$14_1 = distributionTempVariable$var44$13;
													if((0 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$14_1)) {
																{
																	double var192 = pageFaultsMean[traceTempVariable$s$14_1];
																	double var193 = pageFaultsVar[traceTempVariable$s$14_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value12);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample49) {
						if((0 == i$var180)) {
							for(int var117 = 0; var117 < noStates; var117 += 1) {
								if((var117 == st[i$var180])) {
									if(fixedFlag$sample67) {
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if((i$var56 == i$var180)) {
												for(int var168 = 0; var168 < noStates; var168 += 1) {
													if((var168 == st[i$var180])) {
														{
															double var192 = pageFaultsMean[st[i$var180]];
															double var193 = pageFaultsVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
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
										for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
											if(true) {
												for(int index$sample67$27 = 0; index$sample67$27 < noStates; index$sample67$27 += 1) {
													int distributionTempVariable$var62$29 = index$sample67$27;
													double cv$probabilitySample67Value28 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$27]);
													int traceTempVariable$s$30_1 = distributionTempVariable$var62$29;
													if((i$var56 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$30_1)) {
																{
																	double var192 = pageFaultsMean[traceTempVariable$s$30_1];
																	double var193 = pageFaultsVar[traceTempVariable$s$30_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value28) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value28);
																}
															}
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
							for(int index$sample49$19 = 0; index$sample49$19 < noStates; index$sample49$19 += 1) {
								int distributionTempVariable$var44$21 = index$sample49$19;
								double cv$probabilitySample49Value20 = (1.0 * distribution$sample49[index$sample49$19]);
								int traceTempVariable$s$22_1 = distributionTempVariable$var44$21;
								if((0 == i$var180)) {
									for(int var117 = 0; var117 < noStates; var117 += 1) {
										if((var117 == traceTempVariable$s$22_1)) {
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$22_1)) {
																{
																	double var192 = pageFaultsMean[traceTempVariable$s$22_1];
																	double var193 = pageFaultsVar[traceTempVariable$s$22_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value20) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value20);
																}
															}
														}
													}
												}
											} else {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$33 = 0; index$sample67$33 < noStates; index$sample67$33 += 1) {
															int distributionTempVariable$var62$35 = index$sample67$33;
															double cv$probabilitySample67Value34 = (cv$probabilitySample49Value20 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$33]);
															int traceTempVariable$s$36_1 = distributionTempVariable$var62$35;
															if((i$var56 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$36_1)) {
																		{
																			double var192 = pageFaultsMean[traceTempVariable$s$36_1];
																			double var193 = pageFaultsVar[traceTempVariable$s$36_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value34) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value34);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var117 = 0; var117 < noStates; var117 += 1) {
									if((var117 == st[i$var180])) {
										for(int index$i$49_1 = 1; index$i$49_1 < samples; index$i$49_1 += 1) {
											if((index$i$49_1 == i$var180)) {
												for(int var168 = 0; var168 < noStates; var168 += 1) {
													if((var168 == st[i$var180])) {
														{
															double var192 = pageFaultsMean[st[i$var180]];
															double var193 = pageFaultsVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
									int distributionTempVariable$var62$45 = index$sample67$43;
									double cv$probabilitySample67Value44 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$43]);
									int traceTempVariable$s$46_1 = distributionTempVariable$var62$45;
									if((i$var56 == i$var180)) {
										for(int var117 = 0; var117 < noStates; var117 += 1) {
											if((var117 == traceTempVariable$s$46_1)) {
												int traceTempVariable$s$50_1 = distributionTempVariable$var62$45;
												if((i$var56 == i$var180)) {
													for(int var168 = 0; var168 < noStates; var168 += 1) {
														if((var168 == traceTempVariable$s$50_1)) {
															{
																double var192 = pageFaultsMean[traceTempVariable$s$50_1];
																double var193 = pageFaultsVar[traceTempVariable$s$50_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample67Value44) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
															}
														}
													}
												}
												for(int index$i$51 = 1; index$i$51 < samples; index$i$51 += 1) {
													if(!(index$i$51 == i$var56)) {
														for(int index$sample67$52 = 0; index$sample67$52 < noStates; index$sample67$52 += 1) {
															int distributionTempVariable$var62$54 = index$sample67$52;
															double cv$probabilitySample67Value53 = (cv$probabilitySample67Value44 * distribution$sample67[((index$i$51 - 1) / 1)][index$sample67$52]);
															int traceTempVariable$s$55_1 = distributionTempVariable$var62$54;
															if((index$i$51 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$55_1)) {
																		{
																			double var192 = pageFaultsMean[traceTempVariable$s$55_1];
																			double var193 = pageFaultsVar[traceTempVariable$s$55_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample67Value53) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value53);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample67) {
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((i$var56 == i$var180)) {
								for(int var117 = 0; var117 < noStates; var117 += 1) {
									if((var117 == st[i$var180])) {
										if(fixedFlag$sample49) {
											if((0 == i$var180)) {
												for(int var168 = 0; var168 < noStates; var168 += 1) {
													if((var168 == st[i$var180])) {
														{
															double var192 = pageFaultsMean[st[i$var180]];
															double var193 = pageFaultsVar[st[i$var180]];
															double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
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
												for(int index$sample49$68 = 0; index$sample49$68 < noStates; index$sample49$68 += 1) {
													int distributionTempVariable$var44$70 = index$sample49$68;
													double cv$probabilitySample49Value69 = (1.0 * distribution$sample49[index$sample49$68]);
													int traceTempVariable$s$71_1 = distributionTempVariable$var44$70;
													if((0 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$71_1)) {
																{
																	double var192 = pageFaultsMean[traceTempVariable$s$71_1];
																	double var193 = pageFaultsVar[traceTempVariable$s$71_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample49Value69) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value69);
																}
															}
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
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if(true) {
								for(int index$sample67$61 = 0; index$sample67$61 < noStates; index$sample67$61 += 1) {
									int distributionTempVariable$var62$63 = index$sample67$61;
									double cv$probabilitySample67Value62 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$61]);
									int traceTempVariable$s$64_1 = distributionTempVariable$var62$63;
									if((i$var56 == i$var180)) {
										for(int var117 = 0; var117 < noStates; var117 += 1) {
											if((var117 == traceTempVariable$s$64_1)) {
												if(fixedFlag$sample49) {
													if((0 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$64_1)) {
																{
																	double var192 = pageFaultsMean[traceTempVariable$s$64_1];
																	double var193 = pageFaultsVar[traceTempVariable$s$64_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample67Value62) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value62);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample49$73 = 0; index$sample49$73 < noStates; index$sample49$73 += 1) {
															int distributionTempVariable$var44$75 = index$sample49$73;
															double cv$probabilitySample49Value74 = (cv$probabilitySample67Value62 * distribution$sample49[index$sample49$73]);
															int traceTempVariable$s$76_1 = distributionTempVariable$var44$75;
															if((0 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$76_1)) {
																		{
																			double var192 = pageFaultsMean[traceTempVariable$s$76_1];
																			double var193 = pageFaultsVar[traceTempVariable$s$76_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample49Value74) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value74);
																		}
																	}
																}
															}
														}
													}
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
				logProbability$var194[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample200[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample200[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var194[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample49() {
		if(!fixedProbFlag$sample49) {
			if(fixedFlag$sample49) {
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
				logProbability$var43 = cv$sampleAccumulator;
				logProbability$var44 = cv$sampleProbability;
				if(fixedFlag$sample49)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample49)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample46);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var43 = cv$rvAccumulator;
			if(fixedFlag$sample49)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample67() {
		if(!fixedProbFlag$sample67) {
			if(fixedFlag$sample67) {
				double cv$accumulator = 0.0;
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var56;
					{
						int cv$sampleValue = st[i$var56];
						if(fixedFlag$sample49) {
							if((0 == (i$var56 - 1))) {
								for(int var35 = 0; var35 < noStates; var35 += 1) {
									if((var35 == st[(i$var56 - 1)])) {
										{
											double[] var60 = m[st[(i$var56 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample49$4 = 0; index$sample49$4 < noStates; index$sample49$4 += 1) {
									int distributionTempVariable$var44$6 = index$sample49$4;
									double cv$probabilitySample49Value5 = (1.0 * distribution$sample49[index$sample49$4]);
									int traceTempVariable$var59$7_1 = distributionTempVariable$var44$6;
									if((0 == (i$var56 - 1))) {
										for(int var35 = 0; var35 < noStates; var35 += 1) {
											if((var35 == traceTempVariable$var59$7_1)) {
												{
													double[] var60 = m[traceTempVariable$var59$7_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample49Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value5);
												}
											}
										}
									}
								}
							}
						}
						if((index$i$1 == (i$var56 - 1))) {
							for(int var35 = 0; var35 < noStates; var35 += 1) {
								if((var35 == st[(i$var56 - 1)])) {
									{
										double[] var60 = m[st[(i$var56 - 1)]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample67) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var56 - 1))) {
									for(int var35 = 0; var35 < noStates; var35 += 1) {
										if((var35 == st[(i$var56 - 1)])) {
											{
												double[] var60 = m[st[(i$var56 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample67$13 = 0; index$sample67$13 < noStates; index$sample67$13 += 1) {
										int distributionTempVariable$var62$15 = index$sample67$13;
										double cv$probabilitySample67Value14 = (1.0 * distribution$sample67[((index$i$12 - 1) / 1)][index$sample67$13]);
										int traceTempVariable$var59$16_1 = distributionTempVariable$var62$15;
										if((index$i$12 == (i$var56 - 1))) {
											for(int var35 = 0; var35 < noStates; var35 += 1) {
												if((var35 == traceTempVariable$var59$16_1)) {
													{
														double[] var60 = m[traceTempVariable$var59$16_1];
														double cv$weightedProbability = (Math.log(cv$probabilitySample67Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value14);
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
					logProbability$var61[((i$var56 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample67[((i$var56 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample67)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample67)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample67 = ((fixedFlag$sample67 && fixedFlag$sample39) && fixedFlag$sample49);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample67[((i$var56 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var61[((i$var56 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample67)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample105() {
		if(!fixedProbFlag$sample105) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var99 = 0; var99 < noStates; var99 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = memMean[var99];
					{
						{
							double var86 = 94.0;
							double var87 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var86) / Math.sqrt(var87))) - (0.5 * Math.log(var87))));
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
			logProbability$var88 = cv$sampleAccumulator;
			logProbability$var100 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample105)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample105 = fixedFlag$sample105;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var100;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var88 = cv$rvAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample105)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var117 = 0; var117 < noStates; var117 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaultsMean[var117];
					{
						{
							double var104 = 814.0;
							double var105 = 335550.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var104) / Math.sqrt(var105))) - (0.5 * Math.log(var105))));
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
			logProbability$var106 = cv$sampleAccumulator;
			logProbability$var118 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = fixedFlag$sample123;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var118;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var106 = cv$rvAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample140() {
		if(!fixedProbFlag$sample140) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var134 = 0; var134 < noStates; var134 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpuVar[var134];
					{
						{
							double var122 = 5.0;
							double var121 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var122, var121));
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
			logProbability$var123 = cv$sampleAccumulator;
			logProbability$var135 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample140)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample140 = fixedFlag$sample140;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var135;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var123 = cv$rvAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample140)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var151 = 0; var151 < noStates; var151 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = memVar[var151];
					{
						{
							double var139 = 5.0;
							double var138 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var139, var138));
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
			logProbability$var140 = cv$sampleAccumulator;
			logProbability$var152 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample157)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = fixedFlag$sample157;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var152;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var140 = cv$rvAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample157)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample174() {
		if(!fixedProbFlag$sample174) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var168 = 0; var168 < noStates; var168 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaultsVar[var168];
					{
						{
							double var156 = 5.0;
							double var155 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var156, var155));
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
			logProbability$var157 = cv$sampleAccumulator;
			logProbability$var169 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample174)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample174 = fixedFlag$sample174;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var169;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var157 = cv$rvAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample174)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpu[i$var180];
					{
						{
							double var182 = cpuMean[st[i$var180]];
							double var183 = cpuVar[st[i$var180]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var182) / Math.sqrt(var183))) - (0.5 * Math.log(var183))));
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
				logProbability$var184[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample190[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample190[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var184[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample195() {
		if(!fixedProbFlag$sample195) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mem[i$var180];
					{
						{
							double var187 = memMean[st[i$var180]];
							double var188 = memVar[st[i$var180]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var187) / Math.sqrt(var188))) - (0.5 * Math.log(var188))));
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
				logProbability$var189[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample195[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample195[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var189[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample200() {
		if(!fixedProbFlag$sample200) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = pageFaults[i$var180];
					{
						{
							double var192 = pageFaultsMean[st[i$var180]];
							double var193 = pageFaultsVar[st[i$var180]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var192) / Math.sqrt(var193))) - (0.5 * Math.log(var193))));
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
				logProbability$var194[((i$var180 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample200[((i$var180 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample200[((i$var180 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var194[((i$var180 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < noStates; var35 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var35];
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
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var36;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var24 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
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
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var40 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
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
			logProbability$var43 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample46);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var43 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var56;
				{
					int cv$sampleValue = st[i$var56];
					{
						{
							double[] var60 = m[st[(i$var56 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var61[((i$var56 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample67[((i$var56 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample67 = ((fixedFlag$sample67 && fixedFlag$sample39) && fixedFlag$sample49);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample67[((i$var56 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var61[((i$var56 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var81 = 0; var81 < noStates; var81 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = cpuMean[var81];
					{
						{
							double var69 = 16.0;
							double var68 = 8.6;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var69) / Math.sqrt(var68))) - (0.5 * Math.log(var68))));
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
			logProbability$var82 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = fixedFlag$sample87;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var82;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var70 = cv$rvAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample105(int var99, int threadID$cv$var99, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = memMean[var99];
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
					double var100 = cv$proposedValue;
					memMean[var99] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var86;
				{
					cv$temp$0$var86 = 94.0;
				}
				double cv$temp$1$var87;
				{
					cv$temp$1$var87 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var86) / Math.sqrt(cv$temp$1$var87))) - (0.5 * Math.log(cv$temp$1$var87))));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var187$7_1 = cv$currentValue;
									if((var99 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var151 = 0; var151 < noStates; var151 += 1) {
														if((var151 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var187;
																	{
																		double var187 = traceTempVariable$var187$7_1;
																		cv$temp$2$var187 = var187;
																	}
																	double cv$temp$3$var188;
																	{
																		double var188 = memVar[st[i$var180]];
																		cv$temp$3$var188 = var188;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var151 = 0; var151 < noStates; var151 += 1) {
																if((var151 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var187;
																			{
																				double var187 = traceTempVariable$var187$7_1;
																				cv$temp$4$var187 = var187;
																			}
																			double cv$temp$5$var188;
																			{
																				double var188 = memVar[st[i$var180]];
																				cv$temp$5$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var187;
																					{
																						double var187 = traceTempVariable$var187$7_1;
																						cv$temp$6$var187 = var187;
																					}
																					double cv$temp$7$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$29_1];
																						cv$temp$7$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var187$8_1 = cv$currentValue;
											if((var99 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var151 = 0; var151 < noStates; var151 += 1) {
																if((var151 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var187;
																			{
																				double var187 = traceTempVariable$var187$8_1;
																				cv$temp$8$var187 = var187;
																			}
																			double cv$temp$9$var188;
																			{
																				double var188 = memVar[traceTempVariable$s$32_1];
																				cv$temp$9$var188 = var188;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var187;
																					{
																						double var187 = traceTempVariable$var187$8_1;
																						cv$temp$10$var187 = var187;
																					}
																					double cv$temp$11$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$36_1];
																						cv$temp$11$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var187;
																					{
																						double var187 = traceTempVariable$var187$8_1;
																						cv$temp$12$var187 = var187;
																					}
																					double cv$temp$13$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$6_1];
																						cv$temp$13$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var187;
																							{
																								double var187 = traceTempVariable$var187$8_1;
																								cv$temp$14$var187 = var187;
																							}
																							double cv$temp$15$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$44_1];
																								cv$temp$15$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var187$16_1 = cv$currentValue;
										if((var99 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var151 = 0; var151 < noStates; var151 += 1) {
																if((var151 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var187;
																			{
																				double var187 = traceTempVariable$var187$16_1;
																				cv$temp$16$var187 = var187;
																			}
																			double cv$temp$17$var188;
																			{
																				double var188 = memVar[st[i$var180]];
																				cv$temp$17$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var187;
																					{
																						double var187 = traceTempVariable$var187$16_1;
																						cv$temp$18$var187 = var187;
																					}
																					double cv$temp$19$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$51_1];
																						cv$temp$19$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var151 = 0; var151 < noStates; var151 += 1) {
																if((var151 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var187;
																			{
																				double var187 = traceTempVariable$var187$16_1;
																				cv$temp$20$var187 = var187;
																			}
																			double cv$temp$21$var188;
																			{
																				double var188 = memVar[st[i$var180]];
																				cv$temp$21$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var187$17_1 = cv$currentValue;
												if((var99 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var187;
																					{
																						double var187 = traceTempVariable$var187$17_1;
																						cv$temp$22$var187 = var187;
																					}
																					double cv$temp$23$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$15_1];
																						cv$temp$23$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var187;
																							{
																								double var187 = traceTempVariable$var187$17_1;
																								cv$temp$24$var187 = var187;
																							}
																							double cv$temp$25$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$60_1];
																								cv$temp$25$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var187;
																				{
																					double var187 = traceTempVariable$var187$17_1;
																					cv$temp$26$var187 = var187;
																				}
																				double cv$temp$27$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$63_1];
																					cv$temp$27$var188 = var188;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var187;
																							{
																								double var187 = traceTempVariable$var187$17_1;
																								cv$temp$28$var187 = var187;
																							}
																							double cv$temp$29$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$68_1];
																								cv$temp$29$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var100 = cv$originalValue;
			memMean[var99] = var100;
		}
	}

	private final void sample123(int var117, int threadID$cv$var117, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = pageFaultsMean[var117];
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
					double var118 = cv$proposedValue;
					pageFaultsMean[var117] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var104;
				{
					cv$temp$0$var104 = 814.0;
				}
				double cv$temp$1$var105;
				{
					cv$temp$1$var105 = 335550.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var104) / Math.sqrt(cv$temp$1$var105))) - (0.5 * Math.log(cv$temp$1$var105))));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var192$7_1 = cv$currentValue;
									if((var117 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var168 = 0; var168 < noStates; var168 += 1) {
														if((var168 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var192;
																	{
																		double var192 = traceTempVariable$var192$7_1;
																		cv$temp$2$var192 = var192;
																	}
																	double cv$temp$3$var193;
																	{
																		double var193 = pageFaultsVar[st[i$var180]];
																		cv$temp$3$var193 = var193;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var168 = 0; var168 < noStates; var168 += 1) {
																if((var168 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var192;
																			{
																				double var192 = traceTempVariable$var192$7_1;
																				cv$temp$4$var192 = var192;
																			}
																			double cv$temp$5$var193;
																			{
																				double var193 = pageFaultsVar[st[i$var180]];
																				cv$temp$5$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var192;
																					{
																						double var192 = traceTempVariable$var192$7_1;
																						cv$temp$6$var192 = var192;
																					}
																					double cv$temp$7$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$29_1];
																						cv$temp$7$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var192$8_1 = cv$currentValue;
											if((var117 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var168 = 0; var168 < noStates; var168 += 1) {
																if((var168 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var192;
																			{
																				double var192 = traceTempVariable$var192$8_1;
																				cv$temp$8$var192 = var192;
																			}
																			double cv$temp$9$var193;
																			{
																				double var193 = pageFaultsVar[traceTempVariable$s$32_1];
																				cv$temp$9$var193 = var193;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var192;
																					{
																						double var192 = traceTempVariable$var192$8_1;
																						cv$temp$10$var192 = var192;
																					}
																					double cv$temp$11$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$36_1];
																						cv$temp$11$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var192;
																					{
																						double var192 = traceTempVariable$var192$8_1;
																						cv$temp$12$var192 = var192;
																					}
																					double cv$temp$13$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$6_1];
																						cv$temp$13$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var192;
																							{
																								double var192 = traceTempVariable$var192$8_1;
																								cv$temp$14$var192 = var192;
																							}
																							double cv$temp$15$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$44_1];
																								cv$temp$15$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var192$16_1 = cv$currentValue;
										if((var117 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var168 = 0; var168 < noStates; var168 += 1) {
																if((var168 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var192;
																			{
																				double var192 = traceTempVariable$var192$16_1;
																				cv$temp$16$var192 = var192;
																			}
																			double cv$temp$17$var193;
																			{
																				double var193 = pageFaultsVar[st[i$var180]];
																				cv$temp$17$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var192;
																					{
																						double var192 = traceTempVariable$var192$16_1;
																						cv$temp$18$var192 = var192;
																					}
																					double cv$temp$19$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$51_1];
																						cv$temp$19$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var168 = 0; var168 < noStates; var168 += 1) {
																if((var168 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var192;
																			{
																				double var192 = traceTempVariable$var192$16_1;
																				cv$temp$20$var192 = var192;
																			}
																			double cv$temp$21$var193;
																			{
																				double var193 = pageFaultsVar[st[i$var180]];
																				cv$temp$21$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var192$17_1 = cv$currentValue;
												if((var117 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var192;
																					{
																						double var192 = traceTempVariable$var192$17_1;
																						cv$temp$22$var192 = var192;
																					}
																					double cv$temp$23$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$15_1];
																						cv$temp$23$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var192;
																							{
																								double var192 = traceTempVariable$var192$17_1;
																								cv$temp$24$var192 = var192;
																							}
																							double cv$temp$25$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$60_1];
																								cv$temp$25$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var192;
																				{
																					double var192 = traceTempVariable$var192$17_1;
																					cv$temp$26$var192 = var192;
																				}
																				double cv$temp$27$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$63_1];
																					cv$temp$27$var193 = var193;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var192;
																							{
																								double var192 = traceTempVariable$var192$17_1;
																								cv$temp$28$var192 = var192;
																							}
																							double cv$temp$29$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$68_1];
																								cv$temp$29$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var118 = cv$originalValue;
			pageFaultsMean[var117] = var118;
		}
	}

	private final void sample140(int var134, int threadID$cv$var134, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = cpuVar[var134];
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
					double var135 = cv$proposedValue;
					cpuVar[var134] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var122;
				{
					cv$temp$0$var122 = 5.0;
				}
				double cv$temp$1$var121;
				{
					cv$temp$1$var121 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var122, cv$temp$1$var121));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var183$7_1 = cv$currentValue;
									if((var134 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var81 = 0; var81 < noStates; var81 += 1) {
														if((var81 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var182;
																	{
																		double var182 = cpuMean[st[i$var180]];
																		cv$temp$2$var182 = var182;
																	}
																	double cv$temp$3$var183;
																	{
																		double var183 = traceTempVariable$var183$7_1;
																		cv$temp$3$var183 = var183;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var182;
																			{
																				double var182 = cpuMean[st[i$var180]];
																				cv$temp$4$var182 = var182;
																			}
																			double cv$temp$5$var183;
																			{
																				double var183 = traceTempVariable$var183$7_1;
																				cv$temp$5$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$29_1];
																						cv$temp$6$var182 = var182;
																					}
																					double cv$temp$7$var183;
																					{
																						double var183 = traceTempVariable$var183$7_1;
																						cv$temp$7$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var183$8_1 = cv$currentValue;
											if((var134 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var182;
																			{
																				double var182 = cpuMean[traceTempVariable$s$32_1];
																				cv$temp$8$var182 = var182;
																			}
																			double cv$temp$9$var183;
																			{
																				double var183 = traceTempVariable$var183$8_1;
																				cv$temp$9$var183 = var183;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$36_1];
																						cv$temp$10$var182 = var182;
																					}
																					double cv$temp$11$var183;
																					{
																						double var183 = traceTempVariable$var183$8_1;
																						cv$temp$11$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$6_1];
																						cv$temp$12$var182 = var182;
																					}
																					double cv$temp$13$var183;
																					{
																						double var183 = traceTempVariable$var183$8_1;
																						cv$temp$13$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var81 = 0; var81 < noStates; var81 += 1) {
																				if((var81 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$44_1];
																								cv$temp$14$var182 = var182;
																							}
																							double cv$temp$15$var183;
																							{
																								double var183 = traceTempVariable$var183$8_1;
																								cv$temp$15$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var183$16_1 = cv$currentValue;
										if((var134 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var182;
																			{
																				double var182 = cpuMean[st[i$var180]];
																				cv$temp$16$var182 = var182;
																			}
																			double cv$temp$17$var183;
																			{
																				double var183 = traceTempVariable$var183$16_1;
																				cv$temp$17$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$51_1];
																						cv$temp$18$var182 = var182;
																					}
																					double cv$temp$19$var183;
																					{
																						double var183 = traceTempVariable$var183$16_1;
																						cv$temp$19$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var182;
																			{
																				double var182 = cpuMean[st[i$var180]];
																				cv$temp$20$var182 = var182;
																			}
																			double cv$temp$21$var183;
																			{
																				double var183 = traceTempVariable$var183$16_1;
																				cv$temp$21$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var183$17_1 = cv$currentValue;
												if((var134 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$15_1];
																						cv$temp$22$var182 = var182;
																					}
																					double cv$temp$23$var183;
																					{
																						double var183 = traceTempVariable$var183$17_1;
																						cv$temp$23$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var81 = 0; var81 < noStates; var81 += 1) {
																				if((var81 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$60_1];
																								cv$temp$24$var182 = var182;
																							}
																							double cv$temp$25$var183;
																							{
																								double var183 = traceTempVariable$var183$17_1;
																								cv$temp$25$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$63_1];
																					cv$temp$26$var182 = var182;
																				}
																				double cv$temp$27$var183;
																				{
																					double var183 = traceTempVariable$var183$17_1;
																					cv$temp$27$var183 = var183;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var81 = 0; var81 < noStates; var81 += 1) {
																				if((var81 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$68_1];
																								cv$temp$28$var182 = var182;
																							}
																							double cv$temp$29$var183;
																							{
																								double var183 = traceTempVariable$var183$17_1;
																								cv$temp$29$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var135 = cv$originalValue;
			cpuVar[var134] = var135;
		}
	}

	private final void sample157(int var151, int threadID$cv$var151, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = memVar[var151];
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
					double var152 = cv$proposedValue;
					memVar[var151] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var139;
				{
					cv$temp$0$var139 = 5.0;
				}
				double cv$temp$1$var138;
				{
					cv$temp$1$var138 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var139, cv$temp$1$var138));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var188$7_1 = cv$currentValue;
									if((var151 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var99 = 0; var99 < noStates; var99 += 1) {
														if((var99 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var187;
																	{
																		double var187 = memMean[st[i$var180]];
																		cv$temp$2$var187 = var187;
																	}
																	double cv$temp$3$var188;
																	{
																		double var188 = traceTempVariable$var188$7_1;
																		cv$temp$3$var188 = var188;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$2$var187) / Math.sqrt(cv$temp$3$var188))) - (0.5 * Math.log(cv$temp$3$var188)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var187;
																			{
																				double var187 = memMean[st[i$var180]];
																				cv$temp$4$var187 = var187;
																			}
																			double cv$temp$5$var188;
																			{
																				double var188 = traceTempVariable$var188$7_1;
																				cv$temp$5$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$4$var187) / Math.sqrt(cv$temp$5$var188))) - (0.5 * Math.log(cv$temp$5$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$29_1];
																						cv$temp$6$var187 = var187;
																					}
																					double cv$temp$7$var188;
																					{
																						double var188 = traceTempVariable$var188$7_1;
																						cv$temp$7$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$6$var187) / Math.sqrt(cv$temp$7$var188))) - (0.5 * Math.log(cv$temp$7$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var188$8_1 = cv$currentValue;
											if((var151 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var187;
																			{
																				double var187 = memMean[traceTempVariable$s$32_1];
																				cv$temp$8$var187 = var187;
																			}
																			double cv$temp$9$var188;
																			{
																				double var188 = traceTempVariable$var188$8_1;
																				cv$temp$9$var188 = var188;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$8$var187) / Math.sqrt(cv$temp$9$var188))) - (0.5 * Math.log(cv$temp$9$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$36_1];
																						cv$temp$10$var187 = var187;
																					}
																					double cv$temp$11$var188;
																					{
																						double var188 = traceTempVariable$var188$8_1;
																						cv$temp$11$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$10$var187) / Math.sqrt(cv$temp$11$var188))) - (0.5 * Math.log(cv$temp$11$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$6_1];
																						cv$temp$12$var187 = var187;
																					}
																					double cv$temp$13$var188;
																					{
																						double var188 = traceTempVariable$var188$8_1;
																						cv$temp$13$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$12$var187) / Math.sqrt(cv$temp$13$var188))) - (0.5 * Math.log(cv$temp$13$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var99 = 0; var99 < noStates; var99 += 1) {
																				if((var99 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$44_1];
																								cv$temp$14$var187 = var187;
																							}
																							double cv$temp$15$var188;
																							{
																								double var188 = traceTempVariable$var188$8_1;
																								cv$temp$15$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$14$var187) / Math.sqrt(cv$temp$15$var188))) - (0.5 * Math.log(cv$temp$15$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var188$16_1 = cv$currentValue;
										if((var151 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var187;
																			{
																				double var187 = memMean[st[i$var180]];
																				cv$temp$16$var187 = var187;
																			}
																			double cv$temp$17$var188;
																			{
																				double var188 = traceTempVariable$var188$16_1;
																				cv$temp$17$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$16$var187) / Math.sqrt(cv$temp$17$var188))) - (0.5 * Math.log(cv$temp$17$var188)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$51_1];
																						cv$temp$18$var187 = var187;
																					}
																					double cv$temp$19$var188;
																					{
																						double var188 = traceTempVariable$var188$16_1;
																						cv$temp$19$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var187;
																			{
																				double var187 = memMean[st[i$var180]];
																				cv$temp$20$var187 = var187;
																			}
																			double cv$temp$21$var188;
																			{
																				double var188 = traceTempVariable$var188$16_1;
																				cv$temp$21$var188 = var188;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var188$17_1 = cv$currentValue;
												if((var151 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$15_1];
																						cv$temp$22$var187 = var187;
																					}
																					double cv$temp$23$var188;
																					{
																						double var188 = traceTempVariable$var188$17_1;
																						cv$temp$23$var188 = var188;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var99 = 0; var99 < noStates; var99 += 1) {
																				if((var99 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$60_1];
																								cv$temp$24$var187 = var187;
																							}
																							double cv$temp$25$var188;
																							{
																								double var188 = traceTempVariable$var188$17_1;
																								cv$temp$25$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$63_1];
																					cv$temp$26$var187 = var187;
																				}
																				double cv$temp$27$var188;
																				{
																					double var188 = traceTempVariable$var188$17_1;
																					cv$temp$27$var188 = var188;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var99 = 0; var99 < noStates; var99 += 1) {
																				if((var99 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$68_1];
																								cv$temp$28$var187 = var187;
																							}
																							double cv$temp$29$var188;
																							{
																								double var188 = traceTempVariable$var188$17_1;
																								cv$temp$29$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var152 = cv$originalValue;
			memVar[var151] = var152;
		}
	}

	private final void sample174(int var168, int threadID$cv$var168, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = pageFaultsVar[var168];
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
					double var169 = cv$proposedValue;
					pageFaultsVar[var168] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var156;
				{
					cv$temp$0$var156 = 5.0;
				}
				double cv$temp$1$var155;
				{
					cv$temp$1$var155 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var156, cv$temp$1$var155));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var193$7_1 = cv$currentValue;
									if((var168 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var117 = 0; var117 < noStates; var117 += 1) {
														if((var117 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var192;
																	{
																		double var192 = pageFaultsMean[st[i$var180]];
																		cv$temp$2$var192 = var192;
																	}
																	double cv$temp$3$var193;
																	{
																		double var193 = traceTempVariable$var193$7_1;
																		cv$temp$3$var193 = var193;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$2$var192) / Math.sqrt(cv$temp$3$var193))) - (0.5 * Math.log(cv$temp$3$var193)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var192;
																			{
																				double var192 = pageFaultsMean[st[i$var180]];
																				cv$temp$4$var192 = var192;
																			}
																			double cv$temp$5$var193;
																			{
																				double var193 = traceTempVariable$var193$7_1;
																				cv$temp$5$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$4$var192) / Math.sqrt(cv$temp$5$var193))) - (0.5 * Math.log(cv$temp$5$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$29_1];
																						cv$temp$6$var192 = var192;
																					}
																					double cv$temp$7$var193;
																					{
																						double var193 = traceTempVariable$var193$7_1;
																						cv$temp$7$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$6$var192) / Math.sqrt(cv$temp$7$var193))) - (0.5 * Math.log(cv$temp$7$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var193$8_1 = cv$currentValue;
											if((var168 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var192;
																			{
																				double var192 = pageFaultsMean[traceTempVariable$s$32_1];
																				cv$temp$8$var192 = var192;
																			}
																			double cv$temp$9$var193;
																			{
																				double var193 = traceTempVariable$var193$8_1;
																				cv$temp$9$var193 = var193;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$8$var192) / Math.sqrt(cv$temp$9$var193))) - (0.5 * Math.log(cv$temp$9$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$36_1];
																						cv$temp$10$var192 = var192;
																					}
																					double cv$temp$11$var193;
																					{
																						double var193 = traceTempVariable$var193$8_1;
																						cv$temp$11$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$10$var192) / Math.sqrt(cv$temp$11$var193))) - (0.5 * Math.log(cv$temp$11$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$6_1];
																						cv$temp$12$var192 = var192;
																					}
																					double cv$temp$13$var193;
																					{
																						double var193 = traceTempVariable$var193$8_1;
																						cv$temp$13$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$12$var192) / Math.sqrt(cv$temp$13$var193))) - (0.5 * Math.log(cv$temp$13$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var117 = 0; var117 < noStates; var117 += 1) {
																				if((var117 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$44_1];
																								cv$temp$14$var192 = var192;
																							}
																							double cv$temp$15$var193;
																							{
																								double var193 = traceTempVariable$var193$8_1;
																								cv$temp$15$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$14$var192) / Math.sqrt(cv$temp$15$var193))) - (0.5 * Math.log(cv$temp$15$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var193$16_1 = cv$currentValue;
										if((var168 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var192;
																			{
																				double var192 = pageFaultsMean[st[i$var180]];
																				cv$temp$16$var192 = var192;
																			}
																			double cv$temp$17$var193;
																			{
																				double var193 = traceTempVariable$var193$16_1;
																				cv$temp$17$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$16$var192) / Math.sqrt(cv$temp$17$var193))) - (0.5 * Math.log(cv$temp$17$var193)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$51_1];
																						cv$temp$18$var192 = var192;
																					}
																					double cv$temp$19$var193;
																					{
																						double var193 = traceTempVariable$var193$16_1;
																						cv$temp$19$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$18$var192) / Math.sqrt(cv$temp$19$var193))) - (0.5 * Math.log(cv$temp$19$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var192;
																			{
																				double var192 = pageFaultsMean[st[i$var180]];
																				cv$temp$20$var192 = var192;
																			}
																			double cv$temp$21$var193;
																			{
																				double var193 = traceTempVariable$var193$16_1;
																				cv$temp$21$var193 = var193;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$20$var192) / Math.sqrt(cv$temp$21$var193))) - (0.5 * Math.log(cv$temp$21$var193)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var193$17_1 = cv$currentValue;
												if((var168 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$15_1];
																						cv$temp$22$var192 = var192;
																					}
																					double cv$temp$23$var193;
																					{
																						double var193 = traceTempVariable$var193$17_1;
																						cv$temp$23$var193 = var193;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$22$var192) / Math.sqrt(cv$temp$23$var193))) - (0.5 * Math.log(cv$temp$23$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var117 = 0; var117 < noStates; var117 += 1) {
																				if((var117 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$60_1];
																								cv$temp$24$var192 = var192;
																							}
																							double cv$temp$25$var193;
																							{
																								double var193 = traceTempVariable$var193$17_1;
																								cv$temp$25$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$24$var192) / Math.sqrt(cv$temp$25$var193))) - (0.5 * Math.log(cv$temp$25$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$63_1];
																					cv$temp$26$var192 = var192;
																				}
																				double cv$temp$27$var193;
																				{
																					double var193 = traceTempVariable$var193$17_1;
																					cv$temp$27$var193 = var193;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$26$var192) / Math.sqrt(cv$temp$27$var193))) - (0.5 * Math.log(cv$temp$27$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var117 = 0; var117 < noStates; var117 += 1) {
																				if((var117 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$68_1];
																								cv$temp$28$var192 = var192;
																							}
																							double cv$temp$29$var193;
																							{
																								double var193 = traceTempVariable$var193$17_1;
																								cv$temp$29$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$28$var192) / Math.sqrt(cv$temp$29$var193))) - (0.5 * Math.log(cv$temp$29$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var169 = cv$originalValue;
			pageFaultsVar[var168] = var169;
		}
	}

	private final void sample39(int var35, int threadID$cv$var35, Rng RNG$) {
		double[] cv$targetLocal = m[var35];
		double[] cv$countLocal = cv$var36$countGlobal[threadID$cv$var35];
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
						if(fixedFlag$sample49) {
							if((0 == (i$var56 - 1))) {
								if((var35 == st[(i$var56 - 1)])) {
									if(fixedFlag$sample67) {
										{
											int index$i$19 = i$var56;
											{
												{
													{
														{
															cv$countLocal[st[i$var56]] = (cv$countLocal[st[i$var56]] + 1.0);
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
								for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
									int distributionTempVariable$var44$5 = index$sample49$3;
									double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
									int traceTempVariable$var59$6_1 = distributionTempVariable$var44$5;
									if((0 == (i$var56 - 1))) {
										if((var35 == traceTempVariable$var59$6_1)) {
											if(fixedFlag$sample67) {
												{
													int index$i$21 = i$var56;
													{
														{
															{
																{
																	cv$countLocal[st[i$var56]] = (cv$countLocal[st[i$var56]] + cv$probabilitySample49Value4);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
						if(fixedFlag$sample67) {
							for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
								if((index$i$10_1 == (i$var56 - 1))) {
									if((var35 == st[(i$var56 - 1)])) {
										if(fixedFlag$sample67) {
											{
												int index$i$23 = i$var56;
												{
													{
														{
															{
																cv$countLocal[st[i$var56]] = (cv$countLocal[st[i$var56]] + 1.0);
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
									for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
										int distributionTempVariable$var62$14 = index$sample67$12;
										double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((index$i$11 - 1) / 1)][index$sample67$12]);
										int traceTempVariable$var59$15_1 = distributionTempVariable$var62$14;
										if((index$i$11 == (i$var56 - 1))) {
											if((var35 == traceTempVariable$var59$15_1)) {
												if(fixedFlag$sample67) {
													{
														int index$i$25 = i$var56;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var56]] = (cv$countLocal[st[i$var56]] + cv$probabilitySample67Value13);
																	}
																}
															}
														}
													}
												}
											}
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
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
					if(fixedFlag$sample49) {
						if((0 == (i$var56 - 1))) {
							if((var35 == st[(i$var56 - 1)])) {
								if(!fixedFlag$sample67) {
									{
										int index$i$48 = i$var56;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample67[((i$var56 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample49$32 = 0; index$sample49$32 < noStates; index$sample49$32 += 1) {
								int distributionTempVariable$var44$34 = index$sample49$32;
								double cv$probabilitySample49Value33 = (1.0 * distribution$sample49[index$sample49$32]);
								int traceTempVariable$var59$35_1 = distributionTempVariable$var44$34;
								if((0 == (i$var56 - 1))) {
									if((var35 == traceTempVariable$var59$35_1)) {
										if(!fixedFlag$sample67) {
											{
												int index$i$50 = i$var56;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample49Value33);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample67[((i$var56 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
					if(fixedFlag$sample67) {
						for(int index$i$39_1 = 1; index$i$39_1 < samples; index$i$39_1 += 1) {
							if((index$i$39_1 == (i$var56 - 1))) {
								if((var35 == st[(i$var56 - 1)])) {
									if(!fixedFlag$sample67) {
										{
											int index$i$52 = i$var56;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample67[((i$var56 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
									int distributionTempVariable$var62$43 = index$sample67$41;
									double cv$probabilitySample67Value42 = (1.0 * distribution$sample67[((index$i$40 - 1) / 1)][index$sample67$41]);
									int traceTempVariable$var59$44_1 = distributionTempVariable$var62$43;
									if((index$i$40 == (i$var56 - 1))) {
										if((var35 == traceTempVariable$var59$44_1)) {
											if(!fixedFlag$sample67) {
												{
													int index$i$54 = i$var56;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample67Value42);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample67[((i$var56 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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

	private final void sample46() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var41$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if(fixedFlag$sample49) {
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
				if(!fixedFlag$sample49) {
					{
						{
							{
								double scopeVariable$reachedSourceProbability = 0.0;
								{
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
								}
								double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
								for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample49[cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample49() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var44$stateProbabilityGlobal;
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
						int traceTempVariable$var59$1_1 = cv$currentValue;
						for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
							if((0 == (i$var56 - 1))) {
								if(fixedFlag$sample67) {
									{
										int index$i$3 = i$var56;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var35 = 0; var35 < noStates; var35 += 1) {
												if((var35 == traceTempVariable$var59$1_1)) {
													{
														{
															double[] cv$temp$1$var60;
															{
																double[] var60 = m[traceTempVariable$var59$1_1];
																cv$temp$1$var60 = var60;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var56]) && (st[i$var56] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[i$var56]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var56]) && (st[i$var56] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[i$var56]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var56]) && (st[i$var56] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[i$var56]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var56]) && (st[i$var56] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[i$var56]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var56]) && (st[i$var56] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[i$var56]]):Double.NEGATIVE_INFINITY)));
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
						boolean[] guard$sample49gaussian189 = guard$sample49gaussian189$global;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian189[((i$var180 - 0) / 1)] = false;
						}
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian189[((i$var180 - 0) / 1)] = false;
						}
						int traceTempVariable$s$8_1 = cv$currentValue;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian189[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian189[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var81 = 0; var81 < noStates; var81 += 1) {
												if((var81 == traceTempVariable$s$8_1)) {
													int traceTempVariable$s$13_1 = cv$currentValue;
													if((0 == i$var180)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$13_1)) {
																{
																	{
																		double cv$temp$2$var182;
																		{
																			double var182 = cpuMean[traceTempVariable$s$13_1];
																			cv$temp$2$var182 = var182;
																		}
																		double cv$temp$3$var183;
																		{
																			double var183 = cpuVar[traceTempVariable$s$13_1];
																			cv$temp$3$var183 = var183;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample49$14 = 0; index$sample49$14 < noStates; index$sample49$14 += 1) {
															int distributionTempVariable$var44$16 = index$sample49$14;
															double cv$probabilitySample49Value15 = (1.0 * distribution$sample49[index$sample49$14]);
															int traceTempVariable$s$17_1 = distributionTempVariable$var44$16;
															if((0 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$17_1)) {
																		{
																			{
																				double cv$temp$4$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$17_1];
																					cv$temp$4$var182 = var182;
																				}
																				double cv$temp$5$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$17_1];
																					cv$temp$5$var183 = var183;
																				}
																				if(((Math.log(cv$probabilitySample49Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value15) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value15);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var81 = 0; var81 < noStates; var81 += 1) {
												if((var81 == traceTempVariable$s$8_1)) {
													if(fixedFlag$sample67) {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if((i$var56 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$8_1)) {
																		{
																			{
																				double cv$temp$6$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$8_1];
																					cv$temp$6$var182 = var182;
																				}
																				double cv$temp$7$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$8_1];
																					cv$temp$7$var183 = var183;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if(true) {
																for(int index$sample67$23 = 0; index$sample67$23 < noStates; index$sample67$23 += 1) {
																	int distributionTempVariable$var62$25 = index$sample67$23;
																	double cv$probabilitySample67Value24 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$23]);
																	int traceTempVariable$s$26_1 = distributionTempVariable$var62$25;
																	if((i$var56 == i$var180)) {
																		for(int var134 = 0; var134 < noStates; var134 += 1) {
																			if((var134 == traceTempVariable$s$26_1)) {
																				{
																					{
																						double cv$temp$8$var182;
																						{
																							double var182 = cpuMean[traceTempVariable$s$26_1];
																							cv$temp$8$var182 = var182;
																						}
																						double cv$temp$9$var183;
																						{
																							double var183 = cpuVar[traceTempVariable$s$26_1];
																							cv$temp$9$var183 = var183;
																						}
																						if(((Math.log(cv$probabilitySample67Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value24) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value24);
																					}
																				}
																			}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian189[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian189[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$29_1 = cv$currentValue;
											if((0 == i$var180)) {
												for(int var81 = 0; var81 < noStates; var81 += 1) {
													if((var81 == traceTempVariable$s$29_1)) {
														for(int var134 = 0; var134 < noStates; var134 += 1) {
															if((var134 == traceTempVariable$s$29_1)) {
																{
																	{
																		double cv$temp$10$var182;
																		{
																			double var182 = cpuMean[traceTempVariable$s$29_1];
																			cv$temp$10$var182 = var182;
																		}
																		double cv$temp$11$var183;
																		{
																			double var183 = cpuVar[traceTempVariable$s$29_1];
																			cv$temp$11$var183 = var183;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))));
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
												for(int index$sample49$30 = 0; index$sample49$30 < noStates; index$sample49$30 += 1) {
													int distributionTempVariable$var44$32 = index$sample49$30;
													double cv$probabilitySample49Value31 = (1.0 * distribution$sample49[index$sample49$30]);
													int traceTempVariable$s$33_1 = distributionTempVariable$var44$32;
													if((0 == i$var180)) {
														for(int var81 = 0; var81 < noStates; var81 += 1) {
															if((var81 == traceTempVariable$s$33_1)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$33_1)) {
																		{
																			{
																				double cv$temp$12$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$33_1];
																					cv$temp$12$var182 = var182;
																				}
																				double cv$temp$13$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$33_1];
																					cv$temp$13$var183 = var183;
																				}
																				if(((Math.log(cv$probabilitySample49Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value31) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value31);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var81 = 0; var81 < noStates; var81 += 1) {
															if((var81 == traceTempVariable$s$9_1)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$9_1)) {
																		{
																			{
																				double cv$temp$14$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$9_1];
																					cv$temp$14$var182 = var182;
																				}
																				double cv$temp$15$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$9_1];
																					cv$temp$15$var183 = var183;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))));
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
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$40 = 0; index$sample67$40 < noStates; index$sample67$40 += 1) {
															int distributionTempVariable$var62$42 = index$sample67$40;
															double cv$probabilitySample67Value41 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$40]);
															int traceTempVariable$s$43_1 = distributionTempVariable$var62$42;
															if((i$var56 == i$var180)) {
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$43_1)) {
																		for(int var134 = 0; var134 < noStates; var134 += 1) {
																			if((var134 == traceTempVariable$s$43_1)) {
																				{
																					{
																						double cv$temp$16$var182;
																						{
																							double var182 = cpuMean[traceTempVariable$s$43_1];
																							cv$temp$16$var182 = var182;
																						}
																						double cv$temp$17$var183;
																						{
																							double var183 = cpuVar[traceTempVariable$s$43_1];
																							cv$temp$17$var183 = var183;
																						}
																						if(((Math.log(cv$probabilitySample67Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value41) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value41);
																					}
																				}
																			}
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
						boolean[] guard$sample49gaussian194 = guard$sample49gaussian194$global;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian194[((i$var180 - 0) / 1)] = false;
						}
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian194[((i$var180 - 0) / 1)] = false;
						}
						int traceTempVariable$s$58_1 = cv$currentValue;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian194[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian194[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var99 = 0; var99 < noStates; var99 += 1) {
												if((var99 == traceTempVariable$s$58_1)) {
													int traceTempVariable$s$63_1 = cv$currentValue;
													if((0 == i$var180)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$63_1)) {
																{
																	{
																		double cv$temp$18$var187;
																		{
																			double var187 = memMean[traceTempVariable$s$63_1];
																			cv$temp$18$var187 = var187;
																		}
																		double cv$temp$19$var188;
																		{
																			double var188 = memVar[traceTempVariable$s$63_1];
																			cv$temp$19$var188 = var188;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$18$var187) / Math.sqrt(cv$temp$19$var188))) - (0.5 * Math.log(cv$temp$19$var188)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample49$64 = 0; index$sample49$64 < noStates; index$sample49$64 += 1) {
															int distributionTempVariable$var44$66 = index$sample49$64;
															double cv$probabilitySample49Value65 = (1.0 * distribution$sample49[index$sample49$64]);
															int traceTempVariable$s$67_1 = distributionTempVariable$var44$66;
															if((0 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$67_1)) {
																		{
																			{
																				double cv$temp$20$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$67_1];
																					cv$temp$20$var187 = var187;
																				}
																				double cv$temp$21$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$67_1];
																					cv$temp$21$var188 = var188;
																				}
																				if(((Math.log(cv$probabilitySample49Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value65) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$20$var187) / Math.sqrt(cv$temp$21$var188))) - (0.5 * Math.log(cv$temp$21$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value65);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var99 = 0; var99 < noStates; var99 += 1) {
												if((var99 == traceTempVariable$s$58_1)) {
													if(fixedFlag$sample67) {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if((i$var56 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$58_1)) {
																		{
																			{
																				double cv$temp$22$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$58_1];
																					cv$temp$22$var187 = var187;
																				}
																				double cv$temp$23$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$58_1];
																					cv$temp$23$var188 = var188;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$22$var187) / Math.sqrt(cv$temp$23$var188))) - (0.5 * Math.log(cv$temp$23$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if(true) {
																for(int index$sample67$73 = 0; index$sample67$73 < noStates; index$sample67$73 += 1) {
																	int distributionTempVariable$var62$75 = index$sample67$73;
																	double cv$probabilitySample67Value74 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$73]);
																	int traceTempVariable$s$76_1 = distributionTempVariable$var62$75;
																	if((i$var56 == i$var180)) {
																		for(int var151 = 0; var151 < noStates; var151 += 1) {
																			if((var151 == traceTempVariable$s$76_1)) {
																				{
																					{
																						double cv$temp$24$var187;
																						{
																							double var187 = memMean[traceTempVariable$s$76_1];
																							cv$temp$24$var187 = var187;
																						}
																						double cv$temp$25$var188;
																						{
																							double var188 = memVar[traceTempVariable$s$76_1];
																							cv$temp$25$var188 = var188;
																						}
																						if(((Math.log(cv$probabilitySample67Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value74) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$24$var187) / Math.sqrt(cv$temp$25$var188))) - (0.5 * Math.log(cv$temp$25$var188)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value74);
																					}
																				}
																			}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian194[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian194[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$79_1 = cv$currentValue;
											if((0 == i$var180)) {
												for(int var99 = 0; var99 < noStates; var99 += 1) {
													if((var99 == traceTempVariable$s$79_1)) {
														for(int var151 = 0; var151 < noStates; var151 += 1) {
															if((var151 == traceTempVariable$s$79_1)) {
																{
																	{
																		double cv$temp$26$var187;
																		{
																			double var187 = memMean[traceTempVariable$s$79_1];
																			cv$temp$26$var187 = var187;
																		}
																		double cv$temp$27$var188;
																		{
																			double var188 = memVar[traceTempVariable$s$79_1];
																			cv$temp$27$var188 = var188;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$26$var187) / Math.sqrt(cv$temp$27$var188))) - (0.5 * Math.log(cv$temp$27$var188)))));
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
												for(int index$sample49$80 = 0; index$sample49$80 < noStates; index$sample49$80 += 1) {
													int distributionTempVariable$var44$82 = index$sample49$80;
													double cv$probabilitySample49Value81 = (1.0 * distribution$sample49[index$sample49$80]);
													int traceTempVariable$s$83_1 = distributionTempVariable$var44$82;
													if((0 == i$var180)) {
														for(int var99 = 0; var99 < noStates; var99 += 1) {
															if((var99 == traceTempVariable$s$83_1)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$83_1)) {
																		{
																			{
																				double cv$temp$28$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$83_1];
																					cv$temp$28$var187 = var187;
																				}
																				double cv$temp$29$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$83_1];
																					cv$temp$29$var188 = var188;
																				}
																				if(((Math.log(cv$probabilitySample49Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value81) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$28$var187) / Math.sqrt(cv$temp$29$var188))) - (0.5 * Math.log(cv$temp$29$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value81);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var99 = 0; var99 < noStates; var99 += 1) {
															if((var99 == traceTempVariable$s$59_1)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$59_1)) {
																		{
																			{
																				double cv$temp$30$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$59_1];
																					cv$temp$30$var187 = var187;
																				}
																				double cv$temp$31$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$59_1];
																					cv$temp$31$var188 = var188;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$30$var187) / Math.sqrt(cv$temp$31$var188))) - (0.5 * Math.log(cv$temp$31$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$30$var187) / Math.sqrt(cv$temp$31$var188))) - (0.5 * Math.log(cv$temp$31$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$30$var187) / Math.sqrt(cv$temp$31$var188))) - (0.5 * Math.log(cv$temp$31$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$30$var187) / Math.sqrt(cv$temp$31$var188))) - (0.5 * Math.log(cv$temp$31$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$30$var187) / Math.sqrt(cv$temp$31$var188))) - (0.5 * Math.log(cv$temp$31$var188)))));
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
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$90 = 0; index$sample67$90 < noStates; index$sample67$90 += 1) {
															int distributionTempVariable$var62$92 = index$sample67$90;
															double cv$probabilitySample67Value91 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$90]);
															int traceTempVariable$s$93_1 = distributionTempVariable$var62$92;
															if((i$var56 == i$var180)) {
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$93_1)) {
																		for(int var151 = 0; var151 < noStates; var151 += 1) {
																			if((var151 == traceTempVariable$s$93_1)) {
																				{
																					{
																						double cv$temp$32$var187;
																						{
																							double var187 = memMean[traceTempVariable$s$93_1];
																							cv$temp$32$var187 = var187;
																						}
																						double cv$temp$33$var188;
																						{
																							double var188 = memVar[traceTempVariable$s$93_1];
																							cv$temp$33$var188 = var188;
																						}
																						if(((Math.log(cv$probabilitySample67Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$32$var187) / Math.sqrt(cv$temp$33$var188))) - (0.5 * Math.log(cv$temp$33$var188)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$32$var187) / Math.sqrt(cv$temp$33$var188))) - (0.5 * Math.log(cv$temp$33$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$32$var187) / Math.sqrt(cv$temp$33$var188))) - (0.5 * Math.log(cv$temp$33$var188))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$32$var187) / Math.sqrt(cv$temp$33$var188))) - (0.5 * Math.log(cv$temp$33$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value91) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$32$var187) / Math.sqrt(cv$temp$33$var188))) - (0.5 * Math.log(cv$temp$33$var188)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value91);
																					}
																				}
																			}
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
						boolean[] guard$sample49gaussian199 = guard$sample49gaussian199$global;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian199[((i$var180 - 0) / 1)] = false;
						}
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180))
								guard$sample49gaussian199[((i$var180 - 0) / 1)] = false;
						}
						int traceTempVariable$s$108_1 = cv$currentValue;
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian199[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian199[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var117 = 0; var117 < noStates; var117 += 1) {
												if((var117 == traceTempVariable$s$108_1)) {
													int traceTempVariable$s$113_1 = cv$currentValue;
													if((0 == i$var180)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$113_1)) {
																{
																	{
																		double cv$temp$34$var192;
																		{
																			double var192 = pageFaultsMean[traceTempVariable$s$113_1];
																			cv$temp$34$var192 = var192;
																		}
																		double cv$temp$35$var193;
																		{
																			double var193 = pageFaultsVar[traceTempVariable$s$113_1];
																			cv$temp$35$var193 = var193;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$34$var192) / Math.sqrt(cv$temp$35$var193))) - (0.5 * Math.log(cv$temp$35$var193)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$34$var192) / Math.sqrt(cv$temp$35$var193))) - (0.5 * Math.log(cv$temp$35$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$34$var192) / Math.sqrt(cv$temp$35$var193))) - (0.5 * Math.log(cv$temp$35$var193))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$34$var192) / Math.sqrt(cv$temp$35$var193))) - (0.5 * Math.log(cv$temp$35$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$34$var192) / Math.sqrt(cv$temp$35$var193))) - (0.5 * Math.log(cv$temp$35$var193)))));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample49$114 = 0; index$sample49$114 < noStates; index$sample49$114 += 1) {
															int distributionTempVariable$var44$116 = index$sample49$114;
															double cv$probabilitySample49Value115 = (1.0 * distribution$sample49[index$sample49$114]);
															int traceTempVariable$s$117_1 = distributionTempVariable$var44$116;
															if((0 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$117_1)) {
																		{
																			{
																				double cv$temp$36$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$117_1];
																					cv$temp$36$var192 = var192;
																				}
																				double cv$temp$37$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$117_1];
																					cv$temp$37$var193 = var193;
																				}
																				if(((Math.log(cv$probabilitySample49Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$36$var192) / Math.sqrt(cv$temp$37$var193))) - (0.5 * Math.log(cv$temp$37$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$36$var192) / Math.sqrt(cv$temp$37$var193))) - (0.5 * Math.log(cv$temp$37$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$36$var192) / Math.sqrt(cv$temp$37$var193))) - (0.5 * Math.log(cv$temp$37$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$36$var192) / Math.sqrt(cv$temp$37$var193))) - (0.5 * Math.log(cv$temp$37$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value115) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$36$var192) / Math.sqrt(cv$temp$37$var193))) - (0.5 * Math.log(cv$temp$37$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value115);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for(int var117 = 0; var117 < noStates; var117 += 1) {
												if((var117 == traceTempVariable$s$108_1)) {
													if(fixedFlag$sample67) {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if((i$var56 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$108_1)) {
																		{
																			{
																				double cv$temp$38$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$108_1];
																					cv$temp$38$var192 = var192;
																				}
																				double cv$temp$39$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$108_1];
																					cv$temp$39$var193 = var193;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$38$var192) / Math.sqrt(cv$temp$39$var193))) - (0.5 * Math.log(cv$temp$39$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$38$var192) / Math.sqrt(cv$temp$39$var193))) - (0.5 * Math.log(cv$temp$39$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$38$var192) / Math.sqrt(cv$temp$39$var193))) - (0.5 * Math.log(cv$temp$39$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$38$var192) / Math.sqrt(cv$temp$39$var193))) - (0.5 * Math.log(cv$temp$39$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$38$var192) / Math.sqrt(cv$temp$39$var193))) - (0.5 * Math.log(cv$temp$39$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
															if(true) {
																for(int index$sample67$123 = 0; index$sample67$123 < noStates; index$sample67$123 += 1) {
																	int distributionTempVariable$var62$125 = index$sample67$123;
																	double cv$probabilitySample67Value124 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$123]);
																	int traceTempVariable$s$126_1 = distributionTempVariable$var62$125;
																	if((i$var56 == i$var180)) {
																		for(int var168 = 0; var168 < noStates; var168 += 1) {
																			if((var168 == traceTempVariable$s$126_1)) {
																				{
																					{
																						double cv$temp$40$var192;
																						{
																							double var192 = pageFaultsMean[traceTempVariable$s$126_1];
																							cv$temp$40$var192 = var192;
																						}
																						double cv$temp$41$var193;
																						{
																							double var193 = pageFaultsVar[traceTempVariable$s$126_1];
																							cv$temp$41$var193 = var193;
																						}
																						if(((Math.log(cv$probabilitySample67Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$40$var192) / Math.sqrt(cv$temp$41$var193))) - (0.5 * Math.log(cv$temp$41$var193)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$40$var192) / Math.sqrt(cv$temp$41$var193))) - (0.5 * Math.log(cv$temp$41$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$40$var192) / Math.sqrt(cv$temp$41$var193))) - (0.5 * Math.log(cv$temp$41$var193))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$40$var192) / Math.sqrt(cv$temp$41$var193))) - (0.5 * Math.log(cv$temp$41$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value124) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$40$var192) / Math.sqrt(cv$temp$41$var193))) - (0.5 * Math.log(cv$temp$41$var193)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value124);
																					}
																				}
																			}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if((0 == i$var180)) {
								if(!guard$sample49gaussian199[((i$var180 - 0) / 1)]) {
									guard$sample49gaussian199[((i$var180 - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											int traceTempVariable$s$129_1 = cv$currentValue;
											if((0 == i$var180)) {
												for(int var117 = 0; var117 < noStates; var117 += 1) {
													if((var117 == traceTempVariable$s$129_1)) {
														for(int var168 = 0; var168 < noStates; var168 += 1) {
															if((var168 == traceTempVariable$s$129_1)) {
																{
																	{
																		double cv$temp$42$var192;
																		{
																			double var192 = pageFaultsMean[traceTempVariable$s$129_1];
																			cv$temp$42$var192 = var192;
																		}
																		double cv$temp$43$var193;
																		{
																			double var193 = pageFaultsVar[traceTempVariable$s$129_1];
																			cv$temp$43$var193 = var193;
																		}
																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$42$var192) / Math.sqrt(cv$temp$43$var193))) - (0.5 * Math.log(cv$temp$43$var193)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$42$var192) / Math.sqrt(cv$temp$43$var193))) - (0.5 * Math.log(cv$temp$43$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$42$var192) / Math.sqrt(cv$temp$43$var193))) - (0.5 * Math.log(cv$temp$43$var193))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$42$var192) / Math.sqrt(cv$temp$43$var193))) - (0.5 * Math.log(cv$temp$43$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$42$var192) / Math.sqrt(cv$temp$43$var193))) - (0.5 * Math.log(cv$temp$43$var193)))));
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
												for(int index$sample49$130 = 0; index$sample49$130 < noStates; index$sample49$130 += 1) {
													int distributionTempVariable$var44$132 = index$sample49$130;
													double cv$probabilitySample49Value131 = (1.0 * distribution$sample49[index$sample49$130]);
													int traceTempVariable$s$133_1 = distributionTempVariable$var44$132;
													if((0 == i$var180)) {
														for(int var117 = 0; var117 < noStates; var117 += 1) {
															if((var117 == traceTempVariable$s$133_1)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$133_1)) {
																		{
																			{
																				double cv$temp$44$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$133_1];
																					cv$temp$44$var192 = var192;
																				}
																				double cv$temp$45$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$133_1];
																					cv$temp$45$var193 = var193;
																				}
																				if(((Math.log(cv$probabilitySample49Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$44$var192) / Math.sqrt(cv$temp$45$var193))) - (0.5 * Math.log(cv$temp$45$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$44$var192) / Math.sqrt(cv$temp$45$var193))) - (0.5 * Math.log(cv$temp$45$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$44$var192) / Math.sqrt(cv$temp$45$var193))) - (0.5 * Math.log(cv$temp$45$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$44$var192) / Math.sqrt(cv$temp$45$var193))) - (0.5 * Math.log(cv$temp$45$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value131) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$44$var192) / Math.sqrt(cv$temp$45$var193))) - (0.5 * Math.log(cv$temp$45$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value131);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											if(fixedFlag$sample67) {
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if((i$var56 == i$var180)) {
														for(int var117 = 0; var117 < noStates; var117 += 1) {
															if((var117 == traceTempVariable$s$109_1)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$109_1)) {
																		{
																			{
																				double cv$temp$46$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$109_1];
																					cv$temp$46$var192 = var192;
																				}
																				double cv$temp$47$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$109_1];
																					cv$temp$47$var193 = var193;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$46$var192) / Math.sqrt(cv$temp$47$var193))) - (0.5 * Math.log(cv$temp$47$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$46$var192) / Math.sqrt(cv$temp$47$var193))) - (0.5 * Math.log(cv$temp$47$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$46$var192) / Math.sqrt(cv$temp$47$var193))) - (0.5 * Math.log(cv$temp$47$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$46$var192) / Math.sqrt(cv$temp$47$var193))) - (0.5 * Math.log(cv$temp$47$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$46$var192) / Math.sqrt(cv$temp$47$var193))) - (0.5 * Math.log(cv$temp$47$var193)))));
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
												for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
													if(true) {
														for(int index$sample67$140 = 0; index$sample67$140 < noStates; index$sample67$140 += 1) {
															int distributionTempVariable$var62$142 = index$sample67$140;
															double cv$probabilitySample67Value141 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$140]);
															int traceTempVariable$s$143_1 = distributionTempVariable$var62$142;
															if((i$var56 == i$var180)) {
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$143_1)) {
																		for(int var168 = 0; var168 < noStates; var168 += 1) {
																			if((var168 == traceTempVariable$s$143_1)) {
																				{
																					{
																						double cv$temp$48$var192;
																						{
																							double var192 = pageFaultsMean[traceTempVariable$s$143_1];
																							cv$temp$48$var192 = var192;
																						}
																						double cv$temp$49$var193;
																						{
																							double var193 = pageFaultsVar[traceTempVariable$s$143_1];
																							cv$temp$49$var193 = var193;
																						}
																						if(((Math.log(cv$probabilitySample67Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$48$var192) / Math.sqrt(cv$temp$49$var193))) - (0.5 * Math.log(cv$temp$49$var193)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$48$var192) / Math.sqrt(cv$temp$49$var193))) - (0.5 * Math.log(cv$temp$49$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$48$var192) / Math.sqrt(cv$temp$49$var193))) - (0.5 * Math.log(cv$temp$49$var193))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$48$var192) / Math.sqrt(cv$temp$49$var193))) - (0.5 * Math.log(cv$temp$49$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value141) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$48$var192) / Math.sqrt(cv$temp$49$var193))) - (0.5 * Math.log(cv$temp$49$var193)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value141);
																					}
																				}
																			}
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
					int traceTempVariable$var59$156_1 = cv$currentValue;
					for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
						if((0 == (i$var56 - 1))) {
							if(!fixedFlag$sample67) {
								{
									int index$i$158 = i$var56;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var61;
									for(int cv$i = 0; cv$i < noStates; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var35 = 0; var35 < noStates; var35 += 1) {
										if((var35 == traceTempVariable$var59$156_1)) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$50$var60;
												{
													double[] var60 = m[traceTempVariable$var59$156_1];
													cv$temp$50$var60 = var60;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$50$var60);
											}
										}
									}
									double[] cv$sampleDistribution = distribution$sample67[((i$var56 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample49;
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

	private final void sample67(int i$var56) {
		int cv$noStates = 0;
		int index$i$1 = i$var56;
		if(fixedFlag$sample49) {
			if((0 == (i$var56 - 1))) {
				for(int var35 = 0; var35 < noStates; var35 += 1) {
					if((var35 == st[(i$var56 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		} else {
			if(true) {
				for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
					int distributionTempVariable$var44$5 = index$sample49$3;
					double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
					int traceTempVariable$var59$6_1 = distributionTempVariable$var44$5;
					if((0 == (i$var56 - 1))) {
						for(int var35 = 0; var35 < noStates; var35 += 1) {
							if((var35 == traceTempVariable$var59$6_1))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		}
		if((index$i$1 == (i$var56 - 1))) {
			for(int var35 = 0; var35 < noStates; var35 += 1) {
				if((var35 == st[(i$var56 - 1)]))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		if(fixedFlag$sample67) {
			for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
				if((index$i$10_1 == (i$var56 - 1))) {
					for(int var35 = 0; var35 < noStates; var35 += 1) {
						if((var35 == st[(i$var56 - 1)]))
							cv$noStates = Math.max(cv$noStates, noStates);
					}
				}
			}
		} else {
			for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
				if(!(index$i$11 == index$i$1)) {
					for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
						int distributionTempVariable$var62$14 = index$sample67$12;
						double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((index$i$11 - 1) / 1)][index$sample67$12]);
						int traceTempVariable$var59$15_1 = distributionTempVariable$var62$14;
						if((index$i$11 == (i$var56 - 1))) {
							for(int var35 = 0; var35 < noStates; var35 += 1) {
								if((var35 == traceTempVariable$var59$15_1))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var62$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$19 = i$var56;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample49) {
				if((0 == (i$var56 - 1))) {
					for(int var35 = 0; var35 < noStates; var35 += 1) {
						if((var35 == st[(i$var56 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var60;
							{
								double[] var60 = m[st[(i$var56 - 1)]];
								cv$temp$0$var60 = var60;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var60.length))?Math.log(cv$temp$0$var60[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var59$35_1 = cv$currentValue;
								}
							}
							{
								{
									boolean[] guard$sample67gaussian189 = guard$sample67gaussian189$global;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
									}
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
									}
									int traceTempVariable$s$47_1 = cv$currentValue;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var81 = 0; var81 < noStates; var81 += 1) {
															if((var81 == traceTempVariable$s$47_1)) {
																if((0 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$47_1)) {
																			{
																				{
																					double cv$temp$4$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$47_1];
																						cv$temp$4$var182 = var182;
																					}
																					double cv$temp$5$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$47_1];
																						cv$temp$5$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var81 = 0; var81 < noStates; var81 += 1) {
															if((var81 == traceTempVariable$s$47_1)) {
																int traceTempVariable$s$67_1 = cv$currentValue;
																if((index$i$19 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$67_1)) {
																			{
																				{
																					double cv$temp$6$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$67_1];
																						cv$temp$6$var182 = var182;
																					}
																					double cv$temp$7$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$67_1];
																						cv$temp$7$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$68 = 1; index$i$68 < samples; index$i$68 += 1) {
																	if(!(index$i$68 == index$i$19)) {
																		for(int index$sample67$69 = 0; index$sample67$69 < noStates; index$sample67$69 += 1) {
																			int distributionTempVariable$var62$71 = index$sample67$69;
																			double cv$probabilitySample67Value70 = (1.0 * distribution$sample67[((index$i$68 - 1) / 1)][index$sample67$69]);
																			int traceTempVariable$s$72_1 = distributionTempVariable$var62$71;
																			if((index$i$68 == i$var180)) {
																				for(int var134 = 0; var134 < noStates; var134 += 1) {
																					if((var134 == traceTempVariable$s$72_1)) {
																						{
																							{
																								double cv$temp$8$var182;
																								{
																									double var182 = cpuMean[traceTempVariable$s$72_1];
																									cv$temp$8$var182 = var182;
																								}
																								double cv$temp$9$var183;
																								{
																									double var183 = cpuVar[traceTempVariable$s$72_1];
																									cv$temp$9$var183 = var183;
																								}
																								if(((Math.log(cv$probabilitySample67Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value70) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value70);
																							}
																						}
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
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == traceTempVariable$s$51_1)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$36$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$51_1];
																						cv$temp$36$var182 = var182;
																					}
																					double cv$temp$37$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$51_1];
																						cv$temp$37$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$36$var182) / Math.sqrt(cv$temp$37$var183))) - (0.5 * Math.log(cv$temp$37$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$36$var182) / Math.sqrt(cv$temp$37$var183))) - (0.5 * Math.log(cv$temp$37$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$36$var182) / Math.sqrt(cv$temp$37$var183))) - (0.5 * Math.log(cv$temp$37$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$36$var182) / Math.sqrt(cv$temp$37$var183))) - (0.5 * Math.log(cv$temp$37$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$36$var182) / Math.sqrt(cv$temp$37$var183))) - (0.5 * Math.log(cv$temp$37$var183)))));
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
														if((index$i$19 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == traceTempVariable$s$131_1)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$131_1)) {
																			{
																				{
																					double cv$temp$38$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$131_1];
																						cv$temp$38$var182 = var182;
																					}
																					double cv$temp$39$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$131_1];
																						cv$temp$39$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$38$var182) / Math.sqrt(cv$temp$39$var183))) - (0.5 * Math.log(cv$temp$39$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$38$var182) / Math.sqrt(cv$temp$39$var183))) - (0.5 * Math.log(cv$temp$39$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$38$var182) / Math.sqrt(cv$temp$39$var183))) - (0.5 * Math.log(cv$temp$39$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$38$var182) / Math.sqrt(cv$temp$39$var183))) - (0.5 * Math.log(cv$temp$39$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$38$var182) / Math.sqrt(cv$temp$39$var183))) - (0.5 * Math.log(cv$temp$39$var183)))));
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
																for(int index$sample67$133 = 0; index$sample67$133 < noStates; index$sample67$133 += 1) {
																	int distributionTempVariable$var62$135 = index$sample67$133;
																	double cv$probabilitySample67Value134 = (1.0 * distribution$sample67[((index$i$132 - 1) / 1)][index$sample67$133]);
																	int traceTempVariable$s$136_1 = distributionTempVariable$var62$135;
																	if((index$i$132 == i$var180)) {
																		for(int var81 = 0; var81 < noStates; var81 += 1) {
																			if((var81 == traceTempVariable$s$136_1)) {
																				for(int var134 = 0; var134 < noStates; var134 += 1) {
																					if((var134 == traceTempVariable$s$136_1)) {
																						{
																							{
																								double cv$temp$40$var182;
																								{
																									double var182 = cpuMean[traceTempVariable$s$136_1];
																									cv$temp$40$var182 = var182;
																								}
																								double cv$temp$41$var183;
																								{
																									double var183 = cpuVar[traceTempVariable$s$136_1];
																									cv$temp$41$var183 = var183;
																								}
																								if(((Math.log(cv$probabilitySample67Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$40$var182) / Math.sqrt(cv$temp$41$var183))) - (0.5 * Math.log(cv$temp$41$var183)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$40$var182) / Math.sqrt(cv$temp$41$var183))) - (0.5 * Math.log(cv$temp$41$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$40$var182) / Math.sqrt(cv$temp$41$var183))) - (0.5 * Math.log(cv$temp$41$var183))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$40$var182) / Math.sqrt(cv$temp$41$var183))) - (0.5 * Math.log(cv$temp$41$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value134) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$40$var182) / Math.sqrt(cv$temp$41$var183))) - (0.5 * Math.log(cv$temp$41$var183)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value134);
																							}
																						}
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
									boolean[] guard$sample67gaussian194 = guard$sample67gaussian194$global;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
									}
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
									}
									int traceTempVariable$s$241_1 = cv$currentValue;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var99 = 0; var99 < noStates; var99 += 1) {
															if((var99 == traceTempVariable$s$241_1)) {
																if((0 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$241_1)) {
																			{
																				{
																					double cv$temp$68$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$241_1];
																						cv$temp$68$var187 = var187;
																					}
																					double cv$temp$69$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$241_1];
																						cv$temp$69$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$68$var187) / Math.sqrt(cv$temp$69$var188))) - (0.5 * Math.log(cv$temp$69$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$68$var187) / Math.sqrt(cv$temp$69$var188))) - (0.5 * Math.log(cv$temp$69$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$68$var187) / Math.sqrt(cv$temp$69$var188))) - (0.5 * Math.log(cv$temp$69$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$68$var187) / Math.sqrt(cv$temp$69$var188))) - (0.5 * Math.log(cv$temp$69$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$68$var187) / Math.sqrt(cv$temp$69$var188))) - (0.5 * Math.log(cv$temp$69$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var99 = 0; var99 < noStates; var99 += 1) {
															if((var99 == traceTempVariable$s$241_1)) {
																int traceTempVariable$s$261_1 = cv$currentValue;
																if((index$i$19 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$261_1)) {
																			{
																				{
																					double cv$temp$70$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$261_1];
																						cv$temp$70$var187 = var187;
																					}
																					double cv$temp$71$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$261_1];
																						cv$temp$71$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$70$var187) / Math.sqrt(cv$temp$71$var188))) - (0.5 * Math.log(cv$temp$71$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$70$var187) / Math.sqrt(cv$temp$71$var188))) - (0.5 * Math.log(cv$temp$71$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$70$var187) / Math.sqrt(cv$temp$71$var188))) - (0.5 * Math.log(cv$temp$71$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$70$var187) / Math.sqrt(cv$temp$71$var188))) - (0.5 * Math.log(cv$temp$71$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$70$var187) / Math.sqrt(cv$temp$71$var188))) - (0.5 * Math.log(cv$temp$71$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$262 = 1; index$i$262 < samples; index$i$262 += 1) {
																	if(!(index$i$262 == index$i$19)) {
																		for(int index$sample67$263 = 0; index$sample67$263 < noStates; index$sample67$263 += 1) {
																			int distributionTempVariable$var62$265 = index$sample67$263;
																			double cv$probabilitySample67Value264 = (1.0 * distribution$sample67[((index$i$262 - 1) / 1)][index$sample67$263]);
																			int traceTempVariable$s$266_1 = distributionTempVariable$var62$265;
																			if((index$i$262 == i$var180)) {
																				for(int var151 = 0; var151 < noStates; var151 += 1) {
																					if((var151 == traceTempVariable$s$266_1)) {
																						{
																							{
																								double cv$temp$72$var187;
																								{
																									double var187 = memMean[traceTempVariable$s$266_1];
																									cv$temp$72$var187 = var187;
																								}
																								double cv$temp$73$var188;
																								{
																									double var188 = memVar[traceTempVariable$s$266_1];
																									cv$temp$73$var188 = var188;
																								}
																								if(((Math.log(cv$probabilitySample67Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$72$var187) / Math.sqrt(cv$temp$73$var188))) - (0.5 * Math.log(cv$temp$73$var188)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$72$var187) / Math.sqrt(cv$temp$73$var188))) - (0.5 * Math.log(cv$temp$73$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$72$var187) / Math.sqrt(cv$temp$73$var188))) - (0.5 * Math.log(cv$temp$73$var188))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$72$var187) / Math.sqrt(cv$temp$73$var188))) - (0.5 * Math.log(cv$temp$73$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value264) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$72$var187) / Math.sqrt(cv$temp$73$var188))) - (0.5 * Math.log(cv$temp$73$var188)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value264);
																							}
																						}
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
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == traceTempVariable$s$245_1)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$245_1)) {
																			{
																				{
																					double cv$temp$100$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$245_1];
																						cv$temp$100$var187 = var187;
																					}
																					double cv$temp$101$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$245_1];
																						cv$temp$101$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$100$var187) / Math.sqrt(cv$temp$101$var188))) - (0.5 * Math.log(cv$temp$101$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$100$var187) / Math.sqrt(cv$temp$101$var188))) - (0.5 * Math.log(cv$temp$101$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$100$var187) / Math.sqrt(cv$temp$101$var188))) - (0.5 * Math.log(cv$temp$101$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$100$var187) / Math.sqrt(cv$temp$101$var188))) - (0.5 * Math.log(cv$temp$101$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$100$var187) / Math.sqrt(cv$temp$101$var188))) - (0.5 * Math.log(cv$temp$101$var188)))));
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
														if((index$i$19 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == traceTempVariable$s$325_1)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$325_1)) {
																			{
																				{
																					double cv$temp$102$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$325_1];
																						cv$temp$102$var187 = var187;
																					}
																					double cv$temp$103$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$325_1];
																						cv$temp$103$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$102$var187) / Math.sqrt(cv$temp$103$var188))) - (0.5 * Math.log(cv$temp$103$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$102$var187) / Math.sqrt(cv$temp$103$var188))) - (0.5 * Math.log(cv$temp$103$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$102$var187) / Math.sqrt(cv$temp$103$var188))) - (0.5 * Math.log(cv$temp$103$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$102$var187) / Math.sqrt(cv$temp$103$var188))) - (0.5 * Math.log(cv$temp$103$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$102$var187) / Math.sqrt(cv$temp$103$var188))) - (0.5 * Math.log(cv$temp$103$var188)))));
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
																for(int index$sample67$327 = 0; index$sample67$327 < noStates; index$sample67$327 += 1) {
																	int distributionTempVariable$var62$329 = index$sample67$327;
																	double cv$probabilitySample67Value328 = (1.0 * distribution$sample67[((index$i$326 - 1) / 1)][index$sample67$327]);
																	int traceTempVariable$s$330_1 = distributionTempVariable$var62$329;
																	if((index$i$326 == i$var180)) {
																		for(int var99 = 0; var99 < noStates; var99 += 1) {
																			if((var99 == traceTempVariable$s$330_1)) {
																				for(int var151 = 0; var151 < noStates; var151 += 1) {
																					if((var151 == traceTempVariable$s$330_1)) {
																						{
																							{
																								double cv$temp$104$var187;
																								{
																									double var187 = memMean[traceTempVariable$s$330_1];
																									cv$temp$104$var187 = var187;
																								}
																								double cv$temp$105$var188;
																								{
																									double var188 = memVar[traceTempVariable$s$330_1];
																									cv$temp$105$var188 = var188;
																								}
																								if(((Math.log(cv$probabilitySample67Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$104$var187) / Math.sqrt(cv$temp$105$var188))) - (0.5 * Math.log(cv$temp$105$var188)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$104$var187) / Math.sqrt(cv$temp$105$var188))) - (0.5 * Math.log(cv$temp$105$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$104$var187) / Math.sqrt(cv$temp$105$var188))) - (0.5 * Math.log(cv$temp$105$var188))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$104$var187) / Math.sqrt(cv$temp$105$var188))) - (0.5 * Math.log(cv$temp$105$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value328) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$104$var187) / Math.sqrt(cv$temp$105$var188))) - (0.5 * Math.log(cv$temp$105$var188)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value328);
																							}
																						}
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
									boolean[] guard$sample67gaussian199 = guard$sample67gaussian199$global;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
									}
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180))
											guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
									}
									int traceTempVariable$s$435_1 = cv$currentValue;
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var117 = 0; var117 < noStates; var117 += 1) {
															if((var117 == traceTempVariable$s$435_1)) {
																if((0 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$435_1)) {
																			{
																				{
																					double cv$temp$132$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$435_1];
																						cv$temp$132$var192 = var192;
																					}
																					double cv$temp$133$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$435_1];
																						cv$temp$133$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$132$var192) / Math.sqrt(cv$temp$133$var193))) - (0.5 * Math.log(cv$temp$133$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$132$var192) / Math.sqrt(cv$temp$133$var193))) - (0.5 * Math.log(cv$temp$133$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$132$var192) / Math.sqrt(cv$temp$133$var193))) - (0.5 * Math.log(cv$temp$133$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$132$var192) / Math.sqrt(cv$temp$133$var193))) - (0.5 * Math.log(cv$temp$133$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$132$var192) / Math.sqrt(cv$temp$133$var193))) - (0.5 * Math.log(cv$temp$133$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var117 = 0; var117 < noStates; var117 += 1) {
															if((var117 == traceTempVariable$s$435_1)) {
																int traceTempVariable$s$455_1 = cv$currentValue;
																if((index$i$19 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$455_1)) {
																			{
																				{
																					double cv$temp$134$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$455_1];
																						cv$temp$134$var192 = var192;
																					}
																					double cv$temp$135$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$455_1];
																						cv$temp$135$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$134$var192) / Math.sqrt(cv$temp$135$var193))) - (0.5 * Math.log(cv$temp$135$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$134$var192) / Math.sqrt(cv$temp$135$var193))) - (0.5 * Math.log(cv$temp$135$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$134$var192) / Math.sqrt(cv$temp$135$var193))) - (0.5 * Math.log(cv$temp$135$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$134$var192) / Math.sqrt(cv$temp$135$var193))) - (0.5 * Math.log(cv$temp$135$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$134$var192) / Math.sqrt(cv$temp$135$var193))) - (0.5 * Math.log(cv$temp$135$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																for(int index$i$456 = 1; index$i$456 < samples; index$i$456 += 1) {
																	if(!(index$i$456 == index$i$19)) {
																		for(int index$sample67$457 = 0; index$sample67$457 < noStates; index$sample67$457 += 1) {
																			int distributionTempVariable$var62$459 = index$sample67$457;
																			double cv$probabilitySample67Value458 = (1.0 * distribution$sample67[((index$i$456 - 1) / 1)][index$sample67$457]);
																			int traceTempVariable$s$460_1 = distributionTempVariable$var62$459;
																			if((index$i$456 == i$var180)) {
																				for(int var168 = 0; var168 < noStates; var168 += 1) {
																					if((var168 == traceTempVariable$s$460_1)) {
																						{
																							{
																								double cv$temp$136$var192;
																								{
																									double var192 = pageFaultsMean[traceTempVariable$s$460_1];
																									cv$temp$136$var192 = var192;
																								}
																								double cv$temp$137$var193;
																								{
																									double var193 = pageFaultsVar[traceTempVariable$s$460_1];
																									cv$temp$137$var193 = var193;
																								}
																								if(((Math.log(cv$probabilitySample67Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$136$var192) / Math.sqrt(cv$temp$137$var193))) - (0.5 * Math.log(cv$temp$137$var193)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$136$var192) / Math.sqrt(cv$temp$137$var193))) - (0.5 * Math.log(cv$temp$137$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$136$var192) / Math.sqrt(cv$temp$137$var193))) - (0.5 * Math.log(cv$temp$137$var193))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$136$var192) / Math.sqrt(cv$temp$137$var193))) - (0.5 * Math.log(cv$temp$137$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value458) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$136$var192) / Math.sqrt(cv$temp$137$var193))) - (0.5 * Math.log(cv$temp$137$var193)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value458);
																							}
																						}
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
									for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
										if((i$var56 == i$var180)) {
											if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
												guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if((0 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == traceTempVariable$s$439_1)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$439_1)) {
																			{
																				{
																					double cv$temp$164$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$439_1];
																						cv$temp$164$var192 = var192;
																					}
																					double cv$temp$165$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$439_1];
																						cv$temp$165$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$164$var192) / Math.sqrt(cv$temp$165$var193))) - (0.5 * Math.log(cv$temp$165$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$164$var192) / Math.sqrt(cv$temp$165$var193))) - (0.5 * Math.log(cv$temp$165$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$164$var192) / Math.sqrt(cv$temp$165$var193))) - (0.5 * Math.log(cv$temp$165$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$164$var192) / Math.sqrt(cv$temp$165$var193))) - (0.5 * Math.log(cv$temp$165$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$164$var192) / Math.sqrt(cv$temp$165$var193))) - (0.5 * Math.log(cv$temp$165$var193)))));
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
														if((index$i$19 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == traceTempVariable$s$519_1)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$519_1)) {
																			{
																				{
																					double cv$temp$166$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$519_1];
																						cv$temp$166$var192 = var192;
																					}
																					double cv$temp$167$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$519_1];
																						cv$temp$167$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$166$var192) / Math.sqrt(cv$temp$167$var193))) - (0.5 * Math.log(cv$temp$167$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$166$var192) / Math.sqrt(cv$temp$167$var193))) - (0.5 * Math.log(cv$temp$167$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$166$var192) / Math.sqrt(cv$temp$167$var193))) - (0.5 * Math.log(cv$temp$167$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$166$var192) / Math.sqrt(cv$temp$167$var193))) - (0.5 * Math.log(cv$temp$167$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$166$var192) / Math.sqrt(cv$temp$167$var193))) - (0.5 * Math.log(cv$temp$167$var193)))));
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
																for(int index$sample67$521 = 0; index$sample67$521 < noStates; index$sample67$521 += 1) {
																	int distributionTempVariable$var62$523 = index$sample67$521;
																	double cv$probabilitySample67Value522 = (1.0 * distribution$sample67[((index$i$520 - 1) / 1)][index$sample67$521]);
																	int traceTempVariable$s$524_1 = distributionTempVariable$var62$523;
																	if((index$i$520 == i$var180)) {
																		for(int var117 = 0; var117 < noStates; var117 += 1) {
																			if((var117 == traceTempVariable$s$524_1)) {
																				for(int var168 = 0; var168 < noStates; var168 += 1) {
																					if((var168 == traceTempVariable$s$524_1)) {
																						{
																							{
																								double cv$temp$168$var192;
																								{
																									double var192 = pageFaultsMean[traceTempVariable$s$524_1];
																									cv$temp$168$var192 = var192;
																								}
																								double cv$temp$169$var193;
																								{
																									double var193 = pageFaultsVar[traceTempVariable$s$524_1];
																									cv$temp$169$var193 = var193;
																								}
																								if(((Math.log(cv$probabilitySample67Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$168$var192) / Math.sqrt(cv$temp$169$var193))) - (0.5 * Math.log(cv$temp$169$var193)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$168$var192) / Math.sqrt(cv$temp$169$var193))) - (0.5 * Math.log(cv$temp$169$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$168$var192) / Math.sqrt(cv$temp$169$var193))) - (0.5 * Math.log(cv$temp$169$var193))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$168$var192) / Math.sqrt(cv$temp$169$var193))) - (0.5 * Math.log(cv$temp$169$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value522) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$168$var192) / Math.sqrt(cv$temp$169$var193))) - (0.5 * Math.log(cv$temp$169$var193)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value522);
																							}
																						}
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
					for(int index$sample49$21 = 0; index$sample49$21 < noStates; index$sample49$21 += 1) {
						int distributionTempVariable$var44$23 = index$sample49$21;
						double cv$probabilitySample49Value22 = (1.0 * distribution$sample49[index$sample49$21]);
						int traceTempVariable$var59$24_1 = distributionTempVariable$var44$23;
						if((0 == (i$var56 - 1))) {
							for(int var35 = 0; var35 < noStates; var35 += 1) {
								if((var35 == traceTempVariable$var59$24_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample49Value22);
									double[] cv$temp$1$var60;
									{
										double[] var60 = m[traceTempVariable$var59$24_1];
										cv$temp$1$var60 = var60;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample49Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var59$36_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample67gaussian189 = guard$sample67gaussian189$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$48_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$48_1)) {
																		int traceTempVariable$s$76_1 = distributionTempVariable$var44$23;
																		if((0 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$76_1)) {
																					{
																						{
																							double cv$temp$10$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$76_1];
																								cv$temp$10$var182 = var182;
																							}
																							double cv$temp$11$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$76_1];
																								cv$temp$11$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample49$77 = 0; index$sample49$77 < noStates; index$sample49$77 += 1) {
																				int distributionTempVariable$var44$79 = index$sample49$77;
																				double cv$probabilitySample49Value78 = (1.0 * distribution$sample49[index$sample49$77]);
																				int traceTempVariable$s$80_1 = distributionTempVariable$var44$79;
																				if((0 == i$var180)) {
																					for(int var134 = 0; var134 < noStates; var134 += 1) {
																						if((var134 == traceTempVariable$s$80_1)) {
																							{
																								{
																									double cv$temp$12$var182;
																									{
																										double var182 = cpuMean[traceTempVariable$s$80_1];
																										cv$temp$12$var182 = var182;
																									}
																									double cv$temp$13$var183;
																									{
																										double var183 = cpuVar[traceTempVariable$s$80_1];
																										cv$temp$13$var183 = var183;
																									}
																									if(((Math.log(cv$probabilitySample49Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value78) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value78);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$48_1)) {
																		int traceTempVariable$s$84_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$84_1)) {
																					{
																						{
																							double cv$temp$14$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$84_1];
																								cv$temp$14$var182 = var182;
																							}
																							double cv$temp$15$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$84_1];
																								cv$temp$15$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$85 = 1; index$i$85 < samples; index$i$85 += 1) {
																			if(!(index$i$85 == index$i$19)) {
																				for(int index$sample67$86 = 0; index$sample67$86 < noStates; index$sample67$86 += 1) {
																					int distributionTempVariable$var62$88 = index$sample67$86;
																					double cv$probabilitySample67Value87 = (1.0 * distribution$sample67[((index$i$85 - 1) / 1)][index$sample67$86]);
																					int traceTempVariable$s$89_1 = distributionTempVariable$var62$88;
																					if((index$i$85 == i$var180)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$89_1)) {
																								{
																									{
																										double cv$temp$16$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$89_1];
																											cv$temp$16$var182 = var182;
																										}
																										double cv$temp$17$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$89_1];
																											cv$temp$17$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample67Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value87) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value87);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$141_1 = distributionTempVariable$var44$23;
																if((0 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$141_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$141_1)) {
																					{
																						{
																							double cv$temp$42$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$141_1];
																								cv$temp$42$var182 = var182;
																							}
																							double cv$temp$43$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$141_1];
																								cv$temp$43$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$42$var182) / Math.sqrt(cv$temp$43$var183))) - (0.5 * Math.log(cv$temp$43$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$42$var182) / Math.sqrt(cv$temp$43$var183))) - (0.5 * Math.log(cv$temp$43$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$42$var182) / Math.sqrt(cv$temp$43$var183))) - (0.5 * Math.log(cv$temp$43$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$42$var182) / Math.sqrt(cv$temp$43$var183))) - (0.5 * Math.log(cv$temp$43$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$42$var182) / Math.sqrt(cv$temp$43$var183))) - (0.5 * Math.log(cv$temp$43$var183)))));
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
																	for(int index$sample49$142 = 0; index$sample49$142 < noStates; index$sample49$142 += 1) {
																		int distributionTempVariable$var44$144 = index$sample49$142;
																		double cv$probabilitySample49Value143 = (1.0 * distribution$sample49[index$sample49$142]);
																		int traceTempVariable$s$145_1 = distributionTempVariable$var44$144;
																		if((0 == i$var180)) {
																			for(int var81 = 0; var81 < noStates; var81 += 1) {
																				if((var81 == traceTempVariable$s$145_1)) {
																					for(int var134 = 0; var134 < noStates; var134 += 1) {
																						if((var134 == traceTempVariable$s$145_1)) {
																							{
																								{
																									double cv$temp$44$var182;
																									{
																										double var182 = cpuMean[traceTempVariable$s$145_1];
																										cv$temp$44$var182 = var182;
																									}
																									double cv$temp$45$var183;
																									{
																										double var183 = cpuVar[traceTempVariable$s$145_1];
																										cv$temp$45$var183 = var183;
																									}
																									if(((Math.log(cv$probabilitySample49Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$44$var182) / Math.sqrt(cv$temp$45$var183))) - (0.5 * Math.log(cv$temp$45$var183)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$44$var182) / Math.sqrt(cv$temp$45$var183))) - (0.5 * Math.log(cv$temp$45$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$44$var182) / Math.sqrt(cv$temp$45$var183))) - (0.5 * Math.log(cv$temp$45$var183))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$44$var182) / Math.sqrt(cv$temp$45$var183))) - (0.5 * Math.log(cv$temp$45$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value143) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$44$var182) / Math.sqrt(cv$temp$45$var183))) - (0.5 * Math.log(cv$temp$45$var183)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value143);
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
																if((index$i$19 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$150_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$150_1)) {
																					{
																						{
																							double cv$temp$46$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$150_1];
																								cv$temp$46$var182 = var182;
																							}
																							double cv$temp$47$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$150_1];
																								cv$temp$47$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$46$var182) / Math.sqrt(cv$temp$47$var183))) - (0.5 * Math.log(cv$temp$47$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$46$var182) / Math.sqrt(cv$temp$47$var183))) - (0.5 * Math.log(cv$temp$47$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$46$var182) / Math.sqrt(cv$temp$47$var183))) - (0.5 * Math.log(cv$temp$47$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$46$var182) / Math.sqrt(cv$temp$47$var183))) - (0.5 * Math.log(cv$temp$47$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$46$var182) / Math.sqrt(cv$temp$47$var183))) - (0.5 * Math.log(cv$temp$47$var183)))));
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
																		for(int index$sample67$152 = 0; index$sample67$152 < noStates; index$sample67$152 += 1) {
																			int distributionTempVariable$var62$154 = index$sample67$152;
																			double cv$probabilitySample67Value153 = (1.0 * distribution$sample67[((index$i$151 - 1) / 1)][index$sample67$152]);
																			int traceTempVariable$s$155_1 = distributionTempVariable$var62$154;
																			if((index$i$151 == i$var180)) {
																				for(int var81 = 0; var81 < noStates; var81 += 1) {
																					if((var81 == traceTempVariable$s$155_1)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$155_1)) {
																								{
																									{
																										double cv$temp$48$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$155_1];
																											cv$temp$48$var182 = var182;
																										}
																										double cv$temp$49$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$155_1];
																											cv$temp$49$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample67Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$48$var182) / Math.sqrt(cv$temp$49$var183))) - (0.5 * Math.log(cv$temp$49$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$48$var182) / Math.sqrt(cv$temp$49$var183))) - (0.5 * Math.log(cv$temp$49$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$48$var182) / Math.sqrt(cv$temp$49$var183))) - (0.5 * Math.log(cv$temp$49$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$48$var182) / Math.sqrt(cv$temp$49$var183))) - (0.5 * Math.log(cv$temp$49$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value153) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$48$var182) / Math.sqrt(cv$temp$49$var183))) - (0.5 * Math.log(cv$temp$49$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value153);
																									}
																								}
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
											boolean[] guard$sample67gaussian194 = guard$sample67gaussian194$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$242_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$242_1)) {
																		int traceTempVariable$s$270_1 = distributionTempVariable$var44$23;
																		if((0 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$270_1)) {
																					{
																						{
																							double cv$temp$74$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$270_1];
																								cv$temp$74$var187 = var187;
																							}
																							double cv$temp$75$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$270_1];
																								cv$temp$75$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$74$var187) / Math.sqrt(cv$temp$75$var188))) - (0.5 * Math.log(cv$temp$75$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$74$var187) / Math.sqrt(cv$temp$75$var188))) - (0.5 * Math.log(cv$temp$75$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$74$var187) / Math.sqrt(cv$temp$75$var188))) - (0.5 * Math.log(cv$temp$75$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$74$var187) / Math.sqrt(cv$temp$75$var188))) - (0.5 * Math.log(cv$temp$75$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$74$var187) / Math.sqrt(cv$temp$75$var188))) - (0.5 * Math.log(cv$temp$75$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample49$271 = 0; index$sample49$271 < noStates; index$sample49$271 += 1) {
																				int distributionTempVariable$var44$273 = index$sample49$271;
																				double cv$probabilitySample49Value272 = (1.0 * distribution$sample49[index$sample49$271]);
																				int traceTempVariable$s$274_1 = distributionTempVariable$var44$273;
																				if((0 == i$var180)) {
																					for(int var151 = 0; var151 < noStates; var151 += 1) {
																						if((var151 == traceTempVariable$s$274_1)) {
																							{
																								{
																									double cv$temp$76$var187;
																									{
																										double var187 = memMean[traceTempVariable$s$274_1];
																										cv$temp$76$var187 = var187;
																									}
																									double cv$temp$77$var188;
																									{
																										double var188 = memVar[traceTempVariable$s$274_1];
																										cv$temp$77$var188 = var188;
																									}
																									if(((Math.log(cv$probabilitySample49Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$76$var187) / Math.sqrt(cv$temp$77$var188))) - (0.5 * Math.log(cv$temp$77$var188)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$76$var187) / Math.sqrt(cv$temp$77$var188))) - (0.5 * Math.log(cv$temp$77$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$76$var187) / Math.sqrt(cv$temp$77$var188))) - (0.5 * Math.log(cv$temp$77$var188))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$76$var187) / Math.sqrt(cv$temp$77$var188))) - (0.5 * Math.log(cv$temp$77$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value272) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$76$var187) / Math.sqrt(cv$temp$77$var188))) - (0.5 * Math.log(cv$temp$77$var188)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value272);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$242_1)) {
																		int traceTempVariable$s$278_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$278_1)) {
																					{
																						{
																							double cv$temp$78$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$278_1];
																								cv$temp$78$var187 = var187;
																							}
																							double cv$temp$79$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$278_1];
																								cv$temp$79$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$78$var187) / Math.sqrt(cv$temp$79$var188))) - (0.5 * Math.log(cv$temp$79$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$78$var187) / Math.sqrt(cv$temp$79$var188))) - (0.5 * Math.log(cv$temp$79$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$78$var187) / Math.sqrt(cv$temp$79$var188))) - (0.5 * Math.log(cv$temp$79$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$78$var187) / Math.sqrt(cv$temp$79$var188))) - (0.5 * Math.log(cv$temp$79$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$78$var187) / Math.sqrt(cv$temp$79$var188))) - (0.5 * Math.log(cv$temp$79$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$279 = 1; index$i$279 < samples; index$i$279 += 1) {
																			if(!(index$i$279 == index$i$19)) {
																				for(int index$sample67$280 = 0; index$sample67$280 < noStates; index$sample67$280 += 1) {
																					int distributionTempVariable$var62$282 = index$sample67$280;
																					double cv$probabilitySample67Value281 = (1.0 * distribution$sample67[((index$i$279 - 1) / 1)][index$sample67$280]);
																					int traceTempVariable$s$283_1 = distributionTempVariable$var62$282;
																					if((index$i$279 == i$var180)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$283_1)) {
																								{
																									{
																										double cv$temp$80$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$283_1];
																											cv$temp$80$var187 = var187;
																										}
																										double cv$temp$81$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$283_1];
																											cv$temp$81$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample67Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$80$var187) / Math.sqrt(cv$temp$81$var188))) - (0.5 * Math.log(cv$temp$81$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$80$var187) / Math.sqrt(cv$temp$81$var188))) - (0.5 * Math.log(cv$temp$81$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$80$var187) / Math.sqrt(cv$temp$81$var188))) - (0.5 * Math.log(cv$temp$81$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$80$var187) / Math.sqrt(cv$temp$81$var188))) - (0.5 * Math.log(cv$temp$81$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value281) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$80$var187) / Math.sqrt(cv$temp$81$var188))) - (0.5 * Math.log(cv$temp$81$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value281);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$335_1 = distributionTempVariable$var44$23;
																if((0 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$335_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$335_1)) {
																					{
																						{
																							double cv$temp$106$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$335_1];
																								cv$temp$106$var187 = var187;
																							}
																							double cv$temp$107$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$335_1];
																								cv$temp$107$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$106$var187) / Math.sqrt(cv$temp$107$var188))) - (0.5 * Math.log(cv$temp$107$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$106$var187) / Math.sqrt(cv$temp$107$var188))) - (0.5 * Math.log(cv$temp$107$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$106$var187) / Math.sqrt(cv$temp$107$var188))) - (0.5 * Math.log(cv$temp$107$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$106$var187) / Math.sqrt(cv$temp$107$var188))) - (0.5 * Math.log(cv$temp$107$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$106$var187) / Math.sqrt(cv$temp$107$var188))) - (0.5 * Math.log(cv$temp$107$var188)))));
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
																	for(int index$sample49$336 = 0; index$sample49$336 < noStates; index$sample49$336 += 1) {
																		int distributionTempVariable$var44$338 = index$sample49$336;
																		double cv$probabilitySample49Value337 = (1.0 * distribution$sample49[index$sample49$336]);
																		int traceTempVariable$s$339_1 = distributionTempVariable$var44$338;
																		if((0 == i$var180)) {
																			for(int var99 = 0; var99 < noStates; var99 += 1) {
																				if((var99 == traceTempVariable$s$339_1)) {
																					for(int var151 = 0; var151 < noStates; var151 += 1) {
																						if((var151 == traceTempVariable$s$339_1)) {
																							{
																								{
																									double cv$temp$108$var187;
																									{
																										double var187 = memMean[traceTempVariable$s$339_1];
																										cv$temp$108$var187 = var187;
																									}
																									double cv$temp$109$var188;
																									{
																										double var188 = memVar[traceTempVariable$s$339_1];
																										cv$temp$109$var188 = var188;
																									}
																									if(((Math.log(cv$probabilitySample49Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$108$var187) / Math.sqrt(cv$temp$109$var188))) - (0.5 * Math.log(cv$temp$109$var188)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$108$var187) / Math.sqrt(cv$temp$109$var188))) - (0.5 * Math.log(cv$temp$109$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$108$var187) / Math.sqrt(cv$temp$109$var188))) - (0.5 * Math.log(cv$temp$109$var188))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$108$var187) / Math.sqrt(cv$temp$109$var188))) - (0.5 * Math.log(cv$temp$109$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value337) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$108$var187) / Math.sqrt(cv$temp$109$var188))) - (0.5 * Math.log(cv$temp$109$var188)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value337);
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
																if((index$i$19 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$344_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$344_1)) {
																					{
																						{
																							double cv$temp$110$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$344_1];
																								cv$temp$110$var187 = var187;
																							}
																							double cv$temp$111$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$344_1];
																								cv$temp$111$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$110$var187) / Math.sqrt(cv$temp$111$var188))) - (0.5 * Math.log(cv$temp$111$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$110$var187) / Math.sqrt(cv$temp$111$var188))) - (0.5 * Math.log(cv$temp$111$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$110$var187) / Math.sqrt(cv$temp$111$var188))) - (0.5 * Math.log(cv$temp$111$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$110$var187) / Math.sqrt(cv$temp$111$var188))) - (0.5 * Math.log(cv$temp$111$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$110$var187) / Math.sqrt(cv$temp$111$var188))) - (0.5 * Math.log(cv$temp$111$var188)))));
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
																		for(int index$sample67$346 = 0; index$sample67$346 < noStates; index$sample67$346 += 1) {
																			int distributionTempVariable$var62$348 = index$sample67$346;
																			double cv$probabilitySample67Value347 = (1.0 * distribution$sample67[((index$i$345 - 1) / 1)][index$sample67$346]);
																			int traceTempVariable$s$349_1 = distributionTempVariable$var62$348;
																			if((index$i$345 == i$var180)) {
																				for(int var99 = 0; var99 < noStates; var99 += 1) {
																					if((var99 == traceTempVariable$s$349_1)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$349_1)) {
																								{
																									{
																										double cv$temp$112$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$349_1];
																											cv$temp$112$var187 = var187;
																										}
																										double cv$temp$113$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$349_1];
																											cv$temp$113$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample67Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$112$var187) / Math.sqrt(cv$temp$113$var188))) - (0.5 * Math.log(cv$temp$113$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$112$var187) / Math.sqrt(cv$temp$113$var188))) - (0.5 * Math.log(cv$temp$113$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$112$var187) / Math.sqrt(cv$temp$113$var188))) - (0.5 * Math.log(cv$temp$113$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$112$var187) / Math.sqrt(cv$temp$113$var188))) - (0.5 * Math.log(cv$temp$113$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value347) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$112$var187) / Math.sqrt(cv$temp$113$var188))) - (0.5 * Math.log(cv$temp$113$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value347);
																									}
																								}
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
											boolean[] guard$sample67gaussian199 = guard$sample67gaussian199$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$436_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$436_1)) {
																		int traceTempVariable$s$464_1 = distributionTempVariable$var44$23;
																		if((0 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$464_1)) {
																					{
																						{
																							double cv$temp$138$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$464_1];
																								cv$temp$138$var192 = var192;
																							}
																							double cv$temp$139$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$464_1];
																								cv$temp$139$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$138$var192) / Math.sqrt(cv$temp$139$var193))) - (0.5 * Math.log(cv$temp$139$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$138$var192) / Math.sqrt(cv$temp$139$var193))) - (0.5 * Math.log(cv$temp$139$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$138$var192) / Math.sqrt(cv$temp$139$var193))) - (0.5 * Math.log(cv$temp$139$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$138$var192) / Math.sqrt(cv$temp$139$var193))) - (0.5 * Math.log(cv$temp$139$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$138$var192) / Math.sqrt(cv$temp$139$var193))) - (0.5 * Math.log(cv$temp$139$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample49$465 = 0; index$sample49$465 < noStates; index$sample49$465 += 1) {
																				int distributionTempVariable$var44$467 = index$sample49$465;
																				double cv$probabilitySample49Value466 = (1.0 * distribution$sample49[index$sample49$465]);
																				int traceTempVariable$s$468_1 = distributionTempVariable$var44$467;
																				if((0 == i$var180)) {
																					for(int var168 = 0; var168 < noStates; var168 += 1) {
																						if((var168 == traceTempVariable$s$468_1)) {
																							{
																								{
																									double cv$temp$140$var192;
																									{
																										double var192 = pageFaultsMean[traceTempVariable$s$468_1];
																										cv$temp$140$var192 = var192;
																									}
																									double cv$temp$141$var193;
																									{
																										double var193 = pageFaultsVar[traceTempVariable$s$468_1];
																										cv$temp$141$var193 = var193;
																									}
																									if(((Math.log(cv$probabilitySample49Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$140$var192) / Math.sqrt(cv$temp$141$var193))) - (0.5 * Math.log(cv$temp$141$var193)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$140$var192) / Math.sqrt(cv$temp$141$var193))) - (0.5 * Math.log(cv$temp$141$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$140$var192) / Math.sqrt(cv$temp$141$var193))) - (0.5 * Math.log(cv$temp$141$var193))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$140$var192) / Math.sqrt(cv$temp$141$var193))) - (0.5 * Math.log(cv$temp$141$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value466) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$140$var192) / Math.sqrt(cv$temp$141$var193))) - (0.5 * Math.log(cv$temp$141$var193)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value466);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$436_1)) {
																		int traceTempVariable$s$472_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$472_1)) {
																					{
																						{
																							double cv$temp$142$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$472_1];
																								cv$temp$142$var192 = var192;
																							}
																							double cv$temp$143$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$472_1];
																								cv$temp$143$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$142$var192) / Math.sqrt(cv$temp$143$var193))) - (0.5 * Math.log(cv$temp$143$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$142$var192) / Math.sqrt(cv$temp$143$var193))) - (0.5 * Math.log(cv$temp$143$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$142$var192) / Math.sqrt(cv$temp$143$var193))) - (0.5 * Math.log(cv$temp$143$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$142$var192) / Math.sqrt(cv$temp$143$var193))) - (0.5 * Math.log(cv$temp$143$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$142$var192) / Math.sqrt(cv$temp$143$var193))) - (0.5 * Math.log(cv$temp$143$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$473 = 1; index$i$473 < samples; index$i$473 += 1) {
																			if(!(index$i$473 == index$i$19)) {
																				for(int index$sample67$474 = 0; index$sample67$474 < noStates; index$sample67$474 += 1) {
																					int distributionTempVariable$var62$476 = index$sample67$474;
																					double cv$probabilitySample67Value475 = (1.0 * distribution$sample67[((index$i$473 - 1) / 1)][index$sample67$474]);
																					int traceTempVariable$s$477_1 = distributionTempVariable$var62$476;
																					if((index$i$473 == i$var180)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$477_1)) {
																								{
																									{
																										double cv$temp$144$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$477_1];
																											cv$temp$144$var192 = var192;
																										}
																										double cv$temp$145$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$477_1];
																											cv$temp$145$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample67Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$144$var192) / Math.sqrt(cv$temp$145$var193))) - (0.5 * Math.log(cv$temp$145$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$144$var192) / Math.sqrt(cv$temp$145$var193))) - (0.5 * Math.log(cv$temp$145$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$144$var192) / Math.sqrt(cv$temp$145$var193))) - (0.5 * Math.log(cv$temp$145$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$144$var192) / Math.sqrt(cv$temp$145$var193))) - (0.5 * Math.log(cv$temp$145$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value475) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$144$var192) / Math.sqrt(cv$temp$145$var193))) - (0.5 * Math.log(cv$temp$145$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value475);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$s$529_1 = distributionTempVariable$var44$23;
																if((0 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$529_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$529_1)) {
																					{
																						{
																							double cv$temp$170$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$529_1];
																								cv$temp$170$var192 = var192;
																							}
																							double cv$temp$171$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$529_1];
																								cv$temp$171$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$170$var192) / Math.sqrt(cv$temp$171$var193))) - (0.5 * Math.log(cv$temp$171$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$170$var192) / Math.sqrt(cv$temp$171$var193))) - (0.5 * Math.log(cv$temp$171$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$170$var192) / Math.sqrt(cv$temp$171$var193))) - (0.5 * Math.log(cv$temp$171$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$170$var192) / Math.sqrt(cv$temp$171$var193))) - (0.5 * Math.log(cv$temp$171$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$170$var192) / Math.sqrt(cv$temp$171$var193))) - (0.5 * Math.log(cv$temp$171$var193)))));
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
																	for(int index$sample49$530 = 0; index$sample49$530 < noStates; index$sample49$530 += 1) {
																		int distributionTempVariable$var44$532 = index$sample49$530;
																		double cv$probabilitySample49Value531 = (1.0 * distribution$sample49[index$sample49$530]);
																		int traceTempVariable$s$533_1 = distributionTempVariable$var44$532;
																		if((0 == i$var180)) {
																			for(int var117 = 0; var117 < noStates; var117 += 1) {
																				if((var117 == traceTempVariable$s$533_1)) {
																					for(int var168 = 0; var168 < noStates; var168 += 1) {
																						if((var168 == traceTempVariable$s$533_1)) {
																							{
																								{
																									double cv$temp$172$var192;
																									{
																										double var192 = pageFaultsMean[traceTempVariable$s$533_1];
																										cv$temp$172$var192 = var192;
																									}
																									double cv$temp$173$var193;
																									{
																										double var193 = pageFaultsVar[traceTempVariable$s$533_1];
																										cv$temp$173$var193 = var193;
																									}
																									if(((Math.log(cv$probabilitySample49Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$172$var192) / Math.sqrt(cv$temp$173$var193))) - (0.5 * Math.log(cv$temp$173$var193)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$172$var192) / Math.sqrt(cv$temp$173$var193))) - (0.5 * Math.log(cv$temp$173$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$172$var192) / Math.sqrt(cv$temp$173$var193))) - (0.5 * Math.log(cv$temp$173$var193))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$172$var192) / Math.sqrt(cv$temp$173$var193))) - (0.5 * Math.log(cv$temp$173$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value531) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$172$var192) / Math.sqrt(cv$temp$173$var193))) - (0.5 * Math.log(cv$temp$173$var193)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value531);
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
																if((index$i$19 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$538_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$538_1)) {
																					{
																						{
																							double cv$temp$174$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$538_1];
																								cv$temp$174$var192 = var192;
																							}
																							double cv$temp$175$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$538_1];
																								cv$temp$175$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$174$var192) / Math.sqrt(cv$temp$175$var193))) - (0.5 * Math.log(cv$temp$175$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$174$var192) / Math.sqrt(cv$temp$175$var193))) - (0.5 * Math.log(cv$temp$175$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$174$var192) / Math.sqrt(cv$temp$175$var193))) - (0.5 * Math.log(cv$temp$175$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$174$var192) / Math.sqrt(cv$temp$175$var193))) - (0.5 * Math.log(cv$temp$175$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$174$var192) / Math.sqrt(cv$temp$175$var193))) - (0.5 * Math.log(cv$temp$175$var193)))));
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
																		for(int index$sample67$540 = 0; index$sample67$540 < noStates; index$sample67$540 += 1) {
																			int distributionTempVariable$var62$542 = index$sample67$540;
																			double cv$probabilitySample67Value541 = (1.0 * distribution$sample67[((index$i$539 - 1) / 1)][index$sample67$540]);
																			int traceTempVariable$s$543_1 = distributionTempVariable$var62$542;
																			if((index$i$539 == i$var180)) {
																				for(int var117 = 0; var117 < noStates; var117 += 1) {
																					if((var117 == traceTempVariable$s$543_1)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$543_1)) {
																								{
																									{
																										double cv$temp$176$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$543_1];
																											cv$temp$176$var192 = var192;
																										}
																										double cv$temp$177$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$543_1];
																											cv$temp$177$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample67Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$176$var192) / Math.sqrt(cv$temp$177$var193))) - (0.5 * Math.log(cv$temp$177$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$176$var192) / Math.sqrt(cv$temp$177$var193))) - (0.5 * Math.log(cv$temp$177$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$176$var192) / Math.sqrt(cv$temp$177$var193))) - (0.5 * Math.log(cv$temp$177$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$176$var192) / Math.sqrt(cv$temp$177$var193))) - (0.5 * Math.log(cv$temp$177$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value541) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$176$var192) / Math.sqrt(cv$temp$177$var193))) - (0.5 * Math.log(cv$temp$177$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value541);
																									}
																								}
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
			int traceTempVariable$var59$27_1 = cv$currentValue;
			if((index$i$19 == (i$var56 - 1))) {
				for(int var35 = 0; var35 < noStates; var35 += 1) {
					if((var35 == traceTempVariable$var59$27_1)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var60;
						{
							double[] var60 = m[traceTempVariable$var59$27_1];
							cv$temp$2$var60 = var60;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var60.length))?Math.log(cv$temp$2$var60[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var59$37_1 = cv$currentValue;
							}
						}
						{
							{
								boolean[] guard$sample67gaussian189 = guard$sample67gaussian189$global;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
								}
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
								}
								int traceTempVariable$s$49_1 = cv$currentValue;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var81 = 0; var81 < noStates; var81 += 1) {
														if((var81 == traceTempVariable$s$49_1)) {
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$49_1)) {
																			{
																				{
																					double cv$temp$18$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$49_1];
																						cv$temp$18$var182 = var182;
																					}
																					double cv$temp$19$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$49_1];
																						cv$temp$19$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$94 = 0; index$sample49$94 < noStates; index$sample49$94 += 1) {
																		int distributionTempVariable$var44$96 = index$sample49$94;
																		double cv$probabilitySample49Value95 = (1.0 * distribution$sample49[index$sample49$94]);
																		int traceTempVariable$s$97_1 = distributionTempVariable$var44$96;
																		if((0 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$97_1)) {
																					{
																						{
																							double cv$temp$20$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$97_1];
																								cv$temp$20$var182 = var182;
																							}
																							double cv$temp$21$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$97_1];
																								cv$temp$21$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample49Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value95) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value95);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var81 = 0; var81 < noStates; var81 += 1) {
														if((var81 == traceTempVariable$s$49_1)) {
															int traceTempVariable$s$101_1 = cv$currentValue;
															if((index$i$19 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$101_1)) {
																		{
																			{
																				double cv$temp$22$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$101_1];
																					cv$temp$22$var182 = var182;
																				}
																				double cv$temp$23$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$101_1];
																					cv$temp$23$var183 = var183;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$102 = 1; index$i$102 < samples; index$i$102 += 1) {
																if(!(index$i$102 == index$i$19)) {
																	for(int index$sample67$103 = 0; index$sample67$103 < noStates; index$sample67$103 += 1) {
																		int distributionTempVariable$var62$105 = index$sample67$103;
																		double cv$probabilitySample67Value104 = (1.0 * distribution$sample67[((index$i$102 - 1) / 1)][index$sample67$103]);
																		int traceTempVariable$s$106_1 = distributionTempVariable$var62$105;
																		if((index$i$102 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$106_1)) {
																					{
																						{
																							double cv$temp$24$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$106_1];
																								cv$temp$24$var182 = var182;
																							}
																							double cv$temp$25$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$106_1];
																								cv$temp$25$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value104) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value104);
																						}
																					}
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
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var81 = 0; var81 < noStates; var81 += 1) {
																if((var81 == traceTempVariable$s$53_1)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$53_1)) {
																			{
																				{
																					double cv$temp$50$var182;
																					{
																						double var182 = cpuMean[traceTempVariable$s$53_1];
																						cv$temp$50$var182 = var182;
																					}
																					double cv$temp$51$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$53_1];
																						cv$temp$51$var183 = var183;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$50$var182) / Math.sqrt(cv$temp$51$var183))) - (0.5 * Math.log(cv$temp$51$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$50$var182) / Math.sqrt(cv$temp$51$var183))) - (0.5 * Math.log(cv$temp$51$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$50$var182) / Math.sqrt(cv$temp$51$var183))) - (0.5 * Math.log(cv$temp$51$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$50$var182) / Math.sqrt(cv$temp$51$var183))) - (0.5 * Math.log(cv$temp$51$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$50$var182) / Math.sqrt(cv$temp$51$var183))) - (0.5 * Math.log(cv$temp$51$var183)))));
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
															for(int index$sample49$161 = 0; index$sample49$161 < noStates; index$sample49$161 += 1) {
																int distributionTempVariable$var44$163 = index$sample49$161;
																double cv$probabilitySample49Value162 = (1.0 * distribution$sample49[index$sample49$161]);
																int traceTempVariable$s$164_1 = distributionTempVariable$var44$163;
																if((0 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$164_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$164_1)) {
																					{
																						{
																							double cv$temp$52$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$164_1];
																								cv$temp$52$var182 = var182;
																							}
																							double cv$temp$53$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$164_1];
																								cv$temp$53$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample49Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$52$var182) / Math.sqrt(cv$temp$53$var183))) - (0.5 * Math.log(cv$temp$53$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$52$var182) / Math.sqrt(cv$temp$53$var183))) - (0.5 * Math.log(cv$temp$53$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$52$var182) / Math.sqrt(cv$temp$53$var183))) - (0.5 * Math.log(cv$temp$53$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$52$var182) / Math.sqrt(cv$temp$53$var183))) - (0.5 * Math.log(cv$temp$53$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value162) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$52$var182) / Math.sqrt(cv$temp$53$var183))) - (0.5 * Math.log(cv$temp$53$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value162);
																						}
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
													if((index$i$19 == i$var180)) {
														for(int var81 = 0; var81 < noStates; var81 += 1) {
															if((var81 == traceTempVariable$s$169_1)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$169_1)) {
																		{
																			{
																				double cv$temp$54$var182;
																				{
																					double var182 = cpuMean[traceTempVariable$s$169_1];
																					cv$temp$54$var182 = var182;
																				}
																				double cv$temp$55$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$169_1];
																					cv$temp$55$var183 = var183;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$54$var182) / Math.sqrt(cv$temp$55$var183))) - (0.5 * Math.log(cv$temp$55$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$54$var182) / Math.sqrt(cv$temp$55$var183))) - (0.5 * Math.log(cv$temp$55$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$54$var182) / Math.sqrt(cv$temp$55$var183))) - (0.5 * Math.log(cv$temp$55$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$54$var182) / Math.sqrt(cv$temp$55$var183))) - (0.5 * Math.log(cv$temp$55$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$54$var182) / Math.sqrt(cv$temp$55$var183))) - (0.5 * Math.log(cv$temp$55$var183)))));
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
															for(int index$sample67$171 = 0; index$sample67$171 < noStates; index$sample67$171 += 1) {
																int distributionTempVariable$var62$173 = index$sample67$171;
																double cv$probabilitySample67Value172 = (1.0 * distribution$sample67[((index$i$170 - 1) / 1)][index$sample67$171]);
																int traceTempVariable$s$174_1 = distributionTempVariable$var62$173;
																if((index$i$170 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$174_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$174_1)) {
																					{
																						{
																							double cv$temp$56$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$174_1];
																								cv$temp$56$var182 = var182;
																							}
																							double cv$temp$57$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$174_1];
																								cv$temp$57$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$56$var182) / Math.sqrt(cv$temp$57$var183))) - (0.5 * Math.log(cv$temp$57$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$56$var182) / Math.sqrt(cv$temp$57$var183))) - (0.5 * Math.log(cv$temp$57$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$56$var182) / Math.sqrt(cv$temp$57$var183))) - (0.5 * Math.log(cv$temp$57$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$56$var182) / Math.sqrt(cv$temp$57$var183))) - (0.5 * Math.log(cv$temp$57$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value172) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$56$var182) / Math.sqrt(cv$temp$57$var183))) - (0.5 * Math.log(cv$temp$57$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value172);
																						}
																					}
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
								boolean[] guard$sample67gaussian194 = guard$sample67gaussian194$global;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
								}
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
								}
								int traceTempVariable$s$243_1 = cv$currentValue;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var99 = 0; var99 < noStates; var99 += 1) {
														if((var99 == traceTempVariable$s$243_1)) {
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$243_1)) {
																			{
																				{
																					double cv$temp$82$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$243_1];
																						cv$temp$82$var187 = var187;
																					}
																					double cv$temp$83$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$243_1];
																						cv$temp$83$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$82$var187) / Math.sqrt(cv$temp$83$var188))) - (0.5 * Math.log(cv$temp$83$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$82$var187) / Math.sqrt(cv$temp$83$var188))) - (0.5 * Math.log(cv$temp$83$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$82$var187) / Math.sqrt(cv$temp$83$var188))) - (0.5 * Math.log(cv$temp$83$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$82$var187) / Math.sqrt(cv$temp$83$var188))) - (0.5 * Math.log(cv$temp$83$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$82$var187) / Math.sqrt(cv$temp$83$var188))) - (0.5 * Math.log(cv$temp$83$var188)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$288 = 0; index$sample49$288 < noStates; index$sample49$288 += 1) {
																		int distributionTempVariable$var44$290 = index$sample49$288;
																		double cv$probabilitySample49Value289 = (1.0 * distribution$sample49[index$sample49$288]);
																		int traceTempVariable$s$291_1 = distributionTempVariable$var44$290;
																		if((0 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$291_1)) {
																					{
																						{
																							double cv$temp$84$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$291_1];
																								cv$temp$84$var187 = var187;
																							}
																							double cv$temp$85$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$291_1];
																								cv$temp$85$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample49Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$84$var187) / Math.sqrt(cv$temp$85$var188))) - (0.5 * Math.log(cv$temp$85$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$84$var187) / Math.sqrt(cv$temp$85$var188))) - (0.5 * Math.log(cv$temp$85$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$84$var187) / Math.sqrt(cv$temp$85$var188))) - (0.5 * Math.log(cv$temp$85$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$84$var187) / Math.sqrt(cv$temp$85$var188))) - (0.5 * Math.log(cv$temp$85$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value289) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$84$var187) / Math.sqrt(cv$temp$85$var188))) - (0.5 * Math.log(cv$temp$85$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value289);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var99 = 0; var99 < noStates; var99 += 1) {
														if((var99 == traceTempVariable$s$243_1)) {
															int traceTempVariable$s$295_1 = cv$currentValue;
															if((index$i$19 == i$var180)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$295_1)) {
																		{
																			{
																				double cv$temp$86$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$295_1];
																					cv$temp$86$var187 = var187;
																				}
																				double cv$temp$87$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$295_1];
																					cv$temp$87$var188 = var188;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$86$var187) / Math.sqrt(cv$temp$87$var188))) - (0.5 * Math.log(cv$temp$87$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$86$var187) / Math.sqrt(cv$temp$87$var188))) - (0.5 * Math.log(cv$temp$87$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$86$var187) / Math.sqrt(cv$temp$87$var188))) - (0.5 * Math.log(cv$temp$87$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$86$var187) / Math.sqrt(cv$temp$87$var188))) - (0.5 * Math.log(cv$temp$87$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$86$var187) / Math.sqrt(cv$temp$87$var188))) - (0.5 * Math.log(cv$temp$87$var188)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$296 = 1; index$i$296 < samples; index$i$296 += 1) {
																if(!(index$i$296 == index$i$19)) {
																	for(int index$sample67$297 = 0; index$sample67$297 < noStates; index$sample67$297 += 1) {
																		int distributionTempVariable$var62$299 = index$sample67$297;
																		double cv$probabilitySample67Value298 = (1.0 * distribution$sample67[((index$i$296 - 1) / 1)][index$sample67$297]);
																		int traceTempVariable$s$300_1 = distributionTempVariable$var62$299;
																		if((index$i$296 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$300_1)) {
																					{
																						{
																							double cv$temp$88$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$300_1];
																								cv$temp$88$var187 = var187;
																							}
																							double cv$temp$89$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$300_1];
																								cv$temp$89$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$88$var187) / Math.sqrt(cv$temp$89$var188))) - (0.5 * Math.log(cv$temp$89$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$88$var187) / Math.sqrt(cv$temp$89$var188))) - (0.5 * Math.log(cv$temp$89$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$88$var187) / Math.sqrt(cv$temp$89$var188))) - (0.5 * Math.log(cv$temp$89$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$88$var187) / Math.sqrt(cv$temp$89$var188))) - (0.5 * Math.log(cv$temp$89$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value298) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$88$var187) / Math.sqrt(cv$temp$89$var188))) - (0.5 * Math.log(cv$temp$89$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value298);
																						}
																					}
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
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var99 = 0; var99 < noStates; var99 += 1) {
																if((var99 == traceTempVariable$s$247_1)) {
																	for(int var151 = 0; var151 < noStates; var151 += 1) {
																		if((var151 == traceTempVariable$s$247_1)) {
																			{
																				{
																					double cv$temp$114$var187;
																					{
																						double var187 = memMean[traceTempVariable$s$247_1];
																						cv$temp$114$var187 = var187;
																					}
																					double cv$temp$115$var188;
																					{
																						double var188 = memVar[traceTempVariable$s$247_1];
																						cv$temp$115$var188 = var188;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$114$var187) / Math.sqrt(cv$temp$115$var188))) - (0.5 * Math.log(cv$temp$115$var188)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$114$var187) / Math.sqrt(cv$temp$115$var188))) - (0.5 * Math.log(cv$temp$115$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$114$var187) / Math.sqrt(cv$temp$115$var188))) - (0.5 * Math.log(cv$temp$115$var188))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$114$var187) / Math.sqrt(cv$temp$115$var188))) - (0.5 * Math.log(cv$temp$115$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$114$var187) / Math.sqrt(cv$temp$115$var188))) - (0.5 * Math.log(cv$temp$115$var188)))));
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
															for(int index$sample49$355 = 0; index$sample49$355 < noStates; index$sample49$355 += 1) {
																int distributionTempVariable$var44$357 = index$sample49$355;
																double cv$probabilitySample49Value356 = (1.0 * distribution$sample49[index$sample49$355]);
																int traceTempVariable$s$358_1 = distributionTempVariable$var44$357;
																if((0 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$358_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$358_1)) {
																					{
																						{
																							double cv$temp$116$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$358_1];
																								cv$temp$116$var187 = var187;
																							}
																							double cv$temp$117$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$358_1];
																								cv$temp$117$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample49Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$116$var187) / Math.sqrt(cv$temp$117$var188))) - (0.5 * Math.log(cv$temp$117$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$116$var187) / Math.sqrt(cv$temp$117$var188))) - (0.5 * Math.log(cv$temp$117$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$116$var187) / Math.sqrt(cv$temp$117$var188))) - (0.5 * Math.log(cv$temp$117$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$116$var187) / Math.sqrt(cv$temp$117$var188))) - (0.5 * Math.log(cv$temp$117$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value356) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$116$var187) / Math.sqrt(cv$temp$117$var188))) - (0.5 * Math.log(cv$temp$117$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value356);
																						}
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
													if((index$i$19 == i$var180)) {
														for(int var99 = 0; var99 < noStates; var99 += 1) {
															if((var99 == traceTempVariable$s$363_1)) {
																for(int var151 = 0; var151 < noStates; var151 += 1) {
																	if((var151 == traceTempVariable$s$363_1)) {
																		{
																			{
																				double cv$temp$118$var187;
																				{
																					double var187 = memMean[traceTempVariable$s$363_1];
																					cv$temp$118$var187 = var187;
																				}
																				double cv$temp$119$var188;
																				{
																					double var188 = memVar[traceTempVariable$s$363_1];
																					cv$temp$119$var188 = var188;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$118$var187) / Math.sqrt(cv$temp$119$var188))) - (0.5 * Math.log(cv$temp$119$var188)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$118$var187) / Math.sqrt(cv$temp$119$var188))) - (0.5 * Math.log(cv$temp$119$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$118$var187) / Math.sqrt(cv$temp$119$var188))) - (0.5 * Math.log(cv$temp$119$var188))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$118$var187) / Math.sqrt(cv$temp$119$var188))) - (0.5 * Math.log(cv$temp$119$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$118$var187) / Math.sqrt(cv$temp$119$var188))) - (0.5 * Math.log(cv$temp$119$var188)))));
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
															for(int index$sample67$365 = 0; index$sample67$365 < noStates; index$sample67$365 += 1) {
																int distributionTempVariable$var62$367 = index$sample67$365;
																double cv$probabilitySample67Value366 = (1.0 * distribution$sample67[((index$i$364 - 1) / 1)][index$sample67$365]);
																int traceTempVariable$s$368_1 = distributionTempVariable$var62$367;
																if((index$i$364 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$368_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$368_1)) {
																					{
																						{
																							double cv$temp$120$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$368_1];
																								cv$temp$120$var187 = var187;
																							}
																							double cv$temp$121$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$368_1];
																								cv$temp$121$var188 = var188;
																							}
																							if(((Math.log(cv$probabilitySample67Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$120$var187) / Math.sqrt(cv$temp$121$var188))) - (0.5 * Math.log(cv$temp$121$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$120$var187) / Math.sqrt(cv$temp$121$var188))) - (0.5 * Math.log(cv$temp$121$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$120$var187) / Math.sqrt(cv$temp$121$var188))) - (0.5 * Math.log(cv$temp$121$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$120$var187) / Math.sqrt(cv$temp$121$var188))) - (0.5 * Math.log(cv$temp$121$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value366) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$120$var187) / Math.sqrt(cv$temp$121$var188))) - (0.5 * Math.log(cv$temp$121$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value366);
																						}
																					}
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
								boolean[] guard$sample67gaussian199 = guard$sample67gaussian199$global;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
								}
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180))
										guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
								}
								int traceTempVariable$s$437_1 = cv$currentValue;
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var117 = 0; var117 < noStates; var117 += 1) {
														if((var117 == traceTempVariable$s$437_1)) {
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$437_1)) {
																			{
																				{
																					double cv$temp$146$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$437_1];
																						cv$temp$146$var192 = var192;
																					}
																					double cv$temp$147$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$437_1];
																						cv$temp$147$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$146$var192) / Math.sqrt(cv$temp$147$var193))) - (0.5 * Math.log(cv$temp$147$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$146$var192) / Math.sqrt(cv$temp$147$var193))) - (0.5 * Math.log(cv$temp$147$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$146$var192) / Math.sqrt(cv$temp$147$var193))) - (0.5 * Math.log(cv$temp$147$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$146$var192) / Math.sqrt(cv$temp$147$var193))) - (0.5 * Math.log(cv$temp$147$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$146$var192) / Math.sqrt(cv$temp$147$var193))) - (0.5 * Math.log(cv$temp$147$var193)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$482 = 0; index$sample49$482 < noStates; index$sample49$482 += 1) {
																		int distributionTempVariable$var44$484 = index$sample49$482;
																		double cv$probabilitySample49Value483 = (1.0 * distribution$sample49[index$sample49$482]);
																		int traceTempVariable$s$485_1 = distributionTempVariable$var44$484;
																		if((0 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$485_1)) {
																					{
																						{
																							double cv$temp$148$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$485_1];
																								cv$temp$148$var192 = var192;
																							}
																							double cv$temp$149$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$485_1];
																								cv$temp$149$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample49Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$148$var192) / Math.sqrt(cv$temp$149$var193))) - (0.5 * Math.log(cv$temp$149$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$148$var192) / Math.sqrt(cv$temp$149$var193))) - (0.5 * Math.log(cv$temp$149$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$148$var192) / Math.sqrt(cv$temp$149$var193))) - (0.5 * Math.log(cv$temp$149$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$148$var192) / Math.sqrt(cv$temp$149$var193))) - (0.5 * Math.log(cv$temp$149$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value483) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$148$var192) / Math.sqrt(cv$temp$149$var193))) - (0.5 * Math.log(cv$temp$149$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value483);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var117 = 0; var117 < noStates; var117 += 1) {
														if((var117 == traceTempVariable$s$437_1)) {
															int traceTempVariable$s$489_1 = cv$currentValue;
															if((index$i$19 == i$var180)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$489_1)) {
																		{
																			{
																				double cv$temp$150$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$489_1];
																					cv$temp$150$var192 = var192;
																				}
																				double cv$temp$151$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$489_1];
																					cv$temp$151$var193 = var193;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$150$var192) / Math.sqrt(cv$temp$151$var193))) - (0.5 * Math.log(cv$temp$151$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$150$var192) / Math.sqrt(cv$temp$151$var193))) - (0.5 * Math.log(cv$temp$151$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$150$var192) / Math.sqrt(cv$temp$151$var193))) - (0.5 * Math.log(cv$temp$151$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$150$var192) / Math.sqrt(cv$temp$151$var193))) - (0.5 * Math.log(cv$temp$151$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$150$var192) / Math.sqrt(cv$temp$151$var193))) - (0.5 * Math.log(cv$temp$151$var193)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$490 = 1; index$i$490 < samples; index$i$490 += 1) {
																if(!(index$i$490 == index$i$19)) {
																	for(int index$sample67$491 = 0; index$sample67$491 < noStates; index$sample67$491 += 1) {
																		int distributionTempVariable$var62$493 = index$sample67$491;
																		double cv$probabilitySample67Value492 = (1.0 * distribution$sample67[((index$i$490 - 1) / 1)][index$sample67$491]);
																		int traceTempVariable$s$494_1 = distributionTempVariable$var62$493;
																		if((index$i$490 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$494_1)) {
																					{
																						{
																							double cv$temp$152$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$494_1];
																								cv$temp$152$var192 = var192;
																							}
																							double cv$temp$153$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$494_1];
																								cv$temp$153$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$152$var192) / Math.sqrt(cv$temp$153$var193))) - (0.5 * Math.log(cv$temp$153$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$152$var192) / Math.sqrt(cv$temp$153$var193))) - (0.5 * Math.log(cv$temp$153$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$152$var192) / Math.sqrt(cv$temp$153$var193))) - (0.5 * Math.log(cv$temp$153$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$152$var192) / Math.sqrt(cv$temp$153$var193))) - (0.5 * Math.log(cv$temp$153$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value492) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$152$var192) / Math.sqrt(cv$temp$153$var193))) - (0.5 * Math.log(cv$temp$153$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value492);
																						}
																					}
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
								for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
									if((i$var56 == i$var180)) {
										if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
											guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var117 = 0; var117 < noStates; var117 += 1) {
																if((var117 == traceTempVariable$s$441_1)) {
																	for(int var168 = 0; var168 < noStates; var168 += 1) {
																		if((var168 == traceTempVariable$s$441_1)) {
																			{
																				{
																					double cv$temp$178$var192;
																					{
																						double var192 = pageFaultsMean[traceTempVariable$s$441_1];
																						cv$temp$178$var192 = var192;
																					}
																					double cv$temp$179$var193;
																					{
																						double var193 = pageFaultsVar[traceTempVariable$s$441_1];
																						cv$temp$179$var193 = var193;
																					}
																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$178$var192) / Math.sqrt(cv$temp$179$var193))) - (0.5 * Math.log(cv$temp$179$var193)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$178$var192) / Math.sqrt(cv$temp$179$var193))) - (0.5 * Math.log(cv$temp$179$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$178$var192) / Math.sqrt(cv$temp$179$var193))) - (0.5 * Math.log(cv$temp$179$var193))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$178$var192) / Math.sqrt(cv$temp$179$var193))) - (0.5 * Math.log(cv$temp$179$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$178$var192) / Math.sqrt(cv$temp$179$var193))) - (0.5 * Math.log(cv$temp$179$var193)))));
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
															for(int index$sample49$549 = 0; index$sample49$549 < noStates; index$sample49$549 += 1) {
																int distributionTempVariable$var44$551 = index$sample49$549;
																double cv$probabilitySample49Value550 = (1.0 * distribution$sample49[index$sample49$549]);
																int traceTempVariable$s$552_1 = distributionTempVariable$var44$551;
																if((0 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$552_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$552_1)) {
																					{
																						{
																							double cv$temp$180$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$552_1];
																								cv$temp$180$var192 = var192;
																							}
																							double cv$temp$181$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$552_1];
																								cv$temp$181$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample49Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$180$var192) / Math.sqrt(cv$temp$181$var193))) - (0.5 * Math.log(cv$temp$181$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$180$var192) / Math.sqrt(cv$temp$181$var193))) - (0.5 * Math.log(cv$temp$181$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$180$var192) / Math.sqrt(cv$temp$181$var193))) - (0.5 * Math.log(cv$temp$181$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$180$var192) / Math.sqrt(cv$temp$181$var193))) - (0.5 * Math.log(cv$temp$181$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value550) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$180$var192) / Math.sqrt(cv$temp$181$var193))) - (0.5 * Math.log(cv$temp$181$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value550);
																						}
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
													if((index$i$19 == i$var180)) {
														for(int var117 = 0; var117 < noStates; var117 += 1) {
															if((var117 == traceTempVariable$s$557_1)) {
																for(int var168 = 0; var168 < noStates; var168 += 1) {
																	if((var168 == traceTempVariable$s$557_1)) {
																		{
																			{
																				double cv$temp$182$var192;
																				{
																					double var192 = pageFaultsMean[traceTempVariable$s$557_1];
																					cv$temp$182$var192 = var192;
																				}
																				double cv$temp$183$var193;
																				{
																					double var193 = pageFaultsVar[traceTempVariable$s$557_1];
																					cv$temp$183$var193 = var193;
																				}
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$182$var192) / Math.sqrt(cv$temp$183$var193))) - (0.5 * Math.log(cv$temp$183$var193)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$182$var192) / Math.sqrt(cv$temp$183$var193))) - (0.5 * Math.log(cv$temp$183$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$182$var192) / Math.sqrt(cv$temp$183$var193))) - (0.5 * Math.log(cv$temp$183$var193))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$182$var192) / Math.sqrt(cv$temp$183$var193))) - (0.5 * Math.log(cv$temp$183$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$182$var192) / Math.sqrt(cv$temp$183$var193))) - (0.5 * Math.log(cv$temp$183$var193)))));
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
															for(int index$sample67$559 = 0; index$sample67$559 < noStates; index$sample67$559 += 1) {
																int distributionTempVariable$var62$561 = index$sample67$559;
																double cv$probabilitySample67Value560 = (1.0 * distribution$sample67[((index$i$558 - 1) / 1)][index$sample67$559]);
																int traceTempVariable$s$562_1 = distributionTempVariable$var62$561;
																if((index$i$558 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$562_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$562_1)) {
																					{
																						{
																							double cv$temp$184$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$562_1];
																								cv$temp$184$var192 = var192;
																							}
																							double cv$temp$185$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$562_1];
																								cv$temp$185$var193 = var193;
																							}
																							if(((Math.log(cv$probabilitySample67Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$184$var192) / Math.sqrt(cv$temp$185$var193))) - (0.5 * Math.log(cv$temp$185$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$184$var192) / Math.sqrt(cv$temp$185$var193))) - (0.5 * Math.log(cv$temp$185$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$184$var192) / Math.sqrt(cv$temp$185$var193))) - (0.5 * Math.log(cv$temp$185$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$184$var192) / Math.sqrt(cv$temp$185$var193))) - (0.5 * Math.log(cv$temp$185$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value560) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$184$var192) / Math.sqrt(cv$temp$185$var193))) - (0.5 * Math.log(cv$temp$185$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value560);
																						}
																					}
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
					for(int index$sample67$29 = 0; index$sample67$29 < noStates; index$sample67$29 += 1) {
						int distributionTempVariable$var62$31 = index$sample67$29;
						double cv$probabilitySample67Value30 = (1.0 * distribution$sample67[((index$i$28 - 1) / 1)][index$sample67$29]);
						int traceTempVariable$var59$32_1 = distributionTempVariable$var62$31;
						if((index$i$28 == (i$var56 - 1))) {
							for(int var35 = 0; var35 < noStates; var35 += 1) {
								if((var35 == traceTempVariable$var59$32_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample67Value30);
									double[] cv$temp$3$var60;
									{
										double[] var60 = m[traceTempVariable$var59$32_1];
										cv$temp$3$var60 = var60;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample67Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var60.length))?Math.log(cv$temp$3$var60[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var59$38_1 = cv$currentValue;
										}
									}
									{
										{
											boolean[] guard$sample67gaussian189 = guard$sample67gaussian189$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian189[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$50_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$50_1)) {
																		if(fixedFlag$sample49) {
																			if((0 == i$var180)) {
																				for(int var134 = 0; var134 < noStates; var134 += 1) {
																					if((var134 == traceTempVariable$s$50_1)) {
																						{
																							{
																								double cv$temp$26$var182;
																								{
																									double var182 = cpuMean[traceTempVariable$s$50_1];
																									cv$temp$26$var182 = var182;
																								}
																								double cv$temp$27$var183;
																								{
																									double var183 = cpuVar[traceTempVariable$s$50_1];
																									cv$temp$27$var183 = var183;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample49$111 = 0; index$sample49$111 < noStates; index$sample49$111 += 1) {
																					int distributionTempVariable$var44$113 = index$sample49$111;
																					double cv$probabilitySample49Value112 = (1.0 * distribution$sample49[index$sample49$111]);
																					int traceTempVariable$s$114_1 = distributionTempVariable$var44$113;
																					if((0 == i$var180)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$114_1)) {
																								{
																									{
																										double cv$temp$28$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$114_1];
																											cv$temp$28$var182 = var182;
																										}
																										double cv$temp$29$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$114_1];
																											cv$temp$29$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample49Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value112) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value112);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var81 = 0; var81 < noStates; var81 += 1) {
																	if((var81 == traceTempVariable$s$50_1)) {
																		int traceTempVariable$s$118_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$118_1)) {
																					{
																						{
																							double cv$temp$30$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$118_1];
																								cv$temp$30$var182 = var182;
																							}
																							double cv$temp$31$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$118_1];
																								cv$temp$31$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$30$var182) / Math.sqrt(cv$temp$31$var183))) - (0.5 * Math.log(cv$temp$31$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$30$var182) / Math.sqrt(cv$temp$31$var183))) - (0.5 * Math.log(cv$temp$31$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$30$var182) / Math.sqrt(cv$temp$31$var183))) - (0.5 * Math.log(cv$temp$31$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$30$var182) / Math.sqrt(cv$temp$31$var183))) - (0.5 * Math.log(cv$temp$31$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$30$var182) / Math.sqrt(cv$temp$31$var183))) - (0.5 * Math.log(cv$temp$31$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$119_1 = distributionTempVariable$var62$31;
																		if((index$i$28 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$119_1)) {
																					{
																						{
																							double cv$temp$32$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$119_1];
																								cv$temp$32$var182 = var182;
																							}
																							double cv$temp$33$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$119_1];
																								cv$temp$33$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$32$var182) / Math.sqrt(cv$temp$33$var183))) - (0.5 * Math.log(cv$temp$33$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$32$var182) / Math.sqrt(cv$temp$33$var183))) - (0.5 * Math.log(cv$temp$33$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$32$var182) / Math.sqrt(cv$temp$33$var183))) - (0.5 * Math.log(cv$temp$33$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$32$var182) / Math.sqrt(cv$temp$33$var183))) - (0.5 * Math.log(cv$temp$33$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$32$var182) / Math.sqrt(cv$temp$33$var183))) - (0.5 * Math.log(cv$temp$33$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$120 = 1; index$i$120 < samples; index$i$120 += 1) {
																			if((!(index$i$120 == index$i$19) && !(index$i$120 == index$i$28))) {
																				for(int index$sample67$121 = 0; index$sample67$121 < noStates; index$sample67$121 += 1) {
																					int distributionTempVariable$var62$123 = index$sample67$121;
																					double cv$probabilitySample67Value122 = (1.0 * distribution$sample67[((index$i$120 - 1) / 1)][index$sample67$121]);
																					int traceTempVariable$s$124_1 = distributionTempVariable$var62$123;
																					if((index$i$120 == i$var180)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$124_1)) {
																								{
																									{
																										double cv$temp$34$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$124_1];
																											cv$temp$34$var182 = var182;
																										}
																										double cv$temp$35$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$124_1];
																											cv$temp$35$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample67Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$34$var182) / Math.sqrt(cv$temp$35$var183))) - (0.5 * Math.log(cv$temp$35$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$34$var182) / Math.sqrt(cv$temp$35$var183))) - (0.5 * Math.log(cv$temp$35$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$34$var182) / Math.sqrt(cv$temp$35$var183))) - (0.5 * Math.log(cv$temp$35$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$34$var182) / Math.sqrt(cv$temp$35$var183))) - (0.5 * Math.log(cv$temp$35$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value122) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$34$var182) / Math.sqrt(cv$temp$35$var183))) - (0.5 * Math.log(cv$temp$35$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value122);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian189[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian189[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample49) {
																	if((0 == i$var180)) {
																		for(int var81 = 0; var81 < noStates; var81 += 1) {
																			if((var81 == traceTempVariable$s$54_1)) {
																				for(int var134 = 0; var134 < noStates; var134 += 1) {
																					if((var134 == traceTempVariable$s$54_1)) {
																						{
																							{
																								double cv$temp$58$var182;
																								{
																									double var182 = cpuMean[traceTempVariable$s$54_1];
																									cv$temp$58$var182 = var182;
																								}
																								double cv$temp$59$var183;
																								{
																									double var183 = cpuVar[traceTempVariable$s$54_1];
																									cv$temp$59$var183 = var183;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$58$var182) / Math.sqrt(cv$temp$59$var183))) - (0.5 * Math.log(cv$temp$59$var183)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$58$var182) / Math.sqrt(cv$temp$59$var183))) - (0.5 * Math.log(cv$temp$59$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$58$var182) / Math.sqrt(cv$temp$59$var183))) - (0.5 * Math.log(cv$temp$59$var183))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$58$var182) / Math.sqrt(cv$temp$59$var183))) - (0.5 * Math.log(cv$temp$59$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$58$var182) / Math.sqrt(cv$temp$59$var183))) - (0.5 * Math.log(cv$temp$59$var183)))));
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
																		for(int index$sample49$180 = 0; index$sample49$180 < noStates; index$sample49$180 += 1) {
																			int distributionTempVariable$var44$182 = index$sample49$180;
																			double cv$probabilitySample49Value181 = (1.0 * distribution$sample49[index$sample49$180]);
																			int traceTempVariable$s$183_1 = distributionTempVariable$var44$182;
																			if((0 == i$var180)) {
																				for(int var81 = 0; var81 < noStates; var81 += 1) {
																					if((var81 == traceTempVariable$s$183_1)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$183_1)) {
																								{
																									{
																										double cv$temp$60$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$183_1];
																											cv$temp$60$var182 = var182;
																										}
																										double cv$temp$61$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$183_1];
																											cv$temp$61$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample49Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$60$var182) / Math.sqrt(cv$temp$61$var183))) - (0.5 * Math.log(cv$temp$61$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$60$var182) / Math.sqrt(cv$temp$61$var183))) - (0.5 * Math.log(cv$temp$61$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$60$var182) / Math.sqrt(cv$temp$61$var183))) - (0.5 * Math.log(cv$temp$61$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$60$var182) / Math.sqrt(cv$temp$61$var183))) - (0.5 * Math.log(cv$temp$61$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value181) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$60$var182) / Math.sqrt(cv$temp$61$var183))) - (0.5 * Math.log(cv$temp$61$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value181);
																									}
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
																if((index$i$19 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$188_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$188_1)) {
																					{
																						{
																							double cv$temp$62$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$188_1];
																								cv$temp$62$var182 = var182;
																							}
																							double cv$temp$63$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$188_1];
																								cv$temp$63$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$62$var182) / Math.sqrt(cv$temp$63$var183))) - (0.5 * Math.log(cv$temp$63$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$62$var182) / Math.sqrt(cv$temp$63$var183))) - (0.5 * Math.log(cv$temp$63$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$62$var182) / Math.sqrt(cv$temp$63$var183))) - (0.5 * Math.log(cv$temp$63$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$62$var182) / Math.sqrt(cv$temp$63$var183))) - (0.5 * Math.log(cv$temp$63$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$62$var182) / Math.sqrt(cv$temp$63$var183))) - (0.5 * Math.log(cv$temp$63$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$189_1 = distributionTempVariable$var62$31;
																if((index$i$28 == i$var180)) {
																	for(int var81 = 0; var81 < noStates; var81 += 1) {
																		if((var81 == traceTempVariable$s$189_1)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$189_1)) {
																					{
																						{
																							double cv$temp$64$var182;
																							{
																								double var182 = cpuMean[traceTempVariable$s$189_1];
																								cv$temp$64$var182 = var182;
																							}
																							double cv$temp$65$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$189_1];
																								cv$temp$65$var183 = var183;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$64$var182) / Math.sqrt(cv$temp$65$var183))) - (0.5 * Math.log(cv$temp$65$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$64$var182) / Math.sqrt(cv$temp$65$var183))) - (0.5 * Math.log(cv$temp$65$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$64$var182) / Math.sqrt(cv$temp$65$var183))) - (0.5 * Math.log(cv$temp$65$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$64$var182) / Math.sqrt(cv$temp$65$var183))) - (0.5 * Math.log(cv$temp$65$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$64$var182) / Math.sqrt(cv$temp$65$var183))) - (0.5 * Math.log(cv$temp$65$var183)))));
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
																		for(int index$sample67$191 = 0; index$sample67$191 < noStates; index$sample67$191 += 1) {
																			int distributionTempVariable$var62$193 = index$sample67$191;
																			double cv$probabilitySample67Value192 = (1.0 * distribution$sample67[((index$i$190 - 1) / 1)][index$sample67$191]);
																			int traceTempVariable$s$194_1 = distributionTempVariable$var62$193;
																			if((index$i$190 == i$var180)) {
																				for(int var81 = 0; var81 < noStates; var81 += 1) {
																					if((var81 == traceTempVariable$s$194_1)) {
																						for(int var134 = 0; var134 < noStates; var134 += 1) {
																							if((var134 == traceTempVariable$s$194_1)) {
																								{
																									{
																										double cv$temp$66$var182;
																										{
																											double var182 = cpuMean[traceTempVariable$s$194_1];
																											cv$temp$66$var182 = var182;
																										}
																										double cv$temp$67$var183;
																										{
																											double var183 = cpuVar[traceTempVariable$s$194_1];
																											cv$temp$67$var183 = var183;
																										}
																										if(((Math.log(cv$probabilitySample67Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$66$var182) / Math.sqrt(cv$temp$67$var183))) - (0.5 * Math.log(cv$temp$67$var183)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$66$var182) / Math.sqrt(cv$temp$67$var183))) - (0.5 * Math.log(cv$temp$67$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$66$var182) / Math.sqrt(cv$temp$67$var183))) - (0.5 * Math.log(cv$temp$67$var183))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$66$var182) / Math.sqrt(cv$temp$67$var183))) - (0.5 * Math.log(cv$temp$67$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value192) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$66$var182) / Math.sqrt(cv$temp$67$var183))) - (0.5 * Math.log(cv$temp$67$var183)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value192);
																									}
																								}
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
											boolean[] guard$sample67gaussian194 = guard$sample67gaussian194$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian194[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$244_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$244_1)) {
																		if(fixedFlag$sample49) {
																			if((0 == i$var180)) {
																				for(int var151 = 0; var151 < noStates; var151 += 1) {
																					if((var151 == traceTempVariable$s$244_1)) {
																						{
																							{
																								double cv$temp$90$var187;
																								{
																									double var187 = memMean[traceTempVariable$s$244_1];
																									cv$temp$90$var187 = var187;
																								}
																								double cv$temp$91$var188;
																								{
																									double var188 = memVar[traceTempVariable$s$244_1];
																									cv$temp$91$var188 = var188;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$90$var187) / Math.sqrt(cv$temp$91$var188))) - (0.5 * Math.log(cv$temp$91$var188)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$90$var187) / Math.sqrt(cv$temp$91$var188))) - (0.5 * Math.log(cv$temp$91$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$90$var187) / Math.sqrt(cv$temp$91$var188))) - (0.5 * Math.log(cv$temp$91$var188))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$90$var187) / Math.sqrt(cv$temp$91$var188))) - (0.5 * Math.log(cv$temp$91$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$90$var187) / Math.sqrt(cv$temp$91$var188))) - (0.5 * Math.log(cv$temp$91$var188)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample49$305 = 0; index$sample49$305 < noStates; index$sample49$305 += 1) {
																					int distributionTempVariable$var44$307 = index$sample49$305;
																					double cv$probabilitySample49Value306 = (1.0 * distribution$sample49[index$sample49$305]);
																					int traceTempVariable$s$308_1 = distributionTempVariable$var44$307;
																					if((0 == i$var180)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$308_1)) {
																								{
																									{
																										double cv$temp$92$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$308_1];
																											cv$temp$92$var187 = var187;
																										}
																										double cv$temp$93$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$308_1];
																											cv$temp$93$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample49Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$92$var187) / Math.sqrt(cv$temp$93$var188))) - (0.5 * Math.log(cv$temp$93$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$92$var187) / Math.sqrt(cv$temp$93$var188))) - (0.5 * Math.log(cv$temp$93$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$92$var187) / Math.sqrt(cv$temp$93$var188))) - (0.5 * Math.log(cv$temp$93$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$92$var187) / Math.sqrt(cv$temp$93$var188))) - (0.5 * Math.log(cv$temp$93$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value306) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$92$var187) / Math.sqrt(cv$temp$93$var188))) - (0.5 * Math.log(cv$temp$93$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value306);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var99 = 0; var99 < noStates; var99 += 1) {
																	if((var99 == traceTempVariable$s$244_1)) {
																		int traceTempVariable$s$312_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$312_1)) {
																					{
																						{
																							double cv$temp$94$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$312_1];
																								cv$temp$94$var187 = var187;
																							}
																							double cv$temp$95$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$312_1];
																								cv$temp$95$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$94$var187) / Math.sqrt(cv$temp$95$var188))) - (0.5 * Math.log(cv$temp$95$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$94$var187) / Math.sqrt(cv$temp$95$var188))) - (0.5 * Math.log(cv$temp$95$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$94$var187) / Math.sqrt(cv$temp$95$var188))) - (0.5 * Math.log(cv$temp$95$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$94$var187) / Math.sqrt(cv$temp$95$var188))) - (0.5 * Math.log(cv$temp$95$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$94$var187) / Math.sqrt(cv$temp$95$var188))) - (0.5 * Math.log(cv$temp$95$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$313_1 = distributionTempVariable$var62$31;
																		if((index$i$28 == i$var180)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$313_1)) {
																					{
																						{
																							double cv$temp$96$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$313_1];
																								cv$temp$96$var187 = var187;
																							}
																							double cv$temp$97$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$313_1];
																								cv$temp$97$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$96$var187) / Math.sqrt(cv$temp$97$var188))) - (0.5 * Math.log(cv$temp$97$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$96$var187) / Math.sqrt(cv$temp$97$var188))) - (0.5 * Math.log(cv$temp$97$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$96$var187) / Math.sqrt(cv$temp$97$var188))) - (0.5 * Math.log(cv$temp$97$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$96$var187) / Math.sqrt(cv$temp$97$var188))) - (0.5 * Math.log(cv$temp$97$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$96$var187) / Math.sqrt(cv$temp$97$var188))) - (0.5 * Math.log(cv$temp$97$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$314 = 1; index$i$314 < samples; index$i$314 += 1) {
																			if((!(index$i$314 == index$i$19) && !(index$i$314 == index$i$28))) {
																				for(int index$sample67$315 = 0; index$sample67$315 < noStates; index$sample67$315 += 1) {
																					int distributionTempVariable$var62$317 = index$sample67$315;
																					double cv$probabilitySample67Value316 = (1.0 * distribution$sample67[((index$i$314 - 1) / 1)][index$sample67$315]);
																					int traceTempVariable$s$318_1 = distributionTempVariable$var62$317;
																					if((index$i$314 == i$var180)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$318_1)) {
																								{
																									{
																										double cv$temp$98$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$318_1];
																											cv$temp$98$var187 = var187;
																										}
																										double cv$temp$99$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$318_1];
																											cv$temp$99$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample67Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$98$var187) / Math.sqrt(cv$temp$99$var188))) - (0.5 * Math.log(cv$temp$99$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$98$var187) / Math.sqrt(cv$temp$99$var188))) - (0.5 * Math.log(cv$temp$99$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$98$var187) / Math.sqrt(cv$temp$99$var188))) - (0.5 * Math.log(cv$temp$99$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$98$var187) / Math.sqrt(cv$temp$99$var188))) - (0.5 * Math.log(cv$temp$99$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value316) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$98$var187) / Math.sqrt(cv$temp$99$var188))) - (0.5 * Math.log(cv$temp$99$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value316);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian194[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian194[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample49) {
																	if((0 == i$var180)) {
																		for(int var99 = 0; var99 < noStates; var99 += 1) {
																			if((var99 == traceTempVariable$s$248_1)) {
																				for(int var151 = 0; var151 < noStates; var151 += 1) {
																					if((var151 == traceTempVariable$s$248_1)) {
																						{
																							{
																								double cv$temp$122$var187;
																								{
																									double var187 = memMean[traceTempVariable$s$248_1];
																									cv$temp$122$var187 = var187;
																								}
																								double cv$temp$123$var188;
																								{
																									double var188 = memVar[traceTempVariable$s$248_1];
																									cv$temp$123$var188 = var188;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$122$var187) / Math.sqrt(cv$temp$123$var188))) - (0.5 * Math.log(cv$temp$123$var188)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$122$var187) / Math.sqrt(cv$temp$123$var188))) - (0.5 * Math.log(cv$temp$123$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$122$var187) / Math.sqrt(cv$temp$123$var188))) - (0.5 * Math.log(cv$temp$123$var188))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$122$var187) / Math.sqrt(cv$temp$123$var188))) - (0.5 * Math.log(cv$temp$123$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$122$var187) / Math.sqrt(cv$temp$123$var188))) - (0.5 * Math.log(cv$temp$123$var188)))));
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
																		for(int index$sample49$374 = 0; index$sample49$374 < noStates; index$sample49$374 += 1) {
																			int distributionTempVariable$var44$376 = index$sample49$374;
																			double cv$probabilitySample49Value375 = (1.0 * distribution$sample49[index$sample49$374]);
																			int traceTempVariable$s$377_1 = distributionTempVariable$var44$376;
																			if((0 == i$var180)) {
																				for(int var99 = 0; var99 < noStates; var99 += 1) {
																					if((var99 == traceTempVariable$s$377_1)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$377_1)) {
																								{
																									{
																										double cv$temp$124$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$377_1];
																											cv$temp$124$var187 = var187;
																										}
																										double cv$temp$125$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$377_1];
																											cv$temp$125$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample49Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$124$var187) / Math.sqrt(cv$temp$125$var188))) - (0.5 * Math.log(cv$temp$125$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$124$var187) / Math.sqrt(cv$temp$125$var188))) - (0.5 * Math.log(cv$temp$125$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$124$var187) / Math.sqrt(cv$temp$125$var188))) - (0.5 * Math.log(cv$temp$125$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$124$var187) / Math.sqrt(cv$temp$125$var188))) - (0.5 * Math.log(cv$temp$125$var188)))))) + 1)) + (Math.log(cv$probabilitySample49Value375) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$124$var187) / Math.sqrt(cv$temp$125$var188))) - (0.5 * Math.log(cv$temp$125$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value375);
																									}
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
																if((index$i$19 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$382_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$382_1)) {
																					{
																						{
																							double cv$temp$126$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$382_1];
																								cv$temp$126$var187 = var187;
																							}
																							double cv$temp$127$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$382_1];
																								cv$temp$127$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$126$var187) / Math.sqrt(cv$temp$127$var188))) - (0.5 * Math.log(cv$temp$127$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$126$var187) / Math.sqrt(cv$temp$127$var188))) - (0.5 * Math.log(cv$temp$127$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$126$var187) / Math.sqrt(cv$temp$127$var188))) - (0.5 * Math.log(cv$temp$127$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$126$var187) / Math.sqrt(cv$temp$127$var188))) - (0.5 * Math.log(cv$temp$127$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$126$var187) / Math.sqrt(cv$temp$127$var188))) - (0.5 * Math.log(cv$temp$127$var188)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$383_1 = distributionTempVariable$var62$31;
																if((index$i$28 == i$var180)) {
																	for(int var99 = 0; var99 < noStates; var99 += 1) {
																		if((var99 == traceTempVariable$s$383_1)) {
																			for(int var151 = 0; var151 < noStates; var151 += 1) {
																				if((var151 == traceTempVariable$s$383_1)) {
																					{
																						{
																							double cv$temp$128$var187;
																							{
																								double var187 = memMean[traceTempVariable$s$383_1];
																								cv$temp$128$var187 = var187;
																							}
																							double cv$temp$129$var188;
																							{
																								double var188 = memVar[traceTempVariable$s$383_1];
																								cv$temp$129$var188 = var188;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$128$var187) / Math.sqrt(cv$temp$129$var188))) - (0.5 * Math.log(cv$temp$129$var188)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$128$var187) / Math.sqrt(cv$temp$129$var188))) - (0.5 * Math.log(cv$temp$129$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$128$var187) / Math.sqrt(cv$temp$129$var188))) - (0.5 * Math.log(cv$temp$129$var188))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$128$var187) / Math.sqrt(cv$temp$129$var188))) - (0.5 * Math.log(cv$temp$129$var188)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$128$var187) / Math.sqrt(cv$temp$129$var188))) - (0.5 * Math.log(cv$temp$129$var188)))));
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
																		for(int index$sample67$385 = 0; index$sample67$385 < noStates; index$sample67$385 += 1) {
																			int distributionTempVariable$var62$387 = index$sample67$385;
																			double cv$probabilitySample67Value386 = (1.0 * distribution$sample67[((index$i$384 - 1) / 1)][index$sample67$385]);
																			int traceTempVariable$s$388_1 = distributionTempVariable$var62$387;
																			if((index$i$384 == i$var180)) {
																				for(int var99 = 0; var99 < noStates; var99 += 1) {
																					if((var99 == traceTempVariable$s$388_1)) {
																						for(int var151 = 0; var151 < noStates; var151 += 1) {
																							if((var151 == traceTempVariable$s$388_1)) {
																								{
																									{
																										double cv$temp$130$var187;
																										{
																											double var187 = memMean[traceTempVariable$s$388_1];
																											cv$temp$130$var187 = var187;
																										}
																										double cv$temp$131$var188;
																										{
																											double var188 = memVar[traceTempVariable$s$388_1];
																											cv$temp$131$var188 = var188;
																										}
																										if(((Math.log(cv$probabilitySample67Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$130$var187) / Math.sqrt(cv$temp$131$var188))) - (0.5 * Math.log(cv$temp$131$var188)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$130$var187) / Math.sqrt(cv$temp$131$var188))) - (0.5 * Math.log(cv$temp$131$var188)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$130$var187) / Math.sqrt(cv$temp$131$var188))) - (0.5 * Math.log(cv$temp$131$var188))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$130$var187) / Math.sqrt(cv$temp$131$var188))) - (0.5 * Math.log(cv$temp$131$var188)))))) + 1)) + (Math.log(cv$probabilitySample67Value386) + (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$temp$130$var187) / Math.sqrt(cv$temp$131$var188))) - (0.5 * Math.log(cv$temp$131$var188)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value386);
																									}
																								}
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
											boolean[] guard$sample67gaussian199 = guard$sample67gaussian199$global;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
											}
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180))
													guard$sample67gaussian199[((i$var180 - 0) / 1)] = false;
											}
											int traceTempVariable$s$438_1 = cv$currentValue;
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$438_1)) {
																		if(fixedFlag$sample49) {
																			if((0 == i$var180)) {
																				for(int var168 = 0; var168 < noStates; var168 += 1) {
																					if((var168 == traceTempVariable$s$438_1)) {
																						{
																							{
																								double cv$temp$154$var192;
																								{
																									double var192 = pageFaultsMean[traceTempVariable$s$438_1];
																									cv$temp$154$var192 = var192;
																								}
																								double cv$temp$155$var193;
																								{
																									double var193 = pageFaultsVar[traceTempVariable$s$438_1];
																									cv$temp$155$var193 = var193;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$154$var192) / Math.sqrt(cv$temp$155$var193))) - (0.5 * Math.log(cv$temp$155$var193)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$154$var192) / Math.sqrt(cv$temp$155$var193))) - (0.5 * Math.log(cv$temp$155$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$154$var192) / Math.sqrt(cv$temp$155$var193))) - (0.5 * Math.log(cv$temp$155$var193))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$154$var192) / Math.sqrt(cv$temp$155$var193))) - (0.5 * Math.log(cv$temp$155$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$154$var192) / Math.sqrt(cv$temp$155$var193))) - (0.5 * Math.log(cv$temp$155$var193)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample49$499 = 0; index$sample49$499 < noStates; index$sample49$499 += 1) {
																					int distributionTempVariable$var44$501 = index$sample49$499;
																					double cv$probabilitySample49Value500 = (1.0 * distribution$sample49[index$sample49$499]);
																					int traceTempVariable$s$502_1 = distributionTempVariable$var44$501;
																					if((0 == i$var180)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$502_1)) {
																								{
																									{
																										double cv$temp$156$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$502_1];
																											cv$temp$156$var192 = var192;
																										}
																										double cv$temp$157$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$502_1];
																											cv$temp$157$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample49Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$156$var192) / Math.sqrt(cv$temp$157$var193))) - (0.5 * Math.log(cv$temp$157$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$156$var192) / Math.sqrt(cv$temp$157$var193))) - (0.5 * Math.log(cv$temp$157$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$156$var192) / Math.sqrt(cv$temp$157$var193))) - (0.5 * Math.log(cv$temp$157$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$156$var192) / Math.sqrt(cv$temp$157$var193))) - (0.5 * Math.log(cv$temp$157$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value500) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$156$var192) / Math.sqrt(cv$temp$157$var193))) - (0.5 * Math.log(cv$temp$157$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value500);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var117 = 0; var117 < noStates; var117 += 1) {
																	if((var117 == traceTempVariable$s$438_1)) {
																		int traceTempVariable$s$506_1 = cv$currentValue;
																		if((index$i$19 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$506_1)) {
																					{
																						{
																							double cv$temp$158$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$506_1];
																								cv$temp$158$var192 = var192;
																							}
																							double cv$temp$159$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$506_1];
																								cv$temp$159$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$158$var192) / Math.sqrt(cv$temp$159$var193))) - (0.5 * Math.log(cv$temp$159$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$158$var192) / Math.sqrt(cv$temp$159$var193))) - (0.5 * Math.log(cv$temp$159$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$158$var192) / Math.sqrt(cv$temp$159$var193))) - (0.5 * Math.log(cv$temp$159$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$158$var192) / Math.sqrt(cv$temp$159$var193))) - (0.5 * Math.log(cv$temp$159$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$158$var192) / Math.sqrt(cv$temp$159$var193))) - (0.5 * Math.log(cv$temp$159$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$s$507_1 = distributionTempVariable$var62$31;
																		if((index$i$28 == i$var180)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$507_1)) {
																					{
																						{
																							double cv$temp$160$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$507_1];
																								cv$temp$160$var192 = var192;
																							}
																							double cv$temp$161$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$507_1];
																								cv$temp$161$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$160$var192) / Math.sqrt(cv$temp$161$var193))) - (0.5 * Math.log(cv$temp$161$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$160$var192) / Math.sqrt(cv$temp$161$var193))) - (0.5 * Math.log(cv$temp$161$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$160$var192) / Math.sqrt(cv$temp$161$var193))) - (0.5 * Math.log(cv$temp$161$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$160$var192) / Math.sqrt(cv$temp$161$var193))) - (0.5 * Math.log(cv$temp$161$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$160$var192) / Math.sqrt(cv$temp$161$var193))) - (0.5 * Math.log(cv$temp$161$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$508 = 1; index$i$508 < samples; index$i$508 += 1) {
																			if((!(index$i$508 == index$i$19) && !(index$i$508 == index$i$28))) {
																				for(int index$sample67$509 = 0; index$sample67$509 < noStates; index$sample67$509 += 1) {
																					int distributionTempVariable$var62$511 = index$sample67$509;
																					double cv$probabilitySample67Value510 = (1.0 * distribution$sample67[((index$i$508 - 1) / 1)][index$sample67$509]);
																					int traceTempVariable$s$512_1 = distributionTempVariable$var62$511;
																					if((index$i$508 == i$var180)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$512_1)) {
																								{
																									{
																										double cv$temp$162$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$512_1];
																											cv$temp$162$var192 = var192;
																										}
																										double cv$temp$163$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$512_1];
																											cv$temp$163$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample67Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$162$var192) / Math.sqrt(cv$temp$163$var193))) - (0.5 * Math.log(cv$temp$163$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$162$var192) / Math.sqrt(cv$temp$163$var193))) - (0.5 * Math.log(cv$temp$163$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$162$var192) / Math.sqrt(cv$temp$163$var193))) - (0.5 * Math.log(cv$temp$163$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$162$var192) / Math.sqrt(cv$temp$163$var193))) - (0.5 * Math.log(cv$temp$163$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value510) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$162$var192) / Math.sqrt(cv$temp$163$var193))) - (0.5 * Math.log(cv$temp$163$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value510);
																									}
																								}
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
											for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
												if((i$var56 == i$var180)) {
													if(!guard$sample67gaussian199[((i$var180 - 0) / 1)]) {
														guard$sample67gaussian199[((i$var180 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample49) {
																	if((0 == i$var180)) {
																		for(int var117 = 0; var117 < noStates; var117 += 1) {
																			if((var117 == traceTempVariable$s$442_1)) {
																				for(int var168 = 0; var168 < noStates; var168 += 1) {
																					if((var168 == traceTempVariable$s$442_1)) {
																						{
																							{
																								double cv$temp$186$var192;
																								{
																									double var192 = pageFaultsMean[traceTempVariable$s$442_1];
																									cv$temp$186$var192 = var192;
																								}
																								double cv$temp$187$var193;
																								{
																									double var193 = pageFaultsVar[traceTempVariable$s$442_1];
																									cv$temp$187$var193 = var193;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$186$var192) / Math.sqrt(cv$temp$187$var193))) - (0.5 * Math.log(cv$temp$187$var193)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$186$var192) / Math.sqrt(cv$temp$187$var193))) - (0.5 * Math.log(cv$temp$187$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$186$var192) / Math.sqrt(cv$temp$187$var193))) - (0.5 * Math.log(cv$temp$187$var193))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$186$var192) / Math.sqrt(cv$temp$187$var193))) - (0.5 * Math.log(cv$temp$187$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$186$var192) / Math.sqrt(cv$temp$187$var193))) - (0.5 * Math.log(cv$temp$187$var193)))));
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
																		for(int index$sample49$568 = 0; index$sample49$568 < noStates; index$sample49$568 += 1) {
																			int distributionTempVariable$var44$570 = index$sample49$568;
																			double cv$probabilitySample49Value569 = (1.0 * distribution$sample49[index$sample49$568]);
																			int traceTempVariable$s$571_1 = distributionTempVariable$var44$570;
																			if((0 == i$var180)) {
																				for(int var117 = 0; var117 < noStates; var117 += 1) {
																					if((var117 == traceTempVariable$s$571_1)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$571_1)) {
																								{
																									{
																										double cv$temp$188$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$571_1];
																											cv$temp$188$var192 = var192;
																										}
																										double cv$temp$189$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$571_1];
																											cv$temp$189$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample49Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$188$var192) / Math.sqrt(cv$temp$189$var193))) - (0.5 * Math.log(cv$temp$189$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$188$var192) / Math.sqrt(cv$temp$189$var193))) - (0.5 * Math.log(cv$temp$189$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$188$var192) / Math.sqrt(cv$temp$189$var193))) - (0.5 * Math.log(cv$temp$189$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$188$var192) / Math.sqrt(cv$temp$189$var193))) - (0.5 * Math.log(cv$temp$189$var193)))))) + 1)) + (Math.log(cv$probabilitySample49Value569) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$188$var192) / Math.sqrt(cv$temp$189$var193))) - (0.5 * Math.log(cv$temp$189$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value569);
																									}
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
																if((index$i$19 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$576_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$576_1)) {
																					{
																						{
																							double cv$temp$190$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$576_1];
																								cv$temp$190$var192 = var192;
																							}
																							double cv$temp$191$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$576_1];
																								cv$temp$191$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$190$var192) / Math.sqrt(cv$temp$191$var193))) - (0.5 * Math.log(cv$temp$191$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$190$var192) / Math.sqrt(cv$temp$191$var193))) - (0.5 * Math.log(cv$temp$191$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$190$var192) / Math.sqrt(cv$temp$191$var193))) - (0.5 * Math.log(cv$temp$191$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$190$var192) / Math.sqrt(cv$temp$191$var193))) - (0.5 * Math.log(cv$temp$191$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$190$var192) / Math.sqrt(cv$temp$191$var193))) - (0.5 * Math.log(cv$temp$191$var193)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$s$577_1 = distributionTempVariable$var62$31;
																if((index$i$28 == i$var180)) {
																	for(int var117 = 0; var117 < noStates; var117 += 1) {
																		if((var117 == traceTempVariable$s$577_1)) {
																			for(int var168 = 0; var168 < noStates; var168 += 1) {
																				if((var168 == traceTempVariable$s$577_1)) {
																					{
																						{
																							double cv$temp$192$var192;
																							{
																								double var192 = pageFaultsMean[traceTempVariable$s$577_1];
																								cv$temp$192$var192 = var192;
																							}
																							double cv$temp$193$var193;
																							{
																								double var193 = pageFaultsVar[traceTempVariable$s$577_1];
																								cv$temp$193$var193 = var193;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$192$var192) / Math.sqrt(cv$temp$193$var193))) - (0.5 * Math.log(cv$temp$193$var193)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$192$var192) / Math.sqrt(cv$temp$193$var193))) - (0.5 * Math.log(cv$temp$193$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$192$var192) / Math.sqrt(cv$temp$193$var193))) - (0.5 * Math.log(cv$temp$193$var193))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$192$var192) / Math.sqrt(cv$temp$193$var193))) - (0.5 * Math.log(cv$temp$193$var193)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$192$var192) / Math.sqrt(cv$temp$193$var193))) - (0.5 * Math.log(cv$temp$193$var193)))));
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
																		for(int index$sample67$579 = 0; index$sample67$579 < noStates; index$sample67$579 += 1) {
																			int distributionTempVariable$var62$581 = index$sample67$579;
																			double cv$probabilitySample67Value580 = (1.0 * distribution$sample67[((index$i$578 - 1) / 1)][index$sample67$579]);
																			int traceTempVariable$s$582_1 = distributionTempVariable$var62$581;
																			if((index$i$578 == i$var180)) {
																				for(int var117 = 0; var117 < noStates; var117 += 1) {
																					if((var117 == traceTempVariable$s$582_1)) {
																						for(int var168 = 0; var168 < noStates; var168 += 1) {
																							if((var168 == traceTempVariable$s$582_1)) {
																								{
																									{
																										double cv$temp$194$var192;
																										{
																											double var192 = pageFaultsMean[traceTempVariable$s$582_1];
																											cv$temp$194$var192 = var192;
																										}
																										double cv$temp$195$var193;
																										{
																											double var193 = pageFaultsVar[traceTempVariable$s$582_1];
																											cv$temp$195$var193 = var193;
																										}
																										if(((Math.log(cv$probabilitySample67Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$194$var192) / Math.sqrt(cv$temp$195$var193))) - (0.5 * Math.log(cv$temp$195$var193)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$194$var192) / Math.sqrt(cv$temp$195$var193))) - (0.5 * Math.log(cv$temp$195$var193)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$194$var192) / Math.sqrt(cv$temp$195$var193))) - (0.5 * Math.log(cv$temp$195$var193))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$194$var192) / Math.sqrt(cv$temp$195$var193))) - (0.5 * Math.log(cv$temp$195$var193)))))) + 1)) + (Math.log(cv$probabilitySample67Value580) + (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$temp$194$var192) / Math.sqrt(cv$temp$195$var193))) - (0.5 * Math.log(cv$temp$195$var193)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value580);
																									}
																								}
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
					int traceTempVariable$var59$621_1 = cv$currentValue;
					for(int index$i$621_2 = 1; index$i$621_2 < samples; index$i$621_2 += 1) {
						if((i$var56 == (index$i$621_2 - 1))) {
							{
								int index$i$623 = index$i$621_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var61;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var35 = 0; var35 < noStates; var35 += 1) {
									if((var35 == traceTempVariable$var59$621_1)) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample49) {
												if((0 == (i$var56 - 1))) {
													for(int index$var35$630_1 = 0; index$var35$630_1 < noStates; index$var35$630_1 += 1) {
														if((index$var35$630_1 == st[(i$var56 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample49$626 = 0; index$sample49$626 < noStates; index$sample49$626 += 1) {
														int distributionTempVariable$var44$628 = index$sample49$626;
														double cv$probabilitySample49Value627 = (1.0 * distribution$sample49[index$sample49$626]);
														int traceTempVariable$var59$629_1 = distributionTempVariable$var44$628;
														if((0 == (i$var56 - 1))) {
															for(int index$var35$631_1 = 0; index$var35$631_1 < noStates; index$var35$631_1 += 1) {
																if((index$var35$631_1 == traceTempVariable$var59$629_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample49Value627);
															}
														}
													}
												}
											}
											int traceTempVariable$var59$632_1 = cv$currentValue;
											if((index$i$19 == (i$var56 - 1))) {
												for(int index$var35$638_1 = 0; index$var35$638_1 < noStates; index$var35$638_1 += 1) {
													if((index$var35$638_1 == traceTempVariable$var59$632_1))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$633 = 1; index$i$633 < samples; index$i$633 += 1) {
												if((!(index$i$633 == index$i$19) && !(index$i$633 == index$i$623))) {
													for(int index$sample67$634 = 0; index$sample67$634 < noStates; index$sample67$634 += 1) {
														int distributionTempVariable$var62$636 = index$sample67$634;
														double cv$probabilitySample67Value635 = (1.0 * distribution$sample67[((index$i$633 - 1) / 1)][index$sample67$634]);
														int traceTempVariable$var59$637_1 = distributionTempVariable$var62$636;
														if((index$i$633 == (i$var56 - 1))) {
															for(int index$var35$639_1 = 0; index$var35$639_1 < noStates; index$var35$639_1 += 1) {
																if((index$var35$639_1 == traceTempVariable$var59$637_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample67Value635);
															}
														}
													}
												}
											}
											double[] cv$temp$196$var60;
											{
												double[] var60 = m[traceTempVariable$var59$621_1];
												cv$temp$196$var60 = var60;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$196$var60);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample67[((index$i$621_2 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample67[((i$var56 - 1) / 1)];
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

	private final void sample87(int var81, int threadID$cv$var81, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = cpuMean[var81];
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
					double var82 = cv$proposedValue;
					cpuMean[var81] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var69;
				{
					cv$temp$0$var69 = 16.0;
				}
				double cv$temp$1$var68;
				{
					cv$temp$1$var68 = 8.6;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var69) / Math.sqrt(cv$temp$1$var68))) - (0.5 * Math.log(cv$temp$1$var68))));
				{
					{
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample49) {
								if((0 == i$var180)) {
									double traceTempVariable$var182$7_1 = cv$currentValue;
									if((var81 == st[i$var180])) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if((0 == i$var180)) {
													for(int var134 = 0; var134 < noStates; var134 += 1) {
														if((var134 == st[i$var180])) {
															{
																{
																	double cv$temp$2$var182;
																	{
																		double var182 = traceTempVariable$var182$7_1;
																		cv$temp$2$var182 = var182;
																	}
																	double cv$temp$3$var183;
																	{
																		double var183 = cpuVar[st[i$var180]];
																		cv$temp$3$var183 = var183;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$2$var182) / Math.sqrt(cv$temp$3$var183))) - (0.5 * Math.log(cv$temp$3$var183)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												if(fixedFlag$sample67) {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if((i$var56 == i$var180)) {
															for(int var134 = 0; var134 < noStates; var134 += 1) {
																if((var134 == st[i$var180])) {
																	{
																		{
																			double cv$temp$4$var182;
																			{
																				double var182 = traceTempVariable$var182$7_1;
																				cv$temp$4$var182 = var182;
																			}
																			double cv$temp$5$var183;
																			{
																				double var183 = cpuVar[st[i$var180]];
																				cv$temp$5$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$4$var182) / Math.sqrt(cv$temp$5$var183))) - (0.5 * Math.log(cv$temp$5$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
														if(true) {
															for(int index$sample67$26 = 0; index$sample67$26 < noStates; index$sample67$26 += 1) {
																int distributionTempVariable$var62$28 = index$sample67$26;
																double cv$probabilitySample67Value27 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$26]);
																int traceTempVariable$s$29_1 = distributionTempVariable$var62$28;
																if((i$var56 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$29_1)) {
																			{
																				{
																					double cv$temp$6$var182;
																					{
																						double var182 = traceTempVariable$var182$7_1;
																						cv$temp$6$var182 = var182;
																					}
																					double cv$temp$7$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$29_1];
																						cv$temp$7$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value27) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$6$var182) / Math.sqrt(cv$temp$7$var183))) - (0.5 * Math.log(cv$temp$7$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value27);
																				}
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
									for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
										int distributionTempVariable$var44$5 = index$sample49$3;
										double cv$probabilitySample49Value4 = (1.0 * distribution$sample49[index$sample49$3]);
										int traceTempVariable$s$6_1 = distributionTempVariable$var44$5;
										if((0 == i$var180)) {
											double traceTempVariable$var182$8_1 = cv$currentValue;
											if((var81 == traceTempVariable$s$6_1)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$s$32_1 = distributionTempVariable$var44$5;
														if((0 == i$var180)) {
															for(int var134 = 0; var134 < noStates; var134 += 1) {
																if((var134 == traceTempVariable$s$32_1)) {
																	{
																		{
																			double cv$temp$8$var182;
																			{
																				double var182 = traceTempVariable$var182$8_1;
																				cv$temp$8$var182 = var182;
																			}
																			double cv$temp$9$var183;
																			{
																				double var183 = cpuVar[traceTempVariable$s$32_1];
																				cv$temp$9$var183 = var183;
																			}
																			if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$8$var182) / Math.sqrt(cv$temp$9$var183))) - (0.5 * Math.log(cv$temp$9$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample49$33 = 0; index$sample49$33 < noStates; index$sample49$33 += 1) {
																int distributionTempVariable$var44$35 = index$sample49$33;
																double cv$probabilitySample49Value34 = (cv$probabilitySample49Value4 * distribution$sample49[index$sample49$33]);
																int traceTempVariable$s$36_1 = distributionTempVariable$var44$35;
																if((0 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$36_1)) {
																			{
																				{
																					double cv$temp$10$var182;
																					{
																						double var182 = traceTempVariable$var182$8_1;
																						cv$temp$10$var182 = var182;
																					}
																					double cv$temp$11$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$36_1];
																						cv$temp$11$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value34) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$10$var182) / Math.sqrt(cv$temp$11$var183))) - (0.5 * Math.log(cv$temp$11$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value34);
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample67) {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if((i$var56 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$6_1)) {
																			{
																				{
																					double cv$temp$12$var182;
																					{
																						double var182 = traceTempVariable$var182$8_1;
																						cv$temp$12$var182 = var182;
																					}
																					double cv$temp$13$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$6_1];
																						cv$temp$13$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value4) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$12$var182) / Math.sqrt(cv$temp$13$var183))) - (0.5 * Math.log(cv$temp$13$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value4);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
																if(true) {
																	for(int index$sample67$41 = 0; index$sample67$41 < noStates; index$sample67$41 += 1) {
																		int distributionTempVariable$var62$43 = index$sample67$41;
																		double cv$probabilitySample67Value42 = (cv$probabilitySample49Value4 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$41]);
																		int traceTempVariable$s$44_1 = distributionTempVariable$var62$43;
																		if((i$var56 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$44_1)) {
																					{
																						{
																							double cv$temp$14$var182;
																							{
																								double var182 = traceTempVariable$var182$8_1;
																								cv$temp$14$var182 = var182;
																							}
																							double cv$temp$15$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$44_1];
																								cv$temp$15$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value42) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$14$var182) / Math.sqrt(cv$temp$15$var183))) - (0.5 * Math.log(cv$temp$15$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value42);
																						}
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
						for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
							if(fixedFlag$sample67) {
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if((i$var56 == i$var180)) {
										double traceTempVariable$var182$16_1 = cv$currentValue;
										if((var81 == st[i$var180])) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample49) {
														if((0 == i$var180)) {
															for(int var134 = 0; var134 < noStates; var134 += 1) {
																if((var134 == st[i$var180])) {
																	{
																		{
																			double cv$temp$16$var182;
																			{
																				double var182 = traceTempVariable$var182$16_1;
																				cv$temp$16$var182 = var182;
																			}
																			double cv$temp$17$var183;
																			{
																				double var183 = cpuVar[st[i$var180]];
																				cv$temp$17$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$16$var182) / Math.sqrt(cv$temp$17$var183))) - (0.5 * Math.log(cv$temp$17$var183)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample49$48 = 0; index$sample49$48 < noStates; index$sample49$48 += 1) {
																int distributionTempVariable$var44$50 = index$sample49$48;
																double cv$probabilitySample49Value49 = (1.0 * distribution$sample49[index$sample49$48]);
																int traceTempVariable$s$51_1 = distributionTempVariable$var44$50;
																if((0 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$51_1)) {
																			{
																				{
																					double cv$temp$18$var182;
																					{
																						double var182 = traceTempVariable$var182$16_1;
																						cv$temp$18$var182 = var182;
																					}
																					double cv$temp$19$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$51_1];
																						cv$temp$19$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value49) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$18$var182) / Math.sqrt(cv$temp$19$var183))) - (0.5 * Math.log(cv$temp$19$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value49);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$54_1 = 1; index$i$54_1 < samples; index$i$54_1 += 1) {
														if((index$i$54_1 == i$var180)) {
															for(int var134 = 0; var134 < noStates; var134 += 1) {
																if((var134 == st[i$var180])) {
																	{
																		{
																			double cv$temp$20$var182;
																			{
																				double var182 = traceTempVariable$var182$16_1;
																				cv$temp$20$var182 = var182;
																			}
																			double cv$temp$21$var183;
																			{
																				double var183 = cpuVar[st[i$var180]];
																				cv$temp$21$var183 = var183;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$20$var182) / Math.sqrt(cv$temp$21$var183))) - (0.5 * Math.log(cv$temp$21$var183)))));
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
								for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
									if(true) {
										for(int index$sample67$12 = 0; index$sample67$12 < noStates; index$sample67$12 += 1) {
											int distributionTempVariable$var62$14 = index$sample67$12;
											double cv$probabilitySample67Value13 = (1.0 * distribution$sample67[((i$var56 - 1) / 1)][index$sample67$12]);
											int traceTempVariable$s$15_1 = distributionTempVariable$var62$14;
											if((i$var56 == i$var180)) {
												double traceTempVariable$var182$17_1 = cv$currentValue;
												if((var81 == traceTempVariable$s$15_1)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															if(fixedFlag$sample49) {
																if((0 == i$var180)) {
																	for(int var134 = 0; var134 < noStates; var134 += 1) {
																		if((var134 == traceTempVariable$s$15_1)) {
																			{
																				{
																					double cv$temp$22$var182;
																					{
																						double var182 = traceTempVariable$var182$17_1;
																						cv$temp$22$var182 = var182;
																					}
																					double cv$temp$23$var183;
																					{
																						double var183 = cpuVar[traceTempVariable$s$15_1];
																						cv$temp$23$var183 = var183;
																					}
																					if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$22$var182) / Math.sqrt(cv$temp$23$var183))) - (0.5 * Math.log(cv$temp$23$var183)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample49$57 = 0; index$sample49$57 < noStates; index$sample49$57 += 1) {
																		int distributionTempVariable$var44$59 = index$sample49$57;
																		double cv$probabilitySample49Value58 = (cv$probabilitySample67Value13 * distribution$sample49[index$sample49$57]);
																		int traceTempVariable$s$60_1 = distributionTempVariable$var44$59;
																		if((0 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$60_1)) {
																					{
																						{
																							double cv$temp$24$var182;
																							{
																								double var182 = traceTempVariable$var182$17_1;
																								cv$temp$24$var182 = var182;
																							}
																							double cv$temp$25$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$60_1];
																								cv$temp$25$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))))) + 1)) + (Math.log(cv$probabilitySample49Value58) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$24$var182) / Math.sqrt(cv$temp$25$var183))) - (0.5 * Math.log(cv$temp$25$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample49Value58);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															int traceTempVariable$s$63_1 = distributionTempVariable$var62$14;
															if((i$var56 == i$var180)) {
																for(int var134 = 0; var134 < noStates; var134 += 1) {
																	if((var134 == traceTempVariable$s$63_1)) {
																		{
																			{
																				double cv$temp$26$var182;
																				{
																					double var182 = traceTempVariable$var182$17_1;
																					cv$temp$26$var182 = var182;
																				}
																				double cv$temp$27$var183;
																				{
																					double var183 = cpuVar[traceTempVariable$s$63_1];
																					cv$temp$27$var183 = var183;
																				}
																				if(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value13) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$26$var182) / Math.sqrt(cv$temp$27$var183))) - (0.5 * Math.log(cv$temp$27$var183)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value13);
																			}
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < samples; index$i$64 += 1) {
																if(!(index$i$64 == i$var56)) {
																	for(int index$sample67$65 = 0; index$sample67$65 < noStates; index$sample67$65 += 1) {
																		int distributionTempVariable$var62$67 = index$sample67$65;
																		double cv$probabilitySample67Value66 = (cv$probabilitySample67Value13 * distribution$sample67[((index$i$64 - 1) / 1)][index$sample67$65]);
																		int traceTempVariable$s$68_1 = distributionTempVariable$var62$67;
																		if((index$i$64 == i$var180)) {
																			for(int var134 = 0; var134 < noStates; var134 += 1) {
																				if((var134 == traceTempVariable$s$68_1)) {
																					{
																						{
																							double cv$temp$28$var182;
																							{
																								double var182 = traceTempVariable$var182$17_1;
																								cv$temp$28$var182 = var182;
																							}
																							double cv$temp$29$var183;
																							{
																								double var183 = cpuVar[traceTempVariable$s$68_1];
																								cv$temp$29$var183 = var183;
																							}
																							if(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))))) + 1)) + (Math.log(cv$probabilitySample67Value66) + (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$temp$28$var182) / Math.sqrt(cv$temp$29$var183))) - (0.5 * Math.log(cv$temp$29$var183)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample67Value66);
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
			double var82 = cv$originalValue;
			cpuMean[var81] = var82;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var35 = 0; var35 < noStates; var35 += 1)
				cv$max = Math.max(cv$max, noStates);
			{
				int cv$threadCount = threadCount();
				cv$var36$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var36$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var41$countGlobal = new double[cv$max];
		}
		{
			int cv$var37$max = noStates;
			cv$distributionAccumulator$var61 = new double[cv$var37$max];
		}
		{
			cv$var44$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample49gaussian189$global = new boolean[cv$max_i$var180];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample49gaussian194$global = new boolean[cv$max_i$var180];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample49gaussian199$global = new boolean[cv$max_i$var180];
		}
		{
			int cv$var37$max = noStates;
			cv$var62$stateProbabilityGlobal = new double[cv$var37$max];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample67gaussian189$global = new boolean[cv$max_i$var180];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample67gaussian194$global = new boolean[cv$max_i$var180];
		}
		{
			int cv$max_i$var180 = 0;
			cv$max_i$var180 = Math.max(cv$max_i$var180, ((length$cpu_measured - 0) / 1));
			guard$sample67gaussian199$global = new boolean[cv$max_i$var180];
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
				for(int var35 = 0; var35 < noStates; var35 += 1)
					m[var35] = new double[noStates];
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
			distribution$sample49 = new double[noStates];
		}
		{
			distribution$sample67 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)][];
			for(int i$var56 = 1; i$var56 < length$cpu_measured; i$var56 += 1)
				distribution$sample67[((i$var56 - 1) / 1)] = new double[noStates];
		}
		{
			logProbability$var61 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample67 = new double[((((length$cpu_measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var184 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample190 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var189 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample195 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var194 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample200 = new double[((((length$cpu_measured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						double[] var36 = m[var35];
						if(!fixedFlag$sample39)
							DistributionSampling.sampleDirichlet(RNG$1, v, var36);
					}
			}
		);
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
			if(!fixedFlag$sample67)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
						if(!fixedFlag$sample87)
							cpuMean[var81] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
						if(!fixedFlag$sample105)
							memMean[var99] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
						if(!fixedFlag$sample123)
							pageFaultsMean[var117] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
						if(!fixedFlag$sample140)
							cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
						if(!fixedFlag$sample157)
							memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
						if(!fixedFlag$sample174)
							pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var180, int forEnd$i$var180, int threadID$i$var180, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var180 = forStart$i$var180; i$var180 < forEnd$i$var180; i$var180 += 1) {
						if(!fixedFlag$sample190)
							cpu[i$var180] = ((Math.sqrt(cpuVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var180]]);
						if(!fixedFlag$sample195)
							mem[i$var180] = ((Math.sqrt(memVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var180]]);
						if(!fixedFlag$sample200)
							pageFaults[i$var180] = ((Math.sqrt(pageFaultsVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var180]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						double[] var36 = m[var35];
						if(!fixedFlag$sample39)
							DistributionSampling.sampleDirichlet(RNG$1, v, var36);
					}
			}
		);
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		double[] cv$distribution$sample49 = distribution$sample49;
		for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1) {
			double cv$value = (((0.0 <= index$var43) && (index$var43 < initialStateDistribution.length))?initialStateDistribution[index$var43]:0.0);
			if(!fixedFlag$sample49)
				cv$distribution$sample49[index$var43] = cv$value;
		}
		for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
			double[] cv$distribution$sample67 = distribution$sample67[((i$var56 - 1) / 1)];
			for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1) {
				if(!fixedFlag$sample67)
					cv$distribution$sample67[index$var61] = 0.0;
			}
			if(fixedFlag$sample49) {
				if((0 == (i$var56 - 1))) {
					for(int var35 = 0; var35 < noStates; var35 += 1) {
						if((var35 == st[(i$var56 - 1)])) {
							{
								if(!fixedFlag$sample67) {
									double[] var60 = m[st[(i$var56 - 1)]];
									for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
										cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (1.0 * (((0.0 <= index$var61) && (index$var61 < var60.length))?var60[index$var61]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample49$2 = 0; index$sample49$2 < noStates; index$sample49$2 += 1) {
						int distributionTempVariable$var44$4 = index$sample49$2;
						double cv$probabilitySample49Value3 = (1.0 * distribution$sample49[index$sample49$2]);
						int traceTempVariable$var59$5_1 = distributionTempVariable$var44$4;
						if((0 == (i$var56 - 1))) {
							for(int var35 = 0; var35 < noStates; var35 += 1) {
								if((var35 == traceTempVariable$var59$5_1)) {
									{
										if(!fixedFlag$sample67) {
											double[] var60 = m[traceTempVariable$var59$5_1];
											for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
												cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample49Value3 * (((0.0 <= index$var61) && (index$var61 < var60.length))?var60[index$var61]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample67) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var56 - 1))) {
						for(int var35 = 0; var35 < noStates; var35 += 1) {
							if((var35 == st[(i$var56 - 1)])) {
								{
									if(!fixedFlag$sample67) {
										double[] var60 = m[st[(i$var56 - 1)]];
										for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
											cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (1.0 * (((0.0 <= index$var61) && (index$var61 < var60.length))?var60[index$var61]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample67$10 = 0; index$sample67$10 < noStates; index$sample67$10 += 1) {
							int distributionTempVariable$var62$12 = index$sample67$10;
							double cv$probabilitySample67Value11 = (1.0 * distribution$sample67[((index$i$9 - 1) / 1)][index$sample67$10]);
							int traceTempVariable$var59$13_1 = distributionTempVariable$var62$12;
							if((index$i$9 == (i$var56 - 1))) {
								for(int var35 = 0; var35 < noStates; var35 += 1) {
									if((var35 == traceTempVariable$var59$13_1)) {
										{
											if(!fixedFlag$sample67) {
												double[] var60 = m[traceTempVariable$var59$13_1];
												for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
													cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample67Value11 * (((0.0 <= index$var61) && (index$var61 < var60.length))?var60[index$var61]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var61$sum = 0.0;
			for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1) {
				if(!fixedFlag$sample67)
					cv$var61$sum = (cv$var61$sum + cv$distribution$sample67[index$var61]);
			}
			for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1) {
				if(!fixedFlag$sample67)
					cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] / cv$var61$sum);
			}
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
						if(!fixedFlag$sample87)
							cpuMean[var81] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
						if(!fixedFlag$sample105)
							memMean[var99] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
						if(!fixedFlag$sample123)
							pageFaultsMean[var117] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
						if(!fixedFlag$sample140)
							cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
						if(!fixedFlag$sample157)
							memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
						if(!fixedFlag$sample174)
							pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						double[] var36 = m[var35];
						if(!fixedFlag$sample39)
							DistributionSampling.sampleDirichlet(RNG$1, v, var36);
					}
			}
		);
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
			if(!fixedFlag$sample67)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
						if(!fixedFlag$sample87)
							cpuMean[var81] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
						if(!fixedFlag$sample105)
							memMean[var99] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
						if(!fixedFlag$sample123)
							pageFaultsMean[var117] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
						if(!fixedFlag$sample140)
							cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
						if(!fixedFlag$sample157)
							memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
						if(!fixedFlag$sample174)
							pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample39)
								sample39(var35, threadID$var35, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample49)
				sample49();
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				if(!fixedFlag$sample67)
					sample67(i$var56);
			}
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
							if(!fixedFlag$sample87)
								sample87(var81, threadID$var81, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
							if(!fixedFlag$sample105)
								sample105(var99, threadID$var99, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
							if(!fixedFlag$sample123)
								sample123(var117, threadID$var117, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
							if(!fixedFlag$sample140)
								sample140(var134, threadID$var134, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
							if(!fixedFlag$sample157)
								sample157(var151, threadID$var151, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
							if(!fixedFlag$sample174)
								sample174(var168, threadID$var168, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
							if(!fixedFlag$sample174)
								sample174(var168, threadID$var168, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
							if(!fixedFlag$sample157)
								sample157(var151, threadID$var151, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
							if(!fixedFlag$sample140)
								sample140(var134, threadID$var134, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
							if(!fixedFlag$sample123)
								sample123(var117, threadID$var117, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
							if(!fixedFlag$sample105)
								sample105(var99, threadID$var99, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
							if(!fixedFlag$sample87)
								sample87(var81, threadID$var81, RNG$1);
						}
				}
			);
			for(int i$var56 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var56 >= ((1 - 1) + 1); i$var56 -= 1) {
				if(!fixedFlag$sample67)
					sample67(i$var56);
			}
			if(!fixedFlag$sample49)
				sample49();
			if(!fixedFlag$sample46)
				sample46();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample39)
								sample39(var35, threadID$var35, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						v[var21] = 0.1;
			}
		);
		samples = length$cpu_measured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var24 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var36 = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var43 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var44 = 0.0;
		for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
			logProbability$var61[((i$var56 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				logProbability$sample67[((i$var56 - 1) / 1)] = 0.0;
		}
		logProbability$var70 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$var82 = 0.0;
		logProbability$var88 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample105)
			logProbability$var100 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample123)
			logProbability$var118 = 0.0;
		logProbability$var123 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample140)
			logProbability$var135 = 0.0;
		logProbability$var140 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample157)
			logProbability$var152 = 0.0;
		logProbability$var157 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample174)
			logProbability$var169 = 0.0;
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
			logProbability$var184[((i$var180 - 0) / 1)] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample190[((i$var180 - 0) / 1)] = 0.0;
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
			logProbability$var189[((i$var180 - 0) / 1)] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample195) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample195[((i$var180 - 0) / 1)] = 0.0;
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
			logProbability$var194[((i$var180 - 0) / 1)] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample200) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample200[((i$var180 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		if(fixedFlag$sample105)
			logProbabilityValue$sample105();
		if(fixedFlag$sample123)
			logProbabilityValue$sample123();
		if(fixedFlag$sample140)
			logProbabilityValue$sample140();
		if(fixedFlag$sample157)
			logProbabilityValue$sample157();
		if(fixedFlag$sample174)
			logProbabilityValue$sample174();
		logProbabilityValue$sample190();
		logProbabilityValue$sample195();
		logProbabilityValue$sample200();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample46();
		logProbabilityDistribution$sample49();
		logProbabilityDistribution$sample67();
		logProbabilityValue$sample87();
		logProbabilityValue$sample105();
		logProbabilityValue$sample123();
		logProbabilityValue$sample140();
		logProbabilityValue$sample157();
		logProbabilityValue$sample174();
		logProbabilityDistribution$sample190();
		logProbabilityDistribution$sample195();
		logProbabilityDistribution$sample200();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample46();
		logProbabilityValue$sample49();
		logProbabilityValue$sample67();
		logProbabilityValue$sample87();
		logProbabilityValue$sample105();
		logProbabilityValue$sample123();
		logProbabilityValue$sample140();
		logProbabilityValue$sample157();
		logProbabilityValue$sample174();
		logProbabilityValue$sample190();
		logProbabilityValue$sample195();
		logProbabilityValue$sample200();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						double[] var36 = m[var35];
						if(!fixedFlag$sample39)
							DistributionSampling.sampleDirichlet(RNG$1, v, var36);
					}
			}
		);
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
			if(!fixedFlag$sample67)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var81, int forEnd$var81, int threadID$var81, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var81 = forStart$var81; var81 < forEnd$var81; var81 += 1) {
						if(!fixedFlag$sample87)
							cpuMean[var81] = ((Math.sqrt(8.6) * DistributionSampling.sampleGaussian(RNG$1)) + 16.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var99, int forEnd$var99, int threadID$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var99 = forStart$var99; var99 < forEnd$var99; var99 += 1) {
						if(!fixedFlag$sample105)
							memMean[var99] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 94.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var117, int forEnd$var117, int threadID$var117, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var117 = forStart$var117; var117 < forEnd$var117; var117 += 1) {
						if(!fixedFlag$sample123)
							pageFaultsMean[var117] = ((Math.sqrt(335550.0) * DistributionSampling.sampleGaussian(RNG$1)) + 814.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var134, int forEnd$var134, int threadID$var134, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var134 = forStart$var134; var134 < forEnd$var134; var134 += 1) {
						if(!fixedFlag$sample140)
							cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var151, int forEnd$var151, int threadID$var151, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var151 = forStart$var151; var151 < forEnd$var151; var151 += 1) {
						if(!fixedFlag$sample157)
							memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var168, int forEnd$var168, int threadID$var168, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var168 = forStart$var168; var168 < forEnd$var168; var168 += 1) {
						if(!fixedFlag$sample174)
							pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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