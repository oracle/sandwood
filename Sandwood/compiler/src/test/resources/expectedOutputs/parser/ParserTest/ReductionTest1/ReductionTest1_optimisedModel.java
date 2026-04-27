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
 * Class representing the Sandwood model ReductionTest1 This is the class that all
 * user interactions with the model should occur through.
 */
public final class ReductionTest1 extends Model<ReductionTest1.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		int[][] ObsArr;
		int T;
		double[][] TimeFeat;
		int[][] arr;
		boolean[][] constrainedFlag$sample101;
		boolean fixedFlag$sample101 = false;
		boolean fixedProbFlag$sample101 = false;
		boolean fixedProbFlag$sample165 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$arr;
		double[][] logProbability$sample101;
		double[][] logProbability$sample165;
		double logProbability$sum_t;
		double logProbability$time_coeff;
		double logProbability$time_impact;
		int n_ac;
		double[][] sum_t;
		boolean system$gibbsForward = true;
		double[][] time_coeff;
		int time_dim;
		double[][][] time_impact;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// If time_coeff has not been set already allocate space.
			if(!fixedFlag$sample101) {
				// Constructor for time_coeff
				time_coeff = new double[n_ac][];
				for(int var18 = 0; var18 < n_ac; var18 += 1)
					time_coeff[var18] = new double[TimeFeat[0].length];
				for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
					time_coeff[i$var80] = new double[TimeFeat[0].length];
			}
			
			// Constructor for sum_t
			sum_t = new double[T][];
			for(int var31 = 0; var31 < T; var31 += 1)
				sum_t[var31] = new double[n_ac];
			
			// Constructor for time_impact
			time_impact = new double[T][][];
			for(int var44 = 0; var44 < T; var44 += 1) {
				double[][] subarray$0 = new double[n_ac][];
				time_impact[var44] = subarray$0;
				for(int var54 = 0; var54 < n_ac; var54 += 1)
					subarray$0[var54] = new double[TimeFeat[0].length];
			}
			
			// Constructor for arr
			arr = new int[T][];
			for(int var68 = 0; var68 < T; var68 += 1)
				arr[var68] = new int[n_ac];
			
			// Constructor for constrainedFlag$sample101
			constrainedFlag$sample101 = new boolean[n_ac][];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				constrainedFlag$sample101[i$var80] = new boolean[TimeFeat[0].length];
			
			// Constructor for logProbability$sample101
			logProbability$sample101 = new double[n_ac][];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				logProbability$sample101[i$var80] = new double[TimeFeat[0].length];
			
			// Constructor for logProbability$sample165
			logProbability$sample165 = new double[(T - 1)][];
			for(int t = 1; t < T; t += 1)
				logProbability$sample165[(t - 1)] = new double[n_ac];
		}

		// Getter for ObsArr.
		final int[][] get$ObsArr() {
			return ObsArr;
		}

		// Setter for ObsArr.
		final void set$ObsArr(int[][] cv$value, boolean allocated$) {
			ObsArr = cv$value;
		}

		// Getter for T.
		final int get$T() {
			return T;
		}

		// Setter for T.
		final void set$T(int cv$value, boolean allocated$) {
			T = cv$value;
		}

		// Getter for TimeFeat.
		final double[][] get$TimeFeat() {
			return TimeFeat;
		}

		// Setter for TimeFeat.
		final void set$TimeFeat(double[][] cv$value, boolean allocated$) {
			TimeFeat = cv$value;
		}

		// Getter for arr.
		final int[][] get$arr() {
			return arr;
		}

		// Getter for fixedFlag$sample101.
		final boolean get$fixedFlag$sample101() {
			return fixedFlag$sample101;
		}

		// Setter for fixedFlag$sample101.
		final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample101 including if probabilities
			// need to be updated.
			fixedFlag$sample101 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
					boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
					for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
						cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
				}
			}
			
			// Should the probability of sample 101 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample101" with its value "cv$value".
			fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
			
			// Should the probability of sample 165 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample101" with its value "cv$value".
			fixedProbFlag$sample165 = (cv$value && fixedProbFlag$sample165);
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

		// Getter for logProbability$arr.
		final double get$logProbability$arr() {
			return logProbability$arr;
		}

		// Getter for logProbability$sum_t.
		final double get$logProbability$sum_t() {
			return logProbability$sum_t;
		}

		// Getter for logProbability$time_coeff.
		final double get$logProbability$time_coeff() {
			return logProbability$time_coeff;
		}

		// Getter for logProbability$time_impact.
		final double get$logProbability$time_impact() {
			return logProbability$time_impact;
		}

		// Getter for n_ac.
		final int get$n_ac() {
			return n_ac;
		}

		// Setter for n_ac.
		final void set$n_ac(int cv$value, boolean allocated$) {
			n_ac = cv$value;
		}

		// Getter for sum_t.
		final double[][] get$sum_t() {
			return sum_t;
		}

		// Getter for time_coeff.
		final double[][] get$time_coeff() {
			return time_coeff;
		}

		// Setter for time_coeff.
		final void set$time_coeff(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of time_coeff including if probabilities need
			// to be updated.
			time_coeff = cv$value;
			
			// Unset the fixed probability flag for sample 101 as it depends on time_coeff.
			fixedProbFlag$sample101 = false;
			
			// Unset the fixed probability flag for sample 165 as it depends on time_coeff.
			fixedProbFlag$sample165 = false;
		}

		// Getter for time_dim.
		final int get$time_dim() {
			return time_dim;
		}

		// Getter for time_impact.
		final double[][][] get$time_impact() {
			return time_impact;
		}
	}

    private final ComputedObjectArrayInternal<int[]> $arr = new ComputedObjectArrayInternal<int[]>(this, "arr", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$arr(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable arr because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$arr(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing arr of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> arr = $arr;

    private final ComputedObjectArrayInternal<double[]> $sum_t = new ComputedObjectArrayInternal<double[]>(this, "sum_t", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$sum_t(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable sum_t because its value depends on variables \"time_coeff\", and \"time_impact\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$sum_t(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample101())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing sum_t of type double[][] from the Sandwood model.
	 */
    public final ComputedObjectArray<double[]> sum_t = $sum_t;

    private final ComputedObjectArrayInternal<double[]> $time_coeff = new ComputedObjectArrayInternal<double[]>(this, "time_coeff", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$time_coeff(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$time_coeff(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$time_coeff(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample101())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing time_coeff of type double[][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[]> time_coeff = $time_coeff;

    private final ComputedObjectArrayInternal<double[][]> $time_impact = new ComputedObjectArrayInternal<double[][]>(this, "time_impact", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() { return state.get$time_impact(); }

        @Override
        protected void setValueInternal(double[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable time_impact because its value depends on variable \"time_coeff\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$time_impact(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample101())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing time_impact of type double[][][] from the Sandwood
	 * model.
	 */
    public final ComputedObjectArray<double[][]> time_impact = $time_impact;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $T = new ObservedIntegerInternal(this, "T") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$T();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$T(value, allocated); }
    };

	/** Observed variable representing T of type int from the Sandwood model. */
    public final ObservedInteger T = $T;

    private final ObservedObjectArrayInternal<double[]> $TimeFeat = new ObservedObjectArrayInternal<double[]>(this, "TimeFeat", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() {
            synchronized(model) {
                return state.get$TimeFeat();
            }
        }

        @Override
        protected void setValueInternal(double[][] value) { state.set$TimeFeat(value, allocated); }
    };

	/**
	 * Observed variable representing TimeFeat of type double[][] from the Sandwood model.
	 */
    public final ObservedObjectArray<double[]> TimeFeat = $TimeFeat;

    private final ObservedIntegerInternal $n_ac = new ObservedIntegerInternal(this, "n_ac") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$n_ac();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$n_ac(value, allocated); }
    };

	/** Observed variable representing n_ac of type int from the Sandwood model. */
    public final ObservedInteger n_ac = $n_ac;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $ObsArr = new ObservedObjectArrayInternal<int[]>(this, "ObsArr", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$ObsArr();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { state.set$ObsArr(value, allocated); }
    };

	/** Observed variable representing ObsArr of type int[][] from the Sandwood model. */
    public final ObservedObjectArray<int[]> ObsArr = $ObsArr;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$arr, $sum_t, $time_coeff, $time_impact};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public ReductionTest1() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("arr", $arr);
        $computedVariables.put("sum_t", $sum_t);
        $computedVariables.put("time_coeff", $time_coeff);
        $computedVariables.put("time_impact", $time_impact);

        //ModelInputs
        $modelInputs.put("T", $T);
        $modelInputs.put("TimeFeat", $TimeFeat);
        $modelInputs.put("n_ac", $n_ac);

        //Observed scalar fields
        $regularObservedValues.put("ObsArr", $ObsArr);

        ReductionTest1$SingleThreadCPU core = new ReductionTest1$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param T The value to set T to.
	 * @param n_ac The value to set n_ac to.
	 * @param TimeFeat The value to set TimeFeat to.
	 */
    public ReductionTest1(int T, int n_ac, double[][] TimeFeat) {
        this();
        this.$T.setValue(T);
        this.$TimeFeat.setValue(TimeFeat);
        this.$n_ac.setValue(n_ac);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param T The value to set T to.
	 * @param n_ac The value to set n_ac to
	 * @param ObsArr The value to set ObsArr to
	 * @param TimeFeat The value to set TimeFeat to
	 */
    public ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {
        this();
        this.T.setValue(T);
        this.n_ac.setValue(n_ac);
        this.ObsArr.setValue(ObsArr);
        this.TimeFeat.setValue(TimeFeat);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new ReductionTest1$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new ReductionTest1$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input T */
        public final int T;
		/** Field holding the value of model input n_ac */
        public final int n_ac;
		/** Field holding the value of model input TimeFeat */
        public final double[][] TimeFeat;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param T The value to set T to.
		 * @param n_ac The value to set n_ac to.
		 * @param TimeFeat The value to set TimeFeat to.
		 */
        public InferValueInputs(int T, int n_ac, double[][] TimeFeat) {
            this.T = T;
            this.TimeFeat = TimeFeat;
            this.n_ac = n_ac;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input T */
        public final int T;
		/** Field holding the value of model input n_ac */
        public final int n_ac;
		/** Field holding the value of model input ObsArr */
        public final int[][] ObsArr;
		/** Field holding the value of model input TimeFeat */
        public final double[][] TimeFeat;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param T The value to set T to.
		 * @param n_ac The value to set n_ac to.
		 * @param ObsArr The value to set ObsArr to.
		 * @param TimeFeat The value to set TimeFeat to.
		 */
        public AllInputs(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {
            this.T = T;
            this.n_ac = n_ac;
            this.ObsArr = ObsArr;
            this.TimeFeat = TimeFeat;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of arr after a convention execution step. */
        public final int[][] arr;
		/** Field holding the value of sum_t after a convention execution step. */
        public final double[][] sum_t;
		/** Field holding the value of time_coeff after a convention execution step. */
        public final double[][] time_coeff;
		/** Field holding the value of time_impact after a convention execution step. */
        public final double[][][] time_impact;

        InferredValueOutputs(ReductionTest1 system$model) {
            this.arr = system$model.arr.getSamples()[0];
            this.sum_t = system$model.sum_t.getSamples()[0];
            this.time_coeff = system$model.time_coeff.getSamples()[0];
            this.time_impact = system$model.time_impact.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable arr */
        public final double arr;
		/** Field holding the log probability of computed variable sum_t */
        public final double sum_t;
		/** Field holding the log probability of computed variable time_coeff */
        public final double time_coeff;
		/** Field holding the log probability of computed variable time_impact */
        public final double time_impact;

        LogProbabilities(ReductionTest1 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.arr = system$model.arr.getLogProbability();
            this.sum_t = system$model.sum_t.getLogProbability();
            this.time_coeff = system$model.time_coeff.getLogProbability();
            this.time_impact = system$model.time_impact.getLogProbability();
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
		/** Field holding the probability of computed variable arr */
        public final double arr;
		/** Field holding the probability of computed variable sum_t */
        public final double sum_t;
		/** Field holding the probability of computed variable time_coeff */
        public final double time_coeff;
		/** Field holding the probability of computed variable time_impact */
        public final double time_impact;

        Probabilities(ReductionTest1 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.arr = system$model.arr.getProbability();
            this.sum_t = system$model.sum_t.getProbability();
            this.time_coeff = system$model.time_coeff.getProbability();
            this.time_impact = system$model.time_impact.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of sum_t after an infer model call. */
        public final double[][][] sum_t;
		/** Field holding the MAP or Sample value of time_coeff after an infer model call. */
        public final double[][][] time_coeff;
		/** Field holding the MAP or Sample value of time_impact after an infer model call. */
        public final double[][][][] time_impact;

        InferredModelOutputs(ReductionTest1 system$model) {
            this.sum_t = system$model.getInferredValue(system$model.$sum_t);
            this.time_coeff = system$model.getInferredValue(system$model.$time_coeff);
            this.time_impact = system$model.getInferredValue(system$model.$time_impact);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
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
        this.T.setValue(inputs.T);
        this.TimeFeat.setValue(inputs.TimeFeat);
        this.n_ac.setValue(inputs.n_ac);
        this.$ObsArr.setValue(inputs.ObsArr);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}