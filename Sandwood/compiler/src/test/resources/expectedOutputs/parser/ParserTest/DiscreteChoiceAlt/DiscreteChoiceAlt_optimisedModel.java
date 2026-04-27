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
 * Class representing the Sandwood model DiscreteChoiceAlt This is the class that
 * all user interactions with the model should occur through.
 */
public final class DiscreteChoiceAlt extends Model<DiscreteChoiceAlt.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		int[] ObsChoices;
		int[] choices;
		boolean[] constrainedFlag$sample24;
		double[] exped;
		boolean fixedFlag$sample24 = false;
		boolean fixedProbFlag$sample24 = false;
		boolean fixedProbFlag$sample78 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$choices;
		double logProbability$exped;
		double logProbability$prob;
		double[] logProbability$sample24;
		double logProbability$sum;
		double logProbability$ut;
		double logProbability$var77;
		int noObs;
		int noProducts;
		double[] prob;
		double sum;
		boolean system$gibbsForward = true;
		double[] ut;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// If ut has not been set already allocate space.
			if(!fixedFlag$sample24)
				// Constructor for ut
				ut = new double[noProducts];
			
			// Constructor for exped
			exped = new double[noProducts];
			
			// Constructor for prob
			prob = new double[noProducts];
			
			// Constructor for choices
			choices = new int[noObs];
			
			// Constructor for constrainedFlag$sample24
			constrainedFlag$sample24 = new boolean[(noProducts - 1)];
			
			// Constructor for logProbability$sample24
			logProbability$sample24 = new double[(noProducts - 1)];
		}

		// Getter for ObsChoices.
		final int[] get$ObsChoices() {
			return ObsChoices;
		}

		// Setter for ObsChoices.
		final void set$ObsChoices(int[] cv$value, boolean allocated$) {
			ObsChoices = cv$value;
		}

		// Getter for choices.
		final int[] get$choices() {
			return choices;
		}

		// Getter for exped.
		final double[] get$exped() {
			return exped;
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
			
			// Should the probability of sample 78 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample24" with its value "cv$value".
			fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
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

		// Getter for logProbability$choices.
		final double get$logProbability$choices() {
			return logProbability$choices;
		}

		// Getter for logProbability$exped.
		final double get$logProbability$exped() {
			return logProbability$exped;
		}

		// Getter for logProbability$prob.
		final double get$logProbability$prob() {
			return logProbability$prob;
		}

		// Getter for logProbability$sum.
		final double get$logProbability$sum() {
			return logProbability$sum;
		}

		// Getter for logProbability$ut.
		final double get$logProbability$ut() {
			return logProbability$ut;
		}

		// Getter for noObs.
		final int get$noObs() {
			return noObs;
		}

		// Setter for noObs.
		final void set$noObs(int cv$value, boolean allocated$) {
			noObs = cv$value;
		}

		// Getter for noProducts.
		final int get$noProducts() {
			return noProducts;
		}

		// Setter for noProducts.
		final void set$noProducts(int cv$value, boolean allocated$) {
			noProducts = cv$value;
		}

		// Getter for prob.
		final double[] get$prob() {
			return prob;
		}

		// Getter for sum.
		final double get$sum() {
			return sum;
		}

		// Getter for ut.
		final double[] get$ut() {
			return ut;
		}

		// Setter for ut.
		final void set$ut(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of ut including if probabilities need to be
			// updated.
			ut = cv$value;
			
			// Unset the fixed probability flag for sample 24 as it depends on ut.
			fixedProbFlag$sample24 = false;
			
			// Unset the fixed probability flag for sample 78 as it depends on ut.
			fixedProbFlag$sample78 = false;
		}
	}

    private final ComputedIntegerArrayInternal $choices = new ComputedIntegerArrayInternal(this, "choices", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$choices(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable choices because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$choices(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing choices of type int[] from the Sandwood model. */
    public final ComputedIntegerArray choices = $choices;

    private final ComputedDoubleArrayInternal $exped = new ComputedDoubleArrayInternal(this, "exped", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$exped(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable exped because its value depends on variable \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$exped(); }

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

	/** Computed variable representing exped of type double[] from the Sandwood model. */
    public final ComputedDoubleArray exped = $exped;

    private final ComputedDoubleArrayInternal $prob = new ComputedDoubleArrayInternal(this, "prob", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$prob(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable prob because its value depends on variables \"exped\", \"sum\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$prob(); }

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

	/** Computed variable representing prob of type double[] from the Sandwood model. */
    public final ComputedDoubleArray prob = $prob;

    private final ComputedDoubleInternal $sum = new ComputedDoubleInternal(this, "sum", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$sum(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable sum because its value depends on variables \"exped\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$sum(); }

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

	/** Computed variable representing sum of type double from the Sandwood model. */
    public final ComputedDouble sum = $sum;

    private final ComputedDoubleArrayInternal $ut = new ComputedDoubleArrayInternal(this, "ut", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$ut(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$ut(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$ut(); }

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

	/** Computed variable representing ut of type double[] from the Sandwood model. */
    public final ComputedDoubleArray ut = $ut;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $noObs = new ObservedIntegerInternal(this, "noObs") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noObs();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noObs(value, allocated); }
    };

	/** Observed variable representing noObs of type int from the Sandwood model. */
    public final ObservedInteger noObs = $noObs;

    private final ObservedIntegerInternal $noProducts = new ObservedIntegerInternal(this, "noProducts") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noProducts();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noProducts(value, allocated); }
    };

	/** Observed variable representing noProducts of type int from the Sandwood model. */
    public final ObservedInteger noProducts = $noProducts;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayInternal $ObsChoices = new ObservedIntegerArrayInternal(this, "ObsChoices") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return state.get$ObsChoices();
            }
        }

        @Override
        protected void setValueInternal(int[] value) { state.set$ObsChoices(value, allocated); }
    };

	/**
	 * Observed variable representing ObsChoices of type int[] from the Sandwood model.
	 */
    public final ObservedIntegerArray ObsChoices = $ObsChoices;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$choices, $exped, $prob, $sum, $ut};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DiscreteChoiceAlt() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("choices", $choices);
        $computedVariables.put("exped", $exped);
        $computedVariables.put("prob", $prob);
        $computedVariables.put("sum", $sum);
        $computedVariables.put("ut", $ut);

        //ModelInputs
        $modelInputs.put("noObs", $noObs);
        $modelInputs.put("noProducts", $noProducts);

        //Observed scalar fields
        $regularObservedValues.put("ObsChoices", $ObsChoices);

        DiscreteChoiceAlt$SingleThreadCPU core = new DiscreteChoiceAlt$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param noProducts The value to set noProducts to.
	 * @param noObs The value to set noObs to.
	 */
    public DiscreteChoiceAlt(int noProducts, int noObs) {
        this();
        this.$noObs.setValue(noObs);
        this.$noProducts.setValue(noProducts);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param noProducts The value to set noProducts to.
	 * @param noObs The value to set noObs to
	 * @param ObsChoices The value to set ObsChoices to
	 */
    public DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {
        this();
        this.noProducts.setValue(noProducts);
        this.noObs.setValue(noObs);
        this.ObsChoices.setValue(ObsChoices);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DiscreteChoiceAlt$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DiscreteChoiceAlt$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input noProducts */
        public final int noProducts;
		/** Field holding the value of model input noObs */
        public final int noObs;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param noProducts The value to set noProducts to.
		 * @param noObs The value to set noObs to.
		 */
        public InferValueInputs(int noProducts, int noObs) {
            this.noObs = noObs;
            this.noProducts = noProducts;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input noProducts */
        public final int noProducts;
		/** Field holding the value of model input noObs */
        public final int noObs;
		/** Field holding the value of model input ObsChoices */
        public final int[] ObsChoices;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param noProducts The value to set noProducts to.
		 * @param noObs The value to set noObs to.
		 * @param ObsChoices The value to set ObsChoices to.
		 */
        public AllInputs(int noProducts, int noObs, int[] ObsChoices) {
            this.noProducts = noProducts;
            this.noObs = noObs;
            this.ObsChoices = ObsChoices;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of choices after a convention execution step. */
        public final int[] choices;
		/** Field holding the value of exped after a convention execution step. */
        public final double[] exped;
		/** Field holding the value of prob after a convention execution step. */
        public final double[] prob;
		/** Field holding the value of sum after a convention execution step. */
        public final double sum;
		/** Field holding the value of ut after a convention execution step. */
        public final double[] ut;

        InferredValueOutputs(DiscreteChoiceAlt system$model) {
            this.choices = system$model.choices.getSamples()[0];
            this.exped = system$model.exped.getSamples()[0];
            this.prob = system$model.prob.getSamples()[0];
            this.sum = system$model.sum.getSamples()[0];
            this.ut = system$model.ut.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable choices */
        public final double choices;
		/** Field holding the log probability of computed variable exped */
        public final double exped;
		/** Field holding the log probability of computed variable prob */
        public final double prob;
		/** Field holding the log probability of computed variable sum */
        public final double sum;
		/** Field holding the log probability of computed variable ut */
        public final double ut;

        LogProbabilities(DiscreteChoiceAlt system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.choices = system$model.choices.getLogProbability();
            this.exped = system$model.exped.getLogProbability();
            this.prob = system$model.prob.getLogProbability();
            this.sum = system$model.sum.getLogProbability();
            this.ut = system$model.ut.getLogProbability();
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
		/** Field holding the probability of computed variable choices */
        public final double choices;
		/** Field holding the probability of computed variable exped */
        public final double exped;
		/** Field holding the probability of computed variable prob */
        public final double prob;
		/** Field holding the probability of computed variable sum */
        public final double sum;
		/** Field holding the probability of computed variable ut */
        public final double ut;

        Probabilities(DiscreteChoiceAlt system$model) {
            this.$modelProbability = system$model.getProbability();
            this.choices = system$model.choices.getProbability();
            this.exped = system$model.exped.getProbability();
            this.prob = system$model.prob.getProbability();
            this.sum = system$model.sum.getProbability();
            this.ut = system$model.ut.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of exped after an infer model call. */
        public final double[][] exped;
		/** Field holding the MAP or Sample value of prob after an infer model call. */
        public final double[][] prob;
		/** Field holding the MAP or Sample value of sum after an infer model call. */
        public final double[] sum;
		/** Field holding the MAP or Sample value of ut after an infer model call. */
        public final double[][] ut;

        InferredModelOutputs(DiscreteChoiceAlt system$model) {
            this.exped = system$model.getInferredValue(system$model.$exped);
            this.prob = system$model.getInferredValue(system$model.$prob);
            this.sum = system$model.getInferredValue(system$model.$sum);
            this.ut = system$model.getInferredValue(system$model.$ut);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}