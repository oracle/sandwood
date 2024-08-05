package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model DiscreteChoiceRandCoeff This is the class that
  * all user interactions with the model should occur through.
  */
public class DiscreteChoiceRandCoeff extends Model {

    private DiscreteChoiceRandCoeff$CoreInterface system$c = new DiscreteChoiceRandCoeff$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleInternal $b = new ComputedDoubleInternal(this, "b", true) {
        @Override
        protected double getValue() { return system$c.get$b(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$b(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$b(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample29(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample29())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing b of type double from the Sandwood model 
     */
    public final ComputedDouble b = $b;

    private final ComputedDoubleArrayInternal $beta = new ComputedDoubleArrayInternal(this, "beta", true) {
        @Override
        protected double[] getValue() { return system$c.get$beta(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$beta(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$beta(); }

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
     * Computed variable representing beta of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray beta = $beta;

    private final ComputedIntegerArrayInternal $choices = new ComputedIntegerArrayInternal(this, "choices", true) {
        @Override
        protected int[] getValue() { return system$c.get$choices(); }

        @Override
        protected void setValueInternal(int[] value) {
            system$c.set$choices(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$choices(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample76(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample76())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing choices of type int[] from the Sandwood model 
     */
    public final ComputedIntegerArray choices = $choices;

    private final ComputedObjectArrayInternal<double[]> $prob = new ComputedObjectArrayInternal<double[]>(this, "prob", false, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        protected double[][] getValue() { return system$c.get$prob(); }

        @Override
        protected void setValueInternal(double[][] value) {}

        @Override
        protected void testSettable() {
            throw new SandwoodException("Set is not available for variable prob because its value depends on variables \"beta\", \"exped\", and \"ut\".");
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$prob(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample22(fixed);
                system$c.set$fixedFlag$sample41(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            boolean fixedFlag$sample22 = system$c.get$fixedFlag$sample22();
            boolean fixedFlag$sample41 = system$c.get$fixedFlag$sample41();
            if(fixedFlag$sample22 && fixedFlag$sample41)
                return Immutability.FIXED;
            else if(fixedFlag$sample22 || fixedFlag$sample41)
                return Immutability.PARTIALLY_FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing prob of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> prob = $prob;

    private final ComputedDoubleInternal $sigma = new ComputedDoubleInternal(this, "sigma", true) {
        @Override
        protected double getValue() { return system$c.get$sigma(); }

        @Override
        protected void setValueInternal(double value) {
            system$c.set$sigma(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$sigma(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample35(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample35())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing sigma of type double from the Sandwood model 
     */
    public final ComputedDouble sigma = $sigma;

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
                system$c.set$fixedFlag$sample22(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample22())
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

    private final ObservedObjectArrayInternal<int[]> $Prices = new ObservedObjectArrayInternal<int[]>(this, "Prices", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] get() {
            synchronized(model) {
                return system$c.get$Prices();
            }
        }

        @Override
        protected void setValue(int[][] value) { system$c.set$Prices(value); }
    };

    /**
     * Observed variable representing Prices of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArray<int[]> Prices = $Prices;

    private final ObservedIntegerInternal $noObs = new ObservedIntegerInternal(this, "noObs") {
        @Override
        public int get() {
            synchronized(model) {
                return system$c.get$noObs();
            }
        }

        @Override
        protected void setValue(int value) { system$c.set$noObs(value); }
    };

    /**
     * Observed variable representing noObs of type int from the Sandwood model 
     */
    public final ObservedInteger noObs = $noObs;

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

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedIntegerArrayInternal $ObsChoices = new ObservedIntegerArrayInternal(this, "ObsChoices") {
        @Override
        public int[] get() {
            synchronized(model) {
                return system$c.get$ObsChoices();
            }
        }

        @Override
        protected void setValue(int[] value) { system$c.set$ObsChoices(value); }
    };

    /**
     * Observed variable representing ObsChoices of type int[] from the Sandwood model 
     */
    public final ObservedIntegerArray ObsChoices = $ObsChoices;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$b, $beta, $choices, $prob, $sigma, $ut};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public DiscreteChoiceRandCoeff() {
        super();
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
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
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
        this.$Prices.set(Prices);
        this.$noObs.set(noObs);
        this.$noProducts.set(noProducts);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param noProducts The value to set noProducts to.
      * @param noObs The value to set noObs to.
      * @param ObsChoices The value to set ObsChoices to.
      * @param Prices The value to set Prices to.
      */

    public DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {
        this();
        this.noProducts.set(noProducts);
        this.noObs.set(noObs);
        this.ObsChoices.set(ObsChoices);
        this.Prices.set(Prices);
    }
    
    @Override
    protected DiscreteChoiceRandCoeff$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        DiscreteChoiceRandCoeff$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new DiscreteChoiceRandCoeff$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new DiscreteChoiceRandCoeff$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(DiscreteChoiceRandCoeff$CoreInterface oldCore, DiscreteChoiceRandCoeff$CoreInterface newCore) {
        //Model inputs
        if(Prices.isSet())
            newCore.set$Prices(oldCore.get$Prices());
        if(noObs.isSet())
            newCore.set$noObs(oldCore.get$noObs());
        if(noProducts.isSet())
            newCore.set$noProducts(oldCore.get$noProducts());
        //Observed scalars
        if(ObsChoices.isSet())
            newCore.set$ObsChoices(oldCore.get$ObsChoices());

        //ComputedVariables
        if(b.isSet())
            newCore.set$b(oldCore.get$b());
        if(beta.isSet())
            newCore.set$beta(oldCore.get$beta());
        if(choices.isSet())
            newCore.set$choices(oldCore.get$choices());
        if(prob.isSet())
            newCore.set$prob(oldCore.get$prob());
        if(sigma.isSet())
            newCore.set$sigma(oldCore.get$sigma());
        if(ut.isSet())
            newCore.set$ut(oldCore.get$ut());

        //Set fixed flags
        if(b.isSet())
            newCore.set$fixedFlag$sample29(oldCore.get$fixedFlag$sample29());
        if(beta.isSet())
            newCore.set$fixedFlag$sample41(oldCore.get$fixedFlag$sample41());
        if(choices.isSet())
            newCore.set$fixedFlag$sample76(oldCore.get$fixedFlag$sample76());
        if(sigma.isSet())
            newCore.set$fixedFlag$sample35(oldCore.get$fixedFlag$sample35());
        if(ut.isSet())
            newCore.set$fixedFlag$sample22(oldCore.get$fixedFlag$sample22());
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
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
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
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
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

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of b after a convention execution step.*/
        public final double b;
        /** Field holding the value of beta after a convention execution step.*/
        public final double[] beta;
        /** Field holding the value of choices after a convention execution step.*/
        public final int[] choices;
        /** Field holding the value of prob after a convention execution step.*/
        public final double[][] prob;
        /** Field holding the value of sigma after a convention execution step.*/
        public final double sigma;
        /** Field holding the value of ut after a convention execution step.*/
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
     * A class to hold all the probabilities from the model after a generate probabilities step.
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

        /** Method to return log probability of the whole model 
         *  @return The log probability of the whole model. */
        public double getModelProbability() { return $logModelProbability; }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
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

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
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
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
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
        this.Prices.set(inputs.Prices);
        this.noObs.set(inputs.noObs);
        this.noProducts.set(inputs.noProducts);
        this.$ObsChoices.set(inputs.ObsChoices);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
