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

public class BuildCheck3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        BooleanVariable deploy_action = observeBoolean("deploy_action", location(47, 26, 47, 46));
        BooleanVariable deploy_command = observeBoolean("deploy_command", location(48, 26, 48, 47));
        BooleanVariable deploy_kws = observeBoolean("deploy_kws", location(49, 26, 49, 43));
        BooleanVariable release_workflow_trigger_deploy_command = observeBoolean("release_workflow_trigger_deploy_command", location(50, 26, 50, 72));
        BooleanVariable release_workflow_trigger_deploy_action = observeBoolean("release_workflow_trigger_deploy_action", location(51, 26, 51, 71));
        BooleanVariable invalid_trigger = observeBoolean("invalid_trigger", location(52, 26, 52, 48));
        BooleanVariable tested_deploy_action = observeBoolean("tested_deploy_action", location(53, 26, 53, 53));
        BooleanVariable step_uses_secrets_deploy_action = observeBoolean("step_uses_secrets_deploy_action", location(54, 26, 54, 64));
        BooleanVariable step_uses_secrets_deploy_command = observeBoolean("step_uses_secrets_deploy_command", location(55, 26, 55, 65));

        DoubleVariable p_deploy_action = ifElseLambdaAssignment(deploy_action, () -> { return doubleVariable(0.8, location(58, 44, 58, 46)).times(doubleVariable(0.9, location(58, 48, 58, 50)), location(58, 47, 58, 47)); }, () -> { return doubleVariable(0.0, location(58, 54, 58, 56)); }, location(58, 28, 58, 56));
        p_deploy_action.setAlias("p_deploy_action");
        p_deploy_action.setLocation(location(58, 10, 58, 24));

        DoubleVariable p_tested_deploy_action = ifElseLambdaAssignment(tested_deploy_action, () -> { return doubleVariable(0.1, location(59, 58, 59, 60)).times(doubleVariable(0.9, location(59, 64, 59, 66)), location(59, 62, 59, 62)); }, () -> { return doubleVariable(0.0, location(59, 70, 59, 72)); }, location(59, 35, 59, 72));
        p_tested_deploy_action.setAlias("p_tested_deploy_action");
        p_tested_deploy_action.setLocation(location(59, 10, 59, 31));

        DoubleVariable p_release_workflow_trigger_deploy_action = ifElseLambdaAssignment(release_workflow_trigger_deploy_action, () -> { return (ifElseLambdaAssignment(invalid_trigger, () -> { return doubleVariable(0.75, location(61, 75, 61, 78)).times(doubleVariable(0.85, location(61, 80, 61, 83)), location(61, 79, 61, 79)); }, () -> { return doubleVariable(0.9, location(61, 87, 61, 89)).times(doubleVariable(0.85, location(61, 91, 61, 94)), location(61, 90, 61, 90)); }, location(61, 57, 61, 90))); }, () -> { return doubleVariable(0.0, location(62, 56, 62, 58)); }, location(60, 53, 62, 58));
        p_release_workflow_trigger_deploy_action.setAlias("p_release_workflow_trigger_deploy_action");
        p_release_workflow_trigger_deploy_action.setLocation(location(60, 10, 60, 49));

        DoubleVariable p_step_uses_secrets_deploy_action = ifElseLambdaAssignment(step_uses_secrets_deploy_action, () -> { return doubleVariable(0.65, location(63, 80, 63, 83)).times(doubleVariable(0.9, location(63, 85, 63, 87)), location(63, 84, 63, 84)); }, () -> { return intVariable(0, location(63, 91, 63, 91)); }, location(63, 46, 63, 91));
        p_step_uses_secrets_deploy_action.setAlias("p_step_uses_secrets_deploy_action");
        p_step_uses_secrets_deploy_action.setLocation(location(63, 10, 63, 42));

        BooleanVariable deploy_action_certainty = bernoulli(p_deploy_action, location(65, 37, 65, 62)).sample(location(65, 64, 65, 71)).or(bernoulli(p_tested_deploy_action, location(66, 37, 66, 69)).sample(location(66, 71, 66, 78)), location(65, 73, 65, 74)).or(bernoulli(p_release_workflow_trigger_deploy_action, location(67, 37, 67, 87)).sample(location(67, 89, 67, 96)), location(66, 80, 66, 81)).or(bernoulli(p_step_uses_secrets_deploy_action, location(68, 37, 68, 80)).sample(location(68, 82, 68, 89)), location(67, 98, 67, 99));
        deploy_action_certainty.setAlias("deploy_action_certainty");
        deploy_action_certainty.setLocation(location(65, 11, 65, 33));

        DoubleVariable p_deploy_command = ifElseLambdaAssignment(deploy_command, () -> { return doubleVariable(0.75, location(71, 46, 71, 49)).times(doubleVariable(0.8, location(71, 51, 71, 53)), location(71, 50, 71, 50)); }, () -> { return doubleVariable(0.0, location(71, 57, 71, 59)); }, location(71, 29, 71, 59));
        p_deploy_command.setAlias("p_deploy_command");
        p_deploy_command.setLocation(location(71, 10, 71, 25));

        DoubleVariable p_release_workflow_trigger_deploy_command = ifElseLambdaAssignment(release_workflow_trigger_deploy_command, () -> { return (ifElseLambdaAssignment(invalid_trigger, () -> { return doubleVariable(0.75, location(73, 75, 73, 78)).times(doubleVariable(0.85, location(73, 80, 73, 83)), location(73, 79, 73, 79)); }, () -> { return doubleVariable(0.9, location(73, 87, 73, 89)).times(doubleVariable(0.85, location(73, 91, 73, 94)), location(73, 90, 73, 90)); }, location(73, 57, 73, 90))); }, () -> { return doubleVariable(0.0, location(74, 56, 74, 58)); }, location(72, 54, 74, 58));
        p_release_workflow_trigger_deploy_command.setAlias("p_release_workflow_trigger_deploy_command");
        p_release_workflow_trigger_deploy_command.setLocation(location(72, 10, 72, 50));

        DoubleVariable p_step_uses_secrets_deploy_command = ifElseLambdaAssignment(step_uses_secrets_deploy_command, () -> { return doubleVariable(0.65, location(75, 82, 75, 85)).times(doubleVariable(0.9, location(75, 87, 75, 89)), location(75, 86, 75, 86)); }, () -> { return doubleVariable(0.0, location(75, 93, 75, 95)); }, location(75, 47, 75, 95));
        p_step_uses_secrets_deploy_command.setAlias("p_step_uses_secrets_deploy_command");
        p_step_uses_secrets_deploy_command.setLocation(location(75, 10, 75, 43));

        BooleanVariable deploy_command_certainty = bernoulli(p_deploy_command, location(77, 38, 77, 64)).sample(location(77, 66, 77, 73)).or(bernoulli(p_release_workflow_trigger_deploy_command, location(78, 38, 78, 89)).sample(location(78, 91, 78, 98)), location(77, 75, 77, 76)).or(bernoulli(p_step_uses_secrets_deploy_command, location(79, 38, 79, 82)).sample(location(79, 84, 79, 91)), location(78, 100, 78, 101));
        deploy_command_certainty.setAlias("deploy_command_certainty");
        deploy_command_certainty.setLocation(location(77, 11, 77, 34));

        DoubleVariable p_deploy_kws = ifElseLambdaAssignment(deploy_kws, () -> { return doubleVariable(0.7, location(82, 38, 82, 40)).times(doubleVariable(0.4, location(82, 42, 82, 44)), location(82, 41, 82, 41)); }, () -> { return doubleVariable(0.0, location(82, 48, 82, 50)); }, location(82, 25, 82, 50));
        p_deploy_kws.setAlias("p_deploy_kws");
        p_deploy_kws.setLocation(location(82, 10, 82, 21));

        BooleanVariable deploy_kws_certainty = bernoulli(p_deploy_kws, location(84, 34, 84, 56)).sample(location(84, 58, 84, 65));
        deploy_kws_certainty.setAlias("deploy_kws_certainty");
        deploy_kws_certainty.setLocation(location(84, 11, 84, 30));


        Variable<?>[] $variableNames = {deploy_action, deploy_command, deploy_kws, release_workflow_trigger_deploy_command, release_workflow_trigger_deploy_action, invalid_trigger, tested_deploy_action, step_uses_secrets_deploy_action, step_uses_secrets_deploy_command, p_deploy_action, p_tested_deploy_action, p_release_workflow_trigger_deploy_action, p_step_uses_secrets_deploy_action, deploy_action_certainty, p_deploy_command, p_release_workflow_trigger_deploy_command, p_step_uses_secrets_deploy_command, deploy_command_certainty, p_deploy_kws, deploy_kws_certainty};
        String[] $constructorArgs = {"deploy_action", "deploy_command", "deploy_kws", "release_workflow_trigger_deploy_command", "release_workflow_trigger_deploy_action", "invalid_trigger", "tested_deploy_action", "step_uses_secrets_deploy_action", "step_uses_secrets_deploy_command"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "BuildCheck3", $helperClasses, "macaron", $constructorArgs, getOriginalModel(), "/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */");
    }

    private static String getOriginalModel() { 
        return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n\n//Version where the probabilities hard coded in Python are brought into the model.\n//The constants will be multiplied together within the model, and could be multiplied \n//by us, but ultimately they should be inferred.\n \n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\n\n// Simplified version of the second model.\n\npublic model BuildCheck3(boolean deploy_action, \n                         boolean deploy_command, \n                         boolean deploy_kws, \n                         boolean release_workflow_trigger_deploy_command,\n                         boolean release_workflow_trigger_deploy_action,\n                         boolean invalid_trigger,\n                         boolean tested_deploy_action,\n                         boolean step_uses_secrets_deploy_action,\n                         boolean step_uses_secrets_deploy_command) \n{\n  //Calculate deploy_action_certainty\n  double p_deploy_action = deploy_action ? 0.8*0.9 : 0.0;\n  double p_tested_deploy_action = tested_deploy_action ? 0.1 * 0.9 : 0.0;\n  double p_release_workflow_trigger_deploy_action = release_workflow_trigger_deploy_action ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_action = step_uses_secrets_deploy_action ? 0.65*0.9 : 0;\n  \n  boolean deploy_action_certainty = bernoulli(p_deploy_action).sample() ||\n                                    bernoulli(p_tested_deploy_action).sample() ||\n                                    bernoulli(p_release_workflow_trigger_deploy_action).sample() ||\n                                    bernoulli(p_step_uses_secrets_deploy_action).sample();\n\n  //Calculate deploy_command_certainty\n  double p_deploy_command = deploy_command ? 0.75*0.8 : 0.0;\n  double p_release_workflow_trigger_deploy_command = release_workflow_trigger_deploy_command ?\n                                                       (invalid_trigger ? 0.75*0.85 : 0.9*0.85) :\n                                                       0.0;\n  double p_step_uses_secrets_deploy_command = step_uses_secrets_deploy_command ? 0.65*0.9 : 0.0;\n\n  boolean deploy_command_certainty = bernoulli(p_deploy_command).sample() ||\n                                     bernoulli(p_release_workflow_trigger_deploy_command).sample() ||\n                                     bernoulli(p_step_uses_secrets_deploy_command).sample();\n\n  //Calculate deploy_kws_certainty\n  double p_deploy_kws = deploy_kws ? 0.7*0.4 : 0.0;\n\n  boolean deploy_kws_certainty = bernoulli(p_deploy_kws).sample();\n}";
    }
}