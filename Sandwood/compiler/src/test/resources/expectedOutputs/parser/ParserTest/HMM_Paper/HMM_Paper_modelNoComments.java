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
 * Class representing the Sandwood model HMM_Paper This is the class that all user
 * interactions with the model should occur through.
 */
public final class HMM_Paper extends Model<HMM_Paper.State> {
	final class State extends CoreModelState {
double[] bias;
		boolean[] constrainedFlag$sample28;
		boolean constrainedFlag$sample32 = true;
		boolean[] constrainedFlag$sample47;
		boolean constrainedFlag$sample53 = true;
		boolean[] constrainedFlag$sample71;
		boolean fixedFlag$sample28 = false;
		boolean fixedFlag$sample32 = false;
		boolean fixedFlag$sample47 = false;
		boolean fixedFlag$sample53 = false;
		boolean fixedFlag$sample71 = false;
		boolean fixedProbFlag$sample28 = false;
		boolean fixedProbFlag$sample32 = false;
		boolean fixedProbFlag$sample47 = false;
		boolean fixedProbFlag$sample53 = false;
		boolean fixedProbFlag$sample71 = false;
		boolean fixedProbFlag$sample87 = false;
		boolean[] flips;
		double[] initialCoin;
		int length$measured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$bias;
		double logProbability$flips;
		double logProbability$initialCoin;
		double logProbability$m;
		double[] logProbability$sample71;
		double[] logProbability$sample87;
		double logProbability$st;
		double logProbability$var28;
		double logProbability$var46;
		double logProbability$var52;
		double[][] m;
		boolean[] measured;
		int nCoins;
		int nFlips;
		int[] st;
		boolean system$gibbsForward = true;
		double[] v;

		@Override
		public final void allocate() {
			{
				v = new double[nCoins];
			}
			if(!fixedFlag$sample28) {
				{
					m = new double[nCoins][];
					for(int var27 = 0; var27 < nCoins; var27 += 1)
						m[var27] = new double[nCoins];
				}
			}
			if(!fixedFlag$sample32) {
				{
					initialCoin = new double[nCoins];
				}
			}
			if(!fixedFlag$sample47) {
				{
					bias = new double[nCoins];
				}
			}
			if((!fixedFlag$sample53 || !fixedFlag$sample71)) {
				{
					st = new int[length$measured];
				}
			}
			{
				flips = new boolean[length$measured];
			}
			{
				constrainedFlag$sample47 = new boolean[((((nCoins - 1) - 0) / 1) + 1)];
			}
			{
				constrainedFlag$sample28 = new boolean[((((nCoins - 1) - 0) / 1) + 1)];
			}
			{
				constrainedFlag$sample71 = new boolean[((((length$measured - 1) - 1) / 1) + 1)];
			}
			{
				logProbability$sample71 = new double[((((length$measured - 1) - 1) / 1) + 1)];
			}
			{
				logProbability$sample87 = new double[((((length$measured - 1) - 0) / 1) + 1)];
			}
		}

		final double[] get$bias() {
			return bias;
		}

		final void set$bias(double[] cv$value, boolean allocated$) {
			bias = cv$value;
			fixedProbFlag$sample47 = false;
			fixedProbFlag$sample87 = false;
		}

		final boolean get$fixedFlag$sample28() {
			return fixedFlag$sample28;
		}

