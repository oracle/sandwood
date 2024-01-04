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
		fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedProbFlag$sample104);
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
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean calculationVariable$value) {
		fixedFlag$sample46 = calculationVariable$value;
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
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
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean calculationVariable$value) {
		fixedFlag$sample52 = calculationVariable$value;
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
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
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean calculationVariable$value) {
		fixedFlag$sample88 = calculationVariable$value;
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean calculationVariable$value) {
		fixedFlag$sample91 = calculationVariable$value;
		fixedProbFlag$sample91 = (fixedFlag$sample91 && fixedProbFlag$sample91);
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
				double calculationVariable$sampleAccumulator = 0.0;
				double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double calculationVariable$probabilityReached = 0.0;
				{
					boolean calculationVariable$sampleValue = var80;
					{
						{
							double p_deploy_kws = (0.7 * 0.4);
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
				logProbability$var79 = calculationVariable$sampleAccumulator;
				logProbability$sample104 = calculationVariable$sampleProbability;
			}
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			logProbability$var80 = (logProbability$var80 + calculationVariable$accumulator);
			{
				if((ci_parsed && deploy_kws)) {
					boolean traceTempVariable$deploy_kws_certainty$2_1 = var80;
					if(!calculationVariable$guard$deploy_kws_certainty) {
						calculationVariable$guard$deploy_kws_certainty = true;
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample104 = fixedFlag$sample104;
		} else {
			double calculationVariable$accumulator = 0.0;
			if((ci_parsed && deploy_kws)) {
				double calculationVariable$RVaccumulator = 0.0;
				double calculationVariable$sampleValue = logProbability$sample104;
				calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var79 = calculationVariable$RVaccumulator;
			}
			boolean calculationVariable$guard$deploy_kws_certainty = false;
			logProbability$var80 = (logProbability$var80 + calculationVariable$accumulator);
			{
				if((ci_parsed && deploy_kws)) {
					boolean traceTempVariable$deploy_kws_certainty$3_1 = var80;
					if(!calculationVariable$guard$deploy_kws_certainty) {
						calculationVariable$guard$deploy_kws_certainty = true;
						logProbability$deploy_kws_certainty = (logProbability$deploy_kws_certainty + calculationVariable$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var32;
						{
							{
								double p_deploy_action = (0.8 * 0.9);
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
					logProbability$var31 = calculationVariable$sampleAccumulator;
					logProbability$sample44 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var32 = (logProbability$var32 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$2_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$2_2 = traceTempVariable$var43$2_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample44;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample44;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var31 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var32 = (logProbability$var32 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$3_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$3_2 = traceTempVariable$var43$3_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var34;
						{
							{
								double p_tested_deploy_action;
								if(tested_deploy_action)
									p_tested_deploy_action = (0.1 * 0.9);
								else
									p_tested_deploy_action = 0.0;
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
					logProbability$var33 = calculationVariable$sampleAccumulator;
					logProbability$sample46 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var34 = (logProbability$var34 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$2_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$2_2 = traceTempVariable$var43$2_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample46;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var33 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var34 = (logProbability$var34 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$3_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$3_2 = traceTempVariable$var43$3_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var37;
						{
							{
								double p_release_workflow_trigger_deploy$var25;
								if(release_workflow_trigger_deploy) {
									double var23;
									if(invalid_trigger)
										var23 = (0.75 * 0.85);
									else
										var23 = (0.9 * 0.85);
									p_release_workflow_trigger_deploy$var25 = var23;
								} else
									p_release_workflow_trigger_deploy$var25 = 0.0;
								double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy$var25));
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
					logProbability$var36 = calculationVariable$sampleAccumulator;
					logProbability$sample49 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var37 = (logProbability$var37 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$2_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$2_2 = traceTempVariable$var43$2_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample49 = fixedFlag$sample49;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample49;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var36 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var37 = (logProbability$var37 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$3_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$3_2 = traceTempVariable$var43$3_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var40;
						{
							{
								double p_step_uses_secrets$var30;
								if(step_uses_secrets_deploy)
									p_step_uses_secrets$var30 = (0.65 * 0.9);
								else
									p_step_uses_secrets$var30 = 0.0;
								double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets$var30));
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
					logProbability$var39 = calculationVariable$sampleAccumulator;
					logProbability$sample52 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var40 = (logProbability$var40 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$2_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$2_2 = traceTempVariable$var43$2_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_action) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample52;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var39 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_action_certainty = false;
			logProbability$var40 = (logProbability$var40 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_action) {
						boolean traceTempVariable$var43$3_1 = (((var32 || var34) || var37) || var40);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_action_certainty$3_2 = traceTempVariable$var43$3_1;
							if(!calculationVariable$guard$deploy_action_certainty) {
								calculationVariable$guard$deploy_action_certainty = true;
								logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var64;
						{
							{
								double p_deploy_command = (0.75 * 0.8);
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
					logProbability$var63 = calculationVariable$sampleAccumulator;
					logProbability$sample86 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var64 = (logProbability$var64 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$2_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$2_2 = traceTempVariable$var72$2_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample86 = fixedFlag$sample86;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample86;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var63 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var64 = (logProbability$var64 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$3_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$3_2 = traceTempVariable$var72$3_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var66;
						{
							{
								double p_release_workflow_trigger_deploy$var57;
								if(release_workflow_trigger_deploy) {
									double var55;
									if(invalid_trigger)
										var55 = (0.75 * 0.85);
									else
										var55 = (0.9 * 0.85);
									p_release_workflow_trigger_deploy$var57 = var55;
								} else
									p_release_workflow_trigger_deploy$var57 = 0.0;
								double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_release_workflow_trigger_deploy$var57));
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
					logProbability$sample88 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var66 = (logProbability$var66 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$2_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$2_2 = traceTempVariable$var72$2_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample88;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var65 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var66 = (logProbability$var66 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$3_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$3_2 = traceTempVariable$var72$3_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$sampleAccumulator = 0.0;
					double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double calculationVariable$probabilityReached = 0.0;
					{
						boolean calculationVariable$sampleValue = var69;
						{
							{
								double p_step_uses_secrets$var62;
								if(step_uses_secrets_deploy)
									p_step_uses_secrets$var62 = (0.65 * 0.9);
								else
									p_step_uses_secrets$var62 = 0.0;
								double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, p_step_uses_secrets$var62));
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
					logProbability$var68 = calculationVariable$sampleAccumulator;
					logProbability$sample91 = calculationVariable$sampleProbability;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var69 = (logProbability$var69 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$2_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$2_2 = traceTempVariable$var72$2_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample91)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample91 = fixedFlag$sample91;
		} else {
			double calculationVariable$accumulator = 0.0;
			if(ci_parsed) {
				if(deploy_command) {
					double calculationVariable$RVaccumulator = 0.0;
					double calculationVariable$sampleValue = logProbability$sample91;
					calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
					calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
					logProbability$var68 = calculationVariable$RVaccumulator;
				}
			}
			boolean calculationVariable$guard$deploy_command_certainty = false;
			logProbability$var69 = (logProbability$var69 + calculationVariable$accumulator);
			{
				if(ci_parsed) {
					if(deploy_command) {
						boolean traceTempVariable$var72$3_1 = ((var64 || var66) || var69);
						if(ci_parsed) {
							boolean traceTempVariable$deploy_command_certainty$3_2 = traceTempVariable$var72$3_1;
							if(!calculationVariable$guard$deploy_command_certainty) {
								calculationVariable$guard$deploy_command_certainty = true;
								logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$accumulator);
							}
						}
					}
				}
			}
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
					var32 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (0.8 * 0.9));
				double p_tested_deploy_action = 0.0;
				if(tested_deploy_action) {
					if(!fixedFlag$sample46)
						p_tested_deploy_action = (0.1 * 0.9);
				} else {
					if(!fixedFlag$sample46)
						p_tested_deploy_action = 0.0;
				}
				if(!fixedFlag$sample46)
					var34 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_tested_deploy_action);
				double p_release_workflow_trigger_deploy$var25 = 0.0;
				if(release_workflow_trigger_deploy) {
					double var23 = 0.0;
					if(invalid_trigger) {
						if(!fixedFlag$sample49)
							var23 = (0.75 * 0.85);
					} else {
						if(!fixedFlag$sample49)
							var23 = (0.9 * 0.85);
					}
					if(!fixedFlag$sample49)
						p_release_workflow_trigger_deploy$var25 = var23;
				} else {
					if(!fixedFlag$sample49)
						p_release_workflow_trigger_deploy$var25 = 0.0;
				}
				if(!fixedFlag$sample49)
					var37 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy$var25);
				double p_step_uses_secrets$var30 = 0.0;
				if(step_uses_secrets_deploy) {
					if(!fixedFlag$sample52)
						p_step_uses_secrets$var30 = (0.65 * 0.9);
				} else {
					if(!fixedFlag$sample52)
						p_step_uses_secrets$var30 = 0.0;
				}
				if(!fixedFlag$sample52)
					var40 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets$var30);
				if(!(((fixedFlag$sample44 && fixedFlag$sample46) && fixedFlag$sample49) && fixedFlag$sample52))
					deploy_action_certainty = (((var32 || var34) || var37) || var40);
			} else
				deploy_action_certainty = false;
		} else
			deploy_action_certainty = false;
		if(ci_parsed) {
			if(deploy_command) {
				if(!fixedFlag$sample86)
					var64 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (0.75 * 0.8));
				double p_release_workflow_trigger_deploy$var57 = 0.0;
				if(release_workflow_trigger_deploy) {
					double var55 = 0.0;
					if(invalid_trigger) {
						if(!fixedFlag$sample88)
							var55 = (0.75 * 0.85);
					} else {
						if(!fixedFlag$sample88)
							var55 = (0.9 * 0.85);
					}
					if(!fixedFlag$sample88)
						p_release_workflow_trigger_deploy$var57 = var55;
				} else {
					if(!fixedFlag$sample88)
						p_release_workflow_trigger_deploy$var57 = 0.0;
				}
				if(!fixedFlag$sample88)
					var66 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_release_workflow_trigger_deploy$var57);
				double p_step_uses_secrets$var62 = 0.0;
				if(step_uses_secrets_deploy) {
					if(!fixedFlag$sample91)
						p_step_uses_secrets$var62 = (0.65 * 0.9);
				} else {
					if(!fixedFlag$sample91)
						p_step_uses_secrets$var62 = 0.0;
				}
				if(!fixedFlag$sample91)
					var69 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, p_step_uses_secrets$var62);
				if(!((fixedFlag$sample86 && fixedFlag$sample88) && fixedFlag$sample91))
					deploy_command_certainty = ((var64 || var66) || var69);
			} else
				deploy_command_certainty = false;
		} else
			deploy_command_certainty = false;
		if((ci_parsed && deploy_kws)) {
			if(!fixedFlag$sample104)
				var80 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, (0.7 * 0.4));
			if(!fixedFlag$sample104)
				deploy_kws_certainty = var80;
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
		if(true) {
			if(ci_parsed) {
				boolean var43;
				if(deploy_action)
					var43 = (((var32 || var34) || var37) || var40);
				else
					var43 = false;
				deploy_action_certainty = var43;
			} else
				deploy_action_certainty = false;
		}
		if(true) {
			if(ci_parsed) {
				boolean var72;
				if(deploy_command)
					var72 = ((var64 || var66) || var69);
				else
					var72 = false;
				deploy_command_certainty = var72;
			} else
				deploy_command_certainty = false;
		}
		if(true) {
			if((ci_parsed && deploy_kws))
				deploy_kws_certainty = var80;
			else
				deploy_kws_certainty = false;
		}
	}

	@Override
	public String modelCode() {
		return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck4(boolean ci_parsed,\n                         boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy) \n{\n  //Calculate deploy_action_certainty\n  boolean deploy_action_certainty;\n  if(ci_parsed) {\n    if(deploy_action) {\n      double p_deploy_action = 0.8*0.9;\n      double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                 (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                 0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                bernoulli(p_tested_deploy_action).sample() ||\n                                bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_action_certainty = false;\n    }\n  } else {\n    deploy_action_certainty = false;\n  }\n\n  //Calculate deploy_command_certainty\n  boolean deploy_command_certainty;\n  if(ci_parsed) {\n    if(deploy_command) {\n      double p_deploy_command = 0.75*0.8;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                 bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                 bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_command_certainty = false;\n    }\n  } else {\n    deploy_command_certainty = false;\n  }\n\n  //Calculate deploy_kws_certainty\n  boolean deploy_kws_certainty;\n  if(ci_parsed && deploy_kws) {\n    double p_deploy_kws = 0.7*0.4;\n    deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n  } else\n    deploy_kws_certainty = false;\n}";
	}
}