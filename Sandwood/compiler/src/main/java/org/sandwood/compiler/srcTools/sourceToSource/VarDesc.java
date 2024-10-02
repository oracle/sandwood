/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

import java.util.List;

public record VarDesc(String type, List<Token> typeLocation, Token nameLocation, Token permissionsToken,
        String lastJavadoc) {}
