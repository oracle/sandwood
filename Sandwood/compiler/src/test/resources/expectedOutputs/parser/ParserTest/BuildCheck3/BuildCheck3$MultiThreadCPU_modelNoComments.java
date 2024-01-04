package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements BuildCheck3$CoreInterface {
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
	private boolean var36;
	private boolean var38;
	private boolean var41;
	private boolean var44;
	private boolean var66;
	private boolean var68;
	private boolean var71;

	public BuildCheck3$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$deploy_action() {
		return deploy_action;
	}

	@Override
	public final void set$deploy_action(boolean calculationVariable$value) {
		deploy_action = calculationVariable$value;
	}

	@Override
	public final boolean get$deploy_action_certainty() {
		return deploy_action_certainty;
	}

	@Override
	public final void set$deploy_action_certainty(boolean calculationVariable$value) {
		deploy_action_certainty = calculationVariable$value;
	}

	@Override
	public final boolean get$deploy_command() {
		return deploy_command;
	}

	@Override
	public final void set$deploy_command(boolean calculationVariable$value) {
		deploy_command = calculationVariable$value;
	}

	@Override
	public final boolean get$deploy_command_certainty() {
		return deploy_command_certainty;
	}

	@Override
	public final void set$deploy_command_certainty(boolean calculationVariable$value) {
		deploy_command_certainty = calculationVariable$value;
	}

	@Override
	public final boolean get$deploy_kws() {
		return deploy_kws;
	}

	@Override
	public final void set$deploy_kws(boolean calculationVariable$value) {
		deploy_kws = calculationVariable$value;
	}

	@Override
	public final boolean get$deploy_kws_certainty() {
		return deploy_kws_certainty;
	}

	@Override
	public final void set$deploy_kws_certainty(boolean calculationVariable$value) {
		deploy_kws_certainty = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean calculationVariable$value) {
		fixedFlag$sample46 = calculationVariable$value;
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean calculationVariable$value) {
		fixedFlag$sample48 = calculationVariable$value;
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean calculationVariable$value) {
		fixedFlag$sample51 = calculationVariable$value;
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean calculationVariable$value) {
		fixedFlag$sample54 = calculationVariable$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean calculationVariable$value) {
		fixedFlag$sample84 = calculationVariable$value;
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean calculationVariable$value) {
		fixedFlag$sample86 = calculationVariable$value;
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	@Override
	public final void set$fixedFlag$sample89(boolean calculationVariable$value) {
		fixedFlag$sample89 = calculationVariable$value;
		fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedProbFlag$sample89);
	}

	@Override
	public final boolean get$fixedFlag$sample99() {
		return fixedFlag$sample99;
	}

	@Override
	public final void set$fixedFlag$sample99(boolean calculationVariable$value) {
		fixedFlag$sample99 = calculationVariable$value;
		fixedProbFlag$sample99 = (fixedFlag$sample99 && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$invalid_trigger() {
		return invalid_trigger;
	}

	@Override
	public final void set$invalid_trigger(boolean calculationVariable$value) {
		invalid_trigger = calculationVariable$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$deploy_action_certainty() {
		return logProbability$deploy_action_certainty;
	}

	@Override
	public final double get$logProbability$deploy_command_certainty() {
		return logProbability$deploy_command_certainty;
	}

	@Override
	public final double get$logProbability$deploy_kws_certainty() {
		return logProbability$deploy_kws_certainty;
	}

	@Override
	public final double get$p_deploy_action() {
		return p_deploy_action;
	}

	@Override
	public final double get$p_deploy_command() {
		return p_deploy_command;
	}

	@Override
	public final double get$p_deploy_kws() {
		return p_deploy_kws;
	}

	@Override
	public final double get$p_release_workflow_trigger_deploy_action() {
		return p_release_workflow_trigger_deploy_action;
	}

	@Override
	public final double get$p_release_workflow_trigger_deploy_command() {
		return p_release_workflow_trigger_deploy_command;
	}

	@Override
	public final double get$p_step_uses_secrets_deploy_action() {
		return p_step_uses_secrets_deploy_action;
	}

	@Override
	public final double get$p_step_uses_secrets_deploy_command() {
		return p_step_uses_secrets_deploy_command;
	}

	@Override
	public final double get$p_tested_deploy_action() {
		return p_tested_deploy_action;
	}

	@Override
	public final boolean get$release_workflow_trigger_deploy_action() {
		return release_workflow_trigger_deploy_action;
	}

	@Override
	public final void set$release_workflow_trigger_deploy_action(boolean calculationVariable$value) {
		release_workflow_trigger_deploy_action = calculationVariable$value;
	}

	@Override
	public final boolean get$release_workflow_trigger_deploy_command() {
		return release_workflow_trigger_deploy_command;
	}

	@Override
	public final void set$release_workflow_trigger_deploy_command(boolean calculationVariable$value) {
		release_workflow_trigger_deploy_command = calculationVariable$value;
	}

	@Override
	public final boolean get$step_uses_secrets_deploy_action() {
		return step_uses_secrets_deploy_action;
	}

	@Override
	public final void set$step_uses_secrets_deploy_action(boolean calculationVariable$value) {
		step_uses_secrets_deploy_action = calculationVariable$value;
	}

	@Override
	public final boolean get$step_uses_secrets_deploy_command() {
		return step_uses_secrets_deploy_command;
	}

	@Override
	public final void set$step_uses_secrets_deploy_command(boolean calculationVariable$value) {
		step_uses_secrets_deploy_command = calculationVariable$value;
	}

	@Override
	public final boolean get$tested_deploy_action() {
		return tested_deploy_action;
	}

	@Override
	public final void set$tested_deploy_action(boolean calculationVariable$value) {
		tested_deploy_action = calculationVariable$value;
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var36;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_action));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var35 = calculationVariable$sampleAccumulator;
			logProbability$var36 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var36;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var35 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var38;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_tested_deploy_action));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var37 = calculationVariable$sampleAccumulator;
			logProbability$var38 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var38;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var37 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var41;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy_action));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var40 = calculationVariable$sampleAccumulator;
			logProbability$var41 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var41;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var40 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var44;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets_deploy_action));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var43 = calculationVariable$sampleAccumulator;
			logProbability$var44 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample54 = fixedFlag$sample54;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var44;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var43 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var66;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_command));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var65 = calculationVariable$sampleAccumulator;
			logProbability$var66 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample84 = fixedFlag$sample84;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var66;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var65 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var68;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy_command));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var67 = calculationVariable$sampleAccumulator;
			logProbability$var68 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample86 = fixedFlag$sample86;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var68;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var67 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = var71;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets_deploy_command));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var70 = calculationVariable$sampleAccumulator;
			logProbability$var71 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample89 = fixedFlag$sample89;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var71;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var70 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample99() {
		if(!fixedProbFlag$sample99) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = deploy_kws_certainty;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_deploy_kws));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var78 = calculationVariable$sampleAccumulator;
			logProbability$deploy_kws_certainty = calculationVariable$sampleProbability;
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample99)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample99 = fixedFlag$sample99;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_kws_certainty;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var78 = calculationVariable$RVaccumulator;
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample99)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample46)
			var36 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_action);
		if(!fixedFlag$sample48)
			var38 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_tested_deploy_action);
		if(!fixedFlag$sample51)
			var41 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy_action);
		if(!fixedFlag$sample54)
			var44 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets_deploy_action);
		if(!(((fixedFlag$sample46 && fixedFlag$sample48) && fixedFlag$sample51) && fixedFlag$sample54))
			deploy_action_certainty = (((var36 || var38) || var41) || var44);
		if(!fixedFlag$sample84)
			var66 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_command);
		if(!fixedFlag$sample86)
			var68 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy_command);
		if(!fixedFlag$sample89)
			var71 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets_deploy_command);
		if(!((fixedFlag$sample84 && fixedFlag$sample86) && fixedFlag$sample89))
			deploy_command_certainty = ((var66 || var68) || var71);
		if(!fixedFlag$sample99)
			deploy_kws_certainty = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_deploy_kws);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {}

	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	@Override
	public final void gibbsRound() {
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		if(deploy_action)
			p_deploy_action = (0.8 * 0.9);
		else
			p_deploy_action = 0.0;
		if(tested_deploy_action)
			p_tested_deploy_action = (0.1 * 0.9);
		else
			p_tested_deploy_action = 0.0;
		if(release_workflow_trigger_deploy_action) {
			if(invalid_trigger)
				p_release_workflow_trigger_deploy_action = (0.75 * 0.85);
			else
				p_release_workflow_trigger_deploy_action = (0.9 * 0.85);
		} else
			p_release_workflow_trigger_deploy_action = 0.0;
		if(step_uses_secrets_deploy_action)
			p_step_uses_secrets_deploy_action = (0.65 * 0.9);
		p_step_uses_secrets_deploy_action = 0.0;
		if(deploy_command)
			p_deploy_command = (0.75 * 0.8);
		else
			p_deploy_command = 0.0;
		if(release_workflow_trigger_deploy_command) {
			if(invalid_trigger)
				p_release_workflow_trigger_deploy_command = (0.75 * 0.85);
			else
				p_release_workflow_trigger_deploy_command = (0.9 * 0.85);
		} else
			p_release_workflow_trigger_deploy_command = 0.0;
		if(step_uses_secrets_deploy_command)
			p_step_uses_secrets_deploy_command = (0.65 * 0.9);
		else
			p_step_uses_secrets_deploy_command = 0.0;
		if(deploy_kws)
			p_deploy_kws = (0.7 * 0.4);
		else
			p_deploy_kws = 0.0;
	}

	private final void initializeLogProbabilityFields() {
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

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
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

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample48();
		logProbabilityValue$sample51();
		logProbabilityValue$sample54();
		logProbabilityValue$sample84();
		logProbabilityValue$sample86();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample48();
		logProbabilityValue$sample51();
		logProbabilityValue$sample54();
		logProbabilityValue$sample84();
		logProbabilityValue$sample86();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void setIntermediates() {
		if(true)
			deploy_action_certainty = (((var36 || var38) || var41) || var44);
		if(true)
			deploy_command_certainty = ((var66 || var68) || var71);
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck3(boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy_command,\n                         boolean release_workflow_trigger_deploy_action,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy_action,\n                         boolean step_uses_secrets_deploy_command) \n{\n  //Calculate deploy_action_certainty\n  double p_deploy_action = deploy_action ? 0.8*0.9 : 0.0;\n  double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n  double p_release_workflow_trigger_deploy_action = release_workflow_trigger_deploy_action ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_action = step_uses_secrets_deploy_action ? 0.65*0.9 : 0;\n  \n  boolean deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                    bernoulli(p_tested_deploy_action).sample() ||\n                                    bernoulli(p_release_workflow_trigger_deploy_action).sample() ||\n                                    bernoulli(p_step_uses_secrets_deploy_action).sample();\n\n  //Calculate deploy_command_certainty\n  double p_deploy_command = deploy_command ? 0.75*0.8 : 0.0;\n  double p_release_workflow_trigger_deploy_command = release_workflow_trigger_deploy_command ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_command = step_uses_secrets_deploy_command ? 0.65*0.9 : 0.0;\n\n  boolean deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                     bernoulli(p_release_workflow_trigger_deploy_command).sample() ||\n                                     bernoulli(p_step_uses_secrets_deploy_command).sample();\n\n  //Calculate deploy_kws_certainty\n  double p_deploy_kws = deploy_kws ? 0.7*0.4 : 0.0;\n\n  boolean deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n}";
	}
}