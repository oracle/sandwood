package dice;

interface Dice2$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public int get$die1();
	public void set$die1(int calculationVariable$value);
	public int get$die2();
	public void set$die2(int calculationVariable$value);
	public boolean get$even1();
	public void set$even1(boolean calculationVariable$value);
	public boolean get$even2();
	public void set$even2(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample31();
	public void set$fixedFlag$sample31(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample35();
	public void set$fixedFlag$sample35(boolean calculationVariable$value);
	public double get$logProbability$die1();
	public double get$logProbability$die2();
	public double get$logProbability$even1();
	public double get$logProbability$even2();
	public double get$logProbability$odd1();
	public double get$logProbability$odd2();
	public boolean get$odd1();
	public void set$odd1(boolean calculationVariable$value);
	public boolean get$odd2();
	public void set$odd2(boolean calculationVariable$value);
	public double[] get$p_die1();
	public double[] get$p_die2();
	public void logEvidenceGeneration();
}