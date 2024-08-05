/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.common.tests.util;

import java.lang.Runtime.Version;
import java.util.List;
import java.util.Optional;

/**
 * A collection of static methods for testing if the runtime can be used to test against numeric values. The test data
 * sets are generated using the RNG from 17.0.2 onwards and an x86 architecture.
 */
public class TestRuntime {

    private TestRuntime() {}

    /**
     * Test if both the RNG and the architecture are suitable for testing.
     * 
     * @return Return true if tests should proceed.
     */
    public static boolean checkEnvironment() {
        return checkJVM() && checkProcessor();
    }

    /**
     * Test if the architecture is suitable for testing.
     * 
     * @return Return true if tests should proceed.
     */
    public static boolean checkProcessor() {
        String arch = System.getProperty("os.arch");
        if(arch.contains("x86") || arch.contains("amd64"))
            return true;
        else {
            System.err.println("Architecture type found to be: " + arch + ".\nNumeric tests are not supported "
                    + "on non x86 architectures due to potential differences in the floating point "
                    + "calculations. This does not affect the quality of the models generated or executed with "
                    + "Sandwood, just the ability to use the numeric unit tests to check the correctness of "
                    + "modifications to Sandwood. As such development of the Sandwood Compiler and Runtime on "
                    + "non x86 architectures is not recommended.");
            return false;
        }
    }

    /**
     * Test if the RNG is suitable for testing.
     * 
     * @return Return true if tests should proceed.
     */
    public static boolean checkJVM() {
        Version version = Runtime.version();
        List<Integer> numbers = version.version();
        int majorVersion = numbers.get(0);
        if(majorVersion == 17) {
            System.err.println("JVM  version " + version + " detected.\n"
                    + "Skipping comparison to generated results tests due to bug JDK-8273056 "
                    + "and bug JDK-8282551 resulting in different random\n number outputs. It is "
                    + "not recommended to test modifications to Sandwood with this JVM as result\n"
                    + "correctness cannot be tested. Upgrading to Java 18.0.2 or above is recommended.");
            return false;
        }

        if(majorVersion == 18) {
            if(numbers.size() < 3) {
                Optional<Integer> build = version.build();
                if(build.isEmpty()) {
                    System.err.println("JVM  version " + version + " detected.\n"
                            + "Unable to determine the point release so skipping comparison to generated "
                            + "results tests due to bug JDK-8282551 resulting in different random\n number "
                            + "outputs. It is not recommended to test modifications to Sandwood with this "
                            + "JVM as result\ncorrectness cannot be tested. Upgrading to Java 18.0.2 or above "
                            + "is recommended.");
                    return false;
                } else if(build.get() < 9)
                    System.err.println("JVM  version " + version + " detected.\n"
                            + "Skipping comparison to generated results tests due to bug JDK-8282551 "
                            + "resulting in different random\n number outputs. It is not recommended to "
                            + "test modifications to Sandwood with this JVM as result\ncorrectness cannot "
                            + "be tested. Upgrading to Java 18.0.2 or above is recommended.");
                return false;
            } else {
                int minorVersion = numbers.get(1);
                int buildVersion = numbers.get(2);
                if(minorVersion == 0 && buildVersion < 2) {
                    System.err.println("JVM  version " + version + " detected.\n"
                            + "Skipping comparison to generated results tests due to bug JDK-8282551 "
                            + "resulting in different random\n number outputs. It is not recommended to "
                            + "test modifications to Sandwood with this JVM as result\ncorrectness cannot "
                            + "be tested. Upgrading to Java 18.0.2 or above is recommended.");
                    return false;
                }
            }
        }
        return true;
    }
}
