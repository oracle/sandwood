package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample63 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample28;
	private double[] logProbability$sample63;
	private double logProbability$tau;
	private double logProbability$var19;
	private double logProbability$var30;
	private double logProbability$var34;
	private double[] logProbability$var57;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] phi;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double tau;
	private double[] weights;
	private double[][] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample63 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	@Override
	public final int get$k() {
		return k;
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
	public final double get$logProbability$tau() {
		return logProbability$tau;
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
	public final double get$tau() {
		return tau;
	}

	@Override
	public final void set$tau(double cv$value) {
		tau = cv$value;
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample63 = false;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample63 = false;
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
	public final double[] get$y() {
		return y;
	}

	@Override
	public final void set$y(double[] cv$value) {
		y = cv$value;
		setFlag$y = true;
		fixedProbFlag$sample63 = false;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((weights[var23] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample28[var23] = cv$distributionAccumulator;
				if((0 < n))
					logProbability$phi = (logProbability$phi + cv$distributionAccumulator);
			}
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$sampleValue = logProbability$sample28[var23];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < n))
					logProbability$phi = (logProbability$phi + cv$sampleValue);
			}
			logProbability$var19 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var30 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var30 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(tau, 3.0, 1.0);
			logProbability$var34 = cv$distributionAccumulator;
			logProbability$tau = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			logProbability$var34 = logProbability$tau;
			logProbability$$model = (logProbability$$model + logProbability$tau);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$tau);
		}
	}

	private final void logProbabilityValue$sample63() {
		if(!fixedProbFlag$sample63) {
			double cv$accumulator = 0.0;
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
				double reduceVar$var55$8 = 0.0;
				for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1)
					reduceVar$var55$8 = (reduceVar$var55$8 + phi[i$var38][cv$reduction56Index]);
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((y[i$var38] - (reduceVar$var55$8 + bias)) / Math.sqrt(tau))) - (Math.log(tau) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var57[i$var38] = cv$distributionAccumulator;
				logProbability$sample63[i$var38] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample63 = (((fixedFlag$sample63 && fixedFlag$sample28) && fixedFlag$sample35) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
				double cv$rvAccumulator = logProbability$sample63[i$var38];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var57[i$var38] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var23) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
			double cv$denominator = x[i$var38][var23];
			double reduceVar$var55$5 = 0.0;
			for(int cv$reduction273Index = 0; cv$reduction273Index < var23; cv$reduction273Index += 1)
				reduceVar$var55$5 = (reduceVar$var55$5 + phi[i$var38][cv$reduction273Index]);
			for(int cv$reduction273Index = (var23 + 1); cv$reduction273Index < k; cv$reduction273Index += 1)
				reduceVar$var55$5 = (reduceVar$var55$5 + phi[i$var38][cv$reduction273Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (y[i$var38] - (reduceVar$var55$5 + bias))));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = tau;
				cv$sigmaNotFound = false;
			}
		}
		weights[var23] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
			phi[i$var38][var23] = (weights[var23] * x[i$var38][var23]);
	}

	private final void sample35() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
			double reduceVar$var55$6 = 0.0;
			for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1)
				reduceVar$var55$6 = (reduceVar$var55$6 + phi[i$var38][cv$reduction56Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + y[i$var38]) - reduceVar$var55$6);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = tau;
				cv$sigmaNotFound = false;
			}
		}
		bias = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample39() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
			double reduceVar$var55$7 = 0.0;
			for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1)
				reduceVar$var55$7 = (reduceVar$var55$7 + phi[i$var38][cv$reduction56Index]);
			double cv$var57$diff = ((reduceVar$var55$7 + bias) - y[i$var38]);
			cv$sum = (cv$sum + (cv$var57$diff * cv$var57$diff));
			cv$count = (cv$count + 1);
		}
		tau = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 3.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$y)
			y = new double[x.length];
		if(!setFlag$weights)
			weights = new double[x[0].length];
		phi = new double[x.length][];
		for(int i$var38 = 0; i$var38 < x.length; i$var38 += 1)
			phi[i$var38] = new double[x[0].length];
		logProbability$sample28 = new double[x[0].length];
		logProbability$var57 = new double[x.length];
		logProbability$sample63 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample35)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						if(!fixedFlag$sample28)
							parallelFor(RNG$1, 0, k, 1,
								(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
											phi[i$var38][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
								}
							);

						if(!fixedFlag$sample63) {
							double reduceVar$var55$9 = 0.0;
							for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1)
								reduceVar$var55$9 = (reduceVar$var55$9 + phi[i$var38][cv$reduction56Index]);
							y[i$var38] = (((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$1)) + reduceVar$var55$9) + bias);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample35)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
							int i$var38 = index$i$var38;
							parallelFor(RNG$1, 0, k, 1,
								(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
											phi[i$var38][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample35)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
							int i$var38 = index$i$var38;
							parallelFor(RNG$1, 0, k, 1,
								(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
											phi[i$var38][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				for(int var23 = 0; var23 < k; var23 += 1)
					sample28(var23);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample39)
				sample39();
		} else {
			if(!fixedFlag$sample39)
				sample39();
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample28) {
				for(int var23 = (k - 1); var23 >= 0; var23 -= 1)
					sample28(var23);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		n = x.length;
		k = x[0].length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var19 = 0.0;
		logProbability$weights = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample28) {
			for(int var23 = 0; var23 < k; var23 += 1)
				logProbability$sample28[var23] = 0.0;
		}
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$bias = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$tau = 0.0;
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
			logProbability$var57[i$var38] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample63) {
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
				logProbability$sample63[i$var38] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample35)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
							int i$var38 = index$i$var38;
							parallelFor(RNG$1, 0, k, 1,
								(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
											phi[i$var38][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
								}
							);
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			y[cv$index1] = yMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$weights)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
							int i$var38 = index$i$var38;
							parallelFor(RNG$1, 0, k, 1,
								(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
											phi[i$var38][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
								}
							);
						}
				}
			);

	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LinearRegressionTest(double[][] x, double[] yMeasured) {\n\n        int n = x.length;\n        int k = x[0].length;\n\n        double[] y = new double[n];\n\n        double[] weights = gaussian(0,10).sample(k);\n        double bias = gaussian(0,10).sample();\n        double tau = inverseGamma(3.0,1.0).sample();\n\n        for(int i:[0..n)) {\n            double[] phi = new double[k];\n            for(int j:[0..k,1))\n                phi[j] = weights[j] * x[i][j];\n            \n            y[i] = gaussian(sum(phi) + bias, tau).sample();\n        }\n        \n        y.observe(yMeasured);\n\n    private double sum(double[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}\n";
	}
}