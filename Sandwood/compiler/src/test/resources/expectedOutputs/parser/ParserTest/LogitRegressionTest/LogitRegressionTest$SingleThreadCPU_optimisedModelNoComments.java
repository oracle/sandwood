package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LogitRegressionTest$SingleThreadCPU extends CoreModelSingleThreadCPU implements LogitRegressionTest$CoreInterface {
double bias;
	boolean[] constrainedFlag$sample35;
	boolean constrainedFlag$sample42 = true;
	boolean fixedFlag$sample35 = false;
	boolean fixedFlag$sample42 = false;
	boolean fixedProbFlag$sample35 = false;
	boolean fixedProbFlag$sample42 = false;
	boolean fixedProbFlag$sample94 = false;
	double[][] indicator;
	int k;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double[] logProbability$sample35;
	double[][] logProbability$sample94;
	double logProbability$weights;
	double logProbability$y;
	int n;
	double[][] p;
	boolean system$gibbsForward = true;
	double[] weights;
	double[][] x;
	boolean[][] y;
	boolean[][] yMeasured;
	boolean[][] guard$sample35bernoulli93$global;
	boolean[][] guard$sample35put89$global;

	public LogitRegressionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample94 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value, boolean allocated$) {
		fixedFlag$sample35 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
				constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
		}
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample94 = (cv$value && fixedProbFlag$sample94);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
		fixedFlag$sample42 = cv$value;
		constrainedFlag$sample42 = (cv$value || constrainedFlag$sample42);
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample94 = (cv$value && fixedProbFlag$sample94);
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
	public final void set$weights(double[] cv$value, boolean allocated$) {
		weights = cv$value;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample94 = false;
	}

	@Override
	public final double[][] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[][] cv$value, boolean allocated$) {
		x = cv$value;
	}

	@Override
	public final boolean[][] get$y() {
		return y;
	}

	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(boolean[][] cv$value, boolean allocated$) {
		yMeasured = cv$value;
	}

	private final void drawValueSample35(int var33) {
		weights[var33] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if((var33 == 0)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
		}
		if((var33 == 1)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
		}
		if((var33 == 2)) {
			for(int i = 0; i < n; i += 1)
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
		}
		if((var33 == 0)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample35put89$global[i][0] = false;
				guard$sample35put89$global[i][1] = false;
				guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample35put89$global[i][0] = false;
				guard$sample35put89$global[i][1] = false;
				guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < n; i += 1) {
				guard$sample35put89$global[i][0] = false;
				guard$sample35put89$global[i][1] = false;
				guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 0)) {
			for(int i = 0; i < n; i += 1)
				guard$sample35put89$global[i][0] = false;
		}
		if((var33 == 1)) {
			for(int i = 0; i < n; i += 1)
				guard$sample35put89$global[i][1] = false;
		}
		if((var33 == 2)) {
			for(int i = 0; i < n; i += 1)
				guard$sample35put89$global[i][2] = false;
		}
		if((var33 == 0)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][0]) {
					guard$sample35put89$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][1]) {
					guard$sample35put89$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][2]) {
					guard$sample35put89$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][0]) {
					guard$sample35put89$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][1]) {
					guard$sample35put89$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][2]) {
					guard$sample35put89$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][0]) {
					guard$sample35put89$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][1]) {
					guard$sample35put89$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
				if(!guard$sample35put89$global[i][2]) {
					guard$sample35put89$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var33 == 0)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][0]) {
					guard$sample35put89$global[i][0] = true;
					p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][1]) {
					guard$sample35put89$global[i][1] = true;
					p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < n; i += 1) {
				if(!guard$sample35put89$global[i][2]) {
					guard$sample35put89$global[i][2] = true;
					p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
				}
			}
		}
	}

	private final void drawValueSample42() {
		bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
	}

	private final void inferSample35(int var33) {
		constrainedFlag$sample35[var33] = false;
		double cv$originalValue = weights[var33];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var69$15_4 = Math.exp((cv$originalValue * x[i][0]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][0] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][1] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][2] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var71$16_4 = Math.exp((cv$originalValue * x[i][1]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var74$17_4 = Math.exp((cv$originalValue * x[i][2]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((Math.exp((cv$originalValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((Math.exp((cv$originalValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((Math.exp((cv$originalValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample35[var33]) {
			weights[var33] = cv$proposedValue;
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample35put89$global[i][0] = false;
					guard$sample35put89$global[i][1] = false;
					guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample35put89$global[i][0] = false;
					guard$sample35put89$global[i][1] = false;
					guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					guard$sample35put89$global[i][0] = false;
					guard$sample35put89$global[i][1] = false;
					guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35put89$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35put89$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35put89$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][0]) {
						guard$sample35put89$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][1]) {
						guard$sample35put89$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][2]) {
						guard$sample35put89$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][0]) {
						guard$sample35put89$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][1]) {
						guard$sample35put89$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][2]) {
						guard$sample35put89$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][0]) {
						guard$sample35put89$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][1]) {
						guard$sample35put89$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
					if(!guard$sample35put89$global[i][2]) {
						guard$sample35put89$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][0]) {
						guard$sample35put89$global[i][0] = true;
						p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][1]) {
						guard$sample35put89$global[i][1] = true;
						p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35put89$global[i][2]) {
						guard$sample35put89$global[i][2] = true;
						p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
					}
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1)
					guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var69$15_4 = Math.exp((cv$proposedValue * x[i][0]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][0] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][1] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((indicator[i][2] / ((traceTempVariable$var69$15_4 + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var71$16_4 = Math.exp((cv$proposedValue * x[i][1]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][0] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][1] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((indicator[i][2] / ((indicator[i][0] + traceTempVariable$var71$16_4) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					double traceTempVariable$var74$17_4 = Math.exp((cv$proposedValue * x[i][2]));
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + traceTempVariable$var74$17_4)) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][0]) {
						guard$sample35bernoulli93$global[i][0] = true;
						constrainedFlag$sample35[0] = true;
						double var91 = ((Math.exp((cv$proposedValue * x[i][0])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][1]) {
						guard$sample35bernoulli93$global[i][1] = true;
						constrainedFlag$sample35[1] = true;
						double var91 = ((Math.exp((cv$proposedValue * x[i][1])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < n; i += 1) {
					if(!guard$sample35bernoulli93$global[i][2]) {
						guard$sample35bernoulli93$global[i][2] = true;
						constrainedFlag$sample35[2] = true;
						double var91 = ((Math.exp((cv$proposedValue * x[i][2])) / ((indicator[i][0] + indicator[i][1]) + indicator[i][2])) + bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				weights[var33] = cv$originalValue;
				if((var33 == 0)) {
					for(int i = 0; i < n; i += 1)
						indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				}
				if((var33 == 1)) {
					for(int i = 0; i < n; i += 1)
						indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				}
				if((var33 == 2)) {
					for(int i = 0; i < n; i += 1)
						indicator[i][2] = Math.exp((weights[2] * x[i][2]));
				}
				if((var33 == 0)) {
					for(int i = 0; i < n; i += 1) {
						guard$sample35put89$global[i][0] = false;
						guard$sample35put89$global[i][1] = false;
						guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < n; i += 1) {
						guard$sample35put89$global[i][0] = false;
						guard$sample35put89$global[i][1] = false;
						guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < n; i += 1) {
						guard$sample35put89$global[i][0] = false;
						guard$sample35put89$global[i][1] = false;
						guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 0)) {
					for(int i = 0; i < n; i += 1)
						guard$sample35put89$global[i][0] = false;
				}
				if((var33 == 1)) {
					for(int i = 0; i < n; i += 1)
						guard$sample35put89$global[i][1] = false;
				}
				if((var33 == 2)) {
					for(int i = 0; i < n; i += 1)
						guard$sample35put89$global[i][2] = false;
				}
				if((var33 == 0)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][0]) {
							guard$sample35put89$global[i][0] = true;
							p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][1]) {
							guard$sample35put89$global[i][1] = true;
							p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][2]) {
							guard$sample35put89$global[i][2] = true;
							p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][0]) {
							guard$sample35put89$global[i][0] = true;
							p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][1]) {
							guard$sample35put89$global[i][1] = true;
							p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][2]) {
							guard$sample35put89$global[i][2] = true;
							p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][0]) {
							guard$sample35put89$global[i][0] = true;
							p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][1]) {
							guard$sample35put89$global[i][1] = true;
							p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
						if(!guard$sample35put89$global[i][2]) {
							guard$sample35put89$global[i][2] = true;
							p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
				if((var33 == 0)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][0]) {
							guard$sample35put89$global[i][0] = true;
							p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][1]) {
							guard$sample35put89$global[i][1] = true;
							p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < n; i += 1) {
						if(!guard$sample35put89$global[i][2]) {
							guard$sample35put89$global[i][2] = true;
							p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
						}
					}
				}
			}
		}
	}

	private final void inferSample42() {
		constrainedFlag$sample42 = false;
		double cv$originalValue = bias;
		double cv$originalProbability;
		double cv$var = (((bias < 0)?(-bias):bias) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < n; i += 1) {
				{
					constrainedFlag$sample42 = true;
					double var91 = (p[i][0] + bias);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					constrainedFlag$sample42 = true;
					double var91 = (p[i][1] + bias);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				constrainedFlag$sample42 = true;
				double var91 = (p[i][2] + bias);
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample42) {
			bias = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < n; i += 1) {
				{
					constrainedFlag$sample42 = true;
					double var91 = (p[i][0] + cv$proposedValue);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					constrainedFlag$sample42 = true;
					double var91 = (p[i][1] + cv$proposedValue);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				constrainedFlag$sample42 = true;
				double var91 = (p[i][2] + cv$proposedValue);
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				bias = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$sampleAccumulator;
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[0] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = cv$weightedProbability;
				logProbability$sample35[0] = cv$weightedProbability;
			}
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[1] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				logProbability$sample35[1] = cv$weightedProbability;
			}
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((weights[2] / 3.1622776601683795)) - 1.151292546497023);
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			logProbability$sample35[2] = cv$weightedProbability;
			logProbability$weights = (logProbability$weights + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$rvAccumulator = ((logProbability$sample35[0] + logProbability$sample35[1]) + logProbability$sample35[2]);
			logProbability$weights = (logProbability$weights + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample94() {
		if(!fixedProbFlag$sample94) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				{
					double var91 = (p[i][0] + bias);
					double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					logProbability$sample94[i][0] = cv$weightedProbability;
				}
				{
					double var91 = (p[i][1] + bias);
					double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					logProbability$sample94[i][1] = cv$weightedProbability;
				}
				double var91 = (p[i][2] + bias);
				double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				logProbability$sample94[i][2] = cv$weightedProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample94 = (fixedFlag$sample35 && fixedFlag$sample42);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				cv$accumulator = (cv$accumulator + logProbability$sample94[i][0]);
				cv$accumulator = (cv$accumulator + logProbability$sample94[i][1]);
				cv$accumulator = (cv$accumulator + logProbability$sample94[i][2]);
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		y = new boolean[x.length][];
		for(int var15 = 0; var15 < x.length; var15 += 1)
			y[var15] = new boolean[3];
		if(!fixedFlag$sample35)
			weights = new double[3];
		indicator = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			indicator[i] = new double[3];
		p = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			p[i] = new double[3];
		constrainedFlag$sample35 = new boolean[3];
		logProbability$sample35 = new double[3];
		logProbability$sample94 = new double[x.length][];
		for(int i = 0; i < x.length; i += 1)
			logProbability$sample94[i] = new double[3];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var85 = 0;
			if((0 < x.length))
				cv$max_j$var85 = 3;
			guard$sample35put89$global = new boolean[x.length][cv$max_j$var85];
		}
		int cv$max_j$var85 = 0;
		if((0 < x.length))
			cv$max_j$var85 = 3;
		guard$sample35bernoulli93$global = new boolean[x.length][cv$max_j$var85];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample35) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample42)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			if(!fixedFlag$sample35) {
				indicator[i][0] = Math.exp((weights[0] * x[i][0]));
				indicator[i][1] = Math.exp((weights[1] * x[i][1]));
				indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			}
			boolean[] var89 = y[i];
			if(!fixedFlag$sample35)
				p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[0] = DistributionSampling.sampleBernoulli(RNG$, (p[i][0] + bias));
			if(!fixedFlag$sample35)
				p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[1] = DistributionSampling.sampleBernoulli(RNG$, (p[i][1] + bias));
			if(!fixedFlag$sample35)
				p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[2] = DistributionSampling.sampleBernoulli(RNG$, (p[i][2] + bias));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample35) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample42)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample35) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample42)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			boolean[] var89 = y[i];
			p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[0] = DistributionSampling.sampleBernoulli(RNG$, (p[i][0] + bias));
			p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[1] = DistributionSampling.sampleBernoulli(RNG$, (p[i][1] + bias));
			p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			var89[2] = DistributionSampling.sampleBernoulli(RNG$, (p[i][2] + bias));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample35) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample42)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35) {
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
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample35) {
			weights[0] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[1] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			weights[2] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample42)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < n; i += 1) {
			indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample35) {
				inferSample35(0);
				inferSample35(1);
				inferSample35(2);
			}
			if(!fixedFlag$sample42)
				inferSample42();
		} else {
			if(!fixedFlag$sample42)
				inferSample42();
			if(!fixedFlag$sample35) {
				inferSample35(2);
				inferSample35(1);
				inferSample35(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample35[0])
			drawValueSample35(0);
		if(!constrainedFlag$sample35[1])
			drawValueSample35(1);
		if(!constrainedFlag$sample35[2])
			drawValueSample35(2);
		if(!constrainedFlag$sample42)
			drawValueSample42();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$weights = 0.0;
		if(!fixedProbFlag$sample35) {
			logProbability$sample35[0] = Double.NaN;
			logProbability$sample35[1] = Double.NaN;
			logProbability$sample35[2] = Double.NaN;
		}
		if(!fixedProbFlag$sample42)
			logProbability$bias = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample94) {
			for(int i = 0; i < n; i += 1) {
				logProbability$sample94[i][0] = Double.NaN;
				logProbability$sample94[i][1] = Double.NaN;
				logProbability$sample94[i][2] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		n = x.length;
		for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
			constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void propagateObservedValues() {
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
		for(int i = 0; i < n; i += 1) {
			indicator[i][0] = Math.exp((weights[0] * x[i][0]));
			indicator[i][1] = Math.exp((weights[1] * x[i][1]));
			indicator[i][2] = Math.exp((weights[2] * x[i][2]));
			p[i][0] = (indicator[i][0] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][1] = (indicator[i][1] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
			p[i][2] = (indicator[i][2] / ((indicator[i][0] + indicator[i][1]) + indicator[i][2]));
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n"
		     + "    int k = 3;\n"
		     + "\n"
		     + "    int n = x.length;\n"
		     + "    boolean[][] y = new boolean[n][k];\n"
		     + "\n"
		     + "    double[] weights = gaussian(0,10).sample(k);\n"
		     + "    //TODO, change this to a beta distribution.\n"
		     + "    double bias = gaussian(0,10).sample();\n"
		     + "\n"
		     + "    for(int i:[0 .. n)) {\n"
		     + "        double[] indicator = new double[k];\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            indicator[j] = exp(weights[j] * x[i][j]);\n"
		     + "        }\n"
		     + "        \n"
		     + "        //Single assignment semantics means a for loop cannot be used here.\n"
		     + "        double sum = indicator[0] + indicator[1] + indicator[2];\n"
		     + "        double[] p = new double[k];\n"
		     + "\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            p[j] = indicator[j]/sum;\n"
		     + "            //This really wants to be a Categorical, but for now y will have\n"
		     + "            //to be arrays with just a single value set.\n"
		     + "            y[i][j] = bernoulli(p[j] + bias).sample();\n"
		     + "        }    \n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}