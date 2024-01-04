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

public class Alarms2 extends GeneratedAPIBuilder {
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
        DoubleVariable p_burglary = doubleVariable(0.7, location(15, 25, 15, 27));
        p_burglary.setAlias("p_burglary");
        p_burglary.setLocation(location(15, 12, 15, 21));

        DoubleVariable p_earthquake = doubleVariable(0.2, location(16, 27, 16, 29));
        p_earthquake.setAlias("p_earthquake");
        p_earthquake.setLocation(location(16, 12, 16, 23));

        BooleanVariable earthquake = bernoulli(p_earthquake, location(18, 26, 18, 48)).sample(location(18, 50, 18, 57));
        earthquake.setAlias("earthquake");
        earthquake.setLocation(location(18, 13, 18, 22));

        BooleanVariable burglary = bernoulli(p_burglary, location(19, 24, 19, 44)).sample(location(19, 46, 19, 53));
        burglary.setAlias("burglary");
        burglary.setLocation(location(19, 13, 19, 20));

        BooleanVariable alarm;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = burglary;
        ifElse(guard$1, () -> {
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = earthquake;
            ifElse(guard$2, () -> {
                $if2.alarm = bernoulli(doubleVariable(0.9, location(24, 31, 24, 33)), location(24, 21, 24, 34)).sample(location(24, 36, 24, 43));

            }, () -> {
                $else2.alarm = bernoulli(doubleVariable(0.8, location(26, 31, 26, 33)), location(26, 21, 26, 34)).sample(location(26, 36, 26, 43));

            });
            $if1.alarm = ifElseAssignment(guard$2, $if2.alarm, $else2.alarm, location(23, 9, 23, 22));


        }, () -> {
            $IfElseMods3 $if3 = new $IfElseMods3();
            $IfElseMods3 $else3 = new $IfElseMods3();
            BooleanVariable guard$3 = earthquake;
            ifElse(guard$3, () -> {
                $if3.alarm = bernoulli(doubleVariable(0.1, location(30, 31, 30, 33)), location(30, 21, 30, 34)).sample(location(30, 36, 30, 43));

            }, () -> {
                $else3.alarm = booleanVariable(false, location(32, 21, 32, 25));

            });
            $else1.alarm = ifElseAssignment(guard$3, $if3.alarm, $else3.alarm, location(29, 9, 29, 22));


        });
        alarm = ifElseAssignment(guard$1, $if1.alarm, $else1.alarm, location(22, 5, 22, 16));
        alarm.setAlias("alarm");
        alarm.setLocation(location(21, 13, 21, 17));

        alarm.observe(booleanVariable(true, location(36, 19, 36, 22)), location(36, 11, 36, 23));

        Variable<?>[] $variableNames = {p_burglary, p_earthquake, earthquake, burglary, alarm};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        $helperClasses.add("$IfElseMods3");
        return compileAPI(opts, $variableNames, "Alarms2", $helperClasses, "alarms", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package alarms;\n\n/*\n * 0.7::burglary.\n * 0.2::earthquake.\n * \n * 0.9::alarm :- burglary, earthquake.\n * 0.8::alarm :- burglary, \\+earthquake.\n * 0.1::alarm :- \\+burglary, earthquake.\n * \n * evidence(alarm,true).\n */\n\npublic model Alarms2() {\n    double p_burglary = 0.7;\n    double p_earthquake = 0.2;\n    \n    boolean earthquake = bernoulli(p_earthquake).sample();\n    boolean burglary = bernoulli(p_burglary).sample();\n    \n    boolean alarm;\n    if(burglary) {\n        if(earthquake) {\n            alarm = bernoulli(0.9).sample();\n        } else {\n            alarm = bernoulli(0.8).sample();\n        }\n    } else {\n        if(earthquake) {\n            alarm = bernoulli(0.1).sample();\n        } else {\n            alarm = false;\n        }\n    }\n    \n    alarm.observe(true);\n}";
    }
}