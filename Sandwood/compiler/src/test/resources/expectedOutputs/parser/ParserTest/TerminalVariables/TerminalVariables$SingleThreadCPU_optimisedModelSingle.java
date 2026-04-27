package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.TerminalVariables$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.TerminalVariables.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var50$stateProbabilityGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var60$stateProbabilityGlobal;
		double[] cv$var65$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var45$stateProbabilityGlobal
			// 
			// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
			cv$var45$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var50$stateProbabilityGlobal
			// 
			// Allocation of cv$var50$stateProbabilityGlobal for single threaded execution
			cv$var50$stateProbabilityGlobal = new double[2];
			
			// Allocation of cv$var53$stateProbabilityGlobal for single threaded execution
			// 
			// Test if the input to putTask 44 is larger than the current values.
			cv$var53$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var55$stateProbabilityGlobal
			// 
			// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
			cv$var55$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var60$stateProbabilityGlobal
			// 
			// Allocation of cv$var60$stateProbabilityGlobal for single threaded execution
			cv$var60$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var65$stateProbabilityGlobal
			// 
			// Allocation of cv$var65$stateProbabilityGlobal for single threaded execution
			cv$var65$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var70$stateProbabilityGlobal
			// 
			// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
			cv$var70$stateProbabilityGlobal = new double[2];
		}
	}


	public TerminalVariables$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47() {
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample52
	private final void drawValueSample52() {
		state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample55
	private final void drawValueSample55() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_3 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c3))
			lengthCV$conditionals$53_3 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c3))
			lengthCV$conditionals$53_3 = 2;
		state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_3);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57() {
		state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample60
	private final void drawValueSample60() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c5))
			lengthCV$conditionals$58_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c5))
			lengthCV$conditionals$58_1 = 2;
		state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_1);
	}

	// Pick a value from the distribution for the unconditioned variable from sample62
	private final void drawValueSample62() {
		state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample636
	private final void drawValueSample636() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_4 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c5)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c5)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_4 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_4 = 5;
				}
			}
		}
		state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_4);
	}

	// Pick a value from the distribution for the unconditioned variable from sample65
	private final void drawValueSample65() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c7))
			lengthCV$conditionals$63_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c7))
			lengthCV$conditionals$63_1 = 2;
		state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_1);
	}

	// Pick a value from the distribution for the unconditioned variable from sample67
	private final void drawValueSample67() {
		state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample70
	private final void drawValueSample70() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c9))
			lengthCV$conditionals$68_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c9))
			lengthCV$conditionals$68_1 = 2;
		state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_1);
	}

	// Pick a value from the distribution for the unconditioned variable from sample72
	private final void drawValueSample72() {
		state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample75
	private final void drawValueSample75() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c11))
			lengthCV$conditionals$73_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c11))
			lengthCV$conditionals$73_1 = 2;
		state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void inferSample47() {
		state.constrainedFlag$sample47 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c1 = 0;
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample47 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double[] var46 = state.conditionals[0];
			
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 50 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedProbabilities = ((((((0.0 <= state.c2) && (state.c2 < 2)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample47 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = state.a[state.c5][state.c9][0][state.c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_0 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_0 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_0 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_0 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_0 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c1 = 1;
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample47 = true;
		
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double[] var46 = state.conditionals[1];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 50 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		double cv$accumulatedProbabilities = ((((((0.0 <= state.c2) && (state.c2 < 2)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY) + (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample47 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = state.a[state.c5][state.c9][1][state.c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_0 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_0 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_0 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_0 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_0 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_0)) && (0 < lengthCV$var601$634_0)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialize the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = scratch.cv$var45$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = scratch.cv$var45$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Get a local reference to the scratch space.
			// 
			// Get a local reference to the scratch space.
			// 
			// Initialize the sum of the array elements
			cv$logSum = (Math.log((Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
									// Get a local reference to the scratch space.
			scratch.cv$var45$stateProbabilityGlobal[0] = 0.5;
			
									// Get a local reference to the scratch space.
			scratch.cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
									// Get a local reference to the scratch space.
			scratch.cv$var45$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			
									// Get a local reference to the scratch space.
			scratch.cv$var45$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
						// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < scratch.cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			scratch.cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
								// cv$numStates's comment
		// variable marginalization
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var45$stateProbabilityGlobal, 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Categorical 49. Inference was performed using variable
	// marginalization.
	private final void inferSample52() {
		state.constrainedFlag$sample52 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c3 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample55 || state.constrainedFlag$sample55)) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample52 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var51 = state.conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 55 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.c4) && (state.c4 < 2)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c3 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((state.fixedFlag$sample55 || state.constrainedFlag$sample55)) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample52 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var51 = state.conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 55 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.c4) && (state.c4 < 2)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample52) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var50$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var50$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var50$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var50$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var50$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var50$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var50$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var50$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var50$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var50$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var50$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var50$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 52. Inference was performed using variable
	// marginalization.
	private final void inferSample55() {
		state.constrainedFlag$sample55 = false;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_1 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c3))
			lengthCV$conditionals$53_1 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c3))
			lengthCV$conditionals$53_1 = 2;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, lengthCV$conditionals$53_1);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			state.c4 = cv$valuePos;
			
			// Constructing a random variable input for use later.
			double[] var51 = state.conditionals[state.c3];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_2 = 2;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$conditionals$53_2) && (0 < lengthCV$conditionals$53_2)) && (0.0 <= var51[cv$valuePos])) && (var51[cv$valuePos] <= 1.0))?Math.log(var51[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample55 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] var602 = state.a[state.c5][state.c9][state.c1][cv$valuePos];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_1 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c9)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c1)) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((0 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
																					// Value of the variable at this index
							if((1 == cv$valuePos))
								lengthCV$var601$634_1 = 5;
						}
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_1)) && (0 < lengthCV$var601$634_1)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var53$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample55) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var53$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var53$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var53$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var53$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var53$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void inferSample57() {
		state.constrainedFlag$sample57 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c5 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample60) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample57 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var56 = state.conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 60 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.c6) && (state.c6 < 2)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample57 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = state.a[0][state.c9][state.c1][state.c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_2 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_2 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_2 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_2 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_2 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c5 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample60) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample57 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var56 = state.conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 60 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.c6) && (state.c6 < 2)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample57 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = state.a[1][state.c9][state.c1][state.c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_2 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_2 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c4))
						lengthCV$var601$634_2 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c4))
						lengthCV$var601$634_2 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_2)) && (0 < lengthCV$var601$634_2)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample57) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var55$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var55$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var55$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var55$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var55$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var55$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var55$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 62 drawn from Categorical 59. Inference was performed using variable
	// marginalization.
	private final void inferSample62() {
		state.constrainedFlag$sample62 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c7 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample65) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample62 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var61 = state.conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.c8) && (state.c8 < 2)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var60$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c7 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample65) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample62 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var61 = state.conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 65 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.c8) && (state.c8 < 2)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var60$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample62) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var60$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var60$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var60$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var60$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var60$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var60$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var60$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var60$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var60$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var60$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var60$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var60$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var60$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 67 drawn from Categorical 64. Inference was performed using variable
	// marginalization.
	private final void inferSample67() {
		state.constrainedFlag$sample67 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c9 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample70) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample67 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var66 = state.conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 70 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.c10) && (state.c10 < 2)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample636) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample67 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var602 = state.a[state.c5][0][state.c1][state.c4];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$var601$634_3 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_3 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c5)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_3 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((0 == state.c4))
							lengthCV$var601$634_3 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						if((1 == state.c4))
							lengthCV$var601$634_3 = 5;
					}
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 636 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var65$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c9 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample70) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample67 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var66 = state.conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 70 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.c10) && (state.c10 < 2)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample636) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample67 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var602 = state.a[state.c5][1][state.c1][state.c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_3 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_3 = 5;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((0 == state.c4))
						lengthCV$var601$634_3 = 5;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					if((1 == state.c4))
						lengthCV$var601$634_3 = 5;
				}
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 636 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_3)) && (0 < lengthCV$var601$634_3)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var65$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample67) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var65$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var65$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var65$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var65$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var65$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var65$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var65$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var65$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var65$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var65$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var65$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var65$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var65$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 72 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void inferSample72() {
		state.constrainedFlag$sample72 = false;
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.c11 = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= state.priors[0]) && (state.priors[0] <= 1.0))?Math.log(state.priors[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample75) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample72 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var71 = state.conditionals[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.c12) && (state.c12 < 2)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var70$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.c11 = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= state.priors[1]) && (state.priors[1] <= 1.0))?Math.log(state.priors[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample75) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample72 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var71 = state.conditionals[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 75 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.c12) && (state.c12 < 2)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var70$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample72) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var70$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var70$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				// 
				// Get a local reference to the scratch space.
				// 
				// Get a local reference to the scratch space.
				// 
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((scratch.cv$var70$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var70$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var70$stateProbabilityGlobal, 2);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c1) && (state.c1 < 2)) && (0.0 <= state.priors[state.c1])) && (state.priors[state.c1] <= 1.0))?Math.log(state.priors[state.c1]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c1 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c1);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample50) {
			// Generating probabilities for sample task
			double[] var46 = state.conditionals[state.c1];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$48_1 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c1))
				lengthCV$conditionals$48_1 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c1))
				lengthCV$conditionals$48_1 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_1)) && (0 < lengthCV$conditionals$48_1)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c2 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample50 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c2);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c2);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c3) && (state.c3 < 2)) && (0.0 <= state.priors[state.c3])) && (state.priors[state.c3] <= 1.0))?Math.log(state.priors[state.c3]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c3 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c3);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c3);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			double[] var51 = state.conditionals[state.c3];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_4 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_4)) && (0 < lengthCV$conditionals$53_4)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c4 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample52);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c4);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c4);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c5) && (state.c5 < 2)) && (0.0 <= state.priors[state.c5])) && (state.priors[state.c5] <= 1.0))?Math.log(state.priors[state.c5]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c5 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c5);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c5);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample60) {
			// Generating probabilities for sample task
			double[] var56 = state.conditionals[state.c5];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_2)) && (0 < lengthCV$conditionals$58_2)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c6 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample60)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample60 = (state.fixedFlag$sample60 && state.fixedFlag$sample57);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c6);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample60)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c6);
		}
	}

	// Calculate the probability of the samples represented by sample62 using sampled
	// values.
	private final void logProbabilityValue$sample62() {
		// Determine if we need to calculate the values for sample task 62 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample62) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c7) && (state.c7 < 2)) && (0.0 <= state.priors[state.c7])) && (state.priors[state.c7] <= 1.0))?Math.log(state.priors[state.c7]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c7 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample62)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample62 = state.fixedFlag$sample62;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c7);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample62)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c7);
		}
	}

	// Calculate the probability of the samples represented by sample636 using sampled
	// values.
	private final void logProbabilityValue$sample636() {
		// Determine if we need to calculate the values for sample task 636 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample636) {
			// Generating probabilities for sample task
			double[] var602 = state.a[state.c5][state.c9][state.c1][state.c4];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_5 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_5 = 5;
					}
				}
			}
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_5)) && (0 < lengthCV$var601$634_5)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$terminalVariable = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample636)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample636 = ((((state.fixedFlag$sample636 && state.fixedFlag$sample47) && state.fixedFlag$sample55) && state.fixedFlag$sample57) && state.fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$terminalVariable);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample636)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$terminalVariable);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			double[] var61 = state.conditionals[state.c7];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_2)) && (0 < lengthCV$conditionals$63_2)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c8 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample65)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample65 = (state.fixedFlag$sample65 && state.fixedFlag$sample62);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c8);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample65)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c8);
		}
	}

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample67) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c9) && (state.c9 < 2)) && (0.0 <= state.priors[state.c9])) && (state.priors[state.c9] <= 1.0))?Math.log(state.priors[state.c9]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c9 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample67 = state.fixedFlag$sample67;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c9);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c9);
		}
	}

	// Calculate the probability of the samples represented by sample70 using sampled
	// values.
	private final void logProbabilityValue$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample70) {
			// Generating probabilities for sample task
			double[] var66 = state.conditionals[state.c9];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_2)) && (0 < lengthCV$conditionals$68_2)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c10 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample70)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample70 = (state.fixedFlag$sample70 && state.fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c10);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample70)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c10);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((((0.0 <= state.c11) && (state.c11 < 2)) && (0.0 <= state.priors[state.c11])) && (state.priors[state.c11] <= 1.0))?Math.log(state.priors[state.c11]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c11 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample72)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample72 = state.fixedFlag$sample72;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample72)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c11);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			double[] var71 = state.conditionals[state.c11];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_2 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_2 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_2 = 2;
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_2)) && (0 < lengthCV$conditionals$73_2)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$c12 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample75 = (state.fixedFlag$sample75 && state.fixedFlag$sample72);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$c12);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$c12);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_2 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c1))
			lengthCV$conditionals$48_2 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c1))
			lengthCV$conditionals$48_2 = 2;
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_5 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_5);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_3 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_3);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_3 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_3);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_3 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_3);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_3 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_3 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_3);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_6 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_6 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_6);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_9 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_9 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_9 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_9);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_7 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_7);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_7 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_7);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_7 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_7);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_7 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_7);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_10 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_10 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_10 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_10);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_3 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == state.c1))
			lengthCV$conditionals$48_3 = 2;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == state.c1))
			lengthCV$conditionals$48_3 = 2;
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_3);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_6 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_6);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_4 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_4);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_4 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_4);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_4 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_4);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_4 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_4 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_4);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_7 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_7 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_7);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_7 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_7 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_7);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_5 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_5);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_5 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_5);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_5 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_5);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_5 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_5 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_8 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_8 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_8);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$53_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c3))
				lengthCV$conditionals$53_8 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c3))
				lengthCV$conditionals$53_8 = 2;
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_8);
		}
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample60) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$58_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5))
				lengthCV$conditionals$58_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5))
				lengthCV$conditionals$58_6 = 2;
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_6);
		}
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample65) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$63_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c7))
				lengthCV$conditionals$63_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c7))
				lengthCV$conditionals$63_6 = 2;
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_6);
		}
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample70) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$68_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c9))
				lengthCV$conditionals$68_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c9))
				lengthCV$conditionals$68_6 = 2;
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_6);
		}
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample75) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$conditionals$73_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c11))
				lengthCV$conditionals$73_6 = 2;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c11))
				lengthCV$conditionals$73_6 = 2;
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample636) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$var601$634_9 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == state.c5)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == state.c9)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == state.c1)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == state.c4))
							lengthCV$var601$634_9 = 5;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == state.c4))
							lengthCV$var601$634_9 = 5;
					}
				}
			}
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_9);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
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
		}
		// Infer the samples in reverse chronological order.
		else {
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
		
		// Reverse the direction of execution for the next iteration
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

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
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

	// Method for initialising the model into a valid state before commencing inference
	// etc.
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

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
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

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
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

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
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

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		state.c2 = state.evidence;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
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