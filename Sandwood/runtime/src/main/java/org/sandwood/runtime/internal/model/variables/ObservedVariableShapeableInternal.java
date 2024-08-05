/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import java.io.IOException;

import org.sandwood.runtime.internal.json.JsonEncoder;
import org.sandwood.runtime.model.variables.ObservedVariableShapeable;

public interface ObservedVariableShapeableInternal<A> extends ObservedVariableShapeable, ObservedVariableInternal {
    String shapePostfix = "$Shape";

    void shapeToJson(JsonEncoder e) throws IOException;

    void set(A value);
}
