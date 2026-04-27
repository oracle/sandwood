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
 * Class representing the Sandwood model DistributionTest7 This is the class that
 * all user interactions with the model should occur through.
 */
public final class DistributionTest7 extends Model<DistributionTest7.State> {
	final class State extends CoreModelState {
double[] bias;
		int cat;
		boolean constrainedFlag$sample31 = true;
		boolean constrainedFlag$sample45 = true;
		double data;
		double[] distribution$sample31;
		boolean fixedFlag$sample31 = false;
		boolean fixedFlag$sample45 = false;
		boolean fixedProbFlag$sample31 = false;
		boolean fixedProbFlag$sample45 = false;
		boolean fixedProbFlag$sample51 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$cat;
		double logProbability$data;
		double logProbability$result;
		double logProbability$sample45;
		double logProbability$var43;
		double observedData;
		double[] prob;
		int result;
		boolean system$gibbsForward = true;
		int var43;

		@Override
		public final void allocate() {
			{
				bias = new double[3];
			}
			{
				prob = new double[3];
			}
			{
				distribution$sample31 = new double[3];
			}
		}

		final double[] get$bias() {
			return bias;
		}

		final int get$cat() {
			return cat;
		}

		final void set$cat(int cv$value, boolean allocated$) {
			cat = cv$value;
			fixedProbFlag$sample31 = false;
			fixedProbFlag$sample45 = false;
			fixedProbFlag$sample51 = false;
		}

		final double get$data() {
			return data;
		}

		final double[] get$distribution$sample31() {
			return distribution$sample31;
		}

		final void set$distribution$sample31(double[] cv$value, boolean allocated$) {
			distribution$sample31 = cv$value;
		}

		final boolean get$fixedFlag$sample31() {
			return fixedFlag$sample31;
		}

		final void set$fixedFlag$sample31(boolean cv$value, boolean allocated$) {
			fixedFlag$sample31 = cv$value;
			constrainedFlag$sample31 = (fixedFlag$sample31 || constrainedFlag$sample31);
			fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
			fixedProbFlag$sample45 = (fixedFlag$sample31 && fixedProbFlag$sample45);
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedProbFlag$sample51);
		}

		final boolean get$fixedFlag$sample45() {
			return fixedFlag$sample45;
		}

		final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
			fixedFlag$sample45 = cv$value;
			constrainedFlag$sample45 = (fixedFlag$sample45 || constrainedFlag$sample45);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
			fixedProbFlag$sample51 = (fixedFlag$sample45 && fixedProbFlag$sample51);
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$cat() {
			return logProbability$cat;
		}

		final double get$logProbability$data() {
			return logProbability$data;
		}

		final double get$logProbability$result() {
			return logProbability$result;
		}

		final double get$observedData() {
			return observedData;
		}

		final void set$observedData(double cv$value, boolean allocated$) {
			observedData = cv$value;
		}

		final double[] get$prob() {
			return prob;
		}

		final int get$result() {
			return result;
		}

		final int get$var43() {
			return var43;
		}

		final void set$var43(int cv$value, boolean allocated$) {
			var43 = cv$value;
			fixedProbFlag$sample45 = false;
			fixedProbFlag$sample51 = false;
		}
	}

    private final ComputedIntegerInternal $cat = new ComputedIntegerInternal(this, "cat", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$cat(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$cat(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$cat(); }

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

	/** Computed variable representing cat of type int from the Sandwood model. */
    public final ComputedInteger cat = $cat;

    private final ComputedDoubleInternal $data = new ComputedDoubleInternal(this, "data", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$data(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable data because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$data(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing data of type double from the Sandwood model. */
    public final ComputedDouble data = $data;

    private final ComputedIntegerInternal $result = new ComputedIntegerInternal(this, "result", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$result(); }

        @Override
        protected void setValueInternal(int value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable result because its value depends on variable \"cat\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$result(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample31(fixed, allocated);
                state.set$fixedFlag$sample45(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample31 = state.get$fixedFlag$sample31();
            boolean fixedFlag$sample45 = state.get$fixedFlag$sample45();
            if(fixedFlag$sample31 && fixedFlag$sample45)
                return Immutability.FIXED;
            else if(fixedFlag$sample31 || fixedFlag$sample45)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing result of type int from the Sandwood model. */
    public final ComputedInteger result = $result;

    private final ComputedIntegerInternal $var43 = new ComputedIntegerInternal(this, "var43", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public int getValue() { return state.get$var43(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$var43(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodRuntimeException("This method should never be called on a private variable.");
        }

        @Override
        public Immutability isFixed() {
                return Immutability.FREE;
        }
    };

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleInternal $observedData = new ObservedDoubleInternal(this, "observedData") {
        @Override
        public double getValue() {
            synchronized(model) {
                return state.get$observedData();
            }
        }

        @Override
        protected void setValueInternal(double value) { state.set$observedData(value, allocated); }
    };

	/**
	 * Observed variable representing observedData of type double from the Sandwood model.
	 */
    public final ObservedDouble observedData = $observedData;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$cat, $data, $result};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DistributionTest7() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("cat", $cat);
        $computedVariables.put("data", $data);
        $computedVariables.put("result", $result);
        $computedVariables.put("var43", $var43);

        //Observed scalar fields
        $regularObservedValues.put("observedData", $observedData);

        DistributionTest7$SingleThreadCPU core = new DistributionTest7$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param observedData The value to set observedData to.
	 */
    public DistributionTest7(double observedData) {
        this();
        this.observedData.setValue(observedData);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DistributionTest7$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DistributionTest7$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 */
        public InferValueInputs() {
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input observedData */
        public final double observedData;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param observedData The value to set observedData to.
		 */
        public AllInputs(double observedData) {
            this.observedData = observedData;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of cat after a convention execution step. */
        public final int cat;
		/** Field holding the value of data after a convention execution step. */
        public final double data;
		/** Field holding the value of result after a convention execution step. */
        public final int result;

        InferredValueOutputs(DistributionTest7 system$model) {
            this.cat = system$model.cat.getSamples()[0];
            this.data = system$model.data.getSamples()[0];
            this.result = system$model.result.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable cat */
        public final double cat;
		/** Field holding the log probability of computed variable data */
        public final double data;
		/** Field holding the log probability of computed variable result */
        public final double result;

        LogProbabilities(DistributionTest7 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.cat = system$model.cat.getLogProbability();
            this.data = system$model.data.getLogProbability();
            this.result = system$model.result.getLogProbability();
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
		/** Field holding the probability of computed variable cat */
        public final double cat;
		/** Field holding the probability of computed variable data */
        public final double data;
		/** Field holding the probability of computed variable result */
        public final double result;

        Probabilities(DistributionTest7 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.cat = system$model.cat.getProbability();
            this.data = system$model.data.getProbability();
            this.result = system$model.result.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of cat after an infer model call. */
        public final int[] cat;
		/** Field holding the MAP or Sample value of result after an infer model call. */
        public final int[] result;

        InferredModelOutputs(DistributionTest7 system$model) {
            this.cat = system$model.getInferredValue(system$model.$cat);
            this.result = system$model.getInferredValue(system$model.$result);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
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
        this.$observedData.setValue(inputs.observedData);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}