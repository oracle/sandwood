package org.sandwood.compiler.tests.parser;

import java.util.HashMap;
import java.util.Map;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.CoreModelBase;
import org.sandwood.runtime.internal.model.ModelInternal;
import org.sandwood.runtime.internal.model.state.CoreModelState;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;

/**
 * Class representing the Sandwood model LowDimMix This is the class that all user
 * interactions with the model should occur through.
 */
public final class LowDimMix extends ModelInternal<LowDimMix.State> {
	final class State extends CoreModelState {
int N;
		boolean[] component;
		boolean[] constrainedFlag$sample101;
		boolean[] constrainedFlag$sample20;
		boolean[] constrainedFlag$sample83;
		boolean constrainedFlag$sample88 = true;
		boolean fixedFlag$sample101 = false;
		boolean fixedFlag$sample20 = false;
		boolean fixedFlag$sample83 = false;
		boolean fixedFlag$sample88 = false;
		boolean fixedProbFlag$sample101 = false;
		boolean fixedProbFlag$sample138 = false;
		boolean fixedProbFlag$sample20 = false;
		boolean fixedProbFlag$sample83 = false;
		boolean fixedProbFlag$sample88 = false;
		int length$yObserved;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$component;
		double logProbability$componentDistribution;
		double logProbability$mu;
		double logProbability$rawMu;
		double[] logProbability$sample138;
		double[] logProbability$sample20;
		double logProbability$sigma;
		double logProbability$theta;
		double logProbability$var79;
		double logProbability$var97;
		double logProbability$y;
		double[] mu;
		double[] rawMu;
		double[] sigma;
		boolean system$gibbsForward = true;
		double theta;
		double[] y;
		double[] yObserved;

		@Override
		public final void allocate() {
			if(!fixedFlag$sample20)
				rawMu = new double[2];
			mu = new double[2];
			if(!fixedFlag$sample83)
				sigma = new double[2];
			if(!fixedFlag$sample101)
				component = new boolean[length$yObserved];
			y = new double[length$yObserved];
			constrainedFlag$sample101 = new boolean[length$yObserved];
			constrainedFlag$sample20 = new boolean[2];
			constrainedFlag$sample83 = new boolean[2];
			logProbability$sample20 = new double[2];
			logProbability$sample138 = new double[length$yObserved];
		}

		final int get$N() {
			return N;
		}

		final boolean[] get$component() {
			return component;
		}

		final void set$component(boolean[] cv$value, boolean allocated$) {
			component = cv$value;
			fixedProbFlag$sample101 = false;
			fixedProbFlag$sample138 = false;
		}

		final boolean get$fixedFlag$sample101() {
			return fixedFlag$sample101;
		}

