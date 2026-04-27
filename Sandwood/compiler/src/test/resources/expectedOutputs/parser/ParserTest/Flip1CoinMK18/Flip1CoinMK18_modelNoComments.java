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
 * Class representing the Sandwood model Flip1CoinMK18 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip1CoinMK18 extends Model<Flip1CoinMK18.State> {
	final class State extends CoreModelState {
int a;
		int b;
		double[][][] bias;
		int c;
		boolean constrainedFlag$sample11 = true;
		boolean constrainedFlag$sample17 = true;
		boolean fixedFlag$sample11 = false;
		boolean fixedFlag$sample17 = false;
		boolean fixedProbFlag$sample103 = false;
		boolean fixedProbFlag$sample11 = false;
		boolean fixedProbFlag$sample17 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bernoulli;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$q;
		double logProbability$t;
		double logProbability$var97;
		double q;
		int samples;
		boolean system$gibbsForward = true;
		double t;

		@Override
		public final void allocate() {
			{
				bias = new double[2][][];
				double[][] subarray$0 = new double[2][];
				bias[0] = subarray$0;
				subarray$0[0] = new double[2];
				subarray$0[1] = new double[2];
				double[][] subarray$1 = new double[2][];
				bias[1] = subarray$1;
				subarray$1[0] = new double[2];
				subarray$1[1] = new double[2];
			}
			{
				flips = new boolean[samples];
			}
		}

		final int get$a() {
			return a;
		}

		final void set$a(int cv$value, boolean allocated$) {
			a = cv$value;
		}

		final int get$b() {
			return b;
		}

		final void set$b(int cv$value, boolean allocated$) {
			b = cv$value;
		}

		final double[][][] get$bias() {
			return bias;
		}

		final int get$c() {
			return c;
		}

		final void set$c(int cv$value, boolean allocated$) {
			c = cv$value;
		}

		final boolean get$fixedFlag$sample11() {
			return fixedFlag$sample11;
		}

		final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
			fixedFlag$sample11 = cv$value;
			constrainedFlag$sample11 = (fixedFlag$sample11 || constrainedFlag$sample11);
			fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
			fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedProbFlag$sample103);
		}

		final boolean get$fixedFlag$sample17() {
			return fixedFlag$sample17;
		}

		final void set$fixedFlag$sample17(boolean cv$value, boolean allocated$) {
			fixedFlag$sample17 = cv$value;
			constrainedFlag$sample17 = (fixedFlag$sample17 || constrainedFlag$sample17);
			fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
			fixedProbFlag$sample103 = (fixedFlag$sample17 && fixedProbFlag$sample103);
		}

		final boolean[] get$flips() {
			return flips;
		}

		final boolean[] get$flipsMeasured() {
			return flipsMeasured;
		}

		final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
			flipsMeasured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$bernoulli() {
			return logProbability$bernoulli;
		}

		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		final double get$logProbability$q() {
			return logProbability$q;
		}

		final double get$logProbability$t() {
			return logProbability$t;
		}

		final double get$q() {
			return q;
		}

		final void set$q(double cv$value, boolean allocated$) {
			q = cv$value;
			fixedProbFlag$sample11 = false;
			fixedProbFlag$sample103 = false;
		}

		final int get$samples() {
			return samples;
		}

		final void set$samples(int cv$value, boolean allocated$) {
			samples = cv$value;
		}

		final double get$t() {
			return t;
		}

		final void set$t(double cv$value, boolean allocated$) {
			t = cv$value;
			fixedProbFlag$sample17 = false;
			fixedProbFlag$sample103 = false;
		}
	}

    private final ComputedObjectArrayInternal<double[][]> $bias = new ComputedObjectArrayInternal<double[][]>(this, "bias", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable bias because its value depends on variables \"q\", and \"t\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample11(fixed, allocated);
                state.set$fixedFlag$sample17(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample11 = state.get$fixedFlag$sample11();
            boolean fixedFlag$sample17 = state.get$fixedFlag$sample17();
            if(fixedFlag$sample11 && fixedFlag$sample17)
                return Immutability.FIXED;
            else if(fixedFlag$sample11 || fixedFlag$sample17)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing bias of type double[][][] from the Sandwood model.
	 */
    public final ComputedObjectArray<double[][]> bias = $bias;

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
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
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
                state.set$fixedFlag$sample11(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample11())
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
                state.set$fixedFlag$sample17(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample17())
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

    private final ObservedIntegerInternal $c = new ObservedIntegerInternal(this, "c") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$c();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$c(value, allocated); }
    };

	/** Observed variable representing c of type int from the Sandwood model. */
    public final ObservedInteger c = $c;

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
    public Flip1CoinMK18() {
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
        $modelInputs.put("c", $c);
        $modelInputs.put("samples", $samples);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip1CoinMK18$SingleThreadCPU core = new Flip1CoinMK18$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
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
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param samples The value to set samples to.
	 * @param a The value to set a to
	 * @param b The value to set b to
	 * @param c The value to set c to
	 * @param flipsMeasured The value to set flipsMeasured to
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
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK18$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK18$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input c */
        public final int c;
		/** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
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
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[][][] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;
		/** Field holding the value of q after a convention execution step. */
        public final double q;
		/** Field holding the value of t after a convention execution step. */
        public final double t;

        InferredValueOutputs(Flip1CoinMK18 system$model) {
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

        LogProbabilities(Flip1CoinMK18 system$model) {
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

        Probabilities(Flip1CoinMK18 system$model) {
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
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
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
	 * @param inputs An object containing the parameters required to generate the model
	 *               parameters.
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
	 * @param inputs An object containing the parameters required to generate the probabilities
	 *               of the model.
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
        this.c.setValue(inputs.c);
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
        this.c.setValue(inputs.c);
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
        this.c.setValue(inputs.c);
        this.samples.setValue(inputs.samples);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}