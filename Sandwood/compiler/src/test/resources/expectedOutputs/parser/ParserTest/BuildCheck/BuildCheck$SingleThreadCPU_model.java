package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements BuildCheck$CoreInterface {
	
	// Declare the variables for the model.
	private boolean deploy_action;
	private boolean deploy_action_certainty;
	private boolean deploy_command;
	private boolean deploy_command_certainty;
	private boolean deploy_kws;
	private boolean deploy_kws_certainty;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample65 = false;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample65 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean fixedProbFlag$sample83 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$deploy_action;
	private double logProbability$deploy_action_certainty;
	private double logProbability$deploy_command;
	private double logProbability$deploy_command_certainty;
	private double logProbability$deploy_kws;
	private double logProbability$deploy_kws_certainty;
	private double logProbability$release_workflow_trigger_deploy_action;
	private double logProbability$release_workflow_trigger_deploy_command;
	private double logProbability$sample21;
	private double logProbability$sample28;
	private double logProbability$sample36;
	private double logProbability$sample44;
	private double logProbability$sample58;
	private double logProbability$sample65;
	private double logProbability$sample73;
	private double logProbability$sample83;
	private double logProbability$step_uses_secrets_deploy_action;
	private double logProbability$step_uses_secrets_deploy_command;
	private double logProbability$tested_deploy_action;
	private double logProbability$var11;
	private double logProbability$var13;
	private double logProbability$var15;
	private double logProbability$var18;
	private double logProbability$var19;
	private double logProbability$var23;
	private double logProbability$var24;
	private double logProbability$var29;
	private double logProbability$var30;
	private double logProbability$var35;
	private double logProbability$var36;
	private double logProbability$var40;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var47;
	private double logProbability$var48;
	private double logProbability$var52;
	private double logProbability$var53;
	private double logProbability$var58;
	private double logProbability$var59;
	private double logProbability$var63;
	private double logProbability$var66;
	private double logProbability$var67;
	private double logProbability$var9;
	private double p_deploy_action;
	private double p_deploy_command;
	private double p_deploy_kws;
	private double p_release_workflow_trigger_deploy_action;
	private double p_release_workflow_trigger_deploy_command;
	private double p_step_uses_secrets_deploy_action;
	private double p_step_uses_secrets_deploy_command;
	private double p_tested_deploy_action;
	private boolean release_workflow_trigger_deploy_action;
	private boolean release_workflow_trigger_deploy_command;
	private boolean step_uses_secrets_deploy_action;
	private boolean step_uses_secrets_deploy_command;
	private boolean system$gibbsForward = true;
	private boolean tested_deploy_action;
	private boolean var19;
	private boolean var24;
	private boolean var30;
	private boolean var36;
	private boolean var48;
	private boolean var53;
	private boolean var59;
	private boolean var67;

	public BuildCheck$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for fixedFlag$sample10.
	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	// Setter for fixedFlag$sample10.
	@Override
	public final void set$fixedFlag$sample10(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample10 including if probabilities
		// need to be updated.
		fixedFlag$sample10 = calculationVariable$value;
		
		// Should the probability of sample 10 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
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
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
	}

	// Getter for fixedFlag$sample14.
	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	// Setter for fixedFlag$sample14.
	@Override
	public final void set$fixedFlag$sample14(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample14 including if probabilities
		// need to be updated.
		fixedFlag$sample14 = calculationVariable$value;
		
		// Should the probability of sample 14 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample14 = (fixedFlag$sample14 && fixedProbFlag$sample14);
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
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
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
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = calculationVariable$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = calculationVariable$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
	}

	// Getter for fixedFlag$sample44.
	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	// Setter for fixedFlag$sample44.
	@Override
	public final void set$fixedFlag$sample44(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample44 including if probabilities
		// need to be updated.
		fixedFlag$sample44 = calculationVariable$value;
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedProbFlag$sample44);
	}

	// Getter for fixedFlag$sample49.
	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	// Setter for fixedFlag$sample49.
	@Override
	public final void set$fixedFlag$sample49(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample49 including if probabilities
		// need to be updated.
		fixedFlag$sample49 = calculationVariable$value;
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
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
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
	}

	// Getter for fixedFlag$sample53.
	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	// Setter for fixedFlag$sample53.
	@Override
	public final void set$fixedFlag$sample53(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample53 including if probabilities
		// need to be updated.
		fixedFlag$sample53 = calculationVariable$value;
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
	}

	// Getter for fixedFlag$sample58.
	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	// Setter for fixedFlag$sample58.
	@Override
	public final void set$fixedFlag$sample58(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
		// need to be updated.
		fixedFlag$sample58 = calculationVariable$value;
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
	}

	// Getter for fixedFlag$sample65.
	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	// Setter for fixedFlag$sample65.
	@Override
	public final void set$fixedFlag$sample65(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample65 including if probabilities
		// need to be updated.
		fixedFlag$sample65 = calculationVariable$value;
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedProbFlag$sample65);
	}

	// Getter for fixedFlag$sample73.
	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	// Setter for fixedFlag$sample73.
	@Override
	public final void set$fixedFlag$sample73(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample73 including if probabilities
		// need to be updated.
		fixedFlag$sample73 = calculationVariable$value;
		
		// Should the probability of sample 73 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = calculationVariable$value;
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
	}

	// Getter for fixedFlag$sample83.
	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	// Setter for fixedFlag$sample83.
	@Override
	public final void set$fixedFlag$sample83(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample83 including if probabilities
		// need to be updated.
		fixedFlag$sample83 = calculationVariable$value;
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample83 = (fixedFlag$sample83 && fixedProbFlag$sample83);
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

	// Getter for logProbability$deploy_action.
	@Override
	public final double get$logProbability$deploy_action() {
		return logProbability$deploy_action;
	}

	// Getter for logProbability$deploy_action_certainty.
	@Override
	public final double get$logProbability$deploy_action_certainty() {
		return logProbability$deploy_action_certainty;
	}

	// Getter for logProbability$deploy_command.
	@Override
	public final double get$logProbability$deploy_command() {
		return logProbability$deploy_command;
	}

	// Getter for logProbability$deploy_command_certainty.
	@Override
	public final double get$logProbability$deploy_command_certainty() {
		return logProbability$deploy_command_certainty;
	}

	// Getter for logProbability$deploy_kws.
	@Override
	public final double get$logProbability$deploy_kws() {
		return logProbability$deploy_kws;
	}

	// Getter for logProbability$deploy_kws_certainty.
	@Override
	public final double get$logProbability$deploy_kws_certainty() {
		return logProbability$deploy_kws_certainty;
	}

	// Getter for logProbability$release_workflow_trigger_deploy_action.
	@Override
	public final double get$logProbability$release_workflow_trigger_deploy_action() {
		return logProbability$release_workflow_trigger_deploy_action;
	}

	// Getter for logProbability$release_workflow_trigger_deploy_command.
	@Override
	public final double get$logProbability$release_workflow_trigger_deploy_command() {
		return logProbability$release_workflow_trigger_deploy_command;
	}

	// Getter for logProbability$step_uses_secrets_deploy_action.
	@Override
	public final double get$logProbability$step_uses_secrets_deploy_action() {
		return logProbability$step_uses_secrets_deploy_action;
	}

	// Getter for logProbability$step_uses_secrets_deploy_command.
	@Override
	public final double get$logProbability$step_uses_secrets_deploy_command() {
		return logProbability$step_uses_secrets_deploy_command;
	}

	// Getter for logProbability$tested_deploy_action.
	@Override
	public final double get$logProbability$tested_deploy_action() {
		return logProbability$tested_deploy_action;
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

	// Calculate the probability of the samples represented by sample10 using sampled
	// values.
	private final void logProbabilityValue$sample10() {
		// Determine if we need to calculate the values for sample task 10 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample10) {
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
				boolean calculationVariable$sampleValue = deploy_action;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_action));
						
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
			logProbability$var9 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$deploy_action = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample10 = fixedFlag$sample10;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var9 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
				boolean calculationVariable$sampleValue = tested_deploy_action;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_tested_deploy_action));
						
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
			logProbability$var11 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$tested_deploy_action = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample12 = fixedFlag$sample12;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$tested_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var11 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample14 using sampled
	// values.
	private final void logProbabilityValue$sample14() {
		// Determine if we need to calculate the values for sample task 14 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample14) {
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
				boolean calculationVariable$sampleValue = release_workflow_trigger_deploy_action;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy_action));
						
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
			logProbability$var13 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$release_workflow_trigger_deploy_action = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample14 = fixedFlag$sample14;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$release_workflow_trigger_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var13 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
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
				boolean calculationVariable$sampleValue = step_uses_secrets_deploy_action;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets_deploy_action));
						
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
			logProbability$var15 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$step_uses_secrets_deploy_action = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample16 = fixedFlag$sample16;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$step_uses_secrets_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var15 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_action_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_action_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(deploy_action) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var19;
					{
						{
							double var17 = 0.8;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var17));
							
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
				logProbability$var18 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample21 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var19 = (logProbability$var19 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_action) {
					boolean traceTempVariable$var21$2_1 = var19;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample21;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var18 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var19 = (logProbability$var19 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_action) {
					boolean traceTempVariable$var21$3_1 = var19;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(tested_deploy_action) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var24;
					{
						{
							double var22 = 0.1;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var22));
							
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
				logProbability$var23 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample28 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var24 = (logProbability$var24 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(tested_deploy_action) {
					boolean traceTempVariable$var26$2_1 = var24;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(tested_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample28;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var23 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var24 = (logProbability$var24 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(tested_deploy_action) {
					boolean traceTempVariable$var26$3_1 = var24;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_action) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var30;
					{
						{
							double var28 = 0.85;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var28));
							
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
				logProbability$var29 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample36 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var30 = (logProbability$var30 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(release_workflow_trigger_deploy_action) {
					boolean traceTempVariable$var32$2_1 = var30;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = fixedFlag$sample36;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample36;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var29 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var30 = (logProbability$var30 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(release_workflow_trigger_deploy_action) {
					boolean traceTempVariable$var32$3_1 = var30;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample44 using sampled
	// values.
	private final void logProbabilityValue$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_action) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var36;
					{
						{
							double var34 = 0.65;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var34));
							
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
				logProbability$var35 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample44 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var36 = (logProbability$var36 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(step_uses_secrets_deploy_action) {
					boolean traceTempVariable$var38$2_1 = var36;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample44 = fixedFlag$sample44;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample44;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var35 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_action_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_action_certainty = false;
			
			// Update the variable probability
			logProbability$var36 = (logProbability$var36 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(step_uses_secrets_deploy_action) {
					boolean traceTempVariable$var38$3_1 = var36;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_action_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_action_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
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
				boolean calculationVariable$sampleValue = deploy_command;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_command));
						
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
			logProbability$var40 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$deploy_command = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = fixedFlag$sample49;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var40 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
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
				boolean calculationVariable$sampleValue = release_workflow_trigger_deploy_command;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy_command));
						
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
			logProbability$var42 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$release_workflow_trigger_deploy_command = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = fixedFlag$sample51;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$release_workflow_trigger_deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var42 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
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
				boolean calculationVariable$sampleValue = step_uses_secrets_deploy_command;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets_deploy_command));
						
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
			logProbability$var44 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$step_uses_secrets_deploy_command = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = fixedFlag$sample53;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$step_uses_secrets_deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var44 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_command_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_command_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(deploy_command) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var48;
					{
						{
							double var46 = 0.75;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var46));
							
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
				logProbability$var47 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample58 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var48 = (logProbability$var48 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_command) {
					boolean traceTempVariable$var50$2_1 = var48;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample58 = fixedFlag$sample58;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample58;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var47 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var48 = (logProbability$var48 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_command) {
					boolean traceTempVariable$var50$3_1 = var48;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_command) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var53;
					{
						{
							double var51 = 0.85;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var51));
							
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
				logProbability$var52 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample65 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var53 = (logProbability$var53 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(release_workflow_trigger_deploy_command) {
					boolean traceTempVariable$var55$2_1 = var53;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample65 = fixedFlag$sample65;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample65;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var52 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var53 = (logProbability$var53 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(release_workflow_trigger_deploy_command) {
					boolean traceTempVariable$var55$3_1 = var53;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample73 using sampled
	// values.
	private final void logProbabilityValue$sample73() {
		// Determine if we need to calculate the values for sample task 73 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample73) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_command) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var59;
					{
						{
							double var57 = 0.65;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var57));
							
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
				logProbability$var58 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample73 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var59 = (logProbability$var59 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(step_uses_secrets_deploy_command) {
					boolean traceTempVariable$var61$2_1 = var59;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample73 = fixedFlag$sample73;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample73;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var58 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_command_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_command_certainty = false;
			
			// Update the variable probability
			logProbability$var59 = (logProbability$var59 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(step_uses_secrets_deploy_command) {
					boolean traceTempVariable$var61$3_1 = var59;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_command_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_command_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
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
				boolean calculationVariable$sampleValue = deploy_kws;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_kws));
						
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
			logProbability$var63 = calculationVariable$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$deploy_kws = calculationVariable$sampleProbability;
			
			// Guard to ensure that deploy_kws_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_kws_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_kws_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample78 = fixedFlag$sample78;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_kws;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var63 = calculationVariable$RVaccumulator;
			
			// Guard to ensure that deploy_kws_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!calculationVariable$guard$deploy_kws_certainty) {
					// Set the guard so the update is only applied once.
					calculationVariable$guard$deploy_kws_certainty = true;
					
					// Update the variable probability
					logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample83 using sampled
	// values.
	private final void logProbabilityValue$sample83() {
		// Determine if we need to calculate the values for sample task 83 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample83) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			if(deploy_kws) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double calculationVariable$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean calculationVariable$sampleValue = var67;
					{
						{
							double var65 = 0.7;
							
							// Store the value of the function call, so the function call is only made once.
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var65));
							
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
				logProbability$var66 = calculationVariable$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample83 = calculationVariable$sampleProbability;
			}
			
			// Guard to ensure that deploy_kws_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			
			// Update the variable probability
			logProbability$var67 = (logProbability$var67 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_kws) {
					boolean traceTempVariable$deploy_kws_certainty$2_1 = var67;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_kws_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_kws_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample83 = fixedFlag$sample83;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			if(deploy_kws) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample83;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var66 = calculationVariable$RVaccumulator;
			}
			
			// Guard to ensure that deploy_kws_certainty is only updated once for this probability.
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			
			// Update the variable probability
			logProbability$var67 = (logProbability$var67 + calculationVariable$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				if(deploy_kws) {
					boolean traceTempVariable$deploy_kws_certainty$3_1 = var67;
					
					// If the probability of the variable has not already been updated
					if(!calculationVariable$guard$deploy_kws_certainty) {
						// Set the guard so the update is only applied once.
						calculationVariable$guard$deploy_kws_certainty = true;
						
						// Update the variable probability
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
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
	public final void allocator() {}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			deploy_action = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_action);
		if(!fixedFlag$sample12)
			tested_deploy_action = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_tested_deploy_action);
		if(!fixedFlag$sample14)
			release_workflow_trigger_deploy_action = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy_action);
		if(!fixedFlag$sample16)
			step_uses_secrets_deploy_action = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets_deploy_action);
		boolean var21 = false;
		if(deploy_action) {
			if(!fixedFlag$sample21)
				var19 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.8);
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var21 = var19;
		} else {
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var21 = false;
		}
		boolean var26 = false;
		if(tested_deploy_action) {
			if(!fixedFlag$sample28)
				var24 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.1);
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var26 = var24;
		} else {
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var26 = false;
		}
		boolean var32 = false;
		if(release_workflow_trigger_deploy_action) {
			if(!fixedFlag$sample36)
				var30 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.85);
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var32 = var30;
		} else {
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var32 = false;
		}
		boolean var38 = false;
		if(step_uses_secrets_deploy_action) {
			if(!fixedFlag$sample44)
				var36 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.65);
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var38 = var36;
		} else {
			if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
				var38 = false;
		}
		if(!(((((((fixedFlag$sample10 && fixedFlag$sample12) && fixedFlag$sample14) && fixedFlag$sample16) && fixedFlag$sample21) && fixedFlag$sample28) && fixedFlag$sample36) && fixedFlag$sample44))
			deploy_action_certainty = (((var21 || var26) || var32) || var38);
		if(!fixedFlag$sample49)
			deploy_command = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_command);
		if(!fixedFlag$sample51)
			release_workflow_trigger_deploy_command = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy_command);
		if(!fixedFlag$sample53)
			step_uses_secrets_deploy_command = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets_deploy_command);
		boolean var50 = false;
		if(deploy_command) {
			if(!fixedFlag$sample58)
				var48 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.75);
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var50 = var48;
		} else {
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var50 = false;
		}
		boolean var55 = false;
		if(release_workflow_trigger_deploy_command) {
			if(!fixedFlag$sample65)
				var53 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.85);
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var55 = var53;
		} else {
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var55 = false;
		}
		boolean var61 = false;
		if(step_uses_secrets_deploy_command) {
			if(!fixedFlag$sample73)
				var59 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.65);
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var61 = var59;
		} else {
			if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
				var61 = false;
		}
		if(!(((((fixedFlag$sample49 && fixedFlag$sample51) && fixedFlag$sample53) && fixedFlag$sample58) && fixedFlag$sample65) && fixedFlag$sample73))
			deploy_command_certainty = ((var50 || var55) || var61);
		if(!fixedFlag$sample78)
			deploy_kws = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_kws);
		if(deploy_kws) {
			if(!fixedFlag$sample83)
				var67 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.7);
			if(!fixedFlag$sample83)
				deploy_kws_certainty = var67;
		} else
			deploy_kws_certainty = false;
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
		logProbability$var9 = 0.0;
		logProbability$deploy_action_certainty = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$deploy_action = 0.0;
		logProbability$var11 = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$tested_deploy_action = 0.0;
		logProbability$var13 = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$release_workflow_trigger_deploy_action = 0.0;
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$step_uses_secrets_deploy_action = 0.0;
		logProbability$var18 = 0.0;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$sample21 = 0.0;
		logProbability$var23 = 0.0;
		logProbability$var24 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$sample28 = 0.0;
		logProbability$var29 = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$sample36 = 0.0;
		logProbability$var35 = 0.0;
		logProbability$var36 = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$sample44 = 0.0;
		logProbability$var40 = 0.0;
		logProbability$deploy_command_certainty = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$deploy_command = 0.0;
		logProbability$var42 = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$release_workflow_trigger_deploy_command = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$step_uses_secrets_deploy_command = 0.0;
		logProbability$var47 = 0.0;
		logProbability$var48 = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$sample58 = 0.0;
		logProbability$var52 = 0.0;
		logProbability$var53 = 0.0;
		if(!fixedProbFlag$sample65)
			logProbability$sample65 = 0.0;
		logProbability$var58 = 0.0;
		logProbability$var59 = 0.0;
		if(!fixedProbFlag$sample73)
			logProbability$sample73 = 0.0;
		logProbability$var63 = 0.0;
		logProbability$deploy_kws_certainty = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$deploy_kws = 0.0;
		logProbability$var66 = 0.0;
		logProbability$var67 = 0.0;
		if(!fixedProbFlag$sample83)
			logProbability$sample83 = 0.0;
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
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		if(fixedFlag$sample49)
			logProbabilityValue$sample49();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample65)
			logProbabilityValue$sample65();
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample36();
		logProbabilityValue$sample44();
		logProbabilityValue$sample49();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityValue$sample58();
		logProbabilityValue$sample65();
		logProbabilityValue$sample73();
		logProbabilityValue$sample78();
		logProbabilityValue$sample83();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample36();
		logProbabilityValue$sample44();
		logProbabilityValue$sample49();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityValue$sample58();
		logProbabilityValue$sample65();
		logProbabilityValue$sample73();
		logProbabilityValue$sample78();
		logProbabilityValue$sample83();
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
		if(true) {
			boolean var21;
			if(deploy_action)
				var21 = var19;
			else
				var21 = false;
			boolean var26;
			if(tested_deploy_action)
				var26 = var24;
			else
				var26 = false;
			boolean var32;
			if(release_workflow_trigger_deploy_action)
				var32 = var30;
			else
				var32 = false;
			boolean var38;
			if(step_uses_secrets_deploy_action)
				var38 = var36;
			else
				var38 = false;
			deploy_action_certainty = (((var21 || var26) || var32) || var38);
		}
		if(true) {
			boolean var50;
			if(deploy_command)
				var50 = var48;
			else
				var50 = false;
			boolean var55;
			if(release_workflow_trigger_deploy_command)
				var55 = var53;
			else
				var55 = false;
			boolean var61;
			if(step_uses_secrets_deploy_command)
				var61 = var59;
			else
				var61 = false;
			deploy_command_certainty = ((var50 || var55) || var61);
		}
		if(true) {
			if(deploy_kws)
				deploy_kws_certainty = var67;
			else
				deploy_kws_certainty = false;
		}
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n \n// All the arguments have either the value 0, or a constant, so it might be better to change \n// them to booleans and place the constants in the model where they could be inferred.\n//\n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\npublic model BuildCheck(double p_deploy_action, \n                        double p_deploy_command, \n                        double p_deploy_kws, \n                        double p_release_workflow_trigger_deploy_command,\n                        double p_release_workflow_trigger_deploy_action,\n                        double p_tested_deploy_action,\n                        double p_step_uses_secrets_deploy_action,\n                        double p_step_uses_secrets_deploy_command) \n{\n  boolean deploy_action = bernoulli(p_deploy_action).sample();\n  boolean tested_deploy_action = bernoulli(p_tested_deploy_action).sample();\n  boolean release_workflow_trigger_deploy_action = bernoulli(p_release_workflow_trigger_deploy_action).sample();\n  boolean step_uses_secrets_deploy_action = bernoulli(p_step_uses_secrets_deploy_action).sample();\n  \n  boolean deploy_action_certainty = (deploy_action?bernoulli(0.8).sample():false) ||\n                                    (tested_deploy_action?bernoulli(0.1).sample():false) ||\n                                    (release_workflow_trigger_deploy_action?bernoulli(0.85).sample():false) ||\n                                    (step_uses_secrets_deploy_action?bernoulli(0.65).sample():false);\n\n\n  boolean deploy_command = bernoulli(p_deploy_command).sample();\n  boolean release_workflow_trigger_deploy_command = bernoulli(p_release_workflow_trigger_deploy_command).sample();\n  boolean step_uses_secrets_deploy_command = bernoulli(p_step_uses_secrets_deploy_command).sample();\n  \n  boolean deploy_command_certainty = (deploy_command?bernoulli(0.75).sample():false) ||\n                                     (release_workflow_trigger_deploy_command?bernoulli(0.85).sample():false) ||\n                                     (step_uses_secrets_deploy_command?bernoulli(0.65).sample():false);\n\n  boolean deploy_kws = bernoulli(p_deploy_kws).sample();\n\n  boolean deploy_kws_certainty = deploy_kws?bernoulli(0.7).sample():false;\n}";
	}
}