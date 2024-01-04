package macaron;

import org.sandwood.runtime.model.ExecutionTarget;

class BuildCheck2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements BuildCheck2$CoreInterface {
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

	@Override
	public final boolean get$deploy_action_certainty() {
		return deploy_action_certainty;
	}

	@Override
	public final void set$deploy_action_certainty(boolean calculationVariable$value) {
		deploy_action_certainty = calculationVariable$value;
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
	public final boolean get$deploy_kws_certainty() {
		return deploy_kws_certainty;
	}

	@Override
	public final void set$deploy_kws_certainty(boolean calculationVariable$value) {
		deploy_kws_certainty = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean calculationVariable$value) {
		fixedFlag$sample12 = calculationVariable$value;
		fixedProbFlag$sample12 = (calculationVariable$value && fixedProbFlag$sample12);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean calculationVariable$value) {
		fixedFlag$sample16 = calculationVariable$value;
		fixedProbFlag$sample16 = (calculationVariable$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean calculationVariable$value) {
		fixedFlag$sample21 = calculationVariable$value;
		fixedProbFlag$sample21 = (calculationVariable$value && fixedProbFlag$sample21);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean calculationVariable$value) {
		fixedFlag$sample26 = calculationVariable$value;
		fixedProbFlag$sample26 = (calculationVariable$value && fixedProbFlag$sample26);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean calculationVariable$value) {
		fixedFlag$sample31 = calculationVariable$value;
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean calculationVariable$value) {
		fixedFlag$sample35 = calculationVariable$value;
		fixedProbFlag$sample35 = (calculationVariable$value && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample40() {
		return fixedFlag$sample40;
	}

	@Override
	public final void set$fixedFlag$sample40(boolean calculationVariable$value) {
		fixedFlag$sample40 = calculationVariable$value;
		fixedProbFlag$sample40 = (calculationVariable$value && fixedProbFlag$sample40);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean calculationVariable$value) {
		fixedFlag$sample45 = calculationVariable$value;
		fixedProbFlag$sample45 = (calculationVariable$value && fixedProbFlag$sample45);
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

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var12, (p_deploy_action * 0.8));
			logProbability$var11 = calculationVariable$distributionAccumulator;
			logProbability$var12 = calculationVariable$distributionAccumulator;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			logProbability$var11 = logProbability$var12;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var12);
			logProbability$$model = (logProbability$$model + logProbability$var12);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var12);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var16, (p_tested_deploy_action * 0.1));
			logProbability$var15 = calculationVariable$distributionAccumulator;
			logProbability$var16 = calculationVariable$distributionAccumulator;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			logProbability$var15 = logProbability$var16;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var16);
			logProbability$$model = (logProbability$$model + logProbability$var16);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var21, (p_release_workflow_trigger_deploy_action * 0.85));
			logProbability$var20 = calculationVariable$distributionAccumulator;
			logProbability$var21 = calculationVariable$distributionAccumulator;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			logProbability$var20 = logProbability$var21;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var21);
			logProbability$$model = (logProbability$$model + logProbability$var21);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var21);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var26, (p_step_uses_secrets_deploy_action * 0.65));
			logProbability$var25 = calculationVariable$distributionAccumulator;
			logProbability$var26 = calculationVariable$distributionAccumulator;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			logProbability$var25 = logProbability$var26;
			logProbability$deploy_action_certainty = (logProbability$deploy_action_certainty + logProbability$var26);
			logProbability$$model = (logProbability$$model + logProbability$var26);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var31, (p_deploy_command * 0.75));
			logProbability$var30 = calculationVariable$distributionAccumulator;
			logProbability$var31 = calculationVariable$distributionAccumulator;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var30 = logProbability$var31;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var31);
			logProbability$$model = (logProbability$$model + logProbability$var31);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var35, (p_release_workflow_trigger_deploy_command * 0.85));
			logProbability$var34 = calculationVariable$distributionAccumulator;
			logProbability$var35 = calculationVariable$distributionAccumulator;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var34 = logProbability$var35;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var35);
			logProbability$$model = (logProbability$$model + logProbability$var35);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
		}
	}

	private final void logProbabilityValue$sample40() {
		if(!fixedProbFlag$sample40) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(var40, (p_step_uses_secrets_deploy_command * 0.65));
			logProbability$var39 = calculationVariable$distributionAccumulator;
			logProbability$var40 = calculationVariable$distributionAccumulator;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample40)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample40 = fixedFlag$sample40;
		} else {
			logProbability$var39 = logProbability$var40;
			logProbability$deploy_command_certainty = (logProbability$deploy_command_certainty + logProbability$var40);
			logProbability$$model = (logProbability$$model + logProbability$var40);
			if(fixedFlag$sample40)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(deploy_kws_certainty, (p_deploy_kws * 0.7));
			logProbability$var44 = calculationVariable$distributionAccumulator;
			logProbability$deploy_kws_certainty = calculationVariable$distributionAccumulator;
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$var44 = logProbability$deploy_kws_certainty;
			logProbability$$model = (logProbability$$model + logProbability$deploy_kws_certainty);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$deploy_kws_certainty);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

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

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
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

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample26();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample40();
		logProbabilityValue$sample45();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample21();
		logProbabilityValue$sample26();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample40();
		logProbabilityValue$sample45();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

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