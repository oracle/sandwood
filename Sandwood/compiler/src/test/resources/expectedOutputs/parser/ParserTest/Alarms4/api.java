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

public class Alarms4 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        ArrayVariable<DoubleVariable> p_burglary = Variable.arrayVariable(location(27, 37, 27, 39), VariableType.DoubleVariable, intVariable(2, location(27, 38, 27, 38)));
        p_burglary.setAlias("p_burglary");
        p_burglary.setLocation(location(27, 14, 27, 23));

        p_burglary.put(intVariable(0, location(28, 16, 28, 16)), doubleVariable(0.3, location(28, 21, 28, 23)), location(28, 15, 28, 23));
        p_burglary.put(intVariable(1, location(29, 16, 29, 16)), doubleVariable(0.7, location(29, 21, 29, 23)), location(29, 15, 29, 23));
        ArrayVariable<DoubleVariable> p_earthquakes = Variable.arrayVariable(location(31, 40, 31, 42), VariableType.DoubleVariable, intVariable(3, location(31, 41, 31, 41)));
        p_earthquakes.setAlias("p_earthquakes");
        p_earthquakes.setLocation(location(31, 14, 31, 26));

        p_earthquakes.put(intVariable(0, location(32, 19, 32, 19)), doubleVariable(0.01, location(32, 24, 32, 27)), location(32, 18, 32, 27));
        p_earthquakes.put(intVariable(1, location(33, 19, 33, 19)), doubleVariable(0.19, location(33, 24, 33, 27)), location(33, 18, 33, 27));
        p_earthquakes.put(intVariable(2, location(34, 19, 34, 19)), doubleVariable(0.8, location(34, 24, 34, 26)), location(34, 18, 34, 26));
        ArrayVariable<ArrayVariable<DoubleVariable>> p_alarm = Variable.arrayVariable(location(36, 36, 36, 41), VariableType.arrayType(VariableType.DoubleVariable), intVariable(2, location(36, 37, 36, 37)), intVariable(3, location(36, 40, 36, 40)));
        p_alarm.setAlias("p_alarm");
        p_alarm.setLocation(location(36, 16, 36, 22));

        p_alarm.get(intVariable(0, location(37, 13, 37, 13)), location(37, 12, 37, 14)).put(intVariable(0, location(37, 16, 37, 16)), doubleVariable(0.3, location(37, 21, 37, 23)), location(37, 15, 37, 23));
        p_alarm.get(intVariable(0, location(38, 13, 38, 13)), location(38, 12, 38, 14)).put(intVariable(1, location(38, 16, 38, 16)), doubleVariable(0.1, location(38, 21, 38, 23)), location(38, 15, 38, 23));
        p_alarm.get(intVariable(0, location(39, 13, 39, 13)), location(39, 12, 39, 14)).put(intVariable(2, location(39, 16, 39, 16)), doubleVariable(0.0, location(39, 21, 39, 23)), location(39, 15, 39, 23));
        p_alarm.get(intVariable(1, location(40, 13, 40, 13)), location(40, 12, 40, 14)).put(intVariable(0, location(40, 16, 40, 16)), doubleVariable(0.9, location(40, 21, 40, 23)), location(40, 15, 40, 23));
        p_alarm.get(intVariable(1, location(41, 13, 41, 13)), location(41, 12, 41, 14)).put(intVariable(1, location(41, 16, 41, 16)), doubleVariable(0.85, location(41, 21, 41, 24)), location(41, 15, 41, 24));
        p_alarm.get(intVariable(1, location(42, 13, 42, 13)), location(42, 12, 42, 14)).put(intVariable(2, location(42, 16, 42, 16)), doubleVariable(0.8, location(42, 21, 42, 23)), location(42, 15, 42, 23));
        IntVariable earthquake = categorical(p_earthquakes, location(44, 22, 44, 47)).sample(location(44, 49, 44, 56));
        earthquake.setAlias("earthquake");
        earthquake.setLocation(location(44, 9, 44, 18));

        IntVariable burglary = categorical(p_burglary, location(45, 20, 45, 42)).sample(location(45, 44, 45, 51));
        burglary.setAlias("burglary");
        burglary.setLocation(location(45, 9, 45, 16));

        BooleanVariable alarm = bernoulli(p_alarm.get(burglary, location(47, 38, 47, 47)).get(earthquake, location(47, 48, 47, 59)), location(47, 21, 47, 60)).sample(location(47, 62, 47, 69));
        alarm.setAlias("alarm");
        alarm.setLocation(location(47, 13, 47, 17));

        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = alarm;
        ifElse(guard$1, () -> {
            Bernoulli alarmCall = bernoulli(doubleVariable(0.8, location(52, 41, 52, 43)), location(52, 31, 52, 44));
            alarmCall.setAlias("alarmCall");
            alarmCall.setLocation(location(52, 19, 52, 27));

            $if1.johnCalls = alarmCall.sample(location(53, 31, 53, 38));
            $if1.maryCalls = alarmCall.sample(location(54, 31, 54, 38));

        }, () -> {
            Bernoulli noAlarmCall = bernoulli(doubleVariable(0.1, location(56, 43, 56, 45)), location(56, 33, 56, 46));
            noAlarmCall.setAlias("noAlarmCall");
            noAlarmCall.setLocation(location(56, 19, 56, 29));

            $else1.johnCalls = noAlarmCall.sample(location(57, 33, 57, 40));
            $else1.maryCalls = noAlarmCall.sample(location(58, 33, 58, 40));

        });
        johnCalls = ifElseAssignment(guard$1, $if1.johnCalls, $else1.johnCalls, location(51, 5, 51, 13));
        johnCalls.setAlias("johnCalls");
        johnCalls.setLocation(location(49, 13, 49, 21));

        maryCalls = ifElseAssignment(guard$1, $if1.maryCalls, $else1.maryCalls, location(51, 5, 51, 13));
        maryCalls.setAlias("maryCalls");
        maryCalls.setLocation(location(50, 13, 50, 21));

        johnCalls.observe(booleanVariable(true, location(61, 23, 61, 26)), location(61, 15, 61, 27));
        maryCalls.observe(booleanVariable(true, location(62, 23, 62, 26)), location(62, 15, 62, 27));

        Variable<?>[] $variableNames = {p_burglary, p_earthquakes, p_alarm, earthquake, burglary, alarm, johnCalls, maryCalls};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "Alarms4", $helperClasses, "alarms", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package alarms;\n\n/*\n * person(john).\n * person(mary).\n * \n * 0.7::burglary.\n * 0.01::earthquake(heavy); 0.19::earthquake(mild); 0.8::earthquake(none).\n * \n * 0.90::alarm :-   burglary, earthquake(heavy).\n * 0.85::alarm :-   burglary, earthquake(mild).\n * 0.80::alarm :-   burglary, earthquake(none).\n * 0.10::alarm :- \\+burglary, earthquake(mild).\n * 0.30::alarm :- \\+burglary, earthquake(heavy).\n * \n * 0.8::calls(X) :- alarm, person(X).\n * 0.1::calls(X) :- \\+alarm, person(X).\n * \n * evidence(calls(john),true).\n * evidence(calls(mary),true).\n * \n * query(burglary).\n * query(earthquake(_)).\n */\n\npublic model Alarms4() {\n    double[] p_burglary = new double[2];\n    p_burglary[0] = 0.3;\n    p_burglary[1] = 0.7;\n    \n    double[] p_earthquakes = new double[3];\n    p_earthquakes[0] = 0.01;\n    p_earthquakes[1] = 0.19;\n    p_earthquakes[2] = 0.8;\n    \n    double[][] p_alarm = new double[2][3];\n    p_alarm[0][0] = 0.3;\n    p_alarm[0][1] = 0.1;\n    p_alarm[0][2] = 0.0;\n    p_alarm[1][0] = 0.9;\n    p_alarm[1][1] = 0.85;\n    p_alarm[1][2] = 0.8;\n    \n    int earthquake = categorical(p_earthquakes).sample();\n    int burglary = categorical(p_burglary).sample();\n    \n    boolean alarm = bernoulli(p_alarm[burglary][earthquake]).sample();\n    \n    boolean johnCalls;\n    boolean maryCalls;\n    if(alarm) {\n        Bernoulli alarmCall = bernoulli(0.8);\n        johnCalls = alarmCall.sample();\n        maryCalls = alarmCall.sample();\n    } else {\n        Bernoulli noAlarmCall = bernoulli(0.1);\n        johnCalls = noAlarmCall.sample();\n        maryCalls = noAlarmCall.sample();\n    }\n    \n    johnCalls.observe(true);\n    maryCalls.observe(true);\n}";
    }
}