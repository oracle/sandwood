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
 * Class representing the Sandwood model RaggedArray This is the class that all user
 * interactions with the model should occur through.
 */
public final class RaggedArray extends Model<RaggedArray.State> {
	final class State extends CoreModelState {
double[][] a;
		double[][] b;
		boolean constrainedFlag$sample73 = true;
		boolean fixedFlag$sample73 = false;
		boolean fixedProbFlag$sample73 = false;
		boolean fixedProbFlag$sample89 = false;
		int i;
		int length$obs_measured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$i;
		double logProbability$obs;
		double logProbability$var85;
		boolean[] obs;
		boolean[] obs_measured;
		double p;
		boolean system$gibbsForward = true;
		int y;

		@Override
		public final void allocate() {
			{
				a = new double[2][];
				a[0] = new double[2];
				a[1] = new double[3];
			}
			{
				b = new double[2][];
				b[0] = new double[2];
				b[1] = new double[3];
			}
			{
				obs = new boolean[length$obs_measured];
			}
		}

		final double[][] get$a() {
			return a;
		}

		final double[][] get$b() {
			return b;
		}

		final boolean get$fixedFlag$sample73() {
			return fixedFlag$sample73;
		}

		final void set$fixedFlag$sample73(boolean cv$value, boolean allocated$) {
			fixedFlag$sample73 = cv$value;
			constrainedFlag$sample73 = (fixedFlag$sample73 || constrainedFlag$sample73);
			fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
			fixedProbFlag$sample89 = (fixedFlag$sample73 && fixedProbFlag$sample89);
		}

		final int get$i() {
			return i;
		}

		final void set$i(int cv$value, boolean allocated$) {
			i = cv$value;
			fixedProbFlag$sample73 = false;
			fixedProbFlag$sample89 = false;
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

		final double get$logProbability$i() {
			return logProbability$i;
		}

		final double get$logProbability$obs() {
			return logProbability$obs;
		}

		final boolean[] get$obs() {
			return obs;
		}

		final boolean[] get$obs_measured() {
			return obs_measured;
		}

		final void set$obs_measured(boolean[] cv$value, boolean allocated$) {
			obs_measured = cv$value;
		}

		final double get$p() {
			return p;
		}

		final int get$y() {
			return y;
		}

		final void set$y(int cv$value, boolean allocated$) {
			y = cv$value;
		}
	}

    private final ComputedIntegerInternal $i = new ComputedIntegerInternal(this, "i", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$i(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$i(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$i(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample73(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample73())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing i of type int from the Sandwood model. */
    public final ComputedInteger i = $i;

    private final ComputedBooleanArrayInternal $obs = new ComputedBooleanArrayInternal(this, "obs", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$obs(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

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

	/** Computed variable representing obs of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray obs = $obs;

    private final ComputedDoubleInternal $p = new ComputedDoubleInternal(this, "p", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$p(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable p because its value depends on variable \"i\".");
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample73(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample73())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing p of type double from the Sandwood model. */
    public final ComputedDouble p = $p;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $y = new ObservedIntegerInternal(this, "y") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$y();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$y(value, allocated); }
    };

	/** Observed variable representing y of type int from the Sandwood model. */
    public final ObservedInteger y = $y;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $obs_measured = new ObservedBooleanArrayShapeableInternal(this, "obs_measured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$obs_measured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
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
	 * Observed variable representing obs_measured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable obs_measured = $obs_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$i, $obs};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public RaggedArray() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("i", $i);
        $computedVariables.put("obs", $obs);
        $computedVariables.put("p", $p);

        //ModelInputs
        $modelInputs.put("y", $y);

        //Observed array fields
        $shapedObservedValues.put("obs_measured", $obs_measured);

        RaggedArray$SingleThreadCPU core = new RaggedArray$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param y The value to set y to.
	 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
	 *                          to use in the model when generating results.
	 */
    public RaggedArray(int y, int obs_measuredShape) {
        this();
        this.$y.setValue(y);
        this.$obs_measured.setShape(obs_measuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param y The value to set y to.
	 * @param obs_measured The value to set obs_measured to
	 */
    public RaggedArray(int y, boolean[] obs_measured) {
        this();
        this.y.setValue(y);
        this.obs_measured.setValue(obs_measured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new RaggedArray$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new RaggedArray$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input y */
        public final int y;
		/** Field holding the shape of model input obs_measured */
        public final int obs_measuredShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param y The value to set y to.
		 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
		 *                          to use in the model when generating results.
		 */
        public InferValueInputs(int y, int obs_measuredShape) {
            this.y = y;
            this.obs_measuredShape = obs_measuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input y */
        public final int y;
		/** Field holding the value of model input obs_measured */
        public final boolean[] obs_measured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param y The value to set y to.
		 * @param obs_measured The value to set obs_measured to.
		 */
        public AllInputs(int y, boolean[] obs_measured) {
            this.y = y;
            this.obs_measured = obs_measured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of i after a convention execution step. */
        public final int i;
		/** Field holding the value of obs after a convention execution step. */
        public final boolean[] obs;
		/** Field holding the value of p after a convention execution step. */
        public final double p;

        InferredValueOutputs(RaggedArray system$model) {
            this.i = system$model.i.getSamples()[0];
            this.obs = system$model.obs.getSamples()[0];
            this.p = system$model.p.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable i */
        public final double i;
		/** Field holding the log probability of computed variable obs */
        public final double obs;
		/** Field holding the log probability of computed variable p */
        public final double p;

        LogProbabilities(RaggedArray system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.i = system$model.i.getLogProbability();
            this.obs = system$model.obs.getLogProbability();
            this.p = system$model.p.getLogProbability();
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
		/** Field holding the probability of computed variable i */
        public final double i;
		/** Field holding the probability of computed variable obs */
        public final double obs;
		/** Field holding the probability of computed variable p */
        public final double p;

        Probabilities(RaggedArray system$model) {
            this.$modelProbability = system$model.getProbability();
            this.i = system$model.i.getProbability();
            this.obs = system$model.obs.getProbability();
            this.p = system$model.p.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of i after an infer model call. */
        public final int[] i;
		/** Field holding the MAP or Sample value of p after an infer model call. */
        public final double[] p;

        InferredModelOutputs(RaggedArray system$model) {
            this.i = system$model.getInferredValue(system$model.$i);
            this.p = system$model.getInferredValue(system$model.$p);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
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
        this.y.setValue(inputs.y);
        this.$obs_measured.setValue(inputs.obs_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}