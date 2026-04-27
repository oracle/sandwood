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
 * Class representing the Sandwood model LogitRegressionTest This is the class that
 * all user interactions with the model should occur through.
 */
public final class LogitRegressionTest extends Model<LogitRegressionTest.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double bias;
		boolean[] constrainedFlag$sample35;
		boolean constrainedFlag$sample42 = true;
		boolean fixedFlag$sample35 = false;
		boolean fixedFlag$sample42 = false;
		boolean fixedProbFlag$sample35 = false;
		boolean fixedProbFlag$sample42 = false;
		boolean fixedProbFlag$sample94 = false;
		double[][] indicator;
		int k;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double[] logProbability$sample35;
		double[][] logProbability$sample94;
		double logProbability$weights;
		double logProbability$y;
		int n;
		double[][] p;
		boolean system$gibbsForward = true;
		double[] weights;
		double[][] x;
		boolean[][] y;
		boolean[][] yMeasured;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for y
			{
				y = new boolean[x.length][];
				for(int var15 = 0; var15 < x.length; var15 += 1)
					y[var15] = new boolean[3];
			}
			
			// If weights has not been set already allocate space.
			if(!fixedFlag$sample35) {
				// Constructor for weights
				{
					weights = new double[3];
				}
			}
			
			// Constructor for indicator
			{
				indicator = new double[((((x.length - 1) - 0) / 1) + 1)][];
				for(int i = 0; i < x.length; i += 1)
					indicator[((i - 0) / 1)] = new double[3];
			}
			
			// Constructor for p
			{
				p = new double[((((x.length - 1) - 0) / 1) + 1)][];
				for(int i = 0; i < x.length; i += 1)
					p[((i - 0) / 1)] = new double[3];
			}
			
			// Constructor for constrainedFlag$sample35
			{
				constrainedFlag$sample35 = new boolean[((((3 - 1) - 0) / 1) + 1)];
			}
			
			// Constructor for logProbability$sample35
			{
				logProbability$sample35 = new double[((((3 - 1) - 0) / 1) + 1)];
			}
			
			// Constructor for logProbability$sample94
			{
				logProbability$sample94 = new double[((((x.length - 1) - 0) / 1) + 1)][];
				for(int i = 0; i < x.length; i += 1)
					logProbability$sample94[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
			}
		}

		// Getter for bias.
		final double get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of bias including if probabilities need to be
			// updated.
			bias = cv$value;
			
			// Unset the fixed probability flag for sample 42 as it depends on bias.
			fixedProbFlag$sample42 = false;
			
			// Unset the fixed probability flag for sample 94 as it depends on bias.
			fixedProbFlag$sample94 = false;
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
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
					constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
			}
			
			// Should the probability of sample 35 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
			
			// Should the probability of sample 94 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample94 = (fixedFlag$sample35 && fixedProbFlag$sample94);
		}

		// Getter for fixedFlag$sample42.
		final boolean get$fixedFlag$sample42() {
			return fixedFlag$sample42;
		}

		// Setter for fixedFlag$sample42.
		final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
			// need to be updated.
			fixedFlag$sample42 = cv$value;
			constrainedFlag$sample42 = (fixedFlag$sample42 || constrainedFlag$sample42);
			
			// Should the probability of sample 42 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
			
			// Should the probability of sample 94 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample94 = (fixedFlag$sample42 && fixedProbFlag$sample94);
		}

		// Getter for k.
		final int get$k() {
			return k;
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

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$weights.
		final double get$logProbability$weights() {
			return logProbability$weights;
		}

		// Getter for logProbability$y.
		final double get$logProbability$y() {
			return logProbability$y;
		}

		// Getter for n.
		final int get$n() {
			return n;
		}

		// Getter for weights.
		final double[] get$weights() {
			return weights;
		}

		// Setter for weights.
		final void set$weights(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of weights including if probabilities need to
			// be updated.
			weights = cv$value;
			
			// Unset the fixed probability flag for sample 35 as it depends on weights.
			fixedProbFlag$sample35 = false;
			
			// Unset the fixed probability flag for sample 94 as it depends on weights.
			fixedProbFlag$sample94 = false;
		}

		// Getter for x.
		final double[][] get$x() {
			return x;
		}

		// Setter for x.
		final void set$x(double[][] cv$value, boolean allocated$) {
			x = cv$value;
		}

		// Getter for y.
		final boolean[][] get$y() {
			return y;
		}

		// Getter for yMeasured.
		final boolean[][] get$yMeasured() {
			return yMeasured;
		}

		// Setter for yMeasured.
		final void set$yMeasured(boolean[][] cv$value, boolean allocated$) {
			yMeasured = cv$value;
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
                state.set$fixedFlag$sample42(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample42())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double from the Sandwood model. */
    public final ComputedDouble bias = $bias;

    private final ComputedDoubleArrayInternal $weights = new ComputedDoubleArrayInternal(this, "weights", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$weights(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$weights(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$weights(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample35(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing weights of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray weights = $weights;

    private final ComputedObjectArrayInternal<boolean[]> $y = new ComputedObjectArrayInternal<boolean[]>(this, "y", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() { return state.get$y(); }

        @Override
        protected void setValueInternal(boolean[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable y because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$y(); }

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

	/** Computed variable representing y of type boolean[][] from the Sandwood model. */
    public final ComputedObjectArray<boolean[]> y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<double[]> $x = new ObservedObjectArrayInternal<double[]>(this, "x", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() {
            synchronized(model) {
                return state.get$x();
            }
        }

        @Override
        protected void setValueInternal(double[][] value) { state.set$x(value, allocated); }
    };

	/** Observed variable representing x of type double[][] from the Sandwood model. */
    public final ObservedObjectArray<double[]> x = $x;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[]> $yMeasured = new ObservedObjectArrayInternal<boolean[]>(this, "yMeasured", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return state.get$yMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[][] value) { state.set$yMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing yMeasured of type boolean[][] from the Sandwood
	 * model.
	 */
    public final ObservedObjectArray<boolean[]> yMeasured = $yMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $weights, $y};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public LogitRegressionTest() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("weights", $weights);
        $computedVariables.put("y", $y);

        //ModelInputs
        $modelInputs.put("x", $x);

        //Observed scalar fields
        $regularObservedValues.put("yMeasured", $yMeasured);

        LogitRegressionTest$SingleThreadCPU core = new LogitRegressionTest$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param x The value to set x to.
	 */
    public LogitRegressionTest(double[][] x) {
        this();
        this.$x.setValue(x);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param x The value to set x to.
	 * @param yMeasured The value to set yMeasured to
	 */
    public LogitRegressionTest(double[][] x, boolean[][] yMeasured) {
        this();
        this.x.setValue(x);
        this.yMeasured.setValue(yMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new LogitRegressionTest$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new LogitRegressionTest$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input x */
        public final double[][] x;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param x The value to set x to.
		 */
        public InferValueInputs(double[][] x) {
            this.x = x;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input x */
        public final double[][] x;
		/** Field holding the value of model input yMeasured */
        public final boolean[][] yMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param x The value to set x to.
		 * @param yMeasured The value to set yMeasured to.
		 */
        public AllInputs(double[][] x, boolean[][] yMeasured) {
            this.x = x;
            this.yMeasured = yMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of weights after a convention execution step. */
        public final double[] weights;
		/** Field holding the value of y after a convention execution step. */
        public final boolean[][] y;

        InferredValueOutputs(LogitRegressionTest system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.weights = system$model.weights.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
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
		/** Field holding the log probability of computed variable weights */
        public final double weights;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LogitRegressionTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.weights = system$model.weights.getLogProbability();
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
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable weights */
        public final double weights;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LogitRegressionTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.weights = system$model.weights.getProbability();
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
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[] bias;
		/** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(LogitRegressionTest system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.weights = system$model.getInferredValue(system$model.$weights);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.x.setValue(inputs.x);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
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
        this.x.setValue(inputs.x);
        this.$yMeasured.setValue(inputs.yMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}