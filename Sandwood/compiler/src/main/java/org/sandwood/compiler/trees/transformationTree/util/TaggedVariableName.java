/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.trees.TreeID;

/**
 * A class to wrap a variable name with a tag describing a specific instance of that variable in the tree.
 * 
 * @author djgoodma
 *
 */
public class TaggedVariableName {
    public final VariableName name;
    public final TreeID tag;

    public TaggedVariableName(VariableName name, TreeID tag) {
        this.name = name;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return name + "-" + tag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        TaggedVariableName other = (TaggedVariableName) obj;
        if(name == null) {
            if(other.name != null)
                return false;
        } else if(!name.equals(other.name))
            return false;
        if(tag == null)
            return other.tag == null;
        return tag.equals(other.tag);
    }
}
