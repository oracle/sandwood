/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Binomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Cauchy;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Exponential;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.HalfCauchy;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Multinomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Poisson;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.StudentT;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Uniform;
import org.sandwood.compiler.dataflowGraph.variables.rng.RandomNumberGenerator;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.MissingFeatureException;

public class VariableType {

    // A reverse lookup used when converting from the java like language to api
    // calls.
    private static final Map<String, Type<?>> apiLookup = new HashMap<>();

    /**
     * Method for getting a Type instance from a java type. This is used in the parsing of base types.
     * 
     * @param typeName The string defining the type.
     * @return
     */
    public static Type<?> getTypeFromJavaType(String typeName) {
        return apiLookup.get(typeName);
    }

    /**
     * Interface that all types must implement.
     *
     * @param <A>
     */
    public interface Type<A extends Variable<A>> {
        /**
         * Method to determine if this type is a member of the family of array types. All other types are represented by
         * singleton objects and so do not need this check.
         * 
         * @return true if this type is an array.
         */
        boolean isArray();

        /**
         * Get the singleton type that is used to identify this type.
         * 
         * @return
         */
        Type<?> getTypeSingleton();

        /**
         * Get the base type of this array, for example int[][][] would return int.
         * 
         * @return
         */
        Type<?> getBaseType();

        /**
         * What is the dimensionality of this type. I.E. how many [] are there after the base type.
         * 
         * @return
         */
        int getDepth();

        /**
         * Get the corresponding Java type
         * 
         * @return
         */
        String getJavaType();

        /**
         * Get the corresponding Java type
         * 
         * @param requiredImports A set to hold any imports that are required for this type.
         * @return The type assuming that the required classes will have been imported.
         */
        public String getJavaType(Set<String> requiredImports);

        /**
         * Get the String that represents this type in the API. This is used when constructing the API version of the
         * class from a parsed input.
         * 
         * @return
         */
        String getAPIType();
    }

    /**
     * A base class for non array types.
     *
     * @param <A>
     */
    public static abstract class BaseType<A extends Variable<A>> implements Type<A> {
        private final String javaType;
        private final String apiType;

        protected BaseType(String javaType, String apiType) {
            this.javaType = javaType;
            this.apiType = apiType;
            apiLookup.put(javaType, this);
        }

        /**
         * Get the corresponding Java type
         * 
         * @return
         */
        @Override
        public String getJavaType() {
            return javaType;
        }

        /**
         * Get the corresponding Java type
         * 
         * @param requiredImports A set to hold any imports that are required for this type.
         * @return The type assuming that the required classes will have been imported.
         */
        @Override
        public String getJavaType(Set<String> requiredImports) {
            String[] parts = javaType.split("\\.");
            if(parts.length != 1)
                requiredImports.add(javaType);
            return parts[parts.length - 1];
        }

        /**
         * Get the String that represents this type in the API. This is used when constructing the API version of the
         * class from a parsed input.
         * 
         * @return
         */
        @Override
        public String getAPIType() {
            return apiType;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((javaType == null) ? 0 : javaType.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }
        
        /**
         * Method to determine if this type is a member of the family of array types. All other types are represented by
         * singleton objects and so do not need this check.
         * 
         * @return true if this type is an array.
         */
        @Override
        public boolean isArray() {
            return false;
        }

        /**
         * Get the singleton object representing this type.
         */
        @Override
        public BaseType<A> getTypeSingleton() {
            return this;
        }

        /**
         * Get the base type of this array, for example int[][][] would return int.
         * 
         * @return
         */
        @Override
        public Type<?> getBaseType() {
            return this;
        }

        /**
         * What is the dimensionality of this type. I.E. how many [] are there after the base type.
         * 
         * @return
         */
        @Override
        public int getDepth() {
            return 0;
        }

        @Override
        public String toString() {
            return getJavaType();
        }
    }

    /**
     * Abstract class for scalars.
     */
    public abstract static class ScalarType<A extends ScalarVariable<A>> extends BaseType<A> {
        protected ScalarType(String description, String apiDescription) {
            super(description, apiDescription);
        }
    }

    /**
     * Abstract class for numeric values
     */
    public abstract static class NumberType<A extends NumberVariable<A>> extends ScalarType<A> {
        private NumberType(String description, String apiDescription) {
            super(description, apiDescription);
        }

        /**
         * Get an instance of the variable from its type.
         * 
         * @param parent The dataflow task that will be this values parent. Typically, this is a get task constructing a
         *               value when it is returned from an array.
         * @return
         */
        public abstract A getInstance(NumberProducingDataflowTask<A> parent);
    }

    /**
     * Abstract class for numeric values
     */
    public abstract static class BooleanType extends ScalarType<BooleanVariable> {
        private BooleanType(String description, String apiDescription) {
            super(description, apiDescription);
        }

