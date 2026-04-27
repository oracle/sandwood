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
 * Class representing the Sandwood model GaussianMixtureTest This is the class that
 * all user interactions with the model should occur through.
 */
public final class GaussianMixtureTest extends Model<GaussianMixtureTest.State> {
	final class State extends CoreModelState {
double[] alpha;
		boolean constrainedFlag$sample17 = true;
		boolean[] constrainedFlag$sample34;
		boolean[] constrainedFlag$sample52;
		boolean[] constrainedFlag$sample68;
		boolean fixedFlag$sample17 = false;
		boolean fixedFlag$sample34 = false;
		boolean fixedFlag$sample52 = false;
		boolean fixedProbFlag$sample17 = false;
		boolean fixedProbFlag$sample34 = false;
		boolean fixedProbFlag$sample52 = false;
		int k;
		int length$xMeasured;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$mu;
		double logProbability$phi;
		double[] logProbability$sample68;
		double[] logProbability$sample72;
		double logProbability$sigma;
		double logProbability$var34;
		double logProbability$var52;
		double logProbability$x;
		double[] mu;
		double[] phi;
		double[] sigma;
		boolean system$gibbsForward = true;
		double[] x;
		double[] xMeasured;
		int[] z;

		@Override
		public final void allocate() {
			alpha = new double[5];
			if(!fixedFlag$sample17)
				phi = new double[5];
			if(!fixedFlag$sample34)
				mu = new double[5];
			if(!fixedFlag$sample52)
				sigma = new double[5];
			x = new double[length$xMeasured];
			z = new int[length$xMeasured];
			constrainedFlag$sample52 = new boolean[5];
			constrainedFlag$sample68 = new boolean[length$xMeasured];
			constrainedFlag$sample34 = new boolean[5];
			logProbability$sample68 = new double[length$xMeasured];
			logProbability$sample72 = new double[length$xMeasured];
		}

		final double[] get$alpha() {
			return alpha;
		}

		final boolean get$fixedFlag$sample17() {
			return fixedFlag$sample17;
		}

		final void set$fixedFlag$sample17(boolean cv$value, boolean allocated$) {
			fixedFlag$sample17 = cv$value;
			constrainedFlag$sample17 = (cv$value || constrainedFlag$sample17);
			fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		}

		final boolean get$fixedFlag$sample34() {
			return fixedFlag$sample34;
		}

