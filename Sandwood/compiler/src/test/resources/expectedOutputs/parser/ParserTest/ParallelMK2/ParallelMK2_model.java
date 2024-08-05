package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model ParallelMK2 This is the class that
  * all user interactions with the model should occur through.
  */
public class ParallelMK2 extends Model {

    private ParallelMK2$CoreInterface system$c = new ParallelMK2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $generated = new ComputedDoubleArrayInternal(this, "generated", true) {
        @Override
        protected double[] getValue() { return system$c.get$generated(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$generated(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$generated(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample30(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample30())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing generated of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray generated = $generated;

    private final ComputedDoubleArrayInternal $indirection = new ComputedDoubleArrayInternal(this, "indirection", false) {
        @Override
        protected double[] getValue() { return system$c.get$indirection(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$indirection(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$indirection(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample24(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample24())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing indirection of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray indirection = $indirection;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayShapeableInternal $observed = new ObservedDoubleArrayShapeableInternal(this, "observed") {
        @Override
        public double[] get() {
            synchronized(model) {
                return system$c.get$observed();
            }
        }

        @Override
        public void setValue(double[] value) {
            system$c.set$observed(value);
            system$c.set$length$observed(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$observed(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$observed();
        }
    };

    /**
     * Observed variable representing observed of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArrayShapeable observed = $observed;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$generated, $indirection};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public ParallelMK2() {
        super();
        //ComputedVariable
        $computedVariables.put("generated", $generated);
        $computedVariables.put("indirection", $indirection);

        //Observed array fields
        $shapedObservedValues.put("observed", $observed);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param observedShape An integer array describing the shape of variable observed to use in the model when generating results.
      */

    public ParallelMK2(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observed The value to set observed to.
      */

    public ParallelMK2(double[] observed) {
        this();
        this.observed.set(observed);
    }
    
    @Override
    protected ParallelMK2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        ParallelMK2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new ParallelMK2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new ParallelMK2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(ParallelMK2$CoreInterface oldCore, ParallelMK2$CoreInterface newCore) {

        //Observed arrays
        if(observed.isSet()) {
            newCore.set$observed(oldCore.get$observed());
            newCore.set$length$observed(oldCore.get$length$observed());
        }
        else if(observed.shapeSet())
            newCore.set$length$observed(oldCore.get$length$observed());

        //ComputedVariables
        if(generated.isSet())
            newCore.set$generated(oldCore.get$generated());
        if(indirection.isSet())
            newCore.set$indirection(oldCore.get$indirection());

        //Set fixed flags
        if(generated.isSet())
            newCore.set$fixedFlag$sample30(oldCore.get$fixedFlag$sample30());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input observed */
        public final int observedShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param observedShape An integer array describing the shape of variable observed to use in the model when generating results.
          */
        public InferValueInputs(int observedShape) {
            this.observedShape = observedShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input observed */
        public final double[] observed;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param observed The value to set observed to.
          */
        public AllInputs(double[] observed) {
            this.observed = observed;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of generated after a convention execution step.*/
        public final double[] generated;
        /** Field holding the value of indirection after a convention execution step.*/
        public final double[] indirection;

        InferredValueOutputs(ParallelMK2 system$model) {
            this.generated = system$model.generated.getSamples()[0];
            this.indirection = system$model.indirection.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable generated */
        public final double generated;
        /** Field holding the log probability of computed variable indirection */
        public final double indirection;

        LogProbabilities(ParallelMK2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.generated = system$model.generated.getLogProbability();
            this.indirection = system$model.indirection.getLogProbability();
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
        /** Field holding the probability of computed variable generated */
        public final double generated;
        /** Field holding the probability of computed variable indirection */
        public final double indirection;

        Probabilities(ParallelMK2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.generated = system$model.generated.getProbability();
            this.indirection = system$model.indirection.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of indirection after an infer model call. */
        public final double[][] indirection;

        InferredModelOutputs(ParallelMK2 system$model) {
            this.indirection = system$model.getInferredValue(system$model.$indirection);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$observed.setShape(inputs.observedShape);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
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
        this.$observed.set(inputs.observed);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
