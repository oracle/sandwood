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

public class Dice1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        ArrayVariable<DoubleVariable> p_die1 = Variable.arrayVariable(location(22, 33, 22, 35), VariableType.DoubleVariable, () -> { return intVariable(1, location(22, 40, 22, 40)).divide(intVariable(6, location(22, 42, 22, 42)), location(22, 41, 22, 41)); }, intVariable(6, location(22, 34, 22, 34)));
        p_die1.setAlias("p_die1");
        p_die1.setLocation(location(22, 14, 22, 19));

        ArrayVariable<DoubleVariable> p_die2 = Variable.arrayVariable(location(23, 23, 23, 58), VariableType.DoubleVariable, 6);
        {
            p_die2.put(intVariable(0), doubleVariable(0.15, location(23, 24, 23, 27)), location(23, 23, 23, 27));
            p_die2.put(intVariable(1, location(23, 23, 23, 23)), doubleVariable(0.15, location(23, 30, 23, 33)), location(23, 23, 23, 33));
            p_die2.put(intVariable(2, location(23, 23, 23, 23)), doubleVariable(0.15, location(23, 36, 23, 39)), location(23, 23, 23, 39));
            p_die2.put(intVariable(3, location(23, 23, 23, 23)), doubleVariable(0.15, location(23, 42, 23, 45)), location(23, 23, 23, 45));
            p_die2.put(intVariable(4, location(23, 23, 23, 23)), doubleVariable(0.15, location(23, 48, 23, 51)), location(23, 23, 23, 51));
            p_die2.put(intVariable(5, location(23, 23, 23, 23)), doubleVariable(0.25, location(23, 54, 23, 57)), location(23, 23, 23, 57));
        }

        p_die2.setAlias("p_die2");
        p_die2.setLocation(location(23, 14, 23, 19));

        IntVariable die1 = categorical(p_die1, location(25, 16, 25, 34)).sample(location(25, 36, 25, 43)).add(intVariable(1, location(25, 47, 25, 47)), location(25, 45, 25, 45));
        die1.setAlias("die1");
        die1.setLocation(location(25, 9, 25, 12));

        IntVariable die2 = categorical(p_die2, location(26, 16, 26, 34)).sample(location(26, 36, 26, 43)).add(intVariable(1, location(26, 47, 26, 47)), location(26, 45, 26, 45));
        die2.setAlias("die2");
        die2.setLocation(location(26, 9, 26, 12));

        BooleanVariable twoSix = (die1.eq(intVariable(6, location(28, 31, 28, 31)), location(28, 28, 28, 29))).and((die2.eq(intVariable(6, location(28, 46, 28, 46)), location(28, 43, 28, 44))), location(28, 34, 28, 35));
        twoSix.setAlias("twoSix");
        twoSix.setLocation(location(28, 13, 28, 18));

        BooleanVariable someSix = (die1.eq(intVariable(6, location(29, 32, 29, 32)), location(29, 29, 29, 30))).or((die2.eq(intVariable(6, location(29, 47, 29, 47)), location(29, 44, 29, 45))), location(29, 35, 29, 36));
        someSix.setAlias("someSix");
        someSix.setLocation(location(29, 13, 29, 19));


        Variable<?>[] $variableNames = {p_die1, p_die2, die1, die2, twoSix, someSix};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Dice1", $helperClasses, "dice", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one1; 1/6::two1; 1/6::three1; 1/6::four1; 1/6::five1; 1/6::six1.\n * 0.15::one2; 0.15::two2; 0.15::three2; 0.15::four2; 0.15::five2; 0.25::six2.\n * \n * % Rules:\n * twoSix :- six1, six2.\n * \n * someSix :- six1.\n * someSix :- six2.\n * \n * % Queries:\n * query(six1).\n * query(six2).\n * query(twoSix).\n * query(someSix).\n */\n\npublic model Dice1() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean twoSix = (die1 == 6) && (die2 == 6);\n    boolean someSix = (die1 == 6) || (die2 == 6);\n}";
    }
}