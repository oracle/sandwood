package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray4$CoreInterface {
	private double[][] a;
	private double[] b;
	private double[] cv$var45$stateProbabilityGlobal;
	private double[] cv$var48$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var44;
	private double logProbability$var47;
	private double logProbability$var49;
	private double logProbability$var62;
	private double logProbability$y;
	private int[] obs;
	private int[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[] get$b() {
		return b;
	}

	@Override
	public final double[] get$d() {
		return d;
	}

	@Override
	public final void set$d(double[] cv$value) {
		d = cv$value;
		setFlag$d = true;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample64 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample50 = (fixedFlag$sample47 && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample64 = (fixedFlag$sample50 && fixedProbFlag$sample64);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int[] get$obs() {
		return obs;
	}

	@Override
	public final void set$obs(int[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample64 = false;
	}

	@Override
	public final int[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(int[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample50 = false;
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = y;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(b[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$y = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = d;
				{
					{
						double[] var46 = a[y];
						int lengthCV$a$48_15 = -1;
						{
							if((1 == y))
								lengthCV$a$48_15 = 3;
						}
						{
							if((0 == y))
								lengthCV$a$48_15 = 2;
						}
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var46, lengthCV$a$48_15));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$d = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$d;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var47 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < length$obs_measured; var61 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = obs[var61];
					{
						{
							int lengthCV$a$48_16 = -1;
							{
								if((1 == y))
									lengthCV$a$48_16 = 3;
							}
							{
								if((0 == y))
									lengthCV$a$48_16 = 2;
							}
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_16))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var49 = cv$sampleAccumulator;
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedFlag$sample50);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var62;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var49 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample47() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double[] cv$stateProbabilityLocal = cv$var45$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			y = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$b;
				{
					cv$temp$0$b = b;
				}
				int cv$temp$1$$var215;
				{
					cv$temp$1$$var215 = 2;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var215))?Math.log(cv$temp$0$b[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$y$3_1 = cv$currentValue;
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								{
									{
										{
											double[] cv$temp$2$var46;
											{
												double[] var46 = a[traceTempVariable$y$3_1];
												cv$temp$2$var46 = var46;
											}
											int cv$temp$3$$var217;
											{
												int lengthCV$a$48_12 = -1;
												{
													if((1 == traceTempVariable$y$3_1))
														lengthCV$a$48_12 = 3;
												}
												{
													if((0 == traceTempVariable$y$3_1))
														lengthCV$a$48_12 = 2;
												}
												int $var217 = lengthCV$a$48_12;
												cv$temp$3$$var217 = $var217;
											}
											if(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(d, cv$temp$2$var46, cv$temp$3$$var217)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(d, cv$temp$2$var46, cv$temp$3$$var217)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(d, cv$temp$2$var46, cv$temp$3$$var217));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(d, cv$temp$2$var46, cv$temp$3$$var217)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(d, cv$temp$2$var46, cv$temp$3$$var217)));
											}
											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
										}
									}
								}
							}
							cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		y = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
	}

	private final void sample50() {
		double[] cv$targetLocal = d;
		double[] cv$countLocal = cv$var48$countGlobal;
		int lengthCV$a$48_13 = -1;
		{
			if((1 == y))
				lengthCV$a$48_13 = 3;
		}
		{
			if((0 == y))
				lengthCV$a$48_13 = 2;
		}
		int cv$arrayLength = lengthCV$a$48_13;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					{
						for(int var61 = 0; var61 < length$obs_measured; var61 += 1)
							cv$countLocal[obs[var61]] = (cv$countLocal[obs[var61]] + 1.0);
					}
				}
			}
		}
		int lengthCV$a$48_14 = -1;
		{
			if((1 == y))
				lengthCV$a$48_14 = 3;
		}
		{
			if((0 == y))
				lengthCV$a$48_14 = 2;
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$countLocal, cv$targetLocal, lengthCV$a$48_14);
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var45$stateProbabilityGlobal = new double[2];
		}
		{
			int cv$var33$max = 2;
			cv$var33$max = Math.max(cv$var33$max, 3);
			cv$var48$countGlobal = new double[cv$var33$max];
		}
	}

	@Override
	public final void allocator() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		{
			b = new double[2];
		}
		if(!setFlag$d) {
			{
				int lengthCV$a$48_11 = -1;
				{
					if((1 == y))
						lengthCV$a$48_11 = 3;
				}
				{
					if((0 == y))
						lengthCV$a$48_11 = 2;
				}
				d = new double[lengthCV$a$48_11];
			}
		}
		if(!setFlag$obs) {
			{
				obs = new int[length$obs_measured];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		int lengthCV$a$48_17 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_17 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_17 = 2;
			}
		}
		if(!fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_17, d);
		int lengthCV$a$48_18 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample64)
					lengthCV$a$48_18 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample64)
					lengthCV$a$48_18 = 2;
			}
		}
		int lengthCV$a$48_18$1 = lengthCV$a$48_18;
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1) {
						if(!fixedFlag$sample64)
							obs[var61] = DistributionSampling.sampleCategorical(RNG$1, d, lengthCV$a$48_18$1);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		int lengthCV$a$48_20 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_20 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_20 = 2;
			}
		}
		if(!fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_20, d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		int lengthCV$a$48_19 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_19 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_19 = 2;
			}
		}
		if(!fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_19, d);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample47)
				sample47();
			if(!fixedFlag$sample50)
				sample50();
		} else {
			if(!fixedFlag$sample50)
				sample50();
			if(!fixedFlag$sample47)
				sample47();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var5 = a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		b[0] = 0.35;
		b[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$y = 0.0;
		logProbability$var47 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$d = 0.0;
		logProbability$var49 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		int lengthCV$a$48_21 = -1;
		{
			if((1 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_21 = 3;
			}
		}
		{
			if((0 == y)) {
				if(!fixedFlag$sample50)
					lengthCV$a$48_21 = 2;
			}
		}
		if(!fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_21, d);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[] cv$source1 = obs_measured;
		int[] cv$target1 = obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}