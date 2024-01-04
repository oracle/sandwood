package dice;

interface Dice1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for die1.
	public int get$die1();

	// Setter for die1.
	public void set$die1(int calculationVariable$value);

	// Getter for die2.
	public int get$die2();

	// Setter for die2.
	public void set$die2(int calculationVariable$value);

	// Getter for fixedFlag$sample31.
	public boolean get$fixedFlag$sample31();

	// Setter for fixedFlag$sample31.
	public void set$fixedFlag$sample31(boolean calculationVariable$value);

	// Getter for fixedFlag$sample35.
	public boolean get$fixedFlag$sample35();

	// Setter for fixedFlag$sample35.
	public void set$fixedFlag$sample35(boolean calculationVariable$value);

	// Getter for logProbability$die1.
	public double get$logProbability$die1();

	// Getter for logProbability$die2.
	public double get$logProbability$die2();

	// Getter for logProbability$someSix.
	public double get$logProbability$someSix();

	// Getter for logProbability$twoSix.
	public double get$logProbability$twoSix();

	// Getter for p_die1.
	public double[] get$p_die1();

	// Getter for p_die2.
	public double[] get$p_die2();

	// Getter for someSix.
	public boolean get$someSix();

	// Setter for someSix.
	public void set$someSix(boolean calculationVariable$value);

	// Getter for twoSix.
	public boolean get$twoSix();

	// Setter for twoSix.
	public void set$twoSix(boolean calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}