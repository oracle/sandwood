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
 * Class representing the Sandwood model LinearRegressionBasic2 This is the class
 * that all user interactions with the model should occur through.
 */
public final class LinearRegressionBasic2 extends Model<LinearRegressionBasic2.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double b0;
		double b1;
		boolean constrainedFlag$sample11 = true;
		boolean constrainedFlag$sample16 = true;
		boolean constrainedFlag$sample7 = true;
		boolean fixedFlag$sample11 = false;
		boolean fixedFlag$sample16 = false;
		boolean fixedFlag$sample7 = false;
		boolean fixedProbFlag$sample11 = false;
		boolean fixedProbFlag$sample16 = false;
		boolean fixedProbFlag$sample33 = false;
		boolean fixedProbFlag$sample7 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$b0;
		double logProbability$b1;
		double logProbability$var16;
		double logProbability$var33;
		double logProbability$variance;
		double logProbability$y;
		int noSamples;
		boolean system$gibbsForward = true;
		double variance;
		double[] x;
		double[] y;
		double[] yMeasured;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for y
			y = new double[x.length];
		}

		// Getter for b0.
		final double get$b0() {
			return b0;
		}

		// Setter for b0.
		final void set$b0(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of b0 including if probabilities need to be
			// updated.
			b0 = cv$value;
			
			// Unset the fixed probability flag for sample 7 as it depends on b0.
			fixedProbFlag$sample7 = false;
			
			// Unset the fixed probability flag for sample 33 as it depends on b0.
			fixedProbFlag$sample33 = false;
		}

		// Getter for b1.
		final double get$b1() {
			return b1;
		}

		// Setter for b1.
		final void set$b1(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of b1 including if probabilities need to be
			// updated.
			b1 = cv$value;
			
			// Unset the fixed probability flag for sample 11 as it depends on b1.
			fixedProbFlag$sample11 = false;
			
			// Unset the fixed probability flag for sample 33 as it depends on b1.
			fixedProbFlag$sample33 = false;
		}

		// Getter for fixedFlag$sample11.
		final boolean get$fixedFlag$sample11() {
			return fixedFlag$sample11;
		}

		// Setter for fixedFlag$sample11.
		final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
			// need to be updated.
			fixedFlag$sample11 = cv$value;
			
			// Substituted "fixedFlag$sample11" with its value "cv$value".
			constrainedFlag$sample11 = (cv$value || constrainedFlag$sample11);
			
			// Should the probability of sample 11 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample11" with its value "cv$value".
			fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
			
			// Should the probability of sample 33 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample11" with its value "cv$value".
			fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
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
			
			// Should the probability of sample 33 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample16" with its value "cv$value".
			fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
		}

		// Getter for fixedFlag$sample7.
		final boolean get$fixedFlag$sample7() {
			return fixedFlag$sample7;
		}

		// Setter for fixedFlag$sample7.
		final void set$fixedFlag$sample7(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample7 including if probabilities
			// need to be updated.
			fixedFlag$sample7 = cv$value;
			
			// Substituted "fixedFlag$sample7" with its value "cv$value".
			constrainedFlag$sample7 = (cv$value || constrainedFlag$sample7);
			
			// Should the probability of sample 7 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample7" with its value "cv$value".
			fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
			
			// Should the probability of sample 33 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample7" with its value "cv$value".
			fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
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

		// Getter for logProbability$b0.
		final double get$logProbability$b0() {
			return logProbability$b0;
		}

		// Getter for logProbability$b1.
		final double get$logProbability$b1() {
			return logProbability$b1;
		}

		// Getter for logProbability$variance.
		final double get$logProbability$variance() {
			return logProbability$variance;
		}

		// Getter for logProbability$y.
		final double get$logProbability$y() {
			return logProbability$y;
		}

		// Getter for noSamples.
		final int get$noSamples() {
			return noSamples;
		}

		// Getter for variance.
		final double get$variance() {
			return variance;
		}

		// Setter for variance.
		final void set$variance(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of variance including if probabilities need
			// to be updated.
			variance = cv$value;
			
			// Unset the fixed probability flag for sample 16 as it depends on variance.
			fixedProbFlag$sample16 = false;
			
			// Unset the fixed probability flag for sample 33 as it depends on variance.
			fixedProbFlag$sample33 = false;
		}

		// Getter for x.
		final double[] get$x() {
			return x;
		}

		// Setter for x.
		final void set$x(double[] cv$value, boolean allocated$) {
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

    private final ComputedDoubleInternal $b0 = new ComputedDoubleInternal(this, "b0", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$b0(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$b0(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$b0(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample7(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample7())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing b0 of type double from the Sandwood model. */
    public final ComputedDouble b0 = $b0;

    private final ComputedDoubleInternal $b1 = new ComputedDoubleInternal(this, "b1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$b1(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$b1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$b1(); }

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

	/** Computed variable representing b1 of type double from the Sandwood model. */
    public final ComputedDouble b1 = $b1;

    private final ComputedDoubleInternal $variance = new ComputedDoubleInternal(this, "variance", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$variance(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$variance(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$variance(); }

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

	/** Computed variable representing variance of type double from the Sandwood model. */
    public final ComputedDouble variance = $variance;

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

    private final ObservedDoubleArrayInternal $x = new ObservedDoubleArrayInternal(this, "x") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$x();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { state.set$x(value, allocated); }
    };

	/** Observed variable representing x of type double[] from the Sandwood model. */
    public final ObservedDoubleArray x = $x;

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
    private HasProbabilityInternal[] $probabilityVariables = {$b0, $b1, $variance, $y};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public LinearRegressionBasic2() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("b0", $b0);
        $computedVariables.put("b1", $b1);
        $computedVariables.put("variance", $variance);
        $computedVariables.put("y", $y);

        //ModelInputs
        $modelInputs.put("x", $x);

        //Observed scalar fields
        $regularObservedValues.put("yMeasured", $yMeasured);

        LinearRegressionBasic2$SingleThreadCPU core = new LinearRegressionBasic2$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param x The value to set x to.
	 */
    public LinearRegressionBasic2(double[] x) {
        this();
        this.$x.setValue(x);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param x The value to set x to.
	 * @param yMeasured The value to set yMeasured to
	 */
    public LinearRegressionBasic2(double[] x, double[] yMeasured) {
        this();
        this.x.setValue(x);
        this.yMeasured.setValue(yMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new LinearRegressionBasic2$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new LinearRegressionBasic2$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input x */
        public final double[] x;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param x The value to set x to.
		 */
        public InferValueInputs(double[] x) {
            this.x = x;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input x */
        public final double[] x;
		/** Field holding the value of model input yMeasured */
        public final double[] yMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param x The value to set x to.
		 * @param yMeasured The value to set yMeasured to.
		 */
        public AllInputs(double[] x, double[] yMeasured) {
            this.x = x;
            this.yMeasured = yMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of b0 after a convention execution step. */
        public final double b0;
		/** Field holding the value of b1 after a convention execution step. */
        public final double b1;
		/** Field holding the value of variance after a convention execution step. */
        public final double variance;
		/** Field holding the value of y after a convention execution step. */
        public final double[] y;

        InferredValueOutputs(LinearRegressionBasic2 system$model) {
            this.b0 = system$model.b0.getSamples()[0];
            this.b1 = system$model.b1.getSamples()[0];
            this.variance = system$model.variance.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable b0 */
        public final double b0;
		/** Field holding the log probability of computed variable b1 */
        public final double b1;
		/** Field holding the log probability of computed variable variance */
        public final double variance;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LinearRegressionBasic2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b0 = system$model.b0.getLogProbability();
            this.b1 = system$model.b1.getLogProbability();
            this.variance = system$model.variance.getLogProbability();
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
		/** Field holding the probability of computed variable b0 */
        public final double b0;
		/** Field holding the probability of computed variable b1 */
        public final double b1;
		/** Field holding the probability of computed variable variance */
        public final double variance;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LinearRegressionBasic2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b0 = system$model.b0.getProbability();
            this.b1 = system$model.b1.getProbability();
            this.variance = system$model.variance.getProbability();
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
		/** Field holding the MAP or Sample value of b0 after an infer model call. */
        public final double[] b0;
		/** Field holding the MAP or Sample value of b1 after an infer model call. */
        public final double[] b1;
		/** Field holding the MAP or Sample value of variance after an infer model call. */
        public final double[] variance;

        InferredModelOutputs(LinearRegressionBasic2 system$model) {
            this.b0 = system$model.getInferredValue(system$model.$b0);
            this.b1 = system$model.getInferredValue(system$model.$b1);
            this.variance = system$model.getInferredValue(system$model.$variance);
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