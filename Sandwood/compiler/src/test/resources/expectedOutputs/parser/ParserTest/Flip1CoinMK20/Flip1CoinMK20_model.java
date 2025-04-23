package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flip1CoinMK20 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flip1CoinMK20 extends Model {

    private Flip1CoinMK20$CoreInterface system$c = new Flip1CoinMK20$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", true, true, false) {
        @Override
        public double getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$bias(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample8(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample8())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double from the Sandwood model 
     */
    public final ComputedDouble bias = $bias;

    private final ComputedIntegerInternal $count1 = new ComputedIntegerInternal(this, "count1", false, true, false) {
        @Override
        public int getValue() { return system$c.get$count1(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable count1 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$count1(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("Variables that are fixed by observing other variables cannot be directly fixed. Please change the observed variable instead.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing count1 of type int from the Sandwood model 
     */
    public final ComputedInteger count1 = $count1;

    private final ComputedIntegerInternal $count2 = new ComputedIntegerInternal(this, "count2", false, true, false) {
        @Override
        public int getValue() { return system$c.get$count2(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable count2 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$count2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("Variables that are fixed by observing other variables cannot be directly fixed. Please change the observed variable instead.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing count2 of type int from the Sandwood model 
     */
    public final ComputedInteger count2 = $count2;

    private final ComputedIntegerInternal $total = new ComputedIntegerInternal(this, "total", false, false, false) {
        @Override
        public int getValue() { return system$c.get$total(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable total because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("Variables that are fixed by observing other variables cannot be directly fixed. Please change the observed variable instead.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing total of type int from the Sandwood model 
     */
    public final ComputedInteger total = $total;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerInternal $obs1 = new ObservedIntegerInternal(this, "obs1") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$obs1();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$obs1(value); }
    };

    /**
     * Observed variable representing obs1 of type int from the Sandwood model 
     */
    public final ObservedInteger obs1 = $obs1;

    private final ObservedIntegerInternal $obs2 = new ObservedIntegerInternal(this, "obs2") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$obs2();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$obs2(value); }
    };

    /**
     * Observed variable representing obs2 of type int from the Sandwood model 
     */
    public final ObservedInteger obs2 = $obs2;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $binomial = new RandomVariableInternal(this, "binomial") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$binomial();
        }
    };

    /**
     * Random variable representing binomial from the Sandwood model 
     */
    public final RandomVariable binomial = $binomial;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $count1, $count2, $binomial};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flip1CoinMK20() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("count1", $count1);
        $computedVariables.put("count2", $count2);
        $computedVariables.put("total", $total);

        //Observed scalar fields
        $regularObservedValues.put("obs1", $obs1);
        $regularObservedValues.put("obs2", $obs2);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param obs1 The value to set obs1 to.
      * @param obs2 The value to set obs2 to.
      */

    public Flip1CoinMK20(int obs1, int obs2) {
        this();
        this.obs1.setValue(obs1);
        this.obs2.setValue(obs2);
    }
    
    @Override
    protected Flip1CoinMK20$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flip1CoinMK20$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flip1CoinMK20$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flip1CoinMK20$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(Flip1CoinMK20$CoreInterface oldCore, Flip1CoinMK20$CoreInterface newCore) {
        //Observed scalars
        if(obs1.isSet())
            newCore.set$obs1(oldCore.get$obs1());
        if(obs2.isSet())
            newCore.set$obs2(oldCore.get$obs2());

        //ComputedVariables
        if($bias.isSet())
            newCore.set$bias(oldCore.get$bias());

        //Set fixed flags
        newCore.set$fixedFlag$sample8(oldCore.get$fixedFlag$sample8());
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
        /** Field holding the value of model input obs1 */
        public final int obs1;
        /** Field holding the value of model input obs2 */
        public final int obs2;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param obs1 The value to set obs1 to.
          * @param obs2 The value to set obs2 to.
          */
        public AllInputs(int obs1, int obs2) {
            this.obs1 = obs1;
            this.obs2 = obs2;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double bias;
        /** Field holding the value of count1 after a convention execution step.*/
        public final int count1;
        /** Field holding the value of count2 after a convention execution step.*/
        public final int count2;
        /** Field holding the value of total after a convention execution step.*/
        public final int total;

        InferredValueOutputs(Flip1CoinMK20 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.count1 = system$model.count1.getSamples()[0];
            this.count2 = system$model.count2.getSamples()[0];
            this.total = system$model.total.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable binomial */
        public final double binomial;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable count1 */
        public final double count1;
        /** Field holding the log probability of computed variable count2 */
        public final double count2;
        /** Field holding the log probability of computed variable total */
        public final double total;

        LogProbabilities(Flip1CoinMK20 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.binomial = system$model.binomial.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.count1 = system$model.count1.getLogProbability();
            this.count2 = system$model.count2.getLogProbability();
            this.total = system$model.total.getLogProbability();
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
        /** Field holding the probability of random variable binomial */
        public final double binomial;
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable count1 */
        public final double count1;
        /** Field holding the probability of computed variable count2 */
        public final double count2;
        /** Field holding the probability of computed variable total */
        public final double total;

        Probabilities(Flip1CoinMK20 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.binomial = system$model.binomial.getProbability();
            this.bias = system$model.bias.getProbability();
            this.count1 = system$model.count1.getProbability();
            this.count2 = system$model.count2.getProbability();
            this.total = system$model.total.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[] bias;

        InferredModelOutputs(Flip1CoinMK20 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
