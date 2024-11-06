/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

public enum DFType {
    ADDITION("Addition"),
    AND("And"),
    ARRAY_CONSTRUCTOR("Array Constructor"),
    BERNOULLI("Bernoulli"),
    BETA("Beta"),
    BINOMIAL("Binomial"),
    CATEGORICAL("Categorical"),
    CAUCHY("Cauchy"),
    CONSTANT_BOOLEAN("Constant Boolean"),
    CONSTANT_DOUBLE("Constant Double"),
    CONSTANT_INT("Constant Int"),
    CONSTRUCT_INPUT("Construct an input."),
    COPY("Copy"),
    DIRICHLET("Dirichlet"),
    DIVISION("Division"),
    DOUBLE_TO_INT_CAST("Double to int cast"),
    EQ("Equals"),
    EXP("Exp"),
    EXPONENTIAL("Exponential"),
    GAMMA("Gamma"),
    GAUSSIAN("Gaussian"),
    GET("Get"),
    GET_LENGTH("Length"),
    HALF_CAUCHY("Half Cauchy"),
    IF_ASSIGNMENT("If"),
    INT_TO_DOUBLE_CAST("Int to double cast"),
    INVERSE_GAMMA("Inverse Gamma"),
    IS_NAN("Is NaN"),
    LESS_THAN("Less Than"),
    LESS_THAN_EQUAL("Less Than Equal"),
    LOG("Log"),
    MAX("Max"),
    MULTINOMIAL("Multinomial"),
    MULTIPLICATION("Multiplication"),
    NAMED_VARIABLE("Named variable constructor for use in auto generation of code phase only."),
    NEGATE("Negate"),
    NEGATE_BOOLEAN("Negate boolean"),
    OBSERVE_VARIABLE("Observation variable"),
    OR("Or"),
    FOR("For"),
    PAR_FOR("Parallel for"),
    POISSON("Poisson"),
    PUT("Put"),
    REDUCTION_RETURN("Reduce return task"),
    REDUCE_INPUT("Reduce input"),
    REMAINDER("Remainder"),
    SIGMOID("Sigmoid"),
    SAMPLE("Sample"),
    STEPPING_RANGE("Stepping range"),
    STUDENT_T("StudentT"),
    SUBTRACTION("Subtraction"),
    TRUNCATED_GAUSSIAN("Truncated Gaussian"),
    UNIFORM("Uniform");

    private final String description;

    DFType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // TODO Construct an task to Sandwood converter and make the descriptions
    // uniform and clearer. Very low priority.
    @Override
    public String toString() {
        return description;
    }
}
