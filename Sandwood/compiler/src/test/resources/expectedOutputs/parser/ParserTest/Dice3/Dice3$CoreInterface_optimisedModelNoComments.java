package dice;

interface Dice3$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int get$die1();
	public void set$die1(int calculationVariable$value);
	public int get$die2();
	public void set$die2(int calculationVariable$value);
	public boolean get$even();
	public void set$even(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample11();
	public void set$fixedFlag$sample11(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample15();
	public void set$fixedFlag$sample15(boolean calculationVariable$value);
	public double get$logProbability$die1();
	public double get$logProbability$die2();
	public double get$logProbability$even();
	public double get$logProbability$odd();
	public double get$logProbability$sum();
	public boolean get$odd();
	public void set$odd(boolean calculationVariable$value);
	public double[] get$p_die();
	public int get$sum();
	public void set$sum(int calculationVariable$value);
	public void logEvidenceGeneration();
}