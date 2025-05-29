package com.oracle.labs.east.ed;

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

public class EDVolumes_v3_additive_distributed extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 {
        IntVariable dep_vec1;
    }

    private static class $IfElseMods2 {
        IntVariable dep_vec2;
    }

    private static class $IfElseMods3 {
        IntVariable dep_vec3;
    }

    private static class $IfElseMods4 {
        IntVariable dep_vec4;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<IntVariable>> ObsV_c1 = observeArray("ObsV_c1", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(9, 48, 9, 62));
        ArrayVariable<ArrayVariable<IntVariable>> ObsV_c2 = observeArray("ObsV_c2", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(9, 65, 9, 79));
        ArrayVariable<ArrayVariable<IntVariable>> ObsArr = observeArray("ObsArr", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(9, 82, 9, 95));
        ArrayVariable<ArrayVariable<DoubleVariable>> TimeFeat = observeArray("TimeFeat", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(9, 98, 9, 116));

        IntVariable T = ObsV_c1.length(location(11, 21, 11, 26));
        T.setAlias("T");
        T.setLocation(location(11, 9, 11, 9));

        IntVariable n_ac = ObsV_c1.get(intVariable(0, location(12, 24, 12, 24)), location(12, 23, 12, 25)).length(location(12, 27, 12, 32));
        n_ac.setAlias("n_ac");
        n_ac.setLocation(location(12, 9, 12, 12));

        IntVariable time_dim = TimeFeat.get(intVariable(0, location(13, 29, 13, 29)), location(13, 28, 13, 30)).length(location(13, 32, 13, 37));
        time_dim.setAlias("time_dim");
        time_dim.setLocation(location(13, 9, 13, 16));

        ArrayVariable<DoubleVariable> beta = beta(intVariable(1, location(17, 26, 17, 26)), intVariable(1, location(17, 28, 17, 28)), location(17, 21, 17, 29)).sample(n_ac, location(17, 31, 17, 42));
        beta.setAlias("beta");
        beta.setLocation(location(17, 14, 17, 17));

        ArrayVariable<DoubleVariable> gamma = beta(intVariable(1, location(18, 27, 18, 27)), intVariable(1, location(18, 29, 18, 29)), location(18, 22, 18, 30)).sample(n_ac, location(18, 32, 18, 43));
        gamma.setAlias("gamma");
        gamma.setLocation(location(18, 14, 18, 18));

        ArrayVariable<ArrayVariable<DoubleVariable>> time_coeff = Variable.arrayVariable(location(20, 39, 20, 54), VariableType.arrayType(VariableType.DoubleVariable), n_ac, time_dim);
        time_coeff.setAlias("time_coeff");
        time_coeff.setLocation(location(20, 16, 20, 25));

        parFor(intVariable(0, location(21, 19, 21, 19)), n_ac, intVariable(1, location(21, 18, 21, 21)), true, location(21, 5, 21, 27), (i) -> {
            i.setAlias("i");
            i.setLocation(location(21, 14, 21, 14));
            time_coeff.put(i, gaussian(intVariable(0, location(22, 34, 22, 34)), intVariable(1, location(22, 36, 22, 36)), location(22, 25, 22, 37)).sample(time_dim, location(22, 39, 22, 54)), location(22, 19, 22, 54));

        });

        ArrayVariable<DoubleVariable> dep_coeff = gaussian(intVariable(0, location(25, 35, 25, 35)), intVariable(1, location(25, 37, 25, 37)), location(25, 26, 25, 38)).sample(time_dim, location(25, 40, 25, 55));
        dep_coeff.setAlias("dep_coeff");
        dep_coeff.setLocation(location(25, 14, 25, 22));

        ArrayVariable<ArrayVariable<IntVariable>> vol_c1 = Variable.arrayVariable(location(27, 29, 27, 37), VariableType.arrayType(VariableType.IntVariable), T, n_ac);
        vol_c1.setAlias("vol_c1");
        vol_c1.setLocation(location(27, 13, 27, 18));

        ArrayVariable<ArrayVariable<IntVariable>> vol_c2 = Variable.arrayVariable(location(28, 29, 28, 37), VariableType.arrayType(VariableType.IntVariable), T, n_ac);
        vol_c2.setAlias("vol_c2");
        vol_c2.setLocation(location(28, 13, 28, 18));

        ArrayVariable<ArrayVariable<IntVariable>> arr = Variable.arrayVariable(location(29, 26, 29, 34), VariableType.arrayType(VariableType.IntVariable), T, n_ac);
        arr.setAlias("arr");
        arr.setLocation(location(29, 13, 29, 15));

        parFor(intVariable(0, location(33, 19, 33, 19)), n_ac, intVariable(1, location(33, 18, 33, 21)), true, location(33, 5, 33, 27), (i) -> {
            i.setAlias("i");
            i.setLocation(location(33, 14, 33, 14));
            vol_c1.get(intVariable(0, location(34, 16, 34, 16)), location(34, 15, 34, 17)).put(i, poisson(ObsV_c1.get(intVariable(0, location(34, 40, 34, 40)), location(34, 39, 34, 41)).get(i, location(34, 42, 34, 44)), location(34, 24, 34, 45)).sample(location(34, 47, 34, 54)), location(34, 18, 34, 54));
            vol_c2.get(intVariable(0, location(35, 16, 35, 16)), location(35, 15, 35, 17)).put(i, poisson(ObsV_c2.get(intVariable(0, location(35, 40, 35, 40)), location(35, 39, 35, 41)).get(i, location(35, 42, 35, 44)), location(35, 24, 35, 45)).sample(location(35, 47, 35, 54)), location(35, 18, 35, 54));
            ArrayVariable<DoubleVariable> time_impact_0 = Variable.arrayVariable(location(37, 44, 37, 53), VariableType.DoubleVariable, time_dim);
            time_impact_0.setAlias("time_impact_0");
            time_impact_0.setLocation(location(37, 18, 37, 30));

            parFor(intVariable(0, location(38, 23, 38, 23)), time_dim, intVariable(1, location(38, 22, 38, 25)), true, location(38, 9, 38, 35), (j) -> {
                j.setAlias("j");
                j.setLocation(location(38, 18, 38, 18));
                time_impact_0.put(j, TimeFeat.get(intVariable(0, location(39, 41, 39, 41)), location(39, 40, 39, 42)).get(j, location(39, 43, 39, 45)).times(time_coeff.get(i, location(39, 57, 39, 59)).get(j, location(39, 60, 39, 62)), location(39, 46, 39, 46)), location(39, 26, 39, 46));

            });

            DoubleVariable t_0 = reduce(time_impact_0, intVariable(0, location(41, 44, 41, 44)), location(41, 22, 41, 74), (x, y) -> {
                x.setAlias("x");
                x.setLocation(location(41, 48, 41, 48));
                y.setAlias("y");
                y.setLocation(location(41, 51, 41, 51));
                return x.add(y, location(41, 68, 41, 68));
            });
            t_0.setAlias("t_0");
            t_0.setLocation(location(41, 16, 41, 18));

            arr.get(intVariable(0, location(42, 13, 42, 13)), location(42, 12, 42, 14)).put(i, poisson(exp(t_0, location(42, 27, 42, 34)), location(42, 19, 42, 35)).sample(location(42, 37, 42, 44)), location(42, 15, 42, 44));

        });

        parFor(intVariable(0, location(47, 19, 47, 19)).add(intVariable(1, location(47, 18, 47, 21)), location(47, 18, 47, 21)), T, intVariable(1, location(47, 18, 47, 21)), true, location(47, 5, 47, 24), (t) -> {
            t.setAlias("t");
            t.setLocation(location(47, 14, 47, 14));
            ArrayVariable<DoubleVariable> dep_impact = Variable.arrayVariable(location(51, 41, 51, 50), VariableType.DoubleVariable, time_dim);
            dep_impact.setAlias("dep_impact");
            dep_impact.setLocation(location(51, 18, 51, 27));

            parFor(intVariable(0, location(52, 23, 52, 23)), time_dim, intVariable(1, location(52, 22, 52, 25)), true, location(52, 9, 52, 35), (k) -> {
                k.setAlias("k");
                k.setLocation(location(52, 18, 52, 18));
                dep_impact.put(k, TimeFeat.get(t, location(53, 37, 53, 39)).get(k, location(53, 40, 53, 42)).times(dep_coeff.get(k, location(53, 53, 53, 55)), location(53, 43, 53, 43)), location(53, 23, 53, 43));

            });

            DoubleVariable sum_t2 = reduce(dep_impact, intVariable(0, location(56, 44, 56, 44)), location(56, 25, 56, 74), (x, y) -> {
                x.setAlias("x");
                x.setLocation(location(56, 48, 56, 48));
                y.setAlias("y");
                y.setLocation(location(56, 51, 56, 51));
                return x.add(y, location(56, 68, 56, 68));
            });
            sum_t2.setAlias("sum_t2");
            sum_t2.setLocation(location(56, 16, 56, 21));

            IntVariable dep_vec0 = poisson(exp(sum_t2, location(57, 32, 57, 42)), location(57, 24, 57, 43)).sample(location(57, 45, 57, 52));
            dep_vec0.setAlias("dep_vec0");
            dep_vec0.setLocation(location(57, 13, 57, 20));

            parFor(intVariable(0, location(59, 23, 59, 23)), n_ac, intVariable(1, location(59, 22, 59, 25)), true, location(59, 9, 59, 31), (i) -> {
                i.setAlias("i");
                i.setLocation(location(59, 18, 59, 18));
                ArrayVariable<DoubleVariable> time_impact = Variable.arrayVariable(location(61, 46, 61, 55), VariableType.DoubleVariable, time_dim);
                time_impact.setAlias("time_impact");
                time_impact.setLocation(location(61, 22, 61, 32));

                parFor(intVariable(0, location(62, 27, 62, 27)), time_dim, intVariable(1, location(62, 26, 62, 29)), true, location(62, 13, 62, 39), (j) -> {
                    j.setAlias("j");
                    j.setLocation(location(62, 22, 62, 22));
                    time_impact.put(j, TimeFeat.get(t, location(63, 42, 63, 44)).get(j, location(63, 45, 63, 47)).times(time_coeff.get(i, location(63, 59, 63, 61)).get(j, location(63, 62, 63, 64)), location(63, 48, 63, 48)), location(63, 28, 63, 48));

                });

                DoubleVariable sum_t = reduce(time_impact, intVariable(0, location(66, 48, 66, 48)), location(66, 28, 66, 78), (x, y) -> {
                    x.setAlias("x");
                    x.setLocation(location(66, 52, 66, 52));
                    y.setAlias("y");
                    y.setLocation(location(66, 55, 66, 55));
                    return x.add(y, location(66, 72, 66, 72));
                });
                sum_t.setAlias("sum_t");
                sum_t.setLocation(location(66, 20, 66, 24));

                arr.get(t, location(67, 16, 67, 18)).put(i, poisson(exp(sum_t, location(67, 33, 67, 42)), location(67, 25, 67, 43)).sample(location(67, 45, 67, 52)), location(67, 19, 67, 52));

            });

            ArrayVariable<IntVariable> temp = Variable.arrayVariable(location(70, 29, 70, 34), VariableType.IntVariable, n_ac);
            temp.setAlias("temp");
            temp.setLocation(location(70, 15, 70, 18));

            IntVariable dep_vec1;
            $IfElseMods1 $if1 = new $IfElseMods1();
            $IfElseMods1 $else1 = new $IfElseMods1();
            BooleanVariable guard$1 = ObsV_c1.get(t.subtract(intVariable(1, location(74, 23, 74, 23)), location(74, 22, 74, 22)), location(74, 20, 74, 24)).get(intVariable(0, location(74, 26, 74, 26)), location(74, 25, 74, 27)).add(arr.get(t.subtract(intVariable(1, location(74, 37, 74, 37)), location(74, 36, 74, 36)), location(74, 34, 74, 38)).get(intVariable(0, location(74, 40, 74, 40)), location(74, 39, 74, 41)), location(74, 29, 74, 29)).subtract(dep_vec0, location(74, 43, 74, 43)).greaterThan(intVariable(0, location(74, 56, 74, 56)), location(74, 54, 74, 54));
            IfScope ifScope$1 = ifElse(guard$1, () -> {
                temp.put(intVariable(0, location(75, 18, 75, 18)), poisson(ObsV_c1.get(t.subtract(intVariable(1, location(75, 41, 75, 41)), location(75, 40, 75, 40)), location(75, 38, 75, 42)).get(intVariable(0, location(75, 44, 75, 44)), location(75, 43, 75, 45)).add(arr.get(t.subtract(intVariable(1, location(75, 55, 75, 55)), location(75, 54, 75, 54)), location(75, 52, 75, 56)).get(intVariable(0, location(75, 58, 75, 58)), location(75, 57, 75, 59)), location(75, 47, 75, 47)).subtract(dep_vec0, location(75, 61, 75, 61)), location(75, 23, 75, 71)).sample(location(75, 73, 75, 80)), location(75, 17, 75, 80));
                $if1.dep_vec1 = intVariable(0, location(76, 24, 76, 24));

            }, () -> {
                temp.put(intVariable(0, location(79, 18, 79, 18)), poisson(doubleVariable(0.1, location(79, 31, 79, 33)), location(79, 23, 79, 34)).sample(location(79, 36, 79, 43)), location(79, 17, 79, 43));
                $else1.dep_vec1 = dep_vec0.subtract(ObsV_c1.get(t.subtract(intVariable(1, location(80, 45, 80, 45)), location(80, 44, 80, 44)), location(80, 42, 80, 46)).get(intVariable(0, location(80, 48, 80, 48)), location(80, 47, 80, 49)), location(80, 33, 80, 33)).subtract(arr.get(t.subtract(intVariable(1, location(80, 60, 80, 60)), location(80, 59, 80, 59)), location(80, 57, 80, 61)).get(intVariable(0, location(80, 63, 80, 63)), location(80, 62, 80, 64)), location(80, 51, 80, 51));

            });
            dep_vec1 = ifElseAssignment(guard$1, $if1.dep_vec1, $else1.dep_vec1, ifScope$1, location(74, 9, 74, 57));
            dep_vec1.setAlias("dep_vec1");
            dep_vec1.setLocation(location(73, 13, 73, 20));

            IntVariable dep_vec2;
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = ObsV_c1.get(t.subtract(intVariable(1, location(85, 23, 85, 23)), location(85, 22, 85, 22)), location(85, 20, 85, 24)).get(intVariable(1, location(85, 26, 85, 26)), location(85, 25, 85, 27)).add(arr.get(t.subtract(intVariable(1, location(85, 37, 85, 37)), location(85, 36, 85, 36)), location(85, 34, 85, 38)).get(intVariable(1, location(85, 40, 85, 40)), location(85, 39, 85, 41)), location(85, 29, 85, 29)).subtract(dep_vec1, location(85, 43, 85, 43)).greaterThan(intVariable(0, location(85, 56, 85, 56)), location(85, 54, 85, 54));
            IfScope ifScope$2 = ifElse(guard$2, () -> {
                temp.put(intVariable(1, location(86, 18, 86, 18)), poisson(ObsV_c1.get(t.subtract(intVariable(1, location(86, 41, 86, 41)), location(86, 40, 86, 40)), location(86, 38, 86, 42)).get(intVariable(1, location(86, 44, 86, 44)), location(86, 43, 86, 45)).add(arr.get(t.subtract(intVariable(1, location(86, 55, 86, 55)), location(86, 54, 86, 54)), location(86, 52, 86, 56)).get(intVariable(1, location(86, 58, 86, 58)), location(86, 57, 86, 59)), location(86, 47, 86, 47)).subtract(dep_vec1, location(86, 61, 86, 61)), location(86, 23, 86, 71)).sample(location(86, 73, 86, 80)), location(86, 17, 86, 80));
                $if2.dep_vec2 = intVariable(0, location(87, 24, 87, 24));

            }, () -> {
                temp.put(intVariable(1, location(90, 18, 90, 18)), poisson(doubleVariable(0.1, location(90, 31, 90, 33)), location(90, 23, 90, 34)).sample(location(90, 36, 90, 43)), location(90, 17, 90, 43));
                $else2.dep_vec2 = dep_vec1.subtract(ObsV_c1.get(t.subtract(intVariable(1, location(91, 45, 91, 45)), location(91, 44, 91, 44)), location(91, 42, 91, 46)).get(intVariable(1, location(91, 48, 91, 48)), location(91, 47, 91, 49)), location(91, 33, 91, 33)).subtract(arr.get(t.subtract(intVariable(1, location(91, 60, 91, 60)), location(91, 59, 91, 59)), location(91, 57, 91, 61)).get(intVariable(1, location(91, 63, 91, 63)), location(91, 62, 91, 64)), location(91, 51, 91, 51));

            });
            dep_vec2 = ifElseAssignment(guard$2, $if2.dep_vec2, $else2.dep_vec2, ifScope$2, location(85, 9, 85, 57));
            dep_vec2.setAlias("dep_vec2");
            dep_vec2.setLocation(location(84, 13, 84, 20));

            IntVariable dep_vec3;
            $IfElseMods3 $if3 = new $IfElseMods3();
            $IfElseMods3 $else3 = new $IfElseMods3();
            BooleanVariable guard$3 = ObsV_c1.get(t.subtract(intVariable(1, location(96, 23, 96, 23)), location(96, 22, 96, 22)), location(96, 20, 96, 24)).get(intVariable(2, location(96, 26, 96, 26)), location(96, 25, 96, 27)).add(arr.get(t.subtract(intVariable(1, location(96, 37, 96, 37)), location(96, 36, 96, 36)), location(96, 34, 96, 38)).get(intVariable(2, location(96, 40, 96, 40)), location(96, 39, 96, 41)), location(96, 29, 96, 29)).subtract(dep_vec2, location(96, 43, 96, 43)).greaterThan(intVariable(0, location(96, 56, 96, 56)), location(96, 54, 96, 54));
            IfScope ifScope$3 = ifElse(guard$3, () -> {
                temp.put(intVariable(2, location(97, 18, 97, 18)), poisson(ObsV_c1.get(t.subtract(intVariable(1, location(97, 41, 97, 41)), location(97, 40, 97, 40)), location(97, 38, 97, 42)).get(intVariable(2, location(97, 44, 97, 44)), location(97, 43, 97, 45)).add(arr.get(t.subtract(intVariable(1, location(97, 55, 97, 55)), location(97, 54, 97, 54)), location(97, 52, 97, 56)).get(intVariable(2, location(97, 58, 97, 58)), location(97, 57, 97, 59)), location(97, 47, 97, 47)).subtract(dep_vec2, location(97, 61, 97, 61)), location(97, 23, 97, 71)).sample(location(97, 73, 97, 80)), location(97, 17, 97, 80));
                $if3.dep_vec3 = intVariable(0, location(98, 24, 98, 24));

            }, () -> {
                temp.put(intVariable(2, location(101, 18, 101, 18)), poisson(doubleVariable(0.1, location(101, 31, 101, 33)), location(101, 23, 101, 34)).sample(location(101, 36, 101, 43)), location(101, 17, 101, 43));
                $else3.dep_vec3 = dep_vec2.subtract(ObsV_c1.get(t.subtract(intVariable(1, location(102, 45, 102, 45)), location(102, 44, 102, 44)), location(102, 42, 102, 46)).get(intVariable(2, location(102, 48, 102, 48)), location(102, 47, 102, 49)), location(102, 33, 102, 33)).subtract(arr.get(t.subtract(intVariable(1, location(102, 60, 102, 60)), location(102, 59, 102, 59)), location(102, 57, 102, 61)).get(intVariable(2, location(102, 63, 102, 63)), location(102, 62, 102, 64)), location(102, 51, 102, 51));

            });
            dep_vec3 = ifElseAssignment(guard$3, $if3.dep_vec3, $else3.dep_vec3, ifScope$3, location(96, 9, 96, 57));
            dep_vec3.setAlias("dep_vec3");
            dep_vec3.setLocation(location(95, 13, 95, 20));

            IntVariable dep_vec4;
            $IfElseMods4 $if4 = new $IfElseMods4();
            $IfElseMods4 $else4 = new $IfElseMods4();
            BooleanVariable guard$4 = ObsV_c1.get(t.subtract(intVariable(1, location(107, 23, 107, 23)), location(107, 22, 107, 22)), location(107, 20, 107, 24)).get(intVariable(3, location(107, 26, 107, 26)), location(107, 25, 107, 27)).add(arr.get(t.subtract(intVariable(1, location(107, 37, 107, 37)), location(107, 36, 107, 36)), location(107, 34, 107, 38)).get(intVariable(3, location(107, 40, 107, 40)), location(107, 39, 107, 41)), location(107, 29, 107, 29)).subtract(dep_vec3, location(107, 43, 107, 43)).greaterThan(intVariable(0, location(107, 56, 107, 56)), location(107, 54, 107, 54));
            IfScope ifScope$4 = ifElse(guard$4, () -> {
                temp.put(intVariable(3, location(108, 18, 108, 18)), poisson(ObsV_c1.get(t.subtract(intVariable(1, location(108, 41, 108, 41)), location(108, 40, 108, 40)), location(108, 38, 108, 42)).get(intVariable(3, location(108, 44, 108, 44)), location(108, 43, 108, 45)).add(arr.get(t.subtract(intVariable(1, location(108, 55, 108, 55)), location(108, 54, 108, 54)), location(108, 52, 108, 56)).get(intVariable(3, location(108, 58, 108, 58)), location(108, 57, 108, 59)), location(108, 47, 108, 47)).subtract(dep_vec3, location(108, 61, 108, 61)), location(108, 23, 108, 71)).sample(location(108, 73, 108, 80)), location(108, 17, 108, 80));
                $if4.dep_vec4 = intVariable(0, location(109, 24, 109, 24));

            }, () -> {
                temp.put(intVariable(3, location(112, 18, 112, 18)), poisson(doubleVariable(0.1, location(112, 31, 112, 33)), location(112, 23, 112, 34)).sample(location(112, 36, 112, 43)), location(112, 17, 112, 43));
                $else4.dep_vec4 = dep_vec3.subtract(ObsV_c1.get(t.subtract(intVariable(1, location(113, 45, 113, 45)), location(113, 44, 113, 44)), location(113, 42, 113, 46)).get(intVariable(3, location(113, 48, 113, 48)), location(113, 47, 113, 49)), location(113, 33, 113, 33)).subtract(arr.get(t.subtract(intVariable(1, location(113, 60, 113, 60)), location(113, 59, 113, 59)), location(113, 57, 113, 61)).get(intVariable(3, location(113, 63, 113, 63)), location(113, 62, 113, 64)), location(113, 51, 113, 51));

            });
            dep_vec4 = ifElseAssignment(guard$4, $if4.dep_vec4, $else4.dep_vec4, ifScope$4, location(107, 9, 107, 57));
            dep_vec4.setAlias("dep_vec4");
            dep_vec4.setLocation(location(106, 13, 106, 20));

            BooleanVariable guard$5 = ObsV_c1.get(t.subtract(intVariable(1, location(117, 23, 117, 23)), location(117, 22, 117, 22)), location(117, 20, 117, 24)).get(intVariable(4, location(117, 26, 117, 26)), location(117, 25, 117, 27)).add(arr.get(t.subtract(intVariable(1, location(117, 37, 117, 37)), location(117, 36, 117, 36)), location(117, 34, 117, 38)).get(intVariable(4, location(117, 40, 117, 40)), location(117, 39, 117, 41)), location(117, 29, 117, 29)).subtract(dep_vec4, location(117, 43, 117, 43)).greaterThan(intVariable(0, location(117, 56, 117, 56)), location(117, 54, 117, 54));
            IfScope ifScope$5 = ifElse(guard$5, () -> {
                temp.put(intVariable(4, location(118, 18, 118, 18)), poisson(ObsV_c1.get(t.subtract(intVariable(1, location(118, 41, 118, 41)), location(118, 40, 118, 40)), location(118, 38, 118, 42)).get(intVariable(4, location(118, 44, 118, 44)), location(118, 43, 118, 45)).add(arr.get(t.subtract(intVariable(1, location(118, 55, 118, 55)), location(118, 54, 118, 54)), location(118, 52, 118, 56)).get(intVariable(4, location(118, 58, 118, 58)), location(118, 57, 118, 59)), location(118, 47, 118, 47)).subtract(dep_vec4, location(118, 61, 118, 61)), location(118, 23, 118, 71)).sample(location(118, 73, 118, 80)), location(118, 17, 118, 80));

            }, () -> {
                temp.put(intVariable(4, location(121, 18, 121, 18)), poisson(doubleVariable(0.1, location(121, 31, 121, 33)), location(121, 23, 121, 34)).sample(location(121, 36, 121, 43)), location(121, 17, 121, 43));

            });
            parFor(intVariable(0, location(124, 23, 124, 23)), n_ac, intVariable(1, location(124, 22, 124, 25)), true, location(124, 9, 124, 31), (i) -> {
                i.setAlias("i");
                i.setLocation(location(124, 18, 124, 18));
                vol_c1.get(t, location(125, 19, 125, 21)).put(i, poisson(temp.get(i, location(125, 40, 125, 42)), location(125, 28, 125, 43)).sample(location(125, 45, 125, 52)), location(125, 22, 125, 52));
                vol_c2.get(t, location(126, 19, 126, 21)).put(i, poisson(beta.get(i, location(126, 40, 126, 42)).times(ObsV_c2.get(t.subtract(intVariable(1, location(126, 54, 126, 54)), location(126, 53, 126, 53)), location(126, 51, 126, 55)).get(i, location(126, 56, 126, 58)), location(126, 43, 126, 43)).add(gamma.get(i, location(126, 67, 126, 69)).times(ObsV_c1.get(t.subtract(intVariable(1, location(126, 82, 126, 82)), location(126, 81, 126, 81)), location(126, 79, 126, 83)).get(i, location(126, 84, 126, 86)), location(126, 70, 126, 70)), location(126, 60, 126, 60)), location(126, 28, 126, 87)).sample(location(126, 89, 126, 96)), location(126, 22, 126, 96));

            });


        });

        vol_c1.observe(ObsV_c1, location(131, 12, 131, 27));
        vol_c2.observe(ObsV_c2, location(132, 12, 132, 27));
        arr.observe(ObsArr, location(133, 9, 133, 23));

        Variable<?>[] $variableNames = {ObsV_c1, ObsV_c2, ObsArr, TimeFeat, T, n_ac, time_dim, beta, gamma, time_coeff, dep_coeff, vol_c1, vol_c2, arr};
        String[] $constructorArgs = {"ObsV_c1", "ObsV_c2", "ObsArr", "TimeFeat"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        $helperClasses.add("$IfElseMods3");
        $helperClasses.add("$IfElseMods4");
        return compileAPI(opts, $variableNames, "EDVolumes_v3_additive_distributed", $helperClasses, "com.oracle.labs.east.ed", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "package com.oracle.labs.east.ed;\n"
             + "\n"
             + "// adding arrivals as a separate observation\n"
             + "// volumes and arrivals are observed\n"
             + "// two cohorts, multiple levels of acuity\n"
             + "// parameters are specific for acuity levels\n"
             + "// lump sum transition, overflow from one category to the next\n"
             + "\n"
             + "public model EDVolumes_v3_additive_distributed(int[][] ObsV_c1, int[][] ObsV_c2, int[][] ObsArr, double[][] TimeFeat) {\n"
             + "    //first dimension is time, second is acuity 1,...,5\n"
             + "    int T = ObsV_c1.length;\n"
             + "    int n_ac = ObsV_c1[0].length;\n"
             + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
             + "\n"
             + "\n"
             + "    //double[] alpha = beta(1,1).sample(n_ac);\n"
             + "    double[] beta = beta(1,1).sample(n_ac);\n"
             + "    double[] gamma = beta(1,1).sample(n_ac);\n"
             + "\n"
             + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
             + "    for (int i : [0..n_ac)){\n"
             + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
             + "    }\n"
             + "\n"
             + "    double[] dep_coeff = gaussian(0,1).sample(time_dim);\n"
             + "\n"
             + "    int[][] vol_c1 = new int[T][n_ac];\n"
             + "    int[][] vol_c2 = new int[T][n_ac];\n"
             + "    int[][] arr = new int[T][n_ac];\n"
             + "\n"
             + "\n"
             + "    // let's pick some initial states\n"
             + "    for (int i : [0..n_ac)){\n"
             + "        vol_c1[0][i] = poisson(ObsV_c1[0][i]).sample();\n"
             + "        vol_c2[0][i] = poisson(ObsV_c2[0][i]).sample();\n"
             + "\n"
             + "        double[] time_impact_0 = new double[time_dim];\n"
             + "        for (int j : [0..time_dim)){\n"
             + "            time_impact_0[j] = TimeFeat[0][j]*time_coeff[i][j];\n"
             + "        }\n"
             + "        double t_0 = reduce(time_impact_0, 0, (x, y) -> { return x + y; });\n"
             + "        arr[0][i]=poisson(exp(t_0)).sample();\n"
             + "    }\n"
             + "\n"
             + "    //int[] dep = new int[T];\n"
             + "\n"
             + "    for (int t : (0..T)) {   \n"
             + "        //int[] dep_vec = new int[n_ac]; //1 extra length to store not needed overflow of transitioned patients\n"
             + "    \n"
             + "        // populate volumes by acuity level\n"
             + "        double[] dep_impact = new double[time_dim];\n"
             + "        for (int k : [0..time_dim)){\n"
             + "            dep_impact[k] = TimeFeat[t][k]*dep_coeff[k];\n"
             + "            }\n"
             + "        //calculate sum\n"
             + "        double sum_t2 = reduce(dep_impact, 0, (x, y) -> { return x + y; });\n"
             + "        int dep_vec0 = poisson(exp(sum_t2)).sample();\n"
             + "\n"
             + "        for (int i : [0..n_ac)){\n"
             + "            // calculate impact of time features\n"
             + "            double[] time_impact = new double[time_dim];\n"
             + "            for (int j : [0..time_dim)){\n"
             + "                time_impact[j] = TimeFeat[t][j]*time_coeff[i][j];\n"
             + "                }\n"
             + "            //calculate sum\n"
             + "            double sum_t = reduce(time_impact, 0, (x, y) -> { return x + y; });\n"
             + "            arr[t][i] = poisson(exp(sum_t)).sample();\n"
             + "        }\n"
             + "\n"
             + "        int[] temp = new int[n_ac];\n"
             + "\n"
             + "        // acuity = 0\n"
             + "        int dep_vec1;\n"
             + "        if (ObsV_c1[t-1][0] + arr[t-1][0] - dep_vec0 > 0){\n"
             + "            temp[0] = poisson(ObsV_c1[t-1][0] + arr[t-1][0] - dep_vec0).sample();\n"
             + "            dep_vec1 = 0;\n"
             + "        }\n"
             + "        else {\n"
             + "            temp[0] = poisson(0.1).sample();\n"
             + "            dep_vec1 = dep_vec0 - ObsV_c1[t-1][0] -  arr[t-1][0] ;\n"
             + "        }\n"
             + "\n"
             + "        // acuity = 1\n"
             + "        int dep_vec2;\n"
             + "        if (ObsV_c1[t-1][1] + arr[t-1][1] - dep_vec1 > 0){\n"
             + "            temp[1] = poisson(ObsV_c1[t-1][1] + arr[t-1][1] - dep_vec1).sample();\n"
             + "            dep_vec2 = 0;\n"
             + "        }\n"
             + "        else {\n"
             + "            temp[1] = poisson(0.1).sample();\n"
             + "            dep_vec2 = dep_vec1 - ObsV_c1[t-1][1] -  arr[t-1][1] ;\n"
             + "        }\n"
             + "\n"
             + "        // acuity = 2\n"
             + "        int dep_vec3;\n"
             + "        if (ObsV_c1[t-1][2] + arr[t-1][2] - dep_vec2 > 0){\n"
             + "            temp[2] = poisson(ObsV_c1[t-1][2] + arr[t-1][2] - dep_vec2).sample();\n"
             + "            dep_vec3 = 0;\n"
             + "        }\n"
             + "        else {\n"
             + "            temp[2] = poisson(0.1).sample();\n"
             + "            dep_vec3 = dep_vec2 - ObsV_c1[t-1][2] -  arr[t-1][2] ;\n"
             + "        }\n"
             + "\n"
             + "        // acuity = 3\n"
             + "        int dep_vec4;\n"
             + "        if (ObsV_c1[t-1][3] + arr[t-1][3] - dep_vec3 > 0){\n"
             + "            temp[3] = poisson(ObsV_c1[t-1][3] + arr[t-1][3] - dep_vec3).sample();\n"
             + "            dep_vec4 = 0;\n"
             + "        }\n"
             + "        else {\n"
             + "            temp[3] = poisson(0.1).sample();\n"
             + "            dep_vec4 = dep_vec3 - ObsV_c1[t-1][3] -  arr[t-1][3] ;\n"
             + "        }\n"
             + "\n"
             + "        // acuity = 4\n"
             + "        if (ObsV_c1[t-1][4] + arr[t-1][4] - dep_vec4 > 0){\n"
             + "            temp[4] = poisson(ObsV_c1[t-1][4] + arr[t-1][4] - dep_vec4).sample();\n"
             + "        }\n"
             + "        else {\n"
             + "            temp[4] = poisson(0.1).sample();\n"
             + "        }\n"
             + "\n"
             + "        for (int i : [0..n_ac)){\n"
             + "            vol_c1[t][i] = poisson(temp[i]).sample();\n"
             + "            vol_c2[t][i] = poisson(beta[i]*ObsV_c2[t-1][i] + gamma[i]* ObsV_c1[t-1][i]).sample();\n"
             + "            }\n"
             + "    }\n"
             + "\n"
             + "    // assert generated data match observed data\n"
             + "    vol_c1.observe(ObsV_c1);\n"
             + "    vol_c2.observe(ObsV_c2);\n"
             + "    arr.observe(ObsArr);\n"
             + "}";
    }
}