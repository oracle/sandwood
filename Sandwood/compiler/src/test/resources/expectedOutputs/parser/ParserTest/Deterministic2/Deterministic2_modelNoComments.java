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

/** A model for the fairness work. */
public final class Deterministic2 extends Model<Deterministic2.State> {
	final class State extends CoreModelState {
int[] a;
		int[] b;
		boolean[] constrainedFlag$sample29;
		boolean[] constrainedFlag$sample55;
		double[][] distribution$sample55;
		boolean fixedFlag$sample29 = false;
		boolean fixedFlag$sample55 = false;
		boolean fixedProbFlag$sample29 = false;
		boolean fixedProbFlag$sample55 = false;
		boolean fixedProbFlag$sample75 = false;
		boolean[] flips;
		boolean[] flipsMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$a;
		double logProbability$b;
		double logProbability$flips;
		double logProbability$m;
		double[] logProbability$sample55;
		double[] logProbability$sample75;
		double logProbability$var29;
		double[][] m;
		int n;
		int states;
		boolean system$gibbsForward = true;
		double[] v;

		@Override
		public final void allocate() {
			{
				v = new double[5];
			}
			if(!fixedFlag$sample29) {
				{
					m = new double[5][];
					for(int var28 = 0; var28 < 5; var28 += 1)
						m[var28] = new double[5];
				}
			}
			if(!fixedFlag$sample55) {
				{
					a = new int[n];
				}
			}
			{
				b = new int[n];
			}
			{
				flips = new boolean[n];
			}
			{
				distribution$sample55 = new double[((((n - 1) - 1) / 1) + 1)][];
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
					distribution$sample55[((i$var46 - 1) / 1)] = new double[5];
			}
			{
				constrainedFlag$sample29 = new boolean[((((5 - 1) - 0) / 1) + 1)];
			}
			{
				constrainedFlag$sample55 = new boolean[((((n - 1) - 1) / 1) + 1)];
			}
			{
				logProbability$sample55 = new double[((((n - 1) - 1) / 1) + 1)];
			}
			{
				logProbability$sample75 = new double[((((n - 1) - 0) / 1) + 1)];
			}
		}

		final int[] get$a() {
			return a;
		}

		final void set$a(int[] cv$value, boolean allocated$) {
			a = cv$value;
			fixedProbFlag$sample55 = false;
			fixedProbFlag$sample75 = false;
		}

		final int[] get$b() {
			return b;
		}

		final double[][] get$distribution$sample55() {
			return distribution$sample55;
		}

		final void set$distribution$sample55(double[][] cv$value, boolean allocated$) {
			distribution$sample55 = cv$value;
		}

		final boolean get$fixedFlag$sample29() {
			return fixedFlag$sample29;
		}

		final void set$fixedFlag$sample29(boolean cv$value, boolean allocated$) {
			fixedFlag$sample29 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
					constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
			}
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
			fixedProbFlag$sample55 = (fixedFlag$sample29 && fixedProbFlag$sample55);
		}

		final boolean get$fixedFlag$sample55() {
			return fixedFlag$sample55;
		}

