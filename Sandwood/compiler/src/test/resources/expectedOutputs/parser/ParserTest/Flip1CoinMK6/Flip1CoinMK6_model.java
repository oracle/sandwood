package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flip1CoinMK6 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flip1CoinMK6 extends Model {

    private Flip1CoinMK6$CoreInterface system$c = new Flip1CoinMK6$SingleThreadCPU(ExecutionTarget.singleThread);

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
                system$c.set$fixedFlag$sample17(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample17())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double from the Sandwood model 
     */
    public final ComputedDouble bias = $bias;

    private final ComputedBooleanArrayInternal $flips1 = new ComputedBooleanArrayInternal(this, "flips1", true) {
        @Override
        protected boolean[] getValue() { return system$c.get$flips1(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$flips1(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flips1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample23(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample23())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips1 of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray flips1 = $flips1;

    private final ComputedBooleanArrayInternal $flips2 = new ComputedBooleanArrayInternal(this, "flips2", true) {
        @Override
        protected boolean[] getValue() { return system$c.get$flips2(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$flips2(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flips2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample29(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample29())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips2 of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray flips2 = $flips2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured1 = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured1") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured1();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            system$c.set$flipsMeasured1(value);
            system$c.set$length$flipsMeasured1(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$flipsMeasured1(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$flipsMeasured1();
        }
    };

    /**
     * Observed variable representing flipsMeasured1 of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArrayShapeable flipsMeasured1 = $flipsMeasured1;

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured2 = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured2") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured2();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            system$c.set$flipsMeasured2(value);
            system$c.set$length$flipsMeasured2(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$flipsMeasured2(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$flipsMeasured2();
        }
    };

    /**
     * Observed variable representing flipsMeasured2 of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArrayShapeable flipsMeasured2 = $flipsMeasured2;

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

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips1, $flips2, $bernoulli};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flip1CoinMK6() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips1", $flips1);
        $computedVariables.put("flips2", $flips2);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured1", $flipsMeasured1);
        $shapedObservedValues.put("flipsMeasured2", $flipsMeasured2);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param flipsMeasured1Shape An integer array describing the shape of variable flipsMeasured1 to use in the model when generating results.
      * @param flipsMeasured2Shape An integer array describing the shape of variable flipsMeasured2 to use in the model when generating results.
      */

    public Flip1CoinMK6(int flipsMeasured1Shape, int flipsMeasured2Shape) {
        this();
        this.$flipsMeasured1.setShape(flipsMeasured1Shape);
        this.$flipsMeasured2.setShape(flipsMeasured2Shape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param flipsMeasured1 The value to set flipsMeasured1 to.
      * @param flipsMeasured2 The value to set flipsMeasured2 to.
      */

    public Flip1CoinMK6(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {
        this();
        this.flipsMeasured1.setValue(flipsMeasured1);
        this.flipsMeasured2.setValue(flipsMeasured2);
    }
    
    @Override
    protected Flip1CoinMK6$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flip1CoinMK6$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flip1CoinMK6$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flip1CoinMK6$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Flip1CoinMK6$CoreInterface oldCore, Flip1CoinMK6$CoreInterface newCore) {

        //Observed arrays
        if(flipsMeasured1.isSet()) {
            newCore.set$flipsMeasured1(oldCore.get$flipsMeasured1());
            newCore.set$length$flipsMeasured1(oldCore.get$length$flipsMeasured1());
        }
        else if(flipsMeasured1.shapeSet())
            newCore.set$length$flipsMeasured1(oldCore.get$length$flipsMeasured1());
        if(flipsMeasured2.isSet()) {
            newCore.set$flipsMeasured2(oldCore.get$flipsMeasured2());
            newCore.set$length$flipsMeasured2(oldCore.get$length$flipsMeasured2());
        }
        else if(flipsMeasured2.shapeSet())
            newCore.set$length$flipsMeasured2(oldCore.get$length$flipsMeasured2());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(flips1.isSet())
            newCore.set$flips1(oldCore.get$flips1());
        if(flips2.isSet())
            newCore.set$flips2(oldCore.get$flips2());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample17(oldCore.get$fixedFlag$sample17());
        if(flips1.isSet())
            newCore.set$fixedFlag$sample23(oldCore.get$fixedFlag$sample23());
        if(flips2.isSet())
            newCore.set$fixedFlag$sample29(oldCore.get$fixedFlag$sample29());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input flipsMeasured1 */
        public final int flipsMeasured1Shape;
        /** Field holding the shape of model input flipsMeasured2 */
        public final int flipsMeasured2Shape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param flipsMeasured1Shape An integer array describing the shape of variable flipsMeasured1 to use in the model when generating results.
          * @param flipsMeasured2Shape An integer array describing the shape of variable flipsMeasured2 to use in the model when generating results.
          */
        public InferValueInputs(int flipsMeasured1Shape, int flipsMeasured2Shape) {
            this.flipsMeasured1Shape = flipsMeasured1Shape;
            this.flipsMeasured2Shape = flipsMeasured2Shape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input flipsMeasured1 */
        public final boolean[] flipsMeasured1;
        /** Field holding the value of model input flipsMeasured2 */
        public final boolean[] flipsMeasured2;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param flipsMeasured1 The value to set flipsMeasured1 to.
          * @param flipsMeasured2 The value to set flipsMeasured2 to.
          */
        public AllInputs(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {
            this.flipsMeasured1 = flipsMeasured1;
            this.flipsMeasured2 = flipsMeasured2;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double bias;
        /** Field holding the value of flips1 after a convention execution step.*/
        public final boolean[] flips1;
        /** Field holding the value of flips2 after a convention execution step.*/
        public final boolean[] flips2;

        InferredValueOutputs(Flip1CoinMK6 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips1 = system$model.flips1.getSamples()[0];
            this.flips2 = system$model.flips2.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli */
        public final double bernoulli;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable flips1 */
        public final double flips1;
        /** Field holding the log probability of computed variable flips2 */
        public final double flips2;

        LogProbabilities(Flip1CoinMK6 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips1 = system$model.flips1.getLogProbability();
            this.flips2 = system$model.flips2.getLogProbability();
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
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable flips1 */
        public final double flips1;
        /** Field holding the probability of computed variable flips2 */
        public final double flips2;

        Probabilities(Flip1CoinMK6 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips1 = system$model.flips1.getProbability();
            this.flips2 = system$model.flips2.getProbability();
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
        /** Field holding the MAP or Sample value of flips1 after an infer model call. */
        public final boolean[][] flips1;
        /** Field holding the MAP or Sample value of flips2 after an infer model call. */
        public final boolean[][] flips2;

        InferredModelOutputs(Flip1CoinMK6 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.flips1 = system$model.getInferredValue(system$model.$flips1);
            this.flips2 = system$model.getInferredValue(system$model.$flips2);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$flipsMeasured1.setShape(inputs.flipsMeasured1Shape);
        this.$flipsMeasured2.setShape(inputs.flipsMeasured2Shape);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
