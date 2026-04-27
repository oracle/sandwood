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
 * Class representing the Sandwood model Flip2CoinsMK10 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip2CoinsMK10 extends Model<Flip2CoinsMK10.State> {
	final class State extends CoreModelState {
double[] bias;
		int coins;
		boolean constrainedFlag$sample10 = true;
		boolean[] constrainedFlag$sample23;
		boolean fixedFlag$sample10 = false;
		boolean fixedFlag$sample23 = false;
		boolean fixedProbFlag$sample10 = false;
		boolean fixedProbFlag$sample23 = false;
		boolean fixedProbFlag$sample48 = false;
		boolean[][] flips;
		boolean[][] flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double[] logProbability$bernoulli;
		double logProbability$beta;
		double logProbability$bias;
		double logProbability$flips;
		double[] logProbability$sample48;
		double logProbability$var10;
		double logProbability$var23;
		int[] shape;
		boolean system$gibbsForward = true;

		@Override
		public final void allocate() {
			flips = new boolean[shape.length][];
			for(int j = 0; j < shape.length; j += 1)
				flips[j] = new boolean[shape[j]];
			if((!fixedFlag$sample10 || !fixedFlag$sample23))
				bias = new double[shape.length];
			constrainedFlag$sample23 = new boolean[(shape.length - 1)];
			logProbability$bernoulli = new double[shape.length];
			logProbability$sample48 = new double[shape.length];
		}

		final double[] get$bias() {
			return bias;
		}

		final void set$bias(double[] cv$value, boolean allocated$) {
			bias = cv$value;
			fixedProbFlag$sample10 = false;
			fixedProbFlag$sample23 = false;
			fixedProbFlag$sample48 = false;
		}

		final int get$coins() {
			return coins;
		}

		final boolean get$fixedFlag$sample10() {
			return fixedFlag$sample10;
		}

		final void set$fixedFlag$sample10(boolean cv$value, boolean allocated$) {
			fixedFlag$sample10 = cv$value;
			constrainedFlag$sample10 = (cv$value || constrainedFlag$sample10);
			fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
			fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		}

		final boolean get$fixedFlag$sample23() {
			return fixedFlag$sample23;
		}

		final void set$fixedFlag$sample23(boolean cv$value, boolean allocated$) {
			fixedFlag$sample23 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
					constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
			}
			fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
			fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		}

		final boolean[][] get$flips() {
			return flips;
		}

		final boolean[][] get$flipsMeasured() {
			return flipsMeasured;
		}

		final void set$flipsMeasured(boolean[][] cv$value, boolean allocated$) {
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

		final double[] get$logProbability$bernoulli() {
			return logProbability$bernoulli;
		}

		final double get$logProbability$beta() {
			return logProbability$beta;
		}

		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		final int[] get$shape() {
			return shape;
		}

		final void set$shape(int[] cv$value, boolean allocated$) {
			shape = cv$value;
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
                state.set$fixedFlag$sample10(fixed, allocated);
                state.set$fixedFlag$sample23(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample10 = state.get$fixedFlag$sample10();
            boolean fixedFlag$sample23 = state.get$fixedFlag$sample23();
            if(fixedFlag$sample10 && fixedFlag$sample23)
                return Immutability.FIXED;
            else if(fixedFlag$sample10 || fixedFlag$sample23)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[] from the Sandwood model. */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedObjectArrayInternal<boolean[]> $flips = new ComputedObjectArrayInternal<boolean[]>(this, "flips", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return state.get$flips(); }

        @Override
        protected void setValueInternal(boolean[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flips(); }

        @Override
        public boolean[][][] constructArray(int iterations) {
            return new boolean[iterations][][];
        }

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
	 * Computed variable representing flips of type boolean[][] from the Sandwood model.
	 */
    public final ComputedObjectArray<boolean[]> flips = $flips;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerArrayInternal $shape = new ObservedIntegerArrayInternal(this, "shape") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return state.get$shape();
            }
        }

        @Override
        protected void setValueInternal(int[] value) { state.set$shape(value, allocated); }
    };

	/** Observed variable representing shape of type int[] from the Sandwood model. */
    public final ObservedIntegerArray shape = $shape;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[]> $flipsMeasured = new ObservedObjectArrayInternal<boolean[]>(this, "flipsMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[][] value) { state.set$flipsMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArray<boolean[]> flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final IteratedRandomVariableInternal<double[]> $bernoulli = new IteratedRandomVariableInternal<double[]>(this, "bernoulli", 1, ProbabilityType.SKIPPABLE) {
        @Override
        public double[] getCurrentLogProbability() {
            return state.get$logProbability$bernoulli();
        }
    };

	/**
	 * Iterated random variable representing the random variable bernoulli from
	 * the Sandwood model. The random variable is embedded in a loop
	 * so results are returned as anarray.
	 */
    public final IteratedRandomVariable<double[]> bernoulli = $bernoulli;

    private final RandomVariableInternal $beta = new RandomVariableInternal(this, "beta", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$beta();
        }
    };

	/** Random variable representing beta from the Sandwood model. */
    public final RandomVariable beta = $beta;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $bernoulli, $beta};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip2CoinsMK10() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);

        //ModelInputs
        $modelInputs.put("shape", $shape);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip2CoinsMK10$SingleThreadCPU core = new Flip2CoinsMK10$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param shape The value to set shape to.
	 */
    public Flip2CoinsMK10(int[] shape) {
        this();
        this.$shape.setValue(shape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 * @param shape The value to set shape to
	 */
    public Flip2CoinsMK10(boolean[][] flipsMeasured, int[] shape) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
        this.shape.setValue(shape);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip2CoinsMK10$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip2CoinsMK10$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input shape */
        public final int[] shape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param shape The value to set shape to.
		 */
        public InferValueInputs(int[] shape) {
            this.shape = shape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input flipsMeasured */
        public final boolean[][] flipsMeasured;
		/** Field holding the value of model input shape */
        public final int[] shape;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 * @param shape The value to set shape to.
		 */
        public AllInputs(boolean[][] flipsMeasured, int[] shape) {
            this.flipsMeasured = flipsMeasured;
            this.shape = shape;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[][] flips;

        InferredValueOutputs(Flip2CoinsMK10 system$model) {
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
        public final double[] bernoulli;
		/** Field holding the log probability of random variable beta */
        public final double beta;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip2CoinsMK10 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli = system$model.bernoulli.getLogProbability();
            this.beta = system$model.beta.getLogProbability();
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
        public final double[] bernoulli;
		/** Field holding the probability of random variable beta */
        public final double beta;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip2CoinsMK10 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli = system$model.bernoulli.getProbability();
            this.beta = system$model.beta.getProbability();
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
        public final double[][] bias;

        InferredModelOutputs(Flip2CoinsMK10 system$model) {
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
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
        this.shape.setValue(inputs.shape);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}