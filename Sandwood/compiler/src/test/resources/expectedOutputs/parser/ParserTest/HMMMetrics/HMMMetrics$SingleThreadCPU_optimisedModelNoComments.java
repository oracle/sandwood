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
	public final boolean get$fixedFlag$sample100() {
		return fixedFlag$sample100;
	}

	@Override
	public final void set$fixedFlag$sample100(boolean cv$value) {
		fixedFlag$sample100 = cv$value;
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample110() {
		return fixedFlag$sample110;
	}

	@Override
	public final void set$fixedFlag$sample110(boolean cv$value) {
		fixedFlag$sample110 = cv$value;
		fixedProbFlag$sample110 = (cv$value && fixedProbFlag$sample110);
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample119() {
		return fixedFlag$sample119;
	}

	@Override
	public final void set$fixedFlag$sample119(boolean cv$value) {
		fixedFlag$sample119 = cv$value;
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		fixedFlag$sample124 = cv$value;
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample129() {
		return fixedFlag$sample129;
	}

	@Override
	public final void set$fixedFlag$sample129(boolean cv$value) {
		fixedFlag$sample129 = cv$value;
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		fixedFlag$sample25 = cv$value;
		fixedProbFlag$sample25 = (cv$value && fixedProbFlag$sample25);
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
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

	private final void logProbabilityDistribution$sample119() {
		if(!fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = cpu[i$var109];
				if((0 == i$var109)) {
					if(fixedFlag$sample35) {
						if((0 <= st[0])) {
							int var52 = st[0];
							if(((0 <= var52) && (var52 < noStates))) {
								double var112 = cpuVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var112))) - (Math.log(var112) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var112 = cpuVar[index$sample35$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample35$3]) / Math.sqrt(var112)))) - (Math.log(var112) * 0.5));
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
				if((1 <= i$var109)) {
					if(fixedFlag$sample45) {
						if((0 <= st[i$var109])) {
							int var52 = st[i$var109];
							if(((0 <= var52) && (var52 < noStates))) {
								double var112 = cpuVar[st[i$var109]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var109]]) / Math.sqrt(var112))) - (Math.log(var112) * 0.5));
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
					} else {
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var112 = cpuVar[index$sample45$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample45$43]) / Math.sqrt(var112)))) - (Math.log(var112) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var113[i$var109] = cv$distributionAccumulator;
				logProbability$sample119[i$var109] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample119[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[i$var109] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = mem[i$var109];
				if((0 == i$var109)) {
					if(fixedFlag$sample35) {
						if((0 <= st[0])) {
							int var63 = st[0];
							if(((0 <= var63) && (var63 < noStates))) {
								double var117 = memVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var117))) - (Math.log(var117) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var117 = memVar[index$sample35$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample35$3]) / Math.sqrt(var117)))) - (Math.log(var117) * 0.5));
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
				if((1 <= i$var109)) {
					if(fixedFlag$sample45) {
						if((0 <= st[i$var109])) {
							int var63 = st[i$var109];
							if(((0 <= var63) && (var63 < noStates))) {
								double var117 = memVar[st[i$var109]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var109]]) / Math.sqrt(var117))) - (Math.log(var117) * 0.5));
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
					} else {
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var117 = memVar[index$sample45$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample45$43]) / Math.sqrt(var117)))) - (Math.log(var117) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var118[i$var109] = cv$distributionAccumulator;
				logProbability$sample124[i$var109] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample124[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[i$var109] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = pageFaults[i$var109];
				if((0 == i$var109)) {
					if(fixedFlag$sample35) {
						if((0 <= st[0])) {
							int var74 = st[0];
							if(((0 <= var74) && (var74 < noStates))) {
								double var122 = pageFaultsVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var122))) - (Math.log(var122) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var122 = pageFaultsVar[index$sample35$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample35$3]) / Math.sqrt(var122)))) - (Math.log(var122) * 0.5));
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
				if((1 <= i$var109)) {
					if(fixedFlag$sample45) {
						if((0 <= st[i$var109])) {
							int var74 = st[i$var109];
							if(((0 <= var74) && (var74 < noStates))) {
								double var122 = pageFaultsVar[st[i$var109]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var109]]) / Math.sqrt(var122))) - (Math.log(var122) * 0.5));
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
					} else {
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var122 = pageFaultsVar[index$sample45$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample45$43]) / Math.sqrt(var122)))) - (Math.log(var122) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var123[i$var109] = cv$distributionAccumulator;
				logProbability$sample129[i$var109] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample129[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var123[i$var109] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample35() {
		if(!fixedProbFlag$sample35) {
			if(fixedFlag$sample35) {
				int cv$sampleValue = st[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var29 = cv$distributionAccumulator;
				logProbability$var30 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample35 = fixedFlag$sample32;
			}
		} else {
			logProbability$var29 = logProbability$var30;
			if(fixedFlag$sample35)
				logProbability$st = (logProbability$st + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!fixedProbFlag$sample45) {
			if(fixedFlag$sample45) {
				double cv$accumulator = 0.0;
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var34];
					if((1 == i$var34)) {
						if(fixedFlag$sample35) {
							int var21 = st[0];
							if(((0 <= var21) && (var21 < noStates))) {
								double[] var38 = m[st[0]];
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample35$4 = 0; index$sample35$4 < noStates; index$sample35$4 += 1) {
								double cv$probabilitySample35Value5 = distribution$sample35[index$sample35$4];
								double[] var38 = m[index$sample35$4];
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
					if((2 <= i$var34)) {
						int var21 = st[(i$var34 - 1)];
						if(((0 <= var21) && (var21 < noStates))) {
							double[] var38 = m[st[(i$var34 - 1)]];
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$var39[(i$var34 - 1)] = cv$distributionAccumulator;
					logProbability$sample45[(i$var34 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample45 = (fixedFlag$sample25 && fixedFlag$sample35);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = logProbability$sample45[(i$var34 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[(i$var34 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var94 = 0; var94 < noStates; var94 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var94], 5.0, 0.5));
			logProbability$var90 = cv$sampleAccumulator;
			logProbability$var95 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample100)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample100 = fixedFlag$sample100;
		} else {
			logProbability$var90 = logProbability$var95;
			logProbability$memVar = (logProbability$memVar + logProbability$var95);
			logProbability$$model = (logProbability$$model + logProbability$var95);
			if(fixedFlag$sample100)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var95);
		}
	}

	private final void logProbabilityValue$sample110() {
		if(!fixedProbFlag$sample110) {
			double cv$sampleAccumulator = 0.0;
			for(int var104 = 0; var104 < noStates; var104 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var104], 5.0, 0.5));
			logProbability$var100 = cv$sampleAccumulator;
			logProbability$var105 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample110)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample110 = fixedFlag$sample110;
		} else {
			logProbability$var100 = logProbability$var105;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var105);
			logProbability$$model = (logProbability$$model + logProbability$var105);
			if(fixedFlag$sample110)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var105);
		}
	}

	private final void logProbabilityValue$sample119() {
		if(!fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double var112 = cpuVar[st[i$var109]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(var112))) - (Math.log(var112) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var113[i$var109] = cv$distributionAccumulator;
				logProbability$sample119[i$var109] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample119[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var113[i$var109] = cv$rvAccumulator;
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
				double var117 = memVar[st[i$var109]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(var117))) - (Math.log(var117) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var118[i$var109] = cv$distributionAccumulator;
				logProbability$sample124[i$var109] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample124[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var118[i$var109] = cv$rvAccumulator;
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
				double var122 = pageFaultsVar[st[i$var109]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(var122))) - (Math.log(var122) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var123[i$var109] = cv$distributionAccumulator;
				logProbability$sample129[i$var109] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double cv$rvAccumulator = logProbability$sample129[i$var109];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var123[i$var109] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample25() {
		if(!fixedProbFlag$sample25) {
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noStates; var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var21], v));
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample25 = fixedFlag$sample25;
		} else {
			logProbability$var17 = logProbability$var22;
			logProbability$m = (logProbability$m + logProbability$var22);
			logProbability$$model = (logProbability$$model + logProbability$var22);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var26 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			logProbability$var26 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var29 = cv$distributionAccumulator;
			logProbability$var30 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample32);
		} else {
			logProbability$var29 = logProbability$var30;
			logProbability$st = (logProbability$st + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				int cv$sampleValue = st[i$var34];
				double[] var38 = m[st[(i$var34 - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var39[(i$var34 - 1)] = cv$distributionAccumulator;
				logProbability$sample45[(i$var34 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample25) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double cv$rvAccumulator = logProbability$sample45[(i$var34 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[(i$var34 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$sampleAccumulator = 0.0;
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var52] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var53 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			logProbability$var48 = logProbability$var53;
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var53);
			logProbability$$model = (logProbability$$model + logProbability$var53);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
			double cv$sampleAccumulator = 0.0;
			for(int var63 = 0; var63 < noStates; var63 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var63] - 94.0)));
			logProbability$var59 = cv$sampleAccumulator;
			logProbability$var64 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample69 = fixedFlag$sample69;
		} else {
			logProbability$var59 = logProbability$var64;
			logProbability$memMean = (logProbability$memMean + logProbability$var64);
			logProbability$$model = (logProbability$$model + logProbability$var64);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var64);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var74] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			logProbability$var70 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample80 = fixedFlag$sample80;
		} else {
			logProbability$var70 = logProbability$var75;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var75);
			logProbability$$model = (logProbability$$model + logProbability$var75);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var84], 5.0, 0.5));
			logProbability$var80 = cv$sampleAccumulator;
			logProbability$var85 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample90 = fixedFlag$sample90;
		} else {
			logProbability$var80 = logProbability$var85;
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var85);
			logProbability$$model = (logProbability$$model + logProbability$var85);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var85);
		}
	}

	private final void sample100(int var94) {
		double cv$originalValue = memVar[var94];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var94 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var63 = st[0];
						if(((0 <= var63) && (var63 < noStates))) {
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
					}
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var94];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var94]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var94 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var63 = st[i$var109];
						if(((0 <= var63) && (var63 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var94];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[var94]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		memVar[var94] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var94 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var63 = st[0];
					if(((0 <= var63) && (var63 < noStates))) {
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
				}
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var94];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var94]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var94 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var63 = st[i$var109];
					if(((0 <= var63) && (var63 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var94];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[var94]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			memVar[var94] = cv$originalValue;
	}

	private final void sample110(int var104) {
		double cv$originalValue = pageFaultsVar[var104];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var104 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var74 = st[0];
						if(((0 <= var74) && (var74 < noStates))) {
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
					}
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var104];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var104]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var104 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var74 = st[i$var109];
						if(((0 <= var74) && (var74 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var104];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[var104]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		pageFaultsVar[var104] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var104 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var74 = st[0];
					if(((0 <= var74) && (var74 < noStates))) {
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
				}
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var104];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var104]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var104 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var74 = st[i$var109];
					if(((0 <= var74) && (var74 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var104];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[var104]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			pageFaultsVar[var104] = cv$originalValue;
	}

	private final void sample25(int var21) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var22$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample45) {
			if((1 < samples)) {
				if(fixedFlag$sample35) {
					if((var21 == st[0]))
						cv$var22$countGlobal[st[1]] = (cv$var22$countGlobal[st[1]] + 1.0);
				} else
					cv$var22$countGlobal[st[1]] = (cv$var22$countGlobal[st[1]] + distribution$sample35[var21]);
			}
			for(int i$var34 = 2; i$var34 < samples; i$var34 += 1) {
				if((var21 == st[(i$var34 - 1)]))
					cv$var22$countGlobal[st[i$var34]] = (cv$var22$countGlobal[st[i$var34]] + 1.0);
			}
		} else {
			if((1 < samples)) {
				if(fixedFlag$sample35) {
					if((var21 == st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + distribution$sample45[0][cv$loopIndex]);
					}
				} else {
					double cv$distributionProbability = distribution$sample35[var21];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + (distribution$sample45[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				int index$i$40 = (i$var34 - 1);
				if((1 <= index$i$40)) {
					double cv$distributionProbability = distribution$sample45[(index$i$40 - 1)][var21];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + (distribution$sample45[(i$var34 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var22$countGlobal, m[var21]);
	}

	private final void sample32() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample35)
			cv$var27$countGlobal[st[0]] = (cv$var27$countGlobal[st[0]] + 1.0);
		else {
			for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
				cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample35[cv$loopIndex]);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, initialStateDistribution);
	}

	private final void sample35() {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample45 && (1 < samples))) {
				double[] cv$temp$1$var38 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples)) {
				guard$sample35gaussian118$global[0] = false;
				if(!guard$sample35gaussian118$global[0]) {
					guard$sample35gaussian118$global[0] = true;
					double cv$temp$3$var112 = cpuVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$3$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var112) * 0.5));
				}
				if(!guard$sample35gaussian118$global[0]) {
					guard$sample35gaussian118$global[0] = true;
					double cv$temp$11$var112 = cpuVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$11$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$11$var112) * 0.5));
				}
				guard$sample35gaussian123$global[0] = false;
				if(!guard$sample35gaussian123$global[0]) {
					guard$sample35gaussian123$global[0] = true;
					double cv$temp$19$var117 = memVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$19$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$19$var117) * 0.5));
				}
				if(!guard$sample35gaussian123$global[0]) {
					guard$sample35gaussian123$global[0] = true;
					double cv$temp$27$var117 = memVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$27$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$27$var117) * 0.5));
				}
				guard$sample35gaussian128$global[0] = false;
				if(!guard$sample35gaussian128$global[0]) {
					guard$sample35gaussian128$global[0] = true;
					double cv$temp$35$var122 = pageFaultsVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$35$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$35$var122) * 0.5));
				}
				if(!guard$sample35gaussian128$global[0]) {
					guard$sample35gaussian128$global[0] = true;
					double cv$temp$43$var122 = pageFaultsVar[cv$valuePos];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$43$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$43$var122) * 0.5));
				}
			}
			if((!fixedFlag$sample45 && (1 < samples))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var39[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var39, 1.0, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample45[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = cv$distributionAccumulator$var39[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$var30$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var30$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var30$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var30$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var30$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var30$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var30$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample35[cv$indexName] = (1.0 / cv$var30$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var30$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample35[cv$indexName] = Math.exp((cv$var30$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample45(int i$var34) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var34)) {
				if(fixedFlag$sample35) {
					int var21 = st[0];
					if(((0 <= var21) && (var21 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var38 = m[st[0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var38.length)?Math.log(cv$temp$0$var38[cv$valuePos]):Double.NEGATIVE_INFINITY);
						guard$sample45gaussian118$global[1] = false;
						if(!guard$sample45gaussian118$global[1]) {
							guard$sample45gaussian118$global[1] = true;
							double cv$temp$7$var112 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$7$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$7$var112) * 0.5));
						}
						if(!guard$sample45gaussian118$global[1]) {
							guard$sample45gaussian118$global[1] = true;
							double cv$temp$39$var112 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$39$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$39$var112) * 0.5));
						}
						guard$sample45gaussian123$global[1] = false;
						if(!guard$sample45gaussian123$global[1]) {
							guard$sample45gaussian123$global[1] = true;
							double cv$temp$71$var117 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$71$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$71$var117) * 0.5));
						}
						if(!guard$sample45gaussian123$global[1]) {
							guard$sample45gaussian123$global[1] = true;
							double cv$temp$103$var117 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$103$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$103$var117) * 0.5));
						}
						guard$sample45gaussian128$global[1] = false;
						if(!guard$sample45gaussian128$global[1]) {
							guard$sample45gaussian128$global[1] = true;
							double cv$temp$135$var122 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$135$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$135$var122) * 0.5));
						}
						if(!guard$sample45gaussian128$global[1]) {
							guard$sample45gaussian128$global[1] = true;
							double cv$temp$167$var122 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$167$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$167$var122) * 0.5));
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
						double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value4);
						double[] cv$temp$1$var38 = m[index$sample35$3];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample35Value4) + ((cv$valuePos < cv$temp$1$var38.length)?Math.log(cv$temp$1$var38[cv$valuePos]):Double.NEGATIVE_INFINITY));
						guard$sample45gaussian118$global[1] = false;
						if(!guard$sample45gaussian118$global[1]) {
							guard$sample45gaussian118$global[1] = true;
							double cv$temp$15$var112 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$15$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$15$var112) * 0.5));
						}
						if(!guard$sample45gaussian118$global[1]) {
							guard$sample45gaussian118$global[1] = true;
							double cv$temp$47$var112 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$47$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$47$var112) * 0.5));
						}
						guard$sample45gaussian123$global[1] = false;
						if(!guard$sample45gaussian123$global[1]) {
							guard$sample45gaussian123$global[1] = true;
							double cv$temp$79$var117 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$79$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$79$var117) * 0.5));
						}
						if(!guard$sample45gaussian123$global[1]) {
							guard$sample45gaussian123$global[1] = true;
							double cv$temp$111$var117 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$111$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$111$var117) * 0.5));
						}
						guard$sample45gaussian128$global[1] = false;
						if(!guard$sample45gaussian128$global[1]) {
							guard$sample45gaussian128$global[1] = true;
							double cv$temp$143$var122 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$143$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$143$var122) * 0.5));
						}
						if(!guard$sample45gaussian128$global[1]) {
							guard$sample45gaussian128$global[1] = true;
							double cv$temp$175$var122 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$175$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$175$var122) * 0.5));
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
			int index$i$10 = (i$var34 - 1);
			if(((1 <= index$i$10) && !(index$i$10 == i$var34))) {
				for(int index$sample45$11 = 0; index$sample45$11 < noStates; index$sample45$11 += 1) {
					double cv$probabilitySample45Value12 = distribution$sample45[(index$i$10 - 1)][index$sample45$11];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample45Value12);
					double[] cv$temp$3$var38 = m[index$sample45$11];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample45Value12) + ((cv$valuePos < cv$temp$3$var38.length)?Math.log(cv$temp$3$var38[cv$valuePos]):Double.NEGATIVE_INFINITY));
					guard$sample45gaussian118$global[i$var34] = false;
					if(!guard$sample45gaussian118$global[i$var34]) {
						guard$sample45gaussian118$global[i$var34] = true;
						double cv$temp$31$var112 = cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[i$var34] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$31$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$31$var112) * 0.5));
					}
					if(!guard$sample45gaussian118$global[i$var34]) {
						guard$sample45gaussian118$global[i$var34] = true;
						double cv$temp$63$var112 = cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[i$var34] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$63$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$63$var112) * 0.5));
					}
					guard$sample45gaussian123$global[i$var34] = false;
					if(!guard$sample45gaussian123$global[i$var34]) {
						guard$sample45gaussian123$global[i$var34] = true;
						double cv$temp$95$var117 = memVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[i$var34] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$95$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$95$var117) * 0.5));
					}
					if(!guard$sample45gaussian123$global[i$var34]) {
						guard$sample45gaussian123$global[i$var34] = true;
						double cv$temp$127$var117 = memVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[i$var34] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$127$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$127$var117) * 0.5));
					}
					guard$sample45gaussian128$global[i$var34] = false;
					if(!guard$sample45gaussian128$global[i$var34]) {
						guard$sample45gaussian128$global[i$var34] = true;
						double cv$temp$159$var122 = pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[i$var34] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$159$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$159$var122) * 0.5));
					}
					if(!guard$sample45gaussian128$global[i$var34]) {
						guard$sample45gaussian128$global[i$var34] = true;
						double cv$temp$191$var122 = pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[i$var34] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$191$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$191$var122) * 0.5));
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
			int index$i$603_2 = (i$var34 + 1);
			if((index$i$603_2 < samples)) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var39[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var34)) {
					if(fixedFlag$sample35) {
						int index$var21$612_1 = st[0];
						if(((0 <= index$var21$612_1) && (index$var21$612_1 < noStates)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample35$608 = 0; index$sample35$608 < noStates; index$sample35$608 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample35[index$sample35$608]);
					}
				}
				int index$i$615 = (i$var34 - 1);
				if((((1 <= index$i$615) && !(index$i$615 == i$var34)) && !(index$i$615 == index$i$603_2))) {
					for(int index$sample45$616 = 0; index$sample45$616 < noStates; index$sample45$616 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample45[(index$i$615 - 1)][index$sample45$616]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var39, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample45[(index$i$603_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var39[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var40$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample45[(i$var34 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var40$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var40$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var40$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var40$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var40$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var40$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var40$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var40$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var40$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample58(int var52) {
		double cv$originalValue = cpuMean[var52];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var52 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var84 = st[0];
						if(((0 <= var84) && (var84 < noStates))) {
							double cv$temp$3$var112 = cpuVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var112))) - (Math.log(cv$temp$3$var112) * 0.5));
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
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var52];
					double cv$temp$9$var112 = cpuVar[var52];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var112)))) - (Math.log(cv$temp$9$var112) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var52 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var84 = st[i$var109];
						if(((0 <= var84) && (var84 < noStates))) {
							double cv$temp$21$var112 = cpuVar[st[i$var109]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var112))) - (Math.log(cv$temp$21$var112) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var52];
					double cv$temp$27$var112 = cpuVar[var52];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var112)))) - (Math.log(cv$temp$27$var112) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		cpuMean[var52] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var52 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var84 = st[0];
					if(((0 <= var84) && (var84 < noStates))) {
						double cv$temp$3$var112 = cpuVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var112))) - (Math.log(cv$temp$3$var112) * 0.5));
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
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var52];
				double cv$temp$9$var112 = cpuVar[var52];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var112)))) - (Math.log(cv$temp$9$var112) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var52 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var84 = st[i$var109];
					if(((0 <= var84) && (var84 < noStates))) {
						double cv$temp$21$var112 = cpuVar[st[i$var109]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var112))) - (Math.log(cv$temp$21$var112) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var52];
				double cv$temp$27$var112 = cpuVar[var52];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var112)))) - (Math.log(cv$temp$27$var112) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			cpuMean[var52] = cv$originalValue;
	}

	private final void sample69(int var63) {
		double cv$originalValue = memMean[var63];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var63 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var94 = st[0];
						if(((0 <= var94) && (var94 < noStates))) {
							double cv$temp$3$var117 = memVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var117))) - (Math.log(cv$temp$3$var117) * 0.5));
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
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var63];
					double cv$temp$9$var117 = memVar[var63];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var117)))) - (Math.log(cv$temp$9$var117) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var63 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var94 = st[i$var109];
						if(((0 <= var94) && (var94 < noStates))) {
							double cv$temp$21$var117 = memVar[st[i$var109]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var117))) - (Math.log(cv$temp$21$var117) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var63];
					double cv$temp$27$var117 = memVar[var63];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var117)))) - (Math.log(cv$temp$27$var117) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		memMean[var63] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var63 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var94 = st[0];
					if(((0 <= var94) && (var94 < noStates))) {
						double cv$temp$3$var117 = memVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var117))) - (Math.log(cv$temp$3$var117) * 0.5));
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
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var63];
				double cv$temp$9$var117 = memVar[var63];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var117)))) - (Math.log(cv$temp$9$var117) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var63 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var94 = st[i$var109];
					if(((0 <= var94) && (var94 < noStates))) {
						double cv$temp$21$var117 = memVar[st[i$var109]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var117))) - (Math.log(cv$temp$21$var117) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var63];
				double cv$temp$27$var117 = memVar[var63];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var117)))) - (Math.log(cv$temp$27$var117) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			memMean[var63] = cv$originalValue;
	}

	private final void sample80(int var74) {
		double cv$originalValue = pageFaultsMean[var74];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var74 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var104 = st[0];
						if(((0 <= var104) && (var104 < noStates))) {
							double cv$temp$3$var122 = pageFaultsVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var122))) - (Math.log(cv$temp$3$var122) * 0.5));
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
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var74];
					double cv$temp$9$var122 = pageFaultsVar[var74];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var122)))) - (Math.log(cv$temp$9$var122) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var74 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var104 = st[i$var109];
						if(((0 <= var104) && (var104 < noStates))) {
							double cv$temp$21$var122 = pageFaultsVar[st[i$var109]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var122))) - (Math.log(cv$temp$21$var122) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var74];
					double cv$temp$27$var122 = pageFaultsVar[var74];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var122)))) - (Math.log(cv$temp$27$var122) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		pageFaultsMean[var74] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var74 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var104 = st[0];
					if(((0 <= var104) && (var104 < noStates))) {
						double cv$temp$3$var122 = pageFaultsVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var122))) - (Math.log(cv$temp$3$var122) * 0.5));
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
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var74];
				double cv$temp$9$var122 = pageFaultsVar[var74];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var122)))) - (Math.log(cv$temp$9$var122) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var74 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var104 = st[i$var109];
					if(((0 <= var104) && (var104 < noStates))) {
						double cv$temp$21$var122 = pageFaultsVar[st[i$var109]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var122))) - (Math.log(cv$temp$21$var122) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var74];
				double cv$temp$27$var122 = pageFaultsVar[var74];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var122)))) - (Math.log(cv$temp$27$var122) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			pageFaultsMean[var74] = cv$originalValue;
	}

	private final void sample90(int var84) {
		double cv$originalValue = cpuVar[var84];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var84 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var52 = st[0];
						if(((0 <= var52) && (var52 < noStates))) {
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
					}
				} else {
					double cv$probabilitySample35Value4 = distribution$sample35[var84];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var84]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					if((var84 == st[i$var109])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var52 = st[i$var109];
						if(((0 <= var52) && (var52 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
				} else {
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var84];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[var84]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		cpuVar[var84] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var84 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var52 = st[0];
					if(((0 <= var52) && (var52 < noStates))) {
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
				}
			} else {
				double cv$probabilitySample35Value4 = distribution$sample35[var84];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var84]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				if((var84 == st[i$var109])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var52 = st[i$var109];
					if(((0 <= var52) && (var52 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
			} else {
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var84];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[var84]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			cpuVar[var84] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var22$countGlobal = new double[cv$max];
		cv$var27$countGlobal = new double[Math.max(0, noStates)];
		cv$distributionAccumulator$var39 = new double[noStates];
		cv$var30$stateProbabilityGlobal = new double[noStates];
		guard$sample35gaussian118$global = new boolean[length$cpu_measured];
		guard$sample35gaussian123$global = new boolean[length$cpu_measured];
		guard$sample35gaussian128$global = new boolean[length$cpu_measured];
		cv$var40$stateProbabilityGlobal = new double[noStates];
		guard$sample45gaussian118$global = new boolean[length$cpu_measured];
		guard$sample45gaussian123$global = new boolean[length$cpu_measured];
		guard$sample45gaussian128$global = new boolean[length$cpu_measured];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var21 = 0; var21 < noStates; var21 += 1)
				m[var21] = new double[noStates];
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
		distribution$sample35 = new double[noStates];
		distribution$sample45 = new double[(length$cpu_measured - 1)][];
		for(int i$var34 = 1; i$var34 < length$cpu_measured; i$var34 += 1)
			distribution$sample45[(i$var34 - 1)] = new double[noStates];
		logProbability$var39 = new double[(length$cpu_measured - 1)];
		logProbability$sample45 = new double[(length$cpu_measured - 1)];
		logProbability$var113 = new double[length$cpu_measured];
		logProbability$sample119 = new double[length$cpu_measured];
		logProbability$var118 = new double[length$cpu_measured];
		logProbability$sample124 = new double[length$cpu_measured];
		logProbability$var123 = new double[length$cpu_measured];
		logProbability$sample129 = new double[length$cpu_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		if(!fixedFlag$sample58) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample69) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample80) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample90) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample100) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample110) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			if(!fixedFlag$sample119)
				cpu[i$var109] = ((Math.sqrt(cpuVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var109]]);
			if(!fixedFlag$sample124)
				mem[i$var109] = ((Math.sqrt(memVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var109]]);
			if(!fixedFlag$sample129)
				pageFaults[i$var109] = ((Math.sqrt(pageFaultsVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var109]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35) {
			for(int index$var29 = 0; index$var29 < noStates; index$var29 += 1)
				distribution$sample35[index$var29] = ((index$var29 < initialStateDistribution.length)?initialStateDistribution[index$var29]:0.0);
		}
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				double[] cv$distribution$sample45 = distribution$sample45[(i$var34 - 1)];
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					cv$distribution$sample45[index$var39] = 0.0;
				if((1 == i$var34)) {
					if(fixedFlag$sample35) {
						int var21 = st[0];
						if(((0 <= var21) && (var21 < noStates))) {
							double[] var38 = m[st[0]];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + ((index$var39 < var38.length)?var38[index$var39]:0.0));
						}
					} else {
						for(int index$sample35$2 = 0; index$sample35$2 < noStates; index$sample35$2 += 1) {
							double cv$probabilitySample35Value3 = distribution$sample35[index$sample35$2];
							double[] var38 = m[index$sample35$2];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample35Value3 * ((index$var39 < var38.length)?var38[index$var39]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var34 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample45$10 = 0; index$sample45$10 < noStates; index$sample45$10 += 1) {
						double cv$probabilitySample45Value11 = distribution$sample45[(index$i$9 - 1)][index$sample45$10];
						double[] var38 = m[index$sample45$10];
						for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
							cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample45Value11 * ((index$var39 < var38.length)?var38[index$var39]:0.0)));
					}
				}
				double cv$var39$sum = 0.0;
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					cv$var39$sum = (cv$var39$sum + cv$distribution$sample45[index$var39]);
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] / cv$var39$sum);
			}
		}
		if(!fixedFlag$sample58) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample69) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample80) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample90) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample100) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample110) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		if(!fixedFlag$sample58) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample69) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample80) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample90) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample100) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample110) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample25) {
				for(int var21 = 0; var21 < noStates; var21 += 1)
					sample25(var21);
			}
			if(!fixedFlag$sample32)
				sample32();
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample45) {
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
					sample45(i$var34);
			}
			if(!fixedFlag$sample58) {
				for(int var52 = 0; var52 < noStates; var52 += 1)
					sample58(var52);
			}
			if(!fixedFlag$sample69) {
				for(int var63 = 0; var63 < noStates; var63 += 1)
					sample69(var63);
			}
			if(!fixedFlag$sample80) {
				for(int var74 = 0; var74 < noStates; var74 += 1)
					sample80(var74);
			}
			if(!fixedFlag$sample90) {
				for(int var84 = 0; var84 < noStates; var84 += 1)
					sample90(var84);
			}
			if(!fixedFlag$sample100) {
				for(int var94 = 0; var94 < noStates; var94 += 1)
					sample100(var94);
			}
			if(!fixedFlag$sample110) {
				for(int var104 = 0; var104 < noStates; var104 += 1)
					sample110(var104);
			}
		} else {
			if(!fixedFlag$sample110) {
				for(int var104 = (noStates - 1); var104 >= 0; var104 -= 1)
					sample110(var104);
			}
			if(!fixedFlag$sample100) {
				for(int var94 = (noStates - 1); var94 >= 0; var94 -= 1)
					sample100(var94);
			}
			if(!fixedFlag$sample90) {
				for(int var84 = (noStates - 1); var84 >= 0; var84 -= 1)
					sample90(var84);
			}
			if(!fixedFlag$sample80) {
				for(int var74 = (noStates - 1); var74 >= 0; var74 -= 1)
					sample80(var74);
			}
			if(!fixedFlag$sample69) {
				for(int var63 = (noStates - 1); var63 >= 0; var63 -= 1)
					sample69(var63);
			}
			if(!fixedFlag$sample58) {
				for(int var52 = (noStates - 1); var52 >= 0; var52 -= 1)
					sample58(var52);
			}
			if(!fixedFlag$sample45) {
				for(int i$var34 = (samples - 1); i$var34 >= 1; i$var34 -= 1)
					sample45(i$var34);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample32)
				sample32();
			if(!fixedFlag$sample25) {
				for(int var21 = (noStates - 1); var21 >= 0; var21 -= 1)
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
		if(!fixedProbFlag$sample32)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var30 = 0.0;
		for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
			logProbability$var39[(i$var34 - 1)] = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				logProbability$sample45[(i$var34 - 1)] = 0.0;
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
			logProbability$var113[i$var109] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample119) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample119[i$var109] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var118[i$var109] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample124) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample124[i$var109] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
			logProbability$var123[i$var109] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample129) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
				logProbability$sample129[i$var109] = 0.0;
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
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		if(!fixedFlag$sample58) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample69) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample80) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample90) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample100) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample110) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int cv$length1 = pageFaults.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				pageFaults[cv$index1] = pageFaults_measured[cv$index1];
		}
		{
			int cv$length1 = mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				mem[cv$index1] = mem_measured[cv$index1];
		}
		int cv$length1 = cpu.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cpu[cv$index1] = cpu_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n    \n    // Construct vectors describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    // Determine how many samples the model will need to produce.\n    int samples = cpu_measured.length;\n    \n    // Allocate space for the state.\n    int[] st = new int[samples];\n\n    // Set the initial state by sampling from a categorical with learnt weightings.\n    double[] initialStateDistribution = dirichlet(v).sample();\n    st[0] = categorical(initialStateDistribution).sampleDistribution();\n\n    //Determine the remaining states based on the previous state.\n    for(int i:[1 .. samples))\n        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n        \n    //Generate each metric.\n    double[] cpu = new double[samples];\n    double[] mem = new double[samples];\n    double[] pageFaults = new double[samples];\n    \n    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n    double[] memMean = gaussian(94, 1).sample(noStates);\n    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n    \n    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n    \n    for(int i:[0 .. samples)) {\n        int s = st[i];\n        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n    }\n\n    //Tie the values to the values we have measured.\n    cpu.observe(cpu_measured);\n    mem.observe(mem_measured);\n    pageFaults.observe(pageFaults_measured);\n}\n";
	}
}