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
 * Class representing the Sandwood model LDATest This is the class that all user interactions
 * with the model should occur through.
 */
public final class LDATest extends Model<LDATest.State> {
	final class State extends CoreModelState {

		// Declare the variables for the model.
		double[] alpha;
		double[] beta;
		boolean[] constrainedFlag$sample42;
		boolean[] constrainedFlag$sample58;
		boolean[][] constrainedFlag$sample90;
		int[][] documents;
		boolean fixedFlag$sample42 = false;
		boolean fixedFlag$sample58 = false;
		boolean fixedProbFlag$sample42 = false;
		boolean fixedProbFlag$sample58 = false;
		int[] length$documents;
		double logProbability$$evidence;
		double logProbability$$model;
		double logProbability$phi;
		double[][] logProbability$sample90;
		double[][] logProbability$sample93;
		double logProbability$theta;
		double logProbability$var42;
		double logProbability$var57;
		double logProbability$w;
		int noTopics;
		double[][] phi;
		boolean system$gibbsForward = true;
		double[][] theta;
		int vocabSize;
		int[][] w;
		int[][] z;

		// Method to allocate space for model inputs and outputs.
		@Override
		public final void allocate() {
			// Constructor for alpha
			alpha = new double[noTopics];
			
			// Constructor for beta
			beta = new double[vocabSize];
			
			// If phi has not been set already allocate space.
			if(!fixedFlag$sample42) {
				// Constructor for phi
				phi = new double[noTopics][];
				for(int var41 = 0; var41 < noTopics; var41 += 1)
					phi[var41] = new double[vocabSize];
			}
			
			// If theta has not been set already allocate space.
			if(!fixedFlag$sample58) {
				// Constructor for theta
				theta = new double[length$documents.length][];
				for(int var56 = 0; var56 < length$documents.length; var56 += 1)
					theta[var56] = new double[noTopics];
			}
			
			// Constructor for w
			w = new int[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				w[i$var71] = new int[length$documents[i$var71]];
			
			// Constructor for z
			z = new int[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				z[i$var71] = new int[length$documents[i$var71]];
			
			// Constructor for constrainedFlag$sample90
			constrainedFlag$sample90 = new boolean[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				constrainedFlag$sample90[i$var71] = new boolean[length$documents[i$var71]];
			
			// Constructor for constrainedFlag$sample42
			constrainedFlag$sample42 = new boolean[noTopics];
			
			// Constructor for constrainedFlag$sample58
			constrainedFlag$sample58 = new boolean[length$documents.length];
			
			// Constructor for logProbability$sample90
			logProbability$sample90 = new double[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$sample90[i$var71] = new double[length$documents[i$var71]];
			
			// Constructor for logProbability$sample93
			logProbability$sample93 = new double[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$sample93[i$var71] = new double[length$documents[i$var71]];
		}

		// Getter for alpha.
		final double[] get$alpha() {
			return alpha;
		}

		// Getter for beta.
		final double[] get$beta() {
			return beta;
		}

		// Getter for documents.
		final int[][] get$documents() {
			return documents;
		}

		// Setter for documents.
		final void set$documents(int[][] cv$value, boolean allocated$) {
			documents = cv$value;
		}

		// Getter for fixedFlag$sample42.
		final boolean get$fixedFlag$sample42() {
			return fixedFlag$sample42;
		}

		// Setter for fixedFlag$sample42.
		final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
			// need to be updated.
			fixedFlag$sample42 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
					constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
			}
			
			// Should the probability of sample 42 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample42" with its value "cv$value".
			fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		}

		// Getter for fixedFlag$sample58.
		final boolean get$fixedFlag$sample58() {
			return fixedFlag$sample58;
		}

		// Setter for fixedFlag$sample58.
		final void set$fixedFlag$sample58(boolean cv$value, boolean allocated$) {
			// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
			// need to be updated.
			fixedFlag$sample58 = cv$value;
			
			// If the model has been allocated update the constraints flags
			if(allocated$) {
				// Set all the values in the array
				for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
					constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
			}
			
			// Should the probability of sample 58 be set to fixed. This will only every change
			// the flag to false.
			// 
			// Substituted "fixedFlag$sample58" with its value "cv$value".
			fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		}

		// Getter for length$documents.
		final int[] get$length$documents() {
			return length$documents;
		}

		// Setter for length$documents.
		final void set$length$documents(int[] cv$value, boolean allocated$) {
			length$documents = cv$value;
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

		// Getter for logProbability$phi.
		final double get$logProbability$phi() {
			return logProbability$phi;
		}

		// Getter for logProbability$theta.
		final double get$logProbability$theta() {
			return logProbability$theta;
		}

		// Getter for logProbability$w.
		final double get$logProbability$w() {
			return logProbability$w;
		}

		// Getter for noTopics.
		final int get$noTopics() {
			return noTopics;
		}

		// Setter for noTopics.
		final void set$noTopics(int cv$value, boolean allocated$) {
			noTopics = cv$value;
		}

		// Getter for phi.
		final double[][] get$phi() {
			return phi;
		}

		// Setter for phi.
		final void set$phi(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of phi including if probabilities need to be
			// updated.
			phi = cv$value;
			
			// Unset the fixed probability flag for sample 42 as it depends on phi.
			fixedProbFlag$sample42 = false;
		}

		// Getter for theta.
		final double[][] get$theta() {
			return theta;
		}

		// Setter for theta.
		final void set$theta(double[][] cv$value, boolean allocated$) {
			// Set flags for all the side effects of theta including if probabilities need to
			// be updated.
			theta = cv$value;
			
			// Unset the fixed probability flag for sample 58 as it depends on theta.
			fixedProbFlag$sample58 = false;
		}

		// Getter for vocabSize.
		final int get$vocabSize() {
			return vocabSize;
		}

		// Setter for vocabSize.
		final void set$vocabSize(int cv$value, boolean allocated$) {
			vocabSize = cv$value;
		}

		// Getter for w.
		final int[][] get$w() {
			return w;
		}

		// Getter for z.
		final int[][] get$z() {
			return z;
		}

		// Setter for z.
		final void set$z(int[][] cv$value, boolean allocated$) {
			z = cv$value;
		}
	}

    private final ComputedObjectArrayInternal<double[]> $phi = new ComputedObjectArrayInternal<double[]>(this, "phi", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$phi(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$phi(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$phi(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample42(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample42())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/** Computed variable representing phi of type double[][] from the Sandwood model. */
    public final ComputedObjectArray<double[]> phi = $phi;

    private final ComputedObjectArrayInternal<double[]> $theta = new ComputedObjectArrayInternal<double[]>(this, "theta", true, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return state.get$theta(); }

        @Override
        protected void setValueInternal(double[][] value) {
            state.set$theta(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$theta(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                state.set$fixedFlag$sample58(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(state.get$fixedFlag$sample58())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

	/**
	 * Computed variable representing theta of type double[][] from the Sandwood model.
	 */
    public final ComputedObjectArray<double[]> theta = $theta;

    private final ComputedObjectArrayInternal<int[]> $w = new ComputedObjectArrayInternal<int[]>(this, "w", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$w(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable w because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return state.get$logProbability$w(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

	/** Computed variable representing w of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> w = $w;

    private final ComputedObjectArrayInternal<int[]> $z = new ComputedObjectArrayInternal<int[]>(this, "z", true, true, true, ProbabilityType.SKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return state.get$z(); }

        @Override
        protected void setValueInternal(int[][] value) {
            state.set$z(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { throw new SandwoodException("Log probabilities are not available for this value."); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

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

    private final ObservedIntegerInternal $noTopics = new ObservedIntegerInternal(this, "noTopics") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$noTopics();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$noTopics(value, allocated); }
    };

	/** Observed variable representing noTopics of type int from the Sandwood model. */
    public final ObservedInteger noTopics = $noTopics;

    private final ObservedIntegerInternal $vocabSize = new ObservedIntegerInternal(this, "vocabSize") {
        @Override
        public int getValue() {
            synchronized(model) {
                return state.get$vocabSize();
            }
        }

        @Override
        protected void setValueInternal(int value) { state.set$vocabSize(value, allocated); }
    };

	/** Observed variable representing vocabSize of type int from the Sandwood model. */
    public final ObservedInteger vocabSize = $vocabSize;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayShapeableInternal<int[], int[]> $documents = new ObservedObjectArrayShapeableInternal<int[], int[]>(this, "documents", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return state.get$documents();
            }
        }

        @Override
        public void setValueInternal(int[][] value) {
            state.set$documents(value, allocated);
            state.set$length$documents(getDims(value), allocated);
        }

        @Override
        public void setShapeInternal(int[] shape) {
            state.set$length$documents(shape, allocated);
        }

        @Override
        public int[] getShape() {
            return state.get$length$documents();
        }
        private final int[] getDims(int[][] v1) {
            int[] s1 = new int[v1.length];
            for(int i1 = 0; i1 < v1.length; i1++) {
                int[] v0 = v1[i1];
                s1[i1] = v0.length;
            }
            return s1;
        }
    };

	/**
	 * Observed variable representing documents of type int[][] from the Sandwood model.
	 */
    public final ObservedObjectArrayShapeable<int[], int[]> documents = $documents;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$phi, $theta, $w};

    // Constructors
	/** A constructor for a model where no variable values are set. */
    public LDATest() {
        super();
        state = new State();
        //ComputedVariable
        $computedVariables.put("phi", $phi);
        $computedVariables.put("theta", $theta);
        $computedVariables.put("w", $w);
        $computedVariables.put("z", $z);

        //ModelInputs
        $modelInputs.put("noTopics", $noTopics);
        $modelInputs.put("vocabSize", $vocabSize);

        //Observed array fields
        $shapedObservedValues.put("documents", $documents);

        LDATest$SingleThreadCPU core = new LDATest$SingleThreadCPU(state, ExecutionTarget.singleThread);
        init(core, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }

	/**
	 * A constructor to set all the required values in the model to infer values. These
	 * will be values in an untrained model so this will only generate values from the
	 * default distributions described in the model.
	 * @param noTopics The value to set noTopics to.
	 * @param vocabSize The value to set vocabSize to.
	 * @param documentsShape An integer array describing the shape of variable documents
	 *                       to use in the model when generating results.
	 */
    public LDATest(int noTopics, int vocabSize, int[] documentsShape) {
        this();
        this.$noTopics.setValue(noTopics);
        this.$vocabSize.setValue(vocabSize);
        this.$documents.setShape(documentsShape);
    }

	/**
	 * A constructor to set all the required values in the model to infer the model parameters,
	 * or to generate probabilities for the model.
	 * @param noTopics The value to set noTopics to.
	 * @param vocabSize The value to set vocabSize to
	 * @param documents The value to set documents to
	 */
    public LDATest(int noTopics, int vocabSize, int[][] documents) {
        this();
        this.noTopics.setValue(noTopics);
        this.vocabSize.setValue(vocabSize);
        this.documents.setValue(documents);
    }
    
    @Override
    protected CoreModelBase<State,?> setExecutionTargetInternal(ExecutionTarget target) {
        switch(target.executionType) {
            case SingleThreadCPU:
                return new LDATest$SingleThreadCPU(state, target);
            case MultiThreadCPU:
                return new LDATest$MultiThreadCPU(state, target);
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
    }

	/**
	 * A class to hold all the values required to perform a value inference on the model.
	 */
    public static class InferValueInputs {
		/** Field holding the value of model input noTopics */
        public final int noTopics;
		/** Field holding the value of model input vocabSize */
        public final int vocabSize;
		/** Field holding the shape of model input documents */
        public final int[] documentsShape;

		/**
		 * A constructor taking all the values required to set up the model to infer variables.
		 * @param noTopics The value to set noTopics to.
		 * @param vocabSize The value to set vocabSize to.
		 * @param documentsShape An integer array describing the shape of variable documents
		 *                       to use in the model when generating results.
		 */
        public InferValueInputs(int noTopics, int vocabSize, int[] documentsShape) {
            this.noTopics = noTopics;
            this.vocabSize = vocabSize;
            this.documentsShape = documentsShape;
        }
    }

	/**
	 * A class to hold all the inputs for the model. It can be used to parameterize inference
	 * of the model probabilities and probability calculations.
	 */
    public static class AllInputs {
		/** Field holding the value of model input noTopics */
        public final int noTopics;
		/** Field holding the value of model input vocabSize */
        public final int vocabSize;
		/** Field holding the value of model input documents */
        public final int[][] documents;

		/**
		 * A constructor to take all the required values by the model to infer the model parameters,
		 * or to generate probabilities for the model.
		 * @param noTopics The value to set noTopics to.
		 * @param vocabSize The value to set vocabSize to.
		 * @param documents The value to set documents to.
		 */
        public AllInputs(int noTopics, int vocabSize, int[][] documents) {
            this.noTopics = noTopics;
            this.vocabSize = vocabSize;
            this.documents = documents;
        }
    }
	/** A class to hold all the outputs from the model after an infer values step. */
    public static class InferredValueOutputs {
		/** Field holding the value of phi after a convention execution step. */
        public final double[][] phi;
		/** Field holding the value of theta after a convention execution step. */
        public final double[][] theta;
		/** Field holding the value of w after a convention execution step. */
        public final int[][] w;

        InferredValueOutputs(LDATest system$model) {
            this.phi = system$model.phi.getSamples()[0];
            this.theta = system$model.theta.getSamples()[0];
            this.w = system$model.w.getSamples()[0];
        }
    }

	/**
	 * A class to hold all the probabilities from the model after a generate probabilities
	 * step.
	 */
    public static class LogProbabilities {
        private final double $logModelProbability;
		/** Field holding the log probability of computed variable phi */
        public final double phi;
		/** Field holding the log probability of computed variable theta */
        public final double theta;
		/** Field holding the log probability of computed variable w */
        public final double w;

        LogProbabilities(LDATest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.phi = system$model.phi.getLogProbability();
            this.theta = system$model.theta.getLogProbability();
            this.w = system$model.w.getLogProbability();
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
		/** Field holding the probability of computed variable phi */
        public final double phi;
		/** Field holding the probability of computed variable theta */
        public final double theta;
		/** Field holding the probability of computed variable w */
        public final double w;

        Probabilities(LDATest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.phi = system$model.phi.getProbability();
            this.theta = system$model.theta.getProbability();
            this.w = system$model.w.getProbability();
        }

		/**
		 * Method to return probability of the whole model
		 * @return The probability of the whole model.
		 */
        public double getModelProbability() { return $modelProbability; }
    }

	/** A class to hold all the outputs from the model after an infer model call. */
    public static class InferredModelOutputs {
		/** Field holding the MAP or Sample value of phi after an infer model call. */
        public final double[][][] phi;
		/** Field holding the MAP or Sample value of theta after an infer model call. */
        public final double[][][] theta;

        InferredModelOutputs(LDATest system$model) {
            this.phi = system$model.getInferredValue(system$model.$phi);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setShape(inputs.documentsShape);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}