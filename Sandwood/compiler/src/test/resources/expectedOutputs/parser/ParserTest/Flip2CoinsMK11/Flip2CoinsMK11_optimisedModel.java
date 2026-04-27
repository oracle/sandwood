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
 * Class representing the Sandwood model Flip2CoinsMK11 This is the class that all
 * user interactions with the model should occur through.
 */
public final class Flip2CoinsMK11 extends Model<Flip2CoinsMK11.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] bias;
		int coins;
		boolean[] constrainedFlag$sample22;
		boolean constrainedFlag$sample9 = true;
		boolean fixedFlag$sample22 = false;
		boolean fixedFlag$sample9 = false;
		boolean fixedProbFlag$sample22 = false;
		boolean fixedProbFlag$sample49 = false;
		boolean fixedProbFlag$sample77 = false;
		boolean fixedProbFlag$sample9 = false;
		boolean[][] flips;
		boolean[][] flipsMeasured;
		int[] length$flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double[] logProbability$bernoulli1;
		double[] logProbability$bernoulli2;
		double logProbability$beta;
		double logProbability$bias;
		double logProbability$flips;
		double[] logProbability$sample49;
		double[] logProbability$sample77;
		double logProbability$var22;
		double logProbability$var9;
		boolean system$gibbsForward = true;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for flips
			flips = new boolean[length$flipsMeasured.length][];
			
									// Substituted "j" with its value "0".
			flips[0] = new boolean[length$flipsMeasured[0]];
			for(int k = 1; k < length$flipsMeasured.length; k += 1)
				flips[k] = new boolean[length$flipsMeasured[k]];
			
			// If bias has not been set already allocate space.
			if((!fixedFlag$sample9 || !fixedFlag$sample22))
				// Constructor for bias
				bias = new double[length$flipsMeasured.length];
			
			// Constructor for constrainedFlag$sample22
			constrainedFlag$sample22 = new boolean[(length$flipsMeasured.length - 1)];
			
			// Constructor for logProbability$bernoulli1
			logProbability$bernoulli1 = new double[1];
			
			// Constructor for logProbability$sample49
			logProbability$sample49 = new double[1];
			
			// Constructor for logProbability$bernoulli2
			logProbability$bernoulli2 = new double[(length$flipsMeasured.length - 1)];
			
			// Constructor for logProbability$sample77
			logProbability$sample77 = new double[(length$flipsMeasured.length - 1)];
		}

		// Getter for bias.
		final double[] get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of bias including if probabilities need to be
			// updated.
			bias = cv$value;
			
			// Unset the fixed probability flag for sample 9 as it depends on bias.
			fixedProbFlag$sample9 = false;
			
			// Unset the fixed probability flag for sample 22 as it depends on bias.
			fixedProbFlag$sample22 = false;
			
			// Unset the fixed probability flag for sample 49 as it depends on bias.
			fixedProbFlag$sample49 = false;
			
			// Unset the fixed probability flag for sample 77 as it depends on bias.
			fixedProbFlag$sample77 = false;
		}

		// Getter for coins.
		final int get$coins() {
			return coins;
		}

		// Getter for fixedFlag$sample22.
		final boolean get$fixedFlag$sample22() {
			return fixedFlag$sample22;
		}

		// Setter for fixedFlag$sample22.
		final void set$fixedFlag$sample22(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample22 including if probabilities
			// need to be updated.
			fixedFlag$sample22 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample22$1 = 0; index$constrainedFlag$sample22$1 < constrainedFlag$sample22.length; index$constrainedFlag$sample22$1 += 1)
					constrainedFlag$sample22[index$constrainedFlag$sample22$1] = true;
			}
			
			// Should the probability of sample 22 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample22" with its value "cv$value".
			fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
			
			// Should the probability of sample 49 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample22" with its value "cv$value".
			fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
			
			// Should the probability of sample 77 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample22" with its value "cv$value".
			fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
		}

		// Getter for fixedFlag$sample9.
		final boolean get$fixedFlag$sample9() {
			return fixedFlag$sample9;
		}

		// Setter for fixedFlag$sample9.
		final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
			// need to be updated.
			fixedFlag$sample9 = cv$value;
			
			// Substituted "fixedFlag$sample9" with its value "cv$value".
			constrainedFlag$sample9 = (cv$value || constrainedFlag$sample9);
			
			// Should the probability of sample 9 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample9" with its value "cv$value".
			fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
			
			// Should the probability of sample 49 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample9" with its value "cv$value".
			fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
			
			// Should the probability of sample 77 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample9" with its value "cv$value".
			fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
		}

		// Getter for flips.
		final boolean[][] get$flips() {
			return flips;
		}

		// Getter for flipsMeasured.
		final boolean[][] get$flipsMeasured() {
			return flipsMeasured;
		}

		// Setter for flipsMeasured.
		final void set$flipsMeasured(boolean[][] cv$value, boolean allocated$) {
			flipsMeasured = cv$value;
		}

		// Getter for length$flipsMeasured.
		final int[] get$length$flipsMeasured() {
			return length$flipsMeasured;
		}

		// Setter for length$flipsMeasured.
		final void set$length$flipsMeasured(int[] cv$value, boolean allocated$) {
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

		// Getter for logProbability$bernoulli1.
		final double[] get$logProbability$bernoulli1() {
			return logProbability$bernoulli1;
		}

		// Getter for logProbability$bernoulli2.
		final double[] get$logProbability$bernoulli2() {
			return logProbability$bernoulli2;
		}

		// Getter for logProbability$beta.
		final double get$logProbability$beta() {
			return logProbability$beta;
		}

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$flips.
		final double get$logProbability$flips() {
			return logProbability$flips;
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
                state.set$fixedFlag$sample22(fixed, allocated);
                state.set$fixedFlag$sample9(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample22 = state.get$fixedFlag$sample22();
            boolean fixedFlag$sample9 = state.get$fixedFlag$sample9();
            if(fixedFlag$sample22 && fixedFlag$sample9)
                return Immutability.FIXED;
            else if(fixedFlag$sample22 || fixedFlag$sample9)
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
            throw new SandwoodException("Set is not available for variable flips because its value is fixed by observed values.");
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

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayShapeableInternal<boolean[], int[]> $flipsMeasured = new ObservedObjectArrayShapeableInternal<boolean[], int[]>(this, "flipsMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        public void setValueInternal(boolean[][] value) {
            state.set$flipsMeasured(value, allocated);
            state.set$length$flipsMeasured(getDims(value), allocated);
        }

        @Override
        public void setShapeInternal(int[] shape) {
            state.set$length$flipsMeasured(shape, allocated);
        }

        @Override
        public int[] getShape() {
            return state.get$length$flipsMeasured();
        }
        private final int[] getDims(boolean[][] v1) {
            int[] s1 = new int[v1.length];
            for(int i1 = 0; i1 < v1.length; i1++) {
                boolean[] v0 = v1[i1];
                s1[i1] = v0.length;
            }
            return s1;
        }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArrayShapeable<boolean[], int[]> flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final IteratedRandomVariableInternal<double[]> $bernoulli1 = new IteratedRandomVariableInternal<double[]>(this, "bernoulli1", 1, ProbabilityType.SKIPPABLE) {
        @Override
        public double[] getCurrentLogProbability() {
            return state.get$logProbability$bernoulli1();
        }
    };

	/**
	 * Iterated random variable representing the random variable bernoulli1 from
	 * the Sandwood model. The random variable is embedded in a loop
	 * so results are returned as anarray.
	 */
    public final IteratedRandomVariable<double[]> bernoulli1 = $bernoulli1;

    private final IteratedRandomVariableInternal<double[]> $bernoulli2 = new IteratedRandomVariableInternal<double[]>(this, "bernoulli2", 1, ProbabilityType.SKIPPABLE) {
        @Override
        public double[] getCurrentLogProbability() {
            return state.get$logProbability$bernoulli2();
        }
    };

	/**
	 * Iterated random variable representing the random variable bernoulli2 from
	 * the Sandwood model. The random variable is embedded in a loop
	 * so results are returned as anarray.
	 */
    public final IteratedRandomVariable<double[]> bernoulli2 = $bernoulli2;

    private final RandomVariableInternal $beta = new RandomVariableInternal(this, "beta", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$beta();
        }
    };

	/** Random variable representing beta from the Sandwood model. */
    public final RandomVariable beta = $beta;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $bernoulli1, $bernoulli2, $beta};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip2CoinsMK11() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);

        Flip2CoinsMK11$SingleThreadCPU core = new Flip2CoinsMK11$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
	 *                           to use in the model when generating results.
	 */
    public Flip2CoinsMK11(int[] flipsMeasuredShape) {
        this();
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 */
    public Flip2CoinsMK11(boolean[][] flipsMeasured) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip2CoinsMK11$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip2CoinsMK11$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input flipsMeasured */
        public final int[] flipsMeasuredShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
		 *                           to use in the model when generating results.
		 */
        public InferValueInputs(int[] flipsMeasuredShape) {
            this.flipsMeasuredShape = flipsMeasuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input flipsMeasured */
        public final boolean[][] flipsMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 */
        public AllInputs(boolean[][] flipsMeasured) {
            this.flipsMeasured = flipsMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[][] flips;

        InferredValueOutputs(Flip2CoinsMK11 system$model) {
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
		/** Field holding the log probability of random variable bernoulli1 */
        public final double[] bernoulli1;
		/** Field holding the log probability of random variable bernoulli2 */
        public final double[] bernoulli2;
		/** Field holding the log probability of random variable beta */
        public final double beta;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips */
        public final double flips;

        LogProbabilities(Flip2CoinsMK11 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli1 = system$model.bernoulli1.getLogProbability();
            this.bernoulli2 = system$model.bernoulli2.getLogProbability();
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
		/** Field holding the probability of random variable bernoulli1 */
        public final double[] bernoulli1;
		/** Field holding the probability of random variable bernoulli2 */
        public final double[] bernoulli2;
		/** Field holding the probability of random variable beta */
        public final double beta;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips */
        public final double flips;

        Probabilities(Flip2CoinsMK11 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli1 = system$model.bernoulli1.getProbability();
            this.bernoulli2 = system$model.bernoulli2.getProbability();
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

        InferredModelOutputs(Flip2CoinsMK11 system$model) {
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
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}