		final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
			fixedFlag$sample28 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
					constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
			}
			fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
			fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedProbFlag$sample71);
		}

		final boolean get$fixedFlag$sample32() {
			return fixedFlag$sample32;
		}

		final void set$fixedFlag$sample32(boolean cv$value, boolean allocated$) {
			fixedFlag$sample32 = cv$value;
			constrainedFlag$sample32 = (fixedFlag$sample32 || constrainedFlag$sample32);
			fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
			fixedProbFlag$sample53 = (fixedFlag$sample32 && fixedProbFlag$sample53);
		}

		final boolean get$fixedFlag$sample47() {
			return fixedFlag$sample47;
		}

		final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
			fixedFlag$sample47 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
					constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
			}
			fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
			fixedProbFlag$sample87 = (fixedFlag$sample47 && fixedProbFlag$sample87);
		}

		final boolean get$fixedFlag$sample53() {
			return fixedFlag$sample53;
		}

		final void set$fixedFlag$sample53(boolean cv$value, boolean allocated$) {
			fixedFlag$sample53 = cv$value;
			constrainedFlag$sample53 = (fixedFlag$sample53 || constrainedFlag$sample53);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
			fixedProbFlag$sample71 = (fixedFlag$sample53 && fixedProbFlag$sample71);
			fixedProbFlag$sample87 = (fixedFlag$sample53 && fixedProbFlag$sample87);
		}

		final boolean get$fixedFlag$sample71() {
			return fixedFlag$sample71;
		}

		final void set$fixedFlag$sample71(boolean cv$value, boolean allocated$) {
			fixedFlag$sample71 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
					constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
			}
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
			fixedProbFlag$sample87 = (fixedFlag$sample71 && fixedProbFlag$sample87);
		}

		final boolean[] get$flips() {
			return flips;
		}

		final double[] get$initialCoin() {
			return initialCoin;
		}

		final void set$initialCoin(double[] cv$value, boolean allocated$) {
			initialCoin = cv$value;
			fixedProbFlag$sample32 = false;
			fixedProbFlag$sample53 = false;
		}

		final int get$length$measured() {
			return length$measured;
		}

		final void set$length$measured(int cv$value, boolean allocated$) {
			length$measured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$bias() {
			return logProbability$bias;
		}

		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		final double get$logProbability$initialCoin() {
			return logProbability$initialCoin;
		}

		final double get$logProbability$m() {
			return logProbability$m;
		}

		final double get$logProbability$st() {
			return logProbability$st;
		}

		final double[][] get$m() {
			return m;
		}

		final void set$m(double[][] cv$value, boolean allocated$) {
			m = cv$value;
			fixedProbFlag$sample28 = false;
			fixedProbFlag$sample71 = false;
		}

		final boolean[] get$measured() {
			return measured;
		}

		final void set$measured(boolean[] cv$value, boolean allocated$) {
			measured = cv$value;
		}

		final int get$nCoins() {
			return nCoins;
		}

		final void set$nCoins(int cv$value, boolean allocated$) {
			nCoins = cv$value;
		}

		final int get$nFlips() {
			return nFlips;
		}

		final int[] get$st() {
			return st;
		}

		final void set$st(int[] cv$value, boolean allocated$) {
			st = cv$value;
			fixedProbFlag$sample53 = false;
			fixedProbFlag$sample71 = false;
			fixedProbFlag$sample87 = false;
		}

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
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing flips of type boolean[] from the Sandwood model. */
    public final ComputedBooleanArray flips = $flips;

    private final ComputedDoubleArrayInternal $initialCoin = new ComputedDoubleArrayInternal(this, "initialCoin", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$initialCoin(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$initialCoin(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$initialCoin(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample32(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample32())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing initialCoin of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray initialCoin = $initialCoin;

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
                state.set$fixedFlag$sample53(fixed, allocated);
                state.set$fixedFlag$sample71(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample53 = state.get$fixedFlag$sample53();
            boolean fixedFlag$sample71 = state.get$fixedFlag$sample71();
            if(fixedFlag$sample53 && fixedFlag$sample71)
                return Immutability.FIXED;
            else if(fixedFlag$sample53 || fixedFlag$sample71)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing st of type int[] from the Sandwood model. */
    public final ComputedIntegerArray st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $nCoins = new ObservedIntegerInternal(this, "nCoins") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$nCoins();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$nCoins(value, allocated); }
    };

	/** Observed variable representing nCoins of type int from the Sandwood model. */
    public final ObservedInteger nCoins = $nCoins;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $measured = new ObservedBooleanArrayShapeableInternal(this, "measured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$measured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            state.set$measured(value, allocated);
            state.set$length$measured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$measured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$measured();
        }
    };

	/**
	 * Observed variable representing measured of type boolean[] from the Sandwood model.
	 */
    public final ObservedBooleanArrayShapeable measured = $measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $flips, $initialCoin, $m, $st};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public HMM_Paper() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("initialCoin", $initialCoin);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("nCoins", $nCoins);

        //Observed array fields
        $shapedObservedValues.put("measured", $measured);

        HMM_Paper$SingleThreadCPU core = new HMM_Paper$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param measuredShape An integer array describing the shape of variable measured
	 *                      to use in the model when generating results.
	 * @param nCoins The value to set nCoins to.
	 */
    public HMM_Paper(int measuredShape, int nCoins) {
        this();
        this.$nCoins.setValue(nCoins);
        this.$measured.setShape(measuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param measured The value to set measured to.
	 * @param nCoins The value to set nCoins to
	 */
    public HMM_Paper(boolean[] measured, int nCoins) {
        this();
        this.measured.setValue(measured);
        this.nCoins.setValue(nCoins);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new HMM_Paper$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new HMM_Paper$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input measured */
        public final int measuredShape;
		/** Field holding the value of model input nCoins */
        public final int nCoins;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param measuredShape An integer array describing the shape of variable measured
		 *                      to use in the model when generating results.
		 * @param nCoins The value to set nCoins to.
		 */
        public InferValueInputs(int measuredShape, int nCoins) {
            this.nCoins = nCoins;
            this.measuredShape = measuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input measured */
        public final boolean[] measured;
		/** Field holding the value of model input nCoins */
        public final int nCoins;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param measured The value to set measured to.
		 * @param nCoins The value to set nCoins to.
		 */
        public AllInputs(boolean[] measured, int nCoins) {
            this.measured = measured;
            this.nCoins = nCoins;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of bias after a convention execution step. */
        public final double[] bias;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;
		/** Field holding the value of initialCoin after a convention execution step. */
        public final double[] initialCoin;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;
		/** Field holding the value of st after a convention execution step. */
        public final int[] st;

        InferredValueOutputs(HMM_Paper system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.initialCoin = system$model.initialCoin.getSamples()[0];
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
		/** Field holding the log probability of computed variable initialCoin */
        public final double initialCoin;
		/** Field holding the log probability of computed variable m */
        public final double m;
		/** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMM_Paper system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.initialCoin = system$model.initialCoin.getLogProbability();
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
		/** Field holding the probability of computed variable initialCoin */
        public final double initialCoin;
		/** Field holding the probability of computed variable m */
        public final double m;
		/** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMM_Paper system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.flips = system$model.flips.getProbability();
            this.initialCoin = system$model.initialCoin.getProbability();
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
		/** Field holding the MAP or Sample value of initialCoin after an infer model call. */
        public final double[][] initialCoin;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
		/** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][] st;

        InferredModelOutputs(HMM_Paper system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.initialCoin = system$model.getInferredValue(system$model.$initialCoin);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setShape(inputs.measuredShape);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
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
        this.nCoins.setValue(inputs.nCoins);
        this.$measured.setValue(inputs.measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}