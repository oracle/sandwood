package org.sandwood.compiler.tests.parser;

interface HMMMetrics$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for cpu.
	public double[] get$cpu();

	// Setter for cpu.
	public void set$cpu(double[] cv$value);

	// Getter for cpuMean.
	public double[] get$cpuMean();

	// Setter for cpuMean.
	public void set$cpuMean(double[] cv$value);

	// Getter for cpuVar.
	public double[] get$cpuVar();

	// Setter for cpuVar.
	public void set$cpuVar(double[] cv$value);

	// Getter for cpu_measured.
	public double[] get$cpu_measured();

	// Setter for cpu_measured.
	public void set$cpu_measured(double[] cv$value);

	// Getter for fixedFlag$sample100.
	public boolean get$fixedFlag$sample100();

	// Setter for fixedFlag$sample100.
	public void set$fixedFlag$sample100(boolean cv$value);

	// Getter for fixedFlag$sample110.
	public boolean get$fixedFlag$sample110();

	// Setter for fixedFlag$sample110.
	public void set$fixedFlag$sample110(boolean cv$value);

	// Getter for fixedFlag$sample119.
	public boolean get$fixedFlag$sample119();

	// Setter for fixedFlag$sample119.
	public void set$fixedFlag$sample119(boolean cv$value);

	// Getter for fixedFlag$sample124.
	public boolean get$fixedFlag$sample124();

	// Setter for fixedFlag$sample124.
	public void set$fixedFlag$sample124(boolean cv$value);

	// Getter for fixedFlag$sample129.
	public boolean get$fixedFlag$sample129();

	// Setter for fixedFlag$sample129.
	public void set$fixedFlag$sample129(boolean cv$value);

	// Getter for fixedFlag$sample25.
	public boolean get$fixedFlag$sample25();

	// Setter for fixedFlag$sample25.
	public void set$fixedFlag$sample25(boolean cv$value);

	// Getter for fixedFlag$sample32.
	public boolean get$fixedFlag$sample32();

	// Setter for fixedFlag$sample32.
	public void set$fixedFlag$sample32(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample45.
	public boolean get$fixedFlag$sample45();

	// Setter for fixedFlag$sample45.
	public void set$fixedFlag$sample45(boolean cv$value);

	// Getter for fixedFlag$sample58.
	public boolean get$fixedFlag$sample58();

	// Setter for fixedFlag$sample58.
	public void set$fixedFlag$sample58(boolean cv$value);

	// Getter for fixedFlag$sample69.
	public boolean get$fixedFlag$sample69();

	// Setter for fixedFlag$sample69.
	public void set$fixedFlag$sample69(boolean cv$value);

	// Getter for fixedFlag$sample80.
	public boolean get$fixedFlag$sample80();

	// Setter for fixedFlag$sample80.
	public void set$fixedFlag$sample80(boolean cv$value);

	// Getter for fixedFlag$sample90.
	public boolean get$fixedFlag$sample90();

	// Setter for fixedFlag$sample90.
	public void set$fixedFlag$sample90(boolean cv$value);

	// Getter for initialStateDistribution.
	public double[] get$initialStateDistribution();

	// Setter for initialStateDistribution.
	public void set$initialStateDistribution(double[] cv$value);

	// Getter for length$cpu_measured.
	public int get$length$cpu_measured();

	// Setter for length$cpu_measured.
	public void set$length$cpu_measured(int cv$value);

	// Getter for logProbability$cpu.
	public double get$logProbability$cpu();

	// Getter for logProbability$cpuMean.
	public double get$logProbability$cpuMean();

	// Getter for logProbability$cpuVar.
	public double get$logProbability$cpuVar();

	// Getter for logProbability$initialStateDistribution.
	public double get$logProbability$initialStateDistribution();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$mem.
	public double get$logProbability$mem();

	// Getter for logProbability$memMean.
	public double get$logProbability$memMean();

	// Getter for logProbability$memVar.
	public double get$logProbability$memVar();

	// Getter for logProbability$pageFaults.
	public double get$logProbability$pageFaults();

	// Getter for logProbability$pageFaultsMean.
	public double get$logProbability$pageFaultsMean();

	// Getter for logProbability$pageFaultsVar.
	public double get$logProbability$pageFaultsVar();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for mem.
	public double[] get$mem();

	// Setter for mem.
	public void set$mem(double[] cv$value);

	// Getter for memMean.
	public double[] get$memMean();

	// Setter for memMean.
	public void set$memMean(double[] cv$value);

	// Getter for memVar.
	public double[] get$memVar();

	// Setter for memVar.
	public void set$memVar(double[] cv$value);

	// Getter for mem_measured.
	public double[] get$mem_measured();

	// Setter for mem_measured.
	public void set$mem_measured(double[] cv$value);

	// Getter for noStates.
	public int get$noStates();

	// Setter for noStates.
	public void set$noStates(int cv$value);

	// Getter for pageFaults.
	public double[] get$pageFaults();

	// Setter for pageFaults.
	public void set$pageFaults(double[] cv$value);

	// Getter for pageFaultsMean.
	public double[] get$pageFaultsMean();

	// Setter for pageFaultsMean.
	public void set$pageFaultsMean(double[] cv$value);

	// Getter for pageFaultsVar.
	public double[] get$pageFaultsVar();

	// Setter for pageFaultsVar.
	public void set$pageFaultsVar(double[] cv$value);

	// Getter for pageFaults_measured.
	public double[] get$pageFaults_measured();

	// Setter for pageFaults_measured.
	public void set$pageFaults_measured(double[] cv$value);

	// Getter for samples.
	public int get$samples();

	// Getter for st.
	public int[] get$st();

	// Setter for st.
	public void set$st(int[] cv$value);

	// Getter for v.
	public double[] get$v();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}