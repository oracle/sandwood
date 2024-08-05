/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.rng.RandomNumberGenerator;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

//TODO Separate this out so that we have regular names and global names, and only global names can be used to create class fields.

public final class VariableNames {
    private VariableNames() {}

    public static <A extends Variable<A>> VariableDescription<A> internalName(VariableDescription<A> varDesc) {
        return new VariableDescription<>(Name.prefix + varDesc, varDesc.type, varDesc.name.comment);
    }

    public static VariableName internalName(VariableName name) {
        return new VariableName(Name.prefix + name, name.comment);
    }

    public static VariableName internalName(String name) {
        return new VariableName(Name.prefix + name, false);
    }

    public static VariableName internalSystemName(String name) {
        return new VariableName("system" + Name.prefix + name, false);
    }

    private static final String rng = "RNG" + Name.prefix;

    public static VariableDescription<RandomNumberGenerator> rngName(int depth) {
        if(depth == 0)
            return new VariableDescription<>(rng, VariableType.RNG, true);
        else
            return new VariableDescription<>(rng + depth, VariableType.RNG, true);
    }

    private static final String threadId = "threadID" + Name.prefix;

    public static VariableDescription<IntVariable> threadIdName(VariableName name) {
        return new VariableDescription<>(threadId + name, VariableType.IntVariable, name.comment);
    }

    private static final String logProbability = "logProbability" + Name.prefix;

    public static VariableDescription<DoubleVariable> logProbabilityName(VariableDescription<?> varDesc) {
        return new VariableDescription<>(logProbability + varDesc, VariableType.DoubleVariable, varDesc.name.comment);
    }

    public static VariableDescription<DoubleVariable> logProbabilityName(VariableName name) {
        return new VariableDescription<>(logProbability + name, VariableType.DoubleVariable, name.comment);
    }

    private static final String logProbabilityValue = "logProbabilityValue" + Name.prefix;

    public static VariableDescription<DoubleVariable> logProbabilityValueName(VariableDescription<?> varDesc) {
        return new VariableDescription<>(logProbabilityValue + varDesc, VariableType.DoubleVariable,
                varDesc.name.comment);
    }

    private static final String logProbabilityDistribution = "logProbabilityDistribution" + Name.prefix;

    public static VariableDescription<DoubleVariable> logProbabilityDistributionName(VariableDescription<?> varDesc) {
        return new VariableDescription<>(logProbabilityDistribution + varDesc, VariableType.DoubleVariable,
                varDesc.name.comment);
    }

    private static final String fixedSampleProbability = "fixedSampleDistProbability" + Name.prefix;

    public static VariableName fixedSampleProbabilityName(VariableName name) {
        return new VariableName(fixedSampleProbability + name, name.comment);
    }

    private static final String lengthPrefix = "length" + Name.prefix;

    public static boolean isLengthName(String name) {
        return name.startsWith(lengthPrefix);
    }

    public static boolean isLengthName(VariableName name) {
        return name.getName().startsWith(lengthPrefix);
    }

    public static VariableDescription<IntVariable> lengthName(VariableDescription<?> varDesc) {
        return new VariableDescription<>(lengthPrefix + varDesc, VariableType.IntVariable, varDesc.name.comment);
    }

    public static VariableName lengthName(VariableName name) {
        return new VariableName(lengthPrefix + name, name.comment);
    }

    private static final String shape = "Shape";

