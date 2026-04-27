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
 * Class representing the Sandwood model DiscreteChoiceRandCoeff This is the class
 * that all user interactions with the model should occur through.
 */
public final class DiscreteChoiceRandCoeff extends Model<DiscreteChoiceRandCoeff.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		int[] ObsChoices;
		int[][] Prices;
		double b;
		double[] beta;
		int[] choices;
		boolean[] constrainedFlag$sample21;
		boolean constrainedFlag$sample28 = true;
		boolean constrainedFlag$sample34 = true;
		boolean[] constrainedFlag$sample47;
		double[][] exped;
		boolean fixedFlag$sample21 = false;
		boolean fixedFlag$sample28 = false;
		boolean fixedFlag$sample34 = false;
		boolean fixedFlag$sample47 = false;
		boolean fixedProbFlag$sample103 = false;
		boolean fixedProbFlag$sample21 = false;
		boolean fixedProbFlag$sample28 = false;
		boolean fixedProbFlag$sample34 = false;
		boolean fixedProbFlag$sample47 = false;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$b;
		double logProbability$beta;
		double logProbability$choices;
		double logProbability$prob;
		double[] logProbability$sample103;
		double[] logProbability$sample21;
		double[] logProbability$sample47;
		double logProbability$sigma;
		double logProbability$ut;
		int noObs;
		int noProducts;
		double[][] prob;
		double sigma;
		boolean system$gibbsForward = true;
		double[] ut;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// If ut has not been set already allocate space.
			if(!fixedFlag$sample21)
				// Constructor for ut
				ut = new double[noProducts];
			
			// If beta has not been set already allocate space.
			if(!fixedFlag$sample47)
				// Constructor for beta
				beta = new double[noObs];
			
			// Constructor for choices
			choices = new int[noObs];
			
			// Constructor for exped
			exped = new double[noObs][];
			for(int i = 0; i < noObs; i += 1)
				exped[i] = new double[noProducts];
			
			// Constructor for prob
			prob = new double[noObs][];
			for(int i = 0; i < noObs; i += 1)
				prob[i] = new double[noProducts];
			
			// Constructor for constrainedFlag$sample47
			constrainedFlag$sample47 = new boolean[noObs];
			
			// Constructor for constrainedFlag$sample21
			constrainedFlag$sample21 = new boolean[noProducts];
			
			// Constructor for logProbability$sample21
			logProbability$sample21 = new double[noProducts];
			
			// Constructor for logProbability$sample47
			logProbability$sample47 = new double[noObs];
			
			// Constructor for logProbability$sample103
			logProbability$sample103 = new double[noObs];
		}

		// Getter for ObsChoices.
		final int[] get$ObsChoices() {
			return ObsChoices;
		}

		// Setter for ObsChoices.
		final void set$ObsChoices(int[] cv$value, boolean allocated$) {
			ObsChoices = cv$value;
		}

		// Getter for Prices.
		final int[][] get$Prices() {
			return Prices;
		}

		// Setter for Prices.
		final void set$Prices(int[][] cv$value, boolean allocated$) {
			Prices = cv$value;
		}

		// Getter for b.
		final double get$b() {
			return b;
		}

		// Setter for b.
		final void set$b(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of b including if probabilities need to be updated.
			b = cv$value;
			
			// Unset the fixed probability flag for sample 28 as it depends on b.
			fixedProbFlag$sample28 = false;
			
			// Unset the fixed probability flag for sample 47 as it depends on b.
			fixedProbFlag$sample47 = false;
		}

		// Getter for beta.
		final double[] get$beta() {
			return beta;
		}

		// Setter for beta.
		final void set$beta(double[] cv$value, boolean allocated$) {
			// Set flags for all the side effects of beta including if probabilities need to be
			// updated.
			beta = cv$value;
			
			// Unset the fixed probability flag for sample 47 as it depends on beta.
			fixedProbFlag$sample47 = false;
			
			// Unset the fixed probability flag for sample 103 as it depends on beta.
			fixedProbFlag$sample103 = false;
		}

		// Getter for choices.
		final int[] get$choices() {
			return choices;
		}

		// Getter for fixedFlag$sample21.
		final boolean get$fixedFlag$sample21() {
			return fixedFlag$sample21;
		}

		// Setter for fixedFlag$sample21.
		final void set$fixedFlag$sample21(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
			// need to be updated.
			fixedFlag$sample21 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
					constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
			}
			
			// Should the probability of sample 21 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
			
			// Should the probability of sample 103 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample21" with its value "cv$value".
			fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
			
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			constrainedFlag$sample28 = (cv$value || constrainedFlag$sample28);
			
			// Should the probability of sample 28 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
			
			// Should the probability of sample 47 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample28" with its value "cv$value".
			fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		}

		// Getter for fixedFlag$sample34.
		final boolean get$fixedFlag$sample34() {
			return fixedFlag$sample34;
		}

		// Setter for fixedFlag$sample34.
		final void set$fixedFlag$sample34(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
			// need to be updated.
			fixedFlag$sample34 = cv$value;
			
			// Substituted "fixedFlag$sample34" with its value "cv$value".
			constrainedFlag$sample34 = (cv$value || constrainedFlag$sample34);
			
			// Should the probability of sample 34 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample34" with its value "cv$value".
			fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
			
			// Should the probability of sample 47 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample34" with its value "cv$value".
			fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
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
			
			// Should the probability of sample 103 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample47" with its value "cv$value".
			fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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

		// Getter for logProbability$b.
		final double get$logProbability$b() {
			return logProbability$b;
		}

		// Getter for logProbability$beta.
		final double get$logProbability$beta() {
			return logProbability$beta;
		}

		// Getter for logProbability$choices.
		final double get$logProbability$choices() {
			return logProbability$choices;
		}

		// Getter for logProbability$prob.
		final double get$logProbability$prob() {
			return logProbability$prob;
		}

		// Getter for logProbability$sigma.
		final double get$logProbability$sigma() {
			return logProbability$sigma;
		}

		// Getter for logProbability$ut.
		final double get$logProbability$ut() {
			return logProbability$ut;
		}

		// Getter for noObs.
		final int get$noObs() {
			return noObs;
		}

		// Setter for noObs.
		final void set$noObs(int cv$value, boolean allocated$) {
			noObs = cv$value;
		}

		// Getter for noProducts.
		final int get$noProducts() {
			return noProducts;
		}

		// Setter for noProducts.
		final void set$noProducts(int cv$value, boolean allocated$) {
			noProducts = cv$value;
		}

		// Getter for prob.
		final double[][] get$prob() {
			return prob;
		}

		// Getter for sigma.
		final double get$sigma() {
			return sigma;
		}

		// Setter for sigma.
		final void set$sigma(double cv$value, boolean allocated$) {
			// Set flags for all the side effects of sigma including if probabilities need to
			// be updated.
			sigma = cv$value;
			
			// Unset the fixed probability flag for sample 34 as it depends on sigma.
			fixedProbFlag$sample34 = false;
			
			// Unset the fixed probability flag for sample 47 as it depends on sigma.
			fixedProbFlag$sample47 = false;
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
			
			// Unset the fixed probability flag for sample 21 as it depends on ut.
			fixedProbFlag$sample21 = false;
			
			// Unset the fixed probability flag for sample 103 as it depends on ut.
			fixedProbFlag$sample103 = false;
		}
	}

    private final ComputedDoubleInternal $b = new ComputedDoubleInternal(this, "b", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$b(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$b(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$b(); }

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

	/** Computed variable representing b of type double from the Sandwood model. */
    public final ComputedDouble b = $b;

    private final ComputedDoubleArrayInternal $beta = new ComputedDoubleArrayInternal(this, "beta", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double[] getValue() { return state.get$beta(); }

        @Override
        protected void setValueInternal(double[] value) {
            state.set$beta(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$beta(); }

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

	/** Computed variable representing beta of type double[] from the Sandwood model. */
    public final ComputedDoubleArray beta = $beta;

    private final ComputedIntegerArrayInternal $choices = new ComputedIntegerArrayInternal(this, "choices", false, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public int[] getValue() { return state.get$choices(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable choices because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$choices(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variable can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing choices of type int[] from the Sandwood model. */
    public final ComputedIntegerArray choices = $choices;

    private final ComputedObjectArrayInternal<double[]> $prob = new ComputedObjectArrayInternal<double[]>(this, "prob", false, false, false, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$prob(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable prob because its value depends on variables \"beta\", \"exped\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$prob(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample21(fixed, allocated);
                state.set$fixedFlag$sample47(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample21 = state.get$fixedFlag$sample21();
            boolean fixedFlag$sample47 = state.get$fixedFlag$sample47();
            if(fixedFlag$sample21 && fixedFlag$sample47)
                return Immutability.FIXED;
            else if(fixedFlag$sample21 || fixedFlag$sample47)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing prob of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> prob = $prob;

    private final ComputedDoubleInternal $sigma = new ComputedDoubleInternal(this, "sigma", true, true, false, ProbabilityType.UNSKIPPABLE) {
        @Override
        public double getValue() { return state.get$sigma(); }

        @Override
        protected void setValueInternal(double value) {
            state.set$sigma(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$sigma(); }

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

	/** Computed variable representing sigma of type double from the Sandwood model. */
    public final ComputedDouble sigma = $sigma;

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
                state.set$fixedFlag$sample21(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample21())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing ut of type double[] from the Sandwood model. */
    public final ComputedDoubleArray ut = $ut;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $Prices = new ObservedObjectArrayInternal<int[]>(this, "Prices", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$Prices();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { state.set$Prices(value, allocated); }
    };

	/** Observed variable representing Prices of type int[][] from the Sandwood model. */
    public final ObservedObjectArray<int[]> Prices = $Prices;

    private final ObservedIntegerInternal $noObs = new ObservedIntegerInternal(this, "noObs") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noObs();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noObs(value, allocated); }
    };

	/** Observed variable representing noObs of type int from the Sandwood model. */
    public final ObservedInteger noObs = $noObs;

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

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayInternal $ObsChoices = new ObservedIntegerArrayInternal(this, "ObsChoices") {
        @Override
        public int[] getValue() {
            synchronized(model) {
                return state.get$ObsChoices();
            }
        }

        @Override
        protected void setValueInternal(int[] value) { state.set$ObsChoices(value, allocated); }
    };

	/**
	 * Observed variable representing ObsChoices of type int[] from the Sandwood model.
	 */
    public final ObservedIntegerArray ObsChoices = $ObsChoices;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$b, $beta, $choices, $prob, $sigma, $ut};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public DiscreteChoiceRandCoeff() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("b", $b);
        $computedVariables.put("beta", $beta);
        $computedVariables.put("choices", $choices);
        $computedVariables.put("prob", $prob);
        $computedVariables.put("sigma", $sigma);
        $computedVariables.put("ut", $ut);

        //ModelInputs
        $modelInputs.put("Prices", $Prices);
        $modelInputs.put("noObs", $noObs);
        $modelInputs.put("noProducts", $noProducts);

        //Observed scalar fields
        $regularObservedValues.put("ObsChoices", $ObsChoices);

        DiscreteChoiceRandCoeff$SingleThreadCPU core = new DiscreteChoiceRandCoeff$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param noProducts The value to set noProducts to.
	 * @param noObs The value to set noObs to.
	 * @param Prices The value to set Prices to.
	 */
    public DiscreteChoiceRandCoeff(int noProducts, int noObs, int[][] Prices) {
        this();
        this.$Prices.setValue(Prices);
        this.$noObs.setValue(noObs);
        this.$noProducts.setValue(noProducts);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param noProducts The value to set noProducts to.
	 * @param noObs The value to set noObs to
	 * @param ObsChoices The value to set ObsChoices to
	 * @param Prices The value to set Prices to
	 */
    public DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {
        this();
        this.noProducts.setValue(noProducts);
        this.noObs.setValue(noObs);
        this.ObsChoices.setValue(ObsChoices);
        this.Prices.setValue(Prices);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new DiscreteChoiceRandCoeff$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new DiscreteChoiceRandCoeff$MultiThreadCPU(state, target);
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
		/** Field holding the value of model input noObs */
        public final int noObs;
		/** Field holding the value of model input Prices */
        public final int[][] Prices;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param noProducts The value to set noProducts to.
		 * @param noObs The value to set noObs to.
		 * @param Prices The value to set Prices to.
		 */
        public InferValueInputs(int noProducts, int noObs, int[][] Prices) {
            this.Prices = Prices;
            this.noObs = noObs;
            this.noProducts = noProducts;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input noProducts */
        public final int noProducts;
		/** Field holding the value of model input noObs */
        public final int noObs;
		/** Field holding the value of model input ObsChoices */
        public final int[] ObsChoices;
		/** Field holding the value of model input Prices */
        public final int[][] Prices;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param noProducts The value to set noProducts to.
		 * @param noObs The value to set noObs to.
		 * @param ObsChoices The value to set ObsChoices to.
		 * @param Prices The value to set Prices to.
		 */
        public AllInputs(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {
            this.noProducts = noProducts;
            this.noObs = noObs;
            this.ObsChoices = ObsChoices;
            this.Prices = Prices;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of b after a convention execution step. */
        public final double b;
		/** Field holding the value of beta after a convention execution step. */
        public final double[] beta;
		/** Field holding the value of choices after a convention execution step. */
        public final int[] choices;
		/** Field holding the value of prob after a convention execution step. */
        public final double[][] prob;
		/** Field holding the value of sigma after a convention execution step. */
        public final double sigma;
		/** Field holding the value of ut after a convention execution step. */
        public final double[] ut;

        InferredValueOutputs(DiscreteChoiceRandCoeff system$model) {
            this.b = system$model.b.getSamples()[0];
            this.beta = system$model.beta.getSamples()[0];
            this.choices = system$model.choices.getSamples()[0];
            this.prob = system$model.prob.getSamples()[0];
            this.sigma = system$model.sigma.getSamples()[0];
            this.ut = system$model.ut.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable b */
        public final double b;
		/** Field holding the log probability of computed variable beta */
        public final double beta;
		/** Field holding the log probability of computed variable choices */
        public final double choices;
		/** Field holding the log probability of computed variable prob */
        public final double prob;
		/** Field holding the log probability of computed variable sigma */
        public final double sigma;
		/** Field holding the log probability of computed variable ut */
        public final double ut;

        LogProbabilities(DiscreteChoiceRandCoeff system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.b = system$model.b.getLogProbability();
            this.beta = system$model.beta.getLogProbability();
            this.choices = system$model.choices.getLogProbability();
            this.prob = system$model.prob.getLogProbability();
            this.sigma = system$model.sigma.getLogProbability();
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
		/** Field holding the probability of computed variable b */
        public final double b;
		/** Field holding the probability of computed variable beta */
        public final double beta;
		/** Field holding the probability of computed variable choices */
        public final double choices;
		/** Field holding the probability of computed variable prob */
        public final double prob;
		/** Field holding the probability of computed variable sigma */
        public final double sigma;
		/** Field holding the probability of computed variable ut */
        public final double ut;

        Probabilities(DiscreteChoiceRandCoeff system$model) {
            this.$modelProbability = system$model.getProbability();
            this.b = system$model.b.getProbability();
            this.beta = system$model.beta.getProbability();
            this.choices = system$model.choices.getProbability();
            this.prob = system$model.prob.getProbability();
            this.sigma = system$model.sigma.getProbability();
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
		/** Field holding the MAP or Sample value of b after an infer model call. */
        public final double[] b;
		/** Field holding the MAP or Sample value of beta after an infer model call. */
        public final double[][] beta;
		/** Field holding the MAP or Sample value of prob after an infer model call. */
        public final double[][][] prob;
		/** Field holding the MAP or Sample value of sigma after an infer model call. */
        public final double[] sigma;
		/** Field holding the MAP or Sample value of ut after an infer model call. */
        public final double[][] ut;

        InferredModelOutputs(DiscreteChoiceRandCoeff system$model) {
            this.b = system$model.getInferredValue(system$model.$b);
            this.beta = system$model.getInferredValue(system$model.$beta);
            this.prob = system$model.getInferredValue(system$model.$prob);
            this.sigma = system$model.getInferredValue(system$model.$sigma);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
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
        this.Prices.setValue(inputs.Prices);
        this.noObs.setValue(inputs.noObs);
        this.noProducts.setValue(inputs.noProducts);
        this.$ObsChoices.setValue(inputs.ObsChoices);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}