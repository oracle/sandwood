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
	private double[] cv$distributionAccumulator$var61;
	private double[] cv$var36$countGlobal;
	private double[] cv$var41$countGlobal;
	private double[] cv$var44$stateProbabilityGlobal;
	private double[] cv$var62$stateProbabilityGlobal;
	private double[] distribution$sample49;
	private double[][] distribution$sample67;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample140 = false;
	private boolean fixedFlag$sample157 = false;
	private boolean fixedFlag$sample174 = false;
	private boolean fixedFlag$sample190 = false;
	private boolean fixedFlag$sample195 = false;
	private boolean fixedFlag$sample200 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample140 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample174 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample195 = false;
	private boolean fixedProbFlag$sample200 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] guard$sample49gaussian189$global;
	private boolean[] guard$sample49gaussian194$global;
	private boolean[] guard$sample49gaussian199$global;
	private boolean[] guard$sample67gaussian189$global;
	private boolean[] guard$sample67gaussian194$global;
	private boolean[] guard$sample67gaussian199$global;
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
	private double logProbability$var106;
	private double logProbability$var118;
	private double logProbability$var123;
	private double logProbability$var135;
	private double logProbability$var140;
	private double logProbability$var152;
	private double logProbability$var157;
	private double logProbability$var169;
	private double logProbability$var184;
	private double logProbability$var185;
	private double logProbability$var189;
	private double logProbability$var190;
	private double logProbability$var194;
	private double logProbability$var195;
	private double logProbability$var24;
	private double logProbability$var36;
	private double logProbability$var40;
	private double logProbability$var43;
	private double logProbability$var44;
	private double logProbability$var61;
	private double logProbability$var62;
	private double logProbability$var70;
	private double logProbability$var82;
	private double logProbability$var88;
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
		// Set flags for all the side effects of cpu including if probabilities need to be
		// updated.
		// Set cpu with flag to mark that it has been set so another array doesn't need to
		// be constructed
		cpu = cv$value;
		setFlag$cpu = true;
		
		// Unset the fixed probability flag for sample 190 as it depends on cpu.
		fixedProbFlag$sample190 = false;
	}

	// Getter for cpuMean.
	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	// Setter for cpuMean.
	@Override
	public final void set$cpuMean(double[] cv$value) {
		// Set flags for all the side effects of cpuMean including if probabilities need to
		// be updated.
		// Set cpuMean with flag to mark that it has been set so another array doesn't need
		// to be constructed
		cpuMean = cv$value;
		setFlag$cpuMean = true;
		
		// Unset the fixed probability flag for sample 87 as it depends on cpuMean.
		fixedProbFlag$sample87 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on cpuMean.
		fixedProbFlag$sample190 = false;
	}

	// Getter for cpuVar.
	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	// Setter for cpuVar.
	@Override
	public final void set$cpuVar(double[] cv$value) {
		// Set flags for all the side effects of cpuVar including if probabilities need to
		// be updated.
		// Set cpuVar with flag to mark that it has been set so another array doesn't need
		// to be constructed
		cpuVar = cv$value;
		setFlag$cpuVar = true;
		
		// Unset the fixed probability flag for sample 140 as it depends on cpuVar.
		fixedProbFlag$sample140 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on cpuVar.
		fixedProbFlag$sample190 = false;
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

	// Getter for fixedFlag$sample105.
	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	// Setter for fixedFlag$sample105.
	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample105 including if probabilities
		// need to be updated.
		fixedFlag$sample105 = cv$value;
		
		// Should the probability of sample 105 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample105" with its value "cv$value".
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample105" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
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
		
		// Should the probability of sample 200 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	// Getter for fixedFlag$sample140.
	@Override
	public final boolean get$fixedFlag$sample140() {
		return fixedFlag$sample140;
	}

	// Setter for fixedFlag$sample140.
	@Override
	public final void set$fixedFlag$sample140(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample140 including if probabilities
		// need to be updated.
		fixedFlag$sample140 = cv$value;
		
		// Should the probability of sample 140 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample140" with its value "cv$value".
		fixedProbFlag$sample140 = (cv$value && fixedProbFlag$sample140);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample140" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample157.
	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	// Setter for fixedFlag$sample157.
	@Override
	public final void set$fixedFlag$sample157(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample157 including if probabilities
		// need to be updated.
		fixedFlag$sample157 = cv$value;
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample157" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample157" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	// Getter for fixedFlag$sample174.
	@Override
	public final boolean get$fixedFlag$sample174() {
		return fixedFlag$sample174;
	}

	// Setter for fixedFlag$sample174.
	@Override
	public final void set$fixedFlag$sample174(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample174 including if probabilities
		// need to be updated.
		fixedFlag$sample174 = cv$value;
		
		// Should the probability of sample 174 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample174" with its value "cv$value".
		fixedProbFlag$sample174 = (cv$value && fixedProbFlag$sample174);
		
		// Should the probability of sample 200 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample174" with its value "cv$value".
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	// Getter for fixedFlag$sample190.
	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	// Setter for fixedFlag$sample190.
	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample190 including if probabilities
		// need to be updated.
		fixedFlag$sample190 = cv$value;
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample190" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample195.
	@Override
	public final boolean get$fixedFlag$sample195() {
		return fixedFlag$sample195;
	}

	// Setter for fixedFlag$sample195.
	@Override
	public final void set$fixedFlag$sample195(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample195 including if probabilities
		// need to be updated.
		fixedFlag$sample195 = cv$value;
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample195" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	// Getter for fixedFlag$sample200.
	@Override
	public final boolean get$fixedFlag$sample200() {
		return fixedFlag$sample200;
	}

	// Setter for fixedFlag$sample200.
	@Override
	public final void set$fixedFlag$sample200(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample200 including if probabilities
		// need to be updated.
		fixedFlag$sample200 = cv$value;
		
		// Should the probability of sample 200 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample200" with its value "cv$value".
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	// Getter for fixedFlag$sample39.
	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	// Setter for fixedFlag$sample39.
	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample39 including if probabilities
		// need to be updated.
		fixedFlag$sample39 = cv$value;
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		
		// Should the probability of sample 67 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
	}

	// Getter for fixedFlag$sample46.
	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	// Setter for fixedFlag$sample46.
	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample46 including if probabilities
		// need to be updated.
		fixedFlag$sample46 = cv$value;
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample46" with its value "cv$value".
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample46" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample49.
	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	// Setter for fixedFlag$sample49.
	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample49 including if probabilities
		// need to be updated.
		fixedFlag$sample49 = cv$value;
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
		
		// Should the probability of sample 67 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
		
		// Should the probability of sample 200 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample49" with its value "cv$value".
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	// Getter for fixedFlag$sample67.
	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	// Setter for fixedFlag$sample67.
	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample67 including if probabilities
		// need to be updated.
		fixedFlag$sample67 = cv$value;
		
		// Should the probability of sample 67 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
		
		// Should the probability of sample 200 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample67" with its value "cv$value".
		fixedProbFlag$sample200 = (cv$value && fixedProbFlag$sample200);
	}

	// Getter for fixedFlag$sample87.
	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	// Setter for fixedFlag$sample87.
	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample87 including if probabilities
		// need to be updated.
		fixedFlag$sample87 = cv$value;
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample87" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample87" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		// Set flags for all the side effects of initialStateDistribution including if probabilities
		// need to be updated.
		// Set initialStateDistribution with flag to mark that it has been set so another
		// array doesn't need to be constructed
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		
		// Unset the fixed probability flag for sample 46 as it depends on initialStateDistribution.
		fixedProbFlag$sample46 = false;
		
		// Unset the fixed probability flag for sample 49 as it depends on initialStateDistribution.
		fixedProbFlag$sample49 = false;
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
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 39 as it depends on m.
		fixedProbFlag$sample39 = false;
		
		// Unset the fixed probability flag for sample 67 as it depends on m.
		fixedProbFlag$sample67 = false;
	}

	// Getter for mem.
	@Override
	public final double[] get$mem() {
		return mem;
	}

	// Setter for mem.
	@Override
	public final void set$mem(double[] cv$value) {
		// Set flags for all the side effects of mem including if probabilities need to be
		// updated.
		// Set mem with flag to mark that it has been set so another array doesn't need to
		// be constructed
		mem = cv$value;
		setFlag$mem = true;
		
		// Unset the fixed probability flag for sample 195 as it depends on mem.
		fixedProbFlag$sample195 = false;
	}

	// Getter for memMean.
	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	// Setter for memMean.
	@Override
	public final void set$memMean(double[] cv$value) {
		// Set flags for all the side effects of memMean including if probabilities need to
		// be updated.
		// Set memMean with flag to mark that it has been set so another array doesn't need
		// to be constructed
		memMean = cv$value;
		setFlag$memMean = true;
		
		// Unset the fixed probability flag for sample 105 as it depends on memMean.
		fixedProbFlag$sample105 = false;
		
		// Unset the fixed probability flag for sample 195 as it depends on memMean.
		fixedProbFlag$sample195 = false;
	}

	// Getter for memVar.
	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	// Setter for memVar.
	@Override
	public final void set$memVar(double[] cv$value) {
		// Set flags for all the side effects of memVar including if probabilities need to
		// be updated.
		// Set memVar with flag to mark that it has been set so another array doesn't need
		// to be constructed
		memVar = cv$value;
		setFlag$memVar = true;
		
		// Unset the fixed probability flag for sample 157 as it depends on memVar.
		fixedProbFlag$sample157 = false;
		
		// Unset the fixed probability flag for sample 195 as it depends on memVar.
		fixedProbFlag$sample195 = false;
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
		// Set flags for all the side effects of pageFaults including if probabilities need
		// to be updated.
		// Set pageFaults with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaults = cv$value;
		setFlag$pageFaults = true;
		
		// Unset the fixed probability flag for sample 200 as it depends on pageFaults.
		fixedProbFlag$sample200 = false;
	}

	// Getter for pageFaultsMean.
	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	// Setter for pageFaultsMean.
	@Override
	public final void set$pageFaultsMean(double[] cv$value) {
		// Set flags for all the side effects of pageFaultsMean including if probabilities
		// need to be updated.
		// Set pageFaultsMean with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaultsMean = cv$value;
		setFlag$pageFaultsMean = true;
		
		// Unset the fixed probability flag for sample 123 as it depends on pageFaultsMean.
		fixedProbFlag$sample123 = false;
		
		// Unset the fixed probability flag for sample 200 as it depends on pageFaultsMean.
		fixedProbFlag$sample200 = false;
	}

	// Getter for pageFaultsVar.
	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	// Setter for pageFaultsVar.
	@Override
	public final void set$pageFaultsVar(double[] cv$value) {
		// Set flags for all the side effects of pageFaultsVar including if probabilities
		// need to be updated.
		// Set pageFaultsVar with flag to mark that it has been set so another array doesn't
		// need to be constructed
		pageFaultsVar = cv$value;
		setFlag$pageFaultsVar = true;
		
		// Unset the fixed probability flag for sample 174 as it depends on pageFaultsVar.
		fixedProbFlag$sample174 = false;
		
		// Unset the fixed probability flag for sample 200 as it depends on pageFaultsVar.
		fixedProbFlag$sample200 = false;
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
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 49 as it depends on st.
		fixedProbFlag$sample49 = false;
		
		// Unset the fixed probability flag for sample 67 as it depends on st.
		fixedProbFlag$sample67 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on st.
		fixedProbFlag$sample190 = false;
		
		// Unset the fixed probability flag for sample 195 as it depends on st.
		fixedProbFlag$sample195 = false;
		
		// Unset the fixed probability flag for sample 200 as it depends on st.
		fixedProbFlag$sample200 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample190 using probability
	// distributions.
	private final void logProbabilityDistribution$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 190 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = cpu[i$var180];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var180)) {
					// Enumerating the possible arguments for Gaussian 184.
					// 
					// Enumerating the possible arguments for Gaussian 184.
					if(fixedFlag$sample49) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var81 = st[0];
							
							// Substituted "i$var180" with its value "0".
							if(((0 <= var81) && (var81 < noStates))) {
								// Substituted "i$var180" with its value "0".
								double var183 = cpuVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var180" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var183))) - (Math.log(var183) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var183 = cpuVar[index$sample49$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample49$3]) / Math.sqrt(var183)))) - (Math.log(var183) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 184.
				if((1 <= i$var180)) {
					// Enumerating the possible arguments for Gaussian 184.
					if(fixedFlag$sample67) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var180])) {
							int var81 = st[i$var180];
							if(((0 <= var81) && (var81 < noStates))) {
								double var183 = cpuVar[st[i$var180]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var180]]) / Math.sqrt(var183))) - (Math.log(var183) * 0.5));
								
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
						// Enumerating the possible outputs of Categorical 61.
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var56" with its value "i$var180".
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var183 = cpuVar[index$sample67$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample67$43]) / Math.sqrt(var183)))) - (Math.log(var183) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
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
			logProbability$var184 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var185 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var184 = logProbability$var185;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var185);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var185);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var185);
		}
	}

	// Calculate the probability of the samples represented by sample195 using probability
	// distributions.
	private final void logProbabilityDistribution$sample195() {
		// Determine if we need to calculate the values for sample task 195 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample195) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 195 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = mem[i$var180];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var180)) {
					// Enumerating the possible arguments for Gaussian 189.
					// 
					// Enumerating the possible arguments for Gaussian 189.
					if(fixedFlag$sample49) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var99 = st[0];
							
							// Substituted "i$var180" with its value "0".
							if(((0 <= var99) && (var99 < noStates))) {
								// Substituted "i$var180" with its value "0".
								double var188 = memVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var180" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var188))) - (Math.log(var188) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var188 = memVar[index$sample49$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample49$3]) / Math.sqrt(var188)))) - (Math.log(var188) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 189.
				if((1 <= i$var180)) {
					// Enumerating the possible arguments for Gaussian 189.
					if(fixedFlag$sample67) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var180])) {
							int var99 = st[i$var180];
							if(((0 <= var99) && (var99 < noStates))) {
								double var188 = memVar[st[i$var180]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var180]]) / Math.sqrt(var188))) - (Math.log(var188) * 0.5));
								
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
						// Enumerating the possible outputs of Categorical 61.
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var56" with its value "i$var180".
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var188 = memVar[index$sample67$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample67$43]) / Math.sqrt(var188)))) - (Math.log(var188) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
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
			logProbability$var189 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var190 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var189 = logProbability$var190;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var190);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var190);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var190);
		}
	}

	// Calculate the probability of the samples represented by sample200 using probability
	// distributions.
	private final void logProbabilityDistribution$sample200() {
		// Determine if we need to calculate the values for sample task 200 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample200) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 200 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = pageFaults[i$var180];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var180)) {
					// Enumerating the possible arguments for Gaussian 194.
					// 
					// Enumerating the possible arguments for Gaussian 194.
					if(fixedFlag$sample49) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var117 = st[0];
							
							// Substituted "i$var180" with its value "0".
							if(((0 <= var117) && (var117 < noStates))) {
								// Substituted "i$var180" with its value "0".
								double var193 = pageFaultsVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var180" with its value "0".
								cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var193))) - (Math.log(var193) * 0.5));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample49$3 = 0; index$sample49$3 < noStates; index$sample49$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample49Value4 = distribution$sample49[index$sample49$3];
							double var193 = pageFaultsVar[index$sample49$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample49$3]) / Math.sqrt(var193)))) - (Math.log(var193) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 194.
				if((1 <= i$var180)) {
					// Enumerating the possible arguments for Gaussian 194.
					if(fixedFlag$sample67) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var180])) {
							int var117 = st[i$var180];
							if(((0 <= var117) && (var117 < noStates))) {
								double var193 = pageFaultsVar[st[i$var180]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var180]]) / Math.sqrt(var193))) - (Math.log(var193) * 0.5));
								
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
						// Enumerating the possible outputs of Categorical 61.
						for(int index$sample67$43 = 0; index$sample67$43 < noStates; index$sample67$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var56" with its value "i$var180".
							double cv$probabilitySample67Value44 = distribution$sample67[(i$var180 - 1)][index$sample67$43];
							double var193 = pageFaultsVar[index$sample67$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = ((Math.log(cv$probabilitySample67Value44) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample67$43]) / Math.sqrt(var193)))) - (Math.log(var193) * 0.5));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample67Value44);
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
			logProbability$var194 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var195 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var194 = logProbability$var195;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var195);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var195);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var195);
		}
	}

	// Calculate the probability of the samples represented by sample49 using probability
	// distributions.
	private final void logProbabilityDistribution$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample49) {
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
				logProbability$var43 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var44 = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample49" with its value "true".
				fixedProbFlag$sample49 = fixedFlag$sample46;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var43 = logProbability$var44;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample49)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var44);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var44);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample49)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	// Calculate the probability of the samples represented by sample67 using probability
	// distributions.
	private final void logProbabilityDistribution$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample67) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample67) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var56];
					
					// Enumerating the possible arguments for Categorical 61.
					if((1 == i$var56)) {
						// Enumerating the possible arguments for Categorical 61.
						if(fixedFlag$sample49) {
							int var35 = st[0];
							
							// Substituted "i$var56" with its value "1".
							if(((0 <= var35) && (var35 < noStates))) {
								// Substituted "i$var56" with its value "1".
								double[] var60 = m[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 43.
							for(int index$sample49$4 = 0; index$sample49$4 < noStates; index$sample49$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample49Value5 = distribution$sample49[index$sample49$4];
								double[] var60 = m[index$sample49$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample49Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample49Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var56 - 1)".
					if((2 <= i$var56)) {
						int var35 = st[(i$var56 - 1)];
						if(((0 <= var35) && (var35 < noStates))) {
							double[] var60 = m[st[(i$var56 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
				logProbability$var61 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var62 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample67" with its value "true".
				fixedProbFlag$sample67 = (fixedFlag$sample39 && fixedFlag$sample49);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var61 = logProbability$var62;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample67)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var62);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var62);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	// Calculate the probability of the samples represented by sample105 using sampled
	// values.
	private final void logProbabilityValue$sample105() {
		// Determine if we need to calculate the values for sample task 105 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample105) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var99 = 0; var99 < noStates; var99 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var99] - 94.0)));
			logProbability$var88 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var100 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample105)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample105 = fixedFlag$sample105;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var88 = logProbability$var100;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memMean = (logProbability$memMean + logProbability$var100);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var100);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample105)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var100);
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
			for(int var117 = 0; var117 < noStates; var117 += 1)
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var117] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			logProbability$var106 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var118 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample123)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample123 = fixedFlag$sample123;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var106 = logProbability$var118;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var118);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var118);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample123)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var118);
		}
	}

	// Calculate the probability of the samples represented by sample140 using sampled
	// values.
	private final void logProbabilityValue$sample140() {
		// Determine if we need to calculate the values for sample task 140 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample140) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var134 = 0; var134 < noStates; var134 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var134], 5.0, 0.5));
			logProbability$var123 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var135 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample140)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample140 = fixedFlag$sample140;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var123 = logProbability$var135;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var135);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var135);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample140)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var135);
		}
	}

	// Calculate the probability of the samples represented by sample157 using sampled
	// values.
	private final void logProbabilityValue$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var151 = 0; var151 < noStates; var151 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var151], 5.0, 0.5));
			logProbability$var140 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var152 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample157)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample157 = fixedFlag$sample157;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var140 = logProbability$var152;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memVar = (logProbability$memVar + logProbability$var152);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var152);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample157)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var152);
		}
	}

	// Calculate the probability of the samples represented by sample174 using sampled
	// values.
	private final void logProbabilityValue$sample174() {
		// Determine if we need to calculate the values for sample task 174 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample174) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var168 = 0; var168 < noStates; var168 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var168], 5.0, 0.5));
			logProbability$var157 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var169 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample174)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample174 = fixedFlag$sample174;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var157 = logProbability$var169;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var169);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var169);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample174)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var169);
		}
	}

	// Calculate the probability of the samples represented by sample190 using sampled
	// values.
	private final void logProbabilityValue$sample190() {
		// Determine if we need to calculate the values for sample task 190 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample190) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double var183 = cpuVar[st[i$var180]];
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(var183)))) - (Math.log(var183) * 0.5));
			}
			logProbability$var184 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var185 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample190 = ((((fixedFlag$sample190 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample87) && fixedFlag$sample140);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var184 = logProbability$var185;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var185);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var185);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var185);
		}
	}

	// Calculate the probability of the samples represented by sample195 using sampled
	// values.
	private final void logProbabilityValue$sample195() {
		// Determine if we need to calculate the values for sample task 195 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample195) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double var188 = memVar[st[i$var180]];
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(var188)))) - (Math.log(var188) * 0.5));
			}
			logProbability$var189 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var190 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample195 = ((((fixedFlag$sample195 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample105) && fixedFlag$sample157);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var189 = logProbability$var190;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var190);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var190);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var190);
		}
	}

	// Calculate the probability of the samples represented by sample200 using sampled
	// values.
	private final void logProbabilityValue$sample200() {
		// Determine if we need to calculate the values for sample task 200 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample200) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
				double var193 = pageFaultsVar[st[i$var180]];
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(var193)))) - (Math.log(var193) * 0.5));
			}
			logProbability$var194 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var195 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample200 = ((((fixedFlag$sample200 && fixedFlag$sample49) && fixedFlag$sample67) && fixedFlag$sample123) && fixedFlag$sample174);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var194 = logProbability$var195;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var195);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var195);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var195);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < noStates; var35 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var35], v));
			logProbability$var24 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var36 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample39)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample39 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var24 = logProbability$var36;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var36);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var36);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
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
			logProbability$var40 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample46)
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
			fixedProbFlag$sample46 = fixedFlag$sample46;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var40 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
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
			logProbability$var43 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var44 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample49)
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
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample46);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var43 = logProbability$var44;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var44);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var44);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample49)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample67) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var56];
				double[] var60 = m[st[(i$var56 - 1)]];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var60.length))?Math.log(var60[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var61 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var62 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample67)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample67 = ((fixedFlag$sample67 && fixedFlag$sample39) && fixedFlag$sample49);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var61 = logProbability$var62;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var62);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var62);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample67)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var81 = 0; var81 < noStates; var81 += 1)
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var81] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			logProbability$var70 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var82 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample87)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample87 = fixedFlag$sample87;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var70 = logProbability$var82;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var82);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var82);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample87)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var82);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 105 drawn from Gaussian 88. Inference was performed using Metropolis-Hastings.
	private final void sample105(int var99) {
		// The original value of the sample
		double cv$originalValue = memMean[var99];
		
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
			// Substituted "cv$temp$1$var87" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var99 == st[0])) {
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 105.
						int var151 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var151) && (var151 < noStates))) {
							// Variable declaration of cv$temp$3$var188 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							double cv$temp$3$var188 = memVar[st[0]];
							
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var187's comment
							// Variable declaration of cv$temp$2$var187 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var188))) - (Math.log(cv$temp$3$var188) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var99".
					double cv$probabilitySample49Value4 = distribution$sample49[var99];
					
					// Variable declaration of cv$temp$9$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample49$3" with its value "var99".
					double cv$temp$9$var188 = memVar[var99];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$8$var187's comment
					// Variable declaration of cv$temp$8$var187 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var188)))) - (Math.log(cv$temp$9$var188) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$9$var188's comment
						// Variable declaration of cv$temp$9$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 105.
						// 
						// Substituted "index$sample49$3" with its value "var99".
						// 
						// cv$temp$9$var188's comment
						// Variable declaration of cv$temp$9$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 105.
						// 
						// Substituted "index$sample49$3" with its value "var99".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var99 == st[i$var180])) {
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var151 = st[i$var180];
						if(((0 <= var151) && (var151 < noStates))) {
							// Variable declaration of cv$temp$21$var188 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var188 = memVar[st[i$var180]];
							
							// cv$temp$20$var187's comment
							// Variable declaration of cv$temp$20$var187 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var188))) - (Math.log(cv$temp$21$var188) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var99".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var99];
					
					// Variable declaration of cv$temp$27$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample67$12" with its value "var99".
					double cv$temp$27$var188 = memVar[var99];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$26$var187's comment
					// Variable declaration of cv$temp$26$var187 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var188)))) - (Math.log(cv$temp$27$var188) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var188's comment
						// Variable declaration of cv$temp$27$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 105.
						// 
						// Substituted "index$sample67$12" with its value "var99".
						// 
						// cv$temp$27$var188's comment
						// Variable declaration of cv$temp$27$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 105.
						// 
						// Substituted "index$sample67$12" with its value "var99".
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
		memMean[var99] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var87" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var99 == st[0])) {
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					int var151 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var151) && (var151 < noStates))) {
						// Variable declaration of cv$temp$3$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						double cv$temp$3$var188 = memVar[st[0]];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var187's comment
						// Variable declaration of cv$temp$2$var187 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var188))) - (Math.log(cv$temp$3$var188) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var99".
				double cv$probabilitySample49Value4 = distribution$sample49[var99];
				
				// Variable declaration of cv$temp$9$var188 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
				// the output of Sample task 105.
				// 
				// Substituted "index$sample49$3" with its value "var99".
				double cv$temp$9$var188 = memVar[var99];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 195 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$8$var187's comment
				// Variable declaration of cv$temp$8$var187 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var188)))) - (Math.log(cv$temp$9$var188) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var188's comment
					// Variable declaration of cv$temp$9$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample49$3" with its value "var99".
					// 
					// cv$temp$9$var188's comment
					// Variable declaration of cv$temp$9$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample49$3" with its value "var99".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var99 == st[i$var180])) {
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var151 = st[i$var180];
					if(((0 <= var151) && (var151 < noStates))) {
						// Variable declaration of cv$temp$21$var188 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var188 = memVar[st[i$var180]];
						
						// cv$temp$20$var187's comment
						// Variable declaration of cv$temp$20$var187 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var188))) - (Math.log(cv$temp$21$var188) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var99".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var99];
				
				// Variable declaration of cv$temp$27$var188 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
				// the output of Sample task 105.
				// 
				// Substituted "index$sample67$12" with its value "var99".
				double cv$temp$27$var188 = memVar[var99];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 195 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$26$var187's comment
				// Variable declaration of cv$temp$26$var187 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var188)))) - (Math.log(cv$temp$27$var188) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var188's comment
					// Variable declaration of cv$temp$27$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample67$12" with its value "var99".
					// 
					// cv$temp$27$var188's comment
					// Variable declaration of cv$temp$27$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 105.
					// 
					// Substituted "index$sample67$12" with its value "var99".
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
			memMean[var99] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 123 drawn from Gaussian 106. Inference was performed using Metropolis-Hastings.
	private final void sample123(int var117) {
		// The original value of the sample
		double cv$originalValue = pageFaultsMean[var117];
		
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
			// Substituted "cv$temp$1$var105" with its value "335550.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var117 == st[0])) {
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 123.
						int var168 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var168) && (var168 < noStates))) {
							// Variable declaration of cv$temp$3$var193 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							double cv$temp$3$var193 = pageFaultsVar[st[0]];
							
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var192's comment
							// Variable declaration of cv$temp$2$var192 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var193))) - (Math.log(cv$temp$3$var193) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var117".
					double cv$probabilitySample49Value4 = distribution$sample49[var117];
					
					// Variable declaration of cv$temp$9$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample49$3" with its value "var117".
					double cv$temp$9$var193 = pageFaultsVar[var117];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$8$var192's comment
					// Variable declaration of cv$temp$8$var192 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var193)))) - (Math.log(cv$temp$9$var193) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$9$var193's comment
						// Variable declaration of cv$temp$9$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 123.
						// 
						// Substituted "index$sample49$3" with its value "var117".
						// 
						// cv$temp$9$var193's comment
						// Variable declaration of cv$temp$9$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 123.
						// 
						// Substituted "index$sample49$3" with its value "var117".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var117 == st[i$var180])) {
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var168 = st[i$var180];
						if(((0 <= var168) && (var168 < noStates))) {
							// Variable declaration of cv$temp$21$var193 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var193 = pageFaultsVar[st[i$var180]];
							
							// cv$temp$20$var192's comment
							// Variable declaration of cv$temp$20$var192 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var193))) - (Math.log(cv$temp$21$var193) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var117".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var117];
					
					// Variable declaration of cv$temp$27$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample67$12" with its value "var117".
					double cv$temp$27$var193 = pageFaultsVar[var117];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$26$var192's comment
					// Variable declaration of cv$temp$26$var192 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var193)))) - (Math.log(cv$temp$27$var193) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var193's comment
						// Variable declaration of cv$temp$27$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 123.
						// 
						// Substituted "index$sample67$12" with its value "var117".
						// 
						// cv$temp$27$var193's comment
						// Variable declaration of cv$temp$27$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 123.
						// 
						// Substituted "index$sample67$12" with its value "var117".
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
		pageFaultsMean[var117] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var105" with its value "335550.0".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var117 == st[0])) {
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					int var168 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var168) && (var168 < noStates))) {
						// Variable declaration of cv$temp$3$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						double cv$temp$3$var193 = pageFaultsVar[st[0]];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var192's comment
						// Variable declaration of cv$temp$2$var192 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var193))) - (Math.log(cv$temp$3$var193) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var117".
				double cv$probabilitySample49Value4 = distribution$sample49[var117];
				
				// Variable declaration of cv$temp$9$var193 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
				// the output of Sample task 123.
				// 
				// Substituted "index$sample49$3" with its value "var117".
				double cv$temp$9$var193 = pageFaultsVar[var117];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 200 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$8$var192's comment
				// Variable declaration of cv$temp$8$var192 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var193)))) - (Math.log(cv$temp$9$var193) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var193's comment
					// Variable declaration of cv$temp$9$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample49$3" with its value "var117".
					// 
					// cv$temp$9$var193's comment
					// Variable declaration of cv$temp$9$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample49$3" with its value "var117".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var117 == st[i$var180])) {
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var168 = st[i$var180];
					if(((0 <= var168) && (var168 < noStates))) {
						// Variable declaration of cv$temp$21$var193 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var193 = pageFaultsVar[st[i$var180]];
						
						// cv$temp$20$var192's comment
						// Variable declaration of cv$temp$20$var192 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var193))) - (Math.log(cv$temp$21$var193) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var117".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var117];
				
				// Variable declaration of cv$temp$27$var193 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
				// the output of Sample task 123.
				// 
				// Substituted "index$sample67$12" with its value "var117".
				double cv$temp$27$var193 = pageFaultsVar[var117];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 200 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$26$var192's comment
				// Variable declaration of cv$temp$26$var192 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var193)))) - (Math.log(cv$temp$27$var193) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var193's comment
					// Variable declaration of cv$temp$27$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample67$12" with its value "var117".
					// 
					// cv$temp$27$var193's comment
					// Variable declaration of cv$temp$27$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 123.
					// 
					// Substituted "index$sample67$12" with its value "var117".
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
			pageFaultsMean[var117] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 140 drawn from InverseGamma 123. Inference was performed using Metropolis-Hastings.
	private final void sample140(int var134) {
		// The original value of the sample
		double cv$originalValue = cpuVar[var134];
		
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
			// Substituted "cv$temp$1$var121" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var134 == st[0])) {
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 140.
						int var81 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var81) && (var81 < noStates))) {
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var182's comment
							// Variable declaration of cv$temp$2$var182 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$3$var183's comment
							// Variable declaration of cv$temp$3$var183 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var134".
					double cv$probabilitySample49Value4 = distribution$sample49[var134];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var183's comment
					// Variable declaration of cv$temp$9$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var134]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
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
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var134 == st[i$var180])) {
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var81 = st[i$var180];
						if(((0 <= var81) && (var81 < noStates))) {
							// cv$temp$21$var183's comment
							// Variable declaration of cv$temp$21$var183 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var134".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var134];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$27$var183's comment
					// Variable declaration of cv$temp$27$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[var134]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
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
		cpuVar[var134] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var121" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var134 == st[0])) {
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 140.
					int var81 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var81) && (var81 < noStates))) {
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var182's comment
						// Variable declaration of cv$temp$2$var182 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$3$var183's comment
						// Variable declaration of cv$temp$3$var183 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var134".
				double cv$probabilitySample49Value4 = distribution$sample49[var134];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 190 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$9$var183's comment
				// Variable declaration of cv$temp$9$var183 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var134]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
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
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var134 == st[i$var180])) {
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var81 = st[i$var180];
					if(((0 <= var81) && (var81 < noStates))) {
						// cv$temp$21$var183's comment
						// Variable declaration of cv$temp$21$var183 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var134".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var134];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 190 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$27$var183's comment
				// Variable declaration of cv$temp$27$var183 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cpuMean[var134]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
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
			cpuVar[var134] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 157 drawn from InverseGamma 140. Inference was performed using Metropolis-Hastings.
	private final void sample157(int var151) {
		// The original value of the sample
		double cv$originalValue = memVar[var151];
		
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
			// Substituted "cv$temp$1$var138" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var151 == st[0])) {
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 157.
						int var99 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var99) && (var99 < noStates))) {
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var187's comment
							// Variable declaration of cv$temp$2$var187 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$3$var188's comment
							// Variable declaration of cv$temp$3$var188 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var151".
					double cv$probabilitySample49Value4 = distribution$sample49[var151];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var188's comment
					// Variable declaration of cv$temp$9$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var151]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
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
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var151 == st[i$var180])) {
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var99 = st[i$var180];
						if(((0 <= var99) && (var99 < noStates))) {
							// cv$temp$21$var188's comment
							// Variable declaration of cv$temp$21$var188 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var151".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var151];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$27$var188's comment
					// Variable declaration of cv$temp$27$var188 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[var151]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 195 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
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
		memVar[var151] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var138" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var151 == st[0])) {
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 157.
					int var99 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var99) && (var99 < noStates))) {
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var187's comment
						// Variable declaration of cv$temp$2$var187 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$3$var188's comment
						// Variable declaration of cv$temp$3$var188 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var151".
				double cv$probabilitySample49Value4 = distribution$sample49[var151];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 195 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$9$var188's comment
				// Variable declaration of cv$temp$9$var188 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var151]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
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
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var151 == st[i$var180])) {
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var99 = st[i$var180];
					if(((0 <= var99) && (var99 < noStates))) {
						// cv$temp$21$var188's comment
						// Variable declaration of cv$temp$21$var188 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var151".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var151];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 195 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$27$var188's comment
				// Variable declaration of cv$temp$27$var188 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((mem[i$var180] - memMean[var151]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
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
			memVar[var151] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 174 drawn from InverseGamma 157. Inference was performed using Metropolis-Hastings.
	private final void sample174(int var168) {
		// The original value of the sample
		double cv$originalValue = pageFaultsVar[var168];
		
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
			// Substituted "cv$temp$1$var155" with its value "0.5".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var168 == st[0])) {
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 174.
						int var117 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var117) && (var117 < noStates))) {
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var192's comment
							// Variable declaration of cv$temp$2$var192 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$3$var193's comment
							// Variable declaration of cv$temp$3$var193 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var168".
					double cv$probabilitySample49Value4 = distribution$sample49[var168];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var193's comment
					// Variable declaration of cv$temp$9$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var168]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
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
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var168 == st[i$var180])) {
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var117 = st[i$var180];
						if(((0 <= var117) && (var117 < noStates))) {
							// cv$temp$21$var193's comment
							// Variable declaration of cv$temp$21$var193 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var168".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var168];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$27$var193's comment
					// Variable declaration of cv$temp$27$var193 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[var168]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 200 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
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
		pageFaultsVar[var168] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var155" with its value "0.5".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var168 == st[0])) {
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 174.
					int var117 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var117) && (var117 < noStates))) {
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var192's comment
						// Variable declaration of cv$temp$2$var192 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$3$var193's comment
						// Variable declaration of cv$temp$3$var193 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var168".
				double cv$probabilitySample49Value4 = distribution$sample49[var168];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 200 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$9$var193's comment
				// Variable declaration of cv$temp$9$var193 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var168]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
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
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var168 == st[i$var180])) {
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var117 = st[i$var180];
					if(((0 <= var117) && (var117 < noStates))) {
						// cv$temp$21$var193's comment
						// Variable declaration of cv$temp$21$var193 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[st[i$var180]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var168".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var168];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 200 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$27$var193's comment
				// Variable declaration of cv$temp$27$var193 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((pageFaults[i$var180] - pageFaultsMean[var168]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 200 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
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
			pageFaultsVar[var168] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 24. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample39(int var35) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var36$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample67) {
			// Processing random variable 61.
			// 
			// Looking for a path between Sample 39 and consumer Categorical 61.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample49) {
					if((var35 == st[0]))
						// Processing sample task 67 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 67 of random
						// variable var61
						// 
						// A local reference to the scratch space.
						cv$var36$countGlobal[st[1]] = (cv$var36$countGlobal[st[1]] + 1.0);
				} else
					// Processing sample task 67 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 67 of random
					// variable var61
					// 
					// A local reference to the scratch space.
					// 
					// Substituted "index$sample49$3" with its value "var35".
					cv$var36$countGlobal[st[1]] = (cv$var36$countGlobal[st[1]] + distribution$sample49[var35]);
			}
			for(int i$var56 = 2; i$var56 < samples; i$var56 += 1) {
				if((var35 == st[(i$var56 - 1)]))
					// Processing sample task 67 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 67 of random
					// variable var61
					// 
					// A local reference to the scratch space.
					cv$var36$countGlobal[st[i$var56]] = (cv$var36$countGlobal[st[i$var56]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 61.
			// 
			// Looking for a path between Sample 39 and consumer Categorical 61.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample49) {
					if((var35 == st[0])) {
						// Processing sample task 67 of consumer random variable null.
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
							cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + distribution$sample67[0][cv$loopIndex]);
					}
				} else {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample49Value33's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample49$32" with its value "var35".
					double cv$distributionProbability = distribution$sample49[var35];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// A local reference to the scratch space.
						cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + (distribution$sample67[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				int index$i$40 = (i$var56 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$40)) {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample67Value42's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample67$41" with its value "var35".
					double cv$distributionProbability = distribution$sample67[(index$i$40 - 1)][var35];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// A local reference to the scratch space.
						cv$var36$countGlobal[cv$loopIndex] = (cv$var36$countGlobal[cv$loopIndex] + (distribution$sample67[(i$var56 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var36$countGlobal, m[var35]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 46 drawn from Dirichlet 40. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample46() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var41$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample49)
			// Processing random variable 43.
			// 
			// Processing sample task 49 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 49 of random
			// variable var43
			// 
			// A local reference to the scratch space.
			cv$var41$countGlobal[st[0]] = (cv$var41$countGlobal[st[0]] + 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing sample task 49 of consumer random variable null.
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
				cv$var41$countGlobal[cv$loopIndex] = (cv$var41$countGlobal[cv$loopIndex] + distribution$sample49[cv$loopIndex]);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var41$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 49 drawn from Categorical 43. Inference was performed using variable
	// marginalization.
	private final void sample49() {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample67 && (1 < samples))) {
				// Looking for a path between Sample 49 and consumer Categorical 61.
				// Processing sample task 67 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Variable declaration of cv$temp$1$var60 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] cv$temp$1$var60 = m[cv$valuePos];
					
					// Substituted "i$var56" with its value "1".
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[1]) && (st[1] < cv$temp$1$var60.length))?Math.log(cv$temp$1$var60[st[1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 67 with the current configuration.
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				// Processing random variable 184.
				// 
				// Looking for a path between Sample 49 and consumer Gaussian 184.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample49gaussian189$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample49gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian189$global[0] = true;
					
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$3$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$3$var183 = cpuVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var182's comment
						// Variable declaration of cv$temp$2$var182 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				
				// Substituted "i$var180" with its value "0".
				if(!guard$sample49gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian189$global[0] = true;
					
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 49.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$11$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						double cv$temp$11$var183 = cpuVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$10$var182's comment
						// Variable declaration of cv$temp$10$var182 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$11$var183))) - (Math.log(cv$temp$11$var183) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				
				// Processing random variable 189.
				// 
				// Looking for a path between Sample 49 and consumer Gaussian 189.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample49gaussian194$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample49gaussian194$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian194$global[0] = true;
					
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$19$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$19$var188 = memVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$18$var187's comment
						// Variable declaration of cv$temp$18$var187 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$19$var188))) - (Math.log(cv$temp$19$var188) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				
				// Substituted "i$var180" with its value "0".
				if(!guard$sample49gaussian194$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian194$global[0] = true;
					
					// Processing sample task 195 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
					// the output of Sample task 49.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$27$var188 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						double cv$temp$27$var188 = memVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$26$var187's comment
						// Variable declaration of cv$temp$26$var187 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$27$var188))) - (Math.log(cv$temp$27$var188) * 0.5));
						
						// Recorded the probability of reaching sample task 195 with the current configuration.
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
				
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample49gaussian199$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample49gaussian199$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian199$global[0] = true;
					
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$35$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$35$var193 = pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$34$var192's comment
						// Variable declaration of cv$temp$34$var192 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$35$var193))) - (Math.log(cv$temp$35$var193) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
				
				// Substituted "i$var180" with its value "0".
				if(!guard$sample49gaussian199$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample49gaussian199$global[0] = true;
					
					// Processing sample task 200 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
					// the output of Sample task 49.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$43$var193 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						double cv$temp$43$var193 = pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$42$var192's comment
						// Variable declaration of cv$temp$42$var192 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 49.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$43$var193))) - (Math.log(cv$temp$43$var193) * 0.5));
						
						// Recorded the probability of reaching sample task 200 with the current configuration.
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
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample67 && (1 < samples))) {
				// Looking for a path between Sample 49 and consumer Categorical 61.
				// Processing sample task 67 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var61[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$reachedDistributionProbability = 1.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// cv$temp$50$var60's comment
					// Variable declaration of cv$temp$50$var60 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var61, 1.0, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var56" with its value "1".
				double[] cv$sampleDistribution = distribution$sample67[0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var61[cv$i] / cv$reachedDistributionProbability);
					
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
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var44$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
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
		double cv$lseMax = cv$var44$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var44$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var44$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample49[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample49[cv$indexName] = Math.exp((cv$var44$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample49[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 67 drawn from Categorical 61. Inference was performed using variable
	// marginalization.
	private final void sample67(int i$var56) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Enumerating the possible arguments for Categorical 61.
		if((1 == i$var56)) {
			// Enumerating the possible arguments for Categorical 61.
			if(fixedFlag$sample49) {
				int var35 = st[0];
				
				// Substituted "i$var56" with its value "1".
				if(((0 <= var35) && (var35 < noStates)))
					// variable marginalization
					// 
					// cv$noStates's comment
					// Calculate the number of states to evaluate.
					cv$noStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 43.
				if((0 < noStates))
					// variable marginalization
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample67) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= i$var56)) {
				int var35 = st[(i$var56 - 1)];
				if(((0 <= var35) && (var35 < noStates)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$i$11 = (i$var56 - 1);
				
				// index$i$1's comment
				// Exploring all the possible state counts for random variable 61.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$11" with its value "(i$var56 - 1)".
				// 
				// Substituted "index$i$11" with its value "(i$var56 - 1)".
				// 
				// Substituted "index$i$11" with its value "(i$var56 - 1)".
				// 
				// Substituted "index$i$11" with its value "(i$var56 - 1)".
				if(((1 <= index$i$11) && !(index$i$11 == i$var56)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 61 creating
			// sample task 67.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 61.
			if((1 == i$var56)) {
				// Enumerating the possible arguments for Categorical 61.
				if(fixedFlag$sample49) {
					int var35 = st[0];
					
					// Substituted "i$var56" with its value "1".
					if(((0 <= var35) && (var35 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var60 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var56" with its value "1".
						double[] cv$temp$0$var60 = m[st[0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var60.length)?Math.log(cv$temp$0$var60[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Processing random variable 184.
						// 
						// Looking for a path between Sample 67 and consumer Gaussian 184.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian189$global[1] = true;
							
							// Processing sample task 190 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$7$var183 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$7$var183 = cpuVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$6$var182's comment
								// Variable declaration of cv$temp$6$var182 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$7$var183))) - (Math.log(cv$temp$7$var183) * 0.5));
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian189$global[1] = true;
							
							// Processing sample task 190 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$39$var183 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$39$var183 = cpuVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$38$var182's comment
								// Variable declaration of cv$temp$38$var182 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$39$var183))) - (Math.log(cv$temp$39$var183) * 0.5));
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
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
						
						// Processing random variable 189.
						// 
						// Looking for a path between Sample 67 and consumer Gaussian 189.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian194$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian194$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian194$global[1] = true;
							
							// Processing sample task 195 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$71$var188 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$71$var188 = memVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$70$var187's comment
								// Variable declaration of cv$temp$70$var187 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$71$var188))) - (Math.log(cv$temp$71$var188) * 0.5));
								
								// Recorded the probability of reaching sample task 195 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian194$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian194$global[1] = true;
							
							// Processing sample task 195 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$103$var188 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$103$var188 = memVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$102$var187's comment
								// Variable declaration of cv$temp$102$var187 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$103$var188))) - (Math.log(cv$temp$103$var188) * 0.5));
								
								// Recorded the probability of reaching sample task 195 with the current configuration.
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
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian199$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian199$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian199$global[1] = true;
							
							// Processing sample task 200 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$135$var193 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$135$var193 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$134$var192's comment
								// Variable declaration of cv$temp$134$var192 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$135$var193))) - (Math.log(cv$temp$135$var193) * 0.5));
								
								// Recorded the probability of reaching sample task 200 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian199$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian199$global[1] = true;
							
							// Processing sample task 200 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$167$var193 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$167$var193 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$166$var192's comment
								// Variable declaration of cv$temp$166$var192 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$167$var193))) - (Math.log(cv$temp$167$var193) * 0.5));
								
								// Recorded the probability of reaching sample task 200 with the current configuration.
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 43.
					for(int index$sample49$21 = 0; index$sample49$21 < noStates; index$sample49$21 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample49Value22 = distribution$sample49[index$sample49$21];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample49Value22);
						
						// Variable declaration of cv$temp$1$var60 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var60 = m[index$sample49$21];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample49Value22) + ((cv$valuePos < cv$temp$1$var60.length)?Math.log(cv$temp$1$var60[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 184.
						// 
						// Looking for a path between Sample 67 and consumer Gaussian 184.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian189$global[1] = true;
							
							// Processing sample task 190 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$15$var183 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$15$var183 = cpuVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$14$var182's comment
								// Variable declaration of cv$temp$14$var182 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$15$var183))) - (Math.log(cv$temp$15$var183) * 0.5));
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian189$global[1] = true;
							
							// Processing sample task 190 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$47$var183 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$47$var183 = cpuVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$46$var182's comment
								// Variable declaration of cv$temp$46$var182 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$47$var183))) - (Math.log(cv$temp$47$var183) * 0.5));
								
								// Recorded the probability of reaching sample task 190 with the current configuration.
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
						
						// Processing random variable 189.
						// 
						// Looking for a path between Sample 67 and consumer Gaussian 189.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian194$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian194$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian194$global[1] = true;
							
							// Processing sample task 195 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$79$var188 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$79$var188 = memVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$78$var187's comment
								// Variable declaration of cv$temp$78$var187 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$79$var188))) - (Math.log(cv$temp$79$var188) * 0.5));
								
								// Recorded the probability of reaching sample task 195 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian194$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian194$global[1] = true;
							
							// Processing sample task 195 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$111$var188 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$111$var188 = memVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$110$var187's comment
								// Variable declaration of cv$temp$110$var187 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$111$var188))) - (Math.log(cv$temp$111$var188) * 0.5));
								
								// Recorded the probability of reaching sample task 195 with the current configuration.
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
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var180" with its value "1".
						guard$sample67gaussian199$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample67gaussian199$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian199$global[1] = true;
							
							// Processing sample task 200 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$143$var193 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$143$var193 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$142$var192's comment
								// Variable declaration of cv$temp$142$var192 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$143$var193))) - (Math.log(cv$temp$143$var193) * 0.5));
								
								// Recorded the probability of reaching sample task 200 with the current configuration.
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
						
						// Substituted "i$var56" with its value "1".
						// 
						// Substituted "i$var180" with its value "1".
						if(!guard$sample67gaussian199$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var180" with its value "1".
							guard$sample67gaussian199$global[1] = true;
							
							// Processing sample task 200 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$175$var193 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								double cv$temp$175$var193 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var180" with its value "1".
								// 
								// cv$temp$174$var192's comment
								// Variable declaration of cv$temp$174$var192 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
								// the output of Sample task 67.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$175$var193))) - (Math.log(cv$temp$175$var193) * 0.5));
								
								// Recorded the probability of reaching sample task 200 with the current configuration.
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
			int index$i$28 = (i$var56 - 1);
			
			// index$i$19's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$28" with its value "(i$var56 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var56 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var56 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var56 - 1)".
			if(((1 <= index$i$28) && !(index$i$28 == i$var56))) {
				// Enumerating the possible outputs of Categorical 61.
				for(int index$sample67$29 = 0; index$sample67$29 < noStates; index$sample67$29 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample67Value30 = distribution$sample67[(index$i$28 - 1)][index$sample67$29];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample67Value30);
					
					// Variable declaration of cv$temp$3$var60 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var60 = m[index$sample67$29];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample67Value30) + ((cv$valuePos < cv$temp$3$var60.length)?Math.log(cv$temp$3$var60[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 184.
					// 
					// Looking for a path between Sample 67 and consumer Gaussian 184.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample67gaussian189$global[i$var56] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample67gaussian189$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian189$global[i$var56] = true;
						
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$31$var183 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$31$var183 = cpuVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$30$var182's comment
							// Variable declaration of cv$temp$30$var182 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var56] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$31$var183))) - (Math.log(cv$temp$31$var183) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					if(!guard$sample67gaussian189$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian189$global[i$var56] = true;
						
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$63$var183 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							double cv$temp$63$var183 = cpuVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$62$var182's comment
							// Variable declaration of cv$temp$62$var182 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var56] - cpuMean[cv$valuePos]) / Math.sqrt(cv$temp$63$var183))) - (Math.log(cv$temp$63$var183) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					
					// Processing random variable 189.
					// 
					// Looking for a path between Sample 67 and consumer Gaussian 189.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample67gaussian194$global[i$var56] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample67gaussian194$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian194$global[i$var56] = true;
						
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$95$var188 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$95$var188 = memVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$94$var187's comment
							// Variable declaration of cv$temp$94$var187 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var56] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$95$var188))) - (Math.log(cv$temp$95$var188) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					if(!guard$sample67gaussian194$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian194$global[i$var56] = true;
						
						// Processing sample task 195 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$127$var188 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							double cv$temp$127$var188 = memVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$126$var187's comment
							// Variable declaration of cv$temp$126$var187 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 189 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((mem[i$var56] - memMean[cv$valuePos]) / Math.sqrt(cv$temp$127$var188))) - (Math.log(cv$temp$127$var188) * 0.5));
							
							// Recorded the probability of reaching sample task 195 with the current configuration.
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
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample67gaussian199$global[i$var56] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample67gaussian199$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian199$global[i$var56] = true;
						
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$159$var193 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$159$var193 = pageFaultsVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$158$var192's comment
							// Variable declaration of cv$temp$158$var192 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var56] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$159$var193))) - (Math.log(cv$temp$159$var193) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
					if(!guard$sample67gaussian199$global[i$var56]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample67gaussian199$global[i$var56] = true;
						
						// Processing sample task 200 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
						// the output of Sample task 67.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$191$var193 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							double cv$temp$191$var193 = pageFaultsVar[cv$valuePos];
							
							// Substituted "i$var180" with its value "i$var56".
							// 
							// cv$temp$190$var192's comment
							// Variable declaration of cv$temp$190$var192 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 194 which is consuming
							// the output of Sample task 67.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((pageFaults[i$var56] - pageFaultsMean[cv$valuePos]) / Math.sqrt(cv$temp$191$var193))) - (Math.log(cv$temp$191$var193) * 0.5));
							
							// Recorded the probability of reaching sample task 200 with the current configuration.
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
			int index$i$621_2 = (i$var56 + 1);
			if((index$i$621_2 < samples)) {
				// Processing sample task 67 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var61[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Processing random variable 61.
				// 
				// Looking for a path between Sample 67 and consumer Categorical 61.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 61.
					if((1 == i$var56)) {
						// Enumerating the possible arguments for Categorical 61.
						if(fixedFlag$sample49) {
							int index$var35$630_1 = st[0];
							
							// Substituted "i$var56" with its value "1".
							if(((0 <= index$var35$630_1) && (index$var35$630_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 43.
							for(int index$sample49$626 = 0; index$sample49$626 < noStates; index$sample49$626 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample49Value627's comment
								// Update the probability of sampling this value from the distribution value.
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample49[index$sample49$626]);
						}
					}
					int index$i$633 = (i$var56 - 1);
					
					// index$i$623's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$621_2" with its value "(i$var56 + 1)".
					// 
					// Substituted "index$i$621_2" with its value "(i$var56 + 1)".
					// 
					// Substituted "index$i$621_2" with its value "(i$var56 + 1)".
					// 
					// Substituted "index$i$621_2" with its value "(i$var56 + 1)".
					if((((1 <= index$i$633) && !(index$i$633 == i$var56)) && !(index$i$633 == index$i$621_2))) {
						// Enumerating the possible outputs of Categorical 61.
						for(int index$sample67$634 = 0; index$sample67$634 < noStates; index$sample67$634 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample67Value635's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample67[(index$i$633 - 1)][index$sample67$634]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$temp$196$var60's comment
					// Variable declaration of cv$temp$196$var60 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 61.
					// 
					// Looking for a path between Sample 67 and consumer Categorical 61.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var61, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = distribution$sample67[(index$i$621_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var61[cv$i] / cv$reachedDistributionProbability);
					
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
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var62$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample67[(i$var56 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var62$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var62$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var62$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var62$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var62$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 87 drawn from Gaussian 70. Inference was performed using Metropolis-Hastings.
	private final void sample87(int var81) {
		// The original value of the sample
		double cv$originalValue = cpuMean[var81];
		
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
			// Substituted "cv$temp$1$var68" with its value "8.6".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample49) {
					if((var81 == st[0])) {
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 87.
						int var134 = st[0];
						
						// Substituted "i$var180" with its value "0".
						if(((0 <= var134) && (var134 < noStates))) {
							// Variable declaration of cv$temp$3$var183 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var180" with its value "0".
							double cv$temp$3$var183 = cpuVar[st[0]];
							
							// Substituted "i$var180" with its value "0".
							// 
							// cv$temp$2$var182's comment
							// Variable declaration of cv$temp$2$var182 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					// Substituted "index$sample49$3" with its value "var81".
					double cv$probabilitySample49Value4 = distribution$sample49[var81];
					
					// Variable declaration of cv$temp$9$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample49$3" with its value "var81".
					double cv$temp$9$var183 = cpuVar[var81];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$8$var182's comment
					// Variable declaration of cv$temp$8$var182 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(cv$temp$9$var183)))) - (Math.log(cv$temp$9$var183) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$9$var183's comment
						// Variable declaration of cv$temp$9$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 87.
						// 
						// Substituted "index$sample49$3" with its value "var81".
						// 
						// cv$temp$9$var183's comment
						// Variable declaration of cv$temp$9$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 87.
						// 
						// Substituted "index$sample49$3" with its value "var81".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
				if(fixedFlag$sample67) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var81 == st[i$var180])) {
						// Processing sample task 190 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var134 = st[i$var180];
						if(((0 <= var134) && (var134 < noStates))) {
							// Variable declaration of cv$temp$21$var183 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var183 = cpuVar[st[i$var180]];
							
							// cv$temp$20$var182's comment
							// Variable declaration of cv$temp$20$var182 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$21$var183))) - (Math.log(cv$temp$21$var183) * 0.5));
							
							// Recorded the probability of reaching sample task 190 with the current configuration.
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
					// Substituted "i$var56" with its value "i$var180".
					// 
					// Substituted "index$sample67$12" with its value "var81".
					double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var81];
					
					// Variable declaration of cv$temp$27$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample67$12" with its value "var81".
					double cv$temp$27$var183 = cpuVar[var81];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$26$var182's comment
					// Variable declaration of cv$temp$26$var182 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$originalValue) / Math.sqrt(cv$temp$27$var183)))) - (Math.log(cv$temp$27$var183) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 190 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// cv$temp$27$var183's comment
						// Variable declaration of cv$temp$27$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 87.
						// 
						// Substituted "index$sample67$12" with its value "var81".
						// 
						// cv$temp$27$var183's comment
						// Variable declaration of cv$temp$27$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
						// the output of Sample task 87.
						// 
						// Substituted "index$sample67$12" with its value "var81".
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
		cpuMean[var81] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var68" with its value "8.6".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample49) {
				if((var81 == st[0])) {
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					int var134 = st[0];
					
					// Substituted "i$var180" with its value "0".
					if(((0 <= var134) && (var134 < noStates))) {
						// Variable declaration of cv$temp$3$var183 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var180" with its value "0".
						double cv$temp$3$var183 = cpuVar[st[0]];
						
						// Substituted "i$var180" with its value "0".
						// 
						// cv$temp$2$var182's comment
						// Variable declaration of cv$temp$2$var182 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var183))) - (Math.log(cv$temp$3$var183) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				// Substituted "index$sample49$3" with its value "var81".
				double cv$probabilitySample49Value4 = distribution$sample49[var81];
				
				// Variable declaration of cv$temp$9$var183 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
				// the output of Sample task 87.
				// 
				// Substituted "index$sample49$3" with its value "var81".
				double cv$temp$9$var183 = cpuVar[var81];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 190 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var180" with its value "0".
				// 
				// cv$temp$8$var182's comment
				// Variable declaration of cv$temp$8$var182 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample49Value4) + DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var183)))) - (Math.log(cv$temp$9$var183) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample49Value4), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// Substituted "i$var180" with its value "0".
					// 
					// cv$temp$9$var183's comment
					// Variable declaration of cv$temp$9$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample49$3" with its value "var81".
					// 
					// cv$temp$9$var183's comment
					// Variable declaration of cv$temp$9$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample49$3" with its value "var81".
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
					else
						cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int i$var180 = 1; i$var180 < samples; i$var180 += 1) {
			if(fixedFlag$sample67) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var81 == st[i$var180])) {
					// Processing sample task 190 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var134 = st[i$var180];
					if(((0 <= var134) && (var134 < noStates))) {
						// Variable declaration of cv$temp$21$var183 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$temp$21$var183 = cpuVar[st[i$var180]];
						
						// cv$temp$20$var182's comment
						// Variable declaration of cv$temp$20$var182 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$21$var183))) - (Math.log(cv$temp$21$var183) * 0.5));
						
						// Recorded the probability of reaching sample task 190 with the current configuration.
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
				// Substituted "i$var56" with its value "i$var180".
				// 
				// Substituted "index$sample67$12" with its value "var81".
				double cv$probabilitySample67Value13 = distribution$sample67[(i$var180 - 1)][var81];
				
				// Variable declaration of cv$temp$27$var183 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
				// the output of Sample task 87.
				// 
				// Substituted "index$sample67$12" with its value "var81".
				double cv$temp$27$var183 = cpuVar[var81];
				
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 190 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$26$var182's comment
				// Variable declaration of cv$temp$26$var182 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample67Value13) + DistributionSampling.logProbabilityGaussian(((cpu[i$var180] - cv$proposedValue) / Math.sqrt(cv$temp$27$var183)))) - (Math.log(cv$temp$27$var183) * 0.5));
				
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
				// Declaration comment was:
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 190 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample67Value13), 0.0);
				
				// Multiply (log space add) in the probability of the sample task to the overall probability
				// for this configuration of the source random variable.
				if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
					cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
				else {
					// If the second value is -infinity.
					// 
					// cv$temp$27$var183's comment
					// Variable declaration of cv$temp$27$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample67$12" with its value "var81".
					// 
					// cv$temp$27$var183's comment
					// Variable declaration of cv$temp$27$var183 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 184 which is consuming
					// the output of Sample task 87.
					// 
					// Substituted "index$sample67$12" with its value "var81".
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
			cpuMean[var81] = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var36$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		
		// Allocation of cv$var36$countGlobal for single threaded execution
		cv$var36$countGlobal = new double[cv$max];
		
		// Allocation of cv$var41$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var41$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$distributionAccumulator$var61
		// 
		// Allocation of cv$distributionAccumulator$var61 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 65. Initially set to the value
		// of putTask 40.
		cv$distributionAccumulator$var61 = new double[noStates];
		
		// Constructor for cv$var44$stateProbabilityGlobal
		// 
		// Allocation of cv$var44$stateProbabilityGlobal for single threaded execution
		cv$var44$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample49gaussian189$global
		// 
		// Allocation of guard$sample49gaussian189$global for single threaded execution
		guard$sample49gaussian189$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample49gaussian194$global
		// 
		// Allocation of guard$sample49gaussian194$global for single threaded execution
		guard$sample49gaussian194$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample49gaussian199$global
		// 
		// Allocation of guard$sample49gaussian199$global for single threaded execution
		guard$sample49gaussian199$global = new boolean[length$cpu_measured];
		
		// Allocation of cv$var62$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 65. Initially set to the value
		// of putTask 40.
		cv$var62$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample67gaussian189$global
		// 
		// Allocation of guard$sample67gaussian189$global for single threaded execution
		guard$sample67gaussian189$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample67gaussian194$global
		// 
		// Allocation of guard$sample67gaussian194$global for single threaded execution
		guard$sample67gaussian194$global = new boolean[length$cpu_measured];
		
		// Allocation of guard$sample67gaussian199$global for single threaded execution
		guard$sample67gaussian199$global = new boolean[length$cpu_measured];
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
			for(int var35 = 0; var35 < noStates; var35 += 1)
				m[var35] = new double[noStates];
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
		
		// Constructor for distribution$sample49
		distribution$sample49 = new double[noStates];
		
		// Constructor for distribution$sample67
		distribution$sample67 = new double[(length$cpu_measured - 1)][];
		for(int i$var56 = 1; i$var56 < length$cpu_measured; i$var56 += 1)
			distribution$sample67[(i$var56 - 1)] = new double[noStates];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		for(int i$var180 = 0; i$var180 < samples; i$var180 += 1) {
			if(!fixedFlag$sample190)
				cpu[i$var180] = ((Math.sqrt(cpuVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + cpuMean[st[i$var180]]);
			if(!fixedFlag$sample195)
				mem[i$var180] = ((Math.sqrt(memVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + memMean[st[i$var180]]);
			if(!fixedFlag$sample200)
				pageFaults[i$var180] = ((Math.sqrt(pageFaultsVar[st[i$var180]]) * DistributionSampling.sampleGaussian(RNG$)) + pageFaultsMean[st[i$var180]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample49) {
			for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample49's comment
				// Create local copy of variable probabilities.
				distribution$sample49[index$var43] = ((index$var43 < initialStateDistribution.length)?initialStateDistribution[index$var43]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample67 = distribution$sample67[(i$var56 - 1)];
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					// Zero the probability of each value
					cv$distribution$sample67[index$var61] = 0.0;
				
				// Iterate through possible values for var61's arguments.
				// 
				// Enumerating the possible arguments for Categorical 61.
				if((1 == i$var56)) {
					// Iterate through possible values for var61's arguments.
					// 
					// Enumerating the possible arguments for Categorical 61.
					if(fixedFlag$sample49) {
						int var35 = st[0];
						
						// Substituted "i$var56" with its value "1".
						if(((0 <= var35) && (var35 < noStates))) {
							// Substituted "i$var56" with its value "1".
							double[] var60 = m[st[0]];
							for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
								// Save the probability of each value
								cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + ((index$var61 < var60.length)?var60[index$var61]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 43.
						for(int index$sample49$2 = 0; index$sample49$2 < noStates; index$sample49$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample49Value3 = distribution$sample49[index$sample49$2];
							double[] var60 = m[index$sample49$2];
							for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
								// Save the probability of each value
								cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample49Value3 * ((index$var61 < var60.length)?var60[index$var61]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var56 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 61.
					for(int index$sample67$10 = 0; index$sample67$10 < noStates; index$sample67$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample67Value11 = distribution$sample67[(index$i$9 - 1)][index$sample67$10];
						double[] var60 = m[index$sample67$10];
						for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
							// Save the probability of each value
							cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] + (cv$probabilitySample67Value11 * ((index$var61 < var60.length)?var60[index$var61]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var61$sum = 0.0;
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					// sum the probability of each value
					cv$var61$sum = (cv$var61$sum + cv$distribution$sample67[index$var61]);
				for(int index$var61 = 0; index$var61 < noStates; index$var61 += 1)
					// Normalise the probability of each value
					cv$distribution$sample67[index$var61] = (cv$distribution$sample67[index$var61] / cv$var61$sum);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample39) {
				for(int var35 = 0; var35 < noStates; var35 += 1)
					sample39(var35);
			}
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample49)
				sample49();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample67) {
				for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
					sample67(i$var56);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample87) {
				for(int var81 = 0; var81 < noStates; var81 += 1)
					sample87(var81);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample105) {
				for(int var99 = 0; var99 < noStates; var99 += 1)
					sample105(var99);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample123) {
				for(int var117 = 0; var117 < noStates; var117 += 1)
					sample123(var117);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample140) {
				for(int var134 = 0; var134 < noStates; var134 += 1)
					sample140(var134);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample157) {
				for(int var151 = 0; var151 < noStates; var151 += 1)
					sample157(var151);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample174) {
				for(int var168 = 0; var168 < noStates; var168 += 1)
					sample174(var168);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample174) {
				for(int var168 = (noStates - 1); var168 >= 0; var168 -= 1)
					sample174(var168);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample157) {
				for(int var151 = (noStates - 1); var151 >= 0; var151 -= 1)
					sample157(var151);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample140) {
				for(int var134 = (noStates - 1); var134 >= 0; var134 -= 1)
					sample140(var134);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample123) {
				for(int var117 = (noStates - 1); var117 >= 0; var117 -= 1)
					sample123(var117);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample105) {
				for(int var99 = (noStates - 1); var99 >= 0; var99 -= 1)
					sample105(var99);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample87) {
				for(int var81 = (noStates - 1); var81 >= 0; var81 -= 1)
					sample87(var81);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample67) {
				for(int i$var56 = (samples - 1); i$var56 >= 1; i$var56 -= 1)
					sample67(i$var56);
			}
			if(!fixedFlag$sample49)
				sample49();
			if(!fixedFlag$sample46)
				sample46();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample39) {
				for(int var35 = (noStates - 1); var35 >= 0; var35 -= 1)
					sample39(var35);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var21 = 0; var21 < noStates; var21 += 1)
			v[var21] = 0.1;
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
		logProbability$var24 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var36 = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var43 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var44 = 0.0;
		logProbability$var61 = 0.0;
		if(!fixedProbFlag$sample67)
			logProbability$var62 = 0.0;
		logProbability$var70 = 0.0;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$var82 = 0.0;
		logProbability$var88 = 0.0;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample105)
			logProbability$var100 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample123)
			logProbability$var118 = 0.0;
		logProbability$var123 = 0.0;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample140)
			logProbability$var135 = 0.0;
		logProbability$var140 = 0.0;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample157)
			logProbability$var152 = 0.0;
		logProbability$var157 = 0.0;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample174)
			logProbability$var169 = 0.0;
		logProbability$var184 = 0.0;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var185 = 0.0;
		logProbability$var189 = 0.0;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample195)
			logProbability$var190 = 0.0;
		logProbability$var194 = 0.0;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample200)
			logProbability$var195 = 0.0;
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
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		if(fixedFlag$sample105)
			logProbabilityValue$sample105();
		if(fixedFlag$sample123)
			logProbabilityValue$sample123();
		if(fixedFlag$sample140)
			logProbabilityValue$sample140();
		if(fixedFlag$sample157)
			logProbabilityValue$sample157();
		if(fixedFlag$sample174)
			logProbabilityValue$sample174();
		logProbabilityValue$sample190();
		logProbabilityValue$sample195();
		logProbabilityValue$sample200();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample46();
		logProbabilityDistribution$sample49();
		logProbabilityDistribution$sample67();
		logProbabilityValue$sample87();
		logProbabilityValue$sample105();
		logProbabilityValue$sample123();
		logProbabilityValue$sample140();
		logProbabilityValue$sample157();
		logProbabilityValue$sample174();
		logProbabilityDistribution$sample190();
		logProbabilityDistribution$sample195();
		logProbabilityDistribution$sample200();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample46();
		logProbabilityValue$sample49();
		logProbabilityValue$sample67();
		logProbabilityValue$sample87();
		logProbabilityValue$sample105();
		logProbabilityValue$sample123();
		logProbabilityValue$sample140();
		logProbabilityValue$sample157();
		logProbabilityValue$sample174();
		logProbabilityValue$sample190();
		logProbabilityValue$sample195();
		logProbabilityValue$sample200();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			for(int var35 = 0; var35 < noStates; var35 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var35]);
		}
		if(!fixedFlag$sample46)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample49)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample67) {
			for(int i$var56 = 1; i$var56 < samples; i$var56 += 1)
				st[i$var56] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var56 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample87) {
			for(int var81 = 0; var81 < noStates; var81 += 1)
				cpuMean[var81] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample105) {
			for(int var99 = 0; var99 < noStates; var99 += 1)
				memMean[var99] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample123) {
			for(int var117 = 0; var117 < noStates; var117 += 1)
				pageFaultsMean[var117] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample140) {
			for(int var134 = 0; var134 < noStates; var134 += 1)
				cpuVar[var134] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample157) {
			for(int var151 = 0; var151 < noStates; var151 += 1)
				memVar[var151] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample174) {
			for(int var168 = 0; var168 < noStates; var168 += 1)
				pageFaultsVar[var168] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
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
			int cv$length1 = cpu.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cpu[cv$index1] = cpu_measured[cv$index1];
		}
		{
			// Deep copy between arrays
			int cv$length1 = mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				mem[cv$index1] = mem_measured[cv$index1];
		}
		int cv$length1 = pageFaults.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			pageFaults[cv$index1] = pageFaults_measured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n"
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