		final void set$fixedFlag$sample55(boolean cv$value, boolean allocated$) {
			fixedFlag$sample55 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
					constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
			}
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
			fixedProbFlag$sample75 = (fixedFlag$sample55 && fixedProbFlag$sample75);
		}

		final boolean[] get$flips() {
			return flips;
		}

		final boolean[] get$flipsMeasured() {
			return flipsMeasured;
		}

		final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
			flipsMeasured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$a() {
			return logProbability$a;
		}

		final double get$logProbability$b() {
			return logProbability$b;
		}

		final double get$logProbability$flips() {
			return logProbability$flips;
		}

		final double get$logProbability$m() {
			return logProbability$m;
		}

		final double[][] get$m() {
			return m;
		}

		final void set$m(double[][] cv$value, boolean allocated$) {
			m = cv$value;
			fixedProbFlag$sample29 = false;
			fixedProbFlag$sample55 = false;
		}

		final int get$n() {
			return n;
		}

		final void set$n(int cv$value, boolean allocated$) {
			n = cv$value;
		}

		final int get$states() {
			return states;
		}

		final double[] get$v() {
			return v;
		}
	}

    private final ComputedIntegerArrayInternal $a = new ComputedIntegerArrayInternal(this, "a", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$a(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$a(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$a(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample55(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample55())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing a of type int[] from the Sandwood model. */
    public final ComputedIntegerArray a = $a;

    private final ComputedIntegerArrayInternal $b = new ComputedIntegerArrayInternal(this, "b", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$b(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable b because its value depends on variable \"a\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$b(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample55(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample55())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing b of type int[] from the Sandwood model. */
    public final ComputedIntegerArray b = $b;

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
                state.set$fixedFlag$sample29(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample29())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing m of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> m = $m;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $n = new ObservedIntegerInternal(this, "n") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$n();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$n(value, allocated); }
    };

	/** Observed variable representing n of type int from the Sandwood model. */
    public final ObservedInteger n = $n;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayInternal $flipsMeasured = new ObservedBooleanArrayInternal(this, "flipsMeasured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return state.get$flipsMeasured();
            }
        }

        @Override
        protected void setValueInternal(boolean[] value) { state.set$flipsMeasured(value, allocated); }
    };

	/**
	 * Observed variable representing flipsMeasured of type boolean[] from the Sandwood
	 * model.
	 */
    public final ObservedBooleanArray flipsMeasured = $flipsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$a, $b, $flips, $m};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Deterministic2() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("a", $a);
        $computedVariables.put("b", $b);
        $computedVariables.put("flips", $flips);
        $computedVariables.put("m", $m);

        //ModelInputs
        $modelInputs.put("n", $n);

        //Observed scalar fields
        $regularObservedValues.put("flipsMeasured", $flipsMeasured);

        Deterministic2$SingleThreadCPU core = new Deterministic2$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param n The value to set n to.
	 */
    public Deterministic2(int n) {
        this();
        this.$n.setValue(n);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param n The value to set n to.
	 * @param flipsMeasured The value to set flipsMeasured to
	 */
    public Deterministic2(int n, boolean[] flipsMeasured) {
        this();
        this.n.setValue(n);
        this.flipsMeasured.setValue(flipsMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Deterministic2$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Deterministic2$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input n */
        public final int n;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param n The value to set n to.
		 */
        public InferValueInputs(int n) {
            this.n = n;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input n */
        public final int n;
		/** Field holding the value of model input flipsMeasured */
        public final boolean[] flipsMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param n The value to set n to.
		 * @param flipsMeasured The value to set flipsMeasured to.
		 */
        public AllInputs(int n, boolean[] flipsMeasured) {
            this.n = n;
            this.flipsMeasured = flipsMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of a after a convention execution step. */
        public final int[] a;
		/** Field holding the value of b after a convention execution step. */
        public final int[] b;
		/** Field holding the value of flips after a convention execution step. */
        public final boolean[] flips;
		/** Field holding the value of m after a convention execution step. */
        public final double[][] m;

        InferredValueOutputs(Deterministic2 system$model) {
            this.a = system$model.a.getSamples()[0];
            this.b = system$model.b.getSamples()[0];
            this.flips = system$model.flips.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable a */
        public final double a;
		/** Field holding the log probability of computed variable b */
        public final double b;
		/** Field holding the log probability of computed variable flips */
        public final double flips;
		/** Field holding the log probability of computed variable m */
        public final double m;

        LogProbabilities(Deterministic2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.a = system$model.a.getLogProbability();
            this.b = system$model.b.getLogProbability();
            this.flips = system$model.flips.getLogProbability();
            this.m = system$model.m.getLogProbability();
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
		/** Field holding the probability of computed variable a */
        public final double a;
		/** Field holding the probability of computed variable b */
        public final double b;
		/** Field holding the probability of computed variable flips */
        public final double flips;
		/** Field holding the probability of computed variable m */
        public final double m;

        Probabilities(Deterministic2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.a = system$model.a.getProbability();
            this.b = system$model.b.getProbability();
            this.flips = system$model.flips.getProbability();
            this.m = system$model.m.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of a after an infer model call. */
        public final int[][] a;
		/** Field holding the MAP or Sample value of b after an infer model call. */
        public final int[][] b;
		/** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;

        InferredModelOutputs(Deterministic2 system$model) {
            this.a = system$model.getInferredValue(system$model.$a);
            this.b = system$model.getInferredValue(system$model.$b);
            this.m = system$model.getInferredValue(system$model.$m);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
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
        this.n.setValue(inputs.n);
        this.$flipsMeasured.setValue(inputs.flipsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}