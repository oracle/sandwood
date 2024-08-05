/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.compilation.CompilationContext.FieldDesc;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.Traces;

public class OutputSandwoodClassWrapper extends OutputSandwoodClass {
    private static class RandomVariableDesc implements Comparable<RandomVariableDesc> {
        public final VariableName name;
        public final VariableName uniqueName;
        public final String comment;

        public RandomVariableDesc(VariableName name, VariableName uniqueName, String comment) {
            this.name = name;
            this.uniqueName = uniqueName;
            this.comment = comment;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((comment == null) ? 0 : comment.hashCode());
            result = prime * result + name.hashCode();
            result = prime * result + uniqueName.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if((obj == null) || (getClass() != obj.getClass()))
                return false;
            RandomVariableDesc other = (RandomVariableDesc) obj;
            if(comment == null) {
                return other.comment == null;
            } else if(!comment.equals(other.comment))
                return false;
            else if(!name.equals(other.name))
                return false;
            else
                return uniqueName.equals(other.uniqueName);
        }

        @Override
        public int compareTo(RandomVariableDesc r) {
            return name.compareTo(r.name);
        }
    }

    private final ModelClassName className;
    private final PackageName packageName;
    private final String comment;
    private final VariableName[] constructorArgs;
    private final List<VariableName> modelInputs; // Inputs in the model signature that are not observed
    private final List<VariableName> observedOnlyInputs;
    private final List<VariableName> observedShapeableInputs;
    private final List<VariableName> computedVariables;
    private final RandomVariableDesc[] randomVariables;
    private final Map<VariableName, FieldDesc<?>> fieldDescs = new HashMap<>();
    private final Traces traces;
    private final ExecutionType[] targets;

    // Classnames
    private final String executeInputs;
    private final String executeOutputs;
    private final String inferValuesOutputs;
    private final String probabilityOutputs;
    private final String logProbabilityOutputs;
    private final String allInputs;
    private final VariableName coreName = VariableNames.internalSystemName("c");
    private final VariableName modelParam = VariableNames.internalSystemName("model");

    public OutputSandwoodClassWrapper(ModelClassName className, PackageName packageName, VariableName[] constructorArgs,
            Map<VariableName, FieldDesc<?>> classFields, Traces traces, String comment, ExecutionType[] targets) {
        this.className = className;
        this.packageName = packageName;
        this.constructorArgs = constructorArgs;
        fieldDescs.putAll(classFields);
        this.comment = comment;
        this.traces = traces;
        this.targets = targets;

        // Generate list for random variables
        Set<RandomVariableDesc> randomVariables = new HashSet<>();
        for(RandomVariable<?, ?> rv:traces.getAllRandomVariables()) {
            if(!rv.isPrivate()) {
                VariableName name = rv.getVarDesc().name;
                randomVariables.add(new RandomVariableDesc(name, rv.getUniqueVarDesc().name, rv.getComment()));
            }
        }
        this.randomVariables = randomVariables.toArray(new RandomVariableDesc[randomVariables.size()]);
        Arrays.sort(this.randomVariables);

        // Generate a list of computed variable
        computedVariables = new ArrayList<>();
        for(Variable<?> v:traces.computedVariables())
            computedVariables.add(v.getUniqueVarDesc().name);
        Collections.sort(computedVariables);

        // Non observed model inputs
        modelInputs = new ArrayList<>();
        for(Variable<?> v:traces.modelInputs())
            modelInputs.add(v.getUniqueVarDesc().name);
        Collections.sort(modelInputs);

        // Observed model inputs whose shape is not used.
        observedOnlyInputs = new ArrayList<>();
        for(Variable<?> v:traces.observedOnlyInputs())
            observedOnlyInputs.add(v.getUniqueVarDesc().name);
        Collections.sort(observedOnlyInputs);

        // Observed model inputs whose shape is used.
        observedShapeableInputs = new ArrayList<>();
        for(Variable<?> v:traces.observedShapeableValues())
            observedShapeableInputs.add(v.getUniqueVarDesc().name);
        Collections.sort(observedShapeableInputs);

        executeInputs = "InferValueInputs";
        executeOutputs = "InferredValueOutputs";
        inferValuesOutputs = "InferredModelOutputs";
        probabilityOutputs = "Probabilities";
        logProbabilityOutputs = "LogProbabilities";
        allInputs = "AllInputs";
    }

    @Override
    public String getName() {
        return className.getName();
    }

    @Override
    public PackageName getPackage() {
        return packageName;
    }

    @Override
    public void toJava(StringBuilder sb) {

        if(packageName != null)
            sb.append("package " + packageName + ";\n\n");

        sb.append("import org.sandwood.runtime.model.Model;\n");
        sb.append("import org.sandwood.runtime.model.ExecutionTarget;\n");
        sb.append("import org.sandwood.runtime.model.variables.*;\n");
        sb.append("import org.sandwood.runtime.internal.model.variables.*;\n");
        sb.append("import org.sandwood.common.exceptions.SandwoodException;\n");
        sb.append("\n");
        sb.append("import java.util.Map;\n");
        sb.append("import java.util.HashMap;\n");
        sb.append("\n");

        if(comment != null)
            sb.append(comment + "\n");
        else
            sb.append("/**\n" + "  * Class representing the Sandwood model " + className + " This is the class that\n"
                    + "  * all user interactions with the model should occur through.\n" + "  */\n");

        ClassName interfaceName = className.interfaceName();
        sb.append("public class " + className + " extends Model {\n\n");

        sb.append("    private " + interfaceName + " " + coreName + " = new "
                + className.backendName(ExecutionType.SingleThreadCPU) + "(ExecutionTarget.singleThread);\n\n");

        // Construct computed fields
        {
            for(VariableName name:computedVariables) {
                VariableName uniqueName = traces.getVariable(name).getUniqueVarDesc().name;
                FieldDesc<?> fieldDesc = fieldDescs.get(uniqueName);
                constructComputedField(name, fieldDesc, sb);
            }

            // Create a map for all the computed fields. The values will be added to the map
            // in the constructor.
            sb.append("\tprivate Map<String, ComputedVariableInternal> "
                    + VariableNames.internalName("computedVariables") + " = new HashMap<>();\n\n");
        }

        // Add all observed fields.
        {
            // Add the input fields
            for(VariableName name:modelInputs) {
                VariableDescription<?> uniqueName = traces.getVariable(name).getUniqueVarDesc();
                FieldDesc<?> fieldDesc = fieldDescs.get(uniqueName.name);
                constructObservedField(name, fieldDesc, sb);
            }

            // Add all the inputs to an array so that they can be iterated over.
            sb.append("    private Map<String, ObservedVariableInternal> " + VariableNames.internalName("modelInputs")
                    + " = new HashMap<>();\n\n");

            // Add the observed fields
            for(VariableName name:observedOnlyInputs) {
                VariableDescription<?> uniqueName = traces.getVariable(name).getUniqueVarDesc();
                FieldDesc<?> fieldDesc = fieldDescs.get(uniqueName.name);
                constructObservedField(name, fieldDesc, sb);
            }

            for(VariableName name:observedShapeableInputs) {
                VariableDescription<?> uniqueName = traces.getVariable(name).getUniqueVarDesc();
                FieldDesc<?> fieldDesc = fieldDescs.get(uniqueName.name);
                constructShapedObservedField(name, fieldDesc, sb);
            }

            // Add all the observed variables to a map so that they can be iterated
            // over. They are split on weather they implement shaped
            // No Shaping
            sb.append("    private Map<String, ObservedVariableInternal> "
                    + VariableNames.internalName("regularObservedValues") + " = new HashMap<>();\n");

            // Shaped
            sb.append("    private Map<String, ObservedVariableShapeableInternal<?>> "
                    + VariableNames.internalName("shapedObservedValues") + " = new HashMap<>();\n");
        }

        // Add random variables
        {
            for(RandomVariableDesc desc:randomVariables)
                constructRandomVariableField(desc, fieldDescs, sb);

            // Add all the computed fields to an array so that they can be iterated over.
            sb.append("    private HasProbabilityInternal[] " + VariableNames.internalName("probabilityVariables")
                    + " = {");
            boolean started = false;
            for(VariableName name:computedVariables) {
                Variable<?> v = traces.getVariable(name);
                if(fieldDescs.containsKey(VariableNames.logProbabilityName(v.getUniqueVarDesc()).name)) {
                    if(started)
                        sb.append(", ");
                    else
                        started = true;
                    sb.append(VariableNames.internalName(name));
                }
            }

            for(RandomVariableDesc desc:randomVariables) {
                if(started)
                    sb.append(", ");
                else
                    started = true;
                sb.append(VariableNames.internalName(desc.name));
            }

            sb.append("};\n\n");
        }

        // Construct the constructors
        constructConstructors(sb);

        // Construct Configuration Methods
        constructConfigurationMethods(sb);

        // Construct Helper Classes
        constructHelperClasses(sb);

        // Construct Helper Methods
        constructHelperMethods(sb);

        sb.append("}\n");
        sb.append("//END OF CODE\n");
    }

