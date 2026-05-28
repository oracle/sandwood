package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.*;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.*;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.*;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.GeneratedAPIBuilder;
import org.sandwood.compiler.compilation.util.CompilationDesc;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class TerminalVariables extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable evidence = observeInt("evidence", location(3, 32, 3, 43));

        ArrayVariable<DoubleVariable> priors = ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> priors$ = Variable.arrayVariable(location(4, 23, 4, 33), VariableType.DoubleVariable, 2);
                priors$.put(intVariable(0, location(4, 23, 4, 23)), doubleVariable(.01, location(4, 24, 4, 26)), location(4, 23, 4, 26));
                priors$.put(intVariable(1, location(4, 23, 4, 23)), doubleVariable(0.99, location(4, 29, 4, 32)), location(4, 23, 4, 32));
                return priors$;
            });
        priors.setAlias("priors");
        priors.setLocation(location(4, 14, 4, 19));

        ArrayVariable<ArrayVariable<DoubleVariable>> conditionals = ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<ArrayVariable<DoubleVariable>> conditionals$ = Variable.arrayVariable(location(5, 31, 5, 46), VariableType.arrayType(VariableType.DoubleVariable), 2);
                ArrayVariable<DoubleVariable> conditionals$0 = ArrayVariable.getArrayVariable(() -> {
                        ArrayVariable<DoubleVariable> conditionals$0$ = Variable.arrayVariable(location(5, 32, 5, 37), VariableType.DoubleVariable, 2);
                        conditionals$0$.put(intVariable(0, location(5, 32, 5, 32)), intVariable(1, location(5, 33, 5, 33)), location(5, 32, 5, 33));
                        conditionals$0$.put(intVariable(1, location(5, 32, 5, 32)), intVariable(0, location(5, 36, 5, 36)), location(5, 32, 5, 36));
                        return conditionals$0$;
                    });
                conditionals$.put(intVariable(0, location(5, 31, 5, 31)), conditionals$0);

                ArrayVariable<DoubleVariable> conditionals$$1 = ArrayVariable.getArrayVariable(() -> {
                        ArrayVariable<DoubleVariable> conditionals$$1$ = Variable.arrayVariable(location(5, 40, 5, 45), VariableType.DoubleVariable, 2);
                        conditionals$$1$.put(intVariable(0, location(5, 40, 5, 40)), intVariable(0, location(5, 41, 5, 41)), location(5, 40, 5, 41));
                        conditionals$$1$.put(intVariable(1, location(5, 40, 5, 40)), intVariable(1, location(5, 44, 5, 44)), location(5, 40, 5, 44));
                        return conditionals$$1$;
                    });
                conditionals$.put(intVariable(1), conditionals$$1);
                return conditionals$;
            });
        conditionals.setAlias("conditionals");
        conditionals.setLocation(location(5, 16, 5, 27));

        IntVariable c1 = categorical(priors, location(7, 14, 7, 32)).sample(location(7, 34, 7, 41));
        c1.setAlias("c1");
        c1.setLocation(location(7, 9, 7, 10));

        IntVariable c2 = categorical(conditionals.get(c1, location(8, 38, 8, 41)), location(8, 14, 8, 42)).sample(location(8, 44, 8, 51));
        c2.setAlias("c2");
        c2.setLocation(location(8, 9, 8, 10));

        IntVariable c3 = categorical(priors, location(10, 14, 10, 32)).sample(location(10, 34, 10, 41));
        c3.setAlias("c3");
        c3.setLocation(location(10, 9, 10, 10));

        IntVariable c4 = categorical(conditionals.get(c3, location(11, 38, 11, 41)), location(11, 14, 11, 42)).sample(location(11, 44, 11, 51));
        c4.setAlias("c4");
        c4.setLocation(location(11, 9, 11, 10));

        IntVariable c5 = categorical(priors, location(13, 14, 13, 32)).sample(location(13, 34, 13, 41));
        c5.setAlias("c5");
        c5.setLocation(location(13, 9, 13, 10));

        IntVariable c6 = categorical(conditionals.get(c5, location(14, 38, 14, 41)), location(14, 14, 14, 42)).sample(location(14, 44, 14, 51));
        c6.setAlias("c6");
        c6.setLocation(location(14, 9, 14, 10));

        IntVariable c7 = categorical(priors, location(16, 14, 16, 32)).sample(location(16, 34, 16, 41));
        c7.setAlias("c7");
        c7.setLocation(location(16, 9, 16, 10));

        IntVariable c8 = categorical(conditionals.get(c7, location(17, 38, 17, 41)), location(17, 14, 17, 42)).sample(location(17, 44, 17, 51));
        c8.setAlias("c8");
        c8.setLocation(location(17, 9, 17, 10));

        IntVariable c9 = categorical(priors, location(19, 14, 19, 32)).sample(location(19, 34, 19, 41));
        c9.setAlias("c9");
        c9.setLocation(location(19, 9, 19, 10));

        IntVariable c10 = categorical(conditionals.get(c9, location(20, 39, 20, 42)), location(20, 15, 20, 43)).sample(location(20, 45, 20, 52));
        c10.setAlias("c10");
        c10.setLocation(location(20, 9, 20, 11));

        IntVariable c11 = categorical(priors, location(22, 15, 22, 33)).sample(location(22, 35, 22, 42));
        c11.setAlias("c11");
        c11.setLocation(location(22, 9, 22, 11));

        IntVariable c12 = categorical(conditionals.get(c11, location(23, 39, 23, 43)), location(23, 15, 23, 44)).sample(location(23, 46, 23, 53));
        c12.setAlias("c12");
        c12.setLocation(location(23, 9, 23, 11));

        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>>> a = ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>>> a$ = Variable.arrayVariable(location(25, 26, 38, 5), VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)))), 2);
                ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> a$0 = ArrayVariable.getArrayVariable(() -> {
                        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> a$0$ = Variable.arrayVariable(location(26, 8, 31, 8), VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable))), 2);
                        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$0$0 = ArrayVariable.getArrayVariable(() -> {
                                ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$0$0$ = Variable.arrayVariable(location(27, 9, 28, 99), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), 2);
                                ArrayVariable<ArrayVariable<DoubleVariable>> a$0$0$0 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$0$0$0$ = Variable.arrayVariable(location(27, 10, 27, 145), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$0$0$0$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$0$0$0$ = Variable.arrayVariable(location(27, 11, 27, 76), VariableType.DoubleVariable, 5);
                                                a$0$0$0$0$.put(intVariable(0, location(27, 11, 27, 11)), doubleVariable(0.3333333333333334, location(27, 12, 27, 29)), location(27, 11, 27, 29));
                                                a$0$0$0$0$.put(intVariable(1, location(27, 11, 27, 11)), doubleVariable(0.3333333333333334, location(27, 32, 27, 49)), location(27, 11, 27, 49));
                                                a$0$0$0$0$.put(intVariable(2, location(27, 11, 27, 11)), intVariable(0, location(27, 52, 27, 52)), location(27, 11, 27, 52));
                                                a$0$0$0$0$.put(intVariable(3, location(27, 11, 27, 11)), doubleVariable(0.3333333333333334, location(27, 55, 27, 72)), location(27, 11, 27, 72));
                                                a$0$0$0$0$.put(intVariable(4, location(27, 11, 27, 11)), intVariable(0, location(27, 75, 27, 75)), location(27, 11, 27, 75));
                                                return a$0$0$0$0$;
                                            });
                                        a$0$0$0$.put(intVariable(0, location(27, 10, 27, 10)), a$0$0$0$0);

                                        ArrayVariable<DoubleVariable> a$0$0$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$0$0$$1$ = Variable.arrayVariable(location(27, 79, 27, 144), VariableType.DoubleVariable, 5);
                                                a$0$0$0$$1$.put(intVariable(0, location(27, 79, 27, 79)), doubleVariable(0.3333333333333334, location(27, 80, 27, 97)), location(27, 79, 27, 97));
                                                a$0$0$0$$1$.put(intVariable(1, location(27, 79, 27, 79)), doubleVariable(0.3333333333333334, location(27, 100, 27, 117)), location(27, 79, 27, 117));
                                                a$0$0$0$$1$.put(intVariable(2, location(27, 79, 27, 79)), intVariable(0, location(27, 120, 27, 120)), location(27, 79, 27, 120));
                                                a$0$0$0$$1$.put(intVariable(3, location(27, 79, 27, 79)), doubleVariable(0.3333333333333334, location(27, 123, 27, 140)), location(27, 79, 27, 140));
                                                a$0$0$0$$1$.put(intVariable(4, location(27, 79, 27, 79)), intVariable(0, location(27, 143, 27, 143)), location(27, 79, 27, 143));
                                                return a$0$0$0$$1$;
                                            });
                                        a$0$0$0$.put(intVariable(1), a$0$0$0$$1);
                                        return a$0$0$0$;
                                    });
                                a$0$0$.put(intVariable(0, location(27, 9, 27, 9)), a$0$0$0);

                                ArrayVariable<ArrayVariable<DoubleVariable>> a$0$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$0$0$$1$ = Variable.arrayVariable(location(28, 10, 28, 98), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$0$0$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$0$$1$0$ = Variable.arrayVariable(location(28, 11, 28, 76), VariableType.DoubleVariable, 5);
                                                a$0$0$$1$0$.put(intVariable(0, location(28, 11, 28, 11)), doubleVariable(0.3333333333333334, location(28, 12, 28, 29)), location(28, 11, 28, 29));
                                                a$0$0$$1$0$.put(intVariable(1, location(28, 11, 28, 11)), doubleVariable(0.3333333333333334, location(28, 32, 28, 49)), location(28, 11, 28, 49));
                                                a$0$0$$1$0$.put(intVariable(2, location(28, 11, 28, 11)), intVariable(0, location(28, 52, 28, 52)), location(28, 11, 28, 52));
                                                a$0$0$$1$0$.put(intVariable(3, location(28, 11, 28, 11)), doubleVariable(0.3333333333333334, location(28, 55, 28, 72)), location(28, 11, 28, 72));
                                                a$0$0$$1$0$.put(intVariable(4, location(28, 11, 28, 11)), intVariable(0, location(28, 75, 28, 75)), location(28, 11, 28, 75));
                                                return a$0$0$$1$0$;
                                            });
                                        a$0$0$$1$.put(intVariable(0, location(28, 10, 28, 10)), a$0$0$$1$0);

                                        ArrayVariable<DoubleVariable> a$0$0$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$0$$1$$1$ = Variable.arrayVariable(location(28, 79, 28, 97), VariableType.DoubleVariable, 5);
                                                a$0$0$$1$$1$.put(intVariable(0, location(28, 79, 28, 79)), doubleVariable(0.5, location(28, 80, 28, 82)), location(28, 79, 28, 82));
                                                a$0$0$$1$$1$.put(intVariable(1, location(28, 79, 28, 79)), doubleVariable(0.5, location(28, 85, 28, 87)), location(28, 79, 28, 87));
                                                a$0$0$$1$$1$.put(intVariable(2, location(28, 79, 28, 79)), intVariable(0, location(28, 90, 28, 90)), location(28, 79, 28, 90));
                                                a$0$0$$1$$1$.put(intVariable(3, location(28, 79, 28, 79)), intVariable(0, location(28, 93, 28, 93)), location(28, 79, 28, 93));
                                                a$0$0$$1$$1$.put(intVariable(4, location(28, 79, 28, 79)), intVariable(0, location(28, 96, 28, 96)), location(28, 79, 28, 96));
                                                return a$0$0$$1$$1$;
                                            });
                                        a$0$0$$1$.put(intVariable(1), a$0$0$$1$$1);
                                        return a$0$0$$1$;
                                    });
                                a$0$0$.put(intVariable(1), a$0$0$$1);
                                return a$0$0$;
                            });
                        a$0$.put(intVariable(0, location(26, 8, 26, 8)), a$0$0);

                        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$0$$1$ = Variable.arrayVariable(location(29, 9, 30, 48), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), 2);
                                ArrayVariable<ArrayVariable<DoubleVariable>> a$0$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$0$$1$0$ = Variable.arrayVariable(location(29, 10, 29, 51), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$0$$1$0$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$$1$0$0$ = Variable.arrayVariable(location(29, 11, 29, 29), VariableType.DoubleVariable, 5);
                                                a$0$$1$0$0$.put(intVariable(0, location(29, 11, 29, 11)), intVariable(0, location(29, 12, 29, 12)), location(29, 11, 29, 12));
                                                a$0$$1$0$0$.put(intVariable(1, location(29, 11, 29, 11)), doubleVariable(0.5, location(29, 15, 29, 17)), location(29, 11, 29, 17));
                                                a$0$$1$0$0$.put(intVariable(2, location(29, 11, 29, 11)), intVariable(0, location(29, 20, 29, 20)), location(29, 11, 29, 20));
                                                a$0$$1$0$0$.put(intVariable(3, location(29, 11, 29, 11)), doubleVariable(0.5, location(29, 23, 29, 25)), location(29, 11, 29, 25));
                                                a$0$$1$0$0$.put(intVariable(4, location(29, 11, 29, 11)), intVariable(0, location(29, 28, 29, 28)), location(29, 11, 29, 28));
                                                return a$0$$1$0$0$;
                                            });
                                        a$0$$1$0$.put(intVariable(0, location(29, 10, 29, 10)), a$0$$1$0$0);

                                        ArrayVariable<DoubleVariable> a$0$$1$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$$1$0$$1$ = Variable.arrayVariable(location(29, 32, 29, 50), VariableType.DoubleVariable, 5);
                                                a$0$$1$0$$1$.put(intVariable(0, location(29, 32, 29, 32)), intVariable(0, location(29, 33, 29, 33)), location(29, 32, 29, 33));
                                                a$0$$1$0$$1$.put(intVariable(1, location(29, 32, 29, 32)), doubleVariable(0.5, location(29, 36, 29, 38)), location(29, 32, 29, 38));
                                                a$0$$1$0$$1$.put(intVariable(2, location(29, 32, 29, 32)), intVariable(0, location(29, 41, 29, 41)), location(29, 32, 29, 41));
                                                a$0$$1$0$$1$.put(intVariable(3, location(29, 32, 29, 32)), doubleVariable(0.5, location(29, 44, 29, 46)), location(29, 32, 29, 46));
                                                a$0$$1$0$$1$.put(intVariable(4, location(29, 32, 29, 32)), intVariable(0, location(29, 49, 29, 49)), location(29, 32, 29, 49));
                                                return a$0$$1$0$$1$;
                                            });
                                        a$0$$1$0$.put(intVariable(1), a$0$$1$0$$1);
                                        return a$0$$1$0$;
                                    });
                                a$0$$1$.put(intVariable(0, location(29, 9, 29, 9)), a$0$$1$0);

                                ArrayVariable<ArrayVariable<DoubleVariable>> a$0$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$0$$1$$1$ = Variable.arrayVariable(location(30, 10, 30, 47), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$0$$1$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$$1$$1$0$ = Variable.arrayVariable(location(30, 11, 30, 29), VariableType.DoubleVariable, 5);
                                                a$0$$1$$1$0$.put(intVariable(0, location(30, 11, 30, 11)), intVariable(0, location(30, 12, 30, 12)), location(30, 11, 30, 12));
                                                a$0$$1$$1$0$.put(intVariable(1, location(30, 11, 30, 11)), doubleVariable(0.5, location(30, 15, 30, 17)), location(30, 11, 30, 17));
                                                a$0$$1$$1$0$.put(intVariable(2, location(30, 11, 30, 11)), intVariable(0, location(30, 20, 30, 20)), location(30, 11, 30, 20));
                                                a$0$$1$$1$0$.put(intVariable(3, location(30, 11, 30, 11)), doubleVariable(0.5, location(30, 23, 30, 25)), location(30, 11, 30, 25));
                                                a$0$$1$$1$0$.put(intVariable(4, location(30, 11, 30, 11)), intVariable(0, location(30, 28, 30, 28)), location(30, 11, 30, 28));
                                                return a$0$$1$$1$0$;
                                            });
                                        a$0$$1$$1$.put(intVariable(0, location(30, 10, 30, 10)), a$0$$1$$1$0);

                                        ArrayVariable<DoubleVariable> a$0$$1$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$0$$1$$1$$1$ = Variable.arrayVariable(location(30, 32, 30, 46), VariableType.DoubleVariable, 5);
                                                a$0$$1$$1$$1$.put(intVariable(0, location(30, 32, 30, 32)), intVariable(0, location(30, 33, 30, 33)), location(30, 32, 30, 33));
                                                a$0$$1$$1$$1$.put(intVariable(1, location(30, 32, 30, 32)), intVariable(1, location(30, 36, 30, 36)), location(30, 32, 30, 36));
                                                a$0$$1$$1$$1$.put(intVariable(2, location(30, 32, 30, 32)), intVariable(0, location(30, 39, 30, 39)), location(30, 32, 30, 39));
                                                a$0$$1$$1$$1$.put(intVariable(3, location(30, 32, 30, 32)), intVariable(0, location(30, 42, 30, 42)), location(30, 32, 30, 42));
                                                a$0$$1$$1$$1$.put(intVariable(4, location(30, 32, 30, 32)), intVariable(0, location(30, 45, 30, 45)), location(30, 32, 30, 45));
                                                return a$0$$1$$1$$1$;
                                            });
                                        a$0$$1$$1$.put(intVariable(1), a$0$$1$$1$$1);
                                        return a$0$$1$$1$;
                                    });
                                a$0$$1$.put(intVariable(1), a$0$$1$$1);
                                return a$0$$1$;
                            });
                        a$0$.put(intVariable(1), a$0$$1);
                        return a$0$;
                    });
                a$.put(intVariable(0, location(25, 26, 25, 26)), a$0);

                ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> a$$1 = ArrayVariable.getArrayVariable(() -> {
                        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> a$$1$ = Variable.arrayVariable(location(32, 8, 37, 9), VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable))), 2);
                        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$$1$0$ = Variable.arrayVariable(location(33, 10, 34, 100), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), 2);
                                ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$0$0 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$0$0$ = Variable.arrayVariable(location(33, 11, 33, 99), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$$1$0$0$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$0$0$0$ = Variable.arrayVariable(location(33, 12, 33, 77), VariableType.DoubleVariable, 5);
                                                a$$1$0$0$0$.put(intVariable(0, location(33, 12, 33, 12)), doubleVariable(0.3333333333333334, location(33, 13, 33, 30)), location(33, 12, 33, 30));
                                                a$$1$0$0$0$.put(intVariable(1, location(33, 12, 33, 12)), doubleVariable(0.3333333333333334, location(33, 33, 33, 50)), location(33, 12, 33, 50));
                                                a$$1$0$0$0$.put(intVariable(2, location(33, 12, 33, 12)), intVariable(0, location(33, 53, 33, 53)), location(33, 12, 33, 53));
                                                a$$1$0$0$0$.put(intVariable(3, location(33, 12, 33, 12)), doubleVariable(0.3333333333333334, location(33, 56, 33, 73)), location(33, 12, 33, 73));
                                                a$$1$0$0$0$.put(intVariable(4, location(33, 12, 33, 12)), intVariable(0, location(33, 76, 33, 76)), location(33, 12, 33, 76));
                                                return a$$1$0$0$0$;
                                            });
                                        a$$1$0$0$.put(intVariable(0, location(33, 11, 33, 11)), a$$1$0$0$0);

                                        ArrayVariable<DoubleVariable> a$$1$0$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$0$0$$1$ = Variable.arrayVariable(location(33, 80, 33, 98), VariableType.DoubleVariable, 5);
                                                a$$1$0$0$$1$.put(intVariable(0, location(33, 80, 33, 80)), doubleVariable(0.5, location(33, 81, 33, 83)), location(33, 80, 33, 83));
                                                a$$1$0$0$$1$.put(intVariable(1, location(33, 80, 33, 80)), doubleVariable(0.5, location(33, 86, 33, 88)), location(33, 80, 33, 88));
                                                a$$1$0$0$$1$.put(intVariable(2, location(33, 80, 33, 80)), intVariable(0, location(33, 91, 33, 91)), location(33, 80, 33, 91));
                                                a$$1$0$0$$1$.put(intVariable(3, location(33, 80, 33, 80)), intVariable(0, location(33, 94, 33, 94)), location(33, 80, 33, 94));
                                                a$$1$0$0$$1$.put(intVariable(4, location(33, 80, 33, 80)), intVariable(0, location(33, 97, 33, 97)), location(33, 80, 33, 97));
                                                return a$$1$0$0$$1$;
                                            });
                                        a$$1$0$0$.put(intVariable(1), a$$1$0$0$$1);
                                        return a$$1$0$0$;
                                    });
                                a$$1$0$.put(intVariable(0, location(33, 10, 33, 10)), a$$1$0$0);

                                ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$0$$1$ = Variable.arrayVariable(location(34, 11, 34, 99), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$$1$0$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$0$$1$0$ = Variable.arrayVariable(location(34, 12, 34, 77), VariableType.DoubleVariable, 5);
                                                a$$1$0$$1$0$.put(intVariable(0, location(34, 12, 34, 12)), doubleVariable(0.3333333333333334, location(34, 13, 34, 30)), location(34, 12, 34, 30));
                                                a$$1$0$$1$0$.put(intVariable(1, location(34, 12, 34, 12)), doubleVariable(0.3333333333333334, location(34, 33, 34, 50)), location(34, 12, 34, 50));
                                                a$$1$0$$1$0$.put(intVariable(2, location(34, 12, 34, 12)), intVariable(0, location(34, 53, 34, 53)), location(34, 12, 34, 53));
                                                a$$1$0$$1$0$.put(intVariable(3, location(34, 12, 34, 12)), doubleVariable(0.3333333333333334, location(34, 56, 34, 73)), location(34, 12, 34, 73));
                                                a$$1$0$$1$0$.put(intVariable(4, location(34, 12, 34, 12)), intVariable(0, location(34, 76, 34, 76)), location(34, 12, 34, 76));
                                                return a$$1$0$$1$0$;
                                            });
                                        a$$1$0$$1$.put(intVariable(0, location(34, 11, 34, 11)), a$$1$0$$1$0);

                                        ArrayVariable<DoubleVariable> a$$1$0$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$0$$1$$1$ = Variable.arrayVariable(location(34, 80, 34, 98), VariableType.DoubleVariable, 5);
                                                a$$1$0$$1$$1$.put(intVariable(0, location(34, 80, 34, 80)), doubleVariable(0.5, location(34, 81, 34, 83)), location(34, 80, 34, 83));
                                                a$$1$0$$1$$1$.put(intVariable(1, location(34, 80, 34, 80)), doubleVariable(0.5, location(34, 86, 34, 88)), location(34, 80, 34, 88));
                                                a$$1$0$$1$$1$.put(intVariable(2, location(34, 80, 34, 80)), intVariable(0, location(34, 91, 34, 91)), location(34, 80, 34, 91));
                                                a$$1$0$$1$$1$.put(intVariable(3, location(34, 80, 34, 80)), intVariable(0, location(34, 94, 34, 94)), location(34, 80, 34, 94));
                                                a$$1$0$$1$$1$.put(intVariable(4, location(34, 80, 34, 80)), intVariable(0, location(34, 97, 34, 97)), location(34, 80, 34, 97));
                                                return a$$1$0$$1$$1$;
                                            });
                                        a$$1$0$$1$.put(intVariable(1), a$$1$0$$1$$1);
                                        return a$$1$0$$1$;
                                    });
                                a$$1$0$.put(intVariable(1), a$$1$0$$1);
                                return a$$1$0$;
                            });
                        a$$1$.put(intVariable(0, location(32, 8, 32, 8)), a$$1$0);

                        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> a$$1$$1$ = Variable.arrayVariable(location(35, 10, 36, 45), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), 2);
                                ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$$1$0$ = Variable.arrayVariable(location(35, 11, 35, 44), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$$1$$1$0$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$$1$0$0$ = Variable.arrayVariable(location(35, 12, 35, 26), VariableType.DoubleVariable, 5);
                                                a$$1$$1$0$0$.put(intVariable(0, location(35, 12, 35, 12)), intVariable(0, location(35, 13, 35, 13)), location(35, 12, 35, 13));
                                                a$$1$$1$0$0$.put(intVariable(1, location(35, 12, 35, 12)), intVariable(0, location(35, 16, 35, 16)), location(35, 12, 35, 16));
                                                a$$1$$1$0$0$.put(intVariable(2, location(35, 12, 35, 12)), intVariable(0, location(35, 19, 35, 19)), location(35, 12, 35, 19));
                                                a$$1$$1$0$0$.put(intVariable(3, location(35, 12, 35, 12)), intVariable(1, location(35, 22, 35, 22)), location(35, 12, 35, 22));
                                                a$$1$$1$0$0$.put(intVariable(4, location(35, 12, 35, 12)), intVariable(0, location(35, 25, 35, 25)), location(35, 12, 35, 25));
                                                return a$$1$$1$0$0$;
                                            });
                                        a$$1$$1$0$.put(intVariable(0, location(35, 11, 35, 11)), a$$1$$1$0$0);

                                        ArrayVariable<DoubleVariable> a$$1$$1$0$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$$1$0$$1$ = Variable.arrayVariable(location(35, 29, 35, 43), VariableType.DoubleVariable, 5);
                                                a$$1$$1$0$$1$.put(intVariable(0, location(35, 29, 35, 29)), intVariable(0, location(35, 30, 35, 30)), location(35, 29, 35, 30));
                                                a$$1$$1$0$$1$.put(intVariable(1, location(35, 29, 35, 29)), intVariable(0, location(35, 33, 35, 33)), location(35, 29, 35, 33));
                                                a$$1$$1$0$$1$.put(intVariable(2, location(35, 29, 35, 29)), intVariable(0, location(35, 36, 35, 36)), location(35, 29, 35, 36));
                                                a$$1$$1$0$$1$.put(intVariable(3, location(35, 29, 35, 29)), intVariable(1, location(35, 39, 35, 39)), location(35, 29, 35, 39));
                                                a$$1$$1$0$$1$.put(intVariable(4, location(35, 29, 35, 29)), intVariable(0, location(35, 42, 35, 42)), location(35, 29, 35, 42));
                                                return a$$1$$1$0$$1$;
                                            });
                                        a$$1$$1$0$.put(intVariable(1), a$$1$$1$0$$1);
                                        return a$$1$$1$0$;
                                    });
                                a$$1$$1$.put(intVariable(0, location(35, 10, 35, 10)), a$$1$$1$0);

                                ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                        ArrayVariable<ArrayVariable<DoubleVariable>> a$$1$$1$$1$ = Variable.arrayVariable(location(36, 11, 36, 44), VariableType.arrayType(VariableType.DoubleVariable), 2);
                                        ArrayVariable<DoubleVariable> a$$1$$1$$1$0 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$$1$$1$0$ = Variable.arrayVariable(location(36, 12, 36, 26), VariableType.DoubleVariable, 5);
                                                a$$1$$1$$1$0$.put(intVariable(0, location(36, 12, 36, 12)), intVariable(0, location(36, 13, 36, 13)), location(36, 12, 36, 13));
                                                a$$1$$1$$1$0$.put(intVariable(1, location(36, 12, 36, 12)), intVariable(0, location(36, 16, 36, 16)), location(36, 12, 36, 16));
                                                a$$1$$1$$1$0$.put(intVariable(2, location(36, 12, 36, 12)), intVariable(0, location(36, 19, 36, 19)), location(36, 12, 36, 19));
                                                a$$1$$1$$1$0$.put(intVariable(3, location(36, 12, 36, 12)), intVariable(1, location(36, 22, 36, 22)), location(36, 12, 36, 22));
                                                a$$1$$1$$1$0$.put(intVariable(4, location(36, 12, 36, 12)), intVariable(0, location(36, 25, 36, 25)), location(36, 12, 36, 25));
                                                return a$$1$$1$$1$0$;
                                            });
                                        a$$1$$1$$1$.put(intVariable(0, location(36, 11, 36, 11)), a$$1$$1$$1$0);

                                        ArrayVariable<DoubleVariable> a$$1$$1$$1$$1 = ArrayVariable.getArrayVariable(() -> {
                                                ArrayVariable<DoubleVariable> a$$1$$1$$1$$1$ = Variable.arrayVariable(location(36, 29, 36, 43), VariableType.DoubleVariable, 5);
                                                a$$1$$1$$1$$1$.put(intVariable(0, location(36, 29, 36, 29)), intVariable(0, location(36, 30, 36, 30)), location(36, 29, 36, 30));
                                                a$$1$$1$$1$$1$.put(intVariable(1, location(36, 29, 36, 29)), intVariable(0, location(36, 33, 36, 33)), location(36, 29, 36, 33));
                                                a$$1$$1$$1$$1$.put(intVariable(2, location(36, 29, 36, 29)), intVariable(0, location(36, 36, 36, 36)), location(36, 29, 36, 36));
                                                a$$1$$1$$1$$1$.put(intVariable(3, location(36, 29, 36, 29)), intVariable(0, location(36, 39, 36, 39)), location(36, 29, 36, 39));
                                                a$$1$$1$$1$$1$.put(intVariable(4, location(36, 29, 36, 29)), intVariable(1, location(36, 42, 36, 42)), location(36, 29, 36, 42));
                                                return a$$1$$1$$1$$1$;
                                            });
                                        a$$1$$1$$1$.put(intVariable(1), a$$1$$1$$1$$1);
                                        return a$$1$$1$$1$;
                                    });
                                a$$1$$1$.put(intVariable(1), a$$1$$1$$1);
                                return a$$1$$1$;
                            });
                        a$$1$.put(intVariable(1), a$$1$$1);
                        return a$$1$;
                    });
                a$.put(intVariable(1), a$$1);
                return a$;
            });
        a.setAlias("a");
        a.setLocation(location(25, 22, 25, 22));

        IntVariable terminalVariable = categorical(a.get(c5, location(39, 41, 39, 44)).get(c9, location(39, 45, 39, 48)).get(c1, location(39, 49, 39, 52)).get(c4, location(39, 53, 39, 56)), location(39, 28, 39, 57)).sample(location(39, 59, 39, 66));
        terminalVariable.setAlias("terminalVariable");
        terminalVariable.setLocation(location(39, 9, 39, 24));

        c2.observe(evidence, location(42, 8, 42, 24));

        Variable<?>[] $variableNames = {evidence, priors, conditionals, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, a, terminalVariable};
        String[] $constructorArgs = {"evidence"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "TerminalVariables", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model TerminalVariables(int evidence) {\n"
             + "    double[] priors = {.01, 0.99};\n"
             + "    double[][] conditionals = {{1, 0}, {0, 1}};\n"
             + "\n"
             + "    int c1 = categorical(priors).sample();\n"
             + "    int c2 = categorical(conditionals[c1]).sample();\n"
             + "    \n"
             + "    int c3 = categorical(priors).sample();\n"
             + "    int c4 = categorical(conditionals[c3]).sample();\n"
             + "    \n"
             + "    int c5 = categorical(priors).sample();\n"
             + "    int c6 = categorical(conditionals[c5]).sample();\n"
             + "\n"
             + "    int c7 = categorical(priors).sample();\n"
             + "    int c8 = categorical(conditionals[c7]).sample();\n"
             + "\n"
             + "    int c9 = categorical(priors).sample();\n"
             + "    int c10 = categorical(conditionals[c9]).sample();\n"
             + "    \n"
             + "    int c11 = categorical(priors).sample();\n"
             + "    int c12 = categorical(conditionals[c11]).sample();\n"
             + "\n"
             + "    double[][][][][] a = {\n"
             + "       {\n"
             + "        {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}},\n"
             + "         {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
             + "        {{{0, 0.5, 0, 0.5, 0}, {0, 0.5, 0, 0.5, 0}},\n"
             + "         {{0, 0.5, 0, 0.5, 0}, {0, 1, 0, 0, 0}}}\n"
             + "       },\n"
             + "       {\n"
             + "         {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}},\n"
             + "          {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
             + "         {{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}},\n"
             + "          {{0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}}\n"
             + "        }\n"
             + "    };\n"
             + "    int terminalVariable = categorical(a[c5][c9][c1][c4]).sample();\n"
             + "    \n"
             + "    // assert\n"
             + "    c2.observe(evidence);\n"
             + "}";
    }
}