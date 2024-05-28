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

	// Getter for fixedFlag$sample105.
	public boolean get$fixedFlag$sample105();

	// Setter for fixedFlag$sample105.
	public void set$fixedFlag$sample105(boolean cv$value);

	// Getter for fixedFlag$sample123.
	public boolean get$fixedFlag$sample123();

	// Setter for fixedFlag$sample123.
	public void set$fixedFlag$sample123(boolean cv$value);

	// Getter for fixedFlag$sample140.
	public boolean get$fixedFlag$sample140();

	// Setter for fixedFlag$sample140.
	public void set$fixedFlag$sample140(boolean cv$value);

	// Getter for fixedFlag$sample157.
	public boolean get$fixedFlag$sample157();

	// Setter for fixedFlag$sample157.
	public void set$fixedFlag$sample157(boolean cv$value);

	// Getter for fixedFlag$sample174.
	public boolean get$fixedFlag$sample174();

	// Setter for fixedFlag$sample174.
	public void set$fixedFlag$sample174(boolean cv$value);

	// Getter for fixedFlag$sample190.
	public boolean get$fixedFlag$sample190();

	// Setter for fixedFlag$sample190.
	public void set$fixedFlag$sample190(boolean cv$value);

	// Getter for fixedFlag$sample195.
	public boolean get$fixedFlag$sample195();

	// Setter for fixedFlag$sample195.
	public void set$fixedFlag$sample195(boolean cv$value);

	// Getter for fixedFlag$sample200.
	public boolean get$fixedFlag$sample200();

	// Setter for fixedFlag$sample200.
	public void set$fixedFlag$sample200(boolean cv$value);

	// Getter for fixedFlag$sample39.
	public boolean get$fixedFlag$sample39();

	// Setter for fixedFlag$sample39.
	public void set$fixedFlag$sample39(boolean cv$value);

	// Getter for fixedFlag$sample46.
	public boolean get$fixedFlag$sample46();

	// Setter for fixedFlag$sample46.
	public void set$fixedFlag$sample46(boolean cv$value);

	// Getter for fixedFlag$sample49.
	public boolean get$fixedFlag$sample49();

	// Setter for fixedFlag$sample49.
	public void set$fixedFlag$sample49(boolean cv$value);

	// Getter for fixedFlag$sample67.
	public boolean get$fixedFlag$sample67();

	// Setter for fixedFlag$sample67.
	public void set$fixedFlag$sample67(boolean cv$value);

	// Getter for fixedFlag$sample87.
	public boolean get$fixedFlag$sample87();

	// Setter for fixedFlag$sample87.
	public void set$fixedFlag$sample87(boolean cv$value);

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