package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flip2CoinsMK5 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flip2CoinsMK5 extends Model {

    private Flip2CoinsMK5$CoreInterface system$c = new Flip2CoinsMK5$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $bias = new ComputedDoubleArrayInternal(this, "bias", true) {
        @Override
        protected double[] getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$bias(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample20(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample20())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedObjectArrayInternal<boolean[]> $flips = new ComputedObjectArrayInternal<boolean[]>(this, "flips", true, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        protected boolean[][] getValue() { return system$c.get$flips(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            system$c.set$flips(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flips(); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample33(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample33())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips of type boolean[][] from the Sandwood model 
     */
    public final ComputedObjectArray<boolean[]> flips = $flips;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerArrayInternal $shape = new ObservedIntegerArrayInternal(this, "shape") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return system$c.get$shape();
            }
        }

        @Override
        protected void setValueInternal(int[] value) { system$c.set$shape(value); }
    };

    /**
     * Observed variable representing shape of type int[] from the Sandwood model 
     */
    public final ObservedIntegerArray shape = $shape;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[]> $flipsMeasured = new ObservedObjectArrayInternal<boolean[]>(this, "flipsMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[][] value) { system$c.set$flipsMeasured(value); }
    };

    /**
     * Observed variable representing flipsMeasured of type boolean[][] from the Sandwood model 
     */
    public final ObservedObjectArray<boolean[]> flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final IteratedRandomVariableInternal<double[]> $bernoulli = new IteratedRandomVariableInternal<double[]>(this, "bernoulli", 1) {
        @Override
        public double[] getCurrentLogProbability() {
            return system$c.get$logProbability$bernoulli();
        }
    };

    /**
     * Iterated random variable representing the random variable bernoulli from
     * the Sandwood model. The random variable is embedded in a loop
     * so results are returned as anarray.
     */
    public final IteratedRandomVariable<double[]> bernoulli = $bernoulli;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $bernoulli};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flip2CoinsMK5() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);

        //ModelInputs
        $modelInputs.put("shape", $shape);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param shape The value to set shape to.
      */

    public Flip2CoinsMK5(int[] shape) {
        this();
        this.$shape.setValue(shape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param flipsMeasured The value to set flipsMeasured to.
      * @param shape The value to set shape to.
      */

    public Flip2CoinsMK5(boolean[][] flipsMeasured, int[] shape) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
        this.shape.setValue(shape);
    }
    
    @Override
    protected Flip2CoinsMK5$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flip2CoinsMK5$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flip2CoinsMK5$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flip2CoinsMK5$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Flip2CoinsMK5$CoreInterface oldCore, Flip2CoinsMK5$CoreInterface newCore) {
        //Model inputs
        if(shape.isSet())
            newCore.set$shape(oldCore.get$shape());
        //Observed scalars
        if(flipsMeasured.isSet())
            newCore.set$flipsMeasured(oldCore.get$flipsMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(flips.isSet())
            newCore.set$flips(oldCore.get$flips());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample20(oldCore.get$fixedFlag$sample20());
        if(flips.isSet())
            newCore.set$fixedFlag$sample33(oldCore.get$fixedFlag$sample33());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input shape */
        public final int[] shape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param shape The value to set shape to.
          */
        public InferValueInputs(int[] shape) {
            this.shape = shape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input flipsMeasured */
        public final boolean[][] flipsMeasured;
        /** Field holding the value of model input shape */
        public final int[] shape;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param flipsMeasured The value to set flipsMeasured to.
          * @param shape The value to set shape to.
          */
        public AllInputs(boolean[][] flipsMeasured, int[] shape) {
            this.flipsMeasured = flipsMeasured;
            this.shape = shape;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double[] bias;
        /** Field holding the value of flips after a convention execution step.*/
        public final boolean[][] flips;

        InferredValueOutputs(Flip2CoinsMK5 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli */
        public final double[] bernoulli;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip2CoinsMK5 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
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
        public final double[] bernoulli;
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip2CoinsMK5 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
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
        public final double[][] bias;

        InferredModelOutputs(Flip2CoinsMK5 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
