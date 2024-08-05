package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model MultinomialBernoulli This is the class that
  * all user interactions with the model should occur through.
  */
public class MultinomialBernoulli extends Model {

    private MultinomialBernoulli$CoreInterface system$c = new MultinomialBernoulli$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedBooleanArrayInternal $output = new ComputedBooleanArrayInternal(this, "output", true) {
        @Override
        protected boolean[] getValue() { return system$c.get$output(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$output(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$output(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample42(fixed);
                system$c.set$fixedFlag$sample47(fixed);
                system$c.set$fixedFlag$sample52(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample42 = system$c.get$fixedFlag$sample42();
            boolean fixedFlag$sample47 = system$c.get$fixedFlag$sample47();
            boolean fixedFlag$sample52 = system$c.get$fixedFlag$sample52();
            if(fixedFlag$sample42 && fixedFlag$sample47 && fixedFlag$sample52)
                return Immutability.FIXED;
            else if(fixedFlag$sample42 || fixedFlag$sample47 || fixedFlag$sample52)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing output of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray output = $output;

    private final ComputedDoubleArrayInternal $p = new ComputedDoubleArrayInternal(this, "p", true) {
        @Override
        protected double[] getValue() { return system$c.get$p(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$p(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$p(); }

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
     * Computed variable representing p of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray p = $p;

    private final ComputedIntegerArrayInternal $prior = new ComputedIntegerArrayInternal(this, "prior", true) {
        @Override
        protected int[] getValue() { return system$c.get$prior(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$prior(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$prior(); }

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
     * Computed variable representing prior of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray prior = $prior;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $observed = new ObservedBooleanArrayShapeableInternal(this, "observed") {
        @Override
        public boolean[] get() {
            synchronized(model) {
                return system$c.get$observed();
            }
        }

        @Override
        public void setValue(boolean[] value) {
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
    private final RandomVariableInternal $b1 = new RandomVariableInternal(this, "b1") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$b1();
        }
    };

    /**
     * Random variable representing b1 from the Sandwood model 
     */
    public final RandomVariable b1 = $b1;

    private final RandomVariableInternal $b2 = new RandomVariableInternal(this, "b2") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$b2();
        }
    };

    /**
     * Random variable representing b2 from the Sandwood model 
     */
    public final RandomVariable b2 = $b2;

    private final RandomVariableInternal $b3 = new RandomVariableInternal(this, "b3") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$b3();
        }
    };

    /**
     * Random variable representing b3 from the Sandwood model 
     */
    public final RandomVariable b3 = $b3;

    private HasProbabilityInternal[] $probabilityVariables = {$output, $p, $prior, $b1, $b2, $b3};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public MultinomialBernoulli() {
        super();
        //ComputedVariable
        $computedVariables.put("output", $output);
        $computedVariables.put("p", $p);
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

    public MultinomialBernoulli(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observed The value to set observed to.
      */

    public MultinomialBernoulli(boolean[] observed) {
        this();
        this.observed.set(observed);
    }
    
    @Override
    protected MultinomialBernoulli$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        MultinomialBernoulli$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new MultinomialBernoulli$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new MultinomialBernoulli$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(MultinomialBernoulli$CoreInterface oldCore, MultinomialBernoulli$CoreInterface newCore) {

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
        if(p.isSet())
            newCore.set$p(oldCore.get$p());
        if(prior.isSet())
            newCore.set$prior(oldCore.get$prior());

        //Set fixed flags
        if(output.isSet()){
            newCore.set$fixedFlag$sample42(oldCore.get$fixedFlag$sample42());
            newCore.set$fixedFlag$sample47(oldCore.get$fixedFlag$sample47());
            newCore.set$fixedFlag$sample52(oldCore.get$fixedFlag$sample52());
        }
        if(p.isSet())
            newCore.set$fixedFlag$sample17(oldCore.get$fixedFlag$sample17());
        if(prior.isSet())
            newCore.set$fixedFlag$sample20(oldCore.get$fixedFlag$sample20());
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
        /** Field holding the value of p after a convention execution step.*/
        public final double[] p;
        /** Field holding the value of prior after a convention execution step.*/
        public final int[] prior;

        InferredValueOutputs(MultinomialBernoulli system$model) {
            this.output = system$model.output.getSamples()[0];
            this.p = system$model.p.getSamples()[0];
            this.prior = system$model.prior.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable b1 */
        public final double b1;
        /** Field holding the log probability of random variable b2 */
        public final double b2;
        /** Field holding the log probability of random variable b3 */
        public final double b3;
        /** Field holding the log probability of computed variable output */
        public final double output;
        /** Field holding the log probability of computed variable p */
        public final double p;
        /** Field holding the log probability of computed variable prior */
        public final double prior;

        LogProbabilities(MultinomialBernoulli system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b1 = system$model.b1.getLogProbability();
            this.b2 = system$model.b2.getLogProbability();
            this.b3 = system$model.b3.getLogProbability();
            this.output = system$model.output.getLogProbability();
            this.p = system$model.p.getLogProbability();
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
        /** Field holding the probability of random variable b1 */
        public final double b1;
        /** Field holding the probability of random variable b2 */
        public final double b2;
        /** Field holding the probability of random variable b3 */
        public final double b3;
        /** Field holding the probability of computed variable output */
        public final double output;
        /** Field holding the probability of computed variable p */
        public final double p;
        /** Field holding the probability of computed variable prior */
        public final double prior;

        Probabilities(MultinomialBernoulli system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b1 = system$model.b1.getProbability();
            this.b2 = system$model.b2.getProbability();
            this.b3 = system$model.b3.getProbability();
            this.output = system$model.output.getProbability();
            this.p = system$model.p.getProbability();
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
        /** Field holding the MAP or Sample value of p after an infer model call. */
        public final double[][] p;
        /** Field holding the MAP or Sample value of prior after an infer model call. */
        public final int[][] prior;

        InferredModelOutputs(MultinomialBernoulli system$model) {
            this.p = system$model.getInferredValue(system$model.$p);
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