		final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
			fixedFlag$sample101 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
					constrainedFlag$sample101[index$constrainedFlag$sample101$1] = cv$value;
			}
			fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
			fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
		}

		final boolean get$fixedFlag$sample20() {
			return fixedFlag$sample20;
		}

		final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
			fixedFlag$sample20 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
					constrainedFlag$sample20[index$constrainedFlag$sample20$1] = cv$value;
			}
			fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
			fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
		}

		final boolean get$fixedFlag$sample83() {
			return fixedFlag$sample83;
		}

		final void set$fixedFlag$sample83(boolean cv$value, boolean allocated$) {
			fixedFlag$sample83 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
					constrainedFlag$sample83[index$constrainedFlag$sample83$1] = cv$value;
			}
			fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
			fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
		}

		final boolean get$fixedFlag$sample88() {
			return fixedFlag$sample88;
		}

		final void set$fixedFlag$sample88(boolean cv$value, boolean allocated$) {
			fixedFlag$sample88 = cv$value;
			constrainedFlag$sample88 = (cv$value || constrainedFlag$sample88);
			fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
			fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
		}

		final int get$length$yObserved() {
			return length$yObserved;
		}

		final void set$length$yObserved(int cv$value, boolean allocated$) {
			length$yObserved = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$component() {
			return logProbability$component;
		}

		final double get$logProbability$componentDistribution() {
			return logProbability$componentDistribution;
		}

		final double get$logProbability$mu() {
			return logProbability$mu;
		}

		final double get$logProbability$rawMu() {
			return logProbability$rawMu;
		}

		final double get$logProbability$sigma() {
			return logProbability$sigma;
		}

		final double get$logProbability$theta() {
			return logProbability$theta;
		}

		final double get$logProbability$y() {
			return logProbability$y;
		}

		final double[] get$mu() {
			return mu;
		}

		final double[] get$rawMu() {
			return rawMu;
		}

		final void set$rawMu(double[] cv$value, boolean allocated$) {
			rawMu = cv$value;
			fixedProbFlag$sample20 = false;
			fixedProbFlag$sample138 = false;
		}

		final double[] get$sigma() {
			return sigma;
		}

		final void set$sigma(double[] cv$value, boolean allocated$) {
			sigma = cv$value;
			fixedProbFlag$sample83 = false;
			fixedProbFlag$sample138 = false;
		}

		final double get$theta() {
			return theta;
		}

		final void set$theta(double cv$value, boolean allocated$) {
			theta = cv$value;
			fixedProbFlag$sample88 = false;
			fixedProbFlag$sample101 = false;
		}

		final double[] get$y() {
			return y;
		}

		final double[] get$yObserved() {
			return yObserved;
		}

		final void set$yObserved(double[] cv$value, boolean allocated$) {
			yObserved = cv$value;
		}
	}

    private final ComputedBooleanArrayInternal $component = new ComputedBooleanArrayInternal(this, "component", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public boolean[] getValue() { return state.get$component(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            state.set$component(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$component(); }

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
	 * Computed variable representing component of type boolean[] from the Sandwood model.
	 */
    public final ComputedBooleanArray component = $component;

    private final ComputedDoubleArrayInternal $mu = new ComputedDoubleArrayInternal(this, "mu", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$mu(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable mu because its value depends on variable \"rawMu\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$mu(); }

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

	/** Computed variable representing mu of type double[] from the Sandwood model. */
    public final ComputedDoubleArray mu = $mu;

    private final ComputedDoubleArrayInternal $rawMu = new ComputedDoubleArrayInternal(this, "rawMu", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$rawMu(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$rawMu(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$rawMu(); }

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

	/** Computed variable representing rawMu of type double[] from the Sandwood model. */
    public final ComputedDoubleArray rawMu = $rawMu;

    private final ComputedDoubleArrayInternal $sigma = new ComputedDoubleArrayInternal(this, "sigma", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$sigma(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$sigma(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$sigma(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample83(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample83())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing sigma of type double[] from the Sandwood model. */
    public final ComputedDoubleArray sigma = $sigma;

    private final ComputedDoubleInternal $theta = new ComputedDoubleInternal(this, "theta", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$theta(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$theta(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$theta(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample88(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample88())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing theta of type double from the Sandwood model. */
    public final ComputedDouble theta = $theta;

    private final ComputedDoubleArrayInternal $y = new ComputedDoubleArrayInternal(this, "y", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$y(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable y because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing y of type double[] from the Sandwood model. */
    public final ComputedDoubleArray y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayShapeableInternal $yObserved = new ObservedDoubleArrayShapeableInternal(this, "yObserved") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$yObserved();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            state.set$yObserved(value, allocated);
            state.set$length$yObserved(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$yObserved(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$yObserved();
        }
    };

	/**
	 * Observed variable representing yObserved of type double[] from the Sandwood model.
	 */
    public final ObservedDoubleArrayShapeable yObserved = $yObserved;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private final RandomVariableInternal $componentDistribution = new RandomVariableInternal(this, "componentDistribution", ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getCurrentLogProbability() {
            return state.get$logProbability$componentDistribution();
        }
    };

	/** Random variable representing componentDistribution from the Sandwood model. */
    public final RandomVariable componentDistribution = $componentDistribution;

    private HasProbabilityInternal[] $probabilityVariables = {$component, $mu, $rawMu, $sigma, $theta, $y, $componentDistribution};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public LowDimMix() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("component", $component);
        $computedVariables.put("mu", $mu);
        $computedVariables.put("rawMu", $rawMu);
        $computedVariables.put("sigma", $sigma);
        $computedVariables.put("theta", $theta);
        $computedVariables.put("y", $y);

        //Observed array fields
        $shapedObservedValues.put("yObserved", $yObserved);

        LowDimMix$SingleThreadCPU core = new LowDimMix$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param yObservedShape An integer array describing the shape of variable yObserved
	 *                       to use in the model when generating results.
	 */
    public LowDimMix(int yObservedShape) {
        this();
        this.$yObserved.setShape(yObservedShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param yObserved The value to set yObserved to.
	 */
    public LowDimMix(double[] yObserved) {
        this();
        this.yObserved.setValue(yObserved);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new LowDimMix$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new LowDimMix$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input yObserved */
        public final int yObservedShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param yObservedShape An integer array describing the shape of variable yObserved
		 *                       to use in the model when generating results.
		 */
        public InferValueInputs(int yObservedShape) {
            this.yObservedShape = yObservedShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input yObserved */
        public final double[] yObserved;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param yObserved The value to set yObserved to.
		 */
        public AllInputs(double[] yObserved) {
            this.yObserved = yObserved;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of component after a convention execution step. */
        public final boolean[] component;
		/** Field holding the value of mu after a convention execution step. */
        public final double[] mu;
		/** Field holding the value of rawMu after a convention execution step. */
        public final double[] rawMu;
		/** Field holding the value of sigma after a convention execution step. */
        public final double[] sigma;
		/** Field holding the value of theta after a convention execution step. */
        public final double theta;
		/** Field holding the value of y after a convention execution step. */
        public final double[] y;

        InferredValueOutputs(LowDimMix system$model) {
            this.component = system$model.component.getSamples()[0];
            this.mu = system$model.mu.getSamples()[0];
            this.rawMu = system$model.rawMu.getSamples()[0];
            this.sigma = system$model.sigma.getSamples()[0];
            this.theta = system$model.theta.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of random variable componentDistribution */
        public final double componentDistribution;
		/** Field holding the log probability of computed variable component */
        public final double component;
		/** Field holding the log probability of computed variable mu */
        public final double mu;
		/** Field holding the log probability of computed variable rawMu */
        public final double rawMu;
		/** Field holding the log probability of computed variable sigma */
        public final double sigma;
		/** Field holding the log probability of computed variable theta */
        public final double theta;
		/** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(LowDimMix system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.componentDistribution = system$model.componentDistribution.getLogProbability();
            this.component = system$model.component.getLogProbability();
            this.mu = system$model.mu.getLogProbability();
            this.rawMu = system$model.rawMu.getLogProbability();
            this.sigma = system$model.sigma.getLogProbability();
            this.theta = system$model.theta.getLogProbability();
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
		/** Field holding the probability of random variable componentDistribution */
        public final double componentDistribution;
		/** Field holding the probability of computed variable component */
        public final double component;
		/** Field holding the probability of computed variable mu */
        public final double mu;
		/** Field holding the probability of computed variable rawMu */
        public final double rawMu;
		/** Field holding the probability of computed variable sigma */
        public final double sigma;
		/** Field holding the probability of computed variable theta */
        public final double theta;
		/** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(LowDimMix system$model) {
            this.$modelProbability = system$model.getProbability();
            this.componentDistribution = system$model.componentDistribution.getProbability();
            this.component = system$model.component.getProbability();
            this.mu = system$model.mu.getProbability();
            this.rawMu = system$model.rawMu.getProbability();
            this.sigma = system$model.sigma.getProbability();
            this.theta = system$model.theta.getProbability();
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
		/** Field holding the MAP or Sample value of component after an infer model call. */
        public final boolean[][] component;
		/** Field holding the MAP or Sample value of mu after an infer model call. */
        public final double[][] mu;
		/** Field holding the MAP or Sample value of rawMu after an infer model call. */
        public final double[][] rawMu;
		/** Field holding the MAP or Sample value of sigma after an infer model call. */
        public final double[][] sigma;
		/** Field holding the MAP or Sample value of theta after an infer model call. */
        public final double[] theta;

        InferredModelOutputs(LowDimMix system$model) {
            this.component = system$model.getInferredValue(system$model.$component);
            this.mu = system$model.getInferredValue(system$model.$mu);
            this.rawMu = system$model.getInferredValue(system$model.$rawMu);
            this.sigma = system$model.getInferredValue(system$model.$sigma);
            this.theta = system$model.getInferredValue(system$model.$theta);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$yObserved.setShape(inputs.yObservedShape);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
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
        this.$yObserved.setValue(inputs.yObserved);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}