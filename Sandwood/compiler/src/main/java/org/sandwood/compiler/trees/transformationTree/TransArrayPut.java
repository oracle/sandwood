/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputArrayPut;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransArrayPut<X extends Variable<X>> extends TransTreeVoid {

    public final TransTreeReturn<ArrayVariable<X>> array;
    public final TransTreeReturn<IntVariable> index;
    public final TransTreeReturn<X> value;

    TransArrayPut(TransTreeReturn<ArrayVariable<X>> array, TransTreeReturn<IntVariable> index, TransTreeReturn<X> value,
            String comment) {
        super(TransTreeType.ARRAY_PUT, array.size() + index.size() + value.size() + 1, comment);
        this.array = array;
        this.index = index;
        this.value = value;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { array, index, value };
    }

    @Override
    public String getDescription() {
        return "array[index] = value";
    }

    @Override
    public OutputArrayPut<X> toOutputTreeInternal() {
        return OutputTree.arrayPut(array.toOutputTreeReturnInternal(), index.toOutputTreeReturnInternal(),
                value.toOutputTreeReturnInternal(), comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + array.equivalentHashCode();
        result = prime * result + index.equivalentHashCode();
        result = prime * result + value.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransArrayPut<?> other = (TransArrayPut<?>) tree;
        if(!array.equivalent(other.array, substitutions))
            return false;
        if(!index.equivalent(other.index, substitutions))
            return false;
        return value.equivalent(other.value, substitutions);
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return new TransArrayPut<>(t.transform(array), t.transform(index), t.transform(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(array);
        v.visit(index);
        v.visit(value);
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }
}
