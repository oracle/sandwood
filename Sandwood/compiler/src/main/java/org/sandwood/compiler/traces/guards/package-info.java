/*
 * Sandwood
 *
 * Copyright (c) 2018-2023, Oracle and/or its affiliates. All rights reserved.
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
/**
 * Classes for constructing template code that will allow user code to execute only if there is a valid trace between 2
 * points in the dataflow graph. If there is a valid trace the user code will only execute once regardless of the number
 * of valid traces.
 * <P>
 * A trace is considered valid if each pairing of put and get indexes in the trace are equal. i.e. they are referring to
 * the same element in the array, and any conditional guards in the trace evaluate to the correct value.
 */
package org.sandwood.compiler.traces.guards;