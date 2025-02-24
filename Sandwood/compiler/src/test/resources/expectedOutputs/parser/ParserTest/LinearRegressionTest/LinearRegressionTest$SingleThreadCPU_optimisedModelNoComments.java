package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LinearRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample74 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample74 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample24;
	private double[] logProbability$sample74;
	private double logProbability$tau;
	private double logProbability$var12;
	private double logProbability$var30;
	private double logProbability$var34;
	private double[] logProbability$var72;
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

	public LinearRegressionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample74() {
		return fixedFlag$sample74;
	}

	@Override
	public final void set$fixedFlag$sample74(boolean cv$value) {
		fixedFlag$sample74 = cv$value;
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
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
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample74 = false;
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
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((weights[var23] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample24[var23] = cv$distributionAccumulator;
				if((0 < n))
					logProbability$phi = (logProbability$phi + cv$distributionAccumulator);
			}
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$sampleValue = logProbability$sample24[var23];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < n))
					logProbability$phi = (logProbability$phi + cv$sampleValue);
			}
			logProbability$var12 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((bias / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var30 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var30 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(tau, 3.0, 1.0);
			logProbability$var34 = cv$distributionAccumulator;
			logProbability$tau = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var34 = logProbability$tau;
			logProbability$$model = (logProbability$$model + logProbability$tau);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$tau);
		}
	}

	private final void logProbabilityValue$sample74() {
		if(!fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				double reduceVar$var70$3 = 0.0;
				for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1)
					reduceVar$var70$3 = (reduceVar$var70$3 + phi[i$var45][cv$reduction65Index]);
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((y[i$var45] - (reduceVar$var70$3 + bias)) / Math.sqrt(tau))) - (Math.log(tau) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var72[i$var45] = cv$distributionAccumulator;
				logProbability$sample74[i$var45] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample74 = (((fixedFlag$sample74 && fixedFlag$sample24) && fixedFlag$sample31) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				double cv$rvAccumulator = logProbability$sample74[i$var45];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[i$var45] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample24(int var23) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
			double cv$denominator = x[i$var45][var23];
			double reduceVar$var70$0 = 0.0;
			for(int cv$reduction152Index = 0; cv$reduction152Index < var23; cv$reduction152Index += 1)
				reduceVar$var70$0 = (reduceVar$var70$0 + phi[i$var45][cv$reduction152Index]);
			for(int cv$reduction152Index = (var23 + 1); cv$reduction152Index < k; cv$reduction152Index += 1)
				reduceVar$var70$0 = (reduceVar$var70$0 + phi[i$var45][cv$reduction152Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (y[i$var45] - (reduceVar$var70$0 + bias))));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = tau;
				cv$sigmaNotFound = false;
			}
		}
		weights[var23] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
			phi[i$var45][var23] = (weights[var23] * x[i$var45][var23]);
	}

	private final void sample31() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
			double reduceVar$var70$1 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1)
				reduceVar$var70$1 = (reduceVar$var70$1 + phi[i$var45][cv$reduction65Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + y[i$var45]) - reduceVar$var70$1);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = tau;
				cv$sigmaNotFound = false;
			}
		}
		bias = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample35() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
			double reduceVar$var70$2 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1)
				reduceVar$var70$2 = (reduceVar$var70$2 + phi[i$var45][cv$reduction65Index]);
			double cv$var72$diff = ((reduceVar$var70$2 + bias) - y[i$var45]);
			cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
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
		for(int i$var45 = 0; i$var45 < x.length; i$var45 += 1)
			phi[i$var45] = new double[x[0].length];
		logProbability$sample24 = new double[x[0].length];
		logProbability$var72 = new double[x.length];
		logProbability$sample74 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				weights[var23] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample31)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
			if(!fixedFlag$sample24) {
				for(int j$var55 = 0; j$var55 < k; j$var55 += 1)
					phi[i$var45][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
			}
			if(!fixedFlag$sample74) {
				double reduceVar$var70$4 = 0.0;
				for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1)
					reduceVar$var70$4 = (reduceVar$var70$4 + phi[i$var45][cv$reduction65Index]);
				y[i$var45] = (((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$)) + reduceVar$var70$4) + bias);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				weights[var23] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample31)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample24) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				for(int j$var55 = 0; j$var55 < k; j$var55 += 1)
					phi[i$var45][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				weights[var23] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample31)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample24) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				for(int j$var55 = 0; j$var55 < k; j$var55 += 1)
					phi[i$var45][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample24) {
				for(int var23 = 0; var23 < k; var23 += 1)
					sample24(var23);
			}
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample35)
				sample35();
		} else {
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample24) {
				for(int var23 = (k - 1); var23 >= 0; var23 -= 1)
					sample24(var23);
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
		logProbability$var12 = 0.0;
		logProbability$weights = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				logProbability$sample24[var23] = 0.0;
		}
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$bias = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$tau = 0.0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
			logProbability$var72[i$var45] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
				logProbability$sample74[i$var45] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				weights[var23] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample31)
			bias = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		if(!fixedFlag$sample24) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				for(int j$var55 = 0; j$var55 < k; j$var55 += 1)
					phi[i$var45][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
			}
		}
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
		if(setFlag$weights) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				for(int j$var55 = 0; j$var55 < k; j$var55 += 1)
					phi[i$var45][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
			}
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
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}