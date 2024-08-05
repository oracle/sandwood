package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model Vulcano2012basic This is the class that
  * all user interactions with the model should occur through.
  */
public class Vulcano2012basic extends Model {

    private Vulcano2012basic$CoreInterface system$c = new Vulcano2012basic$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<double[]> $Sales = new ComputedObjectArrayInternal<double[]>(this, "Sales", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$Sales(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$Sales(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$Sales(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample85(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample85())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing Sales of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> Sales = $Sales;

    private final ComputedIntegerArrayInternal $arrivals = new ComputedIntegerArrayInternal(this, "arrivals", true) {
        @Override
        protected int[] getValue() { return system$c.get$arrivals(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$arrivals(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$arrivals(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample61(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample61())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing arrivals of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray arrivals = $arrivals;

    private final ComputedDoubleInternal $denom = new ComputedDoubleInternal(this, "denom", false) {
        @Override
        protected double getValue() { return system$c.get$denom(); }

        @Override
        protected void setValueInternal(double value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable denom because its value depends on variables \"exped\", \"sum\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$denom(); }

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
     * Computed variable representing denom of type double from the Sandwood model 
     */
    public final ComputedDouble denom = $denom;

    private final ComputedDoubleArrayInternal $exped = new ComputedDoubleArrayInternal(this, "exped", false) {
        @Override
        protected double[] getValue() { return system$c.get$exped(); }

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
     * Computed variable representing exped of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray exped = $exped;

    private final ComputedDoubleArrayInternal $lambda = new ComputedDoubleArrayInternal(this, "lambda", true) {
        @Override
        protected double[] getValue() { return system$c.get$lambda(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$lambda(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$lambda(); }

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
     * Computed variable representing lambda of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray lambda = $lambda;

    private final ComputedDoubleInternal $sum = new ComputedDoubleInternal(this, "sum", false) {
        @Override
        protected double getValue() { return system$c.get$sum(); }

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
     * Computed variable representing sum of type double from the Sandwood model 
     */
    public final ComputedDouble sum = $sum;

    private final ComputedDoubleArrayInternal $ut = new ComputedDoubleArrayInternal(this, "ut", true) {
        @Override
        protected double[] getValue() { return system$c.get$ut(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$ut(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$ut(); }

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
     * Computed variable representing ut of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray ut = $ut;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedObjectArrayInternal<int[]> $Avail = new ObservedObjectArrayInternal<int[]>(this, "Avail", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] get() {
            synchronized(model) {
                return system$c.get$Avail();
            }
        }

        @Override
        protected void setValue(int[][] value) { system$c.set$Avail(value); }
    };

    /**
     * Observed variable representing Avail of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArray<int[]> Avail = $Avail;

    private final ObservedIntegerInternal $T = new ObservedIntegerInternal(this, "T") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$T();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$T(value); }
    };

    /**
     * Observed variable representing T of type int from the Sandwood model 
     */
    public final ObservedInteger T = $T;

    private final ObservedIntegerInternal $noProducts = new ObservedIntegerInternal(this, "noProducts") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$noProducts();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$noProducts(value); }
    };

    /**
     * Observed variable representing noProducts of type int from the Sandwood model 
     */
    public final ObservedInteger noProducts = $noProducts;

    private final ObservedIntegerInternal $s = new ObservedIntegerInternal(this, "s") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$s();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$s(value); }
    };

    /**
     * Observed variable representing s of type int from the Sandwood model 
     */
    public final ObservedInteger s = $s;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayInternal<double[]> $ObsSales = new ObservedObjectArrayInternal<double[]>(this, "ObsSales", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] get() {
            synchronized(model) {
                return system$c.get$ObsSales();
            }
        }

        @Override
        protected void setValue(double[][] value) { system$c.set$ObsSales(value); }
    };

    /**
     * Observed variable representing ObsSales of type double[][] from the Sandwood model 
     */
    public final ObservedObjectArray<double[]> ObsSales = $ObsSales;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$Sales, $arrivals, $denom, $exped, $lambda, $sum, $ut};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public Vulcano2012basic() {
        super();
        //ComputedVariable
        $computedVariables.put("Sales", $Sales);
        $computedVariables.put("arrivals", $arrivals);
        $computedVariables.put("denom", $denom);
        $computedVariables.put("exped", $exped);
        $computedVariables.put("lambda", $lambda);
        $computedVariables.put("sum", $sum);
        $computedVariables.put("ut", $ut);

        //ModelInputs
        $modelInputs.put("Avail", $Avail);
        $modelInputs.put("T", $T);
        $modelInputs.put("noProducts", $noProducts);
        $modelInputs.put("s", $s);

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
      * @param s The value to set s to.
      * @param Avail The value to set Avail to.
      */

    public Vulcano2012basic(int noProducts, int T, int s, int[][] Avail) {
        this();
        this.$Avail.set(Avail);
        this.$T.set(T);
        this.$noProducts.set(noProducts);
        this.$s.set(s);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param noProducts The value to set noProducts to.
      * @param T The value to set T to.
      * @param s The value to set s to.
      * @param ObsSales The value to set ObsSales to.
      * @param Avail The value to set Avail to.
      */

    public Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {
        this();
        this.noProducts.set(noProducts);
        this.T.set(T);
        this.s.set(s);
        this.ObsSales.set(ObsSales);
        this.Avail.set(Avail);
    }
    
    @Override
    protected Vulcano2012basic$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        Vulcano2012basic$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new Vulcano2012basic$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new Vulcano2012basic$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(Vulcano2012basic$CoreInterface oldCore, Vulcano2012basic$CoreInterface newCore) {
        //Model inputs
        if(Avail.isSet())
            newCore.set$Avail(oldCore.get$Avail());
        if(T.isSet())
            newCore.set$T(oldCore.get$T());
        if(noProducts.isSet())
            newCore.set$noProducts(oldCore.get$noProducts());
        if(s.isSet())
            newCore.set$s(oldCore.get$s());
        //Observed scalars
        if(ObsSales.isSet())
            newCore.set$ObsSales(oldCore.get$ObsSales());

        //ComputedVariables
        if(Sales.isSet())
            newCore.set$Sales(oldCore.get$Sales());
        if(arrivals.isSet())
            newCore.set$arrivals(oldCore.get$arrivals());
        if(denom.isSet())
            newCore.set$denom(oldCore.get$denom());
        if(exped.isSet())
            newCore.set$exped(oldCore.get$exped());
        if(lambda.isSet())
            newCore.set$lambda(oldCore.get$lambda());
        if(sum.isSet())
            newCore.set$sum(oldCore.get$sum());
        if(ut.isSet())
            newCore.set$ut(oldCore.get$ut());

        //Set fixed flags
        if(Sales.isSet())
            newCore.set$fixedFlag$sample85(oldCore.get$fixedFlag$sample85());
        if(arrivals.isSet())
            newCore.set$fixedFlag$sample61(oldCore.get$fixedFlag$sample61());
        if(lambda.isSet())
            newCore.set$fixedFlag$sample53(oldCore.get$fixedFlag$sample53());
        if(ut.isSet())
            newCore.set$fixedFlag$sample25(oldCore.get$fixedFlag$sample25());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input noProducts */
        public final int noProducts;
        /** Field holding the value of model input T */
        public final int T;
        /** Field holding the value of model input s */
        public final int s;
        /** Field holding the value of model input Avail */
        public final int[][] Avail;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param noProducts The value to set noProducts to.
          * @param T The value to set T to.
          * @param s The value to set s to.
          * @param Avail The value to set Avail to.
          */
        public InferValueInputs(int noProducts, int T, int s, int[][] Avail) {
            this.Avail = Avail;
            this.T = T;
            this.noProducts = noProducts;
            this.s = s;
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
        /** Field holding the value of model input s */
        public final int s;
        /** Field holding the value of model input ObsSales */
        public final double[][] ObsSales;
        /** Field holding the value of model input Avail */
        public final int[][] Avail;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param noProducts The value to set noProducts to.
          * @param T The value to set T to.
          * @param s The value to set s to.
          * @param ObsSales The value to set ObsSales to.
          * @param Avail The value to set Avail to.
          */
        public AllInputs(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {
            this.noProducts = noProducts;
            this.T = T;
            this.s = s;
            this.ObsSales = ObsSales;
            this.Avail = Avail;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of Sales after a convention execution step.*/
        public final double[][] Sales;
        /** Field holding the value of arrivals after a convention execution step.*/
        public final int[] arrivals;
        /** Field holding the value of denom after a convention execution step.*/
        public final double denom;
        /** Field holding the value of exped after a convention execution step.*/
        public final double[] exped;
        /** Field holding the value of lambda after a convention execution step.*/
        public final double[] lambda;
        /** Field holding the value of sum after a convention execution step.*/
        public final double sum;
        /** Field holding the value of ut after a convention execution step.*/
        public final double[] ut;

        InferredValueOutputs(Vulcano2012basic system$model) {
            this.Sales = system$model.Sales.getSamples()[0];
            this.arrivals = system$model.arrivals.getSamples()[0];
            this.denom = system$model.denom.getSamples()[0];
            this.exped = system$model.exped.getSamples()[0];
            this.lambda = system$model.lambda.getSamples()[0];
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
        /** Field holding the log probability of computed variable arrivals */
        public final double arrivals;
        /** Field holding the log probability of computed variable denom */
        public final double denom;
        /** Field holding the log probability of computed variable exped */
        public final double exped;
        /** Field holding the log probability of computed variable lambda */
        public final double lambda;
        /** Field holding the log probability of computed variable sum */
        public final double sum;
        /** Field holding the log probability of computed variable ut */
        public final double ut;

        LogProbabilities(Vulcano2012basic system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.Sales = system$model.Sales.getLogProbability();
            this.arrivals = system$model.arrivals.getLogProbability();
            this.denom = system$model.denom.getLogProbability();
            this.exped = system$model.exped.getLogProbability();
            this.lambda = system$model.lambda.getLogProbability();
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
        /** Field holding the probability of computed variable arrivals */
        public final double arrivals;
        /** Field holding the probability of computed variable denom */
        public final double denom;
        /** Field holding the probability of computed variable exped */
        public final double exped;
        /** Field holding the probability of computed variable lambda */
        public final double lambda;
        /** Field holding the probability of computed variable sum */
        public final double sum;
        /** Field holding the probability of computed variable ut */
        public final double ut;

        Probabilities(Vulcano2012basic system$model) {
            this.$modelProbability = system$model.getProbability();
            this.Sales = system$model.Sales.getProbability();
            this.arrivals = system$model.arrivals.getProbability();
            this.denom = system$model.denom.getProbability();
            this.exped = system$model.exped.getProbability();
            this.lambda = system$model.lambda.getProbability();
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
        /** Field holding the MAP or Sample value of arrivals after an infer model call. */
        public final int[][] arrivals;
        /** Field holding the MAP or Sample value of denom after an infer model call. */
        public final double[] denom;
        /** Field holding the MAP or Sample value of exped after an infer model call. */
        public final double[][] exped;
        /** Field holding the MAP or Sample value of lambda after an infer model call. */
        public final double[][] lambda;
        /** Field holding the MAP or Sample value of sum after an infer model call. */
        public final double[] sum;
        /** Field holding the MAP or Sample value of ut after an infer model call. */
        public final double[][] ut;

        InferredModelOutputs(Vulcano2012basic system$model) {
            this.arrivals = system$model.getInferredValue(system$model.$arrivals);
            this.denom = system$model.getInferredValue(system$model.$denom);
            this.exped = system$model.getInferredValue(system$model.$exped);
            this.lambda = system$model.getInferredValue(system$model.$lambda);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
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
        this.Avail.set(inputs.Avail);
        this.T.set(inputs.T);
        this.noProducts.set(inputs.noProducts);
        this.s.set(inputs.s);
        this.$ObsSales.set(inputs.ObsSales);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
