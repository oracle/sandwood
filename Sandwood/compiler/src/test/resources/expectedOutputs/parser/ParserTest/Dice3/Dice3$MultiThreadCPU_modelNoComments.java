package dice;

import org.sandwood.runtime.model.ExecutionTarget;

class Dice3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Dice3$CoreInterface {
	private int die1;
	private int die2;
	private boolean even;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample15 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$die1;
	private double logProbability$die2;
	private double logProbability$even;
	private double logProbability$odd;
	private double logProbability$sum;
	private double logProbability$var10;
	private double logProbability$var11;
	private double logProbability$var14;
	private double logProbability$var15;
	private boolean odd;
	private double[] p_die;
	private int sum;
	private boolean system$gibbsForward = true;

	public Dice3$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$even() {
		return even;
	}

	@Override
	public final void set$even(boolean calculationVariable$value) {
		even = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean calculationVariable$value) {
		fixedFlag$sample11 = calculationVariable$value;
		fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean calculationVariable$value) {
		fixedFlag$sample15 = calculationVariable$value;
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
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
	public final double get$logProbability$even() {
		return logProbability$even;
	}

	@Override
	public final double get$logProbability$odd() {
		return logProbability$odd;
	}

	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	@Override
	public final boolean get$odd() {
		return odd;
	}

	@Override
	public final void set$odd(boolean calculationVariable$value) {
		odd = calculationVariable$value;
	}

	@Override
	public final double[] get$p_die() {
		return p_die;
	}

	@Override
	public final int get$sum() {
		return sum;
	}

	@Override
	public final void set$sum(int calculationVariable$value) {
		sum = calculationVariable$value;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				int calculationVariable$sampleValue = (die1 - 1);
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die));
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
			logProbability$var10 = calculationVariable$sampleAccumulator;
			logProbability$var11 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$sum = false;
			boolean calculationVariable$guard$even = false;
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$sum) {
					calculationVariable$guard$sum = true;
					logProbability$sum = (logProbability$sum + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even) {
					calculationVariable$guard$even = true;
					logProbability$even = (logProbability$even + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var11;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var10 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$sum = false;
			boolean calculationVariable$guard$even = false;
			logProbability$die1 = (logProbability$die1 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$sum) {
					calculationVariable$guard$sum = true;
					logProbability$sum = (logProbability$sum + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$even) {
					calculationVariable$guard$even = true;
					logProbability$even = (logProbability$even + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				int calculationVariable$sampleValue = (die2 - 1);
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityCategorical(calculationVariable$sampleValue, p_die));
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
			logProbability$var14 = calculationVariable$sampleAccumulator;
			logProbability$var15 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$sum = false;
			boolean calculationVariable$guard$odd = false;
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$sum) {
					calculationVariable$guard$sum = true;
					logProbability$sum = (logProbability$sum + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$odd) {
					calculationVariable$guard$odd = true;
					logProbability$odd = (logProbability$odd + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$var15;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var14 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$sum = false;
			boolean calculationVariable$guard$odd = false;
			logProbability$die2 = (logProbability$die2 + calculationVariable$accumulator);
			{
				if(!calculationVariable$guard$sum) {
					calculationVariable$guard$sum = true;
					logProbability$sum = (logProbability$sum + calculationVariable$accumulator);
				}
			}
			{
				if(!calculationVariable$guard$odd) {
					calculationVariable$guard$odd = true;
					logProbability$odd = (logProbability$odd + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		p_die = new double[6];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			die1 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die) + 1);
		if(!fixedFlag$sample15)
			die2 = (org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleCategorical(RNG$, p_die) + 1);
		if(!(fixedFlag$sample11 && fixedFlag$sample15))
			sum = (die1 + die2);
		if(!fixedFlag$sample11)
			even = !((die1 % 2) == 1);
		if(!fixedFlag$sample15)
			odd = ((die2 % 2) == 1);
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
						p_die[var5] = (1 / 6);
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$sum = 0.0;
		logProbability$even = 0.0;
		logProbability$die1 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$die2 = 0.0;
		logProbability$odd = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$var15 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void setIntermediates() {
		if(true)
			sum = (die1 + die2);
		if(true)
			even = !((die1 % 2) == 1);
		if(true)
			odd = ((die2 % 2) == 1);
	}

	@Override
	public String modelCode() {
		return "package dice;\n\n/*\n * 1/6::dice(1,D); 1/6::dice(2,D); 1/6::dice(3,D); 1/6::dice(4,D); 1/6::dice(5,D); 1/6::dice(6,D) :- die(D).\n * \n * die(1).\n * die(2).\n * \n * sum(S) :- dice(A,1), dice(B,2), S is A+B.\n * odd(D) :- dice(1,D).\n * odd(D) :- dice(3,D).\n * odd(D) :- dice(5,D).\n * even(D) :- \\+ odd(D).\n * \n * query(sum(_)).\n * evidence(even(1)).\n * evidence(odd(2)).\n */\n\npublic model Dice3() {\n    double[] p_die = new double[6] <~ 1/6;\n    \n    int die1 = categorical(p_die).sample() + 1;\n    int die2 = categorical(p_die).sample() + 1;\n    \n    int sum = die1 + die2;\n    boolean even = even(die1);\n    boolean odd = odd(die2);\n    \n    private boolean odd(int i) {\n        return (i%2)==1;\n    }\n    \n    private boolean even(int i) {\n        return !odd(i);\n    }\n}";
	}
}