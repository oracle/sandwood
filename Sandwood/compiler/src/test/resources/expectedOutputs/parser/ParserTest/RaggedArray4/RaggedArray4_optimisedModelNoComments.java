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
 * Class representing the Sandwood model RaggedArray4 This is the class that all user
 * interactions with the model should occur through.
 */
public final class RaggedArray4 extends Model<RaggedArray4.State> {
	final class State extends CoreModelState {
double[][] a;
		double[] b;
		boolean constrainedFlag$sample47 = true;
		boolean constrainedFlag$sample50 = true;
		double[] d;
		boolean fixedFlag$sample47 = false;
		boolean fixedFlag$sample50 = false;
		boolean fixedProbFlag$sample47 = false;
		boolean fixedProbFlag$sample50 = false;
		boolean fixedProbFlag$sample64 = false;
		int length$obs_measured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$d;
		double logProbability$obs;
		double logProbability$var62;
		double logProbability$y;
		int[] obs;
		int[] obs_measured;
		boolean system$gibbsForward = true;
		int y;

		@Override
		public final void allocate() {
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
			b = new double[2];
			if(!fixedFlag$sample50) {
				int lengthCV$a$48_0 = -1;
				if((0 == y))
					lengthCV$a$48_0 = 2;
				if((1 == y))
					lengthCV$a$48_0 = 3;
				d = new double[lengthCV$a$48_0];
			}
			obs = new int[length$obs_measured];
		}

		final double[][] get$a() {
			return a;
		}

		final double[] get$b() {
			return b;
		}

		final double[] get$d() {
			return d;
		}

		final void set$d(double[] cv$value, boolean allocated$) {
			d = cv$value;
			fixedProbFlag$sample50 = false;
			fixedProbFlag$sample64 = false;
		}

		final boolean get$fixedFlag$sample47() {
			return fixedFlag$sample47;
		}

		final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
			fixedFlag$sample47 = cv$value;
			constrainedFlag$sample47 = (cv$value || constrainedFlag$sample47);
			fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
			fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		}

		final boolean get$fixedFlag$sample50() {
			return fixedFlag$sample50;
		}

		final void set$fixedFlag$sample50(boolean cv$value, boolean allocated$) {
			fixedFlag$sample50 = cv$value;
			constrainedFlag$sample50 = (cv$value || constrainedFlag$sample50);
			fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
			fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
		}

		final int get$length$obs_measured() {
			return length$obs_measured;
		}

		final void set$length$obs_measured(int cv$value, boolean allocated$) {
			length$obs_measured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$d() {
			return logProbability$d;
		}

		final double get$logProbability$obs() {
			return logProbability$obs;
		}

		final double get$logProbability$y() {
			return logProbability$y;
		}

		final int[] get$obs() {
			return obs;
		}

		final int[] get$obs_measured() {
			return obs_measured;
		}

		final void set$obs_measured(int[] cv$value, boolean allocated$) {
			obs_measured = cv$value;
		}

		final int get$y() {
			return y;
		}

		final void set$y(int cv$value, boolean allocated$) {
			y = cv$value;
			fixedProbFlag$sample47 = false;
			fixedProbFlag$sample50 = false;
		}
	}

    private final ComputedDoubleArrayInternal $d = new ComputedDoubleArrayInternal(this, "d", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$d(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$d(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$d(); }

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

	/** Computed variable representing d of type double[] from the Sandwood model. */
    public final ComputedDoubleArray d = $d;

    private final ComputedIntegerArrayInternal $obs = new ComputedIntegerArrayInternal(this, "obs", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$obs(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable obs because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$obs(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing obs of type int[] from the Sandwood model. */
    public final ComputedIntegerArray obs = $obs;

    private final ComputedIntegerInternal $y = new ComputedIntegerInternal(this, "y", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$y(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$y(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample47(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample47())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing y of type int from the Sandwood model. */
    public final ComputedInteger y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayShapeableInternal $obs_measured = new ObservedIntegerArrayShapeableInternal(this, "obs_measured") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return state.get$obs_measured();
            }
        }

        @Override
        public void setValueInternal(int[] value) {
            state.set$obs_measured(value, allocated);
            state.set$length$obs_measured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$obs_measured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$obs_measured();
        }
    };

	/**
	 * Observed variable representing obs_measured of type int[] from the Sandwood model.
	 */
    public final ObservedIntegerArrayShapeable obs_measured = $obs_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$d, $obs, $y};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public RaggedArray4() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("d", $d);
        $computedVariables.put("obs", $obs);
        $computedVariables.put("y", $y);

        //Observed array fields
        $shapedObservedValues.put("obs_measured", $obs_measured);

        RaggedArray4$SingleThreadCPU core = new RaggedArray4$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
	 *                          to use in the model when generating results.
	 */
    public RaggedArray4(int obs_measuredShape) {
        this();
        this.$obs_measured.setShape(obs_measuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param obs_measured The value to set obs_measured to.
	 */
    public RaggedArray4(int[] obs_measured) {
        this();
        this.obs_measured.setValue(obs_measured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new RaggedArray4$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new RaggedArray4$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input obs_measured */
        public final int obs_measuredShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
		 *                          to use in the model when generating results.
		 */
        public InferValueInputs(int obs_measuredShape) {
            this.obs_measuredShape = obs_measuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input obs_measured */
        public final int[] obs_measured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param obs_measured The value to set obs_measured to.
		 */
        public AllInputs(int[] obs_measured) {
            this.obs_measured = obs_measured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of d after a convention execution step. */
        public final double[] d;
		/** Field holding the value of obs after a convention execution step. */
        public final int[] obs;
		/** Field holding the value of y after a convention execution step. */
        public final int y;

        InferredValueOutputs(RaggedArray4 system$model) {
            this.d = system$model.d.getSamples()[0];
            this.obs = system$model.obs.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable d */
        public final double d;
		/** Field holding the log probability of computed variable obs */
        public final double obs;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(RaggedArray4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.d = system$model.d.getLogProbability();
            this.obs = system$model.obs.getLogProbability();
            this.y = system$model.y.getLogProbability();
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
		/** Field holding the probability of computed variable d */
        public final double d;
		/** Field holding the probability of computed variable obs */
        public final double obs;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(RaggedArray4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.d = system$model.d.getProbability();
            this.obs = system$model.obs.getProbability();
            this.y = system$model.y.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of d after an infer model call. */
        public final double[][] d;
		/** Field holding the MAP or Sample value of y after an infer model call. */
        public final int[] y;

        InferredModelOutputs(RaggedArray4 system$model) {
            this.d = system$model.getInferredValue(system$model.$d);
            this.y = system$model.getInferredValue(system$model.$y);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$obs_measured.setShape(inputs.obs_measuredShape);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}