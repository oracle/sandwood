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
 * Class representing the Sandwood model DistributionTest5 This is the class that
 * all user interactions with the model should occur through.
 */
public final class DistributionTest5 extends Model<DistributionTest5.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean constrainedFlag$sample11 = true;
		boolean[] constrainedFlag$sample27;
		boolean constrainedFlag$sample5 = true;
		double[] distribution$sample11;
		double[][] distribution$sample27;
		double[] distribution$sample5;
		boolean fixedFlag$sample11 = false;
		boolean fixedFlag$sample27 = false;
		boolean fixedFlag$sample5 = false;
		boolean fixedProbFlag$sample11 = false;
		boolean fixedProbFlag$sample27 = false;
		boolean fixedProbFlag$sample5 = false;
		boolean fixedProbFlag$sample70 = false;
		int length$value;
		double logProbability$$evidence;
		double logProbability$$model;
		double[] logProbability$sample27;
		double[] logProbability$sample70;
		double logProbability$v;
		double logProbability$v1;
		double logProbability$v2;
		double logProbability$v3;
		double logProbability$var11;
		int size;
		boolean system$gibbsForward = true;
		boolean[] v;
		int v1;
		int[] v2;
		int[] v3;
		boolean[] value;
		double[] weightings;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// If v2 has not been set already allocate space.
			if((!fixedFlag$sample11 || !fixedFlag$sample27)) {
				// Constructor for v2
				{
					v2 = new int[(length$value + 1)];
				}
			}
			
			// Constructor for v3
			{
				v3 = new int[(length$value + 1)];
			}
			
			// Constructor for v
			{
				v = new boolean[length$value];
			}
			
			// Constructor for distribution$sample5
			{
				distribution$sample5 = new double[weightings.length];
			}
			
			// Constructor for distribution$sample11
			{
				distribution$sample11 = new double[weightings.length];
			}
			
			// Constructor for distribution$sample27
			{
				distribution$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)][];
				for(int i = 0; i < length$value; i += 1)
					distribution$sample27[((i - 0) / 1)] = new double[weightings.length];
			}
			
			// Constructor for constrainedFlag$sample27
			{
				constrainedFlag$sample27 = new boolean[((((length$value - 1) - 0) / 1) + 1)];
			}
			
			// Constructor for logProbability$sample27
			{
				logProbability$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)];
			}
			
			// Constructor for logProbability$sample70
			{
				logProbability$sample70 = new double[((((length$value - 1) - 0) / 1) + 1)];
			}
		}

		// Getter for distribution$sample11.
		final double[] get$distribution$sample11() {
			return distribution$sample11;
		}

		// Setter for distribution$sample11.
		final void set$distribution$sample11(double[] cv$value, boolean allocated$) {
			distribution$sample11 = cv$value;
		}

		// Getter for distribution$sample27.
		final double[][] get$distribution$sample27() {
			return distribution$sample27;
		}

		// Setter for distribution$sample27.
		final void set$distribution$sample27(double[][] cv$value, boolean allocated$) {
			distribution$sample27 = cv$value;
		}

		// Getter for distribution$sample5.
		final double[] get$distribution$sample5() {
			return distribution$sample5;
		}

		// Setter for distribution$sample5.
		final void set$distribution$sample5(double[] cv$value, boolean allocated$) {
			distribution$sample5 = cv$value;
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
			constrainedFlag$sample11 = (fixedFlag$sample11 || constrainedFlag$sample11);
			
			// Should the probability of sample 11 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
			
			// Should the probability of sample 70 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample70 = (fixedFlag$sample11 && fixedProbFlag$sample70);
		}

		// Getter for fixedFlag$sample27.
		final boolean get$fixedFlag$sample27() {
			return fixedFlag$sample27;
		}

		// Setter for fixedFlag$sample27.
		final void set$fixedFlag$sample27(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample27 including if probabilities
			// need to be updated.
			fixedFlag$sample27 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
					constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
			}
			
			// Should the probability of sample 27 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
			
			// Should the probability of sample 70 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample70 = (fixedFlag$sample27 && fixedProbFlag$sample70);
		}

		// Getter for fixedFlag$sample5.
		final boolean get$fixedFlag$sample5() {
			return fixedFlag$sample5;
		}

		// Setter for fixedFlag$sample5.
		final void set$fixedFlag$sample5(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample5 including if probabilities
			// need to be updated.
			fixedFlag$sample5 = cv$value;
			constrainedFlag$sample5 = (fixedFlag$sample5 || constrainedFlag$sample5);
			
			// Should the probability of sample 5 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
			
			// Should the probability of sample 70 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample70 = (fixedFlag$sample5 && fixedProbFlag$sample70);
		}

		// Getter for length$value.
		final int get$length$value() {
			return length$value;
		}

		// Setter for length$value.
		final void set$length$value(int cv$value, boolean allocated$) {
			length$value = cv$value;
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

		// Getter for size.
		final int get$size() {
			return size;
		}

		// Getter for v.
		final boolean[] get$v() {
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
			
			// Unset the fixed probability flag for sample 5 as it depends on v1.
			fixedProbFlag$sample5 = false;
			
			// Unset the fixed probability flag for sample 70 as it depends on v1.
			fixedProbFlag$sample70 = false;
		}

		// Getter for v2.
		final int[] get$v2() {
			return v2;
		}

		// Setter for v2.
		final void set$v2(int[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of v2 including if probabilities need to be
			// updated.
			v2 = cv$value;
			
			// Unset the fixed probability flag for sample 11 as it depends on v2.
			fixedProbFlag$sample11 = false;
			
			// Unset the fixed probability flag for sample 27 as it depends on v2.
			fixedProbFlag$sample27 = false;
			
			// Unset the fixed probability flag for sample 70 as it depends on v2.
			fixedProbFlag$sample70 = false;
		}

		// Getter for v3.
		final int[] get$v3() {
			return v3;
		}

		// Getter for value.
		final boolean[] get$value() {
			return value;
		}

		// Setter for value.
		final void set$value(boolean[] cv$value, boolean allocated$) {
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

    private final ComputedBooleanArrayInternal $v = new ComputedBooleanArrayInternal(this, "v", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$v(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

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

	/** Computed variable representing v of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray v = $v;

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
                state.set$fixedFlag$sample5(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample5())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing v1 of type int from the Sandwood model. */
    public final ComputedInteger v1 = $v1;

    private final ComputedIntegerArrayInternal $v2 = new ComputedIntegerArrayInternal(this, "v2", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$v2(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$v2(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample11(fixed, allocated);
                state.set$fixedFlag$sample27(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample11 = state.get$fixedFlag$sample11();
            boolean fixedFlag$sample27 = state.get$fixedFlag$sample27();
            if(fixedFlag$sample11 && fixedFlag$sample27)
                return Immutability.FIXED;
            else if(fixedFlag$sample11 || fixedFlag$sample27)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing v2 of type int[] from the Sandwood model. */
    public final ComputedIntegerArray v2 = $v2;

    private final ComputedIntegerArrayInternal $v3 = new ComputedIntegerArrayInternal(this, "v3", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$v3(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable v3 because its value depends on variable \"v2\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$v3(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample11(fixed, allocated);
                state.set$fixedFlag$sample27(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample11 = state.get$fixedFlag$sample11();
            boolean fixedFlag$sample27 = state.get$fixedFlag$sample27();
            if(fixedFlag$sample11 && fixedFlag$sample27)
                return Immutability.FIXED;
            else if(fixedFlag$sample11 || fixedFlag$sample27)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing v3 of type int[] from the Sandwood model. */
    public final ComputedIntegerArray v3 = $v3;

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

    private final ObservedBooleanArrayShapeableInternal $value = new ObservedBooleanArrayShapeableInternal(this, "value") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$value();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$value(value, allocated);
            state.set$length$value(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$value(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$value();
        }
    };

	/** Observed variable representing value of type boolean[] from the Sandwood model. */
    public final ObservedBooleanArrayShapeable value = $value;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$v, $v1, $v2, $v3};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DistributionTest5() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("v", $v);
        $computedVariables.put("v1", $v1);
        $computedVariables.put("v2", $v2);
        $computedVariables.put("v3", $v3);

        //ModelInputs
        $modelInputs.put("weightings", $weightings);

        //Observed array fields
        $shapedObservedValues.put("value", $value);

        DistributionTest5$SingleThreadCPU core = new DistributionTest5$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param weightings The value to set weightings to.
	 * @param valueShape An integer array describing the shape of variable value to use
	 *                   in the model when generating results.
	 */
    public DistributionTest5(double[] weightings, int valueShape) {
        this();
        this.$weightings.setValue(weightings);
        this.$value.setShape(valueShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param weightings The value to set weightings to.
	 * @param value The value to set value to
	 */
    public DistributionTest5(double[] weightings, boolean[] value) {
        this();
        this.weightings.setValue(weightings);
        this.value.setValue(value);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DistributionTest5$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DistributionTest5$MultiThreadCPU(state, target);
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
		/** Field holding the shape of model input value */
        public final int valueShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param weightings The value to set weightings to.
		 * @param valueShape An integer array describing the shape of variable value to use
		 *                   in the model when generating results.
		 */
        public InferValueInputs(double[] weightings, int valueShape) {
            this.weightings = weightings;
            this.valueShape = valueShape;
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
        public final boolean[] value;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param weightings The value to set weightings to.
		 * @param value The value to set value to.
		 */
        public AllInputs(double[] weightings, boolean[] value) {
            this.weightings = weightings;
            this.value = value;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of v after a convention execution step. */
        public final boolean[] v;
		/** Field holding the value of v1 after a convention execution step. */
        public final int v1;
		/** Field holding the value of v2 after a convention execution step. */
        public final int[] v2;
		/** Field holding the value of v3 after a convention execution step. */
        public final int[] v3;

        InferredValueOutputs(DistributionTest5 system$model) {
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
		/** Field holding the log probability of computed variable v */
        public final double v;
		/** Field holding the log probability of computed variable v1 */
        public final double v1;
		/** Field holding the log probability of computed variable v2 */
        public final double v2;
		/** Field holding the log probability of computed variable v3 */
        public final double v3;

        LogProbabilities(DistributionTest5 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
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
		/** Field holding the probability of computed variable v */
        public final double v;
		/** Field holding the probability of computed variable v1 */
        public final double v1;
		/** Field holding the probability of computed variable v2 */
        public final double v2;
		/** Field holding the probability of computed variable v3 */
        public final double v3;

        Probabilities(DistributionTest5 system$model) {
            this.$modelProbability = system$model.getProbability();
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
        public final int[][] v2;
		/** Field holding the MAP or Sample value of v3 after an infer model call. */
        public final int[][] v3;

        InferredModelOutputs(DistributionTest5 system$model) {
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
        this.$value.setShape(inputs.valueShape);
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