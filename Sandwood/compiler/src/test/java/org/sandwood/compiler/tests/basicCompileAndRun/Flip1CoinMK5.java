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
import static org.sandwood.compiler.dataflowGraph.variables.Variable.bernoulli;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;

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

public class Flip1CoinMK5 extends ModelTestSkeleton {

    public Flip1CoinMK5() throws SandwoodJsonException, IOException {
        super();
    }

    private static final int noFlips = 100000;

    /*
     * object Flip { class sig(var bias: Double, var flips: Array[Int])
     *
     * val model = bayes { (N1: Int, N2: Int, a: Double, b: Double) => { val bias = Beta(a,b).sample() val flips1 =
     * Bernoulli(bias).sample(N1) val flips2 = Bernoulli(bias).sample(N2)
     *
     * for (i <- 1 to N1) { observe(flips1[i]); }
     *
     * for (i <- 1 to N2) { observe(flips2[i]); } } }
     */

    @Override
    protected String[] getOutputFields(TestType testType) {
        switch(testType) {
            case Evidence:
            case EvidenceLog: {
                return new String[] { "bernoulli1", "bernoulli2", "flips1", "flips2" };
            }
            case Forward: {
                return new String[] { "bias", "flips1", "flips2" };
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
        ArrayVariable<BooleanVariable> flipsMeasured1 = Variable.observeArray("flipsMeasured1",
                VariableType.arrayType(VariableType.BooleanVariable));
        ArrayVariable<BooleanVariable> flipsMeasured2 = Variable.observeArray("flipsMeasured2",
                VariableType.arrayType(VariableType.BooleanVariable));
        IntVariable samples1 = flipsMeasured1.length();
        samples1.setAlias("samples1");
        IntVariable samples2 = flipsMeasured2.length();
        samples2.setAlias("samples2");
        DoubleVariable bias = beta(a, b).sample();
        bias.setAlias("bias");
        Bernoulli bernoulli1 = bernoulli(bias);
        bernoulli1.setAlias("bernoulli1");
        ArrayVariable<BooleanVariable> flips1 = bernoulli1.sample(samples1);
        flips1.setAlias("flips1");
        Bernoulli bernoulli2 = bernoulli(bias);
        bernoulli2.setAlias("bernoulli2");
        ArrayVariable<BooleanVariable> flips2 = bernoulli2.sample(samples2);
        flips2.setAlias("flips2");

        parFor(Variable.intVariable(0), samples1, Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            flips1.get(i).observe(flipsMeasured1.get(i));
        });

        parFor(Variable.intVariable(0), samples2, Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            flips2.get(i).observe(flipsMeasured2.get(i));
        });

        String[] constructorArgs = { "flipsMeasured1", "flipsMeasured2" };
        return compileAPI(opts, flips1, "ClassName", "", constructorArgs, "", null);
    }

    @Override
    protected void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        boolean[] list1 = new boolean[3 * noFlips / 4];
        for(int k = 0; k < 3 * noFlips / 4; k++)
            list1[k] = true;

        boolean[] list2 = new boolean[noFlips / 4];
        for(int k = 0; k < noFlips / 4; k++)
            list2[k] = true;

        Field f = cls.getField("flipsMeasured1");
        ObservedBooleanArray i = (ObservedBooleanArray) f.get(o);
        i.set(list1);

        f = cls.getField("flipsMeasured2");
        i = (ObservedBooleanArray) f.get(o);
        i.set(list2);
    }

    @Override
    protected void initializeClassForward(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        Field f = cls.getField("flipsMeasured1");
        ObservedBooleanArrayShapeable i = (ObservedBooleanArrayShapeable) f.get(o);
        i.setLength(3 * noFlips / 4);

        f = cls.getField("flipsMeasured2");
        i = (ObservedBooleanArrayShapeable) f.get(o);
        i.setLength(noFlips / 4);
    }
}
