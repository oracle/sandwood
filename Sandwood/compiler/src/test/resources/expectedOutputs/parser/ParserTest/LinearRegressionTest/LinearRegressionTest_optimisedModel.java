package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model LinearRegressionTest This is the class that
  * all user interactions with the model should occur through.
  */
public class LinearRegressionTest extends Model {

    private LinearRegressionTest$CoreInterface system$c = new LinearRegressionTest$SingleThreadCPU(ExecutionTarget.singleThread);

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
     * Computed variable representing bias of type double from the Sandwood model 
     */
    public final ComputedDouble bias = $bias;

    private final ComputedDoubleInternal $tau = new ComputedDoubleInternal(this, "tau", true) {
        @Override
        public double getValue() { return system$c.get$tau(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$tau(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$tau(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample39(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample39())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing tau of type double from the Sandwood model 
     */
    public final ComputedDouble tau = $tau;

    private final ComputedDoubleArrayInternal $weights = new ComputedDoubleArrayInternal(this, "weights", true) {
        @Override
        public double[] getValue() { return system$c.get$weights(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$weights(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$weights(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample28(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample28())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing weights of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray weights = $weights;

    private final ComputedDoubleArrayInternal $y = new ComputedDoubleArrayInternal(this, "y", true) {
        @Override
        public double[] getValue() { return system$c.get$y(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$y(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample63(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample63())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing y of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<double[]> $x = new ObservedObjectArrayInternal<double[]>(this, "x", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() {
            synchronized(model) {
                return system$c.get$x();
            }
        }

        @Override
        protected void setValueInternal(double[][] value) { system$c.set$x(value); }
    };

    /**
     * Observed variable representing x of type double[][] from the Sandwood model 
     */
    public final ObservedObjectArray<double[]> x = $x;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayInternal $yMeasured = new ObservedDoubleArrayInternal(this, "yMeasured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$yMeasured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$yMeasured(value); }
    };

    /**
     * Observed variable representing yMeasured of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray yMeasured = $yMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $tau, $weights, $y};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public LinearRegressionTest() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("tau", $tau);
        $computedVariables.put("weights", $weights);
        $computedVariables.put("y", $y);

        //ModelInputs
        $modelInputs.put("x", $x);

        //Observed scalar fields
        $regularObservedValues.put("yMeasured", $yMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param x The value to set x to.
      */

    public LinearRegressionTest(double[][] x) {
        this();
        this.$x.setValue(x);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param x The value to set x to.
      * @param yMeasured The value to set yMeasured to.
      */

    public LinearRegressionTest(double[][] x, double[] yMeasured) {
        this();
        this.x.setValue(x);
        this.yMeasured.setValue(yMeasured);
    }
    
    @Override
    protected LinearRegressionTest$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        LinearRegressionTest$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new LinearRegressionTest$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new LinearRegressionTest$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(LinearRegressionTest$CoreInterface oldCore, LinearRegressionTest$CoreInterface newCore) {
        //Model inputs
        if(x.isSet())
            newCore.set$x(oldCore.get$x());
        //Observed scalars
        if(yMeasured.isSet())
            newCore.set$yMeasured(oldCore.get$yMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(tau.isSet())
            newCore.set$tau(oldCore.get$tau());
        if(weights.isSet())
            newCore.set$weights(oldCore.get$weights());
        if(y.isSet())
            newCore.set$y(oldCore.get$y());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample35(oldCore.get$fixedFlag$sample35());
        if(tau.isSet())
            newCore.set$fixedFlag$sample39(oldCore.get$fixedFlag$sample39());
        if(weights.isSet())
            newCore.set$fixedFlag$sample28(oldCore.get$fixedFlag$sample28());
        if(y.isSet())
            newCore.set$fixedFlag$sample63(oldCore.get$fixedFlag$sample63());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input x */
        public final double[][] x;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param x The value to set x to.
          */
        public InferValueInputs(double[][] x) {
            this.x = x;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input x */
        public final double[][] x;
        /** Field holding the value of model input yMeasured */
        public final double[] yMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param x The value to set x to.
          * @param yMeasured The value to set yMeasured to.
          */
        public AllInputs(double[][] x, double[] yMeasured) {
            this.x = x;
            this.yMeasured = yMeasured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double bias;
        /** Field holding the value of tau after a convention execution step.*/
        public final double tau;
        /** Field holding the value of weights after a convention execution step.*/
        public final double[] weights;
        /** Field holding the value of y after a convention execution step.*/
        public final double[] y;

        InferredValueOutputs(LinearRegressionTest system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.tau = system$model.tau.getSamples()[0];
            this.weights = system$model.weights.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable tau */
        public final double tau;
        /** Field holding the log probability of computed variable weights */
        public final double weights;
        /** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LinearRegressionTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.tau = system$model.tau.getLogProbability();
            this.weights = system$model.weights.getLogProbability();
            this.y = system$model.y.getLogProbability();
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
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable tau */
        public final double tau;
        /** Field holding the probability of computed variable weights */
        public final double weights;
        /** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LinearRegressionTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.tau = system$model.tau.getProbability();
            this.weights = system$model.weights.getProbability();
            this.y = system$model.y.getProbability();
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
        /** Field holding the MAP or Sample value of tau after an infer model call. */
        public final double[] tau;
        /** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(LinearRegressionTest system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.tau = system$model.getInferredValue(system$model.$tau);
            this.weights = system$model.getInferredValue(system$model.$weights);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.x.setValue(inputs.x);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
