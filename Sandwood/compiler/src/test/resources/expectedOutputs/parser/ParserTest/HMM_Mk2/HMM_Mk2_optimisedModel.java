package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model HMM_Mk2 This is the class that
  * all user interactions with the model should occur through.
  */
public class HMM_Mk2 extends Model {

    private HMM_Mk2$CoreInterface system$c = new HMM_Mk2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<double[]> $bias = new ComputedObjectArrayInternal<double[]>(this, "bias", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$bias(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$bias(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$bias(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample33(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample33())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing bias of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> bias = $bias;

    private final ComputedObjectArrayInternal<int[]> $events = new ComputedObjectArrayInternal<int[]>(this, "events", true, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        protected int[][] getValue() { return system$c.get$events(); }

        @Override
        protected void setValueInternal(int[][] value) {
            system$c.set$events(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$events(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample99(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample99())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing events of type int[][] from the Sandwood model 
     */
    public final ComputedObjectArray<int[]> events = $events;

    private final ComputedIntegerInternal $initialState = new ComputedIntegerInternal(this, "initialState", true) {
        @Override
        protected int getValue() { return system$c.get$initialState(); }

        @Override
        protected void setValueInternal(int value) {
            system$c.set$initialState(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$initialState(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample50(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample50())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing initialState of type int from the Sandwood model 
     */
    public final ComputedInteger initialState = $initialState;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$m(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$m(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample26(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample26())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing m of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedObjectArrayInternal<int[]> $st = new ComputedObjectArrayInternal<int[]>(this, "st", true, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        protected int[][] getValue() { return system$c.get$st(); }

        @Override
        protected void setValueInternal(int[][] value) {
            system$c.set$st(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$st(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample58(fixed);
                system$c.set$fixedFlag$sample78(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample58 = system$c.get$fixedFlag$sample58();
            boolean fixedFlag$sample78 = system$c.get$fixedFlag$sample78();
            if(fixedFlag$sample58 && fixedFlag$sample78)
                return Immutability.FIXED;
            else if(fixedFlag$sample58 || fixedFlag$sample78)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing st of type int[][] from the Sandwood model 
     */
    public final ComputedObjectArray<int[]> st = $st;

    private final ComputedDoubleArrayInternal $weights = new ComputedDoubleArrayInternal(this, "weights", true) {
        @Override
        protected double[] getValue() { return system$c.get$weights(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$weights(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$weights(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample48(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample48())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing weights of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray weights = $weights;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $noEvents = new ObservedIntegerInternal(this, "noEvents") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$noEvents();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$noEvents(value); }
    };

    /**
     * Observed variable representing noEvents of type int from the Sandwood model 
     */
    public final ObservedInteger noEvents = $noEvents;

    private final ObservedIntegerInternal $noStates = new ObservedIntegerInternal(this, "noStates") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$noStates();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$noStates(value); }
    };

    /**
     * Observed variable representing noStates of type int from the Sandwood model 
     */
    public final ObservedInteger noStates = $noStates;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayShapeableInternal<int[], int[]> $eventsMeasured = new ObservedObjectArrayShapeableInternal<int[], int[]>(this, "eventsMeasured", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] get() {
            synchronized(model) {
                return system$c.get$eventsMeasured();
            }
        }

        @Override
        public void setValue(int[][] value) {
            system$c.set$eventsMeasured(value);
            system$c.set$length$eventsMeasured(getDims(value));
        }

        @Override
        public void setShapeInternal(int[] shape) {
            system$c.set$length$eventsMeasured(shape);
        }

        @Override
        public int[] getShape() {
            return system$c.get$length$eventsMeasured();
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
     * Observed variable representing eventsMeasured of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArrayShapeable<int[], int[]> eventsMeasured = $eventsMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$bias, $events, $initialState, $m, $st, $weights};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public HMM_Mk2() {
        super();
        //ComputedVariable
        $computedVariables.put("bias", $bias);
        $computedVariables.put("events", $events);
        $computedVariables.put("initialState", $initialState);
        $computedVariables.put("m", $m);
        $computedVariables.put("st", $st);
        $computedVariables.put("weights", $weights);

        //ModelInputs
        $modelInputs.put("noEvents", $noEvents);
        $modelInputs.put("noStates", $noStates);

        //Observed array fields
        $shapedObservedValues.put("eventsMeasured", $eventsMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param eventsMeasuredShape An integer array describing the shape of variable eventsMeasured to use in the model when generating results.
      * @param noStates The value to set noStates to.
      * @param noEvents The value to set noEvents to.
      */

    public HMM_Mk2(int[] eventsMeasuredShape, int noStates, int noEvents) {
        this();
        this.$noEvents.set(noEvents);
        this.$noStates.set(noStates);
        this.$eventsMeasured.setShape(eventsMeasuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param eventsMeasured The value to set eventsMeasured to.
      * @param noStates The value to set noStates to.
      * @param noEvents The value to set noEvents to.
      */

    public HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {
        this();
        this.eventsMeasured.set(eventsMeasured);
        this.noStates.set(noStates);
        this.noEvents.set(noEvents);
    }
    
    @Override
    protected HMM_Mk2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        HMM_Mk2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new HMM_Mk2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new HMM_Mk2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(HMM_Mk2$CoreInterface oldCore, HMM_Mk2$CoreInterface newCore) {
        //Model inputs
        if(noEvents.isSet())
            newCore.set$noEvents(oldCore.get$noEvents());
        if(noStates.isSet())
            newCore.set$noStates(oldCore.get$noStates());

        //Observed arrays
        if(eventsMeasured.isSet()) {
            newCore.set$eventsMeasured(oldCore.get$eventsMeasured());
            newCore.set$length$eventsMeasured(oldCore.get$length$eventsMeasured());
        }
        else if(eventsMeasured.shapeSet())
            newCore.set$length$eventsMeasured(oldCore.get$length$eventsMeasured());

        //ComputedVariables
        if(bias.isSet())
            newCore.set$bias(oldCore.get$bias());
        if(events.isSet())
            newCore.set$events(oldCore.get$events());
        if(initialState.isSet())
            newCore.set$initialState(oldCore.get$initialState());
        if(m.isSet())
            newCore.set$m(oldCore.get$m());
        if(st.isSet())
            newCore.set$st(oldCore.get$st());
        if(weights.isSet())
            newCore.set$weights(oldCore.get$weights());

        //Set fixed flags
        if(bias.isSet())
            newCore.set$fixedFlag$sample33(oldCore.get$fixedFlag$sample33());
        if(events.isSet())
            newCore.set$fixedFlag$sample99(oldCore.get$fixedFlag$sample99());
        if(initialState.isSet())
            newCore.set$fixedFlag$sample50(oldCore.get$fixedFlag$sample50());
        if(m.isSet())
            newCore.set$fixedFlag$sample26(oldCore.get$fixedFlag$sample26());
        if(st.isSet()){
            newCore.set$fixedFlag$sample58(oldCore.get$fixedFlag$sample58());
            newCore.set$fixedFlag$sample78(oldCore.get$fixedFlag$sample78());
        }
        if(weights.isSet())
            newCore.set$fixedFlag$sample48(oldCore.get$fixedFlag$sample48());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input eventsMeasured */
        public final int[] eventsMeasuredShape;
        /** Field holding the value of model input noStates */
        public final int noStates;
        /** Field holding the value of model input noEvents */
        public final int noEvents;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param eventsMeasuredShape An integer array describing the shape of variable eventsMeasured to use in the model when generating results.
          * @param noStates The value to set noStates to.
          * @param noEvents The value to set noEvents to.
          */
        public InferValueInputs(int[] eventsMeasuredShape, int noStates, int noEvents) {
            this.noEvents = noEvents;
            this.noStates = noStates;
            this.eventsMeasuredShape = eventsMeasuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input eventsMeasured */
        public final int[][] eventsMeasured;
        /** Field holding the value of model input noStates */
        public final int noStates;
        /** Field holding the value of model input noEvents */
        public final int noEvents;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param eventsMeasured The value to set eventsMeasured to.
          * @param noStates The value to set noStates to.
          * @param noEvents The value to set noEvents to.
          */
        public AllInputs(int[][] eventsMeasured, int noStates, int noEvents) {
            this.eventsMeasured = eventsMeasured;
            this.noStates = noStates;
            this.noEvents = noEvents;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of bias after a convention execution step.*/
        public final double[][] bias;
        /** Field holding the value of events after a convention execution step.*/
        public final int[][] events;
        /** Field holding the value of initialState after a convention execution step.*/
        public final int initialState;
        /** Field holding the value of m after a convention execution step.*/
        public final double[][] m;
        /** Field holding the value of st after a convention execution step.*/
        public final int[][] st;
        /** Field holding the value of weights after a convention execution step.*/
        public final double[] weights;

        InferredValueOutputs(HMM_Mk2 system$model) {
            this.bias = system$model.bias.getSamples()[0];
            this.events = system$model.events.getSamples()[0];
            this.initialState = system$model.initialState.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
            this.weights = system$model.weights.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable bias */
        public final double bias;
        /** Field holding the log probability of computed variable events */
        public final double events;
        /** Field holding the log probability of computed variable initialState */
        public final double initialState;
        /** Field holding the log probability of computed variable m */
        public final double m;
        /** Field holding the log probability of computed variable st */
        public final double st;
        /** Field holding the log probability of computed variable weights */
        public final double weights;

        LogProbabilities(HMM_Mk2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.bias = system$model.bias.getLogProbability();
            this.events = system$model.events.getLogProbability();
            this.initialState = system$model.initialState.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.st = system$model.st.getLogProbability();
            this.weights = system$model.weights.getLogProbability();
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
        /** Field holding the probability of computed variable bias */
        public final double bias;
        /** Field holding the probability of computed variable events */
        public final double events;
        /** Field holding the probability of computed variable initialState */
        public final double initialState;
        /** Field holding the probability of computed variable m */
        public final double m;
        /** Field holding the probability of computed variable st */
        public final double st;
        /** Field holding the probability of computed variable weights */
        public final double weights;

        Probabilities(HMM_Mk2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.bias = system$model.bias.getProbability();
            this.events = system$model.events.getProbability();
            this.initialState = system$model.initialState.getProbability();
            this.m = system$model.m.getProbability();
            this.st = system$model.st.getProbability();
            this.weights = system$model.weights.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of bias after an infer model call. */
        public final double[][][] bias;
        /** Field holding the MAP or Sample value of initialState after an infer model call. */
        public final int[] initialState;
        /** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
        /** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][][] st;
        /** Field holding the MAP or Sample value of weights after an infer model call. */
        public final double[][] weights;

        InferredModelOutputs(HMM_Mk2 system$model) {
            this.bias = system$model.getInferredValue(system$model.$bias);
            this.initialState = system$model.getInferredValue(system$model.$initialState);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
            this.weights = system$model.getInferredValue(system$model.$weights);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.setShape(inputs.eventsMeasuredShape);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
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
        this.noEvents.set(inputs.noEvents);
        this.noStates.set(inputs.noStates);
        this.$eventsMeasured.set(inputs.eventsMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
