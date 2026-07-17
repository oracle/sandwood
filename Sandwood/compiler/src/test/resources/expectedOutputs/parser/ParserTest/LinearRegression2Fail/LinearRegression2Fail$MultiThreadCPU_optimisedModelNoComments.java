import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegression2Fail$MultiThreadCPU extends CoreModelMultiThreadCPU<LinearRegression2Fail.State, LinearRegression2Fail$MultiThreadCPU.Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public LinearRegression2Fail$MultiThreadCPU(LinearRegression2Fail.State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
	}

	private final void drawValueSample15() {
		state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample7() {
		state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample11 = true;
			double cv$denominator = state.x[i];
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (state.y[i] - state.b0)));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.variance;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample11)
			state.b1 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample15() {
		state.constrainedFlag$sample15 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample15 = true;
			double cv$var30$diff = ((state.b0 + (state.b1 * state.x[i])) - state.y[i]);
			cv$sum = (cv$sum + (cv$var30$diff * cv$var30$diff));
			cv$count = (cv$count + 1);
		}
		if(state.constrainedFlag$sample15)
			state.variance = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample7() {
		state.constrainedFlag$sample7 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample7 = true;
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + state.y[i]) - (state.b1 * state.x[i]));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.variance;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample7)
			state.b0 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((state.b1 - 1.0) / 2.23606797749979)) - 0.8047189562170501);
			state.logProbability$b1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b1);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b1);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!state.fixedProbFlag$sample15) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(state.variance, 1.0, 1.0);
			state.logProbability$variance = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$variance);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$variance);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1) {
				double cv$distributionAccumulator = ((0.0 < state.variance)?(DistributionSampling.logProbabilityGaussian(((state.y[i] - (state.b0 + (state.b1 * state.x[i]))) / Math.sqrt(state.variance))) - (Math.log(state.variance) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample31[i] = cv$distributionAccumulator;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = ((state.fixedFlag$sample7 && state.fixedFlag$sample11) && state.fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample31[i]);
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.b0 / 1.4142135623730951)) - 0.34657359027997264);
			state.logProbability$b0 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample7;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b0);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b0);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample15)
			state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = (((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + state.b0) + (state.b1 * state.x[i]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample15)
			state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample15)
			state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = (((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + state.b0) + (state.b1 * state.x[i]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample15)
			state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample15)
			state.variance = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample7)
				inferSample7();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample15)
				inferSample15();
		} else {
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample7)
				inferSample7();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample7)
			drawValueSample7();
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$b0 = Double.NaN;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$b1 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$variance = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample31) {
			for(int i = 0; i < state.noSamples; i += 1)
				state.logProbability$sample31[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.x.length;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.y[cv$index1] = state.yMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

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
		     + "import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;\n"
		     + "\n"
		     + "public model LinearRegression2Fail(double[] x, double[] yMeasured) {\n"
		     + "    \n"
		     + "        int noSamples = x.length;\n"
		     + "        double b0 = gaussian(0.0, 2.0).sample();\n"
		     + "        double b1 = gaussian(1.0, 5.0).sample();\n"
		     + "        double variance = inverseGamma(1.0, 1.0).sample();\n"
		     + "        double[] y = new double[noSamples];\n"
		     + "        for(int i:[0..noSamples)) {\n"
		     + "           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n"
		     + "        }\n"
		     + "        y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}