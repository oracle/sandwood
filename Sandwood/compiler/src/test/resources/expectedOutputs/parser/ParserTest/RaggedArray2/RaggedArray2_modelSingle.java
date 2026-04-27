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
 * Class representing the Sandwood model RaggedArray2 This is the class that all user
 * interactions with the model should occur through.
 */
public final class RaggedArray2 extends Model<RaggedArray2.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[][] a;
		double[][] b;
		double[] c;
		boolean constrainedFlag$sample81 = true;
		boolean constrainedFlag$sample84 = true;
		boolean fixedFlag$sample81 = false;
		boolean fixedFlag$sample84 = false;
		boolean fixedProbFlag$sample100 = false;
		boolean fixedProbFlag$sample81 = false;
		boolean fixedProbFlag$sample84 = false;
		int i;
		int length$obs_measured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$i;
		double logProbability$obs;
		double logProbability$var96;
		double logProbability$y;
		boolean[] obs;
		boolean[] obs_measured;
		double p;
		boolean system$gibbsForward = true;
		int y;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for a
			{
				a = new double[2][];
				a[0] = new double[2];
				a[1] = new double[3];
			}
			
			// Constructor for b
			{
				b = new double[2][];
				b[0] = new double[2];
				b[1] = new double[3];
			}
			
			// Constructor for c
			{
				c = new double[2];
			}
			
			// Constructor for obs
			{
				obs = new boolean[length$obs_measured];
			}
		}

		// Getter for a.
		final double[][] get$a() {
			return a;
		}

		// Getter for b.
		final double[][] get$b() {
			return b;
		}

		// Getter for c.
		final double[] get$c() {
			return c;
		}

		// Getter for fixedFlag$sample81.
		final boolean get$fixedFlag$sample81() {
			return fixedFlag$sample81;
		}

		// Setter for fixedFlag$sample81.
		final void set$fixedFlag$sample81(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample81 including if probabilities
			// need to be updated.
			fixedFlag$sample81 = cv$value;
			constrainedFlag$sample81 = (fixedFlag$sample81 || constrainedFlag$sample81);
			
			// Should the probability of sample 81 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
			
			// Should the probability of sample 84 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample84 = (fixedFlag$sample81 && fixedProbFlag$sample84);
			
			// Should the probability of sample 100 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample100 = (fixedFlag$sample81 && fixedProbFlag$sample100);
		}

		// Getter for fixedFlag$sample84.
		final boolean get$fixedFlag$sample84() {
			return fixedFlag$sample84;
		}

		// Setter for fixedFlag$sample84.
		final void set$fixedFlag$sample84(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
			// need to be updated.
			fixedFlag$sample84 = cv$value;
			constrainedFlag$sample84 = (fixedFlag$sample84 || constrainedFlag$sample84);
			
			// Should the probability of sample 84 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
			
			// Should the probability of sample 100 be set to fixed. This will only every change
			// the flag to false.
			fixedProbFlag$sample100 = (fixedFlag$sample84 && fixedProbFlag$sample100);
		}

		// Getter for i.
		final int get$i() {
			return i;
		}

		// Setter for i.
		final void set$i(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of i including if probabilities need to be updated.
			i = cv$value;
			
			// Unset the fixed probability flag for sample 84 as it depends on i.
			fixedProbFlag$sample84 = false;
			
			// Unset the fixed probability flag for sample 100 as it depends on i.
			fixedProbFlag$sample100 = false;
		}

		// Getter for length$obs_measured.
		final int get$length$obs_measured() {
			return length$obs_measured;
		}

		// Setter for length$obs_measured.
		final void set$length$obs_measured(int cv$value, boolean allocated$) {
			length$obs_measured = cv$value;
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

		// Getter for logProbability$i.
		final double get$logProbability$i() {
			return logProbability$i;
		}

		// Getter for logProbability$obs.
		final double get$logProbability$obs() {
			return logProbability$obs;
		}

		// Getter for logProbability$y.
		final double get$logProbability$y() {
			return logProbability$y;
		}

		// Getter for obs.
		final boolean[] get$obs() {
			return obs;
		}

		// Getter for obs_measured.
		final boolean[] get$obs_measured() {
			return obs_measured;
		}

		// Setter for obs_measured.
		final void set$obs_measured(boolean[] cv$value, boolean allocated$) {
			obs_measured = cv$value;
		}

		// Getter for p.
		final double get$p() {
			return p;
		}

		// Getter for y.
		final int get$y() {
			return y;
		}

		// Setter for y.
		final void set$y(int cv$value, boolean allocated$) {
			// Set flags for all the side effects of y including if probabilities need to be updated.
			y = cv$value;
			
			// Unset the fixed probability flag for sample 81 as it depends on y.
			fixedProbFlag$sample81 = false;
			
			// Unset the fixed probability flag for sample 84 as it depends on y.
			fixedProbFlag$sample84 = false;
			
			// Unset the fixed probability flag for sample 100 as it depends on y.
			fixedProbFlag$sample100 = false;
		}
	}

    private final ComputedIntegerInternal $i = new ComputedIntegerInternal(this, "i", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$i(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$i(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$i(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample84(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample84())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing i of type int from the Sandwood model. */
    public final ComputedInteger i = $i;

    private final ComputedBooleanArrayInternal $obs = new ComputedBooleanArrayInternal(this, "obs", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$obs(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable obs because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$obs(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing obs of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray obs = $obs;

    private final ComputedDoubleInternal $p = new ComputedDoubleInternal(this, "p", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$p(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable p because its value depends on variables \"i\", and \"y\".");
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample81(fixed, allocated);
                state.set$fixedFlag$sample84(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample81 = state.get$fixedFlag$sample81();
            boolean fixedFlag$sample84 = state.get$fixedFlag$sample84();
            if(fixedFlag$sample81 && fixedFlag$sample84)
                return Immutability.FIXED;
            else if(fixedFlag$sample81 || fixedFlag$sample84)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing p of type double from the Sandwood model. */
    public final ComputedDouble p = $p;

    private final ComputedIntegerInternal $y = new ComputedIntegerInternal(this, "y", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int getValue() { return state.get$y(); }

        @Override
        protected void setValueInternal(int value) {
            state.set$y(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample81(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample81())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing y of type int from the Sandwood model. */
    public final ComputedInteger y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $obs_measured = new ObservedBooleanArrayShapeableInternal(this, "obs_measured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$obs_measured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$obs_measured(value, allocated);
            state.set$length$obs_measured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$obs_measured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$obs_measured();
        }
    };

	/**
	 * Observed variable representing obs_measured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable obs_measured = $obs_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$i, $obs, $y};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public RaggedArray2() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("i", $i);
        $computedVariables.put("obs", $obs);
        $computedVariables.put("p", $p);
        $computedVariables.put("y", $y);

        //Observed array fields
        $shapedObservedValues.put("obs_measured", $obs_measured);

        RaggedArray2$SingleThreadCPU core = new RaggedArray2$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
	 *                          to use in the model when generating results.
	 */
    public RaggedArray2(int obs_measuredShape) {
        this();
        this.$obs_measured.setShape(obs_measuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param obs_measured The value to set obs_measured to.
	 */
    public RaggedArray2(boolean[] obs_measured) {
        this();
        this.obs_measured.setValue(obs_measured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new RaggedArray2$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new RaggedArray2$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input obs_measured */
        public final int obs_measuredShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param obs_measuredShape An integer array describing the shape of variable obs_measured
		 *                          to use in the model when generating results.
		 */
        public InferValueInputs(int obs_measuredShape) {
            this.obs_measuredShape = obs_measuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input obs_measured */
        public final boolean[] obs_measured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param obs_measured The value to set obs_measured to.
		 */
        public AllInputs(boolean[] obs_measured) {
            this.obs_measured = obs_measured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of i after a convention execution step. */
        public final int i;
		/** Field holding the value of obs after a convention execution step. */
        public final boolean[] obs;
		/** Field holding the value of p after a convention execution step. */
        public final double p;
		/** Field holding the value of y after a convention execution step. */
        public final int y;

        InferredValueOutputs(RaggedArray2 system$model) {
            this.i = system$model.i.getSamples()[0];
            this.obs = system$model.obs.getSamples()[0];
            this.p = system$model.p.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable i */
        public final double i;
		/** Field holding the log probability of computed variable obs */
        public final double obs;
		/** Field holding the log probability of computed variable p */
        public final double p;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(RaggedArray2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.i = system$model.i.getLogProbability();
            this.obs = system$model.obs.getLogProbability();
            this.p = system$model.p.getLogProbability();
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
		/** Field holding the probability of computed variable i */
        public final double i;
		/** Field holding the probability of computed variable obs */
        public final double obs;
		/** Field holding the probability of computed variable p */
        public final double p;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(RaggedArray2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.i = system$model.i.getProbability();
            this.obs = system$model.obs.getProbability();
            this.p = system$model.p.getProbability();
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
		/** Field holding the MAP or Sample value of i after an infer model call. */
        public final int[] i;
		/** Field holding the MAP or Sample value of p after an infer model call. */
        public final double[] p;
		/** Field holding the MAP or Sample value of y after an infer model call. */
        public final int[] y;

        InferredModelOutputs(RaggedArray2 system$model) {
            this.i = system$model.getInferredValue(system$model.$i);
            this.p = system$model.getInferredValue(system$model.$p);
            this.y = system$model.getInferredValue(system$model.$y);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$obs_measured.setShape(inputs.obs_measuredShape);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}