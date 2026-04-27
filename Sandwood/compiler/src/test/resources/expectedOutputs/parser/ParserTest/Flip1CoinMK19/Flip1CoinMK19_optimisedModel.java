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
 * Class representing the Sandwood model Flip1CoinMK19 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip1CoinMK19 extends Model<Flip1CoinMK19.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		int a;
		int b;
		double[][] bias;
		boolean constrainedFlag$sample10 = true;
		boolean constrainedFlag$sample16 = true;
		boolean fixedFlag$sample10 = false;
		boolean fixedFlag$sample16 = false;
		boolean fixedProbFlag$sample10 = false;
		boolean fixedProbFlag$sample16 = false;
		boolean fixedProbFlag$sample48 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bernoulli;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$q;
		double logProbability$t;
		double logProbability$var47;
		double q;
		int samples;
		boolean system$gibbsForward = true;
		double t;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for bias
			bias = new double[1][];
			bias[0] = new double[2];
			
			// Constructor for flips
			flips = new boolean[samples];
		}

		// Getter for a.
		final int get$a() {
			return a;
		}

		// Setter for a.
		final void set$a(int cv$value, boolean allocated$) {
			a = cv$value;
		}

		// Getter for b.
		final int get$b() {
			return b;
		}

		// Setter for b.
		final void set$b(int cv$value, boolean allocated$) {
			b = cv$value;
		}

		// Getter for bias.
		final double[][] get$bias() {
			return bias;
		}

		// Getter for fixedFlag$sample10.
		final boolean get$fixedFlag$sample10() {
			return fixedFlag$sample10;
		}

		// Setter for fixedFlag$sample10.
		final void set$fixedFlag$sample10(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample10 including if probabilities
			// need to be updated.
			fixedFlag$sample10 = cv$value;
			
			// Substituted "fixedFlag$sample10" with its value "cv$value".
			constrainedFlag$sample10 = (cv$value || constrainedFlag$sample10);
			
			// Should the probability of sample 10 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample10" with its value "cv$value".
			fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
			
			// Should the probability of sample 48 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample10" with its value "cv$value".
			fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
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
			
			// Substituted "fixedFlag$sample16" with its value "cv$value".
			constrainedFlag$sample16 = (cv$value || constrainedFlag$sample16);
			
			// Should the probability of sample 16 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample16" with its value "cv$value".
			fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
			
			// Should the probability of sample 48 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample16" with its value "cv$value".
			fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
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

		// Getter for logProbability$q.
		final double get$logProbability$q() {
			return logProbability$q;
		}

		// Getter for logProbability$t.
		final double get$logProbability$t() {
			return logProbability$t;
		}

		// Getter for q.
		final double get$q() {
			return q;
		}

		// Setter for q.
		final void set$q(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of q including if probabilities need to be updated.
			q = cv$value;
			
			// Unset the fixed probability flag for sample 10 as it depends on q.
			fixedProbFlag$sample10 = false;
			
			// Unset the fixed probability flag for sample 48 as it depends on q.
			fixedProbFlag$sample48 = false;
		}

		// Getter for samples.
		final int get$samples() {
			return samples;
		}

		// Setter for samples.
		final void set$samples(int cv$value, boolean allocated$) {
			samples = cv$value;
		}

		// Getter for t.
		final double get$t() {
			return t;
		}

		// Setter for t.
		final void set$t(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of t including if probabilities need to be updated.
			t = cv$value;
			
			// Unset the fixed probability flag for sample 16 as it depends on t.
			fixedProbFlag$sample16 = false;
			
			// Unset the fixed probability flag for sample 48 as it depends on t.
			fixedProbFlag$sample48 = false;
		}
	}

    private final ComputedObjectArrayInternal<double[]> $bias = new ComputedObjectArrayInternal<double[]>(this, "bias", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable bias because its value depends on variables \"q\", and \"t\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample10(fixed, allocated);
                state.set$fixedFlag$sample16(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample10 = state.get$fixedFlag$sample10();
            boolean fixedFlag$sample16 = state.get$fixedFlag$sample16();
            if(fixedFlag$sample10 && fixedFlag$sample16)
                return Immutability.FIXED;
            else if(fixedFlag$sample10 || fixedFlag$sample16)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> bias = $bias;

    private final ComputedBooleanArrayInternal $flips = new ComputedBooleanArrayInternal(this, "flips", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$flips(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips because it is fixed by observing a variable.");
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

    private final ComputedDoubleInternal $q = new ComputedDoubleInternal(this, "q", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$q(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$q(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$q(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample10(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample10())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing q of type double from the Sandwood model. */
    public final ComputedDouble q = $q;

    private final ComputedDoubleInternal $t = new ComputedDoubleInternal(this, "t", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$t(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$t(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$t(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample16(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample16())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing t of type double from the Sandwood model. */
    public final ComputedDouble t = $t;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $a = new ObservedIntegerInternal(this, "a") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$a();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$a(value, allocated); }
    };

	/** Observed variable representing a of type int from the Sandwood model. */
    public final ObservedInteger a = $a;

    private final ObservedIntegerInternal $b = new ObservedIntegerInternal(this, "b") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$b();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$b(value, allocated); }
    };

	/** Observed variable representing b of type int from the Sandwood model. */
    public final ObservedInteger b = $b;

    private final ObservedIntegerInternal $samples = new ObservedIntegerInternal(this, "samples") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$samples();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$samples(value, allocated); }
    };

	/** Observed variable representing samples of type int from the Sandwood model. */
    public final ObservedInteger samples = $samples;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayInternal $flipsMeasured = new ObservedBooleanArrayInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[] value) { state.set$flipsMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArray flipsMeasured = $flipsMeasured;

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

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $q, $t, $bernoulli};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip1CoinMK19() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("q", $q);
        $computedVariables.put("t", $t);

        //ModelInputs
        $modelInputs.put("a", $a);
        $modelInputs.put("b", $b);
        $modelInputs.put("samples", $samples);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip1CoinMK19$SingleThreadCPU core = new Flip1CoinMK19$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param samples The value to set samples to.
	 * @param a The value to set a to.
	 * @param b The value to set b to.
	 */
    public Flip1CoinMK19(int samples, int a, int b) {
        this();
        this.$a.setValue(a);
        this.$b.setValue(b);
        this.$samples.setValue(samples);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param samples The value to set samples to.
	 * @param a The value to set a to
	 * @param b The value to set b to
	 * @param flipsMeasured The value to set flipsMeasured to
	 */
    public Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {
        this();
        this.samples.setValue(samples);
        this.a.setValue(a);
        this.b.setValue(b);
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK19$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK19$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
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

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param samples The value to set samples to.
		 * @param a The value to set a to.
		 * @param b The value to set b to.
		 */
        public InferValueInputs(int samples, int a, int b) {
            this.a = a;
            this.b = b;
            this.samples = samples;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input samples */
        public final int samples;
		/** Field holding the value of model input a */
        public final int a;
		/** Field holding the value of model input b */
        public final int b;
		/** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param samples The value to set samples to.
		 * @param a The value to set a to.
		 * @param b The value to set b to.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 */
        public AllInputs(int samples, int a, int b, boolean[] flipsMeasured) {
            this.samples = samples;
            this.a = a;
            this.b = b;
            this.flipsMeasured = flipsMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[][] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;
		/** Field holding the value of q after a convention execution step. */
        public final double q;
		/** Field holding the value of t after a convention execution step. */
        public final double t;

        InferredValueOutputs(Flip1CoinMK19 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.q = system$model.q.getSamples()[0];
            this.t = system$model.t.getSamples()[0];
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
		/** Field holding the log probability of computed variable q */
        public final double q;
		/** Field holding the log probability of computed variable t */
        public final double t;

        LogProbabilities(Flip1CoinMK19 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.q = system$model.q.getLogProbability();
            this.t = system$model.t.getLogProbability();
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
		/** Field holding the probability of computed variable q */
        public final double q;
		/** Field holding the probability of computed variable t */
        public final double t;

        Probabilities(Flip1CoinMK19 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.q = system$model.q.getProbability();
            this.t = system$model.t.getProbability();
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
        public final double[][][] bias;
		/** Field holding the MAP or Sample value of q after an infer model call. */
        public final double[] q;
		/** Field holding the MAP or Sample value of t after an infer model call. */
        public final double[] t;

        InferredModelOutputs(Flip1CoinMK19 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.q = system$model.getInferredValue(system$model.$q);
            this.t = system$model.getInferredValue(system$model.$t);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
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
	 * @param initialIterations The number of iterations to use to start with. Having
	 *                          too low a value here can result in premature termination
	 *                          as the model may not have enough runs to estimate the
	 *                          variance accurately.
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
	 * @return An object containing the computed probabilities for the model.
	 */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
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
        this.a.setValue(inputs.a);
        this.b.setValue(inputs.b);
        this.samples.setValue(inputs.samples);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}