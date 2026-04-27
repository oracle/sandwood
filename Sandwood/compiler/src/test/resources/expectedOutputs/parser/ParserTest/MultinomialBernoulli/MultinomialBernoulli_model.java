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
 * Class representing the Sandwood model MultinomialBernoulli This is the class that
 * all user interactions with the model should occur through.
 */
public final class MultinomialBernoulli extends Model<MultinomialBernoulli.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] beta;
		boolean constrainedFlag$sample17 = true;
		boolean constrainedFlag$sample20 = true;
		boolean fixedFlag$sample17 = false;
		boolean fixedFlag$sample20 = false;
		boolean fixedProbFlag$sample17 = false;
		boolean fixedProbFlag$sample20 = false;
		boolean fixedProbFlag$sample48 = false;
		boolean fixedProbFlag$sample60 = false;
		boolean fixedProbFlag$sample72 = false;
		int length;
		int length$observed;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$b1;
		double logProbability$b2;
		double logProbability$b3;
		double logProbability$output;
		double logProbability$p;
		double logProbability$prior;
		double logProbability$var48;
		double logProbability$var60;
		double logProbability$var72;
		int n;
		boolean[] observed;
		boolean[] output;
		double[] p;
		int[] prior;
		boolean system$gibbsForward = true;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for beta
			{
				beta = new double[3];
			}
			
			// If p has not been set already allocate space.
			if(!fixedFlag$sample17) {
				// Constructor for p
				{
					p = new double[3];
				}
			}
			
			// If prior has not been set already allocate space.
			if(!fixedFlag$sample20) {
				// Constructor for prior
				{
					prior = new int[3];
				}
			}
			
			// Constructor for output
			{
				output = new boolean[length$observed];
			}
		}

		// Getter for beta.
		final double[] get$beta() {
			return beta;
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
			
			// Should the probability of sample 20 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample20 = (fixedFlag$sample17 && fixedProbFlag$sample20);
		}

		// Getter for fixedFlag$sample20.
		final boolean get$fixedFlag$sample20() {
			return fixedFlag$sample20;
		}

		// Setter for fixedFlag$sample20.
		final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
			// need to be updated.
			fixedFlag$sample20 = cv$value;
			constrainedFlag$sample20 = (fixedFlag$sample20 || constrainedFlag$sample20);
			
			// Should the probability of sample 20 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
			
			// Should the probability of sample 48 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample48 = (fixedFlag$sample20 && fixedProbFlag$sample48);
			
			// Should the probability of sample 60 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample60 = (fixedFlag$sample20 && fixedProbFlag$sample60);
			
			// Should the probability of sample 72 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample72 = (fixedFlag$sample20 && fixedProbFlag$sample72);
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

		// Getter for logProbability$b3.
		final double get$logProbability$b3() {
			return logProbability$b3;
		}

		// Getter for logProbability$output.
		final double get$logProbability$output() {
			return logProbability$output;
		}

		// Getter for logProbability$p.
		final double get$logProbability$p() {
			return logProbability$p;
		}

		// Getter for logProbability$prior.
		final double get$logProbability$prior() {
			return logProbability$prior;
		}

		// Getter for n.
		final int get$n() {
			return n;
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

		// Getter for p.
		final double[] get$p() {
			return p;
		}

		// Setter for p.
		final void set$p(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of p including if probabilities need to be updated.
			p = cv$value;
			
			// Unset the fixed probability flag for sample 17 as it depends on p.
			fixedProbFlag$sample17 = false;
			
			// Unset the fixed probability flag for sample 20 as it depends on p.
			fixedProbFlag$sample20 = false;
		}

		// Getter for prior.
		final int[] get$prior() {
			return prior;
		}

		// Setter for prior.
		final void set$prior(int[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of prior including if probabilities need to
			// be updated.
			prior = cv$value;
			
			// Unset the fixed probability flag for sample 20 as it depends on prior.
			fixedProbFlag$sample20 = false;
			
			// Unset the fixed probability flag for sample 48 as it depends on prior.
			fixedProbFlag$sample48 = false;
			
			// Unset the fixed probability flag for sample 60 as it depends on prior.
			fixedProbFlag$sample60 = false;
			
			// Unset the fixed probability flag for sample 72 as it depends on prior.
			fixedProbFlag$sample72 = false;
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
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
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

    private final ComputedDoubleArrayInternal $p = new ComputedDoubleArrayInternal(this, "p", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$p(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$p(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$p(); }

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

	/** Computed variable representing p of type double[] from the Sandwood model. */
    public final ComputedDoubleArray p = $p;

    private final ComputedIntegerArrayInternal $prior = new ComputedIntegerArrayInternal(this, "prior", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$prior(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$prior(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$prior(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample20(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample20())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing prior of type int[] from the Sandwood model. */
    public final ComputedIntegerArray prior = $prior;

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

    private final RandomVariableInternal $b3 = new RandomVariableInternal(this, "b3", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$b3();
        }
    };

	/** Random variable representing b3 from the Sandwood model. */
    public final RandomVariable b3 = $b3;

    private HasProbabilityInternal[] $probabilityVariables = {$output, $p, $prior, $b1, $b2, $b3};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public MultinomialBernoulli() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("output", $output);
        $computedVariables.put("p", $p);
        $computedVariables.put("prior", $prior);

        //Observed array fields
        $shapedObservedValues.put("observed", $observed);

        MultinomialBernoulli$SingleThreadCPU core = new MultinomialBernoulli$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param observedShape An integer array describing the shape of variable observed
	 *                      to use in the model when generating results.
	 */
    public MultinomialBernoulli(int observedShape) {
        this();
        this.$observed.setShape(observedShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observed The value to set observed to.
	 */
    public MultinomialBernoulli(boolean[] observed) {
        this();
        this.observed.setValue(observed);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new MultinomialBernoulli$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new MultinomialBernoulli$MultiThreadCPU(state, target);
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
		/** Field holding the value of p after a convention execution step. */
        public final double[] p;
		/** Field holding the value of prior after a convention execution step. */
        public final int[] prior;

        InferredValueOutputs(MultinomialBernoulli system$model) {
            this.output = system$model.output.getSamples()[0];
            this.p = system$model.p.getSamples()[0];
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
		/** Field holding the log probability of random variable b3 */
        public final double b3;
		/** Field holding the log probability of computed variable output */
        public final double output;
		/** Field holding the log probability of computed variable p */
        public final double p;
		/** Field holding the log probability of computed variable prior */
        public final double prior;

        LogProbabilities(MultinomialBernoulli system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b1 = system$model.b1.getLogProbability();
            this.b2 = system$model.b2.getLogProbability();
            this.b3 = system$model.b3.getLogProbability();
            this.output = system$model.output.getLogProbability();
            this.p = system$model.p.getLogProbability();
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
		/** Field holding the probability of random variable b3 */
        public final double b3;
		/** Field holding the probability of computed variable output */
        public final double output;
		/** Field holding the probability of computed variable p */
        public final double p;
		/** Field holding the probability of computed variable prior */
        public final double prior;

        Probabilities(MultinomialBernoulli system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b1 = system$model.b1.getProbability();
            this.b2 = system$model.b2.getProbability();
            this.b3 = system$model.b3.getProbability();
            this.output = system$model.output.getProbability();
            this.p = system$model.p.getProbability();
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
		/** Field holding the MAP or Sample value of p after an infer model call. */
        public final double[][] p;
		/** Field holding the MAP or Sample value of prior after an infer model call. */
        public final int[][] prior;

        InferredModelOutputs(MultinomialBernoulli system$model) {
            this.p = system$model.getInferredValue(system$model.$p);
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