package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model GaussianMixtureTest This is the class that
  * all user interactions with the model should occur through.
  */
public class GaussianMixtureTest extends Model {

    private GaussianMixtureTest$CoreInterface system$c = new GaussianMixtureTest$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedDoubleArrayInternal $mu = new ComputedDoubleArrayInternal(this, "mu", true) {
        @Override
        protected double[] getValue() { return system$c.get$mu(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$mu(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$mu(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample23(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample23())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing mu of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray mu = $mu;

    private final ComputedDoubleArrayInternal $phi = new ComputedDoubleArrayInternal(this, "phi", true) {
        @Override
        protected double[] getValue() { return system$c.get$phi(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$phi(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$phi(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample13(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample13())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing phi of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray phi = $phi;

    private final ComputedDoubleArrayInternal $sigma = new ComputedDoubleArrayInternal(this, "sigma", true) {
        @Override
        protected double[] getValue() { return system$c.get$sigma(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$sigma(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$sigma(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample34(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample34())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing sigma of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray sigma = $sigma;

    private final ComputedDoubleArrayInternal $x = new ComputedDoubleArrayInternal(this, "x", true) {
        @Override
        protected double[] getValue() { return system$c.get$x(); }

        @Override
        protected void setValueInternal(double[] value) {
            system$c.set$x(value);
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$x(); }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample49(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample49())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing x of type double[] from the Sandwood model 
     */
    public final ComputedDoubleArray x = $x;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedDoubleArrayShapeableInternal $xMeasured = new ObservedDoubleArrayShapeableInternal(this, "xMeasured") {
        @Override
        public double[] getValue() {
            synchronized(model) {
                return system$c.get$xMeasured();
            }
        }

        @Override
        public void setValueInternal(double[] value) {
            system$c.set$xMeasured(value);
            system$c.set$length$xMeasured(value.length);
        }

        @Override
        public void setShapeInternal(int shape) {
            system$c.set$length$xMeasured(shape);
        }

        @Override
        public int getShape() {
            return system$c.get$length$xMeasured();
        }
    };

    /**
     * Observed variable representing xMeasured of type double[] from the Sandwood model 
     */
    public final ObservedDoubleArrayShapeable xMeasured = $xMeasured;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$mu, $phi, $sigma, $x};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public GaussianMixtureTest() {
        super();
        //ComputedVariable
        $computedVariables.put("mu", $mu);
        $computedVariables.put("phi", $phi);
        $computedVariables.put("sigma", $sigma);
        $computedVariables.put("x", $x);

        //Observed array fields
        $shapedObservedValues.put("xMeasured", $xMeasured);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param xMeasuredShape An integer array describing the shape of variable xMeasured to use in the model when generating results.
      */

    public GaussianMixtureTest(int xMeasuredShape) {
        this();
        this.$xMeasured.setShape(xMeasuredShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param xMeasured The value to set xMeasured to.
      */

    public GaussianMixtureTest(double[] xMeasured) {
        this();
        this.xMeasured.setValue(xMeasured);
    }
    
    @Override
    protected GaussianMixtureTest$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        GaussianMixtureTest$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new GaussianMixtureTest$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new GaussianMixtureTest$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(GaussianMixtureTest$CoreInterface oldCore, GaussianMixtureTest$CoreInterface newCore) {

        //Observed arrays
        if(xMeasured.isSet()) {
            newCore.set$xMeasured(oldCore.get$xMeasured());
            newCore.set$length$xMeasured(oldCore.get$length$xMeasured());
        }
        else if(xMeasured.shapeSet())
            newCore.set$length$xMeasured(oldCore.get$length$xMeasured());

        //ComputedVariables
        if(mu.isSet())
            newCore.set$mu(oldCore.get$mu());
        if(phi.isSet())
            newCore.set$phi(oldCore.get$phi());
        if(sigma.isSet())
            newCore.set$sigma(oldCore.get$sigma());
        if(x.isSet())
            newCore.set$x(oldCore.get$x());

        //Set fixed flags
        if(mu.isSet())
            newCore.set$fixedFlag$sample23(oldCore.get$fixedFlag$sample23());
        if(phi.isSet())
            newCore.set$fixedFlag$sample13(oldCore.get$fixedFlag$sample13());
        if(sigma.isSet())
            newCore.set$fixedFlag$sample34(oldCore.get$fixedFlag$sample34());
        if(x.isSet())
            newCore.set$fixedFlag$sample49(oldCore.get$fixedFlag$sample49());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the shape of model input xMeasured */
        public final int xMeasuredShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param xMeasuredShape An integer array describing the shape of variable xMeasured to use in the model when generating results.
          */
        public InferValueInputs(int xMeasuredShape) {
            this.xMeasuredShape = xMeasuredShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input xMeasured */
        public final double[] xMeasured;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param xMeasured The value to set xMeasured to.
          */
        public AllInputs(double[] xMeasured) {
            this.xMeasured = xMeasured;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of mu after a convention execution step.*/
        public final double[] mu;
        /** Field holding the value of phi after a convention execution step.*/
        public final double[] phi;
        /** Field holding the value of sigma after a convention execution step.*/
        public final double[] sigma;
        /** Field holding the value of x after a convention execution step.*/
        public final double[] x;

        InferredValueOutputs(GaussianMixtureTest system$model) {
            this.mu = system$model.mu.getSamples()[0];
            this.phi = system$model.phi.getSamples()[0];
            this.sigma = system$model.sigma.getSamples()[0];
            this.x = system$model.x.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable mu */
        public final double mu;
        /** Field holding the log probability of computed variable phi */
        public final double phi;
        /** Field holding the log probability of computed variable sigma */
        public final double sigma;
        /** Field holding the log probability of computed variable x */
        public final double x;

        LogProbabilities(GaussianMixtureTest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.mu = system$model.mu.getLogProbability();
            this.phi = system$model.phi.getLogProbability();
            this.sigma = system$model.sigma.getLogProbability();
            this.x = system$model.x.getLogProbability();
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
        /** Field holding the probability of computed variable mu */
        public final double mu;
        /** Field holding the probability of computed variable phi */
        public final double phi;
        /** Field holding the probability of computed variable sigma */
        public final double sigma;
        /** Field holding the probability of computed variable x */
        public final double x;

        Probabilities(GaussianMixtureTest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.mu = system$model.mu.getProbability();
            this.phi = system$model.phi.getProbability();
            this.sigma = system$model.sigma.getProbability();
            this.x = system$model.x.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of mu after an infer model call. */
        public final double[][] mu;
        /** Field holding the MAP or Sample value of phi after an infer model call. */
        public final double[][] phi;
        /** Field holding the MAP or Sample value of sigma after an infer model call. */
        public final double[][] sigma;

        InferredModelOutputs(GaussianMixtureTest system$model) {
            this.mu = system$model.getInferredValue(system$model.$mu);
            this.phi = system$model.getInferredValue(system$model.$phi);
            this.sigma = system$model.getInferredValue(system$model.$sigma);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.$xMeasured.setShape(inputs.xMeasuredShape);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
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
        this.$xMeasured.setValue(inputs.xMeasured);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