    private void constructConfigurationMethods(StringBuilder sb) {
        constructSetExecutionTarget(sb);
    }

    private void constructSetExecutionTarget(StringBuilder sb) {
        ClassName interfaceName = className.interfaceName();
        sb.append("    \n    @Override\n");
        sb.append("    protected " + interfaceName + " setExecutionTargetInternal(ExecutionTarget target) {\n");
        sb.append("        " + interfaceName + " newCore;\n");
        sb.append("        switch(target.executionType) {\n");
        for(ExecutionType t:targets) {
            sb.append("            case " + t + ":\n");
            sb.append("                newCore = new " + className.backendName(t) + "(target);\n");
            sb.append("                break;\n");
        }
        sb.append("            default:\n");
        sb.append("                throw new SandwoodException(\"Unsupported execution type: \" + target);\n");
        sb.append("        }\n");
        sb.append("        transferData(" + coreName + ", newCore);\n");
        sb.append("        " + coreName + " = newCore;\n");
        sb.append("        return newCore;\n");
        sb.append("    }\n");

        // Now construct the transferData method.
        sb.append("    private void transferData(" + interfaceName + " oldCore, " + interfaceName + " newCore) {\n");
        if(!modelInputs.isEmpty()) {
            sb.append("        //Model inputs\n");
            for(VariableName name:modelInputs) {
                sb.append("        if(" + name + ".isSet())\n");
                sb.append("            newCore" + setMethod(name) + "(oldCore" + getMethod(name) + "());\n");
            }
        }
        if(!observedOnlyInputs.isEmpty()) {
            sb.append("        //Observed scalars\n");
            for(VariableName name:observedOnlyInputs) {
                sb.append("        if(" + name + ".isSet())\n");
                sb.append("            newCore" + setMethod(name) + "(oldCore" + getMethod(name) + "());\n");
            }
        }

        if(!observedShapeableInputs.isEmpty()) {
            sb.append("\n        //Observed arrays\n");
            for(VariableName name:observedShapeableInputs) {
                VariableName lengthName = VariableNames.lengthName(name);
                VariableName lengthUniqueName = traces.getVariable(lengthName).getUniqueVarDesc().name;
                sb.append("        if(" + name + ".isSet()) {\n");
                sb.append("            newCore" + setMethod(name) + "(oldCore" + getMethod(name) + "());\n");
                sb.append("            newCore" + setMethod(lengthUniqueName) + "(oldCore" + getMethod(lengthUniqueName)
                        + "());\n");
                sb.append("        }\n");
                sb.append("        else if(" + name + ".shapeSet())\n");
                sb.append("            newCore" + setMethod(lengthUniqueName) + "(oldCore" + getMethod(lengthUniqueName)
                        + "());\n");
            }
        }

        sb.append("\n        //ComputedVariables\n");
        for(VariableName name:computedVariables) {
            if(fieldDescs.get(name).setter) {
                sb.append("        if(" + name + ".isSet())\n");
                sb.append("            newCore" + setMethod(name) + "(oldCore" + getMethod(name) + "());\n");
            }
        }

        sb.append("\n        //Set fixed flags\n");
        for(VariableName name:computedVariables) {
            FieldDesc<?> f = fieldDescs.get(name);
            if(f.sample) {
                List<VariableDescription<BooleanVariable>> flags = getFlags(traces, name);
                sb.append("        if(" + name + ".isSet())" + ((flags.size() > 1) ? "{" : "") + "\n");
                for(VariableDescription<BooleanVariable> flag:flags)
                    sb.append("            newCore" + setMethod(flag.name) + "(oldCore" + getMethod(flag.name)
                            + "());\n");
                if(flags.size() > 1)
                    sb.append("        }\n");
            }
        }

        sb.append("    }\n");
    }

    private void constructHelperMethods(StringBuilder sb) {
        constructInfer1Value(sb);
        constructInferValues(sb);
        constructGenerateProbabilities(sb);
        constructGenerateProbabilitiesVar(sb);
        constructGenerateProbabilitiesVarAndMaxIterations(sb);
        constructGenerateLogProbabilities(sb);
        constructGenerateLogProbabilitiesVar(sb);
        constructGenerateLogProbabilitiesVarAndMaxIterations(sb);
    }

