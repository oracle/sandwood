package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK4$CoreInterface {
	private boolean fixedFlag$sample112 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedProbFlag$sample112 = false;
	private boolean fixedProbFlag$sample68 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[] logProbability$sample112;
	private double[][] logProbability$sample68;
	private double[] logProbability$var102;
	private double[][] logProbability$var60;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample112() {
		return fixedFlag$sample112;
	}

	@Override
	public final void set$fixedFlag$sample112(boolean cv$value) {
		fixedFlag$sample112 = cv$value;
		fixedProbFlag$sample112 = (cv$value && fixedProbFlag$sample112);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
		fixedProbFlag$sample112 = (cv$value && fixedProbFlag$sample112);
	}

	@Override
	public final int[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(int[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
		fixedProbFlag$sample112 = false;
	}

	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	@Override
	public final void set$indirection1(double[][] cv$value) {
		indirection1 = cv$value;
		setFlag$indirection1 = true;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample112 = false;
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

	private final void logProbabilityValue$sample112() {
		if(!fixedProbFlag$sample112) {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				int cv$sampleValue = generated[m];
				double[] var101 = indirection2[m];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var101.length))?Math.log(var101[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var102[m] = cv$distributionAccumulator;
				logProbability$sample112[m] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample112 = (fixedFlag$sample112 && fixedFlag$sample68);
		} else {
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$rvAccumulator = logProbability$sample112[m];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var102[m] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$sampleValue = indirection1[i][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var60[i][j] = cv$distributionAccumulator;
					logProbability$sample68[i][j] = cv$distributionAccumulator;
					logProbability$indirection2 = (logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$sampleValue = logProbability$sample68[i][j];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var60[i][j] = cv$sampleValue;
					logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
				}
			}
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample68(int i, int j, int threadID$cv$i, Rng RNG$) {
		double cv$originalValue = indirection1[i][j];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double[] cv$temp$2$var101 = indirection2[i];
			cv$originalProbability = ((((0.0 <= generated[i]) && (generated[i] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
		}
		indirection1[i][j] = cv$proposedValue;
		indirection2[i][j] = indirection1[i][j];
		double[] cv$temp$2$var101 = indirection2[i];
		double cv$accumulatedProbabilities = ((((0.0 <= generated[i]) && (generated[i] < cv$temp$2$var101.length))?Math.log(cv$temp$2$var101[generated[i]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY));
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
			for(int var18 = 0; var18 < length$observed; var18 += 1)
				indirection1[var18] = new double[10];
		}
		indirection2 = new double[length$observed][];
		for(int var33 = 0; var33 < length$observed; var33 += 1)
			indirection2[var33] = new double[10];
		logProbability$var60 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var60[i] = new double[10];
		logProbability$sample68 = new double[length$observed][];
		for(int i = 0; i < length$observed; i += 1)
			logProbability$sample68[i] = new double[10];
		logProbability$var102 = new double[length$observed];
		logProbability$sample112 = new double[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample68) {
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var57 = indirection1[i];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var57[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var85 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var85[l] = indirection1[k][l];
								}
							);
						}
				}
			);
		}
		if(!fixedFlag$sample112)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$m, int forEnd$m, int threadID$m, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int m = forStart$m; m < forEnd$m; m += 1)
							generated[m] = DistributionSampling.sampleCategorical(RNG$1, indirection2[m]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample68) {
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var57 = indirection1[i];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var57[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var85 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var85[l] = indirection1[k][l];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample68) {
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var57 = indirection1[i];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var57[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var85 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var85[l] = indirection1[k][l];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample68) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1) {
								for(int j = 0; j < 10; j += 1)
									sample68(i, j, threadID$i, RNG$1);
							}
					}
				);
			else
				parallelFor(RNG$, 0, length$observed, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1) {
								for(int j = 9; j >= 0; j -= 1)
									sample68(i, j, threadID$i, RNG$1);
							}
					}
				);
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
				logProbability$var60[i][j] = 0.0;
		}
		logProbability$indirection2 = 0.0;
		logProbability$indirection1 = 0.0;
		if(!fixedProbFlag$sample68) {
			for(int i = 0; i < length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1)
					logProbability$sample68[i][j] = 0.0;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var102[m] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample112) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample112[m] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample68();
		logProbabilityValue$sample112();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample68) {
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] var57 = indirection1[i];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											var57[j] = DistributionSampling.sampleUniform(RNG$2);
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
							double[] var85 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var85[l] = indirection1[k][l];
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
							double[] var85 = indirection2[k];
							parallelFor(RNG$1, 0, 10, 1,
								(int forStart$l, int forEnd$l, int threadID$l, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int l = forStart$l; l < forEnd$l; l += 1)
											var85[l] = indirection1[k][l];
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
		     + "public model ParallelMK4(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[observed.length][10];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        for(int j=0; j<10; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[k][l];\n"
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