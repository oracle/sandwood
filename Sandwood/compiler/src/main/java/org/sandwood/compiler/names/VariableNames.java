/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.GlobalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.ScratchVariableDescription;
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

public final class VariableNames {
    private VariableNames() {}

    public static <A extends Variable<A>> GlobalVariableDescription<A> internalName(VariableDescription<A> varDesc) {
        return new GlobalVariableDescription<>(Name.prefix + varDesc, varDesc.type, varDesc.name.comment);
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

    public static GlobalVariableDescription<RandomNumberGenerator> rngName() {
        return new GlobalVariableDescription<>(rng, VariableType.RNG, true);
    }

    public static LocalVariableDescription<RandomNumberGenerator> rngName(int depth) {
        assert (depth > 0);
        return new LocalVariableDescription<>(rng + depth, VariableType.RNG, true);
    }

    private static final String threadId = "threadID" + Name.prefix;

    public static LocalVariableDescription<IntVariable> threadIdName(VariableName name) {
        return new LocalVariableDescription<>(threadId + name, VariableType.IntVariable, name.comment);
    }

    private static final String logProbability = "logProbability" + Name.prefix;

    public static GlobalVariableDescription<DoubleVariable> logProbabilityName(VariableDescription<?> varDesc) {
        return new GlobalVariableDescription<>(logProbability + varDesc, VariableType.DoubleVariable,
                varDesc.name.comment);
    }

    public static GlobalVariableDescription<DoubleVariable> logProbabilityName(VariableName name) {
        return new GlobalVariableDescription<>(logProbability + name, VariableType.DoubleVariable, name.comment);
    }

    private static final String fixedSampleProbability = "fixedSampleDistProbability" + Name.prefix;

    public static VariableName fixedSampleProbabilityName(VariableName name) {
        return new VariableName(fixedSampleProbability + name, name.comment);
    }

    private static final String lengthPrefix = "length" + Name.prefix;

    public static boolean isLengthName(VariableName name) {
        return name.getName().startsWith(lengthPrefix);
    }

    public static String lengthName(String varName) {
        return lengthPrefix + varName;
    }

    public static VariableName lengthName(VariableName name) {
        return new VariableName(lengthPrefix + name, name.comment);
    }

    private static final String lengthCVPrefix = "lengthCV" + Name.prefix;

    public static LocalVariableDescription<IntVariable> lengthCVName(VariableName name, int taskId, int tag) {
        return new LocalVariableDescription<>(lengthCVPrefix + name + Name.prefix + taskId + "_" + tag,
                VariableType.IntVariable, true);
    }

    private static final String shape = "Shape";

    public static VariableName shapeName(VariableName name) {
        return new VariableName(name + shape, name.comment);
    }

    private static final String calcVar = "cv" + Name.prefix;

    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(Variable<?> v, String postfix,
            Type<A> type) {
        return localCalcVarName("var" + v.getId() + Name.prefix + postfix, type, true);
    }

    // TODO remove the generated flag from here and replace it with an enumeration so that it is clear what is being
    // set. This will encourage it to be used correctly.
    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(String name, Type<A> type,
            boolean generated) {
        return new LocalVariableDescription<>(calcVar + name, type, generated);
    }

    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(String name1, String name2,
            Type<A> type, boolean generated) {
        return new LocalVariableDescription<>(calcVar + name1 + Name.prefix + name2, type, generated);
    }

    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(String name1, String name2,
            String name3, Type<A> type, boolean generated) {
        return new LocalVariableDescription<>(calcVar + name1 + Name.prefix + name2 + Name.prefix + name3, type,
                generated);
    }

    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new LocalVariableDescription<>(calcVar + varDesc, type, varDesc.name.comment);
    }

    public static <A extends Variable<A>> LocalVariableDescription<A> localCalcVarName(VariableDescription<?> varDesc,
            String postfix, Type<A> type) {
        return new LocalVariableDescription<>(calcVar + varDesc + Name.prefix + postfix, type, varDesc.name.comment);
    }

    public static <A extends Variable<A>> ScratchVariableDescription<A> globalScratchVarName(Variable<?> v, String postfix,
            Type<A> type) {
        return globalScratchVarName("var" + v.getId() + Name.prefix + postfix, type, true);
    }

