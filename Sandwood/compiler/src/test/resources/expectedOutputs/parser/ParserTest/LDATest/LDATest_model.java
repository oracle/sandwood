package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.variables.*;
import org.sandwood.runtime.internal.model.variables.*;
import org.sandwood.common.exceptions.SandwoodException;

import java.util.Map;
import java.util.HashMap;

/**
  * Class representing the Sandwood model LDATest This is the class that
  * all user interactions with the model should occur through.
  */
public class LDATest extends Model {

    private LDATest$CoreInterface system$c = new LDATest$SingleThreadCPU(ExecutionTarget.singleThread);

    private final ComputedObjectArrayInternal<double[]> $phi = new ComputedObjectArrayInternal<double[]>(this, "phi", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$phi(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$phi(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$phi(); }

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
     * Computed variable representing phi of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> phi = $phi;

    private final ComputedObjectArrayInternal<double[]> $theta = new ComputedObjectArrayInternal<double[]>(this, "theta", true, org.sandwood.runtime.internal.model.util.BaseType.DOUBLE, 2) {
        @Override
        public double[][] getValue() { return system$c.get$theta(); }

        @Override
        protected void setValueInternal(double[][] value) {
            system$c.set$theta(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$theta(); }

        @Override
        public double[][][] constructArray(int iterations) {
            return new double[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample36(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample36())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing theta of type double[][] from the Sandwood model 
     */
    public final ComputedObjectArray<double[]> theta = $theta;

    private final ComputedObjectArrayInternal<int[]> $w = new ComputedObjectArrayInternal<int[]>(this, "w", true, org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() { return system$c.get$w(); }

        @Override
        protected void setValueInternal(int[][] value) {
            system$c.set$w(value);
            valueSet = true;
            setFixed(true);
        }

        @Override
        public double getCurrentLogProbability() { return system$c.get$logProbability$w(); }

        @Override
        public int[][][] constructArray(int iterations) {
            return new int[iterations][][];
        }

        @Override
        public void setFixed(boolean fixed) {
            synchronized(model) {
                system$c.set$fixedFlag$sample64(fixed);
            }
        }

        @Override
        public Immutability isFixed() {
            if(system$c.get$fixedFlag$sample64())
                return Immutability.FIXED;
            else
                return Immutability.FREE;
        }
    };

    /**
     * Computed variable representing w of type int[][] from the Sandwood model 
     */
    public final ComputedObjectArray<int[]> w = $w;

	private Map<String, ComputedVariableInternal> $computedVariables = new HashMap<>();

    private final ObservedIntegerInternal $noTopics = new ObservedIntegerInternal(this, "noTopics") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$noTopics();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$noTopics(value); }
    };

    /**
     * Observed variable representing noTopics of type int from the Sandwood model 
     */
    public final ObservedInteger noTopics = $noTopics;

    private final ObservedIntegerInternal $vocabSize = new ObservedIntegerInternal(this, "vocabSize") {
        @Override
        public int getValue() {
            synchronized(model) {
                return system$c.get$vocabSize();
            }
        }

        @Override
        protected void setValueInternal(int value) { system$c.set$vocabSize(value); }
    };

    /**
     * Observed variable representing vocabSize of type int from the Sandwood model 
     */
    public final ObservedInteger vocabSize = $vocabSize;

    private Map<String, ObservedVariableInternal> $modelInputs = new HashMap<>();

    private final ObservedObjectArrayShapeableInternal<int[], int[]> $documents = new ObservedObjectArrayShapeableInternal<int[], int[]>(this, "documents", org.sandwood.runtime.internal.model.util.BaseType.INT, 2) {
        @Override
        public int[][] getValue() {
            synchronized(model) {
                return system$c.get$documents();
            }
        }

        @Override
        public void setValueInternal(int[][] value) {
            system$c.set$documents(value);
            system$c.set$length$documents(getDims(value));
        }

        @Override
        public void setShapeInternal(int[] shape) {
            system$c.set$length$documents(shape);
        }

        @Override
        public int[] getShape() {
            return system$c.get$length$documents();
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
     * Observed variable representing documents of type int[][] from the Sandwood model 
     */
    public final ObservedObjectArrayShapeable<int[], int[]> documents = $documents;

    private Map<String, ObservedVariableInternal> $regularObservedValues = new HashMap<>();
    private Map<String, ObservedVariableShapeableInternal<?>> $shapedObservedValues = new HashMap<>();
    private HasProbabilityInternal[] $probabilityVariables = {$phi, $theta, $w};

    //Constructors
    /**
     * A constructor for a model where no variable values are set.
     */
    public LDATest() {
        super();
        //ComputedVariable
        $computedVariables.put("phi", $phi);
        $computedVariables.put("theta", $theta);
        $computedVariables.put("w", $w);

        //ModelInputs
        $modelInputs.put("noTopics", $noTopics);
        $modelInputs.put("vocabSize", $vocabSize);

        //Observed array fields
        $shapedObservedValues.put("documents", $documents);
        init(system$c, $modelInputs, $regularObservedValues, $shapedObservedValues, $computedVariables, $probabilityVariables);
    }
    /**
      * A constructor to set all the required values in the model to infer values. These
      * will be values in an untrained model so this will only generate values from the
      * default distributions described in the model.
      * @param noTopics The value to set noTopics to.
      * @param vocabSize The value to set vocabSize to.
      * @param documentsShape An integer array describing the shape of variable documents to use in the model when generating results.
      */

    public LDATest(int noTopics, int vocabSize, int[] documentsShape) {
        this();
        this.$noTopics.setValue(noTopics);
        this.$vocabSize.setValue(vocabSize);
        this.$documents.setShape(documentsShape);
    }
    /**
      * A constructor to set all the required values in the model to infer the model
      * parameters, or to generate probabilities for the model.
      * @param noTopics The value to set noTopics to.
      * @param vocabSize The value to set vocabSize to.
      * @param documents The value to set documents to.
      */

    public LDATest(int noTopics, int vocabSize, int[][] documents) {
        this();
        this.noTopics.setValue(noTopics);
        this.vocabSize.setValue(vocabSize);
        this.documents.setValue(documents);
    }
    
    @Override
    protected LDATest$CoreInterface setExecutionTargetInternal(ExecutionTarget target) {
        LDATest$CoreInterface newCore;
        switch(target.executionType) {
            case SingleThreadCPU:
                newCore = new LDATest$SingleThreadCPU(target);
                break;
            case MultiThreadCPU:
                newCore = new LDATest$MultiThreadCPU(target);
                break;
            default:
                throw new SandwoodException("Unsupported execution type: " + target);
        }
        transferData(system$c, newCore);
        system$c = newCore;
        return newCore;
    }
    private void transferData(LDATest$CoreInterface oldCore, LDATest$CoreInterface newCore) {
        //Model inputs
        if(noTopics.isSet())
            newCore.set$noTopics(oldCore.get$noTopics());
        if(vocabSize.isSet())
            newCore.set$vocabSize(oldCore.get$vocabSize());

        //Observed arrays
        if(documents.isSet()) {
            newCore.set$documents(oldCore.get$documents());
            newCore.set$length$documents(oldCore.get$length$documents());
        }
        else if(documents.shapeSet())
            newCore.set$length$documents(oldCore.get$length$documents());

        //ComputedVariables
        if(phi.isSet())
            newCore.set$phi(oldCore.get$phi());
        if(theta.isSet())
            newCore.set$theta(oldCore.get$theta());
        if(w.isSet())
            newCore.set$w(oldCore.get$w());

        //Set fixed flags
        if(phi.isSet())
            newCore.set$fixedFlag$sample26(oldCore.get$fixedFlag$sample26());
        if(theta.isSet())
            newCore.set$fixedFlag$sample36(oldCore.get$fixedFlag$sample36());
        if(w.isSet())
            newCore.set$fixedFlag$sample64(oldCore.get$fixedFlag$sample64());
    }

    /**
     * A class to hold all the values required to perform a value inference on the model.
     */
    public static class InferValueInputs {
        /** Field holding the value of model input noTopics */
        public final int noTopics;
        /** Field holding the value of model input vocabSize */
        public final int vocabSize;
        /** Field holding the shape of model input documents */
        public final int[] documentsShape;

        /**
          * A constructor taking all the values required to set up the model to infer variables.
          * @param noTopics The value to set noTopics to.
          * @param vocabSize The value to set vocabSize to.
          * @param documentsShape An integer array describing the shape of variable documents to use in the model when generating results.
          */
        public InferValueInputs(int noTopics, int vocabSize, int[] documentsShape) {
            this.noTopics = noTopics;
            this.vocabSize = vocabSize;
            this.documentsShape = documentsShape;
        }
    }

    /**
     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities
     * and probability calculations.
     */
    public static class AllInputs {
        /** Field holding the value of model input noTopics */
        public final int noTopics;
        /** Field holding the value of model input vocabSize */
        public final int vocabSize;
        /** Field holding the value of model input documents */
        public final int[][] documents;

        /**
          * A constructor to take all the required values by the model to infer the model
          * parameters, or to generate probabilities for the model.
          * @param noTopics The value to set noTopics to.
          * @param vocabSize The value to set vocabSize to.
          * @param documents The value to set documents to.
          */
        public AllInputs(int noTopics, int vocabSize, int[][] documents) {
            this.noTopics = noTopics;
            this.vocabSize = vocabSize;
            this.documents = documents;
        }
    }

    /**
     * A class to hold all the outputs from the model after an infer values step.
     */
    public static class InferredValueOutputs {
        /** Field holding the value of phi after a convention execution step.*/
        public final double[][] phi;
        /** Field holding the value of theta after a convention execution step.*/
        public final double[][] theta;
        /** Field holding the value of w after a convention execution step.*/
        public final int[][] w;

        InferredValueOutputs(LDATest system$model) {
            this.phi = system$model.phi.getSamples()[0];
            this.theta = system$model.theta.getSamples()[0];
            this.w = system$model.w.getSamples()[0];
        }
    }

    /**
     * A class to hold all the probabilities from the model after a generate probabilities step.
     */
    public static class LogProbabilities {
        private final double $logModelProbability;
        /** Field holding the log probability of computed variable phi */
        public final double phi;
        /** Field holding the log probability of computed variable theta */
        public final double theta;
        /** Field holding the log probability of computed variable w */
        public final double w;

        LogProbabilities(LDATest system$model) {
            this.$logModelProbability = system$model.getLogProbability();
            this.phi = system$model.phi.getLogProbability();
            this.theta = system$model.theta.getLogProbability();
            this.w = system$model.w.getLogProbability();
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
        /** Field holding the probability of computed variable phi */
        public final double phi;
        /** Field holding the probability of computed variable theta */
        public final double theta;
        /** Field holding the probability of computed variable w */
        public final double w;

        Probabilities(LDATest system$model) {
            this.$modelProbability = system$model.getProbability();
            this.phi = system$model.phi.getProbability();
            this.theta = system$model.theta.getProbability();
            this.w = system$model.w.getProbability();
        }

        /** Method to return probability of the whole model 
         *  @return The probability of the whole model. */
        public double getModelProbability() { return $modelProbability; }
    }

    /**
     * A class to hold all the outputs from the model after an infer model call.
     */
    public static class InferredModelOutputs {
        /** Field holding the MAP or Sample value of phi after an infer model call. */
        public final double[][][] phi;
        /** Field holding the MAP or Sample value of theta after an infer model call. */
        public final double[][][] theta;

        InferredModelOutputs(LDATest system$model) {
            this.phi = system$model.getInferredValue(system$model.$phi);
            this.theta = system$model.getInferredValue(system$model.$theta);
        }
    }

    /**
     * Perform a single pass generating values from the model.
     * @param inputs An object containing the parameters required to run inference on the model.
     * @return An object containing the values computed by the inference step.
     */
    public InferredValueOutputs execute(InferValueInputs inputs) {
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setShape(inputs.documentsShape);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
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
        this.noTopics.setValue(inputs.noTopics);
        this.vocabSize.setValue(inputs.vocabSize);
        this.$documents.setValue(inputs.documents);
        inferProbabilities(variance, initialIterations, maxIterations);
        return new LogProbabilities(this);
    }
}
//END OF CODE
