/*
 * Sandwood
 *
 * Copyright (c) 2018-2023, Oracle and/or its affiliates. All rights reserved.
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
/**
 * Package for Random Variables. These classes are not added to the graph from user models, and cannot be instantiated.
 * They only exist to provide a type for other generic classes that can be instantiated. A restructuring of how the
 * variable types are parameterised will hopefully remove the need for this class replacing it with an entry in an
 * enumeration at some point in the future.
 */
package org.sandwood.compiler.dataflowGraph.variables.rng;