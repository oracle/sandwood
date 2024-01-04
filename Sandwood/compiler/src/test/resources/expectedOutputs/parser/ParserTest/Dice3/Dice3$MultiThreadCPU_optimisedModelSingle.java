package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice3$CoreInterface {
	
	// Declare the variables for the model.
	private int die1;
	private int die2;
	private boolean even;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample15 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$even;
	private double logProbability$odd;
	private double logProbability$sum;
	private double logProbability$var10;
	private double logProbability$var11;
	private double logProbability$var14;
	private double logProbability$var15;
	private boolean odd;
	private double[] p_die;
	private int sum;
	private boolean system$gibbsForward = true;

	public Dice3$MultiThreadCPU(ExecutionTarget target) {
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

	// Getter for even.
	@Override
	public final boolean get$even() {
		return even;
	}

	// Setter for even.
	@Override
	public final void set$even(boolean calculationVariable$value) {
		even = calculationVariable$value;
	}

	// Getter for fixedFlag$sample11.
	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	// Setter for fixedFlag$sample11.
	@Override
	public final void set$fixedFlag$sample11(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
		// need to be updated.
		fixedFlag$sample11 = calculationVariable$value;
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "calculationVariable$value".
		fixedProbFlag$sample11 = (calculationVariable$value && fixedProbFlag$sample11);
	}

	// Getter for fixedFlag$sample15.
	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	// Setter for fixedFlag$sample15.
	@Override
	public final void set$fixedFlag$sample15(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample15 including if probabilities
		// need to be updated.
		fixedFlag$sample15 = calculationVariable$value;
		
		// Should the probability of sample 15 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample15" with its value "calculationVariable$value".
		fixedProbFlag$sample15 = (calculationVariable$value && fixedProbFlag$sample15);
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

	// Getter for logProbability$even.
	@Override
	public final double get$logProbability$even() {
		return logProbability$even;
	}

	// Getter for logProbability$odd.
	@Override
	public final double get$logProbability$odd() {
		return logProbability$odd;
	}

	// Getter for logProbability$sum.
	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	// Getter for odd.
	@Override
	public final boolean get$odd() {
		return odd;
	}

	// Setter for odd.
	@Override
	public final void set$odd(boolean calculationVariable$value) {
		odd = calculationVariable$value;
	}

	// Getter for p_die.
	@Override
	public final double[] get$p_die() {
		return p_die;
	}

	// Getter for sum.
	@Override
	public final int get$sum() {
		return sum;
	}

	// Setter for sum.
	@Override
	public final void set$sum(int calculationVariable$value) {
		sum = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die1 - 1), p_die);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var10 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var11 = calculationVariable$distributionAccumulator;
			
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
			logProbability$sum = (logProbability$sum + calculationVariable$distributionAccumulator);
			
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
			logProbability$even = (logProbability$even + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample11)
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
			fixedProbFlag$sample11 = fixedFlag$sample11;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var10 = logProbability$var11;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$die1 = (logProbability$die1 + logProbability$var11);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$sum = (logProbability$sum + logProbability$var11);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$even = (logProbability$even + logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample15 using sampled
	// values.
	private final void logProbabilityValue$sample15() {
		// Determine if we need to calculate the values for sample task 15 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample15) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die2 - 1), p_die);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var14 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var15 = calculationVariable$distributionAccumulator;
			
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
			logProbability$sum = (logProbability$sum + calculationVariable$distributionAccumulator);
			
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
			logProbability$odd = (logProbability$odd + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample15)
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
			fixedProbFlag$sample15 = fixedFlag$sample15;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$var15;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$die2 = (logProbability$die2 + logProbability$var15);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$sum = (logProbability$sum + logProbability$var15);
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$odd = (logProbability$odd + logProbability$var15);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var15);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample15)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
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
		// Constructor for p_die
		p_die = new double[6];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die) + 1);
		if(!fixedFlag$sample15)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die) + 1);
		if((!fixedFlag$sample11 || !fixedFlag$sample15))
			sum = (die1 + die2);
		if(!fixedFlag$sample11)
			even = !((die1 % 2) == 1);
		if(!fixedFlag$sample15)
			odd = ((die2 % 2) == 1);
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
						p_die[var5] = 0;
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
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$sum = 0.0;
		logProbability$even = 0.0;
		logProbability$die1 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$die2 = 0.0;
		logProbability$odd = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$var15 = 0.0;
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
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
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
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
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
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
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
		sum = (die1 + die2);
		even = !((die1 % 2) == 1);
		odd = ((die2 % 2) == 1);
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * 1/6::dice(1,D); 1/6::dice(2,D); 1/6::dice(3,D); 1/6::dice(4,D); 1/6::dice(5,D); 1/6::dice(6,D) :- die(D).\n * \n * die(1).\n * die(2).\n * \n * sum(S) :- dice(A,1), dice(B,2), S is A+B.\n * odd(D) :- dice(1,D).\n * odd(D) :- dice(3,D).\n * odd(D) :- dice(5,D).\n * even(D) :- \\+ odd(D).\n * \n * query(sum(_)).\n * evidence(even(1)).\n * evidence(odd(2)).\n */\n\npublic model Dice3() {\n    double[] p_die = new double[6] <~ 1/6;\n    \n    int die1 = categorical(p_die).sample() + 1;\n    int die2 = categorical(p_die).sample() + 1;\n    \n    int sum = die1 + die2;\n    boolean even = even(die1);\n    boolean odd = odd(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
	}
}