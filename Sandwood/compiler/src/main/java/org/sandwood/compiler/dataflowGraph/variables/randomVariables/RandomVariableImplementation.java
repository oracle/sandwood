/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import static org.sandwood.compiler.dataflowGraph.Sandwood.parFor;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.DAGInfo;

public abstract class RandomVariableImplementation<A extends Variable<A>, B extends RandomVariable<A, B>>
        extends VariableImplementation<B> implements RandomVariable<A, B> {
    protected final VariableType.Type<A> sampleType;

    protected RandomVariableImplementation(RandomVariableConstructorTask<A, B> parent, VariableType.Type<A> outputType) {
        super(parent);
        this.sampleType = outputType;
        this.setPublic();
    }

    @Override
    public boolean isInfinite() {
        return getType().isInfinite();
    }

    @Override
    public ArrayVariable<A> sample(int count) {
        return sample(Variable.intVariable(count));
    }

    @Override
    public ArrayVariable<ArrayVariable<A>> sample(int count1, int count2) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(int count1, int count2, int count3) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(int count1, int count2, int count3,
            int count4) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3),
                Variable.intVariable(count4));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(int count1, int count2,
            int count3, int count4, int count5) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3),
                Variable.intVariable(count4), Variable.intVariable(count5));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(
            int count1, int count2, int count3, int count4, int count5, int count6) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3),
                Variable.intVariable(count4), Variable.intVariable(count5), Variable.intVariable(count6));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            int count1, int count2, int count3, int count4, int count5, int count6, int count7) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3),
                Variable.intVariable(count4), Variable.intVariable(count5), Variable.intVariable(count6),
                Variable.intVariable(count7));
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            int count1, int count2, int count3, int count4, int count5, int count6, int count7, int count8) {
        return sample(Variable.intVariable(count1), Variable.intVariable(count2), Variable.intVariable(count3),
                Variable.intVariable(count4), Variable.intVariable(count5), Variable.intVariable(count6),
                Variable.intVariable(count7), Variable.intVariable(count8));
    }

    @Override
    public ArrayVariable<A> sample(IntVariable count) {
        return sample(count, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<A>> sample(IntVariable count1, IntVariable count2) {
        return sample(count1, count2, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3) {
        return sample(count1, count2, count3, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3, IntVariable count4) {
        return sample(count1, count2, count3, count4, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(IntVariable count1,
            IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5) {
        return sample(count1, count2, count3, count4, count5, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6) {
        return sample(count1, count2, count3, count4, count5, count6, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7) {
        return sample(count1, count2, count3, count4, count5, count6, count7, (Location) null);
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, IntVariable count8) {
        return sample(count1, count2, count3, count4, count5, count6, count7, count8, (Location) null);
    }

    @Override
    public ArrayVariable<A> sample(IntVariable count, Location location) {
        ArrayVariable<A> samples = Variable.arrayVariable(location, sampleType, count);
        parFor(Variable.intVariable(0, location), count, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<A>> sample(IntVariable count1, IntVariable count2, Location location) {
        ArrayVariable<ArrayVariable<A>> samples = Variable.arrayVariable(location, VariableType.arrayType(sampleType),
                count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<A>>> samples = Variable.arrayVariable(location,
                VariableType.arrayType(VariableType.arrayType(sampleType)), count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3, IntVariable count4, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> samples = Variable.arrayVariable(location,
                VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(sampleType))), count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, count4, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(IntVariable count1,
            IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> samples = Variable
                .arrayVariable(location,
                        VariableType.arrayType(
                                VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(sampleType)))),
                        count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, count4, count5, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> samples = Variable
                .arrayVariable(location,
                        VariableType.arrayType(VariableType.arrayType(
                                VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(sampleType))))),
                        count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, count4, count5, count6, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> samples = Variable
                .arrayVariable(location,
                        VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(
                                VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(sampleType)))))),
                        count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, count4, count5, count6, count7, location), location));
        return samples;
    }

    @Override
    public ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, IntVariable count8, Location location) {
        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> samples = Variable
                .arrayVariable(location,
                        VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(
                                VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(sampleType))))))),
                        count1);
        parFor(Variable.intVariable(0, location), count1, Variable.intVariable(1, location), true, location,
                (i) -> samples.put(i, sample(count2, count3, count4, count5, count6, count7, count8, location),
                        location));
        return samples;
    }

    @Override
    public void constructTrace(DAGInfo dagInfo) {
        dagInfo.addRandomVariable(this);
        constructTrace(dagInfo::addRandomChild);
    }

    @Override
    public RandomVariableType<A, B> getType() {
        return getParent().getOutputType();
    }
    
    @Override
    public RandomVariableConstructorTask<A, B> getParent() {
        return (RandomVariableConstructorTask<A, B>) super.getParent();
    }

    @Override
    public VariableType.Type<A> getSampleType() {
        return sampleType;
    }

    @Override
    public Scope getRandomScope(CompilationContext compilationCtx) {
        Scope randomScope = scope();
        while(randomScope != GlobalScope.scope && randomScope.isSerial(compilationCtx))
            randomScope = randomScope.getEnclosingScope();
        return randomScope;
    }

    /**
     * Method to determine if a value is sampled from this random variable.
     * 
     * @return
     */
    @Override
    public boolean valueSampled() {
        boolean sample = false;
        for(DataflowTask<?> t:getConsumers())
            sample = sample || !((ProducingDataflowTask<?>) t).isDistribution();
        return sample;
    }

    /**
     * method to determine if a distribution is sampled from this random variable.
     * 
     * @return
     */
    @Override
    public boolean distributionSampled() {
        boolean sample = false;
        for(DataflowTask<?> t:getConsumers())
            sample = sample || ((ProducingDataflowTask<?>) t).isDistribution();
        return sample;
    }
}
