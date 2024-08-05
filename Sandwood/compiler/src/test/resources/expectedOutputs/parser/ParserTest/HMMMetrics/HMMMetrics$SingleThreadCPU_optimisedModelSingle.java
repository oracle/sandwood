package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var39;
	private double[] cv$var22$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var30$stateProbabilityGlobal;
	private double[] cv$var40$stateProbabilityGlobal;
	private double[] distribution$sample34;
	private double[][] distribution$sample44;
	private boolean fixedFlag$sample109 = false;
	private boolean fixedFlag$sample118 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample128 = false;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedFlag$sample99 = false;
	private boolean fixedProbFlag$sample109 = false;
	private boolean fixedProbFlag$sample118 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample128 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean fixedProbFlag$sample89 = false;
	private boolean fixedProbFlag$sample99 = false;
	private boolean[] guard$sample34gaussian117$global;
	private boolean[] guard$sample34gaussian122$global;
	private boolean[] guard$sample34gaussian127$global;
	private boolean[] guard$sample44gaussian117$global;
	private boolean[] guard$sample44gaussian122$global;
	private boolean[] guard$sample44gaussian127$global;
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

	public HMMMetrics$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for fixedFlag$sample109.
	@Override
	public final boolean get$fixedFlag$sample109() {
		return fixedFlag$sample109;
	}

	// Setter for fixedFlag$sample109.
	@Override
	public final void set$fixedFlag$sample109(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample109 including if probabilities
		// need to be updated.
		fixedFlag$sample109 = cv$value;
		
		// Should the probability of sample 109 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample109" with its value "cv$value".
		fixedProbFlag$sample109 = (cv$value && fixedProbFlag$sample109);
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample109" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
	}

	// Getter for fixedFlag$sample118.
	@Override
	public final boolean get$fixedFlag$sample118() {
		return fixedFlag$sample118;
	}

	// Setter for fixedFlag$sample118.
	@Override
	public final void set$fixedFlag$sample118(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample118 including if probabilities
		// need to be updated.
		fixedFlag$sample118 = cv$value;
		
		// Should the probability of sample 118 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample118" with its value "cv$value".
		fixedProbFlag$sample118 = (cv$value && fixedProbFlag$sample118);
	}

	// Getter for fixedFlag$sample123.
	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	// Setter for fixedFlag$sample123.
	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample123 including if probabilities
		// need to be updated.
		fixedFlag$sample123 = cv$value;
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	// Getter for fixedFlag$sample128.
	@Override
	public final boolean get$fixedFlag$sample128() {
		return fixedFlag$sample128;
	}

	// Setter for fixedFlag$sample128.
	@Override
	public final void set$fixedFlag$sample128(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample128 including if probabilities
		// need to be updated.
		fixedFlag$sample128 = cv$value;
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample128" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
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
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample25" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		
		// Should the probability of sample 118 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample118 = (cv$value && fixedProbFlag$sample118);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
	}

	// Getter for fixedFlag$sample44.
	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	// Setter for fixedFlag$sample44.
	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample44 including if probabilities
		// need to be updated.
		fixedFlag$sample44 = cv$value;
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		
		// Should the probability of sample 118 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample118 = (cv$value && fixedProbFlag$sample118);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 118 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample118 = (cv$value && fixedProbFlag$sample118);
	}

	// Getter for fixedFlag$sample68.
	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	// Setter for fixedFlag$sample68.
	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample68 including if probabilities
		// need to be updated.
		fixedFlag$sample68 = cv$value;
		
		// Should the probability of sample 68 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	// Getter for fixedFlag$sample79.
	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	// Setter for fixedFlag$sample79.
	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample79 including if probabilities
		// need to be updated.
		fixedFlag$sample79 = cv$value;
		
		// Should the probability of sample 79 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
	}

	// Getter for fixedFlag$sample89.
	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	// Setter for fixedFlag$sample89.
	@Override
	public final void set$fixedFlag$sample89(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample89 including if probabilities
		// need to be updated.
		fixedFlag$sample89 = cv$value;
		
		// Should the probability of sample 89 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample89" with its value "cv$value".
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
		
		// Should the probability of sample 118 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample89" with its value "cv$value".
		fixedProbFlag$sample118 = (cv$value && fixedProbFlag$sample118);
	}

	// Getter for fixedFlag$sample99.
	@Override
	public final boolean get$fixedFlag$sample99() {
		return fixedFlag$sample99;
	}

	// Setter for fixedFlag$sample99.
	@Override
	public final void set$fixedFlag$sample99(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample99 including if probabilities
		// need to be updated.
		fixedFlag$sample99 = cv$value;
		
		// Should the probability of sample 99 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample99" with its value "cv$value".
		fixedProbFlag$sample99 = (cv$value && fixedProbFlag$sample99);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample99" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
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

	// Calculate the probability of the samples represented by sample118 using probability
	// distributions.
	private final void logProbabilityDistribution$sample118() {
		// Determine if we need to calculate the values for sample task 118 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample118) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 118 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = cpu[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 113.
					// 
					// Enumerating the possible arguments for Gaussian 113.
					if(fixedFlag$sample34) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var52 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var52) && (var52 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(cv$sampleValue, cpuMean[st[0]], cpuVar[st[0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value4 = distribution$sample34[index$sample34$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, cpuMean[index$sample34$3], cpuVar[index$sample34$3]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 113.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 113.
					if(fixedFlag$sample44) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var52 = st[i$var109];
							if(((0 <= var52) && (var52 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityGaussian(cv$sampleValue, cpuMean[st[i$var109]], cpuVar[st[i$var109]]);
								
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
						for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample44Value44 = distribution$sample44[(i$var109 - 1)][index$sample44$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, cpuMean[index$sample44$43], cpuVar[index$sample44$43]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
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
			fixedProbFlag$sample118 = ((((fixedFlag$sample118 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample57) && fixedFlag$sample89);
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

	// Calculate the probability of the samples represented by sample123 using probability
	// distributions.
	private final void logProbabilityDistribution$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample123) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 123 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = mem[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 118.
					// 
					// Enumerating the possible arguments for Gaussian 118.
					if(fixedFlag$sample34) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var63 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var63) && (var63 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(cv$sampleValue, memMean[st[0]], memVar[st[0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value4 = distribution$sample34[index$sample34$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, memMean[index$sample34$3], memVar[index$sample34$3]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 118.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 118.
					if(fixedFlag$sample44) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var63 = st[i$var109];
							if(((0 <= var63) && (var63 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityGaussian(cv$sampleValue, memMean[st[i$var109]], memVar[st[i$var109]]);
								
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
						for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample44Value44 = distribution$sample44[(i$var109 - 1)][index$sample44$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, memMean[index$sample44$43], memVar[index$sample44$43]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
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
			fixedProbFlag$sample123 = ((((fixedFlag$sample123 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample68) && fixedFlag$sample99);
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

	// Calculate the probability of the samples represented by sample128 using probability
	// distributions.
	private final void logProbabilityDistribution$sample128() {
		// Determine if we need to calculate the values for sample task 128 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample128) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 128 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = pageFaults[i$var109];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var109)) {
					// Enumerating the possible arguments for Gaussian 123.
					// 
					// Enumerating the possible arguments for Gaussian 123.
					if(fixedFlag$sample34) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var74 = st[0];
							
							// Substituted "i$var109" with its value "0".
							if(((0 <= var74) && (var74 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var109" with its value "0".
								cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(cv$sampleValue, pageFaultsMean[st[0]], pageFaultsVar[st[0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value4 = distribution$sample34[index$sample34$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, pageFaultsMean[index$sample34$3], pageFaultsVar[index$sample34$3]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 123.
				if((1 <= i$var109)) {
					// Enumerating the possible arguments for Gaussian 123.
					if(fixedFlag$sample44) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var109])) {
							int var74 = st[i$var109];
							if(((0 <= var74) && (var74 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityGaussian(cv$sampleValue, pageFaultsMean[st[i$var109]], pageFaultsVar[st[i$var109]]);
								
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
						for(int index$sample44$43 = 0; index$sample44$43 < noStates; index$sample44$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var34" with its value "i$var109".
							double cv$probabilitySample44Value44 = distribution$sample44[(i$var109 - 1)][index$sample44$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample44Value44) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, pageFaultsMean[index$sample44$43], pageFaultsVar[index$sample44$43]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample44Value44);
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
			fixedProbFlag$sample128 = ((((fixedFlag$sample128 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample79) && fixedFlag$sample109);
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

	// Calculate the probability of the samples represented by sample34 using probability
	// distributions.
	private final void logProbabilityDistribution$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample34) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[0], initialStateDistribution);
				
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
				// Substituted "fixedFlag$sample34" with its value "true".
				fixedProbFlag$sample34 = fixedFlag$sample31;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var29 = logProbability$var30;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample34)
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
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample44 using probability
	// distributions.
	private final void logProbabilityDistribution$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample44) {
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
						if(fixedFlag$sample34) {
							int var21 = st[0];
							
							// Substituted "i$var34" with its value "1".
							if(((0 <= var21) && (var21 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var34" with its value "1".
								cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[st[0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 29.
							for(int index$sample34$4 = 0; index$sample34$4 < noStates; index$sample34$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value5 = distribution$sample34[index$sample34$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample34Value5) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[index$sample34$4]));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var34 - 1)".
					if((2 <= i$var34)) {
						int var21 = st[(i$var34 - 1)];
						if(((0 <= var21) && (var21 < noStates))) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[st[(i$var34 - 1)]]);
							
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
				// Substituted "fixedFlag$sample44" with its value "true".
				fixedProbFlag$sample44 = (fixedFlag$sample25 && fixedFlag$sample34);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$var40;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample44)
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
			if(fixedFlag$sample44)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample109 using sampled
	// values.
	private final void logProbabilityValue$sample109() {
		// Determine if we need to calculate the values for sample task 109 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample109) {
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
			if(fixedFlag$sample109)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample109 = fixedFlag$sample109;
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
			if(fixedFlag$sample109)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var105);
		}
	}

	// Calculate the probability of the samples represented by sample118 using sampled
	// values.
	private final void logProbabilityValue$sample118() {
		// Determine if we need to calculate the values for sample task 118 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample118) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(cpu[i$var109], cpuMean[st[i$var109]], cpuVar[st[i$var109]]));
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
			fixedProbFlag$sample118 = ((((fixedFlag$sample118 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample57) && fixedFlag$sample89);
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

	// Calculate the probability of the samples represented by sample123 using sampled
	// values.
	private final void logProbabilityValue$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample123) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(mem[i$var109], memMean[st[i$var109]], memVar[st[i$var109]]));
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
			fixedProbFlag$sample123 = ((((fixedFlag$sample123 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample68) && fixedFlag$sample99);
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

	// Calculate the probability of the samples represented by sample128 using sampled
	// values.
	private final void logProbabilityValue$sample128() {
		// Determine if we need to calculate the values for sample task 128 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample128) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], pageFaultsMean[st[i$var109]], pageFaultsVar[st[i$var109]]));
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
			fixedProbFlag$sample128 = ((((fixedFlag$sample128 && fixedFlag$sample34) && fixedFlag$sample44) && fixedFlag$sample79) && fixedFlag$sample109);
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

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
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
			if(fixedFlag$sample31)
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
			fixedProbFlag$sample31 = fixedFlag$sample31;
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
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[0], initialStateDistribution);
			
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
			if(fixedFlag$sample34)
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
			fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedFlag$sample31);
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
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample44 using sampled
	// values.
	private final void logProbabilityValue$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityCategorical(st[i$var34], m[st[(i$var34 - 1)]]));
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
			if(fixedFlag$sample44)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample44 = ((fixedFlag$sample44 && fixedFlag$sample25) && fixedFlag$sample34);
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
			if(fixedFlag$sample44)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(cpuMean[var52], 16.0, 8.6));
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
			if(fixedFlag$sample57)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = fixedFlag$sample57;
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
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample68) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(memMean[var63], 94.0, 1.0));
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
			if(fixedFlag$sample68)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample68 = fixedFlag$sample68;
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
			if(fixedFlag$sample68)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var64);
		}
	}

	// Calculate the probability of the samples represented by sample79 using sampled
	// values.
	private final void logProbabilityValue$sample79() {
		// Determine if we need to calculate the values for sample task 79 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample79) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(pageFaultsMean[var74], 814.0, 335550.0));
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
			if(fixedFlag$sample79)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample79 = fixedFlag$sample79;
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
			if(fixedFlag$sample79)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	// Calculate the probability of the samples represented by sample89 using sampled
	// values.
	private final void logProbabilityValue$sample89() {
		// Determine if we need to calculate the values for sample task 89 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample89) {
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
			if(fixedFlag$sample89)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample89 = fixedFlag$sample89;
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
			if(fixedFlag$sample89)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var85);
		}
	}

	// Calculate the probability of the samples represented by sample99 using sampled
	// values.
	private final void logProbabilityValue$sample99() {
		// Determine if we need to calculate the values for sample task 99 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample99) {
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
			if(fixedFlag$sample99)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample99 = fixedFlag$sample99;
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
			if(fixedFlag$sample99)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var95);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 109 drawn from InverseGamma 100. Inference was performed using Metropolis-Hastings.
	private final void sample109(int var104) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
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
				if(fixedFlag$sample34) {
					if((var104 == st[0])) {
						// Processing sample task 128 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 109.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[st[0]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 128 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var104".
					double cv$probabilitySample34Value4 = distribution$sample34[var104];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
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
					// the output of Sample task 109.
					// 
					// Substituted "index$sample34$3" with its value "var104".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[var104], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var104 == st[i$var109])) {
						// Processing sample task 128 of consumer random variable null.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], pageFaultsMean[st[i$var109]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 128 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var104".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var104];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
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
					// the output of Sample task 109.
					// 
					// Substituted "index$sample44$12" with its value "var104".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], pageFaultsMean[var104], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
			if(fixedFlag$sample34) {
				if((var104 == st[0])) {
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 109.
					int var74 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var74) && (var74 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var122" with its value "var122".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[st[0]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 128 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var104".
				double cv$probabilitySample34Value4 = distribution$sample34[var104];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 128 of consumer random variable null.
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
				// the output of Sample task 109.
				// 
				// Substituted "index$sample34$3" with its value "var104".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[var104], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var104 == st[i$var109])) {
					// Processing sample task 128 of consumer random variable null.
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
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], pageFaultsMean[st[i$var109]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 128 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var104".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var104];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 128 of consumer random variable null.
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
				// the output of Sample task 109.
				// 
				// Substituted "index$sample44$12" with its value "var104".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], pageFaultsMean[var104], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
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
	private final void sample25(int var21) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var22$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample44) {
			// Processing random variable 39.
			// 
			// Looking for a path between Sample 25 and consumer Categorical 39.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample34) {
					if((var21 == st[0]))
						// Processing sample task 44 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 44 of random
						// variable var39
						// 
						// A local reference to the scratch space.
						cv$var22$countGlobal[st[1]] = (cv$var22$countGlobal[st[1]] + 1.0);
				} else
					// Processing sample task 44 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 44 of random
					// variable var39
					// 
					// A local reference to the scratch space.
					// 
					// Substituted "index$sample34$3" with its value "var21".
					cv$var22$countGlobal[st[1]] = (cv$var22$countGlobal[st[1]] + distribution$sample34[var21]);
			}
			for(int i$var34 = 2; i$var34 < samples; i$var34 += 1) {
				if((var21 == st[(i$var34 - 1)]))
					// Processing sample task 44 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 44 of random
					// variable var39
					// 
					// A local reference to the scratch space.
					cv$var22$countGlobal[st[i$var34]] = (cv$var22$countGlobal[st[i$var34]] + 1.0);
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
				if(fixedFlag$sample34) {
					if((var21 == st[0])) {
						// Processing sample task 44 of consumer random variable null.
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
							cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + distribution$sample44[0][cv$loopIndex]);
					}
				} else {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample34Value33's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample34$32" with its value "var21".
					double cv$distributionProbability = distribution$sample34[var21];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// A local reference to the scratch space.
						cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + (distribution$sample44[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				int index$i$40 = (i$var34 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$40)) {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample44Value42's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample44$41" with its value "var21".
					double cv$distributionProbability = distribution$sample44[(index$i$40 - 1)][var21];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// A local reference to the scratch space.
						cv$var22$countGlobal[cv$loopIndex] = (cv$var22$countGlobal[cv$loopIndex] + (distribution$sample44[(i$var34 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var22$countGlobal, m[var21]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 26. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample34)
			// Processing random variable 29.
			// 
			// Processing sample task 34 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 34 of random
			// variable var29
			// 
			// A local reference to the scratch space.
			cv$var27$countGlobal[st[0]] = (cv$var27$countGlobal[st[0]] + 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing sample task 34 of consumer random variable null.
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
				cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample34[cv$loopIndex]);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Categorical 29. Inference was performed using variable
	// marginalization.
	private final void sample34() {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, initialStateDistribution);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample44 && (1 < samples)))
				// Looking for a path between Sample 34 and consumer Categorical 39.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 44 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 44 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var34" with its value "1".
				// 
				// cv$temp$1$var38's comment
				// Variable declaration of cv$temp$1$var38 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[1], m[cv$valuePos]) + cv$accumulatedProbabilities);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				// Processing random variable 113.
				// 
				// Looking for a path between Sample 34 and consumer Gaussian 113.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34gaussian117$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample34gaussian117$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian117$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$3$var112's comment
					// Variable declaration of cv$temp$3$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample34gaussian117$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian117$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$11$var112's comment
					// Variable declaration of cv$temp$11$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 34.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
				
				// Processing random variable 118.
				// 
				// Looking for a path between Sample 34 and consumer Gaussian 118.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34gaussian122$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample34gaussian122$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian122$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$19$var117's comment
					// Variable declaration of cv$temp$19$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[0], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample34gaussian122$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian122$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$27$var117's comment
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 34.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[0], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34gaussian127$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample34gaussian127$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian127$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$35$var122's comment
					// Variable declaration of cv$temp$35$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
				
				// Substituted "i$var109" with its value "0".
				if(!guard$sample34gaussian127$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34gaussian127$global[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var109" with its value "0".
					// 
					// cv$temp$43$var122's comment
					// Variable declaration of cv$temp$43$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 34.
					// 
					// Value of the variable at this index
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[0], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample44 && (1 < samples))) {
				// Looking for a path between Sample 34 and consumer Categorical 39.
				// Processing sample task 44 of consumer random variable null.
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
				double[] cv$sampleDistribution = distribution$sample44[0];
				
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
				distribution$sample34[cv$indexName] = (1.0 / cv$var30$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var30$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample34[cv$indexName] = Math.exp((cv$var30$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 44 drawn from Categorical 39. Inference was performed using variable
	// marginalization.
	private final void sample44(int i$var34) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 39 creating
			// sample task 44.
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
				if(fixedFlag$sample34) {
					int var21 = st[0];
					
					// Substituted "i$var34" with its value "1".
					if(((0 <= var21) && (var21 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$0$var38's comment
						// Variable declaration of cv$temp$0$var38 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var34" with its value "1".
						double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, m[st[0]]);
						
						// Processing random variable 113.
						// 
						// Looking for a path between Sample 44 and consumer Gaussian 113.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian117$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian117$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian117$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 118 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 118 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$7$var112's comment
							// Variable declaration of cv$temp$7$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[1], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian117$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian117$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 118 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 118 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$39$var112's comment
							// Variable declaration of cv$temp$39$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[1], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Processing random variable 118.
						// 
						// Looking for a path between Sample 44 and consumer Gaussian 118.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian122$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian122$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian122$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 123 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 123 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$71$var117's comment
							// Variable declaration of cv$temp$71$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[1], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian122$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian122$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 123 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 123 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$103$var117's comment
							// Variable declaration of cv$temp$103$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[1], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian127$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian127$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian127$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 128 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 128 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$135$var122's comment
							// Variable declaration of cv$temp$135$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[1], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian127$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian127$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 128 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 128 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$167$var122's comment
							// Variable declaration of cv$temp$167$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[1], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 29.
					for(int index$sample34$3 = 0; index$sample34$3 < noStates; index$sample34$3 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample34Value4 = distribution$sample34[index$sample34$3];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample34Value4);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$1$var38's comment
						// Variable declaration of cv$temp$1$var38 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample34$3]));
						
						// Processing random variable 113.
						// 
						// Looking for a path between Sample 44 and consumer Gaussian 113.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian117$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian117$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian117$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 118 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 118 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$15$var112's comment
							// Variable declaration of cv$temp$15$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[1], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian117$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian117$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 118 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 118 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$47$var112's comment
							// Variable declaration of cv$temp$47$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[1], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Processing random variable 118.
						// 
						// Looking for a path between Sample 44 and consumer Gaussian 118.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian122$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian122$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian122$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 123 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 123 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$79$var117's comment
							// Variable declaration of cv$temp$79$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[1], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian122$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian122$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 123 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 123 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$111$var117's comment
							// Variable declaration of cv$temp$111$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[1], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var109" with its value "1".
						guard$sample44gaussian127$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample44gaussian127$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian127$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 128 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 128 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$143$var122's comment
							// Variable declaration of cv$temp$143$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[1], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var34" with its value "1".
						// 
						// Substituted "i$var109" with its value "1".
						if(!guard$sample44gaussian127$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var109" with its value "1".
							guard$sample44gaussian127$global[1] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 128 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Processing sample task 128 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var109" with its value "1".
							// 
							// cv$temp$175$var122's comment
							// Variable declaration of cv$temp$175$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 44.
							// 
							// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
							// the output of Sample task 44.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[1], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
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
				for(int index$sample44$11 = 0; index$sample44$11 < noStates; index$sample44$11 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample44Value12 = distribution$sample44[(index$i$10 - 1)][index$sample44$11];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample44Value12);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// cv$temp$3$var38's comment
					// Variable declaration of cv$temp$3$var38 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample44Value12) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample44$11]));
					
					// Processing random variable 113.
					// 
					// Looking for a path between Sample 44 and consumer Gaussian 113.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample44gaussian117$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample44gaussian117$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian117$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 118 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 118 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$31$var112's comment
						// Variable declaration of cv$temp$31$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[i$var34], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
					}
					if(!guard$sample44gaussian117$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian117$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 118 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 118 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$63$var112's comment
						// Variable declaration of cv$temp$63$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 44.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(cpu[i$var34], cpuMean[cv$valuePos], cpuVar[cv$valuePos]) + cv$accumulatedProbabilities);
					}
					
					// Processing random variable 118.
					// 
					// Looking for a path between Sample 44 and consumer Gaussian 118.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample44gaussian122$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample44gaussian122$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian122$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 123 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 123 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$95$var117's comment
						// Variable declaration of cv$temp$95$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[i$var34], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
					}
					if(!guard$sample44gaussian122$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian122$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 123 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 123 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$127$var117's comment
						// Variable declaration of cv$temp$127$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 44.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(mem[i$var34], memMean[cv$valuePos], memVar[cv$valuePos]) + cv$accumulatedProbabilities);
					}
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample44gaussian127$global[i$var34] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample44gaussian127$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian127$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 128 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 128 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$159$var122's comment
						// Variable declaration of cv$temp$159$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[i$var34], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
					}
					if(!guard$sample44gaussian127$global[i$var34]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample44gaussian127$global[i$var34] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 128 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 128 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var109" with its value "i$var34".
						// 
						// cv$temp$191$var122's comment
						// Variable declaration of cv$temp$191$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 44.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(pageFaults[i$var34], pageFaultsMean[cv$valuePos], pageFaultsVar[cv$valuePos]) + cv$accumulatedProbabilities);
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
				// Processing sample task 44 of consumer random variable null.
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
					if(fixedFlag$sample34) {
						int index$var21$612_1 = st[0];
						
						// Substituted "i$var34" with its value "1".
						if(((0 <= index$var21$612_1) && (index$var21$612_1 < noStates)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample34$608 = 0; index$sample34$608 < noStates; index$sample34$608 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample34Value609's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample34[index$sample34$608]);
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
					for(int index$sample44$616 = 0; index$sample44$616 < noStates; index$sample44$616 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample44Value617's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample44[(index$i$615 - 1)][index$sample44$616]);
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
				// Looking for a path between Sample 44 and consumer Categorical 39.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var39, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = distribution$sample44[(index$i$603_2 - 1)];
				
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
		double[] cv$localProbability = distribution$sample44[(i$var34 - 1)];
		
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
	// by sample task 57 drawn from Gaussian 48. Inference was performed using Metropolis-Hastings.
	private final void sample57(int var52) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var46" with its value "8.6".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue, 16.0, 8.6);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample34) {
					if((var52 == st[0])) {
						// Processing sample task 118 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 57.
						int var84 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var84) && (var84 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var111" with its value "var111".
							// 
							// cv$temp$3$var112's comment
							// Variable declaration of cv$temp$3$var112 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[0], cv$originalValue, cpuVar[st[0]]);
							
							// Recorded the probability of reaching sample task 118 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var52".
					double cv$probabilitySample34Value4 = distribution$sample34[var52];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
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
					// 
					// cv$temp$9$var112's comment
					// Variable declaration of cv$temp$9$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 57.
					// 
					// Substituted "index$sample34$3" with its value "var52".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cpu[0], cv$originalValue, cpuVar[var52]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var52 == st[i$var109])) {
						// Processing sample task 118 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var84 = st[i$var109];
						if(((0 <= var84) && (var84 < noStates))) {
							// Substituted "cv$temp$20$var111" with its value "var111".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$21$var112's comment
							// Variable declaration of cv$temp$21$var112 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[i$var109], cv$originalValue, cpuVar[st[i$var109]]);
							
							// Recorded the probability of reaching sample task 118 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var52".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var52];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var111" with its value "var111".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$27$var112's comment
					// Variable declaration of cv$temp$27$var112 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 57.
					// 
					// Substituted "index$sample44$12" with its value "var52".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(cpu[i$var109], cv$originalValue, cpuVar[var52]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
		cpuMean[var52] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var46" with its value "8.6".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue, 16.0, 8.6);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample34) {
				if((var52 == st[0])) {
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 57.
					int var84 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var84) && (var84 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var111" with its value "var111".
						// 
						// cv$temp$3$var112's comment
						// Variable declaration of cv$temp$3$var112 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[0], cv$proposedValue, cpuVar[st[0]]);
						
						// Recorded the probability of reaching sample task 118 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var52".
				double cv$probabilitySample34Value4 = distribution$sample34[var52];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 118 of consumer random variable null.
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
				// cv$temp$9$var112's comment
				// Variable declaration of cv$temp$9$var112 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 57.
				// 
				// Substituted "index$sample34$3" with its value "var52".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cpu[0], cv$proposedValue, cpuVar[var52]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var52 == st[i$var109])) {
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var84 = st[i$var109];
					if(((0 <= var84) && (var84 < noStates))) {
						// Substituted "cv$temp$20$var111" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$21$var112's comment
						// Variable declaration of cv$temp$21$var112 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[i$var109], cv$proposedValue, cpuVar[st[i$var109]]);
						
						// Recorded the probability of reaching sample task 118 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var52".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var52];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 118 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var111" with its value "var111".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$27$var112's comment
				// Variable declaration of cv$temp$27$var112 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
				// the output of Sample task 57.
				// 
				// Substituted "index$sample44$12" with its value "var52".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(cpu[i$var109], cv$proposedValue, cpuVar[var52]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			cpuMean[var52] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from Gaussian 59. Inference was performed using Metropolis-Hastings.
	private final void sample68(int var63) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var58" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue, 94.0, 1.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample34) {
					if((var63 == st[0])) {
						// Processing sample task 123 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 68.
						int var94 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var94) && (var94 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var116" with its value "var116".
							// 
							// cv$temp$3$var117's comment
							// Variable declaration of cv$temp$3$var117 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[0], cv$originalValue, memVar[st[0]]);
							
							// Recorded the probability of reaching sample task 123 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var63".
					double cv$probabilitySample34Value4 = distribution$sample34[var63];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
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
					// 
					// cv$temp$9$var117's comment
					// Variable declaration of cv$temp$9$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 68.
					// 
					// Substituted "index$sample34$3" with its value "var63".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(mem[0], cv$originalValue, memVar[var63]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var63 == st[i$var109])) {
						// Processing sample task 123 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var94 = st[i$var109];
						if(((0 <= var94) && (var94 < noStates))) {
							// Substituted "cv$temp$20$var116" with its value "var116".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$21$var117's comment
							// Variable declaration of cv$temp$21$var117 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[i$var109], cv$originalValue, memVar[st[i$var109]]);
							
							// Recorded the probability of reaching sample task 123 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var63".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var63];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var116" with its value "var116".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$27$var117's comment
					// Variable declaration of cv$temp$27$var117 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 68.
					// 
					// Substituted "index$sample44$12" with its value "var63".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(mem[i$var109], cv$originalValue, memVar[var63]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
		memMean[var63] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var58" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue, 94.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample34) {
				if((var63 == st[0])) {
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 68.
					int var94 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var94) && (var94 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var116" with its value "var116".
						// 
						// cv$temp$3$var117's comment
						// Variable declaration of cv$temp$3$var117 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[0], cv$proposedValue, memVar[st[0]]);
						
						// Recorded the probability of reaching sample task 123 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var63".
				double cv$probabilitySample34Value4 = distribution$sample34[var63];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 123 of consumer random variable null.
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
				// cv$temp$9$var117's comment
				// Variable declaration of cv$temp$9$var117 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 68.
				// 
				// Substituted "index$sample34$3" with its value "var63".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(mem[0], cv$proposedValue, memVar[var63]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var63 == st[i$var109])) {
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var94 = st[i$var109];
					if(((0 <= var94) && (var94 < noStates))) {
						// Substituted "cv$temp$20$var116" with its value "var116".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$21$var117's comment
						// Variable declaration of cv$temp$21$var117 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[i$var109], cv$proposedValue, memVar[st[i$var109]]);
						
						// Recorded the probability of reaching sample task 123 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var63".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var63];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 123 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var116" with its value "var116".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$27$var117's comment
				// Variable declaration of cv$temp$27$var117 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
				// the output of Sample task 68.
				// 
				// Substituted "index$sample44$12" with its value "var63".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(mem[i$var109], cv$proposedValue, memVar[var63]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			memMean[var63] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from Gaussian 70. Inference was performed using Metropolis-Hastings.
	private final void sample79(int var74) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var69" with its value "335550.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue, 814.0, 335550.0);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample34) {
					if((var74 == st[0])) {
						// Processing sample task 128 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
						// the output of Sample task 79.
						int var104 = st[0];
						
						// Substituted "i$var109" with its value "0".
						if(((0 <= var104) && (var104 < noStates))) {
							// Substituted "i$var109" with its value "0".
							// 
							// Substituted "cv$temp$2$var121" with its value "var121".
							// 
							// cv$temp$3$var122's comment
							// Variable declaration of cv$temp$3$var122 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var109" with its value "0".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[0], cv$originalValue, pageFaultsVar[st[0]]);
							
							// Recorded the probability of reaching sample task 128 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var74".
					double cv$probabilitySample34Value4 = distribution$sample34[var74];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
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
					// 
					// cv$temp$9$var122's comment
					// Variable declaration of cv$temp$9$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 79.
					// 
					// Substituted "index$sample34$3" with its value "var74".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(pageFaults[0], cv$originalValue, pageFaultsVar[var74]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var74 == st[i$var109])) {
						// Processing sample task 128 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var104 = st[i$var109];
						if(((0 <= var104) && (var104 < noStates))) {
							// Substituted "cv$temp$20$var121" with its value "var121".
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							// 
							// cv$temp$21$var122's comment
							// Variable declaration of cv$temp$21$var122 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], cv$originalValue, pageFaultsVar[st[i$var109]]);
							
							// Recorded the probability of reaching sample task 128 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var74".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var74];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$26$var121" with its value "var121".
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$27$var122's comment
					// Variable declaration of cv$temp$27$var122 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 79.
					// 
					// Substituted "index$sample44$12" with its value "var74".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], cv$originalValue, pageFaultsVar[var74]));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 128 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
		pageFaultsMean[var74] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var69" with its value "335550.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue, 814.0, 335550.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample34) {
				if((var74 == st[0])) {
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
					// the output of Sample task 79.
					int var104 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var104) && (var104 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$2$var121" with its value "var121".
						// 
						// cv$temp$3$var122's comment
						// Variable declaration of cv$temp$3$var122 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var109" with its value "0".
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[0], cv$proposedValue, pageFaultsVar[st[0]]);
						
						// Recorded the probability of reaching sample task 128 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var74".
				double cv$probabilitySample34Value4 = distribution$sample34[var74];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 128 of consumer random variable null.
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
				// cv$temp$9$var122's comment
				// Variable declaration of cv$temp$9$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 79.
				// 
				// Substituted "index$sample34$3" with its value "var74".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(pageFaults[0], cv$proposedValue, pageFaultsVar[var74]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var74 == st[i$var109])) {
					// Processing sample task 128 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var104 = st[i$var109];
					if(((0 <= var104) && (var104 < noStates))) {
						// Substituted "cv$temp$20$var121" with its value "var121".
						// 
						// Constructing a random variable input for use later.
						// 
						// cv$temp$21$var122's comment
						// Variable declaration of cv$temp$21$var122 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], cv$proposedValue, pageFaultsVar[st[i$var109]]);
						
						// Recorded the probability of reaching sample task 128 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var74".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var74];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 128 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$26$var121" with its value "var121".
				// 
				// Constructing a random variable input for use later.
				// 
				// cv$temp$27$var122's comment
				// Variable declaration of cv$temp$27$var122 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 123 which is consuming
				// the output of Sample task 79.
				// 
				// Substituted "index$sample44$12" with its value "var74".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(pageFaults[i$var109], cv$proposedValue, pageFaultsVar[var74]));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 128 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			pageFaultsMean[var74] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 89 drawn from InverseGamma 80. Inference was performed using Metropolis-Hastings.
	private final void sample89(int var84) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
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
				if(fixedFlag$sample34) {
					if((var84 == st[0])) {
						// Processing sample task 118 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
						// the output of Sample task 89.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[st[0]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 118 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var84".
					double cv$probabilitySample34Value4 = distribution$sample34[var84];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
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
					// the output of Sample task 89.
					// 
					// Substituted "index$sample34$3" with its value "var84".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[var84], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var84 == st[i$var109])) {
						// Processing sample task 118 of consumer random variable null.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[i$var109], cpuMean[st[i$var109]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 118 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var84".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var84];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 118 of consumer random variable null.
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
					// the output of Sample task 89.
					// 
					// Substituted "index$sample44$12" with its value "var84".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(cpu[i$var109], cpuMean[var84], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 118 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
			if(fixedFlag$sample34) {
				if((var84 == st[0])) {
					// Processing sample task 118 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 113 which is consuming
					// the output of Sample task 89.
					int var52 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var52) && (var52 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var112" with its value "var112".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[st[0]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 118 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var84".
				double cv$probabilitySample34Value4 = distribution$sample34[var84];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 118 of consumer random variable null.
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
				// the output of Sample task 89.
				// 
				// Substituted "index$sample34$3" with its value "var84".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(cpu[0], cpuMean[var84], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var84 == st[i$var109])) {
					// Processing sample task 118 of consumer random variable null.
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
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(cpu[i$var109], cpuMean[st[i$var109]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 118 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var84".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var84];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 118 of consumer random variable null.
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
				// the output of Sample task 89.
				// 
				// Substituted "index$sample44$12" with its value "var84".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(cpu[i$var109], cpuMean[var84], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 118 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			cpuVar[var84] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 99 drawn from InverseGamma 90. Inference was performed using Metropolis-Hastings.
	private final void sample99(int var94) {
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
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
				if(fixedFlag$sample34) {
					if((var94 == st[0])) {
						// Processing sample task 123 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
						// the output of Sample task 99.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[0], memMean[st[0]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 123 with the current configuration.
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
					// Substituted "index$sample34$3" with its value "var94".
					double cv$probabilitySample34Value4 = distribution$sample34[var94];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
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
					// the output of Sample task 99.
					// 
					// Substituted "index$sample34$3" with its value "var94".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(mem[0], memMean[var94], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
					
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
				if(fixedFlag$sample44) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var94 == st[i$var109])) {
						// Processing sample task 123 of consumer random variable null.
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
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[i$var109], memMean[st[i$var109]], cv$originalValue);
							
							// Recorded the probability of reaching sample task 123 with the current configuration.
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
					// Substituted "index$sample44$12" with its value "var94".
					double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var94];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 123 of consumer random variable null.
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
					// the output of Sample task 99.
					// 
					// Substituted "index$sample44$12" with its value "var94".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(mem[i$var109], memMean[var94], cv$originalValue));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
					
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
			if(fixedFlag$sample34) {
				if((var94 == st[0])) {
					// Processing sample task 123 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 118 which is consuming
					// the output of Sample task 99.
					int var63 = st[0];
					
					// Substituted "i$var109" with its value "0".
					if(((0 <= var63) && (var63 < noStates))) {
						// Substituted "i$var109" with its value "0".
						// 
						// Substituted "cv$temp$3$var117" with its value "var117".
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[0], memMean[st[0]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 123 with the current configuration.
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
				// Substituted "index$sample34$3" with its value "var94".
				double cv$probabilitySample34Value4 = distribution$sample34[var94];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 123 of consumer random variable null.
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
				// the output of Sample task 99.
				// 
				// Substituted "index$sample34$3" with its value "var94".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value4) + DistributionSampling.logProbabilityGaussian(mem[0], memMean[var94], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample34Value4), 0.0);
				
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
			if(fixedFlag$sample44) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var94 == st[i$var109])) {
					// Processing sample task 123 of consumer random variable null.
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
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityGaussian(mem[i$var109], memMean[st[i$var109]], cv$proposedValue);
						
						// Recorded the probability of reaching sample task 123 with the current configuration.
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
				// Substituted "index$sample44$12" with its value "var94".
				double cv$probabilitySample44Value13 = distribution$sample44[(i$var109 - 1)][var94];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 123 of consumer random variable null.
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
				// the output of Sample task 99.
				// 
				// Substituted "index$sample44$12" with its value "var94".
				double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample44Value13) + DistributionSampling.logProbabilityGaussian(mem[i$var109], memMean[var94], cv$proposedValue));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample44Value13), 0.0);
				
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			memVar[var94] = cv$originalValue;
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
		
		// Allocation of cv$var22$countGlobal for single threaded execution
		cv$var22$countGlobal = new double[cv$max];
		
		// Allocation of cv$var27$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var27$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$distributionAccumulator$var39
		// 
		// Allocation of cv$distributionAccumulator$var39 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 42. Initially set to the value
		// of putTask 26.
		cv$distributionAccumulator$var39 = new double[noStates];
		
		// Constructor for cv$var30$stateProbabilityGlobal
		// 
		// Allocation of cv$var30$stateProbabilityGlobal for single threaded execution
		cv$var30$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample34gaussian117$global
		// 
		// Allocation of guard$sample34gaussian117$global for single threaded execution
		guard$sample34gaussian117$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample34gaussian122$global
		// 
		// Allocation of guard$sample34gaussian122$global for single threaded execution
		guard$sample34gaussian122$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample34gaussian127$global
		// 
		// Allocation of guard$sample34gaussian127$global for single threaded execution
		guard$sample34gaussian127$global = new boolean[length$cpu_measured];
		
		// Allocation of cv$var40$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 42. Initially set to the value
		// of putTask 26.
		cv$var40$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample44gaussian117$global
		// 
		// Allocation of guard$sample44gaussian117$global for single threaded execution
		guard$sample44gaussian117$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample44gaussian122$global
		// 
		// Allocation of guard$sample44gaussian122$global for single threaded execution
		guard$sample44gaussian122$global = new boolean[length$cpu_measured];
		
		// Allocation of guard$sample44gaussian127$global for single threaded execution
		guard$sample44gaussian127$global = new boolean[length$cpu_measured];
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
		
		// Constructor for distribution$sample34
		distribution$sample34 = new double[noStates];
		
		// Constructor for distribution$sample44
		distribution$sample44 = new double[(length$cpu_measured - 1)][];
		for(int i$var34 = 1; i$var34 < length$cpu_measured; i$var34 += 1)
			distribution$sample44[(i$var34 - 1)] = new double[noStates];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = DistributionSampling.sampleGaussian(RNG$, 16.0, 8.6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = DistributionSampling.sampleGaussian(RNG$, 94.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = DistributionSampling.sampleGaussian(RNG$, 814.0, 335550.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample89) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample99) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample109) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			if(!fixedFlag$sample118)
				cpu[i$var109] = DistributionSampling.sampleGaussian(RNG$, cpuMean[st[i$var109]], cpuVar[st[i$var109]]);
			if(!fixedFlag$sample123)
				mem[i$var109] = DistributionSampling.sampleGaussian(RNG$, memMean[st[i$var109]], memVar[st[i$var109]]);
			if(!fixedFlag$sample128)
				pageFaults[i$var109] = DistributionSampling.sampleGaussian(RNG$, pageFaultsMean[st[i$var109]], pageFaultsVar[st[i$var109]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34) {
			for(int index$var29 = 0; index$var29 < noStates; index$var29 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample34's comment
				// Create local copy of variable probabilities.
				distribution$sample34[index$var29] = DistributionSampling.probabilityCategorical(index$var29, initialStateDistribution);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample44 = distribution$sample44[(i$var34 - 1)];
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// Zero the probability of each value
					cv$distribution$sample44[index$var39] = 0.0;
				
				// Iterate through possible values for var39's arguments.
				// 
				// Enumerating the possible arguments for Categorical 39.
				if((1 == i$var34)) {
					// Iterate through possible values for var39's arguments.
					// 
					// Enumerating the possible arguments for Categorical 39.
					if(fixedFlag$sample34) {
						int var21 = st[0];
						
						// Substituted "i$var34" with its value "1".
						if(((0 <= var21) && (var21 < noStates))) {
							// Substituted "i$var34" with its value "1".
							double[] var38 = m[st[0]];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								// Save the probability of each value
								cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + DistributionSampling.probabilityCategorical(index$var39, var38));
						}
					} else {
						// Enumerating the possible outputs of Categorical 29.
						for(int index$sample34$2 = 0; index$sample34$2 < noStates; index$sample34$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value3 = distribution$sample34[index$sample34$2];
							double[] var38 = m[index$sample34$2];
							for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
								// Save the probability of each value
								cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (cv$probabilitySample34Value3 * DistributionSampling.probabilityCategorical(index$var39, var38)));
						}
					}
				}
				int index$i$9 = (i$var34 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 39.
					for(int index$sample44$10 = 0; index$sample44$10 < noStates; index$sample44$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample44Value11 = distribution$sample44[(index$i$9 - 1)][index$sample44$10];
						double[] var38 = m[index$sample44$10];
						for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
							// Save the probability of each value
							cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] + (cv$probabilitySample44Value11 * DistributionSampling.probabilityCategorical(index$var39, var38)));
					}
				}
				
				// Sum the values in the array
				double cv$var39$sum = 0.0;
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// sum the probability of each value
					cv$var39$sum = (cv$var39$sum + cv$distribution$sample44[index$var39]);
				for(int index$var39 = 0; index$var39 < noStates; index$var39 += 1)
					// Normalise the probability of each value
					cv$distribution$sample44[index$var39] = (cv$distribution$sample44[index$var39] / cv$var39$sum);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = DistributionSampling.sampleGaussian(RNG$, 16.0, 8.6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = DistributionSampling.sampleGaussian(RNG$, 94.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = DistributionSampling.sampleGaussian(RNG$, 814.0, 335550.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample89) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample99) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample109) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = DistributionSampling.sampleGaussian(RNG$, 16.0, 8.6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = DistributionSampling.sampleGaussian(RNG$, 94.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = DistributionSampling.sampleGaussian(RNG$, 814.0, 335550.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample89) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample99) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample109) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample25) {
				for(int var21 = 0; var21 < noStates; var21 += 1)
					sample25(var21);
			}
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample34)
				sample34();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample44) {
				for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
					sample44(i$var34);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int var52 = 0; var52 < noStates; var52 += 1)
					sample57(var52);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample68) {
				for(int var63 = 0; var63 < noStates; var63 += 1)
					sample68(var63);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79) {
				for(int var74 = 0; var74 < noStates; var74 += 1)
					sample79(var74);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample89) {
				for(int var84 = 0; var84 < noStates; var84 += 1)
					sample89(var84);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample99) {
				for(int var94 = 0; var94 < noStates; var94 += 1)
					sample99(var94);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample109) {
				for(int var104 = 0; var104 < noStates; var104 += 1)
					sample109(var104);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample109) {
				for(int var104 = (noStates - 1); var104 >= 0; var104 -= 1)
					sample109(var104);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample99) {
				for(int var94 = (noStates - 1); var94 >= 0; var94 -= 1)
					sample99(var94);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample89) {
				for(int var84 = (noStates - 1); var84 >= 0; var84 -= 1)
					sample89(var84);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79) {
				for(int var74 = (noStates - 1); var74 >= 0; var74 -= 1)
					sample79(var74);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample68) {
				for(int var63 = (noStates - 1); var63 >= 0; var63 -= 1)
					sample68(var63);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int var52 = (noStates - 1); var52 >= 0; var52 -= 1)
					sample57(var52);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample44) {
				for(int i$var34 = (samples - 1); i$var34 >= 1; i$var34 -= 1)
					sample44(i$var34);
			}
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample31)
				sample31();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample25) {
				for(int var21 = (noStates - 1); var21 >= 0; var21 -= 1)
					sample25(var21);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
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
		if(!fixedProbFlag$sample31)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var30 = 0.0;
		logProbability$var39 = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$var40 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var53 = 0.0;
		logProbability$var59 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var64 = 0.0;
		logProbability$var70 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample79)
			logProbability$var75 = 0.0;
		logProbability$var80 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var85 = 0.0;
		logProbability$var90 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample99)
			logProbability$var95 = 0.0;
		logProbability$var100 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample109)
			logProbability$var105 = 0.0;
		logProbability$var113 = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample118)
			logProbability$var114 = 0.0;
		logProbability$var118 = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample123)
			logProbability$var119 = 0.0;
		logProbability$var123 = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample128)
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
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		if(fixedFlag$sample89)
			logProbabilityValue$sample89();
		if(fixedFlag$sample99)
			logProbabilityValue$sample99();
		if(fixedFlag$sample109)
			logProbabilityValue$sample109();
		logProbabilityValue$sample118();
		logProbabilityValue$sample123();
		logProbabilityValue$sample128();
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
		logProbabilityValue$sample31();
		logProbabilityDistribution$sample34();
		logProbabilityDistribution$sample44();
		logProbabilityValue$sample57();
		logProbabilityValue$sample68();
		logProbabilityValue$sample79();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
		logProbabilityValue$sample109();
		logProbabilityDistribution$sample118();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample128();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample34();
		logProbabilityValue$sample44();
		logProbabilityValue$sample57();
		logProbabilityValue$sample68();
		logProbabilityValue$sample79();
		logProbabilityValue$sample89();
		logProbabilityValue$sample99();
		logProbabilityValue$sample109();
		logProbabilityValue$sample118();
		logProbabilityValue$sample123();
		logProbabilityValue$sample128();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample25) {
			for(int var21 = 0; var21 < noStates; var21 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var21]);
		}
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample34)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample44) {
			for(int i$var34 = 1; i$var34 < samples; i$var34 += 1)
				st[i$var34] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var34 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var52 = 0; var52 < noStates; var52 += 1)
				cpuMean[var52] = DistributionSampling.sampleGaussian(RNG$, 16.0, 8.6);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68) {
			for(int var63 = 0; var63 < noStates; var63 += 1)
				memMean[var63] = DistributionSampling.sampleGaussian(RNG$, 94.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				pageFaultsMean[var74] = DistributionSampling.sampleGaussian(RNG$, 814.0, 335550.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample89) {
			for(int var84 = 0; var84 < noStates; var84 += 1)
				cpuVar[var84] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample99) {
			for(int var94 = 0; var94 < noStates; var94 += 1)
				memVar[var94] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample109) {
			for(int var104 = 0; var104 < noStates; var104 += 1)
				pageFaultsVar[var104] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
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