/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputNewArray;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransNewArray<X extends Variable<X>> extends TransTreeReturn<ArrayVariable<X>> {

    private final List<TransTreeReturn<IntVariable>> lengths;
    private final ArrayType<X> varType;

    TransNewArray(List<TransTreeReturn<IntVariable>> lengths, ArrayType<X> varType) {
        super(TransTreeType.ALLOCATE_ARRAY, size(lengths));
        this.lengths = lengths;
        this.varType = varType;
    }

    private static int size(List<TransTreeReturn<IntVariable>> lengths) {
        int size = 1;
        for(TransTreeReturn<?> t:lengths)
            size += t.size();
        return size;
    }

    @Override
    public TransTree<?>[] getChildren() {
        TransTree<?>[] trees = new TransTree<?>[lengths.size()];
        lengths.toArray(trees);
        return trees;
    }

    @Override
    public String getDescription() {
        return "Construct array of varType:" + varType;
    }

    @Override
    public OutputNewArray<X> toOutputTreeReturnInternal() {
        List<OutputTreeReturn<IntVariable>> outputLengths = new ArrayList<>();
        for(TransTreeReturn<IntVariable> length:lengths)
            outputLengths.add(length.toOutputTreeReturnInternal());
        return OutputTree.newArray(outputLengths, varType);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        for(TransTreeReturn<IntVariable> tree:lengths)
            result = result * prime + tree.equivalentHashCode();
        result = prime * result + varType.hashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransNewArray<?> other = (TransNewArray<?>) tree;
        if(!varType.equals(other.varType))
            return false;
        if(lengths.size() != other.lengths.size())
            return false;
        for(int i = 0; i < lengths.size(); i++)
            if(!lengths.get(i).equivalent(other.lengths.get(i), substitutions))
                return false;
        return true;
    }

    @Override
    public TransTreeReturn<ArrayVariable<X>> applyTransformationInternal(Transformer t) {
        List<TransTreeReturn<IntVariable>> transformedLengths = new ArrayList<>();
        for(TransTreeReturn<IntVariable> length:lengths)
            transformedLengths.add(t.transform(length));
        return new TransNewArray<>(transformedLengths, varType);
    }

    @Override
    public Type<ArrayVariable<X>> getOutputType() {
        return varType;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        for(TransTreeReturn<IntVariable> length:lengths)
            v.visit(length);
    }

    @Override
    public TransTreeReturn<ArrayVariable<X>> maxValue(Bounds bounds) {
        throw new CompilerException("Max value is not supported on array constructors.");
    }

    @Override
    public TransTreeReturn<ArrayVariable<X>> minValue(Bounds bounds) {
        throw new CompilerException("min value is not supported on array constructors.");
    }
}