        /**
         * Get an instance of the variable from its type.
         * 
         * @param parent The dataflow task that will be this values parent. Typically, this is a get task constructing a
         *               value when it is returned from an array.
         * @return
         */
        public abstract BooleanVariable getInstance(ProducingDataflowTask<BooleanVariable> parent);
    }

    /**
     * Type class to represent random variables.
     */
    public static class RandomVariableType<A extends Variable<A>, B extends RandomVariable<A, B>> extends BaseType<B> {
        /** Does the random variable have an infinite number of possible values? */
        private final boolean isInfinite;
        /** Is the random variable continuous? */
        private final boolean isContinuous;

        private RandomVariableType(boolean isInfinite, boolean isContinuous, String description) {
            super(description, description);
            this.isInfinite = isInfinite;
            this.isContinuous = isContinuous;
        }

        public boolean isInfinite() {
            return isInfinite;
        }

        public boolean isContinuous() {
            return isContinuous;
        }
    }

    /**
     * Type class to represent an array instance. This type will include the type of elements that the array holds and
     * so can be used to compare two arrays, or perform reasoning about how array elements should be treated.
     * <p>
     * This is the only type not represented by a singleton type.
     */
    public static class ArrayType<A extends Variable<A>> implements Type<ArrayVariable<A>> {
        private final Type<A> elementType;
        private static final Type<?> type = Array;

        /**
         * Construct a new Array type with element type as the type of the values stored in the array.
         * 
         * @param elementType
         */
        private ArrayType(Type<A> elementType) {
            assert elementType != null : "Base type of array is not set.";
            this.elementType = elementType;
        }

        /**
         * Method to determine if this type is a member of the family of array types. All other types are represented by
         * singleton objects and so do not need this check.
         * 
         * @return true if this type is an array.
         */
        @Override
        public boolean isArray() {
            return true;
        }

        /**
         * Get the singleton type that is used to identify this type.
         * 
         * @return
         */
        @Override
        public Type<?> getTypeSingleton() {
            return type;
        }

        /**
         * Get the type of the elements of the array.
         * 
         * @return
         */
        public Type<A> getElementType() {
            return elementType;
        }

        /**
         * Get the base type of this array, for example int[][][] would return int.
         * 
         * @return
         */
        @Override
        public Type<?> getBaseType() {
            if(elementType.getTypeSingleton() == Array) {
                ArrayType<?> v = (ArrayType<?>) elementType;
                return v.getBaseType();
            } else
                return elementType;
        }

        /**
         * Method for getting the Java type of the base type.
         * 
         * @return
         */
        public String getBaseJavaType() {
            return getBaseType().getJavaType();
        }

        /**
         * Method for getting the Java type of the base type.
         * 
         * @param A set to hold any required imports.
         * @return
         */
        public String getBaseJavaType(Set<String> requiredImports) {
            return getBaseType().getJavaType(requiredImports);
        }

        /**
         * Method for getting the API type of the base type. This is used in the construction of the api by the parser.
         * 
         * @return
         */
        public String getBaseAPIType() {
            return getBaseType().getAPIType();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + elementType.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if((obj == null) || (getClass() != obj.getClass()))
                return false;
            ArrayType<?> other = (ArrayType<?>) obj;
            return elementType.equals(other.elementType);
        }

        /**
         * What is the dimensionality of this array. I.E. how many [] are there after the base type.
         * 
         * @return
         */
        @Override
        public int getDepth() {
            return elementType.getDepth() + 1;
        }

        @Override
        public String getJavaType() {
            String result = getBaseJavaType();
            for(int depth = getDepth(); depth > 0; depth--)
                result = result + "[]";
            return result;
        }

        @Override
        public String getJavaType(Set<String> requiredImports) {
            String result = getBaseJavaType(requiredImports);
            for(int depth = getDepth(); depth > 0; depth--)
                result = result + "[]";
            return result;
        }

        /**
         * Get the String that represents this type in the API. This is used when constructing the API version of the
         * class from a parsed input.
         * 
         * @return
         */
        @Override
        public String getAPIType() {

            String result = getBaseAPIType();
            for(int depth = getDepth(); depth > 0; depth--)
                result = "ArrayVariable<" + result + ">";
            return result;
        }

        @Override
        public String toString() {
            String result = getBaseJavaType();
            for(int depth = getDepth(); depth > 0; depth--)
                result = result + "[]";
            return result;
        }

        /**
         * Get an instance of the variable from its type.
         * 
         * @param parent The dataflow task that will be this values parent. Typically, this is a get task constructing a
         *               value when it is returned from an array.
         * @return A variable based on the output of the parent task.
         */
        public ArrayVariable<A> getInstance(ArrayProducingDataflowTask<A> parent) {
            return ArrayVariable.getArrayVariable(parent);
        }
    }

