package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Conditional2 This is the class that
  * all user interactions with the model should occur through.
  */
public class Conditional2 extends Model {

    private Conditional2$CoreInterface system$c = new Conditional2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $guard = new ComputedBooleanInternal(this, "guard", true) {
        @Override
        public boolean getValue() { return system$c.get$guard(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$guard(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$guard(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample7(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample7())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing guard of type boolean from the Sandwood model 
     */
    public final ComputedBoolean guard = $guard;

    private final ComputedDoubleArrayInternal $value = new ComputedDoubleArrayInternal(this, "value", true) {
        @Override
        public double[] getValue() { return system$c.get$value(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable value because its value depends on variable \"guard\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$value(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample24(fixed);
                system$c.set$fixedFlag$sample7(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample24 = system$c.get$fixedFlag$sample24();
            boolean fixedFlag$sample7 = system$c.get$fixedFlag$sample7();
            if(fixedFlag$sample24 && fixedFlag$sample7)
                return Immutability.FIXED;
            else if(fixedFlag$sample24 || fixedFlag$sample7)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing value of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray value = $value;

    private final ComputedDoubleArrayInternal $value2 = new ComputedDoubleArrayInternal(this, "value2", false) {
        @Override
        public double[] getValue() { return system$c.get$value2(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable value2 because its value depends on variables \"guard\", and \"value\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$value2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample24(fixed);
                system$c.set$fixedFlag$sample7(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample24 = system$c.get$fixedFlag$sample24();
            boolean fixedFlag$sample7 = system$c.get$fixedFlag$sample7();
            if(fixedFlag$sample24 && fixedFlag$sample7)
                return Immutability.FIXED;
            else if(fixedFlag$sample24 || fixedFlag$sample7)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing value2 of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray value2 = $value2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayInternal $observedValue = new ObservedDoubleArrayInternal(this, "observedValue") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$observedValue();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$observedValue(value); }
    };

    /**
     * Observed variable representing observedValue of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray observedValue = $observedValue;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $bernoulli = new RandomVariableInternal(this, "bernoulli") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$bernoulli();
        }
    };

    /**
     * Random variable representing bernoulli from the Sandwood model 
     */
    public final RandomVariable bernoulli = $bernoulli;

    private HasProbabilityInternal[] $probabilityVariables = {$guard, $value, $value2, $bernoulli};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Conditional2() {
        super();
        //ComputedVariable
        $computedVariables.put("guard", $guard);
        $computedVariables.put("value", $value);
        $computedVariables.put("value2", $value2);

        //Observed scalar fields
        $regularObservedValues.put("observedValue", $observedValue);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observedValue The value to set observedValue to.
      */

    public Conditional2(double[] observedValue) {
        this();
        this.observedValue.setValue(observedValue);
    }
    
    @Override
    protected Conditional2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Conditional2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Conditional2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Conditional2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Conditional2$CoreInterface oldCore, Conditional2$CoreInterface newCore) {
        //Observed scalars
        if(observedValue.isSet())
            newCore.set$observedValue(oldCore.get$observedValue());

        //ComputedVariables
        if(guard.isSet())
            newCore.set$guard(oldCore.get$guard());
        if(value.isSet())
            newCore.set$value(oldCore.get$value());

        //Set fixed flags
        if(guard.isSet())
            newCore.set$fixedFlag$sample7(oldCore.get$fixedFlag$sample7());
        if(value.isSet()){
            newCore.set$fixedFlag$sample24(oldCore.get$fixedFlag$sample24());
            newCore.set$fixedFlag$sample7(oldCore.get$fixedFlag$sample7());
        }
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
        /** Field holding the value of model input observedValue */
        public final double[] observedValue;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param observedValue The value to set observedValue to.
          */
        public AllInputs(double[] observedValue) {
            this.observedValue = observedValue;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of guard after a convention execution step.*/
        public final boolean guard;
        /** Field holding the value of value after a convention execution step.*/
        public final double[] value;
        /** Field holding the value of value2 after a convention execution step.*/
        public final double[] value2;

        InferredValueOutputs(Conditional2 system$model) {
            this.guard = system$model.guard.getSamples()[0];
            this.value = system$model.value.getSamples()[0];
            this.value2 = system$model.value2.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli */
        public final double bernoulli;
        /** Field holding the log probability of computed variable guard */
        public final double guard;
        /** Field holding the log probability of computed variable value */
        public final double value;
        /** Field holding the log probability of computed variable value2 */
        public final double value2;

        LogProbabilities(Conditional2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.guard = system$model.guard.getLogProbability();
            this.value = system$model.value.getLogProbability();
            this.value2 = system$model.value2.getLogProbability();
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
        /** Field holding the probability of random variable bernoulli */
        public final double bernoulli;
        /** Field holding the probability of computed variable guard */
        public final double guard;
        /** Field holding the probability of computed variable value */
        public final double value;
        /** Field holding the probability of computed variable value2 */
        public final double value2;

        Probabilities(Conditional2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.guard = system$model.guard.getProbability();
            this.value = system$model.value.getProbability();
            this.value2 = system$model.value2.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of guard after an infer model call. */
        public final boolean[] guard;
        /** Field holding the MAP or Sample value of value after an infer model call. */
        public final double[][] value;

        InferredModelOutputs(Conditional2 system$model) {
            this.guard = system$model.getInferredValue(system$model.$guard);
            this.value = system$model.getInferredValue(system$model.$value);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
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
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
