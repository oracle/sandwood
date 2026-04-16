package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics$SingleThreadCPU extends CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
boolean[] constrainedFlag$sample113;
	boolean[] constrainedFlag$sample130;
	boolean[] constrainedFlag$sample147;
	boolean[] constrainedFlag$sample164;
	boolean[] constrainedFlag$sample30;
	boolean constrainedFlag$sample36 = true;
	boolean constrainedFlag$sample39 = true;
	boolean[] constrainedFlag$sample57;
	boolean[] constrainedFlag$sample77;
	boolean[] constrainedFlag$sample95;
	double[] cpu;
	double[] cpuMean;
	double[] cpuVar;
	double[] cpu_measured;
	double[] distribution$sample39;
	double[][] distribution$sample57;
	boolean fixedFlag$sample113 = false;
	boolean fixedFlag$sample130 = false;
	boolean fixedFlag$sample147 = false;
	boolean fixedFlag$sample164 = false;
	boolean fixedFlag$sample30 = false;
	boolean fixedFlag$sample36 = false;
	boolean fixedFlag$sample39 = false;
	boolean fixedFlag$sample57 = false;
	boolean fixedFlag$sample77 = false;
	boolean fixedFlag$sample95 = false;
	boolean fixedProbFlag$sample113 = false;
	boolean fixedProbFlag$sample130 = false;
	boolean fixedProbFlag$sample147 = false;
	boolean fixedProbFlag$sample164 = false;
	boolean fixedProbFlag$sample180 = false;
	boolean fixedProbFlag$sample185 = false;
	boolean fixedProbFlag$sample190 = false;
	boolean fixedProbFlag$sample30 = false;
	boolean fixedProbFlag$sample36 = false;
	boolean fixedProbFlag$sample39 = false;
	boolean fixedProbFlag$sample57 = false;
	boolean fixedProbFlag$sample77 = false;
	boolean fixedProbFlag$sample95 = false;
	double[] initialStateDistribution;
	int length$cpu_measured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$cpu;
	double logProbability$cpuMean;
	double logProbability$cpuVar;
	double logProbability$initialStateDistribution;
	double logProbability$m;
	double logProbability$mem;
	double logProbability$memMean;
	double logProbability$memVar;
	double logProbability$pageFaults;
	double logProbability$pageFaultsMean;
	double logProbability$pageFaultsVar;
	double[] logProbability$sample180;
	double[] logProbability$sample185;
	double[] logProbability$sample190;
	double[] logProbability$sample57;
	double logProbability$st;
	double logProbability$var112;
	double logProbability$var129;
	double logProbability$var146;
	double logProbability$var163;
	double logProbability$var30;
	double logProbability$var38;
	double logProbability$var76;
	double logProbability$var94;
	double[][] m;
	double[] mem;
	double[] memMean;
	double[] memVar;
	double[] mem_measured;
	int noStates;
	double[] pageFaults;
	double[] pageFaultsMean;
	double[] pageFaultsVar;
	double[] pageFaults_measured;
	int samples;
	int[] st;
	boolean system$gibbsForward = true;
	double[] v;
	double[] cv$distributionAccumulator$var55;
	double[] cv$var30$countGlobal;
	double[] cv$var35$countGlobal;
	double[] cv$var38$stateProbabilityGlobal;
	double[] cv$var56$stateProbabilityGlobal;
	boolean[] guard$sample39gaussian179$global;
	boolean[] guard$sample39gaussian184$global;
	boolean[] guard$sample39gaussian189$global;
	boolean[] guard$sample57gaussian179$global;
	boolean[] guard$sample57gaussian184$global;
	boolean[] guard$sample57gaussian189$global;

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
	public final void set$cpuMean(double[] cv$value, boolean allocated$) {
		cpuMean = cv$value;
		fixedProbFlag$sample77 = false;
		fixedProbFlag$sample180 = false;
	}

	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	@Override
	public final void set$cpuVar(double[] cv$value, boolean allocated$) {
		cpuVar = cv$value;
		fixedProbFlag$sample130 = false;
		fixedProbFlag$sample180 = false;
	}

	@Override
	public final double[] get$cpu_measured() {
		return cpu_measured;
	}

	@Override
	public final void set$cpu_measured(double[] cv$value, boolean allocated$) {
		cpu_measured = cv$value;
	}

	@Override
	public final double[] get$distribution$sample39() {
		return distribution$sample39;
	}

	@Override
	public final void set$distribution$sample39(double[] cv$value, boolean allocated$) {
		distribution$sample39 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	@Override
	public final void set$distribution$sample57(double[][] cv$value, boolean allocated$) {
		distribution$sample57 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	@Override
	public final void set$fixedFlag$sample113(boolean cv$value, boolean allocated$) {
		fixedFlag$sample113 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
				constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
		}
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample130() {
		return fixedFlag$sample130;
	}

	@Override
	public final void set$fixedFlag$sample130(boolean cv$value, boolean allocated$) {
		fixedFlag$sample130 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
				constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
		}
		fixedProbFlag$sample130 = (cv$value && fixedProbFlag$sample130);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample147() {
		return fixedFlag$sample147;
	}

	@Override
	public final void set$fixedFlag$sample147(boolean cv$value, boolean allocated$) {
		fixedFlag$sample147 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
				constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
		}
		fixedProbFlag$sample147 = (cv$value && fixedProbFlag$sample147);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	@Override
	public final void set$fixedFlag$sample164(boolean cv$value, boolean allocated$) {
		fixedFlag$sample164 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
				constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
		}
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value, boolean allocated$) {
		fixedFlag$sample30 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
				constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		}
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value, boolean allocated$) {
		fixedFlag$sample36 = cv$value;
		constrainedFlag$sample36 = (cv$value || constrainedFlag$sample36);
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value, boolean allocated$) {
		fixedFlag$sample39 = cv$value;
		constrainedFlag$sample39 = (cv$value || constrainedFlag$sample39);
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
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		fixedFlag$sample57 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
				constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		}
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
	public final void set$fixedFlag$sample77(boolean cv$value, boolean allocated$) {
		fixedFlag$sample77 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
				constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
		}
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
		fixedFlag$sample95 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
				constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		}
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample39 = false;
	}

	@Override
	public final int get$length$cpu_measured() {
		return length$cpu_measured;
	}

	@Override
	public final void set$length$cpu_measured(int cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
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
	public final void set$memMean(double[] cv$value, boolean allocated$) {
		memMean = cv$value;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample185 = false;
	}

	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	@Override
	public final void set$memVar(double[] cv$value, boolean allocated$) {
		memVar = cv$value;
		fixedProbFlag$sample147 = false;
		fixedProbFlag$sample185 = false;
	}

	@Override
	public final double[] get$mem_measured() {
		return mem_measured;
	}

	@Override
	public final void set$mem_measured(double[] cv$value, boolean allocated$) {
		mem_measured = cv$value;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
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
	public final void set$pageFaultsMean(double[] cv$value, boolean allocated$) {
		pageFaultsMean = cv$value;
		fixedProbFlag$sample113 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	@Override
	public final void set$pageFaultsVar(double[] cv$value, boolean allocated$) {
		pageFaultsVar = cv$value;
		fixedProbFlag$sample164 = false;
		fixedProbFlag$sample190 = false;
	}

	@Override
	public final double[] get$pageFaults_measured() {
		return pageFaults_measured;
	}

	@Override
	public final void set$pageFaults_measured(double[] cv$value, boolean allocated$) {
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
	public final void set$st(int[] cv$value, boolean allocated$) {
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

	private final void drawValueSample113(int var111) {
		pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
	}

	private final void drawValueSample130(int var128) {
		cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample147(int var145) {
		memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample164(int var162) {
		pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample30(int var29) {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
	}

	private final void drawValueSample36() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
	}

	private final void drawValueSample39() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
	}

	private final void drawValueSample57(int i$var50) {
		st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
	}

	private final void drawValueSample77(int var75) {
		cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
	}

	private final void drawValueSample95(int var93) {
		memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
	}

	private final void inferSample113(int var111) {
		constrainedFlag$sample113[var111] = false;
		double cv$originalValue = pageFaultsMean[var111];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var111 == st[0])) {
						constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[0];
						if(((0 <= var162) && (var162 < noStates))) {
							double var187 = pageFaultsVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var111];
					constrainedFlag$sample113[var111] = true;
					double var187 = pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var111 == st[i$var174])) {
						constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							double var187 = pageFaultsVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var111];
					constrainedFlag$sample113[var111] = true;
					double var187 = pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample113[var111]) {
			pageFaultsMean[var111] = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var111 == st[0])) {
						constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[0];
						if(((0 <= var162) && (var162 < noStates))) {
							double var187 = pageFaultsVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var111];
					constrainedFlag$sample113[var111] = true;
					double var187 = pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var111 == st[i$var174])) {
						constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							double var187 = pageFaultsVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var111];
					constrainedFlag$sample113[var111] = true;
					double var187 = pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				pageFaultsMean[var111] = cv$originalValue;
		}
	}

	private final void inferSample130(int var128) {
		constrainedFlag$sample130[var128] = false;
		double cv$originalValue = cpuVar[var128];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var128 == st[0])) {
						constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var128];
					constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var128 == st[i$var174])) {
						constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var128];
					constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample130[var128]) {
			cpuVar[var128] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var128 == st[0])) {
						constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[0];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var128];
					constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var128 == st[i$var174])) {
						constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var128];
					constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				cpuVar[var128] = cv$originalValue;
		}
	}

	private final void inferSample147(int var145) {
		constrainedFlag$sample147[var145] = false;
		double cv$originalValue = memVar[var145];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var145 == st[0])) {
						constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var145];
					constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var145 == st[i$var174])) {
						constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var145];
					constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample147[var145]) {
			memVar[var145] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var145 == st[0])) {
						constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[0];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var145];
					constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var145 == st[i$var174])) {
						constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var145];
					constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				memVar[var145] = cv$originalValue;
		}
	}

	private final void inferSample164(int var162) {
		constrainedFlag$sample164[var162] = false;
		double cv$originalValue = pageFaultsVar[var162];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var162 == st[0])) {
						constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var162];
					constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var162 == st[i$var174])) {
						constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var162];
					constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample164[var162]) {
			pageFaultsVar[var162] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var162 == st[0])) {
						constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[0];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var162];
					constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var162 == st[i$var174])) {
						constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var162];
					constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				pageFaultsVar[var162] = cv$originalValue;
		}
	}

	private final void inferSample30(int var29) {
		constrainedFlag$sample30[var29] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var30$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample57) {
			if((1 < samples)) {
				if(fixedFlag$sample39) {
					if((var29 == st[0])) {
						constrainedFlag$sample30[var29] = true;
						cv$var30$countGlobal[st[1]] = (cv$var30$countGlobal[st[1]] + 1.0);
					}
				} else {
					constrainedFlag$sample30[var29] = true;
					cv$var30$countGlobal[st[1]] = (cv$var30$countGlobal[st[1]] + distribution$sample39[var29]);
				}
			}
			for(int i$var50 = 2; i$var50 < samples; i$var50 += 1) {
				if((var29 == st[(i$var50 - 1)])) {
					constrainedFlag$sample30[var29] = true;
					cv$var30$countGlobal[st[i$var50]] = (cv$var30$countGlobal[st[i$var50]] + 1.0);
				}
			}
		} else {
			if((1 < samples)) {
				if(fixedFlag$sample39) {
					if((var29 == st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var30$countGlobal[cv$loopIndex] = (cv$var30$countGlobal[cv$loopIndex] + distribution$sample57[0][cv$loopIndex]);
					}
				} else {
					double cv$distributionProbability = distribution$sample39[var29];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var30$countGlobal[cv$loopIndex] = (cv$var30$countGlobal[cv$loopIndex] + (distribution$sample57[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				int index$i$40 = (i$var50 - 1);
				if((1 <= index$i$40)) {
					double cv$distributionProbability = distribution$sample57[(index$i$40 - 1)][var29];
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$var30$countGlobal[cv$loopIndex] = (cv$var30$countGlobal[cv$loopIndex] + (distribution$sample57[(i$var50 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		if(constrainedFlag$sample30[var29])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var30$countGlobal, m[var29], noStates);
	}

	private final void inferSample36() {
		constrainedFlag$sample36 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var35$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample39) {
			constrainedFlag$sample36 = true;
			cv$var35$countGlobal[st[0]] = (cv$var35$countGlobal[st[0]] + 1.0);
		} else {
			for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
				cv$var35$countGlobal[cv$loopIndex] = (cv$var35$countGlobal[cv$loopIndex] + distribution$sample39[cv$loopIndex]);
		}
		if(constrainedFlag$sample36)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var35$countGlobal, initialStateDistribution, noStates);
	}

	private final void inferSample39() {
		constrainedFlag$sample39 = false;
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample57 && (1 < samples))) {
				constrainedFlag$sample39 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] var54 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[1]) && (st[1] < noStates)) && (0.0 <= var54[st[1]])) && (var54[st[1]] <= 1.0))?Math.log(var54[st[1]]):Double.NEGATIVE_INFINITY);
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
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var177 = cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
				if(!guard$sample39gaussian179$global[0]) {
					guard$sample39gaussian179$global[0] = true;
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var177 = cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
				guard$sample39gaussian184$global[0] = false;
				if(!guard$sample39gaussian184$global[0]) {
					guard$sample39gaussian184$global[0] = true;
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var182 = memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
				if(!guard$sample39gaussian184$global[0]) {
					guard$sample39gaussian184$global[0] = true;
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var182 = memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
				guard$sample39gaussian189$global[0] = false;
				if(!guard$sample39gaussian189$global[0]) {
					guard$sample39gaussian189$global[0] = true;
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var187 = pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
				if(!guard$sample39gaussian189$global[0]) {
					guard$sample39gaussian189$global[0] = true;
					constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var187 = pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
			if((!fixedFlag$sample57 && (1 < samples))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
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
		if(constrainedFlag$sample39) {
			double cv$logSum;
			double cv$lseMax = cv$var38$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var38$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var38$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample39[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample39[cv$indexName] = Math.exp((cv$var38$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample39[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample57(int i$var50) {
		constrainedFlag$sample57[(i$var50 - 1)] = false;
		int cv$numStates = 0;
		if((1 == i$var50)) {
			if(fixedFlag$sample39) {
				int var29 = st[0];
				if(((0 <= var29) && (var29 < noStates)))
					cv$numStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$numStates = noStates;
			}
		}
		if((0 < noStates)) {
			int index$i$10 = (i$var50 - 1);
			if(((1 <= index$i$10) && !(index$i$10 == i$var50)))
				cv$numStates = noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var50)) {
				if(fixedFlag$sample39) {
					int var29 = st[0];
					if(((0 <= var29) && (var29 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var54 = m[st[0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var54[cv$valuePos])) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
						guard$sample57gaussian179$global[1] = false;
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var177 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var177 = cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
						guard$sample57gaussian184$global[1] = false;
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var182 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var182 = memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
						guard$sample57gaussian189$global[1] = false;
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var187 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double var187 = pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					for(int index$sample39$18 = 0; index$sample39$18 < noStates; index$sample39$18 += 1) {
						double cv$probabilitySample39Value19 = distribution$sample39[index$sample39$18];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value19);
						double[] var54 = m[index$sample39$18];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value19) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
						guard$sample57gaussian179$global[1] = false;
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var177 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!guard$sample57gaussian179$global[1]) {
							guard$sample57gaussian179$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var177 = cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						guard$sample57gaussian184$global[1] = false;
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var182 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!guard$sample57gaussian184$global[1]) {
							guard$sample57gaussian184$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var182 = memVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						guard$sample57gaussian189$global[1] = false;
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var187 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!guard$sample57gaussian189$global[1]) {
							guard$sample57gaussian189$global[1] = true;
							constrainedFlag$sample57[0] = true;
							double var187 = pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$i$25 = (i$var50 - 1);
			if(((1 <= index$i$25) && !(index$i$25 == i$var50))) {
				for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
					double cv$probabilitySample57Value27 = distribution$sample57[(index$i$25 - 1)][index$sample57$26];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
					double[] var54 = m[index$sample57$26];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
					guard$sample57gaussian179$global[i$var50] = false;
					if(!guard$sample57gaussian179$global[i$var50]) {
						guard$sample57gaussian179$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var177 = cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian179$global[i$var50]) {
						guard$sample57gaussian179$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var177 = cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					guard$sample57gaussian184$global[i$var50] = false;
					if(!guard$sample57gaussian184$global[i$var50]) {
						guard$sample57gaussian184$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var182 = memVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian184$global[i$var50]) {
						guard$sample57gaussian184$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var182 = memVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					guard$sample57gaussian189$global[i$var50] = false;
					if(!guard$sample57gaussian189$global[i$var50]) {
						guard$sample57gaussian189$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var187 = pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian189$global[i$var50]) {
						guard$sample57gaussian189$global[i$var50] = true;
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var187 = pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$i$618_2 = (i$var50 + 1);
			if((index$i$618_2 < samples)) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var50)) {
						if(fixedFlag$sample39) {
							int index$var29$627_1 = st[0];
							if(((0 <= index$var29$627_1) && (index$var29$627_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample39$623 = 0; index$sample39$623 < noStates; index$sample39$623 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample39[index$sample39$623]);
						}
					}
					int index$i$630 = (i$var50 - 1);
					if((((1 <= index$i$630) && !(index$i$630 == i$var50)) && !(index$i$630 == index$i$618_2))) {
						for(int index$sample57$631 = 0; index$sample57$631 < noStates; index$sample57$631 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[(index$i$630 - 1)][index$sample57$631]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample57[(index$i$618_2 - 1)];
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
		if(constrainedFlag$sample57[(i$var50 - 1)]) {
			double[] cv$localProbability = distribution$sample57[(i$var50 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var56$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample77(int var75) {
		constrainedFlag$sample77[var75] = false;
		double cv$originalValue = cpuMean[var75];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var75 == st[0])) {
						constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[0];
						if(((0 <= var128) && (var128 < noStates))) {
							double var177 = cpuVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var75];
					constrainedFlag$sample77[var75] = true;
					double var177 = cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var75 == st[i$var174])) {
						constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							double var177 = cpuVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var75];
					constrainedFlag$sample77[var75] = true;
					double var177 = cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample77[var75]) {
			cpuMean[var75] = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var75 == st[0])) {
						constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[0];
						if(((0 <= var128) && (var128 < noStates))) {
							double var177 = cpuVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var75];
					constrainedFlag$sample77[var75] = true;
					double var177 = cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var75 == st[i$var174])) {
						constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							double var177 = cpuVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var75];
					constrainedFlag$sample77[var75] = true;
					double var177 = cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				cpuMean[var75] = cv$originalValue;
		}
	}

	private final void inferSample95(int var93) {
		constrainedFlag$sample95[var93] = false;
		double cv$originalValue = memMean[var93];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var93 == st[0])) {
						constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[0];
						if(((0 <= var145) && (var145 < noStates))) {
							double var182 = memVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var93];
					constrainedFlag$sample95[var93] = true;
					double var182 = memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var93 == st[i$var174])) {
						constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							double var182 = memVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var93];
					constrainedFlag$sample95[var93] = true;
					double var182 = memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
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
		if(constrainedFlag$sample95[var93]) {
			memMean[var93] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					if((var93 == st[0])) {
						constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[0];
						if(((0 <= var145) && (var145 < noStates))) {
							double var182 = memVar[st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample39Value5 = distribution$sample39[var93];
					constrainedFlag$sample95[var93] = true;
					double var182 = memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					if((var93 == st[i$var174])) {
						constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							double var182 = memVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var93];
					constrainedFlag$sample95[var93] = true;
					double var182 = memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				memMean[var93] = cv$originalValue;
		}
	}

	private final void logProbabilityDistribution$sample180() {
		if(!fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = cpu[i$var174];
				if((0 == i$var174)) {
					if(fixedFlag$sample39) {
						if((0 <= st[0])) {
							int var75 = st[0];
							if(((0 <= var75) && (var75 < noStates))) {
								double var177 = cpuVar[st[0]];
								cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var177 = cpuVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample39$3]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((1 <= i$var174)) {
					if(fixedFlag$sample57) {
						if((0 <= st[i$var174])) {
							int var75 = st[i$var174];
							if(((0 <= var75) && (var75 < noStates))) {
								double var177 = cpuVar[st[i$var174]];
								double cv$weightedProbability = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var177 = cpuVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample57$43]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample180[i$var174]);
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
				if((0 == i$var174)) {
					if(fixedFlag$sample39) {
						if((0 <= st[0])) {
							int var93 = st[0];
							if(((0 <= var93) && (var93 < noStates))) {
								double var182 = memVar[st[0]];
								cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var182 = memVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample39$3]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((1 <= i$var174)) {
					if(fixedFlag$sample57) {
						if((0 <= st[i$var174])) {
							int var93 = st[i$var174];
							if(((0 <= var93) && (var93 < noStates))) {
								double var182 = memVar[st[i$var174]];
								double cv$weightedProbability = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var182 = memVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample57$43]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample185[i$var174]);
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
				if((0 == i$var174)) {
					if(fixedFlag$sample39) {
						if((0 <= st[0])) {
							int var111 = st[0];
							if(((0 <= var111) && (var111 < noStates))) {
								double var187 = pageFaultsVar[st[0]];
								cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var187 = pageFaultsVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample39$3]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((1 <= i$var174)) {
					if(fixedFlag$sample57) {
						if((0 <= st[i$var174])) {
							int var111 = st[i$var174];
							if(((0 <= var111) && (var111 < noStates))) {
								double var187 = pageFaultsVar[st[i$var174]];
								double cv$weightedProbability = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var187 = pageFaultsVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample57$43]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample190[i$var174]);
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample39() {
		if(!fixedProbFlag$sample39) {
			if(fixedFlag$sample39) {
				int cv$sampleValue = st[0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var38 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample39 = fixedFlag$sample36;
			}
		} else {
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
								double[] var54 = m[st[0]];
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
								double cv$probabilitySample39Value5 = distribution$sample39[index$sample39$4];
								double[] var54 = m[index$sample39$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					if((2 <= i$var50)) {
						int var29 = st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < noStates))) {
							double[] var54 = m[st[(i$var50 - 1)]];
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = (fixedFlag$sample30 && fixedFlag$sample39);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample57[(i$var50 - 1)]);
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
			logProbability$var112 = cv$sampleAccumulator;
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample113)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample113 = fixedFlag$sample113;
		} else {
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
			logProbability$var129 = cv$sampleAccumulator;
			logProbability$cpuVar = (logProbability$cpuVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample130)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample130 = fixedFlag$sample130;
		} else {
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
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$memVar = (logProbability$memVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample147)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample147 = fixedFlag$sample147;
		} else {
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
			logProbability$var163 = cv$sampleAccumulator;
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample164)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample164 = fixedFlag$sample164;
		} else {
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
				double cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			logProbability$cpu = (logProbability$cpu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample180[i$var174]);
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
				double cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			logProbability$mem = (logProbability$mem + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample185[i$var174]);
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
				double cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			logProbability$pageFaults = (logProbability$pageFaults + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample190[i$var174]);
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			logProbability$m = (logProbability$m + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var38 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedFlag$sample36);
		} else {
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
				double[] var54 = m[st[(i$var50 - 1)]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample57[(i$var50 - 1)]);
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
			logProbability$var76 = cv$sampleAccumulator;
			logProbability$cpuMean = (logProbability$cpuMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
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
			logProbability$var94 = cv$sampleAccumulator;
			logProbability$memMean = (logProbability$memMean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			logProbability$memMean = (logProbability$memMean + logProbability$var94);
			logProbability$$model = (logProbability$$model + logProbability$var94);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var94);
		}
	}

	@Override
	public final void allocate() {
		v = new double[noStates];
		if(!fixedFlag$sample30) {
			m = new double[noStates][];
			for(int var29 = 0; var29 < noStates; var29 += 1)
				m[var29] = new double[noStates];
		}
		if((!fixedFlag$sample39 || !fixedFlag$sample57))
			st = new int[length$cpu_measured];
		if(!fixedFlag$sample36)
			initialStateDistribution = new double[noStates];
		cpu = new double[length$cpu_measured];
		mem = new double[length$cpu_measured];
		pageFaults = new double[length$cpu_measured];
		if(!fixedFlag$sample77)
			cpuMean = new double[noStates];
		if(!fixedFlag$sample95)
			memMean = new double[noStates];
		if(!fixedFlag$sample113)
			pageFaultsMean = new double[noStates];
		if(!fixedFlag$sample130)
			cpuVar = new double[noStates];
		if(!fixedFlag$sample147)
			memVar = new double[noStates];
		if(!fixedFlag$sample164)
			pageFaultsVar = new double[noStates];
		distribution$sample39 = new double[noStates];
		distribution$sample57 = new double[(length$cpu_measured - 1)][];
		for(int i$var50 = 1; i$var50 < length$cpu_measured; i$var50 += 1)
			distribution$sample57[(i$var50 - 1)] = new double[noStates];
		constrainedFlag$sample95 = new boolean[noStates];
		constrainedFlag$sample30 = new boolean[noStates];
		constrainedFlag$sample77 = new boolean[noStates];
		constrainedFlag$sample57 = new boolean[(length$cpu_measured - 1)];
		constrainedFlag$sample164 = new boolean[noStates];
		constrainedFlag$sample147 = new boolean[noStates];
		constrainedFlag$sample130 = new boolean[noStates];
		constrainedFlag$sample113 = new boolean[noStates];
		logProbability$sample57 = new double[(length$cpu_measured - 1)];
		logProbability$sample180 = new double[length$cpu_measured];
		logProbability$sample185 = new double[length$cpu_measured];
		logProbability$sample190 = new double[length$cpu_measured];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		cv$var30$countGlobal = new double[noStates];
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
	public final void forwardGeneration() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noStates; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77) {
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample95) {
			for(int var93 = 0; var93 < noStates; var93 += 1)
				memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample113) {
			for(int var111 = 0; var111 < noStates; var111 += 1)
				pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample130) {
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample147) {
			for(int var145 = 0; var145 < noStates; var145 += 1)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample164) {
			for(int var162 = 0; var162 < noStates; var162 += 1)
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
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noStates; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39) {
			for(int index$var37 = 0; index$var37 < noStates; index$var37 += 1)
				distribution$sample39[index$var37] = (((0.0 <= initialStateDistribution[index$var37]) && (initialStateDistribution[index$var37] <= 1.0))?initialStateDistribution[index$var37]:0.0);
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
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0));
						}
					} else {
						for(int index$sample39$2 = 0; index$sample39$2 < noStates; index$sample39$2 += 1) {
							double cv$probabilitySample39Value3 = distribution$sample39[index$sample39$2];
							double[] var54 = m[index$sample39$2];
							for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var50 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample57$10 = 0; index$sample57$10 < noStates; index$sample57$10 += 1) {
						double cv$probabilitySample57Value11 = distribution$sample57[(index$i$9 - 1)][index$sample57$10];
						double[] var54 = m[index$sample57$10];
						for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
							cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
					}
				}
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		if(!fixedFlag$sample77) {
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample95) {
			for(int var93 = 0; var93 < noStates; var93 += 1)
				memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample113) {
			for(int var111 = 0; var111 < noStates; var111 += 1)
				pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample130) {
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample147) {
			for(int var145 = 0; var145 < noStates; var145 += 1)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample164) {
			for(int var162 = 0; var162 < noStates; var162 += 1)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noStates; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77) {
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample95) {
			for(int var93 = 0; var93 < noStates; var93 += 1)
				memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample113) {
			for(int var111 = 0; var111 < noStates; var111 += 1)
				pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample130) {
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample147) {
			for(int var145 = 0; var145 < noStates; var145 += 1)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample164) {
			for(int var162 = 0; var162 < noStates; var162 += 1)
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
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noStates; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77) {
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample95) {
			for(int var93 = 0; var93 < noStates; var93 += 1)
				memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample113) {
			for(int var111 = 0; var111 < noStates; var111 += 1)
				pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample130) {
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample147) {
			for(int var145 = 0; var145 < noStates; var145 += 1)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample164) {
			for(int var162 = 0; var162 < noStates; var162 += 1)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample30) {
			for(int var29 = 0; var29 < noStates; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
		}
		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		if(!fixedFlag$sample77) {
			for(int var75 = 0; var75 < noStates; var75 += 1)
				cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		if(!fixedFlag$sample95) {
			for(int var93 = 0; var93 < noStates; var93 += 1)
				memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		if(!fixedFlag$sample113) {
			for(int var111 = 0; var111 < noStates; var111 += 1)
				pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		if(!fixedFlag$sample130) {
			for(int var128 = 0; var128 < noStates; var128 += 1)
				cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample147) {
			for(int var145 = 0; var145 < noStates; var145 += 1)
				memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		if(!fixedFlag$sample164) {
			for(int var162 = 0; var162 < noStates; var162 += 1)
				pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample30) {
				for(int var29 = 0; var29 < noStates; var29 += 1)
					inferSample30(var29);
			}
			if(!fixedFlag$sample36)
				inferSample36();
			if(!fixedFlag$sample39)
				inferSample39();
			if(!fixedFlag$sample57) {
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
					inferSample57(i$var50);
			}
			if(!fixedFlag$sample77) {
				for(int var75 = 0; var75 < noStates; var75 += 1)
					inferSample77(var75);
			}
			if(!fixedFlag$sample95) {
				for(int var93 = 0; var93 < noStates; var93 += 1)
					inferSample95(var93);
			}
			if(!fixedFlag$sample113) {
				for(int var111 = 0; var111 < noStates; var111 += 1)
					inferSample113(var111);
			}
			if(!fixedFlag$sample130) {
				for(int var128 = 0; var128 < noStates; var128 += 1)
					inferSample130(var128);
			}
			if(!fixedFlag$sample147) {
				for(int var145 = 0; var145 < noStates; var145 += 1)
					inferSample147(var145);
			}
			if(!fixedFlag$sample164) {
				for(int var162 = 0; var162 < noStates; var162 += 1)
					inferSample164(var162);
			}
		} else {
			if(!fixedFlag$sample164) {
				for(int var162 = (noStates - 1); var162 >= 0; var162 -= 1)
					inferSample164(var162);
			}
			if(!fixedFlag$sample147) {
				for(int var145 = (noStates - 1); var145 >= 0; var145 -= 1)
					inferSample147(var145);
			}
			if(!fixedFlag$sample130) {
				for(int var128 = (noStates - 1); var128 >= 0; var128 -= 1)
					inferSample130(var128);
			}
			if(!fixedFlag$sample113) {
				for(int var111 = (noStates - 1); var111 >= 0; var111 -= 1)
					inferSample113(var111);
			}
			if(!fixedFlag$sample95) {
				for(int var93 = (noStates - 1); var93 >= 0; var93 -= 1)
					inferSample95(var93);
			}
			if(!fixedFlag$sample77) {
				for(int var75 = (noStates - 1); var75 >= 0; var75 -= 1)
					inferSample77(var75);
			}
			if(!fixedFlag$sample57) {
				for(int i$var50 = (samples - 1); i$var50 >= 1; i$var50 -= 1)
					inferSample57(i$var50);
			}
			if(!fixedFlag$sample39)
				inferSample39();
			if(!fixedFlag$sample36)
				inferSample36();
			if(!fixedFlag$sample30) {
				for(int var29 = (noStates - 1); var29 >= 0; var29 -= 1)
					inferSample30(var29);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var29 = 0; var29 < noStates; var29 += 1) {
			if(!constrainedFlag$sample30[var29])
				drawValueSample30(var29);
		}
		if(!constrainedFlag$sample36)
			drawValueSample36();
		if(!constrainedFlag$sample39)
			drawValueSample39();
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!constrainedFlag$sample57[(i$var50 - 1)])
				drawValueSample57(i$var50);
		}
		for(int var75 = 0; var75 < noStates; var75 += 1) {
			if(!constrainedFlag$sample77[var75])
				drawValueSample77(var75);
		}
		for(int var93 = 0; var93 < noStates; var93 += 1) {
			if(!constrainedFlag$sample95[var93])
				drawValueSample95(var93);
		}
		for(int var111 = 0; var111 < noStates; var111 += 1) {
			if(!constrainedFlag$sample113[var111])
				drawValueSample113(var111);
		}
		for(int var128 = 0; var128 < noStates; var128 += 1) {
			if(!constrainedFlag$sample130[var128])
				drawValueSample130(var128);
		}
		for(int var145 = 0; var145 < noStates; var145 += 1) {
			if(!constrainedFlag$sample147[var145])
				drawValueSample147(var145);
		}
		for(int var162 = 0; var162 < noStates; var162 += 1) {
			if(!constrainedFlag$sample164[var162])
				drawValueSample164(var162);
		}
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
				logProbability$sample57[(i$var50 - 1)] = Double.NaN;
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
				logProbability$sample180[i$var174] = Double.NaN;
		}
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample185[i$var174] = Double.NaN;
		}
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1)
				logProbability$sample190[i$var174] = Double.NaN;
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