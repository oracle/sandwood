package coinFlips;

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

public class Flips1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable heads1 = doubleVariable(0.5, location(18, 21, 18, 23));
        heads1.setAlias("heads1");
        heads1.setLocation(location(18, 12, 18, 17));

        DoubleVariable heads2 = doubleVariable(0.6, location(19, 21, 19, 23));
        heads2.setAlias("heads2");
        heads2.setLocation(location(19, 12, 19, 17));

        BooleanVariable coin1 = bernoulli(heads1, location(20, 21, 20, 37)).sample(location(20, 39, 20, 46));
        coin1.setAlias("coin1");
        coin1.setLocation(location(20, 13, 20, 17));

        BooleanVariable coin2 = bernoulli(heads2, location(21, 21, 21, 37)).sample(location(21, 39, 21, 46));
        coin2.setAlias("coin2");
        coin2.setLocation(location(21, 13, 21, 17));

        BooleanVariable twoHeads = coin1.and(coin2, location(22, 30, 22, 31));
        twoHeads.setAlias("twoHeads");
        twoHeads.setLocation(location(22, 13, 22, 20));


        Variable<?>[] $variableNames = {heads1, heads2, coin1, coin2, twoHeads};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flips1", $helperClasses, "coinFlips", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package coinFlips;\n\n/*\n * % Probabilistic facts:\n * 0.5::heads1.\n * 0.6::heads2.\n *\n * % Rules:\n * twoHeads :- heads1, heads2.\n *\n * % Queries:\n * query(heads1).\n * query(heads2).\n * query(twoHeads).\n */\n\npublic model Flips1() {\n    double heads1 = 0.5;\n    double heads2 = 0.6;\n    boolean coin1 = bernoulli(heads1).sample();\n    boolean coin2 = bernoulli(heads2).sample();\n    boolean twoHeads = coin1 && coin2;\n}";
    }
}