package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model HMMMetrics4 This is the class that
  * all user interactions with the model should occur through.
  */
public class HMMMetrics4 extends Model {

    private HMMMetrics4$CoreInterface system$c = new HMMMetrics4$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<double[]> $current_metric_mean = new ComputedObjectArrayInternal<double[]>(this, "current_metric_mean", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$current_metric_mean(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$current_metric_mean(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$current_metric_mean(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample96(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample96())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing current_metric_mean of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> current_metric_mean = $current_metric_mean;

    private final ComputedObjectArrayInternal<double[]> $current_metric_valid_bias = new ComputedObjectArrayInternal<double[]>(this, "current_metric_valid_bias", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$current_metric_valid_bias(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$current_metric_valid_bias(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$current_metric_valid_bias(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample126(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample126())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing current_metric_valid_bias of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> current_metric_valid_bias = $current_metric_valid_bias;

    private final ComputedObjectArrayInternal<double[]> $current_metric_var = new ComputedObjectArrayInternal<double[]>(this, "current_metric_var", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$current_metric_var(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$current_metric_var(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$current_metric_var(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample111(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample111())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing current_metric_var of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> current_metric_var = $current_metric_var;

    private final ComputedDoubleArrayInternal $initialStateDistribution = new ComputedDoubleArrayInternal(this, "initialStateDistribution", true) {
        @Override
        public double[] getValue() { return system$c.get$initialStateDistribution(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$initialStateDistribution(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$initialStateDistribution(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample24(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample24())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing initialStateDistribution of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray initialStateDistribution = $initialStateDistribution;

    private final ComputedObjectArrayInternal<double[]> $m = new ComputedObjectArrayInternal<double[]>(this, "m", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$m(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$m(value);
            valueSet = true;
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
                system$c.set$fixedFlag$sample30(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample30())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing m of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedObjectArrayInternal<double[][]> $metric_g = new ComputedObjectArrayInternal<double[][]>(this, "metric_g", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() { return system$c.get$metric_g(); }

        @Override
        protected void setValueInternal(double[][][] value) {
            system$c.set$metric_g(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$metric_g(); }

        @Override
        public double[][][][] constructArray(int iterations) {
            return new double[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample173(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample173())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing metric_g of type double[][][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[][]> metric_g = $metric_g;

    private final ComputedObjectArrayInternal<boolean[][]> $metric_valid_g = new ComputedObjectArrayInternal<boolean[][]>(this, "metric_valid_g", false, org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 3) {
        @Override
        public boolean[][][] getValue() { return system$c.get$metric_valid_g(); }

        @Override
        protected void setValueInternal(boolean[][][] value) {
            system$c.set$metric_valid_g(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$metric_valid_g(); }

        @Override
        public boolean[][][][] constructArray(int iterations) {
            return new boolean[iterations][][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample161(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample161())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing metric_valid_g of type boolean[][][] from the Sandwood model 
     */
    public final ComputedObjectArray<boolean[][]> metric_valid_g = $metric_valid_g;

    private final ComputedObjectArrayInternal<int[]> $st = new ComputedObjectArrayInternal<int[]>(this, "st", true, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return system$c.get$st(); }

        @Override
        protected void setValueInternal(int[][] value) {
            system$c.set$st(value);
            valueSet = true;
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
                system$c.set$fixedFlag$sample50(fixed);
                system$c.set$fixedFlag$sample63(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample50 = system$c.get$fixedFlag$sample50();
            boolean fixedFlag$sample63 = system$c.get$fixedFlag$sample63();
            if(fixedFlag$sample50 && fixedFlag$sample63)
                return Immutability.FIXED;
            else if(fixedFlag$sample50 || fixedFlag$sample63)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing st of type int[][] from the Sandwood model 
     */
    public final ComputedObjectArray<int[]> st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $max_metric = new ObservedIntegerInternal(this, "max_metric") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$max_metric();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$max_metric(value); }
    };

    /**
     * Observed variable representing max_metric of type int from the Sandwood model 
     */
    public final ObservedInteger max_metric = $max_metric;

    private final ObservedIntegerInternal $noStates = new ObservedIntegerInternal(this, "noStates") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$noStates();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$noStates(value); }
    };

    /**
     * Observed variable representing noStates of type int from the Sandwood model 
     */
    public final ObservedInteger noStates = $noStates;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<boolean[][]> $metric_valid = new ObservedObjectArrayInternal<boolean[][]>(this, "metric_valid", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN, 3) {
        @Override
        public boolean[][][] getValue() {
            synchronized(model) {
                return system$c.get$metric_valid();
            }
        }

        @Override
        protected void setValueInternal(boolean[][][] value) { system$c.set$metric_valid(value); }
    };

    /**
     * Observed variable representing metric_valid of type boolean[][][] from the Sandwood model 
     */
    public final ObservedObjectArray<boolean[][]> metric_valid = $metric_valid;

    private final ObservedObjectArrayShapeableInternal<double[][], int[][]> $metric = new ObservedObjectArrayShapeableInternal<double[][], int[][]>(this, "metric", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 3) {
        @Override
        public double[][][] getValue() {
            synchronized(model) {
                return system$c.get$metric();
            }
        }

        @Override
        public void setValueInternal(double[][][] value) {
            system$c.set$metric(value);
            system$c.set$length$metric(getDims(value));
        }

        @Override
        public void setShapeInternal(int[][] shape) {
            system$c.set$length$metric(shape);
        }

        @Override
        public int[][] getShape() {
            return system$c.get$length$metric();
        }
        private final int[][] getDims(double[][][] v2) {
            int[][] s2 = new int[v2.length][];
            for(int i2 = 0; i2 < v2.length; i2++) {
                double[][] v1 = v2[i2];
                int[] s1 = new int[v1.length];
                for(int i1 = 0; i1 < v1.length; i1++) {
                    double[] v0 = v1[i1];
                    s1[i1] = v0.length;
                }
                s2[i2] = s1;
            }
            return s2;
        }
    };

    /**
     * Observed variable representing metric of type double[][][] from the Sandwood model 
     */
    public final ObservedObjectArrayShapeable<double[][], int[][]> metric = $metric;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$current_metric_mean, $current_metric_valid_bias, $current_metric_var, $initialStateDistribution, $m, $metric_g, $metric_valid_g, $st};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public HMMMetrics4() {
        super();
        //ComputedVariable
        $computedVariables.put("current_metric_mean", $current_metric_mean);
        $computedVariables.put("current_metric_valid_bias", $current_metric_valid_bias);
        $computedVariables.put("current_metric_var", $current_metric_var);
        $computedVariables.put("initialStateDistribution", $initialStateDistribution);
        $computedVariables.put("m", $m);
        $computedVariables.put("metric_g", $metric_g);
        $computedVariables.put("metric_valid_g", $metric_valid_g);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("max_metric", $max_metric);
        $modelInputs.put("noStates", $noStates);

        //Observed scalar fields
        $regularObservedValues.put("metric_valid", $metric_valid);

        //Observed array fields
        $shapedObservedValues.put("metric", $metric);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param metricShape An integer array describing the shape of variable metric to use in the model when generating results.
      * @param max_metric The value to set max_metric to.
      * @param noStates The value to set noStates to.
      */

    public HMMMetrics4(int[][] metricShape, int max_metric, int noStates) {
        this();
        this.$max_metric.setValue(max_metric);
        this.$noStates.setValue(noStates);
        this.$metric.setShape(metricShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param metric The value to set metric to.
      * @param metric_valid The value to set metric_valid to.
      * @param max_metric The value to set max_metric to.
      * @param noStates The value to set noStates to.
      */

    public HMMMetrics4(double[][][] metric, boolean[][][] metric_valid, int max_metric, int noStates) {
        this();
        this.metric.setValue(metric);
        this.metric_valid.setValue(metric_valid);
        this.max_metric.setValue(max_metric);
        this.noStates.setValue(noStates);
    }
    
    @Override
    protected HMMMetrics4$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        HMMMetrics4$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new HMMMetrics4$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new HMMMetrics4$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(HMMMetrics4$CoreInterface oldCore, HMMMetrics4$CoreInterface newCore) {
        //Model inputs
        if(max_metric.isSet())
            newCore.set$max_metric(oldCore.get$max_metric());
        if(noStates.isSet())
            newCore.set$noStates(oldCore.get$noStates());
        //Observed scalars
        if(metric_valid.isSet())
            newCore.set$metric_valid(oldCore.get$metric_valid());

        //Observed arrays
        if(metric.isSet()) {
            newCore.set$metric(oldCore.get$metric());
            newCore.set$length$metric(oldCore.get$length$metric());
        }
        else if(metric.shapeSet())
            newCore.set$length$metric(oldCore.get$length$metric());

        //ComputedVariables
        if(current_metric_mean.isSet())
            newCore.set$current_metric_mean(oldCore.get$current_metric_mean());
        if(current_metric_valid_bias.isSet())
            newCore.set$current_metric_valid_bias(oldCore.get$current_metric_valid_bias());
        if(current_metric_var.isSet())
            newCore.set$current_metric_var(oldCore.get$current_metric_var());
        if(initialStateDistribution.isSet())
            newCore.set$initialStateDistribution(oldCore.get$initialStateDistribution());
        if(m.isSet())
            newCore.set$m(oldCore.get$m());
        if(metric_g.isSet())
            newCore.set$metric_g(oldCore.get$metric_g());
        if(metric_valid_g.isSet())
            newCore.set$metric_valid_g(oldCore.get$metric_valid_g());
        if(st.isSet())
            newCore.set$st(oldCore.get$st());

        //Set fixed flags
        if(current_metric_mean.isSet())
            newCore.set$fixedFlag$sample96(oldCore.get$fixedFlag$sample96());
        if(current_metric_valid_bias.isSet())
            newCore.set$fixedFlag$sample126(oldCore.get$fixedFlag$sample126());
        if(current_metric_var.isSet())
            newCore.set$fixedFlag$sample111(oldCore.get$fixedFlag$sample111());
        if(initialStateDistribution.isSet())
            newCore.set$fixedFlag$sample24(oldCore.get$fixedFlag$sample24());
        if(m.isSet())
            newCore.set$fixedFlag$sample30(oldCore.get$fixedFlag$sample30());
        if(metric_g.isSet())
            newCore.set$fixedFlag$sample173(oldCore.get$fixedFlag$sample173());
        if(st.isSet()){
            newCore.set$fixedFlag$sample50(oldCore.get$fixedFlag$sample50());
            newCore.set$fixedFlag$sample63(oldCore.get$fixedFlag$sample63());
        }
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input metric */
        public final int[][] metricShape;
        /** Field holding the value of model input max_metric */
        public final int max_metric;
        /** Field holding the value of model input noStates */
        public final int noStates;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param metricShape An integer array describing the shape of variable metric to use in the model when generating results.
          * @param max_metric The value to set max_metric to.
          * @param noStates The value to set noStates to.
          */
        public InferValueInputs(int[][] metricShape, int max_metric, int noStates) {
            this.max_metric = max_metric;
            this.noStates = noStates;
            this.metricShape = metricShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input metric */
        public final double[][][] metric;
        /** Field holding the value of model input metric_valid */
        public final boolean[][][] metric_valid;
        /** Field holding the value of model input max_metric */
        public final int max_metric;
        /** Field holding the value of model input noStates */
        public final int noStates;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param metric The value to set metric to.
          * @param metric_valid The value to set metric_valid to.
          * @param max_metric The value to set max_metric to.
          * @param noStates The value to set noStates to.
          */
        public AllInputs(double[][][] metric, boolean[][][] metric_valid, int max_metric, int noStates) {
            this.metric = metric;
            this.metric_valid = metric_valid;
            this.max_metric = max_metric;
            this.noStates = noStates;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of current_metric_mean after a convention execution step.*/
        public final double[][] current_metric_mean;
        /** Field holding the value of current_metric_valid_bias after a convention execution step.*/
        public final double[][] current_metric_valid_bias;
        /** Field holding the value of current_metric_var after a convention execution step.*/
        public final double[][] current_metric_var;
        /** Field holding the value of initialStateDistribution after a convention execution step.*/
        public final double[] initialStateDistribution;
        /** Field holding the value of m after a convention execution step.*/
        public final double[][] m;
        /** Field holding the value of metric_g after a convention execution step.*/
        public final double[][][] metric_g;
        /** Field holding the value of metric_valid_g after a convention execution step.*/
        public final boolean[][][] metric_valid_g;
        /** Field holding the value of st after a convention execution step.*/
        public final int[][] st;

        InferredValueOutputs(HMMMetrics4 system$model) {
            this.current_metric_mean = system$model.current_metric_mean.getSamples()[0];
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getSamples()[0];
            this.current_metric_var = system$model.current_metric_var.getSamples()[0];
            this.initialStateDistribution = system$model.initialStateDistribution.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.metric_g = system$model.metric_g.getSamples()[0];
            this.metric_valid_g = system$model.metric_valid_g.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable current_metric_mean */
        public final double current_metric_mean;
        /** Field holding the log probability of computed variable current_metric_valid_bias */
        public final double current_metric_valid_bias;
        /** Field holding the log probability of computed variable current_metric_var */
        public final double current_metric_var;
        /** Field holding the log probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
        /** Field holding the log probability of computed variable m */
        public final double m;
        /** Field holding the log probability of computed variable metric_g */
        public final double metric_g;
        /** Field holding the log probability of computed variable metric_valid_g */
        public final double metric_valid_g;
        /** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMMetrics4 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.current_metric_mean = system$model.current_metric_mean.getLogProbability();
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getLogProbability();
            this.current_metric_var = system$model.current_metric_var.getLogProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.metric_g = system$model.metric_g.getLogProbability();
            this.metric_valid_g = system$model.metric_valid_g.getLogProbability();
            this.st = system$model.st.getLogProbability();
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
        /** Field holding the probability of computed variable current_metric_mean */
        public final double current_metric_mean;
        /** Field holding the probability of computed variable current_metric_valid_bias */
        public final double current_metric_valid_bias;
        /** Field holding the probability of computed variable current_metric_var */
        public final double current_metric_var;
        /** Field holding the probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
        /** Field holding the probability of computed variable m */
        public final double m;
        /** Field holding the probability of computed variable metric_g */
        public final double metric_g;
        /** Field holding the probability of computed variable metric_valid_g */
        public final double metric_valid_g;
        /** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMMetrics4 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.current_metric_mean = system$model.current_metric_mean.getProbability();
            this.current_metric_valid_bias = system$model.current_metric_valid_bias.getProbability();
            this.current_metric_var = system$model.current_metric_var.getProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getProbability();
            this.m = system$model.m.getProbability();
            this.metric_g = system$model.metric_g.getProbability();
            this.metric_valid_g = system$model.metric_valid_g.getProbability();
            this.st = system$model.st.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of current_metric_mean after an infer model call. */
        public final double[][][] current_metric_mean;
        /** Field holding the MAP or Sample value of current_metric_valid_bias after an infer model call. */
        public final double[][][] current_metric_valid_bias;
        /** Field holding the MAP or Sample value of current_metric_var after an infer model call. */
        public final double[][][] current_metric_var;
        /** Field holding the MAP or Sample value of initialStateDistribution after an infer model call. */
        public final double[][] initialStateDistribution;
        /** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
        /** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][][] st;

        InferredModelOutputs(HMMMetrics4 system$model) {
            this.current_metric_mean = system$model.getInferredValue(system$model.$current_metric_mean);
            this.current_metric_valid_bias = system$model.getInferredValue(system$model.$current_metric_valid_bias);
            this.current_metric_var = system$model.getInferredValue(system$model.$current_metric_var);
            this.initialStateDistribution = system$model.getInferredValue(system$model.$initialStateDistribution);
            this.m = system$model.getInferredValue(system$model.$m);
            this.st = system$model.getInferredValue(system$model.$st);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setShape(inputs.metricShape);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
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
        this.max_metric.setValue(inputs.max_metric);
        this.noStates.setValue(inputs.noStates);
        this.$metric.setValue(inputs.metric);
        this.$metric_valid.setValue(inputs.metric_valid);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
