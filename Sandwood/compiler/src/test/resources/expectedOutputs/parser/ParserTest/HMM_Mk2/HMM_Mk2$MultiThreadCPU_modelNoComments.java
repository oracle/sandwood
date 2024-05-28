package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2$CoreInterface {
	private double[][] bias;
	private double[][] cv$var129$stateProbabilityGlobal;
	private double[][] cv$var46$countGlobal;
	private double[][] cv$var60$countGlobal;
	private double[] cv$var80$countGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[][] cv$var97$stateProbabilityGlobal;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample136 = false;
	private boolean fixedFlag$sample171 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample136 = false;
	private boolean fixedProbFlag$sample171 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean fixedProbFlag$sample88 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[] logProbability$sample103;
	private double[][] logProbability$sample136;
	private double[][] logProbability$sample171;
	private double logProbability$st;
	private double[][] logProbability$var128;
	private double[][] logProbability$var161;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var79;
	private double logProbability$var81;
	private double[] logProbability$var96;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$events = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$weights = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample171 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
		fixedProbFlag$sample171 = false;
	}

	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		eventsMeasured = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample103() {
		return fixedFlag$sample103;
	}

	@Override
	public final void set$fixedFlag$sample103(boolean cv$value) {
		fixedFlag$sample103 = cv$value;
		fixedProbFlag$sample103 = (fixedFlag$sample103 && fixedProbFlag$sample103);
		fixedProbFlag$sample136 = (fixedFlag$sample103 && fixedProbFlag$sample136);
		fixedProbFlag$sample171 = (fixedFlag$sample103 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		fixedFlag$sample136 = cv$value;
		fixedProbFlag$sample136 = (fixedFlag$sample136 && fixedProbFlag$sample136);
		fixedProbFlag$sample171 = (fixedFlag$sample136 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample171() {
		return fixedFlag$sample171;
	}

	@Override
	public final void set$fixedFlag$sample171(boolean cv$value) {
		fixedFlag$sample171 = cv$value;
		fixedProbFlag$sample171 = (fixedFlag$sample171 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedProbFlag$sample103);
		fixedProbFlag$sample136 = (fixedFlag$sample47 && fixedProbFlag$sample136);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		fixedFlag$sample62 = cv$value;
		fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedProbFlag$sample62);
		fixedProbFlag$sample171 = (fixedFlag$sample62 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		fixedFlag$sample86 = cv$value;
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
		fixedProbFlag$sample88 = (fixedFlag$sample86 && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		fixedProbFlag$sample103 = (fixedFlag$sample88 && fixedProbFlag$sample103);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	@Override
	public final void set$length$eventsMeasured(int[] cv$value) {
		length$eventsMeasured = cv$value;
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
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
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
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample103 = false;
		fixedProbFlag$sample136 = false;
	}

	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	@Override
	public final void set$noEvents(int cv$value) {
		noEvents = cv$value;
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
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value) {
		st = cv$value;
		setFlag$st = true;
		fixedProbFlag$sample103 = false;
		fixedProbFlag$sample136 = false;
		fixedProbFlag$sample171 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	@Override
	public final double[] get$v2() {
		return v2;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample86 = false;
		fixedProbFlag$sample88 = false;
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var92][0];
					{
						{
							double[] var95 = m[initialState];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var96[((i$var92 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample103[((i$var92 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((i$var92 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var96[((i$var92 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample136() {
		if(!fixedProbFlag$sample136) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = st[i$var109][j$var121];
						{
							{
								double[] var127 = m[st[i$var109][(j$var121 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample171() {
		if(!fixedProbFlag$sample171) {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var142][j$var156] - 1);
						{
							{
								double[] var160 = bias[st[i$var142][j$var156]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noStates; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var45];
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var59 = 0; var59 < noStates; var59 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = bias[var59];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2));
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
			logProbability$var60 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample62 = fixedFlag$sample62;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = weights;
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
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample86 = fixedFlag$sample86;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = initialState;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weights.length))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var81 = cv$sampleAccumulator;
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var81 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample103(int i$var92, int threadID$cv$i$var92, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal[threadID$cv$i$var92];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var97 = cv$currentValue;
			int[] var93 = st[i$var92];
			var93[0] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var95;
				{
					double[] var95 = m[initialState];
					cv$temp$0$var95 = var95;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var95.length))?Math.log(cv$temp$0$var95[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var126$1_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((i$var92 == i$var109)) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if((0 == (j$var121 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var127;
															{
																double[] var127 = m[traceTempVariable$var126$1_1];
																cv$temp$1$var127 = var127;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
											if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
											else {
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
					{
						int traceTempVariable$var159$4_1 = cv$currentValue;
						for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
							if((i$var92 == i$var142)) {
								for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
									if((0 == j$var156)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var160;
															{
																double[] var160 = bias[traceTempVariable$var159$4_1];
																cv$temp$2$var160 = var160;
															}
															if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
											if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
											else {
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
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var97 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[] var93 = st[i$var92];
		var93[0] = var97;
	}

	private final void sample136(int i$var109, int j$var121, int threadID$cv$i$var109, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var129$stateProbabilityGlobal[threadID$cv$i$var109];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var129 = cv$currentValue;
			int[] var122 = st[i$var109];
			var122[j$var121] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var127;
				{
					double[] var127 = m[st[i$var109][(j$var121 - 1)]];
					cv$temp$0$var127 = var127;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var127.length))?Math.log(cv$temp$0$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var126$1_1 = cv$currentValue;
						for(int index$i$1_2 = 0; index$i$1_2 < samples; index$i$1_2 += 1) {
							if((i$var109 == index$i$1_2)) {
								for(int index$j$1_3 = 1; index$j$1_3 < length$eventsMeasured[index$i$1_2]; index$j$1_3 += 1) {
									if((j$var121 == (index$j$1_3 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var127;
															{
																double[] var127 = m[traceTempVariable$var126$1_1];
																cv$temp$1$var127 = var127;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
											if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
											else {
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
					{
						int traceTempVariable$var159$4_1 = cv$currentValue;
						for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
							if((i$var109 == i$var142)) {
								for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
									if((j$var121 == j$var156)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var160;
															{
																double[] var160 = bias[traceTempVariable$var159$4_1];
																cv$temp$2$var160 = var160;
															}
															if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
											if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
											else {
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
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var129 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[] var122 = st[i$var109];
		var122[j$var121] = var129;
	}

	private final void sample47(int var45, int threadID$cv$var45, Rng RNG$) {
		double[] cv$targetLocal = m[var45];
		double[] cv$countLocal = cv$var46$countGlobal[threadID$cv$var45];
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var45 == initialState)) {
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
							cv$countLocal[st[i$var92][0]] = (cv$countLocal[st[i$var92][0]] + 1.0);
					}
				}
			}
			{
				{
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if((var45 == st[i$var109][(j$var121 - 1)])) {
								{
									{
										{
											{
												{
													cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + 1.0);
												}
											}
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

	private final void sample62(int var59, int threadID$cv$var59, Rng RNG$) {
		double[] cv$targetLocal = bias[var59];
		double[] cv$countLocal = cv$var60$countGlobal[threadID$cv$var59];
		int cv$arrayLength = noEvents;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
						for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
							if((var59 == st[i$var142][j$var156])) {
								{
									{
										{
											{
												{
													cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + 1.0);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal);
	}

	private final void sample86() {
		double[] cv$targetLocal = weights;
		double[] cv$countLocal = cv$var80$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					{
						{
							{
								{
									{
										cv$countLocal[initialState] = (cv$countLocal[initialState] + 1.0);
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

	private final void sample88() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			initialState = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weights;
				{
					cv$temp$0$weights = weights;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weights.length))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double[] cv$temp$1$var95;
												{
													double[] var95 = m[traceTempVariable$initialState$1_2];
													cv$temp$1$var95 = var95;
												}
												if(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									}
								}
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var45 = 0; var45 < noStates; var45 += 1)
				cv$max = Math.max(cv$max, noStates);
			{
				int cv$threadCount = threadCount();
				cv$var46$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var46$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			for(int var59 = 0; var59 < noStates; var59 += 1)
				cv$max = Math.max(cv$max, noEvents);
			{
				int cv$threadCount = threadCount();
				cv$var60$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var60$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var80$countGlobal = new double[cv$max];
		}
		{
			cv$var82$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var47$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var97$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var97$stateProbabilityGlobal[cv$index] = new double[cv$var47$max];
			}
		}
		{
			int cv$var47$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var129$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var129$stateProbabilityGlobal[cv$index] = new double[cv$var47$max];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		{
			v2 = new double[noEvents];
		}
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var45 = 0; var45 < noStates; var45 += 1)
					m[var45] = new double[noStates];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[noStates][];
				for(int var59 = 0; var59 < noStates; var59 += 1)
					bias[var59] = new double[noEvents];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var73 = 0; i$var73 < length$eventsMeasured.length; i$var73 += 1)
					st[i$var73] = new int[length$eventsMeasured[i$var73]];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[noStates];
			}
		}
		if(!setFlag$events) {
			{
				events = new int[length$eventsMeasured.length][];
				for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
					events[i$var142] = new int[length$eventsMeasured[i$var142]];
			}
		}
		{
			logProbability$var96 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var128 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1)
				logProbability$var128[((i$var109 - 0) / 1)] = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample136 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1)
				logProbability$sample136[((i$var109 - 0) / 1)] = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var161 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
				logProbability$var161[((i$var142 - 0) / 1)] = new double[((((length$eventsMeasured[i$var142] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample171 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
				logProbability$sample171[((i$var142 - 0) / 1)] = new double[((((length$eventsMeasured[i$var142] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var142, int forEnd$index$i$var142, int threadID$index$i$var142, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var142 = forStart$index$i$var142; index$i$var142 < forEnd$index$i$var142; index$i$var142 += 1) {
						int i$var142 = index$i$var142;
						int threadID$i$var142 = threadID$index$i$var142;
						int[] var157 = events[i$var142];
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var142], 1,
							(int forStart$j$var156, int forEnd$j$var156, int threadID$j$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var156 = forStart$j$var156; j$var156 < forEnd$j$var156; j$var156 += 1) {
										if(!fixedFlag$sample171)
											var157[j$var156] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var142][j$var156]]) + 1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
							if(!fixedFlag$sample62)
								sample62(var59, threadID$var59, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample86)
				sample86();
			if(!fixedFlag$sample88)
				sample88();
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
							if(!fixedFlag$sample103)
								sample103(i$var92, threadID$i$var92, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
							for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
								if(!fixedFlag$sample136)
									sample136(i$var109, j$var121, threadID$i$var109, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
							for(int j$var121 = (length$eventsMeasured[i$var109] - ((((length$eventsMeasured[i$var109] - 1) - 1) % 1) + 1)); j$var121 >= ((1 - 1) + 1); j$var121 -= 1) {
								if(!fixedFlag$sample136)
									sample136(i$var109, j$var121, threadID$i$var109, RNG$1);
							}
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
							if(!fixedFlag$sample103)
								sample103(i$var92, threadID$i$var92, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample88)
				sample88();
			if(!fixedFlag$sample86)
				sample86();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
							if(!fixedFlag$sample62)
								sample62(var59, threadID$var59, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						v[var18] = 0.1;
			}
		);
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
						v2[var31] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var34 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample62)
			logProbability$var60 = 0.0;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample86)
			logProbability$weights = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$initialState = 0.0;
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
			logProbability$var96[((i$var92 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				logProbability$sample103[((i$var92 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
				logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
			for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1)
				logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample171) {
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1)
					logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = 0.0;
			}
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(fixedFlag$sample86)
			logProbabilityValue$sample86();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample103)
			logProbabilityValue$sample103();
		if(fixedFlag$sample136)
			logProbabilityValue$sample136();
		logProbabilityValue$sample171();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample103();
		logProbabilityValue$sample136();
		logProbabilityValue$sample171();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample103();
		logProbabilityValue$sample136();
		logProbabilityValue$sample171();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[][] cv$source1 = eventsMeasured;
		int[][] cv$target1 = events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sample();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sample();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}