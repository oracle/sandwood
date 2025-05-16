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
  * Class representing the Sandwood model Conditional6 This is the class that
  * all user interactions with the model should occur through.
  */
public class Conditional6 extends Model {

    private Conditional6$CoreInterface system$c = new Conditional6$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $a = new ComputedDoubleInternal(this, "a", true, true, false) {
        @Override
        public double getValue() { return system$c.get$a(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$a(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$a(); }

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
     * Computed variable representing a of type double from the Sandwood model 
     */
    public final ComputedDouble a = $a;

    private final ComputedDoubleInternal $b = new ComputedDoubleInternal(this, "b", true, true, false) {
        @Override
        public double getValue() { return system$c.get$b(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$b(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$b(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample10(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample10())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing b of type double from the Sandwood model 
     */
    public final ComputedDouble b = $b;

    private final ComputedBooleanInternal $guard = new ComputedBooleanInternal(this, "guard", true, true, false) {
        @Override
        public boolean getValue() { return system$c.get$guard(); }

        @Override
        protected void setValueInternal(boolean value) {
            system$c.set$guard(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$guard(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample5(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample5())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing guard of type boolean from the Sandwood model 
     */
    public final ComputedBoolean guard = $guard;

    private final ComputedDoubleInternal $value = new ComputedDoubleInternal(this, "value", false, false, false) {
        @Override
        public double getValue() { return system$c.get$value(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable value because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$value(); }

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
     * Computed variable representing value of type double from the Sandwood model 
     */
    public final ComputedDouble value = $value;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedBooleanInternal $observedGuard = new ObservedBooleanInternal(this, "observedGuard") {
        @Override
        public boolean getValue() {
            synchronized(model) {
                return system$c.get$observedGuard();
            }
        }

        @Override
        protected void setValueInternal(boolean value) { system$c.set$observedGuard(value); }
    };

    /**
     * Observed variable representing observedGuard of type boolean from the Sandwood model 
     */
    public final ObservedBoolean observedGuard = $observedGuard;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleInternal $observedValue = new ObservedDoubleInternal(this, "observedValue") {
        @Override
        public double getValue() {
            synchronized(model) {
                return system$c.get$observedValue();
            }
        }

        @Override
        protected void setValueInternal(double value) { system$c.set$observedValue(value); }
    };

    /**
     * Observed variable representing observedValue of type double from the Sandwood model 
     */
    public final ObservedDouble observedValue = $observedValue;

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

    private final RandomVariableInternal $u = new RandomVariableInternal(this, "u") {
        @Override
        public double getCurrentLogProbability() {
            return system$c.get$logProbability$u();
        }
    };

    /**
     * Random variable representing u from the Sandwood model 
     */
    public final RandomVariable u = $u;

    private HasProbabilityInternal[] $probabilityVariables = {$a, $b, $guard, $value, $bernoulli, $u};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Conditional6() {
        super();
        //ComputedVariable
        $computedVariables.put("a", $a);
        $computedVariables.put("b", $b);
        $computedVariables.put("guard", $guard);
        $computedVariables.put("value", $value);

        //ModelInputs
        $modelInputs.put("observedGuard", $observedGuard);

        //Observed scalar fields
        $regularObservedValues.put("observedValue", $observedValue);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param observedGuard The value to set observedGuard to.
      */

    public Conditional6(boolean observedGuard) {
        this();
        this.$observedGuard.setValue(observedGuard);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param observedValue The value to set observedValue to.
      * @param observedGuard The value to set observedGuard to.
      */

    public Conditional6(double observedValue, boolean observedGuard) {
        this();
        this.observedValue.setValue(observedValue);
        this.observedGuard.setValue(observedGuard);
    }
    
    @Override
    protected Conditional6$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Conditional6$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Conditional6$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Conditional6$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(Conditional6$CoreInterface oldCore, Conditional6$CoreInterface newCore) {
        //Model inputs
        if(observedGuard.isSet())
            newCore.set$observedGuard(oldCore.get$observedGuard());
        //Observed scalars
        if(observedValue.isSet())
            newCore.set$observedValue(oldCore.get$observedValue());

        //ComputedVariables
        if($a.isSet())
            newCore.set$a(oldCore.get$a());
        if($b.isSet())
            newCore.set$b(oldCore.get$b());
        if($guard.isSet())
            newCore.set$guard(oldCore.get$guard());

        //Set fixed flags
        newCore.set$fixedFlag$sample10(oldCore.get$fixedFlag$sample10());
        newCore.set$fixedFlag$sample5(oldCore.get$fixedFlag$sample5());
        newCore.set$fixedFlag$sample9(oldCore.get$fixedFlag$sample9());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input observedGuard */
        public final boolean observedGuard;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param observedGuard The value to set observedGuard to.
          */
        public InferValueInputs(boolean observedGuard) {
            this.observedGuard = observedGuard;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input observedValue */
        public final double observedValue;
        /** Field holding the value of model input observedGuard */
        public final boolean observedGuard;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param observedValue The value to set observedValue to.
          * @param observedGuard The value to set observedGuard to.
          */
        public AllInputs(double observedValue, boolean observedGuard) {
            this.observedValue = observedValue;
            this.observedGuard = observedGuard;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of a after a convention execution step.*/
        public final double a;
        /** Field holding the value of b after a convention execution step.*/
        public final double b;
        /** Field holding the value of guard after a convention execution step.*/
        public final boolean guard;
        /** Field holding the value of value after a convention execution step.*/
        public final double value;

        InferredValueOutputs(Conditional6 system$model) {
            this.a = system$model.a.getSamples()[0];
            this.b = system$model.b.getSamples()[0];
            this.guard = system$model.guard.getSamples()[0];
            this.value = system$model.value.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of random variable bernoulli */
        public final double bernoulli;
        /** Field holding the log probability of random variable u */
        public final double u;
        /** Field holding the log probability of computed variable a */
        public final double a;
        /** Field holding the log probability of computed variable b */
        public final double b;
        /** Field holding the log probability of computed variable guard */
        public final double guard;
        /** Field holding the log probability of computed variable value */
        public final double value;

        LogProbabilities(Conditional6 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.u = system$model.u.getLogProbability();
            this.a = system$model.a.getLogProbability();
            this.b = system$model.b.getLogProbability();
            this.guard = system$model.guard.getLogProbability();
            this.value = system$model.value.getLogProbability();
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
        /** Field holding the probability of random variable u */
        public final double u;
        /** Field holding the probability of computed variable a */
        public final double a;
        /** Field holding the probability of computed variable b */
        public final double b;
        /** Field holding the probability of computed variable guard */
        public final double guard;
        /** Field holding the probability of computed variable value */
        public final double value;

        Probabilities(Conditional6 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.u = system$model.u.getProbability();
            this.a = system$model.a.getProbability();
            this.b = system$model.b.getProbability();
            this.guard = system$model.guard.getProbability();
            this.value = system$model.value.getProbability();
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
        public final double[] a;
        /** Field holding the MAP or Sample value of b after an infer model call. */
        public final double[] b;
        /** Field holding the MAP or Sample value of guard after an infer model call. */
        public final boolean[] guard;

        InferredModelOutputs(Conditional6 system$model) {
            this.a = system$model.getInferredValue(system$model.$a);
            this.b = system$model.getInferredValue(system$model.$b);
            this.guard = system$model.getInferredValue(system$model.$guard);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.observedGuard.setValue(inputs.observedGuard);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
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
        this.observedGuard.setValue(inputs.observedGuard);
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
