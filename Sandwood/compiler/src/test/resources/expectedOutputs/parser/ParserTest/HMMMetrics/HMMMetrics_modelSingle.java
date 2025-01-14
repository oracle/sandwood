package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model HMMMetrics This is the class that
  * all user interactions with the model should occur through.
  */
public class HMMMetrics extends Model {

    private HMMMetrics$CoreInterface system$c = new HMMMetrics$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $cpu = new ComputedDoubleArrayInternal(this, "cpu", true) {
        @Override
        public double[] getValue() { return system$c.get$cpu(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$cpu(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$cpu(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample119(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample119())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing cpu of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray cpu = $cpu;

    private final ComputedDoubleArrayInternal $cpuMean = new ComputedDoubleArrayInternal(this, "cpuMean", true) {
        @Override
        public double[] getValue() { return system$c.get$cpuMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$cpuMean(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$cpuMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample58(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample58())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing cpuMean of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray cpuMean = $cpuMean;

    private final ComputedDoubleArrayInternal $cpuVar = new ComputedDoubleArrayInternal(this, "cpuVar", true) {
        @Override
        public double[] getValue() { return system$c.get$cpuVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$cpuVar(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$cpuVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample90(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample90())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing cpuVar of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray cpuVar = $cpuVar;

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
                system$c.set$fixedFlag$sample32(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample32())
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
                system$c.set$fixedFlag$sample25(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample25())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing m of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> m = $m;

    private final ComputedDoubleArrayInternal $mem = new ComputedDoubleArrayInternal(this, "mem", true) {
        @Override
        public double[] getValue() { return system$c.get$mem(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$mem(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$mem(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample124(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample124())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing mem of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray mem = $mem;

    private final ComputedDoubleArrayInternal $memMean = new ComputedDoubleArrayInternal(this, "memMean", true) {
        @Override
        public double[] getValue() { return system$c.get$memMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$memMean(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$memMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample69(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample69())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing memMean of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray memMean = $memMean;

    private final ComputedDoubleArrayInternal $memVar = new ComputedDoubleArrayInternal(this, "memVar", true) {
        @Override
        public double[] getValue() { return system$c.get$memVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$memVar(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$memVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample100(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample100())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing memVar of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray memVar = $memVar;

    private final ComputedDoubleArrayInternal $pageFaults = new ComputedDoubleArrayInternal(this, "pageFaults", true) {
        @Override
        public double[] getValue() { return system$c.get$pageFaults(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$pageFaults(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$pageFaults(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample129(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample129())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing pageFaults of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray pageFaults = $pageFaults;

    private final ComputedDoubleArrayInternal $pageFaultsMean = new ComputedDoubleArrayInternal(this, "pageFaultsMean", true) {
        @Override
        public double[] getValue() { return system$c.get$pageFaultsMean(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$pageFaultsMean(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$pageFaultsMean(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample80(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample80())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing pageFaultsMean of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray pageFaultsMean = $pageFaultsMean;

    private final ComputedDoubleArrayInternal $pageFaultsVar = new ComputedDoubleArrayInternal(this, "pageFaultsVar", true) {
        @Override
        public double[] getValue() { return system$c.get$pageFaultsVar(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$pageFaultsVar(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$pageFaultsVar(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample110(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample110())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing pageFaultsVar of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray pageFaultsVar = $pageFaultsVar;

    private final ComputedIntegerArrayInternal $st = new ComputedIntegerArrayInternal(this, "st", true) {
        @Override
        public int[] getValue() { return system$c.get$st(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$st(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$st(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
                system$c.set$fixedFlag$sample45(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample35 = system$c.get$fixedFlag$sample35();
            boolean fixedFlag$sample45 = system$c.get$fixedFlag$sample45();
            if(fixedFlag$sample35 && fixedFlag$sample45)
                return Immutability.FIXED;
            else if(fixedFlag$sample35 || fixedFlag$sample45)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing st of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray st = $st;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

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

    private final ObservedDoubleArrayInternal $mem_measured = new ObservedDoubleArrayInternal(this, "mem_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$mem_measured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$mem_measured(value); }
    };

    /**
     * Observed variable representing mem_measured of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray mem_measured = $mem_measured;

    private final ObservedDoubleArrayInternal $pageFaults_measured = new ObservedDoubleArrayInternal(this, "pageFaults_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$pageFaults_measured();
            }
        }

        @Override
        protected void setValueInternal(double[] value) { system$c.set$pageFaults_measured(value); }
    };

    /**
     * Observed variable representing pageFaults_measured of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArray pageFaults_measured = $pageFaults_measured;

    private final ObservedDoubleArrayShapeableInternal $cpu_measured = new ObservedDoubleArrayShapeableInternal(this, "cpu_measured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$cpu_measured();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            system$c.set$cpu_measured(value);
            system$c.set$length$cpu_measured(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$cpu_measured(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$cpu_measured();
        }
    };

    /**
     * Observed variable representing cpu_measured of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArrayShapeable cpu_measured = $cpu_measured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$cpu, $cpuMean, $cpuVar, $initialStateDistribution, $m, $mem, $memMean, $memVar, $pageFaults, $pageFaultsMean, $pageFaultsVar, $st};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public HMMMetrics() {
        super();
        //ComputedVariable
        $computedVariables.put("cpu", $cpu);
        $computedVariables.put("cpuMean", $cpuMean);
        $computedVariables.put("cpuVar", $cpuVar);
        $computedVariables.put("initialStateDistribution", $initialStateDistribution);
        $computedVariables.put("m", $m);
        $computedVariables.put("mem", $mem);
        $computedVariables.put("memMean", $memMean);
        $computedVariables.put("memVar", $memVar);
        $computedVariables.put("pageFaults", $pageFaults);
        $computedVariables.put("pageFaultsMean", $pageFaultsMean);
        $computedVariables.put("pageFaultsVar", $pageFaultsVar);
        $computedVariables.put("st", $st);

        //ModelInputs
        $modelInputs.put("noStates", $noStates);

        //Observed scalar fields
        $regularObservedValues.put("mem_measured", $mem_measured);
        $regularObservedValues.put("pageFaults_measured", $pageFaults_measured);

        //Observed array fields
        $shapedObservedValues.put("cpu_measured", $cpu_measured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param cpu_measuredShape An integer array describing the shape of variable cpu_measured to use in the model when generating results.
      * @param noStates The value to set noStates to.
      */

    public HMMMetrics(int cpu_measuredShape, int noStates) {
        this();
        this.$noStates.setValue(noStates);
        this.$cpu_measured.setShape(cpu_measuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param cpu_measured The value to set cpu_measured to.
      * @param mem_measured The value to set mem_measured to.
      * @param pageFaults_measured The value to set pageFaults_measured to.
      * @param noStates The value to set noStates to.
      */

    public HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {
        this();
        this.cpu_measured.setValue(cpu_measured);
        this.mem_measured.setValue(mem_measured);
        this.pageFaults_measured.setValue(pageFaults_measured);
        this.noStates.setValue(noStates);
    }
    
    @Override
    protected HMMMetrics$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        HMMMetrics$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new HMMMetrics$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new HMMMetrics$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(HMMMetrics$CoreInterface oldCore, HMMMetrics$CoreInterface newCore) {
        //Model inputs
        if(noStates.isSet())
            newCore.set$noStates(oldCore.get$noStates());
        //Observed scalars
        if(mem_measured.isSet())
            newCore.set$mem_measured(oldCore.get$mem_measured());
        if(pageFaults_measured.isSet())
            newCore.set$pageFaults_measured(oldCore.get$pageFaults_measured());

        //Observed arrays
        if(cpu_measured.isSet()) {
            newCore.set$cpu_measured(oldCore.get$cpu_measured());
            newCore.set$length$cpu_measured(oldCore.get$length$cpu_measured());
        }
        else if(cpu_measured.shapeSet())
            newCore.set$length$cpu_measured(oldCore.get$length$cpu_measured());

        //ComputedVariables
        if(cpu.isSet())
            newCore.set$cpu(oldCore.get$cpu());
        if(cpuMean.isSet())
            newCore.set$cpuMean(oldCore.get$cpuMean());
        if(cpuVar.isSet())
            newCore.set$cpuVar(oldCore.get$cpuVar());
        if(initialStateDistribution.isSet())
            newCore.set$initialStateDistribution(oldCore.get$initialStateDistribution());
        if(m.isSet())
            newCore.set$m(oldCore.get$m());
        if(mem.isSet())
            newCore.set$mem(oldCore.get$mem());
        if(memMean.isSet())
            newCore.set$memMean(oldCore.get$memMean());
        if(memVar.isSet())
            newCore.set$memVar(oldCore.get$memVar());
        if(pageFaults.isSet())
            newCore.set$pageFaults(oldCore.get$pageFaults());
        if(pageFaultsMean.isSet())
            newCore.set$pageFaultsMean(oldCore.get$pageFaultsMean());
        if(pageFaultsVar.isSet())
            newCore.set$pageFaultsVar(oldCore.get$pageFaultsVar());
        if(st.isSet())
            newCore.set$st(oldCore.get$st());

        //Set fixed flags
        if(cpu.isSet())
            newCore.set$fixedFlag$sample119(oldCore.get$fixedFlag$sample119());
        if(cpuMean.isSet())
            newCore.set$fixedFlag$sample58(oldCore.get$fixedFlag$sample58());
        if(cpuVar.isSet())
            newCore.set$fixedFlag$sample90(oldCore.get$fixedFlag$sample90());
        if(initialStateDistribution.isSet())
            newCore.set$fixedFlag$sample32(oldCore.get$fixedFlag$sample32());
        if(m.isSet())
            newCore.set$fixedFlag$sample25(oldCore.get$fixedFlag$sample25());
        if(mem.isSet())
            newCore.set$fixedFlag$sample124(oldCore.get$fixedFlag$sample124());
        if(memMean.isSet())
            newCore.set$fixedFlag$sample69(oldCore.get$fixedFlag$sample69());
        if(memVar.isSet())
            newCore.set$fixedFlag$sample100(oldCore.get$fixedFlag$sample100());
        if(pageFaults.isSet())
            newCore.set$fixedFlag$sample129(oldCore.get$fixedFlag$sample129());
        if(pageFaultsMean.isSet())
            newCore.set$fixedFlag$sample80(oldCore.get$fixedFlag$sample80());
        if(pageFaultsVar.isSet())
            newCore.set$fixedFlag$sample110(oldCore.get$fixedFlag$sample110());
        if(st.isSet()){
            newCore.set$fixedFlag$sample35(oldCore.get$fixedFlag$sample35());
            newCore.set$fixedFlag$sample45(oldCore.get$fixedFlag$sample45());
        }
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input cpu_measured */
        public final int cpu_measuredShape;
        /** Field holding the value of model input noStates */
        public final int noStates;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param cpu_measuredShape An integer array describing the shape of variable cpu_measured to use in the model when generating results.
          * @param noStates The value to set noStates to.
          */
        public InferValueInputs(int cpu_measuredShape, int noStates) {
            this.noStates = noStates;
            this.cpu_measuredShape = cpu_measuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input cpu_measured */
        public final double[] cpu_measured;
        /** Field holding the value of model input mem_measured */
        public final double[] mem_measured;
        /** Field holding the value of model input pageFaults_measured */
        public final double[] pageFaults_measured;
        /** Field holding the value of model input noStates */
        public final int noStates;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param cpu_measured The value to set cpu_measured to.
          * @param mem_measured The value to set mem_measured to.
          * @param pageFaults_measured The value to set pageFaults_measured to.
          * @param noStates The value to set noStates to.
          */
        public AllInputs(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {
            this.cpu_measured = cpu_measured;
            this.mem_measured = mem_measured;
            this.pageFaults_measured = pageFaults_measured;
            this.noStates = noStates;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of cpu after a convention execution step.*/
        public final double[] cpu;
        /** Field holding the value of cpuMean after a convention execution step.*/
        public final double[] cpuMean;
        /** Field holding the value of cpuVar after a convention execution step.*/
        public final double[] cpuVar;
        /** Field holding the value of initialStateDistribution after a convention execution step.*/
        public final double[] initialStateDistribution;
        /** Field holding the value of m after a convention execution step.*/
        public final double[][] m;
        /** Field holding the value of mem after a convention execution step.*/
        public final double[] mem;
        /** Field holding the value of memMean after a convention execution step.*/
        public final double[] memMean;
        /** Field holding the value of memVar after a convention execution step.*/
        public final double[] memVar;
        /** Field holding the value of pageFaults after a convention execution step.*/
        public final double[] pageFaults;
        /** Field holding the value of pageFaultsMean after a convention execution step.*/
        public final double[] pageFaultsMean;
        /** Field holding the value of pageFaultsVar after a convention execution step.*/
        public final double[] pageFaultsVar;
        /** Field holding the value of st after a convention execution step.*/
        public final int[] st;

        InferredValueOutputs(HMMMetrics system$model) {
            this.cpu = system$model.cpu.getSamples()[0];
            this.cpuMean = system$model.cpuMean.getSamples()[0];
            this.cpuVar = system$model.cpuVar.getSamples()[0];
            this.initialStateDistribution = system$model.initialStateDistribution.getSamples()[0];
            this.m = system$model.m.getSamples()[0];
            this.mem = system$model.mem.getSamples()[0];
            this.memMean = system$model.memMean.getSamples()[0];
            this.memVar = system$model.memVar.getSamples()[0];
            this.pageFaults = system$model.pageFaults.getSamples()[0];
            this.pageFaultsMean = system$model.pageFaultsMean.getSamples()[0];
            this.pageFaultsVar = system$model.pageFaultsVar.getSamples()[0];
            this.st = system$model.st.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable cpu */
        public final double cpu;
        /** Field holding the log probability of computed variable cpuMean */
        public final double cpuMean;
        /** Field holding the log probability of computed variable cpuVar */
        public final double cpuVar;
        /** Field holding the log probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
        /** Field holding the log probability of computed variable m */
        public final double m;
        /** Field holding the log probability of computed variable mem */
        public final double mem;
        /** Field holding the log probability of computed variable memMean */
        public final double memMean;
        /** Field holding the log probability of computed variable memVar */
        public final double memVar;
        /** Field holding the log probability of computed variable pageFaults */
        public final double pageFaults;
        /** Field holding the log probability of computed variable pageFaultsMean */
        public final double pageFaultsMean;
        /** Field holding the log probability of computed variable pageFaultsVar */
        public final double pageFaultsVar;
        /** Field holding the log probability of computed variable st */
        public final double st;

        LogProbabilities(HMMMetrics system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.cpu = system$model.cpu.getLogProbability();
            this.cpuMean = system$model.cpuMean.getLogProbability();
            this.cpuVar = system$model.cpuVar.getLogProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getLogProbability();
            this.m = system$model.m.getLogProbability();
            this.mem = system$model.mem.getLogProbability();
            this.memMean = system$model.memMean.getLogProbability();
            this.memVar = system$model.memVar.getLogProbability();
            this.pageFaults = system$model.pageFaults.getLogProbability();
            this.pageFaultsMean = system$model.pageFaultsMean.getLogProbability();
            this.pageFaultsVar = system$model.pageFaultsVar.getLogProbability();
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
        /** Field holding the probability of computed variable cpu */
        public final double cpu;
        /** Field holding the probability of computed variable cpuMean */
        public final double cpuMean;
        /** Field holding the probability of computed variable cpuVar */
        public final double cpuVar;
        /** Field holding the probability of computed variable initialStateDistribution */
        public final double initialStateDistribution;
        /** Field holding the probability of computed variable m */
        public final double m;
        /** Field holding the probability of computed variable mem */
        public final double mem;
        /** Field holding the probability of computed variable memMean */
        public final double memMean;
        /** Field holding the probability of computed variable memVar */
        public final double memVar;
        /** Field holding the probability of computed variable pageFaults */
        public final double pageFaults;
        /** Field holding the probability of computed variable pageFaultsMean */
        public final double pageFaultsMean;
        /** Field holding the probability of computed variable pageFaultsVar */
        public final double pageFaultsVar;
        /** Field holding the probability of computed variable st */
        public final double st;

        Probabilities(HMMMetrics system$model) {
            this.$modelProbability = system$model.getProbability();
            this.cpu = system$model.cpu.getProbability();
            this.cpuMean = system$model.cpuMean.getProbability();
            this.cpuVar = system$model.cpuVar.getProbability();
            this.initialStateDistribution = system$model.initialStateDistribution.getProbability();
            this.m = system$model.m.getProbability();
            this.mem = system$model.mem.getProbability();
            this.memMean = system$model.memMean.getProbability();
            this.memVar = system$model.memVar.getProbability();
            this.pageFaults = system$model.pageFaults.getProbability();
            this.pageFaultsMean = system$model.pageFaultsMean.getProbability();
            this.pageFaultsVar = system$model.pageFaultsVar.getProbability();
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
        /** Field holding the MAP or Sample value of cpuMean after an infer model call. */
        public final double[][] cpuMean;
        /** Field holding the MAP or Sample value of cpuVar after an infer model call. */
        public final double[][] cpuVar;
        /** Field holding the MAP or Sample value of initialStateDistribution after an infer model call. */
        public final double[][] initialStateDistribution;
        /** Field holding the MAP or Sample value of m after an infer model call. */
        public final double[][][] m;
        /** Field holding the MAP or Sample value of memMean after an infer model call. */
        public final double[][] memMean;
        /** Field holding the MAP or Sample value of memVar after an infer model call. */
        public final double[][] memVar;
        /** Field holding the MAP or Sample value of pageFaultsMean after an infer model call. */
        public final double[][] pageFaultsMean;
        /** Field holding the MAP or Sample value of pageFaultsVar after an infer model call. */
        public final double[][] pageFaultsVar;
        /** Field holding the MAP or Sample value of st after an infer model call. */
        public final int[][] st;

        InferredModelOutputs(HMMMetrics system$model) {
            this.cpuMean = system$model.getInferredValue(system$model.$cpuMean);
            this.cpuVar = system$model.getInferredValue(system$model.$cpuVar);
            this.initialStateDistribution = system$model.getInferredValue(system$model.$initialStateDistribution);
            this.m = system$model.getInferredValue(system$model.$m);
            this.memMean = system$model.getInferredValue(system$model.$memMean);
            this.memVar = system$model.getInferredValue(system$model.$memVar);
            this.pageFaultsMean = system$model.getInferredValue(system$model.$pageFaultsMean);
            this.pageFaultsVar = system$model.getInferredValue(system$model.$pageFaultsVar);
            this.st = system$model.getInferredValue(system$model.$st);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setShape(inputs.cpu_measuredShape);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
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
        this.noStates.setValue(inputs.noStates);
        this.$cpu_measured.setValue(inputs.cpu_measured);
        this.$mem_measured.setValue(inputs.mem_measured);
        this.$pageFaults_measured.setValue(inputs.pageFaults_measured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
