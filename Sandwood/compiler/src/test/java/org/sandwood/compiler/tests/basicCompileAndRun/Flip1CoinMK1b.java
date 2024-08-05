/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.basicCompileAndRun;

import static org.sandwood.compiler.dataflowGraph.Sandwood.compileAPI;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.bernoulli;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.observeArray;

import java.io.IOException;
import java.lang.reflect.Field;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.variables.ObservedBooleanArray;
import org.sandwood.runtime.model.variables.ObservedBooleanArrayShapeable;

public class Flip1CoinMK1b extends ModelTestSkeleton {

    private static final int noFlips = 100000;

    /*
     * object Flip { class sig(var bias: Double, var flips: Array[Int])
     *
     * val model = bayes { (N: Int, a: Double, b: Double) => { val bias = Beta(a,b).sample() val flips =
     * Bernoulli(bias).sample(N)
     *
     * observe(flips) } }
     */

    public Flip1CoinMK1b() throws SandwoodJsonException, IOException {
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
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured",
                VariableType.arrayType(VariableType.BooleanVariable));
        IntVariable samples = flipsMeasured.length();
        samples.setAlias("samples");
        DoubleVariable a = doubleVariable(1.0);
        a.setAlias("a");
        DoubleVariable b = doubleVariable(1.0);
        b.setAlias("b");
        DoubleVariable bias = beta(a, b).sample();
        bias.setAlias("bias");
        Bernoulli bernoulli = bernoulli(bias);
        bernoulli.setAlias("bernoulli");
        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples);
        flips.setAlias("flips");
        flips.observe(flipsMeasured);
        String[] constructorArgs = { "flipsMeasured" };
        return compileAPI(opts, flips, "ClassName", null, constructorArgs, "", null);
    }

    @Override
    protected void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        boolean[] list = new boolean[noFlips];
        for(int k = 0; k < noFlips; k++)
            list[k] = true;

        Field f = cls.getField("flipsMeasured");
        ObservedBooleanArray i = (ObservedBooleanArray) f.get(o);
        i.set(list);
    }

    @Override
    protected void initializeClassForward(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        Field f = cls.getField("flipsMeasured");
        ObservedBooleanArrayShapeable i = (ObservedBooleanArrayShapeable) f.get(o);
        i.setLength(noFlips);
    }
}
