package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements BuildCheck2$CoreInterface {
	
	// Declare the variables for the model.
	private boolean deploy_action_certainty;
	private boolean deploy_command_certainty;
	private boolean deploy_kws_certainty;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample40 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample40 = false;
	private boolean fixedProbFlag$sample45 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$deploy_action_certainty;
	private double logProbability$deploy_command_certainty;
	private double logProbability$deploy_kws_certainty;
	private double logProbability$var11;
	private double logProbability$var12;
	private double logProbability$var15;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var21;
	private double logProbability$var25;
	private double logProbability$var26;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var35;
	private double logProbability$var39;
	private double logProbability$var40;
	private double logProbability$var44;
	private double p_deploy_action;
	private double p_deploy_command;
	private double p_deploy_kws;
	private double p_release_workflow_trigger_deploy_action;
	private double p_release_workflow_trigger_deploy_command;
	private double p_step_uses_secrets_deploy_action;
	private double p_step_uses_secrets_deploy_command;
	private double p_tested_deploy_action;
	private boolean system$gibbsForward = true;
	private boolean var12;
	private boolean var16;
	private boolean var21;
	private boolean var26;
	private boolean var31;
	private boolean var35;
	private boolean var40;

	public BuildCheck2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for deploy_action_certainty.
	@Override
	public final boolean get$deploy_action_certainty() {
		return deploy_action_certainty;
	}

	// Setter for deploy_action_certainty.
	@Override
	public final void set$deploy_action_certainty(boolean calculationVariable$value) {
		deploy_action_certainty = calculationVariable$value;
	}

	// Getter for deploy_command_certainty.
	@Override
	public final boolean get$deploy_command_certainty() {
		return deploy_command_certainty;
	}

	// Setter for deploy_command_certainty.
	@Override
	public final void set$deploy_command_certainty(boolean calculationVariable$value) {
		deploy_command_certainty = calculationVariable$value;
	}

	// Getter for deploy_kws_certainty.
	@Override
	public final boolean get$deploy_kws_certainty() {
		return deploy_kws_certainty;
	}

	// Setter for deploy_kws_certainty.
	@Override
	public final void set$deploy_kws_certainty(boolean calculationVariable$value) {
		deploy_kws_certainty = calculationVariable$value;
	}

	// Getter for fixedFlag$sample12.
	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	// Setter for fixedFlag$sample12.
	@Override
	public final void set$fixedFlag$sample12(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
		// need to be updated.
		fixedFlag$sample12 = calculationVariable$value;
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "calculationVariable$value".
		fixedProbFlag$sample12 = (calculationVariable$value && fixedProbFlag$sample12);
	}

	// Getter for fixedFlag$sample16.
	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	// Setter for fixedFlag$sample16.
	@Override
	public final void set$fixedFlag$sample16(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
		// need to be updated.
		fixedFlag$sample16 = calculationVariable$value;
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "calculationVariable$value".
		fixedProbFlag$sample16 = (calculationVariable$value && fixedProbFlag$sample16);
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = calculationVariable$value;
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample21" with its value "calculationVariable$value".
		fixedProbFlag$sample21 = (calculationVariable$value && fixedProbFlag$sample21);
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = calculationVariable$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "calculationVariable$value".
		fixedProbFlag$sample26 = (calculationVariable$value && fixedProbFlag$sample26);
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

	// Getter for fixedFlag$sample40.
	@Override
	public final boolean get$fixedFlag$sample40() {
		return fixedFlag$sample40;
	}

	// Setter for fixedFlag$sample40.
	@Override
	public final void set$fixedFlag$sample40(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample40 including if probabilities
		// need to be updated.
		fixedFlag$sample40 = calculationVariable$value;
		
		// Should the probability of sample 40 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample40" with its value "calculationVariable$value".
		fixedProbFlag$sample40 = (calculationVariable$value && fixedProbFlag$sample40);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = calculationVariable$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "calculationVariable$value".
		fixedProbFlag$sample45 = (calculationVariable$value && fixedProbFlag$sample45);
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

	// Getter for logProbability$deploy_action_certainty.
	@Override
	public final double get$logProbability$deploy_action_certainty() {
		return logProbability$deploy_action_certainty;
	}

	// Getter for logProbability$deploy_command_certainty.
	@Override
	public final double get$logProbability$deploy_command_certainty() {
		return logProbability$deploy_command_certainty;
	}

	// Getter for logProbability$deploy_kws_certainty.
	@Override
	public final double get$logProbability$deploy_kws_certainty() {
		return logProbability$deploy_kws_certainty;
	}

	// Getter for p_deploy_action.
	@Override
	public final double get$p_deploy_action() {
		return p_deploy_action;
	}

	// Setter for p_deploy_action.
	@Override
	public final void set$p_deploy_action(double calculationVariable$value) {
		p_deploy_action = calculationVariable$value;
	}

	// Getter for p_deploy_command.
	@Override
	public final double get$p_deploy_command() {
		return p_deploy_command;
	}

	// Setter for p_deploy_command.
	@Override
	public final void set$p_deploy_command(double calculationVariable$value) {
		p_deploy_command = calculationVariable$value;
	}

	// Getter for p_deploy_kws.
	@Override
	public final double get$p_deploy_kws() {
		return p_deploy_kws;
	}

	// Setter for p_deploy_kws.
	@Override
	public final void set$p_deploy_kws(double calculationVariable$value) {
		p_deploy_kws = calculationVariable$value;
	}

	// Getter for p_release_workflow_trigger_deploy_action.
	@Override
	public final double get$p_release_workflow_trigger_deploy_action() {
		return p_release_workflow_trigger_deploy_action;
	}

	// Setter for p_release_workflow_trigger_deploy_action.
	@Override
	public final void set$p_release_workflow_trigger_deploy_action(double calculationVariable$value) {
		p_release_workflow_trigger_deploy_action = calculationVariable$value;
	}

	// Getter for p_release_workflow_trigger_deploy_command.
	@Override
	public final double get$p_release_workflow_trigger_deploy_command() {
		return p_release_workflow_trigger_deploy_command;
	}

	// Setter for p_release_workflow_trigger_deploy_command.
	@Override
	public final void set$p_release_workflow_trigger_deploy_command(double calculationVariable$value) {
		p_release_workflow_trigger_deploy_command = calculationVariable$value;
	}

	// Getter for p_step_uses_secrets_deploy_action.
	@Override
	public final double get$p_step_uses_secrets_deploy_action() {
		return p_step_uses_secrets_deploy_action;
	}

	// Setter for p_step_uses_secrets_deploy_action.
	@Override
	public final void set$p_step_uses_secrets_deploy_action(double calculationVariable$value) {
		p_step_uses_secrets_deploy_action = calculationVariable$value;
	}

	// Getter for p_step_uses_secrets_deploy_command.
	@Override
	public final double get$p_step_uses_secrets_deploy_command() {
		return p_step_uses_secrets_deploy_command;
	}

	// Setter for p_step_uses_secrets_deploy_command.
	@Override
	public final void set$p_step_uses_secrets_deploy_command(double calculationVariable$value) {
		p_step_uses_secrets_deploy_command = calculationVariable$value;
	}

	// Getter for p_tested_deploy_action.
	@Override
	public final double get$p_tested_deploy_action() {
		return p_tested_deploy_action;
	}

	// Setter for p_tested_deploy_action.
	@Override
	public final void set$p_tested_deploy_action(double calculationVariable$value) {
		p_tested_deploy_action = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var12, (p_deploy_action * 0.8));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var11 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var12 = calculationVariable$distributionAccumulator;
			
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
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample12)
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
			fixedProbFlag$sample12 = fixedFlag$sample12;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var11 = logProbability$var12;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var12);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var12);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var12);
		}
	}

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var16, (p_tested_deploy_action * 0.1));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var15 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var16 = calculationVariable$distributionAccumulator;
			
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
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample16)
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
			fixedProbFlag$sample16 = fixedFlag$sample16;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var15 = logProbability$var16;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var16);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var16);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var21, (p_release_workflow_trigger_deploy_action * 0.85));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var20 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var21 = calculationVariable$distributionAccumulator;
			
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
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample21)
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
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$var21;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var21);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var21);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var21);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var26, (p_step_uses_secrets_deploy_action * 0.65));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var25 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var26 = calculationVariable$distributionAccumulator;
			
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
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample26)
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
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var25 = logProbability$var26;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var26);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var26);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var31, (p_deploy_command * 0.75));
			
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
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			
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
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var31);
			
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var35, (p_release_workflow_trigger_deploy_command * 0.85));
			
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
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			
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
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var35);
			
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

	// Calculate the probability of the samples represented by sample40 using sampled
	// values.
	private final void logProbabilityValue$sample40() {
		// Determine if we need to calculate the values for sample task 40 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample40) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var40, (p_step_uses_secrets_deploy_command * 0.65));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var39 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var40 = calculationVariable$distributionAccumulator;
			
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
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			
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
			if(fixedFlag$sample40)
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
			fixedProbFlag$sample40 = fixedFlag$sample40;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$var40;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample40)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(deploy_kws_certainty, (p_deploy_kws * 0.7));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var44 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$deploy_kws_certainty = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample45)
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
			fixedProbFlag$sample45 = fixedFlag$sample45;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var44 = logProbability$deploy_kws_certainty;
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$deploy_kws_certainty);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$deploy_kws_certainty);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			var12 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_deploy_action * 0.8));
		if(!fixedFlag$sample16)
			var16 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_tested_deploy_action * 0.1));
		if(!fixedFlag$sample21)
			var21 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_release_workflow_trigger_deploy_action * 0.85));
		if(!fixedFlag$sample26)
			var26 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_step_uses_secrets_deploy_action * 0.65));
		if((((!fixedFlag$sample12 || !fixedFlag$sample16) || !fixedFlag$sample21) || !fixedFlag$sample26))
			deploy_action_certainty = (((var12 || var16) || var21) || var26);
		if(!fixedFlag$sample31)
			var31 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_deploy_command * 0.75));
		if(!fixedFlag$sample35)
			var35 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_release_workflow_trigger_deploy_command * 0.85));
		if(!fixedFlag$sample40)
			var40 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_step_uses_secrets_deploy_command * 0.65));
		if(((!fixedFlag$sample31 || !fixedFlag$sample35) || !fixedFlag$sample40))
			deploy_command_certainty = ((var31 || var35) || var40);
		if(!fixedFlag$sample45)
			deploy_kws_certainty = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (p_deploy_kws * 0.7));
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
	public final void initializeConstants() {}

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
		logProbability$var11 = 0.0;
		logProbability$deploy_action_certainty = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$var12 = 0.0;
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$var21 = 0.0;
		logProbability$var25 = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var26 = 0.0;
		logProbability$var30 = 0.0;
		logProbability$deploy_command_certainty = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var31 = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var35 = 0.0;
		logProbability$var39 = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var40 = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$deploy_kws_certainty = 0.0;
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
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample40)
			logProbabilityValue$sample40();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample26();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample40();
		logProbabilityValue$sample45();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample26();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample40();
		logProbabilityValue$sample45();
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
		deploy_action_certainty = (((var12 || var16) || var21) || var26);
		deploy_command_certainty = ((var31 || var35) || var40);
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n \n// All the arguments have either the value 0, or a constant, so it might be better to change \n// them to booleans and place the constants in the model where they could be inferred.\n//\n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n//\n// Simplified version of the first model.\npublic model BuildCheck2(double p_deploy_action, \n                         double p_deploy_command, \n                         double p_deploy_kws, \n                         double p_release_workflow_trigger_deploy_command,\n                         double p_release_workflow_trigger_deploy_action,\n                         double p_tested_deploy_action,\n                         double p_step_uses_secrets_deploy_action,\n                         double p_step_uses_secrets_deploy_command) \n{\n  boolean deploy_action_certainty = bernoulli(0.8 * p_deploy_action).sample() ||\n                                    bernoulli(0.1 * p_tested_deploy_action).sample() ||\n                                    bernoulli(0.85 * p_release_workflow_trigger_deploy_action).sample() ||\n                                    bernoulli(0.65 * p_step_uses_secrets_deploy_action).sample();\n\n  boolean deploy_command_certainty = bernoulli(0.75 * p_deploy_command).sample() ||\n                                     bernoulli(0.85 * p_release_workflow_trigger_deploy_command).sample() ||\n                                     bernoulli(0.65 * p_step_uses_secrets_deploy_command).sample();\n\n  boolean deploy_kws_certainty = bernoulli(0.7 * p_deploy_kws).sample();\n}";
	}
}