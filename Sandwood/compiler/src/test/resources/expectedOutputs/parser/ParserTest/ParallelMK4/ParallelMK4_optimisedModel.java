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
 * Class representing the Sandwood model ParallelMK4 This is the class that all user
 * interactions with the model should occur through.
 */
public final class ParallelMK4 extends Model<ParallelMK4.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean[][] constrainedFlag$sample61;
		boolean fixedFlag$sample61 = false;
		boolean fixedProbFlag$sample103 = false;
		boolean fixedProbFlag$sample61 = false;
		int[] generated;
		double[][] indirection1;
		double[][] indirection2;
		int length$observed;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$generated;
		double logProbability$indirection1;
		double logProbability$indirection2;
		double[] logProbability$sample103;
		double[][] logProbability$sample61;
		int[] observed;
		boolean system$gibbsForward = true;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for generated
			generated = new int[length$observed];
			
			// If indirection1 has not been set already allocate space.
			if(!fixedFlag$sample61) {
				// Constructor for indirection1
				indirection1 = new double[length$observed][];
				for(int var16 = 0; var16 < length$observed; var16 += 1)
					indirection1[var16] = new double[10];
			}
			
			// Constructor for indirection2
			indirection2 = new double[length$observed][];
			for(int var31 = 0; var31 < length$observed; var31 += 1)
				indirection2[var31] = new double[10];
			
			// Constructor for constrainedFlag$sample61
			constrainedFlag$sample61 = new boolean[length$observed][];
			for(int i = 0; i < length$observed; i += 1)
				constrainedFlag$sample61[i] = new boolean[10];
			
			// Constructor for logProbability$sample61
			logProbability$sample61 = new double[length$observed][];
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample61[i] = new double[10];
			
			// Constructor for logProbability$sample103
			logProbability$sample103 = new double[length$observed];
		}

		// Getter for fixedFlag$sample61.
		final boolean get$fixedFlag$sample61() {
			return fixedFlag$sample61;
		}

		// Setter for fixedFlag$sample61.
		final void set$fixedFlag$sample61(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
			// need to be updated.
			fixedFlag$sample61 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample61$1 = 0; index$constrainedFlag$sample61$1 < constrainedFlag$sample61.length; index$constrainedFlag$sample61$1 += 1) {
					boolean[] cv$constrainedFlag$sample61$1 = constrainedFlag$sample61[index$constrainedFlag$sample61$1];
					for(int index$constrainedFlag$sample61$2 = 0; index$constrainedFlag$sample61$2 < cv$constrainedFlag$sample61$1.length; index$constrainedFlag$sample61$2 += 1)
						cv$constrainedFlag$sample61$1[index$constrainedFlag$sample61$2] = true;
				}
			}
			
			// Should the probability of sample 61 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample61" with its value "cv$value".
			fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
			
			// Should the probability of sample 103 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample61" with its value "cv$value".
			fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
		}

		// Getter for generated.
		final int[] get$generated() {
			return generated;
		}

		// Getter for indirection1.
		final double[][] get$indirection1() {
			return indirection1;
		}

		// Setter for indirection1.
		final void set$indirection1(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of indirection1 including if probabilities need
			// to be updated.
			indirection1 = cv$value;
			
			// Unset the fixed probability flag for sample 61 as it depends on indirection1.
			fixedProbFlag$sample61 = false;
			
			// Unset the fixed probability flag for sample 103 as it depends on indirection1.
			fixedProbFlag$sample103 = false;
		}

		// Getter for indirection2.
		final double[][] get$indirection2() {
			return indirection2;
		}

		// Getter for length$observed.
		final int get$length$observed() {
			return length$observed;
		}

		// Setter for length$observed.
		final void set$length$observed(int cv$value, boolean allocated$) {
			length$observed = cv$value;
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

		// Getter for logProbability$generated.
		final double get$logProbability$generated() {
			return logProbability$generated;
		}

		// Getter for logProbability$indirection1.
		final double get$logProbability$indirection1() {
			return logProbability$indirection1;
		}

		// Getter for logProbability$indirection2.
		final double get$logProbability$indirection2() {
			return logProbability$indirection2;
		}

		// Getter for observed.
		final int[] get$observed() {
			return observed;
		}

		// Setter for observed.
		final void set$observed(int[] cv$value, boolean allocated$) {
			observed = cv$value;
		}
	}

    private final ComputedIntegerArrayInternal $generated = new ComputedIntegerArrayInternal(this, "generated", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$generated(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable generated because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$generated(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing generated of type int[] from the Sandwood model. */
    public final ComputedIntegerArray generated = $generated;

    private final ComputedObjectArrayInternal<double[]> $indirection1 = new ComputedObjectArrayInternal<double[]>(this, "indirection1", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$indirection1(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$indirection1(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$indirection1(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample61(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample61())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing indirection1 of type double[][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[]> indirection1 = $indirection1;

    private final ComputedObjectArrayInternal<double[]> $indirection2 = new ComputedObjectArrayInternal<double[]>(this, "indirection2", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$indirection2(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable indirection2 because its value depends on variable \"indirection1\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$indirection2(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample61(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample61())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing indirection2 of type double[][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[]> indirection2 = $indirection2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayShapeableInternal $observed = new ObservedIntegerArrayShapeableInternal(this, "observed") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return state.get$observed();
            }
        }

        @Override
        public void setValueInternal(int[] value) {
            state.set$observed(value, allocated);
            state.set$length$observed(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$observed(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$observed();
        }
    };

	/** Observed variable representing observed of type int[] from the Sandwood model. */
    public final ObservedIntegerArrayShapeable observed = $observed;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$generated, $indirection1, $indirection2};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public ParallelMK4() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("generated", $generated);
        $computedVariables.put("indirection1", $indirection1);
        $computedVariables.put("indirection2", $indirection2);

        //Observed array fields
        $shapedObservedValues.put("observed", $observed);

        ParallelMK4$SingleThreadCPU core = new ParallelMK4$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param observedShape An integer array describing the shape of variable observed
	 *                      to use in the model when generating results.
	 */
    public ParallelMK4(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observed The value to set observed to.
	 */
    public ParallelMK4(int[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new ParallelMK4$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new ParallelMK4$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input observed */
        public final int observedShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param observedShape An integer array describing the shape of variable observed
		 *                      to use in the model when generating results.
		 */
        public InferValueInputs(int observedShape) {
            this.observedShape = observedShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input observed */
        public final int[] observed;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param observed The value to set observed to.
		 */
        public AllInputs(int[] observed) {
            this.observed = observed;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of generated after a convention execution step. */
        public final int[] generated;
		/** Field holding the value of indirection1 after a convention execution step. */
        public final double[][] indirection1;
		/** Field holding the value of indirection2 after a convention execution step. */
        public final double[][] indirection2;

        InferredValueOutputs(ParallelMK4 system$model) {
            this.generated = system$model.generated.getSamples()[0];
            this.indirection1 = system$model.indirection1.getSamples()[0];
            this.indirection2 = system$model.indirection2.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable generated */
        public final double generated;
		/** Field holding the log probability of computed variable indirection1 */
        public final double indirection1;
		/** Field holding the log probability of computed variable indirection2 */
        public final double indirection2;

        LogProbabilities(ParallelMK4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.generated = system$model.generated.getLogProbability();
            this.indirection1 = system$model.indirection1.getLogProbability();
            this.indirection2 = system$model.indirection2.getLogProbability();
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
		/** Field holding the probability of computed variable generated */
        public final double generated;
		/** Field holding the probability of computed variable indirection1 */
        public final double indirection1;
		/** Field holding the probability of computed variable indirection2 */
        public final double indirection2;

        Probabilities(ParallelMK4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.generated = system$model.generated.getProbability();
            this.indirection1 = system$model.indirection1.getProbability();
            this.indirection2 = system$model.indirection2.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/**
		 * Field holding the MAP or Sample value of indirection1 after an infer model call.
		 */
        public final double[][][] indirection1;
		/**
		 * Field holding the MAP or Sample value of indirection2 after an infer model call.
		 */
        public final double[][][] indirection2;

        InferredModelOutputs(ParallelMK4 system$model) {
            this.indirection1 = system$model.getInferredValue(system$model.$indirection1);
            this.indirection2 = system$model.getInferredValue(system$model.$indirection2);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$observed.setShape(inputs.observedShape);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
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
        this.$observed.setValue(inputs.observed);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}