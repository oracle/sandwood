package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.TerminalVariables$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.TerminalVariables.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var50$stateProbabilityGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var60$stateProbabilityGlobal;
		double[] cv$var65$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var45$stateProbabilityGlobal = new double[2];
			cv$var50$stateProbabilityGlobal = new double[2];
			cv$var53$stateProbabilityGlobal = new double[2];
			cv$var55$stateProbabilityGlobal = new double[2];
			cv$var60$stateProbabilityGlobal = new double[2];
			cv$var65$stateProbabilityGlobal = new double[2];
			cv$var70$stateProbabilityGlobal = new double[2];
		}
	}


	public TerminalVariables$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample47() {
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample52() {
		state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample55() {
		int lengthCV$conditionals$53_13 = -1;
		if((0 == state.c3))
			lengthCV$conditionals$53_13 = 2;
		if((1 == state.c3))
			lengthCV$conditionals$53_13 = 2;
		state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_13);
	}

	private final void drawValueSample57() {
		state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample60() {
		int lengthCV$conditionals$58_9 = -1;
		if((0 == state.c5))
			lengthCV$conditionals$58_9 = 2;
		if((1 == state.c5))
			lengthCV$conditionals$58_9 = 2;
		state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_9);
	}

	private final void drawValueSample62() {
		state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample636() {
		int lengthCV$var601$634_15 = -1;
		if((0 == state.c5)) {
			if((0 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
			}
			if((1 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
			}
		}
		if((1 == state.c5)) {
			if((0 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
			}
			if((1 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_15 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_15 = 5;
				}
			}
		}
		state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_15);
	}

	private final void drawValueSample65() {
		int lengthCV$conditionals$63_9 = -1;
		if((0 == state.c7))
			lengthCV$conditionals$63_9 = 2;
		if((1 == state.c7))
			lengthCV$conditionals$63_9 = 2;
		state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_9);
	}

	private final void drawValueSample67() {
		state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample70() {
		int lengthCV$conditionals$68_9 = -1;
		if((0 == state.c9))
			lengthCV$conditionals$68_9 = 2;
		if((1 == state.c9))
			lengthCV$conditionals$68_9 = 2;
		state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_9);
	}

	private final void drawValueSample72() {
		state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample75() {
		int lengthCV$conditionals$73_9 = -1;
		if((0 == state.c11))
			lengthCV$conditionals$73_9 = 2;
		if((1 == state.c11))
			lengthCV$conditionals$73_9 = 2;
		state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_9);
	}

	private final void inferSample47() {
		state.constrainedFlag$sample47 = false;
		{
			state.c1 = 0;
			state.constrainedFlag$sample47 = true;
			double[] var46 = state.conditionals[0];
			double cv$accumulatedProbabilities = ((((((0.0 <= state.c2) && (state.c2 < 2)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY));
			if(state.fixedFlag$sample636) {
				state.constrainedFlag$sample47 = true;
				double[] var602 = state.a[state.c5][state.c9][0][state.c4];
				int lengthCV$var601$634_11 = -1;
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c4))
							lengthCV$var601$634_11 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_11 = 5;
					}
					if((1 == state.c9)) {
						if((0 == state.c4))
							lengthCV$var601$634_11 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_11 = 5;
					}
				}
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c4))
							lengthCV$var601$634_11 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_11 = 5;
					}
					if((1 == state.c9)) {
						if((0 == state.c4))
							lengthCV$var601$634_11 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_11 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c1 = 1;
		state.constrainedFlag$sample47 = true;
		double[] var46 = state.conditionals[1];
		double cv$accumulatedProbabilities = ((((((0.0 <= state.c2) && (state.c2 < 2)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY));
		if(state.fixedFlag$sample636) {
			state.constrainedFlag$sample47 = true;
			double[] var602 = state.a[state.c5][state.c9][1][state.c4];
			int lengthCV$var601$634_11 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c4))
						lengthCV$var601$634_11 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_11 = 5;
				}
				if((1 == state.c9)) {
					if((0 == state.c4))
						lengthCV$var601$634_11 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_11 = 5;
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c4))
						lengthCV$var601$634_11 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_11 = 5;
				}
				if((1 == state.c9)) {
					if((0 == state.c4))
						lengthCV$var601$634_11 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_11 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = scratch.cv$var45$stateProbabilityGlobal[0];
		double cv$lseElementValue = scratch.cv$var45$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			scratch.cv$var45$stateProbabilityGlobal[0] = 0.5;
			scratch.cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			scratch.cv$var45$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			scratch.cv$var45$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < scratch.cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			scratch.cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var45$stateProbabilityGlobal, 2);
	}

	private final void inferSample52() {
		state.constrainedFlag$sample52 = false;
		{
			state.c3 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample55 || state.constrainedFlag$sample55)) {
				state.constrainedFlag$sample52 = true;
				double[] var51 = state.conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.c4) && (state.c4 < 2)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c3 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		if((state.fixedFlag$sample55 || state.constrainedFlag$sample55)) {
			state.constrainedFlag$sample52 = true;
			double[] var51 = state.conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.c4) && (state.c4 < 2)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample52) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var50$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var50$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var50$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var50$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var50$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var50$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var50$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var50$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var50$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var50$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var50$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var50$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample55() {
		state.constrainedFlag$sample55 = false;
		int lengthCV$conditionals$53_11 = -1;
		if((0 == state.c3))
			lengthCV$conditionals$53_11 = 2;
		if((1 == state.c3))
			lengthCV$conditionals$53_11 = 2;
		int cv$numStates = Math.max(0, lengthCV$conditionals$53_11);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.c4 = cv$valuePos;
			double[] var51 = state.conditionals[state.c3];
			int lengthCV$conditionals$53_12 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_12 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_12 = 2;
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$conditionals$53_12) && (0 < lengthCV$conditionals$53_12)) && (0.0 <= var51[cv$valuePos])) && (var51[cv$valuePos] <= 1.0))?Math.log(var51[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample636) {
				state.constrainedFlag$sample55 = true;
				double[] var602 = state.a[state.c5][state.c9][state.c1][cv$valuePos];
				int lengthCV$var601$634_12 = -1;
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
						if((1 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
					}
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
						if((1 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
					}
				}
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
						if((1 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
					}
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
						if((1 == state.c1)) {
							if((0 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
							if((1 == cv$valuePos))
								lengthCV$var601$634_12 = 5;
						}
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var53$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample55) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var53$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var53$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var53$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var53$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var53$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample57() {
		state.constrainedFlag$sample57 = false;
		{
			state.c5 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample60) {
				state.constrainedFlag$sample57 = true;
				double[] var56 = state.conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.c6) && (state.c6 < 2)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(state.fixedFlag$sample636) {
				state.constrainedFlag$sample57 = true;
				double[] var602 = state.a[0][state.c9][state.c1][state.c4];
				int lengthCV$var601$634_13 = -1;
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_13 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_13 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_13 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_13 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_13 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_13 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_13 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_13 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c5 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample60) {
			state.constrainedFlag$sample57 = true;
			double[] var56 = state.conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.c6) && (state.c6 < 2)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if(state.fixedFlag$sample636) {
			state.constrainedFlag$sample57 = true;
			double[] var602 = state.a[1][state.c9][state.c1][state.c4];
			int lengthCV$var601$634_13 = -1;
			if((0 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_13 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_13 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_13 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_13 = 5;
				}
			}
			if((1 == state.c9)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_13 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_13 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_13 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_13 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample57) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var55$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var55$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var55$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var55$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var55$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var55$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var55$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample62() {
		state.constrainedFlag$sample62 = false;
		{
			state.c7 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample65) {
				state.constrainedFlag$sample62 = true;
				double[] var61 = state.conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.c8) && (state.c8 < 2)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var60$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c7 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample65) {
			state.constrainedFlag$sample62 = true;
			double[] var61 = state.conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.c8) && (state.c8 < 2)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var60$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample62) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var60$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var60$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var60$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var60$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var60$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var60$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var60$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var60$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var60$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var60$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var60$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var60$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var60$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample67() {
		state.constrainedFlag$sample67 = false;
		{
			state.c9 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample70) {
				state.constrainedFlag$sample67 = true;
				double[] var66 = state.conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.c10) && (state.c10 < 2)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(state.fixedFlag$sample636) {
				state.constrainedFlag$sample67 = true;
				double[] var602 = state.a[state.c5][0][state.c1][state.c4];
				int lengthCV$var601$634_14 = -1;
				if((0 == state.c5)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_14 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_14 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_14 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_14 = 5;
					}
				}
				if((1 == state.c5)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_14 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_14 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_14 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_14 = 5;
					}
				}
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var65$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c9 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample70) {
			state.constrainedFlag$sample67 = true;
			double[] var66 = state.conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.c10) && (state.c10 < 2)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if(state.fixedFlag$sample636) {
			state.constrainedFlag$sample67 = true;
			double[] var602 = state.a[state.c5][1][state.c1][state.c4];
			int lengthCV$var601$634_14 = -1;
			if((0 == state.c5)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_14 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_14 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_14 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_14 = 5;
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_14 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_14 = 5;
				}
				if((1 == state.c1)) {
					if((0 == state.c4))
						lengthCV$var601$634_14 = 5;
					if((1 == state.c4))
						lengthCV$var601$634_14 = 5;
				}
			}
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var65$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample67) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var65$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var65$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var65$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var65$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var65$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var65$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var65$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var65$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var65$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var65$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var65$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var65$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var65$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample72() {
		state.constrainedFlag$sample72 = false;
		{
			state.c11 = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample75) {
				state.constrainedFlag$sample72 = true;
				double[] var71 = state.conditionals[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.c12) && (state.c12 < 2)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var70$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.c11 = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		if(state.fixedFlag$sample75) {
			state.constrainedFlag$sample72 = true;
			double[] var71 = state.conditionals[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.c12) && (state.c12 < 2)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var70$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample72) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var70$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var70$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var70$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var70$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var70$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var70$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var70$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var70$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var70$stateProbabilityGlobal, 2);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$distributionAccumulator = (((((0.0 <= state.c1) && (state.c1 < 2)) && (0.0 <= state.priors[state.c1])) && (state.priors[state.c1] <= 1.0))?Math.log(state.priors[state.c1]):Double.NEGATIVE_INFINITY);
			state.logProbability$c1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c1);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c1);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double[] var46 = state.conditionals[state.c1];
			int lengthCV$conditionals$48_5 = -1;
			if((0 == state.c1))
				lengthCV$conditionals$48_5 = 2;
			if((1 == state.c1))
				lengthCV$conditionals$48_5 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_5)) && (0 < lengthCV$conditionals$48_5)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY);
			state.logProbability$c2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample50 = state.fixedFlag$sample47;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c2);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c2);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$distributionAccumulator = (((((0.0 <= state.c3) && (state.c3 < 2)) && (0.0 <= state.priors[state.c3])) && (state.priors[state.c3] <= 1.0))?Math.log(state.priors[state.c3]):Double.NEGATIVE_INFINITY);
			state.logProbability$c3 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c3);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c3);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!state.fixedProbFlag$sample55) {
			double[] var51 = state.conditionals[state.c3];
			int lengthCV$conditionals$53_14 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_14 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_14 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_14)) && (0 < lengthCV$conditionals$53_14)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY);
			state.logProbability$c4 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample52);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c4);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c4);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$distributionAccumulator = (((((0.0 <= state.c5) && (state.c5 < 2)) && (0.0 <= state.priors[state.c5])) && (state.priors[state.c5] <= 1.0))?Math.log(state.priors[state.c5]):Double.NEGATIVE_INFINITY);
			state.logProbability$c5 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c5);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c5);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!state.fixedProbFlag$sample60) {
			double[] var56 = state.conditionals[state.c5];
			int lengthCV$conditionals$58_10 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_10 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_10 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_10)) && (0 < lengthCV$conditionals$58_10)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY);
			state.logProbability$c6 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample60)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample60 = (state.fixedFlag$sample60 && state.fixedFlag$sample57);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c6);
			if(state.fixedFlag$sample60)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c6);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!state.fixedProbFlag$sample62) {
			double cv$distributionAccumulator = (((((0.0 <= state.c7) && (state.c7 < 2)) && (0.0 <= state.priors[state.c7])) && (state.priors[state.c7] <= 1.0))?Math.log(state.priors[state.c7]):Double.NEGATIVE_INFINITY);
			state.logProbability$c7 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample62 = state.fixedFlag$sample62;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c7);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c7);
		}
	}

	private final void logProbabilityValue$sample636() {
		if(!state.fixedProbFlag$sample636) {
			double[] var602 = state.a[state.c5][state.c9][state.c1][state.c4];
			int lengthCV$var601$634_16 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_16 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_16 = 5;
					}
				}
			}
			double cv$distributionAccumulator = ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_16)) && (0 < lengthCV$var601$634_16)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY);
			state.logProbability$terminalVariable = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample636 = ((((state.fixedFlag$sample636 && state.fixedFlag$sample47) && state.fixedFlag$sample55) && state.fixedFlag$sample57) && state.fixedFlag$sample67);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$terminalVariable);
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$terminalVariable);
		}
	}

	private final void logProbabilityValue$sample65() {
		if(!state.fixedProbFlag$sample65) {
			double[] var61 = state.conditionals[state.c7];
			int lengthCV$conditionals$63_10 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_10 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_10 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_10)) && (0 < lengthCV$conditionals$63_10)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY);
			state.logProbability$c8 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample65 = (state.fixedFlag$sample65 && state.fixedFlag$sample62);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c8);
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c8);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!state.fixedProbFlag$sample67) {
			double cv$distributionAccumulator = (((((0.0 <= state.c9) && (state.c9 < 2)) && (0.0 <= state.priors[state.c9])) && (state.priors[state.c9] <= 1.0))?Math.log(state.priors[state.c9]):Double.NEGATIVE_INFINITY);
			state.logProbability$c9 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample67 = state.fixedFlag$sample67;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c9);
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c9);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!state.fixedProbFlag$sample70) {
			double[] var66 = state.conditionals[state.c9];
			int lengthCV$conditionals$68_10 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_10 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_10 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_10)) && (0 < lengthCV$conditionals$68_10)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY);
			state.logProbability$c10 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample70 = (state.fixedFlag$sample70 && state.fixedFlag$sample67);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c10);
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c10);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!state.fixedProbFlag$sample72) {
			double cv$distributionAccumulator = (((((0.0 <= state.c11) && (state.c11 < 2)) && (0.0 <= state.priors[state.c11])) && (state.priors[state.c11] <= 1.0))?Math.log(state.priors[state.c11]):Double.NEGATIVE_INFINITY);
			state.logProbability$c11 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample72 = state.fixedFlag$sample72;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c11);
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c11);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double[] var71 = state.conditionals[state.c11];
			int lengthCV$conditionals$73_10 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_10 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_10 = 2;
			double cv$distributionAccumulator = ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_10)) && (0 < lengthCV$conditionals$73_10)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY);
			state.logProbability$c12 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample75 = (state.fixedFlag$sample75 && state.fixedFlag$sample72);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c12);
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c12);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$48_6 = -1;
		if((0 == state.c1))
			lengthCV$conditionals$48_6 = 2;
		if((1 == state.c1))
			lengthCV$conditionals$48_6 = 2;
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_6);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample55) {
			int lengthCV$conditionals$53_15 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_15 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_15 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_15);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample60) {
			int lengthCV$conditionals$58_11 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_11 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_11 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_11);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample65) {
			int lengthCV$conditionals$63_11 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_11 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_11 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_11);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample70) {
			int lengthCV$conditionals$68_11 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_11 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_11 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_11);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample75) {
			int lengthCV$conditionals$73_11 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_11 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_11 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_11);
		}
		if(!state.fixedFlag$sample636) {
			int lengthCV$var601$634_17 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_17 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_17 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_17);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample55) {
			int lengthCV$conditionals$53_19 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_19 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_19 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_19);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample60) {
			int lengthCV$conditionals$58_15 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_15 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_15 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_15);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample65) {
			int lengthCV$conditionals$63_15 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_15 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_15 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_15);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample70) {
			int lengthCV$conditionals$68_15 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_15 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_15 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_15);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample75) {
			int lengthCV$conditionals$73_15 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_15 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_15 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_15);
		}
		if(!state.fixedFlag$sample636) {
			int lengthCV$var601$634_21 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_21 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_21 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_21);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$48_7 = -1;
		if((0 == state.c1))
			lengthCV$conditionals$48_7 = 2;
		if((1 == state.c1))
			lengthCV$conditionals$48_7 = 2;
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_7);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample55) {
			int lengthCV$conditionals$53_16 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_16 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_16 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_16);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample60) {
			int lengthCV$conditionals$58_12 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_12 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_12 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_12);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample65) {
			int lengthCV$conditionals$63_12 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_12 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_12 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_12);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample70) {
			int lengthCV$conditionals$68_12 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_12 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_12 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_12);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample75) {
			int lengthCV$conditionals$73_12 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_12 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_12 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_12);
		}
		if(!state.fixedFlag$sample636) {
			int lengthCV$var601$634_18 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_18 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_18 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_18);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample55) {
			int lengthCV$conditionals$53_17 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_17 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_17 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_17);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample60) {
			int lengthCV$conditionals$58_13 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_13 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_13 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_13);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample65) {
			int lengthCV$conditionals$63_13 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_13 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_13 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_13);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample70) {
			int lengthCV$conditionals$68_13 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_13 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_13 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_13);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample75) {
			int lengthCV$conditionals$73_13 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_13 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_13 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_13);
		}
		if(!state.fixedFlag$sample636) {
			int lengthCV$var601$634_19 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_19 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_19 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_19);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample55) {
			int lengthCV$conditionals$53_18 = -1;
			if((0 == state.c3))
				lengthCV$conditionals$53_18 = 2;
			if((1 == state.c3))
				lengthCV$conditionals$53_18 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_18);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample60) {
			int lengthCV$conditionals$58_14 = -1;
			if((0 == state.c5))
				lengthCV$conditionals$58_14 = 2;
			if((1 == state.c5))
				lengthCV$conditionals$58_14 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_14);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample65) {
			int lengthCV$conditionals$63_14 = -1;
			if((0 == state.c7))
				lengthCV$conditionals$63_14 = 2;
			if((1 == state.c7))
				lengthCV$conditionals$63_14 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_14);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample70) {
			int lengthCV$conditionals$68_14 = -1;
			if((0 == state.c9))
				lengthCV$conditionals$68_14 = 2;
			if((1 == state.c9))
				lengthCV$conditionals$68_14 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_14);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample75) {
			int lengthCV$conditionals$73_14 = -1;
			if((0 == state.c11))
				lengthCV$conditionals$73_14 = 2;
			if((1 == state.c11))
				lengthCV$conditionals$73_14 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_14);
		}
		if(!state.fixedFlag$sample636) {
			int lengthCV$var601$634_20 = -1;
			if((0 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
				}
			}
			if((1 == state.c5)) {
				if((0 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
				}
				if((1 == state.c9)) {
					if((0 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
					if((1 == state.c1)) {
						if((0 == state.c4))
							lengthCV$var601$634_20 = 5;
						if((1 == state.c4))
							lengthCV$var601$634_20 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_20);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample72)
				inferSample72();
		} else {
			if(!state.fixedFlag$sample72)
				inferSample72();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample52)
			drawValueSample52();
		if(!state.constrainedFlag$sample55)
			drawValueSample55();
		if(!state.constrainedFlag$sample57)
			drawValueSample57();
		if(!state.fixedFlag$sample60)
			drawValueSample60();
		if(!state.constrainedFlag$sample62)
			drawValueSample62();
		if(!state.fixedFlag$sample65)
			drawValueSample65();
		if(!state.constrainedFlag$sample67)
			drawValueSample67();
		if(!state.fixedFlag$sample70)
			drawValueSample70();
		if(!state.constrainedFlag$sample72)
			drawValueSample72();
		if(!state.fixedFlag$sample75)
			drawValueSample75();
		if(!state.fixedFlag$sample636)
			drawValueSample636();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$c1 = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$c2 = Double.NaN;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$c3 = Double.NaN;
		if(!state.fixedProbFlag$sample55)
			state.logProbability$c4 = Double.NaN;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$c5 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$c6 = Double.NaN;
		if(!state.fixedProbFlag$sample62)
			state.logProbability$c7 = Double.NaN;
		if(!state.fixedProbFlag$sample65)
			state.logProbability$c8 = Double.NaN;
		if(!state.fixedProbFlag$sample67)
			state.logProbability$c9 = Double.NaN;
		if(!state.fixedProbFlag$sample70)
			state.logProbability$c10 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$c11 = Double.NaN;
		if(!state.fixedProbFlag$sample75)
			state.logProbability$c12 = Double.NaN;
		if(!state.fixedProbFlag$sample636)
			state.logProbability$terminalVariable = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.priors[0] = 0.01;
		state.priors[1] = 0.99;
		double[] var15 = state.conditionals[0];
		var15[0] = 1.0;
		var15[1] = 0.0;
		double[] var30 = state.conditionals[1];
		var30[0] = 0.0;
		var30[1] = 1.0;
		double[][][][] var77 = state.a[0];
		double[][][] var79 = var77[0];
		double[][] var81 = var79[0];
		double[] var83 = var81[0];
		var83[0] = 0.3333333333333334;
		var83[1] = 0.3333333333333334;
		var83[2] = 0.0;
		var83[3] = 0.3333333333333334;
		var83[4] = 0.0;
		double[] var110 = var81[1];
		var110[0] = 0.3333333333333334;
		var110[1] = 0.3333333333333334;
		var110[2] = 0.0;
		var110[3] = 0.3333333333333334;
		var110[4] = 0.0;
		double[][] var140 = var79[1];
		double[] var142 = var140[0];
		var142[0] = 0.3333333333333334;
		var142[1] = 0.3333333333333334;
		var142[2] = 0.0;
		var142[3] = 0.3333333333333334;
		var142[4] = 0.0;
		double[] var169 = var140[1];
		var169[0] = 0.5;
		var169[1] = 0.5;
		var169[2] = 0.0;
		var169[3] = 0.0;
		var169[4] = 0.0;
		double[][][] var203 = var77[1];
		double[][] var205 = var203[0];
		double[] var207 = var205[0];
		var207[0] = 0.0;
		var207[1] = 0.5;
		var207[2] = 0.0;
		var207[3] = 0.5;
		var207[4] = 0.0;
		double[] var235 = var205[1];
		var235[0] = 0.0;
		var235[1] = 0.5;
		var235[2] = 0.0;
		var235[3] = 0.5;
		var235[4] = 0.0;
		double[][] var266 = var203[1];
		double[] var268 = var266[0];
		var268[0] = 0.0;
		var268[1] = 0.5;
		var268[2] = 0.0;
		var268[3] = 0.5;
		var268[4] = 0.0;
		double[] var296 = var266[1];
		var296[0] = 0.0;
		var296[1] = 1.0;
		var296[2] = 0.0;
		var296[3] = 0.0;
		var296[4] = 0.0;
		double[][][][] var335 = state.a[1];
		double[][][] var337 = var335[0];
		double[][] var339 = var337[0];
		double[] var341 = var339[0];
		var341[0] = 0.3333333333333334;
		var341[1] = 0.3333333333333334;
		var341[2] = 0.0;
		var341[3] = 0.3333333333333334;
		var341[4] = 0.0;
		double[] var368 = var339[1];
		var368[0] = 0.5;
		var368[1] = 0.5;
		var368[2] = 0.0;
		var368[3] = 0.0;
		var368[4] = 0.0;
		double[][] var399 = var337[1];
		double[] var401 = var399[0];
		var401[0] = 0.3333333333333334;
		var401[1] = 0.3333333333333334;
		var401[2] = 0.0;
		var401[3] = 0.3333333333333334;
		var401[4] = 0.0;
		double[] var428 = var399[1];
		var428[0] = 0.5;
		var428[1] = 0.5;
		var428[2] = 0.0;
		var428[3] = 0.0;
		var428[4] = 0.0;
		double[][][] var462 = var335[1];
		double[][] var464 = var462[0];
		double[] var466 = var464[0];
		var466[0] = 0.0;
		var466[1] = 0.0;
		var466[2] = 0.0;
		var466[3] = 1.0;
		var466[4] = 0.0;
		double[] var496 = var464[1];
		var496[0] = 0.0;
		var496[1] = 0.0;
		var496[2] = 0.0;
		var496[3] = 1.0;
		var496[4] = 0.0;
		double[][] var529 = var462[1];
		double[] var531 = var529[0];
		var531[0] = 0.0;
		var531[1] = 0.0;
		var531[2] = 0.0;
		var531[3] = 1.0;
		var531[4] = 0.0;
		double[] var561 = var529[1];
		var561[0] = 0.0;
		var561[1] = 0.0;
		var561[2] = 0.0;
		var561[3] = 0.0;
		var561[4] = 1.0;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample60)
			logProbabilityValue$sample60();
		if(state.fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(state.fixedFlag$sample65)
			logProbabilityValue$sample65();
		if(state.fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(state.fixedFlag$sample70)
			logProbabilityValue$sample70();
		if(state.fixedFlag$sample72)
			logProbabilityValue$sample72();
		if(state.fixedFlag$sample75)
			logProbabilityValue$sample75();
		if(state.fixedFlag$sample636)
			logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void propagateObservedValues() {
		state.c2 = state.evidence;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model TerminalVariables(int evidence) {\n"
		     + "    double[] priors = {.01, 0.99};\n"
		     + "    double[][] conditionals = {{1, 0}, {0, 1}};\n"
		     + "\n"
		     + "    int c1 = categorical(priors).sample();\n"
		     + "    int c2 = categorical(conditionals[c1]).sample();\n"
		     + "    \n"
		     + "    int c3 = categorical(priors).sample();\n"
		     + "    int c4 = categorical(conditionals[c3]).sample();\n"
		     + "    \n"
		     + "    int c5 = categorical(priors).sample();\n"
		     + "    int c6 = categorical(conditionals[c5]).sample();\n"
		     + "\n"
		     + "    int c7 = categorical(priors).sample();\n"
		     + "    int c8 = categorical(conditionals[c7]).sample();\n"
		     + "\n"
		     + "    int c9 = categorical(priors).sample();\n"
		     + "    int c10 = categorical(conditionals[c9]).sample();\n"
		     + "    \n"
		     + "    int c11 = categorical(priors).sample();\n"
		     + "    int c12 = categorical(conditionals[c11]).sample();\n"
		     + "\n"
		     + "    double[][][][][] a = {\n"
		     + "       {\n"
		     + "        {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}},\n"
		     + "         {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "        {{{0, 0.5, 0, 0.5, 0}, {0, 0.5, 0, 0.5, 0}},\n"
		     + "         {{0, 0.5, 0, 0.5, 0}, {0, 1, 0, 0, 0}}}\n"
		     + "       },\n"
		     + "       {\n"
		     + "         {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}},\n"
		     + "          {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "         {{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}},\n"
		     + "          {{0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}}\n"
		     + "        }\n"
		     + "    };\n"
		     + "    int terminalVariable = categorical(a[c5][c9][c1][c4]).sample();\n"
		     + "    \n"
		     + "    // assert\n"
		     + "    c2.observe(evidence);\n"
		     + "}";
	}
}