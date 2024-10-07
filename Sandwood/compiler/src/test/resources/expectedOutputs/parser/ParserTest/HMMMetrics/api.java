package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
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
        ArrayVariable<DoubleVariable> cpu_measured = observeArray("cpu_measured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 18, 11, 38));
        ArrayVariable<DoubleVariable> mem_measured = observeArray("mem_measured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 41, 11, 61));
        ArrayVariable<DoubleVariable> pageFaults_measured = observeArray("pageFaults_measured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 64, 11, 91));
        IntVariable noStates = observeInt("noStates", location(11, 94, 11, 105));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(14, 28, 14, 37), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(14, 42, 14, 44)); }, noStates);
        v.setAlias("v");
        v.setLocation(location(14, 14, 14, 14));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(15, 20, 15, 31)).sample(noStates, location(15, 33, 15, 48));
        m.setAlias("m");
        m.setLocation(location(15, 16, 15, 16));

        IntVariable samples = cpu_measured.length(location(18, 32, 18, 37));
        samples.setAlias("samples");
        samples.setLocation(location(18, 9, 18, 15));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(21, 23, 21, 31), VariableType.IntVariable, samples);
        st.setAlias("st");
        st.setLocation(location(21, 11, 21, 12));

        ArrayVariable<DoubleVariable> initialStateDistribution = dirichlet(v, location(24, 41, 24, 52)).sample(location(24, 54, 24, 61));
        initialStateDistribution.setAlias("initialStateDistribution");
        initialStateDistribution.setLocation(location(24, 14, 24, 37));

        st.put(intVariable(0, location(25, 8, 25, 8)), categorical(initialStateDistribution, location(25, 13, 25, 49)).sampleDistribution(location(25, 51, 25, 70)), location(25, 7, 25, 70));
        parFor(intVariable(1, location(28, 16, 28, 16)), samples, intVariable(1, location(28, 15, 28, 19)), true, location(28, 5, 28, 29), (i) -> {
            i.setAlias("i");
            i.setLocation(location(28, 13, 28, 13));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(29, 36, 29, 36)), location(29, 35, 29, 35)), location(29, 33, 29, 37)), location(29, 30, 29, 38)), location(29, 17, 29, 39)).sampleDistribution(location(29, 41, 29, 60)), location(29, 11, 29, 60));
        });

        ArrayVariable<DoubleVariable> cpu = Variable.arrayVariable(location(32, 30, 32, 38), VariableType.DoubleVariable, samples);
        cpu.setAlias("cpu");
        cpu.setLocation(location(32, 14, 32, 16));

        ArrayVariable<DoubleVariable> mem = Variable.arrayVariable(location(33, 30, 33, 38), VariableType.DoubleVariable, samples);
        mem.setAlias("mem");
        mem.setLocation(location(33, 14, 33, 16));

        ArrayVariable<DoubleVariable> pageFaults = Variable.arrayVariable(location(34, 37, 34, 45), VariableType.DoubleVariable, samples);
        pageFaults.setAlias("pageFaults");
        pageFaults.setLocation(location(34, 14, 34, 23));

        ArrayVariable<DoubleVariable> cpuMean = gaussian(intVariable(16, location(36, 33, 36, 34)), doubleVariable(8.6, location(36, 37, 36, 39)), location(36, 24, 36, 40)).sample(noStates, location(36, 42, 36, 57));
        cpuMean.setAlias("cpuMean");
        cpuMean.setLocation(location(36, 14, 36, 20));

        ArrayVariable<DoubleVariable> memMean = gaussian(intVariable(94, location(37, 33, 37, 34)), intVariable(1, location(37, 37, 37, 37)), location(37, 24, 37, 38)).sample(noStates, location(37, 40, 37, 55));
        memMean.setAlias("memMean");
        memMean.setLocation(location(37, 14, 37, 20));

        ArrayVariable<DoubleVariable> pageFaultsMean = gaussian(intVariable(814, location(38, 40, 38, 42)), intVariable(335550, location(38, 45, 38, 50)), location(38, 31, 38, 51)).sample(noStates, location(38, 53, 38, 68));
        pageFaultsMean.setAlias("pageFaultsMean");
        pageFaultsMean.setLocation(location(38, 14, 38, 27));

        ArrayVariable<DoubleVariable> cpuVar = inverseGamma(intVariable(5, location(40, 36, 40, 36)), doubleVariable(0.5, location(40, 39, 40, 41)), location(40, 23, 40, 42)).sample(noStates, location(40, 44, 40, 59));
        cpuVar.setAlias("cpuVar");
        cpuVar.setLocation(location(40, 14, 40, 19));

        ArrayVariable<DoubleVariable> memVar = inverseGamma(intVariable(5, location(41, 36, 41, 36)), doubleVariable(0.5, location(41, 39, 41, 41)), location(41, 23, 41, 42)).sample(noStates, location(41, 44, 41, 59));
        memVar.setAlias("memVar");
        memVar.setLocation(location(41, 14, 41, 19));

        ArrayVariable<DoubleVariable> pageFaultsVar = inverseGamma(intVariable(5, location(42, 43, 42, 43)), doubleVariable(0.5, location(42, 46, 42, 48)), location(42, 30, 42, 49)).sample(noStates, location(42, 51, 42, 66));
        pageFaultsVar.setAlias("pageFaultsVar");
        pageFaultsVar.setLocation(location(42, 14, 42, 26));

        parFor(intVariable(0, location(44, 16, 44, 16)), samples, intVariable(1, location(44, 15, 44, 19)), true, location(44, 5, 44, 29), (i) -> {
            i.setAlias("i");
            i.setLocation(location(44, 13, 44, 13));
            IntVariable s = st.get(i, location(45, 19, 45, 21));
            s.setAlias("s");
            s.setLocation(location(45, 13, 45, 13));

            cpu.put(i, gaussian(cpuMean.get(s, location(46, 34, 46, 36)), cpuVar.get(s, location(46, 45, 46, 47)), location(46, 18, 46, 48)).sample(location(46, 50, 46, 57)), location(46, 12, 46, 57));
            mem.put(i, gaussian(memMean.get(s, location(47, 34, 47, 36)), memVar.get(s, location(47, 45, 47, 47)), location(47, 18, 47, 48)).sample(location(47, 50, 47, 57)), location(47, 12, 47, 57));
            pageFaults.put(i, gaussian(pageFaultsMean.get(s, location(48, 48, 48, 50)), pageFaultsVar.get(s, location(48, 66, 48, 68)), location(48, 25, 48, 69)).sample(location(48, 71, 48, 78)), location(48, 19, 48, 78));

        });

        cpu.observe(cpu_measured, location(52, 9, 52, 29));
        mem.observe(mem_measured, location(53, 9, 53, 29));
        pageFaults.observe(pageFaults_measured, location(54, 16, 54, 43));

        Variable<?>[] $variableNames = {cpu_measured, mem_measured, pageFaults_measured, noStates, v, m, samples, st, initialStateDistribution, cpu, mem, pageFaults, cpuMean, memMean, pageFaultsMean, cpuVar, memVar, pageFaultsVar};
        String[] $constructorArgs = {"cpu_measured", "mem_measured", "pageFaults_measured", "noStates"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMMetrics", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
             + " *\n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "model HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n"
             + "    \n"
             + "    // Construct vectors describing the probability of a move from 1 state to another.\n"
             + "    double[] v = new double[noStates] <~ 0.1;\n"
             + "    double[][] m = dirichlet(v).sample(noStates);\n"
             + "    \n"
             + "    // Determine how many samples the model will need to produce.\n"
             + "    int samples = cpu_measured.length;\n"
             + "    \n"
             + "    // Allocate space for the state.\n"
             + "    int[] st = new int[samples];\n"
             + "\n"
             + "    // Set the initial state by sampling from a categorical with learnt weightings.\n"
             + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
             + "    st[0] = categorical(initialStateDistribution).sampleDistribution();\n"
             + "\n"
             + "    //Determine the remaining states based on the previous state.\n"
             + "    for(int i:[1 .. samples))\n"
             + "        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
             + "        \n"
             + "    //Generate each metric.\n"
             + "    double[] cpu = new double[samples];\n"
             + "    double[] mem = new double[samples];\n"
             + "    double[] pageFaults = new double[samples];\n"
             + "    \n"
             + "    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n"
             + "    double[] memMean = gaussian(94, 1).sample(noStates);\n"
             + "    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n"
             + "    \n"
             + "    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n"
             + "    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n"
             + "    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n"
             + "    \n"
             + "    for(int i:[0 .. samples)) {\n"
             + "        int s = st[i];\n"
             + "        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n"
             + "        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n"
             + "        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n"
             + "    }\n"
             + "\n"
             + "    //Tie the values to the values we have measured.\n"
             + "    cpu.observe(cpu_measured);\n"
             + "    mem.observe(mem_measured);\n"
             + "    pageFaults.observe(pageFaults_measured);\n"
             + "}\n"
             + "";
    }
}