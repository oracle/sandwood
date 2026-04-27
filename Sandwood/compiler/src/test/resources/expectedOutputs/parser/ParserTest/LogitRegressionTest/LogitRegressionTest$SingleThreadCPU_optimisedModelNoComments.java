package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LogitRegressionTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LogitRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LogitRegressionTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[][] guard$sample35bernoulli93$global;
		boolean[][] guard$sample35put89$global;

		@Override
		public final void allocateScratch() {
			{
				int cv$max_j$var85 = 0;
				if((0 < state.x.length))
					cv$max_j$var85 = 3;
				guard$sample35put89$global = new boolean[state.x.length][cv$max_j$var85];
			}
			int cv$max_j$var85 = 0;
			if((0 < state.x.length))
				cv$max_j$var85 = 3;
			guard$sample35bernoulli93$global = new boolean[state.x.length][cv$max_j$var85];
		}
	}


	public LogitRegressionTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample35(int var33) {
		state.weights[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1)
				state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
		}
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1)
				state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
		}
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1)
				state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
		}
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1) {
				scratch.guard$sample35put89$global[i][0] = false;
				scratch.guard$sample35put89$global[i][1] = false;
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1) {
				scratch.guard$sample35put89$global[i][0] = false;
				scratch.guard$sample35put89$global[i][1] = false;
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1) {
				scratch.guard$sample35put89$global[i][0] = false;
				scratch.guard$sample35put89$global[i][1] = false;
				scratch.guard$sample35put89$global[i][2] = false;
			}
		}
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1)
				scratch.guard$sample35put89$global[i][0] = false;
		}
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1)
				scratch.guard$sample35put89$global[i][1] = false;
		}
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1)
				scratch.guard$sample35put89$global[i][2] = false;
		}
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][0]) {
					scratch.guard$sample35put89$global[i][0] = true;
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][1]) {
					scratch.guard$sample35put89$global[i][1] = true;
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][2]) {
					scratch.guard$sample35put89$global[i][2] = true;
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][0]) {
					scratch.guard$sample35put89$global[i][0] = true;
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][1]) {
					scratch.guard$sample35put89$global[i][1] = true;
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][2]) {
					scratch.guard$sample35put89$global[i][2] = true;
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][0]) {
					scratch.guard$sample35put89$global[i][0] = true;
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][1]) {
					scratch.guard$sample35put89$global[i][1] = true;
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
				if(!scratch.guard$sample35put89$global[i][2]) {
					scratch.guard$sample35put89$global[i][2] = true;
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		if((var33 == 0)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][0]) {
					scratch.guard$sample35put89$global[i][0] = true;
					state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		if((var33 == 1)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][1]) {
					scratch.guard$sample35put89$global[i][1] = true;
					state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
		if((var33 == 2)) {
			for(int i = 0; i < state.n; i += 1) {
				if(!scratch.guard$sample35put89$global[i][2]) {
					scratch.guard$sample35put89$global[i][2] = true;
					state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				}
			}
		}
	}

	private final void drawValueSample42() {
		state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
	}

	private final void inferSample35(int var33) {
		state.constrainedFlag$sample35[var33] = false;
		double cv$originalValue = state.weights[var33];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var69$15_4 = Math.exp((cv$originalValue * state.x[i][0]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][0] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][1] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][2] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var71$16_4 = Math.exp((cv$originalValue * state.x[i][1]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var74$17_4 = Math.exp((cv$originalValue * state.x[i][2]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((Math.exp((cv$originalValue * state.x[i][0])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((Math.exp((cv$originalValue * state.x[i][1])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((Math.exp((cv$originalValue * state.x[i][2])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample35[var33]) {
			state.weights[var33] = cv$proposedValue;
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					scratch.guard$sample35put89$global[i][0] = false;
					scratch.guard$sample35put89$global[i][1] = false;
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					scratch.guard$sample35put89$global[i][0] = false;
					scratch.guard$sample35put89$global[i][1] = false;
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					scratch.guard$sample35put89$global[i][0] = false;
					scratch.guard$sample35put89$global[i][1] = false;
					scratch.guard$sample35put89$global[i][2] = false;
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35put89$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35put89$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35put89$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][0]) {
						scratch.guard$sample35put89$global[i][0] = true;
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][1]) {
						scratch.guard$sample35put89$global[i][1] = true;
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][2]) {
						scratch.guard$sample35put89$global[i][2] = true;
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][0]) {
						scratch.guard$sample35put89$global[i][0] = true;
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][1]) {
						scratch.guard$sample35put89$global[i][1] = true;
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][2]) {
						scratch.guard$sample35put89$global[i][2] = true;
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][0]) {
						scratch.guard$sample35put89$global[i][0] = true;
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][1]) {
						scratch.guard$sample35put89$global[i][1] = true;
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
					if(!scratch.guard$sample35put89$global[i][2]) {
						scratch.guard$sample35put89$global[i][2] = true;
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][0]) {
						scratch.guard$sample35put89$global[i][0] = true;
						state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][1]) {
						scratch.guard$sample35put89$global[i][1] = true;
						state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35put89$global[i][2]) {
						scratch.guard$sample35put89$global[i][2] = true;
						state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
					}
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][0] = false;
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][1] = false;
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1)
					scratch.guard$sample35bernoulli93$global[i][2] = false;
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var69$15_4 = Math.exp((cv$proposedValue * state.x[i][0]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][0] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][1] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((state.indicator[i][2] / ((traceTempVariable$var69$15_4 + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var71$16_4 = Math.exp((cv$proposedValue * state.x[i][1]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + traceTempVariable$var71$16_4) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					double traceTempVariable$var74$17_4 = Math.exp((cv$proposedValue * state.x[i][2]));
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + traceTempVariable$var74$17_4)) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 0)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][0]) {
						scratch.guard$sample35bernoulli93$global[i][0] = true;
						state.constrainedFlag$sample35[0] = true;
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][0])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 1)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][1]) {
						scratch.guard$sample35bernoulli93$global[i][1] = true;
						state.constrainedFlag$sample35[1] = true;
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][1])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var33 == 2)) {
				for(int i = 0; i < state.n; i += 1) {
					if(!scratch.guard$sample35bernoulli93$global[i][2]) {
						scratch.guard$sample35bernoulli93$global[i][2] = true;
						state.constrainedFlag$sample35[2] = true;
						double var91 = ((Math.exp((cv$proposedValue * state.x[i][2])) / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2])) + state.bias);
						cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.weights[var33] = cv$originalValue;
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1)
						state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
				}
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1)
						state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
				}
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1)
						state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
				}
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1) {
						scratch.guard$sample35put89$global[i][0] = false;
						scratch.guard$sample35put89$global[i][1] = false;
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1) {
						scratch.guard$sample35put89$global[i][0] = false;
						scratch.guard$sample35put89$global[i][1] = false;
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1) {
						scratch.guard$sample35put89$global[i][0] = false;
						scratch.guard$sample35put89$global[i][1] = false;
						scratch.guard$sample35put89$global[i][2] = false;
					}
				}
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1)
						scratch.guard$sample35put89$global[i][0] = false;
				}
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1)
						scratch.guard$sample35put89$global[i][1] = false;
				}
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1)
						scratch.guard$sample35put89$global[i][2] = false;
				}
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][0]) {
							scratch.guard$sample35put89$global[i][0] = true;
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][1]) {
							scratch.guard$sample35put89$global[i][1] = true;
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][2]) {
							scratch.guard$sample35put89$global[i][2] = true;
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][0]) {
							scratch.guard$sample35put89$global[i][0] = true;
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][1]) {
							scratch.guard$sample35put89$global[i][1] = true;
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][2]) {
							scratch.guard$sample35put89$global[i][2] = true;
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][0]) {
							scratch.guard$sample35put89$global[i][0] = true;
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][1]) {
							scratch.guard$sample35put89$global[i][1] = true;
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
						if(!scratch.guard$sample35put89$global[i][2]) {
							scratch.guard$sample35put89$global[i][2] = true;
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				if((var33 == 0)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][0]) {
							scratch.guard$sample35put89$global[i][0] = true;
							state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				if((var33 == 1)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][1]) {
							scratch.guard$sample35put89$global[i][1] = true;
							state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
				if((var33 == 2)) {
					for(int i = 0; i < state.n; i += 1) {
						if(!scratch.guard$sample35put89$global[i][2]) {
							scratch.guard$sample35put89$global[i][2] = true;
							state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
						}
					}
				}
			}
		}
	}

	private final void inferSample42() {
		state.constrainedFlag$sample42 = false;
		double cv$originalValue = state.bias;
		double cv$originalProbability;
		double cv$var = ((state.bias * state.bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.bias);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((state.bias / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.n; i += 1) {
				{
					state.constrainedFlag$sample42 = true;
					double var91 = (state.p[i][0] + state.bias);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					state.constrainedFlag$sample42 = true;
					double var91 = (state.p[i][1] + state.bias);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				state.constrainedFlag$sample42 = true;
				double var91 = (state.p[i][2] + state.bias);
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample42) {
			state.bias = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.n; i += 1) {
				{
					state.constrainedFlag$sample42 = true;
					double var91 = (state.p[i][0] + cv$proposedValue);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				{
					state.constrainedFlag$sample42 = true;
					double var91 = (state.p[i][1] + cv$proposedValue);
					cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				state.constrainedFlag$sample42 = true;
				double var91 = (state.p[i][2] + cv$proposedValue);
				cv$accumulatedProbabilities = ((((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.bias = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$sampleAccumulator;
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[0] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = cv$weightedProbability;
				state.logProbability$sample35[0] = cv$weightedProbability;
			}
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[1] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				state.logProbability$sample35[1] = cv$weightedProbability;
			}
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.weights[2] / 3.1622776601683795)) - 1.151292546497023);
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			state.logProbability$sample35[2] = cv$weightedProbability;
			state.logProbability$weights = (state.logProbability$weights + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			double cv$rvAccumulator = ((state.logProbability$sample35[0] + state.logProbability$sample35[1]) + state.logProbability$sample35[2]);
			state.logProbability$weights = (state.logProbability$weights + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.bias / 3.1622776601683795)) - 1.151292546497023);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample94() {
		if(!state.fixedProbFlag$sample94) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1) {
				{
					double var91 = (state.p[i][0] + state.bias);
					double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][0]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					state.logProbability$sample94[i][0] = cv$weightedProbability;
				}
				{
					double var91 = (state.p[i][1] + state.bias);
					double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][1]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$weightedProbability);
					state.logProbability$sample94[i][1] = cv$weightedProbability;
				}
				double var91 = (state.p[i][2] + state.bias);
				double cv$weightedProbability = (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][2]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$weightedProbability);
				state.logProbability$sample94[i][2] = cv$weightedProbability;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample94 = (state.fixedFlag$sample35 && state.fixedFlag$sample42);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1) {
				cv$accumulator = (cv$accumulator + state.logProbability$sample94[i][0]);
				cv$accumulator = (cv$accumulator + state.logProbability$sample94[i][1]);
				cv$accumulator = (cv$accumulator + state.logProbability$sample94[i][2]);
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample35) {
			state.weights[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[2] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i = 0; i < state.n; i += 1) {
			if(!state.fixedFlag$sample35) {
				state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
				state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
				state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			}
			boolean[] var89 = state.y[i];
			if(!state.fixedFlag$sample35)
				state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[0] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][0] + state.bias));
			if(!state.fixedFlag$sample35)
				state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[1] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][1] + state.bias));
			if(!state.fixedFlag$sample35)
				state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[2] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][2] + state.bias));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample35) {
			state.weights[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[2] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i = 0; i < state.n; i += 1) {
			state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample35) {
			state.weights[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[2] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i = 0; i < state.n; i += 1) {
			state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			boolean[] var89 = state.y[i];
			state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[0] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][0] + state.bias));
			state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[1] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][1] + state.bias));
			state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			var89[2] = DistributionSampling.sampleBernoulli(state.RNG$, (state.p[i][2] + state.bias));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample35) {
			state.weights[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[2] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35) {
			for(int i = 0; i < state.n; i += 1) {
				state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
				state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
				state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
				state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
				state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample35) {
			state.weights[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			state.weights[2] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		if(!state.fixedFlag$sample42)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i = 0; i < state.n; i += 1) {
			state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample35) {
				inferSample35(0);
				inferSample35(1);
				inferSample35(2);
			}
			if(!state.fixedFlag$sample42)
				inferSample42();
		} else {
			if(!state.fixedFlag$sample42)
				inferSample42();
			if(!state.fixedFlag$sample35) {
				inferSample35(2);
				inferSample35(1);
				inferSample35(0);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample35[0])
			drawValueSample35(0);
		if(!state.constrainedFlag$sample35[1])
			drawValueSample35(1);
		if(!state.constrainedFlag$sample35[2])
			drawValueSample35(2);
		if(!state.constrainedFlag$sample42)
			drawValueSample42();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample35) {
			state.logProbability$sample35[0] = Double.NaN;
			state.logProbability$sample35[1] = Double.NaN;
			state.logProbability$sample35[2] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample42)
			state.logProbability$bias = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample94) {
			for(int i = 0; i < state.n; i += 1) {
				state.logProbability$sample94[i][0] = Double.NaN;
				state.logProbability$sample94[i][1] = Double.NaN;
				state.logProbability$sample94[i][2] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.n = state.x.length;
		for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < state.constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
			state.constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(state.fixedFlag$sample42)
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
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.yMeasured[cv$index1];
			boolean[] cv$target2 = state.y[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		for(int i = 0; i < state.n; i += 1) {
			state.indicator[i][0] = Math.exp((state.weights[0] * state.x[i][0]));
			state.indicator[i][1] = Math.exp((state.weights[1] * state.x[i][1]));
			state.indicator[i][2] = Math.exp((state.weights[2] * state.x[i][2]));
			state.p[i][0] = (state.indicator[i][0] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][1] = (state.indicator[i][1] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
			state.p[i][2] = (state.indicator[i][2] / ((state.indicator[i][0] + state.indicator[i][1]) + state.indicator[i][2]));
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