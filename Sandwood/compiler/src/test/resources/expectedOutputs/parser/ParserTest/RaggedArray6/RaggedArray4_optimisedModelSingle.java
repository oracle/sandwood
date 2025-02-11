package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model RaggedArray4 This is the class that
  * all user interactions with the model should occur through.
  */
public class RaggedArray4 extends Model {

    private RaggedArray4$CoreInterface system$c = new RaggedArray4$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $d = new ComputedDoubleArrayInternal(this, "d", true) {
        @Override
        public double[] getValue() { return system$c.get$d(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$d(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$d(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample44(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample44())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing d of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray d = $d;

    private final ComputedBooleanArrayInternal $obs = new ComputedBooleanArrayInternal(this, "obs", true) {
        @Override
        public boolean[] getValue() { return system$c.get$obs(); }

        @Override
        protected void setValueInternal(boolean[] value) {
            system$c.set$obs(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$obs(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample53(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample53())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing obs of type boolean[] from the Sandwood model 
     */
    public final ComputedBooleanArray obs = $obs;

    private final ComputedIntegerInternal $y = new ComputedIntegerInternal(this, "y", true) {
        @Override
        public int getValue() { return system$c.get$y(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$y(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$y(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample41(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample41())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing y of type int from the Sandwood model 
     */
    public final ComputedInteger y = $y;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedBooleanArrayShapeableInternal $obs_measured = new ObservedBooleanArrayShapeableInternal(this, "obs_measured") {
        @Override
        public boolean[] getValue() {
            synchronized(model) {
                return system$c.get$obs_measured();
            }
        }

        @Override
        public void setValueInternal(boolean[] value) {
            system$c.set$obs_measured(value);
            system$c.set$length$obs_measured(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$obs_measured(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$obs_measured();
        }
    };

    /**
     * Observed variable representing obs_measured of type boolean[] from the Sandwood model 
     */
    public final ObservedBooleanArrayShapeable obs_measured = $obs_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$d, $obs, $y};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public RaggedArray4() {
        super();
        //ComputedVariable
        $computedVariables.put("d", $d);
        $computedVariables.put("obs", $obs);
        $computedVariables.put("y", $y);

        //Observed array fields
        $shapedObservedValues.put("obs_measured", $obs_measured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param obs_measuredShape An integer array describing the shape of variable obs_measured to use in the model when generating results.
      */

    public RaggedArray4(int obs_measuredShape) {
        this();
        this.$obs_measured.setShape(obs_measuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param obs_measured The value to set obs_measured to.
      */

    public RaggedArray4(boolean[] obs_measured) {
        this();
        this.obs_measured.setValue(obs_measured);
    }
    
    @Override
    protected RaggedArray4$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        RaggedArray4$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new RaggedArray4$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new RaggedArray4$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(RaggedArray4$CoreInterface oldCore, RaggedArray4$CoreInterface newCore) {

        //Observed arrays
        if(obs_measured.isSet()) {
            newCore.set$obs_measured(oldCore.get$obs_measured());
            newCore.set$length$obs_measured(oldCore.get$length$obs_measured());
        }
        else if(obs_measured.shapeSet())
            newCore.set$length$obs_measured(oldCore.get$length$obs_measured());

        //ComputedVariables
        if(d.isSet())
            newCore.set$d(oldCore.get$d());
        if(obs.isSet())
            newCore.set$obs(oldCore.get$obs());
        if(y.isSet())
            newCore.set$y(oldCore.get$y());

        //Set fixed flags
        if(d.isSet())
            newCore.set$fixedFlag$sample44(oldCore.get$fixedFlag$sample44());
        if(obs.isSet())
            newCore.set$fixedFlag$sample53(oldCore.get$fixedFlag$sample53());
        if(y.isSet())
            newCore.set$fixedFlag$sample41(oldCore.get$fixedFlag$sample41());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input obs_measured */
        public final int obs_measuredShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param obs_measuredShape An integer array describing the shape of variable obs_measured to use in the model when generating results.
          */
        public InferValueInputs(int obs_measuredShape) {
            this.obs_measuredShape = obs_measuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input obs_measured */
        public final boolean[] obs_measured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param obs_measured The value to set obs_measured to.
          */
        public AllInputs(boolean[] obs_measured) {
            this.obs_measured = obs_measured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of d after a convention execution step.*/
        public final double[] d;
        /** Field holding the value of obs after a convention execution step.*/
        public final boolean[] obs;
        /** Field holding the value of y after a convention execution step.*/
        public final int y;

        InferredValueOutputs(RaggedArray4 system$model) {
            this.d = system$model.d.getSamples()[0];
            this.obs = system$model.obs.getSamples()[0];
            this.y = system$model.y.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable d */
        public final double d;
        /** Field holding the log probability of computed variable obs */
        public final double obs;
        /** Field holding the log probability of computed variable y */
        public final double y;

        LogProbabilities(RaggedArray4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.d = system$model.d.getLogProbability();
            this.obs = system$model.obs.getLogProbability();
            this.y = system$model.y.getLogProbability();
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
        /** Field holding the probability of computed variable d */
        public final double d;
        /** Field holding the probability of computed variable obs */
        public final double obs;
        /** Field holding the probability of computed variable y */
        public final double y;

        Probabilities(RaggedArray4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.d = system$model.d.getProbability();
            this.obs = system$model.obs.getProbability();
            this.y = system$model.y.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of d after an infer model call. */
        public final double[][] d;
        /** Field holding the MAP or Sample value of y after an infer model call. */
        public final int[] y;

        InferredModelOutputs(RaggedArray4 system$model) {
            this.d = system$model.getInferredValue(system$model.$d);
            this.y = system$model.getInferredValue(system$model.$y);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$obs_measured.setShape(inputs.obs_measuredShape);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
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
        this.$obs_measured.setValue(inputs.obs_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
