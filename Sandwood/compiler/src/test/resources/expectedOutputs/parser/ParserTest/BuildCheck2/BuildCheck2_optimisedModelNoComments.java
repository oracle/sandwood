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
public class BuildCheck2 extends Model {

    private BuildCheck2$CoreInterface system$c = new BuildCheck2$SingleThreadCPU(ExecutionTarget.singleThread);

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
                system$c.set$fixedFlag$sample12(fixed);
                system$c.set$fixedFlag$sample16(fixed);
                system$c.set$fixedFlag$sample21(fixed);
                system$c.set$fixedFlag$sample26(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample12 = system$c.get$fixedFlag$sample12();
            boolean fixedFlag$sample16 = system$c.get$fixedFlag$sample16();
            boolean fixedFlag$sample21 = system$c.get$fixedFlag$sample21();
            boolean fixedFlag$sample26 = system$c.get$fixedFlag$sample26();
            if(fixedFlag$sample12 && fixedFlag$sample16 && fixedFlag$sample21 && fixedFlag$sample26)
                return Immutability.FIXED;
            else if(fixedFlag$sample12 || fixedFlag$sample16 || fixedFlag$sample21 || fixedFlag$sample26)
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
                system$c.set$fixedFlag$sample31(fixed);
                system$c.set$fixedFlag$sample35(fixed);
                system$c.set$fixedFlag$sample40(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample31 = system$c.get$fixedFlag$sample31();
            boolean fixedFlag$sample35 = system$c.get$fixedFlag$sample35();
            boolean fixedFlag$sample40 = system$c.get$fixedFlag$sample40();
            if(fixedFlag$sample31 && fixedFlag$sample35 && fixedFlag$sample40)
                return Immutability.FIXED;
            else if(fixedFlag$sample31 || fixedFlag$sample35 || fixedFlag$sample40)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_command_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_command_certainty = $deploy_command_certainty;

    private final ComputedBooleanInternal $deploy_kws_certainty = new ComputedBooleanInternal(this, "deploy_kws_certainty", true) {
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
                system$c.set$fixedFlag$sample45(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample45())
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

    private final ObservedDoubleInternal $p_deploy_action = new ObservedDoubleInternal(this, "p_deploy_action") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_deploy_action();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_deploy_action(value); }
    };

    /**
     * Observed variable representing p_deploy_action of type double from the Sandwood model 
     */
    public final ObservedDouble p_deploy_action = $p_deploy_action;

    private final ObservedDoubleInternal $p_deploy_command = new ObservedDoubleInternal(this, "p_deploy_command") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_deploy_command();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_deploy_command(value); }
    };

    /**
     * Observed variable representing p_deploy_command of type double from the Sandwood model 
     */
    public final ObservedDouble p_deploy_command = $p_deploy_command;

    private final ObservedDoubleInternal $p_deploy_kws = new ObservedDoubleInternal(this, "p_deploy_kws") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_deploy_kws();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_deploy_kws(value); }
    };

    /**
     * Observed variable representing p_deploy_kws of type double from the Sandwood model 
     */
    public final ObservedDouble p_deploy_kws = $p_deploy_kws;

    private final ObservedDoubleInternal $p_release_workflow_trigger_deploy_action = new ObservedDoubleInternal(this, "p_release_workflow_trigger_deploy_action") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_release_workflow_trigger_deploy_action();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_release_workflow_trigger_deploy_action(value); }
    };

    /**
     * Observed variable representing p_release_workflow_trigger_deploy_action of type double from the Sandwood model 
     */
    public final ObservedDouble p_release_workflow_trigger_deploy_action = $p_release_workflow_trigger_deploy_action;

    private final ObservedDoubleInternal $p_release_workflow_trigger_deploy_command = new ObservedDoubleInternal(this, "p_release_workflow_trigger_deploy_command") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_release_workflow_trigger_deploy_command();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_release_workflow_trigger_deploy_command(value); }
    };

    /**
     * Observed variable representing p_release_workflow_trigger_deploy_command of type double from the Sandwood model 
     */
    public final ObservedDouble p_release_workflow_trigger_deploy_command = $p_release_workflow_trigger_deploy_command;

    private final ObservedDoubleInternal $p_step_uses_secrets_deploy_action = new ObservedDoubleInternal(this, "p_step_uses_secrets_deploy_action") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_step_uses_secrets_deploy_action();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_step_uses_secrets_deploy_action(value); }
    };

    /**
     * Observed variable representing p_step_uses_secrets_deploy_action of type double from the Sandwood model 
     */
    public final ObservedDouble p_step_uses_secrets_deploy_action = $p_step_uses_secrets_deploy_action;

    private final ObservedDoubleInternal $p_step_uses_secrets_deploy_command = new ObservedDoubleInternal(this, "p_step_uses_secrets_deploy_command") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_step_uses_secrets_deploy_command();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_step_uses_secrets_deploy_command(value); }
    };

    /**
     * Observed variable representing p_step_uses_secrets_deploy_command of type double from the Sandwood model 
     */
    public final ObservedDouble p_step_uses_secrets_deploy_command = $p_step_uses_secrets_deploy_command;

    private final ObservedDoubleInternal $p_tested_deploy_action = new ObservedDoubleInternal(this, "p_tested_deploy_action") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$p_tested_deploy_action();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$p_tested_deploy_action(value); }
    };

    /**
     * Observed variable representing p_tested_deploy_action of type double from the Sandwood model 
     */
    public final ObservedDouble p_tested_deploy_action = $p_tested_deploy_action;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$deploy_action_certainty, $deploy_command_certainty, $deploy_kws_certainty};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public BuildCheck2() {
        super();
        //ComputedVariable
        $computedVariables.put("deploy_action_certainty", $deploy_action_certainty);
        $computedVariables.put("deploy_command_certainty", $deploy_command_certainty);
        $computedVariables.put("deploy_kws_certainty", $deploy_kws_certainty);

        //ModelInputs
        $modelInputs.put("p_deploy_action", $p_deploy_action);
        $modelInputs.put("p_deploy_command", $p_deploy_command);
        $modelInputs.put("p_deploy_kws", $p_deploy_kws);
        $modelInputs.put("p_release_workflow_trigger_deploy_action", $p_release_workflow_trigger_deploy_action);
        $modelInputs.put("p_release_workflow_trigger_deploy_command", $p_release_workflow_trigger_deploy_command);
        $modelInputs.put("p_step_uses_secrets_deploy_action", $p_step_uses_secrets_deploy_action);
        $modelInputs.put("p_step_uses_secrets_deploy_command", $p_step_uses_secrets_deploy_command);
        $modelInputs.put("p_tested_deploy_action", $p_tested_deploy_action);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param p_deploy_action The value to set p_deploy_action to.
      * @param p_deploy_command The value to set p_deploy_command to.
      * @param p_deploy_kws The value to set p_deploy_kws to.
      * @param p_release_workflow_trigger_deploy_command The value to set p_release_workflow_trigger_deploy_command to.
      * @param p_release_workflow_trigger_deploy_action The value to set p_release_workflow_trigger_deploy_action to.
      * @param p_tested_deploy_action The value to set p_tested_deploy_action to.
      * @param p_step_uses_secrets_deploy_action The value to set p_step_uses_secrets_deploy_action to.
      * @param p_step_uses_secrets_deploy_command The value to set p_step_uses_secrets_deploy_command to.
      */

    public BuildCheck2(double p_deploy_action, double p_deploy_command, double p_deploy_kws, double p_release_workflow_trigger_deploy_command, double p_release_workflow_trigger_deploy_action, double p_tested_deploy_action, double p_step_uses_secrets_deploy_action, double p_step_uses_secrets_deploy_command) {
        this();
        this.p_deploy_action.set(p_deploy_action);
        this.p_deploy_command.set(p_deploy_command);
        this.p_deploy_kws.set(p_deploy_kws);
        this.p_release_workflow_trigger_deploy_command.set(p_release_workflow_trigger_deploy_command);
        this.p_release_workflow_trigger_deploy_action.set(p_release_workflow_trigger_deploy_action);
        this.p_tested_deploy_action.set(p_tested_deploy_action);
        this.p_step_uses_secrets_deploy_action.set(p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(p_step_uses_secrets_deploy_command);
    }
    
    @Override
    protected BuildCheck2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        BuildCheck2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new BuildCheck2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new BuildCheck2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(BuildCheck2$CoreInterface oldCore, BuildCheck2$CoreInterface newCore) {
        //Model inputs
        if(p_deploy_action.isSet())
            newCore.set$p_deploy_action(oldCore.get$p_deploy_action());
        if(p_deploy_command.isSet())
            newCore.set$p_deploy_command(oldCore.get$p_deploy_command());
        if(p_deploy_kws.isSet())
            newCore.set$p_deploy_kws(oldCore.get$p_deploy_kws());
        if(p_release_workflow_trigger_deploy_action.isSet())
            newCore.set$p_release_workflow_trigger_deploy_action(oldCore.get$p_release_workflow_trigger_deploy_action());
        if(p_release_workflow_trigger_deploy_command.isSet())
            newCore.set$p_release_workflow_trigger_deploy_command(oldCore.get$p_release_workflow_trigger_deploy_command());
        if(p_step_uses_secrets_deploy_action.isSet())
            newCore.set$p_step_uses_secrets_deploy_action(oldCore.get$p_step_uses_secrets_deploy_action());
        if(p_step_uses_secrets_deploy_command.isSet())
            newCore.set$p_step_uses_secrets_deploy_command(oldCore.get$p_step_uses_secrets_deploy_command());
        if(p_tested_deploy_action.isSet())
            newCore.set$p_tested_deploy_action(oldCore.get$p_tested_deploy_action());

        //ComputedVariables
        if(deploy_action_certainty.isSet())
            newCore.set$deploy_action_certainty(oldCore.get$deploy_action_certainty());
        if(deploy_command_certainty.isSet())
            newCore.set$deploy_command_certainty(oldCore.get$deploy_command_certainty());
        if(deploy_kws_certainty.isSet())
            newCore.set$deploy_kws_certainty(oldCore.get$deploy_kws_certainty());

        //Set fixed flags
        if(deploy_kws_certainty.isSet())
            newCore.set$fixedFlag$sample45(oldCore.get$fixedFlag$sample45());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input p_deploy_action */
        public final double p_deploy_action;
        /** Field holding the value of model input p_deploy_command */
        public final double p_deploy_command;
        /** Field holding the value of model input p_deploy_kws */
        public final double p_deploy_kws;
        /** Field holding the value of model input p_release_workflow_trigger_deploy_command */
        public final double p_release_workflow_trigger_deploy_command;
        /** Field holding the value of model input p_release_workflow_trigger_deploy_action */
        public final double p_release_workflow_trigger_deploy_action;
        /** Field holding the value of model input p_tested_deploy_action */
        public final double p_tested_deploy_action;
        /** Field holding the value of model input p_step_uses_secrets_deploy_action */
        public final double p_step_uses_secrets_deploy_action;
        /** Field holding the value of model input p_step_uses_secrets_deploy_command */
        public final double p_step_uses_secrets_deploy_command;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param p_deploy_action The value to set p_deploy_action to.
          * @param p_deploy_command The value to set p_deploy_command to.
          * @param p_deploy_kws The value to set p_deploy_kws to.
          * @param p_release_workflow_trigger_deploy_command The value to set p_release_workflow_trigger_deploy_command to.
          * @param p_release_workflow_trigger_deploy_action The value to set p_release_workflow_trigger_deploy_action to.
          * @param p_tested_deploy_action The value to set p_tested_deploy_action to.
          * @param p_step_uses_secrets_deploy_action The value to set p_step_uses_secrets_deploy_action to.
          * @param p_step_uses_secrets_deploy_command The value to set p_step_uses_secrets_deploy_command to.
          */
        public InferValueInputs(double p_deploy_action, double p_deploy_command, double p_deploy_kws, double p_release_workflow_trigger_deploy_command, double p_release_workflow_trigger_deploy_action, double p_tested_deploy_action, double p_step_uses_secrets_deploy_action, double p_step_uses_secrets_deploy_command) {
            this.p_deploy_action = p_deploy_action;
            this.p_deploy_command = p_deploy_command;
            this.p_deploy_kws = p_deploy_kws;
            this.p_release_workflow_trigger_deploy_action = p_release_workflow_trigger_deploy_action;
            this.p_release_workflow_trigger_deploy_command = p_release_workflow_trigger_deploy_command;
            this.p_step_uses_secrets_deploy_action = p_step_uses_secrets_deploy_action;
            this.p_step_uses_secrets_deploy_command = p_step_uses_secrets_deploy_command;
            this.p_tested_deploy_action = p_tested_deploy_action;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input p_deploy_action */
        public final double p_deploy_action;
        /** Field holding the value of model input p_deploy_command */
        public final double p_deploy_command;
        /** Field holding the value of model input p_deploy_kws */
        public final double p_deploy_kws;
        /** Field holding the value of model input p_release_workflow_trigger_deploy_command */
        public final double p_release_workflow_trigger_deploy_command;
        /** Field holding the value of model input p_release_workflow_trigger_deploy_action */
        public final double p_release_workflow_trigger_deploy_action;
        /** Field holding the value of model input p_tested_deploy_action */
        public final double p_tested_deploy_action;
        /** Field holding the value of model input p_step_uses_secrets_deploy_action */
        public final double p_step_uses_secrets_deploy_action;
        /** Field holding the value of model input p_step_uses_secrets_deploy_command */
        public final double p_step_uses_secrets_deploy_command;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param p_deploy_action The value to set p_deploy_action to.
          * @param p_deploy_command The value to set p_deploy_command to.
          * @param p_deploy_kws The value to set p_deploy_kws to.
          * @param p_release_workflow_trigger_deploy_command The value to set p_release_workflow_trigger_deploy_command to.
          * @param p_release_workflow_trigger_deploy_action The value to set p_release_workflow_trigger_deploy_action to.
          * @param p_tested_deploy_action The value to set p_tested_deploy_action to.
          * @param p_step_uses_secrets_deploy_action The value to set p_step_uses_secrets_deploy_action to.
          * @param p_step_uses_secrets_deploy_command The value to set p_step_uses_secrets_deploy_command to.
          */
        public AllInputs(double p_deploy_action, double p_deploy_command, double p_deploy_kws, double p_release_workflow_trigger_deploy_command, double p_release_workflow_trigger_deploy_action, double p_tested_deploy_action, double p_step_uses_secrets_deploy_action, double p_step_uses_secrets_deploy_command) {
            this.p_deploy_action = p_deploy_action;
            this.p_deploy_command = p_deploy_command;
            this.p_deploy_kws = p_deploy_kws;
            this.p_release_workflow_trigger_deploy_command = p_release_workflow_trigger_deploy_command;
            this.p_release_workflow_trigger_deploy_action = p_release_workflow_trigger_deploy_action;
            this.p_tested_deploy_action = p_tested_deploy_action;
            this.p_step_uses_secrets_deploy_action = p_step_uses_secrets_deploy_action;
            this.p_step_uses_secrets_deploy_command = p_step_uses_secrets_deploy_command;
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

        InferredValueOutputs(BuildCheck2 system$model) {
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

        LogProbabilities(BuildCheck2 system$model) {
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

        Probabilities(BuildCheck2 system$model) {
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

        InferredModelOutputs(BuildCheck2 system$model) {
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
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
        this.p_deploy_action.set(inputs.p_deploy_action);
        this.p_deploy_command.set(inputs.p_deploy_command);
        this.p_deploy_kws.set(inputs.p_deploy_kws);
        this.p_release_workflow_trigger_deploy_action.set(inputs.p_release_workflow_trigger_deploy_action);
        this.p_release_workflow_trigger_deploy_command.set(inputs.p_release_workflow_trigger_deploy_command);
        this.p_step_uses_secrets_deploy_action.set(inputs.p_step_uses_secrets_deploy_action);
        this.p_step_uses_secrets_deploy_command.set(inputs.p_step_uses_secrets_deploy_command);
        this.p_tested_deploy_action.set(inputs.p_tested_deploy_action);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
