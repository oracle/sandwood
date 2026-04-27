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
 * Class representing the Sandwood model HMMTestPart1 This is the class that all user
 * interactions with the model should occur through.
 */
public final class HMMTestPart1 extends Model<HMMTestPart1.State> {
	final class State extends CoreModelState {
double[] bias;
		boolean[] constrainedFlag$sample28;
		boolean[] constrainedFlag$sample45;
		boolean constrainedFlag$sample50 = true;
		boolean fixedFlag$sample28 = false;
		boolean fixedFlag$sample45 = false;
		boolean fixedFlag$sample50 = false;
		boolean fixedProbFlag$sample28 = false;
		boolean fixedProbFlag$sample45 = false;
		boolean fixedProbFlag$sample50 = false;
		boolean fixedProbFlag$sample53 = false;
		boolean flip;
		boolean flipMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$flip;
		double logProbability$m;
		double logProbability$st;
		double logProbability$var28;
		double logProbability$var44;
		double[][] m;
		int st;
		int states;
		boolean system$gibbsForward = true;
		double[] v;

		@Override
		public final void allocate() {
			v = new double[2];
			if(!fixedFlag$sample28) {
				m = new double[2][];
				m[0] = new double[2];
				m[1] = new double[2];
			}
			if(!fixedFlag$sample45)
				bias = new double[2];
			constrainedFlag$sample45 = new boolean[2];
			constrainedFlag$sample28 = new boolean[2];
		}

		final double[] get$bias() {
			return bias;
		}

		final void set$bias(double[] cv$value, boolean allocated$) {
			bias = cv$value;
			fixedProbFlag$sample45 = false;
			fixedProbFlag$sample53 = false;
		}

		final boolean get$fixedFlag$sample28() {
			return fixedFlag$sample28;
		}

		final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
			fixedFlag$sample28 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
					constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
			}
			fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
			fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		}

		final boolean get$fixedFlag$sample45() {
			return fixedFlag$sample45;
		}

		final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
			fixedFlag$sample45 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
					constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
			}
			fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
			fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		}

		final boolean get$fixedFlag$sample50() {
			return fixedFlag$sample50;
		}

		final void set$fixedFlag$sample50(boolean cv$value, boolean allocated$) {
			fixedFlag$sample50 = cv$value;
			constrainedFlag$sample50 = (cv$value || constrainedFlag$sample50);
			fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
			fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		}

		final boolean get$flip() {
			return flip;
		}

		final boolean get$flipMeasured() {
			return flipMeasured;
		}

		final void set$flipMeasured(boolean cv$value, boolean allocated$) {
			flipMeasured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		final double get$logProbability$flip() {
			return logProbability$flip;
		}

		final double get$logProbability$m() {
			return logProbability$m;
		}

		final double get$logProbability$st() {
			return logProbability$st;
		}

		final double[][] get$m() {
			return m;
		}

		final void set$m(double[][] cv$value, boolean allocated$) {
			m = cv$value;
			fixedProbFlag$sample28 = false;
			fixedProbFlag$sample50 = false;
		}

		final int get$st() {
			return st;
		}

		final void set$st(int cv$value, boolean allocated$) {
			st = cv$value;
			fixedProbFlag$sample50 = false;
			fixedProbFlag$sample53 = false;
		}

		final int get$states() {
			return 2;
		}

		final double[] get$v() {
			return v;
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
                state.set$fixedFlag$sample45(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample45())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[] from the Sandwood model. */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedBooleanInternal $flip = new ComputedBooleanInternal(this, "flip", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$flip(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flip because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flip(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing flip of type boolean from the Sandwood model. */
    public final ComputedBoolean flip = $flip;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$m(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$m(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample28(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample28())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedIntegerInternal $st = new ComputedIntegerInternal(this, "st", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$st(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$st(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$st(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample50(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample50())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int from the Sandwood model. */
    public final ComputedInteger st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanInternal $flipMeasured = new ObservedBooleanInternal(this, "flipMeasured") {
        @Override
        public boolean getValue() {
            synchronized(model) {
                return state.get$flipMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean value) { state.set$flipMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing flipMeasured of type boolean from the Sandwood model.
	 */
    public final ObservedBoolean flipMeasured = $flipMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flip, $m, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMMTestPart1() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flip", $flip);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);

        //Observed scalar fields
        $regularObservedValues.put("flipMeasured", $flipMeasured);

        HMMTestPart1$SingleThreadCPU core = new HMMTestPart1$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipMeasured The value to set flipMeasured to.
	 */
    public HMMTestPart1(boolean flipMeasured) {
        this();
        this.flipMeasured.setValue(flipMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMMTestPart1$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMMTestPart1$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input flipMeasured */
        public final boolean flipMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipMeasured The value to set flipMeasured to.
		 */
        public AllInputs(boolean flipMeasured) {
            this.flipMeasured = flipMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of flip after a convention execution step. */
        public final boolean flip;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of st after a convention execution step. */
        public final int st;

        InferredValueOutputs(HMMTestPart1 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flip = system$model.flip.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flip */
        public final double flip;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMTestPart1 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flip = system$model.flip.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
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
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flip */
        public final double flip;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMTestPart1 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flip = system$model.flip.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
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
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[] st;

        InferredModelOutputs(HMMTestPart1 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
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
        this.$flipMeasured.setValue(inputs.flipMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}