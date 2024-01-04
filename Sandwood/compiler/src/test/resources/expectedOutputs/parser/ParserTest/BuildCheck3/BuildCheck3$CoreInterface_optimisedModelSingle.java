package macaron;

interface BuildCheck3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for deploy_action.
	public boolean get$deploy_action();

	// Setter for deploy_action.
	public void set$deploy_action(boolean calculationVariable$value);

	// Getter for deploy_action_certainty.
	public boolean get$deploy_action_certainty();

	// Setter for deploy_action_certainty.
	public void set$deploy_action_certainty(boolean calculationVariable$value);

	// Getter for deploy_command.
	public boolean get$deploy_command();

	// Setter for deploy_command.
	public void set$deploy_command(boolean calculationVariable$value);

	// Getter for deploy_command_certainty.
	public boolean get$deploy_command_certainty();

	// Setter for deploy_command_certainty.
	public void set$deploy_command_certainty(boolean calculationVariable$value);

	// Getter for deploy_kws.
	public boolean get$deploy_kws();

	// Setter for deploy_kws.
	public void set$deploy_kws(boolean calculationVariable$value);

	// Getter for deploy_kws_certainty.
	public boolean get$deploy_kws_certainty();

	// Setter for deploy_kws_certainty.
	public void set$deploy_kws_certainty(boolean calculationVariable$value);

	// Getter for fixedFlag$sample46.
	public boolean get$fixedFlag$sample46();

	// Setter for fixedFlag$sample46.
	public void set$fixedFlag$sample46(boolean calculationVariable$value);

	// Getter for fixedFlag$sample48.
	public boolean get$fixedFlag$sample48();

	// Setter for fixedFlag$sample48.
	public void set$fixedFlag$sample48(boolean calculationVariable$value);

	// Getter for fixedFlag$sample51.
	public boolean get$fixedFlag$sample51();

	// Setter for fixedFlag$sample51.
	public void set$fixedFlag$sample51(boolean calculationVariable$value);

	// Getter for fixedFlag$sample54.
	public boolean get$fixedFlag$sample54();

	// Setter for fixedFlag$sample54.
	public void set$fixedFlag$sample54(boolean calculationVariable$value);

	// Getter for fixedFlag$sample84.
	public boolean get$fixedFlag$sample84();

	// Setter for fixedFlag$sample84.
	public void set$fixedFlag$sample84(boolean calculationVariable$value);

	// Getter for fixedFlag$sample86.
	public boolean get$fixedFlag$sample86();

	// Setter for fixedFlag$sample86.
	public void set$fixedFlag$sample86(boolean calculationVariable$value);

	// Getter for fixedFlag$sample89.
	public boolean get$fixedFlag$sample89();

	// Setter for fixedFlag$sample89.
	public void set$fixedFlag$sample89(boolean calculationVariable$value);

	// Getter for fixedFlag$sample99.
	public boolean get$fixedFlag$sample99();

	// Setter for fixedFlag$sample99.
	public void set$fixedFlag$sample99(boolean calculationVariable$value);

	// Getter for invalid_trigger.
	public boolean get$invalid_trigger();

	// Setter for invalid_trigger.
	public void set$invalid_trigger(boolean calculationVariable$value);

	// Getter for logProbability$deploy_action_certainty.
	public double get$logProbability$deploy_action_certainty();

	// Getter for logProbability$deploy_command_certainty.
	public double get$logProbability$deploy_command_certainty();

	// Getter for logProbability$deploy_kws_certainty.
	public double get$logProbability$deploy_kws_certainty();

	// Getter for p_deploy_action.
	public double get$p_deploy_action();

	// Getter for p_deploy_command.
	public double get$p_deploy_command();

	// Getter for p_deploy_kws.
	public double get$p_deploy_kws();

	// Getter for p_release_workflow_trigger_deploy_action.
	public double get$p_release_workflow_trigger_deploy_action();

	// Getter for p_release_workflow_trigger_deploy_command.
	public double get$p_release_workflow_trigger_deploy_command();

	// Getter for p_step_uses_secrets_deploy_action.
	public double get$p_step_uses_secrets_deploy_action();

	// Getter for p_step_uses_secrets_deploy_command.
	public double get$p_step_uses_secrets_deploy_command();

	// Getter for p_tested_deploy_action.
	public double get$p_tested_deploy_action();

	// Getter for release_workflow_trigger_deploy_action.
	public boolean get$release_workflow_trigger_deploy_action();

	// Setter for release_workflow_trigger_deploy_action.
	public void set$release_workflow_trigger_deploy_action(boolean calculationVariable$value);

	// Getter for release_workflow_trigger_deploy_command.
	public boolean get$release_workflow_trigger_deploy_command();

	// Setter for release_workflow_trigger_deploy_command.
	public void set$release_workflow_trigger_deploy_command(boolean calculationVariable$value);

	// Getter for step_uses_secrets_deploy_action.
	public boolean get$step_uses_secrets_deploy_action();

	// Setter for step_uses_secrets_deploy_action.
	public void set$step_uses_secrets_deploy_action(boolean calculationVariable$value);

	// Getter for step_uses_secrets_deploy_command.
	public boolean get$step_uses_secrets_deploy_command();

	// Setter for step_uses_secrets_deploy_command.
	public void set$step_uses_secrets_deploy_command(boolean calculationVariable$value);

	// Getter for tested_deploy_action.
	public boolean get$tested_deploy_action();

	// Setter for tested_deploy_action.
	public void set$tested_deploy_action(boolean calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}