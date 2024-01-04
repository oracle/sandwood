package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements BuildCheck4$CoreInterface {
	private boolean ci_parsed;
	private boolean deploy_action;
	private boolean deploy_action_certainty;
	private boolean deploy_command;
	private boolean deploy_command_certainty;
	private boolean deploy_kws;
	private boolean deploy_kws_certainty;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean invalid_trigger;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$deploy_action_certainty;
	private double logProbability$deploy_command_certainty;
	private double logProbability$deploy_kws_certainty;
	private double logProbability$sample104;
	private double logProbability$sample44;
	private double logProbability$sample46;
	private double logProbability$sample49;
	private double logProbability$sample52;
	private double logProbability$sample86;
	private double logProbability$sample88;
	private double logProbability$sample91;
	private double logProbability$var31;
	private double logProbability$var32;
	private double logProbability$var33;
	private double logProbability$var34;
	private double logProbability$var36;
	private double logProbability$var37;
	private double logProbability$var39;
	private double logProbability$var40;
	private double logProbability$var63;
	private double logProbability$var64;
	private double logProbability$var65;
	private double logProbability$var66;
	private double logProbability$var68;
	private double logProbability$var69;
	private double logProbability$var79;
	private double logProbability$var80;
	private boolean release_workflow_trigger_deploy;
	private boolean step_uses_secrets_deploy;
	private boolean system$gibbsForward = true;
	private boolean tested_deploy_action;
	private boolean var32;
	private boolean var34;
	private boolean var37;
	private boolean var40;
	private boolean var64;
	private boolean var66;
	private boolean var69;
	private boolean var80;

	public BuildCheck4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$ci_parsed() {
		return ci_parsed;
	}

	@Override
	public final void set$ci_parsed(boolean calculationVariable$value) {
		ci_parsed = calculationVariable$value;
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
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean calculationVariable$value) {
		fixedFlag$sample104 = calculationVariable$value;
		fixedProbFlag$sample104 = (calculationVariable$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean calculationVariable$value) {
		fixedFlag$sample44 = calculationVariable$value;
		fixedProbFlag$sample44 = (calculationVariable$value && fixedProbFlag$sample44);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean calculationVariable$value) {
		fixedFlag$sample46 = calculationVariable$value;
		fixedProbFlag$sample46 = (calculationVariable$value && fixedProbFlag$sample46);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean calculationVariable$value) {
		fixedFlag$sample49 = calculationVariable$value;
		fixedProbFlag$sample49 = (calculationVariable$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean calculationVariable$value) {
		fixedFlag$sample52 = calculationVariable$value;
		fixedProbFlag$sample52 = (calculationVariable$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean calculationVariable$value) {
		fixedFlag$sample86 = calculationVariable$value;
		fixedProbFlag$sample86 = (calculationVariable$value && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean calculationVariable$value) {
		fixedFlag$sample88 = calculationVariable$value;
		fixedProbFlag$sample88 = (calculationVariable$value && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean calculationVariable$value) {
		fixedFlag$sample91 = calculationVariable$value;
		fixedProbFlag$sample91 = (calculationVariable$value && fixedProbFlag$sample91);
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
	public final boolean get$release_workflow_trigger_deploy() {
		return release_workflow_trigger_deploy;
	}

	@Override
	public final void set$release_workflow_trigger_deploy(boolean calculationVariable$value) {
		release_workflow_trigger_deploy = calculationVariable$value;
	}

	@Override
	public final boolean get$step_uses_secrets_deploy() {
		return step_uses_secrets_deploy;
	}

	@Override
	public final void set$step_uses_secrets_deploy(boolean calculationVariable$value) {
		step_uses_secrets_deploy = calculationVariable$value;
	}

	@Override
	public final boolean get$tested_deploy_action() {
		return tested_deploy_action;
	}

	@Override
	public final void set$tested_deploy_action(boolean calculationVariable$value) {
		tested_deploy_action = calculationVariable$value;
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_kws)) {
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var80, 0.27999999999999997);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var79 = calculationVariable$distributionAccumulator;
				logProbability$sample104 = calculationVariable$distributionAccumulator;
			}
			logProbability$var80 = (logProbability$var80 + calculationVariable$accumulator);
			if((ci_parsed && deploy_kws))
				logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample104 = fixedFlag$sample104;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_kws)) {
				calculationVariable$accumulator = logProbability$sample104;
				logProbability$var79 = logProbability$sample104;
			}
			logProbability$var80 = (logProbability$var80 + calculationVariable$accumulator);
			if((ci_parsed && deploy_kws))
				logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var32, 0.7200000000000001);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var31 = calculationVariable$distributionAccumulator;
				logProbability$sample44 = calculationVariable$distributionAccumulator;
			}
			logProbability$var32 = (logProbability$var32 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample44;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				calculationVariable$accumulator = logProbability$sample44;
				logProbability$var31 = logProbability$sample44;
			}
			logProbability$var32 = (logProbability$var32 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				double p_tested_deploy_action;
				if(tested_deploy_action)
					p_tested_deploy_action = 0.09000000000000001;
				else
					p_tested_deploy_action = 0.0;
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var34, p_tested_deploy_action);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var33 = calculationVariable$distributionAccumulator;
				logProbability$sample46 = calculationVariable$distributionAccumulator;
			}
			logProbability$var34 = (logProbability$var34 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				calculationVariable$accumulator = logProbability$sample46;
				logProbability$var33 = logProbability$sample46;
			}
			logProbability$var34 = (logProbability$var34 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				double p_release_workflow_trigger_deploy$var25;
				if(release_workflow_trigger_deploy) {
					double var23;
					if(invalid_trigger)
						var23 = 0.6375;
					else
						var23 = 0.765;
					p_release_workflow_trigger_deploy$var25 = var23;
				} else
					p_release_workflow_trigger_deploy$var25 = 0.0;
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var37, p_release_workflow_trigger_deploy$var25);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var36 = calculationVariable$distributionAccumulator;
				logProbability$sample49 = calculationVariable$distributionAccumulator;
			}
			logProbability$var37 = (logProbability$var37 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample49 = fixedFlag$sample49;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				calculationVariable$accumulator = logProbability$sample49;
				logProbability$var36 = logProbability$sample49;
			}
			logProbability$var37 = (logProbability$var37 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				double p_step_uses_secrets$var30;
				if(step_uses_secrets_deploy)
					p_step_uses_secrets$var30 = 0.5850000000000001;
				else
					p_step_uses_secrets$var30 = 0.0;
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var40, p_step_uses_secrets$var30);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var39 = calculationVariable$distributionAccumulator;
				logProbability$sample52 = calculationVariable$distributionAccumulator;
			}
			logProbability$var40 = (logProbability$var40 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_action)) {
				calculationVariable$accumulator = logProbability$sample52;
				logProbability$var39 = logProbability$sample52;
			}
			logProbability$var40 = (logProbability$var40 + calculationVariable$accumulator);
			if((ci_parsed && deploy_action))
				logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var64, 0.6000000000000001);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var63 = calculationVariable$distributionAccumulator;
				logProbability$sample86 = calculationVariable$distributionAccumulator;
			}
			logProbability$var64 = (logProbability$var64 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample86 = fixedFlag$sample86;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				calculationVariable$accumulator = logProbability$sample86;
				logProbability$var63 = logProbability$sample86;
			}
			logProbability$var64 = (logProbability$var64 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				double p_release_workflow_trigger_deploy$var57;
				if(release_workflow_trigger_deploy) {
					double var55;
					if(invalid_trigger)
						var55 = 0.6375;
					else
						var55 = 0.765;
					p_release_workflow_trigger_deploy$var57 = var55;
				} else
					p_release_workflow_trigger_deploy$var57 = 0.0;
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var66, p_release_workflow_trigger_deploy$var57);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var65 = calculationVariable$distributionAccumulator;
				logProbability$sample88 = calculationVariable$distributionAccumulator;
			}
			logProbability$var66 = (logProbability$var66 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				calculationVariable$accumulator = logProbability$sample88;
				logProbability$var65 = logProbability$sample88;
			}
			logProbability$var66 = (logProbability$var66 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				double p_step_uses_secrets$var62;
				if(step_uses_secrets_deploy)
					p_step_uses_secrets$var62 = 0.5850000000000001;
				else
					p_step_uses_secrets$var62 = 0.0;
				double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var69, p_step_uses_secrets$var62);
				calculationVariable$accumulator = calculationVariable$distributionAccumulator;
				logProbability$var68 = calculationVariable$distributionAccumulator;
				logProbability$sample91 = calculationVariable$distributionAccumulator;
			}
			logProbability$var69 = (logProbability$var69 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample91)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample91 = fixedFlag$sample91;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_command)) {
				calculationVariable$accumulator = logProbability$sample91;
				logProbability$var68 = logProbability$sample91;
			}
			logProbability$var69 = (logProbability$var69 + calculationVariable$accumulator);
			if((ci_parsed && deploy_command))
				logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample91)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(ci_parsed) {
			if(deploy_action) {
				if(!fixedFlag$sample44)
					var32 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.7200000000000001);
				if(!fixedFlag$sample46) {
					double p_tested_deploy_action;
					if(tested_deploy_action)
						p_tested_deploy_action = 0.09000000000000001;
					else
						p_tested_deploy_action = 0.0;
					var34 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_tested_deploy_action);
				}
				if(!fixedFlag$sample49) {
					double p_release_workflow_trigger_deploy$var25;
					if(release_workflow_trigger_deploy) {
						double var23;
						if(invalid_trigger)
							var23 = 0.6375;
						else
							var23 = 0.765;
						p_release_workflow_trigger_deploy$var25 = var23;
					} else
						p_release_workflow_trigger_deploy$var25 = 0.0;
					var37 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy$var25);
				}
				if(!fixedFlag$sample52) {
					double p_step_uses_secrets$var30;
					if(step_uses_secrets_deploy)
						p_step_uses_secrets$var30 = 0.5850000000000001;
					else
						p_step_uses_secrets$var30 = 0.0;
					var40 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets$var30);
				}
				if((((!fixedFlag$sample44 || !fixedFlag$sample46) || !fixedFlag$sample49) || !fixedFlag$sample52))
					deploy_action_certainty = (((var32 || var34) || var37) || var40);
			} else
				deploy_action_certainty = false;
			if(deploy_command) {
				if(!fixedFlag$sample86)
					var64 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.6000000000000001);
				if(!fixedFlag$sample88) {
					double p_release_workflow_trigger_deploy$var57;
					if(release_workflow_trigger_deploy) {
						double var55;
						if(invalid_trigger)
							var55 = 0.6375;
						else
							var55 = 0.765;
						p_release_workflow_trigger_deploy$var57 = var55;
					} else
						p_release_workflow_trigger_deploy$var57 = 0.0;
					var66 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy$var57);
				}
				if(!fixedFlag$sample91) {
					double p_step_uses_secrets$var62;
					if(step_uses_secrets_deploy)
						p_step_uses_secrets$var62 = 0.5850000000000001;
					else
						p_step_uses_secrets$var62 = 0.0;
					var69 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets$var62);
				}
				if(((!fixedFlag$sample86 || !fixedFlag$sample88) || !fixedFlag$sample91))
					deploy_command_certainty = ((var64 || var66) || var69);
			} else
				deploy_command_certainty = false;
		} else {
			deploy_action_certainty = false;
			deploy_command_certainty = false;
		}
		if((ci_parsed && deploy_kws)) {
			if(!fixedFlag$sample104) {
				var80 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.27999999999999997);
				deploy_kws_certainty = var80;
			}
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
		logProbability$var31 = 0.0;
		logProbability$deploy_action_certainty = 0.0;
		logProbability$var32 = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$sample44 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$sample46 = 0.0;
		logProbability$var36 = 0.0;
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$sample49 = 0.0;
		logProbability$var39 = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$sample52 = 0.0;
		logProbability$var63 = 0.0;
		logProbability$deploy_command_certainty = 0.0;
		logProbability$var64 = 0.0;
		if(!fixedProbFlag$sample86)
			logProbability$sample86 = 0.0;
		logProbability$var65 = 0.0;
		logProbability$var66 = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$sample88 = 0.0;
		logProbability$var68 = 0.0;
		logProbability$var69 = 0.0;
		if(!fixedProbFlag$sample91)
			logProbability$sample91 = 0.0;
		logProbability$var79 = 0.0;
		logProbability$deploy_kws_certainty = 0.0;
		logProbability$var80 = 0.0;
		if(!fixedProbFlag$sample104)
			logProbability$sample104 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample49)
			logProbabilityValue$sample49();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample86)
			logProbabilityValue$sample86();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample91)
			logProbabilityValue$sample91();
		if(fixedFlag$sample104)
			logProbabilityValue$sample104();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample44();
		logProbabilityValue$sample46();
		logProbabilityValue$sample49();
		logProbabilityValue$sample52();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample91();
		logProbabilityValue$sample104();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample44();
		logProbabilityValue$sample46();
		logProbabilityValue$sample49();
		logProbabilityValue$sample52();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample91();
		logProbabilityValue$sample104();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void setIntermediates() {
		if(ci_parsed) {
			boolean var43;
			if(deploy_action)
				var43 = (((var32 || var34) || var37) || var40);
			else
				var43 = false;
			deploy_action_certainty = var43;
			boolean var72;
			if(deploy_command)
				var72 = ((var64 || var66) || var69);
			else
				var72 = false;
			deploy_command_certainty = var72;
		} else {
			deploy_action_certainty = false;
			deploy_command_certainty = false;
		}
		if((ci_parsed && deploy_kws))
			deploy_kws_certainty = var80;
		else
			deploy_kws_certainty = false;
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck4(boolean ci_parsed,\n                         boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy) \n{\n  //Calculate deploy_action_certainty\n  boolean deploy_action_certainty;\n  if(ci_parsed) {\n    if(deploy_action) {\n      double p_deploy_action = 0.8*0.9;\n      double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                 (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                 0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                bernoulli(p_tested_deploy_action).sample() ||\n                                bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_action_certainty = false;\n    }\n  } else {\n    deploy_action_certainty = false;\n  }\n\n  //Calculate deploy_command_certainty\n  boolean deploy_command_certainty;\n  if(ci_parsed) {\n    if(deploy_command) {\n      double p_deploy_command = 0.75*0.8;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                 bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                 bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_command_certainty = false;\n    }\n  } else {\n    deploy_command_certainty = false;\n  }\n\n  //Calculate deploy_kws_certainty\n  boolean deploy_kws_certainty;\n  if(ci_parsed && deploy_kws) {\n    double p_deploy_kws = 0.7*0.4;\n    deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n  } else\n    deploy_kws_certainty = false;\n}";
	}
}