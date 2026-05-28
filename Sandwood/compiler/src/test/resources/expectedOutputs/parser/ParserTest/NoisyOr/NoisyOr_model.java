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
  * Class representing the Sandwood model NoisyOr This is the class that
  * all user interactions with the model should occur through.
  */
public final class NoisyOr extends Model {

    private NoisyOr$CoreInterface system$c = new NoisyOr$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $flag1 = new ComputedBooleanInternal(this, "flag1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag1(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample3(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample3())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flag1 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag1 = $flag1;

    private final ComputedBooleanInternal $flag2 = new ComputedBooleanInternal(this, "flag2", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag2(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag2(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample6(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample6())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flag2 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag2 = $flag2;

    private final ComputedBooleanInternal $flag3 = new ComputedBooleanInternal(this, "flag3", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag3(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag3(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag3(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample9(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample9())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flag3 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag3 = $flag3;

    private final ComputedBooleanInternal $flag4 = new ComputedBooleanInternal(this, "flag4", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag4(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag4(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag4(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample12(fixed, allocated);
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
     * Computed variable representing flag4 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag4 = $flag4;

    private final ComputedBooleanInternal $flag5 = new ComputedBooleanInternal(this, "flag5", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag5(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag5(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag5(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample15(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample15())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flag5 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag5 = $flag5;

    private final ComputedBooleanInternal $flag6 = new ComputedBooleanInternal(this, "flag6", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return system$c.get$flag6(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$flag6(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flag6(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample18(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample18())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flag6 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean flag6 = $flag6;

    private final ComputedObjectArrayInternal<boolean[]> $issues$var213 = new ComputedObjectArrayInternal<boolean[]>(this, "issues$var213", true, true, true, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return system$c.get$issues$var213(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            system$c.set$issues$var213(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

    private final ComputedObjectArrayInternal<boolean[]> $issues$var383 = new ComputedObjectArrayInternal<boolean[]>(this, "issues$var383", true, true, true, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return system$c.get$issues$var383(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            system$c.set$issues$var383(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

    private final ComputedBooleanArrayInternal $n13State = new ComputedBooleanArrayInternal(this, "n13State", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return system$c.get$n13State(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable n13State because its value depends on variable \"issues$var383\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$n13State(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample430(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample430())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing n13State of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray n13State = $n13State;

    private final ComputedBooleanArrayInternal $noisyOr = new ComputedBooleanArrayInternal(this, "noisyOr", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return system$c.get$noisyOr(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable noisyOr because its value depends on variable \"issues$var213\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$noisyOr(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample233(fixed, allocated);
                system$c.set$fixedFlag$sample248(fixed, allocated);
                system$c.set$fixedFlag$sample263(fixed, allocated);
                system$c.set$fixedFlag$sample278(fixed, allocated);
                system$c.set$fixedFlag$sample293(fixed, allocated);
                system$c.set$fixedFlag$sample308(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample233 = system$c.get$fixedFlag$sample233();
            boolean fixedFlag$sample248 = system$c.get$fixedFlag$sample248();
            boolean fixedFlag$sample263 = system$c.get$fixedFlag$sample263();
            boolean fixedFlag$sample278 = system$c.get$fixedFlag$sample278();
            boolean fixedFlag$sample293 = system$c.get$fixedFlag$sample293();
            boolean fixedFlag$sample308 = system$c.get$fixedFlag$sample308();
            if(fixedFlag$sample233 && fixedFlag$sample248 && fixedFlag$sample263 && fixedFlag$sample278 && fixedFlag$sample293 && fixedFlag$sample308)
                return Immutability.FIXED;
            else if(fixedFlag$sample233 || fixedFlag$sample248 || fixedFlag$sample263 || fixedFlag$sample278 || fixedFlag$sample293 || fixedFlag$sample308)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing noisyOr of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray noisyOr = $noisyOr;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$flag1, $flag2, $flag3, $flag4, $flag5, $flag6, $n13State, $noisyOr};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public NoisyOr() {
        super();
        //ComputedVariable
        $computedVariables.put("flag1", $flag1);
        $computedVariables.put("flag2", $flag2);
        $computedVariables.put("flag3", $flag3);
        $computedVariables.put("flag4", $flag4);
        $computedVariables.put("flag5", $flag5);
        $computedVariables.put("flag6", $flag6);
        $computedVariables.put("issues$var213", $issues$var213);
        $computedVariables.put("issues$var383", $issues$var383);
        $computedVariables.put("n13State", $n13State);
        $computedVariables.put("noisyOr", $noisyOr);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    
    @Override
    protected NoisyOr$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        NoisyOr$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new NoisyOr$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new NoisyOr$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(NoisyOr$CoreInterface oldCore, NoisyOr$CoreInterface newCore) {

        //ComputedVariables
        if($flag1.isSet())
            newCore.set$flag1(oldCore.get$flag1(), false);
        if($flag2.isSet())
            newCore.set$flag2(oldCore.get$flag2(), false);
        if($flag3.isSet())
            newCore.set$flag3(oldCore.get$flag3(), false);
        if($flag4.isSet())
            newCore.set$flag4(oldCore.get$flag4(), false);
        if($flag5.isSet())
            newCore.set$flag5(oldCore.get$flag5(), false);
        if($flag6.isSet())
            newCore.set$flag6(oldCore.get$flag6(), false);
        if($issues$var213.isSet())
            newCore.set$issues$var213(oldCore.get$issues$var213(), false);
        if($issues$var383.isSet())
            newCore.set$issues$var383(oldCore.get$issues$var383(), false);

        //Set fixed flags
        newCore.set$fixedFlag$sample12(oldCore.get$fixedFlag$sample12(), false);
        newCore.set$fixedFlag$sample15(oldCore.get$fixedFlag$sample15(), false);
        newCore.set$fixedFlag$sample18(oldCore.get$fixedFlag$sample18(), false);
        newCore.set$fixedFlag$sample3(oldCore.get$fixedFlag$sample3(), false);
        newCore.set$fixedFlag$sample6(oldCore.get$fixedFlag$sample6(), false);
        newCore.set$fixedFlag$sample9(oldCore.get$fixedFlag$sample9(), false);
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

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          */
        public AllInputs() {
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of flag1 after a convention execution step.*/
        public final boolean flag1;
        /** Field holding the value of flag2 after a convention execution step.*/
        public final boolean flag2;
        /** Field holding the value of flag3 after a convention execution step.*/
        public final boolean flag3;
        /** Field holding the value of flag4 after a convention execution step.*/
        public final boolean flag4;
        /** Field holding the value of flag5 after a convention execution step.*/
        public final boolean flag5;
        /** Field holding the value of flag6 after a convention execution step.*/
        public final boolean flag6;
        /** Field holding the value of n13State after a convention execution step.*/
        public final boolean[] n13State;
        /** Field holding the value of noisyOr after a convention execution step.*/
        public final boolean[] noisyOr;

        InferredValueOutputs(NoisyOr system$model) {
            this.flag1 = system$model.flag1.getSamples()[0];
            this.flag2 = system$model.flag2.getSamples()[0];
            this.flag3 = system$model.flag3.getSamples()[0];
            this.flag4 = system$model.flag4.getSamples()[0];
            this.flag5 = system$model.flag5.getSamples()[0];
            this.flag6 = system$model.flag6.getSamples()[0];
            this.n13State = system$model.n13State.getSamples()[0];
            this.noisyOr = system$model.noisyOr.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable flag1 */
        public final double flag1;
        /** Field holding the log probability of computed variable flag2 */
        public final double flag2;
        /** Field holding the log probability of computed variable flag3 */
        public final double flag3;
        /** Field holding the log probability of computed variable flag4 */
        public final double flag4;
        /** Field holding the log probability of computed variable flag5 */
        public final double flag5;
        /** Field holding the log probability of computed variable flag6 */
        public final double flag6;
        /** Field holding the log probability of computed variable n13State */
        public final double n13State;
        /** Field holding the log probability of computed variable noisyOr */
        public final double noisyOr;

        LogProbabilities(NoisyOr system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.flag1 = system$model.flag1.getLogProbability();
            this.flag2 = system$model.flag2.getLogProbability();
            this.flag3 = system$model.flag3.getLogProbability();
            this.flag4 = system$model.flag4.getLogProbability();
            this.flag5 = system$model.flag5.getLogProbability();
            this.flag6 = system$model.flag6.getLogProbability();
            this.n13State = system$model.n13State.getLogProbability();
            this.noisyOr = system$model.noisyOr.getLogProbability();
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
        /** Field holding the probability of computed variable flag1 */
        public final double flag1;
        /** Field holding the probability of computed variable flag2 */
        public final double flag2;
        /** Field holding the probability of computed variable flag3 */
        public final double flag3;
        /** Field holding the probability of computed variable flag4 */
        public final double flag4;
        /** Field holding the probability of computed variable flag5 */
        public final double flag5;
        /** Field holding the probability of computed variable flag6 */
        public final double flag6;
        /** Field holding the probability of computed variable n13State */
        public final double n13State;
        /** Field holding the probability of computed variable noisyOr */
        public final double noisyOr;

        Probabilities(NoisyOr system$model) {
            this.$modelProbability = system$model.getProbability();
            this.flag1 = system$model.flag1.getProbability();
            this.flag2 = system$model.flag2.getProbability();
            this.flag3 = system$model.flag3.getProbability();
            this.flag4 = system$model.flag4.getProbability();
            this.flag5 = system$model.flag5.getProbability();
            this.flag6 = system$model.flag6.getProbability();
            this.n13State = system$model.n13State.getProbability();
            this.noisyOr = system$model.noisyOr.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of flag1 after an infer model call. */
        public final boolean[] flag1;
        /** Field holding the MAP or Sample value of flag2 after an infer model call. */
        public final boolean[] flag2;
        /** Field holding the MAP or Sample value of flag3 after an infer model call. */
        public final boolean[] flag3;
        /** Field holding the MAP or Sample value of flag4 after an infer model call. */
        public final boolean[] flag4;
        /** Field holding the MAP or Sample value of flag5 after an infer model call. */
        public final boolean[] flag5;
        /** Field holding the MAP or Sample value of flag6 after an infer model call. */
        public final boolean[] flag6;
        /** Field holding the MAP or Sample value of n13State after an infer model call. */
        public final boolean[][] n13State;
        /** Field holding the MAP or Sample value of noisyOr after an infer model call. */
        public final boolean[][] noisyOr;

        InferredModelOutputs(NoisyOr system$model) {
            this.flag1 = system$model.getInferredValue(system$model.$flag1);
            this.flag2 = system$model.getInferredValue(system$model.$flag2);
            this.flag3 = system$model.getInferredValue(system$model.$flag3);
            this.flag4 = system$model.getInferredValue(system$model.$flag4);
            this.flag5 = system$model.getInferredValue(system$model.$flag5);
            this.flag6 = system$model.getInferredValue(system$model.$flag6);
            this.n13State = system$model.getInferredValue(system$model.$n13State);
            this.noisyOr = system$model.getInferredValue(system$model.$noisyOr);
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
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
