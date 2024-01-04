package macaron;

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

public class BuildCheck4 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        BooleanVariable deploy_action_certainty;
    }

    private static class $IfElseMods2 { 
        BooleanVariable deploy_action_certainty;
    }

    private static class $IfElseMods3 { 
        BooleanVariable deploy_command_certainty;
    }

    private static class $IfElseMods4 { 
        BooleanVariable deploy_command_certainty;
    }

    private static class $IfElseMods5 { 
        BooleanVariable deploy_kws_certainty;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        BooleanVariable ci_parsed = observeBoolean("ci_parsed", location(47, 26, 47, 42));
        BooleanVariable deploy_action = observeBoolean("deploy_action", location(48, 26, 48, 46));
        BooleanVariable deploy_command = observeBoolean("deploy_command", location(49, 26, 49, 47));
        BooleanVariable deploy_kws = observeBoolean("deploy_kws", location(50, 26, 50, 43));
        BooleanVariable release_workflow_trigger_deploy = observeBoolean("release_workflow_trigger_deploy", location(51, 26, 51, 64));
        BooleanVariable invalid_trigger = observeBoolean("invalid_trigger", location(52, 26, 52, 48));
        BooleanVariable tested_deploy_action = observeBoolean("tested_deploy_action", location(53, 26, 53, 53));
        BooleanVariable step_uses_secrets_deploy = observeBoolean("step_uses_secrets_deploy", location(54, 26, 54, 57));

        BooleanVariable deploy_action_certainty;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = ci_parsed;
        ifElse(guard$1, () -> {
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = deploy_action;
            ifElse(guard$2, () -> {
                DoubleVariable p_deploy_action = doubleVariable(0.8, location(60, 32, 60, 34)).times(doubleVariable(0.9, location(60, 36, 60, 38)), location(60, 35, 60, 35));
                p_deploy_action.setAlias("p_deploy_action");
                p_deploy_action.setLocation(location(60, 14, 60, 28));

                DoubleVariable p_tested_deploy_action = ifElseLambdaAssignment(tested_deploy_action, () -> { return doubleVariable(0.1, location(61, 62, 61, 64)).times(doubleVariable(0.9, location(61, 68, 61, 70)), location(61, 66, 61, 66)); }, () -> { return doubleVariable(0.0, location(61, 74, 61, 76)); }, location(61, 39, 61, 76));
                p_tested_deploy_action.setAlias("p_tested_deploy_action");
                p_tested_deploy_action.setLocation(location(61, 14, 61, 35));

                DoubleVariable p_release_workflow_trigger_deploy = ifElseLambdaAssignment(release_workflow_trigger_deploy, () -> { return (ifElseLambdaAssignment(invalid_trigger, () -> { return doubleVariable(0.75, location(63, 69, 63, 72)).times(doubleVariable(0.85, location(63, 74, 63, 77)), location(63, 73, 63, 73)); }, () -> { return doubleVariable(0.9, location(63, 81, 63, 83)).times(doubleVariable(0.85, location(63, 85, 63, 88)), location(63, 84, 63, 84)); }, location(63, 51, 63, 84))); }, () -> { return doubleVariable(0.0, location(64, 50, 64, 52)); }, location(62, 50, 64, 52));
                p_release_workflow_trigger_deploy.setAlias("p_release_workflow_trigger_deploy");
                p_release_workflow_trigger_deploy.setLocation(location(62, 14, 62, 46));

                DoubleVariable p_step_uses_secrets = ifElseLambdaAssignment(step_uses_secrets_deploy, () -> { return doubleVariable(0.65, location(65, 63, 65, 66)).times(doubleVariable(0.9, location(65, 68, 65, 70)), location(65, 67, 65, 67)); }, () -> { return doubleVariable(0.0, location(65, 74, 65, 76)); }, location(65, 36, 65, 76));
                p_step_uses_secrets.setAlias("p_step_uses_secrets");
                p_step_uses_secrets.setLocation(location(65, 14, 65, 32));

                $if2.deploy_action_certainty = bernoulli(p_deploy_action, location(67, 33, 67, 58)).sample(location(67, 60, 67, 67)).or(bernoulli(p_tested_deploy_action, location(68, 33, 68, 65)).sample(location(68, 67, 68, 74)), location(67, 69, 67, 70)).or(bernoulli(p_release_workflow_trigger_deploy, location(69, 33, 69, 76)).sample(location(69, 78, 69, 85)), location(68, 76, 68, 77)).or(bernoulli(p_step_uses_secrets, location(70, 33, 70, 62)).sample(location(70, 64, 70, 71)), location(69, 87, 69, 88));

            }, () -> {
                $else2.deploy_action_certainty = booleanVariable(false, location(72, 33, 72, 37));

            });
            $if1.deploy_action_certainty = ifElseAssignment(guard$2, $if2.deploy_action_certainty, $else2.deploy_action_certainty, location(59, 5, 59, 21));


        }, () -> {
            $else1.deploy_action_certainty = booleanVariable(false, location(75, 31, 75, 35));

        });
        deploy_action_certainty = ifElseAssignment(guard$1, $if1.deploy_action_certainty, $else1.deploy_action_certainty, location(58, 3, 58, 15));
        deploy_action_certainty.setAlias("deploy_action_certainty");
        deploy_action_certainty.setLocation(location(57, 11, 57, 33));

        BooleanVariable deploy_command_certainty;
        $IfElseMods3 $if3 = new $IfElseMods3();
        $IfElseMods3 $else3 = new $IfElseMods3();
        BooleanVariable guard$3 = ci_parsed;
        ifElse(guard$3, () -> {
            $IfElseMods4 $if4 = new $IfElseMods4();
            $IfElseMods4 $else4 = new $IfElseMods4();
            BooleanVariable guard$4 = deploy_command;
            ifElse(guard$4, () -> {
                DoubleVariable p_deploy_command = doubleVariable(0.75, location(82, 33, 82, 36)).times(doubleVariable(0.8, location(82, 38, 82, 40)), location(82, 37, 82, 37));
                p_deploy_command.setAlias("p_deploy_command");
                p_deploy_command.setLocation(location(82, 14, 82, 29));

                DoubleVariable p_release_workflow_trigger_deploy = ifElseLambdaAssignment(release_workflow_trigger_deploy, () -> { return (ifElseLambdaAssignment(invalid_trigger, () -> { return doubleVariable(0.75, location(84, 75, 84, 78)).times(doubleVariable(0.85, location(84, 80, 84, 83)), location(84, 79, 84, 79)); }, () -> { return doubleVariable(0.9, location(84, 87, 84, 89)).times(doubleVariable(0.85, location(84, 91, 84, 94)), location(84, 90, 84, 90)); }, location(84, 57, 84, 90))); }, () -> { return doubleVariable(0.0, location(85, 56, 85, 58)); }, location(83, 50, 85, 58));
                p_release_workflow_trigger_deploy.setAlias("p_release_workflow_trigger_deploy");
                p_release_workflow_trigger_deploy.setLocation(location(83, 14, 83, 46));

                DoubleVariable p_step_uses_secrets = ifElseLambdaAssignment(step_uses_secrets_deploy, () -> { return doubleVariable(0.65, location(86, 63, 86, 66)).times(doubleVariable(0.9, location(86, 68, 86, 70)), location(86, 67, 86, 67)); }, () -> { return doubleVariable(0.0, location(86, 74, 86, 76)); }, location(86, 36, 86, 76));
                p_step_uses_secrets.setAlias("p_step_uses_secrets");
                p_step_uses_secrets.setLocation(location(86, 14, 86, 32));

                $if4.deploy_command_certainty = bernoulli(p_deploy_command, location(88, 34, 88, 60)).sample(location(88, 62, 88, 69)).or(bernoulli(p_release_workflow_trigger_deploy, location(89, 34, 89, 77)).sample(location(89, 79, 89, 86)), location(88, 71, 88, 72)).or(bernoulli(p_step_uses_secrets, location(90, 34, 90, 63)).sample(location(90, 65, 90, 72)), location(89, 88, 89, 89));

            }, () -> {
                $else4.deploy_command_certainty = booleanVariable(false, location(92, 34, 92, 38));

            });
            $if3.deploy_command_certainty = ifElseAssignment(guard$4, $if4.deploy_command_certainty, $else4.deploy_command_certainty, location(81, 5, 81, 22));


        }, () -> {
            $else3.deploy_command_certainty = booleanVariable(false, location(95, 32, 95, 36));

        });
        deploy_command_certainty = ifElseAssignment(guard$3, $if3.deploy_command_certainty, $else3.deploy_command_certainty, location(80, 3, 80, 15));
        deploy_command_certainty.setAlias("deploy_command_certainty");
        deploy_command_certainty.setLocation(location(79, 11, 79, 34));

        BooleanVariable deploy_kws_certainty;
        $IfElseMods5 $if5 = new $IfElseMods5();
        $IfElseMods5 $else5 = new $IfElseMods5();
        BooleanVariable guard$5 = ci_parsed.and(deploy_kws, location(100, 16, 100, 17));
        ifElse(guard$5, () -> {
            DoubleVariable p_deploy_kws = doubleVariable(0.7, location(101, 27, 101, 29)).times(doubleVariable(0.4, location(101, 31, 101, 33)), location(101, 30, 101, 30));
            p_deploy_kws.setAlias("p_deploy_kws");
            p_deploy_kws.setLocation(location(101, 12, 101, 23));

            $if5.deploy_kws_certainty = bernoulli(p_deploy_kws, location(102, 28, 102, 50)).sample(location(102, 52, 102, 59));

        }, () -> {
            $else5.deploy_kws_certainty = booleanVariable(false, location(104, 28, 104, 32));
        });
        deploy_kws_certainty = ifElseAssignment(guard$5, $if5.deploy_kws_certainty, $else5.deploy_kws_certainty, location(100, 3, 100, 29));
        deploy_kws_certainty.setAlias("deploy_kws_certainty");
        deploy_kws_certainty.setLocation(location(99, 11, 99, 30));


        Variable<?>[] $variableNames = {ci_parsed, deploy_action, deploy_command, deploy_kws, release_workflow_trigger_deploy, invalid_trigger, tested_deploy_action, step_uses_secrets_deploy, deploy_action_certainty, deploy_command_certainty, deploy_kws_certainty};
        String[] $constructorArgs = {"ci_parsed", "deploy_action", "deploy_command", "deploy_kws", "release_workflow_trigger_deploy", "invalid_trigger", "tested_deploy_action", "step_uses_secrets_deploy"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        $helperClasses.add("$IfElseMods3");
        $helperClasses.add("$IfElseMods4");
        $helperClasses.add("$IfElseMods5");
        return compileAPI(opts, $variableNames, "BuildCheck4", $helperClasses, "macaron", $constructorArgs, getOriginalModel(), "/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */");
    }

    private static String getOriginalModel() { 
        return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck4(boolean ci_parsed,\n                         boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy) \n{\n  //Calculate deploy_action_certainty\n  boolean deploy_action_certainty;\n  if(ci_parsed) {\n    if(deploy_action) {\n      double p_deploy_action = 0.8*0.9;\n      double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                 (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                 0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                bernoulli(p_tested_deploy_action).sample() ||\n                                bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_action_certainty = false;\n    }\n  } else {\n    deploy_action_certainty = false;\n  }\n\n  //Calculate deploy_command_certainty\n  boolean deploy_command_certainty;\n  if(ci_parsed) {\n    if(deploy_command) {\n      double p_deploy_command = 0.75*0.8;\n      double p_release_workflow_trigger_deploy = release_workflow_trigger_deploy ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n      double p_step_uses_secrets = step_uses_secrets_deploy ? 0.65*0.9 : 0.0;\n\n      deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                 bernoulli(p_release_workflow_trigger_deploy).sample() ||\n                                 bernoulli(p_step_uses_secrets).sample();\n    } else {\n      deploy_command_certainty = false;\n    }\n  } else {\n    deploy_command_certainty = false;\n  }\n\n  //Calculate deploy_kws_certainty\n  boolean deploy_kws_certainty;\n  if(ci_parsed && deploy_kws) {\n    double p_deploy_kws = 0.7*0.4;\n    deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n  } else\n    deploy_kws_certainty = false;\n}";
    }
}