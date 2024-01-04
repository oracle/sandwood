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

public class Alarms5 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        ArrayVariable<DoubleVariable> p_burglary = Variable.arrayVariable(location(27, 27, 27, 36), VariableType.DoubleVariable, 2);
        {
            p_burglary.put(intVariable(0), doubleVariable(0.3, location(27, 28, 27, 30)), location(27, 27, 27, 30));
            p_burglary.put(intVariable(1, location(27, 27, 27, 27)), doubleVariable(0.7, location(27, 33, 27, 35)), location(27, 27, 27, 35));
        }

        p_burglary.setAlias("p_burglary");
        p_burglary.setLocation(location(27, 14, 27, 23));

        ArrayVariable<DoubleVariable> p_earthquakes = Variable.arrayVariable(location(28, 30, 28, 46), VariableType.DoubleVariable, 3);
        {
            p_earthquakes.put(intVariable(0), doubleVariable(0.01, location(28, 31, 28, 34)), location(28, 30, 28, 34));
            p_earthquakes.put(intVariable(1, location(28, 30, 28, 30)), doubleVariable(0.19, location(28, 37, 28, 40)), location(28, 30, 28, 40));
            p_earthquakes.put(intVariable(2, location(28, 30, 28, 30)), doubleVariable(0.8, location(28, 43, 28, 45)), location(28, 30, 28, 45));
        }

        p_earthquakes.setAlias("p_earthquakes");
        p_earthquakes.setLocation(location(28, 14, 28, 26));

        ArrayVariable<ArrayVariable<DoubleVariable>> p_alarm = Variable.arrayVariable(location(29, 26, 29, 59), VariableType.arrayType(VariableType.DoubleVariable), 2);
        {
            ArrayVariable<DoubleVariable> p_alarm$0 = Variable.arrayVariable(location(29, 27, 29, 41), VariableType.DoubleVariable, 3);
            {
                p_alarm$0.put(intVariable(0), doubleVariable(0.3, location(29, 28, 29, 30)), location(29, 27, 29, 30));
                p_alarm$0.put(intVariable(1, location(29, 27, 29, 27)), doubleVariable(0.1, location(29, 33, 29, 35)), location(29, 27, 29, 35));
                p_alarm$0.put(intVariable(2, location(29, 27, 29, 27)), doubleVariable(0.0, location(29, 38, 29, 40)), location(29, 27, 29, 40));
            }
            p_alarm.put(intVariable(0, location(29, 26, 29, 27)), p_alarm$0);

            ArrayVariable<DoubleVariable> p_alarm$1 = Variable.arrayVariable(location(29, 43, 29, 58), VariableType.DoubleVariable, 3);
            {
                p_alarm$1.put(intVariable(0), doubleVariable(0.9, location(29, 44, 29, 46)), location(29, 43, 29, 46));
                p_alarm$1.put(intVariable(1, location(29, 43, 29, 43)), doubleVariable(0.85, location(29, 49, 29, 52)), location(29, 43, 29, 52));
                p_alarm$1.put(intVariable(2, location(29, 43, 29, 43)), doubleVariable(0.8, location(29, 55, 29, 57)), location(29, 43, 29, 57));
            }
            p_alarm.put(intVariable(1), p_alarm$1);
        }

        p_alarm.setAlias("p_alarm");
        p_alarm.setLocation(location(29, 16, 29, 22));

        IntVariable earthquake = categorical(p_earthquakes, location(31, 22, 31, 47)).sample(location(31, 49, 31, 56));
        earthquake.setAlias("earthquake");
        earthquake.setLocation(location(31, 9, 31, 18));

        IntVariable burglary = categorical(p_burglary, location(32, 20, 32, 42)).sample(location(32, 44, 32, 51));
        burglary.setAlias("burglary");
        burglary.setLocation(location(32, 9, 32, 16));

        BooleanVariable alarm = bernoulli(p_alarm.get(burglary, location(34, 38, 34, 47)).get(earthquake, location(34, 48, 34, 59)), location(34, 21, 34, 60)).sample(location(34, 62, 34, 69));
        alarm.setAlias("alarm");
        alarm.setLocation(location(34, 13, 34, 17));

        BooleanVariable johnCalls;
        BooleanVariable maryCalls;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = alarm;
        ifElse(guard$1, () -> {
            Bernoulli alarmCall = bernoulli(doubleVariable(0.8, location(39, 41, 39, 43)), location(39, 31, 39, 44));
            alarmCall.setAlias("alarmCall");
            alarmCall.setLocation(location(39, 19, 39, 27));

            $if1.johnCalls = alarmCall.sample(location(40, 31, 40, 38));
            $if1.maryCalls = alarmCall.sample(location(41, 31, 41, 38));

        }, () -> {
            Bernoulli noAlarmCall = bernoulli(doubleVariable(0.1, location(43, 43, 43, 45)), location(43, 33, 43, 46));
            noAlarmCall.setAlias("noAlarmCall");
            noAlarmCall.setLocation(location(43, 19, 43, 29));

            $else1.johnCalls = noAlarmCall.sample(location(44, 33, 44, 40));
            $else1.maryCalls = noAlarmCall.sample(location(45, 33, 45, 40));

        });
        johnCalls = ifElseAssignment(guard$1, $if1.johnCalls, $else1.johnCalls, location(38, 5, 38, 13));
        johnCalls.setAlias("johnCalls");
        johnCalls.setLocation(location(36, 13, 36, 21));

        maryCalls = ifElseAssignment(guard$1, $if1.maryCalls, $else1.maryCalls, location(38, 5, 38, 13));
        maryCalls.setAlias("maryCalls");
        maryCalls.setLocation(location(37, 13, 37, 21));

        johnCalls.observe(booleanVariable(true, location(48, 23, 48, 26)), location(48, 15, 48, 27));
        maryCalls.observe(booleanVariable(true, location(49, 23, 49, 26)), location(49, 15, 49, 27));

        Variable<?>[] $variableNames = {p_burglary, p_earthquakes, p_alarm, earthquake, burglary, alarm, johnCalls, maryCalls};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "Alarms5", $helperClasses, "alarms", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package alarms;\n\n/*\n * person(john).\n * person(mary).\n * \n * 0.7::burglary.\n * 0.01::earthquake(heavy); 0.19::earthquake(mild); 0.8::earthquake(none).\n * \n * 0.90::alarm :-   burglary, earthquake(heavy).\n * 0.85::alarm :-   burglary, earthquake(mild).\n * 0.80::alarm :-   burglary, earthquake(none).\n * 0.10::alarm :- \\+burglary, earthquake(mild).\n * 0.30::alarm :- \\+burglary, earthquake(heavy).\n * \n * 0.8::calls(X) :- alarm, person(X).\n * 0.1::calls(X) :- \\+alarm, person(X).\n * \n * evidence(calls(john),true).\n * evidence(calls(mary),true).\n * \n * query(burglary).\n * query(earthquake(_)).\n */\n\npublic model Alarms5() {\n    double[] p_burglary = {0.3, 0.7};\n    double[] p_earthquakes = {0.01, 0.19, 0.8};\n    double[][] p_alarm = {{0.3, 0.1, 0.0},{0.9, 0.85, 0.8}};\n    \n    int earthquake = categorical(p_earthquakes).sample();\n    int burglary = categorical(p_burglary).sample();\n    \n    boolean alarm = bernoulli(p_alarm[burglary][earthquake]).sample();\n    \n    boolean johnCalls;\n    boolean maryCalls;\n    if(alarm) {\n        Bernoulli alarmCall = bernoulli(0.8);\n        johnCalls = alarmCall.sample();\n        maryCalls = alarmCall.sample();\n    } else {\n        Bernoulli noAlarmCall = bernoulli(0.1);\n        johnCalls = noAlarmCall.sample();\n        maryCalls = noAlarmCall.sample();\n    }\n    \n    johnCalls.observe(true);\n    maryCalls.observe(true);\n}";
    }
}