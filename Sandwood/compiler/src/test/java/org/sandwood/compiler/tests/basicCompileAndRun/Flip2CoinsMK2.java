/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.basicCompileAndRun;

import static org.sandwood.compiler.dataflowGraph.Sandwood.compileAPI;
import static org.sandwood.compiler.dataflowGraph.Sandwood.parFor;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.arrayVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.bernoulli;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.observeArray;

import java.io.IOException;
import java.lang.reflect.Field;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.variables.ObservedObjectArray;
import org.sandwood.runtime.model.variables.ObservedObjectArrayShapeable;

public class Flip2CoinsMK2 extends ModelTestSkeleton {

    private static final int noFlips = 100000;

    /*
     * object Flip { class sig(var bias: Array[Double], var flips: Array[Int])
     *
     * val model = bayes { (N: Int, K:Int, a: Double, b: Double) => { val bias = Beta(a,b).sample(K) val flips = for (i
     * <- 1 to N) { for(j <- 1 to K) { Bernoulli(bias(j)).sample() } }
     *
     * observe(flips) } }
     */

    public Flip2CoinsMK2() throws SandwoodJsonException, IOException {
        super();
    }

    @Override
    protected String[] getOutputFields(TestType testType) {
        switch(testType) {
            case Evidence:
            case EvidenceLog: {
                return new String[] { "bernoulli", "flips" };
            }
            case Forward: {
                return new String[] { "bias", "flips" };
            }
            case Gibbs: {
                return new String[] { "bias" };
            }
            default:
                throw new SandwoodTestException("Unknown type: " + testType);
        }
    }

    @Override
    protected CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable a = doubleVariable(1.0);
        a.setAlias("a");
        DoubleVariable b = doubleVariable(1.0);
        b.setAlias("b");
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured",
                VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)));
        IntVariable samples = flipsMeasured.length();
        samples.setAlias("samples");
        IntVariable coins = flipsMeasured.get(0).length();
        coins.setAlias("coins");
        ArrayVariable<DoubleVariable> bias = beta(a, b).sample(coins);
        bias.setAlias("bias");
        ArrayVariable<ArrayVariable<BooleanVariable>> flips = arrayVariable(
                VariableType.arrayType(VariableType.BooleanVariable), samples);
        flips.setAlias("flips");
        parFor(Variable.intVariable(0), samples, Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            ArrayVariable<BooleanVariable> sample = arrayVariable(VariableType.BooleanVariable, coins);
            sample.setAlias("sample");
            parFor(Variable.intVariable(0), coins, Variable.intVariable(1), true, (j) -> {
                j.setAlias("j");
                Bernoulli bernoulli = bernoulli(bias.get(j));
                bernoulli.setAlias("bernoulli");
                sample.put(j, bernoulli.sample());
            });
            flips.put(i, sample);
        });

        flips.observe(flipsMeasured);

        String[] constructorArgs = { "flipsMeasured" };
        return compileAPI(opts, flips, "ClassName", null, constructorArgs, "", null);
    }

    @Override
    protected void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        boolean[][] list2 = new boolean[noFlips][];
        for(int k = 0; k < noFlips;) {
            boolean[] list1 = new boolean[2];
            list1[0] = true;
            list1[1] = true;
            list2[k++] = list1;

            list1 = new boolean[2];
            list1[0] = true;
            list1[1] = false;
            list2[k++] = list1;
        }

        Field f = cls.getField("flipsMeasured");
        @SuppressWarnings("unchecked")
        ObservedObjectArray<boolean[]> i = (ObservedObjectArray<boolean[]>) f.get(o);
        i.set(list2);
    }

    @Override
    protected void initializeClassForward(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        int[] shape = new int[noFlips];
        for(int i = 0; i < noFlips; i++)
            shape[i] = 2;

        Field f = cls.getField("flipsMeasured");
        @SuppressWarnings("unchecked")
        ObservedObjectArrayShapeable<boolean[], int[]> i = (ObservedObjectArrayShapeable<boolean[], int[]>) f.get(o);
        i.setShape(shape);
    }
}
