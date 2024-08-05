package org.sandwood.compiler.tests.parser;

interface HMM_Paper$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample17.
	public boolean get$fixedFlag$sample17();

	// Setter for fixedFlag$sample17.
	public void set$fixedFlag$sample17(boolean cv$value);

	// Getter for fixedFlag$sample20.
	public boolean get$fixedFlag$sample20();

	// Setter for fixedFlag$sample20.
	public void set$fixedFlag$sample20(boolean cv$value);

	// Getter for fixedFlag$sample28.
	public boolean get$fixedFlag$sample28();

	// Setter for fixedFlag$sample28.
	public void set$fixedFlag$sample28(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample45.
	public boolean get$fixedFlag$sample45();

	// Setter for fixedFlag$sample45.
	public void set$fixedFlag$sample45(boolean cv$value);

	// Getter for fixedFlag$sample54.
	public boolean get$fixedFlag$sample54();

	// Setter for fixedFlag$sample54.
	public void set$fixedFlag$sample54(boolean cv$value);

	// Getter for flips.
	public boolean[] get$flips();

	// Setter for flips.
	public void set$flips(boolean[] cv$value);

	// Getter for initialCoin.
	public double[] get$initialCoin();

	// Setter for initialCoin.
	public void set$initialCoin(double[] cv$value);

	// Getter for length$measured.
	public int get$length$measured();

	// Setter for length$measured.
	public void set$length$measured(int cv$value);

	// Getter for logProbability$bias.
	public double get$logProbability$bias();

	// Getter for logProbability$flips.
	public double get$logProbability$flips();

	// Getter for logProbability$initialCoin.
	public double get$logProbability$initialCoin();

	// Getter for logProbability$m.
	public double get$logProbability$m();

	// Getter for logProbability$st.
	public double get$logProbability$st();

	// Getter for m.
	public double[][] get$m();

	// Setter for m.
	public void set$m(double[][] cv$value);

	// Getter for measured.
	public boolean[] get$measured();

	// Setter for measured.
	public void set$measured(boolean[] cv$value);

	// Getter for nCoins.
	public int get$nCoins();

	// Setter for nCoins.
	public void set$nCoins(int cv$value);

	// Getter for nFlips.
	public int get$nFlips();

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