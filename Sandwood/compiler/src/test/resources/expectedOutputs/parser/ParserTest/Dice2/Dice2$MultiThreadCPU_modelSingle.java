package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice2$CoreInterface {
	
	// Declare the variables for the model.
	private int die1;
	private int die2;
	private boolean even1;
	private boolean even2;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$even1;
	private double logProbability$even2;
	private double logProbability$odd1;
	private double logProbability$odd2;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var35;
	private boolean odd1;
	private boolean odd2;
	private double[] p_die1;
	private double[] p_die2;
	private boolean system$gibbsForward = true;

	public Dice2$MultiThreadCPU(ExecutionTarget target) {
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

	// Getter for even1.
	@Override
	public final boolean get$even1() {
		return even1;
	}

	// Setter for even1.
	@Override
	public final void set$even1(boolean calculationVariable$value) {
		even1 = calculationVariable$value;
	}

	// Getter for even2.
	@Override
	public final boolean get$even2() {
		return even2;
	}

	// Setter for even2.
	@Override
	public final void set$even2(boolean calculationVariable$value) {
		even2 = calculationVariable$value;
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
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
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
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
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

	// Getter for logProbability$even1.
	@Override
	public final double get$logProbability$even1() {
		return logProbability$even1;
	}

	// Getter for logProbability$even2.
	@Override
	public final double get$logProbability$even2() {
		return logProbability$even2;
	}

	// Getter for logProbability$odd1.
	@Override
	public final double get$logProbability$odd1() {
		return logProbability$odd1;
	}

	// Getter for logProbability$odd2.
	@Override
	public final double get$logProbability$odd2() {
		return logProbability$odd2;
	}

	// Getter for odd1.
	@Override
	public final boolean get$odd1() {
		return odd1;
	}

	// Setter for odd1.
	@Override
	public final void set$odd1(boolean calculationVariable$value) {
		odd1 = calculationVariable$value;
	}

	// Getter for odd2.
	@Override
	public final boolean get$odd2() {
		return odd2;
	}

	// Setter for odd2.
	@Override
	public final void set$odd2(boolean calculationVariable$value) {
		odd2 = calculationVariable$value;
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

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double calculationVariable$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double calculationVariable$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				int calculationVariable$sampleValue = (die1 - 1);
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die1));
						
						// Add the probability of this sample task to the distribution accumulator.
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							// If the second value is -infinity.
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						
						// Add the probability of this distribution configuration to the accumulator.
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var30 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var31 = calculationVariable$sampleProbability;
			
			// Guard to ensure that odd1 is only updated once for this probability.
			boolean calculationVariable$guard$odd1 = false;
			
			// Guard to ensure that even1 is only updated once for this probability.
			boolean calculationVariable$guard$even1 = false;
			
			// Update the variable probability
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$odd1) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$odd1 = true;
					
					// Update the variable probability
					logProbability$odd1 = (logProbability$odd1 + calculationVariable$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$even1) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$even1 = true;
					
					// Update the variable probability
					logProbability$even1 = (logProbability$even1 + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var31;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var30 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that odd1 is only updated once for this probability.
			boolean calculationVariable$guard$odd1 = false;
			
			// Guard to ensure that even1 is only updated once for this probability.
			boolean calculationVariable$guard$even1 = false;
			
			// Update the variable probability
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$odd1) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$odd1 = true;
					
					// Update the variable probability
					logProbability$odd1 = (logProbability$odd1 + calculationVariable$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$even1) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$even1 = true;
					
					// Update the variable probability
					logProbability$even1 = (logProbability$even1 + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double calculationVariable$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double calculationVariable$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				int calculationVariable$sampleValue = (die2 - 1);
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die2));
						
						// Add the probability of this sample task to the distribution accumulator.
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							// If the second value is -infinity.
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						
						// Add the probability of this distribution configuration to the accumulator.
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var34 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var35 = calculationVariable$sampleProbability;
			
			// Guard to ensure that odd2 is only updated once for this probability.
			boolean calculationVariable$guard$odd2 = false;
			
			// Guard to ensure that even2 is only updated once for this probability.
			boolean calculationVariable$guard$even2 = false;
			
			// Update the variable probability
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$odd2) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$odd2 = true;
					
					// Update the variable probability
					logProbability$odd2 = (logProbability$odd2 + calculationVariable$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$even2) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$even2 = true;
					
					// Update the variable probability
					logProbability$even2 = (logProbability$even2 + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var35;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var34 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that odd2 is only updated once for this probability.
			boolean calculationVariable$guard$odd2 = false;
			
			// Guard to ensure that even2 is only updated once for this probability.
			boolean calculationVariable$guard$even2 = false;
			
			// Update the variable probability
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$odd2) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$odd2 = true;
					
					// Update the variable probability
					logProbability$odd2 = (logProbability$odd2 + calculationVariable$accumulator);
				}
			}
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$even2) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$even2 = true;
					
					// Update the variable probability
					logProbability$even2 = (logProbability$even2 + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
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
		{
			p_die1 = new double[6];
		}
		
		// Constructor for p_die2
		{
			p_die2 = new double[6];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die1) + 1);
		if(!fixedFlag$sample35)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die2) + 1);
		if(!fixedFlag$sample31)
			odd1 = ((die1 % 2) == 1);
		if(!fixedFlag$sample31)
			even1 = !((die1 % 2) == 1);
		if(!fixedFlag$sample35)
			odd2 = ((die2 % 2) == 1);
		if(!fixedFlag$sample35)
			even2 = !((die2 % 2) == 1);
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
						p_die1[var5] = (1 / 6);
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
		logProbability$odd1 = 0.0;
		logProbability$even1 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var31 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$odd2 = 0.0;
		logProbability$die2 = 0.0;
		logProbability$even2 = 0.0;
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
		if(true)
			odd1 = ((die1 % 2) == 1);
		if(true)
			even1 = !((die1 % 2) == 1);
		if(true)
			odd2 = ((die2 % 2) == 1);
		if(true)
			even2 = !((die2 % 2) == 1);
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one(1); 1/6::two(1); 1/6::three(1); 1/6::four(1); 1/6::five(1); 1/6::six(1).\n * 0.15::one(2); 0.15::two(2); 0.15::three(2); 0.15::four(2); 0.15::five(2); 0.25::six(2).\n * \n * % Rules:\n * odd(X) :- one(X).\n * odd(X) :- three(X).\n * odd(X) :- five(X).\n * even(X) :- \\+ odd(X).\n * \n * query(odd(1)).\n * query(even(1)).\n * query(odd(2)).\n * query(even(2)).\n */\n\npublic model Dice2() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean odd1 = odd(die1);\n    boolean even1 = even(die1);\n    boolean odd2 = odd(die2);\n    boolean even2 = even(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
	}
}