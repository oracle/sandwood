/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.parser.compileAndRunInputs;

import java.util.HashMap;
import java.util.Map;

import org.sandwood.compiler.tests.parser.CompileAndRunTest.TestData;
import org.sandwood.compiler.tests.parser.CompileAndRunTest.TestType;

public class Flip1CoinMK6 {

    private static final int noFlips = 100000;
    private static final boolean[] flips1, flips2;

    static {
        flips1 = new boolean[3 * noFlips / 4];
        for(int k = 0; k < 3 * noFlips / 4; k++)
            flips1[k] = true;

        flips2 = new boolean[noFlips / 4];
        for(int k = 0; k < noFlips / 4; k++)
            flips2[k] = true;
    }

    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured1", flips1);
            t.inputs.put("flipsMeasured2", flips2);
            t.args = new String[] { "flipsMeasured1", "flipsMeasured2" };
            t.outputNames = new String[] { "bernoulli", "flips1", "flips2" };
            m.put(TestType.Evidence, t);
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            t.args = new String[] { "flipsMeasured1", "flipsMeasured2" };
            t.inputs.put("flipsMeasured1", 3 * noFlips / 4);
            t.inputs.put("flipsMeasured2", noFlips / 4);
            t.shapes.add("flipsMeasured1");
            t.shapes.add("flipsMeasured2");
            t.outputNames = new String[] { "bias", "flips1", "flips2" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured1", flips1);
            t.inputs.put("flipsMeasured2", flips2);
            t.args = new String[] { "flipsMeasured1", "flipsMeasured2" };
            t.outputNames = new String[] { "bias" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