    public static <A extends Variable<A>> VariableDescription<A> shapeName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new VariableDescription<>(varDesc + shape, type, varDesc.name.comment);
    }

    public static VariableName shapeName(VariableName name) {
        return new VariableName(name + shape, name.comment);
    }

    private static final String calcVar = "cv" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> calcVarName(Variable<?> v, String postfix,
            Type<A> type) {
        return VariableNames.calcVarName("var" + v.getId() + Name.prefix + postfix, type, true);
    }

    // TODO remove the generated flag from here and set it to a static true.
    public static <A extends Variable<A>> VariableDescription<A> calcVarName(String name, Type<A> type,
            boolean generated) {
        return new VariableDescription<>(calcVar + name, type, generated);
    }

    public static <A extends Variable<A>> VariableDescription<A> calcVarName(String name1, String name2, Type<A> type,
            boolean generated) {
        return new VariableDescription<>(calcVar + name1 + Name.prefix + name2, type, generated);
    }

    public static <A extends Variable<A>> VariableDescription<A> calcVarName(String name1, String name2, String name3,
            Type<A> type, boolean generated) {
        return new VariableDescription<>(calcVar + name1 + Name.prefix + name2 + Name.prefix + name3, type, generated);
    }

    public static <A extends Variable<A>> VariableDescription<A> calcVarName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new VariableDescription<>(calcVar + varDesc, type, varDesc.name.comment);
    }

    public static <A extends Variable<A>> VariableDescription<A> calcVarName(VariableDescription<?> varDesc,
            String postfix, Type<A> type) {
        return new VariableDescription<>(calcVar + varDesc + Name.prefix + postfix, type, varDesc.name.comment);
    }

    public static VariableName calcVarName(VariableName name) {
        return new VariableName(calcVar + name, name.comment);
    }

    public static boolean isCalcVar(VariableDescription<?> varDesc) {
        return varDesc.name.getName().startsWith(calcVar);
    }

    private static final String scopeVar = "scopeVariable" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> scopeVarName(String name, Type<A> type) {
        return new VariableDescription<>(scopeVar + name, type, true);
    }

    private static final String distTempVar = "distributionTempVariable" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> distributionTempName(VariableName name, int id,
            Type<A> type) {
        return new VariableDescription<>(distTempVar + name + Name.prefix + id, type, true);
    }

    private static final String traceTempVar = "traceTempVariable" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> traceTempName(VariableName name, int globalId,
            int localId, Type<A> type) {
        return new VariableDescription<>(traceTempVar + name + Name.prefix + globalId + "_" + localId, type, true);
    }

    private static final String reduceTempVar = "reduceVar" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> reduceName(Variable<A> returnVar, int id) {
        VariableDescription<A> desc = returnVar.getUniqueVarDesc();
        return new VariableDescription<>(reduceTempVar + desc.name + Name.prefix + id, desc.type, true);
    }

    private static final String setFlag = "setFlag" + Name.prefix;

    public static VariableDescription<BooleanVariable> setFlagName(VariableDescription<?> varDesc) {
        return new VariableDescription<>(setFlag + varDesc, VariableType.BooleanVariable, varDesc.name.comment);
    }

    private static final String fixedFlag = "fixedFlag" + Name.prefix;

    public static VariableDescription<BooleanVariable> fixedFlagName(SampleTask<?, ?> task) {
        VariableDescription<?> varDesc = task.getUniqueVarDesc();
        return new VariableDescription<>(fixedFlag + varDesc, VariableType.BooleanVariable, varDesc.name.comment);
    }

    private static final String fixedProbFlag = "fixedProbFlag" + Name.prefix;

    public static VariableDescription<BooleanVariable> getProbabilityFixedFlag(SampleTask<?, ?> task) {
        VariableDescription<?> varDesc = task.getUniqueVarDesc();
        return new VariableDescription<>(fixedProbFlag + varDesc, VariableType.BooleanVariable, varDesc.name.comment);
    }

    private static final String index = "index" + Name.prefix;

    public static VariableDescription<IntVariable> indexName(VariableDescription<?> desc) {
        return new VariableDescription<>(index + desc.name, VariableType.IntVariable, desc.name.comment);
    }

    public static VariableDescription<IntVariable> indexName(VariableDescription<?> desc, String id) {
        return new VariableDescription<>(index + desc.name + Name.prefix + id, VariableType.IntVariable,
                desc.name.comment);
    }

    public static <A extends Variable<A>> VariableDescription<A> variableName(String alias, int id, Type<A> type,
            boolean generated) {
        if(alias != null)
            return new VariableDescription<>(alias + Name.prefix + "var" + id, type, generated);
        else
            return new VariableDescription<>(Name.prefix + "var" + id, type, generated);
    }

    private static final String forEnd = "forEnd" + Name.prefix;

    public static VariableDescription<IntVariable> parForEnd(VariableDescription<IntVariable> varDesc) {
        return new VariableDescription<>(forEnd + varDesc, varDesc.type, varDesc.name.comment);
    }

    private static final String forStart = "forStart" + Name.prefix;

    public static VariableDescription<IntVariable> parForStart(VariableDescription<IntVariable> varDesc) {
        return new VariableDescription<>(forStart + varDesc, varDesc.type, varDesc.name.comment);
    }

    private static final String distribution = "distribution" + Name.prefix;

    public static VariableDescription<ArrayVariable<DoubleVariable>> distribution(DistributionSampleTask<?, ?> sTask) {
        VariableDescription<?> varDesc = sTask.getUniqueVarDesc();
        return new VariableDescription<>(distribution + varDesc, VariableType.arrayType(VariableType.DoubleVariable),
                varDesc.name.comment);
    }

    public static VariableDescription<DoubleVariable> distributionAccumulator(RandomVariable<?, ?> rv) {
        return new VariableDescription<>(distribution + "randomVariable" + Name.prefix + rv.getId() + "accumulator",
                VariableType.DoubleVariable, false);
    }

    private static final String guard = "guard" + Name.prefix;

    public static <A extends Variable<A>> VariableDescription<A> guardName(DataflowTask<?> start, DataflowTask<?> end,
            int id, Type<A> type) {
        if(id == 1)
            return new VariableDescription<>(guard + typeToName(start.getType()) + start.id() + typeToName(end.getType()) + end.id(), type,
                    true);
        else
            return new VariableDescription<>(guard + typeToName(start.getType()) + start.id() + typeToName(end.getType()) + end.id() + id, type,
                    true);

    }

    private static String typeToName(DFType type) {
        String[] words = type.getDescription().split(" ");
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<words.length; i++) {
            if(!words[i].isEmpty()) {
                if(first) {
                    sb.append(Character.toLowerCase(words[i].charAt(0)) + words[i].substring(1));
                    first = false;
                } else {
                    sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1));
                }
            }
        }
        return sb.toString();
    }

    public static boolean isGuardVar(VariableDescription<?> varDesc) {
        return varDesc.name.getName().startsWith(guard);
    }

    public static <A extends Variable<A>> VariableDescription<A> globalGuardName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new VariableDescription<>(varDesc + Name.prefix + "global", type, true);
    }

    public static <A extends Variable<A>> VariableDescription<A> altTypeName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new VariableDescription<>(varDesc.name.getName(), type, varDesc.name.comment);
    }

    public static <V extends Variable<V>> VariableDescription<V> lambdaSubstitute(VariableDescription<V> desc, int id) {
        return new VariableDescription<>(desc + Name.prefix + id, desc.type, true);
    }

    public static <A extends Variable<A>> VariableDescription<ArrayVariable<ArrayVariable<A>>> subarrayName(int suffix,
            Type<ArrayVariable<ArrayVariable<A>>> type) {
        return new VariableDescription<>("subarray" + Name.prefix + suffix, type, true);
    }
}
