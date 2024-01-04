package coinFlips;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flips1 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flips1 extends Model {

    private Flips1$CoreInterface system$c = new Flips1$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $coin1 = new ComputedBooleanInternal(this, "coin1", true) {
        @Override
        protected boolean getValue() { return system$c.get$coin1(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$coin1(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$coin1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample4(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample4())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing coin1 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean coin1 = $coin1;

    private final ComputedBooleanInternal $coin2 = new ComputedBooleanInternal(this, "coin2", true) {
        @Override
        protected boolean getValue() { return system$c.get$coin2(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$coin2(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$coin2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample6(fixed);
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
     * Computed variable representing coin2 of type boolean from the Sandwood model 
     */
    public final ComputedBoolean coin2 = $coin2;

    private final ComputedBooleanInternal $twoHeads = new ComputedBooleanInternal(this, "twoHeads", false) {
        @Override
        protected boolean getValue() { return system$c.get$twoHeads(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable twoHeads because its value depends on variables \"coin1\", and \"coin2\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$twoHeads(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample4(fixed);
                system$c.set$fixedFlag$sample6(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample4 = system$c.get$fixedFlag$sample4();
            boolean fixedFlag$sample6 = system$c.get$fixedFlag$sample6();
            if(fixedFlag$sample4 && fixedFlag$sample6)
                return Immutability.FIXED;
            else if(fixedFlag$sample4 || fixedFlag$sample6)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing twoHeads of type boolean from the Sandwood model 
     */
    public final ComputedBoolean twoHeads = $twoHeads;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$coin1, $coin2, $twoHeads};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flips1() {
        super();
        //ComputedVariable
        $computedVariables.put("coin1", $coin1);
        $computedVariables.put("coin2", $coin2);
        $computedVariables.put("twoHeads", $twoHeads);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    
    @Override
    protected Flips1$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flips1$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flips1$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flips1$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Flips1$CoreInterface oldCore, Flips1$CoreInterface newCore) {

        //ComputedVariables
        if(coin1.isSet())
            newCore.set$coin1(oldCore.get$coin1());
        if(coin2.isSet())
            newCore.set$coin2(oldCore.get$coin2());
        if(twoHeads.isSet())
            newCore.set$twoHeads(oldCore.get$twoHeads());

        //Set fixed flags
        if(coin1.isSet())
            newCore.set$fixedFlag$sample4(oldCore.get$fixedFlag$sample4());
        if(coin2.isSet())
            newCore.set$fixedFlag$sample6(oldCore.get$fixedFlag$sample6());
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
        /** Field holding the value of coin1 after a convention execution step.*/
        public final boolean coin1;
        /** Field holding the value of coin2 after a convention execution step.*/
        public final boolean coin2;
        /** Field holding the value of twoHeads after a convention execution step.*/
        public final boolean twoHeads;

        InferredValueOutputs(Flips1 system$model) {
            this.coin1 = system$model.coin1.getSamples()[0];
            this.coin2 = system$model.coin2.getSamples()[0];
            this.twoHeads = system$model.twoHeads.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable coin1 */
        public final double coin1;
        /** Field holding the log probability of computed variable coin2 */
        public final double coin2;
        /** Field holding the log probability of computed variable twoHeads */
        public final double twoHeads;

        LogProbabilities(Flips1 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.coin1 = system$model.coin1.getLogProbability();
            this.coin2 = system$model.coin2.getLogProbability();
            this.twoHeads = system$model.twoHeads.getLogProbability();
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
        /** Field holding the probability of computed variable coin1 */
        public final double coin1;
        /** Field holding the probability of computed variable coin2 */
        public final double coin2;
        /** Field holding the probability of computed variable twoHeads */
        public final double twoHeads;

        Probabilities(Flips1 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.coin1 = system$model.coin1.getProbability();
            this.coin2 = system$model.coin2.getProbability();
            this.twoHeads = system$model.twoHeads.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of coin1 after an infer model call. */
        public final boolean[] coin1;
        /** Field holding the MAP or Sample value of coin2 after an infer model call. */
        public final boolean[] coin2;
        /** Field holding the MAP or Sample value of twoHeads after an infer model call. */
        public final boolean[] twoHeads;

        InferredModelOutputs(Flips1 system$model) {
            this.coin1 = system$model.getInferredValue(system$model.$coin1);
            this.coin2 = system$model.getInferredValue(system$model.$coin2);
            this.twoHeads = system$model.getInferredValue(system$model.$twoHeads);
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
