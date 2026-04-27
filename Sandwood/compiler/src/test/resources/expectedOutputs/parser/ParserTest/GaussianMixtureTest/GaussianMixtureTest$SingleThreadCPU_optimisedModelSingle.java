package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.GaussianMixtureTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.GaussianMixtureTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class GaussianMixtureTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var17$countGlobal;
		double[] cv$var68$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var17$countGlobal
			// 
			// Allocation of cv$var17$countGlobal for single threaded execution
			cv$var17$countGlobal = new double[5];
			
			// Constructor for cv$var68$stateProbabilityGlobal
			// 
			// Allocation of cv$var68$stateProbabilityGlobal for single threaded execution
			cv$var68$stateProbabilityGlobal = new double[5];
		}
	}


	public GaussianMixtureTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample17
	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
	}

	// Pick a value from the distribution for the unconditioned variable from sample34
	private final void drawValueSample34(int var33) {
		state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
	}

	// Pick a value from the distribution for the unconditioned variable from sample52
	private final void drawValueSample52(int var51) {
		state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample68
	private final void drawValueSample68(int i$var66) {
		state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var17$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 67.
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.constrainedFlag$sample68[i$var66]) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample17 = true;
				
				// Increment the sample counter with the value sampled by sample task 68 of random
				// variable var67
				// 
												// A local reference to the scratch space.
				scratch.cv$var17$countGlobal[state.z[i$var66]] = (scratch.cv$var17$countGlobal[state.z[i$var66]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample17)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, scratch.cv$var17$countGlobal, state.phi, 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Gaussian 22. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void inferSample34(int var33) {
		state.constrainedFlag$sample34[var33] = false;
		
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		
		// Processing random variable 71.
		// 
		// Looking for a path between Sample 34 and consumer Gaussian 71.
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if((var33 == state.z[i$var66])) {
				// Processing sample task 72 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample34[var33] = true;
				
				// Record the value of a sample generated by a consuming sample 72 of random variable
				// var71.
				// 
				// Add the denominator squared to the sample denominator
				// 
																// cv$denominator's comment
				// State for tracking the changes that happen to the sampled value between it being
				// consumed and it being produced.
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				
				// Add the weighting of the sample to the sum.
				// 
												// Substituted "cv$numerator" with its value "0.0".
				cv$sum = (cv$sum + state.x[i$var66]);
				
				// If we have not got the value of sigma yet record it and set a flag so it is not
				// recorded again.
				if(cv$sigmaNotFound) {
					cv$sigmaValue = state.sigma[state.z[i$var66]];
					cv$sigmaNotFound = false;
				}
			}
		}
		if(state.constrainedFlag$sample34[var33])
			// Guards to ensure that mu is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.mu[var33] = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from InverseGamma 40. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void inferSample52(int var51) {
		state.constrainedFlag$sample52[var51] = false;
		
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		
		// Processing random variable 71.
		// 
		// Looking for a path between Sample 52 and consumer Gaussian 71.
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if((var51 == state.z[i$var66])) {
				// Processing sample task 72 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample52[var51] = true;
				
				// Consume sample task 72 from random variable var71.
				// 
				// The difference between the mean parameter and the value sampled from the Gaussian.
				// 
				// The mean parameter for Gaussian var71.
				double cv$var71$diff = (state.mu[state.z[i$var66]] - state.x[i$var66]);
				
				// Include this sample by adding the square of the difference to the sum.
				cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
				
				// Increment the number of samples in the calculation.
				cv$count = (cv$count + 1);
			}
		}
		if(state.constrainedFlag$sample52[var51])
			// Guards to ensure that sigma is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.sigma[var51] = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from Categorical 67. Inference was performed using variable
	// marginalization.
	private final void inferSample68(int i$var66) {
		state.constrainedFlag$sample68[i$var66] = false;
		
				// cv$numStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			state.z[i$var66] = cv$valuePos;
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample68[i$var66] = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			double var70 = state.sigma[cv$valuePos];
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 72 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
									// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			scratch.cv$var68$stateProbabilityGlobal[cv$valuePos] = (((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - state.mu[cv$valuePos]) / Math.sqrt(var70))) - (Math.log(var70) * 0.5)):Double.NEGATIVE_INFINITY) + (((0.0 <= state.phi[cv$valuePos]) && (state.phi[cv$valuePos] <= 1.0))?Math.log(state.phi[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		if(state.constrainedFlag$sample68[i$var66]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var68$stateProbabilityGlobal[0];
			
			// Unrolled loop
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var68$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var68$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var68$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var68$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var68$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var68$stateProbabilityGlobal[cv$indexName] = 0.2;
			} else {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var68$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var68$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 5; cv$indexName < scratch.cv$var68$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var68$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var68$stateProbabilityGlobal, 5);
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample17) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.phi, state.alpha, 5);
			
			// Store the sample task probability
			state.logProbability$phi = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample17)
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
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$phi);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$phi);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < 5; var33 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((state.mu[var33] / 4.47213595499958))) - 1.4978661367769954);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var34 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$mu = (state.logProbability$mu + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample34)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$mu = (state.logProbability$mu + state.logProbability$var34);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var34);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var34);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < 5; var51 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.sigma[var51], 1.0, 1.0));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var52 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$sigma = (state.logProbability$sigma + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$sigma = (state.logProbability$sigma + state.logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Generating probabilities for sample task
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// A guard to check if the sample value is ever reached.
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.z[i$var66];
			
			// Record that the sample was reached.
			cv$sampleReached = true;
			
			// Add the probability of this sample task to the sample task accumulator.
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
			cv$sampleAccumulator = (cv$sampleAccumulator + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= state.phi[cv$sampleValue])) && (state.phi[cv$sampleValue] <= 1.0))?Math.log(state.phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
		}
		
		// Only update the sample if it was reached, otherwise the NaN will be
		// erroneously over written.
		if(cv$sampleReached)
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$z = cv$sampleAccumulator;
		
		// Add probability to model
		// 
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		// 
		// Accumulator for probabilities of instances of the random variable
		state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Generating probabilities for sample task
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// A guard to check if the sample value is ever reached.
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			double var70 = state.sigma[state.z[i$var66]];
			
			// Record that the sample was reached.
			cv$sampleReached = true;
			
			// Add the probability of this sample task to the sample task accumulator.
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
			cv$sampleAccumulator = (cv$sampleAccumulator + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - state.mu[state.z[i$var66]]) / Math.sqrt(var70))) - (Math.log(var70) * 0.5)):Double.NEGATIVE_INFINITY));
		}
		
		// Only update the sample if it was reached, otherwise the NaN will be
		// erroneously over written.
		if(cv$sampleReached)
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$var72 = cv$sampleAccumulator;
		
		// Update the variable probability
		// 
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		// 
		// Accumulator for probabilities of instances of the random variable
		state.logProbability$x = (state.logProbability$x + cv$sampleAccumulator);
		
		// Add probability to model
		// 
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		// 
		// Accumulator for probabilities of instances of the random variable
		state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
		
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		// 
		// Accumulator for probabilities of instances of the random variable
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
			state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[i$var66]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mu[state.z[i$var66]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
			state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[i$var66]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mu[state.z[i$var66]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				state.mu[var33] = (DistributionSampling.sampleGaussian(state.RNG$) * 4.47213595499958);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				state.sigma[var51] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.z[i$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.phi, 5);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample34) {
				for(int var33 = 0; var33 < 5; var33 += 1)
					inferSample34(var33);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample52) {
				for(int var51 = 0; var51 < 5; var51 += 1)
					inferSample52(var51);
			}
			for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
				inferSample68(i$var66);
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var66 = (state.length$xMeasured - 1); i$var66 >= 0; i$var66 -= 1)
				inferSample68(i$var66);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample52) {
				for(int var51 = 4; var51 >= 0; var51 -= 1)
					inferSample52(var51);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample34) {
				for(int var33 = 4; var33 >= 0; var33 -= 1)
					inferSample34(var33);
			}
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		for(int var33 = 0; var33 < 5; var33 += 1) {
			if(!state.constrainedFlag$sample34[var33])
				drawValueSample34(var33);
		}
		for(int var51 = 0; var51 < 5; var51 += 1) {
			if(!state.constrainedFlag$sample52[var51])
				drawValueSample52(var51);
		}
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if(!state.constrainedFlag$sample68[i$var66])
				drawValueSample68(i$var66);
		}
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
		if(!state.fixedProbFlag$sample17)
			state.logProbability$phi = Double.NaN;
		state.logProbability$mu = 0.0;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$var34 = Double.NaN;
		state.logProbability$sigma = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var52 = Double.NaN;
		state.logProbability$z = Double.NaN;
		state.logProbability$x = 0.0;
		state.logProbability$var72 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.alpha[i$var13] = 1.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample34$1 = 0; index$constrainedFlag$sample34$1 < state.constrainedFlag$sample34.length; index$constrainedFlag$sample34$1 += 1)
			state.constrainedFlag$sample34[index$constrainedFlag$sample34$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.x.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.x[cv$index1] = state.xMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

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
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}