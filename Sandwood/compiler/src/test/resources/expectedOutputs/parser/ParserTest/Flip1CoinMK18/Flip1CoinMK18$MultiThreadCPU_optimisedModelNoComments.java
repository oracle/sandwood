package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK18$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK18.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK18$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK18$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][1][0] = (1 - state.q);
		double[][] var52 = state.bias[1];
		var52[0][1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	private final void drawValueSample17() {
		state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		var21[1][1] = state.t;
		state.bias[1][0][0] = state.t;
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		double cv$originalValue = state.q;
		double cv$originalProbability;
		double cv$var = ((state.q * state.q) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.q);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.q, 1.0, 1.0);
			if((((0 == state.a) && (1 == state.b)) && (0 == state.c))) {
				double traceTempVariable$var84$5_2 = (1 - state.q);
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					state.constrainedFlag$sample11 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			if((1 == state.a)) {
				if(((0 == state.b) && (1 == state.c))) {
					double traceTempVariable$var84$6_2 = (1 - state.q);
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						state.constrainedFlag$sample11 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				if((1 == state.b)) {
					if((0 == state.c)) {
						double traceTempVariable$var84$7_2 = (1 - state.q);
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample11 = true;
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					if((1 == state.c)) {
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample11 = true;
							cv$accumulatedProbabilities = ((((0.0 <= state.q) && (state.q <= 1.0))?Math.log((state.flips[var96]?state.q:(1.0 - state.q))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample11) {
			{
				state.q = cv$proposedValue;
				state.bias[0][1][0] = (1 - cv$proposedValue);
				double[][] var52 = state.bias[1];
				var52[0][1] = (1 - cv$proposedValue);
				double[] var67 = var52[1];
				var67[0] = (1 - cv$proposedValue);
				var67[1] = cv$proposedValue;
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((((0 == state.a) && (1 == state.b)) && (0 == state.c))) {
				double traceTempVariable$var84$5_2 = (1 - cv$proposedValue);
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					state.constrainedFlag$sample11 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			if((1 == state.a)) {
				if(((0 == state.b) && (1 == state.c))) {
					double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						state.constrainedFlag$sample11 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				if((1 == state.b)) {
					if((0 == state.c)) {
						double traceTempVariable$var84$7_2 = (1 - cv$proposedValue);
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample11 = true;
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					if((1 == state.c)) {
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample11 = true;
							cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.q = cv$originalValue;
				state.bias[0][1][0] = (1 - cv$originalValue);
				double[][] var52 = state.bias[1];
				var52[0][1] = (1 - cv$originalValue);
				double[] var67 = var52[1];
				var67[0] = (1 - cv$originalValue);
				var67[1] = cv$originalValue;
			}
		}
	}

	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		double cv$originalValue = state.t;
		double cv$originalProbability;
		double cv$var = ((state.t * state.t) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.t);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.t, 1.0, 1.0);
			if((0 == state.a)) {
				if((0 == state.b)) {
					if((0 == state.c)) {
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample17 = true;
							cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					if((1 == state.c)) {
						double traceTempVariable$var84$6_2 = (1 - state.t);
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample17 = true;
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				if(((1 == state.b) && (1 == state.c))) {
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						state.constrainedFlag$sample17 = true;
						cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((((1 == state.a) && (0 == state.b)) && (0 == state.c))) {
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					state.constrainedFlag$sample17 = true;
					cv$accumulatedProbabilities = ((((0.0 <= state.t) && (state.t <= 1.0))?Math.log((state.flips[var96]?state.t:(1.0 - state.t))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample17) {
			{
				state.t = cv$proposedValue;
				double[][] var21 = state.bias[0];
				double[] var23 = var21[0];
				var23[0] = cv$proposedValue;
				var23[1] = (1 - cv$proposedValue);
				var21[1][1] = cv$proposedValue;
				state.bias[1][0][0] = cv$proposedValue;
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if((0 == state.a)) {
				if((0 == state.b)) {
					if((0 == state.c)) {
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample17 = true;
							cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					if((1 == state.c)) {
						double traceTempVariable$var84$6_2 = (1 - cv$proposedValue);
						for(int var96 = 0; var96 < state.samples; var96 += 1) {
							state.constrainedFlag$sample17 = true;
							cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				if(((1 == state.b) && (1 == state.c))) {
					for(int var96 = 0; var96 < state.samples; var96 += 1) {
						state.constrainedFlag$sample17 = true;
						cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((((1 == state.a) && (0 == state.b)) && (0 == state.c))) {
				for(int var96 = 0; var96 < state.samples; var96 += 1) {
					state.constrainedFlag$sample17 = true;
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var96]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.t = cv$originalValue;
				double[][] var21 = state.bias[0];
				double[] var23 = var21[0];
				var23[0] = cv$originalValue;
				var23[1] = (1 - cv$originalValue);
				var21[1][1] = cv$originalValue;
				state.bias[1][0][0] = cv$originalValue;
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < state.samples; var96 += 1) {
				double var84 = state.bias[state.a][state.b][state.c];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[var96]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var97 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample11 && state.fixedFlag$sample17);
		} else {
			state.logProbability$bernoulli = state.logProbability$var97;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var97);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var97);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var97);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.q, 1.0, 1.0);
			state.logProbability$q = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$q);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$q);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$q);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.t, 1.0, 1.0);
			state.logProbability$t = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$t);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$t);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$t);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17) {
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			double[][] var21 = state.bias[0];
			double[] var23 = var21[0];
			var23[0] = state.t;
			var23[1] = (1 - state.t);
			double[] var36 = var21[1];
			var36[0] = (1 - state.q);
			var36[1] = state.t;
		}
		if(!state.fixedFlag$sample11) {
			double[][] var52 = state.bias[1];
			double[] var54 = var52[0];
			var54[0] = state.t;
			var54[1] = (1 - state.q);
			double[] var67 = var52[1];
			var67[0] = (1 - state.q);
			var67[1] = state.q;
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
						state.flips[var96] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.a][state.b][state.c]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
						state.flips[var96] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.a][state.b][state.c]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17) {
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			double[][] var21 = state.bias[0];
			double[] var23 = var21[0];
			var23[0] = state.t;
			var23[1] = (1 - state.t);
			double[] var36 = var21[1];
			var36[0] = (1 - state.q);
			var36[1] = state.t;
		}
		if(!state.fixedFlag$sample11) {
			double[][] var52 = state.bias[1];
			double[] var54 = var52[0];
			var54[0] = state.t;
			var54[1] = (1 - state.q);
			double[] var67 = var52[1];
			var67[0] = (1 - state.q);
			var67[1] = state.q;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample17)
				inferSample17();
		} else {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample11)
				inferSample11();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$q = Double.NaN;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$t = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample103)
			state.logProbability$var97 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}