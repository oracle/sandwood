package org.sandwood.compiler.tests.parser;

import java.util.HashMap;
import java.util.Map;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.CoreModelBase;
import org.sandwood.runtime.internal.model.state.CoreModelState;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.*;

/**
 * Class representing the Sandwood model Conditional4 This is the class that all user
 * interactions with the model should occur through.
 */
public final class Conditional4 extends Model<Conditional4.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] bias;
		boolean constrainedFlag$sample21 = true;
		boolean constrainedFlag$sample4 = true;
		boolean fixedFlag$sample21 = false;
		boolean fixedFlag$sample4 = false;
		boolean fixedProbFlag$sample21 = false;
		boolean fixedProbFlag$sample27 = false;
		boolean fixedProbFlag$sample4 = false;
		boolean guard;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bernoulli;
		double logProbability$bias;
		double logProbability$guard;
		double logProbability$value;
		double logProbability$var19;
		double observedValue;
		boolean system$gibbsForward = true;
		double value;
		double var19;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for bias
			bias = new double[1];
		}

		// Getter for bias.
		final double[] get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double[] cv$value, boolean allocated$) {
			bias = cv$value;
		}

		// Getter for fixedFlag$sample21.
		final boolean get$fixedFlag$sample21() {
			return fixedFlag$sample21;
		}

		// Setter for fixedFlag$sample21.
		final void set$fixedFlag$sample21(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
			// need to be updated.
			fixedFlag$sample21 = cv$value;
			
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			constrainedFlag$sample21 = (cv$value || constrainedFlag$sample21);
			
			// Should the probability of sample 21 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
			
			// Should the probability of sample 27 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		}

		// Getter for fixedFlag$sample4.
		final boolean get$fixedFlag$sample4() {
			return fixedFlag$sample4;
		}

		// Setter for fixedFlag$sample4.
		final void set$fixedFlag$sample4(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
			// need to be updated.
			fixedFlag$sample4 = cv$value;
			
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			constrainedFlag$sample4 = (cv$value || constrainedFlag$sample4);
			
			// Should the probability of sample 4 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
			
			// Should the probability of sample 21 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
			
			// Should the probability of sample 27 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		}

		// Getter for guard.
		final boolean get$guard() {
			return guard;
		}

		// Setter for guard.
		final void set$guard(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of guard including if probabilities need to
			// be updated.
			guard = cv$value;
			
			// Unset the fixed probability flag for sample 4 as it depends on guard.
			fixedProbFlag$sample4 = false;
			
			// Unset the fixed probability flag for sample 21 as it depends on guard.
			fixedProbFlag$sample21 = false;
			
			// Unset the fixed probability flag for sample 27 as it depends on guard.
			fixedProbFlag$sample27 = false;
		}

		// Getter for logProbability$$evidence.
		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		// Getter for the probability of logProbability$$model.
		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		// Getter for logProbability$bernoulli.
		final double get$logProbability$bernoulli() {
			return logProbability$bernoulli;
		}

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$guard.
		final double get$logProbability$guard() {
			return logProbability$guard;
		}

		// Getter for logProbability$value.
		final double get$logProbability$value() {
			return logProbability$value;
		}

		// Getter for observedValue.
		final double get$observedValue() {
			return observedValue;
		}

		// Setter for observedValue.
		final void set$observedValue(double cv$value, boolean allocated$) {
			observedValue = cv$value;
		}

		// Getter for value.
		final double get$value() {
			return value;
		}

		// Getter for var19.
		final double get$var19() {
			return var19;
		}

		// Setter for var19.
		final void set$var19(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of var19 including if probabilities need to
			// be updated.
			var19 = cv$value;
			
			// Unset the fixed probability flag for sample 21 as it depends on var19.
			fixedProbFlag$sample21 = false;
			
			// Unset the fixed probability flag for sample 27 as it depends on var19.
			fixedProbFlag$sample27 = false;
		}
	}

    private final ComputedDoubleArrayInternal $bias = new ComputedDoubleArrayInternal(this, "bias", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample21(fixed, allocated);
                state.set$fixedFlag$sample4(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample21 = state.get$fixedFlag$sample21();
            boolean fixedFlag$sample4 = state.get$fixedFlag$sample4();
            if(fixedFlag$sample21 && fixedFlag$sample4)
                return Immutability.FIXED;
            else if(fixedFlag$sample21 || fixedFlag$sample4)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[] from the Sandwood model. */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedBooleanInternal $guard = new ComputedBooleanInternal(this, "guard", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$guard(); }

        @Override
        protected void setValueInternal(boolean value) {
            state.set$guard(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$guard(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample4(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample4())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing guard of type boolean from the Sandwood model. */
    public final ComputedBoolean guard = $guard;

    private final ComputedDoubleInternal $value = new ComputedDoubleInternal(this, "value", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$value(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable value because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$value(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing value of type double from the Sandwood model. */
    public final ComputedDouble value = $value;

    private final ComputedDoubleInternal $var19 = new ComputedDoubleInternal(this, "var19", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public double getValue() { return state.get$var19(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$var19(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleInternal $observedValue = new ObservedDoubleInternal(this, "observedValue") {
        @Override
        public double getValue() {
            synchronized(model) {
                return state.get$observedValue();
            }
        }

        @Override
        protected void setValueInternal(double value) { state.set$observedValue(value, allocated); }
    };

	/**
	 * Observed variable representing observedValue of type double from the Sandwood model.
	 */
    public final ObservedDouble observedValue = $observedValue;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $bernoulli = new RandomVariableInternal(this, "bernoulli", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$bernoulli();
        }
    };

	/** Random variable representing bernoulli from the Sandwood model. */
    public final RandomVariable bernoulli = $bernoulli;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $guard, $value, $bernoulli};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Conditional4() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("guard", $guard);
        $computedVariables.put("value", $value);
        $computedVariables.put("var19", $var19);

        //Observed scalar fields
        $regularObservedValues.put("observedValue", $observedValue);

        Conditional4$SingleThreadCPU core = new Conditional4$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observedValue The value to set observedValue to.
	 */
    public Conditional4(double observedValue) {
        this();
        this.observedValue.setValue(observedValue);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Conditional4$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Conditional4$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 */
        public InferValueInputs() {
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input observedValue */
        public final double observedValue;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param observedValue The value to set observedValue to.
		 */
        public AllInputs(double observedValue) {
            this.observedValue = observedValue;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of guard after a convention execution step. */
        public final boolean guard;
		/** Field holding the value of value after a convention execution step. */
        public final double value;

        InferredValueOutputs(Conditional4 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.guard = system$model.guard.getSamples()[0];
            this.value = system$model.value.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable bernoulli */
        public final double bernoulli;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable guard */
        public final double guard;
		/** Field holding the log probability of computed variable value */
        public final double value;

        LogProbabilities(Conditional4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.guard = system$model.guard.getLogProbability();
            this.value = system$model.value.getLogProbability();
        }

		/**
		 * Method to return log probability of the whole model
		 * @return The log probability of the whole model.
		 */
        public double getModelProbability() { return $logModelProbability; }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class Probabilities {
        private final double $modelProbability;
		/** Field holding the probability of random variable bernoulli */
        public final double bernoulli;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable guard */
        public final double guard;
		/** Field holding the probability of computed variable value */
        public final double value;

        Probabilities(Conditional4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.bias = system$model.bias.getProbability();
            this.guard = system$model.guard.getProbability();
            this.value = system$model.value.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[][] bias;
		/** Field holding the MAP or Sample value of guard after an infer model call. */
        public final boolean[] guard;

        InferredModelOutputs(Conditional4 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.guard = system$model.getInferredValue(system$model.$guard);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        execute();
        return new InferredValueOutputs(this);
    }

	/**
	 * Infer the values of the different elements of the model.
	 * @param iterations The number of iterations to perform when inferring the values.
	 * @param inputs An object containing the parameters required to generate the model
	 *               parameters.
	 * @return An object containing the computed values for the model.
	 */
    public InferredModelOutputs inferValues(int iterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferValues(iterations);
        return new InferredModelOutputs(this);
    }

	/**
	 * Generate the probabilities of the different elements of the model.
	 * @param iterations How many iterations should be used to generate these values?
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(int iterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(iterations);
        return new Probabilities(this);
    }

	/**
	 * Calculate the probability of each variable and the overall model. This method will
	 * iterate until the variance of the overall model drops below the value provide for
	 * variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations);
        return new Probabilities(this);
    }

	/**
	 * Calculate the probability of each variable and the overall model. This method will
	 * iterate until the variance of the overall model drops below the value provide for
	 * variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param maxIterations The maximum number of iterations a that can be used to calculate
	 *                      the probabilities. If the model has not converged by this
	 *                      point the calculation will terminate anyway, and the result
	 *                      generated so far will be returned.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public Probabilities inferProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new Probabilities(this);
    }

	/**
	 * Generate the log probabilities of the different elements of the model.
	 * @param iterations How many iterations should be used to generate these values?
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(int iterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(iterations);
        return new LogProbabilities(this);
    }

	/**
	 * Calculate the log probability of each variable and the overall model. This method
	 * will iterate until the variance of the overall model drops below the value provide
	 * for variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations);
        return new LogProbabilities(this);
    }

	/**
	 * Calculate the log probability of each variable and the overall model. This method
	 * will iterate until the variance of the overall model drops below the value provide
	 * for variance, or the maximum number of iterations is reached.
	 * @param variance The maximum variance in the models overall probability.
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param maxIterations The maximum number of iterations a that can be used to calculate
	 *                      the probabilities. If the model has not converged by this
	 *                      point the calculation will terminate anyway, and the result
	 *                      generated so far will be returned.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.$observedValue.setValue(inputs.observedValue);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}