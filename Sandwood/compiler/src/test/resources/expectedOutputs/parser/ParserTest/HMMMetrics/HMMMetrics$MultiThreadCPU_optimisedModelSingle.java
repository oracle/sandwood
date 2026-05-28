package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics$CoreInterface {
	
	// Declare the variables for the model.
	private boolean[] constrainedFlag$sample113;
	private boolean[] constrainedFlag$sample130;
	private boolean[] constrainedFlag$sample147;
	private boolean[] constrainedFlag$sample164;
	private boolean[] constrainedFlag$sample30;
	private boolean constrainedFlag$sample36 = true;
	private boolean constrainedFlag$sample39 = true;
	private boolean[] constrainedFlag$sample57;
	private boolean[] constrainedFlag$sample77;
	private boolean[] constrainedFlag$sample95;
	private double[] cpu;
	private double[] cpuMean;
	private double[] cpuVar;
	private double[] cpu_measured;
	private double[] cv$distributionAccumulator$var55;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var35$countGlobal;
	private double[] cv$var38$stateProbabilityGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private double[] distribution$sample39;
	private double[][] distribution$sample57;
	private boolean fixedFlag$sample113 = false;
	private boolean fixedFlag$sample130 = false;
	private boolean fixedFlag$sample147 = false;
	private boolean fixedFlag$sample164 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample113 = false;
	private boolean fixedProbFlag$sample130 = false;
	private boolean fixedProbFlag$sample147 = false;
	private boolean fixedProbFlag$sample164 = false;
	private boolean fixedProbFlag$sample180 = false;
	private boolean fixedProbFlag$sample185 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample95 = false;
	private boolean[] guard$sample39gaussian179$global;
	private boolean[] guard$sample39gaussian184$global;
	private boolean[] guard$sample39gaussian189$global;
	private boolean[] guard$sample57gaussian179$global;
	private boolean[] guard$sample57gaussian184$global;
	private boolean[] guard$sample57gaussian189$global;
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
	private double logProbability$var112;
	private double logProbability$var129;
	private double logProbability$var146;
	private double logProbability$var163;
	private double logProbability$var179;
	private double logProbability$var184;
	private double logProbability$var189;
	private double logProbability$var30;
	private double logProbability$var38;
	private double logProbability$var56;
	private double logProbability$var76;
	private double logProbability$var94;
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

	// Getter for cpuMean.
	@Override
	public final double[] get$cpuMean() {
		return cpuMean;
	}

	// Setter for cpuMean.
	@Override
	public final void set$cpuMean(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of cpuMean including if probabilities need to
		// be updated.
		cpuMean = cv$value;
		
		// Unset the fixed probability flag for sample 77 as it depends on cpuMean.
		fixedProbFlag$sample77 = false;
		
		// Unset the fixed probability flag for sample 180 as it depends on cpuMean.
		fixedProbFlag$sample180 = false;
	}

	// Getter for cpuVar.
	@Override
	public final double[] get$cpuVar() {
		return cpuVar;
	}

	// Setter for cpuVar.
	@Override
	public final void set$cpuVar(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of cpuVar including if probabilities need to
		// be updated.
		cpuVar = cv$value;
		
		// Unset the fixed probability flag for sample 130 as it depends on cpuVar.
		fixedProbFlag$sample130 = false;
		
		// Unset the fixed probability flag for sample 180 as it depends on cpuVar.
		fixedProbFlag$sample180 = false;
	}

	// Getter for cpu_measured.
	@Override
	public final double[] get$cpu_measured() {
		return cpu_measured;
	}

	// Setter for cpu_measured.
	@Override
	public final void set$cpu_measured(double[] cv$value, boolean allocated$) {
		cpu_measured = cv$value;
	}

	// Getter for distribution$sample39.
	@Override
	public final double[] get$distribution$sample39() {
		return distribution$sample39;
	}

	// Setter for distribution$sample39.
	@Override
	public final void set$distribution$sample39(double[] cv$value, boolean allocated$) {
		distribution$sample39 = cv$value;
	}

	// Getter for distribution$sample57.
	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	// Setter for distribution$sample57.
	@Override
	public final void set$distribution$sample57(double[][] cv$value, boolean allocated$) {
		distribution$sample57 = cv$value;
	}

	// Getter for fixedFlag$sample113.
	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	// Setter for fixedFlag$sample113.
	@Override
	public final void set$fixedFlag$sample113(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample113 including if probabilities
		// need to be updated.
		fixedFlag$sample113 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
				// Substituted "fixedFlag$sample113" with its value "cv$value".
				constrainedFlag$sample113[index$constrainedFlag$sample113$1] = cv$value;
		}
		
		// Should the probability of sample 113 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample113" with its value "cv$value".
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample113" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample130.
	@Override
	public final boolean get$fixedFlag$sample130() {
		return fixedFlag$sample130;
	}

	// Setter for fixedFlag$sample130.
	@Override
	public final void set$fixedFlag$sample130(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample130 including if probabilities
		// need to be updated.
		fixedFlag$sample130 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
				// Substituted "fixedFlag$sample130" with its value "cv$value".
				constrainedFlag$sample130[index$constrainedFlag$sample130$1] = cv$value;
		}
		
		// Should the probability of sample 130 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample130" with its value "cv$value".
		fixedProbFlag$sample130 = (cv$value && fixedProbFlag$sample130);
		
		// Should the probability of sample 180 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample130" with its value "cv$value".
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	// Getter for fixedFlag$sample147.
	@Override
	public final boolean get$fixedFlag$sample147() {
		return fixedFlag$sample147;
	}

	// Setter for fixedFlag$sample147.
	@Override
	public final void set$fixedFlag$sample147(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample147 including if probabilities
		// need to be updated.
		fixedFlag$sample147 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
				// Substituted "fixedFlag$sample147" with its value "cv$value".
				constrainedFlag$sample147[index$constrainedFlag$sample147$1] = cv$value;
		}
		
		// Should the probability of sample 147 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample147" with its value "cv$value".
		fixedProbFlag$sample147 = (cv$value && fixedProbFlag$sample147);
		
		// Should the probability of sample 185 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample147" with its value "cv$value".
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	// Getter for fixedFlag$sample164.
	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	// Setter for fixedFlag$sample164.
	@Override
	public final void set$fixedFlag$sample164(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample164 including if probabilities
		// need to be updated.
		fixedFlag$sample164 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
				// Substituted "fixedFlag$sample164" with its value "cv$value".
				constrainedFlag$sample164[index$constrainedFlag$sample164$1] = cv$value;
		}
		
		// Should the probability of sample 164 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample164" with its value "cv$value".
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample164" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample30.
	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	// Setter for fixedFlag$sample30.
	@Override
	public final void set$fixedFlag$sample30(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample30 including if probabilities
		// need to be updated.
		fixedFlag$sample30 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
				// Substituted "fixedFlag$sample30" with its value "cv$value".
				constrainedFlag$sample30[index$constrainedFlag$sample30$1] = cv$value;
		}
		
		// Should the probability of sample 30 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		constrainedFlag$sample36 = (cv$value || constrainedFlag$sample36);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
	}

	// Getter for fixedFlag$sample39.
	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	// Setter for fixedFlag$sample39.
	@Override
	public final void set$fixedFlag$sample39(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample39 including if probabilities
		// need to be updated.
		fixedFlag$sample39 = cv$value;
		
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		constrainedFlag$sample39 = (cv$value || constrainedFlag$sample39);
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 180 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		
		// Should the probability of sample 185 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample39" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
				// Substituted "fixedFlag$sample57" with its value "cv$value".
				constrainedFlag$sample57[index$constrainedFlag$sample57$1] = cv$value;
		}
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 180 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
		
		// Should the probability of sample 185 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
		
		// Should the probability of sample 190 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
	}

	// Getter for fixedFlag$sample77.
	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	// Setter for fixedFlag$sample77.
	@Override
	public final void set$fixedFlag$sample77(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample77 including if probabilities
		// need to be updated.
		fixedFlag$sample77 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
				// Substituted "fixedFlag$sample77" with its value "cv$value".
				constrainedFlag$sample77[index$constrainedFlag$sample77$1] = cv$value;
		}
		
		// Should the probability of sample 77 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample77" with its value "cv$value".
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
		
		// Should the probability of sample 180 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample77" with its value "cv$value".
		fixedProbFlag$sample180 = (cv$value && fixedProbFlag$sample180);
	}

	// Getter for fixedFlag$sample95.
	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	// Setter for fixedFlag$sample95.
	@Override
	public final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample95 including if probabilities
		// need to be updated.
		fixedFlag$sample95 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
				// Substituted "fixedFlag$sample95" with its value "cv$value".
				constrainedFlag$sample95[index$constrainedFlag$sample95$1] = cv$value;
		}
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 185 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample185 = (cv$value && fixedProbFlag$sample185);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of initialStateDistribution including if probabilities
		// need to be updated.
		initialStateDistribution = cv$value;
		
		// Unset the fixed probability flag for sample 36 as it depends on initialStateDistribution.
		fixedProbFlag$sample36 = false;
		
		// Unset the fixed probability flag for sample 39 as it depends on initialStateDistribution.
		fixedProbFlag$sample39 = false;
	}

	// Getter for length$cpu_measured.
	@Override
	public final int get$length$cpu_measured() {
		return length$cpu_measured;
	}

	// Setter for length$cpu_measured.
	@Override
	public final void set$length$cpu_measured(int cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		m = cv$value;
		
		// Unset the fixed probability flag for sample 30 as it depends on m.
		fixedProbFlag$sample30 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on m.
		fixedProbFlag$sample57 = false;
	}

	// Getter for mem.
	@Override
	public final double[] get$mem() {
		return mem;
	}

	// Getter for memMean.
	@Override
	public final double[] get$memMean() {
		return memMean;
	}

	// Setter for memMean.
	@Override
	public final void set$memMean(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of memMean including if probabilities need to
		// be updated.
		memMean = cv$value;
		
		// Unset the fixed probability flag for sample 95 as it depends on memMean.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 185 as it depends on memMean.
		fixedProbFlag$sample185 = false;
	}

	// Getter for memVar.
	@Override
	public final double[] get$memVar() {
		return memVar;
	}

	// Setter for memVar.
	@Override
	public final void set$memVar(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of memVar including if probabilities need to
		// be updated.
		memVar = cv$value;
		
		// Unset the fixed probability flag for sample 147 as it depends on memVar.
		fixedProbFlag$sample147 = false;
		
		// Unset the fixed probability flag for sample 185 as it depends on memVar.
		fixedProbFlag$sample185 = false;
	}

	// Getter for mem_measured.
	@Override
	public final double[] get$mem_measured() {
		return mem_measured;
	}

	// Setter for mem_measured.
	@Override
	public final void set$mem_measured(double[] cv$value, boolean allocated$) {
		mem_measured = cv$value;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
		noStates = cv$value;
	}

	// Getter for pageFaults.
	@Override
	public final double[] get$pageFaults() {
		return pageFaults;
	}

	// Getter for pageFaultsMean.
	@Override
	public final double[] get$pageFaultsMean() {
		return pageFaultsMean;
	}

	// Setter for pageFaultsMean.
	@Override
	public final void set$pageFaultsMean(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of pageFaultsMean including if probabilities
		// need to be updated.
		pageFaultsMean = cv$value;
		
		// Unset the fixed probability flag for sample 113 as it depends on pageFaultsMean.
		fixedProbFlag$sample113 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on pageFaultsMean.
		fixedProbFlag$sample190 = false;
	}

	// Getter for pageFaultsVar.
	@Override
	public final double[] get$pageFaultsVar() {
		return pageFaultsVar;
	}

	// Setter for pageFaultsVar.
	@Override
	public final void set$pageFaultsVar(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of pageFaultsVar including if probabilities
		// need to be updated.
		pageFaultsVar = cv$value;
		
		// Unset the fixed probability flag for sample 164 as it depends on pageFaultsVar.
		fixedProbFlag$sample164 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on pageFaultsVar.
		fixedProbFlag$sample190 = false;
	}

	// Getter for pageFaults_measured.
	@Override
	public final double[] get$pageFaults_measured() {
		return pageFaults_measured;
	}

	// Setter for pageFaults_measured.
	@Override
	public final void set$pageFaults_measured(double[] cv$value, boolean allocated$) {
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
	public final void set$st(int[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		st = cv$value;
		
		// Unset the fixed probability flag for sample 39 as it depends on st.
		fixedProbFlag$sample39 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on st.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 180 as it depends on st.
		fixedProbFlag$sample180 = false;
		
		// Unset the fixed probability flag for sample 185 as it depends on st.
		fixedProbFlag$sample185 = false;
		
		// Unset the fixed probability flag for sample 190 as it depends on st.
		fixedProbFlag$sample190 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Pick a value from the distribution for the unconditioned variable from sample113
	private final void drawValueSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample130
	private final void drawValueSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample147
	private final void drawValueSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample164
	private final void drawValueSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	// Pick a value from the distribution for the unconditioned variable from sample30
	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var29]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample36
	private final void drawValueSample36() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int i$var50) {
		st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample77
	private final void drawValueSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample95
	private final void drawValueSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 113 drawn from Gaussian 100. Inference was performed using Metropolis-Hastings.
	private final void inferSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		constrainedFlag$sample113[var111] = false;
		
		// The original value of the sample
		double cv$originalValue = pageFaultsMean[var111];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						int var162 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var162) && (var162 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var187 = pageFaultsVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "index$sample39$4" with its value "var111".
					double cv$probabilitySample39Value5 = distribution$sample39[var111];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 113.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double var187 = pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							// Constructing a random variable input for use later.
							double var187 = pageFaultsVar[st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var111];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double var187 = pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample113[var111]) {
			// Guards to ensure that pageFaultsMean is only updated when there is a valid path.
			pageFaultsMean[var111] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						int var162 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var162) && (var162 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var187 = pageFaultsVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "index$sample39$4" with its value "var111".
					double cv$probabilitySample39Value5 = distribution$sample39[var111];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 113.
					// 
					// Substituted "index$sample39$4" with its value "var111".
					double var187 = pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 113.
						// 
						// Substituted "index$sample39$4" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var111 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample113[var111] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = st[i$var174];
						if(((0 <= var162) && (var162 < noStates))) {
							// Constructing a random variable input for use later.
							double var187 = pageFaultsVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var111];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample113[var111] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var111".
					double var187 = pageFaultsVar[var111];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var111".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that pageFaultsMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				pageFaultsMean[var111] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 130 drawn from InverseGamma 117. Inference was performed using Metropolis-Hastings.
	private final void inferSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		constrainedFlag$sample130[var128] = false;
		
		// The original value of the sample
		double cv$originalValue = cpuVar[var128];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 130.
						int var75 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var75) && (var75 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var128".
					double cv$probabilitySample39Value5 = distribution$sample39[var128];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 130.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var128];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample130[var128]) {
			// Guards to ensure that cpuVar is only updated when there is a valid path.
			cpuVar[var128] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 130.
						int var75 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var75) && (var75 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var128".
					double cv$probabilitySample39Value5 = distribution$sample39[var128];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 130.
					// 
					// Substituted "index$sample39$4" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var128 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample130[var128] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = st[i$var174];
						if(((0 <= var75) && (var75 < noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var128];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample130[var128] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var128".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that cpuVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				cpuVar[var128] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 147 drawn from InverseGamma 134. Inference was performed using Metropolis-Hastings.
	private final void inferSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		constrainedFlag$sample147[var145] = false;
		
		// The original value of the sample
		double cv$originalValue = memVar[var145];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 147.
						int var93 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var93) && (var93 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var145".
					double cv$probabilitySample39Value5 = distribution$sample39[var145];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 147.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var145];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample147[var145]) {
			// Guards to ensure that memVar is only updated when there is a valid path.
			memVar[var145] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 147.
						int var93 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var93) && (var93 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var145".
					double cv$probabilitySample39Value5 = distribution$sample39[var145];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 147.
					// 
					// Substituted "index$sample39$4" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var145 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample147[var145] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = st[i$var174];
						if(((0 <= var93) && (var93 < noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var145];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample147[var145] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var145".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that memVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				memVar[var145] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 164 drawn from InverseGamma 151. Inference was performed using Metropolis-Hastings.
	private final void inferSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		constrainedFlag$sample164[var162] = false;
		
		// The original value of the sample
		double cv$originalValue = pageFaultsVar[var162];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 164.
						int var111 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var111) && (var111 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "index$sample39$4" with its value "var162".
					double cv$probabilitySample39Value5 = distribution$sample39[var162];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 164.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							// Set the current value to the current state of the tree.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var162];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample164[var162]) {
			// Guards to ensure that pageFaultsVar is only updated when there is a valid path.
			pageFaultsVar[var162] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 164.
						int var111 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var111) && (var111 < noStates))) {
							// Substituted "i$var174" with its value "0".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "index$sample39$4" with its value "var162".
					double cv$probabilitySample39Value5 = distribution$sample39[var162];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 164.
					// 
					// Substituted "index$sample39$4" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
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
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var162 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample164[var162] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = st[i$var174];
						if(((0 <= var111) && (var111 < noStates))) {
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var162];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample164[var162] = true;
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var162".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					
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
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
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
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that pageFaultsVar is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				pageFaultsVar[var162] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		constrainedFlag$sample30[var29] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample57) {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 30 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 == st[0])) {
						// Processing sample task 57 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample30[var29] = true;
						
						// Increment the sample counter with the value sampled by sample task 57 of random
						// variable var55
						// 
						// Substituted "i$var50" with its value "1".
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
					}
				} else {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample30[var29] = true;
					
					// Increment the sample counter with the value sampled by sample task 57 of random
					// variable var55
					// 
					// cv$probabilitySample39Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample39$3" with its value "var29".
					cv$countLocal[st[1]] = (cv$countLocal[st[1]] + distribution$sample39[var29]);
				}
			}
			for(int i$var50 = 2; i$var50 < samples; i$var50 += 1) {
				if((var29 == st[(i$var50 - 1)])) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample30[var29] = true;
					
					// Increment the sample counter with the value sampled by sample task 57 of random
					// variable var55
					cv$countLocal[st[i$var50]] = (cv$countLocal[st[i$var50]] + 1.0);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 30 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 == st[0])) {
						// Processing sample task 57 of consumer random variable null.
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
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample57[0][cv$loopIndex]);
					}
				} else {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample39Value33's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample39$32" with its value "var29".
					double cv$distributionProbability = distribution$sample39[var29];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						// Substituted "i$var50" with its value "1".
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				int index$i$40 = (i$var50 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$40)) {
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$probabilitySample57Value42's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// Substituted "index$sample57$41" with its value "var29".
					double cv$distributionProbability = distribution$sample57[(index$i$40 - 1)][var29];
					
					// Merge the distribution probabilities into the count
					// 
					// Get the length of the array
					for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[(i$var50 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		if(constrainedFlag$sample30[var29])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Dirichlet 34. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample36() {
		constrainedFlag$sample36 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var35$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample39) {
			// Processing random variable 37.
			// 
			// Processing sample task 39 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample36 = true;
			
			// Increment the sample counter with the value sampled by sample task 39 of random
			// variable var37
			// 
			// A local reference to the scratch space.
			cv$var35$countGlobal[st[0]] = (cv$var35$countGlobal[st[0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing sample task 39 of consumer random variable null.
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
				cv$var35$countGlobal[cv$loopIndex] = (cv$var35$countGlobal[cv$loopIndex] + distribution$sample39[cv$loopIndex]);
		}
		if(constrainedFlag$sample36)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var35$countGlobal, initialStateDistribution, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Categorical 37. Inference was performed using variable
	// marginalization.
	private final void inferSample39() {
		constrainedFlag$sample39 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample57 && (1 < samples))) {
				// Looking for a path between Sample 39 and consumer Categorical 55.
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample39 = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 39.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var54 = m[cv$valuePos];
					
					// Substituted "i$var50" with its value "1".
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[1]) && (st[1] < noStates)) && (0.0 <= var54[st[1]])) && (var54[st[1]] <= 1.0))?Math.log(var54[st[1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 57 with the current configuration.
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
				// Processing random variable 178.
				// 
				// Looking for a path between Sample 39 and consumer Gaussian 178.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample39gaussian179$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample39gaussian179$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian179$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = cpuVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 180 with the current configuration.
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
				
				// Substituted "i$var174" with its value "0".
				if(!guard$sample39gaussian179$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian179$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var177 = cpuVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 180 with the current configuration.
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
				
				// Processing random variable 183.
				// 
				// Looking for a path between Sample 39 and consumer Gaussian 183.
				// 
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample39gaussian184$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample39gaussian184$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian184$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = memVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 185 with the current configuration.
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
				
				// Substituted "i$var174" with its value "0".
				if(!guard$sample39gaussian184$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian184$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var182 = memVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 185 with the current configuration.
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
				guard$sample39gaussian189$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample39gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian189$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						
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
				
				// Substituted "i$var174" with its value "0".
				if(!guard$sample39gaussian189$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample39gaussian189$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample39 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
					// the output of Sample task 39.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						double var187 = pageFaultsVar[cv$valuePos];
						
						// Substituted "i$var174" with its value "0".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
						// the output of Sample task 39.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[0] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						
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
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample57 && (1 < samples))) {
				// Looking for a path between Sample 39 and consumer Categorical 55.
				// Processing sample task 57 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 39.
				// 
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
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, 1.0, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var50" with its value "1".
				double[] cv$sampleDistribution = distribution$sample57[0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					
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
			cv$var38$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample39) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var38$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var38$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((cv$var38$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample39[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample39[cv$indexName] = Math.exp((cv$var38$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				distribution$sample39[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 55. Inference was performed using variable
	// marginalization.
	private final void inferSample57(int i$var50) {
		constrainedFlag$sample57[(i$var50 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 55.
		// 
		// Enumerating the possible arguments for Categorical 55.
		if((1 == i$var50)) {
			// Exploring all the possible state counts for random variable 55.
			// 
			// Enumerating the possible arguments for Categorical 55.
			if(fixedFlag$sample39) {
				int var29 = st[0];
				
				// Substituted "i$var50" with its value "1".
				if(((0 <= var29) && (var29 < noStates)))
					// variable marginalization
					// 
					// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 37.
				if((0 < noStates))
					// variable marginalization
					cv$numStates = noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noStates)) {
			int index$i$10 = (i$var50 - 1);
			
			// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$10" with its value "(i$var50 - 1)".
			if(((1 <= index$i$10) && !(index$i$10 == i$var50)))
				// variable marginalization
				cv$numStates = noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 55 creating
			// sample task 57.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 55.
			if((1 == i$var50)) {
				// Enumerating the possible arguments for Categorical 55.
				if(fixedFlag$sample39) {
					int var29 = st[0];
					
					// Substituted "i$var50" with its value "1".
					if(((0 <= var29) && (var29 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var50" with its value "1".
						double[] var54 = m[st[0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var54[cv$valuePos])) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Processing random variable 178.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 178.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian179$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var177 = cpuVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 180 with the current configuration.
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
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var177 = cpuVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 180 with the current configuration.
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
						
						// Processing random variable 183.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 183.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian184$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var182 = memVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 185 with the current configuration.
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
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var182 = memVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 185 with the current configuration.
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
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
							// the output of Sample task 57.
							// 
							// Enumerating the possible arguments for the variable Gaussian 188 which is consuming
							// the output of Sample task 57.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var187 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var187 = pageFaultsVar[cv$valuePos];
								
								// Substituted "i$var174" with its value "1".
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 37.
					for(int index$sample39$18 = 0; index$sample39$18 < noStates; index$sample39$18 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample39Value19 = distribution$sample39[index$sample39$18];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value19);
						
						// Constructing a random variable input for use later.
						double[] var54 = m[index$sample39$18];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value19) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 178.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 178.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian179$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var177 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian179$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian179$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var177 = cpuVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 180 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[1] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Processing random variable 183.
						// 
						// Looking for a path between Sample 57 and consumer Gaussian 183.
						// 
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian184$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var182 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian184$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian184$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var182 = memVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 185 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[1] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "i$var174" with its value "1".
						guard$sample57gaussian189$global[1] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var187 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						
						// Substituted "i$var50" with its value "1".
						// 
						// Substituted "i$var174" with its value "1".
						if(!guard$sample57gaussian189$global[1]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							// 
							// Substituted "i$var174" with its value "1".
							guard$sample57gaussian189$global[1] = true;
							
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "i$var50" with its value "1".
							constrainedFlag$sample57[0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var187 = pageFaultsVar[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 190 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// This value is not used before it is set again, so removing the value declaration.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "i$var174" with its value "1".
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[1] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$i$25 = (i$var50 - 1);
			
			// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			// 
			// Substituted "index$i$25" with its value "(i$var50 - 1)".
			if(((1 <= index$i$25) && !(index$i$25 == i$var50))) {
				// Enumerating the possible outputs of Categorical 55.
				for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample57Value27 = distribution$sample57[(index$i$25 - 1)][index$sample57$26];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
					
					// Constructing a random variable input for use later.
					double[] var54 = m[index$sample57$26];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 178.
					// 
					// Looking for a path between Sample 57 and consumer Gaussian 178.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample57gaussian179$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample57gaussian179$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian179$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian179$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian179$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var177 = cpuVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 180 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var50] - cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Processing random variable 183.
					// 
					// Looking for a path between Sample 57 and consumer Gaussian 183.
					// 
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample57gaussian184$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample57gaussian184$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian184$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian184$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian184$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var182 = memVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 185 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var50] - memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample57gaussian189$global[i$var50] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample57gaussian189$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian189$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample57gaussian189$global[i$var50]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample57gaussian189$global[i$var50] = true;
						
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[(i$var50 - 1)] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var187 = pageFaultsVar[cv$valuePos];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 190 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "i$var174" with its value "i$var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var50] - pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$i$618_2 = (i$var50 + 1);
			if((index$i$618_2 < samples)) {
				// Processing sample task 57 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 55 which is consuming
				// the output of Sample task 57.
				// 
				// Processing random variable 55.
				// 
				// Looking for a path between Sample 57 and consumer Categorical 55.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 55.
					if((1 == i$var50)) {
						// Enumerating the possible arguments for Categorical 55.
						if(fixedFlag$sample39) {
							int index$var29$627_1 = st[0];
							
							// Substituted "i$var50" with its value "1".
							if(((0 <= index$var29$627_1) && (index$var29$627_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 37.
							for(int index$sample39$623 = 0; index$sample39$623 < noStates; index$sample39$623 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample39Value624's comment
								// Update the probability of sampling this value from the distribution value.
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample39[index$sample39$623]);
						}
					}
					int index$i$630 = (i$var50 - 1);
					
					// index$i$620's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					// 
					// Substituted "index$i$618_2" with its value "(i$var50 + 1)".
					if((((1 <= index$i$630) && !(index$i$630 == i$var50)) && !(index$i$630 == index$i$618_2))) {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$631 = 0; index$sample57$631 < noStates; index$sample57$631 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample57Value632's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[(index$i$630 - 1)][index$sample57$631]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 55.
					// 
					// Looking for a path between Sample 57 and consumer Categorical 55.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = distribution$sample57[(index$i$618_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					
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
			cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample57[(i$var50 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample57[(i$var50 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var56$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 77 drawn from Gaussian 64. Inference was performed using Metropolis-Hastings.
	private final void inferSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		constrainedFlag$sample77[var75] = false;
		
		// The original value of the sample
		double cv$originalValue = cpuMean[var75];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						int var128 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var128) && (var128 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var177 = cpuVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var75".
					double cv$probabilitySample39Value5 = distribution$sample39[var75];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 77.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double var177 = cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							// Constructing a random variable input for use later.
							double var177 = cpuVar[st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var75];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double var177 = cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample77[var75]) {
			// Guards to ensure that cpuMean is only updated when there is a valid path.
			cpuMean[var75] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						int var128 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var128) && (var128 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var177 = cpuVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var75".
					double cv$probabilitySample39Value5 = distribution$sample39[var75];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
					// the output of Sample task 77.
					// 
					// Substituted "index$sample39$4" with its value "var75".
					double var177 = cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 178 which is consuming
						// the output of Sample task 77.
						// 
						// Substituted "index$sample39$4" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var75 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample77[var75] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = st[i$var174];
						if(((0 <= var128) && (var128 < noStates))) {
							// Constructing a random variable input for use later.
							double var177 = cpuVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 180 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var75];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample77[var75] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var75".
					double var177 = cpuVar[var75];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 180 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var75".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that cpuMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				cpuMean[var75] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Gaussian 82. Inference was performed using Metropolis-Hastings.
	private final void inferSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		constrainedFlag$sample95[var93] = false;
		
		// The original value of the sample
		double cv$originalValue = memMean[var93];
		
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
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						int var145 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var145) && (var145 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var182 = memVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var93".
					double cv$probabilitySample39Value5 = distribution$sample39[var93];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 95.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double var182 = memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							// Constructing a random variable input for use later.
							double var182 = memVar[st[i$var174]];
							
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var93];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double var182 = memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Set the current value to the current state of the tree.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample95[var93]) {
			// Guards to ensure that memMean is only updated when there is a valid path.
			memMean[var93] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples)) {
				if(fixedFlag$sample39) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == st[0])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						int var145 = st[0];
						
						// Substituted "i$var174" with its value "0".
						if(((0 <= var145) && (var145 < noStates))) {
							// Constructing a random variable input for use later.
							// 
							// Substituted "i$var174" with its value "0".
							double var182 = memVar[st[0]];
							
							// Substituted "i$var174" with its value "0".
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "index$sample39$4" with its value "var93".
					double cv$probabilitySample39Value5 = distribution$sample39[var93];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
					// the output of Sample task 95.
					// 
					// Substituted "index$sample39$4" with its value "var93".
					double var182 = memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "i$var174" with its value "0".
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 183 which is consuming
						// the output of Sample task 95.
						// 
						// Substituted "index$sample39$4" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int i$var174 = 1; i$var174 < samples; i$var174 += 1) {
				if(fixedFlag$sample57) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var93 == st[i$var174])) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample95[var93] = true;
						
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = st[i$var174];
						if(((0 <= var145) && (var145 < noStates))) {
							// Constructing a random variable input for use later.
							double var182 = memVar[st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 185 with the current configuration.
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
					// Substituted "i$var50" with its value "i$var174".
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double cv$probabilitySample57Value14 = distribution$sample57[(i$var174 - 1)][var93];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample95[var93] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "index$sample57$13" with its value "var93".
					double var182 = memVar[var93];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 185 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "index$sample57$13" with its value "var93".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that memMean is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				memMean[var93] = cv$originalValue;
		}
	}

	// Calculate the probability of the samples represented by sample180 using probability
	// distributions.
	private final void logProbabilityDistribution$sample180() {
		// Determine if we need to calculate the values for sample task 180 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample180) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 180 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = cpu[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 178.
					// 
					// Enumerating the possible arguments for Gaussian 178.
					if(fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var75 = st[0];
							
							// Substituted "i$var174" with its value "0".
							if(((0 <= var75) && (var75 < noStates))) {
								// Substituted "i$var174" with its value "0".
								double var177 = cpuVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[0]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var177 = cpuVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample39$3]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 178.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 178.
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var174])) {
							int var75 = st[i$var174];
							if(((0 <= var75) && (var75 < noStates))) {
								double var177 = cpuVar[st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var177 = cpuVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - cpuMean[index$sample57$43]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var179 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var179);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var179);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var179);
		}
	}

	// Calculate the probability of the samples represented by sample185 using probability
	// distributions.
	private final void logProbabilityDistribution$sample185() {
		// Determine if we need to calculate the values for sample task 185 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample185) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 185 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = mem[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 183.
					// 
					// Enumerating the possible arguments for Gaussian 183.
					if(fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var93 = st[0];
							
							// Substituted "i$var174" with its value "0".
							if(((0 <= var93) && (var93 < noStates))) {
								// Substituted "i$var174" with its value "0".
								double var182 = memVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[0]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var182 = memVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample39$3]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 183.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 183.
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var174])) {
							int var93 = st[i$var174];
							if(((0 <= var93) && (var93 < noStates))) {
								double var182 = memVar[st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var182 = memVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - memMean[index$sample57$43]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var184 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var184);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var184);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var184);
		}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 190 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				double cv$sampleValue = pageFaults[i$var174];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i$var174)) {
					// Enumerating the possible arguments for Gaussian 188.
					// 
					// Enumerating the possible arguments for Gaussian 188.
					if(fixedFlag$sample39) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[0])) {
							int var111 = st[0];
							
							// Substituted "i$var174" with its value "0".
							if(((0 <= var111) && (var111 < noStates))) {
								// Substituted "i$var174" with its value "0".
								double var187 = pageFaultsVar[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "i$var174" with its value "0".
								cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[0]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$3 = 0; index$sample39$3 < noStates; index$sample39$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value4 = distribution$sample39[index$sample39$3];
							double var187 = pageFaultsVar[index$sample39$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample39$3]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Gaussian 188.
				if((1 <= i$var174)) {
					// Enumerating the possible arguments for Gaussian 188.
					if(fixedFlag$sample57) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[i$var174])) {
							int var111 = st[i$var174];
							if(((0 <= var111) && (var111 < noStates))) {
								double var187 = pageFaultsVar[st[i$var174]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								
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
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample57$43 = 0; index$sample57$43 < noStates; index$sample57$43 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var50" with its value "i$var174".
							double cv$probabilitySample57Value44 = distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var187 = pageFaultsVar[index$sample57$43];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - pageFaultsMean[index$sample57$43]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var189 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var189);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var189);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var189);
		}
	}

	// Calculate the probability of the samples represented by sample39 using probability
	// distributions.
	private final void logProbabilityDistribution$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample39) {
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				logProbability$var38 = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample39" with its value "true".
				fixedProbFlag$sample39 = fixedFlag$sample36;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample39)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample57 using probability
	// distributions.
	private final void logProbabilityDistribution$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample57) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var50];
					
					// Enumerating the possible arguments for Categorical 55.
					if((1 == i$var50)) {
						// Enumerating the possible arguments for Categorical 55.
						if(fixedFlag$sample39) {
							int var29 = st[0];
							
							// Substituted "i$var50" with its value "1".
							if(((0 <= var29) && (var29 < noStates))) {
								// Substituted "i$var50" with its value "1".
								double[] var54 = m[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 37.
							for(int index$sample39$4 = 0; index$sample39$4 < noStates; index$sample39$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample39Value5 = distribution$sample39[index$sample39$4];
								double[] var54 = m[index$sample39$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var50 - 1)".
					if((2 <= i$var50)) {
						int var29 = st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < noStates))) {
							double[] var54 = m[st[(i$var50 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				}
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the random variable instance probability
					// 
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Accumulator for probabilities of instances of the random variable
					logProbability$var56 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample57" with its value "true".
				fixedProbFlag$sample57 = (fixedFlag$sample30 && fixedFlag$sample39);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample57)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Calculate the probability of the samples represented by sample113 using sampled
	// values.
	private final void logProbabilityValue$sample113() {
		// Determine if we need to calculate the values for sample task 113 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample113) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var111 = 0; var111 < noStates; var111 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((pageFaultsMean[var111] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var112 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample113)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample113 = fixedFlag$sample113;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsMean = (logProbability$pageFaultsMean + logProbability$var112);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var112);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample113)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var112);
		}
	}

	// Calculate the probability of the samples represented by sample130 using sampled
	// values.
	private final void logProbabilityValue$sample130() {
		// Determine if we need to calculate the values for sample task 130 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample130) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var128 = 0; var128 < noStates; var128 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(cpuVar[var128], 5.0, 0.5));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var129 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample130)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample130 = fixedFlag$sample130;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuVar = (logProbability$cpuVar + logProbability$var129);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var129);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample130)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var129);
		}
	}

	// Calculate the probability of the samples represented by sample147 using sampled
	// values.
	private final void logProbabilityValue$sample147() {
		// Determine if we need to calculate the values for sample task 147 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample147) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var145 = 0; var145 < noStates; var145 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(memVar[var145], 5.0, 0.5));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var146 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample147)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample147 = fixedFlag$sample147;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memVar = (logProbability$memVar + logProbability$var146);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var146);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample147)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var146);
		}
	}

	// Calculate the probability of the samples represented by sample164 using sampled
	// values.
	private final void logProbabilityValue$sample164() {
		// Determine if we need to calculate the values for sample task 164 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample164) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var162 = 0; var162 < noStates; var162 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(pageFaultsVar[var162], 5.0, 0.5));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var163 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample164)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample164 = fixedFlag$sample164;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaultsVar = (logProbability$pageFaultsVar + logProbability$var163);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var163);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample164)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var163);
		}
	}

	// Calculate the probability of the samples represented by sample180 using sampled
	// values.
	private final void logProbabilityValue$sample180() {
		// Determine if we need to calculate the values for sample task 180 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample180) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double var177 = cpuVar[st[i$var174]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cpu[i$var174] - cpuMean[st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var179 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample180 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample77) && fixedFlag$sample130);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpu = (logProbability$cpu + logProbability$var179);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var179);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var179);
		}
	}

	// Calculate the probability of the samples represented by sample185 using sampled
	// values.
	private final void logProbabilityValue$sample185() {
		// Determine if we need to calculate the values for sample task 185 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample185) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double var182 = memVar[st[i$var174]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((mem[i$var174] - memMean[st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var184 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample185 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample147);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$mem = (logProbability$mem + logProbability$var184);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var184);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var184);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var174 = 0; i$var174 < samples; i$var174 += 1) {
				double var187 = pageFaultsVar[st[i$var174]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((pageFaults[i$var174] - pageFaultsMean[st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var189 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample190 = (((fixedFlag$sample39 && fixedFlag$sample57) && fixedFlag$sample113) && fixedFlag$sample164);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$pageFaults = (logProbability$pageFaults + logProbability$var189);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var189);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var189);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < noStates; var29 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v, noStates));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var30 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample30)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			
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
			if(fixedFlag$sample36)
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
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
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
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$var38 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample39)
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
			fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedFlag$sample36);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var38);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var50];
				double[] var54 = m[st[(i$var50 - 1)]];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var56 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample57)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = ((fixedFlag$sample57 && fixedFlag$sample30) && fixedFlag$sample39);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Calculate the probability of the samples represented by sample77 using sampled
	// values.
	private final void logProbabilityValue$sample77() {
		// Determine if we need to calculate the values for sample task 77 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample77) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var75 = 0; var75 < noStates; var75 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((cpuMean[var75] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var76 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample77)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$cpuMean = (logProbability$cpuMean + logProbability$var76);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var76);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample77)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var76);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var93 = 0; var93 < noStates; var93 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((memMean[var93] - 94.0)));
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var94 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample95)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$memMean = (logProbability$memMean + logProbability$var94);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var94);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var94);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var30$countGlobal
		// 
		// Allocation of cv$var30$countGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var30$countGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[noStates];
		
		// Constructor for cv$var35$countGlobal
		// 
		// Allocation of cv$var35$countGlobal for single threaded execution
		cv$var35$countGlobal = new double[noStates];
		
		// Constructor for cv$distributionAccumulator$var55
		// 
		// Allocation of cv$distributionAccumulator$var55 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 55. Initially set to the value
		// of putTask 31.
		cv$distributionAccumulator$var55 = new double[noStates];
		
		// Constructor for cv$var38$stateProbabilityGlobal
		// 
		// Allocation of cv$var38$stateProbabilityGlobal for single threaded execution
		cv$var38$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample39gaussian179$global
		// 
		// Allocation of guard$sample39gaussian179$global for single threaded execution
		guard$sample39gaussian179$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample39gaussian184$global
		// 
		// Allocation of guard$sample39gaussian184$global for single threaded execution
		guard$sample39gaussian184$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample39gaussian189$global
		// 
		// Allocation of guard$sample39gaussian189$global for single threaded execution
		guard$sample39gaussian189$global = new boolean[length$cpu_measured];
		
		// Allocation of cv$var56$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 55. Initially set to the value
		// of putTask 31.
		cv$var56$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for guard$sample57gaussian179$global
		// 
		// Allocation of guard$sample57gaussian179$global for single threaded execution
		guard$sample57gaussian179$global = new boolean[length$cpu_measured];
		
		// Constructor for guard$sample57gaussian184$global
		// 
		// Allocation of guard$sample57gaussian184$global for single threaded execution
		guard$sample57gaussian184$global = new boolean[length$cpu_measured];
		
		// Allocation of guard$sample57gaussian189$global for single threaded execution
		guard$sample57gaussian189$global = new boolean[length$cpu_measured];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample30) {
			// Constructor for m
			m = new double[noStates][];
			for(int var29 = 0; var29 < noStates; var29 += 1)
				m[var29] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample39 || !fixedFlag$sample57))
			// Constructor for st
			st = new int[length$cpu_measured];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!fixedFlag$sample36)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// Constructor for cpu
		cpu = new double[length$cpu_measured];
		
		// Constructor for mem
		mem = new double[length$cpu_measured];
		
		// Constructor for pageFaults
		pageFaults = new double[length$cpu_measured];
		
		// If cpuMean has not been set already allocate space.
		if(!fixedFlag$sample77)
			// Constructor for cpuMean
			cpuMean = new double[noStates];
		
		// If memMean has not been set already allocate space.
		if(!fixedFlag$sample95)
			// Constructor for memMean
			memMean = new double[noStates];
		
		// If pageFaultsMean has not been set already allocate space.
		if(!fixedFlag$sample113)
			// Constructor for pageFaultsMean
			pageFaultsMean = new double[noStates];
		
		// If cpuVar has not been set already allocate space.
		if(!fixedFlag$sample130)
			// Constructor for cpuVar
			cpuVar = new double[noStates];
		
		// If memVar has not been set already allocate space.
		if(!fixedFlag$sample147)
			// Constructor for memVar
			memVar = new double[noStates];
		
		// If pageFaultsVar has not been set already allocate space.
		if(!fixedFlag$sample164)
			// Constructor for pageFaultsVar
			pageFaultsVar = new double[noStates];
		
		// Constructor for distribution$sample39
		distribution$sample39 = new double[noStates];
		
		// Constructor for distribution$sample57
		distribution$sample57 = new double[(length$cpu_measured - 1)][];
		for(int i$var50 = 1; i$var50 < length$cpu_measured; i$var50 += 1)
			distribution$sample57[(i$var50 - 1)] = new double[noStates];
		
		// Constructor for constrainedFlag$sample95
		constrainedFlag$sample95 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample30
		constrainedFlag$sample30 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample77
		constrainedFlag$sample77 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample57
		constrainedFlag$sample57 = new boolean[(length$cpu_measured - 1)];
		
		// Constructor for constrainedFlag$sample164
		constrainedFlag$sample164 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample147
		constrainedFlag$sample147 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample130
		constrainedFlag$sample130 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample113
		constrainedFlag$sample113 = new boolean[noStates];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						cpu[i$var174] = ((Math.sqrt(cpuVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var174]]);
						mem[i$var174] = ((Math.sqrt(memVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var174]]);
						pageFaults[i$var174] = ((Math.sqrt(pageFaultsVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var174]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample39) {
			for(int index$var37 = 0; index$var37 < noStates; index$var37 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample39's comment
				// Create local copy of variable probabilities.
				distribution$sample39[index$var37] = (((0.0 <= initialStateDistribution[index$var37]) && (initialStateDistribution[index$var37] <= 1.0))?initialStateDistribution[index$var37]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample57 = distribution$sample57[(i$var50 - 1)];
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					// Zero the probability of each value
					cv$distribution$sample57[index$var55] = 0.0;
				
				// Iterate through possible values for var55's arguments.
				// 
				// Enumerating the possible arguments for Categorical 55.
				if((1 == i$var50)) {
					// Iterate through possible values for var55's arguments.
					// 
					// Enumerating the possible arguments for Categorical 55.
					if(fixedFlag$sample39) {
						int var29 = st[0];
						
						// Substituted "i$var50" with its value "1".
						if(((0 <= var29) && (var29 < noStates))) {
							// Substituted "i$var50" with its value "1".
							double[] var54 = m[st[0]];
							for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
								// Save the probability of each value
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 37.
						for(int index$sample39$2 = 0; index$sample39$2 < noStates; index$sample39$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample39Value3 = distribution$sample39[index$sample39$2];
							double[] var54 = m[index$sample39$2];
							for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
								// Save the probability of each value
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var50 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 55.
					for(int index$sample57$10 = 0; index$sample57$10 < noStates; index$sample57$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample57Value11 = distribution$sample57[(index$i$9 - 1)][index$sample57$10];
						double[] var54 = m[index$sample57$10];
						for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
							// Save the probability of each value
							cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					// sum the probability of each value
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
				for(int index$var55 = 0; index$var55 < noStates; index$var55 += 1)
					// Normalise the probability of each value
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						cpu[i$var174] = ((Math.sqrt(cpuVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + cpuMean[st[i$var174]]);
						mem[i$var174] = ((Math.sqrt(memVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + memMean[st[i$var174]]);
						pageFaults[i$var174] = ((Math.sqrt(pageFaultsVar[st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + pageFaultsMean[st[i$var174]]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var29]);
				}
			);

		if(!fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample39)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
				st[i$var50] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var50 - 1)]], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample77)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample113)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample130)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample147)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample164)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

			if(!fixedFlag$sample36)
				inferSample36();
			if(!fixedFlag$sample39)
				inferSample39();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int i$var50 = 1; i$var50 < samples; i$var50 += 1)
					inferSample57(i$var50);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample77)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample113)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample130)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample147)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample164)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample164)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample147)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample130)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample113)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample77)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int i$var50 = (samples - 1); i$var50 >= 1; i$var50 -= 1)
					inferSample57(i$var50);
			}
			if(!fixedFlag$sample39)
				inferSample39();
			if(!fixedFlag$sample36)
				inferSample36();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!constrainedFlag$sample30[var29])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		if(!constrainedFlag$sample36)
			drawValueSample36();
		if(!constrainedFlag$sample39)
			drawValueSample39();
		for(int i$var50 = 1; i$var50 < samples; i$var50 += 1) {
			if(!constrainedFlag$sample57[(i$var50 - 1)])
				drawValueSample57(i$var50);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1) {
						if(!constrainedFlag$sample77[var75])
							drawValueSample77(var75, threadID$var75, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1) {
						if(!constrainedFlag$sample95[var93])
							drawValueSample95(var93, threadID$var93, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1) {
						if(!constrainedFlag$sample113[var111])
							drawValueSample113(var111, threadID$var111, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1) {
						if(!constrainedFlag$sample130[var128])
							drawValueSample130(var128, threadID$var128, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1) {
						if(!constrainedFlag$sample147[var145])
							drawValueSample147(var145, threadID$var145, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1) {
						if(!constrainedFlag$sample164[var162])
							drawValueSample164(var162, threadID$var162, RNG$1);
					}
			}
		);
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
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var30 = Double.NaN;
		if(!fixedProbFlag$sample36)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$var38 = Double.NaN;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		logProbability$cpuMean = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var76 = Double.NaN;
		logProbability$memMean = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var94 = Double.NaN;
		logProbability$pageFaultsMean = 0.0;
		if(!fixedProbFlag$sample113)
			logProbability$var112 = Double.NaN;
		logProbability$cpuVar = 0.0;
		if(!fixedProbFlag$sample130)
			logProbability$var129 = Double.NaN;
		logProbability$memVar = 0.0;
		if(!fixedProbFlag$sample147)
			logProbability$var146 = Double.NaN;
		logProbability$pageFaultsVar = 0.0;
		if(!fixedProbFlag$sample164)
			logProbability$var163 = Double.NaN;
		logProbability$cpu = 0.0;
		if(!fixedProbFlag$sample180)
			logProbability$var179 = Double.NaN;
		logProbability$mem = 0.0;
		if(!fixedProbFlag$sample185)
			logProbability$var184 = Double.NaN;
		logProbability$pageFaults = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var189 = Double.NaN;
	}

	// Method for initializing the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var15 = 0; var15 < noStates; var15 += 1)
			v[var15] = 0.1;
		samples = length$cpu_measured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
			constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
			constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
			constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
			constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
			constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(fixedFlag$sample113)
			logProbabilityValue$sample113();
		if(fixedFlag$sample130)
			logProbabilityValue$sample130();
		if(fixedFlag$sample147)
			logProbabilityValue$sample147();
		if(fixedFlag$sample164)
			logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample39();
		logProbabilityDistribution$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityDistribution$sample180();
		logProbabilityDistribution$sample185();
		logProbabilityDistribution$sample190();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityValue$sample39();
		logProbabilityValue$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
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
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
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