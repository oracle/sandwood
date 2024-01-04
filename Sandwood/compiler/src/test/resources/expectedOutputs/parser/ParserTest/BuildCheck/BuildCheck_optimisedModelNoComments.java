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
public class BuildCheck extends Model {

    private BuildCheck$CoreInterface system$c = new BuildCheck$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $deploy_action = new ComputedBooleanInternal(this, "deploy_action", true) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_action(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_action(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_action(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample10(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample10())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_action of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_action = $deploy_action;

    private final ComputedBooleanInternal $deploy_action_certainty = new ComputedBooleanInternal(this, "deploy_action_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_action_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable deploy_action_certainty because its value depends on variables \"deploy_action\", \"release_workflow_trigger_deploy_action\", \"step_uses_secrets_deploy_action\", and \"tested_deploy_action\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_action_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample10(fixed);
                system$c.set$fixedFlag$sample12(fixed);
                system$c.set$fixedFlag$sample14(fixed);
                system$c.set$fixedFlag$sample16(fixed);
                system$c.set$fixedFlag$sample21(fixed);
                system$c.set$fixedFlag$sample28(fixed);
                system$c.set$fixedFlag$sample36(fixed);
                system$c.set$fixedFlag$sample44(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample10 = system$c.get$fixedFlag$sample10();
            boolean fixedFlag$sample12 = system$c.get$fixedFlag$sample12();
            boolean fixedFlag$sample14 = system$c.get$fixedFlag$sample14();
            boolean fixedFlag$sample16 = system$c.get$fixedFlag$sample16();
            boolean fixedFlag$sample21 = system$c.get$fixedFlag$sample21();
            boolean fixedFlag$sample28 = system$c.get$fixedFlag$sample28();
            boolean fixedFlag$sample36 = system$c.get$fixedFlag$sample36();
            boolean fixedFlag$sample44 = system$c.get$fixedFlag$sample44();
            if(fixedFlag$sample10 && fixedFlag$sample12 && fixedFlag$sample14 && fixedFlag$sample16 && fixedFlag$sample21 && fixedFlag$sample28 && fixedFlag$sample36 && fixedFlag$sample44)
                return Immutability.FIXED;
            else if(fixedFlag$sample10 || fixedFlag$sample12 || fixedFlag$sample14 || fixedFlag$sample16 || fixedFlag$sample21 || fixedFlag$sample28 || fixedFlag$sample36 || fixedFlag$sample44)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_action_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_action_certainty = $deploy_action_certainty;

    private final ComputedBooleanInternal $deploy_command = new ComputedBooleanInternal(this, "deploy_command", true) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_command(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_command(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_command(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample49(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample49())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_command of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_command = $deploy_command;

    private final ComputedBooleanInternal $deploy_command_certainty = new ComputedBooleanInternal(this, "deploy_command_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_command_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable deploy_command_certainty because its value depends on variables \"deploy_command\", \"release_workflow_trigger_deploy_command\", and \"step_uses_secrets_deploy_command\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_command_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample49(fixed);
                system$c.set$fixedFlag$sample51(fixed);
                system$c.set$fixedFlag$sample53(fixed);
                system$c.set$fixedFlag$sample58(fixed);
                system$c.set$fixedFlag$sample65(fixed);
                system$c.set$fixedFlag$sample73(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample49 = system$c.get$fixedFlag$sample49();
            boolean fixedFlag$sample51 = system$c.get$fixedFlag$sample51();
            boolean fixedFlag$sample53 = system$c.get$fixedFlag$sample53();
            boolean fixedFlag$sample58 = system$c.get$fixedFlag$sample58();
            boolean fixedFlag$sample65 = system$c.get$fixedFlag$sample65();
            boolean fixedFlag$sample73 = system$c.get$fixedFlag$sample73();
            if(fixedFlag$sample49 && fixedFlag$sample51 && fixedFlag$sample53 && fixedFlag$sample58 && fixedFlag$sample65 && fixedFlag$sample73)
                return Immutability.FIXED;
            else if(fixedFlag$sample49 || fixedFlag$sample51 || fixedFlag$sample53 || fixedFlag$sample58 || fixedFlag$sample65 || fixedFlag$sample73)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_command_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_command_certainty = $deploy_command_certainty;

    private final ComputedBooleanInternal $deploy_kws = new ComputedBooleanInternal(this, "deploy_kws", true) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_kws(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$deploy_kws(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_kws(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample78(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample78())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_kws of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_kws = $deploy_kws;

    private final ComputedBooleanInternal $deploy_kws_certainty = new ComputedBooleanInternal(this, "deploy_kws_certainty", false) {
        @Override
        protected boolean getValue() { return system$c.get$deploy_kws_certainty(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable deploy_kws_certainty because its value depends on variable \"deploy_kws\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$deploy_kws_certainty(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample78(fixed);
                system$c.set$fixedFlag$sample83(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample78 = system$c.get$fixedFlag$sample78();
            boolean fixedFlag$sample83 = system$c.get$fixedFlag$sample83();
            if(fixedFlag$sample78 && fixedFlag$sample83)
                return Immutability.FIXED;
            else if(fixedFlag$sample78 || fixedFlag$sample83)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing deploy_kws_certainty of type boolean from the Sandwood model 
     */
    public final ComputedBoolean deploy_kws_certainty = $deploy_kws_certainty;

    private final ComputedBooleanInternal $release_workflow_trigger_deploy_action = new ComputedBooleanInternal(this, "release_workflow_trigger_deploy_action", true) {
        @Override
        protected boolean getValue() { return system$c.get$release_workflow_trigger_deploy_action(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$release_workflow_trigger_deploy_action(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$release_workflow_trigger_deploy_action(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample14(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample14())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing release_workflow_trigger_deploy_action of type boolean from the Sandwood model 
     */
    public final ComputedBoolean release_workflow_trigger_deploy_action = $release_workflow_trigger_deploy_action;

    private final ComputedBooleanInternal $release_workflow_trigger_deploy_command = new ComputedBooleanInternal(this, "release_workflow_trigger_deploy_command", true) {
        @Override
        protected boolean getValue() { return system$c.get$release_workflow_trigger_deploy_command(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$release_workflow_trigger_deploy_command(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$release_workflow_trigger_deploy_command(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample51(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample51())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing release_workflow_trigger_deploy_command of type boolean from the Sandwood model 
     */
    public final ComputedBoolean release_workflow_trigger_deploy_command = $release_workflow_trigger_deploy_command;

    private final ComputedBooleanInternal $step_uses_secrets_deploy_action = new ComputedBooleanInternal(this, "step_uses_secrets_deploy_action", true) {
        @Override
        protected boolean getValue() { return system$c.get$step_uses_secrets_deploy_action(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$step_uses_secrets_deploy_action(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$step_uses_secrets_deploy_action(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample16(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample16())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing step_uses_secrets_deploy_action of type boolean from the Sandwood model 
     */
    public final ComputedBoolean step_uses_secrets_deploy_action = $step_uses_secrets_deploy_action;

    private final ComputedBooleanInternal $step_uses_secrets_deploy_command = new ComputedBooleanInternal(this, "step_uses_secrets_deploy_command", true) {
        @Override
        protected boolean getValue() { return system$c.get$step_uses_secrets_deploy_command(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$step_uses_secrets_deploy_command(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$step_uses_secrets_deploy_command(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample53(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample53())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing step_uses_secrets_deploy_command of type boolean from the Sandwood model 
     */
    public final ComputedBoolean step_uses_secrets_deploy_command = $step_uses_secrets_deploy_command;

    private final ComputedBooleanInternal $tested_deploy_action = new ComputedBooleanInternal(this, "tested_deploy_action", true) {
        @Override
        protected boolean getValue() { return system$c.get$tested_deploy_action(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$tested_deploy_action(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$tested_deploy_action(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample12(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample12())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing tested_deploy_action of type boolean from the Sandwood model 
     */
    public final ComputedBoolean tested_deploy_action = $tested_deploy_action;

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
    private HasProbabilityInternal[] $probabilityVariables = {$deploy_action, $deploy_action_certainty, $deploy_command, $deploy_command_certainty, $deploy_kws, $deploy_kws_certainty, $release_workflow_trigger_deploy_action, $release_workflow_trigger_deploy_command, $step_uses_secrets_deploy_action, $step_uses_secrets_deploy_command, $tested_deploy_action};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public BuildCheck() {
        super();
        //ComputedVariable
        $computedVariables.put("deploy_action", $deploy_action);
        $computedVariables.put("deploy_action_certainty", $deploy_action_certainty);
        $computedVariables.put("deploy_command", $deploy_command);
        $computedVariables.put("deploy_command_certainty", $deploy_command_certainty);
        $computedVariables.put("deploy_kws", $deploy_kws);
        $computedVariables.put("deploy_kws_certainty", $deploy_kws_certainty);
        $computedVariables.put("release_workflow_trigger_deploy_action", $release_workflow_trigger_deploy_action);
        $computedVariables.put("release_workflow_trigger_deploy_command", $release_workflow_trigger_deploy_command);
        $computedVariables.put("step_uses_secrets_deploy_action", $step_uses_secrets_deploy_action);
        $computedVariables.put("step_uses_secrets_deploy_command", $step_uses_secrets_deploy_command);
        $computedVariables.put("tested_deploy_action", $tested_deploy_action);

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

    public BuildCheck(double p_deploy_action, double p_deploy_command, double p_deploy_kws, double p_release_workflow_trigger_deploy_command, double p_release_workflow_trigger_deploy_action, double p_tested_deploy_action, double p_step_uses_secrets_deploy_action, double p_step_uses_secrets_deploy_command) {
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
    protected BuildCheck$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        BuildCheck$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new BuildCheck$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new BuildCheck$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(BuildCheck$CoreInterface oldCore, BuildCheck$CoreInterface newCore) {
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
        if(deploy_action.isSet())
            newCore.set$deploy_action(oldCore.get$deploy_action());
        if(deploy_action_certainty.isSet())
            newCore.set$deploy_action_certainty(oldCore.get$deploy_action_certainty());
        if(deploy_command.isSet())
            newCore.set$deploy_command(oldCore.get$deploy_command());
        if(deploy_command_certainty.isSet())
            newCore.set$deploy_command_certainty(oldCore.get$deploy_command_certainty());
        if(deploy_kws.isSet())
            newCore.set$deploy_kws(oldCore.get$deploy_kws());
        if(deploy_kws_certainty.isSet())
            newCore.set$deploy_kws_certainty(oldCore.get$deploy_kws_certainty());
        if(release_workflow_trigger_deploy_action.isSet())
            newCore.set$release_workflow_trigger_deploy_action(oldCore.get$release_workflow_trigger_deploy_action());
        if(release_workflow_trigger_deploy_command.isSet())
            newCore.set$release_workflow_trigger_deploy_command(oldCore.get$release_workflow_trigger_deploy_command());
        if(step_uses_secrets_deploy_action.isSet())
            newCore.set$step_uses_secrets_deploy_action(oldCore.get$step_uses_secrets_deploy_action());
        if(step_uses_secrets_deploy_command.isSet())
            newCore.set$step_uses_secrets_deploy_command(oldCore.get$step_uses_secrets_deploy_command());
        if(tested_deploy_action.isSet())
            newCore.set$tested_deploy_action(oldCore.get$tested_deploy_action());

        //Set fixed flags
        if(deploy_action.isSet())
            newCore.set$fixedFlag$sample10(oldCore.get$fixedFlag$sample10());
        if(deploy_command.isSet())
            newCore.set$fixedFlag$sample49(oldCore.get$fixedFlag$sample49());
        if(deploy_kws.isSet())
            newCore.set$fixedFlag$sample78(oldCore.get$fixedFlag$sample78());
        if(release_workflow_trigger_deploy_action.isSet())
            newCore.set$fixedFlag$sample14(oldCore.get$fixedFlag$sample14());
        if(release_workflow_trigger_deploy_command.isSet())
            newCore.set$fixedFlag$sample51(oldCore.get$fixedFlag$sample51());
        if(step_uses_secrets_deploy_action.isSet())
            newCore.set$fixedFlag$sample16(oldCore.get$fixedFlag$sample16());
        if(step_uses_secrets_deploy_command.isSet())
            newCore.set$fixedFlag$sample53(oldCore.get$fixedFlag$sample53());
        if(tested_deploy_action.isSet())
            newCore.set$fixedFlag$sample12(oldCore.get$fixedFlag$sample12());
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
        /** Field holding the value of deploy_action after a convention execution step.*/
        public final boolean deploy_action;
        /** Field holding the value of deploy_action_certainty after a convention execution step.*/
        public final boolean deploy_action_certainty;
        /** Field holding the value of deploy_command after a convention execution step.*/
        public final boolean deploy_command;
        /** Field holding the value of deploy_command_certainty after a convention execution step.*/
        public final boolean deploy_command_certainty;
        /** Field holding the value of deploy_kws after a convention execution step.*/
        public final boolean deploy_kws;
        /** Field holding the value of deploy_kws_certainty after a convention execution step.*/
        public final boolean deploy_kws_certainty;
        /** Field holding the value of release_workflow_trigger_deploy_action after a convention execution step.*/
        public final boolean release_workflow_trigger_deploy_action;
        /** Field holding the value of release_workflow_trigger_deploy_command after a convention execution step.*/
        public final boolean release_workflow_trigger_deploy_command;
        /** Field holding the value of step_uses_secrets_deploy_action after a convention execution step.*/
        public final boolean step_uses_secrets_deploy_action;
        /** Field holding the value of step_uses_secrets_deploy_command after a convention execution step.*/
        public final boolean step_uses_secrets_deploy_command;
        /** Field holding the value of tested_deploy_action after a convention execution step.*/
        public final boolean tested_deploy_action;

        InferredValueOutputs(BuildCheck system$model) {
            this.deploy_action = system$model.deploy_action.getSamples()[0];
            this.deploy_action_certainty = system$model.deploy_action_certainty.getSamples()[0];
            this.deploy_command = system$model.deploy_command.getSamples()[0];
            this.deploy_command_certainty = system$model.deploy_command_certainty.getSamples()[0];
            this.deploy_kws = system$model.deploy_kws.getSamples()[0];
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getSamples()[0];
            this.release_workflow_trigger_deploy_action = system$model.release_workflow_trigger_deploy_action.getSamples()[0];
            this.release_workflow_trigger_deploy_command = system$model.release_workflow_trigger_deploy_command.getSamples()[0];
            this.step_uses_secrets_deploy_action = system$model.step_uses_secrets_deploy_action.getSamples()[0];
            this.step_uses_secrets_deploy_command = system$model.step_uses_secrets_deploy_command.getSamples()[0];
            this.tested_deploy_action = system$model.tested_deploy_action.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable deploy_action */
        public final double deploy_action;
        /** Field holding the log probability of computed variable deploy_action_certainty */
        public final double deploy_action_certainty;
        /** Field holding the log probability of computed variable deploy_command */
        public final double deploy_command;
        /** Field holding the log probability of computed variable deploy_command_certainty */
        public final double deploy_command_certainty;
        /** Field holding the log probability of computed variable deploy_kws */
        public final double deploy_kws;
        /** Field holding the log probability of computed variable deploy_kws_certainty */
        public final double deploy_kws_certainty;
        /** Field holding the log probability of computed variable release_workflow_trigger_deploy_action */
        public final double release_workflow_trigger_deploy_action;
        /** Field holding the log probability of computed variable release_workflow_trigger_deploy_command */
        public final double release_workflow_trigger_deploy_command;
        /** Field holding the log probability of computed variable step_uses_secrets_deploy_action */
        public final double step_uses_secrets_deploy_action;
        /** Field holding the log probability of computed variable step_uses_secrets_deploy_command */
        public final double step_uses_secrets_deploy_command;
        /** Field holding the log probability of computed variable tested_deploy_action */
        public final double tested_deploy_action;

        LogProbabilities(BuildCheck system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.deploy_action = system$model.deploy_action.getLogProbability();
            this.deploy_action_certainty = system$model.deploy_action_certainty.getLogProbability();
            this.deploy_command = system$model.deploy_command.getLogProbability();
            this.deploy_command_certainty = system$model.deploy_command_certainty.getLogProbability();
            this.deploy_kws = system$model.deploy_kws.getLogProbability();
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getLogProbability();
            this.release_workflow_trigger_deploy_action = system$model.release_workflow_trigger_deploy_action.getLogProbability();
            this.release_workflow_trigger_deploy_command = system$model.release_workflow_trigger_deploy_command.getLogProbability();
            this.step_uses_secrets_deploy_action = system$model.step_uses_secrets_deploy_action.getLogProbability();
            this.step_uses_secrets_deploy_command = system$model.step_uses_secrets_deploy_command.getLogProbability();
            this.tested_deploy_action = system$model.tested_deploy_action.getLogProbability();
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
        /** Field holding the probability of computed variable deploy_action */
        public final double deploy_action;
        /** Field holding the probability of computed variable deploy_action_certainty */
        public final double deploy_action_certainty;
        /** Field holding the probability of computed variable deploy_command */
        public final double deploy_command;
        /** Field holding the probability of computed variable deploy_command_certainty */
        public final double deploy_command_certainty;
        /** Field holding the probability of computed variable deploy_kws */
        public final double deploy_kws;
        /** Field holding the probability of computed variable deploy_kws_certainty */
        public final double deploy_kws_certainty;
        /** Field holding the probability of computed variable release_workflow_trigger_deploy_action */
        public final double release_workflow_trigger_deploy_action;
        /** Field holding the probability of computed variable release_workflow_trigger_deploy_command */
        public final double release_workflow_trigger_deploy_command;
        /** Field holding the probability of computed variable step_uses_secrets_deploy_action */
        public final double step_uses_secrets_deploy_action;
        /** Field holding the probability of computed variable step_uses_secrets_deploy_command */
        public final double step_uses_secrets_deploy_command;
        /** Field holding the probability of computed variable tested_deploy_action */
        public final double tested_deploy_action;

        Probabilities(BuildCheck system$model) {
            this.$modelProbability = system$model.getProbability();
            this.deploy_action = system$model.deploy_action.getProbability();
            this.deploy_action_certainty = system$model.deploy_action_certainty.getProbability();
            this.deploy_command = system$model.deploy_command.getProbability();
            this.deploy_command_certainty = system$model.deploy_command_certainty.getProbability();
            this.deploy_kws = system$model.deploy_kws.getProbability();
            this.deploy_kws_certainty = system$model.deploy_kws_certainty.getProbability();
            this.release_workflow_trigger_deploy_action = system$model.release_workflow_trigger_deploy_action.getProbability();
            this.release_workflow_trigger_deploy_command = system$model.release_workflow_trigger_deploy_command.getProbability();
            this.step_uses_secrets_deploy_action = system$model.step_uses_secrets_deploy_action.getProbability();
            this.step_uses_secrets_deploy_command = system$model.step_uses_secrets_deploy_command.getProbability();
            this.tested_deploy_action = system$model.tested_deploy_action.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of deploy_action after an infer model call. */
        public final boolean[] deploy_action;
        /** Field holding the MAP or Sample value of deploy_action_certainty after an infer model call. */
        public final boolean[] deploy_action_certainty;
        /** Field holding the MAP or Sample value of deploy_command after an infer model call. */
        public final boolean[] deploy_command;
        /** Field holding the MAP or Sample value of deploy_command_certainty after an infer model call. */
        public final boolean[] deploy_command_certainty;
        /** Field holding the MAP or Sample value of deploy_kws after an infer model call. */
        public final boolean[] deploy_kws;
        /** Field holding the MAP or Sample value of deploy_kws_certainty after an infer model call. */
        public final boolean[] deploy_kws_certainty;
        /** Field holding the MAP or Sample value of release_workflow_trigger_deploy_action after an infer model call. */
        public final boolean[] release_workflow_trigger_deploy_action;
        /** Field holding the MAP or Sample value of release_workflow_trigger_deploy_command after an infer model call. */
        public final boolean[] release_workflow_trigger_deploy_command;
        /** Field holding the MAP or Sample value of step_uses_secrets_deploy_action after an infer model call. */
        public final boolean[] step_uses_secrets_deploy_action;
        /** Field holding the MAP or Sample value of step_uses_secrets_deploy_command after an infer model call. */
        public final boolean[] step_uses_secrets_deploy_command;
        /** Field holding the MAP or Sample value of tested_deploy_action after an infer model call. */
        public final boolean[] tested_deploy_action;

        InferredModelOutputs(BuildCheck system$model) {
            this.deploy_action = system$model.getInferredValue(system$model.$deploy_action);
            this.deploy_action_certainty = system$model.getInferredValue(system$model.$deploy_action_certainty);
            this.deploy_command = system$model.getInferredValue(system$model.$deploy_command);
            this.deploy_command_certainty = system$model.getInferredValue(system$model.$deploy_command_certainty);
            this.deploy_kws = system$model.getInferredValue(system$model.$deploy_kws);
            this.deploy_kws_certainty = system$model.getInferredValue(system$model.$deploy_kws_certainty);
            this.release_workflow_trigger_deploy_action = system$model.getInferredValue(system$model.$release_workflow_trigger_deploy_action);
            this.release_workflow_trigger_deploy_command = system$model.getInferredValue(system$model.$release_workflow_trigger_deploy_command);
            this.step_uses_secrets_deploy_action = system$model.getInferredValue(system$model.$step_uses_secrets_deploy_action);
            this.step_uses_secrets_deploy_command = system$model.getInferredValue(system$model.$step_uses_secrets_deploy_command);
            this.tested_deploy_action = system$model.getInferredValue(system$model.$tested_deploy_action);
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
