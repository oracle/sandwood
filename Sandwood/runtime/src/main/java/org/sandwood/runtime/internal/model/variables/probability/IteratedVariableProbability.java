/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

import org.sandwood.runtime.internal.model.variables.HasProbabilityInternal;
import org.sandwood.runtime.model.variables.HasIteratedProbability;

public interface IteratedVariableProbability<A> extends HasProbabilityInternal, HasIteratedProbability<A> {}
