/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.internal.model.variables.probability.SkippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.UnskippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.VariableProbability;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.RandomVariable;

public abstract class RandomVariableInternal implements HasProbabilityInternal, RandomVariable, CurrentProbability {

    private final VariableProbability prob;

    private final String name;
    private final Model model;

    public RandomVariableInternal(Model model, String name, ProbabilityType probType) {
        this.model = model;
        this.name = name;
        switch(probType) {
            case SKIPPABLE:
                prob = new SkippableProbability(this);
                break;
            case UNSKIPPABLE:
                prob = new UnskippableProbability(this);
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
        synchronized(model) {
            return prob.probabilityComputed();
        }
    }

    @Override
    public final double getProbability() {
        synchronized(model) {
            return prob.getProbability();
        }
    }

    @Override
    public final double getLogProbability() {
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