    /**
     * Singleton instance of the type for integers.
     */
    public static final NumberType<IntVariable> IntVariable = new NumberType<>("int", "IntVariable") {

        @Override
        public IntVariable getInstance(NumberProducingDataflowTask<IntVariable> parent) {
            return org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable.intVariable(parent);
        }
    };

    /**
     * Singleton instance of the type for doubles.
     */
    public static final NumberType<DoubleVariable> DoubleVariable = new NumberType<>("double", "DoubleVariable") {

        @Override
        public DoubleVariable getInstance(NumberProducingDataflowTask<DoubleVariable> parent) {
            return org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable.doubleVariable(parent);
        }
    };

    /**
     * Singleton instance of the type for booleans.
     */
    public static final BooleanType BooleanVariable = new BooleanType("boolean", "BooleanVariable") {

        @Override
        public BooleanVariable getInstance(ProducingDataflowTask<BooleanVariable> parent) {
            return org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable
                    .booleanVariable(parent);
        }
    };

    /**
     * Singleton instance of the type for random number generator. This should only ever be used in Trees to allow RNGs
     * to be passed as arguments to functions.
     */
    public static final BaseType<RandomNumberGenerator> RNG = new BaseType<>("org.sandwood.random.internal.Rng",
            "Random Number Generator") {};

    // Singleton instances for the different types of random variable.
    public static final RandomVariableType<BooleanVariable, Bernoulli> Bernoulli = new RandomVariableType<>(false,
            false, "Bernoulli");
    public static final RandomVariableType<DoubleVariable, Beta> Beta = new RandomVariableType<>(true, true, "Beta");
    public static final RandomVariableType<IntVariable, Binomial> Binomial = new RandomVariableType<>(false, false,
            "Binomial");
    public static final RandomVariableType<IntVariable, Categorical> Categorical = new RandomVariableType<>(false,
            false, "Categorical");
    public static final RandomVariableType<DoubleVariable, Cauchy> Cauchy = new RandomVariableType<>(true, true,
            "Cauchy");
    public static final RandomVariableType<ArrayVariable<DoubleVariable>, Dirichlet> Dirichlet = new RandomVariableType<>(
            true, true, "Dirichlet");
    public static final RandomVariableType<DoubleVariable, Exponential> Exponential = new RandomVariableType<>(true,
            true, "Exponential");
    public static final RandomVariableType<DoubleVariable, Gamma> Gamma = new RandomVariableType<>(true, true, "Gamma");
    public static final RandomVariableType<DoubleVariable, Gaussian> Gaussian = new RandomVariableType<>(true, true,
            "Gaussian");
    public static final RandomVariableType<DoubleVariable, HalfCauchy> HalfCauchy = new RandomVariableType<>(true, true,
            "HalfCauchy");
    public static final RandomVariableType<DoubleVariable, InverseGamma> InverseGamma = new RandomVariableType<>(true,
            true, "InverseGamma");
    public static final RandomVariableType<ArrayVariable<IntVariable>, Multinomial> Multinomial = new RandomVariableType<>(
            true, true, "Multinomial");
    public static final RandomVariableType<IntVariable, Poisson> Poisson = new RandomVariableType<>(true, false,
            "Poisson");
    public static final RandomVariableType<DoubleVariable, StudentT> StudentT = new RandomVariableType<>(true, true,
            "StudentT");
    public static final RandomVariableType<DoubleVariable, Uniform> Uniform = new RandomVariableType<>(true, true,
            "Uniform");

    /**
     * Singleton for arrays to allow types to be trivially tested to see if they are arrays.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final Type<? extends ArrayVariable<?>> Array = new BaseType("array", "array") {};

    /**
     * Constructor for arrays where the element type of the array is known.
     * 
     * @param elementType The element type of this array.
     * @return
     */
    public static <A extends Variable<A>> ArrayType<A> arrayType(Type<A> elementType) {
        if(elementType instanceof RandomVariableType)
            throw new MissingFeatureException("Arrays of random variables are not currently supported.");
        return new ArrayType<>(elementType);
    }

    /*
     * public static <A extends Variable<A>> ArrayType<A> arrayType() { return new ArrayType<>(null); }
     */

    /**
     * Helper method for generating the type of values potentially wrapped in many arrays.
     *
     * @param type  The base type.
     * @param depth The number of arrays it is wrapped in.
     * @return
     */
    public static Type<?> getType(Type<?> type, int depth) {
        for(int i = 0; i < depth; i++)
            type = VariableType.arrayType(type);
        return type;
    }
}
