package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var39;
	private double[][] cv$var22$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var30$stateProbabilityGlobal;
	private double[] cv$var40$stateProbabilityGlobal;
	private double[] distribution$sample35;
	private double[][] distribution$sample45;
	private boolean fixedFlag$sample100 = false;
	private boolean fixedFlag$sample110 = false;
	private boolean fixedFlag$sample119 = false;
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample129 = false;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample110 = false;
	private boolean fixedProbFlag$sample119 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample129 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] guard$sample35gaussian118$global;
	private boolean[] guard$sample35gaussian123$global;
	private boolean[] guard$sample35gaussian128$global;
	private boolean[] guard$sample45gaussian118$global;
	private boolean[] guard$sample45gaussian123$global;
	private boolean[] guard$sample45gaussian128$global;
	private double[] initialStateDistribution;
	private int length$cpu_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cpu;
	private double logProbability$cpuMean;
	private double logProbability$cpuVar;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$mem;
	private double logProbability$memMean;
	private double logProbability$memVar;
	private double logProbability$pageFaults;
	private double logProbability$pageFaultsMean;
	private double logProbability$pageFaultsVar;
	private double logProbability$st;
	private double logProbability$var100;
	private double logProbability$var105;
	private double logProbability$var113;
	private double logProbability$var114;
	private double logProbability$var118;
	private double logProbability$var119;
	private double logProbability$var123;
	private double logProbability$var124;
	private double logProbability$var17;
	private double logProbability$var22;
	private double logProbability$var26;
	private double logProbability$var29;
	private double logProbability$var30;
	private double logProbability$var39;
	private double logProbability$var40;
	private double logProbability$var48;
	private double logProbability$var53;
	private double logProbability$var59;
	private double logProbability$var64;
	private double logProbability$var70;
	private double logProbability$var75;
	private double logProbability$var80;
	private double logProbability$var85;
	private double logProbability$var90;
	private double logProbability$var95;
	private double[][] m;
	private double[] mem;
	private double[] memMean;
	private double[] memVar;
	private double[] mem_measured;
	private int noStates;
	private double[] pageFaults;
	private double[] pageFaultsMean;
	private double[] pageFaultsVar;
	private double[] pageFaults_measured;
	private int samples;
	private boolean setFlag$cpu = false;
	private boolean setFlag$cpuMean = false;
	private boolean setFlag$cpuVar = false;
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$mem = false;
	private boolean setFlag$memMean = false;
	private boolean setFlag$memVar = false;
	private boolean setFlag$pageFaults = false;
	private boolean setFlag$pageFaultsMean = false;
	private boolean setFlag$pageFaultsVar = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for cpu.
	@Override
	public final double[] get$cpu() {
		return cpu;
	}

	// Setter for cpu.
	@Override
	public final void set$cpu(double[] cv$value) {
		// Set cpu with flag to mark that it has been set so another array doesn't need to
		// be constructed
		cpu = cv$value;
		setFlag$cpu = true;
	}

	// Getter for cpuMean.
	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	// Setter for cpuMean.
	@Override
	public final void set$cpuMean(double[] cv$value) {
		// Set cpuMean with flag to mark that it has been set so another array doesn't need
		// to be constructed
		cpuMean = cv$value;
		setFlag$cpuMean = true;
	}

	// Getter for cpuVar.
	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	// Setter for cpuVar.
	@Override
	public final void set$cpuVar(double[] cv$value) {
		// Set cpuVar with flag to mark that it has been set so another array doesn't need
		// to be constructed
		cpuVar = cv$value;
		setFlag$cpuVar = true;
	}

	// Getter for cpu_measured.
	@Override
	public final double[] get$cpu_measured() {
		return cpu_measured;
	}

	// Setter for cpu_measured.
	@Override
	public final void set$cpu_measured(double[] cv$value) {
		// Set cpu_measured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		cpu_measured = cv$value;
	}

	// Getter for fixedFlag$sample100.
	@Override
	public final boolean get$fixedFlag$sample100() {
		return fixedFlag$sample100;
	}

	// Setter for fixedFlag$sample100.
	@Override
	public final void set$fixedFlag$sample100(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample100 including if probabilities
		// need to be updated.
		fixedFlag$sample100 = cv$value;
		
		// Should the probability of sample 100 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample100" with its value "cv$value".
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample100" with its value "cv$value".
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	// Getter for fixedFlag$sample110.
	@Override
	public final boolean get$fixedFlag$sample110() {
		return fixedFlag$sample110;
	}

	// Setter for fixedFlag$sample110.
	@Override
	public final void set$fixedFlag$sample110(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample110 including if probabilities
		// need to be updated.
		fixedFlag$sample110 = cv$value;
		
		// Should the probability of sample 110 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample110" with its value "cv$value".
		fixedProbFlag$sample110 = (cv$value && fixedProbFlag$sample110);
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample110" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample119.
	@Override
	public final boolean get$fixedFlag$sample119() {
		return fixedFlag$sample119;
	}

	// Setter for fixedFlag$sample119.
	@Override
	public final void set$fixedFlag$sample119(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample119 including if probabilities
		// need to be updated.
		fixedFlag$sample119 = cv$value;
		
		// Should the probability of sample 119 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample119" with its value "cv$value".
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	// Getter for fixedFlag$sample124.
	@Override
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	// Setter for fixedFlag$sample124.
	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample124 including if probabilities
		// need to be updated.
		fixedFlag$sample124 = cv$value;
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample124" with its value "cv$value".
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	// Getter for fixedFlag$sample129.
	@Override
	public final boolean get$fixedFlag$sample129() {
		return fixedFlag$sample129;
	}

	// Setter for fixedFlag$sample129.
	@Override
	public final void set$fixedFlag$sample129(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample129 including if probabilities
		// need to be updated.
		fixedFlag$sample129 = cv$value;
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample129" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample25.
	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	// Setter for fixedFlag$sample25.
	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample25 including if probabilities
		// need to be updated.
		fixedFlag$sample25 = cv$value;
		
		// Should the probability of sample 25 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample25 = (cv$value && fixedProbFlag$sample25);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
	}

	// Getter for fixedFlag$sample32.
	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	// Setter for fixedFlag$sample32.
	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
		// need to be updated.
		fixedFlag$sample32 = cv$value;
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = cv$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 119 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 119 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample58.
	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	// Setter for fixedFlag$sample58.
	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
		// need to be updated.
		fixedFlag$sample58 = cv$value;
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		
		// Should the probability of sample 119 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	// Getter for fixedFlag$sample69.
	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	// Setter for fixedFlag$sample69.
	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample69 including if probabilities
		// need to be updated.
		fixedFlag$sample69 = cv$value;
		
		// Should the probability of sample 69 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample69" with its value "cv$value".
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		
		// Should the probability of sample 124 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample69" with its value "cv$value".
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	// Getter for fixedFlag$sample80.
	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	// Setter for fixedFlag$sample80.
	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample80 including if probabilities
		// need to be updated.
		fixedFlag$sample80 = cv$value;
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample90.
	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	// Setter for fixedFlag$sample90.
	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample90 including if probabilities
		// need to be updated.
		fixedFlag$sample90 = cv$value;
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		
		// Should the probability of sample 119 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		// Set initialStateDistribution with flag to mark that it has been set so another
		// array doesn't need to be constructed
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
	}

	// Getter for length$cpu_measured.
	@Override
	public final int get$length$cpu_measured() {
		return length$cpu_measured;
	}

	// Setter for length$cpu_measured.
	@Override
	public final void set$length$cpu_measured(int cv$value) {
		length$cpu_measured = cv$value;
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$cpu.
	@Override
	public final double get$logProbability$cpu() {
		return logProbability$cpu;
	}

	// Getter for logProbability$cpuMean.
	@Override
	public final double get$logProbability$cpuMean() {
		return logProbability$cpuMean;
	}

	// Getter for logProbability$cpuVar.
	@Override
	public final double get$logProbability$cpuVar() {
		return logProbability$cpuVar;
	}

	// Getter for logProbability$initialStateDistribution.
	@Override
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for logProbability$mem.
	@Override
	public final double get$logProbability$mem() {
		return logProbability$mem;
	}

	// Getter for logProbability$memMean.
	@Override
	public final double get$logProbability$memMean() {
		return logProbability$memMean;
	}

	// Getter for logProbability$memVar.
	@Override
	public final double get$logProbability$memVar() {
		return logProbability$memVar;
	}

	// Getter for logProbability$pageFaults.
	@Override
	public final double get$logProbability$pageFaults() {
		return logProbability$pageFaults;
	}

	// Getter for logProbability$pageFaultsMean.
	@Override
	public final double get$logProbability$pageFaultsMean() {
		return logProbability$pageFaultsMean;
	}

	// Getter for logProbability$pageFaultsVar.
	@Override
	public final double get$logProbability$pageFaultsVar() {
		return logProbability$pageFaultsVar;
	}

	// Getter for logProbability$st.
	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value) {
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
	}

	// Getter for mem.
	@Override
	public final double[] get$mem() {
		return mem;
	}

	// Setter for mem.
	@Override
	public final void set$mem(double[] cv$value) {
		// Set mem with flag to mark that it has been set so another array doesn't need to
		// be constructed
		mem = cv$value;
		setFlag$mem = true;
	}

	// Getter for memMean.
	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	// Setter for memMean.
	@Override
	public final void set$memMean(double[] cv$value) {
		// Set memMean with flag to mark that it has been set so another array doesn't need
		// to be constructed
		memMean = cv$value;
		setFlag$memMean = true;
	}

	// Getter for memVar.
	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	// Setter for memVar.
	@Override
	public final void set$memVar(double[] cv$value) {
		// Set memVar with flag to mark that it has been set so another array doesn't need
		// to be constructed
		memVar = cv$value;
		setFlag$memVar = true;
	}

	// Getter for mem_measured.
	@Override
	public final double[] get$mem_measured() {
		return mem_measured;
	}

	// Setter for mem_measured.
	@Override
	public final void set$mem_measured(double[] cv$value) {
		// Set mem_measured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		mem_measured = cv$value;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	// Getter for pageFaults.
	@Override
	public final double[] get$pageFaults() {
		return pageFaults;
	}

	// Setter for pageFaults.
	@Override
	public final void set$pageFaults(double[] cv$value) {
		// Set pageFaults with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaults = cv$value;
		setFlag$pageFaults = true;
	}

	// Getter for pageFaultsMean.
	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	// Setter for pageFaultsMean.
	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		// Set pageFaultsMean with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
	}

	// Getter for pageFaultsVar.
	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	// Setter for pageFaultsVar.
	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		// Set pageFaultsVar with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaultsVar = cv$value;
		setFlag$pageFaultsVar = true;
	}

	// Getter for pageFaults_measured.
	@Override
	public final double[] get$pageFaults_measured() {
		return pageFaults_measured;
	}

	// Setter for pageFaults_measured.
	@Override
	public final void set$pageFaults_measured(double[] cv$value) {
		// Set pageFaults_measured with flag to mark that it has been set so another array
		// doesn't need to be constructed
		pageFaults_measured = cv$value;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample119 using probability
	// distributions.
	private final void logProbabilityDistribution$sample119() {
		// Determine if we need to calculate the values for sample task 119 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample119) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 119 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = cpu[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 113.
					// 
					// Enumerating the possible arguments for Gaussian 113.
					if(fixedFlag$sample35) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var52 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var52) && (var52 < noStates))) {
								// Substituted "i$var109" with its value "0".
								double var112 = cpuVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var112))) - (Math.log(var112) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var112 = cpuVar[index$sample35$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample35$3]) / Math.sqrt(var112)))) - (Math.log(var112) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 113.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 113.
					if(fixedFlag$sample45) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var52 = st[i$var109];
							if(((0 <= var52) && (var52 < noStates))) {
								double var112 = cpuVar[st[i$var109]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var109]]) / Math.sqrt(var112))) - (Math.log(var112) * 0.5));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 39.
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var112 = cpuVar[index$sample45$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample45$43]) / Math.sqrt(var112)))) - (Math.log(var112) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var113 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var114 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$cpu = (logProbability$cpu + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var113 = logProbability$var114;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var114);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var114);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var114);
		}
	}

	// Calculate the probability of the samples represented by sample124 using probability
	// distributions.
	private final void logProbabilityDistribution$sample124() {
		// Determine if we need to calculate the values for sample task 124 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample124) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 124 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = mem[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 118.
					// 
					// Enumerating the possible arguments for Gaussian 118.
					if(fixedFlag$sample35) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var63 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var63) && (var63 < noStates))) {
								// Substituted "i$var109" with its value "0".
								double var117 = memVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var117))) - (Math.log(var117) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var117 = memVar[index$sample35$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample35$3]) / Math.sqrt(var117)))) - (Math.log(var117) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 118.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 118.
					if(fixedFlag$sample45) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var63 = st[i$var109];
							if(((0 <= var63) && (var63 < noStates))) {
								double var117 = memVar[st[i$var109]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var109]]) / Math.sqrt(var117))) - (Math.log(var117) * 0.5));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 39.
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var117 = memVar[index$sample45$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample45$43]) / Math.sqrt(var117)))) - (Math.log(var117) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var118 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var119 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$mem = (logProbability$mem + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var118 = logProbability$var119;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var119);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var119);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var119);
		}
	}

	// Calculate the probability of the samples represented by sample129 using probability
	// distributions.
	private final void logProbabilityDistribution$sample129() {
		// Determine if we need to calculate the values for sample task 129 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample129) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 129 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = pageFaults[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 123.
					// 
					// Enumerating the possible arguments for Gaussian 123.
					if(fixedFlag$sample35) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var74 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var74) && (var74 < noStates))) {
								// Substituted "i$var109" with its value "0".
								double var122 = pageFaultsVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var122))) - (Math.log(var122) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
							double var122 = pageFaultsVar[index$sample35$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample35$3]) / Math.sqrt(var122)))) - (Math.log(var122) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 123.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 123.
					if(fixedFlag$sample45) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var74 = st[i$var109];
							if(((0 <= var74) && (var74 < noStates))) {
								double var122 = pageFaultsVar[st[i$var109]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var109]]) / Math.sqrt(var122))) - (Math.log(var122) * 0.5));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 39.
						for(int index$sample45$43 = 0; index$sample45$43 < noStates; index$sample45$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample45Value44 = distribution$sample45[(i$var109 - 1)][index$sample45$43];
							double var122 = pageFaultsVar[index$sample45$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample45Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample45$43]) / Math.sqrt(var122)))) - (Math.log(var122) * 0.5));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var123 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var124 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$pageFaults = (logProbability$pageFaults + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var123 = logProbability$var124;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var124);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var124);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var124);
		}
	}

	// Calculate the probability of the samples represented by sample35 using probability
	// distributions.
	private final void logProbabilityDistribution$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample35) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[0];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var29 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var30 = cv$distributionAccumulator;
				
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				
				// Add probability to model
				// 
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample35" with its value "true".
				fixedProbFlag$sample35 = fixedFlag$sample32;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var29 = logProbability$var30;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample35)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample45 using probability
	// distributions.
	private final void logProbabilityDistribution$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample45) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var34];
					
					// Enumerating the possible arguments for Categorical 39.
					if((1 == i$var34)) {
						// Enumerating the possible arguments for Categorical 39.
						if(fixedFlag$sample35) {
							int var21 = st[0];
							
							// Substituted "i$var34" with its value "1".
							if(((0 <= var21) && (var21 < noStates))) {
								// Substituted "i$var34" with its value "1".
								double[] var38 = m[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 29.
							for(int index$sample35$4 = 0; index$sample35$4 < noStates; index$sample35$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample35Value5 = distribution$sample35[index$sample35$4];
								double[] var38 = m[index$sample35$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample35Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var34 - 1)".
					if((2 <= i$var34)) {
						int var21 = st[(i$var34 - 1)];
						if(((0 <= var21) && (var21 < noStates))) {
							double[] var38 = m[st[(i$var34 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				}
				logProbability$var39 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var40 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample45" with its value "true".
				fixedProbFlag$sample45 = (fixedFlag$sample25 && fixedFlag$sample35);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$var40;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample45)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample100 using sampled
	// values.
	private final void logProbabilityValue$sample100() {
		// Determine if we need to calculate the values for sample task 100 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample100) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var94 = 0; var94 < noStates; var94 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var94], 5.0, 0.5));
			logProbability$var90 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var95 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$memVar = (logProbability$memVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample100)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample100 = fixedFlag$sample100;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var90 = logProbability$var95;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memVar = (logProbability$memVar + logProbability$var95);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var95);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample100)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var95);
		}
	}

	// Calculate the probability of the samples represented by sample110 using sampled
	// values.
	private final void logProbabilityValue$sample110() {
		// Determine if we need to calculate the values for sample task 110 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample110) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var104 = 0; var104 < noStates; var104 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var104], 5.0, 0.5));
			logProbability$var100 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var105 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample110)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample110 = fixedFlag$sample110;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var100 = logProbability$var105;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var105);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var105);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample110)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var105);
		}
	}

	// Calculate the probability of the samples represented by sample119 using sampled
	// values.
	private final void logProbabilityValue$sample119() {
		// Determine if we need to calculate the values for sample task 119 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample119) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double var112 = cpuVar[st[i$var109]];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(var112)))) - (Math.log(var112) * 0.5));
			}
			logProbability$var113 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var114 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$cpu = (logProbability$cpu + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample119 = ((((fixedFlag$sample119 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample58) && fixedFlag$sample90);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var113 = logProbability$var114;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var114);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var114);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var114);
		}
	}

	// Calculate the probability of the samples represented by sample124 using sampled
	// values.
	private final void logProbabilityValue$sample124() {
		// Determine if we need to calculate the values for sample task 124 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample124) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double var117 = memVar[st[i$var109]];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(var117)))) - (Math.log(var117) * 0.5));
			}
			logProbability$var118 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var119 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$mem = (logProbability$mem + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample124 = ((((fixedFlag$sample124 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample69) && fixedFlag$sample100);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var118 = logProbability$var119;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var119);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var119);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var119);
		}
	}

	// Calculate the probability of the samples represented by sample129 using sampled
	// values.
	private final void logProbabilityValue$sample129() {
		// Determine if we need to calculate the values for sample task 129 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample129) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				double var122 = pageFaultsVar[st[i$var109]];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(var122)))) - (Math.log(var122) * 0.5));
			}
			logProbability$var123 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var124 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$pageFaults = (logProbability$pageFaults + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample129 = ((((fixedFlag$sample129 && fixedFlag$sample35) && fixedFlag$sample45) && fixedFlag$sample80) && fixedFlag$sample110);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var123 = logProbability$var124;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var124);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var124);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var124);
		}
	}

	// Calculate the probability of the samples represented by sample25 using sampled
	// values.
	private final void logProbabilityValue$sample25() {
		// Determine if we need to calculate the values for sample task 25 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample25) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noStates; var21 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var21], v));
			logProbability$var17 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var22 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample25)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample25 = fixedFlag$sample25;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var17 = logProbability$var22;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var22);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var22);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample25)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var26 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var26 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var29 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var30 = cv$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample32);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var29 = logProbability$var30;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var34];
				double[] var38 = m[st[(i$var34 - 1)]];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var38.length))?Math.log(var38[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var39 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var40 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample25) && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$var40;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var52 = 0; var52 < noStates; var52 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var52] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			logProbability$var48 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var53 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$cpuMean = (logProbability$cpuMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample58 = fixedFlag$sample58;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var48 = logProbability$var53;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var53);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var53);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
		}
	}

	// Calculate the probability of the samples represented by sample69 using sampled
	// values.
	private final void logProbabilityValue$sample69() {
		// Determine if we need to calculate the values for sample task 69 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample69) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var63 = 0; var63 < noStates; var63 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var63] - 94.0)));
			logProbability$var59 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var64 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$memMean = (logProbability$memMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample69 = fixedFlag$sample69;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var59 = logProbability$var64;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memMean = (logProbability$memMean + logProbability$var64);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var64);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample69)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var64);
		}
	}

	// Calculate the probability of the samples represented by sample80 using sampled
	// values.
	private final void logProbabilityValue$sample80() {
		// Determine if we need to calculate the values for sample task 80 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample80) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var74] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			logProbability$var70 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var75 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample80 = fixedFlag$sample80;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var70 = logProbability$var75;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var75);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var75);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	// Calculate the probability of the samples represented by sample90 using sampled
	// values.
	private final void logProbabilityValue$sample90() {
		// Determine if we need to calculate the values for sample task 90 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample90) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < noStates; var84 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var84], 5.0, 0.5));
			logProbability$var80 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var85 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$cpuVar = (logProbability$cpuVar + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample90 = fixedFlag$sample90;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var80 = logProbability$var85;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var85);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var85);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var85);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 100 drawn from InverseGamma 90. Inference was performed using Metropolis-Hastings.
	private final void sample100(int var94, int threadID$cv$var94, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = memVar[var94];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var88" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var94 == st[0])) {
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 100.
						int var63 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var63) && (var63 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$3$var117" with its value "var117".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var94".
					double cv$probabilitySample35Value4 = distribution$sample35[var94];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$9$var117" with its value "var117".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$8$var116's comment
					// Variable declaration of cv$temp$8$var116 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 100.
					// 
					// Substituted "index$sample35$3" with its value "var94".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var94]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var94 == st[i$var109])) {
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var63 = st[i$var109];
						if(((0 <= var63) && (var63 < noStates))) {
							// Substituted "cv$temp$21$var117" with its value "var117".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$20$var116's comment
							// Variable declaration of cv$temp$20$var116 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var94".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var94];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$27$var117" with its value "var117".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$26$var116's comment
					// Variable declaration of cv$temp$26$var116 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 100.
					// 
					// Substituted "index$sample45$12" with its value "var94".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[var94]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		memVar[var94] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var88" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var94 == st[0])) {
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 100.
					int var63 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var63) && (var63 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var117" with its value "var117".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var94".
				double cv$probabilitySample35Value4 = distribution$sample35[var94];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 124 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$9$var117" with its value "var117".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$8$var116's comment
				// Variable declaration of cv$temp$8$var116 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 100.
				// 
				// Substituted "index$sample35$3" with its value "var94".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var94]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var94 == st[i$var109])) {
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var63 = st[i$var109];
					if(((0 <= var63) && (var63 < noStates))) {
						// Substituted "cv$temp$21$var117" with its value "var117".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$20$var116's comment
						// Variable declaration of cv$temp$20$var116 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var94".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var94];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 124 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$27$var117" with its value "var117".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$26$var116's comment
				// Variable declaration of cv$temp$26$var116 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 100.
				// 
				// Substituted "index$sample45$12" with its value "var94".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - memMean[var94]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			memVar[var94] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 110 drawn from InverseGamma 100. Inference was performed using Metropolis-Hastings.
	private final void sample110(int var104, int threadID$cv$var104, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = pageFaultsVar[var104];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var98" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var104 == st[0])) {
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 110.
						int var74 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var74) && (var74 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$3$var122" with its value "var122".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var104".
					double cv$probabilitySample35Value4 = distribution$sample35[var104];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$9$var122" with its value "var122".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$8$var121's comment
					// Variable declaration of cv$temp$8$var121 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 110.
					// 
					// Substituted "index$sample35$3" with its value "var104".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var104]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var104 == st[i$var109])) {
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var74 = st[i$var109];
						if(((0 <= var74) && (var74 < noStates))) {
							// Substituted "cv$temp$21$var122" with its value "var122".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$20$var121's comment
							// Variable declaration of cv$temp$20$var121 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var104".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var104];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$27$var122" with its value "var122".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$26$var121's comment
					// Variable declaration of cv$temp$26$var121 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 110.
					// 
					// Substituted "index$sample45$12" with its value "var104".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[var104]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		pageFaultsVar[var104] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var98" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var104 == st[0])) {
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 110.
					int var74 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var74) && (var74 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var122" with its value "var122".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var104".
				double cv$probabilitySample35Value4 = distribution$sample35[var104];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 129 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$9$var122" with its value "var122".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$8$var121's comment
				// Variable declaration of cv$temp$8$var121 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 110.
				// 
				// Substituted "index$sample35$3" with its value "var104".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var104]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var104 == st[i$var109])) {
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var74 = st[i$var109];
					if(((0 <= var74) && (var74 < noStates))) {
						// Substituted "cv$temp$21$var122" with its value "var122".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$20$var121's comment
						// Variable declaration of cv$temp$20$var121 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var104".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var104];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 129 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$27$var122" with its value "var122".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$26$var121's comment
				// Variable declaration of cv$temp$26$var121 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 110.
				// 
				// Substituted "index$sample45$12" with its value "var104".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - pageFaultsMean[var104]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			pageFaultsVar[var104] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 25 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample25(int var21, int threadID$cv$var21, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var22$countGlobal[threadID$cv$var21];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample45) {
			// Processing random variable 39.
			// 
			// Looking for a path between Sample 25 and consumer Categorical 39.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample35) {
					if((var21 == st[0]))
						// Processing sample task 45 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 45 of random
						// variable var39
						// 
						// Substituted "i$var34" with its value "1".
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
				} else
					// Processing sample task 45 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 45 of random
					// variable var39
					// 
					// cv$probabilitySample35Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var21".
					cv$countLocal[st[1]] = (cv$countLocal[st[1]] + distribution$sample35[var21]);
			}
			for(int i$var34 = 2; i$var34 < samples; i$var34 += 1) {
				if((var21 == st[(i$var34 - 1)]))
					// Processing sample task 45 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 45 of random
					// variable var39
					cv$countLocal[st[i$var34]] = (cv$countLocal[st[i$var34]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 39.
			// 
			// Looking for a path between Sample 25 and consumer Categorical 39.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample35) {
					if((var21 == st[0])) {
						// Processing sample task 45 of consumer random variable null.
						// 
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample45[0][cv$loopIndex]);
					}
				} else {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample35Value33's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample35$32" with its value "var21".
					double cv$distributionProbability = distribution$sample35[var21];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// Substituted "i$var34" with its value "1".
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				int index$i$40 = (i$var34 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$40)) {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample45Value42's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample45$41" with its value "var21".
					double cv$distributionProbability = distribution$sample45[(index$i$40 - 1)][var21];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[(i$var34 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var21]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 26. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample32() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample35)
			// Processing random variable 29.
			// 
			// Processing sample task 35 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 35 of random
			// variable var29
			// 
			// A local reference to the scratch space.
			cv$var27$countGlobal[st[0]] = (cv$var27$countGlobal[st[0]] + 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing sample task 35 of consumer random variable null.
			// 
			// Merge the distribution probabilities into the count
			// 
			// Get the length of the array
			for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
				// A local reference to the scratch space.
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample35[cv$loopIndex]);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Categorical 29. Inference was performed using variable
	// marginalization.
	private final void sample35() {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample45 && (1 < samples))) {
				// Looking for a path between Sample 35 and consumer Categorical 39.
				// Variable declaration of cv$temp$1$var38 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var38 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 45 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 45 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var34" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var38.length))?Math.log(cv$temp$1$var38[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				// Processing random variable 113.
				// 
				// Looking for a path between Sample 35 and consumer Gaussian 113.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample35gaussian118$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample35gaussian118$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian118$global[0] = true;
					
					// Variable declaration of cv$temp$3$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double cv$temp$3$var112 = cpuVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$2$var111's comment
					// Variable declaration of cv$temp$2$var111 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$3$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var112) * 0.5));
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample35gaussian118$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian118$global[0] = true;
					
					// Variable declaration of cv$temp$11$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					double cv$temp$11$var112 = cpuVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$10$var111's comment
					// Variable declaration of cv$temp$10$var111 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$11$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$11$var112) * 0.5));
				}
				
				// Processing random variable 118.
				// 
				// Looking for a path between Sample 35 and consumer Gaussian 118.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample35gaussian123$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample35gaussian123$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian123$global[0] = true;
					
					// Variable declaration of cv$temp$19$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double cv$temp$19$var117 = memVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$18$var116's comment
					// Variable declaration of cv$temp$18$var116 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$19$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$19$var117) * 0.5));
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample35gaussian123$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian123$global[0] = true;
					
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					double cv$temp$27$var117 = memVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$26$var116's comment
					// Variable declaration of cv$temp$26$var116 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$27$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$27$var117) * 0.5));
				}
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample35gaussian128$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample35gaussian128$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian128$global[0] = true;
					
					// Variable declaration of cv$temp$35$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double cv$temp$35$var122 = pageFaultsVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$34$var121's comment
					// Variable declaration of cv$temp$34$var121 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$35$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$35$var122) * 0.5));
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample35gaussian128$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample35gaussian128$global[0] = true;
					
					// Variable declaration of cv$temp$43$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					double cv$temp$43$var122 = pageFaultsVar[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$42$var121's comment
					// Variable declaration of cv$temp$42$var121 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 35.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$43$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$43$var122) * 0.5));
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample45 && (1 < samples))) {
				// Looking for a path between Sample 35 and consumer Categorical 39.
				// Processing sample task 45 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var39[cv$i] = 0.0;
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				// 
				// cv$temp$50$var38's comment
				// Variable declaration of cv$temp$50$var38 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var39, 1.0, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var34" with its value "1".
				double[] cv$sampleDistribution = distribution$sample45[0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					double cv$normalisedDistValue = cv$distributionAccumulator$var39[cv$i];
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				// 
				// Record the reached distribution.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var30$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var30$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var30$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var30$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var30$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var30$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var30$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample35[cv$indexName] = (1.0 / cv$var30$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var30$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample35[cv$indexName] = Math.exp((cv$var30$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Categorical 39. Inference was performed using variable
	// marginalization.
	private final void sample45(int i$var34) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 39 creating
			// sample task 45.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 39.
			if((1 == i$var34)) {
				// Enumerating the possible arguments for Categorical 39.
				if(fixedFlag$sample35) {
					int var21 = st[0];
					
					// Substituted "i$var34" with its value "1".
					if(((0 <= var21) && (var21 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var38 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var34" with its value "1".
						double[] cv$temp$0$var38 = m[st[0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var38.length)?Math.log(cv$temp$0$var38[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Processing random variable 113.
						// 
						// Looking for a path between Sample 45 and consumer Gaussian 113.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian118$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian118$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian118$global[1] = true;
							
							// Variable declaration of cv$temp$7$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$7$var112 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 119 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$6$var111's comment
							// Variable declaration of cv$temp$6$var111 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$7$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$7$var112) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian118$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian118$global[1] = true;
							
							// Variable declaration of cv$temp$39$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$39$var112 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 119 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$38$var111's comment
							// Variable declaration of cv$temp$38$var111 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$39$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$39$var112) * 0.5));
						}
						
						// Processing random variable 118.
						// 
						// Looking for a path between Sample 45 and consumer Gaussian 118.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian123$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian123$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian123$global[1] = true;
							
							// Variable declaration of cv$temp$71$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$71$var117 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 124 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$70$var116's comment
							// Variable declaration of cv$temp$70$var116 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$71$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$71$var117) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian123$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian123$global[1] = true;
							
							// Variable declaration of cv$temp$103$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$103$var117 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 124 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$102$var116's comment
							// Variable declaration of cv$temp$102$var116 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$103$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$103$var117) * 0.5));
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian128$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian128$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian128$global[1] = true;
							
							// Variable declaration of cv$temp$135$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$135$var122 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 129 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$134$var121's comment
							// Variable declaration of cv$temp$134$var121 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$135$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$135$var122) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian128$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian128$global[1] = true;
							
							// Variable declaration of cv$temp$167$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$167$var122 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 129 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$166$var121's comment
							// Variable declaration of cv$temp$166$var121 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$167$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$167$var122) * 0.5));
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 29.
					for(int index$sample35$3 = 0; index$sample35$3 < noStates; index$sample35$3 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample35Value4 = distribution$sample35[index$sample35$3];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value4);
						
						// Variable declaration of cv$temp$1$var38 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var38 = m[index$sample35$3];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample35Value4) + ((cv$valuePos < cv$temp$1$var38.length)?Math.log(cv$temp$1$var38[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 113.
						// 
						// Looking for a path between Sample 45 and consumer Gaussian 113.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian118$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian118$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian118$global[1] = true;
							
							// Variable declaration of cv$temp$15$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$15$var112 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 119 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$14$var111's comment
							// Variable declaration of cv$temp$14$var111 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$15$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$15$var112) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian118$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian118$global[1] = true;
							
							// Variable declaration of cv$temp$47$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$47$var112 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 119 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$46$var111's comment
							// Variable declaration of cv$temp$46$var111 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$47$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$47$var112) * 0.5));
						}
						
						// Processing random variable 118.
						// 
						// Looking for a path between Sample 45 and consumer Gaussian 118.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian123$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian123$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian123$global[1] = true;
							
							// Variable declaration of cv$temp$79$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$79$var117 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 124 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$78$var116's comment
							// Variable declaration of cv$temp$78$var116 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$79$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$79$var117) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian123$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian123$global[1] = true;
							
							// Variable declaration of cv$temp$111$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$111$var117 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 124 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$110$var116's comment
							// Variable declaration of cv$temp$110$var116 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$111$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$111$var117) * 0.5));
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample45gaussian128$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample45gaussian128$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian128$global[1] = true;
							
							// Variable declaration of cv$temp$143$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$143$var122 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 129 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$142$var121's comment
							// Variable declaration of cv$temp$142$var121 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$143$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$143$var122) * 0.5));
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample45gaussian128$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample45gaussian128$global[1] = true;
							
							// Variable declaration of cv$temp$175$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							double cv$temp$175$var122 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 129 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$174$var121's comment
							// Variable declaration of cv$temp$174$var121 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 45.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$175$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$175$var122) * 0.5));
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			int index$i$10 = (i$var34 - 1);
			
			// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var34 - 1)".
			if(((1 <= index$i$10) && !(index$i$10 == i$var34))) {
				// Enumerating the possible outputs of Categorical 39.
				for(int index$sample45$11 = 0; index$sample45$11 < noStates; index$sample45$11 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample45Value12 = distribution$sample45[(index$i$10 - 1)][index$sample45$11];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample45Value12);
					
					// Variable declaration of cv$temp$3$var38 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var38 = m[index$sample45$11];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample45Value12) + ((cv$valuePos < cv$temp$3$var38.length)?Math.log(cv$temp$3$var38[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 113.
					// 
					// Looking for a path between Sample 45 and consumer Gaussian 113.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45gaussian118$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample45gaussian118$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian118$global[i$var34] = true;
						
						// Variable declaration of cv$temp$31$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$31$var112 = cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$30$var111's comment
						// Variable declaration of cv$temp$30$var111 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[i$var34] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$31$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$31$var112) * 0.5));
					}
					if(!guard$sample45gaussian118$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian118$global[i$var34] = true;
						
						// Variable declaration of cv$temp$63$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						double cv$temp$63$var112 = cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$62$var111's comment
						// Variable declaration of cv$temp$62$var111 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((cpu[i$var34] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$63$var112))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$63$var112) * 0.5));
					}
					
					// Processing random variable 118.
					// 
					// Looking for a path between Sample 45 and consumer Gaussian 118.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45gaussian123$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample45gaussian123$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian123$global[i$var34] = true;
						
						// Variable declaration of cv$temp$95$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$95$var117 = memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$94$var116's comment
						// Variable declaration of cv$temp$94$var116 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[i$var34] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$95$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$95$var117) * 0.5));
					}
					if(!guard$sample45gaussian123$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian123$global[i$var34] = true;
						
						// Variable declaration of cv$temp$127$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						double cv$temp$127$var117 = memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$126$var116's comment
						// Variable declaration of cv$temp$126$var116 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((mem[i$var34] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$127$var117))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$127$var117) * 0.5));
					}
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45gaussian128$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample45gaussian128$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian128$global[i$var34] = true;
						
						// Variable declaration of cv$temp$159$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$159$var122 = pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$158$var121's comment
						// Variable declaration of cv$temp$158$var121 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[i$var34] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$159$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$159$var122) * 0.5));
					}
					if(!guard$sample45gaussian128$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45gaussian128$global[i$var34] = true;
						
						// Variable declaration of cv$temp$191$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						double cv$temp$191$var122 = pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$190$var121's comment
						// Variable declaration of cv$temp$190$var121 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 45.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((pageFaults[i$var34] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$191$var122))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$191$var122) * 0.5));
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$i$603_2 = (i$var34 + 1);
			if((index$i$603_2 < samples)) {
				// Processing sample task 45 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var39[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Enumerating the possible arguments for Categorical 39.
				if((1 == i$var34)) {
					// Enumerating the possible arguments for Categorical 39.
					if(fixedFlag$sample35) {
						int index$var21$612_1 = st[0];
						
						// Substituted "i$var34" with its value "1".
						if(((0 <= index$var21$612_1) && (index$var21$612_1 < noStates)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample35$608 = 0; index$sample35$608 < noStates; index$sample35$608 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample35Value609's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample35[index$sample35$608]);
					}
				}
				int index$i$615 = (i$var34 - 1);
				
				// index$i$605's comment
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				// 
				// Substituted "index$i$603_2" with its value "(i$var34 + 1)".
				if((((1 <= index$i$615) && !(index$i$615 == i$var34)) && !(index$i$615 == index$i$603_2))) {
					// Enumerating the possible outputs of Categorical 39.
					for(int index$sample45$616 = 0; index$sample45$616 < noStates; index$sample45$616 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample45Value617's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample45[(index$i$615 - 1)][index$sample45$616]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// cv$temp$196$var38's comment
				// Variable declaration of cv$temp$196$var38 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 39.
				// 
				// Looking for a path between Sample 45 and consumer Categorical 39.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var39, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = distribution$sample45[(index$i$603_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var39[cv$i] / scopeVariable$reachedSourceProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				// 
				// Record the reached distribution.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Zero an accumulator to track the probabilities reached.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var40$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample45[(i$var34 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var40$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var40$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var40$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var40$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var40$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var40$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = (1.0 / cv$var40$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var40$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var40$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 58 drawn from Gaussian 48. Inference was performed using Metropolis-Hastings.
	private final void sample58(int var52, int threadID$cv$var52, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = cpuMean[var52];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var46" with its value "8.6".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var52 == st[0])) {
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						int var84 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var84) && (var84 < noStates))) {
							// Variable declaration of cv$temp$3$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							double cv$temp$3$var112 = cpuVar[st[0]];
							
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var111" with its value "var111".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var112))) - (Math.log(cv$temp$3$var112) * 0.5));
							
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var52".
					double cv$probabilitySample35Value4 = distribution$sample35[var52];
					
					// Variable declaration of cv$temp$9$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample35$3" with its value "var52".
					double cv$temp$9$var112 = cpuVar[var52];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$8$var111" with its value "var111".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var112)))) - (Math.log(cv$temp$9$var112) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$9$var112's comment
						// Variable declaration of cv$temp$9$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample35$3" with its value "var52".
						// 
						// cv$temp$9$var112's comment
						// Variable declaration of cv$temp$9$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample35$3" with its value "var52".
						// 
						// cv$temp$9$var112's comment
						// Variable declaration of cv$temp$9$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample35$3" with its value "var52".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var52 == st[i$var109])) {
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var84 = st[i$var109];
						if(((0 <= var84) && (var84 < noStates))) {
							// Variable declaration of cv$temp$21$var112 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var112 = cpuVar[st[i$var109]];
							
							// Substituted "cv$temp$20$var111" with its value "var111".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var112))) - (Math.log(cv$temp$21$var112) * 0.5));
							
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var52".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var52];
					
					// Variable declaration of cv$temp$27$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample45$12" with its value "var52".
					double cv$temp$27$var112 = cpuVar[var52];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var111" with its value "var111".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var112)))) - (Math.log(cv$temp$27$var112) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var112's comment
						// Variable declaration of cv$temp$27$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample45$12" with its value "var52".
						// 
						// cv$temp$27$var112's comment
						// Variable declaration of cv$temp$27$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample45$12" with its value "var52".
						// 
						// cv$temp$27$var112's comment
						// Variable declaration of cv$temp$27$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 58.
						// 
						// Substituted "index$sample45$12" with its value "var52".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		cpuMean[var52] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var46" with its value "8.6".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var52 == st[0])) {
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					int var84 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var84) && (var84 < noStates))) {
						// Variable declaration of cv$temp$3$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						double cv$temp$3$var112 = cpuVar[st[0]];
						
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var111" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var112))) - (Math.log(cv$temp$3$var112) * 0.5));
						
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var52".
				double cv$probabilitySample35Value4 = distribution$sample35[var52];
				
				// Variable declaration of cv$temp$9$var112 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 58.
				// 
				// Substituted "index$sample35$3" with its value "var52".
				double cv$temp$9$var112 = cpuVar[var52];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 119 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$8$var111" with its value "var111".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var112)))) - (Math.log(cv$temp$9$var112) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$9$var112's comment
					// Variable declaration of cv$temp$9$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample35$3" with its value "var52".
					// 
					// cv$temp$9$var112's comment
					// Variable declaration of cv$temp$9$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample35$3" with its value "var52".
					// 
					// cv$temp$9$var112's comment
					// Variable declaration of cv$temp$9$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample35$3" with its value "var52".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var52 == st[i$var109])) {
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var84 = st[i$var109];
					if(((0 <= var84) && (var84 < noStates))) {
						// Variable declaration of cv$temp$21$var112 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var112 = cpuVar[st[i$var109]];
						
						// Substituted "cv$temp$20$var111" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var112))) - (Math.log(cv$temp$21$var112) * 0.5));
						
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var52".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var52];
				
				// Variable declaration of cv$temp$27$var112 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 58.
				// 
				// Substituted "index$sample45$12" with its value "var52".
				double cv$temp$27$var112 = cpuVar[var52];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 119 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var111" with its value "var111".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var112)))) - (Math.log(cv$temp$27$var112) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var112's comment
					// Variable declaration of cv$temp$27$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample45$12" with its value "var52".
					// 
					// cv$temp$27$var112's comment
					// Variable declaration of cv$temp$27$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample45$12" with its value "var52".
					// 
					// cv$temp$27$var112's comment
					// Variable declaration of cv$temp$27$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 58.
					// 
					// Substituted "index$sample45$12" with its value "var52".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			cpuMean[var52] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 69 drawn from Gaussian 59. Inference was performed using Metropolis-Hastings.
	private final void sample69(int var63, int threadID$cv$var63, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = memMean[var63];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var58" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var63 == st[0])) {
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						int var94 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var94) && (var94 < noStates))) {
							// Variable declaration of cv$temp$3$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							double cv$temp$3$var117 = memVar[st[0]];
							
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var116" with its value "var116".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var117))) - (Math.log(cv$temp$3$var117) * 0.5));
							
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var63".
					double cv$probabilitySample35Value4 = distribution$sample35[var63];
					
					// Variable declaration of cv$temp$9$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample35$3" with its value "var63".
					double cv$temp$9$var117 = memVar[var63];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$8$var116" with its value "var116".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var117)))) - (Math.log(cv$temp$9$var117) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$9$var117's comment
						// Variable declaration of cv$temp$9$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample35$3" with its value "var63".
						// 
						// cv$temp$9$var117's comment
						// Variable declaration of cv$temp$9$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample35$3" with its value "var63".
						// 
						// cv$temp$9$var117's comment
						// Variable declaration of cv$temp$9$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample35$3" with its value "var63".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var63 == st[i$var109])) {
						// Processing sample task 124 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var94 = st[i$var109];
						if(((0 <= var94) && (var94 < noStates))) {
							// Variable declaration of cv$temp$21$var117 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var117 = memVar[st[i$var109]];
							
							// Substituted "cv$temp$20$var116" with its value "var116".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var117))) - (Math.log(cv$temp$21$var117) * 0.5));
							
							// Recorded the probability of reaching sample task 124 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var63".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var63];
					
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample45$12" with its value "var63".
					double cv$temp$27$var117 = memVar[var63];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var116" with its value "var116".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var117)))) - (Math.log(cv$temp$27$var117) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 124 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var117's comment
						// Variable declaration of cv$temp$27$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample45$12" with its value "var63".
						// 
						// cv$temp$27$var117's comment
						// Variable declaration of cv$temp$27$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample45$12" with its value "var63".
						// 
						// cv$temp$27$var117's comment
						// Variable declaration of cv$temp$27$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 69.
						// 
						// Substituted "index$sample45$12" with its value "var63".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		memMean[var63] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var58" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var63 == st[0])) {
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					int var94 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var94) && (var94 < noStates))) {
						// Variable declaration of cv$temp$3$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						double cv$temp$3$var117 = memVar[st[0]];
						
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var116" with its value "var116".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var117))) - (Math.log(cv$temp$3$var117) * 0.5));
						
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var63".
				double cv$probabilitySample35Value4 = distribution$sample35[var63];
				
				// Variable declaration of cv$temp$9$var117 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 69.
				// 
				// Substituted "index$sample35$3" with its value "var63".
				double cv$temp$9$var117 = memVar[var63];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 124 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$8$var116" with its value "var116".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var117)))) - (Math.log(cv$temp$9$var117) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$9$var117's comment
					// Variable declaration of cv$temp$9$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample35$3" with its value "var63".
					// 
					// cv$temp$9$var117's comment
					// Variable declaration of cv$temp$9$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample35$3" with its value "var63".
					// 
					// cv$temp$9$var117's comment
					// Variable declaration of cv$temp$9$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample35$3" with its value "var63".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var63 == st[i$var109])) {
					// Processing sample task 124 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var94 = st[i$var109];
					if(((0 <= var94) && (var94 < noStates))) {
						// Variable declaration of cv$temp$21$var117 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var117 = memVar[st[i$var109]];
						
						// Substituted "cv$temp$20$var116" with its value "var116".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var117))) - (Math.log(cv$temp$21$var117) * 0.5));
						
						// Recorded the probability of reaching sample task 124 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var63".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var63];
				
				// Variable declaration of cv$temp$27$var117 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 69.
				// 
				// Substituted "index$sample45$12" with its value "var63".
				double cv$temp$27$var117 = memVar[var63];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 124 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var116" with its value "var116".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var117)))) - (Math.log(cv$temp$27$var117) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 124 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var117's comment
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample45$12" with its value "var63".
					// 
					// cv$temp$27$var117's comment
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample45$12" with its value "var63".
					// 
					// cv$temp$27$var117's comment
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 69.
					// 
					// Substituted "index$sample45$12" with its value "var63".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			memMean[var63] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 80 drawn from Gaussian 70. Inference was performed using Metropolis-Hastings.
	private final void sample80(int var74, int threadID$cv$var74, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = pageFaultsMean[var74];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var69" with its value "335550.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var74 == st[0])) {
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						int var104 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var104) && (var104 < noStates))) {
							// Variable declaration of cv$temp$3$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							double cv$temp$3$var122 = pageFaultsVar[st[0]];
							
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var121" with its value "var121".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var122))) - (Math.log(cv$temp$3$var122) * 0.5));
							
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var74".
					double cv$probabilitySample35Value4 = distribution$sample35[var74];
					
					// Variable declaration of cv$temp$9$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample35$3" with its value "var74".
					double cv$temp$9$var122 = pageFaultsVar[var74];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$8$var121" with its value "var121".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var122)))) - (Math.log(cv$temp$9$var122) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$9$var122's comment
						// Variable declaration of cv$temp$9$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample35$3" with its value "var74".
						// 
						// cv$temp$9$var122's comment
						// Variable declaration of cv$temp$9$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample35$3" with its value "var74".
						// 
						// cv$temp$9$var122's comment
						// Variable declaration of cv$temp$9$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample35$3" with its value "var74".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var74 == st[i$var109])) {
						// Processing sample task 129 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var104 = st[i$var109];
						if(((0 <= var104) && (var104 < noStates))) {
							// Variable declaration of cv$temp$21$var122 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var122 = pageFaultsVar[st[i$var109]];
							
							// Substituted "cv$temp$20$var121" with its value "var121".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$21$var122))) - (Math.log(cv$temp$21$var122) * 0.5));
							
							// Recorded the probability of reaching sample task 129 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var74".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var74];
					
					// Variable declaration of cv$temp$27$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample45$12" with its value "var74".
					double cv$temp$27$var122 = pageFaultsVar[var74];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var121" with its value "var121".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$originalValue) / Math.sqrt(cv$temp$27$var122)))) - (Math.log(cv$temp$27$var122) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 129 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var122's comment
						// Variable declaration of cv$temp$27$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample45$12" with its value "var74".
						// 
						// cv$temp$27$var122's comment
						// Variable declaration of cv$temp$27$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample45$12" with its value "var74".
						// 
						// cv$temp$27$var122's comment
						// Variable declaration of cv$temp$27$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 80.
						// 
						// Substituted "index$sample45$12" with its value "var74".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		pageFaultsMean[var74] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var69" with its value "335550.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var74 == st[0])) {
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					int var104 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var104) && (var104 < noStates))) {
						// Variable declaration of cv$temp$3$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						double cv$temp$3$var122 = pageFaultsVar[st[0]];
						
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var121" with its value "var121".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var122))) - (Math.log(cv$temp$3$var122) * 0.5));
						
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var74".
				double cv$probabilitySample35Value4 = distribution$sample35[var74];
				
				// Variable declaration of cv$temp$9$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 80.
				// 
				// Substituted "index$sample35$3" with its value "var74".
				double cv$temp$9$var122 = pageFaultsVar[var74];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 129 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$8$var121" with its value "var121".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var122)))) - (Math.log(cv$temp$9$var122) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$9$var122's comment
					// Variable declaration of cv$temp$9$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample35$3" with its value "var74".
					// 
					// cv$temp$9$var122's comment
					// Variable declaration of cv$temp$9$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample35$3" with its value "var74".
					// 
					// cv$temp$9$var122's comment
					// Variable declaration of cv$temp$9$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample35$3" with its value "var74".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var74 == st[i$var109])) {
					// Processing sample task 129 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var104 = st[i$var109];
					if(((0 <= var104) && (var104 < noStates))) {
						// Variable declaration of cv$temp$21$var122 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var122 = pageFaultsVar[st[i$var109]];
						
						// Substituted "cv$temp$20$var121" with its value "var121".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$21$var122))) - (Math.log(cv$temp$21$var122) * 0.5));
						
						// Recorded the probability of reaching sample task 129 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var74".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var74];
				
				// Variable declaration of cv$temp$27$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 80.
				// 
				// Substituted "index$sample45$12" with its value "var74".
				double cv$temp$27$var122 = pageFaultsVar[var74];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 129 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var121" with its value "var121".
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var109] - cv$proposedValue) / Math.sqrt(cv$temp$27$var122)))) - (Math.log(cv$temp$27$var122) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 129 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var122's comment
					// Variable declaration of cv$temp$27$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample45$12" with its value "var74".
					// 
					// cv$temp$27$var122's comment
					// Variable declaration of cv$temp$27$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample45$12" with its value "var74".
					// 
					// cv$temp$27$var122's comment
					// Variable declaration of cv$temp$27$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 80.
					// 
					// Substituted "index$sample45$12" with its value "var74".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			pageFaultsMean[var74] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 90 drawn from InverseGamma 80. Inference was performed using Metropolis-Hastings.
	private final void sample90(int var84, int threadID$cv$var84, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = cpuVar[var84];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var78" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample35) {
					if((var84 == st[0])) {
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 90.
						int var52 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var52) && (var52 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$3$var112" with its value "var112".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample35$3" with its value "var84".
					double cv$probabilitySample35Value4 = distribution$sample35[var84];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// Substituted "cv$temp$9$var112" with its value "var112".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$8$var111's comment
					// Variable declaration of cv$temp$8$var111 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 90.
					// 
					// Substituted "index$sample35$3" with its value "var84".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var84]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
				if(fixedFlag$sample45) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var84 == st[i$var109])) {
						// Processing sample task 119 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var52 = st[i$var109];
						if(((0 <= var52) && (var52 < noStates))) {
							// Substituted "cv$temp$21$var112" with its value "var112".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$20$var111's comment
							// Variable declaration of cv$temp$20$var111 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 119 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var34" with its value "i$var109".
					// 
					// Substituted "index$sample45$12" with its value "var84".
					double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var84];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$27$var112" with its value "var112".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$26$var111's comment
					// Variable declaration of cv$temp$26$var111 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 90.
					// 
					// Substituted "index$sample45$12" with its value "var84".
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[var84]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 119 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// The original value of the sample
						// 
						// The original value of the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		cpuVar[var84] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var78" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample35) {
				if((var84 == st[0])) {
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 90.
					int var52 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var52) && (var52 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var112" with its value "var112".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample35$3" with its value "var84".
				double cv$probabilitySample35Value4 = distribution$sample35[var84];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 119 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var109" with its value "0".
				// 
				// Substituted "cv$temp$9$var112" with its value "var112".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$8$var111's comment
				// Variable declaration of cv$temp$8$var111 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 90.
				// 
				// Substituted "index$sample35$3" with its value "var84".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var84]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample35Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var109 = 1; i$var109 < samples; i$var109 += 1) {
			if(fixedFlag$sample45) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var84 == st[i$var109])) {
					// Processing sample task 119 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var52 = st[i$var109];
					if(((0 <= var52) && (var52 < noStates))) {
						// Substituted "cv$temp$21$var112" with its value "var112".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$20$var111's comment
						// Variable declaration of cv$temp$20$var111 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[st[i$var109]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 119 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			} else {
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "i$var34" with its value "i$var109".
				// 
				// Substituted "index$sample45$12" with its value "var84".
				double cv$probabilitySample45Value13 = distribution$sample45[(i$var109 - 1)][var84];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 119 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$27$var112" with its value "var112".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$26$var111's comment
				// Variable declaration of cv$temp$26$var111 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 90.
				// 
				// Substituted "index$sample45$12" with its value "var84".
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample45Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var109] - cpuMean[var84]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 119 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample45Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// The proposed new value for the sample
					// 
					// The proposed new value for the sample
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			cpuVar[var84] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var22$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		
		// Allocation of cv$var22$countGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var22$countGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var22$countGlobal[cv$index] = new double[cv$max];
		
		// Allocation of cv$var27$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var27$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$distributionAccumulator$var39
		// 
		// Allocation of cv$distributionAccumulator$var39 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 43. Initially set to the value
		// of putTask 26.
		cv$distributionAccumulator$var39 = new double[noStates];
		
		// Constructor for cv$var30$stateProbabilityGlobal
		// 
		// Allocation of cv$var30$stateProbabilityGlobal for single threaded execution
		cv$var30$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample35gaussian118$global
		// 
		// Allocation of guard$sample35gaussian118$global for single threaded execution
		guard$sample35gaussian118$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample35gaussian123$global
		// 
		// Allocation of guard$sample35gaussian123$global for single threaded execution
		guard$sample35gaussian123$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample35gaussian128$global
		// 
		// Allocation of guard$sample35gaussian128$global for single threaded execution
		guard$sample35gaussian128$global = new boolean[length$cpu_measured];
		
		// Allocation of cv$var40$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 43. Initially set to the value
		// of putTask 26.
		cv$var40$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample45gaussian118$global
		// 
		// Allocation of guard$sample45gaussian118$global for single threaded execution
		guard$sample45gaussian118$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample45gaussian123$global
		// 
		// Allocation of guard$sample45gaussian123$global for single threaded execution
		guard$sample45gaussian123$global = new boolean[length$cpu_measured];
		
		// Allocation of guard$sample45gaussian128$global for single threaded execution
		guard$sample45gaussian128$global = new boolean[length$cpu_measured];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[noStates][];
			for(int var21 = 0; var21 < noStates; var21 += 1)
				m[var21] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st)
			// Constructor for st
			st = new int[length$cpu_measured];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!setFlag$initialStateDistribution)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// If cpu has not been set already allocate space.
		if(!setFlag$cpu)
			// Constructor for cpu
			cpu = new double[length$cpu_measured];
		
		// If mem has not been set already allocate space.
		if(!setFlag$mem)
			// Constructor for mem
			mem = new double[length$cpu_measured];
		
		// If pageFaults has not been set already allocate space.
		if(!setFlag$pageFaults)
			// Constructor for pageFaults
			pageFaults = new double[length$cpu_measured];
		
		// If cpuMean has not been set already allocate space.
		if(!setFlag$cpuMean)
			// Constructor for cpuMean
			cpuMean = new double[noStates];
		
		// If memMean has not been set already allocate space.
		if(!setFlag$memMean)
			// Constructor for memMean
			memMean = new double[noStates];
		
		// If pageFaultsMean has not been set already allocate space.
		if(!setFlag$pageFaultsMean)
			// Constructor for pageFaultsMean
			pageFaultsMean = new double[noStates];
		
		// If cpuVar has not been set already allocate space.
		if(!setFlag$cpuVar)
			// Constructor for cpuVar
			cpuVar = new double[noStates];
		
		// If memVar has not been set already allocate space.
		if(!setFlag$memVar)
			// Constructor for memVar
			memVar = new double[noStates];
		
		// If pageFaultsVar has not been set already allocate space.
		if(!setFlag$pageFaultsVar)
			// Constructor for pageFaultsVar
			pageFaultsVar = new double[noStates];
		
		// Constructor for distribution$sample35
		distribution$sample35 = new double[noStates];
		
		// Constructor for distribution$sample45
		distribution$sample45 = new double[(length$cpu_measured - 1)][];
		for(int i$var34 = 1; i$var34 < length$cpu_measured; i$var34 += 1)
			distribution$sample45[(i$var34 - 1)] = new double[noStates];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var21]);
				}
			);

		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
							memMean[var63] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample80)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample90)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample100)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample110)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						if(!fixedFlag$sample119)
							cpu[i$var109] = ((Math.sqrt(cpuVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var109]]);
						if(!fixedFlag$sample124)
							mem[i$var109] = ((Math.sqrt(memVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var109]]);
						if(!fixedFlag$sample129)
							pageFaults[i$var109] = ((Math.sqrt(pageFaultsVar[st[i$var109]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var109]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var21]);
				}
			);

		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35) {
			for(int index$var29 = 0; index$var29 < noStates; index$var29 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample35's comment
				// Create local copy of variable probabilities.
				distribution$sample35[index$var29] = ((index$var29 < initialStateDistribution.length)?initialStateDistribution[index$var29]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample45 = distribution$sample45[(i$var34 - 1)];
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// Zero the probability of each value
					cv$distribution$sample45[index$var39] = 0.0;
				
				// Iterate through possible values for var39's arguments.
				// 
				// Enumerating the possible arguments for Categorical 39.
				if((1 == i$var34)) {
					// Iterate through possible values for var39's arguments.
					// 
					// Enumerating the possible arguments for Categorical 39.
					if(fixedFlag$sample35) {
						int var21 = st[0];
						
						// Substituted "i$var34" with its value "1".
						if(((0 <= var21) && (var21 < noStates))) {
							// Substituted "i$var34" with its value "1".
							double[] var38 = m[st[0]];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								// Save the probability of each value
								cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + ((index$var39 < var38.length)?var38[index$var39]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample35$2 = 0; index$sample35$2 < noStates; index$sample35$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample35Value3 = distribution$sample35[index$sample35$2];
							double[] var38 = m[index$sample35$2];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								// Save the probability of each value
								cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample35Value3 * ((index$var39 < var38.length)?var38[index$var39]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var34 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 39.
					for(int index$sample45$10 = 0; index$sample45$10 < noStates; index$sample45$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample45Value11 = distribution$sample45[(index$i$9 - 1)][index$sample45$10];
						double[] var38 = m[index$sample45$10];
						for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
							// Save the probability of each value
							cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] + (cv$probabilitySample45Value11 * ((index$var39 < var38.length)?var38[index$var39]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var39$sum = 0.0;
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// sum the probability of each value
					cv$var39$sum = (cv$var39$sum + cv$distribution$sample45[index$var39]);
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// Normalise the probability of each value
					cv$distribution$sample45[index$var39] = (cv$distribution$sample45[index$var39] / cv$var39$sum);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
							memMean[var63] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample80)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample90)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample100)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample110)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var21]);
				}
			);

		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
							memMean[var63] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample80)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample90)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample100)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample110)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample25)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
								sample25(var21, threadID$var21, RNG$1);
					}
				);

			if(!fixedFlag$sample32)
				sample32();
			if(!fixedFlag$sample35)
				sample35();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
					sample45(i$var34);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
								sample58(var52, threadID$var52, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample69)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
								sample69(var63, threadID$var63, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample80)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
								sample80(var74, threadID$var74, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample90)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
								sample90(var84, threadID$var84, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample100)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
								sample100(var94, threadID$var94, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample110)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
								sample110(var104, threadID$var104, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample110)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
								sample110(var104, threadID$var104, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample100)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
								sample100(var94, threadID$var94, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample90)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
								sample90(var84, threadID$var84, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample80)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
								sample80(var74, threadID$var74, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample69)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
								sample69(var63, threadID$var63, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
								sample58(var52, threadID$var52, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				for(int i$var34 = (samples - 1); i$var34 >= 1; i$var34 -= 1)
					sample45(i$var34);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample32)
				sample32();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample25)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
								sample25(var21, threadID$var21, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var14, int forEnd$var14, int threadID$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var14 = forStart$var14; var14 < forEnd$var14; var14 += 1)
						v[var14] = 0.1;
			}
		);
		samples = length$cpu_measured;
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var17 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample25)
			logProbability$var22 = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var30 = 0.0;
		logProbability$var39 = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var40 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var53 = 0.0;
		logProbability$var59 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample69)
			logProbability$var64 = 0.0;
		logProbability$var70 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$var75 = 0.0;
		logProbability$var80 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample90)
			logProbability$var85 = 0.0;
		logProbability$var90 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample100)
			logProbability$var95 = 0.0;
		logProbability$var100 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample110)
			logProbability$var105 = 0.0;
		logProbability$var113 = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample119)
			logProbability$var114 = 0.0;
		logProbability$var118 = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample124)
			logProbability$var119 = 0.0;
		logProbability$var123 = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample129)
			logProbability$var124 = 0.0;
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample25)
			logProbabilityValue$sample25();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		if(fixedFlag$sample100)
			logProbabilityValue$sample100();
		if(fixedFlag$sample110)
			logProbabilityValue$sample110();
		logProbabilityValue$sample119();
		logProbabilityValue$sample124();
		logProbabilityValue$sample129();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample25();
		logProbabilityValue$sample32();
		logProbabilityDistribution$sample35();
		logProbabilityDistribution$sample45();
		logProbabilityValue$sample58();
		logProbabilityValue$sample69();
		logProbabilityValue$sample80();
		logProbabilityValue$sample90();
		logProbabilityValue$sample100();
		logProbabilityValue$sample110();
		logProbabilityDistribution$sample119();
		logProbabilityDistribution$sample124();
		logProbabilityDistribution$sample129();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample25();
		logProbabilityValue$sample32();
		logProbabilityValue$sample35();
		logProbabilityValue$sample45();
		logProbabilityValue$sample58();
		logProbabilityValue$sample69();
		logProbabilityValue$sample80();
		logProbabilityValue$sample90();
		logProbabilityValue$sample100();
		logProbabilityValue$sample110();
		logProbabilityValue$sample119();
		logProbabilityValue$sample124();
		logProbabilityValue$sample129();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var21]);
				}
			);

		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample45) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							cpuMean[var52] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample69)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var63, int forEnd$var63, int threadID$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var63 = forStart$var63; var63 < forEnd$var63; var63 += 1)
							memMean[var63] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample80)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							pageFaultsMean[var74] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample90)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
							cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample100)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var94, int forEnd$var94, int threadID$var94, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var94 = forStart$var94; var94 < forEnd$var94; var94 += 1)
							memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample110)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var104, int forEnd$var104, int threadID$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var104 = forStart$var104; var104 < forEnd$var104; var104 += 1)
							pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		{
			// Deep copy between arrays
			int cv$length1 = pageFaults.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				pageFaults[cv$index1] = pageFaults_measured[cv$index1];
		}
		{
			// Deep copy between arrays
			int cv$length1 = mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				mem[cv$index1] = mem_measured[cv$index1];
		}
		int cv$length1 = cpu.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cpu[cv$index1] = cpu_measured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n    \n    // Construct vectors describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    // Determine how many samples the model will need to produce.\n    int samples = cpu_measured.length;\n    \n    // Allocate space for the state.\n    int[] st = new int[samples];\n\n    // Set the initial state by sampling from a categorical with learnt weightings.\n    double[] initialStateDistribution = dirichlet(v).sample();\n    st[0] = categorical(initialStateDistribution).sampleDistribution();\n\n    //Determine the remaining states based on the previous state.\n    for(int i:[1 .. samples))\n        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n        \n    //Generate each metric.\n    double[] cpu = new double[samples];\n    double[] mem = new double[samples];\n    double[] pageFaults = new double[samples];\n    \n    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n    double[] memMean = gaussian(94, 1).sample(noStates);\n    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n    \n    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n    \n    for(int i:[0 .. samples)) {\n        int s = st[i];\n        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n    }\n\n    //Tie the values to the values we have measured.\n    cpu.observe(cpu_measured);\n    mem.observe(mem_measured);\n    pageFaults.observe(pageFaults_measured);\n}\n";
	}
}