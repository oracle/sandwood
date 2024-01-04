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

public class Dice3 extends GeneratedAPIBuilder {
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

        IntVariable sum = die1.add(die2, location(26, 20, 26, 20));
        sum.setAlias("sum");
        sum.setLocation(location(26, 9, 26, 11));

        BooleanVariable even = even(die1, location(27, 20, 27, 29));
        even.setAlias("even");
        even.setLocation(location(27, 13, 27, 16));

        BooleanVariable odd = odd(die2, location(28, 19, 28, 27));
        odd.setAlias("odd");
        odd.setLocation(location(28, 13, 28, 15));


        Variable<?>[] $variableNames = {p_die, die1, die2, sum, even, odd};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Dice3", $helperClasses, "dice", $constructorArgs, getOriginalModel(), null);
    }

    private static BooleanVariable odd(IntVariable i, Location $location) { 
        return (i.remainder(intVariable(2, location(31, 19, 31, 19)), location(31, 18, 31, 18))).eq(intVariable(1, location(31, 23, 31, 23)), location(31, 21, 31, 22));
    }

    private static BooleanVariable even(IntVariable i, Location $location) { 
        return odd(i, location(35, 17, 35, 22)).negate(location(35, 16, 35, 16));
    }

    private static String getOriginalModel() { 
        return "package dice;\n\n/*\n * 1/6::dice(1,D); 1/6::dice(2,D); 1/6::dice(3,D); 1/6::dice(4,D); 1/6::dice(5,D); 1/6::dice(6,D) :- die(D).\n * \n * die(1).\n * die(2).\n * \n * sum(S) :- dice(A,1), dice(B,2), S is A+B.\n * odd(D) :- dice(1,D).\n * odd(D) :- dice(3,D).\n * odd(D) :- dice(5,D).\n * even(D) :- \\+ odd(D).\n * \n * query(sum(_)).\n * evidence(even(1)).\n * evidence(odd(2)).\n */\n\npublic model Dice3() {\n    double[] p_die = new double[6] <~ 1/6;\n    \n    int die1 = categorical(p_die).sample() + 1;\n    int die2 = categorical(p_die).sample() + 1;\n    \n    int sum = die1 + die2;\n    boolean even = even(die1);\n    boolean odd = odd(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
    }
}