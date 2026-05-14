/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.benchmarking.driver.compileAndRunInputs;

import java.util.HashMap;
import java.util.Map;

import org.sandwood.benchmarking.driver.SandwoodBenchmarkDriver.TestData;
import org.sandwood.benchmarking.driver.SandwoodBenchmarkDriver.TestType;

public class Flip1CoinMK0 {
    public static Map<TestType, TestData> getInputs() {
        Map<TestType, TestData> m = new HashMap<>();
        {
            TestData t = new TestData();
            t.inputs.put("flipMeasured", true);
            t.args = new String[] { "flipMeasured" };
            t.outputNames = new String[] { "bernoulli", "flip" };
            m.put(TestType.EvidenceLog, t);
        }
        {
            TestData t = new TestData();
            t.args = new String[] {};
            t.outputNames = new String[] { "bias", "flip" };
            m.put(TestType.Forward, t);
        }
        {
            TestData t = new TestData();
            t.inputs.put("flipMeasured", true);
            t.args = new String[] { "flipMeasured" };
            t.outputNames = new String[] { "bias" };
            m.put(TestType.Gibbs, t);
        }
        return m;
    }
}
