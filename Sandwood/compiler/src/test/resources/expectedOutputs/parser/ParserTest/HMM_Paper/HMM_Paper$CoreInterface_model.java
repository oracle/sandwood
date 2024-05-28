package org.sandwood.compiler.tests.parser;

interface HMM_Paper$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for bias.
	public double[] get$bias();

	// Setter for bias.
	public void set$bias(double[] cv$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean cv$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean cv$value);

	// Getter for fixedFlag$sample50.
	public boolean get$fixedFlag$sample50();

	// Setter for fixedFlag$sample50.
	public void set$fixedFlag$sample50(boolean cv$value);

	// Getter for fixedFlag$sample57.
	public boolean get$fixedFlag$sample57();

	// Setter for fixedFlag$sample57.
	public void set$fixedFlag$sample57(boolean cv$value);

	// Getter for fixedFlag$sample75.
	public boolean get$fixedFlag$sample75();

	// Setter for fixedFlag$sample75.
	public void set$fixedFlag$sample75(boolean cv$value);

	// Getter for fixedFlag$sample91.
	public boolean get$fixedFlag$sample91();

	// Setter for fixedFlag$sample91.
	public void set$fixedFlag$sample91(boolean cv$value);

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