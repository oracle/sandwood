package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK4$CoreInterface {
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample65 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample65 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[][] logProbability$sample41;
	private double[] logProbability$sample65;
	private double[][] logProbability$var33;
	private double[] logProbability$var55;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean setFlag$indirection2 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	@Override
	public final void set$fixedFlag$sample65(boolean cv$value) {
		fixedFlag$sample65 = cv$value;
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	@Override
	public final int[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(int[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
	}

	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	@Override
	public final void set$indirection1(double[][] cv$value) {
		indirection1 = cv$value;
		setFlag$indirection1 = true;
	}

	@Override
	public final double[][] get$indirection2() {
		return indirection2;
	}

	@Override
	public final void set$indirection2(double[][] cv$value) {
		indirection2 = cv$value;
		setFlag$indirection2 = true;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	@Override
	public final double get$logProbability$indirection1() {
		return logProbability$indirection1;
	}

	@Override
	public final double get$logProbability$indirection2() {
		return logProbability$indirection2;
	}

	@Override
	public final int[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(int[] cv$value) {
		observed = cv$value;
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$sampleValue = indirection1[i][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var33[i][j] = cv$distributionAccumulator;
					logProbability$sample41[i][j] = cv$distributionAccumulator;
					logProbability$indirection2 = (logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = fixedFlag$sample41;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$sampleValue = logProbability$sample41[i][j];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var33[i][j] = cv$sampleValue;
					logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample65() {
		if(!fixedProbFlag$sample65) {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				int cv$sampleValue = generated[m];
				double[] var54 = indirection2[m];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var55[m] = cv$distributionAccumulator;
				logProbability$sample65[m] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedFlag$sample41);
		} else {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$rvAccumulator = logProbability$sample65[m];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[m] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample41(int i, int j) {
		double cv$originalValue = indirection1[i][j];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double[] cv$temp$2$var54 = indirection2[i];
			cv$originalProbability = ((((0.0 <= generated[i]) && (generated[i] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		}
		indirection1[i][j] = cv$proposedValue;
		indirection2[i][j] = indirection1[i][j];
		double[] cv$temp$2$var54 = indirection2[i];
		double cv$accumulatedProbabilities = ((((0.0 <= generated[i]) && (generated[i] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			indirection1[i][j] = cv$originalValue;
			indirection2[i][j] = indirection1[i][j];
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$generated)
			generated = new int[length$observed];
		if(!setFlag$indirection1) {
			indirection1 = new double[length$observed][];
			for(int var11 = 0; var11 < length$observed; var11 += 1)
				indirection1[var11] = new double[10];
		}
		if(!setFlag$indirection2) {
			indirection2 = new double[length$observed][];
			for(int var19 = 0; var19 < length$observed; var19 += 1)
				indirection2[var19] = new double[10];
		}
		logProbability$var33 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var33[i] = new double[10];
		logProbability$sample41 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$sample41[i] = new double[10];
		logProbability$var55 = new double[length$observed];
		logProbability$sample65 = new double[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample41) {
			for(int i = 0; i < length$observed; i += 1) {
				double[] var30 = indirection1[i];
				for(int j = 0; j < 10; j += 1)
					var30[j] = DistributionSampling.sampleUniform(RNG$);
			}
			for(int k = 0; k < length$observed; k += 1) {
				double[] var45 = indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var45[l] = indirection1[k][l];
			}
		}
		if(!fixedFlag$sample65) {
			for(int m = 0; m < length$observed; m += 1)
				generated[m] = DistributionSampling.sampleCategorical(RNG$, indirection2[m]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample41) {
			for(int i = 0; i < length$observed; i += 1) {
				double[] var30 = indirection1[i];
				for(int j = 0; j < 10; j += 1)
					var30[j] = DistributionSampling.sampleUniform(RNG$);
			}
			for(int k = 0; k < length$observed; k += 1) {
				double[] var45 = indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var45[l] = indirection1[k][l];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample41) {
			for(int i = 0; i < length$observed; i += 1) {
				double[] var30 = indirection1[i];
				for(int j = 0; j < 10; j += 1)
					var30[j] = DistributionSampling.sampleUniform(RNG$);
			}
			for(int k = 0; k < length$observed; k += 1) {
				double[] var45 = indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var45[l] = indirection1[k][l];
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample41) {
			if(system$gibbsForward) {
				for(int i = 0; i < length$observed; i += 1) {
					for(int j = 0; j < 10; j += 1)
						sample41(i, j);
				}
			} else {
				for(int i = (length$observed - 1); i >= 0; i -= 1) {
					for(int j = 9; j >= 0; j -= 1)
						sample41(i, j);
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < length$observed; i += 1) {
			for(int j = 0; j < 10; j += 1)
				logProbability$var33[i][j] = 0.0;
		}
		logProbability$indirection2 = 0.0;
		logProbability$indirection1 = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1)
					logProbability$sample41[i][j] = 0.0;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var55[m] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample65) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample65[m] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		logProbabilityValue$sample65();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample41();
		logProbabilityValue$sample65();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample41();
		logProbabilityValue$sample65();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample41) {
			for(int i = 0; i < length$observed; i += 1) {
				double[] var30 = indirection1[i];
				for(int j = 0; j < 10; j += 1)
					var30[j] = DistributionSampling.sampleUniform(RNG$);
			}
			for(int k = 0; k < length$observed; k += 1) {
				double[] var45 = indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var45[l] = indirection1[k][l];
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$indirection1) {
			for(int k = 0; k < length$observed; k += 1) {
				double[] var45 = indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var45[l] = indirection1[k][l];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK4(int[] observed) {\n    int[] generated = new int[observed.length];\n    double[][] indirection1 = new double[observed.length][10];\n    double[][] indirection2 = new double[observed.length][10];\n\n    for(int i=0; i<observed.length; i++) {\n        for(int j=0; j<10; j++) {\n            indirection1[i][j] = uniform(0.0, 1.0).sample();\n        }\n    }\n    \n    for(int k=0; k<observed.length; k++) {\n        for(int l=0; l<10; l++) {\n            indirection2[k][l] = indirection1[k][l];\n        }\n    }\n    \n    for(int m=0; m<observed.length; m++) {\n        generated[m] = categorical(indirection2[m]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}