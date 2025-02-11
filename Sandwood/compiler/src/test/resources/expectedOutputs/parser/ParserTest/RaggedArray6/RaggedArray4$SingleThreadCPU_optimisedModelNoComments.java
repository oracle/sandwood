package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray4$CoreInterface {
	private double[][] a;
	private double[] b;
	private double[] cv$var38$stateProbabilityGlobal;
	private double[] d;
	private double[] distribution$sample41;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample53 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var37;
	private double logProbability$var40;
	private double logProbability$var43;
	private double logProbability$var49;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[] get$b() {
		return b;
	}

	@Override
	public final double[] get$d() {
		return d;
	}

	@Override
	public final void set$d(double[] cv$value) {
		d = cv$value;
		setFlag$d = true;
		fixedProbFlag$sample44 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final void set$obs(boolean[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
		fixedProbFlag$sample41 = false;
		fixedProbFlag$sample44 = false;
		fixedProbFlag$sample53 = false;
	}

	private final void logProbabilityDistribution$sample41() {
		if(!fixedProbFlag$sample41) {
			if(fixedFlag$sample41) {
				double cv$distributionAccumulator = (((0.0 <= y) && (y < b.length))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
				logProbability$var37 = cv$distributionAccumulator;
				logProbability$y = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample41 = true;
			}
		} else {
			logProbability$var37 = logProbability$y;
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityDistribution$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(fixedFlag$sample41) {
				if((0 == y)) {
					cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[0]);
					cv$probabilityReached = 1.0;
				}
				if((1 == y)) {
					double cv$weightedProbability = DistributionSampling.logProbabilityDirichlet(d, a[1]);
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
				double cv$probabilitySample41Value4 = distribution$sample41[0];
				cv$distributionAccumulator = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
				double cv$probabilitySample41Value13 = distribution$sample41[1];
				double cv$weightedProbability = (Math.log(cv$probabilitySample41Value13) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
				if((cv$weightedProbability < cv$distributionAccumulator))
					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
				else {
					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
						cv$distributionAccumulator = cv$weightedProbability;
					else
						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
				}
				cv$probabilityReached = (cv$probabilitySample41Value4 + cv$probabilitySample41Value13);
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			logProbability$var40 = cv$distributionAccumulator;
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample41);
		} else {
			logProbability$var40 = logProbability$d;
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityDistribution$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1) {
				double cv$distributionAccumulator;
				double cv$probabilityReached;
				boolean cv$sampleValue = obs[var48];
				if(fixedFlag$sample41) {
					cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[y]);
					cv$probabilityReached = 1.0;
				} else {
					{
						double cv$probabilitySample41Value4 = distribution$sample41[0];
						cv$distributionAccumulator = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[0]));
						cv$probabilityReached = cv$probabilitySample41Value4;
					}
					double cv$probabilitySample41Value4 = distribution$sample41[1];
					double cv$weightedProbability = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[1]));
					if((cv$weightedProbability < cv$distributionAccumulator))
						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
					else {
						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
							cv$distributionAccumulator = cv$weightedProbability;
						else
							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
					}
					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample41Value4);
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var43 = cv$sampleAccumulator;
			logProbability$var49 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample41) && fixedFlag$sample44);
		} else {
			logProbability$var43 = logProbability$var49;
			logProbability$obs = (logProbability$obs + logProbability$var49);
			logProbability$$model = (logProbability$$model + logProbability$var49);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$distributionAccumulator = (((0.0 <= y) && (y < b.length))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
			logProbability$var37 = cv$distributionAccumulator;
			logProbability$y = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample41 = fixedFlag$sample41;
		} else {
			logProbability$var37 = logProbability$y;
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y]);
			logProbability$var40 = cv$distributionAccumulator;
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample41);
		} else {
			logProbability$var40 = logProbability$d;
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var48], d[y]));
			logProbability$var43 = cv$sampleAccumulator;
			logProbability$var49 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample41) && fixedFlag$sample44);
		} else {
			logProbability$var43 = logProbability$var49;
			logProbability$obs = (logProbability$obs + logProbability$var49);
			logProbability$$model = (logProbability$$model + logProbability$var49);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	private final void sample41() {
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[0]) + ((0 < b.length)?Math.log(b[0]):Double.NEGATIVE_INFINITY));
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
			cv$var38$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[1]) + ((1 < b.length)?Math.log(b[1]):Double.NEGATIVE_INFINITY));
		for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
		cv$var38$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var38$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var38$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var38$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var38$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			distribution$sample41[0] = 0.5;
			distribution$sample41[1] = 0.5;
		} else {
			distribution$sample41[0] = Math.exp((cv$var38$stateProbabilityGlobal[0] - cv$logSum));
			distribution$sample41[1] = Math.exp((cv$var38$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample41[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample44() {
		double cv$originalProbability;
		int cv$arrayLength = d.length;
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = d[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - d[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			if(fixedFlag$sample41) {
				if((0 == y)) {
					cv$reachedDistributionSourceRV = 1.0;
					double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[0]);
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				if((1 == y)) {
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[1]);
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				{
					double cv$probabilitySample41Value3 = distribution$sample41[0];
					cv$reachedDistributionSourceRV = cv$probabilitySample41Value3;
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value3) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				double cv$probabilitySample41Value12 = distribution$sample41[1];
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample41Value12);
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value12) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
		double cv$reachedDistributionSourceRV = 0.0;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		d[cv$indexToChange] = (d[cv$indexToChange] + cv$proposedDifference);
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		if(fixedFlag$sample41) {
			if((0 == y)) {
				cv$reachedDistributionSourceRV = 1.0;
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[0]);
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
				cv$stateProbabilityValue = cv$accumulatedProbabilities;
			}
			if((1 == y)) {
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[1]);
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
		} else {
			{
				double cv$probabilitySample41Value3 = distribution$sample41[0];
				cv$reachedDistributionSourceRV = cv$probabilitySample41Value3;
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value3) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
				cv$stateProbabilityValue = cv$accumulatedProbabilities;
			}
			double cv$probabilitySample41Value12 = distribution$sample41[1];
			cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample41Value12);
			double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value12) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
			if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
				cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
			else {
				if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				else
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
			}
		}
		if(((cv$stateProbabilityValue - (Math.log(cv$reachedDistributionSourceRV) + cv$originalProbability)) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
			d[cv$indexToChange] = (d[cv$indexToChange] - cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var38$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2];
		if(!setFlag$d)
			d = new double[d.length];
		if(!setFlag$obs)
			obs = new boolean[length$obs_measured];
		distribution$sample41 = new double[2];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		if(!fixedFlag$sample53) {
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				obs[var48] = DistributionSampling.sampleBernoulli(RNG$, d[y]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample41) {
			distribution$sample41[0] = ((0 < b.length)?b[0]:0.0);
			distribution$sample41[1] = ((1 < b.length)?b[1]:0.0);
		}
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample41)
				sample41();
			if(!fixedFlag$sample44)
				sample44();
		} else {
			if(!fixedFlag$sample44)
				sample44();
			if(!fixedFlag$sample41)
				sample41();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var7 = a[0];
		var7[0] = 0.4;
		var7[1] = 0.6;
		double[] var17 = a[1];
		var17[0] = 0.2;
		var17[1] = 0.3;
		var17[2] = 0.5;
		b[0] = 0.35;
		b[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample41)
			logProbability$y = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$d = 0.0;
		logProbability$var43 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var49 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample41();
		logProbabilityDistribution$sample44();
		logProbabilityDistribution$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample41();
		logProbabilityValue$sample44();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray4(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[] b = { 0.35, 0.65 };\n    int y = categorical(b).sampleDistribution();\n    double[] d = dirichlet(a[y]).sample();\n    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}