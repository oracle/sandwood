/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask.TraceCallback;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionInput;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable.ArrayValueInit;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Binomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Cauchy;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Exponential;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Geometric;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.HalfCauchy;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Multinomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.NegativeBinomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Poisson;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.StudentT;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.TruncatedGaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Uniform;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.AliasAlreadySetException;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.LocationAlreadySetException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.tools.visuliser.GraphVisualizer;
import org.sandwood.compiler.traces.DAGInfo;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface Variable<A extends Variable<A>> extends Comparable<Variable<?>> {

    /**
     * Method to get the expression that represents the value of this variable. If inline is true and there is an
     * inlineable variable (this will only be true if the value is used only once) it will return the expression
     * otherwise it will return the name of the variable.
     *
     * @param inLine Do we try to inline expressions?
     * @return The expression that represents the variable if we are inlining, otherwise the variable name.
     */
    String getExpression(boolean inLine);

    /**
     * Method to get the variable identifier. This will either be the variables handle id, or the variables alias if we
     * are using aliases and the alias has been set.
     *
     * @return The name of the variable.
     */
    VariableDescription<A> getVarDesc();

    /**
     * Method to get a unique variable identifier. This will be the var id if the alias is not set, and a combination of
     * the two if the alias is set.
     *
     * @return The name of the variable.
     */
    VariableDescription<A> getUniqueVarDesc();

    /**
     * Set the string that represents the code to create value of this variable.
     *
     * @param inLineValue Expression to create the value of this variable.
     */
    void setInLineValue(String inLineValue);

    /**
     * Method to set the value of the alias for a variable. Calls to this should only be added by tooling, not by a
     * user.
     *
     * @param alias The alias for this variable
     * @throws AliasAlreadySetException Aliases can only be set once, trying to reset an alias will cause this exception
     *                                  to be thrown.
     */
    void setAlias(String alias) throws AliasAlreadySetException;

    /**
     * Method to set the location that the variable is declared in the model script.
     *
     * @param location The location this variable is declared at.
     * @throws LocationAlreadySetException Locations can only be set once, trying to reset a location will cause this
     *                                     exception to be thrown.
     */
    void setLocation(Location location) throws LocationAlreadySetException;

    /**
     * Get the location that the variable appears in the model file at.
     * 
     * @return The variable location.
     */
    Location getLocation();

    void setAlias(VariableDescription<A> alias) throws AliasAlreadySetException;

    void setUniqueVarDesc(VariableDescription<A> varDesc);

    /**
     * Get the value of the alias, returns null if no value is set.
     *
     * @return The value of the alias, null if no alias is set.
     */
    String getAlias();

    /**
     * Returns if the alias has been set
     *
     * @return Has the alias been set
     */
    boolean aliasSet();

    /**
     * Method to set the value of the comment for a variable. Calls to this should only be added by tooling, not by a
     * user.
     *
     * @param comment The comment for this variable.
     */
    void setComment(String comment);

    /**
     * Get the value of the variable comment, returns null if no value is set.
     *
     * @return The value of the comment, null if no comment is set.
     */
    String getComment();

    /**
     * Method to record the tasks that consume this variable.
     *
     * @param task A dataflow task that consumes this variable.
     */
    void addConsumer(DataflowTask<?> task);

    /**
     * Getter method for the scope of the variable
     *
     * @return Scope of the variable
     */
    Scope scope();

    /**
     * Method to set the enclosing scope of a variable.
     * 
     * @param enclosingScope The scope that encloses the variable.
     */
    void setScope(Scope enclosingScope);

    /**
     * Method that takes a set of tasks and adds any extra tasks that are required to construct this variable.
     *
     * @param tasks possibly empty set of tasks that will be augmented with any additional tasks required to construct
     *              this variable.
     */
    void getTasks(Set<DataflowTask<?>> tasks);

    /**
     * Method to return the variable ID.
     *
     * @return
     */
    int getId();

    ProducingDataflowTask<A> getParent();

    ObserveVariableTask<A> getObservation();

    /**
     * Method to use to change the parent of a variable when restructuring the dag.
     * 
     * @param alternative A new parent to use instead of the original one.
     */
    void changeParent(ProducingDataflowTask<A> alternative);

    /**
     * Method to remove a task from the set of consumers when restructuring the graph.
     * 
     * @param t The task to remove
     */
    void removeConsumer(ProducingDataflowTask<?> t);

    /**
     * Method to return the type of the variable
     *
     * @return Type of the variable.
     */
    Type<A> getType();

    /**
     * return the instance of the variable that is being used by the api.
     *
     * @return
     */
    Variable<A> instanceHandle();

    /**
     * Get the value of the latest instance of this variable
     *
     * @return The latest instance of this variable
     */
    Variable<A> getCurrentInstance();

    /**
     * Get the id of the first instance of this variable
     *
     * @return ID of the first instance of this variable.
     */
    int getHandleId();

    /**
     * Method to set this to a stop point
     */
    void markStopPoint();

    /**
     * Method to unset this as a stop point
     */
    void unmarkStopPoint();

    boolean isStopPoint();

    /**
     * Method to mark this variable as an intermediate variable.
     */
    void setIntermediate();

    /**
     * Test if this variable is an intermediate variable.
     *
     * @return
     */
    boolean isIntermediate();

    /**
     * A method to control if the value of the intermediates should be calculated from the current state of the
     * compilationCtx, not read from the global variables. This is set on a per variable basis.
     * 
     * @param calculateIntermediate should intermediates be calculated.
     */
    void calculateIntermediate(boolean calculateIntermediate);

    /**
     * A method to copy this variable.
     * 
     * @return Returns a copy of this variable.
     */
    A copy();

    /**
     * A method to copy this variable.
     * 
     * @return Returns a copy of this variable.
     */
    A copy(Location location);

    boolean isSample();

    void setSample();

    IRTreeReturn<A> getForwardIR(CompilationContext compilationCtx);

    /**
     * Method that returns all the consumers of a given variable.
     *
     * @return Set of consumers of a variable.
     */
    Set<DataflowTask<?>> getConsumers();

    /**
     * Method to trigger the construction of traces. It does nothing for most variables, but will start a trace for
     * observed variables, and be overridden to start traces from RandomVariables initially, and later other types as
     * required.
     *
     * @param traces
     */
    void constructTrace(DAGInfo traces);

    void constructTrace(TraceCallback c);

    void constructTrace(TraceConstructionDesc desc);

    /**
     * Method to set this variable to be private.
     */
    void setPrivate();

    /**
     * Method to set this variable to be private.
     */
    void setPublic();

    /**
     * Method to determine if this is a private variable
     */
    boolean isPrivate();

    /**
     * Method to determine if this variable is set to an observed value.
     */
    boolean isObserved();

    Set<Variable<?>> collectInputVariables(DFType... stopTypes);

    boolean equivalent(Variable<?> other);

    @Override
    int compareTo(Variable<?> other);

    void getDescription(StringBuilder sb);

    void setIsDistribution();

    boolean isDistribution();

    void setNonDeterministic();

    boolean isDeterministic();

    /*
     * Arrays: Convenience methods that pass the call through to the factory in the relevant object.
     */

    /* Length and type */
    static <A extends Variable<A>> ArrayVariable<A> arrayVariable(Type<A> baseType, int... dims) {
        IntVariable[] intVarDims = intVarDims(dims);
        return arrayVariable(baseType, intVarDims);
    }

    static <A extends Variable<A>> ArrayVariable<A> arrayVariable(Location location, Type<A> baseType, int... dims) {
        IntVariable[] intVarDims = intVarDims(dims);
        return arrayVariable(location, baseType, intVarDims);
    }

    private static IntVariable[] intVarDims(int... dims) {
        int noDims = dims.length;
        IntVariable[] intVarDims = new IntVariable[noDims];
        for(int i = 0; i < noDims; i++)
            intVarDims[i] = Variable.intVariable(dims[i]);
        return intVarDims;
    }

    static <A extends Variable<A>> ArrayVariable<A> arrayVariable(Type<A> baseType, IntVariable... dims) {
        return ArrayVariable.getArrayVariable(baseType, dims);
    }

    static <A extends Variable<A>> ArrayVariable<A> arrayVariable(Location location, Type<A> baseType,
            IntVariable... dims) {
        return ArrayVariable.getArrayVariable(location, baseType, dims);
    }

    /* length and initial values x8 */
    static ArrayVariable<DoubleVariable> arrayVariable(double v, int... dims) {
        return arrayVariable(Variable.doubleVariable(v), intVarDims(dims));
    }

    static ArrayVariable<DoubleVariable> arrayVariable(Location location, double v, int... dims) {
        return arrayVariable(location, Variable.doubleVariable(v), intVarDims(dims));
    }

    static ArrayVariable<DoubleVariable> arrayVariable(double v, IntVariable... dims) {
        return arrayVariable(Variable.doubleVariable(v), dims);
    }

    static ArrayVariable<DoubleVariable> arrayVariable(Location location, double v, IntVariable... dims) {
        return arrayVariable(location, Variable.doubleVariable(v), dims);
    }

    static ArrayVariable<IntVariable> arrayVariable(int v, int... dims) {
        return arrayVariable(Variable.intVariable(v), intVarDims(dims));
    }

    static ArrayVariable<IntVariable> arrayVariable(Location location, int v, int... dims) {
        return arrayVariable(location, Variable.intVariable(v), intVarDims(dims));
    }

    static ArrayVariable<IntVariable> arrayVariable(int v, IntVariable... dims) {
        return arrayVariable(Variable.intVariable(v), dims);
    }

    static ArrayVariable<IntVariable> arrayVariable(Location location, int v, IntVariable... dims) {
        return arrayVariable(location, Variable.intVariable(v), dims);
    }

    static ArrayVariable<BooleanVariable> arrayVariable(boolean b, int... dims) {
        return arrayVariable(Variable.booleanVariable(b), intVarDims(dims));
    }

    static ArrayVariable<BooleanVariable> arrayVariable(Location location, boolean b, int... dims) {
        return arrayVariable(location, Variable.booleanVariable(b), intVarDims(dims));
    }

    static ArrayVariable<BooleanVariable> arrayVariable(boolean b, IntVariable... dims) {
        return arrayVariable(Variable.booleanVariable(b), dims);
    }

    static ArrayVariable<BooleanVariable> arrayVariable(Location location, boolean b, IntVariable... dims) {
        return arrayVariable(location, Variable.booleanVariable(b), dims);
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(B v, int... dims) {
        return arrayVariable(v, intVarDims(dims));
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(Location location, B v,
            int... dims) {
        return arrayVariable(location, v, intVarDims(dims));
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(B value, IntVariable... dims) {
        @SuppressWarnings("unchecked")
        Type<A> baseType = (Type<A>) VariableType.getType(value.getType(), dims.length - 1);
        return arrayVariable(baseType, () -> value, dims);
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(Location location, B value,
            IntVariable... dims) {
        @SuppressWarnings("unchecked")
        Type<A> baseType = (Type<A>) VariableType.getType(value.getType(), dims.length - 1);
        return arrayVariable(location, baseType, () -> value, dims);
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(Type<A> baseType,
            ArrayValueInit<B> value, IntVariable... dims) {
        return ArrayVariable.getArrayVariable(baseType, value, dims);
    }

    static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> arrayVariable(Location location,
            Type<A> baseType, ArrayValueInit<B> value, IntVariable... dims) {
        return ArrayVariable.getArrayVariable(location, baseType, value, dims);
    }

    /* Observed array */

    static <A extends Variable<A>> ArrayVariable<A> observeArray(String fieldname, ArrayType<A> type, String comment) {
        return ArrayVariable.getArrayVariable(fieldname, type, comment);
    }

    static <A extends Variable<A>> ArrayVariable<A> observeArray(String fieldname, ArrayType<A> type, String comment,
            Location location) {
        return ArrayVariable.getArrayVariable(fieldname, type, comment, location);
    }

    static <A extends Variable<A>> ArrayVariable<A> observeArray(String fieldname, ArrayType<A> type) {
        return ArrayVariable.getArrayVariable(fieldname, type);
    }

    static <A extends Variable<A>> ArrayVariable<A> observeArray(String fieldname, ArrayType<A> type,
            Location location) {
        return ArrayVariable.getArrayVariable(fieldname, type, location);
    }

    /*
     * Scalar Variables: Convenience methods that pass the call through to the factory in the relevant object.
     */
    static DoubleVariable doubleVariable(double v) {
        return DoubleVariable.doubleVariable(v);
    }

    static DoubleVariable doubleVariable(double v, Location location) {
        return DoubleVariable.doubleVariable(v, location);
    }

    static DoubleVariable observeDouble(String fieldname) {
        return DoubleVariable.doubleVariable(fieldname);
    }

    static DoubleVariable observeDouble(String fieldname, Location location) {
        return DoubleVariable.doubleVariable(fieldname, location);
    }

    static DoubleVariable observeDouble(String fieldname, String comment) {
        return DoubleVariable.doubleVariable(fieldname, comment);
    }

    static DoubleVariable observeDouble(String fieldname, String comment, Location location) {
        return DoubleVariable.doubleVariable(fieldname, comment, location);
    }

    static IntVariable intVariable(int v) {
        return IntVariable.intVariable(v);
    }

    static IntVariable intVariable(int v, Location location) {
        return IntVariable.intVariable(v, location);
    }

    static IntVariable observeInt(String fieldname) {
        return IntVariable.intVariable(fieldname);
    }

    static IntVariable observeInt(String fieldname, Location location) {
        return IntVariable.intVariable(fieldname, location);
    }

    static IntVariable observeInt(String fieldname, String comment) {
        return IntVariable.intVariable(fieldname, comment);
    }

    static IntVariable observeInt(String fieldname, String comment, Location location) {
        return IntVariable.intVariable(fieldname, comment, location);
    }

    static BooleanVariable booleanVariable(boolean v) {
        return BooleanVariable.booleanVariable(v);
    }

    static BooleanVariable booleanVariable(boolean v, Location location) {
        return BooleanVariable.booleanVariable(v, location);
    }

    static BooleanVariable observeBoolean(String fieldname) {
        return BooleanVariable.booleanVariable(fieldname);
    }

    static BooleanVariable observeBoolean(String fieldname, Location location) {
        return BooleanVariable.booleanVariable(fieldname, location);
    }

    static BooleanVariable observeBoolean(String fieldname, String comment) {
        return BooleanVariable.booleanVariable(fieldname, comment);
    }

    static BooleanVariable observeBoolean(String fieldname, String comment, Location location) {
        return BooleanVariable.booleanVariable(fieldname, comment, location);
    }

    /*
     * Random Variables: Convenience methods that pass the call through to the factory in the relevant object.
     */
    static Categorical categorical(ArrayVariable<DoubleVariable> elements) {
        return Categorical.categorical(elements);
    }

    static Categorical categorical(ArrayVariable<DoubleVariable> elements, Location location) {
        return Categorical.categorical(elements, location);
    }

    static Bernoulli bernoulli(double p) {
        return Bernoulli.bernoulli(p);
    }

    static Bernoulli bernoulli(DoubleVariable p) {
        return Bernoulli.bernoulli(p);
    }

    static Bernoulli bernoulli(DoubleVariable p, Location location) {
        return Bernoulli.bernoulli(p, location);
    }

    static Bernoulli bernoulli(IntVariable p) {
        return Bernoulli.bernoulli(p);
    }

    static Bernoulli bernoulli(IntVariable p, Location location) {
        return Bernoulli.bernoulli(p, location);
    }

    static Beta beta(double alpha, double beta) {
        return Beta.beta(doubleVariable(alpha), doubleVariable(beta));
    }

    static Beta beta(DoubleVariable alpha, double beta) {
        return Beta.beta(alpha, doubleVariable(beta));
    }

    static Beta beta(double alpha, DoubleVariable beta) {
        return Beta.beta(doubleVariable(alpha), beta);
    }

    static Beta beta(DoubleVariable alpha, DoubleVariable beta) {
        return Beta.beta(alpha, beta);
    }

    static Beta beta(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return Beta.beta(alpha, beta, location);
    }

    static Beta beta(IntVariable alpha, DoubleVariable beta) {
        return Beta.beta(alpha, beta);
    }

    static Beta beta(IntVariable alpha, DoubleVariable beta, Location location) {
        return Beta.beta(alpha, beta, location);
    }

    static Beta beta(DoubleVariable alpha, IntVariable beta) {
        return Beta.beta(alpha, beta);
    }

    static Beta beta(DoubleVariable alpha, IntVariable beta, Location location) {
        return Beta.beta(alpha, beta, location);
    }

    static Beta beta(IntVariable alpha, IntVariable beta) {
        return Beta.beta(alpha, beta);
    }

    static Beta beta(IntVariable alpha, IntVariable beta, Location location) {
        return Beta.beta(alpha, beta, location);
    }

    static Binomial binomial(double p, int length) {
        return Binomial.binomial(p, length);
    }

    static Binomial binomial(DoubleVariable p, int length) {
        return Binomial.binomial(p, length);
    }

    static Binomial binomial(double p, IntVariable length) {
        return Binomial.binomial(p, length);
    }

    static Binomial binomial(DoubleVariable p, IntVariable length) {
        return Binomial.binomial(p, length);
    }

    static Binomial binomial(DoubleVariable p, IntVariable length, Location location) {
        return Binomial.binomial(p, length, location);
    }

    static Binomial binomial(IntVariable p, IntVariable length) {
        return Binomial.binomial(p, length);
    }

    static Binomial binomial(IntVariable p, IntVariable length, Location location) {
        return Binomial.binomial(p, length, location);
    }

    static Cauchy cauchy(double location, double scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(DoubleVariable location, double scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(double location, DoubleVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(DoubleVariable location, DoubleVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        return Cauchy.cauchy(location, scale, sourceLocation);
    }

    static Cauchy cauchy(IntVariable location, double scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(double location, IntVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(IntVariable location, IntVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(IntVariable location, IntVariable scale, Location sourceLocation) {
        return Cauchy.cauchy(location, scale, sourceLocation);
    }

    static Cauchy cauchy(IntVariable location, DoubleVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(IntVariable location, DoubleVariable scale, Location sourceLocation) {
        return Cauchy.cauchy(location, scale, sourceLocation);
    }

    static Cauchy cauchy(DoubleVariable location, IntVariable scale) {
        return Cauchy.cauchy(location, scale);
    }

    static Cauchy cauchy(DoubleVariable location, IntVariable scale, Location sourceLocation) {
        return Cauchy.cauchy(location, scale, sourceLocation);
    }

    static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta) {
        return Dirichlet.dirichlet(beta);
    }

    static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta, Location location) {
        return Dirichlet.dirichlet(beta, location);
    }

    static Exponential exponential(double lambda) {
        return Exponential.exponential(doubleVariable(lambda));
    }

    static Exponential exponential(DoubleVariable lambda) {
        return Exponential.exponential(lambda);
    }

    static Exponential exponential(IntVariable lambda) {
        return Exponential.exponential(lambda);
    }

    static Exponential exponential(IntVariable lambda, Location location) {
        return Exponential.exponential(lambda, location);
    }

    static Exponential exponential(DoubleVariable lambda, Location location) {
        return Exponential.exponential(lambda, location);
    }

    static Gamma gamma(double alpha, double beta) {
        return Gamma.gamma(doubleVariable(alpha), doubleVariable(beta));
    }

    static Gamma gamma(DoubleVariable alpha, double beta) {
        return Gamma.gamma(alpha, doubleVariable(beta));
    }

    static Gamma gamma(double alpha, DoubleVariable beta) {
        return Gamma.gamma(doubleVariable(alpha), beta);
    }

    static Gamma gamma(DoubleVariable alpha, DoubleVariable beta) {
        return Gamma.gamma(alpha, beta);
    }

    static Gamma gamma(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return Gamma.gamma(alpha, beta, location);
    }

    static Gamma gamma(IntVariable alpha, DoubleVariable beta) {
        return Gamma.gamma(alpha, beta);
    }

    static Gamma gamma(IntVariable alpha, DoubleVariable beta, Location location) {
        return Gamma.gamma(alpha, beta, location);
    }

    static Gamma gamma(DoubleVariable alpha, IntVariable beta) {
        return Gamma.gamma(alpha, beta);
    }

    static Gamma gamma(DoubleVariable alpha, IntVariable beta, Location location) {
        return Gamma.gamma(alpha, beta, location);
    }

    static Gamma gamma(IntVariable alpha, IntVariable beta) {
        return Gamma.gamma(alpha, beta);
    }

    static Gamma gamma(IntVariable alpha, IntVariable beta, Location location) {
        return Gamma.gamma(alpha, beta, location);
    }

    static Gaussian gaussian(double mean, double variance) {
        return Gaussian.gaussian(doubleVariable(mean), doubleVariable(variance));
    }

    static Gaussian gaussian(DoubleVariable mean, double variance) {
        return Gaussian.gaussian(mean, doubleVariable(variance));
    }

    static Gaussian gaussian(double mean, DoubleVariable variance) {
        return Gaussian.gaussian(doubleVariable(mean), variance);
    }

    static Gaussian gaussian(DoubleVariable mean, DoubleVariable variance) {
        return Gaussian.gaussian(mean, variance);
    }

    static Gaussian gaussian(DoubleVariable mean, DoubleVariable variance, Location location) {
        return Gaussian.gaussian(mean, variance, location);
    }

    static Gaussian gaussian(IntVariable mean, DoubleVariable variance) {
        return Gaussian.gaussian(mean, variance);
    }

    static Gaussian gaussian(IntVariable mean, DoubleVariable variance, Location location) {
        return Gaussian.gaussian(mean, variance, location);
    }

    static Gaussian gaussian(DoubleVariable mean, IntVariable variance) {
        return Gaussian.gaussian(mean, variance);
    }

    static Gaussian gaussian(DoubleVariable mean, IntVariable variance, Location location) {
        return Gaussian.gaussian(mean, variance, location);
    }

    static Gaussian gaussian(IntVariable mean, IntVariable variance) {
        return Gaussian.gaussian(mean, variance);
    }

    static Gaussian gaussian(IntVariable mean, IntVariable variance, Location location) {
        return Gaussian.gaussian(mean, variance, location);
    }

    public static Geometric geometric(double p) {
        return Geometric.geometric(p);
    }

    public static Geometric geometric(DoubleVariable p) {
        return Geometric.geometric(p);
    }

    public static Geometric geometric(DoubleVariable p, Location location) {
        return Geometric.geometric(p, location);
    }

    public static Geometric geometric(IntVariable p) {
        return Geometric.geometric(p);
    }

    public static Geometric geometric(IntVariable p, Location location) {
        return Geometric.geometric(p, location);
    }

    static HalfCauchy halfCauchy(double location, double scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(DoubleVariable location, double scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(double location, DoubleVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(DoubleVariable location, DoubleVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        return HalfCauchy.halfCauchy(location, scale, sourceLocation);
    }

    static HalfCauchy halfCauchy(IntVariable location, double scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(double location, IntVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(IntVariable location, IntVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(IntVariable location, IntVariable scale, Location sourceLocation) {
        return HalfCauchy.halfCauchy(location, scale, sourceLocation);
    }

    static HalfCauchy halfCauchy(IntVariable location, DoubleVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(IntVariable location, DoubleVariable scale, Location sourceLocation) {
        return HalfCauchy.halfCauchy(location, scale, sourceLocation);
    }

    static HalfCauchy halfCauchy(DoubleVariable location, IntVariable scale) {
        return HalfCauchy.halfCauchy(location, scale);
    }

    static HalfCauchy halfCauchy(DoubleVariable location, IntVariable scale, Location sourceLocation) {
        return HalfCauchy.halfCauchy(location, scale, sourceLocation);
    }

    static InverseGamma inverseGamma(double alpha, double beta) {
        return InverseGamma.inverseGamma(doubleVariable(alpha), doubleVariable(beta));
    }

    static InverseGamma inverseGamma(DoubleVariable alpha, double beta) {
        return InverseGamma.inverseGamma(alpha, doubleVariable(beta));
    }

    static InverseGamma inverseGamma(double alpha, DoubleVariable beta) {
        return InverseGamma.inverseGamma(doubleVariable(alpha), beta);
    }

    static InverseGamma inverseGamma(DoubleVariable alpha, DoubleVariable beta) {
        return InverseGamma.inverseGamma(alpha, beta);
    }

    static InverseGamma inverseGamma(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return InverseGamma.inverseGamma(alpha, beta, location);
    }

    static InverseGamma inverseGamma(IntVariable alpha, DoubleVariable beta) {
        return InverseGamma.inverseGamma(alpha, beta);
    }

    static InverseGamma inverseGamma(IntVariable alpha, DoubleVariable beta, Location location) {
        return InverseGamma.inverseGamma(alpha, beta, location);
    }

    static InverseGamma inverseGamma(DoubleVariable alpha, IntVariable beta) {
        return InverseGamma.inverseGamma(alpha, beta);
    }

    static InverseGamma inverseGamma(DoubleVariable alpha, IntVariable beta, Location location) {
        return InverseGamma.inverseGamma(alpha, beta, location);
    }

    static InverseGamma inverseGamma(IntVariable alpha, IntVariable beta) {
        return InverseGamma.inverseGamma(alpha, beta);
    }

    static InverseGamma inverseGamma(IntVariable alpha, IntVariable beta, Location location) {
        return InverseGamma.inverseGamma(alpha, beta, location);
    }

    static Multinomial multinomial(ArrayVariable<DoubleVariable> p, int n) {
        return Multinomial.multinomial(p, intVariable(n));
    }

    static Multinomial multinomial(ArrayVariable<DoubleVariable> p, IntVariable n) {
        return Multinomial.multinomial(p, n);
    }

    static Multinomial multinomial(ArrayVariable<DoubleVariable> p, IntVariable n, Location location) {
        return Multinomial.multinomial(p, n, location);
    }

    static NegativeBinomial negativeBinomial(double p, int positiveTests) {
        return NegativeBinomial.negativeBinomial(p, positiveTests);
    }

    static NegativeBinomial negativeBinomial(DoubleVariable p, int positiveTests) {
        return NegativeBinomial.negativeBinomial(p, positiveTests);
    }

    static NegativeBinomial negativeBinomial(double p, IntVariable positiveTests) {
        return NegativeBinomial.negativeBinomial(p, positiveTests);
    }

    static NegativeBinomial negativeBinomial(DoubleVariable p, IntVariable positiveTests) {
        return NegativeBinomial.negativeBinomial(p, positiveTests);
    }

    static NegativeBinomial negativeBinomial(DoubleVariable p, IntVariable positiveTests, Location location) {
        return NegativeBinomial.negativeBinomial(p, positiveTests, location);
    }

    static NegativeBinomial negativeBinomial(IntVariable p, IntVariable positiveTests) {
        return NegativeBinomial.negativeBinomial(p, positiveTests);
    }

    static NegativeBinomial negativeBinomial(IntVariable p, IntVariable positiveTests, Location location) {
        return NegativeBinomial.negativeBinomial(p, positiveTests, location);
    }

    static Poisson poisson(double rate) {
        return Poisson.poisson(rate);
    }

    static Poisson poisson(DoubleVariable rate) {
        return Poisson.poisson(rate);
    }

    static Poisson poisson(DoubleVariable rate, Location location) {
        return Poisson.poisson(rate, location);
    }

    static Poisson poisson(IntVariable rate) {
        return Poisson.poisson(rate);
    }

    static Poisson poisson(IntVariable rate, Location location) {
        return Poisson.poisson(rate, location);
    }

    static StudentT studentT(double v) {
        return StudentT.studentT(v);
    }

    static StudentT studentT(DoubleVariable v) {
        return StudentT.studentT(v);
    }

    static StudentT studentT(DoubleVariable v, Location location) {
        return StudentT.studentT(v, location);
    }

    static StudentT studentT(IntVariable v) {
        return StudentT.studentT(v);
    }

    static StudentT studentT(IntVariable v, Location location) {
        return StudentT.studentT(v, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, double upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, DoubleVariable upper) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, double upper,
            Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower, double upper,
            Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower, double upper,
            Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower, double upper,
            Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, DoubleVariable upper,
            Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            double upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            double upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            double upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, double upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, DoubleVariable upper, Location location) {
        return TruncatedGaussian.truncatedGaussian(mean, variance, lower, upper, location);
    }

    static Uniform uniform(double start, double end) {
        return Uniform.uniform(doubleVariable(start), doubleVariable(end));
    }

    static Uniform uniform(DoubleVariable start, double end) {
        return Uniform.uniform(start, doubleVariable(end));
    }

    static Uniform uniform(double start, DoubleVariable end) {
        return Uniform.uniform(doubleVariable(start), end);
    }

    static Uniform uniform(DoubleVariable start, DoubleVariable end) {
        return Uniform.uniform(start, end);
    }

    static Uniform uniform(DoubleVariable start, DoubleVariable end, Location location) {
        return Uniform.uniform(start, end, location);
    }

    static Uniform uniform(IntVariable start, DoubleVariable end) {
        return Uniform.uniform(start, end);
    }

    static Uniform uniform(IntVariable start, DoubleVariable end, Location location) {
        return Uniform.uniform(start, end, location);
    }

    static Uniform uniform(DoubleVariable start, IntVariable end) {
        return Uniform.uniform(start, end);
    }

    static Uniform uniform(DoubleVariable start, IntVariable end, Location location) {
        return Uniform.uniform(start, end, location);
    }

    static Uniform uniform(IntVariable start, IntVariable end) {
        return Uniform.uniform(start, end);
    }

    static Uniform uniform(IntVariable start, IntVariable end, Location location) {
        return Uniform.uniform(start, end, location);
    }

    // Named Variable
    static <A extends Variable<A>> A namedVariable(VariableDescription<A> varDesc) {
        return namedVariable(varDesc, GlobalScope.scope);
    }

    // Named Variable
    @SuppressWarnings("unchecked")
    static <A extends Variable<A>> A namedVariable(VariableDescription<A> varDesc, Scope scope) {
        Type<?> t = varDesc.type.getTypeSingleton();
        if(t == VariableType.BooleanVariable)
            return (A) BooleanVariable.namedBoolean((VariableDescription<BooleanVariable>) varDesc, scope);
        else if(t == VariableType.DoubleVariable)
            return (A) DoubleVariable.namedDouble((VariableDescription<DoubleVariable>) varDesc, scope);
        else if(t == VariableType.IntVariable)
            return (A) IntVariable.namedInt((VariableDescription<IntVariable>) varDesc, scope);
        else if(t == VariableType.Array)
            return (A) ArrayVariable.getNamedArray((VariableDescription<ArrayVariable<A>>) varDesc, scope);
        else
            throw new CompilerException("Unable to generate a named variable for type " + varDesc.type);
    }

    /**
     * Method to collect all the variables used to generate a given set of variables.
     *
     * @param vars
     * @return
     */
    static Set<Variable<?>> collectInputVariable(Set<Variable<?>> vars, DFType... sTypes) {
        Set<Variable<?>> variables = new HashSet<>();
        List<Variable<?>> toProcess = new ArrayList<>(vars);
        Set<DFType> stopTypes = new HashSet<>();
        stopTypes.addAll(Arrays.asList(sTypes));

        while(!toProcess.isEmpty()) {
            Variable<?> v = toProcess.remove(0);
            if(!variables.contains(v)) {
                variables.add(v);
                if(!stopTypes.contains(v.getParent().getType()))
                    toProcess.addAll(v.getParent().getInputs());
            }
        }

        variables.removeAll(vars);
        return variables;
    }

    /**
     * Method to construct a string representing the graph. This is just used for testing to confirm nothing has changed
     * when we refactor code etc.
     *
     * @param newLines Do we include newlines?
     * @param vars     The variables we want to generate the string to produce.
     * @return String representing the graph.
     */
    static String getGraphAsString(boolean newLines, Collection<Variable<?>> vars) {
        return getGraphAsString(newLines, vars.toArray(new Variable[vars.size()]));
    }

    /**
     * Method to construct a string representing the graph. This is just used for testing to confirm nothing has changed
     * when we refactor code etc.
     *
     * @param vars The variables we want to generate the string to produce.
     * @return String representing the graph.
     */
    static String getGraphAsString(Collection<Variable<?>> vars) {
        return getGraphAsString(vars.toArray(new Variable[vars.size()]));
    }

    /**
     * Method to construct a string representing the graph. This is just used for testing to confirm nothing has changed
     * when we refactor code etc.
     *
     * @param vars The variables we want to generate the string to produce.
     * @return String representing the graph.
     */
    static String getGraphAsString(Variable<?>... vars) {
        return getGraphAsString(true, vars);
    }

    /**
     * Method to construct a string representing the graph. This is just used for testing to confirm nothing has changed
     * when we refactor code etc.
     *
     * @param newLines Do we include newlines?
     * @param vars     The variables we want to generate the string to produce.
     * @return String representing the graph.
     */
    static String getGraphAsString(boolean newLines, Variable<?>... vars) {
        StringBuilder sb = new StringBuilder();

        Set<DataflowTask<?>> constructingTasks = getTasks(true, vars);
        Set<Variable<?>> variables = multipleVariableUses(constructingTasks, vars).keySet();

        sb.append("Tasks");
        if(newLines)
            sb.append("\n");
        else
            sb.append(": ");

        PriorityQueue<DataflowTask<?>> pTasks = new PriorityQueue<>(constructingTasks);
        while(!pTasks.isEmpty()) {
            DataflowTask<?> t = pTasks.poll();
            t.getDescription(sb);
            if(newLines)
                sb.append("\n");
            else
                sb.append(", ");
        }

        sb.append("Variables");
        if(newLines)
            sb.append("\n");
        else
            sb.append(": ");

        PriorityQueue<Variable<?>> pVariables = new PriorityQueue<>(variables);
        while(!pVariables.isEmpty()) {
            Variable<?> v = pVariables.poll();
            v.getDescription(sb);
            if(newLines)
                sb.append("\n");
            else
                sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Method to get Sandwood code for generating specific variables. This version defaults to inlining code where
     * possible.
     *
     * @param vars The variables we want to generate Sandwood code to produce.
     * @return The Sandwood code.
     */
    static String getSandwoodCode(Collection<Variable<?>> vars) {
        return getSandwoodCode(true, false, vars);
    }

    /**
     * Method to get Sandwood code for generating specific variables.
     *
     * @param compressSandwoodCode
     * @param vars                 The variables we want to generate Sandwood code to produce.
     * @return The Sandwood code.
     */
    static String getSandwoodCode(boolean compressSandwoodCode, boolean allInstances, Collection<Variable<?>> vars) {
        return getSandwoodCode(compressSandwoodCode, allInstances, vars.toArray(new Variable[vars.size()]));
    }

    /**
     * Method to get Sandwood code for generating specific variables. This version defaults to inlining code where
     * possible.
     *
     * @param vars The variables we want to generate Sandwood code to produce.
     * @return The Sandwood code.
     */
    static String getSandwoodCode(boolean allInstances, Variable<?>... vars) {
        return getSandwoodCode(true, allInstances, vars);
    }

    /**
     * Method to get Sandwood code for generating specific variables.
     *
     * @param compressSandwoodCode
     * @param vars                 The variables we want to generate Sandwood code to produce.
     * @return The Sandwood code.
     */
    static String getSandwoodCode(boolean compressSandwoodCode, boolean allInstances, Variable<?>... vars) {

        // Get the required tasks
        Set<DataflowTask<?>> constructingTasks = getTasks(allInstances, vars);

        // Determine if the variables can be inlined.
        Map<Variable<?>, Boolean> inlineableVariables = multipleVariableUses(constructingTasks, vars);

        // Order the tasks.
        PriorityQueue<DataflowTask<?>> p = new PriorityQueue<>(constructingTasks);

        Stack<Scope> scope = new Stack<>(); // Stack of parent scopes excluding the current scope.
        Scope currentScope = p.peek().scope();
        scope.push(null);

        StringBuilder sb = new StringBuilder();
        int indent = 0;
        for(DataflowTask<?> t; !p.isEmpty();) {
            t = p.poll();

            // Examine scopes
            if(t.scope() != currentScope) { // There has been a scope change
                if(t.scope() != scope.peek()) { // We're in a scope embedded in our previous scope, update variables
                    scope.push(currentScope);
                    currentScope = t.scope();
                    indent = currentScope.getSandwoodCodePrefix(sb, indent, compressSandwoodCode);
                } else { // We are leaving a scope and need to finish off any remaining work.
                    indent = currentScope.getSandwoodCodePostfix(sb, indent);
                    currentScope = scope.pop();
                }
            }
            indent = t.getSandwoodCode(sb, indent, inlineableVariables, compressSandwoodCode);
        }
        return sb.toString();
    }

    /**
     * Method to construct a visualisation of the graph for debugging purposes etc.
     *
     * @param vars The variables the graph must construct.
     */
    static void visualize(Variable<?>... vars) {
        visualize(false, vars);
    }

    static void visualize(boolean allTasks, Variable<?>... vars) {
        Set<DataflowTask<?>> constructingTasks;
        Set<Variable<?>> variables;
        if(allTasks) {
            constructingTasks = new HashSet<>();
            variables = new HashSet<>();
            collectAll(vars, constructingTasks, variables);
        } else {
            constructingTasks = getTasks(true, vars);
            Map<Variable<?>, Boolean> multiUse = multipleVariableUses(constructingTasks, vars);
            variables = multiUse.keySet();
        }

        GraphVisualizer v = GraphVisualizer.visualize(constructingTasks, variables);
        v.setVisible(true);
    }

    private static void collectAll(Variable<?>[] vars, Set<DataflowTask<?>> tasks, Set<Variable<?>> variables) {
        List<Variable<?>> toProcess = new ArrayList<>();
        toProcess.addAll(Arrays.asList(vars));

        while(!toProcess.isEmpty()) {
            Variable<?> v = toProcess.remove(0);
            if(!variables.contains(v)) {
                variables.add(v);

                tasks.add(v.getParent());
                toProcess.addAll(v.getParent().getInputs());
                toProcess.add(v.getParent().getOutput()); // This line should not be required on anything that doesn't
                // have a reduce operation.

                tasks.addAll(v.getConsumers());
                for(DataflowTask<?> t:v.getConsumers())
                    if(t.outputSet())
                        toProcess.add(t.getOutput());
            }
        }
    }

    /**
     * A method to construct a lookup of variables to whether they have been used more than once in the compute graph.
     * This is required for determining if we can inline code.
     *
     * @param constructingTasks The tasks used to construct the variables we are interested in.
     * @param vars              The variables we are interested in.
     * @return A mapping of variables to booleans. Values are true if they can be inlined, i.e. they are only used one,
     *         and false if they are used multiple times or are used in an iteration.
     */
    private static Map<Variable<?>, Boolean> multipleVariableUses(Set<DataflowTask<?>> constructingTasks,
            Variable<?>[] vars) {

        Map<Variable<?>, Boolean> count = new HashMap<>();
        Set<Variable<?>> modifiedVariables = new HashSet<>();

        // Add the variables that we are getting code for and set them to true to ensure
        // that the code is output.
        for(Variable<?> v:vars) {
            Variable<?> handle = v.instanceHandle();
            if(handle != v) {
                modifiedVariables.add(v);
                v = handle;
            }
            count.put(v, true);
        }

        // Test all the variables used by the tasks
        for(DataflowTask<?> t:constructingTasks) {
            List<Variable<?>> temp = t.getInputs();
            for(Variable<?> v:temp) {

                // Get the handle variable, and construct a set of variables referenced by the
                // handles that we will need to add at the end.
                Variable<?> handle = v.instanceHandle();
                if(handle != v) {
                    modifiedVariables.add(v);
                    v = handle;
                }

                /* Test if the statement is embedded in an iteration */
                boolean iterated = false;
                for(Scope d = t.scope(); d != v.scope() && d != GlobalScope.scope; d = d.getEnclosingScope())
                    iterated = iterated || d.iterating();
                iterated = iterated || v.getParent().iterating();
                if((t.getType() != DFType.REDUCE_INPUT || ((ReductionInput<?>) t).first)) {
                    if(count.containsKey(v))
                        count.put(v, true);
                    else
                        count.put(v, iterated);
                }
            }
        }

        // Add the modified variables that have thus far only bee looked up by their
        // handles.
        for(Variable<?> v:modifiedVariables)
            count.put(v, count.get(v.instanceHandle()));
        return count;
    }

    /**
     * Method to return a set of all the tasks executed to construct the provided variables. This method is used to
     * ensure we only consider a minimal graph when inferring values etc.
     *
     * @param vars The variables we want to collect the required tasks for.
     * @return Set of Dataflow tasks.
     */
    static Set<DataflowTask<?>> getTasks(boolean allInstances, Variable<?>... vars) {
        Set<DataflowTask<?>> constructingTasks = new HashSet<>();

        for(Variable<?> v:vars)
            if(allInstances)
                v.getCurrentInstance().getTasks(constructingTasks);
            else
                v.getTasks(constructingTasks);

        return constructingTasks;
    }
}