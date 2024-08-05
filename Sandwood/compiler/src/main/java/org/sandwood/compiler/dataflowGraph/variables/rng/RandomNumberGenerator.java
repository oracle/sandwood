/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.rng;

import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;

/**
 * An uninstantiatable class used to parameterize the generics on VariableDescription classes.
 * 
 * @author djgoodma
 *
 */
public class RandomNumberGenerator extends VariableImplementation<RandomNumberGenerator> {

    private RandomNumberGenerator() {
        super(null);
        throw new CompilerException("RNG cannot be created.");
    }

    @Override
    public Type<RandomNumberGenerator> getType() {
        return VariableType.RNG;
    }

}
