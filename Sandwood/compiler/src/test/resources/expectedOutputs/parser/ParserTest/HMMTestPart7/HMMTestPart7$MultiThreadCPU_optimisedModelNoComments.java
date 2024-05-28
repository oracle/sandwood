package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart7$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var71;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var72$stateProbabilityGlobal;
	private double[] distribution$sample57;
	private double[][] distribution$sample75;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample75;
	private double[] logProbability$sample91;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double[] logProbability$var71;
	private double[] logProbability$var87;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart7$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		fixedFlag$sample91 = cv$value;
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
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
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample75 = false;
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
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample75 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final int get$states() {
		return 5;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				int cv$sampleValue = st[0];
				double[] var52 = m[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var53 = cv$distributionAccumulator;
				logProbability$var54 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample57 = fixedFlag$sample31;
			}
		} else {
			logProbability$var53 = logProbability$var54;
			if(fixedFlag$sample57)
				logProbability$st = (logProbability$st + logProbability$var54);
			logProbability$$model = (logProbability$$model + logProbability$var54);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!fixedProbFlag$sample75) {
			if(fixedFlag$sample75) {
				double cv$accumulator = 0.0;
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var66];
					if((1 == i$var66)) {
						if(fixedFlag$sample57) {
							int var29 = st[0];
							if(((0 <= var29) && (var29 < 5))) {
								double[] var70 = m[st[0]];
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample57$4 = 0; index$sample57$4 < 5; index$sample57$4 += 1) {
								double cv$probabilitySample57Value5 = distribution$sample57[index$sample57$4];
								double[] var70 = m[index$sample57$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
							}
						}
					}
					if((2 <= i$var66)) {
						int var29 = st[(i$var66 - 1)];
						if(((0 <= var29) && (var29 < 5))) {
							double[] var70 = m[st[(i$var66 - 1)]];
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$var71[(i$var66 - 1)] = cv$distributionAccumulator;
					logProbability$sample75[(i$var66 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample75 = (fixedFlag$sample31 && fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = logProbability$sample75[(i$var66 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[(i$var66 - 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample75)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if((0 == j)) {
					if(fixedFlag$sample57) {
						int var45 = st[0];
						if(((0 <= var45) && (var45 < 5))) {
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[0]]);
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample57$3 = 0; index$sample57$3 < 5; index$sample57$3 += 1) {
							double cv$probabilitySample57Value4 = distribution$sample57[index$sample57$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[index$sample57$3]));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value4);
						}
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample75) {
						int var45 = st[j];
						if(((0 <= var45) && (var45 < 5))) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[j]]);
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
						for(int index$sample75$11 = 0; index$sample75$11 < 5; index$sample75$11 += 1) {
							double cv$probabilitySample75Value12 = distribution$sample75[(j - 1)][index$sample75$11];
							double cv$weightedProbability = (Math.log(cv$probabilitySample75Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[index$sample75$11]));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value12);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var87[j] = cv$distributionAccumulator;
				logProbability$sample91[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample91[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < 5; var29 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v));
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var18 = logProbability$var30;
			logProbability$m = (logProbability$m + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < 5; var45 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var45], 1.0, 1.0));
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			logProbability$var34 = logProbability$var46;
			logProbability$bias = (logProbability$bias + logProbability$var46);
			logProbability$$model = (logProbability$$model + logProbability$var46);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			int cv$sampleValue = st[0];
			double[] var52 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var53 = cv$distributionAccumulator;
			logProbability$var54 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		} else {
			logProbability$var53 = logProbability$var54;
			logProbability$st = (logProbability$st + logProbability$var54);
			logProbability$$model = (logProbability$$model + logProbability$var54);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				int cv$sampleValue = st[i$var66];
				double[] var70 = m[st[(i$var66 - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var71[(i$var66 - 1)] = cv$distributionAccumulator;
				logProbability$sample75[(i$var66 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = logProbability$sample75[(i$var66 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[(i$var66 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var87[j] = cv$distributionAccumulator;
				logProbability$sample91[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample91[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(((var29 == 0) && fixedFlag$sample57))
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		if(fixedFlag$sample75) {
			if((1 < samples)) {
				if(fixedFlag$sample57) {
					if((var29 == st[0]))
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
				} else {
					if((var29 < 5))
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + distribution$sample57[var29]);
				}
			}
			for(int i$var66 = 2; i$var66 < samples; i$var66 += 1) {
				if((var29 == st[(i$var66 - 1)]))
					cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
			}
		}
		if(((var29 == 0) && !fixedFlag$sample57)) {
			for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample57[cv$loopIndex]);
		}
		if(!fixedFlag$sample75) {
			if((1 < samples)) {
				if(fixedFlag$sample57) {
					if((var29 == st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample75[0][cv$loopIndex]);
					}
				} else {
					if((var29 < 5)) {
						double cv$distributionProbability = distribution$sample57[var29];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			if((var29 < 5)) {
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					int index$i$45 = (i$var66 - 1);
					if((1 <= index$i$45)) {
						double cv$distributionProbability = distribution$sample75[(index$i$45 - 1)][var29];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[(i$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29]);
	}

	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		if((0 < samples)) {
			if(fixedFlag$sample57) {
				if((var45 == st[0])) {
					cv$count = 1.0;
					if(flips[0])
						cv$sum = 1.0;
				}
			} else {
				if((var45 < 5)) {
					double cv$probabilitySample57Value4 = distribution$sample57[var45];
					cv$count = cv$probabilitySample57Value4;
					if(flips[0])
						cv$sum = cv$probabilitySample57Value4;
				}
			}
		}
		for(int j = 1; j < samples; j += 1) {
			if(fixedFlag$sample75) {
				if((var45 == st[j])) {
					cv$count = (cv$count + 1.0);
					if(flips[j])
						cv$sum = (cv$sum + 1.0);
				}
			} else {
				if((var45 < 5)) {
					double cv$probabilitySample75Value13 = distribution$sample75[(j - 1)][var45];
					cv$count = (cv$count + cv$probabilitySample75Value13);
					if(flips[j])
						cv$sum = (cv$sum + cv$probabilitySample75Value13);
				}
			}
		}
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample57() {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double[] cv$temp$0$var52 = m[0];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var52.length)?Math.log(cv$temp$0$var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample75 && (1 < samples))) {
				double[] cv$temp$1$var70 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			if((!fixedFlag$sample75 && (1 < samples))) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var71[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var71, 1.0, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample75[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = cv$distributionAccumulator$var71[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$var54$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var54$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				distribution$sample57[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				distribution$sample57[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample57[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample75(int i$var66) {
		int cv$noStates = 0;
		if((1 == i$var66)) {
			if(fixedFlag$sample57) {
				int var29 = st[0];
				if(((0 <= var29) && (var29 < 5)))
					cv$noStates = 5;
			} else
				cv$noStates = 5;
		}
		if(fixedFlag$sample75) {
			if((2 <= i$var66)) {
				int var29 = st[(i$var66 - 1)];
				if(((0 <= var29) && (var29 < 5)))
					cv$noStates = 5;
			}
		} else {
			int index$i$11 = (i$var66 - 1);
			if(((1 <= index$i$11) && !(index$i$11 == i$var66)))
				cv$noStates = 5;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var66)) {
				if(fixedFlag$sample57) {
					int var29 = st[0];
					if(((0 <= var29) && (var29 < 5))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var70 = m[st[0]];
						cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[1], bias[cv$valuePos]) + ((cv$valuePos < cv$temp$0$var70.length)?Math.log(cv$temp$0$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
					}
				} else {
					for(int index$sample57$21 = 0; index$sample57$21 < 5; index$sample57$21 += 1) {
						double cv$probabilitySample57Value22 = distribution$sample57[index$sample57$21];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value22);
						double[] cv$temp$1$var70 = m[index$sample57$21];
						double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[1], bias[cv$valuePos]) + Math.log(cv$probabilitySample57Value22)) + ((cv$valuePos < cv$temp$1$var70.length)?Math.log(cv$temp$1$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
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
			int index$i$28 = (i$var66 - 1);
			if(((1 <= index$i$28) && !(index$i$28 == i$var66))) {
				for(int index$sample75$29 = 0; index$sample75$29 < 5; index$sample75$29 += 1) {
					double cv$probabilitySample75Value30 = distribution$sample75[(index$i$28 - 1)][index$sample75$29];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value30);
					double[] cv$temp$3$var70 = m[index$sample75$29];
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[i$var66], bias[cv$valuePos]) + Math.log(cv$probabilitySample75Value30)) + ((cv$valuePos < cv$temp$3$var70.length)?Math.log(cv$temp$3$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
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
			int index$i$55_2 = (i$var66 + 1);
			if((index$i$55_2 < samples)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var71[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var66)) {
					if(fixedFlag$sample57) {
						int index$var29$64_1 = st[0];
						if(((0 <= index$var29$64_1) && (index$var29$64_1 < 5)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample57$60 = 0; index$sample57$60 < 5; index$sample57$60 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[index$sample57$60]);
					}
				}
				int index$i$67 = (i$var66 - 1);
				if((((1 <= index$i$67) && !(index$i$67 == i$var66)) && !(index$i$67 == index$i$55_2))) {
					for(int index$sample75$68 = 0; index$sample75$68 < 5; index$sample75$68 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample75[(index$i$67 - 1)][index$sample75$68]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var71, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample75[(index$i$55_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var71[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var72$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample75[(i$var66 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var72$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var72$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var72$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var72$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var72$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var30$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[5];
		cv$distributionAccumulator$var71 = new double[5];
		cv$var54$stateProbabilityGlobal = new double[5];
		cv$var72$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!setFlag$m) {
			m = new double[5][];
			for(int var29 = 0; var29 < 5; var29 += 1)
				m[var29] = new double[5];
		}
		if(!setFlag$bias)
			bias = new double[5];
		if(!setFlag$st)
			st = new int[length$flipsMeasured];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		distribution$sample57 = new double[5];
		distribution$sample75 = new double[(length$flipsMeasured - 1)][];
		for(int i$var66 = 1; i$var66 < length$flipsMeasured; i$var66 += 1)
			distribution$sample75[(i$var66 - 1)] = new double[5];
		logProbability$var71 = new double[(length$flipsMeasured - 1)];
		logProbability$sample75 = new double[(length$flipsMeasured - 1)];
		logProbability$var87 = new double[length$flipsMeasured];
		logProbability$sample91 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
		if(!fixedFlag$sample91)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57) {
			double[] var52 = m[0];
			for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
				distribution$sample57[index$var53] = ((index$var53 < var52.length)?var52[index$var53]:0.0);
		}
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double[] cv$distribution$sample75 = distribution$sample75[(i$var66 - 1)];
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					cv$distribution$sample75[index$var71] = 0.0;
				if((1 == i$var66)) {
					if(fixedFlag$sample57) {
						int var29 = st[0];
						if(((0 <= var29) && (var29 < 5))) {
							double[] var70 = m[st[0]];
							for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
								cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + ((index$var71 < var70.length)?var70[index$var71]:0.0));
						}
					} else {
						for(int index$sample57$2 = 0; index$sample57$2 < 5; index$sample57$2 += 1) {
							double cv$probabilitySample57Value3 = distribution$sample57[index$sample57$2];
							double[] var70 = m[index$sample57$2];
							for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
								cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample57Value3 * ((index$var71 < var70.length)?var70[index$var71]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var66 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample75$10 = 0; index$sample75$10 < 5; index$sample75$10 += 1) {
						double cv$probabilitySample75Value11 = distribution$sample75[(index$i$9 - 1)][index$sample75$10];
						double[] var70 = m[index$sample75$10];
						for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
							cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample75Value11 * ((index$var71 < var70.length)?var70[index$var71]:0.0)));
					}
				}
				double cv$var71$sum = 0.0;
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					cv$var71$sum = (cv$var71$sum + cv$distribution$sample75[index$var71]);
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] / cv$var71$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

			if(!fixedFlag$sample48)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample75) {
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
					sample75(i$var66);
			}
		} else {
			if(!fixedFlag$sample75) {
				for(int i$var66 = (samples - 1); i$var66 >= 1; i$var66 -= 1)
					sample75(i$var66);
			}
			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample48)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample31)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
			logProbability$var71[(i$var66 - 1)] = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				logProbability$sample75[(i$var66 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var87[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample91[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample91();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart7(boolean[] flipsMeasured) {\n        int states = 5;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sampleDistribution();\n\n        for(int i:[1..samples))\n            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}