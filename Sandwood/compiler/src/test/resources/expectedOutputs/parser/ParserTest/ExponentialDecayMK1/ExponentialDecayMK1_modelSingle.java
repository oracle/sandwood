package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model ExponentialDecayMK1 This is the class that
  * all user interactions with the model should occur through.
  */
public class ExponentialDecayMK1 extends Model {

    private ExponentialDecayMK1$CoreInterface system$c = new ExponentialDecayMK1$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $decay = new ComputedDoubleArrayInternal(this, "decay", true) {
        @Override
        protected double[] getValue() { return system$c.get$decay(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$decay(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$decay(); }

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
     * Computed variable representing decay of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray decay = $decay;

    private final ComputedDoubleInternal $rate = new ComputedDoubleInternal(this, "rate", true) {
        @Override
        protected double getValue() { return system$c.get$rate(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$rate(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$rate(); }

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
     * Computed variable representing rate of type double from the Sandwood model 
     */
    public final ComputedDouble rate = $rate;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedDoubleInternal $a = new ObservedDoubleInternal(this, "a") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$a();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$a(value); }
    };

    /**
     * Observed variable representing a of type double from the Sandwood model 
     */
    public final ObservedDouble a = $a;

    private final ObservedDoubleInternal $b = new ObservedDoubleInternal(this, "b") {
        @Override
        public double get() {
            synchronized(model) {
                return system$c.get$b();
            }
        }

        @Override
        protected void setValue(double value) { system$c.set$b(value); }
    };

    /**
     * Observed variable representing b of type double from the Sandwood model 
     */
    public final ObservedDouble b = $b;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayShapeableInternal $decayDetected = new ObservedDoubleArrayShapeableInternal(this, "decayDetected") {
        @Override
        public double[] get() {
            synchronized(model) {
                return system$c.get$decayDetected();
            }
        }

        @Override
        public void setValue(double[] value) {
            system$c.set$decayDetected(value);
            system$c.set$length$decayDetected(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$decayDetected(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$decayDetected();
        }
    };

    /**
     * Observed variable representing decayDetected of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArrayShapeable decayDetected = $decayDetected;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $exponential = new RandomVariableInternal(this, "exponential") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$exponential();
        }
    };

    /**
     * Random variable representing exponential from the Sandwood model 
     */
    public final RandomVariable exponential = $exponential;

    private HasProbabilityInternal[] $probabilityVariables = {$decay, $rate, $exponential};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public ExponentialDecayMK1() {
        super();
        //ComputedVariable
        $computedVariables.put("decay", $decay);
        $computedVariables.put("rate", $rate);

        //ModelInputs
        $modelInputs.put("a", $a);
        $modelInputs.put("b", $b);

        //Observed array fields
        $shapedObservedValues.put("decayDetected", $decayDetected);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param decayDetectedShape An integer array describing the shape of variable decayDetected to use in the model when generating results.
      * @param a The value to set a to.
      * @param b The value to set b to.
      */

    public ExponentialDecayMK1(int decayDetectedShape, double a, double b) {
        this();
        this.$a.set(a);
        this.$b.set(b);
        this.$decayDetected.setShape(decayDetectedShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param decayDetected The value to set decayDetected to.
      * @param a The value to set a to.
      * @param b The value to set b to.
      */

    public ExponentialDecayMK1(double[] decayDetected, double a, double b) {
        this();
        this.decayDetected.set(decayDetected);
        this.a.set(a);
        this.b.set(b);
    }
    
    @Override
    protected ExponentialDecayMK1$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        ExponentialDecayMK1$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new ExponentialDecayMK1$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new ExponentialDecayMK1$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(ExponentialDecayMK1$CoreInterface oldCore, ExponentialDecayMK1$CoreInterface newCore) {
        //Model inputs
        if(a.isSet())
            newCore.set$a(oldCore.get$a());
        if(b.isSet())
            newCore.set$b(oldCore.get$b());

        //Observed arrays
        if(decayDetected.isSet()) {
            newCore.set$decayDetected(oldCore.get$decayDetected());
            newCore.set$length$decayDetected(oldCore.get$length$decayDetected());
        }
        else if(decayDetected.shapeSet())
            newCore.set$length$decayDetected(oldCore.get$length$decayDetected());

        //ComputedVariables
        if(decay.isSet())
            newCore.set$decay(oldCore.get$decay());
        if(rate.isSet())
            newCore.set$rate(oldCore.get$rate());

        //Set fixed flags
        if(decay.isSet())
            newCore.set$fixedFlag$sample16(oldCore.get$fixedFlag$sample16());
        if(rate.isSet())
            newCore.set$fixedFlag$sample10(oldCore.get$fixedFlag$sample10());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input decayDetected */
        public final int decayDetectedShape;
        /** Field holding the value of model input a */
        public final double a;
        /** Field holding the value of model input b */
        public final double b;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param decayDetectedShape An integer array describing the shape of variable decayDetected to use in the model when generating results.
          * @param a The value to set a to.
          * @param b The value to set b to.
          */
        public InferValueInputs(int decayDetectedShape, double a, double b) {
            this.a = a;
            this.b = b;
            this.decayDetectedShape = decayDetectedShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input decayDetected */
        public final double[] decayDetected;
        /** Field holding the value of model input a */
        public final double a;
        /** Field holding the value of model input b */
        public final double b;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param decayDetected The value to set decayDetected to.
          * @param a The value to set a to.
          * @param b The value to set b to.
          */
        public AllInputs(double[] decayDetected, double a, double b) {
            this.decayDetected = decayDetected;
            this.a = a;
            this.b = b;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of decay after a convention execution step.*/
        public final double[] decay;
        /** Field holding the value of rate after a convention execution step.*/
        public final double rate;

        InferredValueOutputs(ExponentialDecayMK1 system$model) {
            this.decay = system$model.decay.getSamples()[0];
            this.rate = system$model.rate.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable exponential */
        public final double exponential;
        /** Field holding the log probability of computed variable decay */
        public final double decay;
        /** Field holding the log probability of computed variable rate */
        public final double rate;

        LogProbabilities(ExponentialDecayMK1 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.exponential = system$model.exponential.getLogProbability();
            this.decay = system$model.decay.getLogProbability();
            this.rate = system$model.rate.getLogProbability();
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
        /** Field holding the probability of random variable exponential */
        public final double exponential;
        /** Field holding the probability of computed variable decay */
        public final double decay;
        /** Field holding the probability of computed variable rate */
        public final double rate;

        Probabilities(ExponentialDecayMK1 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.exponential = system$model.exponential.getProbability();
            this.decay = system$model.decay.getProbability();
            this.rate = system$model.rate.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of rate after an infer model call. */
        public final double[] rate;

        InferredModelOutputs(ExponentialDecayMK1 system$model) {
            this.rate = system$model.getInferredValue(system$model.$rate);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.setShape(inputs.decayDetectedShape);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
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
        this.a.set(inputs.a);
        this.b.set(inputs.b);
        this.$decayDetected.set(inputs.decayDetected);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
