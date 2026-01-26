package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model TerminalVariables This is the class that
  * all user interactions with the model should occur through.
  */
public final class TerminalVariables extends Model {

    private TerminalVariables$CoreInterface system$c = new TerminalVariables$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedIntegerInternal $c1 = new ComputedIntegerInternal(this, "c1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c1(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c1(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample47(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample47())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c1 of type int from the Sandwood model 
     */
    public final ComputedInteger c1 = $c1;

    private final ComputedIntegerInternal $c10 = new ComputedIntegerInternal(this, "c10", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c10(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c10(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c10(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample70(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample70())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c10 of type int from the Sandwood model 
     */
    public final ComputedInteger c10 = $c10;

    private final ComputedIntegerInternal $c11 = new ComputedIntegerInternal(this, "c11", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c11(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c11(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c11(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample72(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample72())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c11 of type int from the Sandwood model 
     */
    public final ComputedInteger c11 = $c11;

    private final ComputedIntegerInternal $c12 = new ComputedIntegerInternal(this, "c12", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c12(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c12(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c12(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample75(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample75())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c12 of type int from the Sandwood model 
     */
    public final ComputedInteger c12 = $c12;

    private final ComputedIntegerInternal $c2 = new ComputedIntegerInternal(this, "c2", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c2(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable c2 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing c2 of type int from the Sandwood model 
     */
    public final ComputedInteger c2 = $c2;

    private final ComputedIntegerInternal $c3 = new ComputedIntegerInternal(this, "c3", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c3(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c3(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c3(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample52(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample52())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c3 of type int from the Sandwood model 
     */
    public final ComputedInteger c3 = $c3;

    private final ComputedIntegerInternal $c4 = new ComputedIntegerInternal(this, "c4", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c4(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c4(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c4(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample55(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample55())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c4 of type int from the Sandwood model 
     */
    public final ComputedInteger c4 = $c4;

    private final ComputedIntegerInternal $c5 = new ComputedIntegerInternal(this, "c5", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c5(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c5(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c5(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample57(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample57())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c5 of type int from the Sandwood model 
     */
    public final ComputedInteger c5 = $c5;

    private final ComputedIntegerInternal $c6 = new ComputedIntegerInternal(this, "c6", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c6(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c6(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c6(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample60(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample60())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c6 of type int from the Sandwood model 
     */
    public final ComputedInteger c6 = $c6;

    private final ComputedIntegerInternal $c7 = new ComputedIntegerInternal(this, "c7", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c7(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c7(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c7(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample62(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample62())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c7 of type int from the Sandwood model 
     */
    public final ComputedInteger c7 = $c7;

    private final ComputedIntegerInternal $c8 = new ComputedIntegerInternal(this, "c8", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c8(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c8(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c8(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample65(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample65())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c8 of type int from the Sandwood model 
     */
    public final ComputedInteger c8 = $c8;

    private final ComputedIntegerInternal $c9 = new ComputedIntegerInternal(this, "c9", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$c9(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$c9(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$c9(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample67(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample67())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing c9 of type int from the Sandwood model 
     */
    public final ComputedInteger c9 = $c9;

    private final ComputedIntegerInternal $terminalVariable = new ComputedIntegerInternal(this, "terminalVariable", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return system$c.get$terminalVariable(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$terminalVariable(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$terminalVariable(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample636(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample636())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing terminalVariable of type int from the Sandwood model 
     */
    public final ComputedInteger terminalVariable = $terminalVariable;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerInternal $evidence = new ObservedIntegerInternal(this, "evidence") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$evidence();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$evidence(value); }
    };

    /**
     * Observed variable representing evidence of type int from the Sandwood model 
     */
    public final ObservedInteger evidence = $evidence;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$c1, $c10, $c11, $c12, $c2, $c3, $c4, $c5, $c6, $c7, $c8, $c9, $terminalVariable};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public TerminalVariables() {
        super();
        //ComputedVariable
        $computedVariables.put("c1", $c1);
        $computedVariables.put("c10", $c10);
        $computedVariables.put("c11", $c11);
        $computedVariables.put("c12", $c12);
        $computedVariables.put("c2", $c2);
        $computedVariables.put("c3", $c3);
        $computedVariables.put("c4", $c4);
        $computedVariables.put("c5", $c5);
        $computedVariables.put("c6", $c6);
        $computedVariables.put("c7", $c7);
        $computedVariables.put("c8", $c8);
        $computedVariables.put("c9", $c9);
        $computedVariables.put("terminalVariable", $terminalVariable);

        //Observed scalar fields
        $regularObservedValues.put("evidence", $evidence);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param evidence The value to set evidence to.
      */

    public TerminalVariables(int evidence) {
        this();
        this.evidence.setValue(evidence);
    }
    
    @Override
    protected TerminalVariables$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        TerminalVariables$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new TerminalVariables$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new TerminalVariables$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(TerminalVariables$CoreInterface oldCore, TerminalVariables$CoreInterface newCore) {
        //Observed scalars
        if(evidence.isSet())
            newCore.set$evidence(oldCore.get$evidence());

        //ComputedVariables
        if($c1.isSet())
            newCore.set$c1(oldCore.get$c1());
        if($c10.isSet())
            newCore.set$c10(oldCore.get$c10());
        if($c11.isSet())
            newCore.set$c11(oldCore.get$c11());
        if($c12.isSet())
            newCore.set$c12(oldCore.get$c12());
        if($c3.isSet())
            newCore.set$c3(oldCore.get$c3());
        if($c4.isSet())
            newCore.set$c4(oldCore.get$c4());
        if($c5.isSet())
            newCore.set$c5(oldCore.get$c5());
        if($c6.isSet())
            newCore.set$c6(oldCore.get$c6());
        if($c7.isSet())
            newCore.set$c7(oldCore.get$c7());
        if($c8.isSet())
            newCore.set$c8(oldCore.get$c8());
        if($c9.isSet())
            newCore.set$c9(oldCore.get$c9());
        if($terminalVariable.isSet())
            newCore.set$terminalVariable(oldCore.get$terminalVariable());

        //Set fixed flags
        newCore.set$fixedFlag$sample47(oldCore.get$fixedFlag$sample47());
        newCore.set$fixedFlag$sample52(oldCore.get$fixedFlag$sample52());
        newCore.set$fixedFlag$sample55(oldCore.get$fixedFlag$sample55());
        newCore.set$fixedFlag$sample57(oldCore.get$fixedFlag$sample57());
        newCore.set$fixedFlag$sample60(oldCore.get$fixedFlag$sample60());
        newCore.set$fixedFlag$sample62(oldCore.get$fixedFlag$sample62());
        newCore.set$fixedFlag$sample636(oldCore.get$fixedFlag$sample636());
        newCore.set$fixedFlag$sample65(oldCore.get$fixedFlag$sample65());
        newCore.set$fixedFlag$sample67(oldCore.get$fixedFlag$sample67());
        newCore.set$fixedFlag$sample70(oldCore.get$fixedFlag$sample70());
        newCore.set$fixedFlag$sample72(oldCore.get$fixedFlag$sample72());
        newCore.set$fixedFlag$sample75(oldCore.get$fixedFlag$sample75());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          */
        public InferValueInputs() {
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input evidence */
        public final int evidence;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param evidence The value to set evidence to.
          */
        public AllInputs(int evidence) {
            this.evidence = evidence;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of c1 after a convention execution step.*/
        public final int c1;
        /** Field holding the value of c10 after a convention execution step.*/
        public final int c10;
        /** Field holding the value of c11 after a convention execution step.*/
        public final int c11;
        /** Field holding the value of c12 after a convention execution step.*/
        public final int c12;
        /** Field holding the value of c2 after a convention execution step.*/
        public final int c2;
        /** Field holding the value of c3 after a convention execution step.*/
        public final int c3;
        /** Field holding the value of c4 after a convention execution step.*/
        public final int c4;
        /** Field holding the value of c5 after a convention execution step.*/
        public final int c5;
        /** Field holding the value of c6 after a convention execution step.*/
        public final int c6;
        /** Field holding the value of c7 after a convention execution step.*/
        public final int c7;
        /** Field holding the value of c8 after a convention execution step.*/
        public final int c8;
        /** Field holding the value of c9 after a convention execution step.*/
        public final int c9;
        /** Field holding the value of terminalVariable after a convention execution step.*/
        public final int terminalVariable;

        InferredValueOutputs(TerminalVariables system$model) {
            this.c1 = system$model.c1.getSamples()[0];
            this.c10 = system$model.c10.getSamples()[0];
            this.c11 = system$model.c11.getSamples()[0];
            this.c12 = system$model.c12.getSamples()[0];
            this.c2 = system$model.c2.getSamples()[0];
            this.c3 = system$model.c3.getSamples()[0];
            this.c4 = system$model.c4.getSamples()[0];
            this.c5 = system$model.c5.getSamples()[0];
            this.c6 = system$model.c6.getSamples()[0];
            this.c7 = system$model.c7.getSamples()[0];
            this.c8 = system$model.c8.getSamples()[0];
            this.c9 = system$model.c9.getSamples()[0];
            this.terminalVariable = system$model.terminalVariable.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable c1 */
        public final double c1;
        /** Field holding the log probability of computed variable c10 */
        public final double c10;
        /** Field holding the log probability of computed variable c11 */
        public final double c11;
        /** Field holding the log probability of computed variable c12 */
        public final double c12;
        /** Field holding the log probability of computed variable c2 */
        public final double c2;
        /** Field holding the log probability of computed variable c3 */
        public final double c3;
        /** Field holding the log probability of computed variable c4 */
        public final double c4;
        /** Field holding the log probability of computed variable c5 */
        public final double c5;
        /** Field holding the log probability of computed variable c6 */
        public final double c6;
        /** Field holding the log probability of computed variable c7 */
        public final double c7;
        /** Field holding the log probability of computed variable c8 */
        public final double c8;
        /** Field holding the log probability of computed variable c9 */
        public final double c9;
        /** Field holding the log probability of computed variable terminalVariable */
        public final double terminalVariable;

        LogProbabilities(TerminalVariables system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.c1 = system$model.c1.getLogProbability();
            this.c10 = system$model.c10.getLogProbability();
            this.c11 = system$model.c11.getLogProbability();
            this.c12 = system$model.c12.getLogProbability();
            this.c2 = system$model.c2.getLogProbability();
            this.c3 = system$model.c3.getLogProbability();
            this.c4 = system$model.c4.getLogProbability();
            this.c5 = system$model.c5.getLogProbability();
            this.c6 = system$model.c6.getLogProbability();
            this.c7 = system$model.c7.getLogProbability();
            this.c8 = system$model.c8.getLogProbability();
            this.c9 = system$model.c9.getLogProbability();
            this.terminalVariable = system$model.terminalVariable.getLogProbability();
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
        /** Field holding the probability of computed variable c1 */
        public final double c1;
        /** Field holding the probability of computed variable c10 */
        public final double c10;
        /** Field holding the probability of computed variable c11 */
        public final double c11;
        /** Field holding the probability of computed variable c12 */
        public final double c12;
        /** Field holding the probability of computed variable c2 */
        public final double c2;
        /** Field holding the probability of computed variable c3 */
        public final double c3;
        /** Field holding the probability of computed variable c4 */
        public final double c4;
        /** Field holding the probability of computed variable c5 */
        public final double c5;
        /** Field holding the probability of computed variable c6 */
        public final double c6;
        /** Field holding the probability of computed variable c7 */
        public final double c7;
        /** Field holding the probability of computed variable c8 */
        public final double c8;
        /** Field holding the probability of computed variable c9 */
        public final double c9;
        /** Field holding the probability of computed variable terminalVariable */
        public final double terminalVariable;

        Probabilities(TerminalVariables system$model) {
            this.$modelProbability = system$model.getProbability();
            this.c1 = system$model.c1.getProbability();
            this.c10 = system$model.c10.getProbability();
            this.c11 = system$model.c11.getProbability();
            this.c12 = system$model.c12.getProbability();
            this.c2 = system$model.c2.getProbability();
            this.c3 = system$model.c3.getProbability();
            this.c4 = system$model.c4.getProbability();
            this.c5 = system$model.c5.getProbability();
            this.c6 = system$model.c6.getProbability();
            this.c7 = system$model.c7.getProbability();
            this.c8 = system$model.c8.getProbability();
            this.c9 = system$model.c9.getProbability();
            this.terminalVariable = system$model.terminalVariable.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of c1 after an infer model call. */
        public final int[] c1;
        /** Field holding the MAP or Sample value of c10 after an infer model call. */
        public final int[] c10;
        /** Field holding the MAP or Sample value of c11 after an infer model call. */
        public final int[] c11;
        /** Field holding the MAP or Sample value of c12 after an infer model call. */
        public final int[] c12;
        /** Field holding the MAP or Sample value of c3 after an infer model call. */
        public final int[] c3;
        /** Field holding the MAP or Sample value of c4 after an infer model call. */
        public final int[] c4;
        /** Field holding the MAP or Sample value of c5 after an infer model call. */
        public final int[] c5;
        /** Field holding the MAP or Sample value of c6 after an infer model call. */
        public final int[] c6;
        /** Field holding the MAP or Sample value of c7 after an infer model call. */
        public final int[] c7;
        /** Field holding the MAP or Sample value of c8 after an infer model call. */
        public final int[] c8;
        /** Field holding the MAP or Sample value of c9 after an infer model call. */
        public final int[] c9;
        /** Field holding the MAP or Sample value of terminalVariable after an infer model call. */
        public final int[] terminalVariable;

        InferredModelOutputs(TerminalVariables system$model) {
            this.c1 = system$model.getInferredValue(system$model.$c1);
            this.c10 = system$model.getInferredValue(system$model.$c10);
            this.c11 = system$model.getInferredValue(system$model.$c11);
            this.c12 = system$model.getInferredValue(system$model.$c12);
            this.c3 = system$model.getInferredValue(system$model.$c3);
            this.c4 = system$model.getInferredValue(system$model.$c4);
            this.c5 = system$model.getInferredValue(system$model.$c5);
            this.c6 = system$model.getInferredValue(system$model.$c6);
            this.c7 = system$model.getInferredValue(system$model.$c7);
            this.c8 = system$model.getInferredValue(system$model.$c8);
            this.c9 = system$model.getInferredValue(system$model.$c9);
            this.terminalVariable = system$model.getInferredValue(system$model.$terminalVariable);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
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
        this.$evidence.setValue(inputs.evidence);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
