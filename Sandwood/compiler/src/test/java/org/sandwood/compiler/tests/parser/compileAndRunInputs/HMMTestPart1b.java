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

public class HMMTestPart1b {
    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("flipMeasured", true);
            t.args = new String[] { "flipMeasured" };
            t.outputNames = new String[] { "m", "st", "flip", "bias" };
            m.put(TestType.Evidence, t);
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            t.args = new String[] {};
            t.outputNames = new String[] { "flip", "st", "m", "bias" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("flipMeasured", true);
            t.args = new String[] { "flipMeasured" };
            t.outputNames = new String[] { "m", "bias", "st" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
