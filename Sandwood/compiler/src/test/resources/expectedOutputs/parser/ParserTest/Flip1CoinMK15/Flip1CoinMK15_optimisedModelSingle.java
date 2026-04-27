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
 * Class representing the Sandwood model Flip1CoinMK15 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip1CoinMK15 extends Model<Flip1CoinMK15.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double b;
		double bias;
		double[] c;
		boolean constrainedFlag$sample8 = true;
		boolean fixedFlag$sample8 = false;
		boolean fixedProbFlag$sample50 = false;
		boolean fixedProbFlag$sample8 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		boolean guard1;
		int length$flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$b;
		double logProbability$bernoulli;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$var47;
		int samples;
		boolean system$gibbsForward = true;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard1)
				c = new double[2];
			
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		}

		// Getter for b.
		final double get$b() {
			return b;
		}

		// Setter for b.
		final void set$b(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of b including if probabilities need to be updated.
			b = cv$value;
			
			// Unset the fixed probability flag for sample 8 as it depends on b.
			fixedProbFlag$sample8 = false;
			
			// Unset the fixed probability flag for sample 50 as it depends on b.
			fixedProbFlag$sample50 = false;
		}

		// Getter for bias.
		final double get$bias() {
			return bias;
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
			
			// Substituted "fixedFlag$sample8" with its value "cv$value".
			constrainedFlag$sample8 = (cv$value || constrainedFlag$sample8);
			
			// Should the probability of sample 8 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample8" with its value "cv$value".
			fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
			
			// Should the probability of sample 50 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample8" with its value "cv$value".
			fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		}

		// Getter for flips.
		final boolean[] get$flips() {
			return flips;
		}

		// Getter for flipsMeasured.
		final boolean[] get$flipsMeasured() {
			return flipsMeasured;
		}

		// Setter for flipsMeasured.
		final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
			flipsMeasured = cv$value;
		}

		// Getter for guard1.
		final boolean get$guard1() {
			return guard1;
		}

		// Setter for guard1.
		final void set$guard1(boolean cv$value, boolean allocated$) {
			guard1 = cv$value;
		}

		// Getter for length$flipsMeasured.
		final int get$length$flipsMeasured() {
			return length$flipsMeasured;
		}

		// Setter for length$flipsMeasured.
		final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
			length$flipsMeasured = cv$value;
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

		// Getter for logProbability$b.
		final double get$logProbability$b() {
			return logProbability$b;
		}

		// Getter for logProbability$bernoulli.
		final double get$logProbability$bernoulli() {
			return logProbability$bernoulli;
		}

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$flips.
		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		// Getter for samples.
		final int get$samples() {
			return samples;
		}
	}

    private final ComputedDoubleInternal $b = new ComputedDoubleInternal(this, "b", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$b(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$b(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$b(); }

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

	/** Computed variable representing b of type double from the Sandwood model. */
    public final ComputedDouble b = $b;

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable bias because its value depends on variables \"b\", and \"c\".");
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

    private final ComputedBooleanArrayInternal $flips = new ComputedBooleanArrayInternal(this, "flips", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$flips(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flips(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing flips of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray flips = $flips;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedBooleanInternal $guard1 = new ObservedBooleanInternal(this, "guard1") {
        @Override
        public boolean getValue() {
            synchronized(model) {
                return state.get$guard1();
            }
        }

        @Override
        protected void setValueInternal(boolean value) { state.set$guard1(value, allocated); }
    };

	/** Observed variable representing guard1 of type boolean from the Sandwood model. */
    public final ObservedBoolean guard1 = $guard1;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$flipsMeasured(value, allocated);
            state.set$length$flipsMeasured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$flipsMeasured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$flipsMeasured();
        }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable flipsMeasured = $flipsMeasured;

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

    private HasProbabilityInternal[] $probabilityVariables = {$b, $bias, $flips, $bernoulli};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip1CoinMK15() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("b", $b);
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);

        //ModelInputs
        $modelInputs.put("guard1", $guard1);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip1CoinMK15$SingleThreadCPU core = new Flip1CoinMK15$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
	 *                           to use in the model when generating results.
	 * @param guard1 The value to set guard1 to.
	 */
    public Flip1CoinMK15(int flipsMeasuredShape, boolean guard1) {
        this();
        this.$guard1.setValue(guard1);
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 * @param guard1 The value to set guard1 to
	 */
    public Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
        this.guard1.setValue(guard1);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK15$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK15$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input flipsMeasured */
        public final int flipsMeasuredShape;
		/** Field holding the value of model input guard1 */
        public final boolean guard1;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
		 *                           to use in the model when generating results.
		 * @param guard1 The value to set guard1 to.
		 */
        public InferValueInputs(int flipsMeasuredShape, boolean guard1) {
            this.guard1 = guard1;
            this.flipsMeasuredShape = flipsMeasuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;
		/** Field holding the value of model input guard1 */
        public final boolean guard1;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 * @param guard1 The value to set guard1 to.
		 */
        public AllInputs(boolean[] flipsMeasured, boolean guard1) {
            this.flipsMeasured = flipsMeasured;
            this.guard1 = guard1;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of b after a convention execution step. */
        public final double b;
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;

        InferredValueOutputs(Flip1CoinMK15 system$model) {
            this.b = system$model.b.getSamples()[0];
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
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
		/** Field holding the log probability of computed variable b */
        public final double b;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip1CoinMK15 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.b = system$model.b.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
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
		/** Field holding the probability of computed variable b */
        public final double b;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip1CoinMK15 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.b = system$model.b.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of b after an infer model call. */
        public final double[] b;
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[] bias;

        InferredModelOutputs(Flip1CoinMK15 system$model) {
            this.b = system$model.getInferredValue(system$model.$b);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setShape(inputs.flipsMeasuredShape);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.guard1.setValue(inputs.guard1);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}