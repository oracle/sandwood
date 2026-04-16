package org.sandwood.compiler.tests.parser;

import java.util.HashMap;
import java.util.Map;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.*;

/**
 * Class representing the Sandwood model ReductionTest1 This is the class that all
 * user interactions with the model should occur through.
 */
public final class ReductionTest1 extends Model {
    private ReductionTest1$CoreInterface system$c = new ReductionTest1$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<int[]> $arr = new ComputedObjectArrayInternal<int[]>(this, "arr", false, true, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return system$c.get$arr(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable arr because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$arr(); }

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

	/** Computed variable representing arr of type int[][] from the Sandwood model. */
    public final ComputedObjectArray<int[]> arr = $arr;

    private final ComputedObjectArrayInternal<double[]> $sum_t = new ComputedObjectArrayInternal<double[]>(this, "sum_t", false, false, false, ProbabilityType.UNSKIPPABLE, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$sum_t(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable sum_t because its value depends on variables \"time_coeff\", and \"time_impact\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$sum_t(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample101())
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
        public double[][] getValue() { return system$c.get$time_coeff(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$time_coeff(value, allocated);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$time_coeff(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample101())
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
        public double[][][] getValue() { return system$c.get$time_impact(); }

        @Override
        protected void setValueInternal(double[][][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable time_impact because its value depends on variable \"time_coeff\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$time_impact(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample101(fixed, allocated);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample101())
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
                return system$c.get$T();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$T(value, allocated); }
    };

	/** Observed variable representing T of type int from the Sandwood model. */
    public final ObservedInteger T = $T;

    private final ObservedObjectArrayInternal<double[]> $TimeFeat = new ObservedObjectArrayInternal<double[]>(this, "TimeFeat", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() {
            synchronized(model) {
                return system$c.get$TimeFeat();
            }
        }

        @Override
        protected void setValueInternal(double[][] value) { system$c.set$TimeFeat(value, allocated); }
    };

	/**
	 * Observed variable representing TimeFeat of type double[][] from the Sandwood model.
	 */
    public final ObservedObjectArray<double[]> TimeFeat = $TimeFeat;

    private final ObservedIntegerInternal $n_ac = new ObservedIntegerInternal(this, "n_ac") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$n_ac();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$n_ac(value, allocated); }
    };

	/** Observed variable representing n_ac of type int from the Sandwood model. */
    public final ObservedInteger n_ac = $n_ac;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $ObsArr = new ObservedObjectArrayInternal<int[]>(this, "ObsArr", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return system$c.get$ObsArr();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { system$c.set$ObsArr(value, allocated); }
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
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
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
    protected ReductionTest1$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        ReductionTest1$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new ReductionTest1$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new ReductionTest1$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(ReductionTest1$CoreInterface oldCore, ReductionTest1$CoreInterface newCore) {
        //Model inputs
        if(T.isSet())
            newCore.set$T(oldCore.get$T(), false);
        if(TimeFeat.isSet())
            newCore.set$TimeFeat(oldCore.get$TimeFeat(), false);
        if(n_ac.isSet())
            newCore.set$n_ac(oldCore.get$n_ac(), false);

        //Observed scalars
        if(ObsArr.isSet())
            newCore.set$ObsArr(oldCore.get$ObsArr(), false);

        //ComputedVariables
        if($time_coeff.isSet())
            newCore.set$time_coeff(oldCore.get$time_coeff(), false);

        //Set fixed flags
        newCore.set$fixedFlag$sample101(oldCore.get$fixedFlag$sample101(), false);
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