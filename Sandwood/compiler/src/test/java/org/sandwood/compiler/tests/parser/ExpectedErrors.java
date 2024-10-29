/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpectedErrors {
    public static class ErrorDesc {
        public final List<String> messages;
        public final List<String> annotatedMessages;

        public ErrorDesc(List<String> messages, List<String> annotatedMessages) {
            this.messages = messages;
            this.annotatedMessages = annotatedMessages;
        }

        public ErrorDesc(String message, String annotatedMessage) {
            this.messages = new ArrayList<>();
            messages.add(message);

            this.annotatedMessages = new ArrayList<>();
            annotatedMessages.add(annotatedMessage);
        }
    }

    static final Map<String, ErrorDesc> errors = new HashMap<>();
    static {
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "Flip1CoinArrayCopyFail.sandwood";
            String message = "Unable to perform array access on array \"bias\" as the index \"i\" is used to write to this array in a "
                    + "trace after it is used for this read. This means that the compiler cannot statically determine how "
                    + "many iterations of these loops are needed when constructing inverse operations for the model.";
            String annotatedMessage = "Error on line: 22 between columns 25 and 27\n"
                    + "Unable to perform array access on array \"bias\" as the index \"i\" is used to write to this array in a\n"
                    + "trace after it is used for this read. This means that the compiler cannot statically determine how\n"
                    + "many iterations of these loops are needed when constructing inverse operations for the model.\n"
                    + "\n"
                    + "\"        bias[i+1] = bias[i];\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "LinearRegressionFail.sandwood";
            String message = "Missing reference to index \"i\" when writing to array \"phi\". All loop indexes between the "
                    + "declaration of the array and the assignment to it must be used to ensure single assignment "
                    + "semantics are maintained.";
            String annotatedMessage = "Error on line: 25 between columns 20 and 37\n"
                    + "Missing reference to index \"i\" when writing to array \"phi\". All loop indexes between the\n"
                    + "declaration of the array and the assignment to it must be used to ensure single assignment\n"
                    + "semantics are maintained.\n"
                    + "\n"
                    + "\"                phi[j] = weights[j] * x[i][j];\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "LinearRegression2Fail.sandwood";
            String message = "";
            String annotatedMessage = "General Error\n"
                    + "Model LinearRegression2Fail should be in the directory:\n"
                    + "src/test/resources/testInputs/, not:\n"
                    + "src/test/resources/testInputs/org/sandwood/compiler/tests/parser";
            errors.put(file, new ErrorDesc(message, annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "LinearRegression3Fail.sandwood";
            String message = "";
            String annotatedMessage = "General Error\n"
                    + "Model LinearRegressionWrongNameFail should be in a file called LinearRegressionWrongNameFail.sandwood, not LinearRegression3Fail.sandwood";
            errors.put(file, new ErrorDesc(message, annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "Flip2CoinsMK13Fail.sandwood";
            String message = "Unable to write to \"flipsMeasured\" via \"source\" as it is an input and therefore a constant.";
            String annotatedMessage = "Error on line: 44 between columns 19 and 33\n"
                    + "Unable to write to \"flipsMeasured\" via \"source\" as it is an input and therefore a constant.\n"
                    + "\n"
                    + "\"            source[m] = target[m];\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "Flip2CoinsMK14Fail.sandwood";
            String message = "References to arrays may not be held in multiple different arrays. Attempt to assign the "
                    + "same array to measuredFlips and flipsMeasured.";
            String annotatedMessage = "Error on line: 38 between columns 22 and 43\n"
                    + "References to arrays may not be held in multiple different arrays. Attempt to assign the same array\n"
                    + "to measuredFlips and flipsMeasured.\n"
                    + "\n"
                    + "\"        measuredFlips[l] = flipsMeasured[l];\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "Flip2CoinsMK15Fail.sandwood";
            String message = "Public arrays cannot be placed into arrays as this would provide multiple access points to "
                    + "the array's internal state.";
            String annotatedMessage = "Error on line: 21 between columns 14 and 20\n"
                    + "Public arrays cannot be placed into arrays as this would provide multiple access points to the\n"
                    + "array's internal state.\n"
                    + "\n"
                    + "\"        flips[j] = f;\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "Flip1CoinMK11Fail.sandwood";
            String message = "There is no inverse to an \"or\" operation.";
            String annotatedMessage = "Error on line: 20 between columns 16 and 17\n"
                    + "There is no inverse to an \"or\" operation.\n"
                    + "\n"
                    + "\"        (false || flips[i]).observe(flipsMeasured[i]);\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
        {
            String file = "org" + File.separator + "sandwood" + File.separator + "compiler" + File.separator + "tests"
                    + File.separator + "parser" + File.separator + "HMMMetrics3Fail.sandwood";
            String message = "Incompatible types, type double[] cannot be assigned to an array of type int[][].";
            String annotatedMessage = "Error on line: 52 between columns 17 and 36\n"
                    + "Incompatible types, type double[] cannot be assigned to an array of type int[][].\n"
                    + "\n"
                    + "\"\t\tmetric_g[sample] = metric_1d;\"";
            errors.put(file, new ErrorDesc(message, file + "\n" + annotatedMessage + "\n"));
        }
    }

    public static boolean containsFile(String file) {
        return errors.containsKey(file);
    }

    public static ErrorDesc getErrorDesc(String file) {
        return errors.get(file);
    }
}
