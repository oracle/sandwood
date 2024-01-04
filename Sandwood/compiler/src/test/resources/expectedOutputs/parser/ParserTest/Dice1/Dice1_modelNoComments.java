package dice;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Dice1 This is the class that
  * all user interactions with the model should occur through.
  */
public class Dice1 extends Model {

    private Dice1$CoreInterface system$c = new Dice1$SingleThreadCPU(ExecutionTarget.singleThread);

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

    private final ComputedBooleanInternal $someSix = new ComputedBooleanInternal(this, "someSix", false) {
        @Override
        protected boolean getValue() { return system$c.get$someSix(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable someSix because its value depends on variables \"die1\", and \"die2\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$someSix(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample31(fixed);
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample31 = system$c.get$fixedFlag$sample31();
            boolean fixedFlag$sample35 = system$c.get$fixedFlag$sample35();
            if(fixedFlag$sample31 && fixedFlag$sample35)
                return Immutability.FIXED;
            else if(fixedFlag$sample31 || fixedFlag$sample35)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing someSix of type boolean from the Sandwood model 
     */
    public final ComputedBoolean someSix = $someSix;

    private final ComputedBooleanInternal $twoSix = new ComputedBooleanInternal(this, "twoSix", false) {
        @Override
        protected boolean getValue() { return system$c.get$twoSix(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable twoSix because its value depends on variables \"die1\", and \"die2\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$twoSix(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample31(fixed);
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample31 = system$c.get$fixedFlag$sample31();
            boolean fixedFlag$sample35 = system$c.get$fixedFlag$sample35();
            if(fixedFlag$sample31 && fixedFlag$sample35)
                return Immutability.FIXED;
            else if(fixedFlag$sample31 || fixedFlag$sample35)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing twoSix of type boolean from the Sandwood model 
     */
    public final ComputedBoolean twoSix = $twoSix;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$die1, $die2, $someSix, $twoSix};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Dice1() {
        super();
        //ComputedVariable
        $computedVariables.put("die1", $die1);
        $computedVariables.put("die2", $die2);
        $computedVariables.put("someSix", $someSix);
        $computedVariables.put("twoSix", $twoSix);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    
    @Override
    protected Dice1$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Dice1$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Dice1$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Dice1$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Dice1$CoreInterface oldCore, Dice1$CoreInterface newCore) {

        //ComputedVariables
        if(die1.isSet())
            newCore.set$die1(oldCore.get$die1());
        if(die2.isSet())
            newCore.set$die2(oldCore.get$die2());
        if(someSix.isSet())
            newCore.set$someSix(oldCore.get$someSix());
        if(twoSix.isSet())
            newCore.set$twoSix(oldCore.get$twoSix());

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
        /** Field holding the value of someSix after a convention execution step.*/
        public final boolean someSix;
        /** Field holding the value of twoSix after a convention execution step.*/
        public final boolean twoSix;

        InferredValueOutputs(Dice1 system$model) {
            this.die1 = system$model.die1.getSamples()[0];
            this.die2 = system$model.die2.getSamples()[0];
            this.someSix = system$model.someSix.getSamples()[0];
            this.twoSix = system$model.twoSix.getSamples()[0];
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
        /** Field holding the log probability of computed variable someSix */
        public final double someSix;
        /** Field holding the log probability of computed variable twoSix */
        public final double twoSix;

        LogProbabilities(Dice1 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.die1 = system$model.die1.getLogProbability();
            this.die2 = system$model.die2.getLogProbability();
            this.someSix = system$model.someSix.getLogProbability();
            this.twoSix = system$model.twoSix.getLogProbability();
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
        /** Field holding the probability of computed variable someSix */
        public final double someSix;
        /** Field holding the probability of computed variable twoSix */
        public final double twoSix;

        Probabilities(Dice1 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.die1 = system$model.die1.getProbability();
            this.die2 = system$model.die2.getProbability();
            this.someSix = system$model.someSix.getProbability();
            this.twoSix = system$model.twoSix.getProbability();
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
        /** Field holding the MAP or Sample value of someSix after an infer model call. */
        public final boolean[] someSix;
        /** Field holding the MAP or Sample value of twoSix after an infer model call. */
        public final boolean[] twoSix;

        InferredModelOutputs(Dice1 system$model) {
            this.die1 = system$model.getInferredValue(system$model.$die1);
            this.die2 = system$model.getInferredValue(system$model.$die2);
            this.someSix = system$model.getInferredValue(system$model.$someSix);
            this.twoSix = system$model.getInferredValue(system$model.$twoSix);
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
