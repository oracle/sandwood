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
 * Class representing the Sandwood model HMMTestPart3d This is the class that all
 * user interactions with the model should occur through.
 */
public final class HMMTestPart3d extends Model<HMMTestPart3d.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] bias;
		boolean[] constrainedFlag$sample28;
		boolean[] constrainedFlag$sample45;
		boolean constrainedFlag$sample54 = true;
		boolean[] constrainedFlag$sample79;
		boolean fixedFlag$sample28 = false;
		boolean fixedFlag$sample45 = false;
		boolean fixedFlag$sample54 = false;
		boolean fixedFlag$sample79 = false;
		boolean fixedProbFlag$sample119 = false;
		boolean fixedProbFlag$sample28 = false;
		boolean fixedProbFlag$sample45 = false;
		boolean fixedProbFlag$sample54 = false;
		boolean fixedProbFlag$sample79 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		int[][] indirection;
		int length$flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$m;
		double[] logProbability$sample79;
		double logProbability$st;
		double logProbability$st2;
		double logProbability$var118;
		double logProbability$var28;
		double logProbability$var44;
		double logProbability$var53;
		double[][] m;
		int samples;
		int[] st;
		int[] st2;
		int states;
		boolean system$gibbsForward = true;
		double[] v;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for v
			v = new double[2];
			
			// If m has not been set already allocate space.
			if(!fixedFlag$sample28) {
				// Constructor for m
				m = new double[2][];
				m[0] = new double[2];
				m[1] = new double[2];
			}
			
			// If bias has not been set already allocate space.
			if(!fixedFlag$sample45)
				// Constructor for bias
				bias = new double[2];
			
			// If st has not been set already allocate space.
			if((!fixedFlag$sample54 || !fixedFlag$sample79))
				// Constructor for st
				st = new int[length$flipsMeasured];
			
			// Constructor for st2
			st2 = new int[length$flipsMeasured];
			
			// Constructor for indirection
			indirection = new int[(length$flipsMeasured - 1)][];
			for(int i$var71 = 1; i$var71 < length$flipsMeasured; i$var71 += 1)
				indirection[(i$var71 - 1)] = new int[(i$var71 + 1)];
			
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
			
			// Constructor for constrainedFlag$sample79
			constrainedFlag$sample79 = new boolean[(length$flipsMeasured - 1)];
			
			// Constructor for constrainedFlag$sample45
			constrainedFlag$sample45 = new boolean[2];
			
			// Constructor for constrainedFlag$sample28
			constrainedFlag$sample28 = new boolean[2];
			
			// Constructor for logProbability$sample79
			logProbability$sample79 = new double[(length$flipsMeasured - 1)];
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
			
			// Unset the fixed probability flag for sample 45 as it depends on bias.
			fixedProbFlag$sample45 = false;
			
			// Unset the fixed probability flag for sample 119 as it depends on bias.
			fixedProbFlag$sample119 = false;
		}

		// Getter for fixedFlag$sample28.
		final boolean get$fixedFlag$sample28() {
			return fixedFlag$sample28;
		}

		// Setter for fixedFlag$sample28.
		final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
			// need to be updated.
			fixedFlag$sample28 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
					constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
			}
			
			// Should the probability of sample 28 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
			
			// Should the probability of sample 54 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
			
			// Should the probability of sample 79 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		}

		// Getter for fixedFlag$sample45.
		final boolean get$fixedFlag$sample45() {
			return fixedFlag$sample45;
		}

		// Setter for fixedFlag$sample45.
		final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
			// need to be updated.
			fixedFlag$sample45 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
					constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
			}
			
			// Should the probability of sample 45 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample45" with its value "cv$value".
			fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
			
			// Should the probability of sample 119 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample45" with its value "cv$value".
			fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		}

		// Getter for fixedFlag$sample54.
		final boolean get$fixedFlag$sample54() {
			return fixedFlag$sample54;
		}

		// Setter for fixedFlag$sample54.
		final void set$fixedFlag$sample54(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
			// need to be updated.
			fixedFlag$sample54 = cv$value;
			
			// Substituted "fixedFlag$sample54" with its value "cv$value".
			constrainedFlag$sample54 = (cv$value || constrainedFlag$sample54);
			
			// Should the probability of sample 54 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample54" with its value "cv$value".
			fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
			
			// Should the probability of sample 79 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample54" with its value "cv$value".
			fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
			
			// Should the probability of sample 119 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample54" with its value "cv$value".
			fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		}

		// Getter for fixedFlag$sample79.
		final boolean get$fixedFlag$sample79() {
			return fixedFlag$sample79;
		}

		// Setter for fixedFlag$sample79.
		final void set$fixedFlag$sample79(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample79 including if probabilities
			// need to be updated.
			fixedFlag$sample79 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample79$1 = 0; index$constrainedFlag$sample79$1 < constrainedFlag$sample79.length; index$constrainedFlag$sample79$1 += 1)
					constrainedFlag$sample79[index$constrainedFlag$sample79$1] = true;
			}
			
			// Should the probability of sample 79 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample79" with its value "cv$value".
			fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
			
			// Should the probability of sample 119 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample79" with its value "cv$value".
			fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
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

		// Getter for logProbability$st2.
		final double get$logProbability$st2() {
			return logProbability$st2;
		}

		// Getter for m.
		final double[][] get$m() {
			return m;
		}

		// Setter for m.
		final void set$m(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of m including if probabilities need to be updated.
			m = cv$value;
			
			// Unset the fixed probability flag for sample 28 as it depends on m.
			fixedProbFlag$sample28 = false;
			
			// Unset the fixed probability flag for sample 54 as it depends on m.
			fixedProbFlag$sample54 = false;
			
			// Unset the fixed probability flag for sample 79 as it depends on m.
			fixedProbFlag$sample79 = false;
		}

		// Getter for samples.
		final int get$samples() {
			return samples;
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
			
			// Unset the fixed probability flag for sample 54 as it depends on st.
			fixedProbFlag$sample54 = false;
			
			// Unset the fixed probability flag for sample 79 as it depends on st.
			fixedProbFlag$sample79 = false;
			
			// Unset the fixed probability flag for sample 119 as it depends on st.
			fixedProbFlag$sample119 = false;
		}

		// Getter for st2.
		final int[] get$st2() {
			return st2;
		}

		// Getter for states.
		final int get$states() {
			return 2;
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
                state.set$fixedFlag$sample45(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample45())
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
                state.set$fixedFlag$sample28(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample28())
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
                state.set$fixedFlag$sample54(fixed, allocated);
                state.set$fixedFlag$sample79(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample54 = state.get$fixedFlag$sample54();
            boolean fixedFlag$sample79 = state.get$fixedFlag$sample79();
            if(fixedFlag$sample54 && fixedFlag$sample79)
                return Immutability.FIXED;
            else if(fixedFlag$sample54 || fixedFlag$sample79)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[] from the Sandwood model. */
    public final ComputedIntegerArray st = $st;

    private final ComputedIntegerArrayInternal $st2 = new ComputedIntegerArrayInternal(this, "st2", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$st2(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable st2 because its value depends on variable \"st\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$st2(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample54(fixed, allocated);
                state.set$fixedFlag$sample79(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample54 = state.get$fixedFlag$sample54();
            boolean fixedFlag$sample79 = state.get$fixedFlag$sample79();
            if(fixedFlag$sample54 && fixedFlag$sample79)
                return Immutability.FIXED;
            else if(fixedFlag$sample54 || fixedFlag$sample79)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st2 of type int[] from the Sandwood model. */
    public final ComputedIntegerArray st2 = $st2;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

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
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $m, $st, $st2};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMMTestPart3d() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);
        $computedVariables.put("st2", $st2);

        //Observed array fields
        $shapedObservedValues.put("flipsMeasured", $flipsMeasured);

        HMMTestPart3d$SingleThreadCPU core = new HMMTestPart3d$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
	 *                           to use in the model when generating results.
	 */
    public HMMTestPart3d(int flipsMeasuredShape) {
        this();
        this.$flipsMeasured.setShape(flipsMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param flipsMeasured The value to set flipsMeasured to.
	 */
    public HMMTestPart3d(boolean[] flipsMeasured) {
        this();
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMMTestPart3d$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMMTestPart3d$MultiThreadCPU(state, target);
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

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param flipsMeasuredShape An integer array describing the shape of variable flipsMeasured
		 *                           to use in the model when generating results.
		 */
        public InferValueInputs(int flipsMeasuredShape) {
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

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 */
        public AllInputs(boolean[] flipsMeasured) {
            this.flipsMeasured = flipsMeasured;
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
		/** Field holding the value of st2 after a convention execution step. */
        public final int[] st2;

        InferredValueOutputs(HMMTestPart3d system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
            this.st2 = system$model.st2.getSamples()[0];
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
		/** Field holding the log probability of computed variable st2 */
        public final double st2;

        LogProbabilities(HMMTestPart3d system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
            this.st2 = system$model.st2.getLogProbability();
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
		/** Field holding the probability of computed variable st2 */
        public final double st2;

        Probabilities(HMMTestPart3d system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
            this.st2 = system$model.st2.getProbability();
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
		/** Field holding the MAP or Sample value of st2 after an infer model call. */
        public final int[][] st2;

        InferredModelOutputs(HMMTestPart3d system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
            this.st2 = system$model.getInferredValue(system$model.$st2);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
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
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}