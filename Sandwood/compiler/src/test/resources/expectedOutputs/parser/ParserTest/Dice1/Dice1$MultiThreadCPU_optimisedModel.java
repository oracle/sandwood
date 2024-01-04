package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice1$CoreInterface {
	
	// Declare the variables for the model.
	private int die1;
	private int die2;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$someSix;
	private double logProbability$twoSix;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var35;
	private double[] p_die1;
	private double[] p_die2;
	private boolean someSix;
	private boolean system$gibbsForward = true;
	private boolean twoSix;

	public Dice1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for die1.
	@Override
	public final int get$die1() {
		return die1;
	}

	// Setter for die1.
	@Override
	public final void set$die1(int calculationVariable$value) {
		die1 = calculationVariable$value;
	}

	// Getter for die2.
	@Override
	public final int get$die2() {
		return die2;
	}

	// Setter for die2.
	@Override
	public final void set$die2(int calculationVariable$value) {
		die2 = calculationVariable$value;
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = calculationVariable$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "calculationVariable$value".
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = calculationVariable$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "calculationVariable$value".
		fixedProbFlag$sample35 = (calculationVariable$value && fixedProbFlag$sample35);
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$die1.
	@Override
	public final double get$logProbability$die1() {
		return logProbability$die1;
	}

	// Getter for logProbability$die2.
	@Override
	public final double get$logProbability$die2() {
		return logProbability$die2;
	}

	// Getter for logProbability$someSix.
	@Override
	public final double get$logProbability$someSix() {
		return logProbability$someSix;
	}

	// Getter for logProbability$twoSix.
	@Override
	public final double get$logProbability$twoSix() {
		return logProbability$twoSix;
	}

	// Getter for p_die1.
	@Override
	public final double[] get$p_die1() {
		return p_die1;
	}

	// Getter for p_die2.
	@Override
	public final double[] get$p_die2() {
		return p_die2;
	}

	// Getter for someSix.
	@Override
	public final boolean get$someSix() {
		return someSix;
	}

	// Setter for someSix.
	@Override
	public final void set$someSix(boolean calculationVariable$value) {
		someSix = calculationVariable$value;
	}

	// Getter for twoSix.
	@Override
	public final boolean get$twoSix() {
		return twoSix;
	}

	// Setter for twoSix.
	@Override
	public final void set$twoSix(boolean calculationVariable$value) {
		twoSix = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$distributionAccumulator moved.
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
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die1 - 1), p_die1);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var30 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var31 = calculationVariable$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$die1 = (logProbability$die1 + calculationVariable$distributionAccumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$twoSix = (logProbability$twoSix + calculationVariable$distributionAccumulator);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$someSix = (logProbability$someSix + calculationVariable$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of calculationVariable$accumulator moved.
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
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var30 = logProbability$var31;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$die1 = (logProbability$die1 + logProbability$var31);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$twoSix = (logProbability$twoSix + logProbability$var31);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$someSix = (logProbability$someSix + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$distributionAccumulator moved.
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
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die2 - 1), p_die2);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var34 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var35 = calculationVariable$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$die2 = (logProbability$die2 + calculationVariable$distributionAccumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$twoSix = (logProbability$twoSix + calculationVariable$distributionAccumulator);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$someSix = (logProbability$someSix + calculationVariable$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of calculationVariable$accumulator moved.
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
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$var35;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$die2 = (logProbability$die2 + logProbability$var35);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$twoSix = (logProbability$twoSix + logProbability$var35);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$someSix = (logProbability$someSix + logProbability$var35);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var35);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for p_die1
		p_die1 = new double[6];
		
		// Constructor for p_die2
		p_die2 = new double[6];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die1) + 1);
		if(!fixedFlag$sample35)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die2) + 1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample31 || !fixedFlag$sample35)) {
			twoSix = ((die1 == 6) && (die2 == 6));
			someSix = ((die1 == 6) || (die2 == 6));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 6, 1,
			(int forStart$var5, int forEnd$var5, int threadID$var5, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var5=forStart$var5; var5<forEnd$var5; var5+=1)
						p_die1[var5] = 0;
			}
		);
		p_die2[0] = 0.15;
		p_die2[1] = 0.15;
		p_die2[2] = 0.15;
		p_die2[3] = 0.15;
		p_die2[4] = 0.15;
		p_die2[5] = 0.25;
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = 0.0;
		logProbability$die1 = 0.0;
		logProbability$twoSix = 0.0;
		logProbability$someSix = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var31 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$die2 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var35 = 0.0;
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		// 
		// Generate sample values for every call to sample in the model.
		logModelProbabilitiesVal();
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		twoSix = ((die1 == 6) && (die2 == 6));
		someSix = ((die1 == 6) || (die2 == 6));
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one1; 1/6::two1; 1/6::three1; 1/6::four1; 1/6::five1; 1/6::six1.\n * 0.15::one2; 0.15::two2; 0.15::three2; 0.15::four2; 0.15::five2; 0.25::six2.\n * \n * % Rules:\n * twoSix :- six1, six2.\n * \n * someSix :- six1.\n * someSix :- six2.\n * \n * % Queries:\n * query(six1).\n * query(six2).\n * query(twoSix).\n * query(someSix).\n */\n\npublic model Dice1() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean twoSix = (die1 == 6) && (die2 == 6);\n    boolean someSix = (die1 == 6) || (die2 == 6);\n}";
	}
}