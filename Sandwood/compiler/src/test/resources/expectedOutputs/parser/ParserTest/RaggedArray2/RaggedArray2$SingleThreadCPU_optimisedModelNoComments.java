package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray2$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var79$stateProbabilityGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[] distribution$sample84;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean fixedProbFlag$sample87 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var78;
	private double logProbability$var81;
	private double logProbability$var85;
	private double logProbability$var98;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[][] get$b() {
		return b;
	}

	@Override
	public final double[] get$c() {
		return c;
	}

	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		fixedFlag$sample104 = cv$value;
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample87 = false;
		fixedProbFlag$sample104 = false;
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
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
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
		fixedProbFlag$sample104 = false;
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
	public final double get$p() {
		return p;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample87 = false;
		fixedProbFlag$sample104 = false;
	}

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = obs[var97];
				if(fixedFlag$sample84) {
					if((0 == y)) {
						if((0 == i)) {
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2);
							cv$probabilityReached = 1.0;
						}
						if((1 == i)) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.8);
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
					if((1 == y)) {
						if((0 == i)) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.4);
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
						if((1 == i)) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2);
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
						if((2 == i)) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.6);
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
					if((0 == i)) {
						double cv$probabilitySample84Value5 = distribution$sample84[0];
						cv$distributionAccumulator = (Math.log(cv$probabilitySample84Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						cv$probabilityReached = cv$probabilitySample84Value5;
					}
					if((1 == i)) {
						double cv$probabilitySample84Value13 = distribution$sample84[0];
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.8));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
					}
					if((0 == i)) {
						double cv$probabilitySample84Value21 = distribution$sample84[1];
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value21) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.4));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value21);
					}
					if((1 == i)) {
						double cv$probabilitySample84Value29 = distribution$sample84[1];
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value29);
					}
					if((2 == i)) {
						double cv$probabilitySample84Value37 = distribution$sample84[1];
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value37) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.6));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value37);
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var85 = cv$sampleAccumulator;
			logProbability$var98 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		} else {
			logProbability$var85 = logProbability$var98;
			logProbability$obs = (logProbability$obs + logProbability$var98);
			logProbability$$model = (logProbability$$model + logProbability$var98);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var98);
		}
	}

	private final void logProbabilityDistribution$sample84() {
		if(!fixedProbFlag$sample84) {
			if(fixedFlag$sample84) {
				double cv$distributionAccumulator = (((0.0 <= y) && (y < c.length))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
				logProbability$var78 = cv$distributionAccumulator;
				logProbability$y = cv$distributionAccumulator;
				if(fixedFlag$sample87)
					logProbability$p = (logProbability$p + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample84 = true;
			}
		} else {
			logProbability$var78 = logProbability$y;
			if((fixedFlag$sample84 && fixedFlag$sample87))
				logProbability$p = (logProbability$p + logProbability$y);
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(fixedFlag$sample84) {
				if((0 == y)) {
					double[] var80 = a[0];
					cv$distributionAccumulator = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
					cv$probabilityReached = 1.0;
				}
				if((1 == y)) {
					double[] var80 = a[1];
					double cv$weightedProbability = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
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
				{
					double cv$probabilitySample84Value4 = distribution$sample84[0];
					double[] var80 = a[0];
					cv$distributionAccumulator = (Math.log(cv$probabilitySample84Value4) + (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY));
					cv$probabilityReached = cv$probabilitySample84Value4;
				}
				double cv$probabilitySample84Value13 = distribution$sample84[1];
				double[] var80 = a[1];
				double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY));
				if((cv$weightedProbability < cv$distributionAccumulator))
					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
				else {
					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
						cv$distributionAccumulator = cv$weightedProbability;
					else
						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
				}
				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			logProbability$var81 = cv$distributionAccumulator;
			logProbability$i = cv$distributionAccumulator;
			if((fixedFlag$sample84 && fixedFlag$sample87))
				logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		} else {
			logProbability$var81 = logProbability$i;
			if((fixedFlag$sample84 && fixedFlag$sample87))
				logProbability$p = (logProbability$p + logProbability$i);
			logProbability$$model = (logProbability$$model + logProbability$i);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var97], p));
			logProbability$var85 = cv$sampleAccumulator;
			logProbability$var98 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		} else {
			logProbability$var85 = logProbability$var98;
			logProbability$obs = (logProbability$obs + logProbability$var98);
			logProbability$$model = (logProbability$$model + logProbability$var98);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var98);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$distributionAccumulator = (((0.0 <= y) && (y < c.length))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
			logProbability$var78 = cv$distributionAccumulator;
			logProbability$y = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample84 = fixedFlag$sample84;
		} else {
			logProbability$var78 = logProbability$y;
			logProbability$p = (logProbability$p + logProbability$y);
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double[] var80 = a[y];
			double cv$distributionAccumulator = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
			logProbability$var81 = cv$distributionAccumulator;
			logProbability$i = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		} else {
			logProbability$var81 = logProbability$i;
			logProbability$p = (logProbability$p + logProbability$i);
			logProbability$$model = (logProbability$$model + logProbability$i);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	private final void sample84() {
		{
			double[] cv$temp$1$var80 = a[0];
			double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY) + ((0 < c.length)?Math.log(c[0]):Double.NEGATIVE_INFINITY));
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				if((1 == i)) {
					if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
						else
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
				}
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
			cv$var79$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$2$var80 = a[1];
		double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY) + ((1 < c.length)?Math.log(c[1]):Double.NEGATIVE_INFINITY));
		for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if((0 == i)) {
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			}
			if((1 == i)) {
				if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
					else
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
				}
				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
			}
			if((2 == i)) {
				if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
					else
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
				}
				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
			}
			cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
			if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
				cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
			else {
				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
					cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
				else
					cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
			}
		}
		cv$var79$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var79$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var79$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var79$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var79$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			distribution$sample84[0] = 0.5;
			distribution$sample84[1] = 0.5;
		} else {
			distribution$sample84[0] = Math.exp((cv$var79$stateProbabilityGlobal[0] - cv$logSum));
			distribution$sample84[1] = Math.exp((cv$var79$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var79$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample84[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample87() {
		int cv$noStates = 0;
		if(fixedFlag$sample84) {
			if((0 == y))
				cv$noStates = a[0].length;
			if((1 == y))
				cv$noStates = Math.max(cv$noStates, a[1].length);
		} else
			cv$noStates = Math.max(a[0].length, a[1].length);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			i = cv$valuePos;
			p = b[y][cv$valuePos];
			if(fixedFlag$sample84) {
				if((0 == y)) {
					cv$reachedDistributionSourceRV = 1.0;
					double[] cv$temp$0$var80 = a[0];
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var80.length)?Math.log(cv$temp$0$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((0 == cv$valuePos)) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						if((1 == cv$valuePos)) {
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
								else
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
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
				if((1 == y)) {
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$2$var80 = a[1];
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$2$var80.length)?Math.log(cv$temp$2$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((0 == cv$valuePos)) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						if((1 == cv$valuePos)) {
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
								else
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						if((2 == cv$valuePos)) {
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
								else
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
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
			} else {
				{
					double cv$probabilitySample84Value24 = distribution$sample84[0];
					cv$reachedDistributionSourceRV = cv$probabilitySample84Value24;
					double[] cv$temp$1$var80 = a[0];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value24) + ((cv$valuePos < cv$temp$1$var80.length)?Math.log(cv$temp$1$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((0 == cv$valuePos)) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						if((1 == cv$valuePos)) {
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
								else
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
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
				double cv$probabilitySample84Value33 = distribution$sample84[1];
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample84Value33);
				double[] cv$temp$3$var80 = a[1];
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value33) + ((cv$valuePos < cv$temp$3$var80.length)?Math.log(cv$temp$3$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
				for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((0 == cv$valuePos)) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					if((1 == cv$valuePos)) {
						if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
					if((2 == cv$valuePos)) {
						if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
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
			cv$var82$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		double cv$logSum;
		double cv$lseMax = cv$var82$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var82$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var82$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var82$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var82$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var82$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var82$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var82$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		i = DistributionSampling.sampleCategorical(RNG$, cv$var82$stateProbabilityGlobal);
		p = b[y][i];
	}

	@Override
	public final void allocateScratch() {
		cv$var79$stateProbabilityGlobal = new double[2];
		cv$var82$stateProbabilityGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		c = new double[2];
		if(!setFlag$obs)
			obs = new boolean[length$obs_measured];
		distribution$sample84 = new double[2];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
			p = b[y][i];
		if(!fixedFlag$sample104) {
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1)
				obs[var97] = DistributionSampling.sampleBernoulli(RNG$, p);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample84) {
			distribution$sample84[0] = ((0 < c.length)?c[0]:0.0);
			distribution$sample84[1] = ((1 < c.length)?c[1]:0.0);
		}
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
			p = b[y][i];
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample87)
				sample87();
		} else {
			if(!fixedFlag$sample87)
				sample87();
			if(!fixedFlag$sample84)
				sample84();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var7 = a[0];
		var7[0] = 0.4;
		var7[1] = 0.6;
		double[] var20 = a[1];
		var20[0] = 0.2;
		var20[1] = 0.3;
		var20[2] = 0.5;
		double[] var39 = b[0];
		var39[0] = 0.2;
		var39[1] = 0.8;
		double[] var52 = b[1];
		var52[0] = 0.4;
		var52[1] = 0.2;
		var52[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var78 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$y = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$i = 0.0;
		logProbability$var85 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample104)
			logProbability$var98 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		logProbabilityValue$sample104();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample84();
		logProbabilityDistribution$sample87();
		logProbabilityDistribution$sample104();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample84();
		logProbabilityValue$sample87();
		logProbabilityValue$sample104();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
			p = b[y][i];
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray2(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    double[] c = { 0.35, 0.65 };\n    int y = categorical(c).sampleDistribution();\n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}