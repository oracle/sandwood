package coinFlips;

interface Flips1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for coin1.
	public boolean get$coin1();

	// Setter for coin1.
	public void set$coin1(boolean calculationVariable$value);

	// Getter for coin2.
	public boolean get$coin2();

	// Setter for coin2.
	public void set$coin2(boolean calculationVariable$value);

	// Getter for fixedFlag$sample4.
	public boolean get$fixedFlag$sample4();

	// Setter for fixedFlag$sample4.
	public void set$fixedFlag$sample4(boolean calculationVariable$value);

	// Getter for fixedFlag$sample6.
	public boolean get$fixedFlag$sample6();

	// Setter for fixedFlag$sample6.
	public void set$fixedFlag$sample6(boolean calculationVariable$value);

	// Getter for heads1.
	public double get$heads1();

	// Getter for heads2.
	public double get$heads2();

	// Getter for logProbability$coin1.
	public double get$logProbability$coin1();

	// Getter for logProbability$coin2.
	public double get$logProbability$coin2();

	// Getter for logProbability$twoHeads.
	public double get$logProbability$twoHeads();

	// Getter for twoHeads.
	public boolean get$twoHeads();

	// Setter for twoHeads.
	public void set$twoHeads(boolean calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}