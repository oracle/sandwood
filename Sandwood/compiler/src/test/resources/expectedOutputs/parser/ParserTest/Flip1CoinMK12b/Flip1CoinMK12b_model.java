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
 * Class representing the Sandwood model Flip1CoinMK12b This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip1CoinMK12b extends Model<Flip1CoinMK12b.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double bias;
		boolean constrainedFlag$sample16 = true;
		boolean constrainedFlag$sample28 = true;
		boolean constrainedFlag$sample35 = true;
		boolean fixedFlag$sample16 = false;
		boolean fixedFlag$sample28 = false;
		boolean fixedFlag$sample35 = false;
		boolean fixedProbFlag$sample16 = false;
		boolean fixedProbFlag$sample28 = false;
		boolean fixedProbFlag$sample35 = false;
		boolean fixedProbFlag$sample52 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		boolean guard1;
		int guard2;
		int length$flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bernoulli;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$sample16;
		double logProbability$sample28;
		double logProbability$sample35;
		double logProbability$var14;
		double logProbability$var26;
		double logProbability$var33;
		double logProbability$var48;
		int samples;
		boolean system$gibbsForward = true;
		double var14;
		double var26;
		double var33;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			flips = new boolean[length$flipsMeasured];
		}

		// Getter for bias.
		final double get$bias() {
			return bias;
		}

		// Getter for fixedFlag$sample16.
		final boolean get$fixedFlag$sample16() {
			return fixedFlag$sample16;
		}

		// Setter for fixedFlag$sample16.
		final void set$fixedFlag$sample16(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
			// need to be updated.
			fixedFlag$sample16 = cv$value;
			constrainedFlag$sample16 = (fixedFlag$sample16 || constrainedFlag$sample16);
			
			// Should the probability of sample 16 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
			
			// Should the probability of sample 52 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample52 = (fixedFlag$sample16 && fixedProbFlag$sample52);
		}

		// Getter for fixedFlag$sample28.
		final boolean get$fixedFlag$sample28() {
			return fixedFlag$sample28;
		}

		// Setter for fixedFlag$sample28.
		final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
			// need to be updated.
			fixedFlag$sample28 = cv$value;
			constrainedFlag$sample28 = (fixedFlag$sample28 || constrainedFlag$sample28);
			
			// Should the probability of sample 28 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
			
			// Should the probability of sample 52 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample52 = (fixedFlag$sample28 && fixedProbFlag$sample52);
		}

		// Getter for fixedFlag$sample35.
		final boolean get$fixedFlag$sample35() {
			return fixedFlag$sample35;
		}

		// Setter for fixedFlag$sample35.
		final void set$fixedFlag$sample35(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
			// need to be updated.
			fixedFlag$sample35 = cv$value;
			constrainedFlag$sample35 = (fixedFlag$sample35 || constrainedFlag$sample35);
			
			// Should the probability of sample 35 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
			
			// Should the probability of sample 52 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample52 = (fixedFlag$sample35 && fixedProbFlag$sample52);
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

		// Getter for guard2.
		final int get$guard2() {
			return guard2;
		}

		// Setter for guard2.
		final void set$guard2(int cv$value, boolean allocated$) {
			guard2 = cv$value;
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

		// Getter for var14.
		final double get$var14() {
			return var14;
		}

		// Setter for var14.
		final void set$var14(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of var14 including if probabilities need to
			// be updated.
			var14 = cv$value;
			
			// Unset the fixed probability flag for sample 16 as it depends on var14.
			fixedProbFlag$sample16 = false;
			
			// Unset the fixed probability flag for sample 52 as it depends on var14.
			fixedProbFlag$sample52 = false;
		}

		// Getter for var26.
		final double get$var26() {
			return var26;
		}

		// Setter for var26.
		final void set$var26(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of var26 including if probabilities need to
			// be updated.
			var26 = cv$value;
			
			// Unset the fixed probability flag for sample 28 as it depends on var26.
			fixedProbFlag$sample28 = false;
			
			// Unset the fixed probability flag for sample 52 as it depends on var26.
			fixedProbFlag$sample52 = false;
		}

		// Getter for var33.
		final double get$var33() {
			return var33;
		}

		// Setter for var33.
		final void set$var33(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of var33 including if probabilities need to
			// be updated.
			var33 = cv$value;
			
			// Unset the fixed probability flag for sample 35 as it depends on var33.
			fixedProbFlag$sample35 = false;
			
			// Unset the fixed probability flag for sample 52 as it depends on var33.
			fixedProbFlag$sample52 = false;
		}
	}

    private final ComputedDoubleInternal $bias = new ComputedDoubleInternal(this, "bias", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable bias.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample16(fixed, allocated);
                state.set$fixedFlag$sample28(fixed, allocated);
                state.set$fixedFlag$sample35(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample16 = state.get$fixedFlag$sample16();
            boolean fixedFlag$sample28 = state.get$fixedFlag$sample28();
            boolean fixedFlag$sample35 = state.get$fixedFlag$sample35();
            if(fixedFlag$sample16 && fixedFlag$sample28 && fixedFlag$sample35)
                return Immutability.FIXED;
            else if(fixedFlag$sample16 || fixedFlag$sample28 || fixedFlag$sample35)
                return Immutability.PARTIALLY_FIXED;
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
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing flips of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray flips = $flips;

    private final ComputedDoubleInternal $var14 = new ComputedDoubleInternal(this, "var14", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public double getValue() { return state.get$var14(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$var14(value, allocated);
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

    private final ComputedDoubleInternal $var26 = new ComputedDoubleInternal(this, "var26", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public double getValue() { return state.get$var26(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$var26(value, allocated);
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

    private final ComputedDoubleInternal $var33 = new ComputedDoubleInternal(this, "var33", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public double getValue() { return state.get$var33(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$var33(value, allocated);
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

    private final ObservedIntegerInternal $guard2 = new ObservedIntegerInternal(this, "guard2") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$guard2();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$guard2(value, allocated); }
    };

	/** Observed variable representing guard2 of type int from the Sandwood model. */
    public final ObservedInteger guard2 = $guard2;

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

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $bernoulli};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip1CoinMK12b() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("var14", $var14);
        $computedVariables.put("var26", $var26);
        $computedVariables.put("var33", $var33);

        //ModelInputs
        $modelInputs.put("guard1", $guard1);
        $modelInputs.put("guard2", $guard2);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip1CoinMK12b$SingleThreadCPU core = new Flip1CoinMK12b$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
	 *                           to use in the model when generating results.
	 * @param guard1 The value to set guard1 to.
	 * @param guard2 The value to set guard2 to.
	 */
    public Flip1CoinMK12b(int flipsMeasuredShape, boolean guard1, int guard2) {
        this();
        this.$guard1.setValue(guard1);
        this.$guard2.setValue(guard2);
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 * @param guard1 The value to set guard1 to
	 * @param guard2 The value to set guard2 to
	 */
    public Flip1CoinMK12b(boolean[] flipsMeasured, boolean guard1, int guard2) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
        this.guard1.setValue(guard1);
        this.guard2.setValue(guard2);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK12b$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK12b$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input guard2 */
        public final int guard2;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
		 *                           to use in the model when generating results.
		 * @param guard1 The value to set guard1 to.
		 * @param guard2 The value to set guard2 to.
		 */
        public InferValueInputs(int flipsMeasuredShape, boolean guard1, int guard2) {
            this.guard1 = guard1;
            this.guard2 = guard2;
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
		/** Field holding the value of model input guard2 */
        public final int guard2;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 * @param guard1 The value to set guard1 to.
		 * @param guard2 The value to set guard2 to.
		 */
        public AllInputs(boolean[] flipsMeasured, boolean guard1, int guard2) {
            this.flipsMeasured = flipsMeasured;
            this.guard1 = guard1;
            this.guard2 = guard2;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;

        InferredValueOutputs(Flip1CoinMK12b system$model) {
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
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip1CoinMK12b system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
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
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip1CoinMK12b system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
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
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[] bias;

        InferredModelOutputs(Flip1CoinMK12b system$model) {
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
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
        this.guard2.setValue(inputs.guard2);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}