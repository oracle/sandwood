/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.dag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sandwood.compiler.dataflowGraph.Sandwood.parFor;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.intVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.observeArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.trees.irTree.IRLoad;
import org.sandwood.compiler.trees.irTree.IRTree.IRTreeType;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class VariableMinMax {

    private interface ConstructVar {
        NumberVariable<?> getVariable();
    }

    private static class Wrapper<A> {
        A value;
    }

    private final static int noTests;
    private final static List<ConstructVar> variableConstructor;
    private final static List<String> min, minName, max, maxName;

    static {
        variableConstructor = new ArrayList<>();
        min = new ArrayList<>();
        minName = new ArrayList<>();
        max = new ArrayList<>();
        maxName = new ArrayList<>();

        {
            variableConstructor.add(() -> {
                ArrayVariable<IntVariable> a = observeArray("a", VariableType.arrayType(VariableType.IntVariable));
                return a.get(Variable.intVariable(0));
            });
            min.add("// Calculate the minimum size of the inputs to array a.\nint cv$var5$min = 2147483647;\n"
                    + "cv$var5$min = Math.min(cv$var5$min, a[0]);\n");
            max.add("// Calculate the maximum size of the inputs to array a.\nint cv$var5$max = -2147483648;\n"
                    + "cv$var5$max = Math.max(cv$var5$max, a[0]);\n");
            minName.add("cv$var5$min");
            maxName.add("cv$var5$max");
        }

        {
            variableConstructor.add(() -> {
                ArrayVariable<DoubleVariable> a = observeArray("a",
                        VariableType.arrayType(VariableType.DoubleVariable));
                return a.get(Variable.intVariable(0));
            });
            min.add("// Calculate the minimum size of the inputs to array a.\ndouble cv$var5$min = Double.POSITIVE_INFINITY;\n"
                    + "cv$var5$min = Math.min(cv$var5$min, a[0]);\n");
            max.add("// Calculate the maximum size of the inputs to array a.\ndouble cv$var5$max = Double.NEGATIVE_INFINITY;\n"
                    + "cv$var5$max = Math.max(cv$var5$max, a[0]);\n");
            minName.add("cv$var5$min");
            maxName.add("cv$var5$max");
        }
        {
            variableConstructor.add(() -> {
                ArrayVariable<IntVariable> a = observeArray("a", VariableType.arrayType(VariableType.IntVariable));
                Wrapper<IntVariable> v = new Wrapper<>();
                parFor(intVariable(0), a.length(), intVariable(1), true, (i) -> {
                    i.setAlias("i");
                    v.value = a.get(i);
                });
                return v.value;
            });
            min.add("// Calculate the minimum size of the inputs to array a.\nint cv$var8$min = 2147483647;\n"
                    + "for(int i = 0; i < a.length; i += 1)\n\tcv$var8$min = Math.min(cv$var8$min, a[i]);\n");
            max.add("// Calculate the maximum size of the inputs to array a.\nint cv$var8$max = -2147483648;\n"
                    + "for(int i = 0; i < a.length; i += 1)\n\tcv$var8$max = Math.max(cv$var8$max, a[i]);\n");
            minName.add("cv$var8$min");
            maxName.add("cv$var8$max");
        }
        {
            variableConstructor.add(() -> {
                ArrayVariable<DoubleVariable> a = observeArray("a",
                        VariableType.arrayType(VariableType.DoubleVariable));
                Wrapper<DoubleVariable> v = new Wrapper<>();
                parFor(intVariable(0), a.length(), intVariable(1), true, (i) -> {
                    i.setAlias("i");
                    v.value = a.get(i);
                });
                return v.value;
            });
            min.add("// Calculate the minimum size of the inputs to array a.\ndouble cv$var8$min = Double.POSITIVE_INFINITY;\n"
                    + "for(int i = 0; i < a.length; i += 1)\n\tcv$var8$min = Math.min(cv$var8$min, a[i]);\n");
            max.add("// Calculate the maximum size of the inputs to array a.\ndouble cv$var8$max = Double.NEGATIVE_INFINITY;\n"
                    + "for(int i = 0; i < a.length; i += 1)\n\tcv$var8$max = Math.max(cv$var8$max, a[i]);\n");
            minName.add("cv$var8$min");
            maxName.add("cv$var8$max");
        }
        {
            variableConstructor.add(() -> {
                ArrayVariable<ArrayVariable<ArrayVariable<IntVariable>>> a = observeArray("a", VariableType
                        .arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable))));
                Wrapper<IntVariable> v = new Wrapper<>();
                parFor(intVariable(0), a.length(), intVariable(3), true, (i) -> {
                    i.setAlias("i");
                    ArrayVariable<IntVariable> ai0 = a.get(i).get(Variable.intVariable(0));

                    parFor(intVariable(0), ai0.length(), intVariable(2), true, (j) -> {
                        j.setAlias("j");
                        v.value = ai0.get(j);
                    });
                });
                return v.value;
            });
            min.add("// Calculate the minimum size of the inputs to array var12.\nint cv$var19$min = 2147483647;\n"
                    + "for(int i = 0; i < a.length; i += 3) {\n\tint[][] cv$var19$minArray0 = a[i];\n"
                    + "\tint[] cv$var19$minArray1 = cv$var19$minArray0[0];\n"
                    + "\tfor(int j = 0; j < a[i][0].length; j += 2)\n"
                    + "\t\tcv$var19$min = Math.min(cv$var19$min, cv$var19$minArray1[j]);\n}\n");
            max.add("// Calculate the maximum size of the inputs to array var12.\n"
                    + "int cv$var19$max = -2147483648;\nfor(int i = 0; i < a.length; i += 3) {\n"
                    + "\tint[][] cv$var19$maxArray0 = a[i];\n\tint[] cv$var19$maxArray1 = cv$var19$maxArray0[0];\n"
                    + "\tfor(int j = 0; j < a[i][0].length; j += 2)\n"
                    + "\t\tcv$var19$max = Math.max(cv$var19$max, cv$var19$maxArray1[j]);\n}\n");
            minName.add("cv$var19$min");
            maxName.add("cv$var19$max");
        }
        {
            variableConstructor.add(() -> {
                ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a = observeArray("a", VariableType
                        .arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable))));
                Wrapper<DoubleVariable> v = new Wrapper<>();
                parFor(intVariable(0), a.length(), intVariable(3), true, (i) -> {
                    i.setAlias("i");
                    ArrayVariable<ArrayVariable<DoubleVariable>> ai = a.get(i);

                    parFor(intVariable(0), ai.length(), intVariable(2), true, (j) -> {
                        j.setAlias("j");
                        v.value = ai.get(j).get(Variable.intVariable(0));
                    });
                });
                return v.value;
            });
            min.add("// Calculate the minimum size of the inputs to array var16.\n"
                    + "double cv$var18$min = Double.POSITIVE_INFINITY;\nfor(int i = 0; i < a.length; i += 3) {\n"
                    + "\tdouble[][] cv$var18$minArray0 = a[i];\n\tfor(int j = 0; j < a[i].length; j += 2) {\n"
                    + "\t\tdouble[] cv$var18$minArray1 = cv$var18$minArray0[j];\n"
                    + "\t\tcv$var18$min = Math.min(cv$var18$min, cv$var18$minArray1[0]);\n\t}\n" + "}\n");
            max.add("// Calculate the maximum size of the inputs to array var16.\n"
                    + "double cv$var18$max = Double.NEGATIVE_INFINITY;\nfor(int i = 0; i < a.length; i += 3) {\n"
                    + "\tdouble[][] cv$var18$maxArray0 = a[i];\n\tfor(int j = 0; j < a[i].length; j += 2) {\n"
                    + "\t\tdouble[] cv$var18$maxArray1 = cv$var18$maxArray0[j];\n"
                    + "\t\tcv$var18$max = Math.max(cv$var18$max, cv$var18$maxArray1[0]);\n" + "\t}\n}\n");
            minName.add("cv$var18$min");
            maxName.add("cv$var18$max");
        }

        noTests = variableConstructor.size();
    }

    static Stream<Integer> getIndex() {
        Integer[] out = new Integer[noTests];
        for(int i = 0; i < noTests; i++)
            out[i] = i;
        return Stream.of(out);
    }

    @ParameterizedTest
    @MethodSource("getIndex")
    @DisplayName("Test propagation of known values.")
    void test(int i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        extracted(i, true);
        extracted(i, false);
    }

    private void extracted(int i, boolean testMin) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
        NumberVariable<?> v = variableConstructor.get(i).getVariable();
        Traces traces = TracesImplementation.getTraces(v);
        CompilationContext compilationCtx = new CompilationContext(new CompilationOptions(), traces,
                ExecutionType.SingleThreadCPU);
        IRTreeReturn<?> value = testMin ? v.getMin(compilationCtx) : v.getMax(compilationCtx);
        IRTreeVoid generator = compilationCtx.getOutermostScopeTree();
        // Reset the compilation context to catch any left substitutions etc.
        compilationCtx.initialize();

        assertEquals(v.getType(), value.getOutputType());
        assertEquals(IRTreeType.LOAD, value.type);
        assertEquals((testMin ? minName : maxName).get(i), ((IRLoad<?>) value).varDesc.name.toString());

        assertEquals((testMin ? min : max).get(i), generator.toString());
    }

    // Clean up after the last test.
    @AfterAll
    static void resetState() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        CompilerState.reset();
    }
}
