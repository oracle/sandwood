package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model AnonymousSample This is the class that
  * all user interactions with the model should occur through.
  */
public class AnonymousSample extends Model {

    private AnonymousSample$CoreInterface system$c = new AnonymousSample$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $amounts1 = new ComputedDoubleArrayInternal(this, "amounts1", false, true, false) {
        @Override
        public double[] getValue() { return system$c.get$amounts1(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable amounts1 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$amounts1(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing amounts1 of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray amounts1 = $amounts1;

    private final ComputedDoubleArrayInternal $amounts2 = new ComputedDoubleArrayInternal(this, "amounts2", false, false, false) {
        @Override
        public double[] getValue() { return system$c.get$amounts2(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable amounts2 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$amounts2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing amounts2 of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray amounts2 = $amounts2;

    private final ComputedDoubleInternal $mean1 = new ComputedDoubleInternal(this, "mean1", true, true, false) {
        @Override
        public double getValue() { return system$c.get$mean1(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$mean1(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$mean1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample15(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample15())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing mean1 of type double from the Sandwood model 
     */
    public final ComputedDouble mean1 = $mean1;

    private final ComputedDoubleInternal $mean2 = new ComputedDoubleInternal(this, "mean2", true, true, false) {
        @Override
        public double getValue() { return system$c.get$mean2(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$mean2(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$mean2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample21(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample21())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing mean2 of type double from the Sandwood model 
     */
    public final ComputedDouble mean2 = $mean2;

    private final ComputedDoubleInternal $priorSigma2 = new ComputedDoubleInternal(this, "priorSigma2", true, true, false) {
        @Override
        public double getValue() { return system$c.get$priorSigma2(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$priorSigma2(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$priorSigma2(); }

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
     * Computed variable representing priorSigma2 of type double from the Sandwood model 
     */
    public final ComputedDouble priorSigma2 = $priorSigma2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayInternal $obsAmounts2 = new ObservedDoubleArrayInternal(this, "obsAmounts2") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$obsAmounts2();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$obsAmounts2(value); }
    };

    /**
     * Observed variable representing obsAmounts2 of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray obsAmounts2 = $obsAmounts2;

    private final ObservedDoubleArrayShapeableInternal $obsAmounts1 = new ObservedDoubleArrayShapeableInternal(this, "obsAmounts1") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$obsAmounts1();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            system$c.set$obsAmounts1(value);
            system$c.set$length$obsAmounts1(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$obsAmounts1(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$obsAmounts1();
        }
    };

    /**
     * Observed variable representing obsAmounts1 of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArrayShapeable obsAmounts1 = $obsAmounts1;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$amounts1, $amounts2, $mean1, $mean2, $priorSigma2};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public AnonymousSample() {
        super();
        //ComputedVariable
        $computedVariables.put("amounts1", $amounts1);
        $computedVariables.put("amounts2", $amounts2);
        $computedVariables.put("mean1", $mean1);
        $computedVariables.put("mean2", $mean2);
        $computedVariables.put("priorSigma2", $priorSigma2);

        //Observed scalar fields
        $regularObservedValues.put("obsAmounts2", $obsAmounts2);

        //Observed array fields
        $shapedObservedValues.put("obsAmounts1", $obsAmounts1);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param obsAmounts1Shape An integer array describing the shape of variable obsAmounts1 to use in the model when generating results.
      */

    public AnonymousSample(int obsAmounts1Shape) {
        this();
        this.$obsAmounts1.setShape(obsAmounts1Shape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param obsAmounts1 The value to set obsAmounts1 to.
      * @param obsAmounts2 The value to set obsAmounts2 to.
      */

    public AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {
        this();
        this.obsAmounts1.setValue(obsAmounts1);
        this.obsAmounts2.setValue(obsAmounts2);
    }
    
    @Override
    protected AnonymousSample$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        AnonymousSample$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new AnonymousSample$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new AnonymousSample$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(AnonymousSample$CoreInterface oldCore, AnonymousSample$CoreInterface newCore) {
        //Observed scalars
        if(obsAmounts2.isSet())
            newCore.set$obsAmounts2(oldCore.get$obsAmounts2());

        //Observed arrays
        if(obsAmounts1.isSet()) {
            newCore.set$obsAmounts1(oldCore.get$obsAmounts1());
            newCore.set$length$obsAmounts1(oldCore.get$length$obsAmounts1());
        }
        else if(obsAmounts1.shapeSet())
            newCore.set$length$obsAmounts1(oldCore.get$length$obsAmounts1());

        //ComputedVariables
        if($mean1.isSet())
            newCore.set$mean1(oldCore.get$mean1());
        if($mean2.isSet())
            newCore.set$mean2(oldCore.get$mean2());
        if($priorSigma2.isSet())
            newCore.set$priorSigma2(oldCore.get$priorSigma2());

        //Set fixed flags
        newCore.set$fixedFlag$sample15(oldCore.get$fixedFlag$sample15());
        newCore.set$fixedFlag$sample21(oldCore.get$fixedFlag$sample21());
        newCore.set$fixedFlag$sample9(oldCore.get$fixedFlag$sample9());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input obsAmounts1 */
        public final int obsAmounts1Shape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param obsAmounts1Shape An integer array describing the shape of variable obsAmounts1 to use in the model when generating results.
          */
        public InferValueInputs(int obsAmounts1Shape) {
            this.obsAmounts1Shape = obsAmounts1Shape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input obsAmounts1 */
        public final double[] obsAmounts1;
        /** Field holding the value of model input obsAmounts2 */
        public final double[] obsAmounts2;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param obsAmounts1 The value to set obsAmounts1 to.
          * @param obsAmounts2 The value to set obsAmounts2 to.
          */
        public AllInputs(double[] obsAmounts1, double[] obsAmounts2) {
            this.obsAmounts1 = obsAmounts1;
            this.obsAmounts2 = obsAmounts2;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of amounts1 after a convention execution step.*/
        public final double[] amounts1;
        /** Field holding the value of amounts2 after a convention execution step.*/
        public final double[] amounts2;
        /** Field holding the value of mean1 after a convention execution step.*/
        public final double mean1;
        /** Field holding the value of mean2 after a convention execution step.*/
        public final double mean2;
        /** Field holding the value of priorSigma2 after a convention execution step.*/
        public final double priorSigma2;

        InferredValueOutputs(AnonymousSample system$model) {
            this.amounts1 = system$model.amounts1.getSamples()[0];
            this.amounts2 = system$model.amounts2.getSamples()[0];
            this.mean1 = system$model.mean1.getSamples()[0];
            this.mean2 = system$model.mean2.getSamples()[0];
            this.priorSigma2 = system$model.priorSigma2.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable amounts1 */
        public final double amounts1;
        /** Field holding the log probability of computed variable amounts2 */
        public final double amounts2;
        /** Field holding the log probability of computed variable mean1 */
        public final double mean1;
        /** Field holding the log probability of computed variable mean2 */
        public final double mean2;
        /** Field holding the log probability of computed variable priorSigma2 */
        public final double priorSigma2;

        LogProbabilities(AnonymousSample system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.amounts1 = system$model.amounts1.getLogProbability();
            this.amounts2 = system$model.amounts2.getLogProbability();
            this.mean1 = system$model.mean1.getLogProbability();
            this.mean2 = system$model.mean2.getLogProbability();
            this.priorSigma2 = system$model.priorSigma2.getLogProbability();
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
        /** Field holding the probability of computed variable amounts1 */
        public final double amounts1;
        /** Field holding the probability of computed variable amounts2 */
        public final double amounts2;
        /** Field holding the probability of computed variable mean1 */
        public final double mean1;
        /** Field holding the probability of computed variable mean2 */
        public final double mean2;
        /** Field holding the probability of computed variable priorSigma2 */
        public final double priorSigma2;

        Probabilities(AnonymousSample system$model) {
            this.$modelProbability = system$model.getProbability();
            this.amounts1 = system$model.amounts1.getProbability();
            this.amounts2 = system$model.amounts2.getProbability();
            this.mean1 = system$model.mean1.getProbability();
            this.mean2 = system$model.mean2.getProbability();
            this.priorSigma2 = system$model.priorSigma2.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of mean1 after an infer model call. */
        public final double[] mean1;
        /** Field holding the MAP or Sample value of mean2 after an infer model call. */
        public final double[] mean2;
        /** Field holding the MAP or Sample value of priorSigma2 after an infer model call. */
        public final double[] priorSigma2;

        InferredModelOutputs(AnonymousSample system$model) {
            this.mean1 = system$model.getInferredValue(system$model.$mean1);
            this.mean2 = system$model.getInferredValue(system$model.$mean2);
            this.priorSigma2 = system$model.getInferredValue(system$model.$priorSigma2);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$obsAmounts1.setShape(inputs.obsAmounts1Shape);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
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
        this.$obsAmounts1.setValue(inputs.obsAmounts1);
        this.$obsAmounts2.setValue(inputs.obsAmounts2);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
