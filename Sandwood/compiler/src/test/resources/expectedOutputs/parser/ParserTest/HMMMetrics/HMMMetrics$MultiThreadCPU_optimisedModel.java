package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var55;
		double[][] cv$var30$countGlobal;
		double[] cv$var35$countGlobal;
		double[] cv$var38$stateProbabilityGlobal;
		double[] cv$var56$stateProbabilityGlobal;
		boolean[] guard$sample39gaussian179$global;
		boolean[] guard$sample39gaussian184$global;
		boolean[] guard$sample39gaussian189$global;
		boolean[] guard$sample57gaussian179$global;
		boolean[] guard$sample57gaussian184$global;
		boolean[] guard$sample57gaussian189$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var30$countGlobal
			// 
			// Allocation of cv$var30$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var30$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var30$countGlobal[cv$index] = new double[state.noStates];
			
			// Constructor for cv$var35$countGlobal
			// 
			// Allocation of cv$var35$countGlobal for single threaded execution
			cv$var35$countGlobal = new double[state.noStates];
			
			// Constructor for cv$distributionAccumulator$var55
			// 
			// Allocation of cv$distributionAccumulator$var55 for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 55. Initially set to the value
			// of putTask 31.
			cv$distributionAccumulator$var55 = new double[state.noStates];
			
			// Constructor for cv$var38$stateProbabilityGlobal
			// 
			// Allocation of cv$var38$stateProbabilityGlobal for single threaded execution
			cv$var38$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample39gaussian179$global
			// 
			// Allocation of guard$sample39gaussian179$global for single threaded execution
			guard$sample39gaussian179$global = new boolean[state.length$cpu_measured];
			
			// Constructor for guard$sample39gaussian184$global
			// 
			// Allocation of guard$sample39gaussian184$global for single threaded execution
			guard$sample39gaussian184$global = new boolean[state.length$cpu_measured];
			
			// Constructor for guard$sample39gaussian189$global
			// 
			// Allocation of guard$sample39gaussian189$global for single threaded execution
			guard$sample39gaussian189$global = new boolean[state.length$cpu_measured];
			
			// Allocation of cv$var56$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 55. Initially set to the value
			// of putTask 31.
			cv$var56$stateProbabilityGlobal = new double[state.noStates];
			
			// Constructor for guard$sample57gaussian179$global
			// 
			// Allocation of guard$sample57gaussian179$global for single threaded execution
			guard$sample57gaussian179$global = new boolean[state.length$cpu_measured];
			
			// Constructor for guard$sample57gaussian184$global
			// 
			// Allocation of guard$sample57gaussian184$global for single threaded execution
			guard$sample57gaussian184$global = new boolean[state.length$cpu_measured];
			
			// Allocation of guard$sample57gaussian189$global for single threaded execution
			guard$sample57gaussian189$global = new boolean[state.length$cpu_measured];
		}
	}


	public HMMMetrics$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample113
	private final void drawValueSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample130
	private final void drawValueSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample147
	private final void drawValueSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample164
	private final void drawValueSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample30
	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, state.m[var29]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample36
	private final void drawValueSample36() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int i$var50) {
		state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample77
	private final void drawValueSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample95
	private final void drawValueSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 113 drawn from Gaussian 100. Inference was performed using Metropolis-Hastings.
	private final void inferSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		state.constrainedFlag$sample113[var111] = false;
		
		// The original value of the sample
		double cv$originalValue = state.pageFaultsMean[var111];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						int var162 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var162) && (var162 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var187 = state.pageFaultsVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var111];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 113.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double var187 = state.pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[i$var174];
						if(((0 <= var162) && (var162 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var187 = state.pageFaultsVar[state.st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var111];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double var187 = state.pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample113[var111]) {
			// Guards to ensure that pageFaultsMean is only updated when there is a valid path.
			state.pageFaultsMean[var111] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						int var162 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var162) && (var162 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var187 = state.pageFaultsVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var111];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 113.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double var187 = state.pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[i$var174];
						if(((0 <= var162) && (var162 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var187 = state.pageFaultsVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var111];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double var187 = state.pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that pageFaultsMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.pageFaultsMean[var111] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 130 drawn from InverseGamma 117. Inference was performed using Metropolis-Hastings.
	private final void inferSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		state.constrainedFlag$sample130[var128] = false;
		
		// The original value of the sample
		double cv$originalValue = state.cpuVar[var128];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 130.
						int var75 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var75) && (var75 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
																					// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var128];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 130.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[i$var174];
						if(((0 <= var75) && (var75 < state.noStates))) {
																					// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var128];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample130[var128]) {
			// Guards to ensure that cpuVar is only updated when there is a valid path.
			state.cpuVar[var128] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 130.
						int var75 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var75) && (var75 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var128];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 130.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[i$var174];
						if(((0 <= var75) && (var75 < state.noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var128];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that cpuVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.cpuVar[var128] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 147 drawn from InverseGamma 134. Inference was performed using Metropolis-Hastings.
	private final void inferSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		state.constrainedFlag$sample147[var145] = false;
		
		// The original value of the sample
		double cv$originalValue = state.memVar[var145];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 147.
						int var93 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var93) && (var93 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
																					// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var145];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 147.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[i$var174];
						if(((0 <= var93) && (var93 < state.noStates))) {
																					// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var145];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample147[var145]) {
			// Guards to ensure that memVar is only updated when there is a valid path.
			state.memVar[var145] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 147.
						int var93 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var93) && (var93 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var145];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 147.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[i$var174];
						if(((0 <= var93) && (var93 < state.noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var145];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that memVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.memVar[var145] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 164 drawn from InverseGamma 151. Inference was performed using Metropolis-Hastings.
	private final void inferSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		state.constrainedFlag$sample164[var162] = false;
		
		// The original value of the sample
		double cv$originalValue = state.pageFaultsVar[var162];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 164.
						int var111 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var111) && (var111 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
																					// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var162];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 164.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[i$var174];
						if(((0 <= var111) && (var111 < state.noStates))) {
																					// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var162];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The original value of the sample
						// 
																		// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample164[var162]) {
			// Guards to ensure that pageFaultsVar is only updated when there is a valid path.
			state.pageFaultsVar[var162] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 164.
						int var111 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var111) && (var111 < state.noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var162];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 164.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[i$var174];
						if(((0 <= var111) && (var111 < state.noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var162];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// The proposed new value for the sample
						// 
																		// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that pageFaultsVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.pageFaultsVar[var162] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		state.constrainedFlag$sample30[var29] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var30$countGlobal[threadID$cv$var29];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample57) {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 30 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 == state.st[0])) {
						// Processing sample task 57 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample30[var29] = true;
						
						// Increment the sample counter with the value sampled by sample task 57 of random
						// variable var55
						// 
																		// Substituted "i$var50" with its value "1".
						cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + 1.0);
					}
				} else {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample30[var29] = true;
					
					// Increment the sample counter with the value sampled by sample task 57 of random
					// variable var55
					// 
																				// cv$probabilitySample39Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$3" with its value "var29".
					cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + state.distribution$sample39[var29]);
				}
			}
			for(int i$var50 = 2; i$var50 < state.samples; i$var50 += 1) {
				if((var29 == state.st[(i$var50 - 1)])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample30[var29] = true;
					
					// Increment the sample counter with the value sampled by sample task 57 of random
					// variable var55
					cv$countLocal[state.st[i$var50]] = (cv$countLocal[state.st[i$var50]] + 1.0);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 30 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 == state.st[0])) {
						// Processing sample task 57 of consumer random variable null.
						// 
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
																					// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample57[0][cv$loopIndex]);
					}
				} else {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
										// cv$probabilitySample39Value33's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample39$32" with its value "var29".
					double cv$distributionProbability = state.distribution$sample39[var29];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
						// Substituted "i$var50" with its value "1".
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				int index$i$40 = (i$var50 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$40)) {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
										// cv$probabilitySample57Value42's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample57$41" with its value "var29".
					double cv$distributionProbability = state.distribution$sample57[(index$i$40 - 1)][var29];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[(i$var50 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		if(state.constrainedFlag$sample30[var29])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var29], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Dirichlet 34. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample36() {
		state.constrainedFlag$sample36 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var35$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample39) {
			// Processing random variable 37.
			// 
			// Processing sample task 39 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample36 = true;
			
			// Increment the sample counter with the value sampled by sample task 39 of random
			// variable var37
			// 
									// A local reference to the scratch space.
			scratch.cv$var35$countGlobal[state.st[0]] = (scratch.cv$var35$countGlobal[state.st[0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing sample task 39 of consumer random variable null.
			// 
			// Merge the distribution probabilities into the count
			// 
			// Get the length of the array
			for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
												// A local reference to the scratch space.
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				scratch.cv$var35$countGlobal[cv$loopIndex] = (scratch.cv$var35$countGlobal[cv$loopIndex] + state.distribution$sample39[cv$loopIndex]);
		}
		if(state.constrainedFlag$sample36)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var35$countGlobal, state.initialStateDistribution, state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Categorical 37. Inference was performed using variable
	// marginalization.
	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$valuePos])) && (state.initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(state.initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample57 && (1 < state.samples))) {
				// Looking for a path between Sample 39 and consumer Categorical 55.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample39 = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 39.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var54 = state.m[cv$valuePos];
					
															// Substituted "i$var50" with its value "1".
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[1]) && (state.st[1] < state.noStates)) && (0.0 <= var54[state.st[1]])) && (var54[state.st[1]] <= 1.0))?Math.log(var54[state.st[1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 57 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				// Processing random variable 178.
				// 
				// Looking for a path between Sample 39 and consumer Gaussian 178.
				// 
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample39gaussian179$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample39gaussian179$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian179$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = state.cpuVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Substituted "i$var174" with its value "0".
				if(!scratch.guard$sample39gaussian179$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian179$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var177 = state.cpuVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Processing random variable 183.
				// 
				// Looking for a path between Sample 39 and consumer Gaussian 183.
				// 
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample39gaussian184$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample39gaussian184$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian184$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = state.memVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Substituted "i$var174" with its value "0".
				if(!scratch.guard$sample39gaussian184$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian184$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var182 = state.memVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample39gaussian189$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!scratch.guard$sample39gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian189$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = state.pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
				
				// Substituted "i$var174" with its value "0".
				if(!scratch.guard$sample39gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample39gaussian189$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < state.noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var187 = state.pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!state.fixedFlag$sample57 && (1 < state.samples))) {
				// Looking for a path between Sample 39 and consumer Categorical 55.
				// Processing sample task 57 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var55[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 39.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
															// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$reachedDistributionProbability = 1.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
															// Constructing a random variable input for use later.
					// 
															// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var55, 1.0, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var50" with its value "1".
				double[] cv$sampleDistribution = state.distribution$sample57[0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var38$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample39) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var38$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var38$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var38$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					state.distribution$sample39[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Local copy of the probability array
					state.distribution$sample39[cv$indexName] = Math.exp((scratch.cv$var38$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				state.distribution$sample39[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 55. Inference was performed using variable
	// marginalization.
	private final void inferSample57(int i$var50) {
		state.constrainedFlag$sample57[(i$var50 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 55.
		// 
		// Enumerating the possible arguments for Categorical 55.
		if((1 == i$var50)) {
			// Exploring all the possible state counts for random variable 55.
			// 
			// Enumerating the possible arguments for Categorical 55.
			if(state.fixedFlag$sample39) {
				int var29 = state.st[0];
				
												// Substituted "i$var50" with its value "1".
				if(((0 <= var29) && (var29 < state.noStates)))
					// variable marginalization
					// 
										// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, state.noStates);
			} else {
				// Enumerating the possible outputs of Categorical 37.
				if((0 < state.noStates))
					// variable marginalization
					cv$numStates = state.noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < state.noStates)) {
			int index$i$10 = (i$var50 - 1);
			
												// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$10" with its value "(i$var50 - 1)".
			if(((1 <= index$i$10) && !(index$i$10 == i$var50)))
				// variable marginalization
				cv$numStates = state.noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 55 creating
			// sample task 57.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 55.
			if((1 == i$var50)) {
				// Enumerating the possible arguments for Categorical 55.
				if(state.fixedFlag$sample39) {
					int var29 = state.st[0];
					
															// Substituted "i$var50" with its value "1".
					if(((0 <= var29) && (var29 < state.noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var50" with its value "1".
						double[] var54 = state.m[state.st[0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var54[cv$valuePos])) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Processing random variable 178.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 178.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian179$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var177 = state.cpuVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 180 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var177 = state.cpuVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 180 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						
						// Processing random variable 183.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 183.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian184$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var182 = state.memVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 185 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var182 = state.memVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 185 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var187 = state.pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < state.noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var187 = state.pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
							}
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 37.
					for(int index$sample39$18 = 0; index$sample39$18 < state.noStates; index$sample39$18 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample39Value19 = state.distribution$sample39[index$sample39$18];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value19);
						
						// Constructing a random variable input for use later.
						double[] var54 = state.m[index$sample39$18];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value19) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 178.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 178.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian179$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var177 = state.cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var177 = state.cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Processing random variable 183.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 183.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian184$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var182 = state.memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var182 = state.memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						scratch.guard$sample57gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!scratch.guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var187 = state.pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
																		// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!scratch.guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							scratch.guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							state.constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var187 = state.pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			int index$i$25 = (i$var50 - 1);
			
												// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
									// Substituted "index$i$25" with its value "(i$var50 - 1)".
			if(((1 <= index$i$25) && !(index$i$25 == i$var50))) {
				// Enumerating the possible outputs of Categorical 55.
				for(int index$sample57$26 = 0; index$sample57$26 < state.noStates; index$sample57$26 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample57Value27 = state.distribution$sample57[(index$i$25 - 1)][index$sample57$26];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
					
					// Constructing a random variable input for use later.
					double[] var54 = state.m[index$sample57$26];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 178.
					// 
					// Looking for a path between Sample 57 and consumer Gaussian 178.
					// 
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample57gaussian179$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample57gaussian179$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian179$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = state.cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var50] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian179$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian179$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = state.cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var50] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Processing random variable 183.
					// 
					// Looking for a path between Sample 57 and consumer Gaussian 183.
					// 
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample57gaussian184$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample57gaussian184$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian184$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = state.memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var50] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian184$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian184$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = state.memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var50] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Set the flags to false
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample57gaussian189$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!scratch.guard$sample57gaussian189$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian189$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = state.pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var50] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian189$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
																		// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						scratch.guard$sample57gaussian189$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = state.pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var50] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$i$618_2 = (i$var50 + 1);
			if((index$i$618_2 < state.samples)) {
				// Processing sample task 57 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					scratch.cv$distributionAccumulator$var55[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 57.
				// 
				// Processing random variable 55.
				// 
				// Looking for a path between Sample 57 and consumer Categorical 55.
				// 
				// Value of the variable at this index
				if((cv$valuePos < state.noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 55.
					if((1 == i$var50)) {
						// Enumerating the possible arguments for Categorical 55.
						if(state.fixedFlag$sample39) {
							int index$var29$627_1 = state.st[0];
							
																					// Substituted "i$var50" with its value "1".
							if(((0 <= index$var29$627_1) && (index$var29$627_1 < state.noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 37.
							for(int index$sample39$623 = 0; index$sample39$623 < state.noStates; index$sample39$623 += 1)
								// Add the probability of this argument configuration.
								// 
																// cv$probabilitySample39Value624's comment
								// Update the probability of sampling this value from the distribution value.
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample39[index$sample39$623]);
						}
					}
					int index$i$630 = (i$var50 - 1);
					
																				// index$i$620's comment
					// Copy of index so that its values can be safely substituted
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
															// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					if((((1 <= index$i$630) && !(index$i$630 == i$var50)) && !(index$i$630 == index$i$618_2))) {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$631 = 0; index$sample57$631 < state.noStates; index$sample57$631 += 1)
							// Add the probability of this argument configuration.
							// 
														// cv$probabilitySample57Value632's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample57[(index$i$630 - 1)][index$sample57$631]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
															// Constructing a random variable input for use later.
					// 
					// Processing random variable 55.
					// 
					// Looking for a path between Sample 57 and consumer Categorical 55.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = state.distribution$sample57[(index$i$618_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			scratch.cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample57[(i$var50 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample57[(i$var50 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var56$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var56$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 77 drawn from Gaussian 64. Inference was performed using Metropolis-Hastings.
	private final void inferSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		state.constrainedFlag$sample77[var75] = false;
		
		// The original value of the sample
		double cv$originalValue = state.cpuMean[var75];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						int var128 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var128) && (var128 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var177 = state.cpuVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var75];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 77.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double var177 = state.cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[i$var174];
						if(((0 <= var128) && (var128 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var177 = state.cpuVar[state.st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var75];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double var177 = state.cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample77[var75]) {
			// Guards to ensure that cpuMean is only updated when there is a valid path.
			state.cpuMean[var75] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						int var128 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var128) && (var128 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var177 = state.cpuVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var75];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 77.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double var177 = state.cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[i$var174];
						if(((0 <= var128) && (var128 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var177 = state.cpuVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var75];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double var177 = state.cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that cpuMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.cpuMean[var75] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Gaussian 82. Inference was performed using Metropolis-Hastings.
	private final void inferSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		state.constrainedFlag$sample95[var93] = false;
		
		// The original value of the sample
		double cv$originalValue = state.memMean[var93];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						int var145 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var145) && (var145 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var182 = state.memVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var93];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 95.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double var182 = state.memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[i$var174];
						if(((0 <= var145) && (var145 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var182 = state.memVar[state.st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var93];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double var182 = state.memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.constrainedFlag$sample95[var93]) {
			// Guards to ensure that memMean is only updated when there is a valid path.
			state.memMean[var93] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == state.st[0])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						int var145 = state.st[0];
						
																		// Substituted "i$var174" with its value "0".
						if(((0 <= var145) && (var145 < state.noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var182 = state.memVar[state.st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double cv$probabilitySample39Value5 = state.distribution$sample39[var93];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 95.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double var182 = state.memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == state.st[i$var174])) {
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[i$var174];
						if(((0 <= var145) && (var145 < state.noStates))) {
							// Constructing a random variable input for use later.
							double var182 = state.memVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var93];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double var182 = state.memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
																		// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that memMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				state.memMean[var93] = cv$originalValue;
		}
	}

	// Calculate the probability of the samples represented by sample180 using probability
	// distributions.
	private final void logProbabilityDistribution$sample180() {
		// Determine if we need to calculate the values for sample task 180 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample180) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 180 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = state.cpu[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 178.
					// 
					// Enumerating the possible arguments for Gaussian 178.
					if(state.fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[0])) {
							int var75 = state.st[0];
							
																					// Substituted "i$var174" with its value "0".
							if(((0 <= var75) && (var75 < state.noStates))) {
								// Substituted "i$var174" with its value "0".
								double var177 = state.cpuVar[state.st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[state.st[0]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var177 = state.cpuVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[index$sample39$3]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 178.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 178.
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[i$var174])) {
							int var75 = state.st[i$var174];
							if(((0 <= var75) && (var75 < state.noStates))) {
								double var177 = state.cpuVar[state.st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[state.st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var177 = state.cpuVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[index$sample57$43]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample180 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample77) && state.fixedFlag$sample130);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample180[i$var174]);
			
			// Update the variable probability
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample185 using probability
	// distributions.
	private final void logProbabilityDistribution$sample185() {
		// Determine if we need to calculate the values for sample task 185 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample185) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 185 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = state.mem[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 183.
					// 
					// Enumerating the possible arguments for Gaussian 183.
					if(state.fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[0])) {
							int var93 = state.st[0];
							
																					// Substituted "i$var174" with its value "0".
							if(((0 <= var93) && (var93 < state.noStates))) {
								// Substituted "i$var174" with its value "0".
								double var182 = state.memVar[state.st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[state.st[0]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var182 = state.memVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[index$sample39$3]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 183.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 183.
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[i$var174])) {
							int var93 = state.st[i$var174];
							if(((0 <= var93) && (var93 < state.noStates))) {
								double var182 = state.memVar[state.st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[state.st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var182 = state.memVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[index$sample57$43]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample185 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample95) && state.fixedFlag$sample147);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample185[i$var174]);
			
			// Update the variable probability
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample190 using probability
	// distributions.
	private final void logProbabilityDistribution$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 190 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = state.pageFaults[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 188.
					// 
					// Enumerating the possible arguments for Gaussian 188.
					if(state.fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[0])) {
							int var111 = state.st[0];
							
																					// Substituted "i$var174" with its value "0".
							if(((0 <= var111) && (var111 < state.noStates))) {
								// Substituted "i$var174" with its value "0".
								double var187 = state.pageFaultsVar[state.st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[state.st[0]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var187 = state.pageFaultsVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[index$sample39$3]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 188.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 188.
					if(state.fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= state.st[i$var174])) {
							int var111 = state.st[i$var174];
							if(((0 <= var111) && (var111 < state.noStates))) {
								double var187 = state.pageFaultsVar[state.st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var187 = state.pageFaultsVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[index$sample57$43]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample190 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample113) && state.fixedFlag$sample164);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample190[i$var174]);
			
			// Update the variable probability
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample39 using probability
	// distributions.
	private final void logProbabilityDistribution$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample39) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample39) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[0];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				state.logProbability$var38 = cv$distributionAccumulator;
				
				// Update the variable probability
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
				state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
				
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
				// 
				// Substituted "fixedFlag$sample39" with its value "true".
				state.fixedProbFlag$sample39 = state.fixedFlag$sample36;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample39)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$st = (state.logProbability$st + state.logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample57 using probability
	// distributions.
	private final void logProbabilityDistribution$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample57) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[i$var50];
					
					// Enumerating the possible arguments for Categorical 55.
					if((1 == i$var50)) {
						// Enumerating the possible arguments for Categorical 55.
						if(state.fixedFlag$sample39) {
							int var29 = state.st[0];
							
																					// Substituted "i$var50" with its value "1".
							if(((0 <= var29) && (var29 < state.noStates))) {
								// Substituted "i$var50" with its value "1".
								double[] var54 = state.m[state.st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 37.
							for(int index$sample39$4 = 0; index$sample39$4 < state.noStates; index$sample39$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample39Value5 = state.distribution$sample39[index$sample39$4];
								double[] var54 = state.m[index$sample39$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var50 - 1)".
					if((2 <= i$var50)) {
						int var29 = state.st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < state.noStates))) {
							double[] var54 = state.m[state.st[(i$var50 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample57" with its value "true".
				state.fixedProbFlag$sample57 = (state.fixedFlag$sample30 && state.fixedFlag$sample39);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[(i$var50 - 1)]);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample57)
				// Update the variable probability
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample113 using sampled
	// values.
	private final void logProbabilityValue$sample113() {
		// Determine if we need to calculate the values for sample task 113 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample113) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var111 = 0; var111 < state.noStates; var111 += 1)
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((state.pageFaultsMean[var111] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			
			// Store the random variable instance probability
			state.logProbability$var112 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$pageFaultsMean = (state.logProbability$pageFaultsMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample113)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample113 = state.fixedFlag$sample113;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$pageFaultsMean = (state.logProbability$pageFaultsMean + state.logProbability$var112);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var112);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample113)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var112);
		}
	}

	// Calculate the probability of the samples represented by sample130 using sampled
	// values.
	private final void logProbabilityValue$sample130() {
		// Determine if we need to calculate the values for sample task 130 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample130) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var128 = 0; var128 < state.noStates; var128 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.cpuVar[var128], 5.0, 0.5));
			
			// Store the random variable instance probability
			state.logProbability$var129 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$cpuVar = (state.logProbability$cpuVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample130)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample130 = state.fixedFlag$sample130;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$cpuVar = (state.logProbability$cpuVar + state.logProbability$var129);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var129);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample130)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var129);
		}
	}

	// Calculate the probability of the samples represented by sample147 using sampled
	// values.
	private final void logProbabilityValue$sample147() {
		// Determine if we need to calculate the values for sample task 147 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample147) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var145 = 0; var145 < state.noStates; var145 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.memVar[var145], 5.0, 0.5));
			
			// Store the random variable instance probability
			state.logProbability$var146 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$memVar = (state.logProbability$memVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample147)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample147 = state.fixedFlag$sample147;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$memVar = (state.logProbability$memVar + state.logProbability$var146);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var146);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample147)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var146);
		}
	}

	// Calculate the probability of the samples represented by sample164 using sampled
	// values.
	private final void logProbabilityValue$sample164() {
		// Determine if we need to calculate the values for sample task 164 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample164) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var162 = 0; var162 < state.noStates; var162 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.pageFaultsVar[var162], 5.0, 0.5));
			
			// Store the random variable instance probability
			state.logProbability$var163 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$pageFaultsVar = (state.logProbability$pageFaultsVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample164)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample164 = state.fixedFlag$sample164;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$pageFaultsVar = (state.logProbability$pageFaultsVar + state.logProbability$var163);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var163);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample164)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var163);
		}
	}

	// Calculate the probability of the samples represented by sample180 using sampled
	// values.
	private final void logProbabilityValue$sample180() {
		// Determine if we need to calculate the values for sample task 180 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample180) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var177 = state.cpuVar[state.st[i$var174]];
				
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
				double cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample180 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample77) && state.fixedFlag$sample130);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample180[i$var174]);
			
			// Update the variable probability
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample185 using sampled
	// values.
	private final void logProbabilityValue$sample185() {
		// Determine if we need to calculate the values for sample task 185 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample185) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var182 = state.memVar[state.st[i$var174]];
				
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
				double cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample185 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample95) && state.fixedFlag$sample147);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample185[i$var174]);
			
			// Update the variable probability
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var187 = state.pageFaultsVar[state.st[i$var174]];
				
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
				double cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample190 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample113) && state.fixedFlag$sample164);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample190[i$var174]);
			
			// Update the variable probability
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < state.noStates; var29 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var29], state.v, state.noStates));
			
			// Store the random variable instance probability
			state.logProbability$var30 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample30)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample30 = state.fixedFlag$sample30;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample30)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample36) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialStateDistribution, state.v, state.noStates);
			
			// Store the sample task probability
			state.logProbability$initialStateDistribution = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample36)
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
			state.fixedProbFlag$sample36 = state.fixedFlag$sample36;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample36)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.st[0];
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
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
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$var38 = cv$distributionAccumulator;
			
			// Update the variable probability
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
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			
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
			if(state.fixedFlag$sample39)
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
			state.fixedProbFlag$sample39 = (state.fixedFlag$sample39 && state.fixedFlag$sample36);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[i$var50];
				double[] var54 = state.m[state.st[(i$var50 - 1)]];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample57 = ((state.fixedFlag$sample57 && state.fixedFlag$sample30) && state.fixedFlag$sample39);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[(i$var50 - 1)]);
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample77 using sampled
	// values.
	private final void logProbabilityValue$sample77() {
		// Determine if we need to calculate the values for sample task 77 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample77) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var75 = 0; var75 < state.noStates; var75 += 1)
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((state.cpuMean[var75] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			
			// Store the random variable instance probability
			state.logProbability$var76 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$cpuMean = (state.logProbability$cpuMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample77)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample77 = state.fixedFlag$sample77;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$cpuMean = (state.logProbability$cpuMean + state.logProbability$var76);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var76);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample77)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var76);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var93 = 0; var93 < state.noStates; var93 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((state.memMean[var93] - 94.0)));
			
			// Store the random variable instance probability
			state.logProbability$var94 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$memMean = (state.logProbability$memMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample95)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample95 = state.fixedFlag$sample95;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$memMean = (state.logProbability$memMean + state.logProbability$var94);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var94);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample95)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var94);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						state.cpu[i$var174] = ((Math.sqrt(state.cpuVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.cpuMean[state.st[i$var174]]);
						state.mem[i$var174] = ((Math.sqrt(state.memVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.memMean[state.st[i$var174]]);
						state.pageFaults[i$var174] = ((Math.sqrt(state.pageFaultsVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.pageFaultsMean[state.st[i$var174]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample39) {
			for(int index$var37 = 0; index$var37 < state.noStates; index$var37 += 1)
				// Save the probability of each value
				// 
																// cv$distribution$sample39's comment
				// Create local copy of variable probabilities.
				state.distribution$sample39[index$var37] = (((0.0 <= state.initialStateDistribution[index$var37]) && (state.initialStateDistribution[index$var37] <= 1.0))?state.initialStateDistribution[index$var37]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample57 = state.distribution$sample57[(i$var50 - 1)];
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					// Zero the probability of each value
					cv$distribution$sample57[index$var55] = 0.0;
				
				// Iterate through possible values for var55's arguments.
				// 
				// Enumerating the possible arguments for Categorical 55.
				if((1 == i$var50)) {
					// Iterate through possible values for var55's arguments.
					// 
					// Enumerating the possible arguments for Categorical 55.
					if(state.fixedFlag$sample39) {
						int var29 = state.st[0];
						
																		// Substituted "i$var50" with its value "1".
						if(((0 <= var29) && (var29 < state.noStates))) {
							// Substituted "i$var50" with its value "1".
							double[] var54 = state.m[state.st[0]];
							for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
								// Save the probability of each value
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$2 = 0; index$sample39$2 < state.noStates; index$sample39$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value3 = state.distribution$sample39[index$sample39$2];
							double[] var54 = state.m[index$sample39$2];
							for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
								// Save the probability of each value
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var50 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 55.
					for(int index$sample57$10 = 0; index$sample57$10 < state.noStates; index$sample57$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample57Value11 = state.distribution$sample57[(index$i$9 - 1)][index$sample57$10];
						double[] var54 = state.m[index$sample57$10];
						for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
							// Save the probability of each value
							cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					// sum the probability of each value
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					// Normalise the probability of each value
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						state.cpu[i$var174] = ((Math.sqrt(state.cpuVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.cpuMean[state.st[i$var174]]);
						state.mem[i$var174] = ((Math.sqrt(state.memVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.memMean[state.st[i$var174]]);
						state.pageFaults[i$var174] = ((Math.sqrt(state.pageFaultsVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.pageFaultsMean[state.st[i$var174]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample30)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

			if(!state.fixedFlag$sample36)
				inferSample36();
			if(!state.fixedFlag$sample39)
				inferSample39();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample57) {
				for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
					inferSample57(i$var50);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample77)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample113)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample130)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample147)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample164)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample164)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample147)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample130)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample113)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample77)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample57) {
				for(int i$var50 = (state.samples - 1); i$var50 >= 1; i$var50 -= 1)
					inferSample57(i$var50);
			}
			if(!state.fixedFlag$sample39)
				inferSample39();
			if(!state.fixedFlag$sample36)
				inferSample36();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample30)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!state.constrainedFlag$sample30[var29])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample36)
			drawValueSample36();
		if(!state.constrainedFlag$sample39)
			drawValueSample39();
		for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
			if(!state.constrainedFlag$sample57[(i$var50 - 1)])
				drawValueSample57(i$var50);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1) {
						if(!state.constrainedFlag$sample77[var75])
							drawValueSample77(var75, threadID$var75, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1) {
						if(!state.constrainedFlag$sample95[var93])
							drawValueSample95(var93, threadID$var93, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1) {
						if(!state.constrainedFlag$sample113[var111])
							drawValueSample113(var111, threadID$var111, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1) {
						if(!state.constrainedFlag$sample130[var128])
							drawValueSample130(var128, threadID$var128, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1) {
						if(!state.constrainedFlag$sample147[var145])
							drawValueSample147(var145, threadID$var145, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1) {
						if(!state.constrainedFlag$sample164[var162])
							drawValueSample164(var162, threadID$var162, RNG$1);
					}
			}
		);
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
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample30)
			state.logProbability$var30 = Double.NaN;
		if(!state.fixedProbFlag$sample36)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample39)
			state.logProbability$var38 = Double.NaN;
		if(!state.fixedProbFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.logProbability$sample57[(i$var50 - 1)] = Double.NaN;
		}
		state.logProbability$cpuMean = 0.0;
		if(!state.fixedProbFlag$sample77)
			state.logProbability$var76 = Double.NaN;
		state.logProbability$memMean = 0.0;
		if(!state.fixedProbFlag$sample95)
			state.logProbability$var94 = Double.NaN;
		state.logProbability$pageFaultsMean = 0.0;
		if(!state.fixedProbFlag$sample113)
			state.logProbability$var112 = Double.NaN;
		state.logProbability$cpuVar = 0.0;
		if(!state.fixedProbFlag$sample130)
			state.logProbability$var129 = Double.NaN;
		state.logProbability$memVar = 0.0;
		if(!state.fixedProbFlag$sample147)
			state.logProbability$var146 = Double.NaN;
		state.logProbability$pageFaultsVar = 0.0;
		if(!state.fixedProbFlag$sample164)
			state.logProbability$var163 = Double.NaN;
		state.logProbability$cpu = 0.0;
		if(!state.fixedProbFlag$sample180) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample180[i$var174] = Double.NaN;
		}
		state.logProbability$mem = 0.0;
		if(!state.fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample185[i$var174] = Double.NaN;
		}
		state.logProbability$pageFaults = 0.0;
		if(!state.fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample190[i$var174] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var15 = 0; var15 < state.noStates; var15 += 1)
			state.v[var15] = 0.1;
		state.samples = state.length$cpu_measured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < state.constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			state.constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < state.constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			state.constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < state.constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
			state.constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < state.constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
			state.constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < state.constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
			state.constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < state.constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
			state.constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < state.constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
			state.constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(state.fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(state.fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(state.fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(state.fixedFlag$sample113)
			logProbabilityValue$sample113();
		if(state.fixedFlag$sample130)
			logProbabilityValue$sample130();
		if(state.fixedFlag$sample147)
			logProbabilityValue$sample147();
		if(state.fixedFlag$sample164)
			logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample39();
		logProbabilityDistribution$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityDistribution$sample180();
		logProbabilityDistribution$sample185();
		logProbabilityDistribution$sample190();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityValue$sample39();
		logProbabilityValue$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		{
			// Deep copy between arrays
			int cv$length1 = state.cpu.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				state.cpu[cv$index1] = state.cpu_measured[cv$index1];
		}
		{
			// Deep copy between arrays
			int cv$length1 = state.mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				state.mem[cv$index1] = state.mem_measured[cv$index1];
		}
		int cv$length1 = state.pageFaults.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.pageFaults[cv$index1] = state.pageFaults_measured[cv$index1];
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n"
		     + "    \n"
		     + "    // Construct vectors describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    // Determine how many samples the model will need to produce.\n"
		     + "    int samples = cpu_measured.length;\n"
		     + "    \n"
		     + "    // Allocate space for the state.\n"
		     + "    int[] st = new int[samples];\n"
		     + "\n"
		     + "    // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    st[0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "\n"
		     + "    //Determine the remaining states based on the previous state.\n"
		     + "    for(int i:[1 .. samples))\n"
		     + "        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "    //Generate each metric.\n"
		     + "    double[] cpu = new double[samples];\n"
		     + "    double[] mem = new double[samples];\n"
		     + "    double[] pageFaults = new double[samples];\n"
		     + "    \n"
		     + "    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n"
		     + "    double[] memMean = gaussian(94, 1).sample(noStates);\n"
		     + "    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n"
		     + "    \n"
		     + "    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    \n"
		     + "    for(int i:[0 .. samples)) {\n"
		     + "        int s = st[i];\n"
		     + "        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n"
		     + "        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n"
		     + "        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the values we have measured.\n"
		     + "    cpu.observe(cpu_measured);\n"
		     + "    mem.observe(mem_measured);\n"
		     + "    pageFaults.observe(pageFaults_measured);\n"
		     + "}\n"
		     + "";
	}
}