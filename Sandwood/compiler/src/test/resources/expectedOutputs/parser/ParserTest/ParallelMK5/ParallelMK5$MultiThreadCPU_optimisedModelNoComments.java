package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK5$CoreInterface {
	private boolean fixedFlag$sample61 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample61 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[] logProbability$sample103;
	private double[][] logProbability$sample61;
	private double[] logProbability$var100;
	private double[][] logProbability$var58;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final int[] get$generated() {
		return generated;
	}

	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	@Override
	public final void set$indirection1(double[][] cv$value) {
		indirection1 = cv$value;
		setFlag$indirection1 = true;
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final double[][] get$indirection2() {
		return indirection2;
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

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				int cv$sampleValue = generated[m];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 10))?Math.log(indirection2[m][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var100[m] = cv$distributionAccumulator;
				logProbability$sample103[m] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$rvAccumulator = logProbability$sample103[m];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var100[m] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$sampleValue = indirection1[i][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var58[i][j] = cv$distributionAccumulator;
					logProbability$sample61[i][j] = cv$distributionAccumulator;
					logProbability$indirection2 = (logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$sampleValue = logProbability$sample61[i][j];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var58[i][j] = cv$sampleValue;
					logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample61(int i, int j, int threadID$cv$j, Rng RNG$) {
		double cv$originalValue = indirection1[i][j];
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$originalProbability = ((((0.0 <= generated[j]) && (generated[j] < 10))?Math.log(indirection2[j][generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		indirection1[i][j] = cv$proposedValue;
		indirection2[j][i] = indirection1[i][j];
		double cv$accumulatedProbabilities = ((((0.0 <= generated[j]) && (generated[j] < 10))?Math.log(indirection2[j][generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			indirection1[i][j] = cv$originalValue;
			indirection2[j][i] = indirection1[i][j];
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		generated = new int[length$observed];
		if(!setFlag$indirection1) {
			indirection1 = new double[10][];
			for(int var16 = 0; var16 < 10; var16 += 1)
				indirection1[var16] = new double[length$observed];
		}
		indirection2 = new double[length$observed][];
		for(int var31 = 0; var31 < length$observed; var31 += 1)
			indirection2[var31] = new double[10];
		logProbability$var58 = new double[10][];
		for(int i = 0; i < 10; i += 1)
			logProbability$var58[i] = new double[length$observed];
		logProbability$sample61 = new double[10][];
		for(int i = 0; i < 10; i += 1)
			logProbability$sample61[i] = new double[length$observed];
		logProbability$var100 = new double[length$observed];
		logProbability$sample103 = new double[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample61) {
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$m, int forEnd$m, int threadID$m, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int m = forStart$m; m < forEnd$m; m += 1)
						generated[m] = DistributionSampling.sampleCategorical(RNG$1, indirection2[m], 10);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample61) {
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample61) {
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[l][k];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample61) {
			if(system$gibbsForward) {
				for(int i = 0; i < 10; i += 1) {
					int i$1 = i;
					parallelFor(RNG$, 0, length$observed, 1,
						(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
							for(int j = forStart$j; j < forEnd$j; j += 1)
									sample61(i$1, j, threadID$j, RNG$1);
						}
					);
				}
			} else {
				for(int i = 9; i >= 0; i -= 1) {
					int i$2 = i;
					parallelFor(RNG$, 0, length$observed, 1,
						(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
							for(int j = forStart$j; j < forEnd$j; j += 1)
									sample61(i$2, j, threadID$j, RNG$1);
						}
					);
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
		for(int i = 0; i < 10; i += 1) {
			for(int j = 0; j < length$observed; j += 1)
				logProbability$var58[i][j] = 0.0;
		}
		logProbability$indirection1 = 0.0;
		logProbability$indirection2 = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1)
					logProbability$sample61[i][j] = 0.0;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var100[m] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample103[m] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample61) {
			parallelFor(RNG$, 0, 10, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var55 = indirection1[i];
							parallelFor(RNG$1, 0, length$observed, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var55[j] = DistributionSampling.sampleUniform(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[l][k];
								}
							);
						}
				}
			);
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
		if(setFlag$indirection1)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							double[] var83 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var83[l] = indirection1[l][k];
								}
							);
						}
				}
			);

	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK5(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[10][observed.length];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<10; i++) {\n"
		     + "        for(int j=0; j<observed.length; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[l][k];\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int m=0; m<observed.length; m++) {\n"
		     + "        generated[m] = categorical(indirection2[m]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}