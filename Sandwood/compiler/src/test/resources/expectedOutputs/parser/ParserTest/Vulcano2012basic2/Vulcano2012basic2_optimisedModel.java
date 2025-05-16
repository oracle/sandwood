package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Vulcano2012basic2 This is the class that
  * all user interactions with the model should occur through.
  */
public class Vulcano2012basic2 extends Model {

    private Vulcano2012basic2$CoreInterface system$c = new Vulcano2012basic2$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<int[]> $Sales = new ComputedObjectArrayInternal<int[]>(this, "Sales", false, true, false, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return system$c.get$Sales(); }

        @Override
        protected void setValueInternal(int[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable Sales because it is fixed by observing a variable.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$Sales(); }

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

    /**
     * Computed variable representing Sales of type int[][] from the Sandwood model 
     */
    public final ComputedObjectArray<int[]> Sales = $Sales;

    private final ComputedDoubleArrayInternal $exped = new ComputedDoubleArrayInternal(this, "exped", false, false, false) {
        @Override
        public double[] getValue() { return system$c.get$exped(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable exped because its value depends on variable \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$exped(); }

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
     * Computed variable representing exped of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray exped = $exped;

    private final ComputedDoubleArrayInternal $expedNorm = new ComputedDoubleArrayInternal(this, "expedNorm", false, false, false) {
        @Override
        public double[] getValue() { return system$c.get$expedNorm(); }

        @Override
        protected void setValueInternal(double[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable expedNorm because its value depends on variables \"exped\", \"sum\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$expedNorm(); }

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
     * Computed variable representing expedNorm of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray expedNorm = $expedNorm;

    private final ComputedIntegerArrayInternal $sales_sum = new ComputedIntegerArrayInternal(this, "sales_sum", false, true, false) {
        @Override
        public int[] getValue() { return system$c.get$sales_sum(); }

        @Override
        protected void setValueInternal(int[] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable sales_sum because its value is fixed by observed values.");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$sales_sum(); }

        @Override
        public void setFixed(boolean fixed) {
            throw new SandwoodException("An observed variables can only have the value fixed to the observed value if the value is consumed by another random variable.");
        }

        @Override
        public Immutability isFixed() {
            return Immutability.OBSERVED;
        }
    };

    /**
     * Computed variable representing sales_sum of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray sales_sum = $sales_sum;

    private final ComputedDoubleInternal $sum = new ComputedDoubleInternal(this, "sum", false, false, false) {
        @Override
        public double getValue() { return system$c.get$sum(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable sum because its value depends on variables \"exped\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$sum(); }

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
     * Computed variable representing sum of type double from the Sandwood model 
     */
    public final ComputedDouble sum = $sum;

    private final ComputedDoubleArrayInternal $ut = new ComputedDoubleArrayInternal(this, "ut", true, true, false) {
        @Override
        public double[] getValue() { return system$c.get$ut(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$ut(value);
            intermediatesPrimed = false;
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$ut(); }

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
     * Computed variable representing ut of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray ut = $ut;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $Avail = new ObservedObjectArrayInternal<int[]>(this, "Avail", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return system$c.get$Avail();
            }
        }

        @Override
        protected void setValueInternal(int[][] value) { system$c.set$Avail(value); }
    };

    /**
     * Observed variable representing Avail of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArray<int[]> Avail = $Avail;

    private final ObservedIntegerInternal $T = new ObservedIntegerInternal(this, "T") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$T();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$T(value); }
    };

    /**
     * Observed variable representing T of type int from the Sandwood model 
     */
    public final ObservedInteger T = $T;

    private final ObservedIntegerInternal $noProducts = new ObservedIntegerInternal(this, "noProducts") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$noProducts();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$noProducts(value); }
    };

    /**
     * Observed variable representing noProducts of type int from the Sandwood model 
     */
    public final ObservedInteger noProducts = $noProducts;

    private final ObservedDoubleInternal $r = new ObservedDoubleInternal(this, "r") {
        @Override
        public double getValue() {
            synchronized(model) {
                return system$c.get$r();
            }
        }

        @Override
        protected void setValueInternal(double value) { system$c.set$r(value); }
    };

    /**
     * Observed variable representing r of type double from the Sandwood model 
     */
    public final ObservedDouble r = $r;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

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

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$Sales, $exped, $expedNorm, $sales_sum, $sum, $ut};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Vulcano2012basic2() {
        super();
        //ComputedVariable
        $computedVariables.put("Sales", $Sales);
        $computedVariables.put("exped", $exped);
        $computedVariables.put("expedNorm", $expedNorm);
        $computedVariables.put("sales_sum", $sales_sum);
        $computedVariables.put("sum", $sum);
        $computedVariables.put("ut", $ut);

        //ModelInputs
        $modelInputs.put("Avail", $Avail);
        $modelInputs.put("T", $T);
        $modelInputs.put("noProducts", $noProducts);
        $modelInputs.put("r", $r);

        //Observed scalar fields
        $regularObservedValues.put("ObsSales", $ObsSales);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param noProducts The value to set noProducts to.
      * @param T The value to set T to.
      * @param Avail The value to set Avail to.
      * @param r The value to set r to.
      */

    public Vulcano2012basic2(int noProducts, int T, int[][] Avail, double r) {
        this();
        this.$Avail.setValue(Avail);
        this.$T.setValue(T);
        this.$noProducts.setValue(noProducts);
        this.$r.setValue(r);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param noProducts The value to set noProducts to.
      * @param T The value to set T to.
      * @param ObsSales The value to set ObsSales to.
      * @param Avail The value to set Avail to.
      * @param r The value to set r to.
      */

    public Vulcano2012basic2(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {
        this();
        this.noProducts.setValue(noProducts);
        this.T.setValue(T);
        this.ObsSales.setValue(ObsSales);
        this.Avail.setValue(Avail);
        this.r.setValue(r);
    }
    
    @Override
    protected Vulcano2012basic2$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Vulcano2012basic2$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Vulcano2012basic2$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Vulcano2012basic2$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }

    private void transferData(Vulcano2012basic2$CoreInterface oldCore, Vulcano2012basic2$CoreInterface newCore) {
        //Model inputs
        if(Avail.isSet())
            newCore.set$Avail(oldCore.get$Avail());
        if(T.isSet())
            newCore.set$T(oldCore.get$T());
        if(noProducts.isSet())
            newCore.set$noProducts(oldCore.get$noProducts());
        if(r.isSet())
            newCore.set$r(oldCore.get$r());
        //Observed scalars
        if(ObsSales.isSet())
            newCore.set$ObsSales(oldCore.get$ObsSales());

        //ComputedVariables
        if($ut.isSet())
            newCore.set$ut(oldCore.get$ut());

        //Set fixed flags
        newCore.set$fixedFlag$sample26(oldCore.get$fixedFlag$sample26());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input noProducts */
        public final int noProducts;
        /** Field holding the value of model input T */
        public final int T;
        /** Field holding the value of model input Avail */
        public final int[][] Avail;
        /** Field holding the value of model input r */
        public final double r;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param noProducts The value to set noProducts to.
          * @param T The value to set T to.
          * @param Avail The value to set Avail to.
          * @param r The value to set r to.
          */
        public InferValueInputs(int noProducts, int T, int[][] Avail, double r) {
            this.Avail = Avail;
            this.T = T;
            this.noProducts = noProducts;
            this.r = r;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
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
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
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

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of Sales after a convention execution step.*/
        public final int[][] Sales;
        /** Field holding the value of exped after a convention execution step.*/
        public final double[] exped;
        /** Field holding the value of expedNorm after a convention execution step.*/
        public final double[] expedNorm;
        /** Field holding the value of sales_sum after a convention execution step.*/
        public final int[] sales_sum;
        /** Field holding the value of sum after a convention execution step.*/
        public final double sum;
        /** Field holding the value of ut after a convention execution step.*/
        public final double[] ut;

        InferredValueOutputs(Vulcano2012basic2 system$model) {
            this.Sales = system$model.Sales.getSamples()[0];
            this.exped = system$model.exped.getSamples()[0];
            this.expedNorm = system$model.expedNorm.getSamples()[0];
            this.sales_sum = system$model.sales_sum.getSamples()[0];
            this.sum = system$model.sum.getSamples()[0];
            this.ut = system$model.ut.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable Sales */
        public final double Sales;
        /** Field holding the log probability of computed variable exped */
        public final double exped;
        /** Field holding the log probability of computed variable expedNorm */
        public final double expedNorm;
        /** Field holding the log probability of computed variable sales_sum */
        public final double sales_sum;
        /** Field holding the log probability of computed variable sum */
        public final double sum;
        /** Field holding the log probability of computed variable ut */
        public final double ut;

        LogProbabilities(Vulcano2012basic2 system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.Sales = system$model.Sales.getLogProbability();
            this.exped = system$model.exped.getLogProbability();
            this.expedNorm = system$model.expedNorm.getLogProbability();
            this.sales_sum = system$model.sales_sum.getLogProbability();
            this.sum = system$model.sum.getLogProbability();
            this.ut = system$model.ut.getLogProbability();
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
        /** Field holding the probability of computed variable Sales */
        public final double Sales;
        /** Field holding the probability of computed variable exped */
        public final double exped;
        /** Field holding the probability of computed variable expedNorm */
        public final double expedNorm;
        /** Field holding the probability of computed variable sales_sum */
        public final double sales_sum;
        /** Field holding the probability of computed variable sum */
        public final double sum;
        /** Field holding the probability of computed variable ut */
        public final double ut;

        Probabilities(Vulcano2012basic2 system$model) {
            this.$modelProbability = system$model.getProbability();
            this.Sales = system$model.Sales.getProbability();
            this.exped = system$model.exped.getProbability();
            this.expedNorm = system$model.expedNorm.getProbability();
            this.sales_sum = system$model.sales_sum.getProbability();
            this.sum = system$model.sum.getProbability();
            this.ut = system$model.ut.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of exped after an infer model call. */
        public final double[][] exped;
        /** Field holding the MAP or Sample value of expedNorm after an infer model call. */
        public final double[][] expedNorm;
        /** Field holding the MAP or Sample value of sum after an infer model call. */
        public final double[] sum;
        /** Field holding the MAP or Sample value of ut after an infer model call. */
        public final double[][] ut;

        InferredModelOutputs(Vulcano2012basic2 system$model) {
            this.exped = system$model.getInferredValue(system$model.$exped);
            this.expedNorm = system$model.getInferredValue(system$model.$expedNorm);
            this.sum = system$model.getInferredValue(system$model.$sum);
            this.ut = system$model.getInferredValue(system$model.$ut);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
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
        this.Avail.setValue(inputs.Avail);
        this.T.setValue(inputs.T);
        this.noProducts.setValue(inputs.noProducts);
        this.r.setValue(inputs.r);
        this.$ObsSales.setValue(inputs.ObsSales);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
