package macaron;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
 * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').
 *
 * A :: ci_parsed :- ci_parsed_check(A).
 * B :: deploy_action :- deploy_action_check(B).
 * C :: deploy_command :- deploy_command_check(C).
 * D :: deploy_kws :- deploy_kws_check(D).
 * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).
 * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).
 * G :: tested_deploy_action :- tested_deploy_action_check(G).
 * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).
 * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).
 * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).
 * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).
 *
 * 0.8 :: deploy_action_certainty :- deploy_action.
 * 0.10 :: deploy_action_certainty :- tested_deploy_action.
 * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.
 * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.
 * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.
 *
 * 0.75 :: deploy_command_certainty :- deploy_command.
 * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.
 * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.
 * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.
 *
 * 0.70 :: deploy_kws_certainty :- deploy_kws.
 *
 * query(deploy_command_certainty).
 * query(deploy_action_certainty).
 * query(deploy_kws_certainty).
 */
public class BuildCheck4 extends Model {

    private BuildCheck4$CoreInterface system$c = new BuildCheck4$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $deploy_action_certainty = new ComputedBooleanInternal(this, "deploy_action_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_action_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_action_certainty(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_action_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample44(fixed);
                system$c.set$fixedFlag$sample46(fixed);
                system$c.set$fixedFlag$sample49(fixed);
                system$c.set$fixedFlag$sample52(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample44 = system$c.get$fixedFlag$sample44();
            boolean fixedFlag$sample46 = system$c.get$fixedFlag$sample46();
            boolean fixedFlag$sample49 = system$c.get$fixedFlag$sample49();
            boolean fixedFlag$sample52 = system$c.get$fixedFlag$sample52();
            if(fixedFlag$sample44 && fixedFlag$sample46 && fixedFlag$sample49 && fixedFlag$sample52)
                return Immutability.FIXED;
            else if(fixedFlag$sample44 || fixedFlag$sample46 || fixedFlag$sample49 || fixedFlag$sample52)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_action_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_action_certainty = $deploy_action_certainty;

    private final ComputedBooleanInternal $deploy_command_certainty = new ComputedBooleanInternal(this, "deploy_command_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_command_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_command_certainty(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_command_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample86(fixed);
                system$c.set$fixedFlag$sample88(fixed);
                system$c.set$fixedFlag$sample91(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample86 = system$c.get$fixedFlag$sample86();
            boolean fixedFlag$sample88 = system$c.get$fixedFlag$sample88();
            boolean fixedFlag$sample91 = system$c.get$fixedFlag$sample91();
            if(fixedFlag$sample86 && fixedFlag$sample88 && fixedFlag$sample91)
                return Immutability.FIXED;
            else if(fixedFlag$sample86 || fixedFlag$sample88 || fixedFlag$sample91)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_command_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_command_certainty = $deploy_command_certainty;

    private final ComputedBooleanInternal $deploy_kws_certainty = new ComputedBooleanInternal(this, "deploy_kws_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_kws_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_kws_certainty(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_kws_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample104(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample104())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_kws_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_kws_certainty = $deploy_kws_certainty;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedBooleanInternal $ci_parsed = new ObservedBooleanInternal(this, "ci_parsed") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$ci_parsed();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$ci_parsed(value); }
    };

    /**
     * Observed variable representing ci_parsed of type boolean from the Sandwood model 
     */
    public final ObservedBoolean ci_parsed = $ci_parsed;

    private final ObservedBooleanInternal $deploy_action = new ObservedBooleanInternal(this, "deploy_action") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$deploy_action();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$deploy_action(value); }
    };

    /**
     * Observed variable representing deploy_action of type boolean from the Sandwood model 
     */
    public final ObservedBoolean deploy_action = $deploy_action;

    private final ObservedBooleanInternal $deploy_command = new ObservedBooleanInternal(this, "deploy_command") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$deploy_command();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$deploy_command(value); }
    };

    /**
     * Observed variable representing deploy_command of type boolean from the Sandwood model 
     */
    public final ObservedBoolean deploy_command = $deploy_command;

