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

public class Flip2CoinsMK1 {

    private static final int noFlips = 100000;
    private static final boolean[][] flips;

    static {
        flips = new boolean[2][];
        boolean[] list = new boolean[noFlips];
        for(int k = 0; k < noFlips; k++)
            list[k] = true;
        flips[0] = list;

        list = new boolean[noFlips];
        for(int k = 0; k < noFlips; k += 2) {
            list[k] = true;
            list[k + 1] = false;
        }
        flips[1] = list;
    }

    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured", flips);
            t.args = new String[] { "flipsMeasured" };
            t.outputNames = new String[] { "bernoulli", "flips" };
            m.put(TestType.Evidence, t);
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            int[] shape = new int[2];
            shape[0] = noFlips;
            shape[1] = noFlips;
            t.inputs.put("flipsMeasured", shape);
            t.shapes.add("flipsMeasured");
            t.args = new String[] { "flipsMeasured" };
            t.outputNames = new String[] { "bias", "flips" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured", flips);
            t.args = new String[] { "flipsMeasured" };
            t.outputNames = new String[] { "bias" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
