package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var61;
	private double[] cv$var36$countGlobal;
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
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample140() {
		return fixedFlag$sample140;
	}

	@Override
	public final void set$fixedFlag$sample140(boolean cv$value) {
		fixedFlag$sample140 = cv$value;
		fixedProbFlag$sample140 = (cv$value && fixedProbFlag$sample140);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	@Override
	public final void set$fixedFlag$sample157(boolean cv$value) {
		fixedFlag$sample157 = cv$value;
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample174() {
		return fixedFlag$sample174;
	}

	@Override
	public final void set$fixedFlag$sample174(boolean cv$value) {
		fixedFlag$sample174 = cv$value;
		fixedProbFlag$sample174 = (cv$value && fixedProbFlag$sample174);
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
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
	public final boolean get$fixedFlag$sample195() {
		return fixedFlag$sample195;
	}

	@Override
	public final void set$fixedFlag$sample195(boolean cv$value) {
		fixedFlag$sample195 = cv$value;
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample200() {
		return fixedFlag$sample200;
	}

	@Override
	public final void set$fixedFlag$sample200(boolean cv$value) {
		fixedFlag$sample200 = cv$value;
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		fixedFlag$sample67 = cv$value;
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = cpu[i$var180];
				if((0 == i$var180)) {
					if(fixedFlag$sample49) {
						if((0 <= st[0])) {
							int var81 = st[0];
							if(((0 <= var81) && (var81 < noStates))) {
								double var183 = cpuVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var183))) - (Math.log(var183) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var183 = cpuVar[index$sample49$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample49$3]) / Math.sqrt(var183)))) - (Math.log(var183) * 0.5));
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
				if((1 <= i$var180)) {
					if(fixedFlag$sample67) {
						if((0 <= st[i$var180])) {
							int var81 = st[i$var180];
							if(((0 <= var81) && (var81 < noStates))) {
								double var183 = cpuVar[st[i$var180]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var180]]) / Math.sqrt(var183))) - (Math.log(var183) * 0.5));
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
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var183 = cpuVar[index$sample67$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample67$43]) / Math.sqrt(var183)))) - (Math.log(var183) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var184[i$var180] = cv$distributionAccumulator;
				logProbability$sample190[i$var180] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample190[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var184[i$var180] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = mem[i$var180];
				if((0 == i$var180)) {
					if(fixedFlag$sample49) {
						if((0 <= st[0])) {
							int var99 = st[0];
							if(((0 <= var99) && (var99 < noStates))) {
								double var188 = memVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var188))) - (Math.log(var188) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var188 = memVar[index$sample49$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample49$3]) / Math.sqrt(var188)))) - (Math.log(var188) * 0.5));
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
				if((1 <= i$var180)) {
					if(fixedFlag$sample67) {
						if((0 <= st[i$var180])) {
							int var99 = st[i$var180];
							if(((0 <= var99) && (var99 < noStates))) {
								double var188 = memVar[st[i$var180]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var180]]) / Math.sqrt(var188))) - (Math.log(var188) * 0.5));
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
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var188 = memVar[index$sample67$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample67$43]) / Math.sqrt(var188)))) - (Math.log(var188) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var189[i$var180] = cv$distributionAccumulator;
				logProbability$sample195[i$var180] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample195[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var189[i$var180] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = pageFaults[i$var180];
				if((0 == i$var180)) {
					if(fixedFlag$sample49) {
						if((0 <= st[0])) {
							int var117 = st[0];
							if(((0 <= var117) && (var117 < noStates))) {
								double var193 = pageFaultsVar[st[0]];
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var193))) - (Math.log(var193) * 0.5));
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var193 = pageFaultsVar[index$sample49$3];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample49$3]) / Math.sqrt(var193)))) - (Math.log(var193) * 0.5));
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
				if((1 <= i$var180)) {
					if(fixedFlag$sample67) {
						if((0 <= st[i$var180])) {
							int var117 = st[i$var180];
							if(((0 <= var117) && (var117 < noStates))) {
								double var193 = pageFaultsVar[st[i$var180]];
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var180]]) / Math.sqrt(var193))) - (Math.log(var193) * 0.5));
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
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var193 = pageFaultsVar[index$sample67$43];
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample67$43]) / Math.sqrt(var193)))) - (Math.log(var193) * 0.5));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var194[i$var180] = cv$distributionAccumulator;
				logProbability$sample200[i$var180] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample200[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var194[i$var180] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample49() {
		if(!fixedProbFlag$sample49) {
			if(fixedFlag$sample49) {
				int cv$sampleValue = st[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var43 = cv$distributionAccumulator;
				logProbability$var44 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample49 = fixedFlag$sample46;
			}
		} else {
			logProbability$var43 = logProbability$var44;
			if(fixedFlag$sample49)
				logProbability$st = (logProbability$st + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityDistribution$sample67() {
		if(!fixedProbFlag$sample67) {
			if(fixedFlag$sample67) {
				double cv$accumulator = 0.0;
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var56];
					if((1 == i$var56)) {
						if(fixedFlag$sample49) {
							int var35 = st[0];
							if(((0 <= var35) && (var35 < noStates))) {
								double[] var60 = m[st[0]];
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample49$4 = 0; index$sample49$4 < noStates; index$sample49$4 += 1) {
								double cv$probabilitySample49Value5 = distribution$sample49[index$sample49$4];
								double[] var60 = m[index$sample49$4];
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
					if((2 <= i$var56)) {
						int var35 = st[(i$var56 - 1)];
						if(((0 <= var35) && (var35 < noStates))) {
							double[] var60 = m[st[(i$var56 - 1)]];
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$var61[(i$var56 - 1)] = cv$distributionAccumulator;
					logProbability$sample67[(i$var56 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample67 = (fixedFlag$sample39 && fixedFlag$sample49);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double cv$rvAccumulator = logProbability$sample67[(i$var56 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var61[(i$var56 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var99 = 0; var99 < noStates; var99 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var99] - 94.0)));
			logProbability$var88 = cv$sampleAccumulator;
			logProbability$var100 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample105)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample105 = fixedFlag$sample105;
		} else {
			logProbability$var88 = logProbability$var100;
			logProbability$memMean = (logProbability$memMean + logProbability$var100);
			logProbability$$model = (logProbability$$model + logProbability$var100);
			if(fixedFlag$sample105)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var100);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$sampleAccumulator = 0.0;
			for(int var117 = 0; var117 < noStates; var117 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var117] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			logProbability$var106 = cv$sampleAccumulator;
			logProbability$var118 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample123 = fixedFlag$sample123;
		} else {
			logProbability$var106 = logProbability$var118;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var118);
			logProbability$$model = (logProbability$$model + logProbability$var118);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var118);
		}
	}

	private final void logProbabilityValue$sample140() {
		if(!fixedProbFlag$sample140) {
			double cv$sampleAccumulator = 0.0;
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var134], 5.0, 0.5));
			logProbability$var123 = cv$sampleAccumulator;
			logProbability$var135 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample140)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample140 = fixedFlag$sample140;
		} else {
			logProbability$var123 = logProbability$var135;
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var135);
			logProbability$$model = (logProbability$$model + logProbability$var135);
			if(fixedFlag$sample140)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var135);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$sampleAccumulator = 0.0;
			for(int var151 = 0; var151 < noStates; var151 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var151], 5.0, 0.5));
			logProbability$var140 = cv$sampleAccumulator;
			logProbability$var152 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample157)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample157 = fixedFlag$sample157;
		} else {
			logProbability$var140 = logProbability$var152;
			logProbability$memVar = (logProbability$memVar + logProbability$var152);
			logProbability$$model = (logProbability$$model + logProbability$var152);
			if(fixedFlag$sample157)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var152);
		}
	}

	private final void logProbabilityValue$sample174() {
		if(!fixedProbFlag$sample174) {
			double cv$sampleAccumulator = 0.0;
			for(int var168 = 0; var168 < noStates; var168 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var168], 5.0, 0.5));
			logProbability$var157 = cv$sampleAccumulator;
			logProbability$var169 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample174)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample174 = fixedFlag$sample174;
		} else {
			logProbability$var157 = logProbability$var169;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var169);
			logProbability$$model = (logProbability$$model + logProbability$var169);
			if(fixedFlag$sample174)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var169);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double var183 = cpuVar[st[i$var180]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(var183))) - (Math.log(var183) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var184[i$var180] = cv$distributionAccumulator;
				logProbability$sample190[i$var180] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample190[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var184[i$var180] = cv$rvAccumulator;
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
				double var188 = memVar[st[i$var180]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(var188))) - (Math.log(var188) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var189[i$var180] = cv$distributionAccumulator;
				logProbability$sample195[i$var180] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample195[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var189[i$var180] = cv$rvAccumulator;
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
				double var193 = pageFaultsVar[st[i$var180]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(var193))) - (Math.log(var193) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var194[i$var180] = cv$distributionAccumulator;
				logProbability$sample200[i$var180] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double cv$rvAccumulator = logProbability$sample200[i$var180];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var194[i$var180] = cv$rvAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < noStates; var35 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var35], v));
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			logProbability$var24 = logProbability$var36;
			logProbability$m = (logProbability$m + logProbability$var36);
			logProbability$$model = (logProbability$$model + logProbability$var36);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var40 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			logProbability$var40 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var43 = cv$distributionAccumulator;
			logProbability$var44 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample46);
		} else {
			logProbability$var43 = logProbability$var44;
			logProbability$st = (logProbability$st + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				int cv$sampleValue = st[i$var56];
				double[] var60 = m[st[(i$var56 - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var61[(i$var56 - 1)] = cv$distributionAccumulator;
				logProbability$sample67[(i$var56 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample67 = ((fixedFlag$sample67 && fixedFlag$sample39) && fixedFlag$sample49);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double cv$rvAccumulator = logProbability$sample67[(i$var56 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var61[(i$var56 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$sampleAccumulator = 0.0;
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var81] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			logProbability$var70 = cv$sampleAccumulator;
			logProbability$var82 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample87 = fixedFlag$sample87;
		} else {
			logProbability$var70 = logProbability$var82;
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var82);
			logProbability$$model = (logProbability$$model + logProbability$var82);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var82);
		}
	}

	private final void sample105(int var99) {
		double cv$originalValue = memMean[var99];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var99 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var151 = st[0];
						if(((0 <= var151) && (var151 < noStates))) {
							double cv$temp$3$var188 = memVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var188))) - (Math.log(cv$temp$3$var188) * 0.5));
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
					double cv$probabilitySample49Value4 = distribution$sample49[var99];
					double cv$temp$9$var188 = memVar[var99];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var188)))) - (Math.log(cv$temp$9$var188) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var99 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var151 = st[i$var180];
						if(((0 <= var151) && (var151 < noStates))) {
							double cv$temp$21$var188 = memVar[st[i$var180]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var188))) - (Math.log(cv$temp$21$var188) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var99];
					double cv$temp$27$var188 = memVar[var99];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var188)))) - (Math.log(cv$temp$27$var188) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		memMean[var99] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var99 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var151 = st[0];
					if(((0 <= var151) && (var151 < noStates))) {
						double cv$temp$3$var188 = memVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var188))) - (Math.log(cv$temp$3$var188) * 0.5));
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
				double cv$probabilitySample49Value4 = distribution$sample49[var99];
				double cv$temp$9$var188 = memVar[var99];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var188)))) - (Math.log(cv$temp$9$var188) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var99 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var151 = st[i$var180];
					if(((0 <= var151) && (var151 < noStates))) {
						double cv$temp$21$var188 = memVar[st[i$var180]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var188))) - (Math.log(cv$temp$21$var188) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var99];
				double cv$temp$27$var188 = memVar[var99];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var188)))) - (Math.log(cv$temp$27$var188) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			memMean[var99] = cv$originalValue;
	}

	private final void sample123(int var117) {
		double cv$originalValue = pageFaultsMean[var117];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var117 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var168 = st[0];
						if(((0 <= var168) && (var168 < noStates))) {
							double cv$temp$3$var193 = pageFaultsVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var193))) - (Math.log(cv$temp$3$var193) * 0.5));
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
					double cv$probabilitySample49Value4 = distribution$sample49[var117];
					double cv$temp$9$var193 = pageFaultsVar[var117];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var193)))) - (Math.log(cv$temp$9$var193) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var117 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var168 = st[i$var180];
						if(((0 <= var168) && (var168 < noStates))) {
							double cv$temp$21$var193 = pageFaultsVar[st[i$var180]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var193))) - (Math.log(cv$temp$21$var193) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var117];
					double cv$temp$27$var193 = pageFaultsVar[var117];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var193)))) - (Math.log(cv$temp$27$var193) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		pageFaultsMean[var117] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var117 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var168 = st[0];
					if(((0 <= var168) && (var168 < noStates))) {
						double cv$temp$3$var193 = pageFaultsVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var193))) - (Math.log(cv$temp$3$var193) * 0.5));
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
				double cv$probabilitySample49Value4 = distribution$sample49[var117];
				double cv$temp$9$var193 = pageFaultsVar[var117];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var193)))) - (Math.log(cv$temp$9$var193) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var117 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var168 = st[i$var180];
					if(((0 <= var168) && (var168 < noStates))) {
						double cv$temp$21$var193 = pageFaultsVar[st[i$var180]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var193))) - (Math.log(cv$temp$21$var193) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var117];
				double cv$temp$27$var193 = pageFaultsVar[var117];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var193)))) - (Math.log(cv$temp$27$var193) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			pageFaultsMean[var117] = cv$originalValue;
	}

	private final void sample140(int var134) {
		double cv$originalValue = cpuVar[var134];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var134 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var81 = st[0];
						if(((0 <= var81) && (var81 < noStates))) {
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
					double cv$probabilitySample49Value4 = distribution$sample49[var134];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var134]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var134 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var81 = st[i$var180];
						if(((0 <= var81) && (var81 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var134];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[var134]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		cpuVar[var134] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var134 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var81 = st[0];
					if(((0 <= var81) && (var81 < noStates))) {
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
				double cv$probabilitySample49Value4 = distribution$sample49[var134];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var134]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var134 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var81 = st[i$var180];
					if(((0 <= var81) && (var81 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var134];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[var134]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			cpuVar[var134] = cv$originalValue;
	}

	private final void sample157(int var151) {
		double cv$originalValue = memVar[var151];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var151 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var99 = st[0];
						if(((0 <= var99) && (var99 < noStates))) {
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
					double cv$probabilitySample49Value4 = distribution$sample49[var151];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var151]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var151 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var99 = st[i$var180];
						if(((0 <= var99) && (var99 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var151];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[var151]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		memVar[var151] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var151 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var99 = st[0];
					if(((0 <= var99) && (var99 < noStates))) {
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
				double cv$probabilitySample49Value4 = distribution$sample49[var151];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var151]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var151 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var99 = st[i$var180];
					if(((0 <= var99) && (var99 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var151];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[var151]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			memVar[var151] = cv$originalValue;
	}

	private final void sample174(int var168) {
		double cv$originalValue = pageFaultsVar[var168];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var168 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var117 = st[0];
						if(((0 <= var117) && (var117 < noStates))) {
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
					double cv$probabilitySample49Value4 = distribution$sample49[var168];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var168]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var168 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var117 = st[i$var180];
						if(((0 <= var117) && (var117 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var168];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[var168]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		pageFaultsVar[var168] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var168 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var117 = st[0];
					if(((0 <= var117) && (var117 < noStates))) {
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
				double cv$probabilitySample49Value4 = distribution$sample49[var168];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var168]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var168 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var117 = st[i$var180];
					if(((0 <= var117) && (var117 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var168];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[var168]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			pageFaultsVar[var168] = cv$originalValue;
	}

	private final void sample39(int var35) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var36$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample67) {
			if((1 < samples)) {
				if(fixedFlag$sample49) {
					if((var35 == st[0]))
						cv$var36$countGlobal[st[1]] = (cv$var36$countGlobal[st[1]] + 1.0);
				} else
					cv$var36$countGlobal[st[1]] = (cv$var36$countGlobal[st[1]] + distribution$sample49[var35]);
			}
			for(int i$var56 = 2; i$var56 < samples; i$var56 += 1) {
				if((var35 == st[(i$var56 - 1)]))
					cv$var36$countGlobal[st[i$var56]] = (cv$var36$countGlobal[st[i$var56]] + 1.0);
			}
		} else {
			if((1 < samples)) {
				if(fixedFlag$sample49) {
					if((var35 == st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + distribution$sample67[0][cv$loopIndex]);
					}
				} else {
					double cv$distributionProbability = distribution$sample49[var35];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + (distribution$sample67[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				int index$i$40 = (i$var56 - 1);
				if((1 <= index$i$40)) {
					double cv$distributionProbability = distribution$sample67[(index$i$40 - 1)][var35];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + (distribution$sample67[(i$var56 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var36$countGlobal, m[var35]);
	}

	private final void sample46() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var41$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample49)
			cv$var41$countGlobal[st[0]] = (cv$var41$countGlobal[st[0]] + 1.0);
		else {
			for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
				cv$var41$countGlobal[cv$loopIndex] = (cv$var41$countGlobal[cv$loopIndex] + distribution$sample49[cv$loopIndex]);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var41$countGlobal, initialStateDistribution);
	}

	private final void sample49() {
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample67 && (1 < samples))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] cv$temp$1$var60 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[1]) && (st[1] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[1]]):Double.NEGATIVE_INFINITY);
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
				guard$sample49gaussian189$global[0] = false;
				if(!guard$sample49gaussian189$global[0]) {
					guard$sample49gaussian189$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$3$var183 = cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
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
				if(!guard$sample49gaussian189$global[0]) {
					guard$sample49gaussian189$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$11$var183 = cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$11$var183))) - (Math.log(cv$temp$11$var183) * 0.5));
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
				guard$sample49gaussian194$global[0] = false;
				if(!guard$sample49gaussian194$global[0]) {
					guard$sample49gaussian194$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$19$var188 = memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$19$var188))) - (Math.log(cv$temp$19$var188) * 0.5));
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
				if(!guard$sample49gaussian194$global[0]) {
					guard$sample49gaussian194$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$27$var188 = memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$27$var188))) - (Math.log(cv$temp$27$var188) * 0.5));
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
				guard$sample49gaussian199$global[0] = false;
				if(!guard$sample49gaussian199$global[0]) {
					guard$sample49gaussian199$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$35$var193 = pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$35$var193))) - (Math.log(cv$temp$35$var193) * 0.5));
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
				if(!guard$sample49gaussian199$global[0]) {
					guard$sample49gaussian199$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double cv$temp$43$var193 = pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$43$var193))) - (Math.log(cv$temp$43$var193) * 0.5));
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
			}
			if((!fixedFlag$sample67 && (1 < samples))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var61[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var61, 1.0, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample67[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var61[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var44$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var44$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var44$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var44$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample49[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample49[cv$indexName] = Math.exp((cv$var44$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample49[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample67(int i$var56) {
		int cv$noStates = 0;
		if((1 == i$var56)) {
			if(fixedFlag$sample49) {
				int var35 = st[0];
				if(((0 <= var35) && (var35 < noStates)))
					cv$noStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample67) {
			if((2 <= i$var56)) {
				int var35 = st[(i$var56 - 1)];
				if(((0 <= var35) && (var35 < noStates)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$i$11 = (i$var56 - 1);
				if(((1 <= index$i$11) && !(index$i$11 == i$var56)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var56)) {
				if(fixedFlag$sample49) {
					int var35 = st[0];
					if(((0 <= var35) && (var35 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var60 = m[st[0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var60.length)?Math.log(cv$temp$0$var60[cv$valuePos]):Double.NEGATIVE_INFINITY);
						guard$sample67gaussian189$global[1] = false;
						if(!guard$sample67gaussian189$global[1]) {
							guard$sample67gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$7$var183 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$7$var183))) - (Math.log(cv$temp$7$var183) * 0.5));
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
						if(!guard$sample67gaussian189$global[1]) {
							guard$sample67gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$39$var183 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$39$var183))) - (Math.log(cv$temp$39$var183) * 0.5));
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
						guard$sample67gaussian194$global[1] = false;
						if(!guard$sample67gaussian194$global[1]) {
							guard$sample67gaussian194$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$71$var188 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$71$var188))) - (Math.log(cv$temp$71$var188) * 0.5));
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
						if(!guard$sample67gaussian194$global[1]) {
							guard$sample67gaussian194$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$103$var188 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$103$var188))) - (Math.log(cv$temp$103$var188) * 0.5));
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
						guard$sample67gaussian199$global[1] = false;
						if(!guard$sample67gaussian199$global[1]) {
							guard$sample67gaussian199$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$135$var193 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$135$var193))) - (Math.log(cv$temp$135$var193) * 0.5));
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
						if(!guard$sample67gaussian199$global[1]) {
							guard$sample67gaussian199$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$167$var193 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$167$var193))) - (Math.log(cv$temp$167$var193) * 0.5));
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample49$21 = 0; index$sample49$21 < noStates; index$sample49$21 += 1) {
						double cv$probabilitySample49Value22 = distribution$sample49[index$sample49$21];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample49Value22);
						double[] cv$temp$1$var60 = m[index$sample49$21];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample49Value22) + ((cv$valuePos < cv$temp$1$var60.length)?Math.log(cv$temp$1$var60[cv$valuePos]):Double.NEGATIVE_INFINITY));
						guard$sample67gaussian189$global[1] = false;
						if(!guard$sample67gaussian189$global[1]) {
							guard$sample67gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$15$var183 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$15$var183))) - (Math.log(cv$temp$15$var183) * 0.5));
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
						if(!guard$sample67gaussian189$global[1]) {
							guard$sample67gaussian189$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$47$var183 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$47$var183))) - (Math.log(cv$temp$47$var183) * 0.5));
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
						guard$sample67gaussian194$global[1] = false;
						if(!guard$sample67gaussian194$global[1]) {
							guard$sample67gaussian194$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$79$var188 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$79$var188))) - (Math.log(cv$temp$79$var188) * 0.5));
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
						if(!guard$sample67gaussian194$global[1]) {
							guard$sample67gaussian194$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$111$var188 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$111$var188))) - (Math.log(cv$temp$111$var188) * 0.5));
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
						guard$sample67gaussian199$global[1] = false;
						if(!guard$sample67gaussian199$global[1]) {
							guard$sample67gaussian199$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$143$var193 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$143$var193))) - (Math.log(cv$temp$143$var193) * 0.5));
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
						if(!guard$sample67gaussian199$global[1]) {
							guard$sample67gaussian199$global[1] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$175$var193 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$175$var193))) - (Math.log(cv$temp$175$var193) * 0.5));
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
			int index$i$28 = (i$var56 - 1);
			if(((1 <= index$i$28) && !(index$i$28 == i$var56))) {
				for(int index$sample67$29 = 0; index$sample67$29 < noStates; index$sample67$29 += 1) {
					double cv$probabilitySample67Value30 = distribution$sample67[(index$i$28 - 1)][index$sample67$29];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample67Value30);
					double[] cv$temp$3$var60 = m[index$sample67$29];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample67Value30) + ((cv$valuePos < cv$temp$3$var60.length)?Math.log(cv$temp$3$var60[cv$valuePos]):Double.NEGATIVE_INFINITY));
					guard$sample67gaussian189$global[i$var56] = false;
					if(!guard$sample67gaussian189$global[i$var56]) {
						guard$sample67gaussian189$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$31$var183 = cpuVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var56] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$31$var183))) - (Math.log(cv$temp$31$var183) * 0.5));
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
					if(!guard$sample67gaussian189$global[i$var56]) {
						guard$sample67gaussian189$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$63$var183 = cpuVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var56] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$63$var183))) - (Math.log(cv$temp$63$var183) * 0.5));
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
					guard$sample67gaussian194$global[i$var56] = false;
					if(!guard$sample67gaussian194$global[i$var56]) {
						guard$sample67gaussian194$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$95$var188 = memVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var56] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$95$var188))) - (Math.log(cv$temp$95$var188) * 0.5));
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
					if(!guard$sample67gaussian194$global[i$var56]) {
						guard$sample67gaussian194$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$127$var188 = memVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var56] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$127$var188))) - (Math.log(cv$temp$127$var188) * 0.5));
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
					guard$sample67gaussian199$global[i$var56] = false;
					if(!guard$sample67gaussian199$global[i$var56]) {
						guard$sample67gaussian199$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$159$var193 = pageFaultsVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var56] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$159$var193))) - (Math.log(cv$temp$159$var193) * 0.5));
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
					if(!guard$sample67gaussian199$global[i$var56]) {
						guard$sample67gaussian199$global[i$var56] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$191$var193 = pageFaultsVar[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var56] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$191$var193))) - (Math.log(cv$temp$191$var193) * 0.5));
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
			int index$i$621_2 = (i$var56 + 1);
			if((index$i$621_2 < samples)) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var61[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var56)) {
						if(fixedFlag$sample49) {
							int index$var35$630_1 = st[0];
							if(((0 <= index$var35$630_1) && (index$var35$630_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample49$626 = 0; index$sample49$626 < noStates; index$sample49$626 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample49[index$sample49$626]);
						}
					}
					int index$i$633 = (i$var56 - 1);
					if((((1 <= index$i$633) && !(index$i$633 == i$var56)) && !(index$i$633 == index$i$621_2))) {
						for(int index$sample67$634 = 0; index$sample67$634 < noStates; index$sample67$634 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample67[(index$i$633 - 1)][index$sample67$634]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var61, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample67[(index$i$621_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var61[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var62$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample67[(i$var56 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var62$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var62$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var62$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var62$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var62$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample87(int var81) {
		double cv$originalValue = cpuMean[var81];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var81 == st[0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var134 = st[0];
						if(((0 <= var134) && (var134 < noStates))) {
							double cv$temp$3$var183 = cpuVar[st[0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
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
					double cv$probabilitySample49Value4 = distribution$sample49[var81];
					double cv$temp$9$var183 = cpuVar[var81];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var183)))) - (Math.log(cv$temp$9$var183) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					if((var81 == st[i$var180])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var134 = st[i$var180];
						if(((0 <= var134) && (var134 < noStates))) {
							double cv$temp$21$var183 = cpuVar[st[i$var180]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var183))) - (Math.log(cv$temp$21$var183) * 0.5));
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
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var81];
					double cv$temp$27$var183 = cpuVar[var81];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var183)))) - (Math.log(cv$temp$27$var183) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		cpuMean[var81] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var81 == st[0])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var134 = st[0];
					if(((0 <= var134) && (var134 < noStates))) {
						double cv$temp$3$var183 = cpuVar[st[0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
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
				double cv$probabilitySample49Value4 = distribution$sample49[var81];
				double cv$temp$9$var183 = cpuVar[var81];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var183)))) - (Math.log(cv$temp$9$var183) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				if((var81 == st[i$var180])) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var134 = st[i$var180];
					if(((0 <= var134) && (var134 < noStates))) {
						double cv$temp$21$var183 = cpuVar[st[i$var180]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var183))) - (Math.log(cv$temp$21$var183) * 0.5));
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
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var81];
				double cv$temp$27$var183 = cpuVar[var81];
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var183)))) - (Math.log(cv$temp$27$var183) * 0.5));
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
			cpuMean[var81] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var36$countGlobal = new double[cv$max];
		cv$var41$countGlobal = new double[Math.max(0, noStates)];
		cv$distributionAccumulator$var61 = new double[noStates];
		cv$var44$stateProbabilityGlobal = new double[noStates];
		guard$sample49gaussian189$global = new boolean[length$cpu_measured];
		guard$sample49gaussian194$global = new boolean[length$cpu_measured];
		guard$sample49gaussian199$global = new boolean[length$cpu_measured];
		cv$var62$stateProbabilityGlobal = new double[noStates];
		guard$sample67gaussian189$global = new boolean[length$cpu_measured];
		guard$sample67gaussian194$global = new boolean[length$cpu_measured];
		guard$sample67gaussian199$global = new boolean[length$cpu_measured];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var35 = 0; var35 < noStates; var35 += 1)
				m[var35] = new double[noStates];
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
		distribution$sample49 = new double[noStates];
		distribution$sample67 = new double[(length$cpu_measured - 1)][];
		for(int i$var56 = 1; i$var56 < length$cpu_measured; i$var56 += 1)
			distribution$sample67[(i$var56 - 1)] = new double[noStates];
		logProbability$var61 = new double[(length$cpu_measured - 1)];
		logProbability$sample67 = new double[(length$cpu_measured - 1)];
		logProbability$var184 = new double[length$cpu_measured];
		logProbability$sample190 = new double[length$cpu_measured];
		logProbability$var189 = new double[length$cpu_measured];
		logProbability$sample195 = new double[length$cpu_measured];
		logProbability$var194 = new double[length$cpu_measured];
		logProbability$sample200 = new double[length$cpu_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
			if(!fixedFlag$sample190)
				cpu[i$var180] = ((Math.sqrt(cpuVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var180]]);
			if(!fixedFlag$sample195)
				mem[i$var180] = ((Math.sqrt(memVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var180]]);
			if(!fixedFlag$sample200)
				pageFaults[i$var180] = ((Math.sqrt(pageFaultsVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var180]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49) {
			for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1)
				distribution$sample49[index$var43] = ((index$var43 < initialStateDistribution.length)?initialStateDistribution[index$var43]:0.0);
		}
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				double[] cv$distribution$sample67 = distribution$sample67[(i$var56 - 1)];
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					cv$distribution$sample67[index$var61] = 0.0;
				if((1 == i$var56)) {
					if(fixedFlag$sample49) {
						int var35 = st[0];
						if(((0 <= var35) && (var35 < noStates))) {
							double[] var60 = m[st[0]];
							for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
								cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + ((index$var61 < var60.length)?var60[index$var61]:0.0));
						}
					} else {
						for(int index$sample49$2 = 0; index$sample49$2 < noStates; index$sample49$2 += 1) {
							double cv$probabilitySample49Value3 = distribution$sample49[index$sample49$2];
							double[] var60 = m[index$sample49$2];
							for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
								cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample49Value3 * ((index$var61 < var60.length)?var60[index$var61]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var56 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample67$10 = 0; index$sample67$10 < noStates; index$sample67$10 += 1) {
						double cv$probabilitySample67Value11 = distribution$sample67[(index$i$9 - 1)][index$sample67$10];
						double[] var60 = m[index$sample67$10];
						for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
							cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample67Value11 * ((index$var61 < var60.length)?var60[index$var61]:0.0)));
					}
				}
				double cv$var61$sum = 0.0;
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					cv$var61$sum = (cv$var61$sum + cv$distribution$sample67[index$var61]);
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] / cv$var61$sum);
			}
		}
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample39) {
				for(int var35 = 0; var35 < noStates; var35 += 1)
					sample39(var35);
			}
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample49)
				sample49();
			if(!fixedFlag$sample67) {
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
					sample67(i$var56);
			}
			if(!fixedFlag$sample87) {
				for(int var81 = 0; var81 < noStates; var81 += 1)
					sample87(var81);
			}
			if(!fixedFlag$sample105) {
				for(int var99 = 0; var99 < noStates; var99 += 1)
					sample105(var99);
			}
			if(!fixedFlag$sample123) {
				for(int var117 = 0; var117 < noStates; var117 += 1)
					sample123(var117);
			}
			if(!fixedFlag$sample140) {
				for(int var134 = 0; var134 < noStates; var134 += 1)
					sample140(var134);
			}
			if(!fixedFlag$sample157) {
				for(int var151 = 0; var151 < noStates; var151 += 1)
					sample157(var151);
			}
			if(!fixedFlag$sample174) {
				for(int var168 = 0; var168 < noStates; var168 += 1)
					sample174(var168);
			}
		} else {
			if(!fixedFlag$sample174) {
				for(int var168 = (noStates - 1); var168 >= 0; var168 -= 1)
					sample174(var168);
			}
			if(!fixedFlag$sample157) {
				for(int var151 = (noStates - 1); var151 >= 0; var151 -= 1)
					sample157(var151);
			}
			if(!fixedFlag$sample140) {
				for(int var134 = (noStates - 1); var134 >= 0; var134 -= 1)
					sample140(var134);
			}
			if(!fixedFlag$sample123) {
				for(int var117 = (noStates - 1); var117 >= 0; var117 -= 1)
					sample123(var117);
			}
			if(!fixedFlag$sample105) {
				for(int var99 = (noStates - 1); var99 >= 0; var99 -= 1)
					sample105(var99);
			}
			if(!fixedFlag$sample87) {
				for(int var81 = (noStates - 1); var81 >= 0; var81 -= 1)
					sample87(var81);
			}
			if(!fixedFlag$sample67) {
				for(int i$var56 = (samples - 1); i$var56 >= 1; i$var56 -= 1)
					sample67(i$var56);
			}
			if(!fixedFlag$sample49)
				sample49();
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample39) {
				for(int var35 = (noStates - 1); var35 >= 0; var35 -= 1)
					sample39(var35);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var21 = 0; var21 < noStates; var21 += 1)
			v[var21] = 0.1;
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
			logProbability$var61[(i$var56 - 1)] = 0.0;
		if(!fixedProbFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				logProbability$sample67[(i$var56 - 1)] = 0.0;
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
			logProbability$var184[i$var180] = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample190[i$var180] = 0.0;
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
			logProbability$var189[i$var180] = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample195) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample195[i$var180] = 0.0;
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
			logProbability$var194[i$var180] = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample200) {
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1)
				logProbability$sample200[i$var180] = 0.0;
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
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
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
		return "package org.sandwood.compiler.tests.parser;\n"
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