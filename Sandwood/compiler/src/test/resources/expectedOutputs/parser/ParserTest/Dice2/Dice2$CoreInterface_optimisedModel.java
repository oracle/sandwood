package dice;

interface Dice2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {

	// Getter for die1.
	public int get$die1();

	// Setter for die1.
	public void set$die1(int calculationVariable$value);

	// Getter for die2.
	public int get$die2();

	// Setter for die2.
	public void set$die2(int calculationVariable$value);

	// Getter for even1.
	public boolean get$even1();

	// Setter for even1.
	public void set$even1(boolean calculationVariable$value);

	// Getter for even2.
	public boolean get$even2();

	// Setter for even2.
	public void set$even2(boolean calculationVariable$value);

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

	// Getter for logProbability$even1.
	public double get$logProbability$even1();

	// Getter for logProbability$even2.
	public double get$logProbability$even2();

	// Getter for logProbability$odd1.
	public double get$logProbability$odd1();

	// Getter for logProbability$odd2.
	public double get$logProbability$odd2();

	// Getter for odd1.
	public boolean get$odd1();

	// Setter for odd1.
	public void set$odd1(boolean calculationVariable$value);

	// Getter for odd2.
	public boolean get$odd2();

	// Setter for odd2.
	public void set$odd2(boolean calculationVariable$value);

	// Getter for p_die1.
	public double[] get$p_die1();

	// Getter for p_die2.
	public double[] get$p_die2();

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	public void logEvidenceGeneration();
}