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
 * Class representing the Sandwood model Vulcano2012basic This is the class that all
 * user interactions with the model should occur through.
 */
public final class Vulcano2012basic extends Model<Vulcano2012basic.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		int[][] Avail;
		int[][] ObsSales;
		int[][] Sales;
		int T;
		boolean[] constrainedFlag$sample26;
		double[] exped;
		double[] expedNorm;
		boolean fixedFlag$sample26 = false;
		boolean fixedProbFlag$sample157 = false;
		boolean fixedProbFlag$sample26 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$Sales;
		double logProbability$exped;
		double logProbability$expedNorm;
		double[] logProbability$sample26;
		double logProbability$sum;
		double logProbability$ut;
		double logProbability$weekly_sales;
		int noProducts;
		double r;
		int[] sales_sum;
		double sum;
		boolean system$gibbsForward = true;
		double[] ut;
		double[][] weekly_rates;
		double[][] weekly_ut;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// If ut has not been set already allocate space.
			if(!fixedFlag$sample26)
				// Constructor for ut
				ut = new double[noProducts];
			
			// Constructor for exped
			exped = new double[noProducts];
			
			// Constructor for expedNorm
			expedNorm = new double[noProducts];
			
			// Constructor for sales_sum
			sales_sum = new int[T];
			
			// Constructor for Sales
			Sales = new int[T][];
			for(int var100 = 0; var100 < T; var100 += 1)
				Sales[var100] = new int[noProducts];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				Sales[t$var112] = new int[noProducts];
			
			// Constructor for weekly_rates
			weekly_rates = new double[T][];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				weekly_rates[t$var112] = new double[noProducts];
			
			// Constructor for weekly_ut
			weekly_ut = new double[T][];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				weekly_ut[t$var112] = new double[noProducts];
			
			// Constructor for constrainedFlag$sample26
			constrainedFlag$sample26 = new boolean[(noProducts - 1)];
			
			// Constructor for logProbability$sample26
			logProbability$sample26 = new double[(noProducts - 1)];
		}

		// Getter for Avail.
		final int[][] get$Avail() {
			return Avail;
		}

		// Setter for Avail.
		final void set$Avail(int[][] cv$value, boolean allocated$) {
			Avail = cv$value;
		}

		// Getter for ObsSales.
		final int[][] get$ObsSales() {
			return ObsSales;
		}

		// Setter for ObsSales.
		final void set$ObsSales(int[][] cv$value, boolean allocated$) {
			ObsSales = cv$value;
		}

		// Getter for Sales.
		final int[][] get$Sales() {
			return Sales;
		}

		// Getter for T.
		final int get$T() {
			return T;
		}

		// Setter for T.
		final void set$T(int cv$value, boolean allocated$) {
			T = cv$value;
		}

		// Getter for exped.
		final double[] get$exped() {
			return exped;
		}

		// Getter for expedNorm.
		final double[] get$expedNorm() {
			return expedNorm;
		}

		// Getter for fixedFlag$sample26.
		final boolean get$fixedFlag$sample26() {
			return fixedFlag$sample26;
		}

		// Setter for fixedFlag$sample26.
		final void set$fixedFlag$sample26(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
			// need to be updated.
			fixedFlag$sample26 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
					constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
			}
			
			// Should the probability of sample 26 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample26" with its value "cv$value".
			fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
			
			// Should the probability of sample 157 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample26" with its value "cv$value".
			fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
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

		// Getter for logProbability$Sales.
		final double get$logProbability$Sales() {
			return logProbability$Sales;
		}

		// Getter for logProbability$exped.
		final double get$logProbability$exped() {
			return logProbability$exped;
		}

		// Getter for logProbability$expedNorm.
		final double get$logProbability$expedNorm() {
			return logProbability$expedNorm;
		}

		// Getter for logProbability$sum.
		final double get$logProbability$sum() {
			return logProbability$sum;
		}

		// Getter for logProbability$ut.
		final double get$logProbability$ut() {
			return logProbability$ut;
		}

		// Getter for noProducts.
		final int get$noProducts() {
			return noProducts;
		}

		// Setter for noProducts.
		final void set$noProducts(int cv$value, boolean allocated$) {
			noProducts = cv$value;
		}

		// Getter for r.
		final double get$r() {
			return r;
		}

		// Setter for r.
		final void set$r(double cv$value, boolean allocated$) {
			r = cv$value;
		}

		// Getter for sales_sum.
		final int[] get$sales_sum() {
			return sales_sum;
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
			
			// Unset the fixed probability flag for sample 26 as it depends on ut.
			fixedProbFlag$sample26 = false;
			
			// Unset the fixed probability flag for sample 157 as it depends on ut.
			fixedProbFlag$sample157 = false;
		}
	}

    private final ComputedObjectArrayInternal<int[]> $Sales = new ComputedObjectArrayInternal<int[]>(this, "Sales", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$Sales(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable Sales because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$Sales(); }

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

	/** Computed variable representing Sales of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> Sales = $Sales;

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
                state.set$fixedFlag$sample26(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample26())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing exped of type double[] from the Sandwood model. */
    public final ComputedDoubleArray exped = $exped;

    private final ComputedDoubleArrayInternal $expedNorm = new ComputedDoubleArrayInternal(this, "expedNorm", false, false, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$expedNorm(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable expedNorm because its value depends on variables \"exped\", \"sum\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$expedNorm(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample26(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample26())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing expedNorm of type double[] from the Sandwood model.
	 */
    public final ComputedDoubleArray expedNorm = $expedNorm;

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
                state.set$fixedFlag$sample26(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample26())
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
                state.set$fixedFlag$sample26(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample26())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing ut of type double[] from the Sandwood model. */
    public final ComputedDoubleArray ut = $ut;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $Avail = new ObservedObjectArrayInternal<int[]>(this, "Avail", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$Avail();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { state.set$Avail(value, allocated); }
    };

	/** Observed variable representing Avail of type int[][] from the Sandwood model. */
    public final ObservedObjectArray<int[]> Avail = $Avail;

    private final ObservedObjectArrayInternal<int[]> $ObsSales = new ObservedObjectArrayInternal<int[]>(this, "ObsSales", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$ObsSales();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { state.set$ObsSales(value, allocated); }
    };

	/**
	 * Observed variable representing ObsSales of type int[][] from the Sandwood model.
	 */
    public final ObservedObjectArray<int[]> ObsSales = $ObsSales;

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

    private final ObservedDoubleInternal $r = new ObservedDoubleInternal(this, "r") {
        @Override
        public double getValue() {
            synchronized(model) {
                return state.get$r();
            }
        }

        @Override
        protected void setValueInternal(double value) { state.set$r(value, allocated); }
    };

	/** Observed variable representing r of type double from the Sandwood model. */
    public final ObservedDouble r = $r;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$Sales, $exped, $expedNorm, $sum, $ut};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public Vulcano2012basic() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("Sales", $Sales);
        $computedVariables.put("exped", $exped);
        $computedVariables.put("expedNorm", $expedNorm);
        $computedVariables.put("sum", $sum);
        $computedVariables.put("ut", $ut);

        //ModelInputs
        $modelInputs.put("Avail", $Avail);
        $modelInputs.put("ObsSales", $ObsSales);
        $modelInputs.put("T", $T);
        $modelInputs.put("noProducts", $noProducts);
        $modelInputs.put("r", $r);

        Vulcano2012basic$SingleThreadCPU core = new Vulcano2012basic$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param noProducts The value to set noProducts to.
	 * @param T The value to set T to
	 * @param ObsSales The value to set ObsSales to
	 * @param Avail The value to set Avail to
	 * @param r The value to set r to
	 */
    public Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {
        this();
        this.noProducts.setValue(noProducts);
        this.T.setValue(T);
        this.ObsSales.setValue(ObsSales);
        this.Avail.setValue(Avail);
        this.r.setValue(r);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new Vulcano2012basic$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new Vulcano2012basic$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input T */
        public final int T;
		/** Field holding the value of model input ObsSales */
        public final int[][] ObsSales;
		/** Field holding the value of model input Avail */
        public final int[][] Avail;
		/** Field holding the value of model input r */
        public final double r;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param noProducts The value to set noProducts to.
		 * @param T The value to set T to.
		 * @param ObsSales The value to set ObsSales to.
		 * @param Avail The value to set Avail to.
		 * @param r The value to set r to.
		 */
        public InferValueInputs(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {
            this.Avail = Avail;
            this.ObsSales = ObsSales;
            this.T = T;
            this.noProducts = noProducts;
            this.r = r;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input noProducts */
        public final int noProducts;
		/** Field holding the value of model input T */
        public final int T;
		/** Field holding the value of model input ObsSales */
        public final int[][] ObsSales;
		/** Field holding the value of model input Avail */
        public final int[][] Avail;
		/** Field holding the value of model input r */
        public final double r;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param noProducts The value to set noProducts to.
		 * @param T The value to set T to.
		 * @param ObsSales The value to set ObsSales to.
		 * @param Avail The value to set Avail to.
		 * @param r The value to set r to.
		 */
        public AllInputs(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {
            this.noProducts = noProducts;
            this.T = T;
            this.ObsSales = ObsSales;
            this.Avail = Avail;
            this.r = r;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of Sales after a convention execution step. */
        public final int[][] Sales;
		/** Field holding the value of exped after a convention execution step. */
        public final double[] exped;
		/** Field holding the value of expedNorm after a convention execution step. */
        public final double[] expedNorm;
		/** Field holding the value of sum after a convention execution step. */
        public final double sum;
		/** Field holding the value of ut after a convention execution step. */
        public final double[] ut;

        InferredValueOutputs(Vulcano2012basic system$model) {
            this.Sales = system$model.Sales.getSamples()[0];
            this.exped = system$model.exped.getSamples()[0];
            this.expedNorm = system$model.expedNorm.getSamples()[0];
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
		/** Field holding the log probability of computed variable Sales */
        public final double Sales;
		/** Field holding the log probability of computed variable exped */
        public final double exped;
		/** Field holding the log probability of computed variable expedNorm */
        public final double expedNorm;
		/** Field holding the log probability of computed variable sum */
        public final double sum;
		/** Field holding the log probability of computed variable ut */
        public final double ut;

        LogProbabilities(Vulcano2012basic system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.Sales = system$model.Sales.getLogProbability();
            this.exped = system$model.exped.getLogProbability();
            this.expedNorm = system$model.expedNorm.getLogProbability();
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
		/** Field holding the probability of computed variable Sales */
        public final double Sales;
		/** Field holding the probability of computed variable exped */
        public final double exped;
		/** Field holding the probability of computed variable expedNorm */
        public final double expedNorm;
		/** Field holding the probability of computed variable sum */
        public final double sum;
		/** Field holding the probability of computed variable ut */
        public final double ut;

        Probabilities(Vulcano2012basic system$model) {
            this.$modelProbability = system$model.getProbability();
            this.Sales = system$model.Sales.getProbability();
            this.exped = system$model.exped.getProbability();
            this.expedNorm = system$model.expedNorm.getProbability();
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
		/** Field holding the MAP or Sample value of expedNorm after an infer model call. */
        public final double[][] expedNorm;
		/** Field holding the MAP or Sample value of sum after an infer model call. */
        public final double[] sum;
		/** Field holding the MAP or Sample value of ut after an infer model call. */
        public final double[][] ut;

        InferredModelOutputs(Vulcano2012basic system$model) {
            this.exped = system$model.getInferredValue(system$model.$exped);
            this.expedNorm = system$model.getInferredValue(system$model.$expedNorm);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.ObsSales.setValue(inputs.ObsSales);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}