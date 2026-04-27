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
 * Class representing the Sandwood model ReductionTest This is the class that all
 * user interactions with the model should occur through.
 */
public final class ReductionTest extends Model<ReductionTest.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] bias;
		boolean[] constrainedFlag$sample30;
		boolean[] constrainedFlag$sample47;
		boolean[] constrainedFlag$sample62;
		boolean fixedFlag$sample30 = false;
		boolean fixedFlag$sample47 = false;
		boolean fixedFlag$sample62 = false;
		boolean fixedProbFlag$sample30 = false;
		boolean fixedProbFlag$sample47 = false;
		boolean fixedProbFlag$sample62 = false;
		boolean fixedProbFlag$sample87 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		int length$flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$m;
		double[] logProbability$sample62;
		double[] logProbability$sample87;
		double logProbability$st;
		double logProbability$var30;
		double logProbability$var46;
		double[][] m;
		int noCats;
		int noFlips;
		int noStates;
		int[] st;
		boolean system$gibbsForward = true;
		double[] v;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			v = new double[(length$flipsMeasured / noCats)];
			
			// If m has not been set already allocate space.
			if(!fixedFlag$sample30) {
				// Constructor for m
				m = new double[noCats][];
				for(int var29 = 0; var29 < noCats; var29 += 1)
					m[var29] = new double[(length$flipsMeasured / noCats)];
			}
			
			// If bias has not been set already allocate space.
			if(!fixedFlag$sample47)
				// Constructor for bias
				bias = new double[length$flipsMeasured];
			
			// If st has not been set already allocate space.
			if(!fixedFlag$sample62)
				// Constructor for st
				st = new int[noCats];
			
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
			
			// Constructor for constrainedFlag$sample47
			constrainedFlag$sample47 = new boolean[length$flipsMeasured];
			
			// Constructor for constrainedFlag$sample30
			constrainedFlag$sample30 = new boolean[noCats];
			
			// Constructor for constrainedFlag$sample62
			constrainedFlag$sample62 = new boolean[noCats];
			
			// Constructor for logProbability$sample62
			logProbability$sample62 = new double[noCats];
			
			// Constructor for logProbability$sample87
			logProbability$sample87 = new double[length$flipsMeasured];
		}

		// Getter for bias.
		final double[] get$bias() {
			return bias;
		}

		// Setter for bias.
		final void set$bias(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of bias including if probabilities need to be
			// updated.
			bias = cv$value;
			
			// Unset the fixed probability flag for sample 47 as it depends on bias.
			fixedProbFlag$sample47 = false;
			
			// Unset the fixed probability flag for sample 87 as it depends on bias.
			fixedProbFlag$sample87 = false;
		}

		// Getter for fixedFlag$sample30.
		final boolean get$fixedFlag$sample30() {
			return fixedFlag$sample30;
		}

		// Setter for fixedFlag$sample30.
		final void set$fixedFlag$sample30(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample30 including if probabilities
			// need to be updated.
			fixedFlag$sample30 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
					constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
			}
			
			// Should the probability of sample 30 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample30" with its value "cv$value".
			fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
			
			// Should the probability of sample 62 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample30" with its value "cv$value".
			fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		}

		// Getter for fixedFlag$sample47.
		final boolean get$fixedFlag$sample47() {
			return fixedFlag$sample47;
		}

		// Setter for fixedFlag$sample47.
		final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
			// need to be updated.
			fixedFlag$sample47 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
					constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
			}
			
			// Should the probability of sample 47 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample47" with its value "cv$value".
			fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
			
			// Should the probability of sample 87 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample47" with its value "cv$value".
			fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		}

		// Getter for fixedFlag$sample62.
		final boolean get$fixedFlag$sample62() {
			return fixedFlag$sample62;
		}

		// Setter for fixedFlag$sample62.
		final void set$fixedFlag$sample62(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample62 including if probabilities
			// need to be updated.
			fixedFlag$sample62 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample62$1 = 0; index$constrainedFlag$sample62$1 < constrainedFlag$sample62.length; index$constrainedFlag$sample62$1 += 1)
					constrainedFlag$sample62[index$constrainedFlag$sample62$1] = true;
			}
			
			// Should the probability of sample 62 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample62" with its value "cv$value".
			fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
			
			// Should the probability of sample 87 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample62" with its value "cv$value".
			fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		}

		// Getter for flips.
		final boolean[] get$flips() {
			return flips;
		}

		// Getter for flipsMeasured.
		final boolean[] get$flipsMeasured() {
			return flipsMeasured;
		}

		// Setter for flipsMeasured.
		final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
			flipsMeasured = cv$value;
		}

		// Getter for length$flipsMeasured.
		final int get$length$flipsMeasured() {
			return length$flipsMeasured;
		}

		// Setter for length$flipsMeasured.
		final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
			length$flipsMeasured = cv$value;
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

		// Getter for logProbability$bias.
		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		// Getter for logProbability$flips.
		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		// Getter for logProbability$m.
		final double get$logProbability$m() {
			return logProbability$m;
		}

		// Getter for logProbability$st.
		final double get$logProbability$st() {
			return logProbability$st;
		}

		// Getter for m.
		final double[][] get$m() {
			return m;
		}

		// Setter for m.
		final void set$m(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of m including if probabilities need to be updated.
			m = cv$value;
			
			// Unset the fixed probability flag for sample 30 as it depends on m.
			fixedProbFlag$sample30 = false;
			
			// Unset the fixed probability flag for sample 62 as it depends on m.
			fixedProbFlag$sample62 = false;
		}

		// Getter for noCats.
		final int get$noCats() {
			return noCats;
		}

		// Setter for noCats.
		final void set$noCats(int cv$value, boolean allocated$) {
			noCats = cv$value;
		}

		// Getter for noFlips.
		final int get$noFlips() {
			return noFlips;
		}

		// Getter for noStates.
		final int get$noStates() {
			return noStates;
		}

		// Getter for st.
		final int[] get$st() {
			return st;
		}

		// Setter for st.
		final void set$st(int[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of st including if probabilities need to be
			// updated.
			st = cv$value;
			
			// Unset the fixed probability flag for sample 62 as it depends on st.
			fixedProbFlag$sample62 = false;
			
			// Unset the fixed probability flag for sample 87 as it depends on st.
			fixedProbFlag$sample87 = false;
		}

		// Getter for v.
		final double[] get$v() {
			return v;
		}
	}

    private final ComputedDoubleArrayInternal $bias = new ComputedDoubleArrayInternal(this, "bias", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$bias(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$bias(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$bias(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample47(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample47())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing bias of type double[] from the Sandwood model. */
    public final ComputedDoubleArray bias = $bias;

    private final ComputedBooleanArrayInternal $flips = new ComputedBooleanArrayInternal(this, "flips", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$flips(); }

        @Override
        protected void setValueInternal(boolean[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable flips because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$flips(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing flips of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray flips = $flips;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$m(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$m(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample30(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample30())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedIntegerArrayInternal $st = new ComputedIntegerArrayInternal(this, "st", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$st(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$st(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$st(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample62(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample62())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[] from the Sandwood model. */
    public final ComputedIntegerArray st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $noCats = new ObservedIntegerInternal(this, "noCats") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noCats();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noCats(value, allocated); }
    };

	/** Observed variable representing noCats of type int from the Sandwood model. */
    public final ObservedInteger noCats = $noCats;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $flipsMeasured = new ObservedBooleanArrayShapeableInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$flipsMeasured(value, allocated);
            state.set$length$flipsMeasured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$flipsMeasured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$flipsMeasured();
        }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArrayShapeable flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $m, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public ReductionTest() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("noCats", $noCats);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);

        ReductionTest$SingleThreadCPU core = new ReductionTest$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
	 *                           to use in the model when generating results.
	 * @param noCats The value to set noCats to.
	 */
    public ReductionTest(int flipsMeasuredShape, int noCats) {
        this();
        this.$noCats.setValue(noCats);
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 * @param noCats The value to set noCats to
	 */
    public ReductionTest(boolean[] flipsMeasured, int noCats) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
        this.noCats.setValue(noCats);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new ReductionTest$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new ReductionTest$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input flipsMeasured */
        public final int flipsMeasuredShape;
		/** Field holding the value of model input noCats */
        public final int noCats;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
		 *                           to use in the model when generating results.
		 * @param noCats The value to set noCats to.
		 */
        public InferValueInputs(int flipsMeasuredShape, int noCats) {
            this.noCats = noCats;
            this.flipsMeasuredShape = flipsMeasuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;
		/** Field holding the value of model input noCats */
        public final int noCats;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 * @param noCats The value to set noCats to.
		 */
        public AllInputs(boolean[] flipsMeasured, int noCats) {
            this.flipsMeasured = flipsMeasured;
            this.noCats = noCats;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of st after a convention execution step. */
        public final int[] st;

        InferredValueOutputs(ReductionTest system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable bias */
        public final double bias;
		/** Field holding the log probability of computed variable flips */
        public final double flips;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(ReductionTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
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
		/** Field holding the probability of computed variable bias */
        public final double bias;
		/** Field holding the probability of computed variable flips */
        public final double flips;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(ReductionTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[][] bias;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][] st;

        InferredModelOutputs(ReductionTest system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setShape(inputs.flipsMeasuredShape);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
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
        this.noCats.setValue(inputs.noCats);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}