    private final ObservedBooleanInternal $deploy_kws = new ObservedBooleanInternal(this, "deploy_kws") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$deploy_kws();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$deploy_kws(value); }
    };

    /**
     * Observed variable representing deploy_kws of type boolean from the Sandwood model 
     */
    public final ObservedBoolean deploy_kws = $deploy_kws;

    private final ObservedBooleanInternal $invalid_trigger = new ObservedBooleanInternal(this, "invalid_trigger") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$invalid_trigger();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$invalid_trigger(value); }
    };

    /**
     * Observed variable representing invalid_trigger of type boolean from the Sandwood model 
     */
    public final ObservedBoolean invalid_trigger = $invalid_trigger;

    private final ObservedBooleanInternal $release_workflow_trigger_deploy = new ObservedBooleanInternal(this, "release_workflow_trigger_deploy") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$release_workflow_trigger_deploy();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$release_workflow_trigger_deploy(value); }
    };

    /**
     * Observed variable representing release_workflow_trigger_deploy of type boolean from the Sandwood model 
     */
    public final ObservedBoolean release_workflow_trigger_deploy = $release_workflow_trigger_deploy;

    private final ObservedBooleanInternal $step_uses_secrets_deploy = new ObservedBooleanInternal(this, "step_uses_secrets_deploy") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$step_uses_secrets_deploy();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$step_uses_secrets_deploy(value); }
    };

    /**
     * Observed variable representing step_uses_secrets_deploy of type boolean from the Sandwood model 
     */
    public final ObservedBoolean step_uses_secrets_deploy = $step_uses_secrets_deploy;

    private final ObservedBooleanInternal $tested_deploy_action = new ObservedBooleanInternal(this, "tested_deploy_action") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$tested_deploy_action();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$tested_deploy_action(value); }
    };

    /**
     * Observed variable representing tested_deploy_action of type boolean from the Sandwood model 
     */
    public final ObservedBoolean tested_deploy_action = $tested_deploy_action;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$deploy_action_certainty, $deploy_command_certainty, $deploy_kws_certainty};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public BuildCheck4() {
        super();
        //ComputedVariable
        $computedVariables.put("deploy_action_certainty", $deploy_action_certainty);
        $computedVariables.put("deploy_command_certainty", $deploy_command_certainty);
        $computedVariables.put("deploy_kws_certainty", $deploy_kws_certainty);

        //ModelInputs
        $modelInputs.put("ci_parsed", $ci_parsed);
        $modelInputs.put("deploy_action", $deploy_action);
        $modelInputs.put("deploy_command", $deploy_command);
        $modelInputs.put("deploy_kws", $deploy_kws);
        $modelInputs.put("invalid_trigger", $invalid_trigger);
        $modelInputs.put("release_workflow_trigger_deploy", $release_workflow_trigger_deploy);
        $modelInputs.put("step_uses_secrets_deploy", $step_uses_secrets_deploy);
        $modelInputs.put("tested_deploy_action", $tested_deploy_action);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param ci_parsed The value to set ci_parsed to.
      * @param deploy_action The value to set deploy_action to.
      * @param deploy_command The value to set deploy_command to.
      * @param deploy_kws The value to set deploy_kws to.
      * @param release_workflow_trigger_deploy The value to set release_workflow_trigger_deploy to.
      * @param invalid_trigger The value to set invalid_trigger to.
      * @param tested_deploy_action The value to set tested_deploy_action to.
      * @param step_uses_secrets_deploy The value to set step_uses_secrets_deploy to.
      */

    public BuildCheck4(boolean ci_parsed, boolean deploy_action, boolean deploy_command, boolean deploy_kws, boolean release_workflow_trigger_deploy, boolean invalid_trigger, boolean tested_deploy_action, boolean step_uses_secrets_deploy) {
        this();
        this.ci_parsed.set(ci_parsed);
        this.deploy_action.set(deploy_action);
        this.deploy_command.set(deploy_command);
        this.deploy_kws.set(deploy_kws);
        this.release_workflow_trigger_deploy.set(release_workflow_trigger_deploy);
        this.invalid_trigger.set(invalid_trigger);
        this.tested_deploy_action.set(tested_deploy_action);
        this.step_uses_secrets_deploy.set(step_uses_secrets_deploy);
    }
    
    @Override
    protected BuildCheck4$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        BuildCheck4$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new BuildCheck4$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new BuildCheck4$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(BuildCheck4$CoreInterface oldCore, BuildCheck4$CoreInterface newCore) {
        //Model inputs
        if(ci_parsed.isSet())
            newCore.set$ci_parsed(oldCore.get$ci_parsed());
        if(deploy_action.isSet())
            newCore.set$deploy_action(oldCore.get$deploy_action());
        if(deploy_command.isSet())
            newCore.set$deploy_command(oldCore.get$deploy_command());
        if(deploy_kws.isSet())
            newCore.set$deploy_kws(oldCore.get$deploy_kws());
        if(invalid_trigger.isSet())
            newCore.set$invalid_trigger(oldCore.get$invalid_trigger());
        if(release_workflow_trigger_deploy.isSet())
            newCore.set$release_workflow_trigger_deploy(oldCore.get$release_workflow_trigger_deploy());
        if(step_uses_secrets_deploy.isSet())
            newCore.set$step_uses_secrets_deploy(oldCore.get$step_uses_secrets_deploy());
        if(tested_deploy_action.isSet())
            newCore.set$tested_deploy_action(oldCore.get$tested_deploy_action());

        //ComputedVariables
        if(deploy_action_certainty.isSet())
            newCore.set$deploy_action_certainty(oldCore.get$deploy_action_certainty());
        if(deploy_command_certainty.isSet())
            newCore.set$deploy_command_certainty(oldCore.get$deploy_command_certainty());
        if(deploy_kws_certainty.isSet())
            newCore.set$deploy_kws_certainty(oldCore.get$deploy_kws_certainty());

        //Set fixed flags
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input ci_parsed */
        public final boolean ci_parsed;
        /** Field holding the value of model input deploy_action */
        public final boolean deploy_action;
        /** Field holding the value of model input deploy_command */
        public final boolean deploy_command;
        /** Field holding the value of model input deploy_kws */
        public final boolean deploy_kws;
        /** Field holding the value of model input release_workflow_trigger_deploy */
        public final boolean release_workflow_trigger_deploy;
        /** Field holding the value of model input invalid_trigger */
        public final boolean invalid_trigger;
        /** Field holding the value of model input tested_deploy_action */
        public final boolean tested_deploy_action;
        /** Field holding the value of model input step_uses_secrets_deploy */
        public final boolean step_uses_secrets_deploy;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param ci_parsed The value to set ci_parsed to.
          * @param deploy_action The value to set deploy_action to.
          * @param deploy_command The value to set deploy_command to.
          * @param deploy_kws The value to set deploy_kws to.
          * @param release_workflow_trigger_deploy The value to set release_workflow_trigger_deploy to.
          * @param invalid_trigger The value to set invalid_trigger to.
          * @param tested_deploy_action The value to set tested_deploy_action to.
          * @param step_uses_secrets_deploy The value to set step_uses_secrets_deploy to.
          */
        public InferValueInputs(boolean ci_parsed, boolean deploy_action, boolean deploy_command, boolean deploy_kws, boolean release_workflow_trigger_deploy, boolean invalid_trigger, boolean tested_deploy_action, boolean step_uses_secrets_deploy) {
            this.ci_parsed = ci_parsed;
            this.deploy_action = deploy_action;
            this.deploy_command = deploy_command;
            this.deploy_kws = deploy_kws;
            this.invalid_trigger = invalid_trigger;
            this.release_workflow_trigger_deploy = release_workflow_trigger_deploy;
            this.step_uses_secrets_deploy = step_uses_secrets_deploy;
            this.tested_deploy_action = tested_deploy_action;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input ci_parsed */
        public final boolean ci_parsed;
        /** Field holding the value of model input deploy_action */
        public final boolean deploy_action;
        /** Field holding the value of model input deploy_command */
        public final boolean deploy_command;
        /** Field holding the value of model input deploy_kws */
        public final boolean deploy_kws;
        /** Field holding the value of model input release_workflow_trigger_deploy */
        public final boolean release_workflow_trigger_deploy;
        /** Field holding the value of model input invalid_trigger */
        public final boolean invalid_trigger;
        /** Field holding the value of model input tested_deploy_action */
        public final boolean tested_deploy_action;
        /** Field holding the value of model input step_uses_secrets_deploy */
        public final boolean step_uses_secrets_deploy;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param ci_parsed The value to set ci_parsed to.
          * @param deploy_action The value to set deploy_action to.
          * @param deploy_command The value to set deploy_command to.
          * @param deploy_kws The value to set deploy_kws to.
          * @param release_workflow_trigger_deploy The value to set release_workflow_trigger_deploy to.
          * @param invalid_trigger The value to set invalid_trigger to.
          * @param tested_deploy_action The value to set tested_deploy_action to.
          * @param step_uses_secrets_deploy The value to set step_uses_secrets_deploy to.
          */
        public AllInputs(boolean ci_parsed, boolean deploy_action, boolean deploy_command, boolean deploy_kws, boolean release_workflow_trigger_deploy, boolean invalid_trigger, boolean tested_deploy_action, boolean step_uses_secrets_deploy) {
            this.ci_parsed = ci_parsed;
            this.deploy_action = deploy_action;
            this.deploy_command = deploy_command;
            this.deploy_kws = deploy_kws;
            this.release_workflow_trigger_deploy = release_workflow_trigger_deploy;
            this.invalid_trigger = invalid_trigger;
            this.tested_deploy_action = tested_deploy_action;
            this.step_uses_secrets_deploy = step_uses_secrets_deploy;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of deploy_action_certainty after a convention execution step.*/
        public final boolean deploy_action_certainty;
        /** Field holding the value of deploy_command_certainty after a convention execution step.*/
        public final boolean deploy_command_certainty;
        /** Field holding the value of deploy_kws_certainty after a convention execution step.*/
        public final boolean deploy_kws_certainty;

        InferredValueOutputs(BuildCheck4 system$model) {
            this.deploy_action_certainty = system$model.deploy_action_certainty.getSamples()[0];
            this.deploy_command_certainty = system$model.deploy_command_certainty.getSamples()[0];
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable deploy_action_certainty */
        public final double deploy_action_certainty;
        /** Field holding the log probability of computed variable deploy_command_certainty */
        public final double deploy_command_certainty;
        /** Field holding the log probability of computed variable deploy_kws_certainty */
        public final double deploy_kws_certainty;

        LogProbabilities(BuildCheck4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.deploy_action_certainty = system$model.deploy_action_certainty.getLogProbability();
            this.deploy_command_certainty = system$model.deploy_command_certainty.getLogProbability();
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getLogProbability();
        }

        /** Method to return log probability of the whole model 
         *  @return The log probability of the whole model. */
        public double getModelProbability() { return $logModelProbability; }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class Probabilities {
        private final double $modelProbability;
        /** Field holding the probability of computed variable deploy_action_certainty */
        public final double deploy_action_certainty;
        /** Field holding the probability of computed variable deploy_command_certainty */
        public final double deploy_command_certainty;
        /** Field holding the probability of computed variable deploy_kws_certainty */
        public final double deploy_kws_certainty;

        Probabilities(BuildCheck4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.deploy_action_certainty = system$model.deploy_action_certainty.getProbability();
            this.deploy_command_certainty = system$model.deploy_command_certainty.getProbability();
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of deploy_action_certainty after an infer model call. */
        public final boolean[] deploy_action_certainty;
        /** Field holding the MAP or Sample value of deploy_command_certainty after an infer model call. */
        public final boolean[] deploy_command_certainty;
        /** Field holding the MAP or Sample value of deploy_kws_certainty after an infer model call. */
        public final boolean[] deploy_kws_certainty;

        InferredModelOutputs(BuildCheck4 system$model) {
            this.deploy_action_certainty = system$model.getInferredValue(system$model.$deploy_action_certainty);
            this.deploy_command_certainty = system$model.getInferredValue(system$model.$deploy_command_certainty);
            this.deploy_kws_certainty = system$model.getInferredValue(system$model.$deploy_kws_certainty);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        execute();
        return new InferredValueOutputs(this);
    }

    /**
     * Infer the values of the different elements of the model.
     * @param iterations The number of iterations to perform when inferring the values.
     * @param inputs An object containing the parameters required to generate the model parameters.
     * @return An object containing the computed values for the model.
     */
    public InferredModelOutputs inferValues(int iterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferValues(iterations);
        return new InferredModelOutputs(this);
    }

    /**
     * Generate the probabilities of the different elements of the model.
     * @param iterations How many iterations should be used to generate these values?
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(int iterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(iterations);
        return new Probabilities(this);
    }

    /**
     * Calculate the probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(variance, initialIterations);
        return new Probabilities(this);
    }

    /**
     * Calculate the probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not
     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new Probabilities(this);
    }

    /**
     * Generate the log probabilities of the different elements of the model.
     * @param iterations How many iterations should be used to generate these values?
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(int iterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(iterations);
        return new LogProbabilities(this);
    }

    /**
     * Calculate the log probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(variance, initialIterations);
        return new LogProbabilities(this);
    }

    /**
     * Calculate the log probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not
     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.ci_parsed.set(inputs.ci_parsed);
        this.deploy_action.set(inputs.deploy_action);
        this.deploy_command.set(inputs.deploy_command);
        this.deploy_kws.set(inputs.deploy_kws);
        this.invalid_trigger.set(inputs.invalid_trigger);
        this.release_workflow_trigger_deploy.set(inputs.release_workflow_trigger_deploy);
        this.step_uses_secrets_deploy.set(inputs.step_uses_secrets_deploy);
        this.tested_deploy_action.set(inputs.tested_deploy_action);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
