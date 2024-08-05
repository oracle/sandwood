package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model DistributionTest1b This is the class that
  * all user interactions with the model should occur through.
  */
public class DistributionTest1b extends Model {

    private DistributionTest1b$CoreInterface system$c = new DistributionTest1b$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanInternal $v = new ComputedBooleanInternal(this, "v", true) {
        @Override
        protected boolean getValue() { return system$c.get$v(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$v(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$v(); }

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
     * Computed variable representing v of type boolean from the Sandwood model 
     */
    public final ComputedBoolean v = $v;

    private final ComputedIntegerInternal $v1 = new ComputedIntegerInternal(this, "v1", true) {
        @Override
        protected int getValue() { return system$c.get$v1(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$v1(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$v1(); }

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
     * Computed variable representing v1 of type int from the Sandwood model 
     */
    public final ComputedInteger v1 = $v1;

    private final ComputedIntegerInternal $v2 = new ComputedIntegerInternal(this, "v2", true) {
        @Override
        protected int getValue() { return system$c.get$v2(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$v2(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$v2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample9(fixed);
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
     * Computed variable representing v2 of type int from the Sandwood model 
     */
    public final ComputedInteger v2 = $v2;

    private final ComputedIntegerInternal $v3 = new ComputedIntegerInternal(this, "v3", true) {
        @Override
        protected int getValue() { return system$c.get$v3(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$v3(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$v3(); }

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
     * Computed variable representing v3 of type int from the Sandwood model 
     */
    public final ComputedInteger v3 = $v3;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedDoubleArrayInternal $weightings = new ObservedDoubleArrayInternal(this, "weightings") {
        @Override
        public double[] get() {
            synchronized(model) {
                return system$c.get$weightings();
            }
        }

        @Override
        protected void setValue(double[] value) { system$c.set$weightings(value); }
    };

    /**
     * Observed variable representing weightings of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray weightings = $weightings;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanInternal $value = new ObservedBooleanInternal(this, "value") {
        @Override
        public boolean get() {
            synchronized(model) {
                return system$c.get$value();
            }
        }

        @Override
        protected void setValue(boolean value) { system$c.set$value(value); }
    };

    /**
     * Observed variable representing value of type boolean from the Sandwood model 
     */
    public final ObservedBoolean value = $value;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $c = new RandomVariableInternal(this, "c") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$c();
        }
    };

    /**
     * Random variable representing c from the Sandwood model 
     */
    public final RandomVariable c = $c;

    private HasProbabilityInternal[] $probabilityVariables = {$v, $v1, $v2, $v3, $c};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public DistributionTest1b() {
        super();
        //ComputedVariable
        $computedVariables.put("v", $v);
        $computedVariables.put("v1", $v1);
        $computedVariables.put("v2", $v2);
        $computedVariables.put("v3", $v3);

        //ModelInputs
        $modelInputs.put("weightings", $weightings);

        //Observed scalar fields
        $regularObservedValues.put("value", $value);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param weightings The value to set weightings to.
      */

    public DistributionTest1b(double[] weightings) {
        this();
        this.$weightings.set(weightings);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param weightings The value to set weightings to.
      * @param value The value to set value to.
      */

    public DistributionTest1b(double[] weightings, boolean value) {
        this();
        this.weightings.set(weightings);
        this.value.set(value);
    }
    
    @Override
    protected DistributionTest1b$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        DistributionTest1b$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new DistributionTest1b$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new DistributionTest1b$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(DistributionTest1b$CoreInterface oldCore, DistributionTest1b$CoreInterface newCore) {
        //Model inputs
        if(weightings.isSet())
            newCore.set$weightings(oldCore.get$weightings());
        //Observed scalars
        if(value.isSet())
            newCore.set$value(oldCore.get$value());

        //ComputedVariables
        if(v.isSet())
            newCore.set$v(oldCore.get$v());
        if(v1.isSet())
            newCore.set$v1(oldCore.get$v1());
        if(v2.isSet())
            newCore.set$v2(oldCore.get$v2());
        if(v3.isSet())
            newCore.set$v3(oldCore.get$v3());

        //Set fixed flags
        if(v.isSet())
            newCore.set$fixedFlag$sample16(oldCore.get$fixedFlag$sample16());
        if(v1.isSet())
            newCore.set$fixedFlag$sample7(oldCore.get$fixedFlag$sample7());
        if(v2.isSet())
            newCore.set$fixedFlag$sample9(oldCore.get$fixedFlag$sample9());
        if(v3.isSet())
            newCore.set$fixedFlag$sample10(oldCore.get$fixedFlag$sample10());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input weightings */
        public final double[] weightings;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param weightings The value to set weightings to.
          */
        public InferValueInputs(double[] weightings) {
            this.weightings = weightings;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input weightings */
        public final double[] weightings;
        /** Field holding the value of model input value */
        public final boolean value;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param weightings The value to set weightings to.
          * @param value The value to set value to.
          */
        public AllInputs(double[] weightings, boolean value) {
            this.weightings = weightings;
            this.value = value;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of v after a convention execution step.*/
        public final boolean v;
        /** Field holding the value of v1 after a convention execution step.*/
        public final int v1;
        /** Field holding the value of v2 after a convention execution step.*/
        public final int v2;
        /** Field holding the value of v3 after a convention execution step.*/
        public final int v3;

        InferredValueOutputs(DistributionTest1b system$model) {
            this.v = system$model.v.getSamples()[0];
            this.v1 = system$model.v1.getSamples()[0];
            this.v2 = system$model.v2.getSamples()[0];
            this.v3 = system$model.v3.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable c */
        public final double c;
        /** Field holding the log probability of computed variable v */
        public final double v;
        /** Field holding the log probability of computed variable v1 */
        public final double v1;
        /** Field holding the log probability of computed variable v2 */
        public final double v2;
        /** Field holding the log probability of computed variable v3 */
        public final double v3;

        LogProbabilities(DistributionTest1b system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.c = system$model.c.getLogProbability();
            this.v = system$model.v.getLogProbability();
            this.v1 = system$model.v1.getLogProbability();
            this.v2 = system$model.v2.getLogProbability();
            this.v3 = system$model.v3.getLogProbability();
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
        /** Field holding the probability of random variable c */
        public final double c;
        /** Field holding the probability of computed variable v */
        public final double v;
        /** Field holding the probability of computed variable v1 */
        public final double v1;
        /** Field holding the probability of computed variable v2 */
        public final double v2;
        /** Field holding the probability of computed variable v3 */
        public final double v3;

        Probabilities(DistributionTest1b system$model) {
            this.$modelProbability = system$model.getProbability();
            this.c = system$model.c.getProbability();
            this.v = system$model.v.getProbability();
            this.v1 = system$model.v1.getProbability();
            this.v2 = system$model.v2.getProbability();
            this.v3 = system$model.v3.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of v1 after an infer model call. */
        public final int[] v1;
        /** Field holding the MAP or Sample value of v2 after an infer model call. */
        public final int[] v2;
        /** Field holding the MAP or Sample value of v3 after an infer model call. */
        public final int[] v3;

        InferredModelOutputs(DistributionTest1b system$model) {
            this.v1 = system$model.getInferredValue(system$model.$v1);
            this.v2 = system$model.getInferredValue(system$model.$v2);
            this.v3 = system$model.getInferredValue(system$model.$v3);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.weightings.set(inputs.weightings);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
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
        this.weightings.set(inputs.weightings);
        this.$value.set(inputs.value);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
