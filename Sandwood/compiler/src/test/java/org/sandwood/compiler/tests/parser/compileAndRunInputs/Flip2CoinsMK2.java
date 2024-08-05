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

public class Flip2CoinsMK2 {

    private static final int noFlips = 100000;
    private static final boolean[][] flips;

    static {
        flips = new boolean[noFlips][2];
        for(int k = 0; k < noFlips; k++) {
            flips[k][0] = true;
            flips[k][1] = true;
            flips[++k][0] = true;
            flips[k][1] = false;
        }
    }

    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("a", 1.0);
            t.inputs.put("b", 1.0);
            t.inputs.put("flipsMeasured", flips);
            t.args = new String[] { "a", "b", "flipsMeasured" };
            t.outputNames = new String[] { "bernoulli", "flips" };
            m.put(TestType.Evidence, t);
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("a", 1.0);
            t.inputs.put("b", 1.0);
            int[] shape = new int[noFlips];
            for(int i = 0; i < noFlips; i++)
                shape[i] = 2;
            t.inputs.put("flipsMeasured", shape);
            t.shapes.add("flipsMeasured");
            t.args = new String[] { "a", "b", "flipsMeasured" };
            t.outputNames = new String[] { "bias", "flips" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("a", 1.0);
            t.inputs.put("b", 1.0);
            t.inputs.put("flipsMeasured", flips);
            t.args = new String[] { "a", "b", "flipsMeasured" };
            t.outputNames = new String[] { "bias" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
