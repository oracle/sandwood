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
 * Class representing the Sandwood model DirichletBernoulli This is the class that
 * all user interactions with the model should occur through.
 */
public final class DirichletBernoulli extends Model<DirichletBernoulli.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		boolean constrainedFlag$sample17 = true;
		boolean fixedFlag$sample17 = false;
		boolean fixedProbFlag$sample17 = false;
		boolean fixedProbFlag$sample38 = false;
		boolean fixedProbFlag$sample51 = false;
		int length;
		int length$observed;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$b1;
		double logProbability$b2;
		double logProbability$output;
		double logProbability$prior;
		double logProbability$var38;
		double logProbability$var51;
		boolean[] observed;
		boolean[] output;
		double[] prior;
		boolean system$gibbsForward = true;
		double[] v;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			{
				v = new double[2];
			}
			
			// If prior has not been set already allocate space.
			if(!fixedFlag$sample17) {
				// Constructor for prior
				{
					prior = new double[2];
				}
			}
			
			// Constructor for output
			{
				output = new boolean[length$observed];
			}
		}

		// Getter for fixedFlag$sample17.
		final boolean get$fixedFlag$sample17() {
			return fixedFlag$sample17;
		}

		// Setter for fixedFlag$sample17.
		final void set$fixedFlag$sample17(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
			// need to be updated.
			fixedFlag$sample17 = cv$value;
			constrainedFlag$sample17 = (fixedFlag$sample17 || constrainedFlag$sample17);
			
			// Should the probability of sample 17 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
			
			// Should the probability of sample 38 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample38 = (fixedFlag$sample17 && fixedProbFlag$sample38);
			
			// Should the probability of sample 51 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample51 = (fixedFlag$sample17 && fixedProbFlag$sample51);
		}

		// Getter for length.
		final int get$length() {
			return length;
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

		// Getter for logProbability$b1.
		final double get$logProbability$b1() {
			return logProbability$b1;
		}

		// Getter for logProbability$b2.
		final double get$logProbability$b2() {
			return logProbability$b2;
		}

		// Getter for logProbability$output.
		final double get$logProbability$output() {
			return logProbability$output;
		}

		// Getter for logProbability$prior.
		final double get$logProbability$prior() {
			return logProbability$prior;
		}

		// Getter for observed.
		final boolean[] get$observed() {
			return observed;
		}

		// Setter for observed.
		final void set$observed(boolean[] cv$value, boolean allocated$) {
			observed = cv$value;
		}

		// Getter for output.
		final boolean[] get$output() {
			return output;
		}

		// Getter for prior.
		final double[] get$prior() {
			return prior;
		}

		// Setter for prior.
		final void set$prior(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of prior including if probabilities need to
			// be updated.
			prior = cv$value;
			
			// Unset the fixed probability flag for sample 17 as it depends on prior.
			fixedProbFlag$sample17 = false;
			
			// Unset the fixed probability flag for sample 38 as it depends on prior.
			fixedProbFlag$sample38 = false;
			
			// Unset the fixed probability flag for sample 51 as it depends on prior.
			fixedProbFlag$sample51 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}
	}

    private final ComputedBooleanArrayInternal $output = new ComputedBooleanArrayInternal(this, "output", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$output(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable output because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$output(); }

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
	 * Computed variable representing output of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray output = $output;

    private final ComputedDoubleArrayInternal $prior = new ComputedDoubleArrayInternal(this, "prior", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$prior(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$prior(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$prior(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample17(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample17())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing prior of type double[] from the Sandwood model. */
    public final ComputedDoubleArray prior = $prior;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $observed = new ObservedBooleanArrayShapeableInternal(this, "observed") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$observed();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
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
	 * Observed variable representing observed of type boolean[] from the Sandwood model.
	 */
    public final ObservedBooleanArrayShapeable observed = $observed;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $b1 = new RandomVariableInternal(this, "b1", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$b1();
        }
    };

	/** Random variable representing b1 from the Sandwood model. */
    public final RandomVariable b1 = $b1;

    private final RandomVariableInternal $b2 = new RandomVariableInternal(this, "b2", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$b2();
        }
    };

	/** Random variable representing b2 from the Sandwood model. */
    public final RandomVariable b2 = $b2;

    private HasProbabilityInternal[] $probabilityVariables = {$output, $prior, $b1, $b2};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DirichletBernoulli() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("output", $output);
        $computedVariables.put("prior", $prior);

        //Observed array fields
        $shapedObservedValues.put("observed", $observed);

        DirichletBernoulli$SingleThreadCPU core = new DirichletBernoulli$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param observedShape An integer array describing the shape of variable observed
	 *                      to use in the model when generating results.
	 */
    public DirichletBernoulli(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observed The value to set observed to.
	 */
    public DirichletBernoulli(boolean[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DirichletBernoulli$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DirichletBernoulli$MultiThreadCPU(state, target);
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
        public final boolean[] observed;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param observed The value to set observed to.
		 */
        public AllInputs(boolean[] observed) {
            this.observed = observed;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of output after a convention execution step. */
        public final boolean[] output;
		/** Field holding the value of prior after a convention execution step. */
        public final double[] prior;

        InferredValueOutputs(DirichletBernoulli system$model) {
            this.output = system$model.output.getSamples()[0];
            this.prior = system$model.prior.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable b1 */
        public final double b1;
		/** Field holding the log probability of random variable b2 */
        public final double b2;
		/** Field holding the log probability of computed variable output */
        public final double output;
		/** Field holding the log probability of computed variable prior */
        public final double prior;

        LogProbabilities(DirichletBernoulli system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b1 = system$model.b1.getLogProbability();
            this.b2 = system$model.b2.getLogProbability();
            this.output = system$model.output.getLogProbability();
            this.prior = system$model.prior.getLogProbability();
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
		/** Field holding the probability of random variable b1 */
        public final double b1;
		/** Field holding the probability of random variable b2 */
        public final double b2;
		/** Field holding the probability of computed variable output */
        public final double output;
		/** Field holding the probability of computed variable prior */
        public final double prior;

        Probabilities(DirichletBernoulli system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b1 = system$model.b1.getProbability();
            this.b2 = system$model.b2.getProbability();
            this.output = system$model.output.getProbability();
            this.prior = system$model.prior.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of prior after an infer model call. */
        public final double[][] prior;

        InferredModelOutputs(DirichletBernoulli system$model) {
            this.prior = system$model.getInferredValue(system$model.$prior);
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