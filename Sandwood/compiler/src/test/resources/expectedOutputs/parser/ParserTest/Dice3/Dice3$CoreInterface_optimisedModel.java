package dice;

interface Dice3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for die1.
	public int get$die1();

	// Setter for die1.
	public void set$die1(int calculationVariable$value);

	// Getter for die2.
	public int get$die2();

	// Setter for die2.
	public void set$die2(int calculationVariable$value);

	// Getter for even.
	public boolean get$even();

	// Setter for even.
	public void set$even(boolean calculationVariable$value);

	// Getter for fixedFlag$sample11.
	public boolean get$fixedFlag$sample11();

	// Setter for fixedFlag$sample11.
	public void set$fixedFlag$sample11(boolean calculationVariable$value);

	// Getter for fixedFlag$sample15.
	public boolean get$fixedFlag$sample15();

	// Setter for fixedFlag$sample15.
	public void set$fixedFlag$sample15(boolean calculationVariable$value);

	// Getter for logProbability$die1.
	public double get$logProbability$die1();

	// Getter for logProbability$die2.
	public double get$logProbability$die2();

	// Getter for logProbability$even.
	public double get$logProbability$even();

	// Getter for logProbability$odd.
	public double get$logProbability$odd();

	// Getter for logProbability$sum.
	public double get$logProbability$sum();

	// Getter for odd.
	public boolean get$odd();

	// Setter for odd.
	public void set$odd(boolean calculationVariable$value);

	// Getter for p_die.
	public double[] get$p_die();

	// Getter for sum.
	public int get$sum();

	// Setter for sum.
	public void set$sum(int calculationVariable$value);

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}