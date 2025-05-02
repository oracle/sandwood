/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.exceptions.CompilerException;

public class DAGUtils {

    /**
     * Method to see if scope is nested in or equal to outerScope.
     * 
     * @param outerScope
     * @param scope
     * @return
     */
    public static boolean nestedScope(Scope outerScope, Scope scope) {
        while(scope != outerScope && scope != GlobalScope.scope)
            scope = scope.getEnclosingScope();
        return scope == outerScope;
    }

    /**
     * A method to determine if a task can be skipped due to the structure of the control flow during the execution of
     * the model. This will not evaluate a loop guard to determine if a loop will always execute.
     * 
     * @param task The task to test.
     * @return Returns true if it is possible to not execute the task during normal control flow.
     */
    public static boolean skipableTask(DataflowTask<?> task) {
        Scope s = task.scope();
        boolean constraint = false;
        while(s != GlobalScope.scope) {
            switch(s.getScopeType()) {
                case IF:
                case ELSE:
                case FOR:
                    constraint = true;
                    break;
                default:
                    break;
            }
            s = s.getEnclosingScope();
        }
        return constraint;
    }

    /**
     * Method to check if all the tasks in a set can be skipped. This will test if the individual tasks are skipable,
     * but will also test if tasks span both the if and the else branches of conditionals. This will not evaluated loop
     * guards to know if a loop will always execute.
     * 
     * @param tasks The set of tasks to test.
     * @return If it is possible to skip all the tasks in the set.
     */
    public static boolean skipable(Collection<DataflowTask<?>> tasks) {
        boolean loopFree = false;
        Set<Scope> ifElseScopes = new HashSet<>();
        Set<Scope> traceScopes = new HashSet<>();
        for(DataflowTask<?> task:tasks) {
            Scope s = task.scope();
            boolean includesFor = false;
            while(s != GlobalScope.scope) {
                switch(s.getScopeType()) {
                    case IF:
                    case ELSE:
                        traceScopes.add(s);
                        break;
                    case FOR:
                        includesFor = true;
                        break;
                    default:
                        break;
                }
                s = s.getEnclosingScope();
            }
            if(!includesFor) {
                loopFree = true;
                ifElseScopes.addAll(traceScopes);
                traceScopes.clear();
            }
        }

        if(!loopFree)
            return true;

        // Check if
        for(Scope s:ifElseScopes) {
            switch(s.getScopeType()) {
                case IF: {
                    IfScope f = (IfScope) s;
                    if(!ifElseScopes.contains(f.elseScope))
                        return true;
                    break;
                }
                case ELSE: {
                    ElseScope e = (ElseScope) s;
                    if(!ifElseScopes.contains(e.ifScope))
                        return true;
                    break;
                }
                default:
                    throw new CompilerException("Error unexpected scope type.");
            }
        }

        return false;
    }
}
