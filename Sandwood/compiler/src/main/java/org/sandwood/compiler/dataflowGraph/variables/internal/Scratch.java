/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.internal;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.BaseType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

/**
 * An uninstantiatable class used to parameterize the generics on VariableDescription classes.
 * 
 * @author djgoodma
 * 
 *         TODO now that we have more classes that are only being consumed at the output stage and all we want for from
 *         them is a type, revisit the structure of variables so that these classes can be moved to the output tree
 *         stage of the compilation
 *
 */
public abstract class Scratch extends VariableImplementation<Scratch> implements ObjectVariable<Scratch> {
    
    public static final Type<Scratch> scratchType = new BaseType<>("null",
            "Scratch Space") {};

    private Scratch() {
        super(null);
    }

    @Override
    public Type<Scratch> getType() {
        return scratchType;
    }

    @Override
    public Scratch copy() {
        throw new SandwoodException("Copying of the internal random number generators is not expected.");
    }

    @Override
    public Scratch copy(Location location) {
        throw new SandwoodException("Copying of the internal random number generators is not expected.");
    }

}
