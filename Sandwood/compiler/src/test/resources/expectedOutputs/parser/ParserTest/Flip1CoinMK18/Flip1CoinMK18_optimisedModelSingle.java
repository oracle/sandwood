package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Flip1CoinMK18 This is the class that
  * all user interactions with the model should occur through.
  */
public class Flip1CoinMK18 extends Model {

    private Flip1CoinMK18$CoreInterface system$c = new Flip1CoinMK18$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<double[][]> $bias = new ComputedObjectArrayInternal<double[][]>(this, "bias", false, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable bias because its value depends on variables \"q\", and \"t\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample14(fixed);
                system$c.set$fixedFlag$sample20(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample14 = system$c.get$fixedFlag$sample14();
            boolean fixedFlag$sample20 = system$c.get$fixedFlag$sample20();
            if(fixedFlag$sample14 && fixedFlag$sample20)
                return Immutability.FIXED;
            else if(fixedFlag$sample14 || fixedFlag$sample20)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double[][][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[][]> bias = $bias;

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
                system$c.set$fixedFlag$sample85(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample85())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing flips of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray flips = $flips;

    private final ComputedDoubleInternal $q = new ComputedDoubleInternal(this, "q", true) {
        @Override
        public double getValue() { return system$c.get$q(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$q(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$q(); }

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
     * Computed variable representing q of type double from the Sandwood model 
     */
    public final ComputedDouble q = $q;

    private final ComputedDoubleInternal $t = new ComputedDoubleInternal(this, "t", true) {
        @Override
        public double getValue() { return system$c.get$t(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$t(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$t(); }

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
     * Computed variable representing t of type double from the Sandwood model 
     */
    public final ComputedDouble t = $t;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $a = new ObservedIntegerInternal(this, "a") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$a();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$a(value); }
    };

    /**
     * Observed variable representing a of type int from the Sandwood model 
     */
    public final ObservedInteger a = $a;

    private final ObservedIntegerInternal $b = new ObservedIntegerInternal(this, "b") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$b();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$b(value); }
    };

    /**
     * Observed variable representing b of type int from the Sandwood model 
     */
    public final ObservedInteger b = $b;

    private final ObservedIntegerInternal $c = new ObservedIntegerInternal(this, "c") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$c();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$c(value); }
    };

    /**
     * Observed variable representing c of type int from the Sandwood model 
     */
    public final ObservedInteger c = $c;

    private final ObservedIntegerInternal $samples = new ObservedIntegerInternal(this, "samples") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$samples();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$samples(value); }
    };

    /**
     * Observed variable representing samples of type int from the Sandwood model 
     */
    public final ObservedInteger samples = $samples;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayInternal $flipsMeasured = new ObservedBooleanArrayInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$flipsMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[] value) { system$c.set$flipsMeasured(value); }
    };

    /**
     * Observed variable representing flipsMeasured of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArray flipsMeasured = $flipsMeasured;

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

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $q, $t, $bernoulli};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Flip1CoinMK18() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("q", $q);
        $computedVariables.put("t", $t);

        //ModelInputs
        $modelInputs.put("a", $a);
        $modelInputs.put("b", $b);
        $modelInputs.put("c", $c);
        $modelInputs.put("samples", $samples);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param samples The value to set samples to.
      * @param a The value to set a to.
      * @param b The value to set b to.
      * @param c The value to set c to.
      */

    public Flip1CoinMK18(int samples, int a, int b, int c) {
        this();
        this.$a.setValue(a);
        this.$b.setValue(b);
        this.$c.setValue(c);
        this.$samples.setValue(samples);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param samples The value to set samples to.
      * @param a The value to set a to.
      * @param b The value to set b to.
      * @param c The value to set c to.
      * @param flipsMeasured The value to set flipsMeasured to.
      */

    public Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {
        this();
        this.samples.setValue(samples);
        this.a.setValue(a);
        this.b.setValue(b);
        this.c.setValue(c);
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected Flip1CoinMK18$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Flip1CoinMK18$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Flip1CoinMK18$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Flip1CoinMK18$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Flip1CoinMK18$CoreInterface oldCore, Flip1CoinMK18$CoreInterface newCore) {
        //Model inputs
        if(a.isSet())
            newCore.set$a(oldCore.get$a());
        if(b.isSet())
            newCore.set$b(oldCore.get$b());
        if(c.isSet())
            newCore.set$c(oldCore.get$c());
        if(samples.isSet())
            newCore.set$samples(oldCore.get$samples());
        //Observed scalars
        if(flipsMeasured.isSet())
            newCore.set$flipsMeasured(oldCore.get$flipsMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(flips.isSet())
            newCore.set$flips(oldCore.get$flips());
        if(q.isSet())
            newCore.set$q(oldCore.get$q());
        if(t.isSet())
            newCore.set$t(oldCore.get$t());

        //Set fixed flags
        if(flips.isSet())
            newCore.set$fixedFlag$sample85(oldCore.get$fixedFlag$sample85());
        if(q.isSet())
            newCore.set$fixedFlag$sample14(oldCore.get$fixedFlag$sample14());
        if(t.isSet())
            newCore.set$fixedFlag$sample20(oldCore.get$fixedFlag$sample20());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input samples */
        public final int samples;
        /** Field holding the value of model input a */
        public final int a;
        /** Field holding the value of model input b */
        public final int b;
        /** Field holding the value of model input c */
        public final int c;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param samples The value to set samples to.
          * @param a The value to set a to.
          * @param b The value to set b to.
          * @param c The value to set c to.
          */
        public InferValueInputs(int samples, int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.samples = samples;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input samples */
        public final int samples;
        /** Field holding the value of model input a */
        public final int a;
        /** Field holding the value of model input b */
        public final int b;
        /** Field holding the value of model input c */
        public final int c;
        /** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param samples The value to set samples to.
          * @param a The value to set a to.
          * @param b The value to set b to.
          * @param c The value to set c to.
          * @param flipsMeasured The value to set flipsMeasured to.
          */
        public AllInputs(int samples, int a, int b, int c, boolean[] flipsMeasured) {
            this.samples = samples;
            this.a = a;
            this.b = b;
            this.c = c;
            this.flipsMeasured = flipsMeasured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double[][][] bias;
        /** Field holding the value of flips after a convention execution step.*/
        public final boolean[] flips;
        /** Field holding the value of q after a convention execution step.*/
        public final double q;
        /** Field holding the value of t after a convention execution step.*/
        public final double t;

        InferredValueOutputs(Flip1CoinMK18 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.q = system$model.q.getSamples()[0];
            this.t = system$model.t.getSamples()[0];
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
        /** Field holding the log probability of computed variable flips */
        public final double flips;
        /** Field holding the log probability of computed variable q */
        public final double q;
        /** Field holding the log probability of computed variable t */
        public final double t;

        LogProbabilities(Flip1CoinMK18 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.q = system$model.q.getLogProbability();
            this.t = system$model.t.getLogProbability();
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
        /** Field holding the probability of computed variable flips */
        public final double flips;
        /** Field holding the probability of computed variable q */
        public final double q;
        /** Field holding the probability of computed variable t */
        public final double t;

        Probabilities(Flip1CoinMK18 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.q = system$model.q.getProbability();
            this.t = system$model.t.getProbability();
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
        public final double[][][][] bias;
        /** Field holding the MAP or Sample value of q after an infer model call. */
        public final double[] q;
        /** Field holding the MAP or Sample value of t after an infer model call. */
        public final double[] t;

        InferredModelOutputs(Flip1CoinMK18 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.q = system$model.getInferredValue(system$model.$q);
            this.t = system$model.getInferredValue(system$model.$t);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
