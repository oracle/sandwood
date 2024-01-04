package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice1$CoreInterface {
	private int die1;
	private int die2;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$someSix;
	private double logProbability$twoSix;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var35;
	private double[] p_die1;
	private double[] p_die2;
	private boolean someSix;
	private boolean system$gibbsForward = true;
	private boolean twoSix;

	public Dice1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$die1() {
		return die1;
	}

	@Override
	public final void set$die1(int calculationVariable$value) {
		die1 = calculationVariable$value;
	}

	@Override
	public final int get$die2() {
		return die2;
	}

	@Override
	public final void set$die2(int calculationVariable$value) {
		die2 = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean calculationVariable$value) {
		fixedFlag$sample31 = calculationVariable$value;
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean calculationVariable$value) {
		fixedFlag$sample35 = calculationVariable$value;
		fixedProbFlag$sample35 = (calculationVariable$value && fixedProbFlag$sample35);
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$die1() {
		return logProbability$die1;
	}

	@Override
	public final double get$logProbability$die2() {
		return logProbability$die2;
	}

	@Override
	public final double get$logProbability$someSix() {
		return logProbability$someSix;
	}

	@Override
	public final double get$logProbability$twoSix() {
		return logProbability$twoSix;
	}

	@Override
	public final double[] get$p_die1() {
		return p_die1;
	}

	@Override
	public final double[] get$p_die2() {
		return p_die2;
	}

	@Override
	public final boolean get$someSix() {
		return someSix;
	}

	@Override
	public final void set$someSix(boolean calculationVariable$value) {
		someSix = calculationVariable$value;
	}

	@Override
	public final boolean get$twoSix() {
		return twoSix;
	}

	@Override
	public final void set$twoSix(boolean calculationVariable$value) {
		twoSix = calculationVariable$value;
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die1 - 1), p_die1);
			logProbability$var30 = calculationVariable$distributionAccumulator;
			logProbability$var31 = calculationVariable$distributionAccumulator;
			logProbability$die1 = (logProbability$die1 + calculationVariable$distributionAccumulator);
			logProbability$twoSix = (logProbability$twoSix + calculationVariable$distributionAccumulator);
			logProbability$someSix = (logProbability$someSix + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var30 = logProbability$var31;
			logProbability$die1 = (logProbability$die1 + logProbability$var31);
			logProbability$twoSix = (logProbability$twoSix + logProbability$var31);
			logProbability$someSix = (logProbability$someSix + logProbability$var31);
			logProbability$$model = (logProbability$$model + logProbability$var31);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical((die2 - 1), p_die2);
			logProbability$var34 = calculationVariable$distributionAccumulator;
			logProbability$var35 = calculationVariable$distributionAccumulator;
			logProbability$die2 = (logProbability$die2 + calculationVariable$distributionAccumulator);
			logProbability$twoSix = (logProbability$twoSix + calculationVariable$distributionAccumulator);
			logProbability$someSix = (logProbability$someSix + calculationVariable$distributionAccumulator);
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var34 = logProbability$var35;
			logProbability$die2 = (logProbability$die2 + logProbability$var35);
			logProbability$twoSix = (logProbability$twoSix + logProbability$var35);
			logProbability$someSix = (logProbability$someSix + logProbability$var35);
			logProbability$$model = (logProbability$$model + logProbability$var35);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		p_die1 = new double[6];
		p_die2 = new double[6];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die1) + 1);
		if(!fixedFlag$sample35)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die2) + 1);
		if((!fixedFlag$sample31 || !fixedFlag$sample35)) {
			twoSix = ((die1 == 6) && (die2 == 6));
			someSix = ((die1 == 6) || (die2 == 6));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {}

	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	@Override
	public final void gibbsRound() {
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 6, 1,
			(int forStart$var5, int forEnd$var5, int threadID$var5, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var5=forStart$var5; var5<forEnd$var5; var5+=1)
						p_die1[var5] = 0;
			}
		);
		p_die2[0] = 0.15;
		p_die2[1] = 0.15;
		p_die2[2] = 0.15;
		p_die2[3] = 0.15;
		p_die2[4] = 0.15;
		p_die2[5] = 0.25;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = 0.0;
		logProbability$die1 = 0.0;
		logProbability$twoSix = 0.0;
		logProbability$someSix = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var31 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$die2 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var35 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void setIntermediates() {
		twoSix = ((die1 == 6) && (die2 == 6));
		someSix = ((die1 == 6) || (die2 == 6));
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one1; 1/6::two1; 1/6::three1; 1/6::four1; 1/6::five1; 1/6::six1.\n * 0.15::one2; 0.15::two2; 0.15::three2; 0.15::four2; 0.15::five2; 0.25::six2.\n * \n * % Rules:\n * twoSix :- six1, six2.\n * \n * someSix :- six1.\n * someSix :- six2.\n * \n * % Queries:\n * query(six1).\n * query(six2).\n * query(twoSix).\n * query(someSix).\n */\n\npublic model Dice1() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean twoSix = (die1 == 6) && (die2 == 6);\n    boolean someSix = (die1 == 6) || (die2 == 6);\n}";
	}
}