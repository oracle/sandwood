package alarms;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

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

public class Alarms3 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        BooleanVariable alarm;
    }

    private static class $IfElseMods2 { 
        BooleanVariable alarm;
    }

    private static class $IfElseMods3 { 
        BooleanVariable alarm;
    }

    private static class $IfElseMods4 { 
        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable p_burglary = doubleVariable(0.7, location(25, 25, 25, 27));
        p_burglary.setAlias("p_burglary");
        p_burglary.setLocation(location(25, 12, 25, 21));

        DoubleVariable p_earthquake = doubleVariable(0.2, location(26, 27, 26, 29));
        p_earthquake.setAlias("p_earthquake");
        p_earthquake.setLocation(location(26, 12, 26, 23));

        BooleanVariable earthquake = bernoulli(p_earthquake, location(28, 26, 28, 48)).sample(location(28, 50, 28, 57));
        earthquake.setAlias("earthquake");
        earthquake.setLocation(location(28, 13, 28, 22));

        BooleanVariable burglary = bernoulli(p_burglary, location(29, 24, 29, 44)).sample(location(29, 46, 29, 53));
        burglary.setAlias("burglary");
        burglary.setLocation(location(29, 13, 29, 20));

        BooleanVariable alarm;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = burglary;
        ifElse(guard$1, () -> {
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = earthquake;
            ifElse(guard$2, () -> {
                $if2.alarm = bernoulli(doubleVariable(0.9, location(34, 31, 34, 33)), location(34, 21, 34, 34)).sample(location(34, 36, 34, 43));

            }, () -> {
                $else2.alarm = bernoulli(doubleVariable(0.8, location(36, 31, 36, 33)), location(36, 21, 36, 34)).sample(location(36, 36, 36, 43));

            });
            $if1.alarm = ifElseAssignment(guard$2, $if2.alarm, $else2.alarm, location(33, 9, 33, 22));


        }, () -> {
            $IfElseMods3 $if3 = new $IfElseMods3();
            $IfElseMods3 $else3 = new $IfElseMods3();
            BooleanVariable guard$3 = earthquake;
            ifElse(guard$3, () -> {
                $if3.alarm = bernoulli(doubleVariable(0.1, location(40, 31, 40, 33)), location(40, 21, 40, 34)).sample(location(40, 36, 40, 43));

            }, () -> {
                $else3.alarm = booleanVariable(false, location(42, 21, 42, 25));

            });
            $else1.alarm = ifElseAssignment(guard$3, $if3.alarm, $else3.alarm, location(39, 9, 39, 22));


        });
        alarm = ifElseAssignment(guard$1, $if1.alarm, $else1.alarm, location(32, 5, 32, 16));
        alarm.setAlias("alarm");
        alarm.setLocation(location(31, 13, 31, 17));

        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
        $IfElseMods4 $if4 = new $IfElseMods4();
        $IfElseMods4 $else4 = new $IfElseMods4();
        BooleanVariable guard$4 = alarm;
        ifElse(guard$4, () -> {
            Bernoulli alarmCall = bernoulli(doubleVariable(0.8, location(49, 41, 49, 43)), location(49, 31, 49, 44));
            alarmCall.setAlias("alarmCall");
            alarmCall.setLocation(location(49, 19, 49, 27));

            $if4.johnCalls = alarmCall.sample(location(50, 31, 50, 38));
            $if4.maryCalls = alarmCall.sample(location(51, 31, 51, 38));

        }, () -> {
            Bernoulli noAlarmCall = bernoulli(doubleVariable(0.1, location(53, 43, 53, 45)), location(53, 33, 53, 46));
            noAlarmCall.setAlias("noAlarmCall");
            noAlarmCall.setLocation(location(53, 19, 53, 29));

            $else4.johnCalls = noAlarmCall.sample(location(54, 33, 54, 40));
            $else4.maryCalls = noAlarmCall.sample(location(55, 33, 55, 40));

        });
        johnCalls = ifElseAssignment(guard$4, $if4.johnCalls, $else4.johnCalls, location(48, 5, 48, 13));
        johnCalls.setAlias("johnCalls");
        johnCalls.setLocation(location(46, 13, 46, 21));

        maryCalls = ifElseAssignment(guard$4, $if4.maryCalls, $else4.maryCalls, location(48, 5, 48, 13));
        maryCalls.setAlias("maryCalls");
        maryCalls.setLocation(location(47, 13, 47, 21));

        johnCalls.observe(booleanVariable(true, location(58, 23, 58, 26)), location(58, 15, 58, 27));
        maryCalls.observe(booleanVariable(true, location(59, 23, 59, 26)), location(59, 15, 59, 27));

        Variable<?>[] $variableNames = {p_burglary, p_earthquake, earthquake, burglary, alarm, johnCalls, maryCalls};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        $helperClasses.add("$IfElseMods3");
        $helperClasses.add("$IfElseMods4");
        return compileAPI(opts, $variableNames, "Alarms3", $helperClasses, "alarms", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package alarms;\n\n/*\n * person(john).\n * person(mary).\n * \n * 0.7::burglary.\n * 0.2::earthquake.\n * \n * 0.9::alarm :- burglary, earthquake.\n * 0.8::alarm :- burglary, \\+earthquake.\n * 0.1::alarm :- \\+burglary, earthquake.\n * \n * 0.8::calls(X) :- alarm, person(X).\n * 0.1::calls(X) :- \\+alarm, person(X).\n * \n * evidence(calls(john),true).\n * evidence(calls(mary),true).\n * \n * query(burglary).\n * query(earthquake).\n */\n\npublic model Alarms3() {\n    double p_burglary = 0.7;\n    double p_earthquake = 0.2;\n    \n    boolean earthquake = bernoulli(p_earthquake).sample();\n    boolean burglary = bernoulli(p_burglary).sample();\n    \n    boolean alarm;\n    if(burglary) {\n        if(earthquake) {\n            alarm = bernoulli(0.9).sample();\n        } else {\n            alarm = bernoulli(0.8).sample();\n        }\n    } else {\n        if(earthquake) {\n            alarm = bernoulli(0.1).sample();\n        } else {\n            alarm = false;\n        }\n    }\n    \n    boolean johnCalls;\n    boolean maryCalls;\n    if(alarm) {\n        Bernoulli alarmCall = bernoulli(0.8);\n        johnCalls = alarmCall.sample();\n        maryCalls = alarmCall.sample();\n    } else {\n        Bernoulli noAlarmCall = bernoulli(0.1);\n        johnCalls = noAlarmCall.sample();\n        maryCalls = noAlarmCall.sample();\n    }\n    \n    johnCalls.observe(true);\n    maryCalls.observe(true);\n}";
    }
}