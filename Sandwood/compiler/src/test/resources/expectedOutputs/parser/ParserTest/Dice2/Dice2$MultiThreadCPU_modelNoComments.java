package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice2$CoreInterface {
	private int die1;
	private int die2;
	private boolean even1;
	private boolean even2;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$even1;
	private double logProbability$even2;
	private double logProbability$odd1;
	private double logProbability$odd2;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var34;
	private double logProbability$var35;
	private boolean odd1;
	private boolean odd2;
	private double[] p_die1;
	private double[] p_die2;
	private boolean system$gibbsForward = true;

	public Dice2$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$even1() {
		return even1;
	}

	@Override
	public final void set$even1(boolean calculationVariable$value) {
		even1 = calculationVariable$value;
	}

	@Override
	public final boolean get$even2() {
		return even2;
	}

	@Override
	public final void set$even2(boolean calculationVariable$value) {
		even2 = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean calculationVariable$value) {
		fixedFlag$sample31 = calculationVariable$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean calculationVariable$value) {
		fixedFlag$sample35 = calculationVariable$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
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
	public final double get$logProbability$even1() {
		return logProbability$even1;
	}

	@Override
	public final double get$logProbability$even2() {
		return logProbability$even2;
	}

	@Override
	public final double get$logProbability$odd1() {
		return logProbability$odd1;
	}

	@Override
	public final double get$logProbability$odd2() {
		return logProbability$odd2;
	}

	@Override
	public final boolean get$odd1() {
		return odd1;
	}

	@Override
	public final void set$odd1(boolean calculationVariable$value) {
		odd1 = calculationVariable$value;
	}

	@Override
	public final boolean get$odd2() {
		return odd2;
	}

	@Override
	public final void set$odd2(boolean calculationVariable$value) {
		odd2 = calculationVariable$value;
	}

	@Override
	public final double[] get$p_die1() {
		return p_die1;
	}

	@Override
	public final double[] get$p_die2() {
		return p_die2;
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				int calculationVariable$sampleValue = (die1 - 1);
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die1));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var30 = calculationVariable$sampleAccumulator;
			logProbability$var31 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$odd1 = false;
			boolean calculationVariable$guard$even1 = false;
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$odd1) {
					calculationVariable$guard$odd1 = true;
					logProbability$odd1 = (logProbability$odd1 + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even1) {
					calculationVariable$guard$even1 = true;
					logProbability$even1 = (logProbability$even1 + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var31;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var30 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$odd1 = false;
			boolean calculationVariable$guard$even1 = false;
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$odd1) {
					calculationVariable$guard$odd1 = true;
					logProbability$odd1 = (logProbability$odd1 + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even1) {
					calculationVariable$guard$even1 = true;
					logProbability$even1 = (logProbability$even1 + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				int calculationVariable$sampleValue = (die2 - 1);
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die2));
						if((calculationVariable$weightedProbability < calculationVariable$distributionAccumulator))
							calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$weightedProbability - calculationVariable$distributionAccumulator)) + 1)) + calculationVariable$distributionAccumulator);
						else {
							if((calculationVariable$distributionAccumulator == Double.NEGATIVE_INFINITY))
								calculationVariable$distributionAccumulator = calculationVariable$weightedProbability;
							else
								calculationVariable$distributionAccumulator = (Math.log((Math.exp((calculationVariable$distributionAccumulator - calculationVariable$weightedProbability)) + 1)) + calculationVariable$weightedProbability);
						}
						calculationVariable$probabilityReached = (calculationVariable$probabilityReached + 1.0);
					}
				}
			}
			if((calculationVariable$probabilityReached == 0.0))
				calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				calculationVariable$distributionAccumulator = (calculationVariable$distributionAccumulator - Math.log(calculationVariable$probabilityReached));
			double calculationVariable$sampleProbability = calculationVariable$distributionAccumulator;
			calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + calculationVariable$sampleProbability);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
			logProbability$var34 = calculationVariable$sampleAccumulator;
			logProbability$var35 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$odd2 = false;
			boolean calculationVariable$guard$even2 = false;
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$odd2) {
					calculationVariable$guard$odd2 = true;
					logProbability$odd2 = (logProbability$odd2 + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even2) {
					calculationVariable$guard$even2 = true;
					logProbability$even2 = (logProbability$even2 + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var35;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var34 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$odd2 = false;
			boolean calculationVariable$guard$even2 = false;
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$odd2) {
					calculationVariable$guard$odd2 = true;
					logProbability$odd2 = (logProbability$odd2 + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even2) {
					calculationVariable$guard$even2 = true;
					logProbability$even2 = (logProbability$even2 + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			p_die1 = new double[6];
		}
		{
			p_die2 = new double[6];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die1) + 1);
		if(!fixedFlag$sample35)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die2) + 1);
		if(!fixedFlag$sample31)
			odd1 = ((die1 % 2) == 1);
		if(!fixedFlag$sample31)
			even1 = !((die1 % 2) == 1);
		if(!fixedFlag$sample35)
			odd2 = ((die2 % 2) == 1);
		if(!fixedFlag$sample35)
			even2 = !((die2 % 2) == 1);
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
						p_die1[var5] = (1 / 6);
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
		logProbability$odd1 = 0.0;
		logProbability$even1 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var31 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$odd2 = 0.0;
		logProbability$die2 = 0.0;
		logProbability$even2 = 0.0;
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
		if(true)
			odd1 = ((die1 % 2) == 1);
		if(true)
			even1 = !((die1 % 2) == 1);
		if(true)
			odd2 = ((die2 % 2) == 1);
		if(true)
			even2 = !((die2 % 2) == 1);
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * % annotated disjunctions\n * 1/6::one(1); 1/6::two(1); 1/6::three(1); 1/6::four(1); 1/6::five(1); 1/6::six(1).\n * 0.15::one(2); 0.15::two(2); 0.15::three(2); 0.15::four(2); 0.15::five(2); 0.25::six(2).\n * \n * % Rules:\n * odd(X) :- one(X).\n * odd(X) :- three(X).\n * odd(X) :- five(X).\n * even(X) :- \\+ odd(X).\n * \n * query(odd(1)).\n * query(even(1)).\n * query(odd(2)).\n * query(even(2)).\n */\n\npublic model Dice2() {\n    double[] p_die1 = new double[6] <~ 1/6;\n    double[] p_die2 = {0.15, 0.15, 0.15, 0.15, 0.15, 0.25};\n    \n    int die1 = categorical(p_die1).sample() + 1;\n    int die2 = categorical(p_die2).sample() + 1;\n    \n    boolean odd1 = odd(die1);\n    boolean even1 = even(die1);\n    boolean odd2 = odd(die2);\n    boolean even2 = even(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
	}
}