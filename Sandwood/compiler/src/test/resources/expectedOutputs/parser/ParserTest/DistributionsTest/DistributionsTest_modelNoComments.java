package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model DistributionsTest This is the class that
  * all user interactions with the model should occur through.
  */
public class DistributionsTest extends Model {

    private DistributionsTest$CoreInterface system$c = new DistributionsTest$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $b0 = new ComputedDoubleInternal(this, "b0", true) {
        @Override
        public double getValue() { return system$c.get$b0(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$b0(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$b0(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample14(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample14())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing b0 of type double from the Sandwood model 
     */
    public final ComputedDouble b0 = $b0;

    private final ComputedDoubleInternal $b1 = new ComputedDoubleInternal(this, "b1", true) {
        @Override
        public double getValue() { return system$c.get$b1(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$b1(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$b1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample18(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample18())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing b1 of type double from the Sandwood model 
     */
    public final ComputedDouble b1 = $b1;

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
                system$c.set$fixedFlag$sample27(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample27())
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

    private final ObservedDoubleArrayInternal $x = new ObservedDoubleArrayInternal(this, "x") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$x();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$x(value); }
    };

    /**
     * Observed variable representing x of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray x = $x;

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
    private HasProbabilityInternal[] $probabilityVariables = {$b0, $b1, $y};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public DistributionsTest() {
        super();
        //ComputedVariable
        $computedVariables.put("b0", $b0);
        $computedVariables.put("b1", $b1);
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

    public DistributionsTest(double[] x) {
        this();
        this.$x.setValue(x);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param x The value to set x to.
      * @param yMeasured The value to set yMeasured to.
      */

    public DistributionsTest(double[] x, double[] yMeasured) {
        this();
        this.x.setValue(x);
        this.yMeasured.setValue(yMeasured);
    }
    
    @Override
    protected DistributionsTest$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        DistributionsTest$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new DistributionsTest$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new DistributionsTest$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(DistributionsTest$CoreInterface oldCore, DistributionsTest$CoreInterface newCore) {
        //Model inputs
        if(x.isSet())
            newCore.set$x(oldCore.get$x());
        //Observed scalars
        if(yMeasured.isSet())
            newCore.set$yMeasured(oldCore.get$yMeasured());

        //ComputedVariables
        if(b0.isSet())
            newCore.set$b0(oldCore.get$b0());
        if(b1.isSet())
            newCore.set$b1(oldCore.get$b1());
        if(y.isSet())
            newCore.set$y(oldCore.get$y());

        //Set fixed flags
        if(b0.isSet())
            newCore.set$fixedFlag$sample14(oldCore.get$fixedFlag$sample14());
        if(b1.isSet())
            newCore.set$fixedFlag$sample18(oldCore.get$fixedFlag$sample18());
        if(y.isSet())
            newCore.set$fixedFlag$sample27(oldCore.get$fixedFlag$sample27());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input x */
        public final double[] x;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param x The value to set x to.
          */
        public InferValueInputs(double[] x) {
            this.x = x;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input x */
        public final double[] x;
        /** Field holding the value of model input yMeasured */
        public final double[] yMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param x The value to set x to.
          * @param yMeasured The value to set yMeasured to.
          */
        public AllInputs(double[] x, double[] yMeasured) {
            this.x = x;
            this.yMeasured = yMeasured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of b0 after a convention execution step.*/
        public final double b0;
        /** Field holding the value of b1 after a convention execution step.*/
        public final double b1;
        /** Field holding the value of y after a convention execution step.*/
        public final double[] y;

        InferredValueOutputs(DistributionsTest system$model) {
            this.b0 = system$model.b0.getSamples()[0];
            this.b1 = system$model.b1.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable b0 */
        public final double b0;
        /** Field holding the log probability of computed variable b1 */
        public final double b1;
        /** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(DistributionsTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b0 = system$model.b0.getLogProbability();
            this.b1 = system$model.b1.getLogProbability();
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
        /** Field holding the probability of computed variable b0 */
        public final double b0;
        /** Field holding the probability of computed variable b1 */
        public final double b1;
        /** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(DistributionsTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b0 = system$model.b0.getProbability();
            this.b1 = system$model.b1.getProbability();
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
        /** Field holding the MAP or Sample value of b0 after an infer model call. */
        public final double[] b0;
        /** Field holding the MAP or Sample value of b1 after an infer model call. */
        public final double[] b1;

        InferredModelOutputs(DistributionsTest system$model) {
            this.b0 = system$model.getInferredValue(system$model.$b0);
            this.b1 = system$model.getInferredValue(system$model.$b1);
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
