/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.variables.probability.IteratedSkippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.IteratedUnskippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.IteratedVariableProbability;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.IteratedRandomVariable;

public abstract class IteratedRandomVariableInternal<A>
        implements HasProbabilityInternal, IteratedRandomVariable<A>, IteratedCurrentProbability<A> {

    private final String name;
    private final Model model;

    private final IteratedVariableProbability<A> prob;

    public IteratedRandomVariableInternal(Model model, String name, int arrayDimensions, ProbabilityType probType) {
        this.name = name;
        this.model = model;
        switch(probType) {
            case SKIPPABLE:
                prob = new IteratedSkippableProbability<>(this, arrayDimensions);
                break;
            case UNSKIPPABLE:
                prob = new IteratedUnskippableProbability<>(this, arrayDimensions);
                break;
            default:
                throw new SandwoodRuntimeException("Unexpected probability type.");
        }
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final boolean probabilityComputed() {
        return prob.probabilityComputed();
    }

    @Override
    public final A getProbability() {
        synchronized(model) {
            return prob.getProbability();
        }
    }

    @Override
    public final A getLogProbability() {
        synchronized(model) {
            return prob.getLogProbability();
        }
    }

    @Override
    public final void startLogProbability() {
        prob.startLogProbability();
    }

    @Override
    public final void ingestLogProbability() {
        prob.ingestLogProbability();
    }

    @Override
    public final void completeLogProbability(int iterations) {
        prob.completeLogProbability(iterations);
    }
}