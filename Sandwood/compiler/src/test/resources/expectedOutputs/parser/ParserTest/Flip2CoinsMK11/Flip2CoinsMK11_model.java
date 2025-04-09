package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flip2CoinsMK11 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flip2CoinsMK11 extends Model {

    private Flip2CoinsMK11$CoreInterface system$c = new Flip2CoinsMK11$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $bias = new ComputedDoubleArrayInternal(this, "bias", true) {
        @Override
        public double[] getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$bias(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample15(fixed);
                system$c.set$fixedFlag$sample20(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample15 = system$c.get$fixedFlag$sample15();
            boolean fixedFlag$sample20 = system$c.get$fixedFlag$sample20();
            if(fixedFlag$sample15 && fixedFlag$sample20)
                return Immutability.FIXED;
            else if(fixedFlag$sample15 || fixedFlag$sample20)
                return Immutability.PARTIALLY_FIXED;
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
        public boolean[][] getValue() { return system$c.get$flips(); }

        @Override
        protected void setValueInternal(boolean[][] value) {
            system$c.set$flips(value);
            valueSet = true;
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
                system$c.set$fixedFlag$sample36(fixed);
                system$c.set$fixedFlag$sample52(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample36 = system$c.get$fixedFlag$sample36();
            boolean fixedFlag$sample52 = system$c.get$fixedFlag$sample52();
            if(fixedFlag$sample36 && fixedFlag$sample52)
                return Immutability.FIXED;
            else if(fixedFlag$sample36 || fixedFlag$sample52)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips of type boolean[][] from the Sandwood model 
     */
    public final ComputedObjectArray<boolean[]> flips = $flips;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayShapeableInternal<boolean[], int[]> $flipsMeasured = new ObservedObjectArrayShapeableInternal<boolean[], int[]>(this, "flipsMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured();
            }
        }

        @Override
        public void setValueInternal(boolean[][] value) {
            system$c.set$flipsMeasured(value);
            system$c.set$length$flipsMeasured(getDims(value));
        }

        @Override
        public void setShapeInternal(int[] shape) {
            system$c.set$length$flipsMeasured(shape);
        }

        @Override
        public int[] getShape() {
            return system$c.get$length$flipsMeasured();
        }
        private final int[] getDims(boolean[][] v1) {
            int[] s1 = new int[v1.length];
            for(int i1 = 0; i1 < v1.length; i1++) {
                boolean[] v0 = v1[i1];
                s1[i1] = v0.length;
            }
            return s1;
        }
    };

    /**
     * Observed variable representing flipsMeasured of type boolean[][] from the Sandwood model 
     */
    public final ObservedObjectArrayShapeable<boolean[], int[]> flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final IteratedRandomVariableInternal<double[]> $bernoulli1 = new IteratedRandomVariableInternal<double[]>(this, "bernoulli1", 1) {
        @Override
        public double[] getCurrentLogProbability() {
            return system$c.get$logProbability$bernoulli1();
        }
    };

    /**
     * Iterated random variable representing the random variable bernoulli1 from
     * the Sandwood model. The random variable is embedded in a loop
     * so results are returned as anarray.
     */
    public final IteratedRandomVariable<double[]> bernoulli1 = $bernoulli1;

    private final IteratedRandomVariableInternal<double[]> $bernoulli2 = new IteratedRandomVariableInternal<double[]>(this, "bernoulli2", 1) {
        @Override
        public double[] getCurrentLogProbability() {
            return system$c.get$logProbability$bernoulli2();
        }
    };

    /**
     * Iterated random variable representing the random variable bernoulli2 from
     * the Sandwood model. The random variable is embedded in a loop
     * so results are returned as anarray.
     */
    public final IteratedRandomVariable<double[]> bernoulli2 = $bernoulli2;

    private final RandomVariableInternal $beta = new RandomVariableInternal(this, "beta") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$beta();
        }
    };

    /**
     * Random variable representing beta from the Sandwood model 
     */
    public final RandomVariable beta = $beta;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $bernoulli1, $bernoulli2, $beta};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flip2CoinsMK11() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured to use in the model when generating results.
      */

    public Flip2CoinsMK11(int[] flipsMeasuredShape) {
        this();
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param flipsMeasured The value to set flipsMeasured to.
      */

    public Flip2CoinsMK11(boolean[][] flipsMeasured) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected Flip2CoinsMK11$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flip2CoinsMK11$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flip2CoinsMK11$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flip2CoinsMK11$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Flip2CoinsMK11$CoreInterface oldCore, Flip2CoinsMK11$CoreInterface newCore) {

        //Observed arrays
        if(flipsMeasured.isSet()) {
            newCore.set$flipsMeasured(oldCore.get$flipsMeasured());
            newCore.set$length$flipsMeasured(oldCore.get$length$flipsMeasured());
        }
        else if(flipsMeasured.shapeSet())
            newCore.set$length$flipsMeasured(oldCore.get$length$flipsMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(flips.isSet())
            newCore.set$flips(oldCore.get$flips());

        //Set fixed flags
        if(bias.isSet()){
            newCore.set$fixedFlag$sample15(oldCore.get$fixedFlag$sample15());
            newCore.set$fixedFlag$sample20(oldCore.get$fixedFlag$sample20());
        }
        if(flips.isSet()){
            newCore.set$fixedFlag$sample36(oldCore.get$fixedFlag$sample36());
            newCore.set$fixedFlag$sample52(oldCore.get$fixedFlag$sample52());
        }
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input flipsMeasured */
        public final int[] flipsMeasuredShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured to use in the model when generating results.
          */
        public InferValueInputs(int[] flipsMeasuredShape) {
            this.flipsMeasuredShape = flipsMeasuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input flipsMeasured */
        public final boolean[][] flipsMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param flipsMeasured The value to set flipsMeasured to.
          */
        public AllInputs(boolean[][] flipsMeasured) {
            this.flipsMeasured = flipsMeasured;
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

        InferredValueOutputs(Flip2CoinsMK11 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli1 */
        public final double[] bernoulli1;
        /** Field holding the log probability of random variable bernoulli2 */
        public final double[] bernoulli2;
        /** Field holding the log probability of random variable beta */
        public final double beta;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip2CoinsMK11 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli1 = system$model.bernoulli1.getLogProbability();
            this.bernoulli2 = system$model.bernoulli2.getLogProbability();
            this.beta = system$model.beta.getLogProbability();
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
        /** Field holding the probability of random variable bernoulli1 */
        public final double[] bernoulli1;
        /** Field holding the probability of random variable bernoulli2 */
        public final double[] bernoulli2;
        /** Field holding the probability of random variable beta */
        public final double beta;
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip2CoinsMK11 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli1 = system$model.bernoulli1.getProbability();
            this.bernoulli2 = system$model.bernoulli2.getProbability();
            this.beta = system$model.beta.getProbability();
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
        /** Field holding the MAP or Sample value of flips after an infer model call. */
        public final boolean[][][] flips;

        InferredModelOutputs(Flip2CoinsMK11 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.flips = system$model.getInferredValue(system$model.$flips);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$flipsMeasured.setShape(inputs.flipsMeasuredShape);
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
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
