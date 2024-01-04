package macaron;

interface BuildCheck4$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for ci_parsed.
	public boolean get$ci_parsed();

	// Setter for ci_parsed.
	public void set$ci_parsed(boolean calculationVariable$value);

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

	// Getter for fixedFlag$sample104.
	public boolean get$fixedFlag$sample104();

	// Setter for fixedFlag$sample104.
	public void set$fixedFlag$sample104(boolean calculationVariable$value);

	// Getter for fixedFlag$sample44.
	public boolean get$fixedFlag$sample44();

	// Setter for fixedFlag$sample44.
	public void set$fixedFlag$sample44(boolean calculationVariable$value);

	// Getter for fixedFlag$sample46.
	public boolean get$fixedFlag$sample46();

	// Setter for fixedFlag$sample46.
	public void set$fixedFlag$sample46(boolean calculationVariable$value);

	// Getter for fixedFlag$sample49.
	public boolean get$fixedFlag$sample49();

	// Setter for fixedFlag$sample49.
	public void set$fixedFlag$sample49(boolean calculationVariable$value);

	// Getter for fixedFlag$sample52.
	public boolean get$fixedFlag$sample52();

	// Setter for fixedFlag$sample52.
	public void set$fixedFlag$sample52(boolean calculationVariable$value);

	// Getter for fixedFlag$sample86.
	public boolean get$fixedFlag$sample86();

	// Setter for fixedFlag$sample86.
	public void set$fixedFlag$sample86(boolean calculationVariable$value);

	// Getter for fixedFlag$sample88.
	public boolean get$fixedFlag$sample88();

	// Setter for fixedFlag$sample88.
	public void set$fixedFlag$sample88(boolean calculationVariable$value);

	// Getter for fixedFlag$sample91.
	public boolean get$fixedFlag$sample91();

	// Setter for fixedFlag$sample91.
	public void set$fixedFlag$sample91(boolean calculationVariable$value);

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

	// Getter for release_workflow_trigger_deploy.
	public boolean get$release_workflow_trigger_deploy();

	// Setter for release_workflow_trigger_deploy.
	public void set$release_workflow_trigger_deploy(boolean calculationVariable$value);

	// Getter for step_uses_secrets_deploy.
	public boolean get$step_uses_secrets_deploy();

	// Setter for step_uses_secrets_deploy.
	public void set$step_uses_secrets_deploy(boolean calculationVariable$value);

	// Getter for tested_deploy_action.
	public boolean get$tested_deploy_action();

	// Setter for tested_deploy_action.
	public void set$tested_deploy_action(boolean calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}