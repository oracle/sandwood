package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements BuildCheck$CoreInterface {
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
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean calculationVariable$value) {
		fixedFlag$sample10 = calculationVariable$value;
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean calculationVariable$value) {
		fixedFlag$sample12 = calculationVariable$value;
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean calculationVariable$value) {
		fixedFlag$sample14 = calculationVariable$value;
		fixedProbFlag$sample14 = (fixedFlag$sample14 && fixedProbFlag$sample14);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean calculationVariable$value) {
		fixedFlag$sample16 = calculationVariable$value;
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean calculationVariable$value) {
		fixedFlag$sample21 = calculationVariable$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean calculationVariable$value) {
		fixedFlag$sample28 = calculationVariable$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean calculationVariable$value) {
		fixedFlag$sample36 = calculationVariable$value;
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean calculationVariable$value) {
		fixedFlag$sample44 = calculationVariable$value;
		fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedProbFlag$sample44);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean calculationVariable$value) {
		fixedFlag$sample49 = calculationVariable$value;
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
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
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean calculationVariable$value) {
		fixedFlag$sample53 = calculationVariable$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean calculationVariable$value) {
		fixedFlag$sample58 = calculationVariable$value;
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	@Override
	public final void set$fixedFlag$sample65(boolean calculationVariable$value) {
		fixedFlag$sample65 = calculationVariable$value;
		fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedProbFlag$sample65);
	}

	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	@Override
	public final void set$fixedFlag$sample73(boolean calculationVariable$value) {
		fixedFlag$sample73 = calculationVariable$value;
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean calculationVariable$value) {
		fixedFlag$sample78 = calculationVariable$value;
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	@Override
	public final void set$fixedFlag$sample83(boolean calculationVariable$value) {
		fixedFlag$sample83 = calculationVariable$value;
		fixedProbFlag$sample83 = (fixedFlag$sample83 && fixedProbFlag$sample83);
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
	public final double get$logProbability$deploy_action() {
		return logProbability$deploy_action;
	}

	@Override
	public final double get$logProbability$deploy_action_certainty() {
		return logProbability$deploy_action_certainty;
	}

	@Override
	public final double get$logProbability$deploy_command() {
		return logProbability$deploy_command;
	}

	@Override
	public final double get$logProbability$deploy_command_certainty() {
		return logProbability$deploy_command_certainty;
	}

	@Override
	public final double get$logProbability$deploy_kws() {
		return logProbability$deploy_kws;
	}

	@Override
	public final double get$logProbability$deploy_kws_certainty() {
		return logProbability$deploy_kws_certainty;
	}

	@Override
	public final double get$logProbability$release_workflow_trigger_deploy_action() {
		return logProbability$release_workflow_trigger_deploy_action;
	}

	@Override
	public final double get$logProbability$release_workflow_trigger_deploy_command() {
		return logProbability$release_workflow_trigger_deploy_command;
	}

	@Override
	public final double get$logProbability$step_uses_secrets_deploy_action() {
		return logProbability$step_uses_secrets_deploy_action;
	}

	@Override
	public final double get$logProbability$step_uses_secrets_deploy_command() {
		return logProbability$step_uses_secrets_deploy_command;
	}

	@Override
	public final double get$logProbability$tested_deploy_action() {
		return logProbability$tested_deploy_action;
	}

	@Override
	public final double get$p_deploy_action() {
		return p_deploy_action;
	}

	@Override
	public final void set$p_deploy_action(double calculationVariable$value) {
		p_deploy_action = calculationVariable$value;
	}

	@Override
	public final double get$p_deploy_command() {
		return p_deploy_command;
	}

	@Override
	public final void set$p_deploy_command(double calculationVariable$value) {
		p_deploy_command = calculationVariable$value;
	}

	@Override
	public final double get$p_deploy_kws() {
		return p_deploy_kws;
	}

	@Override
	public final void set$p_deploy_kws(double calculationVariable$value) {
		p_deploy_kws = calculationVariable$value;
	}

	@Override
	public final double get$p_release_workflow_trigger_deploy_action() {
		return p_release_workflow_trigger_deploy_action;
	}

	@Override
	public final void set$p_release_workflow_trigger_deploy_action(double calculationVariable$value) {
		p_release_workflow_trigger_deploy_action = calculationVariable$value;
	}

	@Override
	public final double get$p_release_workflow_trigger_deploy_command() {
		return p_release_workflow_trigger_deploy_command;
	}

	@Override
	public final void set$p_release_workflow_trigger_deploy_command(double calculationVariable$value) {
		p_release_workflow_trigger_deploy_command = calculationVariable$value;
	}

	@Override
	public final double get$p_step_uses_secrets_deploy_action() {
		return p_step_uses_secrets_deploy_action;
	}

	@Override
	public final void set$p_step_uses_secrets_deploy_action(double calculationVariable$value) {
		p_step_uses_secrets_deploy_action = calculationVariable$value;
	}

	@Override
	public final double get$p_step_uses_secrets_deploy_command() {
		return p_step_uses_secrets_deploy_command;
	}

	@Override
	public final void set$p_step_uses_secrets_deploy_command(double calculationVariable$value) {
		p_step_uses_secrets_deploy_command = calculationVariable$value;
	}

	@Override
	public final double get$p_tested_deploy_action() {
		return p_tested_deploy_action;
	}

	@Override
	public final void set$p_tested_deploy_action(double calculationVariable$value) {
		p_tested_deploy_action = calculationVariable$value;
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

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = deploy_action;
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
			logProbability$var9 = calculationVariable$sampleAccumulator;
			logProbability$deploy_action = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var9 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = tested_deploy_action;
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
			logProbability$var11 = calculationVariable$sampleAccumulator;
			logProbability$tested_deploy_action = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$tested_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var11 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = release_workflow_trigger_deploy_action;
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
			logProbability$var13 = calculationVariable$sampleAccumulator;
			logProbability$release_workflow_trigger_deploy_action = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$release_workflow_trigger_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var13 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = step_uses_secrets_deploy_action;
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
			logProbability$var15 = calculationVariable$sampleAccumulator;
			logProbability$step_uses_secrets_deploy_action = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$step_uses_secrets_deploy_action;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var15 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_action_certainty = false;
			{
				if(!calculationVariable$guard$deploy_action_certainty) {
					calculationVariable$guard$deploy_action_certainty = true;
					logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double calculationVariable$accumulator = 0.0;
			if(deploy_action) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var19;
					{
						{
							double var17 = 0.8;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var17));
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
				logProbability$var18 = calculationVariable$sampleAccumulator;
				logProbability$sample21 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var19 = (logProbability$var19 + calculationVariable$accumulator);
			{
				if(deploy_action) {
					boolean traceTempVariable$var21$2_1 = var19;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample21;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var18 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var19 = (logProbability$var19 + calculationVariable$accumulator);
			{
				if(deploy_action) {
					boolean traceTempVariable$var21$3_1 = var19;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double calculationVariable$accumulator = 0.0;
			if(tested_deploy_action) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var24;
					{
						{
							double var22 = 0.1;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var22));
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
				logProbability$var23 = calculationVariable$sampleAccumulator;
				logProbability$sample28 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var24 = (logProbability$var24 + calculationVariable$accumulator);
			{
				if(tested_deploy_action) {
					boolean traceTempVariable$var26$2_1 = var24;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(tested_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample28;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var23 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var24 = (logProbability$var24 + calculationVariable$accumulator);
			{
				if(tested_deploy_action) {
					boolean traceTempVariable$var26$3_1 = var24;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_action) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var30;
					{
						{
							double var28 = 0.85;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var28));
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
				logProbability$var29 = calculationVariable$sampleAccumulator;
				logProbability$sample36 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var30 = (logProbability$var30 + calculationVariable$accumulator);
			{
				if(release_workflow_trigger_deploy_action) {
					boolean traceTempVariable$var32$2_1 = var30;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample36;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var29 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var30 = (logProbability$var30 + calculationVariable$accumulator);
			{
				if(release_workflow_trigger_deploy_action) {
					boolean traceTempVariable$var32$3_1 = var30;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_action) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var36;
					{
						{
							double var34 = 0.65;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var34));
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
				logProbability$sample44 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var36 = (logProbability$var36 + calculationVariable$accumulator);
			{
				if(step_uses_secrets_deploy_action) {
					boolean traceTempVariable$var38$2_1 = var36;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample44;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_action) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample44;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var35 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var36 = (logProbability$var36 + calculationVariable$accumulator);
			{
				if(step_uses_secrets_deploy_action) {
					boolean traceTempVariable$var38$3_1 = var36;
					if(!calculationVariable$guard$deploy_action_certainty) {
						calculationVariable$guard$deploy_action_certainty = true;
						logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = deploy_command;
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
			logProbability$var40 = calculationVariable$sampleAccumulator;
			logProbability$deploy_command = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample49 = fixedFlag$sample49;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var40 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
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
				boolean calculationVariable$sampleValue = release_workflow_trigger_deploy_command;
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
			logProbability$var42 = calculationVariable$sampleAccumulator;
			logProbability$release_workflow_trigger_deploy_command = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$release_workflow_trigger_deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var42 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = step_uses_secrets_deploy_command;
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
			logProbability$var44 = calculationVariable$sampleAccumulator;
			logProbability$step_uses_secrets_deploy_command = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample53 = fixedFlag$sample53;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$step_uses_secrets_deploy_command;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var44 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_command_certainty = false;
			{
				if(!calculationVariable$guard$deploy_command_certainty) {
					calculationVariable$guard$deploy_command_certainty = true;
					logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double calculationVariable$accumulator = 0.0;
			if(deploy_command) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var48;
					{
						{
							double var46 = 0.75;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var46));
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
				logProbability$var47 = calculationVariable$sampleAccumulator;
				logProbability$sample58 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var48 = (logProbability$var48 + calculationVariable$accumulator);
			{
				if(deploy_command) {
					boolean traceTempVariable$var50$2_1 = var48;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample58;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var47 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var48 = (logProbability$var48 + calculationVariable$accumulator);
			{
				if(deploy_command) {
					boolean traceTempVariable$var50$3_1 = var48;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample65() {
		if(!fixedProbFlag$sample65) {
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_command) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var53;
					{
						{
							double var51 = 0.85;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var51));
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
				logProbability$var52 = calculationVariable$sampleAccumulator;
				logProbability$sample65 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var53 = (logProbability$var53 + calculationVariable$accumulator);
			{
				if(release_workflow_trigger_deploy_command) {
					boolean traceTempVariable$var55$2_1 = var53;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample65 = fixedFlag$sample65;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(release_workflow_trigger_deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample65;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var52 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var53 = (logProbability$var53 + calculationVariable$accumulator);
			{
				if(release_workflow_trigger_deploy_command) {
					boolean traceTempVariable$var55$3_1 = var53;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample65)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample73() {
		if(!fixedProbFlag$sample73) {
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_command) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var59;
					{
						{
							double var57 = 0.65;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var57));
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
				logProbability$var58 = calculationVariable$sampleAccumulator;
				logProbability$sample73 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var59 = (logProbability$var59 + calculationVariable$accumulator);
			{
				if(step_uses_secrets_deploy_command) {
					boolean traceTempVariable$var61$2_1 = var59;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample73 = fixedFlag$sample73;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(step_uses_secrets_deploy_command) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample73;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var58 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var59 = (logProbability$var59 + calculationVariable$accumulator);
			{
				if(step_uses_secrets_deploy_command) {
					boolean traceTempVariable$var61$3_1 = var59;
					if(!calculationVariable$guard$deploy_command_certainty) {
						calculationVariable$guard$deploy_command_certainty = true;
						logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = deploy_kws;
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
			logProbability$var63 = calculationVariable$sampleAccumulator;
			logProbability$deploy_kws = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			{
				if(!calculationVariable$guard$deploy_kws_certainty) {
					calculationVariable$guard$deploy_kws_certainty = true;
					logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample78 = fixedFlag$sample78;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$deploy_kws;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var63 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			{
				if(!calculationVariable$guard$deploy_kws_certainty) {
					calculationVariable$guard$deploy_kws_certainty = true;
					logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!fixedProbFlag$sample83) {
			double calculationVariable$accumulator = 0.0;
			if(deploy_kws) {
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var67;
					{
						{
							double var65 = 0.7;
							double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, var65));
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
				logProbability$var66 = calculationVariable$sampleAccumulator;
				logProbability$sample83 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			logProbability$var67 = (logProbability$var67 + calculationVariable$accumulator);
			{
				if(deploy_kws) {
					boolean traceTempVariable$deploy_kws_certainty$2_1 = var67;
					if(!calculationVariable$guard$deploy_kws_certainty) {
						calculationVariable$guard$deploy_kws_certainty = true;
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample83 = fixedFlag$sample83;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(deploy_kws) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample83;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var66 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			logProbability$var67 = (logProbability$var67 + calculationVariable$accumulator);
			{
				if(deploy_kws) {
					boolean traceTempVariable$deploy_kws_certainty$3_1 = var67;
					if(!calculationVariable$guard$deploy_kws_certainty) {
						calculationVariable$guard$deploy_kws_certainty = true;
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

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

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {}

	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	@Override
	public final void gibbsRound() {
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
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

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
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

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
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

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
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

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

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