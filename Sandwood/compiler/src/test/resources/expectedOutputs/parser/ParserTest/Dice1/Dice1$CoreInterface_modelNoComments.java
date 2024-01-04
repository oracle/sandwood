package dice;

interface Dice1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int get$die1();
	public void set$die1(int calculationVariable$value);
	public int get$die2();
	public void set$die2(int calculationVariable$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean calculationVariable$value);
	public double get$logProbability$die1();
	public double get$logProbability$die2();
	public double get$logProbability$someSix();
	public double get$logProbability$twoSix();
	public double[] get$p_die1();
	public double[] get$p_die2();
	public boolean get$someSix();
	public void set$someSix(boolean calculationVariable$value);
	public boolean get$twoSix();
	public void set$twoSix(boolean calculationVariable$value);
	public void logEvidenceGeneration();
}