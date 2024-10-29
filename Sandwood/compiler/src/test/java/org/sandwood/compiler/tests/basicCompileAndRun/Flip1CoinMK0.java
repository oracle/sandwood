/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.basicCompileAndRun;

import static org.sandwood.compiler.dataflowGraph.Sandwood.compileAPI;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.bernoulli;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.observeBoolean;

import java.io.IOException;
import java.lang.reflect.Field;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.variables.ObservedBoolean;

public class Flip1CoinMK0 extends ModelTestSkeleton {

    /*
     * object Flip { class sig(var bias: Double, var flip: Int)
     *
     * val model = bayes { (N: Int, a: Double, b: Double) => { val bias = Beta(a,b).sample() val flip =
     * Bernoulli(bias).sample() observe(flip) } }
     */

    public Flip1CoinMK0() throws SandwoodJsonException, IOException {
        forwardInitialisationRequired = false;
    }

    @Override
    protected String[] getOutputFields(TestType testType) {
        switch(testType) {
            case Evidence:
            case EvidenceLog: {
                return new String[] { "bernoulli", "flip" };
            }
            case Forward: {
                return new String[] { "bias", "flip" };
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
        DoubleVariable bias = beta(a, b).sample();
        bias.setAlias("bias");
        Bernoulli bernoulli = bernoulli(bias);
        bernoulli.setAlias("bernoulli");
        BooleanVariable flip = bernoulli.sample();
        flip.setAlias("flip");
        BooleanVariable flipMeasured = observeBoolean("flipMeasured");
        flip.observe(flipMeasured);
        String[] constructorArgs = { "flipMeasured" };
        return compileAPI(opts, flip, "ClassName", "", constructorArgs, "", null);
    }

    @Override
    protected void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException {
        Field f = cls.getField("flipMeasured");
        ObservedBoolean i = (ObservedBoolean) f.get(o);
        i.set(true);
    }

    @Override
    protected void initializeClassForward(Class<?> cls, Object o) {}
}
