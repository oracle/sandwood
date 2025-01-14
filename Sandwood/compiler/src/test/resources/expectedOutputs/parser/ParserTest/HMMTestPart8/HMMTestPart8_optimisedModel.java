package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model HMMTestPart8 This is the class that
  * all user interactions with the model should occur through.
  */
public class HMMTestPart8 extends Model {

    private HMMTestPart8$CoreInterface system$c = new HMMTestPart8$SingleThreadCPU(ExecutionTarget.singleThread);

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
     * Computed variable representing bias of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedBooleanArrayInternal $flips = new ComputedBooleanArrayInternal(this, "flips", true) {
        @Override
        public boolean[] getValue() { return system$c.get$flips(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$flips(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flips(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample55(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample55())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray flips = $flips;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$m(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$m(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

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
     * Computed variable representing m of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedIntegerArrayInternal $st = new ComputedIntegerArrayInternal(this, "st", true) {
        @Override
        public int[] getValue() { return system$c.get$st(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$st(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$st(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample36(fixed);
                system$c.set$fixedFlag$sample46(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample36 = system$c.get$fixedFlag$sample36();
            boolean fixedFlag$sample46 = system$c.get$fixedFlag$sample46();
            if(fixedFlag$sample36 && fixedFlag$sample46)
                return Immutability.FIXED;
            else if(fixedFlag$sample36 || fixedFlag$sample46)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing st of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            system$c.set$flipsMeasured(value);
            system$c.set$length$flipsMeasured(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$flipsMeasured(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$flipsMeasured();
        }
    };

    /**
     * Observed variable representing flipsMeasured of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArrayShapeable flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $m, $st};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public HMMTestPart8() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);

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

    public HMMTestPart8(int flipsMeasuredShape) {
        this();
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param flipsMeasured The value to set flipsMeasured to.
      */

    public HMMTestPart8(boolean[] flipsMeasured) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected HMMTestPart8$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        HMMTestPart8$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new HMMTestPart8$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new HMMTestPart8$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(HMMTestPart8$CoreInterface oldCore, HMMTestPart8$CoreInterface newCore) {

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
        if(m.isSet())
            newCore.set$m(oldCore.get$m());
        if(st.isSet())
            newCore.set$st(oldCore.get$st());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample27(oldCore.get$fixedFlag$sample27());
        if(flips.isSet())
            newCore.set$fixedFlag$sample55(oldCore.get$fixedFlag$sample55());
        if(m.isSet())
            newCore.set$fixedFlag$sample17(oldCore.get$fixedFlag$sample17());
        if(st.isSet()){
            newCore.set$fixedFlag$sample36(oldCore.get$fixedFlag$sample36());
            newCore.set$fixedFlag$sample46(oldCore.get$fixedFlag$sample46());
        }
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input flipsMeasured */
        public final int flipsMeasuredShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured to use in the model when generating results.
          */
        public InferValueInputs(int flipsMeasuredShape) {
            this.flipsMeasuredShape = flipsMeasuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param flipsMeasured The value to set flipsMeasured to.
          */
        public AllInputs(boolean[] flipsMeasured) {
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
        public final boolean[] flips;
        /** Field holding the value of m after a convention execution step.*/
        public final double[][] m;
        /** Field holding the value of st after a convention execution step.*/
        public final int[] st;

        InferredValueOutputs(HMMTestPart8 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable flips */
        public final double flips;
        /** Field holding the log probability of computed variable m */
        public final double m;
        /** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMTestPart8 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
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
        /** Field holding the probability of computed variable flips */
        public final double flips;
        /** Field holding the probability of computed variable m */
        public final double m;
        /** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMTestPart8 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
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
        /** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
        /** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][] st;

        InferredModelOutputs(HMMTestPart8 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
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
