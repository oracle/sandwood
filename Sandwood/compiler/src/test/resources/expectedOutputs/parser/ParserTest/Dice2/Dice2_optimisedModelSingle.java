package dice;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Dice2 This is the class that
  * all user interactions with the model should occur through.
  */
public class Dice2 extends Model {

    private Dice2$CoreInterface system$c = new Dice2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedIntegerInternal $die1 = new ComputedIntegerInternal(this, "die1", true) {
        @Override
        protected int getValue() { return system$c.get$die1(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$die1(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$die1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample31(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample31())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing die1 of type int from the Sandwood model 
     */
    public final ComputedInteger die1 = $die1;

    private final ComputedIntegerInternal $die2 = new ComputedIntegerInternal(this, "die2", true) {
        @Override
        protected int getValue() { return system$c.get$die2(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$die2(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$die2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing die2 of type int from the Sandwood model 
     */
    public final ComputedInteger die2 = $die2;

    private final ComputedBooleanInternal $even1 = new ComputedBooleanInternal(this, "even1", false) {
        @Override
        protected boolean getValue() { return system$c.get$even1(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable even1 because its value depends on variable \"die1\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$even1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample31(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample31())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing even1 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean even1 = $even1;

    private final ComputedBooleanInternal $even2 = new ComputedBooleanInternal(this, "even2", false) {
        @Override
        protected boolean getValue() { return system$c.get$even2(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable even2 because its value depends on variable \"die2\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$even2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing even2 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean even2 = $even2;

    private final ComputedBooleanInternal $odd1 = new ComputedBooleanInternal(this, "odd1", false) {
        @Override
        protected boolean getValue() { return system$c.get$odd1(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable odd1 because its value depends on variable \"die1\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$odd1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample31(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample31())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing odd1 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean odd1 = $odd1;

    private final ComputedBooleanInternal $odd2 = new ComputedBooleanInternal(this, "odd2", false) {
        @Override
        protected boolean getValue() { return system$c.get$odd2(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable odd2 because its value depends on variable \"die2\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$odd2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing odd2 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean odd2 = $odd2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$die1, $die2, $even1, $even2, $odd1, $odd2};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Dice2() {
        super();
        //ComputedVariable
        $computedVariables.put("die1", $die1);
        $computedVariables.put("die2", $die2);
        $computedVariables.put("even1", $even1);
        $computedVariables.put("even2", $even2);
        $computedVariables.put("odd1", $odd1);
        $computedVariables.put("odd2", $odd2);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    
    @Override
    protected Dice2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Dice2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Dice2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Dice2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Dice2$CoreInterface oldCore, Dice2$CoreInterface newCore) {

        //ComputedVariables
        if(die1.isSet())
            newCore.set$die1(oldCore.get$die1());
        if(die2.isSet())
            newCore.set$die2(oldCore.get$die2());
        if(even1.isSet())
            newCore.set$even1(oldCore.get$even1());
        if(even2.isSet())
            newCore.set$even2(oldCore.get$even2());
        if(odd1.isSet())
            newCore.set$odd1(oldCore.get$odd1());
        if(odd2.isSet())
            newCore.set$odd2(oldCore.get$odd2());

        //Set fixed flags
        if(die1.isSet())
            newCore.set$fixedFlag$sample31(oldCore.get$fixedFlag$sample31());
        if(die2.isSet())
            newCore.set$fixedFlag$sample35(oldCore.get$fixedFlag$sample35());
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
        /** Field holding the value of die1 after a convention execution step.*/
        public final int die1;
        /** Field holding the value of die2 after a convention execution step.*/
        public final int die2;
        /** Field holding the value of even1 after a convention execution step.*/
        public final boolean even1;
        /** Field holding the value of even2 after a convention execution step.*/
        public final boolean even2;
        /** Field holding the value of odd1 after a convention execution step.*/
        public final boolean odd1;
        /** Field holding the value of odd2 after a convention execution step.*/
        public final boolean odd2;

        InferredValueOutputs(Dice2 system$model) {
            this.die1 = system$model.die1.getSamples()[0];
            this.die2 = system$model.die2.getSamples()[0];
            this.even1 = system$model.even1.getSamples()[0];
            this.even2 = system$model.even2.getSamples()[0];
            this.odd1 = system$model.odd1.getSamples()[0];
            this.odd2 = system$model.odd2.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable die1 */
        public final double die1;
        /** Field holding the log probability of computed variable die2 */
        public final double die2;
        /** Field holding the log probability of computed variable even1 */
        public final double even1;
        /** Field holding the log probability of computed variable even2 */
        public final double even2;
        /** Field holding the log probability of computed variable odd1 */
        public final double odd1;
        /** Field holding the log probability of computed variable odd2 */
        public final double odd2;

        LogProbabilities(Dice2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.die1 = system$model.die1.getLogProbability();
            this.die2 = system$model.die2.getLogProbability();
            this.even1 = system$model.even1.getLogProbability();
            this.even2 = system$model.even2.getLogProbability();
            this.odd1 = system$model.odd1.getLogProbability();
            this.odd2 = system$model.odd2.getLogProbability();
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
        /** Field holding the probability of computed variable die1 */
        public final double die1;
        /** Field holding the probability of computed variable die2 */
        public final double die2;
        /** Field holding the probability of computed variable even1 */
        public final double even1;
        /** Field holding the probability of computed variable even2 */
        public final double even2;
        /** Field holding the probability of computed variable odd1 */
        public final double odd1;
        /** Field holding the probability of computed variable odd2 */
        public final double odd2;

        Probabilities(Dice2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.die1 = system$model.die1.getProbability();
            this.die2 = system$model.die2.getProbability();
            this.even1 = system$model.even1.getProbability();
            this.even2 = system$model.even2.getProbability();
            this.odd1 = system$model.odd1.getProbability();
            this.odd2 = system$model.odd2.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of die1 after an infer model call. */
        public final int[] die1;
        /** Field holding the MAP or Sample value of die2 after an infer model call. */
        public final int[] die2;
        /** Field holding the MAP or Sample value of even1 after an infer model call. */
        public final boolean[] even1;
        /** Field holding the MAP or Sample value of even2 after an infer model call. */
        public final boolean[] even2;
        /** Field holding the MAP or Sample value of odd1 after an infer model call. */
        public final boolean[] odd1;
        /** Field holding the MAP or Sample value of odd2 after an infer model call. */
        public final boolean[] odd2;

        InferredModelOutputs(Dice2 system$model) {
            this.die1 = system$model.getInferredValue(system$model.$die1);
            this.die2 = system$model.getInferredValue(system$model.$die2);
            this.even1 = system$model.getInferredValue(system$model.$even1);
            this.even2 = system$model.getInferredValue(system$model.$even2);
            this.odd1 = system$model.getInferredValue(system$model.$odd1);
            this.odd2 = system$model.getInferredValue(system$model.$odd2);
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
