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
 * Class representing the Sandwood model Flip1CoinMK5 This is the class that all user
 * interactions with the model should occur through.
 */
public final class Flip1CoinMK5 extends Model<Flip1CoinMK5.State> {
	final class State extends CoreModelState {
double bias;
		boolean constrainedFlag$sample9 = true;
		boolean fixedFlag$sample9 = false;
		boolean fixedProbFlag$sample22 = false;
		boolean fixedProbFlag$sample36 = false;
		boolean fixedProbFlag$sample9 = false;
		boolean[] flips1;
		boolean[] flips2;
		boolean[] flipsMeasured1;
		boolean[] flipsMeasured2;
		int length$flipsMeasured1;
		int length$flipsMeasured2;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bernoulli1;
		double logProbability$bernoulli2;
		double logProbability$bias;
		double logProbability$flips1;
		double logProbability$flips2;
		double logProbability$var22;
		double logProbability$var36;
		int samples1;
		int samples2;
		boolean system$gibbsForward = true;

		@Override
		public final void allocate() {
			{
				flips1 = new boolean[length$flipsMeasured1];
			}
			{
				flips2 = new boolean[length$flipsMeasured2];
			}
		}

		final double get$bias() {
			return bias;
		}

		final void set$bias(double cv$value, boolean allocated$) {
			bias = cv$value;
			fixedProbFlag$sample9 = false;
			fixedProbFlag$sample22 = false;
			fixedProbFlag$sample36 = false;
		}

		final boolean get$fixedFlag$sample9() {
			return fixedFlag$sample9;
		}

		final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
			fixedFlag$sample9 = cv$value;
			constrainedFlag$sample9 = (fixedFlag$sample9 || constrainedFlag$sample9);
			fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
			fixedProbFlag$sample22 = (fixedFlag$sample9 && fixedProbFlag$sample22);
			fixedProbFlag$sample36 = (fixedFlag$sample9 && fixedProbFlag$sample36);
		}

		final boolean[] get$flips1() {
			return flips1;
		}

		final boolean[] get$flips2() {
			return flips2;
		}

		final boolean[] get$flipsMeasured1() {
			return flipsMeasured1;
		}

		final void set$flipsMeasured1(boolean[] cv$value, boolean allocated$) {
			flipsMeasured1 = cv$value;
		}

		final boolean[] get$flipsMeasured2() {
			return flipsMeasured2;
		}

		final void set$flipsMeasured2(boolean[] cv$value, boolean allocated$) {
			flipsMeasured2 = cv$value;
		}

		final int get$length$flipsMeasured1() {
			return length$flipsMeasured1;
		}

		final void set$length$flipsMeasured1(int cv$value, boolean allocated$) {
			length$flipsMeasured1 = cv$value;
		}

		final int get$length$flipsMeasured2() {
			return length$flipsMeasured2;
		}

