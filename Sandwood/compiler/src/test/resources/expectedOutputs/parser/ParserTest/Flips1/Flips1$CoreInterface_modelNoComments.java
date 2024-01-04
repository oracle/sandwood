package coinFlips;

interface Flips1$CoreInterface extends org.sandwood.runtime.internal.model.CoreModel {
	public boolean get$coin1();
	public void set$coin1(boolean calculationVariable$value);
	public boolean get$coin2();
	public void set$coin2(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample4();
	public void set$fixedFlag$sample4(boolean calculationVariable$value);
	public boolean get$fixedFlag$sample6();
	public void set$fixedFlag$sample6(boolean calculationVariable$value);
	public double get$heads1();
	public double get$heads2();
	public double get$logProbability$coin1();
	public double get$logProbability$coin2();
	public double get$logProbability$twoHeads();
	public boolean get$twoHeads();
	public void set$twoHeads(boolean calculationVariable$value);
	public void logEvidenceGeneration();
}