package coinFlips;

import org.sandwood.runtime.model.ExecutionTarget;

class Flips1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flips1$CoreInterface {
	private boolean coin1;
	private boolean coin2;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean fixedProbFlag$sample6 = false;
	private double heads1;
	private double heads2;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$coin1;
	private double logProbability$coin2;
	private double logProbability$twoHeads;
	private double logProbability$var3;
	private double logProbability$var5;
	private boolean system$gibbsForward = true;
	private boolean twoHeads;

	public Flips1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$coin1() {
		return coin1;
	}

	@Override
	public final void set$coin1(boolean calculationVariable$value) {
		coin1 = calculationVariable$value;
	}

	@Override
	public final boolean get$coin2() {
		return coin2;
	}

	@Override
	public final void set$coin2(boolean calculationVariable$value) {
		coin2 = calculationVariable$value;
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean calculationVariable$value) {
		fixedFlag$sample4 = calculationVariable$value;
		fixedProbFlag$sample4 = (fixedFlag$sample4 && fixedProbFlag$sample4);
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean calculationVariable$value) {
		fixedFlag$sample6 = calculationVariable$value;
		fixedProbFlag$sample6 = (fixedFlag$sample6 && fixedProbFlag$sample6);
	}

	@Override
	public final double get$heads1() {
		return heads1;
	}

	@Override
	public final double get$heads2() {
		return heads2;
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
	public final double get$logProbability$coin1() {
		return logProbability$coin1;
	}

	@Override
	public final double get$logProbability$coin2() {
		return logProbability$coin2;
	}

	@Override
	public final double get$logProbability$twoHeads() {
		return logProbability$twoHeads;
	}

	@Override
	public final boolean get$twoHeads() {
		return twoHeads;
	}

	@Override
	public final void set$twoHeads(boolean calculationVariable$value) {
		twoHeads = calculationVariable$value;
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = coin1;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, heads1));
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
			logProbability$var3 = calculationVariable$sampleAccumulator;
			logProbability$coin1 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$twoHeads = false;
			{
				if(!calculationVariable$guard$twoHeads) {
					calculationVariable$guard$twoHeads = true;
					logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$coin1;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var3 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$twoHeads = false;
			{
				if(!calculationVariable$guard$twoHeads) {
					calculationVariable$guard$twoHeads = true;
					logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$sampleAccumulator = 0.0;
			double calculationVariable$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double calculationVariable$probabilityReached = 0.0;
			{
				boolean calculationVariable$sampleValue = coin2;
				{
					{
						double calculationVariable$weightedProbability = (Math.log(1.0) + org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(calculationVariable$sampleValue, heads2));
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
			logProbability$var5 = calculationVariable$sampleAccumulator;
			logProbability$coin2 = calculationVariable$sampleProbability;
			boolean calculationVariable$guard$twoHeads = false;
			{
				if(!calculationVariable$guard$twoHeads) {
					calculationVariable$guard$twoHeads = true;
					logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			double calculationVariable$accumulator = 0.0;
			double calculationVariable$RVaccumulator = 0.0;
			double calculationVariable$sampleValue = logProbability$coin2;
			calculationVariable$RVaccumulator = (calculationVariable$RVaccumulator + calculationVariable$sampleValue);
			calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
			logProbability$var5 = calculationVariable$RVaccumulator;
			boolean calculationVariable$guard$twoHeads = false;
			{
				if(!calculationVariable$guard$twoHeads) {
					calculationVariable$guard$twoHeads = true;
					logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			coin1 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, heads1);
		if(!fixedFlag$sample6)
			coin2 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, heads2);
		if(!(fixedFlag$sample4 && fixedFlag$sample6))
			twoHeads = (coin1 && coin2);
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
		heads1 = 0.5;
		heads2 = 0.6;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var3 = 0.0;
		logProbability$twoHeads = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$coin1 = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$coin2 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void setIntermediates() {
		if(true)
			twoHeads = (coin1 && coin2);
	}

	@Override
	public String modelCode() {
		return "package coinFlips;\n\n/*\n * % Probabilistic facts:\n * 0.5::heads1.\n * 0.6::heads2.\n *\n * % Rules:\n * twoHeads :- heads1, heads2.\n *\n * % Queries:\n * query(heads1).\n * query(heads2).\n * query(twoHeads).\n */\n\npublic model Flips1() {\n    double heads1 = 0.5;\n    double heads2 = 0.6;\n    boolean coin1 = bernoulli(heads1).sample();\n    boolean coin2 = bernoulli(heads2).sample();\n    boolean twoHeads = coin1 && coin2;\n}";
	}
}