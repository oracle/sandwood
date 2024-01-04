package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck3$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements BuildCheck3$CoreInterface {
	
	// Declare the variables for the model.
	private boolean deploy_action;
	private boolean deploy_action_certainty;
	private boolean deploy_command;
	private boolean deploy_command_certainty;
	private boolean deploy_kws;
	private boolean deploy_kws_certainty;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedFlag$sample99 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean fixedProbFlag$sample89 = false;
	private boolean fixedProbFlag$sample99 = false;
	private boolean invalid_trigger;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$deploy_action_certainty;
	private double logProbability$deploy_command_certainty;
	private double logProbability$deploy_kws_certainty;
	private double logProbability$var35;
	private double logProbability$var36;
	private double logProbability$var37;
	private double logProbability$var38;
	private double logProbability$var40;
	private double logProbability$var41;
	private double logProbability$var43;
	private double logProbability$var44;
	private double logProbability$var65;
	private double logProbability$var66;
	private double logProbability$var67;
	private double logProbability$var68;
	private double logProbability$var70;
	private double logProbability$var71;
	private double logProbability$var78;
	private boolean release_workflow_trigger_deploy_action;
	private boolean release_workflow_trigger_deploy_command;
	private boolean step_uses_secrets_deploy_action;
	private boolean step_uses_secrets_deploy_command;
	private boolean system$gibbsForward = true;
	private boolean tested_deploy_action;
	private boolean var36;
	private boolean var38;
	private boolean var41;
	private boolean var44;
	private boolean var66;
	private boolean var68;
	private boolean var71;

	public BuildCheck3$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for deploy_action.
	@Override
	public final boolean get$deploy_action() {
		return deploy_action;
	}

	// Setter for deploy_action.
	@Override
	public final void set$deploy_action(boolean calculationVariable$value) {
		deploy_action = calculationVariable$value;
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

	// Getter for deploy_command.
	@Override
	public final boolean get$deploy_command() {
		return deploy_command;
	}

	// Setter for deploy_command.
	@Override
	public final void set$deploy_command(boolean calculationVariable$value) {
		deploy_command = calculationVariable$value;
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

	// Getter for deploy_kws.
	@Override
	public final boolean get$deploy_kws() {
		return deploy_kws;
	}

	// Setter for deploy_kws.
	@Override
	public final void set$deploy_kws(boolean calculationVariable$value) {
		deploy_kws = calculationVariable$value;
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

	// Getter for fixedFlag$sample46.
	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	// Setter for fixedFlag$sample46.
	@Override
	public final void set$fixedFlag$sample46(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample46 including if probabilities
		// need to be updated.
		fixedFlag$sample46 = calculationVariable$value;
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample46" with its value "calculationVariable$value".
		fixedProbFlag$sample46 = (calculationVariable$value && fixedProbFlag$sample46);
	}

	// Getter for fixedFlag$sample48.
	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	// Setter for fixedFlag$sample48.
	@Override
	public final void set$fixedFlag$sample48(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample48 including if probabilities
		// need to be updated.
		fixedFlag$sample48 = calculationVariable$value;
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "calculationVariable$value".
		fixedProbFlag$sample48 = (calculationVariable$value && fixedProbFlag$sample48);
	}

	// Getter for fixedFlag$sample51.
	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	// Setter for fixedFlag$sample51.
	@Override
	public final void set$fixedFlag$sample51(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample51 including if probabilities
		// need to be updated.
		fixedFlag$sample51 = calculationVariable$value;
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "calculationVariable$value".
		fixedProbFlag$sample51 = (calculationVariable$value && fixedProbFlag$sample51);
	}

	// Getter for fixedFlag$sample54.
	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	// Setter for fixedFlag$sample54.
	@Override
	public final void set$fixedFlag$sample54(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
		// need to be updated.
		fixedFlag$sample54 = calculationVariable$value;
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "calculationVariable$value".
		fixedProbFlag$sample54 = (calculationVariable$value && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample84.
	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	// Setter for fixedFlag$sample84.
	@Override
	public final void set$fixedFlag$sample84(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
		// need to be updated.
		fixedFlag$sample84 = calculationVariable$value;
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "calculationVariable$value".
		fixedProbFlag$sample84 = (calculationVariable$value && fixedProbFlag$sample84);
	}

	// Getter for fixedFlag$sample86.
	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	// Setter for fixedFlag$sample86.
	@Override
	public final void set$fixedFlag$sample86(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample86 including if probabilities
		// need to be updated.
		fixedFlag$sample86 = calculationVariable$value;
		
		// Should the probability of sample 86 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample86" with its value "calculationVariable$value".
		fixedProbFlag$sample86 = (calculationVariable$value && fixedProbFlag$sample86);
	}

	// Getter for fixedFlag$sample89.
	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	// Setter for fixedFlag$sample89.
	@Override
	public final void set$fixedFlag$sample89(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample89 including if probabilities
		// need to be updated.
		fixedFlag$sample89 = calculationVariable$value;
		
		// Should the probability of sample 89 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample89" with its value "calculationVariable$value".
		fixedProbFlag$sample89 = (calculationVariable$value && fixedProbFlag$sample89);
	}

	// Getter for fixedFlag$sample99.
	@Override
	public final boolean get$fixedFlag$sample99() {
		return fixedFlag$sample99;
	}

	// Setter for fixedFlag$sample99.
	@Override
	public final void set$fixedFlag$sample99(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample99 including if probabilities
		// need to be updated.
		fixedFlag$sample99 = calculationVariable$value;
		
		// Should the probability of sample 99 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample99" with its value "calculationVariable$value".
		fixedProbFlag$sample99 = (calculationVariable$value && fixedProbFlag$sample99);
	}

	// Getter for invalid_trigger.
	@Override
	public final boolean get$invalid_trigger() {
		return invalid_trigger;
	}

	// Setter for invalid_trigger.
	@Override
	public final void set$invalid_trigger(boolean calculationVariable$value) {
		invalid_trigger = calculationVariable$value;
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
		return 0.0;
	}

	// Getter for p_deploy_command.
	@Override
	public final double get$p_deploy_command() {
		return 0.0;
	}

	// Getter for p_deploy_kws.
	@Override
	public final double get$p_deploy_kws() {
		return 0.0;
	}

	// Getter for p_release_workflow_trigger_deploy_action.
	@Override
	public final double get$p_release_workflow_trigger_deploy_action() {
		return 0.0;
	}

	// Getter for p_release_workflow_trigger_deploy_command.
	@Override
	public final double get$p_release_workflow_trigger_deploy_command() {
		return 0.0;
	}

	// Getter for p_step_uses_secrets_deploy_action.
	@Override
	public final double get$p_step_uses_secrets_deploy_action() {
		return 0.0;
	}

	// Getter for p_step_uses_secrets_deploy_command.
	@Override
	public final double get$p_step_uses_secrets_deploy_command() {
		return 0.0;
	}

	// Getter for p_tested_deploy_action.
	@Override
	public final double get$p_tested_deploy_action() {
		return 0.0;
	}

	// Getter for release_workflow_trigger_deploy_action.
	@Override
	public final boolean get$release_workflow_trigger_deploy_action() {
		return release_workflow_trigger_deploy_action;
	}

	// Setter for release_workflow_trigger_deploy_action.
	@Override
	public final void set$release_workflow_trigger_deploy_action(boolean calculationVariable$value) {
		release_workflow_trigger_deploy_action = calculationVariable$value;
	}

	// Getter for release_workflow_trigger_deploy_command.
	@Override
	public final boolean get$release_workflow_trigger_deploy_command() {
		return release_workflow_trigger_deploy_command;
	}

	// Setter for release_workflow_trigger_deploy_command.
	@Override
	public final void set$release_workflow_trigger_deploy_command(boolean calculationVariable$value) {
		release_workflow_trigger_deploy_command = calculationVariable$value;
	}

	// Getter for step_uses_secrets_deploy_action.
	@Override
	public final boolean get$step_uses_secrets_deploy_action() {
		return step_uses_secrets_deploy_action;
	}

	// Setter for step_uses_secrets_deploy_action.
	@Override
	public final void set$step_uses_secrets_deploy_action(boolean calculationVariable$value) {
		step_uses_secrets_deploy_action = calculationVariable$value;
	}

	// Getter for step_uses_secrets_deploy_command.
	@Override
	public final boolean get$step_uses_secrets_deploy_command() {
		return step_uses_secrets_deploy_command;
	}

	// Setter for step_uses_secrets_deploy_command.
	@Override
	public final void set$step_uses_secrets_deploy_command(boolean calculationVariable$value) {
		step_uses_secrets_deploy_command = calculationVariable$value;
	}

	// Getter for tested_deploy_action.
	@Override
	public final boolean get$tested_deploy_action() {
		return tested_deploy_action;
	}

	// Setter for tested_deploy_action.
	@Override
	public final void set$tested_deploy_action(boolean calculationVariable$value) {
		tested_deploy_action = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var36, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var35 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var36 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample46)
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
			fixedProbFlag$sample46 = fixedFlag$sample46;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var35 = logProbability$var36;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var36);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var36);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var38, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var37 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var38 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample48)
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
			fixedProbFlag$sample48 = fixedFlag$sample48;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var37 = logProbability$var38;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var41, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var40 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var41 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample51)
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
			fixedProbFlag$sample51 = fixedFlag$sample51;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var40 = logProbability$var41;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var41);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var41);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var41);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var44, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var43 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var44 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample54)
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
			fixedProbFlag$sample54 = fixedFlag$sample54;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var43 = logProbability$var44;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var44);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var44);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample54)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var66, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var65 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var66 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample84)
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
			fixedProbFlag$sample84 = fixedFlag$sample84;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var65 = logProbability$var66;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var66);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var66);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var66);
		}
	}

	// Calculate the probability of the samples represented by sample86 using sampled
	// values.
	private final void logProbabilityValue$sample86() {
		// Determine if we need to calculate the values for sample task 86 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample86) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var68, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var67 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var68 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample86)
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
			fixedProbFlag$sample86 = fixedFlag$sample86;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var67 = logProbability$var68;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var68);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var68);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample86)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var68);
		}
	}

	// Calculate the probability of the samples represented by sample89 using sampled
	// values.
	private final void logProbabilityValue$sample89() {
		// Determine if we need to calculate the values for sample task 89 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample89) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var71, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var70 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var71 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample89)
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
			fixedProbFlag$sample89 = fixedFlag$sample89;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var70 = logProbability$var71;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var71);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var71);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample89)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var71);
		}
	}

	// Calculate the probability of the samples represented by sample99 using sampled
	// values.
	private final void logProbabilityValue$sample99() {
		// Determine if we need to calculate the values for sample task 99 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample99) {
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
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(deploy_kws_certainty, 0.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var78 = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample99)
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
			fixedProbFlag$sample99 = fixedFlag$sample99;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var78 = logProbability$deploy_kws_certainty;
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$deploy_kws_certainty);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample99)
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
		if(!fixedFlag$sample46)
			var36 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(!fixedFlag$sample48)
			var38 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(!fixedFlag$sample51)
			var41 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(!fixedFlag$sample54)
			var44 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if((((!fixedFlag$sample46 || !fixedFlag$sample48) || !fixedFlag$sample51) || !fixedFlag$sample54))
			deploy_action_certainty = (((var36 || var38) || var41) || var44);
		if(!fixedFlag$sample84)
			var66 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(!fixedFlag$sample86)
			var68 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(!fixedFlag$sample89)
			var71 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
		if(((!fixedFlag$sample84 || !fixedFlag$sample86) || !fixedFlag$sample89))
			deploy_command_certainty = ((var66 || var68) || var71);
		if(!fixedFlag$sample99)
			deploy_kws_certainty = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.0);
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
		logProbability$var35 = 0.0;
		logProbability$deploy_action_certainty = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$var36 = 0.0;
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var38 = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var41 = 0.0;
		logProbability$var43 = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var44 = 0.0;
		logProbability$var65 = 0.0;
		logProbability$deploy_command_certainty = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var66 = 0.0;
		logProbability$var67 = 0.0;
		if(!fixedProbFlag$sample86)
			logProbability$var68 = 0.0;
		logProbability$var70 = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var71 = 0.0;
		logProbability$var78 = 0.0;
		if(!fixedProbFlag$sample99)
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
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		if(fixedFlag$sample86)
			logProbabilityValue$sample86();
		if(fixedFlag$sample89)
			logProbabilityValue$sample89();
		if(fixedFlag$sample99)
			logProbabilityValue$sample99();
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
		logProbabilityValue$sample46();
		logProbabilityValue$sample48();
		logProbabilityValue$sample51();
		logProbabilityValue$sample54();
		logProbabilityValue$sample84();
		logProbabilityValue$sample86();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
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
		logProbabilityValue$sample46();
		logProbabilityValue$sample48();
		logProbabilityValue$sample51();
		logProbabilityValue$sample54();
		logProbabilityValue$sample84();
		logProbabilityValue$sample86();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
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
		deploy_action_certainty = (((var36 || var38) || var41) || var44);
		deploy_command_certainty = ((var66 || var68) || var71);
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck3(boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy_command,\n                         boolean release_workflow_trigger_deploy_action,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy_action,\n                         boolean step_uses_secrets_deploy_command) \n{\n  //Calculate deploy_action_certainty\n  double p_deploy_action = deploy_action ? 0.8*0.9 : 0.0;\n  double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n  double p_release_workflow_trigger_deploy_action = release_workflow_trigger_deploy_action ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_action = step_uses_secrets_deploy_action ? 0.65*0.9 : 0;\n  \n  boolean deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                    bernoulli(p_tested_deploy_action).sample() ||\n                                    bernoulli(p_release_workflow_trigger_deploy_action).sample() ||\n                                    bernoulli(p_step_uses_secrets_deploy_action).sample();\n\n  //Calculate deploy_command_certainty\n  double p_deploy_command = deploy_command ? 0.75*0.8 : 0.0;\n  double p_release_workflow_trigger_deploy_command = release_workflow_trigger_deploy_command ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_command = step_uses_secrets_deploy_command ? 0.65*0.9 : 0.0;\n\n  boolean deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                     bernoulli(p_release_workflow_trigger_deploy_command).sample() ||\n                                     bernoulli(p_step_uses_secrets_deploy_command).sample();\n\n  //Calculate deploy_kws_certainty\n  double p_deploy_kws = deploy_kws ? 0.7*0.4 : 0.0;\n\n  boolean deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n}";
	}
}