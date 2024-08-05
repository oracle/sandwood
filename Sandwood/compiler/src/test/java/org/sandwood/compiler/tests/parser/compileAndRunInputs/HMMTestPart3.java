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

public class HMMTestPart3 {

    private static final int noFlips = 100;
    private static final boolean[] flipsMeasured;

    static {
        flipsMeasured = new boolean[noFlips];
        for(int k = 0; k < noFlips; k++)
            flipsMeasured[k] = true;
    }

    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured", flipsMeasured);
            t.args = new String[] { "flipsMeasured" };
            t.outputNames = new String[] { "m", "st", "flips", "bias" };
            m.put(TestType.Evidence, t);
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            String[] args = { "flipsMeasured" };
            t.inputs.put("flipsMeasured", flipsMeasured.length);
            t.shapes.add("flipsMeasured");
            t.args = args;
            t.outputNames = new String[] { "flips", "st", "m", "bias" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("flipsMeasured", flipsMeasured);
            t.args = new String[] { "flipsMeasured" };
            t.outputNames = new String[] { "m", "bias", "st" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
