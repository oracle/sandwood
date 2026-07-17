/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;
import static org.sandwood.compiler.trees.transformationTree.TransTree.getField;
import static org.sandwood.compiler.trees.transformationTree.TransTree.setField;

import org.sandwood.compiler.dataflowGraph.variables.ClassVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.GlobalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.internal.Scratch;
import org.sandwood.compiler.dataflowGraph.variables.internal.State;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree.TreeLocation;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class AccessRedirection extends Transformer {

    private static final VariableDescription<State> stateName = VariableNames.stateName();
    private static final VariableDescription<Scratch> scratchName = VariableNames.scratchName();

    private final boolean useStateClass, useScratchClass;

    public AccessRedirection(TreeLocation treeLocation) {
        switch(treeLocation) {
            case CORE:
                useStateClass = true;
                useScratchClass = true;
                break;
            case SCRATCH:
                useStateClass = true;
                useScratchClass = false;
                break;
            case STATE:
                useStateClass = false;
                useScratchClass = false;
                break;
            case UNKNOWN:
                useStateClass = false;
                useScratchClass = false;
                break;
            default:
                throw new CompilerException("Unknown tree location " + treeLocation);
        }
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case STORE: {
                return processStore((TransStore<?>) tree);
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    private <X extends Variable<X>> TransTreeVoid processStore(TransStore<X> tree) {
        VariableDescription<X> varDesc = tree.varDesc;
        TransTreeReturn<X> value = transform(tree.value);
        String comment = tree.getComment();
        // TODO change this to a pattern matching switch once we move to Java 21.
        if(varDesc instanceof GlobalVariableDescription)
            return useStateClass ? setField(load(stateName), varDesc, value, comment) : store(varDesc, value, comment);
        else if(varDesc instanceof ClassVariableDescription)
            return useScratchClass ? setField(load(scratchName), varDesc, value, comment)
                    : store(varDesc, value, comment);
        else if(varDesc instanceof LocalVariableDescription)
            return store(varDesc, value, comment);
        else
            throw new CompilerException("Unexpected Variable Type");
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case LOAD: {
                TransLoad<X> load = (TransLoad<X>) tree;
                VariableDescription<X> varDesc = load.varDesc;
                // TODO change this to a pattern matching switch once we move to Java 21.
                if(varDesc instanceof GlobalVariableDescription)
                    return useStateClass ? getField(load(stateName), varDesc) : load(varDesc);
                else if(varDesc instanceof ClassVariableDescription)
                    return useScratchClass ? getField(load(scratchName), varDesc) : load(varDesc);
                else if(varDesc instanceof LocalVariableDescription)
                    return load(varDesc);
                else
                    throw new CompilerException("Unexpected Variable Type");
            }

            default:
                return tree.applyTransformation(this);
        }
    }

}