		final void set$length$flipsMeasured2(int cv$value, boolean allocated$) {
			length$flipsMeasured2 = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$bernoulli1() {
			return logProbability$bernoulli1;
		}

		final double get$logProbability$bernoulli2() {
			return logProbability$bernoulli2;
		}

		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		final double get$logProbability$flips1() {
			return logProbability$flips1;
		}

		final double get$logProbability$flips2() {
			return logProbability$flips2;
		}

		final int get$samples1() {
			return samples1;
		}

		final int get$samples2() {
			return samples2;
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
                state.set$fixedFlag$sample9(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample9())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double from the Sandwood model. */
    public final ComputedDouble bias = $bias;

    private final ComputedBooleanArrayInternal $flips1 = new ComputedBooleanArrayInternal(this, "flips1", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$flips1(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips1 because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flips1(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/**
	 * Computed variable representing flips1 of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray flips1 = $flips1;

    private final ComputedBooleanArrayInternal $flips2 = new ComputedBooleanArrayInternal(this, "flips2", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$flips2(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips2 because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flips2(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/**
	 * Computed variable representing flips2 of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray flips2 = $flips2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured1 = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured1") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured1();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$flipsMeasured1(value, allocated);
            state.set$length$flipsMeasured1(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$flipsMeasured1(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$flipsMeasured1();
        }
    };

	/**
	 * Observed variable representing flipsMeasured1 of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable flipsMeasured1 = $flipsMeasured1;

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured2 = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured2") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured2();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$flipsMeasured2(value, allocated);
            state.set$length$flipsMeasured2(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$flipsMeasured2(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$flipsMeasured2();
        }
    };

	/**
	 * Observed variable representing flipsMeasured2 of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable flipsMeasured2 = $flipsMeasured2;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $bernoulli1 = new RandomVariableInternal(this, "bernoulli1", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$bernoulli1();
        }
    };

	/** Random variable representing bernoulli1 from the Sandwood model. */
    public final RandomVariable bernoulli1 = $bernoulli1;

    private final RandomVariableInternal $bernoulli2 = new RandomVariableInternal(this, "bernoulli2", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$bernoulli2();
        }
    };

	/** Random variable representing bernoulli2 from the Sandwood model. */
    public final RandomVariable bernoulli2 = $bernoulli2;

    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips1, $flips2, $bernoulli1, $bernoulli2};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Flip1CoinMK5() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips1", $flips1);
        $computedVariables.put("flips2", $flips2);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured1", $flipsMeasured1);
        $shapedObservedValues.put("flipsMeasured2", $flipsMeasured2);

        Flip1CoinMK5$SingleThreadCPU core = new Flip1CoinMK5$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasured1Shape An integer array describing the shape of variable flipsMeasured1
	 *                            to use in the model when generating results.
	 * @param flipsMeasured2Shape An integer array describing the shape of variable flipsMeasured2
	 *                            to use in the model when generating results.
	 */
    public Flip1CoinMK5(int flipsMeasured1Shape, int flipsMeasured2Shape) {
        this();
        this.$flipsMeasured1.setShape(flipsMeasured1Shape);
        this.$flipsMeasured2.setShape(flipsMeasured2Shape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured1 The value to set flipsMeasured1 to.
	 * @param flipsMeasured2 The value to set flipsMeasured2 to
	 */
    public Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {
        this();
        this.flipsMeasured1.setValue(flipsMeasured1);
        this.flipsMeasured2.setValue(flipsMeasured2);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Flip1CoinMK5$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Flip1CoinMK5$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input flipsMeasured1 */
        public final int flipsMeasured1Shape;
		/** Field holding the shape of model input flipsMeasured2 */
        public final int flipsMeasured2Shape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasured1Shape An integer array describing the shape of variable flipsMeasured1
		 *                            to use in the model when generating results.
		 * @param flipsMeasured2Shape An integer array describing the shape of variable flipsMeasured2
		 *                            to use in the model when generating results.
		 */
        public InferValueInputs(int flipsMeasured1Shape, int flipsMeasured2Shape) {
            this.flipsMeasured1Shape = flipsMeasured1Shape;
            this.flipsMeasured2Shape = flipsMeasured2Shape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input flipsMeasured1 */
        public final boolean[] flipsMeasured1;
		/** Field holding the value of model input flipsMeasured2 */
        public final boolean[] flipsMeasured2;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured1 The value to set flipsMeasured1 to.
		 * @param flipsMeasured2 The value to set flipsMeasured2 to.
		 */
        public AllInputs(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {
            this.flipsMeasured1 = flipsMeasured1;
            this.flipsMeasured2 = flipsMeasured2;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of flips1 after a convention execution step. */
        public final boolean[] flips1;
		/** Field holding the value of flips2 after a convention execution step. */
        public final boolean[] flips2;

        InferredValueOutputs(Flip1CoinMK5 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips1 = system$model.flips1.getSamples()[0];
            this.flips2 = system$model.flips2.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable bernoulli1 */
        public final double bernoulli1;
		/** Field holding the log probability of random variable bernoulli2 */
        public final double bernoulli2;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips1 */
        public final double flips1;
		/** Field holding the log probability of computed variable flips2 */
        public final double flips2;

        LogProbabilities(Flip1CoinMK5 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bernoulli1 = system$model.bernoulli1.getLogProbability();
            this.bernoulli2 = system$model.bernoulli2.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips1 = system$model.flips1.getLogProbability();
            this.flips2 = system$model.flips2.getLogProbability();
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
        public final double bernoulli1;
		/** Field holding the probability of random variable bernoulli2 */
        public final double bernoulli2;
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips1 */
        public final double flips1;
		/** Field holding the probability of computed variable flips2 */
        public final double flips2;

        Probabilities(Flip1CoinMK5 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bernoulli1 = system$model.bernoulli1.getProbability();
            this.bernoulli2 = system$model.bernoulli2.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips1 = system$model.flips1.getProbability();
            this.flips2 = system$model.flips2.getProbability();
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

        InferredModelOutputs(Flip1CoinMK5 system$model) {
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
        this.$flipsMeasured1.setShape(inputs.flipsMeasured1Shape);
        this.$flipsMeasured2.setShape(inputs.flipsMeasured2Shape);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
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
        this.$flipsMeasured1.setValue(inputs.flipsMeasured1);
        this.$flipsMeasured2.setValue(inputs.flipsMeasured2);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}