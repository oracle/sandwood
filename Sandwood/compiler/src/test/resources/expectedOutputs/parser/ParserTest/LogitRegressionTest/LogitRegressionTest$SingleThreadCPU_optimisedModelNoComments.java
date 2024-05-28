package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LogitRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[][] guard$sample46bernoulli104$global;
	private boolean[][] guard$sample46put100$global;
	private double[][] indicator;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[][] logProbability$sample105;
	private double[] logProbability$sample46;
	private double[][] logProbability$var100;
	private double logProbability$var30;
	private double logProbability$var48;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] p;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double[] weights;
	private double[][] x;
	private boolean[][] y;
	private boolean[][] yMeasured;

	public LogitRegressionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		fixedFlag$sample105 = cv$value;
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final int get$k() {
		return 3;
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
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final double[][] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[][] cv$value) {
		x = cv$value;
	}

	@Override
	public final boolean[][] get$y() {
		return y;
	}

	@Override
	public final void set$y(boolean[][] cv$value) {
		y = cv$value;
		setFlag$y = true;
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample105() {
		if(!fixedProbFlag$sample105) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				{
					double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + bias));
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					logProbability$var100[i][0] = cv$weightedProbability;
					logProbability$sample105[i][0] = cv$weightedProbability;
				}
				{
					double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + bias));
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					logProbability$var100[i][1] = cv$weightedProbability;
					logProbability$sample105[i][1] = cv$weightedProbability;
				}
				double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + bias));
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				logProbability$var100[i][2] = cv$weightedProbability;
				logProbability$sample105[i][2] = cv$weightedProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample105 = ((fixedFlag$sample105 && fixedFlag$sample46) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				{
					double cv$rvAccumulator = logProbability$sample105[i][0];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var100[i][0] = cv$rvAccumulator;
				}
				{
					double cv$rvAccumulator = logProbability$sample105[i][1];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var100[i][1] = cv$rvAccumulator;
				}
				double cv$rvAccumulator = logProbability$sample105[i][2];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var100[i][2] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$sampleAccumulator;
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[0] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = cv$weightedProbability;
				logProbability$sample46[0] = cv$weightedProbability;
				if((0 < n)) {
					logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
					logProbability$p = (logProbability$p + cv$weightedProbability);
				}
			}
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[1] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				logProbability$sample46[1] = cv$weightedProbability;
				if((0 < n)) {
					logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
					logProbability$p = (logProbability$p + cv$weightedProbability);
				}
			}
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[2] / 3.1622776601683795)) - 1.151292546497023);
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			logProbability$sample46[2] = cv$weightedProbability;
			if((0 < n)) {
				logProbability$indicator = (logProbability$indicator + cv$weightedProbability);
				logProbability$p = (logProbability$p + cv$weightedProbability);
			}
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double cv$rvAccumulator;
			{
				double cv$sampleValue = logProbability$sample46[0];
				cv$rvAccumulator = cv$sampleValue;
				if((0 < n)) {
					logProbability$indicator = (logProbability$indicator + cv$sampleValue);
					logProbability$p = (logProbability$p + cv$sampleValue);
				}
			}
			{
				double cv$sampleValue = logProbability$sample46[1];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < n)) {
					logProbability$indicator = (logProbability$indicator + cv$sampleValue);
					logProbability$p = (logProbability$p + cv$sampleValue);
				}
			}
			double cv$sampleValue = logProbability$sample46[2];
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			if((0 < n)) {
				logProbability$indicator = (logProbability$indicator + cv$sampleValue);
				logProbability$p = (logProbability$p + cv$sampleValue);
			}
			logProbability$var30 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var48 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = fixedFlag$sample53;
		} else {
			logProbability$var48 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void sample46(int var41) {
		double cv$originalValue = weights[var41];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][2] = false;
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][2] = false;
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][2] = false;
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][0] = false;
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][1] = false;
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46bernoulli104$global[i][2] = false;
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var77$14_4 = Math.exp((cv$originalValue * x[i][0]));
					if(!guard$sample46bernoulli104$global[i][0]) {
						guard$sample46bernoulli104$global[i][0] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][1]) {
						guard$sample46bernoulli104$global[i][1] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][2]) {
						guard$sample46bernoulli104$global[i][2] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var79$15_4 = Math.exp((cv$originalValue * x[i][1]));
					if(!guard$sample46bernoulli104$global[i][0]) {
						guard$sample46bernoulli104$global[i][0] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][1]) {
						guard$sample46bernoulli104$global[i][1] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][2]) {
						guard$sample46bernoulli104$global[i][2] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var82$16_4 = Math.exp((cv$originalValue * x[i][2]));
					if(!guard$sample46bernoulli104$global[i][0]) {
						guard$sample46bernoulli104$global[i][0] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][1]) {
						guard$sample46bernoulli104$global[i][1] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
					if(!guard$sample46bernoulli104$global[i][2]) {
						guard$sample46bernoulli104$global[i][2] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46bernoulli104$global[i][0]) {
						guard$sample46bernoulli104$global[i][0] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((Math.exp((cv$originalValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46bernoulli104$global[i][1]) {
						guard$sample46bernoulli104$global[i][1] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((Math.exp((cv$originalValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46bernoulli104$global[i][2]) {
						guard$sample46bernoulli104$global[i][2] = true;
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((Math.exp((cv$originalValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		weights[var41] = cv$proposedValue;
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample46put100$global[i][0] = false;
				guard$sample46put100$global[i][1] = false;
				guard$sample46put100$global[i][2] = false;
			}
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample46put100$global[i][0] = false;
				guard$sample46put100$global[i][1] = false;
				guard$sample46put100$global[i][2] = false;
			}
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample46put100$global[i][0] = false;
				guard$sample46put100$global[i][1] = false;
				guard$sample46put100$global[i][2] = false;
			}
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46put100$global[i][0] = false;
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46put100$global[i][1] = false;
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46put100$global[i][2] = false;
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][0]) {
					guard$sample46put100$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][1]) {
					guard$sample46put100$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][2]) {
					guard$sample46put100$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][0]) {
					guard$sample46put100$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][1]) {
					guard$sample46put100$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][2]) {
					guard$sample46put100$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][0]) {
					guard$sample46put100$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][1]) {
					guard$sample46put100$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample46put100$global[i][2]) {
					guard$sample46put100$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][0]) {
					guard$sample46put100$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][1]) {
					guard$sample46put100$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46put100$global[i][2]) {
					guard$sample46put100$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][2] = false;
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][2] = false;
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][0] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][1] = false;
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][2] = false;
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][0] = false;
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][1] = false;
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1)
				guard$sample46bernoulli104$global[i][2] = false;
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1) {
				double traceTempVariable$var77$14_4 = Math.exp((cv$proposedValue * x[i][0]));
				if(!guard$sample46bernoulli104$global[i][0]) {
					guard$sample46bernoulli104$global[i][0] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][1]) {
					guard$sample46bernoulli104$global[i][1] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][2]) {
					guard$sample46bernoulli104$global[i][2] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((traceTempVariable$var77$14_4 + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1) {
				double traceTempVariable$var79$15_4 = Math.exp((cv$proposedValue * x[i][1]));
				if(!guard$sample46bernoulli104$global[i][0]) {
					guard$sample46bernoulli104$global[i][0] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][1]) {
					guard$sample46bernoulli104$global[i][1] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][2]) {
					guard$sample46bernoulli104$global[i][2] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var79$15_4) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1) {
				double traceTempVariable$var82$16_4 = Math.exp((cv$proposedValue * x[i][2]));
				if(!guard$sample46bernoulli104$global[i][0]) {
					guard$sample46bernoulli104$global[i][0] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][1]) {
					guard$sample46bernoulli104$global[i][1] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
				if(!guard$sample46bernoulli104$global[i][2]) {
					guard$sample46bernoulli104$global[i][2] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var82$16_4)) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((var41 == 0)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46bernoulli104$global[i][0]) {
					guard$sample46bernoulli104$global[i][0] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], ((Math.exp((cv$proposedValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((var41 == 1)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46bernoulli104$global[i][1]) {
					guard$sample46bernoulli104$global[i][1] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], ((Math.exp((cv$proposedValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((var41 == 2)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample46bernoulli104$global[i][2]) {
					guard$sample46bernoulli104$global[i][2] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], ((Math.exp((cv$proposedValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias)) + cv$accumulatedProbabilities);
				}
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			weights[var41] = cv$originalValue;
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample46put100$global[i][0] = false;
					guard$sample46put100$global[i][1] = false;
					guard$sample46put100$global[i][2] = false;
				}
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample46put100$global[i][0] = false;
					guard$sample46put100$global[i][1] = false;
					guard$sample46put100$global[i][2] = false;
				}
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample46put100$global[i][0] = false;
					guard$sample46put100$global[i][1] = false;
					guard$sample46put100$global[i][2] = false;
				}
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46put100$global[i][0] = false;
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46put100$global[i][1] = false;
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample46put100$global[i][2] = false;
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][0]) {
						guard$sample46put100$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][1]) {
						guard$sample46put100$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][2]) {
						guard$sample46put100$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][0]) {
						guard$sample46put100$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][1]) {
						guard$sample46put100$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][2]) {
						guard$sample46put100$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][0]) {
						guard$sample46put100$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][1]) {
						guard$sample46put100$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample46put100$global[i][2]) {
						guard$sample46put100$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var41 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][0]) {
						guard$sample46put100$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var41 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][1]) {
						guard$sample46put100$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var41 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample46put100$global[i][2]) {
						guard$sample46put100$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
		}
	}

	private final void sample53() {
		double cv$originalValue = bias;
		double cv$originalProbability;
		double cv$var = ((bias * bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < n; i += 1) {
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + bias)) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + bias)) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + bias)) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		bias = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < n; i += 1) {
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][0], (p[i][0] + cv$proposedValue)) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][1], (p[i][1] + cv$proposedValue)) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(y[i][2], (p[i][2] + cv$proposedValue)) + cv$accumulatedProbabilities);
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var93 = 0;
			if((0 < x.length))
				cv$max_j$var93 = 3;
			guard$sample46put100$global = new boolean[x.length][cv$max_j$var93];
		}
		int cv$max_j$var93 = 0;
		if((0 < x.length))
			cv$max_j$var93 = 3;
		guard$sample46bernoulli104$global = new boolean[x.length][cv$max_j$var93];
	}

	@Override
	public final void allocator() {
		if(!setFlag$y) {
			y = new boolean[x.length][];
			for(int var23 = 0; var23 < x.length; var23 += 1)
				y[var23] = new boolean[3];
		}
		if(!setFlag$weights)
			weights = new double[3];
		indicator = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			indicator[i] = new double[3];
		p = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			p[i] = new double[3];
		logProbability$sample46 = new double[3];
		logProbability$var100 = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			logProbability$var100[i] = new double[3];
		logProbability$sample105 = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			logProbability$sample105[i] = new double[3];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample46) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample53)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			boolean[] var97 = y[i];
			if(!fixedFlag$sample46) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
			if(!fixedFlag$sample105)
				var97[0] = DistributionSampling.sampleBernoulli(RNG$, (p[i][0] + bias));
			if(!fixedFlag$sample46)
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			if(!fixedFlag$sample105)
				var97[1] = DistributionSampling.sampleBernoulli(RNG$, (p[i][1] + bias));
			if(!fixedFlag$sample46)
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			if(!fixedFlag$sample105)
				var97[2] = DistributionSampling.sampleBernoulli(RNG$, (p[i][2] + bias));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample46) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample53)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample46) {
			for(int i = 0; i < n; i += 1) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample46) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample53)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample46) {
			for(int i = 0; i < n; i += 1) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample46) {
				sample46(0);
				sample46(1);
				sample46(2);
			}
			if(!fixedFlag$sample53)
				sample53();
		} else {
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample46) {
				sample46(2);
				sample46(1);
				sample46(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		n = x.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = 0.0;
		logProbability$weights = 0.0;
		logProbability$indicator = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample46) {
			logProbability$sample46[0] = 0.0;
			logProbability$sample46[1] = 0.0;
			logProbability$sample46[2] = 0.0;
		}
		logProbability$var48 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			logProbability$var100[i][0] = 0.0;
			logProbability$var100[i][1] = 0.0;
			logProbability$var100[i][2] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample105) {
			for(int i = 0; i < n; i += 1) {
				logProbability$sample105[i][0] = 0.0;
				logProbability$sample105[i][1] = 0.0;
				logProbability$sample105[i][2] = 0.0;
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
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample46) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample53)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample46) {
			for(int i = 0; i < n; i += 1) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = yMeasured[cv$index1];
			boolean[] cv$target2 = y[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$weights) {
			for(int i = 0; i < n; i += 1) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
	}
}