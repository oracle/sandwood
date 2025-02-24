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
	private double[] cv$distributionAccumulator$var55;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var35$countGlobal;
	private double[] cv$var38$stateProbabilityGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private double[] distribution$sample39;
	private double[][] distribution$sample57;
	private boolean fixedFlag$sample113 = false;
	private boolean fixedFlag$sample130 = false;
	private boolean fixedFlag$sample147 = false;
	private boolean fixedFlag$sample164 = false;
	private boolean fixedFlag$sample180 = false;
	private boolean fixedFlag$sample185 = false;
	private boolean fixedFlag$sample190 = false;
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
		fixedProbFlag$sample180 = false;
	}

	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	@Override
	public final void set$cpuMean(double[] cv$value) {
		cpuMean = cv$value;
		setFlag$cpuMean = true;
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
		setFlag$cpuVar = true;
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
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	@Override
	public final void set$fixedFlag$sample113(boolean cv$value) {
		fixedFlag$sample113 = cv$value;
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample130() {
		return fixedFlag$sample130;
	}

	@Override
	public final void set$fixedFlag$sample130(boolean cv$value) {
		fixedFlag$sample130 = cv$value;
		fixedProbFlag$sample130 = (cv$value && fixedProbFlag$sample130);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample147() {
		return fixedFlag$sample147;
	}

	@Override
	public final void set$fixedFlag$sample147(boolean cv$value) {
		fixedFlag$sample147 = cv$value;
		fixedProbFlag$sample147 = (cv$value && fixedProbFlag$sample147);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	@Override
	public final void set$fixedFlag$sample164(boolean cv$value) {
		fixedFlag$sample164 = cv$value;
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample180() {
		return fixedFlag$sample180;
	}

	@Override
	public final void set$fixedFlag$sample180(boolean cv$value) {
		fixedFlag$sample180 = cv$value;
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample185() {
		return fixedFlag$sample185;
	}

	@Override
	public final void set$fixedFlag$sample185(boolean cv$value) {
		fixedFlag$sample185 = cv$value;
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		fixedFlag$sample190 = cv$value;
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		fixedFlag$sample77 = cv$value;
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
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
		setFlag$m = true;
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample57 = false;
	}

	@Override
	public final double[] get$mem() {
		return mem;
	}

	@Override
	public final void set$mem(double[] cv$value) {
		mem = cv$value;
		setFlag$mem = true;
		fixedProbFlag$sample185 = false;
	}

	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	@Override
	public final void set$memMean(double[] cv$value) {
		memMean = cv$value;
		setFlag$memMean = true;
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
		setFlag$memVar = true;
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
	public final void set$pageFaults(double[] cv$value) {
		pageFaults = cv$value;
		setFlag$pageFaults = true;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
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
		setFlag$pageFaultsVar = true;
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
		setFlag$st = true;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = cpu[i$var174];
				if(((0 == i$var174) && (0 <= st[0]))) {
					if(fixedFlag$sample39) {
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							double var177 = cpuVar[st[0]];
							cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5));
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							int var75 = st[0];
							if(((0 <= var75) && (var75 < noStates))) {
								double var177 = cpuVar[st[0]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var177)))) - (Math.log(var177) * 0.5));
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
				if(((1 <= i$var174) && (0 <= st[i$var174]))) {
					if(fixedFlag$sample57) {
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							double var177 = cpuVar[st[i$var174]];
							double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5));
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							int var75 = st[i$var174];
							if(((0 <= var75) && (var75 < noStates))) {
								double var177 = cpuVar[st[i$var174]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample57Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var174]]) / Math.sqrt(var177)))) - (Math.log(var177) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var178[i$var174] = cv$distributionAccumulator;
				logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = ((((fixedFlag$sample180 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample180[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var178[i$var174] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = mem[i$var174];
				if(((0 == i$var174) && (0 <= st[0]))) {
					if(fixedFlag$sample39) {
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							double var182 = memVar[st[0]];
							cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5));
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							int var93 = st[0];
							if(((0 <= var93) && (var93 < noStates))) {
								double var182 = memVar[st[0]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var182)))) - (Math.log(var182) * 0.5));
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
				if(((1 <= i$var174) && (0 <= st[i$var174]))) {
					if(fixedFlag$sample57) {
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							double var182 = memVar[st[i$var174]];
							double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5));
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							int var93 = st[i$var174];
							if(((0 <= var93) && (var93 < noStates))) {
								double var182 = memVar[st[i$var174]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample57Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var174]]) / Math.sqrt(var182)))) - (Math.log(var182) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var183[i$var174] = cv$distributionAccumulator;
				logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = ((((fixedFlag$sample185 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample185[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var183[i$var174] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = pageFaults[i$var174];
				if(((0 == i$var174) && (0 <= st[0]))) {
					if(fixedFlag$sample39) {
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							double var187 = pageFaultsVar[st[0]];
							cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5));
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							int var111 = st[0];
							if(((0 <= var111) && (var111 < noStates))) {
								double var187 = pageFaultsVar[st[0]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var187)))) - (Math.log(var187) * 0.5));
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
				if(((1 <= i$var174) && (0 <= st[i$var174]))) {
					if(fixedFlag$sample57) {
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							double var187 = pageFaultsVar[st[i$var174]];
							double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5));
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							int var111 = st[i$var174];
							if(((0 <= var111) && (var111 < noStates))) {
								double var187 = pageFaultsVar[st[i$var174]];
								double cv$weightedProbability = ((Math.log(cv$probabilitySample57Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187)))) - (Math.log(var187) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var188[i$var174] = cv$distributionAccumulator;
				logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample190[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var188[i$var174] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample39() {
		if(!fixedProbFlag$sample39) {
			if(fixedFlag$sample39) {
				int cv$sampleValue = st[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var37 = cv$distributionAccumulator;
				logProbability$var38 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample39 = fixedFlag$sample36;
			}
		} else {
			logProbability$var37 = logProbability$var38;
			if(fixedFlag$sample39)
				logProbability$st = (logProbability$st + logProbability$var38);
			logProbability$$model = (logProbability$$model + logProbability$var38);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var50];
					if((1 == i$var50)) {
						if(fixedFlag$sample39) {
							int var29 = st[0];
							if(((0 <= var29) && (var29 < noStates))) {
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
								double cv$probabilitySample39Value5 = distribution$sample39[index$sample39$4];
								int var29 = st[0];
								if(((0 <= var29) && (var29 < noStates))) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[0]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					if((2 <= i$var50)) {
						int var29 = st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < noStates))) {
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[(i$var50 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var55[(i$var50 - 1)] = cv$distributionAccumulator;
					logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = (fixedFlag$sample30 && fixedFlag$sample39);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = logProbability$sample57[(i$var50 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[(i$var50 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var111 = 0; var111 < noStates; var111 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var111] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			logProbability$var100 = cv$sampleAccumulator;
			logProbability$var112 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample113 = fixedFlag$sample113;
		} else {
			logProbability$var100 = logProbability$var112;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var112);
			logProbability$$model = (logProbability$$model + logProbability$var112);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var112);
		}
	}

	private final void logProbabilityValue$sample130() {
		if(!fixedProbFlag$sample130) {
			double cv$sampleAccumulator = 0.0;
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var128], 5.0, 0.5));
			logProbability$var117 = cv$sampleAccumulator;
			logProbability$var129 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample130 = fixedFlag$sample130;
		} else {
			logProbability$var117 = logProbability$var129;
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var129);
			logProbability$$model = (logProbability$$model + logProbability$var129);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var129);
		}
	}

	private final void logProbabilityValue$sample147() {
		if(!fixedProbFlag$sample147) {
			double cv$sampleAccumulator = 0.0;
			for(int var145 = 0; var145 < noStates; var145 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var145], 5.0, 0.5));
			logProbability$var134 = cv$sampleAccumulator;
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample147 = fixedFlag$sample147;
		} else {
			logProbability$var134 = logProbability$var146;
			logProbability$memVar = (logProbability$memVar + logProbability$var146);
			logProbability$$model = (logProbability$$model + logProbability$var146);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var146);
		}
	}

	private final void logProbabilityValue$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$sampleAccumulator = 0.0;
			for(int var162 = 0; var162 < noStates; var162 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var162], 5.0, 0.5));
			logProbability$var151 = cv$sampleAccumulator;
			logProbability$var163 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample164 = fixedFlag$sample164;
		} else {
			logProbability$var151 = logProbability$var163;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var163);
			logProbability$$model = (logProbability$$model + logProbability$var163);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var163);
		}
	}

	private final void logProbabilityValue$sample180() {
		if(!fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double var177 = cpuVar[st[i$var174]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var178[i$var174] = cv$distributionAccumulator;
				logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = ((((fixedFlag$sample180 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample180[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var178[i$var174] = cv$rvAccumulator;
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
				double var182 = memVar[st[i$var174]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var183[i$var174] = cv$distributionAccumulator;
				logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = ((((fixedFlag$sample185 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample185[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var183[i$var174] = cv$rvAccumulator;
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
				double var187 = pageFaultsVar[st[i$var174]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var188[i$var174] = cv$distributionAccumulator;
				logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample39) && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$rvAccumulator = logProbability$sample190[i$var174];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var188[i$var174] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noStates; var29 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v, noStates));
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			logProbability$var18 = logProbability$var30;
			logProbability$m = (logProbability$m + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			logProbability$var34 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			logProbability$var34 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var37 = cv$distributionAccumulator;
			logProbability$var38 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedFlag$sample36);
		} else {
			logProbability$var37 = logProbability$var38;
			logProbability$st = (logProbability$st + logProbability$var38);
			logProbability$$model = (logProbability$$model + logProbability$var38);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				int cv$sampleValue = st[i$var50];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[(i$var50 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var55[(i$var50 - 1)] = cv$distributionAccumulator;
				logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = logProbability$sample57[(i$var50 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[(i$var50 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$sampleAccumulator = 0.0;
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var75] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			logProbability$var64 = cv$sampleAccumulator;
			logProbability$var76 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
			logProbability$var64 = logProbability$var76;
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var76);
			logProbability$$model = (logProbability$$model + logProbability$var76);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var76);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$sampleAccumulator = 0.0;
			for(int var93 = 0; var93 < noStates; var93 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var93] - 94.0)));
			logProbability$var82 = cv$sampleAccumulator;
			logProbability$var94 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			logProbability$var82 = logProbability$var94;
			logProbability$memMean = (logProbability$memMean + logProbability$var94);
			logProbability$$model = (logProbability$$model + logProbability$var94);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var94);
		}
	}

	private final void sample113(int var111, int threadID$cv$var111, Rng RNG$) {
		double cv$originalValue = pageFaultsMean[var111];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if(((var111 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var162 = st[0];
					if(((0 <= var162) && (var162 < noStates))) {
						double cv$temp$3$var187 = pageFaultsVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var187))) - (Math.log(cv$temp$3$var187) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[0];
						if(((0 <= var162) && (var162 < noStates))) {
							double cv$temp$9$var187 = pageFaultsVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var187)))) - (Math.log(cv$temp$9$var187) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var111 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							double cv$temp$21$var187 = pageFaultsVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$21$var187))) - (Math.log(cv$temp$21$var187) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var162 = st[i$var174];
							if(((0 <= var162) && (var162 < noStates))) {
								double cv$temp$27$var187 = pageFaultsVar[st[i$var174]];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$27$var187)))) - (Math.log(cv$temp$27$var187) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		pageFaultsMean[var111] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
		if(((var111 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var162 = st[0];
				if(((0 <= var162) && (var162 < noStates))) {
					double cv$temp$3$var187 = pageFaultsVar[st[0]];
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var187))) - (Math.log(cv$temp$3$var187) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var162 = st[0];
					if(((0 <= var162) && (var162 < noStates))) {
						double cv$temp$9$var187 = pageFaultsVar[st[0]];
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var187)))) - (Math.log(cv$temp$9$var187) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var111 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var162 = st[i$var174];
					if(((0 <= var162) && (var162 < noStates))) {
						double cv$temp$21$var187 = pageFaultsVar[st[i$var174]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$21$var187))) - (Math.log(cv$temp$21$var187) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							double cv$temp$27$var187 = pageFaultsVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$27$var187)))) - (Math.log(cv$temp$27$var187) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			pageFaultsMean[var111] = cv$originalValue;
	}

	private final void sample130(int var128, int threadID$cv$var128, Rng RNG$) {
		double cv$originalValue = cpuVar[var128];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if(((var128 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var75 = st[0];
					if(((0 <= var75) && (var75 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var128 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var75 = st[i$var174];
							if(((0 <= var75) && (var75 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		cpuVar[var128] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if(((var128 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var75 = st[0];
				if(((0 <= var75) && (var75 < noStates))) {
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var75 = st[0];
					if(((0 <= var75) && (var75 < noStates))) {
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var128 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var75 = st[i$var174];
					if(((0 <= var75) && (var75 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			cpuVar[var128] = cv$originalValue;
	}

	private final void sample147(int var145, int threadID$cv$var145, Rng RNG$) {
		double cv$originalValue = memVar[var145];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if(((var145 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var93 = st[0];
					if(((0 <= var93) && (var93 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var145 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var93 = st[i$var174];
							if(((0 <= var93) && (var93 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		memVar[var145] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if(((var145 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var93 = st[0];
				if(((0 <= var93) && (var93 < noStates))) {
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var93 = st[0];
					if(((0 <= var93) && (var93 < noStates))) {
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var145 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var93 = st[i$var174];
					if(((0 <= var93) && (var93 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			memVar[var145] = cv$originalValue;
	}

	private final void sample164(int var162, int threadID$cv$var162, Rng RNG$) {
		double cv$originalValue = pageFaultsVar[var162];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if(((var162 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var111 = st[0];
					if(((0 <= var111) && (var111 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var162 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var111 = st[i$var174];
							if(((0 <= var111) && (var111 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		pageFaultsVar[var162] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if(((var162 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var111 = st[0];
				if(((0 <= var111) && (var111 < noStates))) {
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var111 = st[0];
					if(((0 <= var111) && (var111 < noStates))) {
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var162 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var111 = st[i$var174];
					if(((0 <= var111) && (var111 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			pageFaultsVar[var162] = cv$originalValue;
	}

	private final void sample30(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample57) {
			if(((var29 == st[0]) && (1 < samples))) {
				if(fixedFlag$sample39)
					cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
				else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1)
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + distribution$sample39[index$sample39$3]);
				}
			}
			for(int i$var50 = 2; i$var50 < samples; i$var50 += 1) {
				if((var29 == st[(i$var50 - 1)]))
					cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + 1.0);
			}
		} else {
			if(((var29 == st[0]) && (1 < samples))) {
				if(fixedFlag$sample39) {
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample57[0][cv$loopIndex]);
				} else {
					for(int index$sample39$32 = 0; index$sample39$32 < noStates; index$sample39$32 += 1) {
						double cv$distributionProbability = distribution$sample39[index$sample39$32];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				if((var29 == st[(i$var50 - 1)])) {
					int index$i$40 = (i$var50 - 1);
					if((1 <= index$i$40)) {
						for(int index$sample57$41 = 0; index$sample57$41 < noStates; index$sample57$41 += 1) {
							double cv$distributionProbability = distribution$sample57[(index$i$40 - 1)][index$sample57$41];
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[(i$var50 - 1)][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29], noStates);
	}

	private final void sample36() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var35$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample39)
			cv$var35$countGlobal[st[0]] = (cv$var35$countGlobal[st[0]] + 1.0);
		else {
			for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
				cv$var35$countGlobal[cv$loopIndex] = (cv$var35$countGlobal[cv$loopIndex] + distribution$sample39[cv$loopIndex]);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var35$countGlobal, initialStateDistribution, noStates);
	}

	private final void sample39() {
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample57 && (1 < samples))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var29 = st[0];
				if(((0 <= var29) && (var29 < noStates))) {
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[1]) && (st[1] < noStates))?Math.log(m[cv$valuePos][st[1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
			if((0 < samples)) {
				guard$sample39gaussian179$global[0] = false;
				if(!guard$sample39gaussian179$global[0]) {
					guard$sample39gaussian179$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							double cv$temp$5$var177 = cpuVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$5$var177))) - (Math.log(cv$temp$5$var177) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				if(!guard$sample39gaussian179$global[0]) {
					guard$sample39gaussian179$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							double cv$temp$13$var177 = cpuVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$13$var177))) - (Math.log(cv$temp$13$var177) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				guard$sample39gaussian184$global[0] = false;
				if(!guard$sample39gaussian184$global[0]) {
					guard$sample39gaussian184$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							double cv$temp$21$var182 = memVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$21$var182))) - (Math.log(cv$temp$21$var182) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				if(!guard$sample39gaussian184$global[0]) {
					guard$sample39gaussian184$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							double cv$temp$29$var182 = memVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$29$var182))) - (Math.log(cv$temp$29$var182) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				guard$sample39gaussian189$global[0] = false;
				if(!guard$sample39gaussian189$global[0]) {
					guard$sample39gaussian189$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							double cv$temp$37$var187 = pageFaultsVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$37$var187))) - (Math.log(cv$temp$37$var187) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				if(!guard$sample39gaussian189$global[0]) {
					guard$sample39gaussian189$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 <= st[0])) {
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							double cv$temp$45$var187 = pageFaultsVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$45$var187))) - (Math.log(cv$temp$45$var187) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((!fixedFlag$sample57 && (1 < samples))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var29 = st[0];
				if(((0 <= var29) && (var29 < noStates))) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample57[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var38$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var38$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var38$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var38$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample39[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample39[cv$indexName] = Math.exp((cv$var38$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample39[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample57(int i$var50) {
		int cv$numNumStates = 0;
		if((1 == i$var50)) {
			if(fixedFlag$sample39) {
				int var29 = st[0];
				if(((0 <= var29) && (var29 < noStates)))
					cv$numNumStates = Math.max(0, noStates);
			} else {
				if((0 < noStates)) {
					int var29 = st[0];
					if(((0 <= var29) && (var29 < noStates)))
						cv$numNumStates = noStates;
				}
			}
		}
		if(fixedFlag$sample57) {
			if((2 <= i$var50)) {
				int var29 = st[(i$var50 - 1)];
				if(((0 <= var29) && (var29 < noStates)))
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$i$11 = (i$var50 - 1);
				if(((1 <= index$i$11) && !(index$i$11 == i$var50))) {
					int var29 = st[(i$var50 - 1)];
					if(((0 <= var29) && (var29 < noStates)))
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var50)) {
				if(fixedFlag$sample39) {
					int var29 = st[0];
					if(((0 <= var29) && (var29 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[0]][cv$valuePos]):Double.NEGATIVE_INFINITY);
						guard$sample57gaussian179$global[1] = false;
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var75 = st[1];
								if(((0 <= var75) && (var75 < noStates))) {
									double cv$temp$11$var177 = cpuVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$11$var177))) - (Math.log(cv$temp$11$var177) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var75 = st[1];
								if(((0 <= var75) && (var75 < noStates))) {
									double cv$temp$43$var177 = cpuVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$43$var177))) - (Math.log(cv$temp$43$var177) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						guard$sample57gaussian184$global[1] = false;
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var93 = st[1];
								if(((0 <= var93) && (var93 < noStates))) {
									double cv$temp$75$var182 = memVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$75$var182))) - (Math.log(cv$temp$75$var182) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var93 = st[1];
								if(((0 <= var93) && (var93 < noStates))) {
									double cv$temp$107$var182 = memVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$107$var182))) - (Math.log(cv$temp$107$var182) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						guard$sample57gaussian189$global[1] = false;
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var111 = st[1];
								if(((0 <= var111) && (var111 < noStates))) {
									double cv$temp$139$var187 = pageFaultsVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$139$var187))) - (Math.log(cv$temp$139$var187) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[1])) {
								int var111 = st[1];
								if(((0 <= var111) && (var111 < noStates))) {
									double cv$temp$171$var187 = pageFaultsVar[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$171$var187))) - (Math.log(cv$temp$171$var187) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample39$21 = 0; index$sample39$21 < noStates; index$sample39$21 += 1) {
						double cv$probabilitySample39Value22 = distribution$sample39[index$sample39$21];
						int var29 = st[0];
						if(((0 <= var29) && (var29 < noStates))) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value22);
							double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value22) + ((cv$valuePos < noStates)?Math.log(m[st[0]][cv$valuePos]):Double.NEGATIVE_INFINITY));
							guard$sample57gaussian179$global[1] = false;
							if(!guard$sample57gaussian179$global[1]) {
								guard$sample57gaussian179$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var75 = st[1];
									if(((0 <= var75) && (var75 < noStates))) {
										double cv$temp$19$var177 = cpuVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$19$var177))) - (Math.log(cv$temp$19$var177) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							if(!guard$sample57gaussian179$global[1]) {
								guard$sample57gaussian179$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var75 = st[1];
									if(((0 <= var75) && (var75 < noStates))) {
										double cv$temp$51$var177 = cpuVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$51$var177))) - (Math.log(cv$temp$51$var177) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							guard$sample57gaussian184$global[1] = false;
							if(!guard$sample57gaussian184$global[1]) {
								guard$sample57gaussian184$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var93 = st[1];
									if(((0 <= var93) && (var93 < noStates))) {
										double cv$temp$83$var182 = memVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$83$var182))) - (Math.log(cv$temp$83$var182) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							if(!guard$sample57gaussian184$global[1]) {
								guard$sample57gaussian184$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var93 = st[1];
									if(((0 <= var93) && (var93 < noStates))) {
										double cv$temp$115$var182 = memVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$115$var182))) - (Math.log(cv$temp$115$var182) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							guard$sample57gaussian189$global[1] = false;
							if(!guard$sample57gaussian189$global[1]) {
								guard$sample57gaussian189$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var111 = st[1];
									if(((0 <= var111) && (var111 < noStates))) {
										double cv$temp$147$var187 = pageFaultsVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$147$var187))) - (Math.log(cv$temp$147$var187) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
							if(!guard$sample57gaussian189$global[1]) {
								guard$sample57gaussian189$global[1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[1])) {
									int var111 = st[1];
									if(((0 <= var111) && (var111 < noStates))) {
										double cv$temp$179$var187 = pageFaultsVar[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$179$var187))) - (Math.log(cv$temp$179$var187) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
			int index$i$28 = (i$var50 - 1);
			if(((1 <= index$i$28) && !(index$i$28 == i$var50))) {
				for(int index$sample57$29 = 0; index$sample57$29 < noStates; index$sample57$29 += 1) {
					double cv$probabilitySample57Value30 = distribution$sample57[(index$i$28 - 1)][index$sample57$29];
					int var29 = st[(i$var50 - 1)];
					if(((0 <= var29) && (var29 < noStates))) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value30);
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value30) + ((cv$valuePos < noStates)?Math.log(m[cv$valuePos][cv$valuePos]):Double.NEGATIVE_INFINITY));
						guard$sample57gaussian179$global[i$var50] = false;
						if(!guard$sample57gaussian179$global[i$var50]) {
							guard$sample57gaussian179$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var75 = st[i$var50];
								if(((0 <= var75) && (var75 < noStates))) {
									double cv$temp$35$var177 = cpuVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[index$sample57$29]) / Math.sqrt(cv$temp$35$var177))) - (Math.log(cv$temp$35$var177) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian179$global[i$var50]) {
							guard$sample57gaussian179$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var75 = st[i$var50];
								if(((0 <= var75) && (var75 < noStates))) {
									double cv$temp$67$var177 = cpuVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[index$sample57$29]) / Math.sqrt(cv$temp$67$var177))) - (Math.log(cv$temp$67$var177) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						guard$sample57gaussian184$global[i$var50] = false;
						if(!guard$sample57gaussian184$global[i$var50]) {
							guard$sample57gaussian184$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var93 = st[i$var50];
								if(((0 <= var93) && (var93 < noStates))) {
									double cv$temp$99$var182 = memVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[index$sample57$29]) / Math.sqrt(cv$temp$99$var182))) - (Math.log(cv$temp$99$var182) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian184$global[i$var50]) {
							guard$sample57gaussian184$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var93 = st[i$var50];
								if(((0 <= var93) && (var93 < noStates))) {
									double cv$temp$131$var182 = memVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[index$sample57$29]) / Math.sqrt(cv$temp$131$var182))) - (Math.log(cv$temp$131$var182) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						guard$sample57gaussian189$global[i$var50] = false;
						if(!guard$sample57gaussian189$global[i$var50]) {
							guard$sample57gaussian189$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var111 = st[i$var50];
								if(((0 <= var111) && (var111 < noStates))) {
									double cv$temp$163$var187 = pageFaultsVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[index$sample57$29]) / Math.sqrt(cv$temp$163$var187))) - (Math.log(cv$temp$163$var187) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						if(!guard$sample57gaussian189$global[i$var50]) {
							guard$sample57gaussian189$global[i$var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((0 <= st[i$var50])) {
								int var111 = st[i$var50];
								if(((0 <= var111) && (var111 < noStates))) {
									double cv$temp$195$var187 = pageFaultsVar[index$sample57$29];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[index$sample57$29]) / Math.sqrt(cv$temp$195$var187))) - (Math.log(cv$temp$195$var187) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = 0.0;
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
			int index$i$621_2 = (i$var50 + 1);
			if((index$i$621_2 < samples)) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var29 = st[(index$i$621_2 - 1)];
				if(((0 <= var29) && (var29 < noStates))) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var50)) {
						if(fixedFlag$sample39) {
							int index$var29$630_1 = st[0];
							if(((0 <= index$var29$630_1) && (index$var29$630_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample39$626 = 0; index$sample39$626 < noStates; index$sample39$626 += 1) {
								int index$var29$631_1 = st[0];
								if(((0 <= index$var29$631_1) && (index$var29$631_1 < noStates)))
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample39[index$sample39$626]);
							}
						}
					}
					int index$i$633 = (i$var50 - 1);
					if((((1 <= index$i$633) && !(index$i$633 == i$var50)) && !(index$i$633 == index$i$621_2))) {
						for(int index$sample57$634 = 0; index$sample57$634 < noStates; index$sample57$634 += 1) {
							int index$var29$639_1 = st[(i$var50 - 1)];
							if(((0 <= index$var29$639_1) && (index$var29$639_1 < noStates)))
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[(index$i$633 - 1)][index$sample57$634]);
						}
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample57[(index$i$621_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample57[(i$var50 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample77(int var75, int threadID$cv$var75, Rng RNG$) {
		double cv$originalValue = cpuMean[var75];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if(((var75 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var128 = st[0];
					if(((0 <= var128) && (var128 < noStates))) {
						double cv$temp$3$var177 = cpuVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var177))) - (Math.log(cv$temp$3$var177) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[0];
						if(((0 <= var128) && (var128 < noStates))) {
							double cv$temp$9$var177 = cpuVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var177)))) - (Math.log(cv$temp$9$var177) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var75 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							double cv$temp$21$var177 = cpuVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$21$var177))) - (Math.log(cv$temp$21$var177) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var128 = st[i$var174];
							if(((0 <= var128) && (var128 < noStates))) {
								double cv$temp$27$var177 = cpuVar[st[i$var174]];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$27$var177)))) - (Math.log(cv$temp$27$var177) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		cpuMean[var75] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
		if(((var75 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var128 = st[0];
				if(((0 <= var128) && (var128 < noStates))) {
					double cv$temp$3$var177 = cpuVar[st[0]];
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var177))) - (Math.log(cv$temp$3$var177) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var128 = st[0];
					if(((0 <= var128) && (var128 < noStates))) {
						double cv$temp$9$var177 = cpuVar[st[0]];
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var177)))) - (Math.log(cv$temp$9$var177) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var75 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var128 = st[i$var174];
					if(((0 <= var128) && (var128 < noStates))) {
						double cv$temp$21$var177 = cpuVar[st[i$var174]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$21$var177))) - (Math.log(cv$temp$21$var177) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							double cv$temp$27$var177 = cpuVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$27$var177)))) - (Math.log(cv$temp$27$var177) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			cpuMean[var75] = cv$originalValue;
	}

	private final void sample95(int var93, int threadID$cv$var93, Rng RNG$) {
		double cv$originalValue = memMean[var93];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			if(((var93 == st[0]) && (0 < samples))) {
				if(fixedFlag$sample39) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var145 = st[0];
					if(((0 <= var145) && (var145 < noStates))) {
						double cv$temp$3$var182 = memVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var182))) - (Math.log(cv$temp$3$var182) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
						double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[0];
						if(((0 <= var145) && (var145 < noStates))) {
							double cv$temp$9$var182 = memVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var182)))) - (Math.log(cv$temp$9$var182) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if((var93 == st[i$var174])) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							double cv$temp$21$var182 = memVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$21$var182))) - (Math.log(cv$temp$21$var182) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					} else {
						for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
							double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var145 = st[i$var174];
							if(((0 <= var145) && (var145 < noStates))) {
								double cv$temp$27$var182 = memVar[st[i$var174]];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(cv$temp$27$var182)))) - (Math.log(cv$temp$27$var182) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		memMean[var93] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
		if(((var93 == st[0]) && (0 < samples))) {
			if(fixedFlag$sample39) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var145 = st[0];
				if(((0 <= var145) && (var145 < noStates))) {
					double cv$temp$3$var182 = memVar[st[0]];
					cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var182))) - (Math.log(cv$temp$3$var182) * 0.5));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			} else {
				for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
					double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var145 = st[0];
					if(((0 <= var145) && (var145 < noStates))) {
						double cv$temp$9$var182 = memVar[st[0]];
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample39Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var182)))) - (Math.log(cv$temp$9$var182) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample39Value4);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
			if((var93 == st[i$var174])) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var145 = st[i$var174];
					if(((0 <= var145) && (var145 < noStates))) {
						double cv$temp$21$var182 = memVar[st[i$var174]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$21$var182))) - (Math.log(cv$temp$21$var182) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				} else {
					for(int index$sample57$12 = 0; index$sample57$12 < noStates; index$sample57$12 += 1) {
						double cv$probabilitySample57Value13 = distribution$sample57[(i$var174 - 1)][index$sample57$12];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							double cv$temp$27$var182 = memVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(cv$temp$27$var182)))) - (Math.log(cv$temp$27$var182) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value13);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			memMean[var93] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var30$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[noStates];
		cv$var35$countGlobal = new double[noStates];
		cv$distributionAccumulator$var55 = new double[noStates];
		cv$var38$stateProbabilityGlobal = new double[noStates];
		guard$sample39gaussian179$global = new boolean[length$cpu_measured];
		guard$sample39gaussian184$global = new boolean[length$cpu_measured];
		guard$sample39gaussian189$global = new boolean[length$cpu_measured];
		cv$var56$stateProbabilityGlobal = new double[noStates];
		guard$sample57gaussian179$global = new boolean[length$cpu_measured];
		guard$sample57gaussian184$global = new boolean[length$cpu_measured];
		guard$sample57gaussian189$global = new boolean[length$cpu_measured];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var29 = 0; var29 < noStates; var29 += 1)
				m[var29] = new double[noStates];
		}
		if(!setFlag$st)
			st = new int[length$cpu_measured];
		if(!setFlag$initialStateDistribution)
			initialStateDistribution = new double[noStates];
		if(!setFlag$cpu)
			cpu = new double[length$cpu_measured];
		if(!setFlag$mem)
			mem = new double[length$cpu_measured];
		if(!setFlag$pageFaults)
			pageFaults = new double[length$cpu_measured];
		if(!setFlag$cpuMean)
			cpuMean = new double[noStates];
		if(!setFlag$memMean)
			memMean = new double[noStates];
		if(!setFlag$pageFaultsMean)
			pageFaultsMean = new double[noStates];
		if(!setFlag$cpuVar)
			cpuVar = new double[noStates];
		if(!setFlag$memVar)
			memVar = new double[noStates];
		if(!setFlag$pageFaultsVar)
			pageFaultsVar = new double[noStates];
		distribution$sample39 = new double[noStates];
		distribution$sample57 = new double[(length$cpu_measured - 1)][];
		for(int i$var50 = 1; i$var50 < length$cpu_measured; i$var50 += 1)
			distribution$sample57[(i$var50 - 1)] = new double[noStates];
		logProbability$var55 = new double[(length$cpu_measured - 1)];
		logProbability$sample57 = new double[(length$cpu_measured - 1)];
		logProbability$var178 = new double[length$cpu_measured];
		logProbability$sample180 = new double[length$cpu_measured];
		logProbability$var183 = new double[length$cpu_measured];
		logProbability$sample185 = new double[length$cpu_measured];
		logProbability$var188 = new double[length$cpu_measured];
		logProbability$sample190 = new double[length$cpu_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!fixedFlag$sample113)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!fixedFlag$sample130)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample147)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample164)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						if(!fixedFlag$sample180)
							cpu[i$var174] = ((Math.sqrt(cpuVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var174]]);
						if(!fixedFlag$sample185)
							mem[i$var174] = ((Math.sqrt(memVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var174]]);
						if(!fixedFlag$sample190)
							pageFaults[i$var174] = ((Math.sqrt(pageFaultsVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var174]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39) {
			for(int index$var37 = 0; index$var37 < noStates; index$var37 += 1)
				distribution$sample39[index$var37] = initialStateDistribution[index$var37];
		}
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				double[] cv$distribution$sample57 = distribution$sample57[(i$var50 - 1)];
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					cv$distribution$sample57[index$var55] = 0.0;
				if((1 == i$var50)) {
					if(fixedFlag$sample39) {
						int var29 = st[0];
						if(((0 <= var29) && (var29 < noStates))) {
							double[] var54 = m[st[0]];
							for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + var54[index$var55]);
						}
					} else {
						for(int index$sample39$2 = 0; index$sample39$2 < noStates; index$sample39$2 += 1) {
							double cv$probabilitySample39Value3 = distribution$sample39[index$sample39$2];
							int var29 = st[0];
							if(((0 <= var29) && (var29 < noStates))) {
								double[] var54 = m[st[0]];
								for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
									cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * var54[index$var55]));
							}
						}
					}
				}
				int index$i$9 = (i$var50 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample57$10 = 0; index$sample57$10 < noStates; index$sample57$10 += 1) {
						double cv$probabilitySample57Value11 = distribution$sample57[(index$i$9 - 1)][index$sample57$10];
						int var29 = st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < noStates))) {
							double[] var54 = m[st[(i$var50 - 1)]];
							for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * var54[index$var55]));
						}
					}
				}
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		if(!fixedFlag$sample77)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!fixedFlag$sample113)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!fixedFlag$sample130)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample147)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample164)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!fixedFlag$sample113)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!fixedFlag$sample130)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample147)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample164)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample30)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample30(var29, threadID$var29, RNG$1);
					}
				);

			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample39)
				sample39();
			if(!fixedFlag$sample57) {
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
					sample57(i$var50);
			}
			if(!fixedFlag$sample77)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								sample77(var75, threadID$var75, RNG$1);
					}
				);

			if(!fixedFlag$sample95)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								sample95(var93, threadID$var93, RNG$1);
					}
				);

			if(!fixedFlag$sample113)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								sample113(var111, threadID$var111, RNG$1);
					}
				);

			if(!fixedFlag$sample130)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								sample130(var128, threadID$var128, RNG$1);
					}
				);

			if(!fixedFlag$sample147)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								sample147(var145, threadID$var145, RNG$1);
					}
				);

			if(!fixedFlag$sample164)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								sample164(var162, threadID$var162, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample164)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								sample164(var162, threadID$var162, RNG$1);
					}
				);

			if(!fixedFlag$sample147)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								sample147(var145, threadID$var145, RNG$1);
					}
				);

			if(!fixedFlag$sample130)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								sample130(var128, threadID$var128, RNG$1);
					}
				);

			if(!fixedFlag$sample113)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								sample113(var111, threadID$var111, RNG$1);
					}
				);

			if(!fixedFlag$sample95)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								sample95(var93, threadID$var93, RNG$1);
					}
				);

			if(!fixedFlag$sample77)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								sample77(var75, threadID$var75, RNG$1);
					}
				);

			if(!fixedFlag$sample57) {
				for(int i$var50 = (samples - 1); i$var50 >= 1; i$var50 -= 1)
					sample57(i$var50);
			}
			if(!fixedFlag$sample39)
				sample39();
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample30)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample30(var29, threadID$var29, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
						v[var15] = 0.1;
			}
		);
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
			logProbability$var55[(i$var50 - 1)] = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				logProbability$sample57[(i$var50 - 1)] = 0.0;
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
			logProbability$var178[i$var174] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample180) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample180[i$var174] = 0.0;
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
			logProbability$var183[i$var174] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample185[i$var174] = 0.0;
		}
		for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
			logProbability$var188[i$var174] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample190[i$var174] = 0.0;
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
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!fixedFlag$sample113)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!fixedFlag$sample130)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample147)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!fixedFlag$sample164)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int cv$length1 = cpu.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cpu[cv$index1] = cpu_measured[cv$index1];
		}
		{
			int cv$length1 = mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				mem[cv$index1] = mem_measured[cv$index1];
		}
		int cv$length1 = pageFaults.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			pageFaults[cv$index1] = pageFaults_measured[cv$index1];
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