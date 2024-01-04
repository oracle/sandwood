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

public class BuildCheck extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable p_deploy_action = observeDouble("p_deploy_action", location(43, 25, 43, 46));
        DoubleVariable p_deploy_command = observeDouble("p_deploy_command", location(44, 25, 44, 47));
        DoubleVariable p_deploy_kws = observeDouble("p_deploy_kws", location(45, 25, 45, 43));
        DoubleVariable p_release_workflow_trigger_deploy_command = observeDouble("p_release_workflow_trigger_deploy_command", location(46, 25, 46, 72));
        DoubleVariable p_release_workflow_trigger_deploy_action = observeDouble("p_release_workflow_trigger_deploy_action", location(47, 25, 47, 71));
        DoubleVariable p_tested_deploy_action = observeDouble("p_tested_deploy_action", location(48, 25, 48, 53));
        DoubleVariable p_step_uses_secrets_deploy_action = observeDouble("p_step_uses_secrets_deploy_action", location(49, 25, 49, 64));
        DoubleVariable p_step_uses_secrets_deploy_command = observeDouble("p_step_uses_secrets_deploy_command", location(50, 25, 50, 65));

        BooleanVariable deploy_action = bernoulli(p_deploy_action, location(52, 27, 52, 52)).sample(location(52, 54, 52, 61));
        deploy_action.setAlias("deploy_action");
        deploy_action.setLocation(location(52, 11, 52, 23));

        BooleanVariable tested_deploy_action = bernoulli(p_tested_deploy_action, location(53, 34, 53, 66)).sample(location(53, 68, 53, 75));
        tested_deploy_action.setAlias("tested_deploy_action");
        tested_deploy_action.setLocation(location(53, 11, 53, 30));

        BooleanVariable release_workflow_trigger_deploy_action = bernoulli(p_release_workflow_trigger_deploy_action, location(54, 52, 54, 102)).sample(location(54, 104, 54, 111));
        release_workflow_trigger_deploy_action.setAlias("release_workflow_trigger_deploy_action");
        release_workflow_trigger_deploy_action.setLocation(location(54, 11, 54, 48));

        BooleanVariable step_uses_secrets_deploy_action = bernoulli(p_step_uses_secrets_deploy_action, location(55, 45, 55, 88)).sample(location(55, 90, 55, 97));
        step_uses_secrets_deploy_action.setAlias("step_uses_secrets_deploy_action");
        step_uses_secrets_deploy_action.setLocation(location(55, 11, 55, 41));

        BooleanVariable deploy_action_certainty = (ifElseLambdaAssignment(deploy_action, () -> { return bernoulli(doubleVariable(0.8, location(57, 62, 57, 64)), location(57, 52, 57, 65)).sample(location(57, 67, 57, 74)); }, () -> { return booleanVariable(false, location(57, 76, 57, 80)); }, location(57, 38, 57, 80))).or((ifElseLambdaAssignment(tested_deploy_action, () -> { return bernoulli(doubleVariable(0.1, location(58, 69, 58, 71)), location(58, 59, 58, 72)).sample(location(58, 74, 58, 81)); }, () -> { return booleanVariable(false, location(58, 83, 58, 87)); }, location(58, 38, 58, 87))), location(57, 83, 57, 84)).or((ifElseLambdaAssignment(release_workflow_trigger_deploy_action, () -> { return bernoulli(doubleVariable(0.85, location(59, 87, 59, 90)), location(59, 77, 59, 91)).sample(location(59, 93, 59, 100)); }, () -> { return booleanVariable(false, location(59, 102, 59, 106)); }, location(59, 38, 59, 106))), location(58, 90, 58, 91)).or((ifElseLambdaAssignment(step_uses_secrets_deploy_action, () -> { return bernoulli(doubleVariable(0.65, location(60, 80, 60, 83)), location(60, 70, 60, 84)).sample(location(60, 86, 60, 93)); }, () -> { return booleanVariable(false, location(60, 95, 60, 99)); }, location(60, 38, 60, 99))), location(59, 109, 59, 110));
        deploy_action_certainty.setAlias("deploy_action_certainty");
        deploy_action_certainty.setLocation(location(57, 11, 57, 33));

        BooleanVariable deploy_command = bernoulli(p_deploy_command, location(63, 28, 63, 54)).sample(location(63, 56, 63, 63));
        deploy_command.setAlias("deploy_command");
        deploy_command.setLocation(location(63, 11, 63, 24));

        BooleanVariable release_workflow_trigger_deploy_command = bernoulli(p_release_workflow_trigger_deploy_command, location(64, 53, 64, 104)).sample(location(64, 106, 64, 113));
        release_workflow_trigger_deploy_command.setAlias("release_workflow_trigger_deploy_command");
        release_workflow_trigger_deploy_command.setLocation(location(64, 11, 64, 49));

        BooleanVariable step_uses_secrets_deploy_command = bernoulli(p_step_uses_secrets_deploy_command, location(65, 46, 65, 90)).sample(location(65, 92, 65, 99));
        step_uses_secrets_deploy_command.setAlias("step_uses_secrets_deploy_command");
        step_uses_secrets_deploy_command.setLocation(location(65, 11, 65, 42));

        BooleanVariable deploy_command_certainty = (ifElseLambdaAssignment(deploy_command, () -> { return bernoulli(doubleVariable(0.75, location(67, 64, 67, 67)), location(67, 54, 67, 68)).sample(location(67, 70, 67, 77)); }, () -> { return booleanVariable(false, location(67, 79, 67, 83)); }, location(67, 39, 67, 83))).or((ifElseLambdaAssignment(release_workflow_trigger_deploy_command, () -> { return bernoulli(doubleVariable(0.85, location(68, 89, 68, 92)), location(68, 79, 68, 93)).sample(location(68, 95, 68, 102)); }, () -> { return booleanVariable(false, location(68, 104, 68, 108)); }, location(68, 39, 68, 108))), location(67, 86, 67, 87)).or((ifElseLambdaAssignment(step_uses_secrets_deploy_command, () -> { return bernoulli(doubleVariable(0.65, location(69, 82, 69, 85)), location(69, 72, 69, 86)).sample(location(69, 88, 69, 95)); }, () -> { return booleanVariable(false, location(69, 97, 69, 101)); }, location(69, 39, 69, 101))), location(68, 111, 68, 112));
        deploy_command_certainty.setAlias("deploy_command_certainty");
        deploy_command_certainty.setLocation(location(67, 11, 67, 34));

        BooleanVariable deploy_kws = bernoulli(p_deploy_kws, location(71, 24, 71, 46)).sample(location(71, 48, 71, 55));
        deploy_kws.setAlias("deploy_kws");
        deploy_kws.setLocation(location(71, 11, 71, 20));

        BooleanVariable deploy_kws_certainty = ifElseLambdaAssignment(deploy_kws, () -> { return bernoulli(doubleVariable(0.7, location(73, 55, 73, 57)), location(73, 45, 73, 58)).sample(location(73, 60, 73, 67)); }, () -> { return booleanVariable(false, location(73, 69, 73, 73)); }, location(73, 34, 73, 73));
        deploy_kws_certainty.setAlias("deploy_kws_certainty");
        deploy_kws_certainty.setLocation(location(73, 11, 73, 30));


        Variable<?>[] $variableNames = {p_deploy_action, p_deploy_command, p_deploy_kws, p_release_workflow_trigger_deploy_command, p_release_workflow_trigger_deploy_action, p_tested_deploy_action, p_step_uses_secrets_deploy_action, p_step_uses_secrets_deploy_command, deploy_action, tested_deploy_action, release_workflow_trigger_deploy_action, step_uses_secrets_deploy_action, deploy_action_certainty, deploy_command, release_workflow_trigger_deploy_command, step_uses_secrets_deploy_command, deploy_command_certainty, deploy_kws, deploy_kws_certainty};
        String[] $constructorArgs = {"p_deploy_action", "p_deploy_command", "p_deploy_kws", "p_release_workflow_trigger_deploy_command", "p_release_workflow_trigger_deploy_action", "p_tested_deploy_action", "p_step_uses_secrets_deploy_action", "p_step_uses_secrets_deploy_command"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "BuildCheck", $helperClasses, "macaron", $constructorArgs, getOriginalModel(), "/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */");
    }

    private static String getOriginalModel() { 
        return "package macaron;\n\n/**\n * :- use_module('src/macaron/slsa_analyzer/checks/problog_predicates.py').\n *\n * A :: ci_parsed :- ci_parsed_check(A).\n * B :: deploy_action :- deploy_action_check(B).\n * C :: deploy_command :- deploy_command_check(C).\n * D :: deploy_kws :- deploy_kws_check(D).\n * E :: release_workflow_trigger_deploy_command :- release_workflow_trigger_deploy_command_check(E).\n * F :: release_workflow_trigger_deploy_action :- release_workflow_trigger_deploy_action_check(F).\n * G :: tested_deploy_action :- tested_deploy_action_check(G).\n * H :: publishing_workflow_deploy_command :- publishing_workflow_deploy_command_check(H).\n * I :: publishing_workflow_deploy_action :- publishing_workflow_deploy_action_check(I).\n * J :: step_uses_secrets_deploy_action :- step_uses_secrets_deploy_action_check(J).\n * K :: step_uses_secrets_deploy_command :- step_uses_secrets_deploy_command_check(K).\n *\n * 0.8 :: deploy_action_certainty :- deploy_action.\n * 0.10 :: deploy_action_certainty :- tested_deploy_action.\n * 0.85 :: deploy_action_certainty :- release_workflow_trigger_deploy_action.\n * %0.95 :: deploy_action_certainty :- publishing_workflow_deploy_action.\n * 0.65 :: deploy_action_certainty :- step_uses_secrets_deploy_action.\n *\n * 0.75 :: deploy_command_certainty :- deploy_command.\n * 0.85 :: deploy_command_certainty :- release_workflow_trigger_deploy_command.\n * %0.95 :: deploy_command_certainty :- publishing_workflow_deploy_command.\n * 0.65 :: deploy_command_certainty :- step_uses_secrets_deploy_command.\n *\n * 0.70 :: deploy_kws_certainty :- deploy_kws.\n *\n * query(deploy_command_certainty).\n * query(deploy_action_certainty).\n * query(deploy_kws_certainty).\n */\n \n// All the arguments have either the value 0, or a constant, so it might be better to change \n// them to booleans and place the constants in the model where they could be inferred.\n//\n// The probabilities are also not independent for example if p_deploy_action is 0 \n// release_workflow_trigger_deploy_action_check will also be 0. This could also be \n// added to the model.\n\npublic model BuildCheck(double p_deploy_action, \n                        double p_deploy_command, \n                        double p_deploy_kws, \n                        double p_release_workflow_trigger_deploy_command,\n                        double p_release_workflow_trigger_deploy_action,\n                        double p_tested_deploy_action,\n                        double p_step_uses_secrets_deploy_action,\n                        double p_step_uses_secrets_deploy_command) \n{\n  boolean deploy_action = bernoulli(p_deploy_action).sample();\n  boolean tested_deploy_action = bernoulli(p_tested_deploy_action).sample();\n  boolean release_workflow_trigger_deploy_action = bernoulli(p_release_workflow_trigger_deploy_action).sample();\n  boolean step_uses_secrets_deploy_action = bernoulli(p_step_uses_secrets_deploy_action).sample();\n  \n  boolean deploy_action_certainty = (deploy_action?bernoulli(0.8).sample():false) ||\n                                    (tested_deploy_action?bernoulli(0.1).sample():false) ||\n                                    (release_workflow_trigger_deploy_action?bernoulli(0.85).sample():false) ||\n                                    (step_uses_secrets_deploy_action?bernoulli(0.65).sample():false);\n\n\n  boolean deploy_command = bernoulli(p_deploy_command).sample();\n  boolean release_workflow_trigger_deploy_command = bernoulli(p_release_workflow_trigger_deploy_command).sample();\n  boolean step_uses_secrets_deploy_command = bernoulli(p_step_uses_secrets_deploy_command).sample();\n  \n  boolean deploy_command_certainty = (deploy_command?bernoulli(0.75).sample():false) ||\n                                     (release_workflow_trigger_deploy_command?bernoulli(0.85).sample():false) ||\n                                     (step_uses_secrets_deploy_command?bernoulli(0.65).sample():false);\n\n  boolean deploy_kws = bernoulli(p_deploy_kws).sample();\n\n  boolean deploy_kws_certainty = deploy_kws?bernoulli(0.7).sample():false;\n}";
    }
}