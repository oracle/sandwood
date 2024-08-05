package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
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

public class HMMMetrics extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> cpu_measured = observeArray("cpu_measured", VariableType.arrayType(VariableType.DoubleVariable), location(3, 18, 3, 38));
        ArrayVariable<DoubleVariable> mem_measured = observeArray("mem_measured", VariableType.arrayType(VariableType.DoubleVariable), location(3, 41, 3, 61));
        ArrayVariable<DoubleVariable> pageFaults_measured = observeArray("pageFaults_measured", VariableType.arrayType(VariableType.DoubleVariable), location(3, 64, 3, 91));
        IntVariable noStates = observeInt("noStates", location(3, 94, 3, 105));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(6, 28, 6, 37), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(6, 42, 6, 44)); }, noStates);
        v.setAlias("v");
        v.setLocation(location(6, 14, 6, 14));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(7, 20, 7, 31)).sample(noStates, location(7, 33, 7, 48));
        m.setAlias("m");
        m.setLocation(location(7, 16, 7, 16));

        IntVariable samples = cpu_measured.length(location(10, 32, 10, 37));
        samples.setAlias("samples");
        samples.setLocation(location(10, 9, 10, 15));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(13, 23, 13, 31), VariableType.IntVariable, samples);
        st.setAlias("st");
        st.setLocation(location(13, 11, 13, 12));

        ArrayVariable<DoubleVariable> initialStateDistribution = dirichlet(v, location(16, 41, 16, 52)).sample(location(16, 54, 16, 61));
        initialStateDistribution.setAlias("initialStateDistribution");
        initialStateDistribution.setLocation(location(16, 14, 16, 37));

        st.put(intVariable(0, location(17, 8, 17, 8)), categorical(initialStateDistribution, location(17, 13, 17, 49)).sampleDistribution(location(17, 51, 17, 70)), location(17, 7, 17, 70));
        parFor(intVariable(1, location(20, 16, 20, 16)), samples, intVariable(1, location(20, 15, 20, 19)), true, location(20, 5, 20, 29), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(20, 13, 20, 13));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(21, 36, 21, 36)), location(21, 35, 21, 35)), location(21, 33, 21, 37)), location(21, 30, 21, 38)), location(21, 17, 21, 39)).sampleDistribution(location(21, 41, 21, 60)), location(21, 11, 21, 60));
        });

        ArrayVariable<DoubleVariable> cpu = Variable.arrayVariable(location(24, 30, 24, 38), VariableType.DoubleVariable, samples);
        cpu.setAlias("cpu");
        cpu.setLocation(location(24, 14, 24, 16));

        ArrayVariable<DoubleVariable> mem = Variable.arrayVariable(location(25, 30, 25, 38), VariableType.DoubleVariable, samples);
        mem.setAlias("mem");
        mem.setLocation(location(25, 14, 25, 16));

        ArrayVariable<DoubleVariable> pageFaults = Variable.arrayVariable(location(26, 37, 26, 45), VariableType.DoubleVariable, samples);
        pageFaults.setAlias("pageFaults");
        pageFaults.setLocation(location(26, 14, 26, 23));

        ArrayVariable<DoubleVariable> cpuMean = gaussian(intVariable(16, location(28, 33, 28, 34)), doubleVariable(8.6, location(28, 37, 28, 39)), location(28, 24, 28, 40)).sample(noStates, location(28, 42, 28, 57));
        cpuMean.setAlias("cpuMean");
        cpuMean.setLocation(location(28, 14, 28, 20));

        ArrayVariable<DoubleVariable> memMean = gaussian(intVariable(94, location(29, 33, 29, 34)), intVariable(1, location(29, 37, 29, 37)), location(29, 24, 29, 38)).sample(noStates, location(29, 40, 29, 55));
        memMean.setAlias("memMean");
        memMean.setLocation(location(29, 14, 29, 20));

        ArrayVariable<DoubleVariable> pageFaultsMean = gaussian(intVariable(814, location(30, 40, 30, 42)), intVariable(335550, location(30, 45, 30, 50)), location(30, 31, 30, 51)).sample(noStates, location(30, 53, 30, 68));
        pageFaultsMean.setAlias("pageFaultsMean");
        pageFaultsMean.setLocation(location(30, 14, 30, 27));

        ArrayVariable<DoubleVariable> cpuVar = inverseGamma(intVariable(5, location(32, 36, 32, 36)), doubleVariable(0.5, location(32, 39, 32, 41)), location(32, 23, 32, 42)).sample(noStates, location(32, 44, 32, 59));
        cpuVar.setAlias("cpuVar");
        cpuVar.setLocation(location(32, 14, 32, 19));

        ArrayVariable<DoubleVariable> memVar = inverseGamma(intVariable(5, location(33, 36, 33, 36)), doubleVariable(0.5, location(33, 39, 33, 41)), location(33, 23, 33, 42)).sample(noStates, location(33, 44, 33, 59));
        memVar.setAlias("memVar");
        memVar.setLocation(location(33, 14, 33, 19));

        ArrayVariable<DoubleVariable> pageFaultsVar = inverseGamma(intVariable(5, location(34, 43, 34, 43)), doubleVariable(0.5, location(34, 46, 34, 48)), location(34, 30, 34, 49)).sample(noStates, location(34, 51, 34, 66));
        pageFaultsVar.setAlias("pageFaultsVar");
        pageFaultsVar.setLocation(location(34, 14, 34, 26));

        parFor(intVariable(0, location(36, 16, 36, 16)), samples, intVariable(1, location(36, 15, 36, 19)), true, location(36, 5, 36, 29), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(36, 13, 36, 13));
            IntVariable s = st.get(i, location(37, 19, 37, 21));
            s.setAlias("s");
            s.setLocation(location(37, 13, 37, 13));

            cpu.put(i, gaussian(cpuMean.get(s, location(38, 34, 38, 36)), cpuVar.get(s, location(38, 45, 38, 47)), location(38, 18, 38, 48)).sample(location(38, 50, 38, 57)), location(38, 12, 38, 57));
            mem.put(i, gaussian(memMean.get(s, location(39, 34, 39, 36)), memVar.get(s, location(39, 45, 39, 47)), location(39, 18, 39, 48)).sample(location(39, 50, 39, 57)), location(39, 12, 39, 57));
            pageFaults.put(i, gaussian(pageFaultsMean.get(s, location(40, 48, 40, 50)), pageFaultsVar.get(s, location(40, 66, 40, 68)), location(40, 25, 40, 69)).sample(location(40, 71, 40, 78)), location(40, 19, 40, 78));

        });

        cpu.observe(cpu_measured, location(44, 9, 44, 29));
        mem.observe(mem_measured, location(45, 9, 45, 29));
        pageFaults.observe(pageFaults_measured, location(46, 16, 46, 43));

        Variable<?>[] $variableNames = {cpu_measured, mem_measured, pageFaults_measured, noStates, v, m, samples, st, initialStateDistribution, cpu, mem, pageFaults, cpuMean, memMean, pageFaultsMean, cpuVar, memVar, pageFaultsVar};
        String[] $constructorArgs = {"cpu_measured", "mem_measured", "pageFaults_measured", "noStates"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMMetrics", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "package org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n    \n    // Construct vectors describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    // Determine how many samples the model will need to produce.\n    int samples = cpu_measured.length;\n    \n    // Allocate space for the state.\n    int[] st = new int[samples];\n\n    // Set the initial state by sampling from a categorical with learnt weightings.\n    double[] initialStateDistribution = dirichlet(v).sample();\n    st[0] = categorical(initialStateDistribution).sampleDistribution();\n\n    //Determine the remaining states based on the previous state.\n    for(int i:[1 .. samples))\n        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n        \n    //Generate each metric.\n    double[] cpu = new double[samples];\n    double[] mem = new double[samples];\n    double[] pageFaults = new double[samples];\n    \n    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n    double[] memMean = gaussian(94, 1).sample(noStates);\n    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n    \n    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n    \n    for(int i:[0 .. samples)) {\n        int s = st[i];\n        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n    }\n\n    //Tie the values to the values we have measured.\n    cpu.observe(cpu_measured);\n    mem.observe(mem_measured);\n    pageFaults.observe(pageFaults_measured);\n}\n";
    }
}