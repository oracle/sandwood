/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.compiler.compilation.CompilationContext.SampleFunctionClass;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.exceptions.CompilerException;

public class FunctionName extends Name {

    private FunctionName(String name) {
        super(name);
        if(name == null)
            throw new CompilerException("Null function name set");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        FunctionName other = (FunctionName) obj;
        return name.equals(other.name);
    }

    public static FunctionName setterName(VariableName name) {
        return new FunctionName("set" + prefix + name);
    }

    public static FunctionName getterName(VariableName name) {
        return new FunctionName("get" + prefix + name);
    }

    public static FunctionName createFunctionName(String name) {
        return new FunctionName(name);
    }

    public static FunctionName createFunctionName(VariableName name) {
        return new FunctionName(name.getName());
    }

    private static String logProbabilityDistributionName(VariableDescription<?> varDesc) {
        return "logProbabilityDistribution" + Name.prefix + varDesc;
    }

    public static String logProbabilityValueName(VariableDescription<?> varDesc) {
        return "logProbabilityValue" + Name.prefix + varDesc;
    }

    public static FunctionName createFunctionName(SampleFunctionClass functionClass, SampleTask<?, ?> sample) {
        return switch(functionClass) {
            case INFERENCE -> new FunctionName("inferSample" + sample.id());
            case LOG_PROBABILITY_DISTRIBUTIONS -> new FunctionName(
                    logProbabilityDistributionName(sample.getUniqueVarDesc()));
            case LOG_PROBABILITY_VALUE -> new FunctionName(logProbabilityValueName(sample.getUniqueVarDesc()));
            case SAMPLE -> new FunctionName("drawValueSample" + sample.id());
        };
    }

    public static FunctionName getConstructorName(ClassName className) {
        return new FunctionName(className.getName());
    }
}