		final void set$fixedFlag$sample34(boolean cv$value, boolean allocated$) {
			fixedFlag$sample34 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample34$1 = 0; index$constrainedFlag$sample34$1 < constrainedFlag$sample34.length; index$constrainedFlag$sample34$1 += 1)
					constrainedFlag$sample34[index$constrainedFlag$sample34$1] = true;
			}
			fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		}

		final boolean get$fixedFlag$sample52() {
			return fixedFlag$sample52;
		}

		final void set$fixedFlag$sample52(boolean cv$value, boolean allocated$) {
			fixedFlag$sample52 = cv$value;
			if(allocated$) {
				for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
					constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
			}
			fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		}

		final int get$k() {
			return 5;
		}

		final int get$length$xMeasured() {
			return length$xMeasured;
		}

		final void set$length$xMeasured(int cv$value, boolean allocated$) {
			length$xMeasured = cv$value;
		}

		@Override
		public final double get$logProbability$$evidence() {
			return logProbability$$evidence;
		}

		@Override
		public final double getCurrentLogProbability() {
			return logProbability$$model;
		}

		final double get$logProbability$mu() {
			return logProbability$mu;
		}

		final double get$logProbability$phi() {
			return logProbability$phi;
		}

		final double get$logProbability$sigma() {
			return logProbability$sigma;
		}

		final double get$logProbability$x() {
			return logProbability$x;
		}

		final double[] get$mu() {
			return mu;
		}

		final void set$mu(double[] cv$value, boolean allocated$) {
			mu = cv$value;
			fixedProbFlag$sample34 = false;
		}

		final double[] get$phi() {
			return phi;
		}

		final void set$phi(double[] cv$value, boolean allocated$) {
			phi = cv$value;
			fixedProbFlag$sample17 = false;
		}

		final double[] get$sigma() {
			return sigma;
		}

		final void set$sigma(double[] cv$value, boolean allocated$) {
			sigma = cv$value;
			fixedProbFlag$sample52 = false;
		}

		final double[] get$x() {
			return x;
		}

		final double[] get$xMeasured() {
			return xMeasured;
		}

		final void set$xMeasured(double[] cv$value, boolean allocated$) {
			xMeasured = cv$value;
		}

		final int[] get$z() {
			return z;
		}

		final void set$z(int[] cv$value, boolean allocated$) {
			z = cv$value;
		}
	}

    private final ComputedDoubleArrayInternal $mu = new ComputedDoubleArrayInternal(this, "mu", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$mu(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$mu(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$mu(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample34(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample34())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing mu of type double[] from the Sandwood model. */
    public final ComputedDoubleArray mu = $mu;

    private final ComputedDoubleArrayInternal $phi = new ComputedDoubleArrayInternal(this, "phi", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$phi(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$phi(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$phi(); }

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

	/** Computed variable representing phi of type double[] from the Sandwood model. */
    public final ComputedDoubleArray phi = $phi;

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
                state.set$fixedFlag$sample52(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample52())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing sigma of type double[] from the Sandwood model. */
    public final ComputedDoubleArray sigma = $sigma;

    private final ComputedDoubleArrayInternal $x = new ComputedDoubleArrayInternal(this, "x", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$x(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable x because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$x(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing x of type double[] from the Sandwood model. */
    public final ComputedDoubleArray x = $x;

    private final ComputedIntegerArrayInternal $z = new ComputedIntegerArrayInternal(this, "z", true, true, true, ProbabilityType.SKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$z(); }

        @Override
        protected void setValueInternal(int[] value) {
            state.set$z(value, allocated);
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

    private final ObservedDoubleArrayShapeableInternal $xMeasured = new ObservedDoubleArrayShapeableInternal(this, "xMeasured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return state.get$xMeasured();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            state.set$xMeasured(value, allocated);
            state.set$length$xMeasured(value.length, allocated);
        }

        @Override
        public void setShapeInternal(int shape) {
            state.set$length$xMeasured(shape, allocated);
        }

        @Override
        public int getShape() {
            return state.get$length$xMeasured();
        }
    };

	/**
	 * Observed variable representing xMeasured of type double[] from the Sandwood model.
	 */
    public final ObservedDoubleArrayShapeable xMeasured = $xMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$mu, $phi, $sigma, $x};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public GaussianMixtureTest() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("mu", $mu);
        $computedVariables.put("phi", $phi);
        $computedVariables.put("sigma", $sigma);
        $computedVariables.put("x", $x);
        $computedVariables.put("z", $z);

        //Observed array fields
        $shapedObservedValues.put("xMeasured", $xMeasured);

        GaussianMixtureTest$SingleThreadCPU core = new GaussianMixtureTest$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param xMeasuredShape An integer array describing the shape of variable xMeasured
	 *                       to use in the model when generating results.
	 */
    public GaussianMixtureTest(int xMeasuredShape) {
        this();
        this.$xMeasured.setShape(xMeasuredShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param xMeasured The value to set xMeasured to.
	 */
    public GaussianMixtureTest(double[] xMeasured) {
        this();
        this.xMeasured.setValue(xMeasured);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new GaussianMixtureTest$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new GaussianMixtureTest$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the shape of model input xMeasured */
        public final int xMeasuredShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param xMeasuredShape An integer array describing the shape of variable xMeasured
		 *                       to use in the model when generating results.
		 */
        public InferValueInputs(int xMeasuredShape) {
            this.xMeasuredShape = xMeasuredShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input xMeasured */
        public final double[] xMeasured;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param xMeasured The value to set xMeasured to.
		 */
        public AllInputs(double[] xMeasured) {
            this.xMeasured = xMeasured;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of mu after a convention execution step. */
        public final double[] mu;
		/** Field holding the value of phi after a convention execution step. */
        public final double[] phi;
		/** Field holding the value of sigma after a convention execution step. */
        public final double[] sigma;
		/** Field holding the value of x after a convention execution step. */
        public final double[] x;

        InferredValueOutputs(GaussianMixtureTest system$model) {
            this.mu = system$model.mu.getSamples()[0];
            this.phi = system$model.phi.getSamples()[0];
            this.sigma = system$model.sigma.getSamples()[0];
            this.x = system$model.x.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable mu */
        public final double mu;
		/** Field holding the log probability of computed variable phi */
        public final double phi;
		/** Field holding the log probability of computed variable sigma */
        public final double sigma;
		/** Field holding the log probability of computed variable x */
        public final double x;

        LogProbabilities(GaussianMixtureTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.mu = system$model.mu.getLogProbability();
            this.phi = system$model.phi.getLogProbability();
            this.sigma = system$model.sigma.getLogProbability();
            this.x = system$model.x.getLogProbability();
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
		/** Field holding the probability of computed variable mu */
        public final double mu;
		/** Field holding the probability of computed variable phi */
        public final double phi;
		/** Field holding the probability of computed variable sigma */
        public final double sigma;
		/** Field holding the probability of computed variable x */
        public final double x;

        Probabilities(GaussianMixtureTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.mu = system$model.mu.getProbability();
            this.phi = system$model.phi.getProbability();
            this.sigma = system$model.sigma.getProbability();
            this.x = system$model.x.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of mu after an infer model call. */
        public final double[][] mu;
		/** Field holding the MAP or Sample value of phi after an infer model call. */
        public final double[][] phi;
		/** Field holding the MAP or Sample value of sigma after an infer model call. */
        public final double[][] sigma;

        InferredModelOutputs(GaussianMixtureTest system$model) {
            this.mu = system$model.getInferredValue(system$model.$mu);
            this.phi = system$model.getInferredValue(system$model.$phi);
            this.sigma = system$model.getInferredValue(system$model.$sigma);
        }
    }

	/**
	 * Perform a single pass generating values from the model.
	 * @param inputs An object containing the parameters required to run inference on
	 *               the model.
	 * @return An object containing the values computed by the inference step.
	 */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$xMeasured.setShape(inputs.xMeasuredShape);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}