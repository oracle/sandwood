package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Vulcano2012basicDG This is the class that
  * all user interactions with the model should occur through.
  */
public class Vulcano2012basicDG extends Model {

    private Vulcano2012basicDG$CoreInterface system$c = new Vulcano2012basicDG$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedIntegerArrayInternal $arrivals = new ComputedIntegerArrayInternal(this, "arrivals", true) {
        @Override
        public int[] getValue() { return system$c.get$arrivals(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$arrivals(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$arrivals(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample89(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample89())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing arrivals of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray arrivals = $arrivals;

    private final ComputedDoubleArrayInternal $lambda = new ComputedDoubleArrayInternal(this, "lambda", true) {
        @Override
        public double[] getValue() { return system$c.get$lambda(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$lambda(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$lambda(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample87(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample87())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing lambda of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray lambda = $lambda;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $ObsSales = new ObservedObjectArrayInternal<int[]>(this, "ObsSales", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return system$c.get$ObsSales();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { system$c.set$ObsSales(value); }
    };

    /**
     * Observed variable representing ObsSales of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArray<int[]> ObsSales = $ObsSales;

    private final ObservedObjectArrayInternal<boolean[]> $avail = new ObservedObjectArrayInternal<boolean[]>(this, "avail", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 2) {
        @Override
        public boolean[][] getValue() {
            synchronized(model) {
                return system$c.get$avail();
            }
        }

        @Override
        protected void setValueInternal(boolean[][] value) { system$c.set$avail(value); }
    };

    /**
     * Observed variable representing avail of type boolean[][] from the Sandwood model 
     */
    public final ObservedObjectArray<boolean[]> avail = $avail;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$arrivals, $lambda};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Vulcano2012basicDG() {
        super();
        //ComputedVariable
        $computedVariables.put("arrivals", $arrivals);
        $computedVariables.put("lambda", $lambda);

        //ModelInputs
        $modelInputs.put("ObsSales", $ObsSales);
        $modelInputs.put("avail", $avail);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param ObsSales The value to set ObsSales to.
      * @param avail The value to set avail to.
      */

    public Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {
        this();
        this.ObsSales.setValue(ObsSales);
        this.avail.setValue(avail);
    }
    
    @Override
    protected Vulcano2012basicDG$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Vulcano2012basicDG$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Vulcano2012basicDG$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Vulcano2012basicDG$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Vulcano2012basicDG$CoreInterface oldCore, Vulcano2012basicDG$CoreInterface newCore) {
        //Model inputs
        if(ObsSales.isSet())
            newCore.set$ObsSales(oldCore.get$ObsSales());
        if(avail.isSet())
            newCore.set$avail(oldCore.get$avail());

        //ComputedVariables
        if(arrivals.isSet())
            newCore.set$arrivals(oldCore.get$arrivals());
        if(lambda.isSet())
            newCore.set$lambda(oldCore.get$lambda());

        //Set fixed flags
        if(arrivals.isSet())
            newCore.set$fixedFlag$sample89(oldCore.get$fixedFlag$sample89());
        if(lambda.isSet())
            newCore.set$fixedFlag$sample87(oldCore.get$fixedFlag$sample87());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input ObsSales */
        public final int[][] ObsSales;
        /** Field holding the value of model input avail */
        public final boolean[][] avail;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param ObsSales The value to set ObsSales to.
          * @param avail The value to set avail to.
          */
        public InferValueInputs(int[][] ObsSales, boolean[][] avail) {
            this.ObsSales = ObsSales;
            this.avail = avail;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input ObsSales */
        public final int[][] ObsSales;
        /** Field holding the value of model input avail */
        public final boolean[][] avail;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param ObsSales The value to set ObsSales to.
          * @param avail The value to set avail to.
          */
        public AllInputs(int[][] ObsSales, boolean[][] avail) {
            this.ObsSales = ObsSales;
            this.avail = avail;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of arrivals after a convention execution step.*/
        public final int[] arrivals;
        /** Field holding the value of lambda after a convention execution step.*/
        public final double[] lambda;

        InferredValueOutputs(Vulcano2012basicDG system$model) {
            this.arrivals = system$model.arrivals.getSamples()[0];
            this.lambda = system$model.lambda.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable arrivals */
        public final double arrivals;
        /** Field holding the log probability of computed variable lambda */
        public final double lambda;

        LogProbabilities(Vulcano2012basicDG system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.arrivals = system$model.arrivals.getLogProbability();
            this.lambda = system$model.lambda.getLogProbability();
        }

        /** Method to return log probability of the whole model 
         *  @return The log probability of the whole model. */
        public double getModelProbability() { return $logModelProbability; }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class Probabilities {
        private final double $modelProbability;
        /** Field holding the probability of computed variable arrivals */
        public final double arrivals;
        /** Field holding the probability of computed variable lambda */
        public final double lambda;

        Probabilities(Vulcano2012basicDG system$model) {
            this.$modelProbability = system$model.getProbability();
            this.arrivals = system$model.arrivals.getProbability();
            this.lambda = system$model.lambda.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of arrivals after an infer model call. */
        public final int[][] arrivals;
        /** Field holding the MAP or Sample value of lambda after an infer model call. */
        public final double[][] lambda;

        InferredModelOutputs(Vulcano2012basicDG system$model) {
            this.arrivals = system$model.getInferredValue(system$model.$arrivals);
            this.lambda = system$model.getInferredValue(system$model.$lambda);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        execute();
        return new InferredValueOutputs(this);
    }

    /**
     * Infer the values of the different elements of the model.
     * @param iterations The number of iterations to perform when inferring the values.
     * @param inputs An object containing the parameters required to generate the model parameters.
     * @return An object containing the computed values for the model.
     */
    public InferredModelOutputs inferValues(int iterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferValues(iterations);
        return new InferredModelOutputs(this);
    }

    /**
     * Generate the probabilities of the different elements of the model.
     * @param iterations How many iterations should be used to generate these values?
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(int iterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(iterations);
        return new Probabilities(this);
    }

    /**
     * Calculate the probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(variance, initialIterations);
        return new Probabilities(this);
    }

    /**
     * Calculate the probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not
     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public Probabilities inferProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new Probabilities(this);
    }

    /**
     * Generate the log probabilities of the different elements of the model.
     * @param iterations How many iterations should be used to generate these values?
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(int iterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(iterations);
        return new LogProbabilities(this);
    }

    /**
     * Calculate the log probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(variance, initialIterations);
        return new LogProbabilities(this);
    }

    /**
     * Calculate the log probability of each variable and the overall model. This method
     * will iterate until the variance of the overall model drops below the value provide 
     * for variance, or the maximum number of iterations is reached.
     * @param variance The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     * premature termination as the model may not have enough runs to estimate the variance accurately.
     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not
     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.
     * @param inputs An object containing the parameters required to generate the probabilities of the model.
     * @return An object containing the computed probabilities for the model.
     */
    public LogProbabilities inferLogProbabilities(double variance, int initialIterations, int maxIterations, AllInputs inputs) {
        this.ObsSales.setValue(inputs.ObsSales);
        this.avail.setValue(inputs.avail);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
