package dice;

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

public class Dice4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        ArrayVariable<DoubleVariable> p_die = Variable.arrayVariable(location(21, 32, 21, 34), VariableType.DoubleVariable, () -> { return intVariable(1, location(21, 39, 21, 39)).divide(intVariable(6, location(21, 41, 21, 41)), location(21, 40, 21, 40)); }, intVariable(6, location(21, 33, 21, 33)));
        p_die.setAlias("p_die");
        p_die.setLocation(location(21, 14, 21, 18));

        IntVariable die1 = categorical(p_die, location(23, 16, 23, 33)).sample(location(23, 35, 23, 42)).add(intVariable(1, location(23, 46, 23, 46)), location(23, 44, 23, 44));
        die1.setAlias("die1");
        die1.setLocation(location(23, 9, 23, 12));

        IntVariable die2 = categorical(p_die, location(24, 16, 24, 33)).sample(location(24, 35, 24, 42)).add(intVariable(1, location(24, 46, 24, 46)), location(24, 44, 24, 44));
        die2.setAlias("die2");
        die2.setLocation(location(24, 9, 24, 12));

        IntVariable die3 = categorical(p_die, location(25, 16, 25, 33)).sample(location(25, 35, 25, 42)).add(intVariable(1, location(25, 46, 25, 46)), location(25, 44, 25, 44));
        die3.setAlias("die3");
        die3.setLocation(location(25, 9, 25, 12));

        BooleanVariable increasing = die1.lessThan(die2, location(27, 31, 27, 31)).and(die2.lessThan(die3, location(27, 46, 27, 46)), location(27, 38, 27, 39));
        increasing.setAlias("increasing");
        increasing.setLocation(location(27, 13, 27, 22));

        IntVariable sum = die1.add(die2, location(28, 20, 28, 20)).add(die2, location(28, 27, 28, 27));
        sum.setAlias("sum");
        sum.setLocation(location(28, 9, 28, 11));

        increasing.observe(booleanVariable(true, location(30, 24, 30, 27)), location(30, 16, 30, 28));

        Variable<?>[] $variableNames = {p_die, die1, die2, die3, increasing, sum};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Dice4", $helperClasses, "dice", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package dice;\n\n/*\n * 1/6::dice(1,D); 1/6::dice(2,D); 1/6::dice(3,D); 1/6::dice(4,D); 1/6::dice(5,D); 1/6::dice(6,D) :- die(D).\n * \n * die(1).\n * die(2).\n * die(3).\n * \n * outcome(A,B,C) :- dice(A,1), dice(B,2), dice(C,3).\n * \n * increasing :- outcome(A,B,C), A<B, B<C.\n * \n * sum(S) :- outcome(A,B,C), S is A+B+C.\n * \n * query(sum(_)).\n * evidence(increasing).\n */\n\npublic model Dice4() {\n    double[] p_die = new double[6] <~ 1/6;\n    \n    int die1 = categorical(p_die).sample() + 1;\n    int die2 = categorical(p_die).sample() + 1;\n    int die3 = categorical(p_die).sample() + 1;\n    \n    boolean increasing = die1 < die2 && die2 < die3;\n    int sum = die1 + die2 + die2;\n    \n    increasing.observe(true);\n}";
    }
}