/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.json;

import com.fasterxml.jackson.core.JsonFactory;

/**
 * A parent class to maintain a single instance of the parent factory as recommended by the Jackson documentation.
 */
public class Json {
    protected static final JsonFactory factory = new JsonFactory();
}
