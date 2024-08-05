package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
 * A model for the fairness work.
 */
public class Deterministic extends Model {

    private Deterministic$CoreInterface system$c = new Deterministic$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedIntegerArrayInternal $a = new ComputedIntegerArrayInternal(this, "a", true) {
        @Override
        protected int[] getValue() { return system$c.get$a(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$a(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$a(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing a of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray a = $a;

    private final ComputedIntegerArrayInternal $b = new ComputedIntegerArrayInternal(this, "b", false) {
        @Override
        protected int[] getValue() { return system$c.get$b(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable b because its value depends on variable \"a\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$b(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing b of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray b = $b;

    private final ComputedBooleanArrayInternal $flips = new ComputedBooleanArrayInternal(this, "flips", true) {
        @Override
        protected boolean[] getValue() { return system$c.get$flips(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$flips(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$flips(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample48(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample48())
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
        protected double[][] getValue() { return system$c.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$m(value);
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
                system$c.set$fixedFlag$sample18(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample18())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing m of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> m = $m;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $n = new ObservedIntegerInternal(this, "n") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$n();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$n(value); }
    };

    /**
     * Observed variable representing n of type int from the Sandwood model 
     */
    public final ObservedInteger n = $n;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayInternal $flipsMeasured = new ObservedBooleanArrayInternal(this, "flipsMeasured") {
        @Override
        public boolean[] get() {
            synchronized(model) {
                return system$c.get$flipsMeasured();
            }
        }

        @Override
        protected void setValue(boolean[] value) { system$c.set$flipsMeasured(value); }
    };

    /**
     * Observed variable representing flipsMeasured of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArray flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$a, $b, $flips, $m};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Deterministic() {
        super();
        //ComputedVariable
        $computedVariables.put("a", $a);
        $computedVariables.put("b", $b);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("m", $m);

        //ModelInputs
        $modelInputs.put("n", $n);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param n The value to set n to.
      */

    public Deterministic(int n) {
        this();
        this.$n.set(n);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param n The value to set n to.
      * @param flipsMeasured The value to set flipsMeasured to.
      */

    public Deterministic(int n, boolean[] flipsMeasured) {
        this();
        this.n.set(n);
        this.flipsMeasured.set(flipsMeasured);
    }
    
    @Override
    protected Deterministic$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Deterministic$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Deterministic$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Deterministic$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Deterministic$CoreInterface oldCore, Deterministic$CoreInterface newCore) {
        //Model inputs
        if(n.isSet())
            newCore.set$n(oldCore.get$n());
        //Observed scalars
        if(flipsMeasured.isSet())
            newCore.set$flipsMeasured(oldCore.get$flipsMeasured());

        //ComputedVariables
        if(a.isSet())
            newCore.set$a(oldCore.get$a());
        if(b.isSet())
            newCore.set$b(oldCore.get$b());
        if(flips.isSet())
            newCore.set$flips(oldCore.get$flips());
        if(m.isSet())
            newCore.set$m(oldCore.get$m());

        //Set fixed flags
        if(a.isSet())
            newCore.set$fixedFlag$sample35(oldCore.get$fixedFlag$sample35());
        if(flips.isSet())
            newCore.set$fixedFlag$sample48(oldCore.get$fixedFlag$sample48());
        if(m.isSet())
            newCore.set$fixedFlag$sample18(oldCore.get$fixedFlag$sample18());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input n */
        public final int n;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param n The value to set n to.
          */
        public InferValueInputs(int n) {
            this.n = n;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input n */
        public final int n;
        /** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param n The value to set n to.
          * @param flipsMeasured The value to set flipsMeasured to.
          */
        public AllInputs(int n, boolean[] flipsMeasured) {
            this.n = n;
            this.flipsMeasured = flipsMeasured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of a after a convention execution step.*/
        public final int[] a;
        /** Field holding the value of b after a convention execution step.*/
        public final int[] b;
        /** Field holding the value of flips after a convention execution step.*/
        public final boolean[] flips;
        /** Field holding the value of m after a convention execution step.*/
        public final double[][] m;

        InferredValueOutputs(Deterministic system$model) {
            this.a = system$model.a.getSamples()[0];
            this.b = system$model.b.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable a */
        public final double a;
        /** Field holding the log probability of computed variable b */
        public final double b;
        /** Field holding the log probability of computed variable flips */
        public final double flips;
        /** Field holding the log probability of computed variable m */
        public final double m;

        LogProbabilities(Deterministic system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.a = system$model.a.getLogProbability();
            this.b = system$model.b.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.m = system$model.m.getLogProbability();
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
        /** Field holding the probability of computed variable a */
        public final double a;
        /** Field holding the probability of computed variable b */
        public final double b;
        /** Field holding the probability of computed variable flips */
        public final double flips;
        /** Field holding the probability of computed variable m */
        public final double m;

        Probabilities(Deterministic system$model) {
            this.$modelProbability = system$model.getProbability();
            this.a = system$model.a.getProbability();
            this.b = system$model.b.getProbability();
            this.flips = system$model.flips.getProbability();
            this.m = system$model.m.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of a after an infer model call. */
        public final int[][] a;
        /** Field holding the MAP or Sample value of b after an infer model call. */
        public final int[][] b;
        /** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;

        InferredModelOutputs(Deterministic system$model) {
            this.a = system$model.getInferredValue(system$model.$a);
            this.b = system$model.getInferredValue(system$model.$b);
            this.m = system$model.getInferredValue(system$model.$m);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.n.set(inputs.n);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
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
        this.n.set(inputs.n);
        this.$flipsMeasured.set(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
