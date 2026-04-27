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
 * Class representing the Sandwood model Flip1CoinMK20 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip1CoinMK20 extends Model<Flip1CoinMK20.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double bias;
		boolean constrainedFlag$sample8 = true;
		int count1;
		int count2;
		boolean fixedFlag$sample11 = false;
		boolean fixedFlag$sample12 = false;
		boolean fixedFlag$sample8 = false;
		boolean fixedProbFlag$sample11 = false;
		boolean fixedProbFlag$sample12 = false;
		boolean fixedProbFlag$sample8 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$binomial;
		double logProbability$count1;
		double logProbability$count2;
		int obs1;
		int obs2;
		boolean system$gibbsForward = true;
		int total;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {}

		// Getter for bias.
		final double get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of bias including if probabilities need to be
			// updated.
			bias = cv$value;
			
			// Unset the fixed probability flag for sample 8 as it depends on bias.
			fixedProbFlag$sample8 = false;
			
			// Unset the fixed probability flag for sample 11 as it depends on bias.
			fixedProbFlag$sample11 = false;
			
			// Unset the fixed probability flag for sample 12 as it depends on bias.
			fixedProbFlag$sample12 = false;
		}

		// Getter for count1.
		final int get$count1() {
			return count1;
		}

		// Getter for count2.
		final int get$count2() {
			return count2;
		}

		// Getter for fixedFlag$sample11.
		final boolean get$fixedFlag$sample11() {
			return fixedFlag$sample11;
		}

		// Setter for fixedFlag$sample11.
		final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
			fixedFlag$sample11 = cv$value;
		}

		// Getter for fixedFlag$sample12.
		final boolean get$fixedFlag$sample12() {
			return fixedFlag$sample12;
		}

		// Setter for fixedFlag$sample12.
		final void set$fixedFlag$sample12(boolean cv$value, boolean allocated$) {
			fixedFlag$sample12 = cv$value;
		}

		// Getter for fixedFlag$sample8.
		final boolean get$fixedFlag$sample8() {
			return fixedFlag$sample8;
		}

		// Setter for fixedFlag$sample8.
		final void set$fixedFlag$sample8(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample8 including if probabilities
			// need to be updated.
			fixedFlag$sample8 = cv$value;
			constrainedFlag$sample8 = (fixedFlag$sample8 || constrainedFlag$sample8);
			
			// Should the probability of sample 8 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample8 = (fixedFlag$sample8 && fixedProbFlag$sample8);
			
			// Should the probability of sample 11 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample11 = (fixedFlag$sample8 && fixedProbFlag$sample11);
			
			// Should the probability of sample 12 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample12 = (fixedFlag$sample8 && fixedProbFlag$sample12);
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

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$binomial.
		final double get$logProbability$binomial() {
			return logProbability$binomial;
		}

		// Getter for logProbability$count1.
		final double get$logProbability$count1() {
			return logProbability$count1;
		}

		// Getter for logProbability$count2.
		final double get$logProbability$count2() {
			return logProbability$count2;
		}

		// Getter for obs1.
		final int get$obs1() {
			return obs1;
		}

		// Setter for obs1.
		final void set$obs1(int cv$value, boolean allocated$) {
			obs1 = cv$value;
		}

		// Getter for obs2.
		final int get$obs2() {
			return obs2;
		}

		// Setter for obs2.
		final void set$obs2(int cv$value, boolean allocated$) {
			obs2 = cv$value;
		}

		// Getter for total.
		final int get$total() {
			return total;
		}
	}

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample8(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample8())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double from the Sandwood model. */
    public final ComputedDouble bias = $bias;

    private final ComputedIntegerInternal $count1 = new ComputedIntegerInternal(this, "count1", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$count1(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable count1 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$count1(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing count1 of type int from the Sandwood model. */
    public final ComputedInteger count1 = $count1;

    private final ComputedIntegerInternal $count2 = new ComputedIntegerInternal(this, "count2", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$count2(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable count2 because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$count2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing count2 of type int from the Sandwood model. */
    public final ComputedInteger count2 = $count2;

    private final ComputedIntegerInternal $total = new ComputedIntegerInternal(this, "total", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$total(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable total because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing total of type int from the Sandwood model. */
    public final ComputedInteger total = $total;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerInternal $obs1 = new ObservedIntegerInternal(this, "obs1") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$obs1();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$obs1(value, allocated); }
    };

	/** Observed variable representing obs1 of type int from the Sandwood model. */
    public final ObservedInteger obs1 = $obs1;

    private final ObservedIntegerInternal $obs2 = new ObservedIntegerInternal(this, "obs2") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$obs2();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$obs2(value, allocated); }
    };

	/** Observed variable representing obs2 of type int from the Sandwood model. */
    public final ObservedInteger obs2 = $obs2;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $binomial = new RandomVariableInternal(this, "binomial", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$binomial();
        }
    };

	/** Random variable representing binomial from the Sandwood model. */
    public final RandomVariable binomial = $binomial;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $count1, $count2, $binomial};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip1CoinMK20() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("count1", $count1);
        $computedVariables.put("count2", $count2);
        $computedVariables.put("total", $total);

        //Observed scalar fields
        $regularObservedValues.put("obs1", $obs1);
        $regularObservedValues.put("obs2", $obs2);

        Flip1CoinMK20$SingleThreadCPU core = new Flip1CoinMK20$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param obs1 The value to set obs1 to.
	 * @param obs2 The value to set obs2 to
	 */
    public Flip1CoinMK20(int obs1, int obs2) {
        this();
        this.obs1.setValue(obs1);
        this.obs2.setValue(obs2);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK20$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK20$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input obs1 */
        public final int obs1;
		/** Field holding the value of model input obs2 */
        public final int obs2;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param obs1 The value to set obs1 to.
		 * @param obs2 The value to set obs2 to.
		 */
        public AllInputs(int obs1, int obs2) {
            this.obs1 = obs1;
            this.obs2 = obs2;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of count1 after a convention execution step. */
        public final int count1;
		/** Field holding the value of count2 after a convention execution step. */
        public final int count2;
		/** Field holding the value of total after a convention execution step. */
        public final int total;

        InferredValueOutputs(Flip1CoinMK20 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.count1 = system$model.count1.getSamples()[0];
            this.count2 = system$model.count2.getSamples()[0];
            this.total = system$model.total.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable binomial */
        public final double binomial;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable count1 */
        public final double count1;
		/** Field holding the log probability of computed variable count2 */
        public final double count2;
		/** Field holding the log probability of computed variable total */
        public final double total;

        LogProbabilities(Flip1CoinMK20 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.binomial = system$model.binomial.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.count1 = system$model.count1.getLogProbability();
            this.count2 = system$model.count2.getLogProbability();
            this.total = system$model.total.getLogProbability();
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
		/** Field holding the probability of random variable binomial */
        public final double binomial;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable count1 */
        public final double count1;
		/** Field holding the probability of computed variable count2 */
        public final double count2;
		/** Field holding the probability of computed variable total */
        public final double total;

        Probabilities(Flip1CoinMK20 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.binomial = system$model.binomial.getProbability();
            this.bias = system$model.bias.getProbability();
            this.count1 = system$model.count1.getProbability();
            this.count2 = system$model.count2.getProbability();
            this.total = system$model.total.getProbability();
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
        public final double[] bias;

        InferredModelOutputs(Flip1CoinMK20 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
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
        this.$obs1.setValue(inputs.obs1);
        this.$obs2.setValue(inputs.obs2);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}