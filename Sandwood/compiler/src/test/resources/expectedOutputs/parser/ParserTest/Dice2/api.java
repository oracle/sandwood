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

public class Dice2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        ArrayVariable<DoubleVariable> p_die1 = Variable.arrayVariable(location(21, 33, 21, 35), VariableType.DoubleVariable, () -> { return intVariable(1, location(21, 40, 21, 40)).divide(intVariable(6, location(21, 42, 21, 42)), location(21, 41, 21, 41)); }, intVariable(6, location(21, 34, 21, 34)));
        p_die1.setAlias("p_die1");
        p_die1.setLocation(location(21, 14, 21, 19));

        ArrayVariable<DoubleVariable> p_die2 = Variable.arrayVariable(location(22, 23, 22, 58), VariableType.DoubleVariable, 6);
        {
            p_die2.put(intVariable(0), doubleVariable(0.15, location(22, 24, 22, 27)), location(22, 23, 22, 27));
            p_die2.put(intVariable(1, location(22, 23, 22, 23)), doubleVariable(0.15, location(22, 30, 22, 33)), location(22, 23, 22, 33));
            p_die2.put(intVariable(2, location(22, 23, 22, 23)), doubleVariable(0.15, location(22, 36, 22, 39)), location(22, 23, 22, 39));
            p_die2.put(intVariable(3, location(22, 23, 22, 23)), doubleVariable(0.15, location(22, 42, 22, 45)), location(22, 23, 22, 45));
            p_die2.put(intVariable(4, location(22, 23, 22, 23)), doubleVariable(0.15, location(22, 48, 22, 51)), location(22, 23, 22, 51));
            p_die2.put(intVariable(5, location(22, 23, 22, 23)), doubleVariable(0.25, location(22, 54, 22, 57)), location(22, 23, 22, 57));
        }

        p_die2.setAlias("p_die2");
        p_die2.setLocation(location(22, 14, 22, 19));

        IntVariable die1 = categorical(p_die1, location(24, 16, 24, 34)).sample(location(24, 36, 24, 43)).add(intVariable(1, location(24, 47, 24, 47)), location(24, 45, 24, 45));
        die1.setAlias("die1");
        die1.setLocation(location(24, 9, 24, 12));

        IntVariable die2 = categorical(p_die2, location(25, 16, 25, 34)).sample(location(25, 36, 25, 43)).add(intVariable(1, location(25, 47, 25, 47)), location(25, 45, 25, 45));
        die2.setAlias("die2");
        die2.setLocation(location(25, 9, 25, 12));

        BooleanVariable odd1 = odd(die1, location(27, 20, 27, 28));
        odd1.setAlias("odd1");
        odd1.setLocation(location(27, 13, 27, 16));

        BooleanVariable even1 = even(die1, location(28, 21, 28, 30));
        even1.setAlias("even1");
        even1.setLocation(location(28, 13, 28, 17));

        BooleanVariable odd2 = odd(die2, location(29, 20, 29, 28));
        odd2.setAlias("odd2");
        odd2.setLocation(location(29, 13, 29, 16));

        BooleanVariable even2 = even(die2, location(30, 21, 30, 30));
        even2.setAlias("even2");
        even2.setLocation(location(30, 13, 30, 17));


        Variable<?>[] $variableNames = {p_die1, p_die2, die1, die2, odd1, even1, odd2, even2};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Dice2", $helperClasses, "dice", $constructorArgs, getOriginalModel(), null);
    }

    private static BooleanVariable odd(IntVariable i, Location $location) { 
        return (i.remainder(intVariable(2, location(33, 19, 33, 19)), location(33, 18, 33, 18))).eq(intVariable(1, location(33, 23, 33, 23)), location(33, 21, 33, 22));
    }

    private static BooleanVariable even(IntVariable i, Location $location) { 
        return odd(i, location(37, 17, 37, 22)).negate(location(37, 16, 37, 16));
    }

    private static String getOriginalModel() { 
        return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one(1); 1/6::two(1); 1/6::three(1); 1/6::four(1); 1/6::five(1); 1/6::six(1).\n * 0.15::one(2); 0.15::two(2); 0.15::three(2); 0.15::four(2); 0.15::five(2); 0.25::six(2).\n * \n * % Rules:\n * odd(X) :- one(X).\n * odd(X) :- three(X).\n * odd(X) :- five(X).\n * even(X) :- \\+ odd(X).\n * \n * query(odd(1)).\n * query(even(1)).\n * query(odd(2)).\n * query(even(2)).\n */\n\npublic model Dice2() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean odd1 = odd(die1);\n    boolean even1 = even(die1);\n    boolean odd2 = odd(die2);\n    boolean even2 = even(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
    }
}