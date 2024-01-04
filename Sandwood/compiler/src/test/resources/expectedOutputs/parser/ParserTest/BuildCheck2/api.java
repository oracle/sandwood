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

public class BuildCheck2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable p_deploy_action = observeDouble("p_deploy_action", location(44, 26, 44, 47));
        DoubleVariable p_deploy_command = observeDouble("p_deploy_command", location(45, 26, 45, 48));
        DoubleVariable p_deploy_kws = observeDouble("p_deploy_kws", location(46, 26, 46, 44));
        DoubleVariable p_release_workflow_trigger_deploy_command = observeDouble("p_release_workflow_trigger_deploy_command", location(47, 26, 47, 73));
        DoubleVariable p_release_workflow_trigger_deploy_action = observeDouble("p_release_workflow_trigger_deploy_action", location(48, 26, 48, 72));
        DoubleVariable p_tested_deploy_action = observeDouble("p_tested_deploy_action", location(49, 26, 49, 54));
        DoubleVariable p_step_uses_secrets_deploy_action = observeDouble("p_step_uses_secrets_deploy_action", location(50, 26, 50, 65));
        DoubleVariable p_step_uses_secrets_deploy_command = observeDouble("p_step_uses_secrets_deploy_command", location(51, 26, 51, 66));

        BooleanVariable deploy_action_certainty = bernoulli(doubleVariable(0.8, location(53, 47, 53, 49)).times(p_deploy_action, location(53, 51, 53, 51)), location(53, 37, 53, 68)).sample(location(53, 70, 53, 77)).or(bernoulli(doubleVariable(0.1, location(54, 47, 54, 49)).times(p_tested_deploy_action, location(54, 51, 54, 51)), location(54, 37, 54, 75)).sample(location(54, 77, 54, 84)), location(53, 79, 53, 80)).or(bernoulli(doubleVariable(0.85, location(55, 47, 55, 50)).times(p_release_workflow_trigger_deploy_action, location(55, 52, 55, 52)), location(55, 37, 55, 94)).sample(location(55, 96, 55, 103)), location(54, 86, 54, 87)).or(bernoulli(doubleVariable(0.65, location(56, 47, 56, 50)).times(p_step_uses_secrets_deploy_action, location(56, 52, 56, 52)), location(56, 37, 56, 87)).sample(location(56, 89, 56, 96)), location(55, 105, 55, 106));
        deploy_action_certainty.setAlias("deploy_action_certainty");
        deploy_action_certainty.setLocation(location(53, 11, 53, 33));

        BooleanVariable deploy_command_certainty = bernoulli(doubleVariable(0.75, location(58, 48, 58, 51)).times(p_deploy_command, location(58, 53, 58, 53)), location(58, 38, 58, 71)).sample(location(58, 73, 58, 80)).or(bernoulli(doubleVariable(0.85, location(59, 48, 59, 51)).times(p_release_workflow_trigger_deploy_command, location(59, 53, 59, 53)), location(59, 38, 59, 96)).sample(location(59, 98, 59, 105)), location(58, 82, 58, 83)).or(bernoulli(doubleVariable(0.65, location(60, 48, 60, 51)).times(p_step_uses_secrets_deploy_command, location(60, 53, 60, 53)), location(60, 38, 60, 89)).sample(location(60, 91, 60, 98)), location(59, 107, 59, 108));
        deploy_command_certainty.setAlias("deploy_command_certainty");
        deploy_command_certainty.setLocation(location(58, 11, 58, 34));

        BooleanVariable deploy_kws_certainty = bernoulli(doubleVariable(0.7, location(62, 44, 62, 46)).times(p_deploy_kws, location(62, 48, 62, 48)), location(62, 34, 62, 62)).sample(location(62, 64, 62, 71));
        deploy_kws_certainty.setAlias("deploy_kws_certainty");
        deploy_kws_certainty.setLocation(location(62, 11, 62, 30));


        Variable<?>[] $variableNames = {p_deploy_action, p_deploy_command, p_deploy_kws, p_release_workflow_trigger_deploy_command, p_release_workflow_trigger_deploy_action, p_tested_deploy_action, p_step_uses_secrets_deploy_action, p_step_uses_secrets_deploy_command, deploy_action_certainty, deploy_command_certainty, deploy_kws_certainty};
        String[] $constructorArgs = {"p_deploy_action", "p_deploy_command", "p_deploy_kws", "p_release_workflow_trigger_deploy_command", "p_release_workflow_trigger_deploy_action", "p_tested_deploy_action", "p_step_uses_secrets_deploy_action", "p_step_uses_secrets_deploy_command"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "BuildCheck2", $helperClasses, "macaron", $constructorArgs, getOriginalModel(), "/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */");
    }

    private static String getOriginalModel() { 
        return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n \n// All the arguments have either the value 0, or a constant, so it might be better to change \n// them to booleans and place the constants in the model where they could be inferred.\n//\n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n//\n// Simplified version of the first model.\npublic model BuildCheck2(double p_deploy_action, \n                         double p_deploy_command, \n                         double p_deploy_kws, \n                         double p_release_workflow_trigger_deploy_command,\n                         double p_release_workflow_trigger_deploy_action,\n                         double p_tested_deploy_action,\n                         double p_step_uses_secrets_deploy_action,\n                         double p_step_uses_secrets_deploy_command) \n{\n  boolean deploy_action_certainty = bernoulli(0.8 * p_deploy_action).sample() ||\n                                    bernoulli(0.1 * p_tested_deploy_action).sample() ||\n                                    bernoulli(0.85 * p_release_workflow_trigger_deploy_action).sample() ||\n                                    bernoulli(0.65 * p_step_uses_secrets_deploy_action).sample();\n\n  boolean deploy_command_certainty = bernoulli(0.75 * p_deploy_command).sample() ||\n                                     bernoulli(0.85 * p_release_workflow_trigger_deploy_command).sample() ||\n                                     bernoulli(0.65 * p_step_uses_secrets_deploy_command).sample();\n\n  boolean deploy_kws_certainty = bernoulli(0.7 * p_deploy_kws).sample();\n}";
    }
}