    public static <A extends Variable<A>> ScratchVariableDescription<A> globalScratchVarName(String name, Type<A> type,
            boolean generated) {
        return new ScratchVariableDescription<>(calcVar + name, type, generated);
    }

    public static <A extends Variable<A>> ScratchVariableDescription<A> globalScratchVarName(String name1, String name2,
            Type<A> type, boolean generated) {
        return new ScratchVariableDescription<>(calcVar + name1 + Name.prefix + name2, type, generated);
    }

    public static VariableName calcVarName(VariableName name) {
        return new VariableName(calcVar + name, name.comment);
    }

    public static boolean isCalcVar(VariableDescription<?> varDesc) {
        return varDesc.name.getName().startsWith(calcVar);
    }

    private static final String scopeVar = "scopeVariable" + Name.prefix;

    public static <A extends Variable<A>> LocalVariableDescription<A> scopeVarName(String name, Type<A> type) {
        return new LocalVariableDescription<>(scopeVar + name, type, true);
    }

    private static final String distTempVar = "distributionTempVariable" + Name.prefix;

    public static <A extends Variable<A>> LocalVariableDescription<A> distributionTempName(VariableName name, int id,
            Type<A> type) {
        return new LocalVariableDescription<>(distTempVar + name + Name.prefix + id, type, true);
    }

    private static final String traceTempVar = "traceTempVariable" + Name.prefix;

    public static <A extends Variable<A>> LocalVariableDescription<A> traceTempName(VariableName name, int globalId,
            int localId, Type<A> type) {
        return new LocalVariableDescription<>(traceTempVar + name + Name.prefix + globalId + "_" + localId, type, true);
    }

    private static final String reduceTempVar = "reduceVar" + Name.prefix;

    public static <A extends Variable<A>> LocalVariableDescription<A> reduceName(Variable<A> returnVar, int id) {
        VariableDescription<A> desc = returnVar.getUniqueVarDesc();
        return new LocalVariableDescription<>(reduceTempVar + desc.name + Name.prefix + id, desc.type, true);
    }

    private static final String fixedFlag = "fixedFlag" + Name.prefix;

    public static GlobalVariableDescription<BooleanVariable> fixedFlagName(SampleTask<?, ?> task) {
        VariableName sampleName = task.getSampleName();
        return new GlobalVariableDescription<>(fixedFlag + sampleName, VariableType.BooleanVariable,
                sampleName.comment);
    }

    /**
     * Prefix for a flag to mark if a sample task could have been constrained either by a fixed result or an observed
     * result somewhere in the model.
     */
    private static final String constrainedFlag = "constrainedFlag" + Name.prefix;

    public static GlobalVariableDescription<BooleanVariable> constrainedFlagName(SampleTask<?, ?> task) {
        VariableName sampleName = task.getSampleName();
        return new GlobalVariableDescription<BooleanVariable>(constrainedFlag + sampleName,
                VariableType.BooleanVariable, false);
    }

    private static final String fixedProbFlag = "fixedProbFlag" + Name.prefix;

    public static GlobalVariableDescription<BooleanVariable> getProbabilityFixedFlag(SampleTask<?, ?> task) {
        VariableName sampleName = task.getSampleName();
        return new GlobalVariableDescription<>(fixedProbFlag + sampleName, VariableType.BooleanVariable,
                sampleName.comment);
    }

    private static final String index = "index" + Name.prefix;

    public static LocalVariableDescription<IntVariable> indexName(VariableDescription<?> desc) {
        return new LocalVariableDescription<>(index + desc.name, VariableType.IntVariable, desc.name.comment);
    }

    public static LocalVariableDescription<IntVariable> indexName(VariableDescription<?> desc, String id) {
        return new LocalVariableDescription<>(index + desc.name + Name.prefix + id, VariableType.IntVariable,
                desc.name.comment);
    }

    public static LocalVariableDescription<IntVariable> indexName(VariableName name, String id) {
        return new LocalVariableDescription<>(index + name + Name.prefix + id, VariableType.IntVariable, name.comment);
    }

    public static <A extends Variable<A>> LocalVariableDescription<A> localVariableName(String alias, int id,
            Type<A> type, boolean generated) {
        if(alias == null)
            return new LocalVariableDescription<>(Name.prefix + "var" + id, type, generated);
        else
            return new LocalVariableDescription<>(alias + Name.prefix + "var" + id, type, generated);
    }

