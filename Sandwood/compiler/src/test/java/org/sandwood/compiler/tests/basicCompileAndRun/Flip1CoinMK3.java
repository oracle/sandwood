/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
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
import org.sandwood.runtime.model.variables.ObservedBooleanArray;
import org.sandwood.runtime.model.variables.ObservedBooleanArrayShapeable;

public class Flip1CoinMK3 extends ModelTestSkeleton {

    private final int noFlips = 100000;

    /*
     * object Flip { class sig(var bias: Double, var flips: Array[Int])
     *
     * val model = bayes { (N: Int, a: Double, b: Double) => { val bias = Beta(a,b).sample() val flips = for (i <- 1 to
     * N) { Bernoulli(bias).sample() }
     *
     * for (i <- 1 to N) { observe(flips[i]); } } }
     */

    public Flip1CoinMK3() throws SandwoodJsonException, IOException {
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
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured",
                VariableType.arrayType(VariableType.BooleanVariable));
        IntVariable samples = flipsMeasured.length();
        samples.setAlias("samples");
        DoubleVariable bias = beta(a, b).sample();
        bias.setAlias("bias");
        ArrayVariable<BooleanVariable> flips = arrayVariable(VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        parFor(Variable.intVariable(0), samples, Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            Bernoulli bernoulli = bernoulli(bias);
            bernoulli.setAlias("bernoulli");
            flips.put(i, bernoulli.sample());
        });

        parFor(Variable.intVariable(0), samples, Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            flips.get(i).observe(flipsMeasured.get(i));
        });
        String[] constructorArgs = { "flipsMeasured" };
        return compileAPI(opts, flips, "ClassName", "", constructorArgs, "", null);
    }

    @Override
    protected void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        boolean[] list = new boolean[noFlips];
        for(int k = 0; k < noFlips; k++)
            list[k] = true;

        Field f = cls.getField("flipsMeasured");
        ObservedBooleanArray i = (ObservedBooleanArray) f.get(o);
        i.setValue(list);
    }

    @Override
    protected void initializeClassForward(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        Field f = cls.getField("flipsMeasured");
        ObservedBooleanArrayShapeable i = (ObservedBooleanArrayShapeable) f.get(o);
        i.setLength(noFlips);
    }
}
