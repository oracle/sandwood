package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model LogitRegressionTest This is the class that
  * all user interactions with the model should occur through.
  */
public class LogitRegressionTest extends Model {

    private LogitRegressionTest$CoreInterface system$c = new LogitRegressionTest$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", true) {
        @Override
        protected double getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$bias(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample38(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample38())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double from the Sandwood model 
     */
    public final ComputedDouble bias = $bias;

    private final ComputedDoubleArrayInternal $weights = new ComputedDoubleArrayInternal(this, "weights", true) {
        @Override
        protected double[] getValue() { return system$c.get$weights(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$weights(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$weights(); }

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
     * Computed variable representing weights of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray weights = $weights;

    private final ComputedObjectArrayInternal<boolean[]> $y = new ComputedObjectArrayInternal<boolean[]>(this, "y", true, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        protected boolean[][] getValue() { return system$c.get$y(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            system$c.set$y(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$y(); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample71(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample71())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing y of type boolean[][] from the Sandwood model 
     */
    public final ComputedObjectArray<boolean[]> y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<double[]> $x = new ObservedObjectArrayInternal<double[]>(this, "x", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] get() {
            synchronized(model) {
                return system$c.get$x();
            }
        }

        @Override
        protected void setValue(double[][] value) { system$c.set$x(value); }
    };

    /**
     * Observed variable representing x of type double[][] from the Sandwood model 
     */
    public final ObservedObjectArray<double[]> x = $x;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[]> $yMeasured = new ObservedObjectArrayInternal<boolean[]>(this, "yMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] get() {
            synchronized(model) {
                return system$c.get$yMeasured();
            }
        }

        @Override
        protected void setValue(boolean[][] value) { system$c.set$yMeasured(value); }
    };

    /**
     * Observed variable representing yMeasured of type boolean[][] from the Sandwood model 
     */
    public final ObservedObjectArray<boolean[]> yMeasured = $yMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $weights, $y};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public LogitRegressionTest() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
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

    public LogitRegressionTest(double[][] x) {
        this();
        this.$x.set(x);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param x The value to set x to.
      * @param yMeasured The value to set yMeasured to.
      */

    public LogitRegressionTest(double[][] x, boolean[][] yMeasured) {
        this();
        this.x.set(x);
        this.yMeasured.set(yMeasured);
    }
    
    @Override
    protected LogitRegressionTest$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        LogitRegressionTest$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new LogitRegressionTest$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new LogitRegressionTest$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(LogitRegressionTest$CoreInterface oldCore, LogitRegressionTest$CoreInterface newCore) {
        //Model inputs
        if(x.isSet())
            newCore.set$x(oldCore.get$x());
        //Observed scalars
        if(yMeasured.isSet())
            newCore.set$yMeasured(oldCore.get$yMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(weights.isSet())
            newCore.set$weights(oldCore.get$weights());
        if(y.isSet())
            newCore.set$y(oldCore.get$y());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample38(oldCore.get$fixedFlag$sample38());
        if(weights.isSet())
            newCore.set$fixedFlag$sample31(oldCore.get$fixedFlag$sample31());
        if(y.isSet())
            newCore.set$fixedFlag$sample71(oldCore.get$fixedFlag$sample71());
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
        public final boolean[][] yMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param x The value to set x to.
          * @param yMeasured The value to set yMeasured to.
          */
        public AllInputs(double[][] x, boolean[][] yMeasured) {
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
        /** Field holding the value of weights after a convention execution step.*/
        public final double[] weights;
        /** Field holding the value of y after a convention execution step.*/
        public final boolean[][] y;

        InferredValueOutputs(LogitRegressionTest system$model) {
            this.bias = system$model.bias.getSamples()[0];
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
        /** Field holding the log probability of computed variable weights */
        public final double weights;
        /** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LogitRegressionTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
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
        /** Field holding the probability of computed variable weights */
        public final double weights;
        /** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LogitRegressionTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
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
        /** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(LogitRegressionTest system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.weights = system$model.getInferredValue(system$model.$weights);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.x.set(inputs.x);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
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
        this.x.set(inputs.x);
        this.$yMeasured.set(inputs.yMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
