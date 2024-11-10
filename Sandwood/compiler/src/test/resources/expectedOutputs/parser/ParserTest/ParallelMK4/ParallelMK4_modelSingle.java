package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model ParallelMK4 This is the class that
  * all user interactions with the model should occur through.
  */
public class ParallelMK4 extends Model {

    private ParallelMK4$CoreInterface system$c = new ParallelMK4$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedIntegerArrayInternal $generated = new ComputedIntegerArrayInternal(this, "generated", true) {
        @Override
        protected int[] getValue() { return system$c.get$generated(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$generated(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$generated(); }

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
     * Computed variable representing generated of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray generated = $generated;

    private final ComputedObjectArrayInternal<double[]> $indirection1 = new ComputedObjectArrayInternal<double[]>(this, "indirection1", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$indirection1(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$indirection1(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$indirection1(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

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
     * Computed variable representing indirection1 of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> indirection1 = $indirection1;

    private final ComputedObjectArrayInternal<double[]> $indirection2 = new ComputedObjectArrayInternal<double[]>(this, "indirection2", false, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$indirection2(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable indirection2 because its value depends on variable \"indirection1\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$indirection2(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

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
     * Computed variable representing indirection2 of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> indirection2 = $indirection2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayShapeableInternal $observed = new ObservedIntegerArrayShapeableInternal(this, "observed") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return system$c.get$observed();
            }
        }

        @Override
        public void setValueInternal(int[] value) {
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
     * Observed variable representing observed of type int[] from the Sandwood model 
     */
    public final ObservedIntegerArrayShapeable observed = $observed;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$generated, $indirection1, $indirection2};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public ParallelMK4() {
        super();
        //ComputedVariable
        $computedVariables.put("generated", $generated);
        $computedVariables.put("indirection1", $indirection1);
        $computedVariables.put("indirection2", $indirection2);

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

    public ParallelMK4(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observed The value to set observed to.
      */

    public ParallelMK4(int[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected ParallelMK4$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        ParallelMK4$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new ParallelMK4$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new ParallelMK4$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(ParallelMK4$CoreInterface oldCore, ParallelMK4$CoreInterface newCore) {

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
        if(indirection1.isSet())
            newCore.set$indirection1(oldCore.get$indirection1());
        if(indirection2.isSet())
            newCore.set$indirection2(oldCore.get$indirection2());

        //Set fixed flags
        if(generated.isSet())
            newCore.set$fixedFlag$sample63(oldCore.get$fixedFlag$sample63());
        if(indirection1.isSet())
            newCore.set$fixedFlag$sample39(oldCore.get$fixedFlag$sample39());
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
        public final int[] observed;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param observed The value to set observed to.
          */
        public AllInputs(int[] observed) {
            this.observed = observed;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of generated after a convention execution step.*/
        public final int[] generated;
        /** Field holding the value of indirection1 after a convention execution step.*/
        public final double[][] indirection1;
        /** Field holding the value of indirection2 after a convention execution step.*/
        public final double[][] indirection2;

        InferredValueOutputs(ParallelMK4 system$model) {
            this.generated = system$model.generated.getSamples()[0];
            this.indirection1 = system$model.indirection1.getSamples()[0];
            this.indirection2 = system$model.indirection2.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable generated */
        public final double generated;
        /** Field holding the log probability of computed variable indirection1 */
        public final double indirection1;
        /** Field holding the log probability of computed variable indirection2 */
        public final double indirection2;

        LogProbabilities(ParallelMK4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.generated = system$model.generated.getLogProbability();
            this.indirection1 = system$model.indirection1.getLogProbability();
            this.indirection2 = system$model.indirection2.getLogProbability();
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
        /** Field holding the probability of computed variable indirection1 */
        public final double indirection1;
        /** Field holding the probability of computed variable indirection2 */
        public final double indirection2;

        Probabilities(ParallelMK4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.generated = system$model.generated.getProbability();
            this.indirection1 = system$model.indirection1.getProbability();
            this.indirection2 = system$model.indirection2.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of indirection1 after an infer model call. */
        public final double[][][] indirection1;
        /** Field holding the MAP or Sample value of indirection2 after an infer model call. */
        public final double[][][] indirection2;

        InferredModelOutputs(ParallelMK4 system$model) {
            this.indirection1 = system$model.getInferredValue(system$model.$indirection1);
            this.indirection2 = system$model.getInferredValue(system$model.$indirection2);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
