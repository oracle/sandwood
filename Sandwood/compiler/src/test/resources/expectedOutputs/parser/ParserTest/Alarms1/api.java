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

public class Alarms1 extends GeneratedAPIBuilder {
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

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable p_burglary = doubleVariable(0.7, location(18, 25, 18, 27));
        p_burglary.setAlias("p_burglary");
        p_burglary.setLocation(location(18, 12, 18, 21));

        DoubleVariable p_earthquake = doubleVariable(0.2, location(19, 27, 19, 29));
        p_earthquake.setAlias("p_earthquake");
        p_earthquake.setLocation(location(19, 12, 19, 23));

        DoubleVariable p_alarm1 = doubleVariable(0.9, location(20, 23, 20, 25));
        p_alarm1.setAlias("p_alarm1");
        p_alarm1.setLocation(location(20, 12, 20, 19));

        DoubleVariable p_alarm2 = doubleVariable(0.8, location(21, 23, 21, 25));
        p_alarm2.setAlias("p_alarm2");
        p_alarm2.setLocation(location(21, 12, 21, 19));

        DoubleVariable p_alarm3 = doubleVariable(0.1, location(22, 23, 22, 25));
        p_alarm3.setAlias("p_alarm3");
        p_alarm3.setLocation(location(22, 12, 22, 19));

        BooleanVariable earthquake = bernoulli(p_earthquake, location(24, 26, 24, 48)).sample(location(24, 50, 24, 57));
        earthquake.setAlias("earthquake");
        earthquake.setLocation(location(24, 13, 24, 22));

        BooleanVariable burglary = bernoulli(p_burglary, location(25, 24, 25, 44)).sample(location(25, 46, 25, 53));
        burglary.setAlias("burglary");
        burglary.setLocation(location(25, 13, 25, 20));

        BooleanVariable alarm;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = burglary;
        ifElse(guard$1, () -> {
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = earthquake;
            ifElse(guard$2, () -> {
                $if2.alarm = bernoulli(p_alarm1, location(30, 21, 30, 39)).sample(location(30, 41, 30, 48));

            }, () -> {
                $else2.alarm = bernoulli(p_alarm2, location(32, 21, 32, 39)).sample(location(32, 41, 32, 48));

            });
            $if1.alarm = ifElseAssignment(guard$2, $if2.alarm, $else2.alarm, location(29, 9, 29, 22));


        }, () -> {
            $IfElseMods3 $if3 = new $IfElseMods3();
            $IfElseMods3 $else3 = new $IfElseMods3();
            BooleanVariable guard$3 = earthquake;
            ifElse(guard$3, () -> {
                $if3.alarm = bernoulli(p_alarm3, location(36, 21, 36, 39)).sample(location(36, 41, 36, 48));

            }, () -> {
                $else3.alarm = booleanVariable(false, location(38, 21, 38, 25));

            });
            $else1.alarm = ifElseAssignment(guard$3, $if3.alarm, $else3.alarm, location(35, 9, 35, 22));


        });
        alarm = ifElseAssignment(guard$1, $if1.alarm, $else1.alarm, location(28, 5, 28, 16));
        alarm.setAlias("alarm");
        alarm.setLocation(location(27, 13, 27, 17));

        alarm.observe(booleanVariable(true, location(42, 19, 42, 22)), location(42, 11, 42, 23));

        Variable<?>[] $variableNames = {p_burglary, p_earthquake, p_alarm1, p_alarm2, p_alarm3, earthquake, burglary, alarm};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        $helperClasses.add("$IfElseMods3");
        return compileAPI(opts, $variableNames, "Alarms1", $helperClasses, "alarms", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package alarms;\n\n/*\n * 0.7::burglary.\n * 0.2::earthquake.\n * 0.9::p_alarm1.\n * 0.8::p_alarm2.\n * 0.1::p_alarm3.\n * \n * alarm :- burglary, earthquake, p_alarm1.\n * alarm :- burglary, \\+earthquake, p_alarm2.\n * alarm :- \\+burglary, earthquake, p_alarm3.\n * \n * evidence(alarm,true).\n */\n\npublic model Alarms1() {\n    double p_burglary = 0.7;\n    double p_earthquake = 0.2;\n    double p_alarm1 = 0.9;\n    double p_alarm2 = 0.8;\n    double p_alarm3 = 0.1;\n    \n    boolean earthquake = bernoulli(p_earthquake).sample();\n    boolean burglary = bernoulli(p_burglary).sample();\n    \n    boolean alarm;\n    if(burglary) {\n        if(earthquake) {\n            alarm = bernoulli(p_alarm1).sample();\n        } else {\n            alarm = bernoulli(p_alarm2).sample();\n        }\n    } else {\n        if(earthquake) {\n            alarm = bernoulli(p_alarm3).sample();\n        } else {\n            alarm = false;\n        }\n    }\n    \n    alarm.observe(true);\n}";
    }
}