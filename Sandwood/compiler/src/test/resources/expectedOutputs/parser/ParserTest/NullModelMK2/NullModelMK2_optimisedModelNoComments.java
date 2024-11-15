package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model NullModelMK2 This is the class that
  * all user interactions with the model should occur through.
  */
public class NullModelMK2 extends Model {

    private NullModelMK2$CoreInterface system$c = new NullModelMK2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", true) {
        @Override
        public double getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$bias(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

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
     * Computed variable representing bias of type double from the Sandwood model 
     */
    public final ComputedDouble bias = $bias;

    private final ComputedIntegerInternal $positiveCount = new ComputedIntegerInternal(this, "positiveCount", true) {
        @Override
        public int getValue() { return system$c.get$positiveCount(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$positiveCount(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$positiveCount(); }

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
     * Computed variable representing positiveCount of type int from the Sandwood model 
     */
    public final ComputedInteger positiveCount = $positiveCount;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedDoubleInternal $eta = new ObservedDoubleInternal(this, "eta") {
        @Override
        public double getValue() {
            synchronized(model) {
                return system$c.get$eta();
            }
        }

        @Override
        protected void setValueInternal(double value) { system$c.set$eta(value); }
    };

    /**
     * Observed variable representing eta of type double from the Sandwood model 
     */
    public final ObservedDouble eta = $eta;

    private final ObservedIntegerInternal $observedSampleCount = new ObservedIntegerInternal(this, "observedSampleCount") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$observedSampleCount();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$observedSampleCount(value); }
    };

    /**
     * Observed variable representing observedSampleCount of type int from the Sandwood model 
     */
    public final ObservedInteger observedSampleCount = $observedSampleCount;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerInternal $observedPositiveCount = new ObservedIntegerInternal(this, "observedPositiveCount") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$observedPositiveCount();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$observedPositiveCount(value); }
    };

    /**
     * Observed variable representing observedPositiveCount of type int from the Sandwood model 
     */
    public final ObservedInteger observedPositiveCount = $observedPositiveCount;

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

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $positiveCount, $binomial};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public NullModelMK2() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("positiveCount", $positiveCount);

        //ModelInputs
        $modelInputs.put("eta", $eta);
        $modelInputs.put("observedSampleCount", $observedSampleCount);

        //Observed scalar fields
        $regularObservedValues.put("observedPositiveCount", $observedPositiveCount);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param eta The value to set eta to.
      * @param observedSampleCount The value to set observedSampleCount to.
      */

    public NullModelMK2(double eta, int observedSampleCount) {
        this();
        this.$eta.setValue(eta);
        this.$observedSampleCount.setValue(observedSampleCount);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param eta The value to set eta to.
      * @param observedSampleCount The value to set observedSampleCount to.
      * @param observedPositiveCount The value to set observedPositiveCount to.
      */

    public NullModelMK2(double eta, int observedSampleCount, int observedPositiveCount) {
        this();
        this.eta.setValue(eta);
        this.observedSampleCount.setValue(observedSampleCount);
        this.observedPositiveCount.setValue(observedPositiveCount);
    }
    
    @Override
    protected NullModelMK2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        NullModelMK2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new NullModelMK2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new NullModelMK2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(NullModelMK2$CoreInterface oldCore, NullModelMK2$CoreInterface newCore) {
        //Model inputs
        if(eta.isSet())
            newCore.set$eta(oldCore.get$eta());
        if(observedSampleCount.isSet())
            newCore.set$observedSampleCount(oldCore.get$observedSampleCount());
        //Observed scalars
        if(observedPositiveCount.isSet())
            newCore.set$observedPositiveCount(oldCore.get$observedPositiveCount());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(positiveCount.isSet())
            newCore.set$positiveCount(oldCore.get$positiveCount());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample10(oldCore.get$fixedFlag$sample10());
        if(positiveCount.isSet())
            newCore.set$fixedFlag$sample12(oldCore.get$fixedFlag$sample12());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input eta */
        public final double eta;
        /** Field holding the value of model input observedSampleCount */
        public final int observedSampleCount;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param eta The value to set eta to.
          * @param observedSampleCount The value to set observedSampleCount to.
          */
        public InferValueInputs(double eta, int observedSampleCount) {
            this.eta = eta;
            this.observedSampleCount = observedSampleCount;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input eta */
        public final double eta;
        /** Field holding the value of model input observedSampleCount */
        public final int observedSampleCount;
        /** Field holding the value of model input observedPositiveCount */
        public final int observedPositiveCount;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param eta The value to set eta to.
          * @param observedSampleCount The value to set observedSampleCount to.
          * @param observedPositiveCount The value to set observedPositiveCount to.
          */
        public AllInputs(double eta, int observedSampleCount, int observedPositiveCount) {
            this.eta = eta;
            this.observedSampleCount = observedSampleCount;
            this.observedPositiveCount = observedPositiveCount;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double bias;
        /** Field holding the value of positiveCount after a convention execution step.*/
        public final int positiveCount;

        InferredValueOutputs(NullModelMK2 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.positiveCount = system$model.positiveCount.getSamples()[0];
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
        /** Field holding the log probability of computed variable positiveCount */
        public final double positiveCount;

        LogProbabilities(NullModelMK2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.binomial = system$model.binomial.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.positiveCount = system$model.positiveCount.getLogProbability();
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
        /** Field holding the probability of computed variable positiveCount */
        public final double positiveCount;

        Probabilities(NullModelMK2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.binomial = system$model.binomial.getProbability();
            this.bias = system$model.bias.getProbability();
            this.positiveCount = system$model.positiveCount.getProbability();
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

        InferredModelOutputs(NullModelMK2 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
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
        this.eta.setValue(inputs.eta);
        this.observedSampleCount.setValue(inputs.observedSampleCount);
        this.$observedPositiveCount.setValue(inputs.observedPositiveCount);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
