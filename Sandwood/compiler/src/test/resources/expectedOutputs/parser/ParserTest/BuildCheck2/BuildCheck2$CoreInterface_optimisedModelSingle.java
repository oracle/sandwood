package macaron;

interface BuildCheck2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for deploy_action_certainty.
	public boolean get$deploy_action_certainty();

	// Setter for deploy_action_certainty.
	public void set$deploy_action_certainty(boolean calculationVariable$value);

	// Getter for deploy_command_certainty.
	public boolean get$deploy_command_certainty();

	// Setter for deploy_command_certainty.
	public void set$deploy_command_certainty(boolean calculationVariable$value);

	// Getter for deploy_kws_certainty.
	public boolean get$deploy_kws_certainty();

	// Setter for deploy_kws_certainty.
	public void set$deploy_kws_certainty(boolean calculationVariable$value);

	// Getter for fixedFlag$sample12.
	public boolean get$fixedFlag$sample12();

	// Setter for fixedFlag$sample12.
	public void set$fixedFlag$sample12(boolean calculationVariable$value);

	// Getter for fixedFlag$sample16.
	public boolean get$fixedFlag$sample16();

	// Setter for fixedFlag$sample16.
	public void set$fixedFlag$sample16(boolean calculationVariable$value);

	// Getter for fixedFlag$sample21.
	public boolean get$fixedFlag$sample21();

	// Setter for fixedFlag$sample21.
	public void set$fixedFlag$sample21(boolean calculationVariable$value);

	// Getter for fixedFlag$sample26.
	public boolean get$fixedFlag$sample26();

	// Setter for fixedFlag$sample26.
	public void set$fixedFlag$sample26(boolean calculationVariable$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean calculationVariable$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean calculationVariable$value);

	// Getter for fixedFlag$sample40.
	public boolean get$fixedFlag$sample40();

	// Setter for fixedFlag$sample40.
	public void set$fixedFlag$sample40(boolean calculationVariable$value);

	// Getter for fixedFlag$sample45.
	public boolean get$fixedFlag$sample45();

	// Setter for fixedFlag$sample45.
	public void set$fixedFlag$sample45(boolean calculationVariable$value);

	// Getter for logProbability$deploy_action_certainty.
	public double get$logProbability$deploy_action_certainty();

	// Getter for logProbability$deploy_command_certainty.
	public double get$logProbability$deploy_command_certainty();

	// Getter for logProbability$deploy_kws_certainty.
	public double get$logProbability$deploy_kws_certainty();

	// Getter for p_deploy_action.
	public double get$p_deploy_action();

	// Setter for p_deploy_action.
	public void set$p_deploy_action(double calculationVariable$value);

	// Getter for p_deploy_command.
	public double get$p_deploy_command();

	// Setter for p_deploy_command.
	public void set$p_deploy_command(double calculationVariable$value);

	// Getter for p_deploy_kws.
	public double get$p_deploy_kws();

	// Setter for p_deploy_kws.
	public void set$p_deploy_kws(double calculationVariable$value);

	// Getter for p_release_workflow_trigger_deploy_action.
	public double get$p_release_workflow_trigger_deploy_action();

	// Setter for p_release_workflow_trigger_deploy_action.
	public void set$p_release_workflow_trigger_deploy_action(double calculationVariable$value);

	// Getter for p_release_workflow_trigger_deploy_command.
	public double get$p_release_workflow_trigger_deploy_command();

	// Setter for p_release_workflow_trigger_deploy_command.
	public void set$p_release_workflow_trigger_deploy_command(double calculationVariable$value);

	// Getter for p_step_uses_secrets_deploy_action.
	public double get$p_step_uses_secrets_deploy_action();

	// Setter for p_step_uses_secrets_deploy_action.
	public void set$p_step_uses_secrets_deploy_action(double calculationVariable$value);

	// Getter for p_step_uses_secrets_deploy_command.
	public double get$p_step_uses_secrets_deploy_command();

	// Setter for p_step_uses_secrets_deploy_command.
	public void set$p_step_uses_secrets_deploy_command(double calculationVariable$value);

	// Getter for p_tested_deploy_action.
	public double get$p_tested_deploy_action();

	// Setter for p_tested_deploy_action.
	public void set$p_tested_deploy_action(double calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}