    private void constructInferValues(StringBuilder sb) {
        sb.append("\n    /**\n" + "     * Infer the values of the different elements of the model.\n"
                + "     * @param iterations The number of iterations to perform when inferring the values.\n"
                + "     * @param inputs An object containing the parameters required to generate the model parameters.\n"
                + "     * @return An object containing the computed values for the model.\n" + "     */\n");
        sb.append("    public " + inferValuesOutputs + " inferValues(int iterations, " + allInputs + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferValues(iterations);\n");
        sb.append("        return new " + inferValuesOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateProbabilities(StringBuilder sb) {
        sb.append("\n    /**\n" + "     * Generate the probabilities of the different elements of the model.\n"
                + "     * @param iterations How many iterations should be used to generate these values?\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + probabilityOutputs + " inferProbabilities(int iterations, " + allInputs
                + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(iterations);\n");
        sb.append("        return new " + probabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateLogProbabilities(StringBuilder sb) {
        sb.append("\n    /**\n" + "     * Generate the log probabilities of the different elements of the model.\n"
                + "     * @param iterations How many iterations should be used to generate these values?\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + logProbabilityOutputs + " inferLogProbabilities(int iterations, " + allInputs
                + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(iterations);\n");
        sb.append("        return new " + logProbabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateProbabilitiesVarAndMaxIterations(StringBuilder sb) {
        sb.append("\n    /**\n"
                + "     * Calculate the probability of each variable and the overall model. This method\n"
                + "     * will iterate until the variance of the overall model drops below the value provide \n"
                + "     * for variance, or the maximum number of iterations is reached.\n"
                + "     * @param variance The maximum variance in the models overall probability.\n"
                + "     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in\n"
                + "     * premature termination as the model may not have enough runs to estimate the variance accurately.\n"
                + "     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not\n"
                + "     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + probabilityOutputs
                + " inferProbabilities(double variance, int initialIterations, int maxIterations, " + allInputs
                + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(variance, initialIterations, maxIterations);\n");
        sb.append("        return new " + probabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateLogProbabilitiesVarAndMaxIterations(StringBuilder sb) {
        sb.append("\n    /**\n"
                + "     * Calculate the log probability of each variable and the overall model. This method\n"
                + "     * will iterate until the variance of the overall model drops below the value provide \n"
                + "     * for variance, or the maximum number of iterations is reached.\n"
                + "     * @param variance The maximum variance in the models overall probability.\n"
                + "     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in\n"
                + "     * premature termination as the model may not have enough runs to estimate the variance accurately.\n"
                + "     * @param maxIterations The maximum number of iterations a that can be used to calculate the probabilities. If the model has not\n"
                + "     * converged by this point the calculation will terminate anyway, and the result generated so far will be returned.\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + logProbabilityOutputs
                + " inferLogProbabilities(double variance, int initialIterations, int maxIterations, " + allInputs
                + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(variance, initialIterations, maxIterations);\n");
        sb.append("        return new " + logProbabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateProbabilitiesVar(StringBuilder sb) {
        sb.append("\n    /**\n"
                + "     * Calculate the probability of each variable and the overall model. This method\n"
                + "     * will iterate until the variance of the overall model drops below the value provide \n"
                + "     * for variance, or the maximum number of iterations is reached.\n"
                + "     * @param variance The maximum variance in the models overall probability.\n"
                + "     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in\n"
                + "     * premature termination as the model may not have enough runs to estimate the variance accurately.\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + probabilityOutputs + " inferProbabilities(double variance, int initialIterations, "
                + allInputs + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(variance, initialIterations);\n");
        sb.append("        return new " + probabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void constructGenerateLogProbabilitiesVar(StringBuilder sb) {
        sb.append("\n    /**\n"
                + "     * Calculate the log probability of each variable and the overall model. This method\n"
                + "     * will iterate until the variance of the overall model drops below the value provide \n"
                + "     * for variance, or the maximum number of iterations is reached.\n"
                + "     * @param variance The maximum variance in the models overall probability.\n"
                + "     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in\n"
                + "     * premature termination as the model may not have enough runs to estimate the variance accurately.\n"
                + "     * @param inputs An object containing the parameters required to generate the probabilities of the model.\n"
                + "     * @return An object containing the computed probabilities for the model.\n" + "     */\n");
        sb.append("    public " + logProbabilityOutputs
                + " inferLogProbabilities(double variance, int initialIterations, " + allInputs + " inputs) {\n");
        setAllInputs(sb, "inputs");
        sb.append("        inferProbabilities(variance, initialIterations);\n");
        sb.append("        return new " + logProbabilityOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void setAllInputs(StringBuilder sb, String objectName) {
        for(VariableName arg:modelInputs) {
            Variable<?> v = traces.getVariable(arg);
            if(fieldDescs.containsKey(v.getUniqueVarDesc().name))
                sb.append("        this." + arg + ".set(" + objectName + "." + arg + ");\n");
        }
        for(VariableName arg:observedShapeableInputs)
            sb.append("        this." + VariableNames.internalName(arg) + ".set(" + objectName + "." + arg + ");\n");
        for(VariableName arg:observedOnlyInputs)
            sb.append("        this." + VariableNames.internalName(arg) + ".set(" + objectName + "." + arg + ");\n");
    }

    private void constructInfer1Value(StringBuilder sb) {
        sb.append("\n    /**\n" + "     * Perform a single pass generating values from the model.\n"
                + "     * @param inputs An object containing the parameters required to run inference on the model.\n"
                + "     * @return An object containing the values computed by the inference step.\n" + "     */\n");
        sb.append("    public " + executeOutputs + " execute(" + executeInputs + " inputs) {\n");
        setExecuteInputs(sb, "inputs");
        sb.append("        execute();\n");
        sb.append("        return new " + executeOutputs + "(this);\n");
        sb.append("    }\n");
    }

    private void setExecuteInputs(StringBuilder sb, String objectName) {
        for(VariableName arg:modelInputs) {
            Variable<?> v = traces.getVariable(arg);
            if(fieldDescs.containsKey(v.getUniqueVarDesc().name))
                sb.append("        this." + arg + ".set(" + objectName + "." + arg + ");\n");
        }
        for(VariableName arg:observedShapeableInputs) {
            VariableName shapeArg = VariableNames.shapeName(arg);
            shapeArg = (modelInputs.contains(shapeArg)) ? arg : shapeArg;
            sb.append("        this." + VariableNames.internalName(arg) + ".setShape(" + objectName + "." + shapeArg
                    + ");\n");
        }
    }

    private void constructHelperClasses(StringBuilder sb) {
        constructExecuteInputClass(sb);
        constructAllInputClass(sb);
        constructExecuteOutputClass(sb);
        constructProbabilityOutputClass(sb, true);
        constructProbabilityOutputClass(sb, false);
        constructInferredModelOutputClass(sb);
    }

    private void constructInferredModelOutputClass(StringBuilder sb) {
        // A class to set all the variables
        StringBuilder fields = new StringBuilder();
        StringBuilder sig = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();

        sb.append("\n    /**\n" + "     * A class to hold all the outputs from the model after an infer model call.\n"
                + "     */\n");

        sig.append("\n        " + inferValuesOutputs + "(" + className + " " + modelParam + ")");

        constructorBody.append("{\n");
        for(VariableName arg:computedVariables) {
            VariableDescription<?> uniqueName = traces.getVariable(arg).getUniqueVarDesc();
            FieldDesc<?> argDesc = fieldDescs.get(uniqueName.name);
            if(!argDesc.observed) {
                fields.append("        /** Field holding the MAP or Sample value of " + arg
                        + " after an infer model call. */\n");
                fields.append("        public final " + argDesc.varDesc.type.getJavaType() + "[] " + arg + ";\n");
                constructorBody.append("            this." + arg + " = " + modelParam + ".getInferredValue("
                        + modelParam + "." + VariableNames.internalName(arg) + ");\n");
            }
        }
        constructorBody.append("        }\n");

        // Concatenate the parts.
        sb.append("    public static class " + inferValuesOutputs + " {\n");
        sb.append(fields.toString() + sig + " " + constructorBody);
        sb.append("    }\n");
    }

    public void constructExecuteOutputClass(StringBuilder sb) {
        // A class to set all the variables
        StringBuilder fields = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();

        sb.append("\n    /**\n" + "     * A class to hold all the outputs from the model after an infer values step.\n"
                + "     */\n");

        String sig = "\n        " + executeOutputs + "(" + className + " " + modelParam + ")";

        constructorBody.append("{\n");
        for(VariableName arg:computedVariables) {
            VariableDescription<?> uniqueName = traces.getVariable(arg).getUniqueVarDesc();
            FieldDesc<?> argDesc = fieldDescs.get(uniqueName.name);
            fields.append("        /** Field holding the value of " + arg + " after a convention execution step.*/\n");
            fields.append("        public final " + argDesc.varDesc.type.getJavaType() + " " + arg + ";\n");
            constructorBody.append("            this." + arg + " = " + modelParam + "." + arg + ".getSamples()[0];\n");
        }
        constructorBody.append("        }\n");

        // Concatenate the parts.
        sb.append("    public static class " + executeOutputs + " {\n");
        sb.append(fields + sig + " " + constructorBody);
        sb.append("    }\n");
    }

    // TODO remove use logs as all probabilities are log probabilities now.
    public void constructProbabilityOutputClass(StringBuilder sb, boolean useLogs) {
        // A class to set all the variables
        StringBuilder fields = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();

        sb.append("\n    /**\n"
                + "     * A class to hold all the probabilities from the model after a generate probabilities step.\n"
                + "     */\n");

        constructorBody.append("\n        " + ((useLogs) ? logProbabilityOutputs : probabilityOutputs) + "(" + className
                + " " + modelParam + ")");

        constructorBody.append(" {\n");

        fields.append("        private final double "
                + VariableNames.internalName(((useLogs) ? "logM" : "m") + "odelProbability") + ";\n");
        constructorBody
                .append("            this." + VariableNames.internalName(((useLogs) ? "logM" : "m") + "odelProbability")
                        + " = " + modelParam + ".get" + ((useLogs) ? "Log" : "") + "Probability();\n");

        for(RandomVariableDesc rv:randomVariables) {
            VariableName arg = rv.name;
            String javaType = fieldDescs.get(VariableNames.logProbabilityName(rv.uniqueName).name).varDesc.type
                    .getJavaType();
            fields.append("        /** Field holding the " + ((useLogs) ? "log " : "")
                    + "probability of random variable " + arg + " */\n");
            fields.append("        public final " + javaType + " " + arg + ";\n");
            constructorBody.append("            this." + arg + " = " + modelParam + "." + arg + ".get"
                    + ((useLogs) ? "Log" : "") + "Probability();\n");
        }

        for(VariableName arg:computedVariables) {

            fields.append("        /** Field holding the " + ((useLogs) ? "log " : "")
                    + "probability of computed variable " + arg + " */\n");
            fields.append("        public final double " + arg + ";\n");
            constructorBody.append("            this." + arg + " = " + modelParam + "." + arg + ".get"
                    + ((useLogs) ? "Log" : "") + "Probability();\n");
        }

        constructorBody.append("        }\n\n");

        constructorBody.append(
                "        /** Method to return " + ((useLogs) ? "log " : "") + "probability of the whole model \n");
        constructorBody.append(
                "         *  @return The " + ((useLogs) ? "log " : "") + "probability of the whole model. */\n");
        constructorBody.append("        public double getModelProbability() { return "
                + VariableNames.internalName(((useLogs) ? "logM" : "m") + "odelProbability") + "; }\n");

        // Concatenate the parts.
        sb.append("    public static class " + ((useLogs) ? logProbabilityOutputs : probabilityOutputs) + " {\n");
        sb.append(fields.toString() + constructorBody);
        sb.append("    }\n");
    }

    private void constructAllInputClass(StringBuilder sb) {
        // A class to set all the variables
        StringBuilder constructorComment = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        StringBuilder sig = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();

        sb.append("\n    /**\n"
                + "     * A class to hold all the inputs for the model. It can be used to parameterize inference of the model probabilities\n"
                + "     * and probability calculations.\n" + "     */\n");

        constructorComment.append("\n        /**\n"
                + "          * A constructor to take all the required values by the model to infer the model\n"
                + "          * parameters, or to generate probabilities for the model.\n");

        sig.append("        public " + allInputs + "(");
        constructorBody.append("{\n");

        if(constructorArgs.length != 0) {
            VariableName arg = constructorArgs[0];
            Variable<?> v = traces.getVariable(arg);
            Type<?> type = v.getType();
            constructorComment.append("          * @param " + arg + " The value to set " + arg + " to.\n");
            sig.append(type.getJavaType() + " " + arg);
            if(fieldDescs.containsKey(v.getUniqueVarDesc().name)) {
                fields.append("        /** Field holding the value of model input " + arg + " */\n");
                fields.append("        public final " + type.getJavaType() + " " + arg + ";\n");
                constructorBody.append("            this." + arg + " = " + arg + ";\n");
            }

            for(int i = 1; i < constructorArgs.length; i++) {
                arg = constructorArgs[i];
                v = traces.getVariable(arg);
                type = v.getType();
                constructorComment.append("          * @param " + arg + " The value to set " + arg + " to.\n");
                sig.append(", " + type.getJavaType() + " " + arg);
                if(fieldDescs.containsKey(v.getUniqueVarDesc().name)) {
                    fields.append("        /** Field holding the value of model input " + arg + " */\n");
                    fields.append("        public final " + type.getJavaType() + " " + arg + ";\n");
                    constructorBody.append("            this." + arg + " = " + arg + ";\n");
                }
            }
        }

        constructorComment.append("          */\n");
        sig.append(")");
        constructorBody.append("        }\n");

        // Concatenate the parts.
        sb.append("    public static class " + allInputs + " {\n");
        sb.append(fields.toString() + constructorComment + sig + " " + constructorBody);
        sb.append("    }\n");
    }

    public void constructExecuteInputClass(StringBuilder sb) {
        StringBuilder constructorComment = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        StringBuilder sig = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();

        sb.append("\n    /**\n"
                + "     * A class to hold all the values required to perform a value inference on the model.\n"
                + "     */\n");

        // Construct the signature and finish the comments.
        constructorComment.append("\n        /**\n"
                + "          * A constructor taking all the values required to set up the model to infer variables.\n");
        sig.append("        public " + executeInputs + "(");

        boolean first = true;

        for(VariableName arg:constructorArgs) {
            if(modelInputs.contains(arg)) {
                constructorComment.append("          * @param " + arg + " The value to set " + arg + " to.\n");
                if(first)
                    first = false;
                else
                    sig.append(", ");
                Type<?> type = traces.getVariable(arg).getType();
                sig.append(type.getJavaType() + " " + arg);
                fields.append("        /** Field holding the value of model input " + arg + " */\n");
                fields.append("        public final " + type.getJavaType() + " " + arg + ";\n");
            }

            if(observedShapeableInputs.contains(arg)) {
                constructorComment.append("          * @param " + VariableNames.shapeName(arg)
                        + " An integer array describing the shape of variable " + arg
                        + " to use in the model when generating results.\n");
                if(first)
                    first = false;
                else
                    sig.append(", ");
                Type<?> type = traces.getVariable(arg).getType();
                String typeString = type.getJavaType();
                typeString = "int" + typeString.split("\\]", 2)[1];
                VariableName shapeArg = VariableNames.shapeName(arg);
                shapeArg = (modelInputs.contains(shapeArg)) ? arg : shapeArg;
                sig.append(typeString + " " + shapeArg);
                fields.append("        /** Field holding the shape of model input " + arg + " */\n");
                fields.append("        public final " + typeString + " " + shapeArg + ";\n");
            }
        }
        constructorComment.append("          */\n");
        sig.append(")");

        // Construct the body.
        constructorBody.append("{\n");
        for(VariableName arg:modelInputs)
            constructorBody.append("            this." + arg + " = " + arg + ";\n");
        for(VariableName arg:observedShapeableInputs) {
            VariableName shapeArg = VariableNames.shapeName(arg);
            shapeArg = (modelInputs.contains(shapeArg)) ? arg : shapeArg;
            constructorBody.append("            this." + shapeArg + " = " + shapeArg + ";\n");
        }
        constructorBody.append("        }\n");

        // Concatenate the parts.
        sb.append("    public static class " + executeInputs + " {\n");
        sb.append(fields.toString() + constructorComment + sig + " " + constructorBody);
        sb.append("    }\n");
    }

    private void constructConstructors(StringBuilder sb) {
        sb.append("    //Constructors\n");
        sb.append("    /**\n" + "     * A constructor for a model where no variable values are set.\n" + "     */\n");
        // Empty constructor
        sb.append("    public " + className + "() {\n");
        sb.append("        super();\n");

        // Add computed fields to the map of computed fields.
        sb.append("        //ComputedVariable\n");
        for(VariableName cVar:computedVariables)
            sb.append("        " + VariableNames.internalName("computedVariables") + ".put(\"" + cVar + "\", "
                    + VariableNames.internalName(cVar) + ");\n");

        // Add the model input fields
        if(modelInputs.size() != 0) {
            sb.append("\n        //ModelInputs\n");
            for(VariableName mVar:modelInputs)
                sb.append("        " + VariableNames.internalName("modelInputs") + ".put(\"" + mVar + "\", "
                        + VariableNames.internalName(mVar) + ");\n");
        }

        // Add the observed fields
        if(observedOnlyInputs.size() != 0) {
            sb.append("\n        //Observed scalar fields\n");
            for(VariableName oVar:observedOnlyInputs)
                sb.append("        " + VariableNames.internalName("regularObservedValues") + ".put(\"" + oVar + "\", "
                        + VariableNames.internalName(oVar) + ");\n");
        }

        if(observedShapeableInputs.size() != 0) {
            sb.append("\n        //Observed array fields\n");
            for(VariableName oVar:observedShapeableInputs)
                sb.append("        " + VariableNames.internalName("shapedObservedValues") + ".put(\"" + oVar + "\", "
                        + VariableNames.internalName(oVar) + ");\n");
        }

        sb.append("        init(" + coreName + ", " + VariableNames.internalName("modelInputs") + ", "
                + VariableNames.internalName("regularObservedValues") + ", "
                + VariableNames.internalName("shapedObservedValues") + ", "
                + VariableNames.internalName("computedVariables") + ", "
                + VariableNames.internalName("probabilityVariables") + ");\n");
        sb.append("    }\n");

        // If we have inputs, and have observed variables, then we can have a
        // constructor that
        // is just for variable inference.
        {
            if((modelInputs.size() != 0 || observedShapeableInputs.size() != 0)
                    && modelInputs.size() != constructorArgs.length) {

                StringBuilder comment = new StringBuilder();
                StringBuilder sig = new StringBuilder();

                // Construct the signature and finish the comments.
                comment.append("    /**\n"
                        + "      * A constructor to set all the required values in the model to infer values. These\n"
                        + "      * will be values in an untrained model so this will only generate values from the\n"
                        + "      * default distributions described in the model.\n");
                sig.append("\n    public " + className + "(");

                boolean first = true;

                for(VariableName arg:constructorArgs) {
                    if(modelInputs.contains(arg)) {
                        comment.append("      * @param " + arg + " The value to set " + arg + " to.\n");
                        if(first)
                            first = false;
                        else
                            sig.append(", ");
                        Type<?> type = traces.getVariable(arg).getType();
                        sig.append(type.getJavaType() + " " + arg);
                    }

                    if(observedShapeableInputs.contains(arg)) {
                        VariableName shapeArg = VariableNames.shapeName(arg);
                        comment.append(
                                "      * @param " + shapeArg + " An integer array describing the shape of variable "
                                        + arg + " to use in the model when generating results.\n");
                        if(first)
                            first = false;
                        else
                            sig.append(", ");
                        Type<?> type = traces.getVariable(arg).getType();
                        String typeString = type.getJavaType();
                        typeString = "int" + typeString.split("\\]", 2)[1];
                        // Test to avoid name collisions on the argument renaming.
                        shapeArg = (modelInputs.contains(shapeArg)) ? arg : shapeArg;
                        sig.append(typeString + " " + shapeArg);
                    }
                }
                comment.append("      */\n");
                sig.append(")");

                // Construct the body.
                StringBuilder body = new StringBuilder();
                body.append("{\n");
                body.append("        this();\n");
                for(VariableName arg:modelInputs) {
                    Variable<?> v = traces.getVariable(arg);
                    if(fieldDescs.containsKey(v.getUniqueVarDesc().name))
                        body.append("        this." + VariableNames.internalName(arg) + ".set(" + arg + ");\n");
                }
                for(VariableName arg:observedShapeableInputs) {
                    // Test to avoid name collisions on the argument renaming.
                    VariableName shapeArg = VariableNames.shapeName(arg);
                    shapeArg = (modelInputs.contains(shapeArg)) ? arg : shapeArg;
                    body.append("        this." + VariableNames.internalName(arg) + ".setShape(" + shapeArg + ");\n");
                }
                body.append("    }\n");

                // Concatenate the parts.
                sb.append(comment.toString() + sig + " " + body);
            }
        }

        // If required, a constructor that sets all the observed variables.
        if(constructorArgs.length != 0) {
            StringBuilder comment = new StringBuilder();
            StringBuilder sig = new StringBuilder();
            StringBuilder body = new StringBuilder();

            comment.append("    /**\n"
                    + "      * A constructor to set all the required values in the model to infer the model\n"
                    + "      * parameters, or to generate probabilities for the model.\n");

            sig.append("\n    public " + className + "(");
            body.append("{\n        this();\n");

            VariableName arg = constructorArgs[0];
            Variable<?> v = traces.getVariable(arg);
            Type<?> type = v.getType();
            comment.append("      * @param " + arg + " The value to set " + arg + " to.\n");
            sig.append(type.getJavaType() + " " + arg);
            if(fieldDescs.containsKey(v.getUniqueVarDesc().name))
                body.append("        this." + arg + ".set(" + arg + ");\n");

            for(int i = 1; i < constructorArgs.length; i++) {
                arg = constructorArgs[i];
                v = traces.getVariable(arg);
                type = v.getType();
                comment.append("      * @param " + arg + " The value to set " + arg + " to.\n");
                sig.append(", " + type.getJavaType() + " " + arg);
                if(fieldDescs.containsKey(v.getUniqueVarDesc().name))
                    body.append("        this." + arg + ".set(" + arg + ");\n");
            }

            comment.append("      */\n");
            sig.append(")");
            body.append("    }\n");

            // Concatenate the parts.
            sb.append(comment.toString() + sig + " " + body);
        }
    }

    private void constructComputedField(VariableName fieldName, FieldDesc<?> fieldDesc, StringBuilder sb) {
        String javaType = fieldDesc.varDesc.type.getJavaType();
        VariableName uniqueName = fieldDesc.varDesc.name;
        String internalType;
        String genericType = "";
        String constructorArgs = "this, \"" + fieldName + "\"";
        constructorArgs += ", " + ((fieldDesc.sample) ? "true" : "false");
        boolean generic = false;
        switch(javaType) {
            case "double":
                internalType = "ComputedDouble";
                break;

            case "int":
                internalType = "ComputedInteger";
                break;

            case "boolean":
                internalType = "ComputedBoolean";
                break;

            case "double[]":
                internalType = "ComputedDoubleArray";
                break;

            case "int[]":
                internalType = "ComputedIntegerArray";
                break;

            case "boolean[]":
                internalType = "ComputedBooleanArray";
                break;

            default:
                internalType = "ComputedObjectArray";
                genericType = "<" + javaType.substring(0, javaType.length() - 2) + ">";
                String[] parts = javaType.split("\\[");
                switch(parts[0]) {
                    case "int":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.INT";
                        break;

                    case "double":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE";
                        break;

                    case "boolean":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN";
                        break;

                    default:
                        throw new CompilerException("Unknown type encountered " + parts[0]);
                }
                constructorArgs += ", " + (parts.length - 1);
                generic = true;
        }

        sb.append("    private final " + internalType + "Internal" + genericType + " "
                + VariableNames.internalName(fieldName) + " = new " + internalType + "Internal" + genericType + "("
                + constructorArgs + ") {\n");

        sb.append("        @Override\n");
        sb.append("        protected " + javaType + " getValue() { return " + coreName + getMethod(uniqueName)
                + "(); }\n\n");

        Set<Variable<?>> requirements = getSourceVariables(fieldDesc);
        if(fieldDesc.setter && requirements.isEmpty()) {
            sb.append("        @Override\n");
            sb.append("        protected void setValueInternal(" + javaType + " value) {\n");
            sb.append("            " + coreName + setMethod(uniqueName) + "(value);\n");
            sb.append("            setFixed(true);\n");
            sb.append("        }\n\n");
        } else {
            sb.append("        @Override\n");
            sb.append("        protected void setValueInternal(" + javaType + " value) {}\n\n");

            if(requirements.isEmpty()) {
                sb.append("        @Override\n");
                sb.append("        protected void testSettable() {"
                        + "\n            throw new SandwoodException(\"Set is not available for variable " + fieldName
                        + ".\");\n" + "        }\n\n");
            } else {
                sb.append("        @Override\n");
                sb.append("        protected void testSettable() {\n"
                        + "            throw new SandwoodException(\"Set is not available for variable " + fieldName
                        + " because its value depends on variable" + ((requirements.size() > 1) ? "s " : " "));
                PriorityQueue<String> p = new PriorityQueue<>();
                for(Variable<?> v:requirements)
                    p.add("\\\"" + v.getUniqueVarDesc().name.getName() + "\\\"");
                sb.append(p.poll());
                while(!p.isEmpty()) {
                    sb.append(", ");
                    if(p.size() == 1)
                        sb.append("and ");
                    sb.append(p.poll());
                }
                sb.append(".\");\n        }\n\n");
            }
        }

        sb.append("        @Override\n");
        VariableDescription<?> probFieldName = VariableNames.logProbabilityName(uniqueName);
        if(fieldDescs.containsKey(probFieldName.name))
            sb.append("        public double getCurrentLogProbability() { return " + coreName
                    + getMethod(probFieldName.name) + "(); }\n");
        else
            sb.append(
                    "        public double getCurrentLogProbability() { throw new SandwoodException(\"Log probabilities are not available for this value.\"); }\n");

        if(generic) {
            sb.append("\n        @Override\n");
            sb.append("        public " + javaType + "[] constructArray(int iterations) {\n");
            String[] parts = javaType.split("\\[", 2);
            sb.append("            return new " + parts[0] + "[iterations][" + parts[1] + ";\n");
            sb.append("        }\n\n");
        } else
            sb.append("\n");

        // Gather all the flags associated with this variable
        List<VariableDescription<BooleanVariable>> flags = getFlags(traces, uniqueName);

        // Construct set and query methods.
        sb.append("        @Override\n");
        sb.append("        public void setFixed(boolean fixed) {\n" + "            synchronized(model) {\n");
        for(VariableDescription<BooleanVariable> flag:flags)
            sb.append("                " + coreName + setMethod(flag.name) + "(fixed);\n");
        sb.append("            }\n");
        sb.append("        }\n\n");

        sb.append("        @Override\n");
        sb.append("        public Immutability isFixed() {\n");
        if(flags.isEmpty())
            sb.append("            return Immutability.DETERMINISTIC;\n");
        else if(flags.size() == 1) {
            VariableDescription<BooleanVariable> flag = flags.iterator().next();
            // construct the outputs.
            sb.append("            if(" + coreName + getMethod(flag.name) + "())\n");
            sb.append("                return Immutability.FIXED;\n");
            sb.append("            else\n");
            sb.append("                return Immutability.FREE;\n");
        } else {
            // Get the values of all the flags, and construct the guards
            String andGuard = "";
            String orGuard = "";
            boolean first = true;
            for(VariableDescription<BooleanVariable> flag:flags) {
                sb.append("            boolean " + flag + " = " + coreName + getMethod(flag.name) + "();\n");
                if(first)
                    first = false;
                else {
                    andGuard += " && ";
                    orGuard += " || ";
                }
                andGuard += flag;
                orGuard += flag;
            }

            // construct the outputs.
            sb.append("            if(" + andGuard + ")\n");
            sb.append("                return Immutability.FIXED;\n");
            sb.append("            else if(" + orGuard + ")\n");
            sb.append("                return Immutability.PARTIALLY_FIXED;\n");
            sb.append("            else\n");
            sb.append("                return Immutability.FREE;\n");
        }
        sb.append("        }\n");

        sb.append("    };\n\n");

        computedJavaDoc(sb, javaType, fieldName, fieldDesc.comment);

        sb.append("    public final " + internalType + genericType + " " + fieldName + " = "
                + VariableNames.internalName(fieldName) + ";\n\n");
    }

    private Set<Variable<?>> getSourceVariables(FieldDesc<?> fieldDesc) {
        Variable<?> v = traces.getVariable(fieldDesc.varDesc.name);
        if(v == null)
            return Collections.emptySet();
        if(v.getCurrentInstance().isDeterministic())
            return traces.getRequiredIntermediates(v);
        else {
            Set<Variable<?>> requirements = new HashSet<>();
            for(Variable<?> r:traces.getRequiredIntermediates(v))
                if(!r.getCurrentInstance().isDeterministic())
                    requirements.add(r);
            return requirements;
        }
    }

    private String getMethod(VariableName name) {
        return "." + FunctionName.getterName(name);
    }

    private String setMethod(VariableName name) {
        return "." + FunctionName.setterName(name);
    }

    private List<VariableDescription<BooleanVariable>> getFlags(Traces traces, VariableName uniqueName) {
        Variable<?> v = traces.getVariable(uniqueName);
        Set<SampleTask<?, ?>> s = new HashSet<>(traces.getSourceSampleTasks(v));
        if(v.getType().isArray()) {
            ArrayVariable<?> vec = (ArrayVariable<?>) v;
            vec = vec.getChildInstance();
            while(vec != null) {
                s.addAll(traces.getSourceSampleTasks(vec));
                vec = vec.getChildInstance();
            }
        }

        List<VariableDescription<BooleanVariable>> l = new ArrayList<>();
        for(SampleTask<?, ?> task:s)
            l.add(VariableNames.fixedFlagName(task));
        Collections.sort(l);
        return l;
    }

    private void computedJavaDoc(StringBuilder sb, String javaType, VariableName name, String comment) {
        if(comment != null)
            sb.append(comment + "\n");
        else {
            sb.append("    /**\n");
            sb.append("     * Computed variable representing " + name + " of type " + javaType
                    + " from the Sandwood model \n");
            sb.append("     */\n");
        }
    }

    private void constructObservedField(VariableName fieldName, FieldDesc<?> fieldDesc, StringBuilder sb) {
        String javaType = fieldDesc.varDesc.type.getJavaType();
        VariableName uniqueName = fieldDesc.varDesc.name;
        String varType;
        String genericType = "";
        String constructorArgs = "this, \"" + fieldName + "\"";
        switch(javaType) {
            case "double":
                varType = "ObservedDouble";
                break;

            case "int":
                varType = "ObservedInteger";
                break;

            case "boolean":
                varType = "ObservedBoolean";
                break;

            case "double[]":
                varType = "ObservedDoubleArray";
                break;

            case "int[]":
                varType = "ObservedIntegerArray";
                break;

            case "boolean[]":
                varType = "ObservedBooleanArray";
                break;

            default:
                varType = "ObservedObjectArray";
                genericType = "<" + javaType.substring(0, javaType.length() - 2) + ">";
                String[] parts = javaType.split("\\[");
                switch(parts[0]) {
                    case "int":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.INT";
                        break;

                    case "double":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE";
                        break;

                    case "boolean":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN";
                        break;

                    default:
                        throw new CompilerException("Unknown type encountered " + parts[0]);
                }
                constructorArgs += ", " + (parts.length - 1);
        }

        sb.append(
                "    private final " + varType + "Internal" + genericType + " " + VariableNames.internalName(fieldName)
                        + " = new " + varType + "Internal" + genericType + "(" + constructorArgs + ") {\n");

        sb.append("        @Override\n");
        sb.append("        public " + javaType + " get() {\n" + "            synchronized(model) {\n"
                + "                return " + coreName + getMethod(uniqueName) + "();\n" + "            }\n"
                + "        }\n\n");

        sb.append("        @Override\n");
        sb.append("        protected void setValue(" + javaType + " value) { " + coreName + setMethod(uniqueName)
                + "(value); }\n");

        sb.append("    };\n\n");

        observedJavaDoc(sb, javaType, fieldName, fieldDesc.comment);

        sb.append("    public final " + varType + genericType + " " + fieldName + " = "
                + VariableNames.internalName(fieldName) + ";\n\n");
    }

    private void constructShapedObservedField(VariableName fieldName, FieldDesc<?> fieldDesc, StringBuilder sb) {
        String javaType = fieldDesc.varDesc.type.getJavaType();
        VariableName uniqueName = fieldDesc.varDesc.name;
        String varType;
        String baseType = javaType.substring(0, javaType.length() - 2);
        String genericType = "";
        String shapeType = "int";
        String constructorArgs = "this, \"" + fieldName + "\"";
        int dims = 1;

        boolean simple = true;
        switch(javaType) {
            case "double[]":
                varType = "ObservedDoubleArrayShapeable";
                break;

            case "int[]":
                varType = "ObservedIntegerArrayShapeable";
                break;

            case "boolean[]":
                varType = "ObservedBooleanArrayShapeable";
                break;

            default:
                varType = "ObservedObjectArrayShapeable";
                dims = baseType.split("\\[").length - 1;
                for(int i = 0; i < dims; i++)
                    shapeType += "[]";
                genericType = "<" + baseType + ", " + shapeType + ">";
                String[] parts = javaType.split("\\[");
                switch(parts[0]) {
                    case "int":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.INT";
                        break;

                    case "double":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.DOUBLE";
                        break;

                    case "boolean":
                        constructorArgs += ", org.sandwood.runtime.internal.model.util.BaseType.BOOLEAN";
                        break;

                    default:
                        throw new CompilerException("Unknown type encountered " + parts[0]);
                }
                constructorArgs += ", " + (parts.length - 1);
                simple = false;
        }

        sb.append(
                "    private final " + varType + "Internal" + genericType + " " + VariableNames.internalName(fieldName)
                        + " = new " + varType + "Internal" + genericType + "(" + constructorArgs + ") {\n");

        sb.append("        @Override\n");
        sb.append("        public " + javaType + " get() {\n" + "            synchronized(model) {\n"
                + "                return " + coreName + getMethod(uniqueName) + "();\n" + "            }\n"
                + "        }\n\n");

        sb.append("        @Override\n");
        sb.append("        public void setValue(" + javaType + " value) {\n" + "            " + coreName
                + setMethod(uniqueName) + "(value);\n");

        VariableName lengthName = VariableNames.lengthName(fieldName);
        VariableName lengthUniqueName = traces.getVariable(lengthName).getUniqueVarDesc().name;

        if(simple)
            sb.append("            " + coreName + setMethod(lengthUniqueName) + "(value.length);\n");
        else
            sb.append("            " + coreName + setMethod(lengthUniqueName) + "("
                    + ((dims == 0) ? "value.length" : "getDims(value)") + ");\n");
        sb.append("        }\n\n");

        sb.append("        @Override\n");
        sb.append("        public void setShapeInternal(" + shapeType + " shape) {\n" + "            " + coreName
                + setMethod(lengthUniqueName) + "(shape);\n" + "        }\n\n");

        sb.append("        @Override\n");
        sb.append("        public " + shapeType + " getShape() {\n" + "            return " + coreName
                + getMethod(lengthUniqueName) + "();\n" + "        }\n");

        if(!simple) {
            // Appends are split to allow text to be set before the content of constructDims
            // is written.
            sb.append("        private final " + shapeType + " getDims(" + javaType + " v" + dims + ") {\n");
            sb.append("            return " + constructGetDims(dims, baseType, sb, "            ") + ";\n"
                    + "        }\n");
        }

        sb.append("    };\n\n");

        observedJavaDoc(sb, javaType, fieldName, fieldDesc.comment);

        sb.append("    public final " + varType + genericType + " " + fieldName + " = "
                + VariableNames.internalName(fieldName) + ";\n\n");
    }

    private static String constructGetDims(int dims, String baseType, StringBuilder sb, String indent) {
        if(dims == 0)
            return "v0.length";
        else {
            // Allocate array sx of length vx.length
            sb.append(indent + "int");
            for(int i = 0; i < dims; i++)
                sb.append("[]");
            sb.append(" s" + dims + " = new int[v" + dims + ".length]");
            for(int i = 1; i < dims; i++)
                sb.append("[]");
            sb.append(";\n");

            // populate the array
            sb.append(indent + "for(int i" + dims + " = 0; i" + dims + " < v" + dims + ".length; i" + dims + "++) {\n");
            sb.append(indent + "    " + baseType + " v" + (dims - 1) + " = v" + dims + "[i" + dims + "];\n");
            String result = constructGetDims(dims - 1, baseType.substring(0, baseType.length() - 2), sb,
                    indent + "    ");
            sb.append(indent + "    s" + dims + "[i" + dims + "] = " + result + ";\n");
            sb.append(indent + "}\n");
            return "s" + dims;
        }
    }

    private void observedJavaDoc(StringBuilder sb, String javaType, VariableName name, String comment) {
        if(comment != null)
            sb.append("    " + comment + "\n");
        else {
            sb.append("    /**\n");
            sb.append("     * Observed variable representing " + name + " of type " + javaType
                    + " from the Sandwood model \n");
            sb.append("     */\n");
        }
    }

    private void constructRandomVariableField(RandomVariableDesc desc, Map<VariableName, FieldDesc<?>> classFields,
            StringBuilder sb) {
        Type<?> type = classFields.get(VariableNames.logProbabilityName(desc.uniqueName).name).varDesc.type;
        String javaType = type.getJavaType();
        if(javaType.equals("double"))
            sb.append("    private final RandomVariableInternal " + VariableNames.internalName(desc.name)
                    + " = new RandomVariableInternal(this, \"" + desc.name + "\") {\n");
        else {
            int arrayDimension = type.getDepth();
            sb.append("    private final IteratedRandomVariableInternal<" + javaType + "> "
                    + VariableNames.internalName(desc.name) + " = new IteratedRandomVariableInternal<" + javaType
                    + ">(this, \"" + desc.name + "\", " + arrayDimension + ") {\n");
        }

        sb.append("        @Override\n");
        sb.append("        public " + javaType + " getCurrentLogProbability() {\n");
        sb.append("            return " + coreName + getMethod(VariableNames.logProbabilityName(desc.uniqueName).name)
                + "();\n");
        sb.append("        }\n");

        sb.append("    };\n\n");
        if(javaType.equals("double")) {
            randomVariableJavaDoc(sb, desc);
            sb.append("    public final RandomVariable " + desc.name + " = " + VariableNames.internalName(desc.name)
                    + ";\n\n");
        } else {
            iteratedRandomVariableJavaDoc(sb, desc, javaType);
            sb.append("    public final IteratedRandomVariable<" + javaType + "> " + desc.name + " = "
                    + VariableNames.internalName(desc.name) + ";\n\n");
        }
    }

    private void randomVariableJavaDoc(StringBuilder sb, RandomVariableDesc desc) {
        if(desc.comment != null)
            sb.append("    " + desc.comment + "\n");
        else {
            sb.append("    /**\n");
            sb.append("     * Random variable representing " + desc.name + " from the Sandwood model \n");
            sb.append("     */\n");
        }
    }

    private void iteratedRandomVariableJavaDoc(StringBuilder sb, RandomVariableDesc desc, String javaType) {
        if(desc.comment != null)
            sb.append("    " + desc.comment + "\n");
        else {
            int dims = javaType.split("\\[").length - 1;
            sb.append("    /**\n");
            sb.append("     * Iterated random variable representing the random variable " + desc.name + " from\n");
            sb.append("     * the Sandwood model. The random variable is embedded in "
                    + ((dims == 1) ? "a loop" : (dims + " loops")) + "\n");
            sb.append("     * so results are returned as " + ((dims == 1) ? "an" : ("a " + dims + "dimensional "))
                    + "array.\n");
            sb.append("     */\n");
        }
    }
}
