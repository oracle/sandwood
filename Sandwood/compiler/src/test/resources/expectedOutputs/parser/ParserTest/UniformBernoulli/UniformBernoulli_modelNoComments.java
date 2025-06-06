package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model UniformBernoulli This is the class that
  * all user interactions with the model should occur through.
  */
public class UniformBernoulli extends Model {

    private UniformBernoulli$CoreInterface system$c = new UniformBernoulli$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanArrayInternal $output = new ComputedBooleanArrayInternal(this, "output", true) {
        @Override
        public boolean[] getValue() { return system$c.get$output(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$output(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$output(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample16(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample16())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing output of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray output = $output;

    private final ComputedDoubleInternal $prior = new ComputedDoubleInternal(this, "prior", true) {
        @Override
        public double getValue() { return system$c.get$prior(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$prior(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$prior(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample8(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample8())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing prior of type double from the Sandwood model 
     */
    public final ComputedDouble prior = $prior;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $observed = new ObservedBooleanArrayShapeableInternal(this, "observed") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$observed();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
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
     * Observed variable representing observed of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArrayShapeable observed = $observed;

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

    private HasProbabilityInternal[] $probabilityVariables = {$output, $prior, $bernoulli};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public UniformBernoulli() {
        super();
        //ComputedVariable
        $computedVariables.put("output", $output);
        $computedVariables.put("prior", $prior);

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

    public UniformBernoulli(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observed The value to set observed to.
      */

    public UniformBernoulli(boolean[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected UniformBernoulli$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        UniformBernoulli$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new UniformBernoulli$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new UniformBernoulli$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(UniformBernoulli$CoreInterface oldCore, UniformBernoulli$CoreInterface newCore) {

        //Observed arrays
        if(observed.isSet()) {
            newCore.set$observed(oldCore.get$observed());
            newCore.set$length$observed(oldCore.get$length$observed());
        }
        else if(observed.shapeSet())
            newCore.set$length$observed(oldCore.get$length$observed());

        //ComputedVariables
        if(output.isSet())
            newCore.set$output(oldCore.get$output());
        if(prior.isSet())
            newCore.set$prior(oldCore.get$prior());

        //Set fixed flags
        if(output.isSet())
            newCore.set$fixedFlag$sample16(oldCore.get$fixedFlag$sample16());
        if(prior.isSet())
            newCore.set$fixedFlag$sample8(oldCore.get$fixedFlag$sample8());
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
        public final boolean[] observed;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param observed The value to set observed to.
          */
        public AllInputs(boolean[] observed) {
            this.observed = observed;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of output after a convention execution step.*/
        public final boolean[] output;
        /** Field holding the value of prior after a convention execution step.*/
        public final double prior;

        InferredValueOutputs(UniformBernoulli system$model) {
            this.output = system$model.output.getSamples()[0];
            this.prior = system$model.prior.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli */
        public final double bernoulli;
        /** Field holding the log probability of computed variable output */
        public final double output;
        /** Field holding the log probability of computed variable prior */
        public final double prior;

        LogProbabilities(UniformBernoulli system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.output = system$model.output.getLogProbability();
            this.prior = system$model.prior.getLogProbability();
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
        /** Field holding the probability of computed variable output */
        public final double output;
        /** Field holding the probability of computed variable prior */
        public final double prior;

        Probabilities(UniformBernoulli system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.output = system$model.output.getProbability();
            this.prior = system$model.prior.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of prior after an infer model call. */
        public final double[] prior;

        InferredModelOutputs(UniformBernoulli system$model) {
            this.prior = system$model.getInferredValue(system$model.$prior);
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
