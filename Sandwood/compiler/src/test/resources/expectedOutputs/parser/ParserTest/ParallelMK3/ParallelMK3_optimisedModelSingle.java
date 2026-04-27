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
 * Class representing the Sandwood model ParallelMK3 This is the class that all user
 * interactions with the model should occur through.
 */
public final class ParallelMK3 extends Model<ParallelMK3.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean constrainedFlag$sample21 = true;
		boolean fixedFlag$sample21 = false;
		boolean fixedProbFlag$sample21 = false;
		boolean fixedProbFlag$sample38 = false;
		double[] generated;
		double[] indirection;
		int length$observed;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$generated;
		double logProbability$indirection;
		double logProbability$sample;
		double logProbability$var38;
		double[] observed;
		double[] sample;
		boolean system$gibbsForward = true;
		double[] v;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for generated
			generated = new double[length$observed];
			
			// Constructor for indirection
			indirection = new double[length$observed];
			
			// Constructor for v
			v = new double[10];
			
			// If sample has not been set already allocate space.
			if(!fixedFlag$sample21)
				// Constructor for sample
				sample = new double[10];
		}

		// Getter for fixedFlag$sample21.
		final boolean get$fixedFlag$sample21() {
			return fixedFlag$sample21;
		}

		// Setter for fixedFlag$sample21.
		final void set$fixedFlag$sample21(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
			// need to be updated.
			fixedFlag$sample21 = cv$value;
			
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			constrainedFlag$sample21 = (cv$value || constrainedFlag$sample21);
			
			// Should the probability of sample 21 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
			
			// Should the probability of sample 38 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
		}

		// Getter for generated.
		final double[] get$generated() {
			return generated;
		}

		// Getter for indirection.
		final double[] get$indirection() {
			return indirection;
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

		// Getter for logProbability$indirection.
		final double get$logProbability$indirection() {
			return logProbability$indirection;
		}

		// Getter for logProbability$sample.
		final double get$logProbability$sample() {
			return logProbability$sample;
		}

		// Getter for observed.
		final double[] get$observed() {
			return observed;
		}

		// Setter for observed.
		final void set$observed(double[] cv$value, boolean allocated$) {
			observed = cv$value;
		}

		// Getter for sample.
		final double[] get$sample() {
			return sample;
		}

		// Setter for sample.
		final void set$sample(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of sample including if probabilities need to
			// be updated.
			sample = cv$value;
			
			// Unset the fixed probability flag for sample 21 as it depends on sample.
			fixedProbFlag$sample21 = false;
			
			// Unset the fixed probability flag for sample 38 as it depends on sample.
			fixedProbFlag$sample38 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}
	}

    private final ComputedDoubleArrayInternal $generated = new ComputedDoubleArrayInternal(this, "generated", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$generated(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable generated because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$generated(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/**
	 * Computed variable representing generated of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray generated = $generated;

    private final ComputedDoubleArrayInternal $indirection = new ComputedDoubleArrayInternal(this, "indirection", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$indirection(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable indirection because its value depends on variable \"sample\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$indirection(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample21(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample21())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing indirection of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray indirection = $indirection;

    private final ComputedDoubleArrayInternal $sample = new ComputedDoubleArrayInternal(this, "sample", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$sample(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$sample(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$sample(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample21(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample21())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing sample of type double[] from the Sandwood model. */
    public final ComputedDoubleArray sample = $sample;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayShapeableInternal $observed = new ObservedDoubleArrayShapeableInternal(this, "observed") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$observed();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
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

	/**
	 * Observed variable representing observed of type double[] from the Sandwood model.
	 */
    public final ObservedDoubleArrayShapeable observed = $observed;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$generated, $indirection, $sample};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public ParallelMK3() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("generated", $generated);
        $computedVariables.put("indirection", $indirection);
        $computedVariables.put("sample", $sample);

        //Observed array fields
        $shapedObservedValues.put("observed", $observed);

        ParallelMK3$SingleThreadCPU core = new ParallelMK3$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param observedShape An integer array describing the shape of variable observed
	 *                      to use in the model when generating results.
	 */
    public ParallelMK3(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observed The value to set observed to.
	 */
    public ParallelMK3(double[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new ParallelMK3$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new ParallelMK3$MultiThreadCPU(state, target);
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
        public final double[] observed;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param observed The value to set observed to.
		 */
        public AllInputs(double[] observed) {
            this.observed = observed;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of generated after a convention execution step. */
        public final double[] generated;
		/** Field holding the value of indirection after a convention execution step. */
        public final double[] indirection;
		/** Field holding the value of sample after a convention execution step. */
        public final double[] sample;

        InferredValueOutputs(ParallelMK3 system$model) {
            this.generated = system$model.generated.getSamples()[0];
            this.indirection = system$model.indirection.getSamples()[0];
            this.sample = system$model.sample.getSamples()[0];
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
		/** Field holding the log probability of computed variable indirection */
        public final double indirection;
		/** Field holding the log probability of computed variable sample */
        public final double sample;

        LogProbabilities(ParallelMK3 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.generated = system$model.generated.getLogProbability();
            this.indirection = system$model.indirection.getLogProbability();
            this.sample = system$model.sample.getLogProbability();
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
		/** Field holding the probability of computed variable indirection */
        public final double indirection;
		/** Field holding the probability of computed variable sample */
        public final double sample;

        Probabilities(ParallelMK3 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.generated = system$model.generated.getProbability();
            this.indirection = system$model.indirection.getProbability();
            this.sample = system$model.sample.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of indirection after an infer model call. */
        public final double[][] indirection;
		/** Field holding the MAP or Sample value of sample after an infer model call. */
        public final double[][] sample;

        InferredModelOutputs(ParallelMK3 system$model) {
            this.indirection = system$model.getInferredValue(system$model.$indirection);
            this.sample = system$model.getInferredValue(system$model.$sample);
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