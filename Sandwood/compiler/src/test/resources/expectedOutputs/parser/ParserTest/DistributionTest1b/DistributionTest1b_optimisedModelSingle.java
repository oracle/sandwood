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
 * Class representing the Sandwood model DistributionTest1b This is the class that
 * all user interactions with the model should occur through.
 */
public final class DistributionTest1b extends Model<DistributionTest1b.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean constrainedFlag$sample4 = true;
		boolean constrainedFlag$sample6 = true;
		boolean constrainedFlag$sample7 = true;
		double[] distribution$sample4;
		double[] distribution$sample6;
		boolean fixedFlag$sample4 = false;
		boolean fixedFlag$sample6 = false;
		boolean fixedFlag$sample7 = false;
		boolean fixedProbFlag$sample13 = false;
		boolean fixedProbFlag$sample4 = false;
		boolean fixedProbFlag$sample6 = false;
		boolean fixedProbFlag$sample7 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$c;
		double logProbability$v;
		double logProbability$v1;
		double logProbability$v2;
		double logProbability$v3;
		boolean system$gibbsForward = true;
		boolean v;
		int v1;
		int v2;
		int v3;
		boolean value;
		double[] weightings;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for distribution$sample4
			distribution$sample4 = new double[weightings.length];
			
			// Constructor for distribution$sample6
			distribution$sample6 = new double[weightings.length];
		}

		// Getter for distribution$sample4.
		final double[] get$distribution$sample4() {
			return distribution$sample4;
		}

		// Setter for distribution$sample4.
		final void set$distribution$sample4(double[] cv$value, boolean allocated$) {
			distribution$sample4 = cv$value;
		}

		// Getter for distribution$sample6.
		final double[] get$distribution$sample6() {
			return distribution$sample6;
		}

		// Setter for distribution$sample6.
		final void set$distribution$sample6(double[] cv$value, boolean allocated$) {
			distribution$sample6 = cv$value;
		}

		// Getter for fixedFlag$sample4.
		final boolean get$fixedFlag$sample4() {
			return fixedFlag$sample4;
		}

		// Setter for fixedFlag$sample4.
		final void set$fixedFlag$sample4(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
			// need to be updated.
			fixedFlag$sample4 = cv$value;
			
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			constrainedFlag$sample4 = (cv$value || constrainedFlag$sample4);
			
			// Should the probability of sample 4 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
			
			// Should the probability of sample 13 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample4" with its value "cv$value".
			fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		}

		// Getter for fixedFlag$sample6.
		final boolean get$fixedFlag$sample6() {
			return fixedFlag$sample6;
		}

		// Setter for fixedFlag$sample6.
		final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample6 including if probabilities
			// need to be updated.
			fixedFlag$sample6 = cv$value;
			
			// Substituted "fixedFlag$sample6" with its value "cv$value".
			constrainedFlag$sample6 = (cv$value || constrainedFlag$sample6);
			
			// Should the probability of sample 6 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample6" with its value "cv$value".
			fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
			
			// Should the probability of sample 13 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample6" with its value "cv$value".
			fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
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
			
			// Should the probability of sample 13 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample7" with its value "cv$value".
			fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
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

		// Getter for logProbability$c.
		final double get$logProbability$c() {
			return logProbability$c;
		}

		// Getter for logProbability$v.
		final double get$logProbability$v() {
			return logProbability$v;
		}

		// Getter for logProbability$v1.
		final double get$logProbability$v1() {
			return logProbability$v1;
		}

		// Getter for logProbability$v2.
		final double get$logProbability$v2() {
			return logProbability$v2;
		}

		// Getter for logProbability$v3.
		final double get$logProbability$v3() {
			return logProbability$v3;
		}

		// Getter for v.
		final boolean get$v() {
			return v;
		}

		// Getter for v1.
		final int get$v1() {
			return v1;
		}

		// Setter for v1.
		final void set$v1(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of v1 including if probabilities need to be
			// updated.
			v1 = cv$value;
			
			// Unset the fixed probability flag for sample 4 as it depends on v1.
			fixedProbFlag$sample4 = false;
			
			// Unset the fixed probability flag for sample 13 as it depends on v1.
			fixedProbFlag$sample13 = false;
		}

		// Getter for v2.
		final int get$v2() {
			return v2;
		}

		// Setter for v2.
		final void set$v2(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of v2 including if probabilities need to be
			// updated.
			v2 = cv$value;
			
			// Unset the fixed probability flag for sample 6 as it depends on v2.
			fixedProbFlag$sample6 = false;
			
			// Unset the fixed probability flag for sample 13 as it depends on v2.
			fixedProbFlag$sample13 = false;
		}

		// Getter for v3.
		final int get$v3() {
			return v3;
		}

		// Setter for v3.
		final void set$v3(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of v3 including if probabilities need to be
			// updated.
			v3 = cv$value;
			
			// Unset the fixed probability flag for sample 7 as it depends on v3.
			fixedProbFlag$sample7 = false;
			
			// Unset the fixed probability flag for sample 13 as it depends on v3.
			fixedProbFlag$sample13 = false;
		}

		// Getter for value.
		final boolean get$value() {
			return value;
		}

		// Setter for value.
		final void set$value(boolean cv$value, boolean allocated$) {
			value = cv$value;
		}

		// Getter for weightings.
		final double[] get$weightings() {
			return weightings;
		}

		// Setter for weightings.
		final void set$weightings(double[] cv$value, boolean allocated$) {
			weightings = cv$value;
		}
	}

    private final ComputedBooleanInternal $v = new ComputedBooleanInternal(this, "v", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean getValue() { return state.get$v(); }

        @Override
        protected void setValueInternal(boolean value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable v because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing v of type boolean from the Sandwood model. */
    public final ComputedBoolean v = $v;

    private final ComputedIntegerInternal $v1 = new ComputedIntegerInternal(this, "v1", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$v1(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$v1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v1(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample4(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample4())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing v1 of type int from the Sandwood model. */
    public final ComputedInteger v1 = $v1;

    private final ComputedIntegerInternal $v2 = new ComputedIntegerInternal(this, "v2", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$v2(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$v2(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample6(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample6())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing v2 of type int from the Sandwood model. */
    public final ComputedInteger v2 = $v2;

    private final ComputedIntegerInternal $v3 = new ComputedIntegerInternal(this, "v3", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$v3(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$v3(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v3(); }

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

	/** Computed variable representing v3 of type int from the Sandwood model. */
    public final ComputedInteger v3 = $v3;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedDoubleArrayInternal $weightings = new ObservedDoubleArrayInternal(this, "weightings") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$weightings();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { state.set$weightings(value, allocated); }
    };

	/**
	 * Observed variable representing weightings of type double[] from the Sandwood model.
	 */
    public final ObservedDoubleArray weightings = $weightings;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanInternal $value = new ObservedBooleanInternal(this, "value") {
        @Override
        public boolean getValue() {
            synchronized(model) {
                return state.get$value();
            }
        }

        @Override
        protected void setValueInternal(boolean value) { state.set$value(value, allocated); }
    };

	/** Observed variable representing value of type boolean from the Sandwood model. */
    public final ObservedBoolean value = $value;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $c = new RandomVariableInternal(this, "c", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$c();
        }
    };

	/** Random variable representing c from the Sandwood model. */
    public final RandomVariable c = $c;

    private HasProbabilityInternal[] $probabilityVariables = {$v, $v1, $v2, $v3, $c};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DistributionTest1b() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("v", $v);
        $computedVariables.put("v1", $v1);
        $computedVariables.put("v2", $v2);
        $computedVariables.put("v3", $v3);

        //ModelInputs
        $modelInputs.put("weightings", $weightings);

        //Observed scalar fields
        $regularObservedValues.put("value", $value);

        DistributionTest1b$SingleThreadCPU core = new DistributionTest1b$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param weightings The value to set weightings to.
	 */
    public DistributionTest1b(double[] weightings) {
        this();
        this.$weightings.setValue(weightings);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param weightings The value to set weightings to.
	 * @param value The value to set value to
	 */
    public DistributionTest1b(double[] weightings, boolean value) {
        this();
        this.weightings.setValue(weightings);
        this.value.setValue(value);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DistributionTest1b$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DistributionTest1b$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input weightings */
        public final double[] weightings;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param weightings The value to set weightings to.
		 */
        public InferValueInputs(double[] weightings) {
            this.weightings = weightings;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input weightings */
        public final double[] weightings;
		/** Field holding the value of model input value */
        public final boolean value;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param weightings The value to set weightings to.
		 * @param value The value to set value to.
		 */
        public AllInputs(double[] weightings, boolean value) {
            this.weightings = weightings;
            this.value = value;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of v after a convention execution step. */
        public final boolean v;
		/** Field holding the value of v1 after a convention execution step. */
        public final int v1;
		/** Field holding the value of v2 after a convention execution step. */
        public final int v2;
		/** Field holding the value of v3 after a convention execution step. */
        public final int v3;

        InferredValueOutputs(DistributionTest1b system$model) {
            this.v = system$model.v.getSamples()[0];
            this.v1 = system$model.v1.getSamples()[0];
            this.v2 = system$model.v2.getSamples()[0];
            this.v3 = system$model.v3.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable c */
        public final double c;
		/** Field holding the log probability of computed variable v */
        public final double v;
		/** Field holding the log probability of computed variable v1 */
        public final double v1;
		/** Field holding the log probability of computed variable v2 */
        public final double v2;
		/** Field holding the log probability of computed variable v3 */
        public final double v3;

        LogProbabilities(DistributionTest1b system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.c = system$model.c.getLogProbability();
            this.v = system$model.v.getLogProbability();
            this.v1 = system$model.v1.getLogProbability();
            this.v2 = system$model.v2.getLogProbability();
            this.v3 = system$model.v3.getLogProbability();
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
		/** Field holding the probability of random variable c */
        public final double c;
		/** Field holding the probability of computed variable v */
        public final double v;
		/** Field holding the probability of computed variable v1 */
        public final double v1;
		/** Field holding the probability of computed variable v2 */
        public final double v2;
		/** Field holding the probability of computed variable v3 */
        public final double v3;

        Probabilities(DistributionTest1b system$model) {
            this.$modelProbability = system$model.getProbability();
            this.c = system$model.c.getProbability();
            this.v = system$model.v.getProbability();
            this.v1 = system$model.v1.getProbability();
            this.v2 = system$model.v2.getProbability();
            this.v3 = system$model.v3.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of v1 after an infer model call. */
        public final int[] v1;
		/** Field holding the MAP or Sample value of v2 after an infer model call. */
        public final int[] v2;
		/** Field holding the MAP or Sample value of v3 after an infer model call. */
        public final int[] v3;

        InferredModelOutputs(DistributionTest1b system$model) {
            this.v1 = system$model.getInferredValue(system$model.$v1);
            this.v2 = system$model.getInferredValue(system$model.$v2);
            this.v3 = system$model.getInferredValue(system$model.$v3);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.weightings.setValue(inputs.weightings);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
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
        this.weightings.setValue(inputs.weightings);
        this.$value.setValue(inputs.value);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}