    public static <A extends Variable<A>> GlobalVariableDescription<A> globalVariableName(String alias, int id,
            Type<A> type) {
        assert alias != null;
        return new GlobalVariableDescription<>(alias + Name.prefix + "var" + id, type, false);
    }

    private static final String forEnd = "forEnd" + Name.prefix;

    public static LocalVariableDescription<IntVariable> parForEnd(VariableDescription<IntVariable> varDesc) {
        return new LocalVariableDescription<>(forEnd + varDesc, varDesc.type, varDesc.name.comment);
    }

    private static final String forStart = "forStart" + Name.prefix;

    public static LocalVariableDescription<IntVariable> parForStart(VariableDescription<IntVariable> varDesc) {
        return new LocalVariableDescription<>(forStart + varDesc, varDesc.type, varDesc.name.comment);
    }

    private static final String distribution = "distribution" + Name.prefix;

    public static GlobalVariableDescription<ArrayVariable<DoubleVariable>> distribution(
            DistributionSampleTask<?, ?> sTask) {
        VariableName sampleName = sTask.getSampleName();
        return new GlobalVariableDescription<>(distribution + sampleName,
                VariableType.arrayType(VariableType.DoubleVariable), sampleName.comment);
    }

    public static LocalVariableDescription<DoubleVariable> distributionAccumulator(RandomVariable<?, ?> rv) {
        return new LocalVariableDescription<>(
                distribution + "randomVariable" + Name.prefix + rv.getId() + "accumulator", VariableType.DoubleVariable,
                false);
    }

    private static final String guard = "guard" + Name.prefix;

    public static LocalVariableDescription<BooleanVariable> guardName(DataflowTask<?> start, DataflowTask<?> end,
            int id) {
        if(id == 1)
            return new LocalVariableDescription<>(
                    guard + typeToName(start.getType()) + start.id() + typeToName(end.getType()) + end.id(),
                    VariableType.BooleanVariable, true);
        else
            return new LocalVariableDescription<>(
                    guard + typeToName(start.getType()) + start.id() + typeToName(end.getType()) + end.id() + id,
                    VariableType.BooleanVariable, true);

    }

    private static String typeToName(DFType type) {
        String[] words = type.getDescription().split(" ");
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
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

    public static <A extends Variable<A>> ScratchVariableDescription<A> globalGuardName(VariableDescription<?> varDesc,
            Type<A> type) {
        return new ScratchVariableDescription<>(varDesc + Name.prefix + "global", type, true);
    }

    public static <V extends Variable<V>> LocalVariableDescription<V> lambdaSubstitute(VariableDescription<V> desc,
            int id) {
        return new LocalVariableDescription<>(desc + Name.prefix + id, desc.type, true);
    }

    public static <V extends Variable<V>> LocalVariableDescription<V> scopedCopySubstitute(Variable<V> v, int id) {
        VariableDescription<V> desc = v.getUniqueVarDesc();
        return new LocalVariableDescription<>("sc" + Name.prefix + desc + Name.prefix + id, desc.type, true);
    }

    public static <A extends Variable<A>> LocalVariableDescription<ArrayVariable<ArrayVariable<A>>> subarrayName(
            int suffix, Type<ArrayVariable<ArrayVariable<A>>> type) {
        return new LocalVariableDescription<>("subarray" + Name.prefix + suffix, type, true);
    }

    public static LocalVariableDescription<BooleanVariable> observedGuard(Variable<?> v) {
        return new LocalVariableDescription<>("observationGuard" + Name.prefix + v.getUniqueVarDesc().name,
                VariableType.BooleanVariable, true);
    }

    private static final LocalVariableDescription<BooleanVariable> allocatedFlag = new LocalVariableDescription<>(
            "allocated" + Name.prefix, VariableType.BooleanVariable, true);

    public static LocalVariableDescription<BooleanVariable> allocatedFlag() {
        return allocatedFlag;
    }

    public static <A extends Variable<A>> GlobalVariableDescription<A> makeGlobal(VariableDescription<A> varDesc) {
        return new GlobalVariableDescription<A>(varDesc.name, varDesc.type);
    }
}
