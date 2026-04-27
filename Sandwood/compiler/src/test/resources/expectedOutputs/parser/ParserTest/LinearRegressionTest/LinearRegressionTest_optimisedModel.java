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
 * Class representing the Sandwood model LinearRegressionTest This is the class that
 * all user interactions with the model should occur through.
 */
public final class LinearRegressionTest extends Model<LinearRegressionTest.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double bias;
		boolean[] constrainedFlag$sample24;
		boolean constrainedFlag$sample31 = true;
		boolean constrainedFlag$sample35 = true;
		boolean fixedFlag$sample24 = false;
		boolean fixedFlag$sample31 = false;
		boolean fixedFlag$sample35 = false;
		boolean fixedProbFlag$sample24 = false;
		boolean fixedProbFlag$sample31 = false;
		boolean fixedProbFlag$sample35 = false;
		boolean fixedProbFlag$sample74 = false;
		int k;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double[] logProbability$sample24;
		double[] logProbability$sample74;
		double logProbability$tau;
		double logProbability$weights;
		double logProbability$y;
		int n;
		double[][] phi;
		boolean system$gibbsForward = true;
		double tau;
		double[] weights;
		double[][] x;
		double[] y;
		double[] yMeasured;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for y
			y = new double[x.length];
			
			// If weights has not been set already allocate space.
			if(!fixedFlag$sample24)
				// Constructor for weights
				weights = new double[x[0].length];
			
			// Constructor for phi
			phi = new double[x.length][];
			for(int i$var45 = 0; i$var45 < x.length; i$var45 += 1)
				phi[i$var45] = new double[x[0].length];
			
			// Constructor for constrainedFlag$sample24
			constrainedFlag$sample24 = new boolean[x[0].length];
			
			// Constructor for logProbability$sample24
			logProbability$sample24 = new double[x[0].length];
			
			// Constructor for logProbability$sample74
			logProbability$sample74 = new double[x.length];
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
			
			// Unset the fixed probability flag for sample 31 as it depends on bias.
			fixedProbFlag$sample31 = false;
			
			// Unset the fixed probability flag for sample 74 as it depends on bias.
			fixedProbFlag$sample74 = false;
		}

		// Getter for fixedFlag$sample24.
		final boolean get$fixedFlag$sample24() {
			return fixedFlag$sample24;
		}

		// Setter for fixedFlag$sample24.
		final void set$fixedFlag$sample24(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample24 including if probabilities
			// need to be updated.
			fixedFlag$sample24 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
					constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
			}
			
			// Should the probability of sample 24 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample24" with its value "cv$value".
			fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
			
			// Should the probability of sample 74 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample24" with its value "cv$value".
			fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
		}

		// Getter for fixedFlag$sample31.
		final boolean get$fixedFlag$sample31() {
			return fixedFlag$sample31;
		}

		// Setter for fixedFlag$sample31.
		final void set$fixedFlag$sample31(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
			// need to be updated.
			fixedFlag$sample31 = cv$value;
			
			// Substituted "fixedFlag$sample31" with its value "cv$value".
			constrainedFlag$sample31 = (cv$value || constrainedFlag$sample31);
			
			// Should the probability of sample 31 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample31" with its value "cv$value".
			fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
			
			// Should the probability of sample 74 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample31" with its value "cv$value".
			fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
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
			
			// Substituted "fixedFlag$sample35" with its value "cv$value".
			constrainedFlag$sample35 = (cv$value || constrainedFlag$sample35);
			
			// Should the probability of sample 35 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample35" with its value "cv$value".
			fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
			
			// Should the probability of sample 74 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample35" with its value "cv$value".
			fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
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

		// Getter for logProbability$tau.
		final double get$logProbability$tau() {
			return logProbability$tau;
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

		// Getter for tau.
		final double get$tau() {
			return tau;
		}

		// Setter for tau.
		final void set$tau(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of tau including if probabilities need to be
			// updated.
			tau = cv$value;
			
			// Unset the fixed probability flag for sample 35 as it depends on tau.
			fixedProbFlag$sample35 = false;
			
			// Unset the fixed probability flag for sample 74 as it depends on tau.
			fixedProbFlag$sample74 = false;
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
			
			// Unset the fixed probability flag for sample 24 as it depends on weights.
			fixedProbFlag$sample24 = false;
			
			// Unset the fixed probability flag for sample 74 as it depends on weights.
			fixedProbFlag$sample74 = false;
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
		final double[] get$y() {
			return y;
		}

		// Getter for yMeasured.
		final double[] get$yMeasured() {
			return yMeasured;
		}

		// Setter for yMeasured.
		final void set$yMeasured(double[] cv$value, boolean allocated$) {
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
                state.set$fixedFlag$sample31(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample31())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double from the Sandwood model. */
    public final ComputedDouble bias = $bias;

    private final ComputedDoubleInternal $tau = new ComputedDoubleInternal(this, "tau", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$tau(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$tau(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$tau(); }

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

	/** Computed variable representing tau of type double from the Sandwood model. */
    public final ComputedDouble tau = $tau;

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
                state.set$fixedFlag$sample24(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample24())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing weights of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray weights = $weights;

    private final ComputedDoubleArrayInternal $y = new ComputedDoubleArrayInternal(this, "y", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$y(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable y because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing y of type double[] from the Sandwood model. */
    public final ComputedDoubleArray y = $y;

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

    private final ObservedDoubleArrayInternal $yMeasured = new ObservedDoubleArrayInternal(this, "yMeasured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$yMeasured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { state.set$yMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing yMeasured of type double[] from the Sandwood model.
	 */
    public final ObservedDoubleArray yMeasured = $yMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $tau, $weights, $y};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public LinearRegressionTest() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("tau", $tau);
        $computedVariables.put("weights", $weights);
        $computedVariables.put("y", $y);

        //ModelInputs
        $modelInputs.put("x", $x);

        //Observed scalar fields
        $regularObservedValues.put("yMeasured", $yMeasured);

        LinearRegressionTest$SingleThreadCPU core = new LinearRegressionTest$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param x The value to set x to.
	 */
    public LinearRegressionTest(double[][] x) {
        this();
        this.$x.setValue(x);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param x The value to set x to.
	 * @param yMeasured The value to set yMeasured to
	 */
    public LinearRegressionTest(double[][] x, double[] yMeasured) {
        this();
        this.x.setValue(x);
        this.yMeasured.setValue(yMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new LinearRegressionTest$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new LinearRegressionTest$MultiThreadCPU(state, target);
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
        public final double[] yMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param x The value to set x to.
		 * @param yMeasured The value to set yMeasured to.
		 */
        public AllInputs(double[][] x, double[] yMeasured) {
            this.x = x;
            this.yMeasured = yMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double bias;
		/** Field holding the value of tau after a convention execution step. */
        public final double tau;
		/** Field holding the value of weights after a convention execution step. */
        public final double[] weights;
		/** Field holding the value of y after a convention execution step. */
        public final double[] y;

        InferredValueOutputs(LinearRegressionTest system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.tau = system$model.tau.getSamples()[0];
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
		/** Field holding the log probability of computed variable tau */
        public final double tau;
		/** Field holding the log probability of computed variable weights */
        public final double weights;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LinearRegressionTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.tau = system$model.tau.getLogProbability();
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
		/** Field holding the probability of computed variable tau */
        public final double tau;
		/** Field holding the probability of computed variable weights */
        public final double weights;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LinearRegressionTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.tau = system$model.tau.getProbability();
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
		/** Field holding the MAP or Sample value of tau after an infer model call. */
        public final double[] tau;
		/** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(LinearRegressionTest system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.tau = system$model.getInferredValue(system$model.$tau);
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